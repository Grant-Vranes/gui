package cn.akio.lesson02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestActionEvent2 {
    public static void main(String[] args) {
        //两个按钮，实现同一个监听
        //开始    停止
        Frame frame = new Frame("开始-停止");
        Button button1 = new Button("start");
        Button button2 = new Button("stop");

        //可以显式的定义触发会返回的命令，如果不显式的定义，则会走默认的值
        //可以多个按钮只写一个监听类
        button2.setActionCommand("button2-stop");
        MyMonitor myMonitor = new MyMonitor();//只使用了一个监听器

        button1.addActionListener(myMonitor);
        button2.addActionListener(myMonitor);

        frame.add(button1,BorderLayout.NORTH);
        frame.add(button2,BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }
}
class MyMonitor implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        //e.getActionCommand()  获得按钮的信息
        System.out.println("按钮被点击了：msg=>"+e.getActionCommand());
        if (e.getActionCommand().equals("start")){

        }
    }
}
