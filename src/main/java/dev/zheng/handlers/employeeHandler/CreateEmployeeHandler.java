package dev.zheng.handlers.employeeHandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee hiredEmployee = App.employeeService.hireEmployee(employee);
        String employeeJSON = gson.toJson(hiredEmployee);
        ctx.status(201);
        ctx.result(employeeJSON);
    }
}