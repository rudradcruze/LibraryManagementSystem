package org.rudradcruze.datamapping.librarymanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    @NotBlank(message = "The name field of the author cannot be blank")
    @NotEmpty(message = "The name field of the author cannot be empty")
    @NotNull(message = "The name field of the author cannot be null")
    @Size(min = 2, max = 50, message = "The name field of the author should be in range: [2,50]")
    private String name;

    @NotBlank(message = "The email field of the author cannot be blank")
    @Email(message = "The email field of the author should be a valid email")
    private String email;

    @NotBlank(message = "The address field of the author cannot be blank")
    private String address;

    @NotBlank(message = "The phone field of the author cannot be blank")
    private String phone;
}
