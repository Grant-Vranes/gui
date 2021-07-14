package cn.akio.lesson01;

import java.awt.*;

public class TestWork {
    public static void main(String[] args) {
        //总
        Frame frame = new Frame("TestWork");
        frame.setSize(400, 400);
        frame.setLocation(300, 400);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 1));//上下两行一列的大布局

        //4个面板
        Panel p1 = new Panel(new BorderLayout());
        Panel p2 = new Panel(new GridLayout(2, 1));
        Panel p3 = new Panel(new BorderLayout());
        Panel p4 = new Panel(new GridLayout(2, 2));

        //上半部分
        p1.add(new Button("East-1"), BorderLayout.EAST);
        p1.add(new Button("West-1"), BorderLayout.WEST);
        p2.add(new Button("p2-btn-1"));
        p2.add(new Button("p2-btn-2"));
        p1.add(p2, BorderLayout.CENTER);

        //下半部分
        p3.add(new Button("East-2"), BorderLayout.EAST);
        p3.add(new Button("West-2"), BorderLayout.WEST);
        //下部分中间四个
        for (int i = 0; i < 4; i++) {
            p4.add(new Button("for-" + i));
        }
        p3.add(p4, BorderLayout.CENTER);

        //面板放到界面
        frame.add(p1);
        frame.add(p3);
    }
}
