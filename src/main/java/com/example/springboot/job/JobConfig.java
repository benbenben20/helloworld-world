package com.example.springboot.job;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.spring.namespace.parser.common.AbstractJobConfigurationDto;
import com.dangdang.ddframe.job.lite.spring.schedule.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

@Configuration
@EnableConfigurationProperties(JobProperties.class)
public class JobConfig implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Autowired
	private JobProperties jobProperties;
	
	@Override
	public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException {
		this.applicationContext = paramApplicationContext;
	}

	@Bean
	public ZookeeperConfiguration zookeeperConfiguration(){
		ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(jobProperties.getServerList(),jobProperties.getNamespace());
		zookeeperConfiguration.setBaseSleepTimeMilliseconds(jobProperties.getBaseSleepTimeMilliseconds());
		zookeeperConfiguration.setMaxRetries(jobProperties.getMaxRetries());
		zookeeperConfiguration.setMaxSleepTimeMilliseconds(jobProperties.getMaxSleepTimeMilliseconds());
		return zookeeperConfiguration;
	}
	
    @PreDestroy
    public void shutdownDestroy() {
        //销毁调度器
        String[] jobSchedulerNames = applicationContext.getBeanNamesForType(JobScheduler.class);
        for (String jobSchedulerName : jobSchedulerNames) {
            JobScheduler jobScheduler = (JobScheduler) applicationContext.getBean(jobSchedulerName);
            jobScheduler.shutdown();
        }
    }
	
    @Bean(destroyMethod = "close")
	@Order(Integer.MAX_VALUE)
	public CoordinatorRegistryCenter regCenter(ZookeeperConfiguration zookeeperConfiguration) {
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
		regCenter.init();
		Map<String, SimpleJob> simpleMap = applicationContext.getBeansOfType(SimpleJob.class);
		int i=0;
		for (Entry<String, SimpleJob> simpleEntry : simpleMap.entrySet()) {
			final Class<? extends SimpleJob> cls = simpleEntry.getValue().getClass();
			MySimpleJob sj = (MySimpleJob) cls.getAnnotation(MySimpleJob.class);
			SpringJobScheduler jobScheduler=	new SpringJobScheduler(regCenter, new AbstractJobConfigurationDto(sj.value(),  sj.cron(), sj.shardingTotalCount()) {			
				@Override
				protected JobTypeConfiguration toJobConfiguration(JobCoreConfiguration jobCoreConfig) {
					return new SimpleJobConfiguration(jobCoreConfig, cls.getCanonicalName());
				}
			}, new ElasticJobListener[]{});
			jobScheduler.setApplicationContext(this.applicationContext);
			  DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
	            String beanName = JobScheduler.class.getCanonicalName() + "$" + i;
	            beanFactory.registerSingleton(beanName, jobScheduler);
			jobScheduler.init();
			i++;
			
		}

		return regCenter;
	}
}
