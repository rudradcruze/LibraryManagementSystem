package org.rudradcruze.datamapping.librarymanagementsystem.services;

import org.modelmapper.ModelMapper;
import org.rudradcruze.datamapping.librarymanagementsystem.dto.BookDto;
import org.rudradcruze.datamapping.librarymanagementsystem.entities.Author;
import org.rudradcruze.datamapping.librarymanagementsystem.entities.Book;
import org.rudradcruze.datamapping.librarymanagementsystem.exception.DuplicateResourceException;
import org.rudradcruze.datamapping.librarymanagementsystem.exception.ResourceNotFoundException;
import org.rudradcruze.datamapping.librarymanagementsystem.repositories.AuthorRepository;
import org.rudradcruze.datamapping.librarymanagementsystem.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public BookDto save(BookDto bookDto) {
        return modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)), BookDto.class);
    }

    public BookDto findById(Long id) {
        return modelMapper.map(bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book not found with id: " + id)
        ), BookDto.class);
    }

    public List<BookDto> findByTitle(String title) {
        return bookRepository.findBooksByTitleContainingIgnoreCase(title).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    public BookDto update(BookDto bookDto, Long id) {
        Book book = modelMapper.map(bookDto, Book.class);
        book.setId(id);
        return modelMapper.map(bookRepository.save(book), BookDto.class);
    }

    public boolean deleteById(Long id) {
        isExistById(id);
        bookRepository.deleteById(id);
        return true;
    }

    public BookDto updatePartial(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book not found with id: " + id)
        );
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(Book.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, book, value);
        });
        return modelMapper.map(bookRepository.save(book), BookDto.class);
    }

    public List<BookDto> findByAuthorId(Long authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    public void isExistById(Long id) {
        boolean exist = bookRepository.existsById(id);
        if (!exist) throw new ResourceNotFoundException("Book not found with id: " + id);
    }

    public List<BookDto> findByAfterCertainPublishedDate(String date) {
        List<Book> books = bookRepository.findByPublicationDateAfter(LocalDate.parse(date));
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    public BookDto updateBookAuthor(Long bookId, Long authorId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (book.getAuthor() != null) {
            throw new DuplicateResourceException("Author already assigned to this book");
        }

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));

        book.setAuthor(author);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }
}
