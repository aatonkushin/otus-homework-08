package org.tonkushin.hw08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.model.Book;
import org.tonkushin.hw08.model.Genre;
import org.tonkushin.hw08.repository.AuthorRepository;
import org.tonkushin.hw08.repository.BookRepository;
import org.tonkushin.hw08.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository repository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Book insert(Book item) {
        return repository.save(item);
    }

    @Override
    public Book getById(String id) throws Exception {
        Optional<Book> opt = repository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else throw new Exception("Книга не найдена!");
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Book insert(String name, String authorId, String genreId) throws Exception {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Genre> genre = genreRepository.findById(genreId);

        Book book = new Book();
        book.setName(name);

        if (author.isPresent())
            book.setAuthor(author.get());
        else
            throw new Exception("Author not found");

        if (genre.isPresent())
            book.setGenre(genre.get());
        else
            throw new Exception("Genre not found");

        return repository.save(book);
    }

    @Override
    public Book addComment(String bookId, String commentText) throws Exception {
        Book book = getById(bookId);
        if (book.getComments() == null){
            book.setComments(new ArrayList<>());
        }
        book.getComments().add(commentText);

        return repository.save(book);
    }
}
