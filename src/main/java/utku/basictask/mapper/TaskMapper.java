package utku.basictask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import utku.basictask.dto.TaskDto;
import utku.basictask.entity.Task;
import utku.basictask.entity.User;

import java.util.List;

@Mapper(componentModel = "spring",imports = {})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    @Mapping(target="userId",ignore = true)
    TaskDto entityToDto(Task task);

    @Mapping(target="id",ignore = true)
    @Mapping(target="user",source = "user")
    Task dtoToEntityToCreate(TaskDto taskDto, User user);

    @Mapping(target="id",ignore = true)
    @Mapping(target="user",source = "user")
    @Mapping(target="createdDate" , ignore = true)
    Task dtoToEntityToUpdate(@MappingTarget Task task ,  TaskDto taskDto ,User user);
    @Mapping(target="userId",ignore = true)
    List<TaskDto> entitiesToDtos(List<Task> task);

}
