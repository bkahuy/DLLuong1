/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GLuong;

/**
 *
 * @author buikh
 */
public class nhanvien extends person{
    private String diachi;
    private int luong;

    public String getDiachi() {
        return diachi;
    }

    public int getLuong() {
        return luong;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public nhanvien() {
    }

    public nhanvien(String manv, String hoten, String diachi, int luong) {
        super(manv, hoten);
        this.diachi = diachi;
        this.luong = luong;
    }
    
    
}
