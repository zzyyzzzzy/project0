package dev.zheng.daos.expensedao;

import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;
import dev.zheng.utils.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO {
    @Override
    public Expense createExpense(Expense e) {
        try(Connection conn = ConnectionUtil.createConnection()){
            e.setStatus(Status.PENDING);
            String sql = "insert into expense values(default, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, e.getAmount());
            ps.setString(2, e.getStatus().toString());
            ps.setString(3, e.getDescription());
            ps.setInt(4, e.getEmployeeId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int generatedId = rs.getInt("id");
            e.setId(generatedId);
            return e;

        }catch (SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense e) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set amount=?, description=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, e.getAmount());
            ps.setString(2, e.getDescription());
            ps.setInt(3, e.getId());
            ps.execute();
            return e;
        }catch (SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense patchExpense(int id, Status status) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set status=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toString());
            ps.setInt(2, id);
            ps.execute();
            return getOneExpense(id);
        } catch (SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getOneExpense(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Expense e = new Expense(rs.getInt("id"), rs.getInt("employee_id"),
                    rs.getDouble("amount"), Status.valueOf(rs.getString("status")),
                    rs.getString("description"));
            return e;
        }catch (SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpense() {
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
