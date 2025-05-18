package org.example.microbookservice.infrastructure.controller;

import org.example.microbookservice.domain.model.Book;
import org.example.microbookservice.domain.port.in.BookUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookUseCase service;
    public BookController(BookUseCase service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        return ResponseEntity.ok(service.addBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable String id) {
        return service.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Book>> search(@RequestParam String search) {
        return ResponseEntity.ok(service.searchBooks(search));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable String id, @RequestBody Map<String, Integer> book) {
        return ResponseEntity.ok(service.updateBook(id, book.get("copies")));
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Book> patchAvailability(@PathVariable String id, @RequestBody AvailabilityRequest request) {
        int delta = request.getOperation().equalsIgnoreCase("increment") ? 1 : -1;
        return ResponseEntity.ok(service.adjustAvailability(id, delta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    public static class AvailabilityRequest {
        private int availableCopies;
        private String operation;

        public int getAvailableCopies() { return availableCopies; }
        public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
        public String getOperation() { return operation; }
        public void setOperation(String operation) { this.operation = operation; }
    }

}
