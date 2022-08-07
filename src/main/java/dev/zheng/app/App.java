package dev.zheng.app;

import dev.zheng.daos.employeedao.EmployeeDAOLocal;
import dev.zheng.daos.employeedao.EmployeeDAOPostgres;
import dev.zheng.daos.expensedao.ExpenseDAOLocal;
import dev.zheng.daos.expensedao.ExpenseDAOPostgres;
import dev.zheng.handlers.employeehandler.*;
import dev.zheng.handlers.expensehandler.*;
import dev.zheng.services.employeeservice.EmployeeService;
import dev.zheng.services.employeeservice.EmployeeServiceImpl;
import dev.zheng.services.expenseservice.ExpenseService;
import dev.zheng.services.expenseservice.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgres());
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        //Employee handlers
        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        GetEmployeeByIdHandler getEmployeeByIdHandler = new GetEmployeeByIdHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();

        //Expense handlers
        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        GetExpenseByIdHandler getExpenseByIdHandler = new GetExpenseByIdHandler();
        GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        PatchExpenseByIdHandler patchExpenseByIdHandler = new PatchExpenseByIdHandler();
        GetExpensesByEmployeeIdHandler getExpensesByEmployeeIdHandler = new GetExpensesByEmployeeIdHandler();
        CreateExpenseForEmployeeId createExpenseForEmployeeId = new CreateExpenseForEmployeeId();

        //Employees Routes
        app.post("/employees", createEmployeeHandler);
        app.get("/employees/{id}", getEmployeeByIdHandler);
        app.get("/employees", getAllEmployeesHandler);
        app.put("/employees/{id}", updateEmployeeHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);

        //Expenses Routes
        app.post("/expenses", createExpenseHandler);
        app.get("/expenses/{id}", getExpenseByIdHandler);
        app.get("/expenses/", getAllExpensesHandler);
        app.put("/expenses/{id}", updateExpenseHandler);
        app.delete("/expenses/{id}", deleteExpenseHandler);
        app.patch("/expenses/{id}/{status}", patchExpenseByIdHandler);
        app.get("/employees/{id}/expenses", getExpensesByEmployeeIdHandler);
        app.post("/employees/{id}/expenses", createExpenseForEmployeeId);

        app.start();
    }
}
