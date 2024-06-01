package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminAdd extends JFrame {
    private String selected;
    public AdminAdd() {
        JFrame frame = new JFrame();
        frame.setTitle("管理员添加页面");
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("管理员添加页面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(195, 0, 500, 50);
        panel.add(label);

        JLabel text1=new JLabel("类别");
        text1.setFont(new Font("",Font.BOLD,20));
        text1.setBounds(120,110,100,100);
        panel.add(text1);

        //创建多项列表框
        JComboBox comboBox=new JComboBox();
        comboBox.setBounds(180,150,100,30);
        comboBox.addItem("请选择");
        comboBox.addItem("教师");
        comboBox.addItem("学生");
        panel.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected=comboBox.getSelectedItem().toString();
            }
        });

        JLabel text2=new JLabel("姓名");
        text2.setFont(new Font("",Font.BOLD,20));
        text2.setBounds(120,190,100,100);
        panel.add(text2);

        JLabel text3=new JLabel("账号");
        text3.setFont(new Font("",Font.BOLD,20));
        text3.setBounds(120,270,100,100);
        panel.add(text3);

        JLabel text4=new JLabel("密码");
        text4.setFont(new Font("",Font.BOLD,20));
        text4.setBounds(120,350,100,100);
        panel.add(text4);

        //创建文本框
        JTextField word2=new JTextField();
        word2.setFont(new Font("",Font.PLAIN,18));
        word2.setBounds(180,230,250,30);
        panel.add(word2);

        JTextField word3=new JTextField();
        word3.setFont(new Font("",Font.PLAIN,18));
        word3.setBounds(180,310,250,30);
        panel.add(word3);

        JTextField word4=new JTextField();
        word4.setFont(new Font("",Font.PLAIN,18));
        word4.setBounds(180,390,250,30);
        panel.add(word4);

        //确认按钮
        JButton button1=new JButton("确认");
        button1.setBounds(100, 450, 80, 50);
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(button1);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent r) {

                String url = "jdbc:mysql://localhost:3306/mysql";
                String username = "root";
                String password = "123456";
                String sql = "INSERT INTO 登录表(category,uname,id,password) VALUES(?,?,?,?)";
                String sql1 = "INSERT INTO 选课表(category,uname,id) VALUES(?,?,?) ";

                if (selected.equals("学生")) {
                try (Connection conn = DriverManager.getConnection(url, username, password);
                     PreparedStatement pstmt = conn.prepareStatement(sql);
                     PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
                    // 设置参数
                    pstmt.setString(1, "学生");
                    pstmt.setString(2, word2.getText());
                    pstmt.setString(3, word3.getText());
                    pstmt.setString(4, word4.getText());


                    pstmt1.setString(1, "学生");
                    pstmt1.setString(2, word2.getText());
                    pstmt1.setString(3, word3.getText());

                    pstmt.executeUpdate();
                    pstmt1.executeUpdate();

                    JOptionPane.showMessageDialog(null, "添加成功");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
                else{
                    try (Connection conn = DriverManager.getConnection(url, username, password);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        // 设置参数
                        pstmt.setString(1, "教师");
                        pstmt.setString(2, word2.getText());
                        pstmt.setString(3, word3.getText());
                        pstmt.setString(4, word4.getText());
                        pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "添加成功");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //返回按钮
        JButton button2=new JButton("返回");
        button2.setBounds(400, 450, 80, 50);
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
