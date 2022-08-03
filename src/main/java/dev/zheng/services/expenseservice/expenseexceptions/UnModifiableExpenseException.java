package dev.zheng.services.expenseservice.expenseexceptions;

public class UnModifiableExpenseException extends IllegalArgumentException{
    public UnModifiableExpenseException(String message){
        super(message);
    }
}
