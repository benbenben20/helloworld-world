package com.example.springboot.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.springboot.mybatis.mapper.ProCityAreaMapper;
import com.example.springboot.mybatis.model.ProCityAreaModel;
import com.mysql.fabric.xmlrpc.base.Array;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Slf4j
public class CityJob {
	
	private ProCityAreaMapper proCityAreaMapper;

	public String readToString(String fileName) throws IOException {  
        File file = new File(fileName);  
        StringBuffer filecontent = new StringBuffer();
        FileInputStream in = new FileInputStream(file);  
        InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
        String text = null;
        while((text = bufferedReader.readLine()) != null){
        	String temp = text;
        	temp.replace("\r", "");
        	temp.replace("\t", "");
        	temp.replace("\n", "");
        	temp.replace("\r\n", "");
        	//去除BOM头
        	temp = temp.replaceAll("\ufeff", "");
        	filecontent.append(temp.trim());
        }
        return filecontent.toString();
    } 
	
	public static void main(String[] args) {
		CityJob cityBatch = new CityJob();
		String file = "C://Users/Administrator/Desktop/CITY.txt";
		String cityList = "";
		try {
			cityList = cityBatch.readToString(file);
		} catch (IOException e) {
			log.info(e.getMessage());
		}
//        String json = cityList.toString().trim().substring(1);
		JSONObject jsonObject = JSONObject.fromObject(cityList);
		JSONArray array = (JSONArray) jsonObject.get("message");
		
//		System.out.println(array.toString());
		List<ProCityAreaModel> list = new ArrayList<ProCityAreaModel>();
		
		for(int i = 0;i < array.size() ;i++){
			JSONObject jo = JSONObject.fromObject(array.get(i));
			Integer parentId = (Integer) jo.get("id");
			String parentName = (String) jo.get("name");
			Integer childId = (Integer) JSONObject.fromObject(jo.get("cities")).get("id");
			String childName = (String) JSONObject.fromObject(jo.get("cities")).get("name");
			
			
			//子集数据
			if("01".equals(String.valueOf(childId).substring(2,4))){
				ProCityAreaModel model = new ProCityAreaModel();
				model.setParentId("0");
				model.setCityId(String.valueOf(childId));
				model.setCityName(parentName);
				model.setCreateTime(new Date());
				System.out.println("parentId:"+model.getParentId()+" _cityId:"+model.getCityId()+" _cityName:"+model.getCityName());
				list.add(model);
			}
			ProCityAreaModel model = new ProCityAreaModel();
			model.setParentId(String.valueOf(parentId));
			model.setCityId(String.valueOf(childId));
			model.setCityName(childName);
			model.setCreateTime(new Date());
			System.out.println("parentId:"+model.getParentId()+" _cityId:"+model.getCityId()+" _cityName:"+model.getCityName());
			list.add(model);
//			System.out.println(array.get(i)+"\n");
		}
		
		System.err.println(list.size()+"");
		
	}
	
	
	
	
	
}
