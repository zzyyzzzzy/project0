package dev.zheng.services;

import dev.zheng.daos.EmployeeDAO;
import dev.zheng.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee hireEmployee(Employee e) {
        Employee savedEmployee = employeeDAO.createEmployee(e);
        return savedEmployee;
    }

    @Override
    public Employee retrieveEmployeeById(int id) {
        return employeeDAO.getOneEmployee(id);
    }

    @Override
    public boolean unHireEmployee(Employee e) {
        return false;
    }

    @Override
    public Employee modifyEmployee(Employee e) {
        return null;
    }
}
