package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class StudentRegister extends JFrame {
    public StudentRegister(String title) {
        setTitle(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("学生注册页面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(160, 0, 500, 50);
        panel.add(label);

        JLabel text0=new JLabel("姓名");
        text0.setFont(new Font("",Font.BOLD,20));
        text0.setBounds(120,80,100,100);
        panel.add(text0);

        JLabel text1=new JLabel("学号");
        text1.setFont(new Font("",Font.BOLD,20));
        text1.setBounds(120,160,100,100);
        panel.add(text1);

        JLabel text2=new JLabel("密码");
        text2.setFont(new Font("",Font.BOLD,20));
        text2.setBounds(120,240,100,100);
        panel.add(text2);

        JLabel text3=new JLabel("确认密码");
        text3.setFont(new Font("",Font.BOLD,20));
        text3.setBounds(80,320,100,100);
        panel.add(text3);

        //创建姓名文本框
        JTextField word0=new JTextField();
        word0.setFont(new Font("",Font.PLAIN,18));
        word0.setBounds(180,120,250,30);
        panel.add(word0);

        //创建学号文本框
        JTextField word1=new JTextField();
        word1.setFont(new Font("",Font.PLAIN,18));
        word1.setBounds(180,200,250,30);
        panel.add(word1);

        //密码框创建
        JPasswordField word2=new JPasswordField();
        word2.setFont(new Font("",Font.PLAIN,18));
        word2.setBounds(180,280,250,30);
        panel.add(word2);

        //确认密码框创建
        JPasswordField word3=new JPasswordField();
        word3.setFont(new Font("",Font.PLAIN,18));
        word3.setBounds(180,360,250,30);
        panel.add(word3);

        //确认和取消按钮
        JButton button1=new JButton("确认");
        button1.setFont(new Font("",Font.BOLD,20));
        button1.setBounds(130,450,100,50);
        panel.add(button1);

        JButton button2=new JButton("取消");
        button2.setFont(new Font("",Font.BOLD,20));
        button2.setBounds(360,450,100,50);
        panel.add(button2);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent r) {

                //两次密码相同判断
                if (word2.getText().equals(word3.getText())) {

                    String url = "jdbc:mysql://localhost:3306/mysql";
                    String username = "root";
                    String password = "123456";
                    String sql = "INSERT INTO 登录表(category,uname,id,password) VALUES(?,?,?,?)";
                    String sql1 = "INSERT INTO 选课表(category,uname,id) VALUES(?,?,?)";
                    try (Connection conn = DriverManager.getConnection(url, username, password);
                         PreparedStatement pstmt = conn.prepareStatement(sql);
                         PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
                        // 设置参数
                        pstmt.setString(1, "学生");
                        pstmt.setString(2, word0.getText());
                        pstmt.setString(3, word1.getText());
                        pstmt.setString(4, word2.getText());

                        pstmt1.setString(1, "学生");
                        pstmt1.setString(2, word0.getText());
                        pstmt1.setString(3, word1.getText());

                        pstmt.executeUpdate();
                        pstmt1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "注册成功");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"两次密码不同，请重新输入");
                }
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });


    }
}
