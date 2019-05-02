package org.tonkushin.hw08.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AuthorServiceImplTest {

    @Test
    public void insert() {
        Author author = getAuthor();

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        Mockito.when(repository.insert(author)).thenReturn(author);

        AuthorService service = new AuthorServiceImpl(repository);
        Assertions.assertThat(service.insert(author)).isEqualTo(author);
    }

    @Test
    public void getById() throws Exception {
        Author author = getAuthor();
        Optional<Author> opt = Optional.of(author);

        AuthorRepository repository = Mockito.mock(AuthorRepository.class);
        String uuid = UUID.randomUUID().toString();
        Mockito.when(repository.findById(uuid)).thenReturn(opt);

        AuthorService service = new AuthorServiceImpl(repository);
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

        AuthorService service = new AuthorServiceImpl(repository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3);
    }

    private Author getAuthor() {
        return new Author("Пушкин");
    }
}
