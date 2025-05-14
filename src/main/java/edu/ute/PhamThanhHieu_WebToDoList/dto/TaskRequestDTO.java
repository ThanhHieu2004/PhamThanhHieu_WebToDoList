package edu.ute.PhamThanhHieu_WebToDoList.dto;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import edu.ute.PhamThanhHieu_WebToDoList.utils.PRIORITY;

public class TaskRequestDTO {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 3, max = 100, message = "Tiêu đề phải có từ 3 đến 100 ký tự")
    private String title;
    
    @Size(max = 1000, message = "Mô tả không được quá 1000 ký tự")
    private String description;
    
    @NotNull(message = "Ngày đến hạn không được để trống")
    private LocalDate dueDate;
    
    private Boolean isCompleted = false;
    
    private String priority = PRIORITY.medium.name();
    
    private int categoryId;
    
    private Set<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

}
