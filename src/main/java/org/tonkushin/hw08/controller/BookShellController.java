package org.tonkushin.hw08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import org.tonkushin.hw08.model.Book;
import org.tonkushin.hw08.service.BookService;

import java.util.List;

@ShellComponent
public class BookShellController {
    private final BookService service;

    @Autowired
    public BookShellController(BookService service) {
        this.service = service;
    }

    @ShellMethod(value = "Выводит список всех книг", key = {"get-books", "gb"})
    public String getBooks() {
        List<Book> items = service.getAll();

        StringBuilder sb = new StringBuilder();
        sb.append("Код\tНазвание книги\tАвтор\tЖанр\n");
        for (Book item : items) {
            sb.append(item.getId()).append("\t")
                    .append(item.getName()).append("\t")
                    .append(item.getAuthor().getName()).append("\t")
                    .append(item.getGenre().getName()).append("\n");
        }

        return sb.toString();
    }

    @ShellMethod(value = "Добавляет книгу с названием name, автором с кодом authorId и жанром с кодом genreId", key = {"add-book", "ab"})
    public String addBook(String name, String authorId, String genreId) {
        try {
            service.insert(name, authorId, genreId);
        } catch (Exception e) {
            return e.toString();
        }
        return "Книга с названием '" + name + "' добавлена";
    }

    @ShellMethod(value = "Добавляет комментарий к книге", key = {"add-book-comment", "abc"})
    public String addBookComment(String bookId, String commentText) {
        try {
            service.addComment(bookId, commentText);
        } catch (Exception e) {
            return e.toString();
        }
        return String.format("Комментарий %s добавлен", commentText) ;
    }

    @ShellMethod(value = "Выводит все комментарии к книге", key = {"get-book-comments", "gbc"})
    @Transactional
    public String getBookComments(String bookId) {
        Book b = null;
        try {
            b = service.getById(bookId);
        } catch (Exception e) {
            return e.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Название: ").append(b.getName()).append("\n");
        sb.append("Автор: ").append(b.getAuthor().getName()).append("\n");
        sb.append("Жанр: ").append(b.getGenre().getName()).append("\n");
        sb.append("Коментарии:\n");

        for (String bc : b.getComments()) {
            sb.append(bc).append("\n");
        }

        return sb.toString();
    }

    @ShellMethod(value = "Удаляет книгу с указанным кодом.", key = {"delete-book", "db"})
    public String deleteBook(String id) {
        service.deleteById(id);
        return "Книга удалена";
    }
}
