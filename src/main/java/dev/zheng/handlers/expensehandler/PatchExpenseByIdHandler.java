package dev.zheng.handlers.expensehandler;

import com.google.gson.Gson;
import dev.zheng.app.App;
import dev.zheng.entities.Expense;
import dev.zheng.services.employeeservice.employeeexceptions.InvalidEmployeeIdException;
import dev.zheng.services.expenseservice.expenseexceptions.InvalidExpenseIdException;
import dev.zheng.services.expenseservice.expenseexceptions.InvalidStatusException;
import dev.zheng.services.expenseservice.expenseexceptions.UnModifiableExpenseException;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class PatchExpenseByIdHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String status = ctx.pathParam("status");
        Gson gson = new Gson();
        try{
            Expense updatedExpense = App.expenseService.changeExpenseStatus(id, status);
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
    }
}
