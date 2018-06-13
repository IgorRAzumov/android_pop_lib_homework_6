package ru.geekbrains.android3_6.model.utils;

import java.io.File;

public interface IImageUtils<T> {
    String MD5(String s);

    float getSizeKb(File directoryPath);

    void deleteFileOrDirRecursive(File fileOrDirectory);

    long getFileOrDirSize(File f);

    File getImagesDirectory(String folderName);

    File saveBitmapImage(String key, T image, File directory);
}
