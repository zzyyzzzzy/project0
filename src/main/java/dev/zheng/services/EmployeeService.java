package dev.zheng.services;

import dev.zheng.entities.Employee;

public interface EmployeeService {
    Employee hireEmployee(Employee e);
    Employee retrieveEmployeeById(int id);
    boolean unHireEmployee(Employee e);
    Employee modifyEmployee(Employee e);
}
