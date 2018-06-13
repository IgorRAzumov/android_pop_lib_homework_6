package ru.geekbrains.android3_6.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.api.ApiService;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.repo.UsersRepo;

@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule {
    @Provides
    public UsersRepo usersRepo(@Named("realmCache") ICache cache, ApiService api) {
        return new UsersRepo(cache, api);
    }
}
