package com.blackcandletech.filehandler;

import com.google.gson.GsonBuilder;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class MappedFile extends FileSection {

    public final File file;

    public MappedFile(File file, Map<String, Object> rawData)
    {
        super(rawData);
        this.file = file;
    }

    public void saveFile() {

        if (this.file.getName().endsWith(".yml"))
        {
            final DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            try {
                PrintWriter writer = new PrintWriter(file);
                Map<String, Object> data = gatherData();
                System.out.println(data);
                yaml.dump(data, writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.file.getName().endsWith(".json"))
        {
            String jsonString = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(gatherData());

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
