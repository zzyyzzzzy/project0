package dev.zheng.handlers.expensehandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Expense;
import dev.zheng.services.employeeservice.employeeexceptions.InvalidEmployeeIdException;
import dev.zheng.services.expenseservice.expenseexceptions.EmptyDescriptionException;
import dev.zheng.services.expenseservice.expenseexceptions.IllegalAmountException;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseForEmployeeId implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = gson.fromJson(ctx.body(), Expense.class);
        if(expense.getEmployeeId() != id){
            ctx.status(400);
            ctx.result("Make sure the employee ID in the path is the same as the ID in the request body");
            return;
        }
        try{
            Expense addedExpense = App.expenseService.addExpense(expense);
            String expenseJSON = gson.toJson(addedExpense);
            ctx.status(201);
            ctx.result(expenseJSON);
            System.out.println(addedExpense);
        } catch (EmptyDescriptionException e){
            ctx.status(400);
            ctx.result("Description cannot be empty");
        } catch (IllegalAmountException e){
            ctx.status(400);
            ctx.result("Invalid amount entered");
        } catch (InvalidEmployeeIdException e){
            ctx.status(400);
            ctx.result("Invalid employee ID entered");
        }
    }
}
