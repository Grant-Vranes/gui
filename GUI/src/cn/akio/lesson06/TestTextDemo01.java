package cn.akio.lesson06;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName TestTextDemo01
 * @Description TODO
 * @Date 2021/7/15 19:20
 */
public class TestTextDemo01 extends JFrame {
    public TestTextDemo01(){
        Container container = this.getContentPane();

        JTextField textField = new JTextField("hello");
        JTextField textField1 = new JTextField("world",20);//20为默认字符长度

        container.add(textField,BorderLayout.NORTH);
        container.add(textField1,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setSize(500,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestTextDemo01();
    }
}
