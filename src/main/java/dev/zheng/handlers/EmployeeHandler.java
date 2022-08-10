package dev.zheng.handlers;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Employee;
import dev.zheng.services.employeeservice.EmployeeService;
import dev.zheng.services.employeeservice.employeeexceptions.NullNameException;
import io.javalin.http.Handler;

public class EmployeeHandler {
    private Gson gson = new Gson();
    private EmployeeService employeeService;

    public EmployeeHandler(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public Handler createEmployee = ctx -> {
        String json = ctx.body();
        Employee employee = gson.fromJson(json, Employee.class);
        try{
            Employee hiredEmployee = employeeService.hireEmployee(employee);
            String employeeJSON = gson.toJson(hiredEmployee);
            ctx.status(201);
            ctx.result(employeeJSON);
        } catch (NullNameException e){
            ctx.status(400);
            ctx.result("First/last name should not be empty");
        }
    };

    public Handler getAllEmployees = ctx -> {
        ctx.result(gson.toJson(employeeService.retrieveAllEmployees()));
    };

    public Handler getEmployeeById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee e = employeeService.retrieveEmployeeById(id);
        if(e == null){
            ctx.result("Employee with id: " + id + " not found");
            ctx.status(404);
            return;
        }
        ctx.result(gson.toJson(e));
    };

    public Handler updateEmployee = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String json = ctx.body();
        Employee e = gson.fromJson(json, Employee.class);
        if(e.getId() != id){
            ctx.status(400);
            ctx.result("Make sure the employee ID in the path is the same as the ID in the request body");
            return;
        }
        if(employeeService.retrieveEmployeeById(id) == null){
            ctx.status(404);
            ctx.result("Could not find the employee to be updated");
            return;
        }
        try{
            Employee employeeSaved = employeeService.modifyEmployee(e);
            ctx.result(gson.toJson(employeeSaved));
        }catch (NullNameException err){
            ctx.status(400);
            ctx.result("First/last name should not be empty");
        }
    };

    public Handler deleteEmployee = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (employeeService.unHireEmployee(id)){
            ctx.result("Employee with id: " + id + " is deleted");
        } else {
            ctx.status(404);
            ctx.result("Employee with id: " + id + " not found");
        }
    };
}
