package dev.zheng.daos.employeedao;

import dev.zheng.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    // curd

    // create
    Employee createEmployee(Employee e);

    // update
    Employee updateEmployee(Employee e);

    // read
    Employee getOneEmployee(int id);
    List<Employee> getAllEmployees();

    // delete
    boolean deleteEmployee(int id);


}
