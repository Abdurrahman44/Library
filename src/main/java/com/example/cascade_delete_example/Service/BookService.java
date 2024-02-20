package com.example.cascade_delete_example.Service;

import com.example.cascade_delete_example.Entities.Book;
import com.example.cascade_delete_example.Repositories.BookR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookR bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setBookTitle(updatedBook.getBookTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            bookRepository.save(existingBook);
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}