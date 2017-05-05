package com.nilson.resource;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
@Priority(value = 1)
public class AAInterceptor implements ContainerRequestFilter {
	
	private static final Logger LOGGER = LogManager.getLogger(AAInterceptor.class);
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		LOGGER.debug("Starting Authentication filter.");
	}
}