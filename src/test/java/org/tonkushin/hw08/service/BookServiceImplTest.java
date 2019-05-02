package org.tonkushin.hw08.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.model.Book;
import org.tonkushin.hw08.model.Genre;
import org.tonkushin.hw08.repository.AuthorRepository;
import org.tonkushin.hw08.repository.BookRepository;
import org.tonkushin.hw08.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    @Test
    public void insert() {
        Book book = getBook();

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.save(book)).thenReturn(book);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.insert(book)).isEqualTo(book);
    }

    @Test
    public void getAll() {
        List<Book> books = new ArrayList<>(3);
        for (long i = 1; i <= 3; i++) {
            books.add(getBook());
        }

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.findAll()).thenReturn(books);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3);
    }

    @Test
    public void insert1() throws Exception {
        Book book = getBook();
        Optional<Book> opt = Optional.of(book);

        Author author = getAuthor();
        Optional<Author> optAuthor = Optional.of(author);

        Genre genre = getGenre();
        Optional<Genre> optGenre = Optional.of(genre);

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.save(book)).thenReturn(book);
        String uuid = UUID.randomUUID().toString();
        Mockito.when(authorRepository.findById(uuid)).thenReturn(optAuthor);
        Mockito.when(genreRepository.findById(uuid)).thenReturn(optGenre);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.insert(book.getName(), uuid, uuid).getName()).isEqualTo(book.getName());
    }

    @Test
    public void addComment() throws Exception {
        Book book = getBook();
        book.setComments(new ArrayList<>());
        book.getComments().add("Комментарий");

        Optional<Book> opt = Optional.of(book);

        Author author = getAuthor();
        Optional<Author> optAuthor = Optional.of(author);

        Genre genre = getGenre();
        Optional<Genre> optGenre = Optional.of(genre);

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.save(book)).thenReturn(book);
        String uuid = UUID.randomUUID().toString();
        Mockito.when(repository.findById(uuid)).thenReturn(opt);

        Mockito.when(authorRepository.findById(uuid)).thenReturn(optAuthor);
        Mockito.when(genreRepository.findById(uuid)).thenReturn(optGenre);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.addComment(uuid, "Комментарий").getComments().isEmpty()).isFalse();
    }

    private Book getBook() {
        Author a = getAuthor();
        Genre g = getGenre();
        return new Book("Капитанская дочь", g, a);
    }

    private Author getAuthor() {
        return new Author("Пушкин");
    }

    private Genre getGenre() {
        return new Genre("Драма");
    }
}
