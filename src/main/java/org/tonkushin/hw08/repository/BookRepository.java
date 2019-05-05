package org.tonkushin.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.model.Book;
import org.tonkushin.hw08.model.Genre;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    long countAllByAuthor_Id(String authorId);

    long countAllByGenre_Id(String genreId);
}
