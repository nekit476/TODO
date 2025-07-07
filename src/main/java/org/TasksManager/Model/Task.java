package org.TasksManager.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private LocalDate taskDeadline;

    public Task(String taskName, String taskDescription, TaskStatus taskStatus, LocalDate taskDeadline) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskDeadline = taskDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName) && Objects.equals(taskDescription, task.taskDescription) && taskStatus == task.taskStatus && Objects.equals(taskDeadline, task.taskDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskDescription, taskStatus, taskDeadline);
    }

    @Override
    public String toString() {
        return  "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskDeadline= " + taskDeadline.getDayOfMonth() + " " + taskDeadline.getMonth();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getTaskDeadline() { return taskDeadline; }

    public void setTaskDeadline(LocalDate taskDeadline) { this.taskDeadline = taskDeadline; }
}
