package org.TasksManager.Repository;

import org.TasksManager.Model.Task;
import org.TasksManager.Model.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Repository {


    private final List<Task> taskList = new ArrayList<>(List.of(
            new Task("firstTask", "descriptionOfFirstTask", TaskStatus.DONE, LocalDate.now().plusDays(3)),
            new Task("secondTask", "descriptionOfSecondTask", TaskStatus.TODO, LocalDate.now().plusDays(4))
    ));




    public List<Task> getListOfTasks() {
        return taskList;
    }
     public void addTask(Task task){
        taskList.add(task);
     }
     public void removeTask(int index){
        taskList.remove(index);
     }



}
