package org.rudradcruze.datamapping.librarymanagementsystem.controllers;

import jakarta.validation.Valid;
import org.rudradcruze.datamapping.librarymanagementsystem.dto.AuthorDto;
import org.rudradcruze.datamapping.librarymanagementsystem.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(authorService.findByName(name));
        }
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.save(authorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthorById(@RequestBody @Valid AuthorDto authorDto, @PathVariable Long id) {
        return ResponseEntity.ok(authorService.update(authorDto, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDto> updatePartialById(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        return new ResponseEntity<>(authorService.updatePartial(id, update), HttpStatus.OK);
    }
}
