package com.mri.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public Properties initConfig(){
        prop = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/config/config.properties")){
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
    public static String getProperty(String key) {
        Properties prop = new Properties();
        FileInputStream input;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "C:/Users/Khushali.Mehta/Desktop/HorizonFramework/src/test/resources/config/config.properties");
            prop.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static void setProperty(String key, String Value) {
        try {
            // Load the properties file
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "C:/Users/Khushali.Mehta/Desktop/HorizonFramework/src/test/resources/config/config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            // Print original property value
            String originalValue = properties.getProperty("Version");
            System.out.println("Original Property Value: " + originalValue);

            // Update (rename) the property value
            properties.setProperty("Version", Value);

            // Save the updated properties back to the file
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + "C:/Users/Khushali.Mehta/Desktop/HorizonFramework/src/test/resources/config/config.properties");
            properties.store(outputStream, null);
            outputStream.close();

            System.out.println("Property updated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}