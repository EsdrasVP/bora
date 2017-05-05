package resource;

import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.http.util.URLDecoder;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path(ImageResource.IMAGE_URL_BASE)
public class ImageResource {

	protected static final String IMAGE_URL_BASE = "image";

	private static final Logger LOGGER = LogManager.getLogger(ImageResource.class);
	
	@Context
	private HttpServletRequest request;
	
	@Inject
	private Properties properties;
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadImage(@FormDataParam("file") InputStream inputStream, 
			@FormDataParam("photo_path") String photoPath) throws IOException {

		String rootPath = getImageRootPath();
		
		if (photoPath != null) {
			try {
				File file = new File(rootPath + photoPath);
				file.delete();				
			} catch (Exception e) {}
		}
		
//		String relativePath = authResponse.getUser().getDomain() + "/" + UUID.randomUUID().toString() + ".png";
		String relativePath = "";
    	String absolutePath = rootPath + "/" + relativePath;
    	
    	new File(absolutePath).getParentFile().mkdirs();
    	
		IOUtils.copy(inputStream, new FileOutputStream(new File(absolutePath)));
		
		return Response.status(Status.OK).entity(relativePath).build();
	}

	private String getImageRootPath() {
		String filesPath = properties.getProperty("files_path");
		if (filesPath == null) {
			LOGGER.warn("There is no file path set for image storage.");
		}
		return filesPath;
	}
 
//	@Path("/{fileParent}/{fileName}")
	@Path("/{filePath}")
	@GET
	@Produces("image/png")
	public Response get(@PathParam("filePath") String filePath) 
			throws FileNotFoundException, IOException {
    	String relativePath = null;
		try {
    		relativePath = URLDecoder.decode(filePath);
		} catch (CharConversionException e) {
		
		}
		
		String rootPath = getImageRootPath();
    	String absolutePath = rootPath + "/files/" + relativePath;
		
		File file = new File(absolutePath);
		if (file.exists()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(new FileInputStream(file), baos);
			return Response.status(Status.OK).entity(baos.toByteArray()).build();
		} else {
			LOGGER.warn("File " + absolutePath + " was not found.");
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@Path("/{fileParent}/{fileName}")
	@DELETE
	public Response deleteImage(@PathParam("fileParent") String fileParent, 
			@PathParam("fileName") String fileName) {
		
		String relativePath = null;
		try {
    		relativePath = URLDecoder.decode(fileParent + "/" + fileName);
		} catch (CharConversionException e) {
		
		}
		
		String rootPath = getImageRootPath();
    	String absolutePath = rootPath + "/" + relativePath;
		
		File file = new File(absolutePath);
		if (file.delete()) {
			return Response.status(Status.OK).entity("deleted").build();
		} else {
			LOGGER.warn("File " + absolutePath + " was not deleted.");
			return Response.status(Status.OK).entity("not deleted").build();
		}
	}
}
