package teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class TeacherScore extends JFrame {
    private int total;
    public TeacherScore() {
        JFrame frame = new JFrame();
        setTitle("教师赋分界面");
        frame.setSize(600, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("教师赋分界面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(190, 0, 500, 50);
        panel.add(label);

        JLabel text0=new JLabel("姓名");
        text0.setFont(new Font("",Font.BOLD,20));
        text0.setBounds(120,50,100,100);
        panel.add(text0);

        JLabel text1=new JLabel("学号");
        text1.setFont(new Font("",Font.BOLD,20));
        text1.setBounds(120,130,100,100);
        panel.add(text1);

        JLabel text2=new JLabel("课程一");
        text2.setFont(new Font("",Font.BOLD,20));
        text2.setBounds(120,210,100,100);
        panel.add(text2);

        JLabel text3=new JLabel("课程二");
        text3.setFont(new Font("",Font.BOLD,20));
        text3.setBounds(120,290,100,100);
        panel.add(text3);

        JLabel text4=new JLabel("课程三");
        text4.setFont(new Font("",Font.BOLD,20));
        text4.setBounds(120,370,100,100);
        panel.add(text4);

        JLabel text5=new JLabel("课程四");
        text5.setFont(new Font("",Font.BOLD,20));
        text5.setBounds(120,450,100,100);
        panel.add(text5);

        JLabel text6=new JLabel("课程五");
        text6.setFont(new Font("",Font.BOLD,20));
        text6.setBounds(120,530,100,100);
        panel.add(text6);

        //创建文本框
        JTextField word0=new JTextField();
        word0.setFont(new Font("",Font.PLAIN,18));
        word0.setBounds(200,90,250,30);
        panel.add(word0);

        JTextField word1=new JTextField();
        word1.setFont(new Font("",Font.PLAIN,18));
        word1.setBounds(200,170,250,30);
        panel.add(word1);

        JTextField word2=new JTextField();
        word2.setFont(new Font("",Font.PLAIN,18));
        word2.setBounds(200,250,250,30);
        panel.add(word2);

        JTextField word3=new JTextField();
        word3.setFont(new Font("",Font.PLAIN,18));
        word3.setBounds(200,330,250,30);
        panel.add(word3);

        JTextField word4=new JTextField();
        word4.setFont(new Font("",Font.PLAIN,18));
        word4.setBounds(200,410,250,30);
        panel.add(word4);

        JTextField word5=new JTextField();
        word5.setFont(new Font("",Font.PLAIN,18));
        word5.setBounds(200,490,250,30);
        panel.add(word5);

        JTextField word6=new JTextField();
        word6.setFont(new Font("",Font.PLAIN,18));
        word6.setBounds(200,570,250,30);
        panel.add(word6);

        //确认和取消按钮
        JButton button1=new JButton("确认");
        button1.setFont(new Font("",Font.BOLD,20));
        button1.setBounds(130,700,100,50);
        panel.add(button1);

        JButton button2=new JButton("返回");
        button2.setFont(new Font("",Font.BOLD,20));
        button2.setBounds(360,700,100,50);
        panel.add(button2);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            // 连接数据库
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "123456")) {
                total= Integer.parseInt(word2.getText())+Integer.parseInt(word3.getText())+Integer.parseInt(word4.getText())+Integer.parseInt(word5.getText())+Integer.parseInt(word6.getText());
                // 查询数据库
                String query = "select * from 登录表 where uname=? AND id=?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, word0.getText());
                    stmt.setString(2, word1.getText());
                    ResultSet rs = stmt.executeQuery();

                    boolean flag=false;
                    while (rs.next()) {
                        // 更新数据行
                        String update = "UPDATE 登录表 SET Lesson1=? ,Lesson2=? ,Lesson3=? ,Lesson4=?, Lesson5=? ,Total=? WHERE uname=? AND id=? ";
                        try (PreparedStatement updateStmt = conn.prepareStatement(update)) {
                            updateStmt.setString(1, word2.getText());
                            updateStmt.setString(2, word3.getText());
                            updateStmt.setString(3, word4.getText());
                            updateStmt.setString(4, word5.getText());
                            updateStmt.setString(5, word6.getText());
                            updateStmt.setInt(6, total);
                            updateStmt.setString(7, word0.getText());
                            updateStmt.setString(8, word1.getText());

                            updateStmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "成绩赋值成功！");
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        JOptionPane.showMessageDialog(null, "没有找到该学生！");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "错误");
            }
        }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
