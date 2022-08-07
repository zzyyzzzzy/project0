package dev.zheng.handlers.expensehandler;

import dev.zheng.app.App;
import dev.zheng.services.expenseservice.expenseexceptions.UnModifiableExpenseException;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try{
            if(!App.expenseService.removeExpense(id)){
                ctx.status(404);
                ctx.result("Expense with id: " + id + " not found");
            } else{
                ctx.result("Expense with id: " + id + " is deleted");
            }
        } catch (UnModifiableExpenseException e){
            ctx.status(422);
            ctx.result("This expense cannot be deleted");
        }
    }
}
