package com.example.cascade_delete_example.Repositories;

import com.example.cascade_delete_example.Entities.Genre;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;


public interface GenreR extends JpaRepository<Genre,Long> {
}
