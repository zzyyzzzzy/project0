package dev.zheng.handlers.expensehandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseByIdHandler implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense e = App.expenseService.retrieveExpenseById(id);
        if(e == null){
            ctx.result("Expense with id: " + id + " not found");
            ctx.status(404);
            return;
        }
        Gson gson = new Gson();
        ctx.result(gson.toJson(e));
    }
}
