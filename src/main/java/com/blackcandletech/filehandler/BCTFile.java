package com.blackcandletech.filehandler;

import com.google.gson.GsonBuilder;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class BCTFile extends FileSection {

    public final File file;

    public BCTFile(File file, Map<String, Object> rawData)
    {
        super(rawData);
        this.file = file;
    }

    public void saveFile() {
        for (String key : sections.keySet()) {
            FileSection section = sections.get(key);
            Map<String, Object> data = section.gatherData();
            this.rawData.put(key, data);
        }

        if (this.file.getName().endsWith(".yml"))
        {
            final DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            try {
                PrintWriter writer = new PrintWriter(file);
                yaml.dump(rawData, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.file.getName().endsWith(".json"))
        {
            String jsonString = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(rawData);

            try {
                PrintWriter writer = new PrintWriter(file);
                writer.write(jsonString);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
