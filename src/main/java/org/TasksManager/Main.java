package org.TasksManager;


import org.TasksManager.Controller.Controller;
import org.TasksManager.Repository.Repository;
import org.TasksManager.Service.Service;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Repository repository = new Repository();
        Service service = new Service(repository, sc);
        Controller controller = new Controller(service);

        controller.start();
    }
}