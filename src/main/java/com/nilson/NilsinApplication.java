package com.nilson;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.nilson.controller.ApplicationController;
import com.nilson.resource.CORSResponseFilter;
import com.nilson.util.json.JSONArrayBodyReader;
import com.nilson.util.json.JSONArrayBodyWriter;
import com.nilson.util.json.JSONObjectBodyReader;
import com.nilson.util.json.JSONObjectBodyWriter;

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
		final ApplicationController applicationController = new ApplicationController();
		
		packages(NilsinApplication.class.getPackage().toString());
		register(new AbstractBinder() {
            @Override
            protected void configure() {
            	bind(applicationController).to(ApplicationController.class);
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
