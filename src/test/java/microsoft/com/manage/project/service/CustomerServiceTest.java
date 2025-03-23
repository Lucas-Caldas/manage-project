package microsoft.com.manage.project.service;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import microsoft.com.manage.project.exception.ResourceNotFoundException;
import microsoft.com.manage.project.mapper.CustomerMapper;
import microsoft.com.manage.project.repository.CustomerRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Test
    void listAllCustomers() {
        List<CustomerEntity> customerEntityList = new ArrayList<>();
        customerEntityList.add(new CustomerEntity(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111"));
        customerEntityList.add(new CustomerEntity(2L,"Maria Oliveira", "maria.oliveira@example.com", "(22) 22222-2222"));
        customerEntityList.add(new CustomerEntity(3L,"Carlos Souza", "carlos.souza@example.com", "(33) 33333-3333"));

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(new CustomerDTO(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111"));
        customerDTOList.add(new CustomerDTO(2L,"Maria Oliveira", "maria.oliveira@example.com", "(22) 22222-2222"));
        customerDTOList.add(new CustomerDTO(3L,"Carlos Souza", "carlos.souza@example.com", "(33) 33333-3333"));


        Mockito.when(customerRepository.findAll())
                .thenReturn(customerEntityList);
        Mockito.when(customerMapper.toDTOList(customerEntityList)).thenReturn(customerDTOList);

        List<CustomerDTO> result = customerService.listAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }

    @Test
    public void testCreateClient() {
        CustomerEntity customerEntity =  new CustomerEntity(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111");
        CustomerDTO  customerDTO = new CustomerDTO(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111");

        Mockito.when(customerMapper.toEntity(customerDTO)).thenReturn(customerEntity);
        Mockito.when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);
        Mockito.when(customerMapper.toDTO(customerEntity)).thenReturn(customerDTO);

        CustomerDTO customerSaved = customerService.save(customerDTO);

        Assertions.assertNotNull(customerSaved);
        Assertions.assertEquals(1L, customerSaved.getId());
    }

    @Test
    public void testFetchCustomerById() {
        CustomerEntity customerEntity =  new CustomerEntity(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111");
        CustomerDTO  customerDTO = new CustomerDTO(1L,"João Silva", "joao.silva@example.com", "(11) 11111-1111");


        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));
        Mockito.when(customerMapper.toDTO(customerEntity)).thenReturn(customerDTO);

        CustomerDTO customerFound = customerService.fetchById(1L);


        Assertions.assertNotNull(customerFound);
        Assertions.assertEquals(1L, customerFound.getId());
    }

    @Test
    public void testDeleteCustomer() {
        Mockito.when(customerRepository.existsById(1L)).thenReturn(true); // Simula que o cliente existe
        doNothing().when(customerRepository).deleteById(1L);

        customerService.delete(1L);

        verify(customerRepository).existsById(1L);
        verify(customerRepository).deleteById(1L);
    }

    @Test
    public void testDeleteCustomerNotFound() {
        Mockito.when(customerRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.delete(1L);
        });

        verify(customerRepository).existsById(1L);

        verify(customerRepository, never()).deleteById(1L);
    }
}
