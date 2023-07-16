package utku.basictask.services;

import org.springframework.stereotype.Service;
import utku.basictask.entity.Task;
import utku.basictask.repository.TaskRepository;


import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Task createOneTask(Task task) {
        task.setCreatedDate(new java.sql.Date(System.currentTimeMillis()+10800000));
        return taskRepository.save((task));
    }

    public Task updateOneTask(Task taskUpdate) {

        return taskRepository.save(taskUpdate);
    }

    public void deleteOneTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
