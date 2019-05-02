package org.tonkushin.hw08.service;

import org.tonkushin.hw08.model.Book;

public interface BookService extends Service<Book> {
    Book insert(String name, String authorId, String genreId) throws Exception;
    Book addComment(String bookId, String commentText) throws Exception;
}
