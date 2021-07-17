package cn.akio.lesson05;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName JScrollDemo
 * @Description 滚动条
 * @Date 2021/7/15 12:56
 */
public class JScrollDemo extends JFrame{
    public JScrollDemo(){
        Container container = this.getContentPane();

        //文本域
        JTextArea textArea = new JTextArea(20,30);
        textArea.setText("欢迎来到90年代");

        //Scorll面板
        JScrollPane scrollPane = new JScrollPane(textArea);
        container.add(scrollPane);

        this.setVisible(true);
        this.setBounds(100,100,300,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new JScrollDemo();
    }
}
