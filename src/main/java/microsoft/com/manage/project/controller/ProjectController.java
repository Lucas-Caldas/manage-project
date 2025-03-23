package microsoft.com.manage.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Projetos", description = "Gerenciamento de Projetos")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/opens")
    @Operation(summary = "List all open projects", description = "Returns a list of registered open projects")
    public ResponseEntity<List<ProjectDTO>> listOpenProjects() {
        return ResponseEntity.ok(projectService.listOpenProjects());
    }
}
