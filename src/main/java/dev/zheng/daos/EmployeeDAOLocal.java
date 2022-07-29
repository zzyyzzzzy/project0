package dev.zheng.daos;

import dev.zheng.entities.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return null;
    }

    @Override
    public Employee getOneEmployee(int id) {
        return employeeTables.get(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }
}
