package org.rudradcruze.datamapping.librarymanagementsystem.repositories;

import org.rudradcruze.datamapping.librarymanagementsystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByNameContainingIgnoreCase(String name);
}
