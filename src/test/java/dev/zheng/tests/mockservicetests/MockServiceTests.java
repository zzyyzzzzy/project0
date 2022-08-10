package dev.zheng.tests.mockservicetests;

import dev.zheng.daos.employeedao.EmployeeDAO;
import dev.zheng.daos.expensedao.ExpenseDAO;
import dev.zheng.daos.expensedao.ExpenseDAOPostgres;
import dev.zheng.entities.Employee;
import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;
import dev.zheng.services.employeeservice.EmployeeService;
import dev.zheng.services.employeeservice.EmployeeServiceImpl;
import dev.zheng.services.employeeservice.employeeexceptions.NullNameException;
import dev.zheng.services.expenseservice.ExpenseService;
import dev.zheng.services.expenseservice.ExpenseServiceImpl;
import dev.zheng.services.expenseservice.expenseexceptions.InvalidStatusException;
import dev.zheng.tests.daotests.EmployeeDAOTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockServiceTests {

    @Test
    void hire_employee_test(){
        EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
        Employee e = new Employee(0, null,
                "zheng");
        Mockito.when(employeeDAO.createEmployee(e)).thenReturn(e);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);
        Assertions.assertThrows(NullNameException.class, ()->{
           employeeService.hireEmployee(e);
        });
    }
    @Test
    void get_expense_by_status_test(){
        ExpenseDAO expenseDAO = Mockito.mock(ExpenseDAO.class);
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1, 1, 300, Status.PENDING, "travel"));
        expenses.add(new Expense(2, 2, 200, Status.PENDING, "hotel"));
        expenses.add(new Expense(3, 1, 100, Status.DENIED, "car"));
        expenses.add(new Expense(4, 3, 100, Status.DENIED, "rent"));

        Mockito.when(expenseDAO.getAllExpense()).thenReturn(expenses);

        ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);
        List<Expense> deniedExpenses =expenseService.retrieveAllExpensesByStatus("denied");
        Assertions.assertEquals(2,deniedExpenses.size());
        Assertions.assertThrows(InvalidStatusException.class, ()->{
            expenseService.retrieveAllExpensesByStatus("deni");
        });
    }
}
