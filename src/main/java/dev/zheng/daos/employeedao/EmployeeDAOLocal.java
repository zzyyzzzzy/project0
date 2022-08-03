package dev.zheng.daos.employeedao;

import dev.zheng.entities.Employee;

import java.util.*;

public class EmployeeDAOLocal implements EmployeeDAO {
    static Map<Integer, Employee> employeeTables = new HashMap<>();
    private int id = 1;
    @Override
    public Employee createEmployee(Employee e) {
        e.setId(id);
        employeeTables.put(e.getId(), e);
        id++;
        return e;
    }

    @Override
    public Employee updateEmployee(Employee e) {
        employeeTables.put(e.getId(), e);
        return e;
    }

    @Override
    public Employee getOneEmployee(int id) {
        return employeeTables.get(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
       Collection<Employee> allEmployeesValues=  employeeTables.values();
       ArrayList<Employee> allEmployees = new ArrayList<>(allEmployeesValues);
       return new ArrayList<>(allEmployees);
    }

    @Override
    public boolean deleteEmployee(int id) {
        if(!employeeTables.containsKey(id)){
            return false;
        }
        employeeTables.remove(id);
        return true;
    }
}
