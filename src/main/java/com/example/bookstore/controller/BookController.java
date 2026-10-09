package com.example.bookstore.controller;

import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "CRUD operations for books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "List all books, optionally filtered by author")
    @GetMapping
    public List<Book> getAll(
            @Parameter(description = "Partial author name (case-insensitive)")
            @RequestParam(required = false) String author) {
        return (author == null || author.isBlank())
                ? repository.findAll()
                : repository.findByAuthorContainingIgnoreCase(author);
    }

    @Operation(summary = "Get a book by id")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return findOrThrow(id);
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Book created")
    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookRequest request) {
        Book saved = repository.save(new Book(null, request.title(), request.author(),
                request.isbn(), request.publishedYear()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Update an existing book")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book book = findOrThrow(id);
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setIsbn(request.isbn());
        book.setPublishedYear(request.publishedYear());
        return repository.save(book);
    }

    @Operation(summary = "Delete a book")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(findOrThrow(id));
    }

    private Book findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book " + id + " not found"));
    }

    @GetMapping("/new")
    public String hi()
    {
        return "massu";
    }
}
