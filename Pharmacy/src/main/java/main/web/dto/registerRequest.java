package main.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class registerRequest {
    @NotBlank
    @Size(min = 3, max = 10,message = "Username length must be between 3 and 10 symbols.")
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 10,message = "Username length must be between 3 and 10 symbols.")
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 20,message = "Password length must be between 6 and 20 symbols")
    private String password;
}
