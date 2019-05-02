package org.tonkushin.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.hw08.model.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findAllByOrderByName();
}
