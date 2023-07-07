package utku.basictask.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import utku.basictask.entity.Task;

import java.util.Date;

@Data
public class TaskResponse {

    Long id;

    String name;

    String description;

    String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdDate;

    String assignedTo;

    Long point;

    public TaskResponse(Task task){
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.createdDate =task.getCreatedDate();
        this.assignedTo = task.getAssignedTo();
        this.point = task.getPoint();
    }
}
