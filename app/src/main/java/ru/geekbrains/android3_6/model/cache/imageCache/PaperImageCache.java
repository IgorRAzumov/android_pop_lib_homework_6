package ru.geekbrains.android3_6.model.cache.imageCache;

import android.graphics.Bitmap;

import java.io.File;

import io.paperdb.Paper;
import ru.geekbrains.android3_6.model.cache.IImageCache;
import ru.geekbrains.android3_6.model.entity.CachedImage;
import ru.geekbrains.android3_6.model.utils.android.ImageFileUtils;

public class PaperImageCache implements IImageCache<Bitmap> {
    @Override
    public File getFile(String key) {
        if (Paper.book("cachedImage").contains(key)) {
            CachedImage cachedImage = Paper.book("cachedImage").read(key);
            return new File(cachedImage.getPath());
        }
        return null;
    }

    @Override
    public File saveImage(String key, Bitmap image) {
        final File imageFile = ImageFileUtils.saveImage(key, image, IMAGE_FOLDER_NAME);
        if (imageFile == null) {
            return null;
        }
        CachedImage cachedImage = new CachedImage();
        cachedImage.setUrl(key);
        cachedImage.setPath(imageFile.toString());

        Paper.book("cachedImage").write(key, cachedImage);
        return imageFile;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public void clear() {

    }
}
