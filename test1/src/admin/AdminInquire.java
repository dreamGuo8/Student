package admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AdminInquire extends JFrame {
    public AdminInquire() {
        JFrame frame = new JFrame();
        frame.setTitle("管理员查询界面");
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        //getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        //返回按钮
        JButton button=new JButton("返回");
        button.setBounds(400, 400, 80, 50);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        frame.add(button);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "root";
        String password = "123456";
        String sql = "SELECT category,uname,id,password FROM 登录表 WHERE category=? OR category=? ORDER BY category DESC;";
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "教师");
            stmt.setString(2, "学生");
            ResultSet rs = stmt.executeQuery();

            // 将 ResultSet 转换为 DefaultTableModel，以便 JTable 可以显示它
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }
            Object[] columnNames = {"类别","姓名", "账号","密码"};
            model.addRow(columnNames);
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            JTable table = new JTable();
            table.setModel(model);
            table.setPreferredScrollableViewportSize(new Dimension(800, 600));
            table.setFont(new Font("", Font.PLAIN, 30));
            table.setRowHeight(40);
            frame.add(table);
            frame.setVisible(true);
            frame.getContentPane().add(panel);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
