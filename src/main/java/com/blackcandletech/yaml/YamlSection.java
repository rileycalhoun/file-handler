package com.blackcandletech.yaml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class YamlSection {

    protected final Map<String, Object> rawData;
    protected final Map<String, YamlSection> sections = new HashMap<>();

    public YamlSection (Map<String, Object> rawData)
    {
        this.rawData = rawData;
        if(rawData.isEmpty()) return;
        for(String key : rawData.keySet())
        {
            Object obj = rawData.get(key);
            if(obj instanceof Map)
            {
                Map<String, Object> section = (Map<String, Object>) obj;
                this.sections.put(key, new YamlSection(section));
            }
        }
    }

    public Map<String, Object> getData()
    {
        return rawData;
    }

    public String getString(String path)
    {
        return (String) rawData.get(path);
    }

    public int getInteger(String path)
    {
        return (int) rawData.get(path);
    }

    public boolean getBoolean(String path)
    {
        return (boolean) rawData.get(path);
    }

    public float getFloat(String path)
    {
        return (float) rawData.get(path);
    }

    public double getDouble(String path)
    {
        return (double) rawData.get(path);
    }

    public ArrayList<String> getStringList(String path)
    {
        return (ArrayList<String>) rawData.get(path);
    }

    public YamlSection getYamlSection(String path)
    {
        YamlSection section = getYamlSections().get(path);
        if(section == null) {
            section = new YamlSection(new HashMap<>());
            getYamlSections().put(path, section);
        }

        return getYamlSections().get(path);
    }

    public Map<String, YamlSection> getYamlSections()
    {
        return sections;
    }

    public void set(String path, Object obj) {
        if(path.contains("."))
        {
            String[] pathArray = path.split("\\.");
            String section = pathArray[0];
            String newPath = String.join(" ", Arrays.copyOfRange(pathArray, 1, pathArray.length));
            getYamlSection(section).set(newPath, obj);
            return;
        }

        if(this.getData().containsKey(path))
            this.getData().replace(path, obj);
        else
            this.getData().put(path, obj);
    }

    protected Map<String, Object> gatherData() {
        Map<String, Object> data = new HashMap<>(Map.copyOf(rawData));
        for(String key : sections.keySet()) {
            YamlSection section = sections.get(key);
            Map<String, Object> sectionData = section.gatherData();
            data.replace(key, sectionData);
        }

        System.out.println(data);
        return data;
    }
}