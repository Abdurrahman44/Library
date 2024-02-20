package com.example.cascade_delete_example.Repositories;


import com.example.cascade_delete_example.Entities.AuthorBookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookDetailsR extends JpaRepository<AuthorBookDetails,Long> {
}
