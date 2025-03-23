package microsoft.com.manage.project.controller;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    public ProjectControllerTest() {
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void listOnlyOpenProjects() throws Exception  {
        CustomerDTO customerDTO = new CustomerDTO(1L, "Empresa X", "email@empresa.com", "(11) 9999-9999");
        ProjectDTO projectDTO = new ProjectDTO(1L, "Projeto 1", " Descrição projeto 1" , "OPEN", customerDTO);

        Mockito.when(projectService.listOpenProjects())
                .thenReturn(List.of(projectDTO));
        mockMvc.perform(get("/projects/opens")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Verifica se retorna HTTP 200 OK
                .andExpect(jsonPath("$[0].id").value(1)) // Verifica se o primeiro item tem ID 1
                .andExpect(jsonPath("$[0].name").value("Projeto 1")) // Verifica o nome do projeto
                .andExpect(jsonPath("$[0].status").value("OPEN")); // Verifica o status
    }
}
