package cn.akio.lesson04;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName JFrameDemo
 * @Description 标签居中
 * @Date 2021/7/13 18:52
 */
public class JFrameDemo2 {
    public static void main(String[] args) {
        new MyJframe2().init();
    }
}

class MyJframe2 extends JFrame {
    public void init() {
        this.setBounds(10, 10, 200, 300);
        this.setVisible(true);

        JLabel label = new JLabel("welcome to my hometown");
        this.add(label);

        //让文本标签居中   设置水平对齐
        label.setHorizontalAlignment(SwingConstants.CENTER);

        //获得当前容器
        Container container = this.getContentPane();
        container.setBackground(Color.YELLOW);//给这个容器重新着色
    }
}
