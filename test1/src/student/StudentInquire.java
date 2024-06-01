package student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class StudentInquire extends JFrame{

    public StudentInquire(String ids) throws SQLException {

        JFrame frame = new JFrame();
        frame.setTitle("学生成绩查询");
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        //返回按钮
        JButton button=new JButton("返回");
        button.setBounds(400, 400, 80, 50);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        JLabel labelname = new JLabel("姓名");
        labelname.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        labelname.setBounds(120, 0, 100, 50);
        panel.add(labelname);

        JLabel labelnum = new JLabel("学号");
        labelnum.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        labelnum.setBounds(120, 50, 100, 50);
        panel.add(labelnum);

        JLabel labelsc = new JLabel("总分");
        labelsc.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        labelsc.setBounds(120, 350, 100, 50);
        panel.add(labelsc);

        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "root";
        String password = "123456";
        String sql = "SELECT * FROM 登录表 WHERE id = ?";
        String sql1 = "SELECT * FROM 选课表 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {

            // 设置参数
            pstmt.setString(1, ids);
            ResultSet result = pstmt.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 设置参数
            pstmt1.setString(1, ids);
            ResultSet result1 = pstmt1.executeQuery();
            ResultSetMetaData metaData1 = result.getMetaData();
            int columnCount1 = metaData1.getColumnCount();

            // 遍历结果集，打印id对应行的数据
            if (result.next()) {
                String name = result.getString("uname");
                int chinese = result.getInt("Lesson1");
                int math = result.getInt("Lesson2");
                int english = result.getInt("Lesson3");
                int pol = result.getInt("Lesson4");
                int sc = result.getInt("Lesson5");
                int total = chinese + math + english + pol + sc;

                JLabel labelname1 = new JLabel(String.valueOf(name));
                labelname1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelname1.setBounds(300, 0, 100, 50);
                panel.add(labelname1);

                JLabel labelnum1 = new JLabel(ids);
                labelnum1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelnum1.setBounds(300, 50, 300, 50);
                panel.add(labelnum1);

                JLabel labelchinese1 = new JLabel(String.valueOf(chinese));
                labelchinese1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelchinese1.setBounds(300, 100, 100, 50);
                panel.add(labelchinese1);

                JLabel labelmath1 = new JLabel(String.valueOf(math));
                labelmath1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelmath1.setBounds(300, 150, 100, 50);
                panel.add(labelmath1);

                JLabel labelenglish1 = new JLabel(String.valueOf(english));
                labelenglish1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelenglish1.setBounds(300, 200, 100, 50);
                panel.add(labelenglish1);

                JLabel labelpol1 = new JLabel(String.valueOf(pol));
                labelpol1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelpol1.setBounds(300, 250, 80, 50);
                panel.add(labelpol1);

                JLabel labelsp1 = new JLabel(String.valueOf(sc));
                labelsp1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelsp1.setBounds(300, 300, 100, 50);
                panel.add(labelsp1);

                JLabel labelsc1 = new JLabel(String.valueOf(total));
                labelsc1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelsc1.setBounds(300, 350, 100, 50);
                panel.add(labelsc1);
            }

                if (result1.next()) {
                String lesson1 = result1.getString("Lesson1");
                String lesson2 = result1.getString("Lesson2");
                String lesson3 = result1.getString("Lesson3");
                String lesson4 = result1.getString("Lesson4");
                String lesson5 = result1.getString("Lesson5");

                JLabel labelchinese = new JLabel(lesson1);
                labelchinese.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelchinese.setBounds(120, 100, 500, 50);
                panel.add(labelchinese);

                JLabel labelmath = new JLabel(lesson2);
                labelmath.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelmath.setBounds(120, 150, 500, 50);
                panel.add(labelmath);

                JLabel labelenglish = new JLabel(lesson3);
                labelenglish.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelenglish.setBounds(120, 200, 500, 50);
                panel.add(labelenglish);

                JLabel labelpol = new JLabel(lesson4);
                labelpol.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelpol.setBounds(120, 250, 500, 50);
                panel.add(labelpol);

                JLabel labelsp = new JLabel(lesson5);
                labelsp.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                labelsp.setBounds(120, 300, 500, 50);
                panel.add(labelsp);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}