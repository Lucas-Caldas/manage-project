package microsoft.com.manage.project.repository;

import microsoft.com.manage.project.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}