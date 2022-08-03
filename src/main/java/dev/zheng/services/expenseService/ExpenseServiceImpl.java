package dev.zheng.services.expenseService;

import dev.zheng.daos.expenseDAO.ExpenseDAO;
import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;
import dev.zheng.services.employeeService.employeeExceptions.InvalidEmployeeIdException;
import dev.zheng.services.expenseService.expenseExceptions.EmptyDescriptionException;
import dev.zheng.services.expenseService.expenseExceptions.IllegalAmountException;
import dev.zheng.services.expenseService.expenseExceptions.InvalidStatusException;
import dev.zheng.services.expenseService.expenseExceptions.UnModifiableExpenseException;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseServiceImpl implements ExpenseService{
    ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO){this.expenseDAO = expenseDAO;}
    private void checkNullAndAmount(Expense e){
        if (e.getDescription() == null){
            throw new EmptyDescriptionException("The description cannot be empty");
        }
        if(e.getAmount() <= 0){
            throw new IllegalAmountException("Amount should not be zero or negative");
        }
        if(e.getEmployeeId() <= 0){
            throw new InvalidEmployeeIdException("Invalid employeeId");
        }
    }

    private Status convertStatusToEnum(String status){
        try{
            // need to update the code here
            return Status.valueOf(status);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    @Override
    public Expense addExpense(Expense e) {
        checkNullAndAmount(e);
        return expenseDAO.createExpense(e);
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return expenseDAO.getOneExpense(id);
    }

    @Override
    public List<Expense> retrieveAllExpenses() {
        return expenseDAO.getAllExpense();
    }

    @Override
    public List<Expense> retrieveAllExpensesByStatus(String status) {
        String upperCasedStatus = status.toUpperCase();
        Status actualStatus = convertStatusToEnum(upperCasedStatus);
        if(actualStatus == null){
            throw new InvalidStatusException("invalid status entered");
        }
        List<Expense> allExpenses = this.retrieveAllExpenses();
        List<Expense> resultExpenses = allExpenses.stream().filter(e -> e.getStatus() == actualStatus).collect(Collectors.toList());
        return resultExpenses;

    }

    @Override
    public boolean removeExpense(int id) {
        return false;
    }

    @Override
    public Expense modifyExpense(Expense e) {

        checkNullAndAmount(e);
        Expense oldExpense = expenseDAO.getOneExpense(e.getId());
        // will also check if the status is invalid
        if(e.getStatus() == null){
            throw new InvalidStatusException("null status entered");
        }
        if(oldExpense.getStatus() == Status.APPROVED || oldExpense.getStatus() == Status.DENIED){
            throw new UnModifiableExpenseException("This expense cannot be modified");
        }
        return expenseDAO.updateExpense(e);
    }

    @Override
    public Expense changeExpenseStatus(Expense e) {
        return null;
    }
}
