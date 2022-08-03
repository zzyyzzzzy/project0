package dev.zheng.app;

import dev.zheng.daos.employeedao.EmployeeDAOLocal;
import dev.zheng.daos.expensedao.ExpenseDAOLocal;
import dev.zheng.handlers.employeeHandler.*;
import dev.zheng.handlers.expenseHandler.CreateExpenseHandler;
import dev.zheng.handlers.expenseHandler.GetAllExpensesHandler;
import dev.zheng.handlers.expenseHandler.GetExpenseByIdHandler;
import dev.zheng.handlers.expenseHandler.UpdateExpenseHandler;
import dev.zheng.services.employeeservice.EmployeeService;
import dev.zheng.services.employeeservice.EmployeeServiceImpl;
import dev.zheng.services.expenseservice.ExpenseService;
import dev.zheng.services.expenseservice.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOLocal());
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

        app.start();
    }
}
