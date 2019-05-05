package org.tonkushin.hw08.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.repository.AuthorRepository;
import org.tonkushin.hw08.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
public class AuthorServiceImplTest {

    @Test
    public void insert() {
        Author author = getAuthor();

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        Mockito.when(repository.insert(author)).thenReturn(author);

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        AuthorService service = new AuthorServiceImpl(repository, bookRepository);
        Assertions.assertThat(service.insert(author)).isEqualTo(author);
    }

    @Test
    public void getById() throws Exception {
        Author author = getAuthor();
        Optional<Author> opt = Optional.of(author);

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        String uuid = UUID.randomUUID().toString();
        Mockito.when(repository.findById(uuid)).thenReturn(opt);

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        AuthorService service = new AuthorServiceImpl(repository, bookRepository);
        Assertions.assertThat(service.getById(uuid)).isEqualTo(author);
    }

    @Test
    public void getAll() {
        List<Author> authors = new ArrayList<>(3);
        for (long i = 1; i <= 3; i++) {
            authors.add(getAuthor());
        }

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        Mockito.when(repository.findAllByOrderByName()).thenReturn(authors);

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        AuthorService service = new AuthorServiceImpl(repository, bookRepository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3);
    }

    private Author getAuthor() {
        return new Author("Пушкин");
    }
}
