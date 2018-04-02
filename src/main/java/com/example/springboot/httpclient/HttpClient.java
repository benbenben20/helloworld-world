package com.example.springboot.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HttpClient {

	private static SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();

	private final String SECREAT = "2d3f814b372adf18835be86150b4e571";
	private final String APPID = "SsI9Pgy25H6gG8U3LT";
	private final String NONCESTR = "13E2B1E673C442DB453DC0DA47A6C31D";
	
	public String myPostTest() {
		String url = "http://api.shasenggujia.com/evaluation/create";
		// 创建默认的httpclient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建post
		HttpPost post = new HttpPost(url);
		// 添加请求资源
		List<NameValuePair> prarm = new ArrayList<NameValuePair>();
		prarm.add(new BasicNameValuePair("vin", "LFV2B28V6E5004981"));
		prarm.add(new BasicNameValuePair("spec_id", "1"));
		prarm.add(new BasicNameValuePair("register_date", "2015-05"));
		prarm.add(new BasicNameValuePair("city_id", "110100"));
		prarm.add(new BasicNameValuePair("mileage", "10000"));
		prarm.add(new BasicNameValuePair("service", "1"));
		prarm.add(new BasicNameValuePair("transfer_times", "1"));
		prarm.add(new BasicNameValuePair("color", "红色"));
		prarm.add(new BasicNameValuePair("policy_company_id", "1"));
		prarm.add(new BasicNameValuePair("policy_number", "PDZA20141102T000210305"));
		prarm.add(new BasicNameValuePair("policy_identify", "23028119811216042X"));
		prarm.add(new BasicNameValuePair("plate_number", "沪A88888"));
		prarm.add(new BasicNameValuePair("engine_number", "CA4GD1"));
		prarm.add(new BasicNameValuePair("trade_no", "998"));
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		prarm.add(new BasicNameValuePair("created", now));
		
		Map<String,String> params = new HashMap<>();
//		singed(params)
		
		
		prarm.add(new BasicNameValuePair("sign_type", "MD5"));
		prarm.add(new BasicNameValuePair("sign", "0543c00d30c2eac0fbf555e99ae696d1"));
		// 设置请求资源编码
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(prarm, "UTF-8");
			// 封装post请求
			post.setEntity(entity);
			// 接收返回
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(post);
				if (response.getEntity() != null) {
					log.info("=========response:" + response);
					log.info("=========EntityUtils.toString(xxx,utf-8):" + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("#########################");
					System.out.println("=========response:" + response);
					System.out.println(
							"=========EntityUtils.toString(xxx,utf-8):" + EntityUtils.toString(entity, "UTF-8"));
					return EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		} catch (ClientProtocolException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}

	public String myGetTest(Map<String, String> map) {
		String url = "http://api.shasenggujia.com/evaluation/create?";
		CloseableHttpClient client = HttpClients.createDefault();
		StringBuilder sb = new StringBuilder(url);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpGet get = new HttpGet(sb.toString());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			log.info("*****返回状态：" + response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				log.info("EntityUtils.toString(xxx):" + EntityUtils.toString(entity, "UTF-8"));
				return EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return null;
	}

	
	/**
	 * postSSL:(https协议,post方式发送K-V参数到指定url,返回响应报文)
	 * @param submitUrl
	 * @param params
	 * @return
	 */
	public static String postSSL(String submitUrl, Map<String, Object> params) {

		HttpsURLConnection conn;

		String response = "";
		try {
			String urlString = submitUrl;

			URL url = new URL(urlString);
			// 组织请求参数
			StringBuilder postBody = new StringBuilder();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue() == null) {

					continue;
				}
				postBody.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue().toString(), "utf-8")).append("&");
			}

			if (!params.isEmpty()) {
				postBody.deleteCharAt(postBody.length() - 1);
			}

			conn = (HttpsURLConnection) url.openConnection();

			// 设置https
			conn.setSSLSocketFactory(ssf);
			// 设置长链接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置连接超时
			conn.setConnectTimeout(5000);
			// 设置读取超时
			conn.setReadTimeout(5000);
			// 提交参数
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			/**********************************************************************************************/
			// System.out.println("请求地址:" +submitUrl+"?"+ postBody.toString());
			/**********************************************************************************************/
			OutputStream out = conn.getOutputStream();

			out.write(postBody.toString().getBytes());
			conn.getOutputStream().flush();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuilder result = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					result.append(line).append("\n");
				}
				/**********************************************************************************************/
				// System.out.println("responseResult:" + result);
				/**********************************************************************************************/
				response = result.toString();
				return response;
			} else {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuilder result = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					result.append(line).append("\n");
				}
				// System.out.println("responseResult:" + result);
				response = result.toString();
				return response;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	
	public String sign(Map<String, String> map){
		//增加时间戳
		if(!map.containsKey("created")){
			map.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		map.put("app_id", APPID);
		map.put("nonce_str",NONCESTR);
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
		String toSign = spliceUrl(sort)+SECREAT;
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
