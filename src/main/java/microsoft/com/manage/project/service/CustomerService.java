package microsoft.com.manage.project.service;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import microsoft.com.manage.project.exception.ResourceNotFoundException;
import microsoft.com.manage.project.mapper.CustomerMapper;
import microsoft.com.manage.project.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> listAll() {
        return customerMapper.toDTOList(customerRepository.findAll());
    }

    public CustomerDTO fetchById(Long id) {
        return customerMapper.toDTO(customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    public CustomerDTO save(CustomerDTO customer) {
        return customerMapper.toDTO(customerRepository.save(customerMapper.toEntity(customer)));
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with ID: " + id);
        }

        customerRepository.deleteById(id);
    }
}
