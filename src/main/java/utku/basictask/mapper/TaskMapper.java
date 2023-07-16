package utku.basictask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import utku.basictask.dto.TaskDto;
import utku.basictask.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring",imports = {})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto entityToDto(Task task);

    @Mapping(target="id",ignore = true)
    @Mapping(target="createdDate",ignore = true)
    Task dtoToEntityToCreate(TaskDto taskDto);

    @Mapping(target="id",ignore = true)
    @Mapping(target="createdDate",ignore = true)
    Task dtoToEntityToUpdate(@MappingTarget Task task ,  TaskDto taskDto );

    List<TaskDto> entitiesToDtos(List<Task> task);

}
