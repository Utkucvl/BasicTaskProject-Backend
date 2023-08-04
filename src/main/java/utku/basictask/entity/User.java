package utku.basictask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="userr")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;

    String userName;
    String password;
}
