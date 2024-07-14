package de.dhbw.webeng.lab.webeng2labor.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    @NotNull
    @Size(min = 13, max = 13)
    private String isbn;

    @NotBlank
    private String title;

    @DBRef
    private List<Author> authors;

    @NotBlank
    private String publisher;

    @NotBlank
    private String genre;

    @NotNull
    private String publishedDate;

    @NotBlank
    private String language;

    private String description;

    @NotNull
    private Integer pages;
}