package microsoft.com.manage.project.service;

import microsoft.com.manage.project.dto.TaskDTO;
import microsoft.com.manage.project.exception.ResourceNotFoundException;
import microsoft.com.manage.project.mapper.TaskMapper;
import microsoft.com.manage.project.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository TaskRepository;

    private final TaskMapper taskMapper;

    public TaskService(TaskRepository TaskRepository, TaskMapper TaskMapper) {
        this.TaskRepository = TaskRepository;
        this.taskMapper = TaskMapper;
    }

    public List<TaskDTO> listAll() {
        return taskMapper.toDTOList(TaskRepository.findAll());
    }

    public TaskDTO fetchById(Long id) {
        return taskMapper.toDTO(TaskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found")));
    }

    public TaskDTO save(TaskDTO Task) {
        return taskMapper.toDTO(TaskRepository.save(taskMapper.toEntity(Task)));
    }

    public void delete(Long id) {
        if (!TaskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with ID: " + id);
        }

        TaskRepository.deleteById(id);
    }
}
