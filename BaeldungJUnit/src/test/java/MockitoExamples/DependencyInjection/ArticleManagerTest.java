package MockitoExamples.DependencyInjection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ArticleManagerTest {

    @Mock ArticleCalculator calculator;
    @Mock ArticleDatabase database;
    @Mock User user;

    @Spy private UserProvider userProvider = new ConsumerUserProvider();

    @InjectMocks private ArticleManager manager;

    @Test public void doTheThing() {
        manager.initialize();
        verify(database).addListener(any(ArticleListener.class));
    }

}
