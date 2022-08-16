package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibAddDEL {

   public void info(){
        JFrame jf = new JFrame("图书管理窗口");
        FlowLayout fl = new FlowLayout(FlowLayout.LEADING, 10, 10);
        jf.setLayout(fl);

        JLabel jl1 = new JLabel("书籍编号");
    jf.add(jl1);
        JTextField jt1 = new JTextField(10);
        Dimension di = new Dimension(30,30);
        jt1.setPreferredSize(di);
        jf.add(jt1);

        JLabel jl2 =new JLabel("书籍分类");
    jf.add(jl2);
        JTextField jt2 = new JTextField(10);
        jt2.setPreferredSize(di);
    jf.add(jt2);

        JLabel jl3 = new JLabel("书籍名称");
    jf.add(jl3);
        JTextField jt3 = new JTextField(10);
        jt3.setPreferredSize(di);
    jf.add(jt3);

        JLabel jl4 =new JLabel("书籍作者");
    jf.add(jl4);
        JTextField jt4 = new JTextField(10);
        jt4.setPreferredSize(di);
    jf.add(jt4);

        JLabel jl5 = new JLabel("出版年份");
    jf.add(jl5);
        JTextField jt5 = new JTextField(10);
        jt5.setPreferredSize(di);
    jf.add(jt5);

        JLabel jl6 =new JLabel("剩余数量");
    jf.add(jl6);
        JTextField jt6 = new JTextField(10);
        jt6.setPreferredSize(di);
    jf.add(jt6);

        JLabel jl7 = new JLabel("被借数量");
    jf.add(jl7);
        JTextField jt7 = new JTextField(10);
        jt7.setPreferredSize(di);
    jf.add(jt7);

        JLabel jl8 =new JLabel("能否出借");
    jf.add(jl8);
        JTextField jt8 = new JTextField(10);
        jt8.setPreferredSize(di);
    jf.add(jt8);

        JButton jb1 =new JButton(new AbstractAction("添加图书") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url ="jdbc:mysql:///test";
                String user ="root";
                String password ="itheima";
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String number =jt1.getText();
                    String classify =jt2.getText();
                    String name =jt3.getText();
                    String writer =jt4.getText();
                    String day=jt5.getText();
                    String num =jt6.getText();
                    String lend =jt7.getText();
                    String state =jt8.getText();
                    String sql ="INSERT INTO lib ( number,classify, name, writer, day, num, lend, state) VALUES (?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,number);
                    ps.setString(2,classify);
                    ps.setString(3,name);
                    ps.setString(4,writer);
                    ps.setString(5,day);
                    ps.setString(6,num);
                    ps.setString(7,lend);
                    ps.setString(8,state);
                    int count = ps.executeUpdate();
                    if (count>0){
                        JOptionPane.showMessageDialog(jf,"添加成功","添加窗口",JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(jf,"添加失败","添加窗口",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        JButton jb2 = new JButton(new AbstractAction("删除图书") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url ="jdbc:mysql:///test";
                String user ="root";
                String password ="itheima";
                try {
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String name =jt3.getText();
                    String sql ="DELETE FROM LIB WHERE NAME=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,name);
                    int count = ps.executeUpdate();
                    if (count>0){
                        JOptionPane.showMessageDialog(jf,"删除成功","添加窗口",JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(jf,"删除失败","添加窗口",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });
        JPanel jp = new JPanel();
        jp.add(jb1);
        jp.add(jb2);
        jf.pack();
        jf.add(jp);
        jf.setBounds(100,300,100,100);
        jf.setVisible(true);

    }
}
