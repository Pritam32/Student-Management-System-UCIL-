/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

/**
 *
 * @author prita
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Myconnection {
    
    private static final String user="project";
    private static final String password="javahome";
    private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
    private static Connection conn=null;
    
    public static Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(url,user,password);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        } 
        return conn;
    }
}
