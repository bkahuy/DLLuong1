/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GLuong;
import java.sql.*;
/**
 *
 * @author buikh
 */
public class XLLuong {
    private static Connection cn;
    
    public static void getcon(){
        if(cn == null){
            try {
                cn =DriverManager.getConnection("jdbc:sqlserver://BKAHUYYYYY;database=DLLuong1;user=sa;password=1;trustServerCertificate=true;");
                System.out.println("pass connect");
            } catch (SQLException e) {
                System.out.println("failed connect "+ e.getMessage());
            }
        }
    }
    
    public static ResultSet getNV(){
        getcon();
        try {
            Statement st = cn.createStatement();
            return st.executeQuery("select * from tbNhanvien");
        } catch (SQLException e) {
            System.out.println("failed getNV "+ e.getMessage());
            return null;
        }
    }
    
    public static ResultSet getNVbyMaNV(String manv){
        getcon();
        try {
            PreparedStatement pst = cn.prepareStatement("select * from tbNhanvien where manv = '" + manv + "'");
            return pst.executeQuery();
            
        } catch (SQLException e) {
            System.out.println("failed getNVbyMaNV "+e.getMessage());
            return null;
        }
    }
    
    public static boolean insertNV(nhanvien nv){
        getcon();
        try {
            PreparedStatement pst = cn.prepareStatement("insert into tbNhanvien values (?,?,?,?)");
            pst.setString(1, nv.getManv());
            pst.setString(2, nv.getHoten());
            pst.setString(3, nv.getDiachi());
            pst.setInt(4, nv.getLuong());
            int res = pst.executeUpdate();
            return res>0;
        } catch (SQLException e) {
            System.out.println("failed insertNV "+e.getMessage());
            return false;
        }
    }
    public static boolean updateNV(String manv, nhanvien nv){
        getcon();
        try {
            PreparedStatement pst = cn.prepareStatement("update tbNhanvien set hoten = N'" + nv.getHoten() + "', diachi = N'" + nv.getDiachi() + "', luong = " + nv.getLuong() + " where manv = '" + manv + "'");
            
            int res = pst.executeUpdate();
            return res>0;
        } catch (SQLException e) {
            System.out.println("failed insertNV "+e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteNV(String manv){
        getcon();
        try {
            PreparedStatement pst = cn.prepareStatement("delete tbNhanvien where manv = '" + manv +"'");
            
            int res = pst.executeUpdate();
            return res>0;
        } catch (SQLException e) {
            System.out.println("failed deleteNV "+e.getMessage());
            return false;
        }
    }
    
}
