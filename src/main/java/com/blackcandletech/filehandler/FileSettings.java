package com.blackcandletech.filehandler;

import java.nio.file.Path;

public class FileSettings {

    private static Path defaultDirectory = Path.of(System.getProperty("user.dir"));
    public static Path getDefaultDirectory() {
        return defaultDirectory;
    }

    public void setDefaultDirectory(Path newDefault) {
        defaultDirectory = newDefault;
    }

    private final boolean copyFromResources;
    private final Path directory;
    private final FileType fileType;

    private FileSettings(Builder builder)
    {
        this.fileType = builder.fileType;
        this.copyFromResources = builder.copyFromResources;
        this.directory = builder.directory;
    }

    public boolean copyFromResources() {
        return copyFromResources;
    }

    public Path getDirectory() {
        return directory;
    }

    public FileType getFileType()
    {
        return fileType;
    }

    public static class Builder {

        private boolean copyFromResources = true;
        private Path directory = FileSettings.getDefaultDirectory();
        private FileType fileType;

        public Builder (FileType fileType)
        {
            this.fileType = fileType;
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

        public FileSettings build()
        {
            return new FileSettings(this);
        }

    }

    public enum FileType
    {
        YAML, JSON
    }

}
