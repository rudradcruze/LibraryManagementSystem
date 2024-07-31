package org.rudradcruze.datamapping.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;

    @NotNull(message = "The title field of the book cannot be null")
    @NotEmpty(message = "The title field of the book cannot be empty")
    @NotBlank(message = "The title field of the book cannot be blank")
    @Size(min = 3, max = 150, message = "The title field of the book should be in range: [3,150]")
    private String title;

    @NotNull(message = "The ISBN field of the book cannot be null")
    private String isbn;

    @NotNull(message = "The publisher field of the book cannot be null")
    private String publisher;

    private LocalDate publicationDate;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @NotNull(message = "The language field of the book cannot be null")
    private String language;

    @NotNull(message = "The category field of the book cannot be null")
    private String category;

    @NotNull(message = "The description field of the book cannot be null")
    private String description;

    @Min(value = 5, message = "The number of pages should be at least 5")
    @Max(value = 10000, message = "The number of pages should be at most 10000")
    private int numberOfPages;

    @JsonProperty("isActive")
    private Boolean isAvailable;

    @DecimalMin(value = "10.00", message = "The price of the book should be at least 10.00")
    @DecimalMax(value = "5000.00", message = "The price of the book should be at most 5000.00")
    @Digits(integer = 4, fraction = 2, message = "The price of the book should be in the form of xxxx.xx")
    private Double price;
}
