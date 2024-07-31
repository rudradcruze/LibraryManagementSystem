package org.rudradcruze.datamapping.librarymanagementsystem.repositories;

import org.rudradcruze.datamapping.librarymanagementsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);

    List<Book> findBooksByTitleContainingIgnoreCase(String title);

    List<Book> findByPublicationDateAfter(LocalDate publicationDate);
}
