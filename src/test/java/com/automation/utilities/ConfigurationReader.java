package com.automation.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;

    static {

        try {

            String path = System.getProperty("user.dir")+"/configuration.properties";
            FileInputStream input = new FileInputStream(path); //takes file and loads the file into our program

            //A <code>FileInputStream</code> obtains input bytes from a file in a file system. What files are  available depends on the host environment.
            //file input stream throws IOExceptions
            configFile = new Properties(); //the class where we load our own custom property
            configFile.load(input);
            input.close(); //it closes the connection between our path to configuration file and FileINputStream object..

            //static => will execute once whenever this class is called and it will load first
            //for static block you do not need Object to call . it is executed once class is referred
            //static never has a RETURN

        } catch (Exception e) {

            e.printStackTrace(); //error information -> if properties file is not FOUND we have to throw Exception
            throw new RuntimeException("Failed to load properties file!");

        }
    }

    /**
     * This method returns property value from configuration.properties file
     * @param keyName property name
     * @return property value
     */
    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);

    }
}


//opt + cmd ( arrow left ) go back to previous location of the mouse
//opt + cmd ( arrow right ) come back from the place where you went back