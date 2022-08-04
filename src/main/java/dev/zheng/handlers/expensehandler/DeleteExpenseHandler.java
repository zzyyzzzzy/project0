package dev.zheng.handlers.expensehandler;

import dev.zheng.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(!App.expenseService.removeExpense(id)){
            ctx.status(404);
            ctx.result("Expense with id: " + id + " not found");
        } else{
            ctx.result("Expense with id: " + id + " is deleted");
        }
    }
}
