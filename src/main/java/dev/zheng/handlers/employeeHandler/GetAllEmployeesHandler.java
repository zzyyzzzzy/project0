package dev.zheng.handlers.employeeHandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllEmployeesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        ctx.result(gson.toJson(App.employeeService.retrieveAllEmployees()));
    }
}
