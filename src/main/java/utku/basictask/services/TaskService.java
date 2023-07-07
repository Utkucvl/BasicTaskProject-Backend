package utku.basictask.services;

import org.springframework.stereotype.Service;
import utku.basictask.entity.Task;
import utku.basictask.repository.TaskRepository;
import utku.basictask.request.TaskCreateRequest;
import utku.basictask.request.TaskUpdateRequest;

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

    public Task createOneTask(TaskCreateRequest taskRequest) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis+10800000);
        Task toSave = new Task();
        toSave.setCreatedDate(date);
        toSave.setName(taskRequest.getName());
        toSave.setDescription(taskRequest.getDescription());
        toSave.setPoint(taskRequest.getPoint());
        toSave.setAssignedTo(taskRequest.getAssignedTo());
        toSave.setStatus(taskRequest.getStatus());

        return taskRepository.save(toSave);
    }

    public Task updateOneTask(TaskUpdateRequest taskUpdate, Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if(task != null){
            task.setStatus(taskUpdate.getStatus());
            task.setName(taskUpdate.getName());
            task.setPoint(taskUpdate.getPoint());
            task.setAssignedTo(taskUpdate.getAssignedTo());
            task.setDescription(taskUpdate.getDescription());
        }
        return taskRepository.save((task));
    }

    public void deleteOneTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
