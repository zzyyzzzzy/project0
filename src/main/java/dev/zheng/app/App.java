package dev.zheng.app;

import dev.zheng.daos.EmployeeDAOLocal;
import dev.zheng.handlers.CreateEmployeeHandler;
import dev.zheng.handlers.GetEmployeeByIdHandler;
import dev.zheng.services.EmployeeService;
import dev.zheng.services.EmployeeServiceImpl;
import io.javalin.Javalin;

public class App {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();

        // employees routes
        app.post("/employees", createEmployeeHandler);
        app.get("/employees/{id}", getEmployeeByIdHandler);
        app.start();
    }
}
