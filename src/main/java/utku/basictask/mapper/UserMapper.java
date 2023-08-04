package utku.basictask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import utku.basictask.dto.TaskDto;
import utku.basictask.dto.UserDto;
import utku.basictask.entity.Task;
import utku.basictask.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(User user);
    @Mapping(target="id",ignore = true)
    User dtoToEntity(UserDto userDto);

    List<UserDto> entitiesToDtos(List<User> user);

}
