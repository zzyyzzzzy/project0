package dev.zheng.services;

import dev.zheng.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee hireEmployee(Employee e);
    Employee retrieveEmployeeById(int id);

    List<Employee> retrieveAllEmployees();
    boolean unHireEmployee(int id);
    Employee modifyEmployee(Employee e);
}
