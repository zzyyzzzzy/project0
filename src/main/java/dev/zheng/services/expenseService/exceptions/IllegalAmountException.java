package dev.zheng.services.expenseService.exceptions;

public class IllegalAmountException extends IllegalArgumentException{
    public IllegalAmountException(String message){
        super(message);
    }
}
