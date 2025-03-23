package microsoft.com.manage.project.repository;

import microsoft.com.manage.project.entity.ProjectEntity;
import microsoft.com.manage.project.enums.StatusProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findByStatus(StatusProject status);
}