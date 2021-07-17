package cn.akio.lesson05;

import cn.akio.lesson04.ImageIconDemo;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Akio
 * @ClassName JButtonDemo01
 * @Description TODO
 * @Date 2021/7/15 13:07
 */
public class JButtonDemo01 extends JFrame {
    public JButtonDemo01(){
        Container container = this.getContentPane();

        //将一个图片变成图标
        URL resource = JButtonDemo01.class.getResource("123.jpg");
        Icon icon = new ImageIcon(resource);

        //把这个图标放在按钮上
        JButton button = new JButton();
        button.setIcon(icon);
        button.setToolTipText("图片按钮");//鼠标悬停时展示的文本

        container.add(button);

        this.setVisible(true);
        this.setSize(500,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JButtonDemo01();
    }
}
