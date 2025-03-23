package microsoft.com.manage.project.service;

import lombok.RequiredArgsConstructor;
import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.enums.StatusProject;
import microsoft.com.manage.project.mapper.ProjectMapper;
import microsoft.com.manage.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> listOpenProjects() {
        return projectRepository.findByStatus(StatusProject.OPEN)
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }
}
