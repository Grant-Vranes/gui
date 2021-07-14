package cn.akio.lesson03;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Akio
 * @ClassName TestMouseListener
 * @Description 鼠标监听事件
 * @Date 2021/7/12 18:39
 */
public class TestMouseListener {
    public static void main(String[] args) {
        new MyFrame("画图");
    }
}

//自己的类
class MyFrame extends Frame {
    //画画需要画笔，需要监听鼠标当前的位置，需要集合来存储这个点
    ArrayList points;

    public MyFrame(String title) {
        super(title);
        setBounds(200, 200, 400, 300);
        setVisible(true);
        //存鼠标点击的点
        points = new ArrayList<>();

        //鼠标监听器，正对这个窗口
        this.addMouseListener(new MyMouseListener());
    }

    @Override
    public void paint(Graphics g) {
        //画画，监听鼠标的事件
        Iterator iterator = points.iterator();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            g.setColor(Color.BLUE);
            g.fillOval(point.x, point.y, 10, 10);//一个实心点
        }
    }

    //添加一个点到界面上
    public void addPaint(Point point){
        points.add(point);
    }

    //内部类，如下这么写就必须要实现其接口中所有方法，所以我们采用适配器
//    private class MyMouseListener implements MouseListener{}
    private class MyMouseListener extends MouseAdapter {
        //鼠标    按下、弹起、按住不放
        @Override
        public void mousePressed(MouseEvent e) {
            MyFrame myFrame = (MyFrame) e.getSource();
            //这个我们点击的时候，就会在界面上产生一个点！这时候就要使用画笔
            //这个点就是鼠标的点
            myFrame.addPaint(new Point(e.getX(), e.getY()));

            //每次点击鼠标都需要重新画一遍
            myFrame.repaint();//刷新
        }
    }
}

