package cn.akio.lesson06;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName TestComboboxDemo01
 * @Description TODO
 * @Date 2021/7/15 18:57
 */
public class TestComboboxDemo01 extends JFrame {
    public TestComboboxDemo01(){
        Container container = this.getContentPane();

        JComboBox status = new JComboBox();

        status.addItem(null);
        status.addItem("正在热映");
        status.addItem("已下架");
        status.addItem("即将上映");

        //如何获取这个值呢？可以使用监听器
        //status.addActionListener();

        container.add(status);//正常情况下我们应该是把下拉框放在panel面板中

        this.setVisible(true);
        this.setSize(500,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestComboboxDemo01();
    }
}
