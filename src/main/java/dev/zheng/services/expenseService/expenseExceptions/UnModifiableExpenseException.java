package dev.zheng.services.expenseService.expenseExceptions;

public class UnModifiableExpenseException extends IllegalArgumentException{
    public UnModifiableExpenseException(String message){
        super(message);
    }
}
