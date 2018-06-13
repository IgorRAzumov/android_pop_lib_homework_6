package ru.geekbrains.android3_6.model.cache;

import java.io.File;

public interface IImageCache<T> {
    String IMAGE_FOLDER_NAME = "image";

    File getFile(String key);

    File saveImage(final String url, T image);

    boolean contains(String key);

    void clear();

}
