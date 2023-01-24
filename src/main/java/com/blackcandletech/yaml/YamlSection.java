package com.blackcandletech.yaml;

import java.util.*;

@SuppressWarnings({"unchecked"})
public class YamlSection {

    protected final Map<String, Object> rawData;
    protected final Map<String, YamlSection> sections = new HashMap<>();

    public YamlSection (Map<String, Object> rawData)
    {
        this.rawData = rawData;
        if(rawData.isEmpty()) return;

        Iterator<String> keys = rawData.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();
            Object obj = rawData.get(key);
            if(obj instanceof Map)
            {
                Map<String, Object> section = (Map<String, Object>) obj;
                this.sections.put(key, new YamlSection(section));
                keys.remove();
            }
        }
    }

    public Map<String, Object> getData()
    {
        return rawData;
    }

    public Object get(String path) {
        if(path.contains("."))
        {
            String[] pathArray = path.split("\\.");
            String section = pathArray[0];
            String newPath = String.join(".", Arrays.copyOfRange(pathArray, 1, pathArray.length));
            return getYamlSection(section).get(newPath);
        }

        return rawData.get(path);
    }

    public String getString(String path)
    {
        return (String) get(path);
    }

    public int getInteger(String path)
    {
        return (int) get(path);
    }

    public boolean getBoolean(String path)
    {
        return (boolean) get(path);
    }

    public float getFloat(String path)
    {
        return (float) get(path);
    }

    public double getDouble(String path)
    {
        return (double) get(path);
    }

    public ArrayList<String> getStringList(String path)
    {
        return (ArrayList<String>) get(path);
    }

    public YamlSection getYamlSection(String path)
    {
        YamlSection section = getYamlSections().get(path);
        if(section == null) {
            section = new YamlSection(new HashMap<>());
            getYamlSections().put(path, section);
        }

        return section;
    }

    public Map<String, YamlSection> getYamlSections()
    {
        return sections;
    }

    public void set(String path, Object obj)
    {
        if(path.contains("."))
        {
            String[] pathArray = path.split("\\.");
            String section = pathArray[0];
            String newPath = String.join(".", Arrays.copyOfRange(pathArray, 1, pathArray.length));

            getYamlSection(section).set(newPath, obj);
            return;
        }

        this.getData().put(path, obj);
    }

    protected Map<String, Object> gatherData()
    {
        Map<String, Object> data = new HashMap<>(Map.copyOf(rawData));
        if(sections.isEmpty()) return data;
        for(String key : sections.keySet())
        {
            YamlSection section = sections.get(key);

            Map<String, Object> sectionData = section.gatherData();
            data.put(key, sectionData);
        }

        return data;
    }

}