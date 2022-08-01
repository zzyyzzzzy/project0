import dev.zheng.daos.expenseDAO.ExpenseDAO;
import dev.zheng.daos.expenseDAO.ExpenseDAOLocal;
import dev.zheng.entities.Expense;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {
    static ExpenseDAO expenseDAO = new ExpenseDAOLocal();
    @Test
    @Order(1)
    void create_expense_test(){
        Expense e = new Expense(0, 1, 200,
                "pending", "traffic", 1659044614);
        Expense createdExpense = expenseDAO.createExpense(e);
        Assertions.assertEquals(1, createdExpense.getId());
        Assertions.assertEquals("traffic", createdExpense.getDescription());
    }
    @Test
    @Order(2)
    void update_expense_test(){

    }
    @Test
    @Order(3)
    void get_one_expense_test(){

    }

    @Test
    @Order(4)
    void get_all_expense_test(){

    }

    @Test
    @Order(5)
    void delete_expense_by_id_test(){

    }

}
