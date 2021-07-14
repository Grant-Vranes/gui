package cn.akio.lesson01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//Panel 可以看成是一个空间，但是不能单独存在
public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame();

        //布局的概念
        Panel panel = new Panel();

        //设置布局
        frame.setLayout(null);

        //坐标
        frame.setBounds(300, 300, 500, 500);
        frame.setBackground(new Color(40, 161, 35));

        //panel设置坐标，相对于frame
        panel.setBounds(50, 50, 400, 400);
        panel.setBackground(new Color(193,15,60));

        //将面板布局到界面
        frame.add(panel);

        //设置可见性
        frame.setVisible(true);

        //监听事件，监听窗口关闭事件 System.exit(0）
        //适配器模式
        frame.addWindowListener(new WindowAdapter() {
            //窗口点击关闭的时候需要做的事情
            @Override
            public void windowClosing(WindowEvent e) {
                //要做什么，结束程序
                System.exit(0);
            }
        });
    }
}
