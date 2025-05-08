package edu.ute.PhamThanhHieu_WebToDoList.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskRequestDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.model.Task;
import edu.ute.PhamThanhHieu_WebToDoList.utils.TagMapperHelper;

@Mapper(componentModel = "spring", uses = {TagMapperHelper.class})
public interface TaskMapper{
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "mapTagsToNames")
    TaskResponseDTO toDto (Task task);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "tags", ignore = true) // Map this later
    Task toEntity (TaskRequestDTO dto);
}
