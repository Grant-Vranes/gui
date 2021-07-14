package cn.akio.lesson03;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Akio
 * @ClassName TestWindow
 * @Description TODO
 * @Date 2021/7/12 19:45
 */
public class TestWindow {
    public static void main(String[] args) {
        new WindowFrame();
    }
}

class WindowFrame extends Frame {
    public WindowFrame(){
        setBackground(Color.blue);
        setBounds(100,100,200,200);
        setVisible(true);
        this.addWindowListener(
                //匿名内部类
                new WindowAdapter() {
                    //关闭窗口
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("windowClosing");
                        System.exit(0);
                    }
                    //激活窗口
                    @Override
                    public void windowActivated(WindowEvent e) {
                        WindowFrame sourse = (WindowFrame) e.getSource();
                        sourse.setTitle("窗口被激活了");
                        System.out.println("windowActivated");
                    }
                }
        );
    }
}
