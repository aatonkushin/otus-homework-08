package org.tonkushin.hw08.exception;

public class AuthorHasBooksException extends Exception {
    public AuthorHasBooksException() {
        super("Author has books");
    }
}
