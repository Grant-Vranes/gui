package cn.akio.fovl;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Akio
 * @ClassName DinoWorld
 * @Description TODO
 * @Date 2021/7/19 11:12
 */
public class DinoWorld extends JPanel {
    Random random = new Random();
    public static final int WIDTH = 734;
    public static final int HEIGHT = 286;

    //如下对象为窗口中所显示的对象
    private Map map = new Map();
    private Dinosaur dino = new Dinosaur();
    private SuperObject[] cactus = {};
    private SuperObject[] birds = {};
    private SuperObject[] clouds = {};


    /**
     * 地图，仙人掌等移动
     */
    public void stepAction() {//每10ms走一次
        map.step();//地图移动
        //所有的仙人掌集合移动
        for (int i = 0; i < cactus.length; i++) {
            cactus[i].step();
        }
    }

    /**
     * 生成仙人掌对象,,应该有三个仙人掌的类
     */
    public SuperObject nextCactus() {
        //因为仙人掌障碍物有三个模型，每个模型大小不一，所以采用随机数的方法生成不同的仙人掌
        Random random = new Random();
        int type = random.nextInt(20);
        if (type < 9) {
            return new CactusA();
        } else if (type < 15) {
            return new CactusB();
        } else {
            return new CactusC();
        }

    }

    private int enterIndex = 0;

    /**
     * 仙人掌移动
     */
    public void enterCactusAction() {//每10ms走一次
        enterIndex++;//每10ms增1
        //为了增加游戏的真实性，随机间隔时间里产生仙人掌障碍物
        //生成100~200区间
        if (enterIndex % 400 == 0) {//每400（10*40）ms走一次
            SuperObject obj = nextCactus();
            cactus = Arrays.copyOf(cactus, cactus.length + 1);
            cactus[cactus.length - 1] = obj;
        }
    }

    public void action() {
        //定时器----------------------------------------------
        java.util.Timer timer = new Timer();//定时器对象
        int intervel = 10;//定时间隔（以毫秒为单位）
        /*
        使用schedule(TimerTask ?,long intervel,long intervel);
        第一个intervel表示从程序启动到第一次触发的时间间隔
        第二个intervel表示第一次触发到第二次触发的时间间隔
                     表示第二次触发到第三次触发的时间间隔
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {//定时干的事（每10ms走一次）
                //...
                stepAction();//背景图移动
                enterCactusAction();//仙人掌移动
                repaint();//重画（重新调用paint方法）
            }
        }, intervel, intervel);
    }

    /**
     * 重写paint()  g：画笔
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(map.getImage(0), map.x, map.y, null);
        g.drawImage(map.getImage(1), map.getX1(), map.y, null);
        g.drawImage(dino.getImage(), dino.x, dino.y, null);
        //遍历仙人掌
        for (int i = 0; i < cactus.length; i++) {
            SuperObject s = cactus[i];
            g.drawImage(s.getImage(), s.x, s.y, null);
        }
    }

}
