package org.tonkushin.hw08;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw08.repository.AuthorRepositoryTest;
import org.tonkushin.hw08.service.AuthorServiceImplTest;
import org.tonkushin.hw08.service.BookServiceImplTest;
import org.tonkushin.hw08.service.GenreServiceImplTest;

@RunWith(Suite.class)
@SpringBootTest
@TestPropertySource("/test.properties")
@Suite.SuiteClasses({AuthorServiceImplTest.class, GenreServiceImplTest.class, BookServiceImplTest.class, AuthorRepositoryTest.class})
public class Hw08ApplicationTests {

	@Test
	public void contextLoads() {
	}
}
