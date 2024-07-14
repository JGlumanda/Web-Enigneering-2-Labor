package de.dhbw.webeng.lab.webeng2labor.repository;

import de.dhbw.webeng.lab.webeng2labor.model.Author;
import de.dhbw.webeng.lab.webeng2labor.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAuthorsContaining(Author author);
}
