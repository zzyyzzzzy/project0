package dev.zheng.daos.employeedao;

import dev.zheng.entities.Employee;
import dev.zheng.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO{

    @Override
    public Employee createEmployee(Employee e) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into employee values(default, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getFname());
            ps.setString(2, e.getLname());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int generatedId = rs.getInt("id");
            e.setId(generatedId);
            return e;
        }catch(SQLException err){
            err.printStackTrace();
            System.out.println("Something went wrong when creating a employee");
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee e) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set fname = ?, lname = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getFname());
            ps.setString(2, e.getLname());
            ps.setInt(3, e.getId());
            ps.executeUpdate();
            return e;
        }catch (SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getOneEmployee(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Employee e = new Employee(rs.getInt("id"),
                    rs.getString("fname"), rs.getString("lname"));
            return e;

        }catch(SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Employee> allEmployees = new ArrayList<>();
            while(rs.next()){
                Employee e = new Employee(rs.getInt("id"),
                        rs.getString("fname"), rs.getString("lname"));
                allEmployees.add(e);
            }
            return allEmployees;
        }catch (SQLException err){
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int deletedRecords = ps.executeUpdate();
            return deletedRecords > 0;
        }catch (SQLException err){
            err.printStackTrace();
            return false;
        }
    }
}
