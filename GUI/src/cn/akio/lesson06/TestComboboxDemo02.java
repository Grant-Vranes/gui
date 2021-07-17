package cn.akio.lesson06;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author Akio
 * @ClassName TestComboboxDemo01
 * @Description TODO
 * @Date 2021/7/15 18:57
 */
public class TestComboboxDemo02 extends JFrame {
    public TestComboboxDemo02(){
        Container container = this.getContentPane();

        //生成列表的内容
        //String[] contents = {"1","2","3"};//静态写死
        Vector contents = new Vector();//动态添加
                                                                                                                                    
        //列表中需要放入内容
        JList jList = new JList(contents);

        contents.add("1");
        contents.add("12");
        contents.add("123");

        container.add(jList);

        this.setVisible(true);
        this.setSize(500,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestComboboxDemo02();
    }
}
