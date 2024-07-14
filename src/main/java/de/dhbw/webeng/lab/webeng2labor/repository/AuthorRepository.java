package de.dhbw.webeng.lab.webeng2labor.repository;

import de.dhbw.webeng.lab.webeng2labor.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
