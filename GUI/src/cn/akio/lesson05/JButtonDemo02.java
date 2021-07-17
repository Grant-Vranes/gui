package cn.akio.lesson05;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Akio
 * @ClassName JButtonDemo01
 * @Description TODO
 * @Date 2021/7/15 13:07
 */
public class JButtonDemo02 extends JFrame {
    public JButtonDemo02(){
        Container container = this.getContentPane();

        //将一个图片变成图标
        URL resource = JButtonDemo02.class.getResource("123.jpg");
        Icon icon = new ImageIcon(resource);

        //单选框
        JRadioButton radioButton1 = new JRadioButton("JRadioButton01");
        JRadioButton radioButton2 = new JRadioButton("JRadioButton02");
        JRadioButton radioButton3 = new JRadioButton("JRadioButton03");

        //由于单选框只能选择一个，所以我们对其进行分组，就是一个组中只能选择一个
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        container.add(radioButton1,BorderLayout.NORTH);
        container.add(radioButton2,BorderLayout.CENTER);
        container.add(radioButton3,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setSize(500,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JButtonDemo02();
    }
}
