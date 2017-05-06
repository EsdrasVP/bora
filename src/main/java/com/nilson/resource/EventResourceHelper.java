package com.nilson.resource;

import java.util.Properties;

import com.nilson.Configuration;
import com.nilson.util.CrunchifyQRCode;

public class EventResourceHelper {
	
	public static void createEventQRCode(String uuid, Properties properties) {
		String domain = properties.getProperty(Configuration.DOMAIN_PROPERTIES); 
		String filesPath = properties.getProperty(Configuration.FILES_PATH_PROPERTIES); 
		CrunchifyQRCode.generateQRCode("http://" + domain + "/#!/event/view/" + uuid, filesPath + Configuration.QRCODE_PATH_PROPERTIES + uuid);	
	}
	
}
