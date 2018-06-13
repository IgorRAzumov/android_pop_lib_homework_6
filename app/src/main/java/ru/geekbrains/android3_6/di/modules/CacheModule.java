package ru.geekbrains.android3_6.di.modules;

import android.graphics.Bitmap;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.dataCache.AACache;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.cache.dataCache.PaperCache;
import ru.geekbrains.android3_6.model.cache.dataCache.RealmCache;
import ru.geekbrains.android3_6.model.cache.IImageCache;
import ru.geekbrains.android3_6.model.cache.imageCache.PaperImageCache;
import ru.geekbrains.android3_6.model.cache.imageCache.RealmImageCache;

@Module
public class CacheModule {
    @Named("realmCache")
    @Provides
    public ICache realmCache() {
        return new RealmCache();
    }

    @Named("aACache")
    @Provides
    public ICache aAcache() {
        return new AACache();
    }

    @Named("paperCache")
    @Provides
    public ICache paperCache() {
        return new PaperCache();
    }


    @Named("realmImageCache")
    @Provides
    public IImageCache<Bitmap> realmImageCache() {
        return new RealmImageCache();
    }

    @Named("paperImageCache")
    @Provides
    public IImageCache<Bitmap> paperImageCache() {
        return new PaperImageCache();
    }

}
