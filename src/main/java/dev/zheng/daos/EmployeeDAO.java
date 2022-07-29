package dev.zheng.daos;

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
    List<Employee> getAllEmployee();

    // delete
    boolean deleteEmployee(int id);


}
