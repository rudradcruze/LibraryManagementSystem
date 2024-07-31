package org.rudradcruze.datamapping.librarymanagementsystem.controllers;

import org.rudradcruze.datamapping.librarymanagementsystem.dto.BookDto;
import org.rudradcruze.datamapping.librarymanagementsystem.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String date)
    {
        if (title != null) return ResponseEntity.ok(bookService.findByTitle(title));

        if (date != null) return ResponseEntity.ok(bookService.findByAfterCertainPublishedDate(date));

        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBookById(@RequestBody BookDto bookDto,
                                                  @PathVariable Long id) {
        return ResponseEntity.ok(bookService.update(bookDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updatePartialById(@PathVariable Long id,
                                                     @RequestBody Map<String, Object> update) {
        return ResponseEntity.ok(bookService.updatePartial(id, update));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.findByAuthorId(authorId));
    }

    @PatchMapping("/{id}/author/{authorId}")
    public ResponseEntity<BookDto> updateAuthorById(@PathVariable Long id,
                                                   @PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.updateBookAuthor(id, authorId));
    }

    @GetMapping("/publishedAfter/{date}")
    public ResponseEntity<List<BookDto>> getBooksPublishedAfterCertainDate(@PathVariable String date) {
        return ResponseEntity.ok(bookService.findByAfterCertainPublishedDate(date));
    }
}
