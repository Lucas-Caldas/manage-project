package microsoft.com.manage.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;

    @NotBlank(message = "The name is mandatory")
    @Size(max = 100, message = "The name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "The description is mandatory")
    @Size(max = 100, message = "The description must not exceed 100 characters")
    private String description;

    @NotNull(message = "The status is mandatory")
    private String status;

    private CustomerDTO customer;
}
