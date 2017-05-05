package core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Event {
	
	private String id;
	private String name;
	private Date date;
	private List<Attendee> attendees;
	
    private static final Logger LOGGER = Logger.getLogger(Event.class);
	
	public Event(String name, Date date) {
		this("create-id-here", name, date, new ArrayList<Attendee>());
	}

	public Event(String id, String name, Date date,
			List<Attendee> attendees) {
		
		// TODO: this will be the treatment?
		if(validateParams(id, name, date)) {
			System.exit(1);
		}
		
		this.id = id;
		this.name = name;
		this.date = date;
		this.attendees = attendees;
	}

	private boolean validateParams(String id, String name, Date date) {
		if(id == null || id.isEmpty()) {
			LOGGER.error("ID cannot be null/empty");
			return false;
		}
		
		if(name == null || name.isEmpty()) {
			LOGGER.error("Name cannot be null/empty");
			return false;
		}
		
		if(date == null) {
			LOGGER.error("Date cannot be null");
			return false;
		}
		
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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