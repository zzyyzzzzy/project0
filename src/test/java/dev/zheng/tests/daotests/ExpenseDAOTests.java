package dev.zheng.tests.daotests;

import dev.zheng.daos.expensedao.ExpenseDAO;
import dev.zheng.daos.expensedao.ExpenseDAOLocal;
import dev.zheng.daos.expensedao.ExpenseDAOPostgres;
import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;
import dev.zheng.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {
    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();

    @BeforeAll // this method will execute before any tests ordered or unordered
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table expense(\n" +
                    "\tid serial primary key,\n" +
                    "\tamount double precision not null,\n" +
                    "\tstatus varchar(20) not null,\n" +
                    "\tdescription varchar(100) not null,\n" +
                    "\temployee_id int references employee(id)\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    @Order(1)
    void create_expense_test(){
        Expense e = new Expense(0, 1, 200, null, "travel");
        Expense createdExpense = expenseDAO.createExpense(e);
        Assertions.assertEquals(1, createdExpense.getId());
        Assertions.assertEquals(200, createdExpense.getAmount());
        Assertions.assertEquals("travel", createdExpense.getDescription());
        Assertions.assertEquals(Status.PENDING, createdExpense.getStatus());
    }
    @Test
    @Order(2)
    void get_one_expense_test(){
        Expense e = expenseDAO.getOneExpense(1);
        Assertions.assertEquals(200, e.getAmount());
    }
    @Test
    @Order(3)
    void get_all_expense_test(){
        Expense e = new Expense(0, 1, 132, null, "hotel");
        expenseDAO.createExpense(e);
        List<Expense> allExpense= expenseDAO.getAllExpense();
        Assertions.assertEquals(2, allExpense.size());
    }
    @Test
    @Order(4)
    void update_expense_test(){
        Expense e = new Expense(1, 1, 300, Status.PENDING, "travel");
        Expense updatedExpense = expenseDAO.updateExpense(e);
        Assertions.assertEquals(300, updatedExpense.getAmount());
        Assertions.assertEquals(1, updatedExpense.getEmployeeId());
        Assertions.assertEquals(Status.PENDING, updatedExpense.getStatus());
    }

    @Test
    @Order(5)
    void patch_expense_test(){
        Expense updatedExpense = expenseDAO.patchExpense(1, Status.DENIED);
        Assertions.assertEquals(Status.DENIED, updatedExpense.getStatus());
    }


    @Test
    @Order(6)
    void delete_expense_by_id_test(){
        boolean deleteExistingExpense= expenseDAO.deleteExpense(1);
        boolean deleteNonExistingExpense = expenseDAO.deleteExpense(4);
        Assertions.assertTrue(deleteExistingExpense);
        Assertions.assertFalse(deleteNonExistingExpense);
    }
//    @AfterAll // runs after the last test finishes
//    static void teardown(){
//        try(Connection connection = ConnectionUtil.createConnection()){
//            String sql = "drop table expense";
//            Statement statement = connection.createStatement();
//            statement.execute(sql);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }
}
