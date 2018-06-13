package ru.geekbrains.android3_6.model.cache.dataCache;

import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.entity.Repository;
import ru.geekbrains.android3_6.model.entity.User;

public class PaperCache implements ICache {
    @Override
    public void putUser(User user) {
        Paper.book("users").write(user.getLogin(), user);
    }

    @Override
    public Observable<User> getUser(String username) {
        return Observable.create(o -> {
            if (!Paper.book("users").contains(username)) {
                o.onError(new RuntimeException("No user in cache"));
            }
            o.onNext(Paper.book("users").read(username));
            o.onComplete();
        });
    }

    @Override
    public void putUserRepos(User user, List<Repository> repositories) {
        Paper.book("repos").write(user.getReposUrl(), repositories);
    }

    @Override
    public Observable<List<Repository>> getUserRepos(User user) {
        return Observable.create(o -> {
            String md5 = user.getReposUrl();
            List<Repository> repositories = null;
            if (Paper.book("repos").contains(md5)) {
                repositories = Paper.book("repos").read(md5);
            }

            if (repositories == null) {
                o.onError(new RuntimeException("No repo in cache"));
            }
            o.onNext(repositories);
            o.onComplete();
        });
    }
}