package dev.zheng.app;
import dev.zheng.daos.employeedao.EmployeeDAOPostgres;
import dev.zheng.daos.expensedao.ExpenseDAOPostgres;
import dev.zheng.handlers.EmployeeHandler;
import dev.zheng.handlers.ExpenseHandler;
import dev.zheng.services.employeeservice.EmployeeService;
import dev.zheng.services.employeeservice.EmployeeServiceImpl;
import dev.zheng.services.expenseservice.ExpenseService;
import dev.zheng.services.expenseservice.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create();
        EmployeeService employeeServ = new EmployeeServiceImpl(new EmployeeDAOPostgres());
        EmployeeHandler employeeHandler = new EmployeeHandler(employeeServ);
        ExpenseService expenseServ = new ExpenseServiceImpl(new ExpenseDAOPostgres());
        ExpenseHandler expenseHandler = new ExpenseHandler(expenseServ);


        //Employees Routes
        app.post("/employees", employeeHandler.createEmployee);
        app.get("/employees/{id}", employeeHandler.getEmployeeById);
        app.get("/employees", employeeHandler.getAllEmployees);
        app.put("/employees/{id}", employeeHandler.updateEmployee);
        app.delete("/employees/{id}", employeeHandler.deleteEmployee);

        //Expenses Routes
        app.post("/expenses", expenseHandler.createExpense);
        app.get("/expenses/{id}", expenseHandler.getExpenseById);
        app.get("/expenses", expenseHandler.getAllExpense);
        app.put("/expenses/{id}", expenseHandler.updateExpenseById);
        app.delete("/expenses/{id}", expenseHandler.deleteExpense);
        app.patch("/expenses/{id}/{status}", expenseHandler.patchExpenseById);
        app.get("/employees/{id}/expenses", expenseHandler.getAllExpenseByEmployeeId);
        app.post("/employees/{id}/expenses", expenseHandler.createExpenseForEmployeeId);

        app.start();
    }
}
