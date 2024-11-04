/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GLuong;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author buikh
 */
public class Gui_Nhanvien extends JFrame implements MouseListener, ActionListener{

    private JTextField tfmanv, tfhoten;
    private JComboBox<String> cbdiachi;
    private JTextField tfluong;
    private JButton btnthem, btnsua, btnxoa, btntimkiem;
    private JTable tb;
    private DefaultTableModel dfmodel;
    
    public Gui_Nhanvien(){
        setTitle("quan ly nhan vien");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,600);
        setResizable(false);
        setLocationRelativeTo(null);
        
        buildGui();
        loadData(dfmodel);
    }
    
    private void buildGui(){
        JPanel pntrai = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // ma nv
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lbmanv = new JLabel("ma nhan vien");
        pntrai.add(lbmanv,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfmanv = new JTextField();
        tfmanv.setSize(new Dimension(500,30));
        pntrai.add(tfmanv,gbc);
        
        // ho ten
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lbhoten = new JLabel("ho ten");
        pntrai.add(lbhoten,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        tfhoten = new JTextField();
        tfhoten.setSize(new Dimension(500,30));
        pntrai.add(tfhoten,gbc);
        
        //dia chi
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lbdiachi = new JLabel("dia chi");
        pntrai.add(lbdiachi,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        cbdiachi = new JComboBox<>(new String[]{"hai phong", "ha noi", "nam dinh", "thanh hoa"});
        pntrai.add(cbdiachi,gbc);
        
        //luong
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lbluong = new JLabel("luong");
        pntrai.add(lbluong,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        tfluong = new JTextField();
        tfluong.setSize(new Dimension(500,30));
        pntrai.add(tfluong,gbc);
        
        //button
        gbc.gridx = 0;
        gbc.gridy = 4;
        btnthem = new JButton("them");
        btnthem.addActionListener((e) -> {
            XLLuong xl = new XLLuong();
            String manv = tfmanv.getText().trim();
            String hoten = tfhoten.getText().trim();
            String diachi = cbdiachi.getSelectedItem().toString();
            int luong = Integer.parseInt(tfluong.getText().trim().toString());
            nhanvien nv = new nhanvien(manv, hoten, diachi, luong);
            boolean res = xl.insertNV(nv);
            if (res) {
                loadData(dfmodel);
                JOptionPane.showMessageDialog(null, "them nhan vien thanh cong");
            }
            else{
                JOptionPane.showMessageDialog(null, "them khong thanh cong");
            }
        });
        pntrai.add(btnthem, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        btnsua = new JButton("sua");
        btnsua.addActionListener((e) -> {
            XLLuong xl = new XLLuong();
            String manv = tfmanv.getText().trim();
            String hoten = tfhoten.getText().trim();
            String diachi = cbdiachi.getSelectedItem().toString();
            int luong = Integer.parseInt(tfluong.getText().trim().toString());
            nhanvien nv = new nhanvien(manv, hoten, diachi, luong);
            boolean res = xl.updateNV(manv, nv);
            if (res) {
                loadData(dfmodel);
                JOptionPane.showMessageDialog(null, "sua nhan vien thanh cong");
            }
            else{
                JOptionPane.showMessageDialog(null, "sua khong thanh cong");
            }
        });
        pntrai.add(btnsua, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        btnxoa = new JButton("xoa");
        btnxoa.addActionListener((e) -> {
            XLLuong xl = new XLLuong();
            String manv = tfmanv.getText().trim();
            
            boolean res = xl.deleteNV(manv);
            if (res) {
                loadData(dfmodel);
                JOptionPane.showMessageDialog(null, "xoa nhan vien thanh cong");
            }
            else{
                JOptionPane.showMessageDialog(null, "xoa khong thanh cong");
            }
        });
        pntrai.add(btnxoa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        btntimkiem = new JButton("tim kiem");
        btntimkiem.addActionListener((e) -> {
            XLLuong xl = new XLLuong();
            String manv = tfmanv.getText().trim();
            
            ResultSet res = xl.getNVbyMaNV(manv);
            try {
                if (res != null && res.next()) {
                    dfmodel.setRowCount(0);
                    dfmodel.addRow(new Object[]{
                        res.getString("manv"),
                        res.getString("hoten"),
                        res.getString("diachi"),
                        res.getInt("luong")
                    });
                    JOptionPane.showMessageDialog(null, "them nhan vien thanh cong");
                }
                else{
                    JOptionPane.showMessageDialog(null, "them khong thanh cong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Gui_Nhanvien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        pntrai.add(btntimkiem, gbc);
        
        
        
        JPanel pnphai = new JPanel(new GridLayout(1, 1));
        String[] header = {"ma nhan vien", "ho ten", "dia chi", "luong"};
        dfmodel = new DefaultTableModel(header,0);
        tb = new JTable(dfmodel);
        pnphai.add(new JScrollPane(tb));
        
        //su kien click bang
        
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pntrai, BorderLayout.WEST);
        getContentPane().add(pnphai, BorderLayout.CENTER);
    }
    
    private void loadData(DefaultTableModel dfmodel){
        try {
            XLLuong xl = new XLLuong();
            ResultSet res = xl.getNV();
            dfmodel.setRowCount(0);
            dfmodel.fireTableDataChanged();
            if (res != null) {
                while(res.next()){
                    dfmodel.addRow(new Object[]{
                        res.getString("manv"),
                        res.getString("hoten"),
                        res.getString("diachi"),
                        res.getInt("luong")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("loi loaddata "+ e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new Gui_Nhanvien().setVisible(true);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
