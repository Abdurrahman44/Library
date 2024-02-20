package com.example.cascade_delete_example.Repositories;

import com.example.cascade_delete_example.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsR extends JpaRepository<Author,Long> {
}
