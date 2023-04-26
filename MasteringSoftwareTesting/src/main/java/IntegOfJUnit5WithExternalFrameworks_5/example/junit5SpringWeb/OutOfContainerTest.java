package IntegOfJUnit5WithExternalFrameworks_5.example.junit5SpringWeb;

import IntegOfJUnit5WithExternalFrameworks_5.example.junit5Mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OutOfContainerTest {

    @InjectMocks
    private WebController webController;

    @Mock
    private PageService pageService;

    @Test
    void test() {
        when(pageService.getPage()).thenReturn("/page.html");
        assertEquals("/page.html", webController.greeting());
        verify(pageService, times(1)).getPage();
    }
}
