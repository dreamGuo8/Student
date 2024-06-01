package view;
import admin.Admin;
import student.Student;
import teacher.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class MyLogin extends JFrame {
    private String selected;
    public MyLogin(String title) {
        setTitle(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        //文本提示
        JLabel text1=new JLabel("类别");
        text1.setFont(new Font("",Font.BOLD,20));
        text1.setBounds(120,0,100,100);
        panel.add(text1);

        JLabel text2=new JLabel("账号");
        text2.setFont(new Font("",Font.BOLD,20));
        text2.setBounds(120,100,100,100);
        panel.add(text2);

        JLabel text3=new JLabel("密码");
        text3.setFont(new Font("",Font.BOLD,20));
        text3.setBounds(120,200,100,100);
        panel.add(text3);

        //创建文本框
        JTextField word1=new JTextField();
        word1.setFont(new Font("",Font.PLAIN,18));
        word1.setBounds(180,140,250,30);
        panel.add(word1);

        //密码框创建
        JPasswordField word2=new JPasswordField();
        word2.setFont(new Font("",Font.PLAIN,18));
        word2.setBounds(180,240,250,30);
        panel.add(word2);

        //创建多项列表框
        JComboBox comboBox=new JComboBox();
        comboBox.setBounds(180,35,100,30);
        comboBox.addItem("请选择");
        comboBox.addItem("管理员");
        comboBox.addItem("教师");
        comboBox.addItem("学生");
        panel.add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected=comboBox.getSelectedItem().toString();
            }
        });

        //确认和取消按钮
        JButton button1=new JButton("确认");
        button1.setFont(new Font("",Font.BOLD,20));
        button1.setBounds(130,320,100,50);
        panel.add(button1);

        JButton button2=new JButton("取消");
        button2.setFont(new Font("",Font.BOLD,20));
        button2.setBounds(330,320,100,50);
        panel.add(button2);

        //鼠标事件
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent r) {

                //调用数据库
                try {
                    //注册驱动
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    //获取连接
                    Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "123456");
                    //获取执行者连接
                    Statement stat = com.createStatement();
                    try {
                        ResultSet result=stat.executeQuery("select * from 登录表");
                        boolean flag=false;

                        while(result.next()){
                            String category=result.getString("category");
                            String id=result.getString("id");
                            String password=result.getString("password");

                            if(word1.getText().equals(id)&&word2.getText().equals(password) &&selected.equals(category)){
                                flag=true;
                                if(category.equals("学生")){
                                    Student student=new Student(id);
                                }
                                else if(category.equals("管理员")){
                                    Admin admin=new Admin();
                                }
                                else if(category.equals("教师")){
                                    Teacher teacher=new Teacher();
                                }
                                break;
                            }
                        }
                        if(!flag){
                            JOptionPane.showMessageDialog(null,"账号或密码错误");
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
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
