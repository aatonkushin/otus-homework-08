package org.tonkushin.hw08.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw08.model.Author;

@DataMongoTest
@ComponentScan("org.tonkushin.hw08.repository")
@TestPropertySource("/test.properties")
@EnableConfigurationProperties
@RunWith(SpringRunner.class)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    public void test() throws Exception {

        Author item1 = new Author("Достоевский");
        Author item2 = new Author("Пушкин");

        repository.save(item2);
        repository.save(item1);

        Assertions.assertThat(repository.findAllByOrderByName().size() == 2);
        Assertions.assertThat(repository.findAllByOrderByName().get(0).getName().equals("Достоевский"));
    }
}
