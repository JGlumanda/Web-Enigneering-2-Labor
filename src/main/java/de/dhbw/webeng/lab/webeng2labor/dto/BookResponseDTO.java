package de.dhbw.webeng.lab.webeng2labor.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookResponseDTO {
    private String id;
    private String isbn;
    private String title;
    private List<AuthorDTO> authors;
    private String publisher;
    private String genre;
    private String publishedDate;
    private String language;
    private String description;
    private Integer pages;
}
