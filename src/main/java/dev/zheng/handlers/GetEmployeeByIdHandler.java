package dev.zheng.handlers;


import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee e = App.employeeService.retrieveEmployeeById(id);
        Gson gson = new Gson();
        ctx.result(gson.toJson(e));
    }
}
