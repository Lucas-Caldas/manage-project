package microsoft.com.manage.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.dto.TaskDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import microsoft.com.manage.project.entity.ProjectEntity;
import microsoft.com.manage.project.entity.TaskEntity;
import microsoft.com.manage.project.enums.StatusProject;
import microsoft.com.manage.project.enums.StatusTask;
import microsoft.com.manage.project.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    public TaskControllerTest() {
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAllTasks() throws Exception  {
        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1", "OPEN", customerDTO);

        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskDTOList.add(new TaskDTO(1L,"Fazer algo", StatusTask.IN_PROGRESS,projectDTO));
        taskDTOList.add(new TaskDTO(2L,"Fazer alguma coisa", StatusTask.FINISHED, projectDTO));

        CustomerEntity customer = new CustomerEntity(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectEntity project = new ProjectEntity(1L, "Projeto 1", " Descrição projeto 1", StatusProject.OPEN, customer);

        List<TaskEntity> taskEntityList = new ArrayList<>();
        taskEntityList.add(new TaskEntity(1L,"Fazer algo", StatusTask.IN_PROGRESS,project));
        taskEntityList.add(new TaskEntity(2L,"Fazer alguma coisa", StatusTask.FINISHED, project));


        when(taskService.listAll())
                .thenReturn(taskDTOList);
        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSaveClient() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1", "OPEN", customerDTO);

        TaskDTO taskDTO = new TaskDTO(1L,"Fazer algo", StatusTask.IN_PROGRESS,projectDTO);

        when(taskService.save(any(TaskDTO.class))).thenReturn(taskDTO);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(new ObjectMapper().writeValueAsString(taskDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
        verify(taskService).save(any(TaskDTO.class));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testFetchTaskById() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1", "OPEN", customerDTO);

        TaskDTO taskDTO = new TaskDTO(1L,"Fazer algo", StatusTask.IN_PROGRESS,projectDTO);

        when(taskService.fetchById(1L)).thenReturn(taskDTO);

        mockMvc.perform(get("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteTask() throws Exception {
        doNothing().when(taskService).delete(1L);
        mockMvc.perform(delete("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNoContent());
        verify(taskService).delete(1L);
    }
}
