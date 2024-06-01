package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AdminEdit extends JFrame {
    public AdminEdit() {
        JFrame frame = new JFrame();
        frame.setTitle("管理员修改页面");
        frame.setSize(600, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        //getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("管理员修改界面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(190, 0, 500, 50);
        panel.add(label);

        JLabel text1=new JLabel("姓名");
        text1.setFont(new Font("",Font.BOLD,20));
        text1.setBounds(120,80,100,100);
        panel.add(text1);

        JLabel text2=new JLabel("账号");
        text2.setFont(new Font("",Font.BOLD,20));
        text2.setBounds(120,160,100,100);
        panel.add(text2);

        JLabel text3=new JLabel("密码");
        text3.setFont(new Font("",Font.BOLD,20));
        text3.setBounds(120,240,100,100);
        panel.add(text3);

        JLabel text4=new JLabel("修改后姓名");
        text4.setFont(new Font("",Font.BOLD,20));
        text4.setBounds(55,330,150,100);
        panel.add(text4);

        JLabel text5=new JLabel("修改后账号");
        text5.setFont(new Font("",Font.BOLD,20));
        text5.setBounds(55,410,150,100);
        panel.add(text5);

        JLabel text6=new JLabel("修改后密码");
        text6.setFont(new Font("",Font.BOLD,20));
        text6.setBounds(55,490,150,100);
        panel.add(text6);

        //创建文本框
        JTextField word1=new JTextField();
        word1.setFont(new Font("",Font.PLAIN,18));
        word1.setBounds(180,120,250,30);
        panel.add(word1);

        JTextField word2=new JTextField();
        word2.setFont(new Font("",Font.PLAIN,18));
        word2.setBounds(180,200,250,30);
        panel.add(word2);

        JTextField word3=new JTextField();
        word3.setFont(new Font("",Font.PLAIN,18));
        word3.setBounds(180,280,250,30);
        panel.add(word3);

        JTextField word4=new JTextField();
        word4.setFont(new Font("",Font.PLAIN,18));
        word4.setBounds(180,360,250,30);
        panel.add(word4);

        JTextField word5=new JTextField();
        word5.setFont(new Font("",Font.PLAIN,18));
        word5.setBounds(180,440,250,30);
        panel.add(word5);

        JTextField word6=new JTextField();
        word6.setFont(new Font("",Font.PLAIN,18));
        word6.setBounds(180,520,250,30);
        panel.add(word6);

        JButton button1=new JButton("确认");
        button1.setBounds(100, 600, 80, 50);
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
                            String update = "UPDATE 登录表 SET uname=? ,id=? ,password=?  WHERE uname=? AND id=? ";
                            String update1 = "UPDATE 选课表 SET uname=? ,id=?   WHERE uname=? AND id=? ";
                            try (PreparedStatement updateStmt = conn.prepareStatement(update);
                                 PreparedStatement updateStmt1 = conn.prepareStatement(update1)) {

                                updateStmt.setString(1, word4.getText());
                                updateStmt.setString(2, word5.getText());
                                updateStmt.setString(3, word6.getText());
                                updateStmt.setString(4, word1.getText());
                                updateStmt.setString(5, word2.getText());

                                updateStmt1.setString(1, word4.getText());
                                updateStmt1.setString(2, word5.getText());
                                updateStmt1.setString(3, word1.getText());
                                updateStmt1.setString(4, word2.getText());

                                updateStmt.executeUpdate();
                                updateStmt1.executeUpdate();

                                JOptionPane.showMessageDialog(null, "修改成功！");
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

        JButton button2=new JButton("返回");
        button2.setBounds(400, 600, 80, 50);
        button2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(button2);

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
