package com.blackcandletech.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"ResultOfMethodCallIgnored", "resource"})
public class BCTYaml {

    public static YamlFile getYamlFile (String fileName) {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(System.getProperty("user.dir"), fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Write to file from resources folder
            InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                if(resourceStream != null)
                    resourceStream.transferTo(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Read from file and map
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();
        Map<String, Object> rawData =
                yaml.load(inputStream);
        if(rawData == null) rawData = new HashMap<>();

        return new YamlFile(file, rawData);
    }

    public static YamlFile getYamlFile (String directory, String fileName) {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(directory, fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Write to file from resources folder
            InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                if(resourceStream != null)
                    resourceStream.transferTo(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Read from file and map
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();
        Map<String, Object> rawData =
                yaml.load(inputStream);
        if(rawData == null) rawData = new HashMap<>();

        return new YamlFile(file, rawData);
    }

    public static YamlFile getYamlFile (String directory, String fileName, boolean copyResource) {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(directory, fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(copyResource) {
                // Write to file from resources folder
                InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    if(resourceStream != null)
                        resourceStream.transferTo(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Read from file and map
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();
        Map<String, Object> rawData =
                yaml.load(inputStream);
        if(rawData == null) rawData = new HashMap<>();

        return new YamlFile(file, rawData);
    }

    public static YamlFile getYamlFile (String fileName, boolean copyResource) {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(System.getProperty("user.dir"), fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(copyResource) {
                // Write to file from resources folder
                InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    if(resourceStream != null)
                        resourceStream.transferTo(outputStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Read from file and map
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Yaml yaml = new Yaml();
        Map<String, Object> rawData =
                yaml.load(inputStream);
        if(rawData == null) rawData = new HashMap<>();

        return new YamlFile(file, rawData);
    }

}
