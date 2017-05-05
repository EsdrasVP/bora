package com.nilson;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;

public class Main {
	
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
    
	@SuppressWarnings("deprecation")
	private static void startWebSocketServer() throws IOException, SQLException, ServletException {
		LOGGER.debug("Initializing service.");
    	Properties propreties = new Properties();
    	propreties.load(new FileInputStream(NilsinApplicationHelper.FILE_PROPERTIES));
    	String basePort = propreties.getProperty(NilsinApplicationHelper.BASE_PORT_PROPERTIES);
        HttpServer server = new ServerWrapper().create(
        		basePort == null ? NilsinApplicationHelper.DEFAULT_PORT : Integer.valueOf(basePort), NilsinApplication.class);
        try {
            server.start();
            Thread.currentThread().suspend();
        } finally {
            server.shutdownNow();
        } 
	}
	
    public static void main(String[] args) throws Exception {
    	startWebSocketServer();
    }

}
