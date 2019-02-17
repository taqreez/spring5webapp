package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepositoriy;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepositoriy publisherRepositoriy;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepositoriy publisherRepositoriy) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepositoriy = publisherRepositoriy;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Author taqy = new Author("Taqy", "Ali");
        Publisher p = new Publisher("McGraw Hills", "Bangalore");
        Book b = new Book("Understanding web server", "1234", p);
        taqy.getBooks().add(b);
        b.getAuthors().add(taqy);

        publisherRepositoriy.save(p);
        authorRepository.save(taqy);
        bookRepository.save(b);

        Author areeb = new Author("Areeb", "Ali");
        Publisher p2 = new Publisher("Marvel Publication", "Delhi");
        Book b2 = new Book("Wonders Ali family", "2344", p2);
        areeb.getBooks().add(b2);

        publisherRepositoriy.save(p2);
        authorRepository.save(areeb);
        bookRepository.save(b2);
    }
}
