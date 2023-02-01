package com.blackcandletech.filehandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@SuppressWarnings({"unchecked"})
public class FileSection {

    protected Map<String, Object> entries = new HashMap<>();
    protected final Map<String, FileSection> sections = new HashMap<>();

    public FileSection(@Nullable Map<String, Object> rawData)
    {
        if(rawData == null) return;

        Iterator<String> keys = rawData.keySet().iterator();
        while(keys.hasNext())
        {
            String key = keys.next();
            Object obj = rawData.get(key);
            if(obj instanceof Map)
            {
                Map<String, Object> section = (Map<String, Object>) obj;
                this.sections.put(key, new FileSection(section));
                keys.remove();
            } else entries.put(key, obj);
        }
    }

    public Map<String, Object> getData()
    {
        Map<String, Object> rawData = new HashMap<>(entries);
        rawData.putAll(sections);

        System.out.println(entries);
        System.out.println(sections);
        return rawData;
    }

    public Object get(@NotNull  String path) {
        if(path.contains("."))
        {
            String[] pathArray = path.split("\\.");
            String section = pathArray[0];
            String newPath = String.join(".", Arrays.copyOfRange(pathArray, 1, pathArray.length));
            return getSection(section).get(newPath);
        }

        return sections.get(path) == null ? entries.get(path) : sections.get(path);
    }

    @Nullable public String getString(@NotNull String path)
    {
        return (String) get(path);
    }

    @Nullable public Integer getInteger(@NotNull String path)
    {
        return (int) get(path);
    }

    @Nullable public Boolean getBoolean(@NotNull String path)
    {
        return (boolean) get(path);
    }

    @Nullable public Float getFloat(@NotNull String path)
    {
        return (float) get(path);
    }

    @Nullable public Double getDouble(@NotNull String path)
    {
        return (double) get(path);
    }

    public ArrayList<String> getStringList(String path)
    {
        return (ArrayList<String>) get(path);
    }

    public FileSection getSection(String path)
    {
        Object obj = get(path);
        System.out.println(obj);
        if(obj == null) {
            FileSection section = new FileSection(null);
            sections.put(path, section);
            return section;
        }
        if(!(obj instanceof FileSection section)) return null;
        return section;
    }

    public Map<String, FileSection> getSections()
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
            getSection(section).set(newPath, obj);
            return;
        }

        if(obj instanceof Map m) sections.put(path, new FileSection(m));
        else if (obj instanceof FileSection section) sections.put(path, section);
        else entries.put(path, obj);
    }

    protected Map<String, Object> gatherData()
    {
        Map<String, Object> data = new HashMap<>(getData());
        if(sections.isEmpty()) return data;
        for(String key : sections.keySet())
        {
            FileSection section = sections.get(key);

            Map<String, Object> sectionData = section.gatherData();
            data.put(key, sectionData);
        }

        return data;
    }

}