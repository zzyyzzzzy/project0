package dev.zheng.services.employeeservice.employeeexceptions;

public class NullNameException extends IllegalArgumentException{
    public NullNameException(String message){
        super(message);
    }
}
