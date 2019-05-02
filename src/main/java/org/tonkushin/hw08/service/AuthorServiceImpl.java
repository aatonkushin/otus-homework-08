package org.tonkushin.hw08.service;

import org.springframework.stereotype.Service;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author insert(Author item) {
        return repository.insert(item);
    }

    @Override
    public Author getById(String id) throws Exception {
        Optional<Author> opt = repository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new Exception("Автор не найден!");
    }

    @Override
    public List<Author> getAll() {
        return repository.findAllByOrderByName();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
