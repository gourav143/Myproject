/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author gaurav ambildhuke
 */
public class MySqlConnect {
    Connection con = null;
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","user","12345678");
            JOptionPane.showMessageDialog(null, "conected to the database DEMODB(stud)");
            return con;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
     public static Connection connectDB2(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/faculti","user","12345678");
            JOptionPane.showMessageDialog(null, "conected to the database FACULTI(fac)");
            return con;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
