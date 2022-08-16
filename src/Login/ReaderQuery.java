package Login;


import javafx.beans.binding.BooleanBinding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ReaderQuery {


   public void info(){



       JFrame jf1 = new JFrame("借书查询窗口");
       FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);

        // 实例化流式布局类的对象
       jf1.setLayout(fl);

       JLabel jl1 = new JLabel("书籍分类");
       jf1.add(jl1);
       JTextField jt1 = new JTextField(14);
       Dimension dim1 = new Dimension(30,30);
       jt1.setPreferredSize(dim1);
       jf1.add(jt1);

       //用户名
      JLabel jl2 = new JLabel("书名");
      jf1.add(jl2);
      JTextField jt2 = new JTextField(6);
      jt2.setPreferredSize(dim1);
      jf1.add(jt2);

      JButton jb1 = new JButton(new AbstractAction("查询") {
          @Override
          public void actionPerformed(ActionEvent e) {
              String url ="jdbc:mysql:///test";
              String user ="root";
              String password ="itheima";
              try {
                  String classify = jt1.getText();
                  String name = jt2.getText();
                  Boolean is =true;
                  Connection conn = DriverManager.getConnection(url, user, password);
                  String sql = "SELECT classify,name FROM LIB WHERE CLASSIFY=? and NAME =?;";
                  PreparedStatement ps = conn.prepareStatement(sql);
                  ps.setString(1,classify);
                  ps.setString(2,name);
                  ResultSet rs = ps.executeQuery();
                      while(rs.next()){
                          String c = rs.getString("classify");
                          String n = rs.getString("name");
                          if (classify.equals(c)&&name.equals(n)){
                               JOptionPane.showMessageDialog(jf1,"可以租借","查询窗口",JOptionPane.PLAIN_MESSAGE);
                              is=false;
                              break;
                           }
                      }if (is){
                      JOptionPane.showMessageDialog(jf1,"已空","查询窗口",JOptionPane.WARNING_MESSAGE);
                  }
                      conn.close();
                      rs.close();
                      ps.close();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }
      });
      JButton jb2 =new JButton(new AbstractAction("租借") {
          @Override
          public void actionPerformed(ActionEvent e) {

          }
      });

      JPanel jp = new JPanel();
      jp.add(jb1);
      jp.add(jb2);
      jf1.add(jp,BorderLayout.SOUTH);
      jf1.pack();
      jf1.setVisible(true);

}

}
