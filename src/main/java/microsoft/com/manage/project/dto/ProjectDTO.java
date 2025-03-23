package microsoft.com.manage.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microsoft.com.manage.project.enums.StatusProject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;

    private String name;

    private String description;

    private StatusProject status;

    private CustomerDTO customer;
}
