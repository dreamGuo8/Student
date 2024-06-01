package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Student extends JFrame {
    public Student(String id) {
        setTitle("学生界面");
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("学生选课界面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(220, 0, 500, 50);
        panel.add(label);

        //创建按钮
        JButton button1=new JButton("选课");
        button1.setFont(new Font("",Font.BOLD,20));
        button1.setBounds(250,150,100,50);
        panel.add(button1);

        JButton button2=new JButton("查询成绩");
        button2.setFont(new Font("",Font.BOLD,20));
        button2.setBounds(225,230,150,50);
        panel.add(button2);

        JButton button3=new JButton("退出");
        button3.setFont(new Font("",Font.BOLD,20));
        button3.setBounds(400,450,100,50);
        panel.add(button3);

        //按钮监听器
        button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        StudentSelect studentSelect=new StudentSelect(id);
                    }
                }
        );

        button2.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            StudentInquire inquire=new StudentInquire(id);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
        );

        button3.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.exit(0);
                    }
                }
        );
    }

}
