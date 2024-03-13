package it.syncroweb.logintest.dto;

import it.syncroweb.logintest.utils.validation.PasswordMatches;
import it.syncroweb.logintest.utils.validation.ValidEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    @Email(regexp = "[]", message = "")
    private String email;


}
