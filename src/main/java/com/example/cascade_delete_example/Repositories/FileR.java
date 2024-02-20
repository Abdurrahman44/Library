package com.example.cascade_delete_example.Repositories;

import com.example.cascade_delete_example.Entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileR  extends JpaRepository<FileEntity,Long> {
}
