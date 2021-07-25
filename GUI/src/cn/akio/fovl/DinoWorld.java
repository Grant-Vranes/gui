package cn.akio.fovl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
public class DinoWorld extends JPanel{
    Random random = new Random();
    public static final int WIDTH = 734;
    public static final int HEIGHT = 286;

    //如下对象为窗口中所显示的对象
    private Map map = new Map();
    private Dinosaur dino = new Dinosaur();
    private SuperObject[] reverseObject = {};//所有的逆向来物集合（仙人掌、鸟、云朵）在此统称为reverseObject（逆向来物），但是需要注意的是云朵只是背景因素，不参与碰撞


    /**
     * 地图，仙人掌等所有因素移动
     */
    public void stepAction() {//每10ms走一次
        map.step();//地图移动
        //所有的逆向来物集合移动
        for (int i = 0; i < reverseObject.length; i++) {
            reverseObject[i].step();
        }
        //恐龙移动
        dino.step();
    }

    /**
     * 生成仙人掌、鸟、云朵等对象,对于三类仙人掌应该有三个仙人掌的类
     */
    public SuperObject nextOne() {
        //所以采用随机数的方法生成不同的障碍物对象
        int type = random.nextInt(100);
        if (type < 20) {
            return new CactusA();
        } else if (type < 45) {
            return new CactusB();
        } else if (type < 70){
            return new CactusC();
        } else if (type < 85){
            return new Bird();
        } else{
            return new Cloud();
        }
    }

    private int enterIndex = 0;
    private int base = random.nextInt(50)+300;
    /**
     * 逆向来物生成并加入
     */
    public void enterReverseObjectAction() {//每10ms走一次
        enterIndex++;//每10ms增1
        //为了增加游戏的真实性，随机间隔时间里产生逆向来物
        //生成200~250区间
        if (enterIndex % base == 0) {//每100*10~200*10ms之间走一次
            base = random.nextInt(50)+300;
            SuperObject obj = nextOne();
            reverseObject = Arrays.copyOf(reverseObject, reverseObject.length + 1);
            reverseObject[reverseObject.length - 1] = obj;
        }
    }

    /**
     * 删除出界的逆向来物reverseObject
     */
    public void outOfBoundsAction(){//每10ms走一次
        //删除越界的逆向来物
        for (int i = 0; i < reverseObject.length; i++) {
            if (reverseObject[i].isOutOfBounds()){
                reverseObject[i] = reverseObject[reverseObject.length-1];//将越界的元素用最后一个元素顶替
                reverseObject = Arrays.copyOf(reverseObject,reverseObject.length-1);//然后缩容
            }
        }

    }

    /**
     * 启动程序的执行
     */
    public void action() {
        //键盘监听--------------------------------------------
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();//获取键盘按下了什么
                if (keyCode == KeyEvent.VK_SPACE){//如果按下的是空格键盘
                    System.out.println("小恐龙跳起来了");
//                    //针对跳跃应该也有一个定时器,定时刷新小恐龙的跳跃轨迹，这样就能做到一点动画效果，但是仔细想了想，使用定时器，只要我按了空格，就会一直执行，不会停啊
                    dino.jump();//这个方法就将RUN改为了JUMP
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        };
        //获得焦点和键盘事件
        this.setFocusable(true);//获取焦点事件
        this.addKeyListener(keyAdapter);

        //定时器----------------------------------------------
        Timer timer = new Timer();//定时器对象
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
                enterReverseObjectAction();//逆向来物加入
                outOfBoundsAction();//删除越界逆向来物
//                System.out.println(cactus.length);
                repaint();//重画（重新调用paint方法）
            }
        }, intervel, intervel);
    }

    /**
     * 重写paint()  g：画笔
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(map.getImage(0), map.x, map.y, null);
        g.drawImage(map.getImage(1), map.getX1(), map.y, null);
        g.drawImage(dino.getImage(), dino.x, dino.y, null);
        //遍历逆向来物
        for (int i = 0; i < reverseObject.length; i++) {
            SuperObject s = reverseObject[i];
            g.drawImage(s.getImage(), s.x, s.y, null);
        }
    }

}
