package dev.zheng.handlers.expenseHandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(App.expenseService.retrieveExpenseById(id) == null){
            ctx.status(404);
            ctx.result("Could not find the expense to be updated");
            return;
        }
        Expense e = gson.fromJson(ctx.body(), Expense.class);
        e.setId(id);
        Expense savedExpense = App.expenseService.modifyExpense(e);
        ctx.result(gson.toJson(savedExpense));

    }
}
