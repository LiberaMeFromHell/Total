package ru.job4j.total.model;

public class FileModel {

    private String absolutePath;
    private String name;
    private Boolean directory;

    public FileModel(String absolutePath, String name, Boolean isDirectory) {
        this.absolutePath = absolutePath;
        this.name = name;
        this.directory = isDirectory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileModel)) return false;

        FileModel fileModel = (FileModel) o;

        if (!getAbsolutePath().equals(fileModel.getAbsolutePath())) return false;
        if (!getName().equals(fileModel.getName())) return false;
        return directory != null ? directory.equals(fileModel.directory) : fileModel.directory == null;
    }

    @Override
    public int hashCode() {
        int result = getAbsolutePath().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (directory != null ? directory.hashCode() : 0);
        return result;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDirectory() {
        return directory;
    }

    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }
}
