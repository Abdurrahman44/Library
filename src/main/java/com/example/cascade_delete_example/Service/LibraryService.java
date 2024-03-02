package com.example.cascade_delete_example.Service;

import com.example.cascade_delete_example.Entities.Library;
import com.example.cascade_delete_example.Repositories.LibraryR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryR libraryRepository;

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Library addLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}