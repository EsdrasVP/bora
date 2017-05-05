package com.nilson.util;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {

	public static List<Long> toLongList(JSONArray json) {
		List<Long> list = new LinkedList<Long>();
		for (int i = 0; i < json.length(); i++) {
			list.add(json.optLong(i));
		}
		return list;
	}
	
	public static String formatPriceValue(Double price){
		DecimalFormat df = new DecimalFormat("#######0.00");
		return df.format(price);
	}
	
	public static String formatPriceValueBr(Double price){
		return formatPriceValue(price).replace(".", ",");
	}
	
	public static Double getDoubleFromJsonAttrib(JSONObject json, String att){
		String value = json.optString(att);
		try{
			return new Double(value);
		}catch(Exception e){
			return new Double(0.00);
		}
	}
	
	
}
