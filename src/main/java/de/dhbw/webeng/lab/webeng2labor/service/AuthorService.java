package de.dhbw.webeng.lab.webeng2labor.service;

import de.dhbw.webeng.lab.webeng2labor.dto.AddressDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.AuthorDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.CreateAuthorDTO;
import de.dhbw.webeng.lab.webeng2labor.dto.UpdateAuthorDTO;
import de.dhbw.webeng.lab.webeng2labor.exception.ResourceNotFoundException;
import de.dhbw.webeng.lab.webeng2labor.model.Address;
import de.dhbw.webeng.lab.webeng2labor.model.Author;
import de.dhbw.webeng.lab.webeng2labor.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(String id) {
        return authorRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    public AuthorDTO createAuthor(CreateAuthorDTO createAuthorDTO) {
        Author author = convertToEntity(createAuthorDTO);
        Author savedAuthor = authorRepository.save(author);
        return convertToDTO(savedAuthor);
    }

    public Optional<AuthorDTO> updateAuthor(String id, UpdateAuthorDTO updateAuthorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        if (updateAuthorDTO.getFirstName() != null) {
            author.setFirstName(updateAuthorDTO.getFirstName());
        }
        if (updateAuthorDTO.getLastName() != null) {
            author.setLastName(updateAuthorDTO.getLastName());
        }
        if (updateAuthorDTO.getEmail() != null) {
            author.setEmail(updateAuthorDTO.getEmail());
        }
        if (updateAuthorDTO.getAddress() != null) {
            AddressDTO addressDTO = updateAuthorDTO.getAddress();
            if (author.getAddress() == null) {
                author.setAddress(new Address());
            }
            author.getAddress().setStreet(addressDTO.getStreet());
            author.getAddress().setPostalCode(addressDTO.getPostalCode());
            author.getAddress().setCity(addressDTO.getCity());
        }

        Author updatedAuthor = authorRepository.save(author);
        return Optional.of(convertToDTO(updatedAuthor));
    }

    public void deleteAuthor(String id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
    }

    private Author convertToEntity(CreateAuthorDTO createAuthorDTO) {
        Author author = new Author();
        author.setFirstName(createAuthorDTO.getFirstName());
        author.setLastName(createAuthorDTO.getLastName());
        author.setEmail(createAuthorDTO.getEmail());
        if (createAuthorDTO.getAddress() != null) {
            AddressDTO addressDTO = createAuthorDTO.getAddress();
            Address address = new Address();
            address.setStreet(addressDTO.getStreet());
            address.setPostalCode(addressDTO.getPostalCode());
            address.setCity(addressDTO.getCity());
            author.setAddress(address);
        }
        return author;
    }

    private AuthorDTO convertToDTO(Author author) {
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
