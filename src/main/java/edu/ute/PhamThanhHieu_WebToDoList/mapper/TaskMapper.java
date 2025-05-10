package edu.ute.PhamThanhHieu_WebToDoList.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskRequestDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.model.Task;
import edu.ute.PhamThanhHieu_WebToDoList.utils.TagMapperHelper;

@Mapper(componentModel = "spring", uses = {TagMapperHelper.class})
public interface TaskMapper{    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "mapTagsToNames")
    TaskResponseDTO toDto(Task task); 

    default List<TaskResponseDTO> toDtoList(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }
        return tasks.stream()
                    .map(this::toDto)  
                    .collect(Collectors.toList());
    }


    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "tags", ignore = true) // Map this later
    Task toEntity (TaskRequestDTO dto);
}
