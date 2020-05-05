package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class Utils {

	public static String getConfigData(String key) throws Exception {
        String value;
        try {
            Properties prop = new Properties();
            File f = new File("config.properties");
            if (f.exists()) {
                prop.load(new FileInputStream(f));
                value = prop.getProperty(key);
            } else {
                throw new Exception("File not found");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Failed to read from application.properties file.");
            throw ex;
        }
        if (value == null) {
            throw new Exception("Key not found in properties file");
        }
        return value;
    }
	
	public static boolean isAlpha(String s) {
		return s != null && s.matches("^[a-zA-Z]*$");
	}
	
	public static String getDateTime() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss");
		return date.format(format);
	}
}
