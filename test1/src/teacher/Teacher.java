package teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Teacher extends JFrame {
    public Teacher() {
        setTitle("教师界面");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("教师页面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(190, 0, 500, 50);
        panel.add(label);

        JButton button0=new JButton("学生科目查询");
        button0.setFont(new Font("",Font.BOLD,20));
        button0.setBounds(160,100,180,50);
        panel.add(button0);

        JButton button1=new JButton("学生成绩查询");
        button1.setFont(new Font("",Font.BOLD,20));
        button1.setBounds(160,200,180,50);
        panel.add(button1);

        JButton button2=new JButton("赋分");
        button2.setFont(new Font("",Font.BOLD,20));
        button2.setBounds(200,300,100,50);
        panel.add(button2);

        JButton button3=new JButton("退出");
        button3.setFont(new Font("",Font.BOLD,20));
        button3.setBounds(350,350,100,50);
        panel.add(button3);

        button0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TeacherCourse course=new TeacherCourse();
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    TeacherInquire teainquire=new TeacherInquire();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        button2.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TeacherScore teacherScore =new TeacherScore();
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
    }
}
