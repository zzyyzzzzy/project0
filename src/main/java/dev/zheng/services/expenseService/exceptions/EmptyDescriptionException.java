package dev.zheng.services.expenseService.exceptions;

public class EmptyDescriptionException extends IllegalArgumentException{
    public EmptyDescriptionException(String message){
        super(message);
    }
}
