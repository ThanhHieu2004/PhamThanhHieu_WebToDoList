package edu.ute.PhamThanhHieu_WebToDoList.dto;

import java.time.LocalDateTime;
import java.util.Set;

import edu.ute.PhamThanhHieu_WebToDoList.utils.PRIORITY;

public class TaskRequestDTO {

    private String title;

    private String description;
    private LocalDateTime dueDate;
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
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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
