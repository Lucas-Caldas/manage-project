package microsoft.com.manage.project.mapper;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(CustomerEntity cliente);
    CustomerEntity toEntity(CustomerDTO clienteDTO);
    List<CustomerDTO> toDTOList(List<CustomerEntity> customers);
    List<CustomerEntity> toEntityList(List<CustomerDTO> customerDTOs);
}
