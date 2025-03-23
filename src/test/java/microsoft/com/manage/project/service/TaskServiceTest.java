package microsoft.com.manage.project.service;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.dto.TaskDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import microsoft.com.manage.project.entity.ProjectEntity;
import microsoft.com.manage.project.entity.TaskEntity;
import microsoft.com.manage.project.enums.StatusProject;
import microsoft.com.manage.project.enums.StatusTask;
import microsoft.com.manage.project.exception.ResourceNotFoundException;
import microsoft.com.manage.project.mapper.TaskMapper;
import microsoft.com.manage.project.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskService TaskService;

    @Mock
    private TaskRepository TaskRepository;

    @Mock
    private TaskMapper TaskMapper;

    @Test
    void listAllTasks() {
        CustomerEntity customer = new CustomerEntity(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectEntity project = new ProjectEntity(1L, "Projeto 1", " Descrição projeto 1", StatusProject.OPEN, customer);

        List<TaskEntity> TaskEntityList = new ArrayList<>();
        TaskEntityList.add(new TaskEntity(1L,"Fazer algo", StatusTask.IN_PROGRESS,project));
        TaskEntityList.add(new TaskEntity(2L,"Fazer alguma coisa", StatusTask.FINISHED, project));

        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1", "OPEN", customerDTO);

        List<TaskDTO> TaskDTOList = new ArrayList<>();
        TaskDTOList.add(new TaskDTO(1L,"Fazer algo", StatusTask.IN_PROGRESS,projectDTO));
        TaskDTOList.add(new TaskDTO(2L,"Fazer alguma coisa", StatusTask.FINISHED, projectDTO));


        Mockito.when(TaskRepository.findAll())
                .thenReturn(TaskEntityList);
        Mockito.when(TaskMapper.toDTOList(TaskEntityList)).thenReturn(TaskDTOList);

        List<TaskDTO> result = TaskService.listAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testCreateClient() {
        CustomerEntity customer = new CustomerEntity(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectEntity project = new ProjectEntity(1L, "Projeto 1", " Descrição projeto 1", StatusProject.OPEN, customer);
        TaskEntity taskEntity = new TaskEntity(1L,"Fazer algo", StatusTask.IN_PROGRESS,project);

        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1", "OPEN", customerDTO);
        TaskDTO  taskDTO = new TaskDTO(1L,"Fazer algo", StatusTask.IN_PROGRESS,projectDTO);

        Mockito.when(TaskMapper.toEntity(taskDTO)).thenReturn(taskEntity);
        Mockito.when(TaskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);
        Mockito.when(TaskMapper.toDTO(taskEntity)).thenReturn(taskDTO);

        TaskDTO taskSaved = TaskService.save(taskDTO);

        Assertions.assertNotNull(taskSaved);
        Assertions.assertEquals(1L, taskSaved.getId());
    }

    @Test
    public void testFetchTaskById() {

        CustomerEntity customer = new CustomerEntity(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectEntity project = new ProjectEntity(1L, "Projeto 1", " Descrição projeto 1", StatusProject.OPEN, customer);
        TaskEntity taskEntity = new TaskEntity(1L,"Fazer algo", StatusTask.IN_PROGRESS,project);

        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1", "OPEN", customerDTO);
        TaskDTO  taskDTO = new TaskDTO(1L,"Fazer algo", StatusTask.IN_PROGRESS,projectDTO);


        Mockito.when(TaskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
        Mockito.when(TaskMapper.toDTO(taskEntity)).thenReturn(taskDTO);

        TaskDTO TaskFound = TaskService.fetchById(1L);


        Assertions.assertNotNull(TaskFound);
        Assertions.assertEquals(1L, TaskFound.getId());
    }

    @Test
    public void testDeleteTask() {
        Mockito.when(TaskRepository.existsById(1L)).thenReturn(true); // Simula que o cliente existe
        doNothing().when(TaskRepository).deleteById(1L);

        TaskService.delete(1L);

        verify(TaskRepository).existsById(1L);
        verify(TaskRepository).deleteById(1L);
    }

    @Test
    public void testDeleteTaskNotFound() {
        Mockito.when(TaskRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            TaskService.delete(1L);
        });

        verify(TaskRepository).existsById(1L);

        verify(TaskRepository, never()).deleteById(1L);
    }
}
