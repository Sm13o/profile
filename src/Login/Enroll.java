package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/*
               管理员通过菜单条按钮登录界面
 */
public class Enroll {
   
    
    public void infoA() throws Exception {


        JFrame jf = new JFrame("管理员登录页面");
        //创建水平排布
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        jf.setLayout(fl);


        JLabel jl = new JLabel("管理登录密码");
        jf.add(jl);
        JTextField jt = new JTextField(10);
        Dimension d = new Dimension(50,30);
        jt.setPreferredSize(d);
        jf.add(jt);



        JButton jb = new JButton(new AbstractAction("登录") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //插入数据库sql语句
                try {
                    String url="jdbc:mysql:///test";
                    String user="root";
                    String password="itheima";
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String x= jt.getText();
                    boolean is =true;
                    String sql ="SELECT * FROM TEACHER";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String p = rs.getString("password");
                        if (p.equals(x)){
                           JOptionPane.showMessageDialog(jt,"管理员登陆成功","登录情况",JOptionPane.PLAIN_MESSAGE);
                           is=false;
                           new LibTable().info();
                           break;
                        }else if (is){
                            JOptionPane.showMessageDialog(jt,"管理员登陆失败","登录情况",JOptionPane.WARNING_MESSAGE);
                        }

                    }
                    conn.close();
                    rs.close();
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });

        jf.setPreferredSize(new Dimension(100,100));
        jf.add(jb);
        jf.pack();
        jf.setVisible(true);


    }
    public void infoR() throws Exception {

        JFrame jf1 = new JFrame("用户登陆界面");
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);

        // 实例化流式布局类的对象
        jf1.setLayout(fl);

        //ID号
        JLabel jl1 = new JLabel("ID号");
        jf1.add(jl1);
        JTextField jt1 = new JTextField(14);
        Dimension dim1 = new Dimension(30,30);
        jt1.setPreferredSize(dim1);
        jf1.add(jt1);

        //用户名
        JLabel jl2 = new JLabel("用户名");
        jf1.add(jl2);
        JTextField jt2 = new JTextField(6);
        jt2.setPreferredSize(dim1);
        jf1.add(jt2);


        //底部按钮
        JPanel jp = new JPanel();
        JButton jb1 = new JButton(new AbstractAction("登录") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //创建操作数据库对象
                try {
                    String url="jdbc:mysql:///test";
                    String user="root";
                    String password="itheima";
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String sql ="SELECT * FROM students";
                    String x = jt1.getText();
                    String y = jt2.getText();
                    Boolean is = true;
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                        String num = rs.getString("num");
                        String name = rs.getString("name");
                        if (num.equals(x) && name.equals(y)) {
                            JOptionPane.showMessageDialog(jf1, "用户登陆成功", "登录情况", JOptionPane.PLAIN_MESSAGE);
                            is=false;
                            new ReaderQuery().info();
                            break;
                        }
                    }if (is) {
                        JOptionPane.showMessageDialog(jf1, "用户登陆失败", "登录情况", JOptionPane.WARNING_MESSAGE);
                    }
                    conn.close();
                    rs.close();
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        JButton jb2 = new JButton(new AbstractAction("注册") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String url="jdbc:mysql:///test";
                    String user="root";
                    String password="itheima";
                    Connection conn = DriverManager.getConnection(url, user, password);
                    String sql ="INSERT INTO students (num,name ) VALUES (?,?)";
                    String x = jt1.getText();
                    String y = jt2.getText();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1,x);
                    ps.setString(2,y);
                    int count = ps.executeUpdate();
                    if (count>0){
                        JOptionPane.showMessageDialog(jf1,"用户注册成功","登录情况",JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(jf1,"用户注册失败","登录情况",JOptionPane.WARNING_MESSAGE);
                    }
                    conn.close();
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });


        jf1.pack();
        jp.add(jb1);
        jp.add(jb2);


        //添加各组件
        jf1.setPreferredSize(new Dimension(200,700));
        jf1.add(jp,BorderLayout.WEST);
        jf1.setVisible(true);


    }

}
