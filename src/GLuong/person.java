/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GLuong;

/**
 *
 * @author buikh
 */
public class person {
    private String manv, hoten;

    public String getManv() {
        return manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public person(String manv, String hoten) {
        this.manv = manv;
        this.hoten = hoten;
    }

    public person() {
        this.manv = "";
        this.hoten = "";
    }
    
    
}
