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

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(json, Expense.class);
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
