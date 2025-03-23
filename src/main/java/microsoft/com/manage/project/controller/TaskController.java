package microsoft.com.manage.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import microsoft.com.manage.project.dto.TaskDTO;
import microsoft.com.manage.project.exception.ResourceNotFoundException;
import microsoft.com.manage.project.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Taska management")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "List all tasks", description = "Returns a list of tasks")
    public ResponseEntity<List<TaskDTO>> listAlltasks() {
        return ResponseEntity.ok(taskService.listAll());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> save(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO savedtask = taskService.save(taskDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedtask.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedtask);

    }

    @GetMapping("/{id}")
    public TaskDTO fetchById(@PathVariable Long id) {
        return taskService.fetchById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
