package com.example.cascade_delete_example.Service;

import com.example.cascade_delete_example.Entities.Genre;
import com.example.cascade_delete_example.Repositories.GenreR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreR genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}