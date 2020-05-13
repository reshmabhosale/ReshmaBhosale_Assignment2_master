package com.qa.provision.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigProperties {
	
	 private static final Logger log = Logger.getLogger( ConfigProperties.class );
	    private static ConfigProperties configProperties;
	    private Properties properties;
	    private ConfigProperties() {
	        properties = loadProperties();
	    }

	    private Properties loadProperties() {
	        File file = new File( ".\\Configs\\config.properties" );
	        FileInputStream fileInput = null;
	        Properties props = new Properties();
	        try {
	            fileInput = new FileInputStream( file );
	            props.load( fileInput );
	            fileInput.close();
	        } catch ( FileNotFoundException e ) {
	            log.error( "automation.properties is missing or corrupt : " + e.getMessage() );

	        } catch ( IOException e ) {
	            log.error( "read failed due to: " + e.getMessage() );
	        }
	        return props;
	    }

	    public static ConfigProperties getInstance() {
	        if ( configProperties == null ) {
	            configProperties = new ConfigProperties();
	        }
	        return configProperties;
	    }

	    public String getProperty( String key ) {
	        return properties.getProperty( key );
	    }

}
