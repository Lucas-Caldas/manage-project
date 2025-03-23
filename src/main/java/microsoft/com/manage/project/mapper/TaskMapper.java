package microsoft.com.manage.project.mapper;

import microsoft.com.manage.project.dto.TaskDTO;
import microsoft.com.manage.project.entity.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDTO(TaskEntity task);
    TaskEntity toEntity(TaskDTO taskDTO);
    List<TaskDTO> toDTOList(List<TaskEntity> tasks);
}
