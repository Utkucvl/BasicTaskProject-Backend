package utku.basictask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utku.basictask.entity.Task;
import utku.basictask.exceptions.TaskIsNotValidException;
import utku.basictask.exceptions.TaskNotDeletedException;
import utku.basictask.exceptions.TaskNotFoundException;
import utku.basictask.exceptions.TaskNotUpdatedException;
import utku.basictask.request.TaskCreateRequest;
import utku.basictask.request.TaskUpdateRequest;
import utku.basictask.response.TaskResponse;
import utku.basictask.services.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(){
        List<Task> tasks;

        tasks = taskService.getAllTasks();

        return tasks.stream().map(task -> new TaskResponse(task)).collect(Collectors.toList());
    }
    @GetMapping("/{taskId}")
    public TaskResponse getOneTaskById(@PathVariable Long taskId){
        Task task = taskService.getTaskById(taskId);
        if(task == null){
            throw new TaskNotFoundException();
        }
        return new TaskResponse(task);
    }
    @PostMapping
    public Task createTask(@RequestBody TaskCreateRequest taskRequest){
        if(!isTaskValid(taskRequest)){
            throw new TaskIsNotValidException();
        }
        return taskService.createOneTask(taskRequest);
    }
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId , @RequestBody TaskUpdateRequest taskUpdate){
        Task task = taskService.getTaskById(taskId);

        if(!isTaskUpdateValid(taskUpdate)){
            throw new TaskNotUpdatedException();
        }
        else if (task == null){
            throw new TaskNotFoundException();
        }
        return taskService.updateOneTask(taskUpdate,taskId);
    }
    @DeleteMapping("/{taskId}")
    public void deleteOneTaskById(@PathVariable Long taskId){
        Task task = taskService.getTaskById(taskId);
        if(task == null){
            throw new TaskNotDeletedException();
        }

        else {
            taskService.deleteOneTaskById(taskId);
        }
    }

    public boolean isTaskValid(TaskCreateRequest taskRequest){
        if(taskRequest.getName() == "" || taskRequest.getDescription() == ""|| taskRequest.getStatus() == "" ||
           taskRequest.getPoint() ==null || taskRequest.getAssignedTo()==""){
            return false;
        }
        return true;
    }
    public boolean isTaskUpdateValid(TaskUpdateRequest taskRequest){
        if(taskRequest.getName() == "" || taskRequest.getDescription() == ""|| taskRequest.getStatus() == "" ||
                taskRequest.getPoint() ==null || taskRequest.getAssignedTo()==""){
            return false;
        }
        return true;
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleTaskNotFoundException() {

    }

    @ExceptionHandler(TaskIsNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleTaskIsNotValidException() {

    }

    @ExceptionHandler(TaskNotDeletedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleTaskNotDeletedException() {

    }

    @ExceptionHandler(TaskNotUpdatedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleTaskNotUpdatedException() {

    }
}
