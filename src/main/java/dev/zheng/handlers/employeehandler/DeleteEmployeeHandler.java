package dev.zheng.handlers.employeehandler;

import dev.zheng.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (App.employeeService.unHireEmployee(id)){
            ctx.result("Employee with id: " + id + " is deleted");
        } else {
            ctx.status(404);
            ctx.result("Employee with id: " + id + " not found");
        }
    }
}
