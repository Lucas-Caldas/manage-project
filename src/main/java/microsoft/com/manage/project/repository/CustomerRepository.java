package microsoft.com.manage.project.repository;

import microsoft.com.manage.project.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}