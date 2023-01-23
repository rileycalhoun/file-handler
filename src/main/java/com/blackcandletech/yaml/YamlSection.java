package com.blackcandletech.yaml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class YamlSection {

    private final Map<String, Object> rawData;
    private final Map<String, YamlSection> sections = new HashMap<>();

    public YamlSection (Map<String, Object> rawData)
    {
        this.rawData = rawData;
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
        return getYamlSections().get(path);
    }

    public Map<String, YamlSection> getYamlSections()
    {
        return sections;
    }
}