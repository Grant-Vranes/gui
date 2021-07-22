package cn.akio.fovl;

import javax.swing.*;

/**
 * @author Akio
 * @ClassName StartGame
 * @Description TODO
 * @Date 2021/7/20 20:00
 */
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(DinoWorld.WIDTH,DinoWorld.HEIGHT+30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//设置居于屏幕中央
//        frame.setResizable(false);


        //游戏居于面板上
        DinoWorld dinoWorld = new DinoWorld();
        frame.add(dinoWorld);

        frame.setVisible(true);
//        frame.pack();
        dinoWorld.action();
    }
}
