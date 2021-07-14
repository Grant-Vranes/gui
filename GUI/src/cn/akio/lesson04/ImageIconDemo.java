package cn.akio.lesson04;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Akio
 * @ClassName ImageIconDemo
 * @Description 图片标签
 * @Date 2021/7/14 8:51
 */
public class ImageIconDemo extends JFrame {
    public ImageIconDemo(){
        //获取图片的地址
        JLabel label = new JLabel("ImageIcon");
        //通过这个类，获取当前类路径下的东西
        URL url = ImageIconDemo.class.getResource("img.png");

        ImageIcon imageIcon = new ImageIcon(url);
        label.setIcon(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);//水平对齐

        Container container = getContentPane();
        container.add(label);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100,200,200);
    }

    public static void main(String[] args) {
        new ImageIconDemo();
    }
}
