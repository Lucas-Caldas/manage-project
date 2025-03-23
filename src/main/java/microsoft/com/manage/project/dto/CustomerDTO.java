package microsoft.com.manage.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "The name is mandatory")
    @Size(max = 100, message = "The name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "The email must be valid")
    private String email;

    @NotBlank(message = "The phone is a must")
    @Size(max = 20, message = "The phone number must be a maximum of 20 characters")
    private String telephone;
}
