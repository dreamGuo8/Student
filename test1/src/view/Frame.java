package view;
import javax.swing.*;

public class Frame {
    public static void main(String[] args) {
        MyFrame frame=new MyFrame("学生成绩管理系统登录注册页面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);//显示在中央
        frame.setVisible(true);
        frame.setResizable(true);         //不固定窗格
    }
}
