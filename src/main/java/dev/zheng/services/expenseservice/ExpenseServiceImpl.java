package dev.zheng.services.expenseservice;

import dev.zheng.daos.expensedao.ExpenseDAO;
import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;
import dev.zheng.services.employeeservice.employeeexceptions.InvalidEmployeeIdException;
import dev.zheng.services.expenseservice.expenseexceptions.EmptyDescriptionException;
import dev.zheng.services.expenseservice.expenseexceptions.IllegalAmountException;
import dev.zheng.services.expenseservice.expenseexceptions.InvalidStatusException;
import dev.zheng.services.expenseservice.expenseexceptions.UnModifiableExpenseException;

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
        return expenseDAO.deleteExpense(id);
    }

    @Override
    public Expense modifyExpense(Expense e) {

        checkNullAndAmount(e);
        Expense oldExpense = expenseDAO.getOneExpense(e.getId());
        if(oldExpense.getStatus() == Status.APPROVED || oldExpense.getStatus() == Status.DENIED){
            throw new UnModifiableExpenseException("This expense cannot be modified");
        }
        return expenseDAO.updateExpense(e);
    }

    @Override
    public Expense changeExpenseStatus(int id, String status) {
        Expense oldExpense = expenseDAO.getOneExpense(id);
        if(oldExpense == null){
            throw new InvalidEmployeeIdException("invalid employee id");
        }

        Status actualStatus;
        if (status.equalsIgnoreCase("approve")){
            actualStatus = Status.APPROVED;
        } else if (status.equalsIgnoreCase("deny")) {
            actualStatus = Status.DENIED;
        }else {
            throw new InvalidStatusException("invalid status entered");
        }
        if(oldExpense.getStatus() != Status.PENDING){
            throw new UnModifiableExpenseException("this expense cannot be changed");
        }
        return expenseDAO.patchExpense(id, actualStatus);
    }
}
