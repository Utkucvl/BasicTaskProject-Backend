package utku.basictask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    String description;

    String status;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    String assignedTo;

    Long point;
}
