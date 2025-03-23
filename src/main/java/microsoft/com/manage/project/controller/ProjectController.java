package microsoft.com.manage.project.controller;

import lombok.RequiredArgsConstructor;
import microsoft.com.manage.project.dto.ProjectDTO;
import microsoft.com.manage.project.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/opens")
    public ResponseEntity<List<ProjectDTO>> listOpenProjects() {
        return ResponseEntity.ok(projectService.listOpenProjects());
    }
}
