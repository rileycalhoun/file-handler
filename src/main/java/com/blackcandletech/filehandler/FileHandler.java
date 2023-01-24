package com.blackcandletech.filehandler;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class FileHandler {

    private static String directory = System.getProperty("user.dir");
    public static String getDirectory() {
        return directory;
    }

    public static void setDirectory(String directory) {
        FileHandler.directory = directory;
    }

    public static BCTFile getFile(String fileName, FileSettings fileSettings) {
        if(!fileName.contains("."))
            switch(fileSettings.getFileType())
            {
                case JSON -> fileName = fileName + ".json";
                case YAML -> fileName = fileName + ".yml";
            }

        File file = new File(fileSettings.getDirectory().toFile(), fileName);
        // Create files if they do not exist already
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if(fileSettings.copyFromResources()) {
                // Write to file from resources folder
                InputStream resourceStream = FileHandler.class.getClassLoader().getResourceAsStream(fileName);
                try {
                    OutputStream outputStream = new FileOutputStream(file);
                    if (resourceStream != null)
                        resourceStream.transferTo(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Read from file and map
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Map rawData = new HashMap<>();
        if(fileSettings.getFileType() == FileSettings.FileType.YAML)
        {
            Yaml yaml = new Yaml();
            rawData = yaml.load(inputStream);
            if (rawData == null) rawData = new HashMap<>();
        } else if (fileSettings.getFileType() == FileSettings.FileType.JSON)
        {
            Gson gson = new Gson();
            StringBuilder builder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, StandardCharsets.UTF_8))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    builder.append((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            rawData = gson.fromJson(builder.toString(), Map.class);
        }

        return new BCTFile(file, rawData);
    }

}
