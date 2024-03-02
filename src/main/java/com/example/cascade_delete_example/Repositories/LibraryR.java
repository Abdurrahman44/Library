package com.example.cascade_delete_example.Repositories;

import com.example.cascade_delete_example.Entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryR extends JpaRepository<Library,Long> {
}
