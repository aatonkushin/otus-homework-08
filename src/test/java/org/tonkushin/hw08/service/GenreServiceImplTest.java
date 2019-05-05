package org.tonkushin.hw08.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw08.model.Genre;
import org.tonkushin.hw08.repository.BookRepository;
import org.tonkushin.hw08.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
public class GenreServiceImplTest {

    @Test
    public void insert() {
        Genre genre = getGenre();

        GenreRepository repository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.insert(genre)).thenReturn(genre);

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        GenreService service = new GenreServiceImpl(repository, bookRepository);
        Assertions.assertThat(service.insert(genre)).isEqualTo(genre);
    }

    @Test
    public void getById() throws Exception {
        Genre genre = getGenre();
        Optional<Genre> opt = Optional.of(genre);

        GenreRepository repository = Mockito.mock(GenreRepository.class);
        String uuid = UUID.randomUUID().toString();
        Mockito.when(repository.findById(uuid)).thenReturn(opt);

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        GenreService service = new GenreServiceImpl(repository, bookRepository);
        Assertions.assertThat(service.getById(uuid)).isEqualTo(genre);
    }

    @Test
    public void getAll() {
        List<Genre> genres = new ArrayList<>(3);
        for (long i = 1; i <= 3; i++) {
            genres.add(getGenre());
        }

        GenreRepository repository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.findAllByOrderByName()).thenReturn(genres);

        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        GenreService service = new GenreServiceImpl(repository, bookRepository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3);
    }

    private Genre getGenre() {
        return new Genre("Драма");
    }
}
