package ru.geekbrains.android3_6;

import io.paperdb.Paper;
import io.realm.Realm;
import ru.geekbrains.android3_6.di.DaggerAppComponent;
import ru.geekbrains.android3_6.di.modules.AppModule;


public class App extends com.activeandroid.app.Application {
    private static App instance;

    private ru.geekbrains.android3_6.di.AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Paper.init(this);
        Realm.init(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public ru.geekbrains.android3_6.di.AppComponent getAppComponent() {
        return appComponent;
    }
}
