package ru.geekbrains.android3_6.di.modules;

import android.graphics.Bitmap;
import android.widget.ImageView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.IImageCache;
import ru.geekbrains.android3_6.model.image.ImageLoader;
import ru.geekbrains.android3_6.model.image.android.ImageLoaderGlide;

@Module(includes = {CacheModule.class})
public class ImageLoaderModule {
    @Provides
    public ImageLoader<ImageView> imageLoader(@Named("realmImageCache") IImageCache<Bitmap> imageCache) {
        return new ImageLoaderGlide(imageCache);
    }
}
