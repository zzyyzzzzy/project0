import dev.zheng.daos.employeeDAO.EmployeeDAO;
import dev.zheng.daos.employeeDAO.EmployeeDAOLocal;
import dev.zheng.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDao = new EmployeeDAOLocal();
    @Test
    @Order(1)
    void create_first_employee_test(){
        Employee e = new Employee(0, "zuojun",
                "zheng");
        Employee savedEmployee = employeeDao.createEmployee(e);
        Assertions.assertNotEquals(0, savedEmployee.getId());
    }
    @Test
    @Order(2)
    void create_second_employee_test(){
        Employee e = new Employee(0, "gaven",
                "chen");
        Employee savedEmployee = employeeDao.createEmployee(e);
        Assertions.assertNotEquals(1, savedEmployee.getId());
    }
    @Test
    @Order(3)
    void get_one_employee_test(){
        Employee e = employeeDao.getOneEmployee(2);
        Assertions.assertEquals("gaven", e.getFname());

    }

    @Test
    @Order(4)
    void update_employee_test(){
        Employee e = new Employee(2, "gaven",
                "chen");
        Employee updatedE = employeeDao.updateEmployee(e);
        Assertions.assertEquals("gaven", updatedE.getFname());
        Assertions.assertEquals(2, updatedE.getId());

    }

    @Test
    @Order(5)
    void get_all_employee_test(){
        List<Employee> allEmployees = employeeDao.getAllEmployees();
        Assertions.assertEquals(2, allEmployees.size());
    }

    @Test
    @Order(6)
    void delete_employee_by_id_test(){
       boolean isDeletedExist = employeeDao.deleteEmployee(1);
       boolean isDeletedNotExist = employeeDao.deleteEmployee(4);
       Assertions.assertTrue(isDeletedExist);
       Assertions.assertFalse(isDeletedNotExist);
    }

}
