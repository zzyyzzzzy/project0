package dev.zheng.handlers;

import com.google.gson.Gson;
import dev.zheng.entities.Expense;
import dev.zheng.services.employeeservice.employeeexceptions.InvalidEmployeeIdException;
import dev.zheng.services.expenseservice.ExpenseService;
import dev.zheng.services.expenseservice.expenseexceptions.*;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class ExpenseHandler {
    private Gson gson = new Gson();
    private ExpenseService expenseService;

    public ExpenseHandler(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    public Handler createExpense = ctx -> {
        String json = ctx.body();
        Expense expense = gson.fromJson(json, Expense.class);
        try{
            Expense addedExpense = expenseService.addExpense(expense);
            String expenseJSON = gson.toJson(addedExpense);
            ctx.status(201);
            ctx.result(expenseJSON);
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
    };

    public Handler createExpenseForEmployeeId = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = gson.fromJson(ctx.body(), Expense.class);
        if(expense.getEmployeeId() != id){
            ctx.status(400);
            ctx.result("Make sure the employee ID in the path is the same as the ID in the request body");
            return;
        }
        try{
            Expense addedExpense = expenseService.addExpense(expense);
            if (addedExpense == null){
                ctx.status(400);
                ctx.result("Cannot add expense due to the employee does not exist");
                return;
            }
            String expenseJSON = gson.toJson(addedExpense);
            ctx.status(201);
            ctx.result(expenseJSON);
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
    };

    public Handler getExpenseById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense e = expenseService.retrieveExpenseById(id);
        if(e == null){
            ctx.result("Expense with id: " + id + " not found");
            ctx.status(404);
            return;
        }
        ctx.result(gson.toJson(e));
    };

    public Handler getAllExpense = ctx -> {
        String status = ctx.queryParam("status");
        if (status == null){
            ctx.result(gson.toJson(expenseService.retrieveAllExpenses()));
            return;
        }
        try{
            ctx.result(gson.toJson(expenseService.retrieveAllExpensesByStatus(status)));
        } catch (InvalidStatusException e){
            ctx.status(400);
            ctx.result("Invalid status");
        }
    };

    public Handler getAllExpenseByEmployeeId = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Expense> expenses = expenseService.retrieveAllExpensesByEmployeeId(id);
        if (expenses == null){
            ctx.result(gson.toJson(new ArrayList<Expense>()));
            return;
        }
        ctx.result(gson.toJson(expenses));
    };

    public Handler patchExpenseById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String status = ctx.pathParam("status");
        Gson gson = new Gson();
        try{
            Expense updatedExpense = expenseService.changeExpenseStatus(id, status);
            ctx.result(gson.toJson(updatedExpense));
        } catch (InvalidExpenseIdException e){
            ctx.result("Expense id not found");
            e.printStackTrace();
        } catch(InvalidStatusException e){
            ctx.result("Cannot to change to a invalid status");
            e.printStackTrace();
        }catch(UnModifiableExpenseException e) {
            ctx.result("Cannot modify denied/approved expense");
            e.printStackTrace();
        }
    };

    public Handler updateExpenseById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense e = gson.fromJson(ctx.body(), Expense.class);
        if(e.getId() != id){
            ctx.status(400);
            ctx.result("Make sure the expense ID in the path is the same as the ID in the request body");
            return;
        }
        if(expenseService.retrieveExpenseById(id) == null){
            ctx.status(404);
            ctx.result("Could not find the expense to be updated");
            return;
        }

        try{
            Expense savedExpense = expenseService.modifyExpense(e);
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
    };

    public Handler deleteExpense = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try{
            if(!expenseService.removeExpense(id)){
                ctx.status(404);
                ctx.result("Expense with id: " + id + " not found");
            } else{
                ctx.result("Expense with id: " + id + " is deleted");
            }
        } catch (UnModifiableExpenseException e){
            ctx.status(422);
            ctx.result("This expense cannot be deleted");
        }
    };

}
