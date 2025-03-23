package microsoft.com.manage.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microsoft.com.manage.project.entity.ProjectEntity;
import microsoft.com.manage.project.enums.StatusTask;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    private String description;

    private StatusTask status;

    private ProjectEntity project;
}
