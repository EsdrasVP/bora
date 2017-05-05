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
		if (!validateEventParams(eventName, eventDate)) {
			return false;
		}

		events.add(new Event(eventName, eventDate));
		return true;
	}

	protected boolean validateEventParams(String eventName, Date eventDate) {
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

	protected boolean addAttendeeToEvent(String attendeeEmail,
			String attendeeName, String eventID) {
		if (!validateAttendeeParams(attendeeEmail, attendeeName, eventID)) {
			return false;
		}

		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
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

	protected boolean validateAttendeeParams(String attendeeEmail,
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

	protected List<Attendee> getEventAttendees(String eventID) {
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

	protected Event getEvent(String eventID) {
		for (Event existingEvent : events) {
			if (existingEvent.getId().equals(eventID)) {
				LOGGER.debug("Getting event " + existingEvent.getName()
						+ " with ID" + existingEvent.getId());
				return existingEvent;
			}
		}

		LOGGER.error("Event with ID " + eventID + " does not exist");
		return null;
	}

	protected List<Event> getEvents() {
		LOGGER.debug("Listing existing events");
		return events;
	}

	protected String getEventPhoto(String eventID, String photoName) {
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

	protected List<String> getPhotoNamesFromEvent(String eventID) {
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