package dev.zheng.handlers.expensehandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Expense;
import dev.zheng.services.expenseservice.expenseexceptions.EmptyDescriptionException;
import dev.zheng.services.expenseservice.expenseexceptions.IllegalAmountException;
import dev.zheng.services.expenseservice.expenseexceptions.InvalidStatusException;
import dev.zheng.services.expenseservice.expenseexceptions.UnModifiableExpenseException;
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
        if(e.getId() != id){
            ctx.status(400);
            ctx.result("Make sure the expense ID in the path is the same as the ID in the request body");
            return;
        }
        try{
            Expense savedExpense = App.expenseService.modifyExpense(e);
            ctx.result(gson.toJson(savedExpense));
        }catch (EmptyDescriptionException err){
            ctx.status(400);
            ctx.result("Description cannot be empty");
        }catch (IllegalAmountException err){
            ctx.status(400);
            ctx.result("Invalid amount");
        }catch (InvalidStatusException err){
            ctx.status(400);
            ctx.result("Invalid status");
        }catch (UnModifiableExpenseException err){
            ctx.status(400);
            ctx.result("This expense is not modifiable");
        }

    }
}
