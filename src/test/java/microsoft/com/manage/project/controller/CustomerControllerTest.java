package microsoft.com.manage.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    public CustomerControllerTest() {
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getAllCustomers() throws Exception  {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        customerDTOList.add(new CustomerDTO(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111"));
        customerDTOList.add(new CustomerDTO(2L,"Maria Oliveira", "maria.oliveira@example.com", "(22) 22222-2222"));
        customerDTOList.add(new CustomerDTO(3L,"Carlos Souza", "carlos.souza@example.com", "(33) 33333-3333"));


        when(customerService.listAll())
                .thenReturn(customerDTOList);
        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("João Silva"))
                .andExpect(jsonPath("$[0].email").value("joao.silva@example.com"))
                .andExpect(jsonPath("$[0].telephone").value("(11) 11111-1111"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Maria Oliveira"))
                .andExpect(jsonPath("$[1].email").value("maria.oliveira@example.com"))
                .andExpect(jsonPath("$[1].telephone").value("(22) 22222-2222"))
                .andExpect(jsonPath("$[2].id").value(3L))
                .andExpect(jsonPath("$[2].name").value("Carlos Souza"))
                .andExpect(jsonPath("$[2].email").value("carlos.souza@example.com"))
                .andExpect(jsonPath("$[2].telephone").value("(33) 33333-3333"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSaveClient() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111");

        when(customerService.save(any(CustomerDTO.class))).thenReturn(customerDTO);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(new ObjectMapper().writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João Silva"))
                .andExpect(jsonPath("$.email").value("joao.silva@example.com"))
                .andExpect(jsonPath("$.telephone").value("(11) 11111-1111"));
        verify(customerService).save(any(CustomerDTO.class));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testFetchCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111");

        when(customerService.fetchById(1L)).thenReturn(customerDTO);

        mockMvc.perform(get("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).delete(1L);
        mockMvc.perform(delete("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNoContent());
        verify(customerService).delete(1L);
    }
}
