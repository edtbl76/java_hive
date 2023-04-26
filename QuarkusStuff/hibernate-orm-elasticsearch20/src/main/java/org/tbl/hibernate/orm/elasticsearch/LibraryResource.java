package org.tbl.hibernate.orm.elasticsearch;

import io.quarkus.runtime.StartupEvent;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.tbl.hibernate.orm.elasticsearch.model.Author;
import org.tbl.hibernate.orm.elasticsearch.model.Book;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryResource {

    @Inject
    SearchSession session;

    @Transactional
    void onStart(@Observes StartupEvent event) throws InterruptedException {
        if (Book.count() > 0) {
            session.massIndexer().startAndWait();
        }
    }

    @PUT
    @Path("book")
    @Transactional
    @Consumes(APPLICATION_FORM_URLENCODED)
    public void addBook(@FormParam String title, @FormParam Long authorId) {
        Author author = Author.findById(authorId);
        if (author == null) {
            return;
        }

        Book book = new Book();
        book.title = title;
        book.author = author;
        book.persist();

        author.books.add(book);
        author.persist();
    }

    @DELETE
    @Path("book/{id}")
    @Transactional
    public void deleteBook(@PathParam Long id) {
        Book book = Book.findById(id);
        if (book != null) {
            book.author.books.remove(book);
            book.delete();
        }
    }

    @PUT
    @Path("author")
    @Transactional
    @Consumes(APPLICATION_FORM_URLENCODED)
    public void addAuthor(@FormParam String firstName, @FormParam String lastName) {
        Author author = new Author();
        author.firstName = firstName;
        author.lastName = lastName;
        author.persist();
    }

    @POST
    @Path("author/{id}")
    @Transactional
    @Consumes(APPLICATION_FORM_URLENCODED)
    public void updateAuthor(@PathParam("id") Long id, @FormParam String firstName, @FormParam String lastName) {
        Author author = Author.findById(id);
        if (author == null) {
            return;
        }
        author.firstName = firstName;
        author.lastName = lastName;
        author.persist();
    }

    @DELETE
    @Path("author/{id}")
    @Transactional
    public void deleteAuthor(@PathParam Long id) {
        Author author = Author.findById(id);
        if (author != null) {
            author.delete();
        }
    }

    @GET
    @Path("author/search")
    @Transactional
    public List<Author> searchAuthors(@QueryParam String pattern, @QueryParam Optional<Integer> size) {
        return session.search(Author.class)
                .where(factory -> pattern == null || pattern.trim().isEmpty() ? factory.matchAll()
                        : factory.simpleQueryString()
                        .fields("firstName", "lastName", "books.title")
                        .matching(pattern))
                .sort(factory -> factory.field("lastName_sort").then().field("firstName_sort"))
                .fetchHits(size.orElse(20));
    }
}
