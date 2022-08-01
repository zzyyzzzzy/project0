package dev.zheng.handlers;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler{
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(App.employeeService.retrieveEmployeeById(id) == null){
            ctx.status(404);
            ctx.result("Could not find the employee to be updated");
            return;
        }
        Gson gson = new Gson();
        String json = ctx.body();
        Employee e = gson.fromJson(json, Employee.class);
        e.setId(id);
        Employee employeeSaved = App.employeeService.modifyEmployee(e);
        ctx.result(gson.toJson(employeeSaved));

    }
}
