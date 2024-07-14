package de.dhbw.webeng.lab.webeng2labor.service;

import de.dhbw.webeng.lab.webeng2labor.dto.*;
import de.dhbw.webeng.lab.webeng2labor.exception.ResourceNotFoundException;
import de.dhbw.webeng.lab.webeng2labor.model.Author;
import de.dhbw.webeng.lab.webeng2labor.model.Book;
import de.dhbw.webeng.lab.webeng2labor.repository.AuthorRepository;
import de.dhbw.webeng.lab.webeng2labor.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(String id) {
        return bookRepository.findById(id)
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public BookResponseDTO createBook(CreateBookDTO createBookDTO) {
        Book book = convertToEntity(createBookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToResponseDTO(savedBook);
    }

    public Optional<BookResponseDTO> updateBook(String id, UpdateBookDTO updateBookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        if (updateBookDTO.getIsbn() != null) {
            book.setIsbn(updateBookDTO.getIsbn());
        }
        if (updateBookDTO.getTitle() != null) {
            book.setTitle(updateBookDTO.getTitle());
        }
        if (updateBookDTO.getAuthorIds() != null) {
            List<Author> authors = authorRepository.findAllById(updateBookDTO.getAuthorIds());
            if (authors.size() != updateBookDTO.getAuthorIds().size()) {
                throw new ResourceNotFoundException("One or more authors not found");
            }
            book.setAuthors(authors);
        }
        if (updateBookDTO.getPublisher() != null) {
            book.setPublisher(updateBookDTO.getPublisher());
        }
        if (updateBookDTO.getGenre() != null) {
            book.setGenre(updateBookDTO.getGenre());
        }
        if (updateBookDTO.getPublishedDate() != null) {
            book.setPublishedDate(updateBookDTO.getPublishedDate());
        }
        if (updateBookDTO.getLanguage() != null) {
            book.setLanguage(updateBookDTO.getLanguage());
        }
        if (updateBookDTO.getDescription() != null) {
            book.setDescription(updateBookDTO.getDescription());
        }
        if (updateBookDTO.getPages() != null) {
            book.setPages(updateBookDTO.getPages());
        }

        Book updatedBook = bookRepository.save(book);
        return Optional.of(convertToResponseDTO(updatedBook));
    }

    public void deleteBook(String id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
    }

    public List<BookResponseDTO> getBooksByAuthorId(String authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

        List<Book> books = bookRepository.findByAuthorsContaining(author);
        return books.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private Book convertToEntity(CreateBookDTO createBookDTO) {
        Book book = new Book();
        book.setIsbn(createBookDTO.getIsbn());
        book.setTitle(createBookDTO.getTitle());
        List<Author> authors = authorRepository.findAllById(createBookDTO.getAuthorIds());
        if (authors.size() != createBookDTO.getAuthorIds().size()) {
            throw new ResourceNotFoundException("One or more authors not found");
        }
        book.setAuthors(authors);
        book.setPublisher(createBookDTO.getPublisher());
        book.setGenre(createBookDTO.getGenre());
        book.setPublishedDate(createBookDTO.getPublishedDate());
        book.setLanguage(createBookDTO.getLanguage());
        book.setDescription(createBookDTO.getDescription());
        book.setPages(createBookDTO.getPages());
        return book;
    }

    private BookResponseDTO convertToResponseDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setIsbn(book.getIsbn());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthors(book.getAuthors().stream()
                .map(this::convertToAuthorDTO)
                .collect(Collectors.toList()));
        bookResponseDTO.setPublisher(book.getPublisher());
        bookResponseDTO.setGenre(book.getGenre());
        bookResponseDTO.setPublishedDate(book.getPublishedDate());
        bookResponseDTO.setLanguage(book.getLanguage());
        bookResponseDTO.setDescription(book.getDescription());
        bookResponseDTO.setPages(book.getPages());
        return bookResponseDTO;
    }

    private AuthorDTO convertToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setEmail(author.getEmail());
        if (author.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(author.getAddress().getStreet());
            addressDTO.setPostalCode(author.getAddress().getPostalCode());
            addressDTO.setCity(author.getAddress().getCity());
            authorDTO.setAddress(addressDTO);
        }
        return authorDTO;
    }
}