package admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin extends JFrame {
    public Admin() {
        setTitle("管理员界面");
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        JLabel label = new JLabel("管理员界面");
        label.setFont(new Font("微软雅黑", Font.BOLD, 30));
        label.setBounds(220, 0, 500, 50);
        panel.add(label);

        JButton button1=new JButton("添加");
        button1.setFont(new Font("",Font.BOLD,20));
        button1.setBounds(250,150,100,50);
        panel.add(button1);

        JButton button2=new JButton("查询");
        button2.setFont(new Font("",Font.BOLD,20));
        button2.setBounds(250,230,100,50);
        panel.add(button2);

        JButton button3=new JButton("修改");
        button3.setFont(new Font("",Font.BOLD,20));
        button3.setBounds(250,310,100,50);
        panel.add(button3);

        JButton button4=new JButton("删除");
        button4.setFont(new Font("",Font.BOLD,20));
        button4.setBounds(250,390,100,50);
        panel.add(button4);

        JButton button5=new JButton("退出");
        button5.setFont(new Font("",Font.BOLD,20));
        button5.setBounds(400,450,100,50);
        panel.add(button5);

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminAdd adminAdd = new AdminAdd();
            }
        });

        button2.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        AdminInquire adminInquire = new AdminInquire();
                    }
                });

        button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        AdminEdit adminEdit = new AdminEdit();
                    }
        });

        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminDel adminDel = new AdminDel();
            }
        });

        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

    }
}



