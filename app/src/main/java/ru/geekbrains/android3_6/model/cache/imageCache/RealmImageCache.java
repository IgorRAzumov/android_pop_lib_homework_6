package ru.geekbrains.android3_6.model.cache.imageCache;

import android.graphics.Bitmap;

import java.io.File;

import io.realm.Realm;
import ru.geekbrains.android3_6.model.cache.IImageCache;
import ru.geekbrains.android3_6.model.entity.realm.CachedRealmImage;
import ru.geekbrains.android3_6.model.utils.android.ImageFileUtils;

public class RealmImageCache implements IImageCache<Bitmap> {
    @Override
    public File getFile(String key) {
        CachedRealmImage cachedRealmImage = Realm
                .getDefaultInstance()
                .where(CachedRealmImage.class)
                .equalTo("url", key)
                .findFirst();
        if (cachedRealmImage != null) {
            return new File(cachedRealmImage.getPath());
        }
        return null;
    }

    @Override
    public File saveImage(String key, Bitmap image) {
        final File imageFile = ImageFileUtils.saveImage(key, image, IMAGE_FOLDER_NAME);
        if (imageFile == null) {
            return null;
        }

        Realm
                .getDefaultInstance()
                .executeTransactionAsync(realm -> {
                    CachedRealmImage cachedRealmImage = new CachedRealmImage();
                    cachedRealmImage.setUrl(key);
                    cachedRealmImage.setPath(imageFile.toString());
                    realm.copyToRealm(cachedRealmImage);
                });

        return imageFile;
    }




    @Override
    public boolean contains(String key) {
        return Realm
                .getDefaultInstance()
                .where(CachedRealmImage.class)
                .equalTo("url", key)
                .count() > 0;
    }

    @Override
    public void clear() {
        Realm
                .getDefaultInstance()
                .executeTransaction(realm -> realm.delete(CachedRealmImage.class));
        ImageFileUtils.deleteFileOrDirRecursive(ImageFileUtils.getDirectory(IMAGE_FOLDER_NAME));
    }
}
