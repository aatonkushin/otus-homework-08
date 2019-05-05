package org.tonkushin.hw08.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("Book not found");
    }
}
