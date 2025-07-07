import org.TasksManager.Model.Task;
import org.TasksManager.Model.TaskStatus;
import org.TasksManager.Repository.Repository;
import org.TasksManager.Service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {

    private Repository repo;
    private List<Task> taskList;

    @BeforeEach
    void setUp(){
        repo = new Repository();

        taskList = new ArrayList<>(List.of(
                new Task("firstTask", "descriptionOfFirstTask", TaskStatus.DONE, LocalDate.now().plusDays(3)),
                new Task("secondTask", "descriptionOfSecondTask", TaskStatus.TODO, LocalDate.now().plusDays(4)),
                new Task("testTask", "descriptionOfTestTask", TaskStatus.TODO, LocalDate.now().plusDays(7))
        ));
    }

    @Test
    public void testAddTask(){

        Task task = new Task("testTask", "descriptionOfTestTask", TaskStatus.TODO, LocalDate.now().plusDays(7));
        List<Task> result = repo.getListOfTasks();
        repo.addTask(task);

        assertEquals(result, taskList);

    }

    @Test
    public void testEditTask_whenChangeName(){
        String simulationInput = "2\nname\nnewName";
        Scanner testInput = new Scanner(simulationInput);
        Service service = new Service(repo, testInput);

        service.edit();

        Task result = new Task("newName", "descriptionOfSecondTask", TaskStatus.TODO, LocalDate.now().plusDays(4));

        assertEquals(result, repo.getListOfTasks().get(1));

    }
    @Test
    public void testEditTask_whenChangeDescription(){
        String simulationInput = "2\ndescription\nnewDescription";
        Scanner testInput = new Scanner(simulationInput);
        Service service = new Service(repo, testInput);

        service.edit();

        Task result = new Task("secondTask", "newDescription", TaskStatus.TODO, LocalDate.now().plusDays(4));

        assertEquals(result, repo.getListOfTasks().get(1));

    }

    @Test
    public void testEditTask_whenChangeStatus(){
        String simulationInput = "2\nstatus\nDONE";
        Scanner testInput = new Scanner(simulationInput);
        Service service = new Service(repo, testInput);

        service.edit();

        Task result = new Task("secondTask", "descriptionOfSecondTask", TaskStatus.DONE, LocalDate.now().plusDays(4));

        assertEquals(result, repo.getListOfTasks().get(1));

    }
    @Test
    public void testDeleteTask(){
        String simulationInput = "1\n";
        Scanner testInput = new Scanner(simulationInput);
        Service service = new Service(repo, testInput);

        service.delete();
        List<Task> result = List.of(
                new Task("secondTask", "descriptionOfSecondTask", TaskStatus.TODO, LocalDate.now().plusDays(4))
        );

        assertEquals(result, repo.getListOfTasks());
    }
}
