package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AdminDel extends JFrame {
    private String selected;
    public AdminDel() {
        JFrame frame = new JFrame();
        frame.setTitle("管理员删除页面");
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        //getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("管理员删除页面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(200, 0, 500, 50);
        panel.add(label);

        /*JLabel text0=new JLabel("类别");
        text0.setFont(new Font("",Font.BOLD,20));
        text0.setBounds(120,120,100,100);
        panel.add(text0);*/

        JLabel text1=new JLabel("账号");
        text1.setFont(new Font("",Font.BOLD,20));
        text1.setBounds(120,100,100,100);
        panel.add(text1);

        JLabel text2=new JLabel("姓名");
        text2.setFont(new Font("",Font.BOLD,20));
        text2.setBounds(120,220,100,100);
        panel.add(text2);

        /*创建多项列表框
        JComboBox comboBox=new JComboBox();
        comboBox.setBounds(180,160,100,30);
        comboBox.addItem("请选择");
        comboBox.addItem("教师");
        comboBox.addItem("学生");
        panel.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected=comboBox.getSelectedItem().toString();
            }
        });*/

        JTextField word1=new JTextField();
        word1.setFont(new Font("",Font.PLAIN,18));
        word1.setBounds(180,135,250,30);
        panel.add(word1);

        JTextField word2=new JTextField();
        word2.setFont(new Font("",Font.PLAIN,18));
        word2.setBounds(180,255,250,30);
        panel.add(word2);

        //确认按钮
        JButton button1=new JButton("确认");
        button1.setBounds(100, 400, 80, 50);
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(button1);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "123456")) {
                    // 查询数据库
                    String query = "select * from 登录表 ";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        ResultSet rs = stmt.executeQuery();
                        boolean flag=false;
                        while (rs.next()) {
                            // 删除数据行
                            String update = "DELETE FROM 登录表 WHERE id=? AND uname=? ";
                            String update1 = "DELETE FROM 选课表 WHERE id=? AND uname=? ";
                            try (PreparedStatement updateStmt = conn.prepareStatement(update);
                                 PreparedStatement updateStmt1 = conn.prepareStatement(update1)) {
                                updateStmt.setString(1, word1.getText());
                                updateStmt.setString(2, word2.getText());

                                updateStmt1.setString(1, word1.getText());
                                updateStmt1.setString(2, word2.getText());

                                updateStmt.executeUpdate();
                                updateStmt1.executeUpdate();
                                JOptionPane.showMessageDialog(null, "删除成功！");
                                flag=true;
                                break;
                            }
                        }
                        if(!flag){
                            JOptionPane.showMessageDialog(null, "没有找到！");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "错误");
                }
            }
        });

        //返回按钮
        JButton button=new JButton("返回");
        button.setBounds(400, 400, 80, 50);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
}
