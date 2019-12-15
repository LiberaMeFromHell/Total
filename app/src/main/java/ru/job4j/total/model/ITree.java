package ru.job4j.total.model;

import java.util.List;

public interface ITree {
    FileModel getRootDir();
    FileModel getParentByChild(String childAbsolutePath);
    List<FileModel> getChildrenByParent(String absolutePath);
}
