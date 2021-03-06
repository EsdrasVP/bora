package com.nilson.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.nilson.model.Attendee;
import com.nilson.model.Event;

public class ApplicationController {

	private List<Event> events;
	private static final Logger LOGGER = Logger.getLogger(ApplicationController.class);

	public ApplicationController() {
		this.events = new ArrayList<Event>();
	}

	public Event createEvent(String eventName, String local, Date eventDate) {
		LOGGER.debug("Creating event " + eventName);
		if (!validateEventParams(eventName, eventDate)) {
			return null;
		}

		Event event = new Event(eventName, local, eventDate);
		events.add(event);
		return event;
	}

	public boolean validateEventParams(String eventName, Date eventDate) {
		if (eventName == null || eventName.isEmpty()) {
			LOGGER.error("Event name cannot be null/empty");
			return false;
		}

		if (eventDate == null) {
			LOGGER.error("Event date cannot be null");
			return false;
		}

		return true;
	}

	public boolean addAttendeeToEvent(String attendeeEmail,
			String attendeeName, String eventID) {
		if (!validateAttendeeParams(attendeeEmail, attendeeName, eventID)) {
			return false;
		}

		for (Event existingEvent : events) {
			if (existingEvent.getId().toString().equals(eventID)) {
				LOGGER.debug("Adding attendee " + attendeeName + " to event "
						+ existingEvent.getName());
				existingEvent.getAttendees().add(
						new Attendee(attendeeEmail, attendeeName));
				return true;
			}
		}

		LOGGER.debug("Event with ID " + eventID + " does not exist");
		return false;
	}

	public boolean validateAttendeeParams(String attendeeEmail,
			String attendeeName, String eventID) {
		if (attendeeEmail == null || attendeeEmail.isEmpty()) {
			LOGGER.error("Attendee e-mail cannot be null/empty");
			return false;
		}

		if (attendeeName == null) {
			LOGGER.error("Attendee name cannot be null/empty");
			return false;
		}

		return true;
	}

	public boolean addEventPhoto(String eventID, String photoName) {
		if (!validatePhotoParams(eventID, photoName)) {
			return false;
		}

		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
				existingEvent.getPhotoNames().add(photoName);
			}
		}

		return false;
	}

	private boolean validatePhotoParams(String eventID, String photoName) {
		if (eventID == null || eventID.isEmpty()) {
			LOGGER.error("Event ID cannot be null/empty");
			return false;
		}

		if (photoName == null || photoName.isEmpty()) {
			LOGGER.error("Event photo name cannot be null/empty");
			return false;
		}

		return false;
	}

	public boolean addEventPhotos(String eventID, List<String> photoNames) {
		if (!validatePhotoNamesParams(eventID, photoNames)) {
			return false;
		}

		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
				for (String photo : photoNames) {
					existingEvent.getPhotoNames().add(photo);
				}
			}
		}

		LOGGER.debug("Event with ID " + eventID + " does not exist");
		return false;
	}

	private boolean validatePhotoNamesParams(String eventID,
			List<String> photoNames) {
		if (eventID == null || eventID.isEmpty()) {
			LOGGER.error("Event ID cannot be null/empty");
			return false;
		}

		if (photoNames == null) {
			LOGGER.error("Event photo names cannot be null");
			return false;
		}

		return false;
	}

	public List<Attendee> getEventAttendees(String eventID) {
		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
				LOGGER.debug("Getting list of attendees for event "
						+ existingEvent.getName());
				return existingEvent.getAttendees();
			}
		}

		LOGGER.debug("Event with ID " + eventID + " does not exist");
		return null;
	}

	public Event getEvent(String eventID) {
		for (Event existingEvent : events) {
			if (existingEvent.getId().toString().equals(eventID)) {
				LOGGER.debug("Getting event " + existingEvent.getName()
						+ " with ID" + existingEvent.getId());
				return existingEvent;
			}
		}

		LOGGER.error("Event with ID " + eventID + " does not exist");
		return null;
	}

	public List<Event> getEvents() {
		LOGGER.debug("Listing existing events");
		return events;
	}

	public String getEventPhoto(String eventID, String photoName) {
		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
				for (String photo : existingEvent.getPhotoNames()) {
					if (photo.equals(photoName)) {
						LOGGER.debug("Getting event " + existingEvent.getName()
								+ " photo " + photo);
						return photo;
					}
				}
			}
		}

		LOGGER.error("Event with ID " + eventID + " does not exist");
		return null;
	}

	public List<String> getPhotoNamesFromEvent(String eventID) {
		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
				LOGGER.debug("Getting event " + existingEvent.getName()
						+ " photos");
				return existingEvent.getPhotoNames();
			}
		}

		LOGGER.error("Event with ID " + eventID + " does not exist");
		return null;
	}
}