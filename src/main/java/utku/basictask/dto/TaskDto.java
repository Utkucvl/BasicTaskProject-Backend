package utku.basictask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    Long id;

    String name;

    String description;

    String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdDate;

    String assignedTo;

    Long point;
}
