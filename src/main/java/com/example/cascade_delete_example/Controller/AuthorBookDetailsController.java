package com.example.cascade_delete_example.Controller;


import com.example.cascade_delete_example.Entities.AuthorBookDetails;
import com.example.cascade_delete_example.Service.AuthorBookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authorbookdetails")
public class AuthorBookDetailsController {

    @Autowired
    private AuthorBookDetailsService authorBookDetailsService;

    @GetMapping
    public List<AuthorBookDetails> getAllAuthorBookDetails() {
        return authorBookDetailsService.getAllAuthorBookDetails();
    }

    @GetMapping("/{id}")
    public AuthorBookDetails getAuthorBookDetailsById(@PathVariable Long id) {
        return authorBookDetailsService.getAuthorBookDetailsById(id);
    }

    @PostMapping("/create")
    public AuthorBookDetails saveAuthorBookDetails(@RequestBody AuthorBookDetails authorBookDetails) {
        return authorBookDetailsService.saveAuthorBookDetails(authorBookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorBookDetails(@PathVariable Long id) {
        authorBookDetailsService.deleteAuthorBookDetails(id);
    }
}
