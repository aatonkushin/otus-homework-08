package org.tonkushin.hw08.exception;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException() {
        super("Author not found");
    }
}
