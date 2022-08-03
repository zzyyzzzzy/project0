import dev.zheng.daos.expenseDAO.ExpenseDAO;
import dev.zheng.daos.expenseDAO.ExpenseDAOLocal;
import dev.zheng.entities.Employee;
import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {
    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();
    @Test
    @Order(1)
    void create_expense_test(){
        Expense e = new Expense(0, 1, 200, null, "travel");
        Expense createdExpense = expenseDAO.createExpense(e);
        Assertions.assertEquals(1, createdExpense.getId());
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
        Expense e = new Expense(0, 2, 132, null, "hotel");
        expenseDAO.createExpense(e);
        List<Expense> allExpense= expenseDAO.getAllExpense(null);
        Assertions.assertEquals(2, allExpense.size());
    }
    @Test
    @Order(4)
    void update_expense_test(){
        Expense e = new Expense(1, 3, 300, null, "travel");
        Expense updatedExpense = expenseDAO.updateExpense(e);
        Assertions.assertEquals(300, updatedExpense.getAmount());
        Assertions.assertEquals(3, updatedExpense.getEmployeeId());

    }


    @Test
    @Order(5)
    void delete_expense_by_id_test(){

    }

}
