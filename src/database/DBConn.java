/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Ryan
 */
import java.sql.*;

public class DBConn {
    private static final String url = "jdbc:mysql://localhost:3307/student_seminar_db";
    private static final String user = "root";
    private static final String pass = ""; 

    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }catch(ClassNotFoundException e){
            System.out.println("❌ JDBC Driver not found: " + e.getMessage());
        }catch(SQLException e){
            System.out.println("❌ Database connection error: " + e.getMessage());
        }
        return con;
    }
}
