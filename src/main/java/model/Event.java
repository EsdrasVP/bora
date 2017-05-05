package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

public class Event implements Serializable {
	
	private UUID id;
	private String name;
	private Date date;
	private List<Attendee> attendees;
	
	private static final long serialVersionUID = -6111900503095749695L;
	
	public Event(String name, Date date) {
		this(UUID.randomUUID(), name, date, new ArrayList<Attendee>());
	}

	public Event(UUID id, String name, Date date,
			List<Attendee> attendees) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.attendees = attendees;
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
}