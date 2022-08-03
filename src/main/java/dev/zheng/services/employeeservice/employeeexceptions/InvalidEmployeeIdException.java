package dev.zheng.services.employeeservice.employeeexceptions;

public class InvalidEmployeeIdException extends IllegalArgumentException{
    public InvalidEmployeeIdException(String message){
        super(message);
    }
}
