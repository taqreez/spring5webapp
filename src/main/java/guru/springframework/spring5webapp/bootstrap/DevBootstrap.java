package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Author taqy = new Author("Taqy", "Ali");
        Book b = new Book("Understanding web server", "1234", "McGraw Hills");
        taqy.getBooks().add(b);
        b.getAuthors().add(taqy);

        authorRepository.save(taqy);
        bookRepository.save(b);

        Author areeb = new Author("Areeb", "Ali");
        Book b2 = new Book("Wonders Ali family", "2344", "Marvel Publication");
        areeb.getBooks().add(b2);

        authorRepository.save(areeb);
        bookRepository.save(b2);
    }
}
