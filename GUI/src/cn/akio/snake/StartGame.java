package cn.akio.snake;

import javax.swing.*;

/**
 * @author Akio
 * @ClassName StartGame
 * @Description 游戏主启动类
 * @Date 2021/7/15 19:39
 */
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(500,230,900,720);
        frame.setResizable(false);//窗口大小不可变
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //正常的游戏界面都在面板上
        frame.add(new GamePanel());
        frame.setVisible(true);

    }
}
