package com.example.springboot.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.tomcat.util.descriptor.web.MessageDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * MD5加密
 * @author Administrator
 *
 */
@Service
public class encrypt {

	static final Logger log = LoggerFactory.getLogger(encrypt.class);
	
	@Value("${shaseng.appid}")
	private String appId;
	
	@Value("${shaseng.noncestr}")
	private String nonceStr;
	
	@Value("${shaseng.secret}")
	private String secreat;
	
	public String sign(Map<String, String> map){
		//增加时间戳
		if(!map.containsKey("created")){
			map.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		map.put("app_id", appId);
		map.put("nonce_str",nonceStr);
		String signed = singed(map);
		map.put("sign", signed);
		map.put("sign_type", "MD5");
		SortedMap<String, String> sort = new TreeMap<String,String>(map);
		return spliceUrl(sort);
	}
	
	/**
	 * 获取签名
	 * @param params
	 * @return
	 */
	public String singed(Map<String,String> params){
		SortedMap<String, String> sort = new TreeMap<String,String>(params);
		//加密规则：把第三方给的密钥拼在需要排好序的url后面 然后整体进行MD5加密 获得固定长度的签名
		String toSign = spliceUrl(sort)+secreat;
		log.info("toSign:"+toSign);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(toSign.getBytes("UTF-8"));
			byte[] bArray = md.digest();
			String singed = byte2String(bArray);
			log.info("singed:"+singed);
//			return new BigInteger(1,bArray).toString(16);
			return singed;
		} catch (NoSuchAlgorithmException e) {
			log.info(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage());
		}
		return toSign;
	}
	
	
	private String spliceUrl(SortedMap<String, String> map){
		if(map.isEmpty()){
			return "";
		}
		StringBuffer buf = new StringBuffer();
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			buf.append(entry.getKey());
			buf.append("=");
			buf.append(entry.getValue());
			if(it.hasNext()){
				buf.append("&");
			}
		}
		return buf.toString();
	}
	
	private String byte2String(byte[] bArray){
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for(int i = 0;i< bArray.length;i++){
			sTemp = Integer.toHexString(bArray[i] & 0xFF);
			if(sTemp.length() < 2){
				sb.append(0);
				sb.append(sTemp);
			}else {
				sb.append(sTemp);
			}
		}
		return sb.toString();
	}
}
