import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {
    @Test
    @Order(1)
    void create_expense_test(){

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
