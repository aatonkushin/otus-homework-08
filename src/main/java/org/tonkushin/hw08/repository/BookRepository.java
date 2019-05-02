package org.tonkushin.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.hw08.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
