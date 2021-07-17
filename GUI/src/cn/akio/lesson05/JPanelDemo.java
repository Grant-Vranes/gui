package cn.akio.lesson05;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName JPanelDemo
 * @Description 面板
 * @Date 2021/7/14 10:05
 */
public class JPanelDemo extends JFrame {
    public JPanelDemo(){
        Container container = this.getContentPane();

        container.setLayout(new GridLayout(2,2,10,10));//最后的两个参数是间距

        JPanel panel1 = new JPanel(new GridLayout(1,3));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        JPanel panel3 = new JPanel(new GridLayout(2,1));
        JPanel panel4 = new JPanel(new GridLayout(3,2));

        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));
        panel1.add(new JButton("1"));

        panel2.add(new JButton("2"));
        panel2.add(new JButton("2"));

        panel3.add(new JButton("3"));
        panel3.add(new JButton("3"));

        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));
        panel4.add(new JButton("4"));

        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);

        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JPanelDemo();
    }
}
