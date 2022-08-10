package dev.zheng.services.employeeservice;

import dev.zheng.daos.employeedao.EmployeeDAO;
import dev.zheng.entities.Employee;
import dev.zheng.services.employeeservice.employeeexceptions.NullNameException;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    private void checkNullName(Employee e){
        if (e.getFname() == null || e.getLname() == null
                || e.getLname().length() == 0 || e.getFname().length() == 0){
            throw new NullNameException("Name cannot be empty");
        }
    }

    @Override
    public Employee hireEmployee(Employee e) {
        checkNullName(e);
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
        checkNullName(e);
        employeeDAO.updateEmployee(e);
        return e;
    }
}
