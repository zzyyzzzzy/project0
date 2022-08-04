package dev.zheng.handlers.expensehandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetExpensesByEmployeeIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Expense> expenses = App.expenseService.retrieveAllExpensesByEmployeeId(id);
        ctx.result(gson.toJson(expenses));
    }
}
