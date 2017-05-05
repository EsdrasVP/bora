import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import core.BoraController;
import resource.filter.CORSResponseFilter;
import utils.json.JSONArrayBodyReader;
import utils.json.JSONArrayBodyWriter;
import utils.json.JSONObjectBodyReader;
import utils.json.JSONObjectBodyWriter;

public class NilsinApplication extends ResourceConfig {
	
	private static final Logger LOGGER = LogManager.getLogger(NilsinApplication.class);
	
	public NilsinApplication() throws Exception {
		Properties properties = NilsinApplicationHelper.getPropertiesInDefaultFile();
		init(properties);
	}
	
	protected NilsinApplication(Properties properties) throws Exception {
		init(properties);
	}
	
	private NilsinApplication init(final Properties properties) throws SQLException {	
		LOGGER.debug("Init nilsin app.");
		final BoraController boraController = new BoraController();
		
		packages(NilsinApplication.class.getPackage().toString());
		register(new AbstractBinder() {
            @Override
            protected void configure() {
            	bind(boraController).to(BoraController.class);
                bind(properties).to(Properties.class);
            }
        });
		
		register(JSONObjectBodyReader.class);
		register(JSONObjectBodyWriter.class);
		register(JSONArrayBodyReader.class);
		register(JSONArrayBodyWriter.class);
		register(MultiPartFeature.class);
		register(CORSResponseFilter.class);
		
		return this;
	}
	
}
