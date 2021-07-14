package cn.akio.lesson03;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Akio
 * @ClassName TestKeyListener
 * @Description 键盘监听
 * @Date 2021/7/13 18:37
 */
public class TestKeyListener {
    public static void main(String[] args) {
        new KeyFrame();
    }
}
class KeyFrame extends Frame {
    public KeyFrame(){
        setBounds(1,2,300,400);
        setVisible(true);
        //匿名内部类
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //获得键盘侠的键是哪一个，当前码
                int keyCode = e.getKeyCode();//直接使用静态属性VK_XXX
                System.out.println(keyCode);//当按下键盘的时候会输出对应的ASCii码
                if (keyCode == KeyEvent.VK_UP) {
                    System.out.println("你按下了方向上键");
                }
            }
        });
    }
}
