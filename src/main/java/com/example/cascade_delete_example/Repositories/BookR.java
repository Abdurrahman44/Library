package com.example.cascade_delete_example.Repositories;

import com.example.cascade_delete_example.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookR extends JpaRepository<Book,Long> {
}
