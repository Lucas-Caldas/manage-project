package microsoft.com.manage.project.mapper;

import microsoft.com.manage.project.dto.CustomerDTO;
import microsoft.com.manage.project.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(CustomerEntity cliente);
    CustomerEntity toEntity(CustomerDTO clienteDTO);
}
