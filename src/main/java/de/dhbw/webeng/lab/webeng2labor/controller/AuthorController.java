package de.dhbw.webeng.lab.webeng2labor.controller;

import de.dhbw.webeng.lab.webeng2labor.dto.AuthorDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.BookResponseDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.CreateAuthorDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.UpdateAuthorDTO;
import de.dhbw.webeng.lab.webeng2labor.service.AuthorService;
import de.dhbw.webeng.lab.webeng2labor.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable String id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthorId(@PathVariable String id) {
        List<BookResponseDTO> books = bookService.getBooksByAuthorId(id);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody CreateAuthorDTO createAuthorDTO) {
        AuthorDTO savedAuthor = authorService.createAuthor(createAuthorDTO);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable String id, @Valid @RequestBody UpdateAuthorDTO updateAuthorDTO) {
        Optional<AuthorDTO> updatedAuthor = authorService.updateAuthor(id, updateAuthorDTO);
        return updatedAuthor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }
}