package com.blackcandletech.yaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class YamlFile extends YamlSection {

    public final File file;

    public YamlFile(File file, Map<String, Object> rawData)
    {
        super(rawData);
        this.file = file;
    }

    public void saveYamlFile() {
        for(String key : sections.keySet()) {
            YamlSection section = sections.get(key);
            Map<String, Object> data = section.gatherData();
            if(this.rawData.containsKey(key))
                this.rawData.replace(key, data);
            else
                this.rawData.put(key, data);
        }

        final DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        try {
            System.out.println(rawData.toString());
            PrintWriter writer = new PrintWriter(file);
            yaml.dump(rawData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
