package com.nilson.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nilson.model.Attendee;
import com.nilson.model.Event;

public class BoraController {
	
	private List<Event> events;
    private static final Logger LOGGER = Logger.getLogger(BoraController.class);
    
	public BoraController() {
		this.events = new ArrayList<Event>();
	}
	
	protected boolean createEvent(String eventName, Date eventDate) {
		LOGGER.debug("Creating event " + eventName);
		
		if(!validateEventParams(eventName, eventDate)) {
			return false;
		}
		
		events.add(new Event(eventName, eventDate));
		return true;
	}
	
	protected boolean validateEventParams(String eventName, Date eventDate) {
		if(eventName == null || eventName.isEmpty()) {
			LOGGER.error("Event name is invalid");
			return false;
		}
		
		if(eventDate == null) {
			LOGGER.error("Event date is invalid");
			return false;
		}
		
		return true;
	}
	
	protected boolean addAttendeeToEvent(String attendeeEmail, String attendeeName, Event event) {
		LOGGER.debug("Adding attendee " + attendeeName + " to event " + event.getName());
		
		for(Event existingEvent : events) {
			if(existingEvent.equals(event)) {
				existingEvent.getAttendees().add(new Attendee(attendeeEmail, attendeeName));
				return true;
			}
		}
		
		LOGGER.debug("Event " + event.getName() + " does not exist");
		return false;
	}
	
	protected List<Attendee> getEventAttendees(Event event) {
		LOGGER.debug("Getting list of attendees for event " + event);
		
		for(Event existingEvent : events) {
			if(existingEvent.equals(event)) {
				return existingEvent.getAttendees();
			}
		}
		
		LOGGER.debug("Event " + event.getName() + " does not exist");
		return null;
	}
}