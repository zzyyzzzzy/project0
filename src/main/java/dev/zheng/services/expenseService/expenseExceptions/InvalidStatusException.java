package dev.zheng.services.expenseService.expenseExceptions;

public class InvalidStatusException extends IllegalArgumentException{
    public InvalidStatusException(String message){
        super(message);
    }
}
