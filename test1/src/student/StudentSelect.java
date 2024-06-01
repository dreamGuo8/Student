package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentSelect extends JFrame {
    public StudentSelect(String id) {
        JFrame frame = new JFrame();
        frame.setTitle("学生选课页面");
        frame.setSize(600, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("学生选课界面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(240, 0, 500, 50);
        panel.add(label);

        JCheckBox jCheckBox1=new JCheckBox("Java");
        jCheckBox1.setSelected(false);
        jCheckBox1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox1.setBounds(10, 50, 100, 30);
        panel.add(jCheckBox1);

        JCheckBox jCheckBox2=new JCheckBox("C++");
        jCheckBox2.setSelected(false);
        jCheckBox2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox2.setBounds(10, 100, 100, 30);
        panel.add(jCheckBox2);

        JCheckBox jCheckBox3=new JCheckBox("C");
        jCheckBox3.setSelected(false);
        jCheckBox3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox3.setBounds(10, 150, 100, 30);
        panel.add(jCheckBox3);

        JCheckBox jCheckBox4=new JCheckBox("Python");
        jCheckBox4.setSelected(false);
        jCheckBox4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox4.setBounds(10, 200, 100, 30);
        panel.add(jCheckBox4);

        JCheckBox jCheckBox5=new JCheckBox("C#");
        jCheckBox5.setSelected(false);
        jCheckBox5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox5.setBounds(10, 250, 100, 30);
        panel.add(jCheckBox5);

        JCheckBox jCheckBox6=new JCheckBox("PHP");
        jCheckBox6.setSelected(false);
        jCheckBox6.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox6.setBounds(10, 300, 100, 30);
        panel.add(jCheckBox6);

        JCheckBox jCheckBox7=new JCheckBox("MATLAB");
        jCheckBox7.setSelected(false);
        jCheckBox7.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox7.setBounds(10, 350, 150, 30);
        panel.add(jCheckBox7);

        JCheckBox jCheckBox8=new JCheckBox("Go");
        jCheckBox8.setSelected(false);
        jCheckBox8.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox8.setBounds(10, 400, 100, 30);
        panel.add(jCheckBox8);

        JCheckBox jCheckBox9=new JCheckBox("Html");
        jCheckBox9.setSelected(false);
        jCheckBox9.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jCheckBox9.setBounds(10, 450, 100, 30);
        panel.add(jCheckBox9);

        JButton button1=new JButton("确认");
        button1.setBounds(100, 600, 80, 50);
        button1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(button1);

        JLabel tips = new JLabel("注：有且仅能选择五项");
        tips.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        tips.setBounds(300, 500, 500, 50);
        panel.add(tips);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                List<String> selectedValues = new ArrayList<>();
                if (jCheckBox1.isSelected()) {
                    selectedValues.add("Java");
                }
                if (jCheckBox2.isSelected()) {
                    selectedValues.add("C++");
                }
                if (jCheckBox3.isSelected()) {
                    selectedValues.add("C");
                }
                if (jCheckBox4.isSelected()) {
                    selectedValues.add("Python");
                }
                if (jCheckBox5.isSelected()) {
                    selectedValues.add("C#");
                }
                if (jCheckBox6.isSelected()) {
                    selectedValues.add("PHP");
                }
                if (jCheckBox7.isSelected()) {
                    selectedValues.add("MATLAB");
                }
                if (jCheckBox8.isSelected()) {
                    selectedValues.add("GO");
                }
                if (jCheckBox9.isSelected()) {
                    selectedValues.add("Html");
                }

                // 连接数据库
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "123456")) {
                    // 查询数据库
                    String query = "select * from 选课表 where id=?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, id);
                        ResultSet rs = stmt.executeQuery();
                        boolean flag=false;
                        while (rs.next()) {
                            // 更新数据行
                            String update = "UPDATE 选课表 SET Lesson1=? ,Lesson2=? ,Lesson3=? ,Lesson4=?, Lesson5=? WHERE id=? ";
                            try (PreparedStatement updateStmt = conn.prepareStatement(update)) {
                                updateStmt.setString(1, selectedValues.get(0));
                                updateStmt.setString(2, selectedValues.get(1));
                                updateStmt.setString(3, selectedValues.get(2));
                                updateStmt.setString(4, selectedValues.get(3));
                                updateStmt.setString(5, selectedValues.get(4));
                                updateStmt.setString(6, id);

                                updateStmt.executeUpdate();
                                JOptionPane.showMessageDialog(null, "科目选择成功！");
                                flag=true;
                                selectedValues.clear();
                                break;
                            }
                        }
                        if(!flag){
                            JOptionPane.showMessageDialog(null, "没有该学生信息！");
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
