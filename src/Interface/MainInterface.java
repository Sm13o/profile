package Interface;







import Login.Enroll;
import Login.LibAddDEL;
import Login.LibTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/*

                         主界面按钮和风格设计
 */

public class MainInterface extends JFrame{

    //主窗口
    JFrame jf = new JFrame("图书管理系统主界面");
    //定义界面风格
    static {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //顶部菜单条
    MenuBar top = new MenuBar();
    //菜单条按钮
    Menu topSon1 = new Menu("基本数据维护");
    Menu topSon2 = new Menu("关于我们");
    MenuItem item1 = new MenuItem("图书类别管理");
    MenuItem item2 = new MenuItem("图书管理");


    //创建一个底部按钮的容器
    JPanel jp = new JPanel();
    //底部按钮的相应事件
    JButton jb1 = new JButton(new AbstractAction("图书租借") {
        @Override
        public void actionPerformed(ActionEvent e) {
       //调用EnrollReaders类方法
            try {
                new Enroll().infoR();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    });
    JButton jb2 = new JButton(new AbstractAction("图书归还") {
        @Override
        public void actionPerformed(ActionEvent e) {
        //调用EnrollReaders类方法
            try {
                new Enroll().infoR();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    });
    JButton jb3 = new JButton(new AbstractAction("图书查询") {
        @Override
        public void actionPerformed(ActionEvent e) {
          new LibTable().info();
        }
    });

    public void info(){

        //top菜单条两个菜单项组件事件
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //调用EnrollAdministrators类的方法
                try {
                    new Enroll().infoA();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //调用EnrollAdministrators类的方法
                try {
                    new LibAddDEL().info();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        //传入主界面图片
        String picPath="D:"+File.separator+"英雄时刻\\GL.jpg";
        Icon icon =new ImageIcon(picPath);
        JLabel jLabel = new JLabel(icon,JLabel.CENTER);


        //装载底部按钮
        topSon1.add(item1);
        topSon1.add(item2);
        top.add(topSon1);
        top.add(topSon2);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);

        jLabel.setBackground(Color.YELLOW);
        jf.add(jLabel);
        jf.setSize(1080,1080);    //初始窗口大小
        jf.setBackground(Color.WHITE);
        jf.setLocation(200, 200);      //初始化位置
        jf.setMenuBar(top);
        jf.add(jp,BorderLayout.SOUTH);
        jf.pack();
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new MainInterface().info();

    }
}






