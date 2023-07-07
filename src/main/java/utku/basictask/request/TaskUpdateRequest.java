package utku.basictask.request;

import lombok.Data;

@Data
public class TaskUpdateRequest {

    String name ;

    String description;

    String status;

    String assignedTo;

    Long point;

}
