package dev.zheng.services.expenseservice.expenseexceptions;

public class EmptyDescriptionException extends IllegalArgumentException{
    public EmptyDescriptionException(String message){
        super(message);
    }
}
