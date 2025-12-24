package com.example.demo.Service;

import com.example.demo.models.Task;
import com.example.demo.models.TaskComplete;
import com.example.demo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ===== ADMIN =====
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }

    // ===== USER =====
    public List<Task> getTasksForUser(Integer userId) {
        return taskRepository.findByAssignedToUserId(userId);
    }

    public Task completeTask(TaskComplete taskComplete) {
        Task task = taskRepository.findById(taskComplete.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskComplete.getTaskId()));

        task.setCompleted(true);
        Task savedTask = taskRepository.save(task);

        System.out.println("Admin notified: User " + taskComplete.getUsername() +
                " completed task " + taskComplete.getTaskId());

        return savedTask;
    }
}