package cn.akio.fovl;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName StartGame
 * @Description 游戏启动类
 * @Date 2021/7/20 20:00
 */
public class StartGame {
    public static void main(String[] args) {
        new DoHI();//为了调用构造方法
        JFrame frame = new JFrame();
        frame.setTitle("侏罗纪来袭");
        DinoWorld dinoWorld = new DinoWorld();
        dinoWorld.action();

        frame.setSize(DinoWorld.WIDTH,DinoWorld.HEIGHT+30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//设置居于屏幕中央
//        frame.setResizable(false);

        //游戏居于面板上
        frame.add(dinoWorld);
//        frame.addKeyListener(dinoWorld);

        frame.setVisible(true);
//        frame.pack();

    }
}
