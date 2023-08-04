package utku.basictask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AuthDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    int userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String accessToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String refreshToken;


}
