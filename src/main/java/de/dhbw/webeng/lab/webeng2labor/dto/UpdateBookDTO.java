package de.dhbw.webeng.lab.webeng2labor.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateBookDTO {
    @Size(min = 13, max = 13, message = "ISBN must be 13 characters long")
    private String isbn;

    private String title;

    private List<String> authorIds;

    private String publisher;

    private String genre;

    private String publishedDate;

    private String language;

    private String description;

    private Integer pages;
}