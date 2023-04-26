package IntegOfJUnit5WithExternalFrameworks_5.example.junit5RESTSpring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SpringBootRestTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testGetAllBooks() {
        ResponseEntity<Book[]> responseEntity = testRestTemplate
                .getForEntity("/books", Book[].class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).length);
    }

    @Test
    void testGetBook() {
        ResponseEntity<Book> responseEntity = testRestTemplate
                .getForEntity("/book/0", Book.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals("The Hobbit", Objects.requireNonNull(responseEntity.getBody()).getName());
    }

    @Test
    void testPostBook() {
        Book book = new Book("I, Robot", "Isaac Asimov",
                LocalDate.of(1950, 12, 2));

        ResponseEntity<Boolean> responseEntity = testRestTemplate
                .postForEntity("/book", book, Boolean.class);
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());

        ResponseEntity<Book[]> responseEntity2 = testRestTemplate
                .getForEntity("/books", Book[].class);
        assertEquals(4, Objects.requireNonNull(responseEntity2.getBody()).length);
    }
}
