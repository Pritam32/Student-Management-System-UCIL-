/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import DB.Myconnection;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Student {
    Connection conn=Myconnection.getConnection();
    PreparedStatement ps;
    
    public int getMax(){
        int id=0;
        Statement st;
        try{
            st=conn.createStatement();
            ResultSet rs=st.executeQuery("select max(id) from student");
            while(rs.next()){
                id=rs.getInt(1);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE,null,ex);
        }
        return id+1;
    } 
    public void insert(int id,String sname,String gender,String email,
    String phone,String father,String mother,String address1,String address2,String imagepath)
    {
        String sql="insert into student values(?,?,?,?,?,?,?,?,?,?)";
        try{
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,sname);
            ps.setString(3,gender);
            ps.setString(4,email);
            ps.setString(5,phone);
            ps.setString(6,father);
            ps.setString(7,mother);
            ps.setString(8,address1);
            ps.setString(9,address2);
            ps.setString(10,imagepath);
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"New Student added Successfully");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void getStudentValue(JTable table, String searchValue){
        String sql="select * from student order by ID desc";
        try{
            ps=conn.prepareStatement(sql);
            
            ResultSet rs= ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next()){
                row=new Object[10];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getString(5);
                row[5]=rs.getString(6);
                row[6]=rs.getString(7);
                row[7]=rs.getString(8);
                row[8]=rs.getString(9);
                row[9]=rs.getString(10);
                model.addRow(row);
            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void update(int id,String sname,String gender,String email,
    String phone,String father,String mother,String address1,String address2,String imagepath){
        String sql="update student set STUDENT_NAME=?,GENDER=?,STUDENT_EMAIL=?,PHONE=?,FATHER_NAME=?, MOTHER_NAME=?,ADDRESSLINE_1=?,ADDRESSLINE_2=?,IMAGE=? where ID=?";
        try{
             ps=conn.prepareStatement(sql);

            ps.setString(1,sname);
            ps.setString(2,gender);
            ps.setString(3,email);
            ps.setString(4,phone);
            ps.setString(5,father);
            ps.setString(6,mother);
            ps.setString(7,address1);
            ps.setString(8,address2);
            ps.setString(9,imagepath);
            ps.setInt(10,id);
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Student data updated Successfully");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void delete(int id){
        int yesorNo=JOptionPane.showConfirmDialog(null, "All the details will be deleted ","Student Delete",JOptionPane.OK_OPTION);
        if(yesorNo==JOptionPane.OK_OPTION){
            try{
                ps=conn.prepareStatement("delete from student where ID=?");
                ps.setInt(1, id);
                if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Student data deleted Successfully");
            }
            }
            catch(SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE,null,ex);
        }
        }
    }
}
