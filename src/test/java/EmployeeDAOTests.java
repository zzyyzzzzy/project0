import dev.zheng.daos.EmployeeDAO;
import dev.zheng.daos.EmployeeDAOLocal;
import dev.zheng.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDao = new EmployeeDAOLocal();
    @Test
    @Order(1)
    void create_first_employee_test(){
        Employee e = new Employee(0, "zuojun",
                "zheng", 964904605, "manger", "IT");
        Employee savedEmployee = employeeDao.createEmployee(e);
        Assertions.assertNotEquals(0, savedEmployee.getId());
    }
    @Test
    @Order(2)
    void create_second_employee_test(){
        Employee e = new Employee(0, "gaven",
                "chen", 933282205, "software engineer", "IT");
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
    @Order(3)
    void update_employee_test(){

    }

    @Test
    @Order(4)
    void get_all_employee_test(){

    }

    @Test
    @Order(5)
    void delete_employee_by_id_test(){

    }

}
