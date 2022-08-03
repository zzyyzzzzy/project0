package dev.zheng.services.employeeService.employeeExceptions;

public class InvalidEmployeeIdException extends IllegalArgumentException{
    public InvalidEmployeeIdException(String message){
        super(message);
    }
}
