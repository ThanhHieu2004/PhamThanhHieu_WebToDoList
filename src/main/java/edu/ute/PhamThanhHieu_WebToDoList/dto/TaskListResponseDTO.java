package edu.ute.PhamThanhHieu_WebToDoList.dto;

import java.util.List;

public class TaskListResponseDTO {
    private List<TaskResponseDTO> tasks;

    public List<TaskResponseDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDTO> tasks) {
        this.tasks = tasks;
    }
}

