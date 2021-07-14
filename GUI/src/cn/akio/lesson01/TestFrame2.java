package cn.akio.lesson01;

import java.awt.*;

public class TestFrame2 {
    public static void main(String[] args) {
        //展示多个窗口
        new MyFrame(100, 100, 400, 200, Color.blue);
        new MyFrame(300, 100, 400, 200, Color.yellow);
        new MyFrame(100, 300, 400, 200, Color.red);
        new MyFrame(300, 300, 400, 200, Color.magenta);
    }
}
class MyFrame extends Frame{
    static int id = 0;//可能存在多个窗口，我们需要一个计数器
    public MyFrame(int x, int y, int width, int height, Color color){
        super("MyFrame"+(++id));//title:窗口标签,调用父类构造
        setBackground(color);
        setBounds(x,y,width,height);
        setVisible(true);
    }
}
