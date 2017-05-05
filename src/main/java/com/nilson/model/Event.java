package com.nilson.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;

import com.nilson.util.BoraApplicationConstants;

public class Event implements Serializable {

	private UUID id;
	private String name;
	private Date date;
	private List<Attendee> attendees;
	private List<String> photoNames;

	private static final long serialVersionUID = -6111900503095749695L;

	public Event(String name, Date date) {
		this(UUID.randomUUID(), name, date, new ArrayList<Attendee>());
	}

	public Event(UUID id, String name, Date date, List<Attendee> attendees) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.attendees = attendees;
	}
	
	public JSONObject toJson(JSONObject attrToFilterJsonObject) {
		JSONObject toJson = new JSONObject();
		toJson.put(BoraApplicationConstants.EVENT_ID_KEY_JSON, id)
				.put(BoraApplicationConstants.EVENT_NAME_KEY_JSON, name)
				.put(BoraApplicationConstants.EVENT_DATE_KEY_JSON, date)
				.put(BoraApplicationConstants.EVENT_ATTENDEES_KEY_JSON, attendees)
				.put(BoraApplicationConstants.EVENT_PHOTOS_KEY_JSON, photoNames);
		return toJson;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}
	
	public List<String> getPhotoNames() {
		return photoNames;
	}
	
	public void setPhotoNames(List<String> photoNames) {
		this.photoNames = photoNames;
	}
}