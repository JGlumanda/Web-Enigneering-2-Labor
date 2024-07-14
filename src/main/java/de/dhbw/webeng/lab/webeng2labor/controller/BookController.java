package de.dhbw.webeng.lab.webeng2labor.controller;

import de.dhbw.webeng.lab.webeng2labor.dto.BookResponseDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.CreateBookDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.UpdateBookDTO;
import de.dhbw.webeng.lab.webeng2labor.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable String id) {
        BookResponseDTO bookResponseDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        BookResponseDTO savedBook = bookService.createBook(createBookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable String id, @Valid @RequestBody UpdateBookDTO updateBookDTO) {
        Optional<BookResponseDTO> updatedBook = bookService.updateBook(id, updateBookDTO);
        return updatedBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}