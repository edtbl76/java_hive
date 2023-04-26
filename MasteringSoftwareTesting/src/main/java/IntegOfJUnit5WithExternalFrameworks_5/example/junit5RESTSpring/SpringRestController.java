package IntegOfJUnit5WithExternalFrameworks_5.example.junit5RESTSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class SpringRestController {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/books", method = GET)
    public List<Book> getBooks() {
        return libraryService.getBooks();
    }

    @RequestMapping(value = "/book/{index}", method = GET)
    public Book getTeam(@PathVariable("index")int index) {
        return libraryService.getBook(index);
    }

    @RequestMapping(value = "/book", method = POST)
    public ResponseEntity<Boolean> addBook(@RequestBody Book book) {
        libraryService.addBook(book);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
}
