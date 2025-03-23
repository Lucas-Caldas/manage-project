package microsoft.com.manage.project.repository;

import microsoft.com.manage.project.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {
}