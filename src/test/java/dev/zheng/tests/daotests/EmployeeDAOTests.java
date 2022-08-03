package dev.zheng.tests.daotests;

import dev.zheng.daos.employeedao.EmployeeDAO;
import dev.zheng.daos.employeedao.EmployeeDaoPostgres;
import dev.zheng.entities.Employee;
import dev.zheng.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDao = new EmployeeDaoPostgres();

    @BeforeAll // this method will execute before any tests ordered or unordered
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table employee(\n" +
                    "\tid serial primary key,\n" +
                    "\tfname varchar(100) not null,\n" +
                    "\tlname varchar(100) not null\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
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
        Assertions.assertNotEquals(0, savedEmployee.getId());
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
       boolean deleteExistingEmployee = employeeDao.deleteEmployee(1);
       boolean deleteNonExistingEmployee = employeeDao.deleteEmployee(4);
       Assertions.assertTrue(deleteExistingEmployee);
       Assertions.assertFalse(deleteNonExistingEmployee);
    }

    @AfterAll // runs after the last test finishes
    static void teardown(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table employee";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
