package MockitoExamples.DependencyInjection;

import java.util.List;

public class ArticleManager {

    private User user;
    private ArticleDatabase database;

    public ArticleManager(User user, ArticleDatabase articleDatabase) {
        super();
        this.user = user;
        this.database = articleDatabase;
    }

    public void initialize() {
        database.addListener(new ArticleListener());
    }
}

class User {}

interface UserProvider {}

class ConsumerUserProvider implements UserProvider {}

class ArticleDatabase {

    List<ArticleListener> listenerPool;

    public void addListener(ArticleListener listener) {
        listenerPool.add(listener);
    }
}

class ArticleListener {}

class ArticleCalculator {}