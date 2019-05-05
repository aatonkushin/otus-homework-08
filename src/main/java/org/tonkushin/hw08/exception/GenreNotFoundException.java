package org.tonkushin.hw08.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException() {
        super("Genre not found");
    }
}
