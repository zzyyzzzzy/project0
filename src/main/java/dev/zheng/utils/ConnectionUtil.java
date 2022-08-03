package dev.zheng.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection createConnection(){
        try{
            Connection conn = DriverManager.getConnection(System.getenv("PROJ0_SQL_DB"));
            return conn;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
