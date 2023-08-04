package utku.basictask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import utku.basictask.dto.RefreshTokenDto;
import utku.basictask.entity.RefreshToken;
import utku.basictask.entity.User;

@Mapper(componentModel = "spring",imports = {})
public interface RefreshTokenMapper {
    @Mapping(target="id",ignore = true)
    @Mapping(target="user",source = "user")
    RefreshTokenDto entityToDto(RefreshToken refreshToken, User user);
}
