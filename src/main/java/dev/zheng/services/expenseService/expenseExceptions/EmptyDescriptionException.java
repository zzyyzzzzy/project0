package dev.zheng.services.expenseService.expenseExceptions;

public class EmptyDescriptionException extends IllegalArgumentException{
    public EmptyDescriptionException(String message){
        super(message);
    }
}
