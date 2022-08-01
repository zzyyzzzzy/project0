package dev.zheng.services;

import dev.zheng.app.App;
import dev.zheng.daos.EmployeeDAO;
import dev.zheng.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee hireEmployee(Employee e) {
        return employeeDAO.createEmployee(e);
    }

    @Override
    public Employee retrieveEmployeeById(int id) {
        return employeeDAO.getOneEmployee(id);
    }

    @Override
    public List<Employee> retrieveAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public boolean unHireEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }

    @Override
    public Employee modifyEmployee(Employee e) {
        employeeDAO.updateEmployee(e);
        return e;
    }
}
