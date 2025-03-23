package microsoft.com.manage.project.repository;

import microsoft.com.manage.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, Long> {
}