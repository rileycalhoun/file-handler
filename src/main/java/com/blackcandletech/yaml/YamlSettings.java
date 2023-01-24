package com.blackcandletech.yaml;

import java.nio.file.Path;

public class YamlSettings {

    private static Path defaultDirectory = Path.of(System.getProperty("user.dir"));
    public static Path getDefaultDirectory() {
        return defaultDirectory;
    }

    public void setDefaultDirectory(Path newDefault) {
        defaultDirectory = newDefault;
    }

    private final boolean copyFromResources;
    private final Path directory;

    private YamlSettings (Builder builder)
    {
        this.copyFromResources = builder.copyFromResources;
        this.directory = builder.directory;
    }

    public boolean copyFromResources() {
        return copyFromResources;
    }

    public Path getDirectory() {
        return directory;
    }

    public static class Builder {

        private boolean copyFromResources = true;
        private Path directory = YamlSettings.getDefaultDirectory();

        public Builder (boolean copyFromResources)
        {
            this.copyFromResources = copyFromResources;
        }

        public Builder (String directory)
        {
            this.directory = Path.of(directory);
        }

        public Builder setCopyResources(boolean copyFromResources) {
            this.copyFromResources = copyFromResources;
            return this;
        }

        public Builder setPath (String directory)
        {
            this.directory = Path.of(directory);
            return this;
        }

        public YamlSettings build()
        {
            return new YamlSettings(this);
        }

    }

}
