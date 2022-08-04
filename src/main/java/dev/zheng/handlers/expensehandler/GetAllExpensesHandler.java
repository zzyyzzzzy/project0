package dev.zheng.handlers.expensehandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.services.expenseservice.expenseexceptions.InvalidStatusException;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetAllExpensesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam("status");
        Gson gson = new Gson();
        if (status == null){
            ctx.result(gson.toJson(App.expenseService.retrieveAllExpenses()));
            return;
        }
        try{
            ctx.result(gson.toJson(App.expenseService.retrieveAllExpensesByStatus(status)));
        } catch (InvalidStatusException e){
            ctx.status(400);
            ctx.result("Invalid status");
        }

    }
}
