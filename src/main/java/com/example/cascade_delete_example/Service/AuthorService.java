package com.example.cascade_delete_example.Service;

import com.example.cascade_delete_example.Entities.Author;
import com.example.cascade_delete_example.Repositories.AuthorsR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorsR authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public void updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor != null) {
            existingAuthor.setAuthorName(updatedAuthor.getAuthorName());
            authorRepository.save(existingAuthor);
        }
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}