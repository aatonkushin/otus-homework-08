package org.tonkushin.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.hw08.model.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findAllByOrderByName();
}
