package cn.akio.lesson01;

import java.awt.*;

//GUI的第一个界面
public class TestFrame {
    public static void main(String[] args) {
        //设置Frame对象
        Frame frame = new Frame("我的第一个java图形界面窗口");

        //需要设置可见性
        frame.setVisible(true);

        //设置窗口大小
        frame.setSize(400,400);

        //设置背景颜色
        frame.setBackground(new Color(71, 69, 234));

        //窗口弹出的初始位置，即为窗口左上角的所在位置
        frame.setLocation(200,200);

        //设置大小固定，即窗口不能拉伸和缩小
        frame.setResizable(false);
    }
}
