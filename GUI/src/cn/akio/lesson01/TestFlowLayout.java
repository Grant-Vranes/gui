package cn.akio.lesson01;

import java.awt.*;
//流式布局
public class TestFlowLayout {
    public static void main(String[] args) {
        Frame frame = new Frame();

        //组件-按钮
        Button button1 = new Button("buttom1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");

        //设置为流式布局
        //frame.setLayout(new FlowLayout());//默认居中
        //frame.setLayout(new FlowLayout(FlowLayout.LEFT));//居左
        frame.setLayout(new FlowLayout(FlowLayout.RIGHT));


        frame.setSize(500,500);

        //把按钮添加上去
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        frame.setVisible(true);
    }
}
