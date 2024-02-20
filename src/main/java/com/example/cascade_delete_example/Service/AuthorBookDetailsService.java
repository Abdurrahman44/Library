package com.example.cascade_delete_example.Service;


import com.example.cascade_delete_example.Entities.AuthorBookDetails;
import com.example.cascade_delete_example.Repositories.AuthorBookDetailsR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorBookDetailsService {

    @Autowired
    private AuthorBookDetailsR authorBookDetailsRepository;

    public List<AuthorBookDetails> getAllAuthorBookDetails() {
        return authorBookDetailsRepository.findAll();
    }

    public AuthorBookDetails getAuthorBookDetailsById(Long id) {
        return authorBookDetailsRepository.findById(id).orElse(null);
    }

    public AuthorBookDetails saveAuthorBookDetails(AuthorBookDetails authorBookDetails) {
        return authorBookDetailsRepository.save(authorBookDetails);
    }

    public void deleteAuthorBookDetails(Long id) {
        authorBookDetailsRepository.deleteById(id);
    }
}