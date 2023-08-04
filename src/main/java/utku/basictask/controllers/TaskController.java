package utku.basictask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utku.basictask.dto.TaskDto;
import utku.basictask.entity.Task;
import utku.basictask.entity.User;
import utku.basictask.exceptions.*;
import utku.basictask.mapper.TaskMapper;
import utku.basictask.services.TaskService;
import utku.basictask.services.UserService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskMapper taskMapper;
    private TaskService taskService;

    private UserService userService;

    public TaskController(TaskService taskService, TaskMapper taskMapper,UserService userService) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return new ResponseEntity<>(taskMapper.entitiesToDtos(taskService.getAllTasks()),HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getOneTaskById(@PathVariable Long taskId){
        Task task = taskService.getTaskById(taskId);
        if(task == null){
            throw new TaskNotFoundException();
        }
        return new ResponseEntity<>(taskMapper.entityToDto(task),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskRequest){
        if(!isTaskValid(taskRequest)){
            throw new TaskIsNotValidException();
        }
        User user = userService.findById(taskRequest.getUserId());
        if(user == null){
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(taskService.createOneTask(taskMapper.dtoToEntityToCreate(taskRequest,user)),HttpStatus.CREATED);
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId , @RequestBody TaskDto taskUpdate){
        Task task = taskService.getTaskById(taskId);

        if(!isTaskUpdateValid(taskUpdate)){
            throw new TaskNotUpdatedException();
        }
        else if (task == null){
            throw new TaskNotFoundException();
        }
        User user = userService.findById(taskUpdate.getUserId());
        if(user == null){
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(taskService.updateOneTask(taskMapper.dtoToEntityToUpdate(task,taskUpdate,user)),HttpStatus.OK);
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

    public boolean isTaskValid(TaskDto taskRequest){
        if(taskRequest.getName() == "" || taskRequest.getDescription() == ""|| taskRequest.getStatus() == "" ||
           taskRequest.getPoint() ==null || taskRequest.getAssignedTo()==""){
            return false;
        }
        return true;
    }
    public boolean isTaskUpdateValid(TaskDto taskRequest){
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
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handleUserNotFoundException() {

    }
}
