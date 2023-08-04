package utku.basictask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import utku.basictask.entity.User;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long userId;

    User user;

    Long point;
}
