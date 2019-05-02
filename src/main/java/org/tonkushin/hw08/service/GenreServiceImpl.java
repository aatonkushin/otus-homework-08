package org.tonkushin.hw08.service;

import org.springframework.stereotype.Service;
import org.tonkushin.hw08.model.Genre;
import org.tonkushin.hw08.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre insert(Genre item) {
        return repository.insert(item);
    }

    @Override
    public Genre getById(String id) throws Exception {
        Optional<Genre> opt = repository.findById(id);

        if (opt.isPresent()){
            return opt.get();
        } else {
            throw new Exception("Жанр не найден!");
        }

    }

    @Override
    public List<Genre> getAll() {
        return repository.findAllByOrderByName();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
