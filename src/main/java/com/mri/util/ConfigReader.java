package com.mri.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static Properties initConfig(){
        prop = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("src/test/resources/config/config.properties"))){
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}