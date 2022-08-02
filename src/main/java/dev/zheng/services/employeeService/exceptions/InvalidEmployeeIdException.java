package dev.zheng.services.employeeService.exceptions;

public class InvalidEmployeeIdException extends IllegalArgumentException{
    public InvalidEmployeeIdException(String message){
        super(message);
    }
}
