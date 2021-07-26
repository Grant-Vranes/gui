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
    public static final int START = 0;//启动状态
    public static final int RUNNING = 1;//运行状态
    public static final int PAUSE = 2;//暂停状态
    public static final int GAME_OVER = 3;//游戏结束状态
    private int state = START;//当前状态（默认为启动状态）

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
     * 恐龙撞击到逆向来物
     */
    public void dinoBangAction(){//每10ms走一次
        for (int i = 0; i < reverseObject.length; i++) {
            SuperObject s = reverseObject[i];//获取每一个逆向来物
            if (!dino.isDead() && s.isHit(dino)){
                dino.goDead();
                System.out.println("游戏结束");
            }
        }
    }

    /**
     * 检测游戏结束
     */
    public void checkGameOverAction(){//每10ms走一次
        if (dino.isDead()){//如果小恐龙失败，表示游戏结束
            state = GAME_OVER;//将游戏状态修改为结束
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
                switch (state) {
                    case START:
                        state = RUNNING;
                        break;
                    case RUNNING:
                        int keyCode = e.getKeyCode();//获取键盘按下了什么
                        if (keyCode == KeyEvent.VK_SPACE){//如果按下的是空格键盘
                            System.out.println("小恐龙跳起来了");
                            dino.jump();//这个方法就将RUN改为了JUMP
                        }
                        break;
                    case GAME_OVER:
                        //清空游戏数据，开启下辈子
                        map = new Map();
                        dino = new Dinosaur();
                        reverseObject = new SuperObject[0];
                        System.gc();
                        state = START;
                        break;
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
                if (state == RUNNING) {
                    stepAction();//背景图移动
                    enterReverseObjectAction();//逆向来物加入
                    outOfBoundsAction();//删除越界逆向来物
                    dinoBangAction();//检测碰撞
                    checkGameOverAction();//检测游戏是否结束
                }
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

        switch (state) {//根据不同状态画不同的图
            case START://启动状态

                break;
            case PAUSE://暂停状态

                break;
            case GAME_OVER://游戏结束状态
                g.drawImage(Images.game_over,DinoWorld.WIDTH/3,DinoWorld.HEIGHT/3,null);
        }
    }
}

//遗留问题：
//恐龙不撞击飞鸟的时候也会出现问题？
//关于暂停状态是需要按什么按键才能暂停还是做一个ICON？