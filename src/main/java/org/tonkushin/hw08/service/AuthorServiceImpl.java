package org.tonkushin.hw08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw08.exception.AuthorHasBooksException;
import org.tonkushin.hw08.exception.AuthorNotFoundException;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.repository.AuthorRepository;
import org.tonkushin.hw08.repository.BookRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Author insert(Author item) {
        return repository.insert(item);
    }

    @Override
    public Author getById(String id) throws Exception {
        return repository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public List<Author> getAll() {
        return repository.findAllByOrderByName();
    }

    @Override
    public void deleteById(String id) throws AuthorHasBooksException {
        if (bookRepository.countAllByAuthor_Id(id) == 0)
            repository.deleteById(id);
        else
            throw new AuthorHasBooksException();
    }
}
