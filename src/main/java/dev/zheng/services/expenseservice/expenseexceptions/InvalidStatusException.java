package dev.zheng.services.expenseservice.expenseexceptions;

public class InvalidStatusException extends IllegalArgumentException{
    public InvalidStatusException(String message){
        super(message);
    }
}
