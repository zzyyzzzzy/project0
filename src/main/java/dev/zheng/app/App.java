package dev.zheng.app;

import dev.zheng.daos.EmployeeDAOLocal;
import dev.zheng.handlers.*;
import dev.zheng.services.EmployeeService;
import dev.zheng.services.EmployeeServiceImpl;
import io.javalin.Javalin;

public class App {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        //Employee handlers
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();

        //Employees Routes
        app.post("/employees", createEmployeeHandler);
        app.get("/employees/{id}", getEmployeeByIdHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.put("/employees/{id}", updateEmployeeHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);


        app.start();
    }
}
