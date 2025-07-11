package org.TasksManager.Service;

import org.TasksManager.Model.Task;
import org.TasksManager.Model.TaskStatus;
import org.TasksManager.Repository.Repository;

import java.time.LocalDate;
import java.util.*;

public class Service {

    private final Repository repo ;

    private final Scanner sc;

    public Service(Repository repo, Scanner sc) {
        this.repo = repo;
        this.sc = sc;
    }


    public void add(){
        cleanConsole();

        try{
            System.out.println("Введите название задачи");
            String name = sc.nextLine();
            System.out.println("Введите описание задачи");
            String description = sc.nextLine();
            System.out.println("Введите время на выполнение");
            int deadline = sc.nextInt();

            repo.addTask(new Task(name, description,  TaskStatus.TODO, LocalDate.now().plusDays(deadline)));

        }catch(InputMismatchException e){
            System.out.println("Вы ввели недопустимое колличество дней");
        }

        list();
    }

    public void list(){
        cleanConsole();

        System.out.println("Список текущих задач");
        for (int i = 0; i < repo.getListOfTasks().size(); i++) {
            System.out.printf("%d. " + "%s%n", i+1, repo.getListOfTasks().get(i));
            System.out.println(123);
        }
    }

    public void edit() {
        cleanConsole();

        System.out.println("Введите номер задачи:");

        try {
            int index = sc.nextInt();
            if (index <= 0 || index - 1 >= repo.getListOfTasks().size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            sc.nextLine();

            System.out.println("Что бы вы хотели изменить: name, description, status or deadline?");

            String editFlag = sc.nextLine();

            selectTaskUpdate(editFlag, index);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы ввели число не из списка, повторите действие");
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели недопустимое значение");
            sc.nextLine();
            return;
        }catch (IllegalArgumentException e) {
            System.out.println("Вы ввели недопустимое значение");
            return;
        }
        list();

    }

    public void delete(){
        cleanConsole();

        System.out.println("Введите номер задачи, которую хотите удалить");

        try{
            int index = sc.nextInt();
            if(index <= 0 || index-1 >= repo.getListOfTasks().size()){
                throw new ArrayIndexOutOfBoundsException();
            }
            repo.removeTask(index-1);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Вы ввели номер, которого нет в перечне, повторите действие");
            return;
        }catch(InputMismatchException e){
            System.out.println("Вы ввели недопустимое значение");
            sc.nextLine();
            return;
        }

        list();
    }

    public void filter(){
        cleanConsole();
        System.out.println("Введите статус задачи: todo, in_progress, done");
        String taskStatus = sc.nextLine();

            List<Task> listOfTasks = repo.getListOfTasks();
            try{
                listOfTasks.stream()
                        .filter(task -> task.getTaskStatus()
                                .equals(TaskStatus.valueOf(taskStatus.toUpperCase())))
                        .forEach(System.out::println);
            }catch(IllegalArgumentException e){
                System.out.println("Вы неправильно ввели статус");
            }
    }


    public void sort() {
        System.out.println("По какому критерию сортеровать: по status или по deadline");
        String compareFlag = sc.nextLine();
        List<Task> sortedList = repo.getListOfTasks().stream()
                .sorted(createCompare(compareFlag))
                .toList();
        sortedList.forEach(System.out::println);
    }

    //for terminal
    public void cleanConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Comparator<Task> createCompare(String compareFlag){
        if(compareFlag.equals("deadline")){
            return Comparator.comparing(Task::getTaskDeadline);

        }else if(compareFlag.equals("status")){
            List<TaskStatus> customOrder = List.of(
                    TaskStatus.TODO,
                    TaskStatus.IN_PROGRESS,
                    TaskStatus.DONE
            );
            return Comparator.comparing(task -> customOrder.indexOf(task.getTaskStatus()));

        }
        System.out.println("Вы ввели недоступное значение для сортировки, поэтому произошла сортировка по имени");
        return Comparator.comparing(Task::getTaskName);
    }

    private void selectTaskUpdate(String editFlag, int index){
        if (editFlag.equals("name")) {
            String newName = sc.nextLine();
            repo.getListOfTasks().get(index - 1).setTaskName(newName);
        } else if (editFlag.equals("description")) {
            String newDescription = sc.nextLine();
            repo.getListOfTasks().get(index - 1).setTaskDescription(newDescription);
        } else if (editFlag.equals("status")) {
            System.out.println("Введите новый статус задачи");
            String newStatus = sc.nextLine();
            repo.getListOfTasks().get(index - 1).setTaskStatus(TaskStatus.valueOf(newStatus));
        }else if (editFlag.equals("deadline")) {
            System.out.println("Введите новый deadline задачи");
            int newDeadline = sc.nextInt();
            repo.getListOfTasks().get(index - 1).setTaskDeadline(LocalDate.now().plusDays(newDeadline));
        }
    }
    private void uselessMethod(){
        System.out.println(111);
    }






}
