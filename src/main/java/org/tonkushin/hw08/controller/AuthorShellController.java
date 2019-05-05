package org.tonkushin.hw08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.tonkushin.hw08.exception.AuthorHasBooksException;
import org.tonkushin.hw08.model.Author;
import org.tonkushin.hw08.service.AuthorService;

import java.util.List;

@ShellComponent
public class AuthorShellController {

    private final AuthorService authorService;

    @Autowired
    public AuthorShellController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "Выводит список всех авторов.", key={"get-authors", "ga"})
    public String getAuthors() {

        List<Author> items = authorService.getAll();

        StringBuilder sb = new StringBuilder();
        sb.append("Код\tИмя автора\n");
        for (Author item : items) {
            sb.append(item.getId()).append("\t")
                    .append(item.getName()).append("\n");
        }

        return sb.toString();
    }

    @ShellMethod(value = "Добавляет автора с именем name.", key = {"add-author", "aa"})
    public String addAuthor(String name) {
        authorService.insert(new Author(name));
        return "Автор '"+name+"' добавлен";
    }

    @ShellMethod(value = "Удаляет автора с указанным кодом.", key={"delete-author", "da"})
    public String deleteAuthor(String id){
        try{
            authorService.deleteById(id);
        } catch (AuthorHasBooksException e){
            return "Невозможно удалить автора, т.к. у него есть книги";
        } catch (Exception e){
            return "Ошибка при удалении автора";
        }

        return "Автор удалён";
    }
}

