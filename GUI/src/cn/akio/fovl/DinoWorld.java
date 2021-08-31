package cn.akio.fovl;


import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

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
    public static final int INIT = -1;//首次启动状态
    public static final int START = 0;//启动状态
    public static final int RUNNING = 1;//运行状态
    public static final int PAUSE = 2;//暂停状态
    public static final int GAME_OVER = 3;//游戏结束状态
    public static final int RESURRECT = 4;//复活中状态
    private static int state = INIT;//当前状态（默认为启动状态）


    //如下对象为窗口中所显示的对象
    private Map map = new Map();
    private static Dinosaur dino = new Dinosaur();
    private SuperObject[] reverseObject = {};//所有的逆向来物集合（仙人掌、鸟、云朵）在此统称为reverseObject（逆向来物），但是需要注意的是云朵只是背景因素，不参与碰撞

    /**
     * 给外部提供一个修改状态的方法
     * 用于复活按钮
     */
    public static void setState(){
        state = RESURRECT;
        dino.setLife(-1);
//        Thread thread = new Thread(){//启用一个线程进行倒计时，复活后三秒倒计时开始
//            @Override
//            public void run() {
//                for (int i = 3; i >= 1; i--) {
//                    try {
////                        g.drawString(i+"", 280,60);
//                        Thread.sleep(1000);
//                        System.out.println(i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                state = RUNNING;
//            }
//        };
//        thread.start();
        System.out.println(dino.life);
    }

    /**
     * 用于下辈子按钮
     */
    public static void setNextLife(){
        state = GAME_OVER;
    }


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
        if (type < 25) {
            return new CactusA();
        } else if (type < 55) {
            return new CactusB();
        } else if (type < 75){
            return new CactusC();
        } else if (type < 90){
            return new Bird();
        } else{
            return new Cloud();
        }
    }

    private int enterIndex = 0;
    private int base = 100;
    /**
     * 逆向来物生成并加入
     */
    public void enterReverseObjectAction() {//每10ms走一次
//        int base = random.nextInt(50)+300;
        enterIndex++;//每10ms增1
        //为了增加游戏的真实性，随机间隔时间里产生逆向来物
        //生成200~250区间
        if (enterIndex % base == 0) {//每100*10~200*10ms之间走一次
//            base = random.nextInt(50)+300;
            SuperObject obj = nextOne();
            reverseObject = Arrays.copyOf(reverseObject, reverseObject.length + 1);
            reverseObject[reverseObject.length - 1] = obj;
            System.out.println(enterIndex);
            base = random.nextInt(100)+50;
            enterIndex = 0;
        }
    }

    private int score = 0;
    /**
     * 删除出界的逆向来物reverseObject
     */
    public void outOfBoundsAction(){//每10ms走一次
        //删除越界的逆向来物
        for (int i = 0; i < reverseObject.length; i++) {
            if (reverseObject[i].isOutOfBounds()){
                //被过掉的因素才会得分
                if (reverseObject[i] instanceof GetScore) {//因为有些逆向来物不具备得分效果，所以需要筛选
                    GetScore ge = (GetScore)reverseObject[i];
                    score += ge.getScore();
                    //与HI.txt文件交换最高分
                    int HI = DoHI.getHI();
                    if (score > HI){
                        DoHI.setHI(score);
                    }
                }
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
            if (!(s instanceof Cloud) && !dino.isDead() && s.isHit(dino)){
                if (dino.getLife() <= 0){//如果没有复活次数
                    dino.goDead();
                    System.out.println("游戏结束");
                }else{
                    //复活弹窗
                    state = PAUSE;
                    new ContinueDialog();
                    //缩容很重要，当小恐龙撞到逆向来物，应当在暂停的同时把这个撞到的逆向来物给删除，以免解除暂停后一直撞上他了
                    reverseObject[i] = reverseObject[reverseObject.length-1];//将越界的元素用最后一个元素顶替
                    reverseObject = Arrays.copyOf(reverseObject,reverseObject.length-1);//然后缩容
                }
            }
        }
    }

    /**
     * 检测游戏结束
     */
    public void checkGameOverAction(){//每10ms走一次
        if (dino.isDead()){//如果小恐龙失败，表示游戏结束
            state = GAME_OVER;
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
                switch (state) {
                    case INIT:
                        state = START;
                        break;
                    case START:
                        state = RUNNING;
                        break;
                    case RUNNING:
                        if (keyCode == KeyEvent.VK_SPACE){//如果按下的是空格键盘
                            System.out.println("小恐龙跳起来了");
                            dino.jump();//这个方法就将RUN改为了JUMP
                        }
                        if (keyCode == KeyEvent.VK_CAPS_LOCK){//按下caps转换大小写暂停
                            System.out.println("暂停");
                            state = PAUSE;
                        }
                        break;
                    case PAUSE:
                        if (keyCode == KeyEvent.VK_CAPS_LOCK){//按下caps转换大小写暂停
                            System.out.println("解除暂停");
                            state = RUNNING;
                        }
                        break;
                    case GAME_OVER:

                        //清空游戏数据，开启下辈子
                        score = 0;
                        map = new Map();
                        dino = new Dinosaur();
                        reverseObject = new SuperObject[0];
                        System.gc();
                        state = START;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {//释放按键
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

    private int index = 1;
    private int countDown = 3;
    /**
     * 重写paint()  g：画笔
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if (state == INIT){
            g.drawImage(Images.init,95,-120,null);
        }else{
            //RUNNING状态
            g.drawImage(map.getImage(0), map.x, map.y, null);
            g.drawImage(map.getImage(1), map.getX1(), map.y, null);
            g.drawImage(dino.getImage(), dino.x, dino.y, null);
            //遍历逆向来物
            for (int i = 0; i < reverseObject.length; i++) {
                SuperObject s = reverseObject[i];
                g.drawImage(s.getImage(), s.x, s.y, null);
            }
            //调整字体和颜色
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体",Font.BOLD,16));
            g.drawString("HI:"+DoHI.getHI()+"   SCORE:"+score, 530, 30);
//            g.drawString("SCORE:"+score, 600, 30);

            switch (state) {//根据不同状态画不同的图
                case START://启动状态
                    g.drawImage(Images.start,245,60,null);
                    //调整字体和颜色
                    g.setColor(Color.gray);
                    g.setFont(new Font("宋体",Font.BOLD,18));
                    g.drawString("caps-暂停   空格-跳跃",250,150);
                    g.drawString("快速双击空格可定格飞行",250,170);
                    break;
                case PAUSE://暂停状态
                    //g.drawImage(Images.start,260,60,null);
                    g.drawImage(Images.pause,280,60,null);
                    break;
                case GAME_OVER://游戏结束状态
                    g.drawImage(Images.game_over,DinoWorld.WIDTH/3,DinoWorld.HEIGHT/3,null);
                    break;
                case RESURRECT://复活中状态
                    index++;
                    if ( index % 150 == 0){
                        index = 0;
                        g.setColor(Color.black);
                        g.setFont(new Font("黑体",Font.BOLD,100));
                        g.drawString(countDown--+"", 350, 100);
                        System.out.println(countDown);
                        if (countDown == 0){
                            countDown = 3;
                            state = RUNNING;
                        }
                    }
                    break;
            }
        }
    }
}
