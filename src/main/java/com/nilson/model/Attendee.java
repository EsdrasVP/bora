package com.nilson.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nilson.util.ApplicationConstants;

public class Attendee {

	private String email;
	private String name;

	public Attendee(String email, String name) {
		this.email = email;
		this.name = name;
	}
	
	public JSONObject toJson() {
		JSONObject toJson = new JSONObject();
		toJson.put(ApplicationConstants.ATTENDEE_EMAIL_KEY_JSON, email).put(
				ApplicationConstants.ATTENDEE_NAME_KEY_JSON, name);
		return toJson;
	}
	
	public static JSONArray toJson(List<Attendee> attendees) {
		JSONArray attendeesJsonArray = new JSONArray();
		for (Attendee attendee : attendees) {
			attendeesJsonArray.put(attendee.toJson());
		}
		return attendeesJsonArray;
	}	
	
	public static Attendee fromJson(JSONObject jsonObject) {		
		String name = jsonObject.optString(ApplicationConstants.ATTENDEE_NAME_KEY_JSON);
		
		return new Attendee("", name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}