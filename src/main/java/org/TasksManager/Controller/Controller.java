package org.TasksManager.Controller;

import org.TasksManager.Service.Service;

import java.util.Scanner;

public class Controller {


    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }


    private void handleCommand(String command) {
        if(command.equals("add"))
                service.add();
        else if(command.equals("list"))
                service.list();
        else if(command.equals("edit"))
                service.edit();
        else if(command.equals("delete"))
                service.delete();
        else if(command.equals("filter"))
                service.filter();
        else if(command.equals("sort"))
                service.sort();

    }



    public void start(){

        Scanner sc = new Scanner(System.in);
        service.cleanConsole();
        System.out.println("Программа заработала успешно!");
        while(true){
            String command = sc.nextLine();

            if(command.equals("stop"))
                break;

            handleCommand(command);
        }

    }

}
