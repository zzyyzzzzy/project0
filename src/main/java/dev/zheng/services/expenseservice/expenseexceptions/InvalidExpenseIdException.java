package dev.zheng.services.expenseservice.expenseexceptions;

public class InvalidExpenseIdException extends IllegalArgumentException{
    public InvalidExpenseIdException(String message){
        super(message);
    }
}
