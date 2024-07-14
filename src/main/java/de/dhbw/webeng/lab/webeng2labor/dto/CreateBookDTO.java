package de.dhbw.webeng.lab.webeng2labor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateBookDTO {
    @NotNull(message = "ISBN is mandatory")
    @Size(min = 13, max = 13, message = "ISBN must be 13 characters long")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Authors are mandatory")
    private List<String> authorIds;

    @NotBlank(message = "Publisher is mandatory")
    private String publisher;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotNull(message = "Published date is mandatory")
    private String publishedDate;

    @NotBlank(message = "Language is mandatory")
    private String language;

    private String description;

    @NotNull(message = "Pages are mandatory")
    private Integer pages;
}