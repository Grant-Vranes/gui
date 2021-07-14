package cn.akio.lesson04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Akio
 * @ClassName DialogDemo
 * @Description 弹窗
 * @Date 2021/7/13 19:26
 */
public class DialogDemo extends JFrame {
    public DialogDemo() {
        this.setVisible(true);
        this.setSize(700, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //JFrame    放东西，容器
        Container container = this.getContentPane();
        //绝对布局（即没有布局），就是把所有组件的位置全部写死
        container.setLayout(null);

        //按钮
        JButton button = new JButton("点击弹出对话框");//创建
        button.setBounds(30, 30, 200, 50);

        //点击这个按钮的时候，弹出一个弹窗，匿名内部类
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹窗
                new MyDialogDemo();
            }
        });
        container.add(button);
    }

    public static void main(String[] args) {
        new DialogDemo();
    }
}

//弹窗的窗口
class MyDialogDemo extends JDialog {
    public MyDialogDemo() {
        this.setVisible(true);
        this.setBounds(100, 100, 500, 500);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(null);

        container.add(new Label("欢迎来到西部世界"));
    }
}

