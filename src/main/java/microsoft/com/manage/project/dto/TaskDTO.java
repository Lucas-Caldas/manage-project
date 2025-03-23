package microsoft.com.manage.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microsoft.com.manage.project.enums.StatusTask;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    @NotBlank(message = "The description is mandatory")
    @Size(max = 100, message = "The description must not exceed 100 characters")
    private String description;

    @NotNull(message = "The status is mandatory")
    private StatusTask status;

    private ProjectDTO project;
}
