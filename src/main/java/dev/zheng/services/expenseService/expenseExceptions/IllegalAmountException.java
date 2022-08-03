package dev.zheng.services.expenseService.expenseExceptions;

public class IllegalAmountException extends IllegalArgumentException{
    public IllegalAmountException(String message){
        super(message);
    }
}
