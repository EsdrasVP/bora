package resource;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

@Path(EventResource.EVENT_BASE_URL)
public class EventResource {

	private static final String JSON_QUERY_ATTR = "json";
	protected static final String EVENT_BASE_URL = "event";
	protected static final String SEARCH_URL = "search";
	protected static final String ENABLE_OR_DISABLE_URL = "disable";

	private static final Logger LOGGER = LogManager.getLogger(EventResource.class);
	
	@Context
	private HttpServletRequest request;
		
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvents(@QueryParam(JSON_QUERY_ATTR) String jsonStrEncoded) {
        try {
			return Response.status(Status.OK).entity("").build();
		} catch (Exception e) {
			LOGGER.error("Couldn't retrive events.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }
	
	@GET
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvent(@PathParam("id") Long id) {
        try {
			return Response.status(Status.OK).entity("").build();
		} catch (Exception e) {
			LOGGER.error("Couldn't retrieve event", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response createEvent(JSONObject json) throws SQLException {
		LOGGER.debug("Creating event " + json.toString());
		try {
			return Response.status(Status.CREATED).entity("").build();
		} catch (Exception e) {
			LOGGER.error("Couldn't create event.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }	
	
	@DELETE
	@Path("{id}")
    public Response removeEvent(@PathParam("id") Long id) {
        try {
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			LOGGER.error("Couldn't remove event.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }
		
	// TODO implement for future	
	@PUT
	@Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateEvent(@PathParam("id") Long id, JSONObject json) {
		LOGGER.debug("Updating event " + json.toString());
        try {
			return Response.status(Status.OK).entity("").build();
		} catch (Exception e) {
			LOGGER.error("Couldn't update event.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }	
	
	// TODO implement for future
	@PUT
	@Path(ENABLE_OR_DISABLE_URL + "/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response enableEvent(@PathParam("id") Long id, JSONObject json) {
		LOGGER.debug("Update event: " + json.toString());
		try {
			return Response.status(Status.OK).entity("").build();
		} catch (Exception e) {
			LOGGER.error("Couldn't update event.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// TODO implement for future	
	@GET
	@Path(SEARCH_URL)
	@Produces(MediaType.APPLICATION_JSON)
    public Response searchEvents(@QueryParam("q") String query) {
		try {
			return Response.status(Status.OK).entity("").build();
		} catch (Exception e) {
			LOGGER.error("Couldn't find events.", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
    }
}