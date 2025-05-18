package org.example.smartlibrary.controller;

import org.example.smartlibrary.Entity.Book;
import org.example.smartlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/")
    public ResponseEntity<Book> addBook(@RequestBody Map<String, Object> requestData) {
        String title = (String) requestData.get("title");
        String author = (String) requestData.get("author");
        String isbn = (String) requestData.get("isbn");
        int copies = (int) requestData.get("copies");
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setCopies(copies);
        book.setAvailableCopies(copies);
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.status(201).body(bookService.addBook(book));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        return ResponseEntity.status(200).body(bookService.getBookDetails(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Map<String, Object> requestData, @PathVariable String id) {
        int copies=(int) requestData.get("copies");
        int availableCopies=(int) requestData.get("availableCopies");
        Book book=bookService.updateBook(id,copies,availableCopies);
        return ResponseEntity.status(200).body(book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(204).body("No Content");
    }
    @GetMapping("/active")
    public ResponseEntity<List<Map<String, Object>>> getPopularBooks() {
        List<Map<String, Object>> result = bookService.getMostBorrowedBooks();
        return ResponseEntity.ok(result);
    }

}
