package org.rudradcruze.datamapping.librarymanagementsystem.services;

import org.modelmapper.ModelMapper;
import org.rudradcruze.datamapping.librarymanagementsystem.dto.AuthorDto;
import org.rudradcruze.datamapping.librarymanagementsystem.entities.Author;
import org.rudradcruze.datamapping.librarymanagementsystem.exception.ResourceNotFoundException;
import org.rudradcruze.datamapping.librarymanagementsystem.repositories.AuthorRepository;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public AuthorDto save(AuthorDto authorDto) {
        return modelMapper.map(authorRepository.save(modelMapper.map(authorDto, Author.class)), AuthorDto.class);
    }

    public AuthorDto findById(Long id) {
        return modelMapper.map(authorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Author not found with id: " + id)
        ), AuthorDto.class);
    }

    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .toList();
    }

    public AuthorDto update(AuthorDto authorDto, Long id) {
        Author author = modelMapper.map(authorDto, Author.class);
        author.setId(id);
        return modelMapper.map(authorRepository.save(author), AuthorDto.class);
    }

    public void isExistById(Long id) {
        boolean exist = authorRepository.existsById(id);
        if (!exist) throw new ResourceNotFoundException("Author not found with id: " + id);
    }

    public boolean deleteById(Long id) {
        isExistById(id);
        authorRepository.deleteById(id);
        return true;
    }

    public List<AuthorDto> findByName(String name) {
        return authorRepository.findAuthorsByNameContainingIgnoreCase(name).stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .toList();
    }

    public AuthorDto updatePartial(Long id, Map<String, Object> update) {
        Author author = modelMapper.map(findById(id), Author.class);
        update.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(Author.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, author, value);
        });
        return modelMapper.map(authorRepository.save(author), AuthorDto.class);
    }
}
