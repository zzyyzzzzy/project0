package dev.zheng.services.expenseservice.expenseexceptions;

public class IllegalAmountException extends IllegalArgumentException{
    public IllegalAmountException(String message){
        super(message);
    }
}
