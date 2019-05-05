package org.tonkushin.hw08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw08.exception.GenreHasBooksException;
import org.tonkushin.hw08.exception.GenreNotFoundException;
import org.tonkushin.hw08.model.Genre;
import org.tonkushin.hw08.repository.BookRepository;
import org.tonkushin.hw08.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;
    private final BookRepository bookRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Genre insert(Genre item) {
        return repository.insert(item);
    }

    @Override
    public Genre getById(String id) throws Exception {
        return repository.findById(id).orElseThrow(GenreNotFoundException::new);
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAllByOrderByName();
    }

    @Override
    public void deleteById(String id) throws GenreHasBooksException {
        if (bookRepository.countAllByGenre_Id(id) == 0)
            repository.deleteById(id);
        else
            throw new GenreHasBooksException();
    }
}
