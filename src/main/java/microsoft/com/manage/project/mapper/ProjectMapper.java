package microsoft.com.manage.project.mapper;

import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDTO(ProjectEntity project);
    ProjectEntity toEntity(ProjectDTO projectDTO);
}
