package microsoft.com.manage.project.service;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import microsoft.com.manage.project.entity.ProjectEntity;
import microsoft.com.manage.project.enums.StatusProject;
import microsoft.com.manage.project.mapper.ProjectMapper;
import microsoft.com.manage.project.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Test
    void listOnlyOpenProjects() {
        CustomerEntity customer = new CustomerEntity(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectEntity project = new ProjectEntity(1L, "Projeto 1", " Descrição projeto 1", StatusProject.OPEN, customer);
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1" , "OPEN", new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999"));

        Mockito.when(projectRepository.findByStatus(StatusProject.OPEN))
                .thenReturn(List.of(project));
        Mockito.when(projectMapper.toDTO(project)).thenReturn(projectDTO);

        List<ProjectDTO> result = projectService.listOpenProjects();
        Assertions.assertEquals(1, result.size());
    }
}
