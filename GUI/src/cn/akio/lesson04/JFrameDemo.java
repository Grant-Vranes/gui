package cn.akio.lesson04;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName JFrameDemo
 * @Description TODO
 * @Date 2021/7/13 18:52
 */
public class JFrameDemo {
    //init(); 初始化
    public void init() {
        JFrame jf = new JFrame("这是一个JFrame窗口");
        jf.setVisible(true);
        jf.setBounds(100, 100, 200, 200);

        //设置文字  JLable
        JLabel lable = new JLabel("欢迎来到地球");

        jf.add(lable);

        //关闭事件
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //建立一个窗口
        new JFrameDemo().init();
    }
}
