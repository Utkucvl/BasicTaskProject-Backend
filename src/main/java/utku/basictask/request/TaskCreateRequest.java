package utku.basictask.request;

import lombok.Data;
@Data
public class TaskCreateRequest {

    String name ;

    String description;

    String status;

    String assignedTo;

    Long point;

}
