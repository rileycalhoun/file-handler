package com.blackcandletech.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;

@SuppressWarnings({"ResultOfMethodCallIgnored", "resource"})
public class BCTYaml {

    public static YamlSection getYamlFile (String fileName) throws IOException {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(System.getProperty("user.dir"), fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            file.createNewFile();

            // Write to file from resources folder
            InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
            OutputStream outputStream = new FileOutputStream(file);
            if(resourceStream == null) return null;
            resourceStream.transferTo(outputStream);
        }

        // Read from file and map
        InputStream inputStream = new FileInputStream(file);
        Yaml snakeYAML = new Yaml();
        return snakeYAML.load(inputStream);
    }

    public static YamlSection getYamlFile (String directory, String fileName) throws IOException {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(directory, fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            file.createNewFile();

            // Write to file from resources folder
            InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
            OutputStream outputStream = new FileOutputStream(file);
            if(resourceStream == null) return null;
            resourceStream.transferTo(outputStream);
        }

        // Read from file and map
        InputStream inputStream = new FileInputStream(file);
        Yaml snakeYAML = new Yaml();
        return snakeYAML.load(inputStream);
    }

    public static YamlSection getYamlFile (String directory, String fileName, boolean copyResource) throws IOException {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(directory, fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            file.createNewFile();

            if(copyResource) {
                // Write to file from resources folder
                InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
                OutputStream outputStream = new FileOutputStream(file);
                if (resourceStream == null) return null;
                resourceStream.transferTo(outputStream);
            }
        }

        // Read from file and map
        InputStream inputStream = new FileInputStream(file);
        Yaml snakeYAML = new Yaml();
        return snakeYAML.load(inputStream);
    }

    public static YamlSection getYamlFile (String fileName, boolean copyResource) throws IOException {
        if(!fileName.contains(".")) fileName = fileName + ".yml";

        File file = new File(System.getProperty("user.dir"), fileName);

        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists()) {
            file.createNewFile();

            if(copyResource) {
                // Write to file from resources folder
                InputStream resourceStream = BCTYaml.class.getClassLoader().getResourceAsStream(fileName);
                OutputStream outputStream = new FileOutputStream(file);
                if (resourceStream == null) return null;
                resourceStream.transferTo(outputStream);
            }
        }

        // Read from file and map
        InputStream inputStream = new FileInputStream(file);
        Yaml snakeYAML = new Yaml();
        return snakeYAML.load(inputStream);
    }

}
