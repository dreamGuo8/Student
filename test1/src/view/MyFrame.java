package view;
import student.StudentRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame {
    public MyFrame(String title) {
        super(title);
        JPanel panel = new JPanel();
        this.setContentPane(panel);

        int width=panel.getWidth();     //面板的宽度
        int height=panel.getHeight();   //面板的高度


        //panel.setLayout(new SimpleLayout());//自定义控件
        panel.setLayout(null);

        JLabel label = new JLabel("学生成绩管理系统");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(width+120, 0, 500, 50);
        panel.add(label);


        JButton button1=new JButton("登录");
        this.setSize(50,50);
        button1.setFont(new Font("", Font.BOLD, 20));
        button1.setBounds(width+180, 120, 100, 50);
        panel.add(button1);

        JButton button2=new JButton("注册");
        this.setSize(50,50);
        button2.setFont(new Font("", Font.BOLD, 20));
        button2.setBounds(width+180, 240, 100, 50);
        panel.add(button2);

        JButton button3=new JButton("退出");
        this.setSize(50,50);
        button3.setFont(new Font("", Font.BOLD, 20));
        button3.setBounds(width+180, 360, 100, 50);
        panel.add(button3);

        //鼠标点击事件 登录按钮
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyLogin myLogin=new MyLogin("登陆页面");
                myLogin.setSize(500,500);
                myLogin.setLocationRelativeTo(null);


            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StudentRegister studentRegister =new StudentRegister("学生注册页面");
                studentRegister.setSize(500,600);
                studentRegister.setLocationRelativeTo(null);


            }
        });
        //鼠标点击事件 退出按钮
        button3.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               System.exit(0);
           }
        });


    }
}
