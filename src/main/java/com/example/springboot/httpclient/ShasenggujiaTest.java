package com.example.springboot.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShasenggujiaTest {

	private static String APP_SECRET = "6e206013d1419c56eb4975f451b3fe3906e7119d";
	private static String APP_KEY = "80f49526dd923d02b979d9c105046757";
	private static String NONCESTR = "13E2B1E673C442DB453DC0DA47A6C31D";
	private static String url = "http://data.shasenggujia.com/api/open/evaluation/add";

	public static void main(String[] args) {
		// 创建订单
		Map<String, String> creatOrderMap = new HashMap<String, String>();
		//必输
		creatOrderMap.put("app_key", APP_KEY);
		creatOrderMap.put("nonce_str", NONCESTR);
		creatOrderMap.put("vin", "LFV2B28V6E5004981");
		creatOrderMap.put("carBrand", "39");
		creatOrderMap.put("carSerial", "453");
		creatOrderMap.put("carType", "562049");
		creatOrderMap.put("date", "2018-3");//上牌日期Y-m（2018-3
		creatOrderMap.put("areaCode", "220200");//车辆属地 城市ID 传二级id
//		creatOrderMap.put("city", "110100");
		creatOrderMap.put("trade_sn", "GJ-180319-00003");//要求32个字符内，只能是数字大小写字母_-
//		creatOrderMap.put("vin", "LFV2B28V6E5004981");
//		creatOrderMap.put("vin", "LFMAPE2C0C0392063");
//		creatOrderMap.put("spec_id", "1");
//		creatOrderMap.put("spec_id", "6816");
//		creatOrderMap.put("register_date", "2015-05");
//		creatOrderMap.put("register_date", "2017-12");
//		creatOrderMap.put("city_id", "110100");
//		creatOrderMap.put("city_id", "140600");
//		creatOrderMap.put("mileage", "10000");
//		creatOrderMap.put("mileage", "110570");
//		creatOrderMap.put("service", "1");
//		creatOrderMap.put("service", "0");
//		creatOrderMap.put("transfer_times", "1");
//		creatOrderMap.put("transfer_times", "1");
//		creatOrderMap.put("color", "红色");
//		creatOrderMap.put("color", "黑");
//		creatOrderMap.put("policy_company_id", "1");
//		creatOrderMap.put("policy_company_id", "1");
//		creatOrderMap.put("policy_number", "PDZA20141102T000210305");
//		creatOrderMap.put("policy_number", "PDZA20141102T000210305");
//		creatOrderMap.put("policy_identify", "23028119811216042X");
//		creatOrderMap.put("policy_identify", "23028119811216042X");
//		creatOrderMap.put("plate_number", "沪A88888");
//		creatOrderMap.put("plate_number", "苏BC101S");
//		creatOrderMap.put("engine_number", "CA4GD1");
//		creatOrderMap.put("engine_number", "F035828");
//		creatOrderMap.put("trade_no", "TN-180211-00005");
//		creatOrderMap.put("trade_no", "99123456");
//		creatOrderMap.put("nonce_str", "13E2B1E673C442DB453DC0DA47A6C31D");
//		creatOrderMap.put("nonce_str", "13E2B1E673C442DB453DC0DA47A6C31D");
//		creatOrderMap.put("app_id", "SsI9Pgy25H6gG8U3LT");
//		creatOrderMap.put("app_id", "SsI9Pgy25H6gG8U3LT");
//		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		creatOrderMap.put("created", now);
//		creatOrderMap.put("created", "2018-03-07 10:54:58");
		String signed = singed(creatOrderMap);
		System.out.println("signed:" + signed.toUpperCase());
		creatOrderMap.put("sign", signed.toUpperCase());
		String resp = postForm(url, creatOrderMap);
		System.out.println("resp:" + resp);
		
		
		
//		String resp = " {\"code\":200,\"orderExt\":{\"id\":1847,\"code\":\"66wHR5pujT\",\"recommend_price\":15.94,\"app_order_id\":\"998\",\"created_at\":\"2018-01-29 10:13:19\",\"historyPriceTrend\":[\"16.39\",\"16.30\",\"16.20\",\"16.12\",\"16.03\",\"15.94\"],\"futurePriceTrend\":[\"15.94\",\"14.84\",\"13.81\"]}} ";                  
//		
//		JSONObject fromObject = JSONObject.fromObject(resp);
//		Integer code = fromObject.getInt("code");
//		JSONObject result = (JSONObject) fromObject.get("orderExt");
//		
//		System.out.println(result.get("app_order_id"));
//		System.out.println(fromObject.getString("orderExt"));
		
		
		
		
		
		
		
	}

	public static String postForm(String url, Map<String, String> params) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			System.out.println("key:"+entry.getKey()+"-value:"+entry.getValue());
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}

		return null;

	}

	public static String sign(Map<String, String> map) {
		// 增加时间戳
		if (!map.containsKey("created")) {
			map.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		map.put("app_id", APP_KEY);
		map.put("nonce_str", NONCESTR);
		String signed = singed(map);
		map.put("sign", signed);
		map.put("sign_type", "MD5");
		SortedMap<String, String> sort = new TreeMap<String, String>(map);
		return spliceUrl(sort);
	}

	/**
	 * 获取签名
	 * 
	 * @param params
	 * @return
	 */
	public static String singed(Map<String, String> params) {
		SortedMap<String, String> sort = new TreeMap<String, String>(params);
		// 加密规则：把第三方给的密钥拼在需要排好序的url后面 然后整体进行MD5加密 获得固定长度的签名
		String toSign = spliceUrl(sort) + "&key="+ APP_SECRET;
		// log.info("toSign:" + toSign);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(toSign.getBytes("UTF-8"));
			byte[] bArray = md.digest();
			String singed = byte2String(bArray);
			// log.info("singed:" + singed);
			// return new BigInteger(1,bArray).toString(16);
			return singed;
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return toSign;
	}

	private static String spliceUrl(SortedMap<String, String> map) {
		if (map.isEmpty()) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			buf.append(entry.getKey());
			buf.append("=");
			buf.append(entry.getValue());
			if (it.hasNext()) {
				buf.append("&");
			}
		}
		return buf.toString();
	}

	private static String byte2String(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(bArray[i] & 0xFF);
			if (sTemp.length() < 2) {
				sb.append(0);
				sb.append(sTemp);
			} else {
				sb.append(sTemp);
			}
		}
		return sb.toString();
	}

}
