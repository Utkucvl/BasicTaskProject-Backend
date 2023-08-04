package utku.basictask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import utku.basictask.entity.User;

import java.util.Date;

@Data
public class RefreshTokenDto {



    int userId;

    String refreshToken ;


}
