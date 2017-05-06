package com.nilson.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nilson.util.ApplicationConstants;

public class Event implements Serializable {

	private UUID id;
	private String name;
	private Date date;
	private String local;
	private List<Attendee> attendees;
	private List<String> photoNames;

	private static final long serialVersionUID = -6111900503095749695L;

	public Event(String name, String local, Date date) {
		this(UUID.randomUUID(), name, local, date, new ArrayList<Attendee>(),
				new ArrayList<String>());
	}

	public Event(UUID id, String name, String local, Date date, List<Attendee> attendees,
			List<String> photoNames) {
		this.id = id;
		this.name = name;
		this.local = local;
		this.date = date;
		this.attendees = attendees;
		this.photoNames = photoNames;
	}

	public JSONObject toJson() {
		JSONObject toJson = new JSONObject();
		toJson.put(ApplicationConstants.EVENT_ID_KEY_JSON, id)
				.put(ApplicationConstants.EVENT_NAME_KEY_JSON, name)
				.put(ApplicationConstants.EVENT_LOCAL_KEY_JSON, local)
				.put(ApplicationConstants.EVENT_DATE_KEY_JSON, date.getTime())
				.put(ApplicationConstants.EVENT_ATTENDEES_KEY_JSON, Attendee.toJson(attendees))
				.put(ApplicationConstants.EVENT_PHOTOS_KEY_JSON, photoNames);
		return toJson;
	}
	
	public static JSONArray toJson(List<Event> events) {
		JSONArray eventsJsonArray = new JSONArray();
		for (Event event : events) {
			eventsJsonArray.put(event.toJson());
		}
		return eventsJsonArray;
	}
	
	public static Event fromJson(JSONObject jsonObject) {		
		String name = jsonObject.optString(ApplicationConstants.EVENT_NAME_KEY_JSON);
		String local = jsonObject.optString(ApplicationConstants.EVENT_LOCAL_KEY_JSON);
		
		return new Event(name, local, new Date());
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}