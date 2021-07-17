package cn.akio.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author Akio
 * @ClassName GamePanel
 * @Description 游戏的面板
 * @Date 2021/7/15 19:43
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //定义贪吃蛇的数据结构
    private int length;//蛇的长度
    private int[] snakeX = new int[600];//贪吃蛇的x坐标（600的单位其实是像素） 25*25,贪吃蛇的头和身体的长款都是25
    private int[] snakeY = new int[500];//贪吃蛇的y坐标 25*25
    private String initDir;//初始化方向

    //食物的坐标
    private int foodX;
    private int foodY;
    Random random = new Random();

    int score;//成绩

    //游戏当前状态：start，stop
    private boolean isStart = false;//默认是停止状态
    //游戏是否失败的判定
    private boolean isFail = false;//默认是不失败

    //定时器
    Timer timer = new Timer(100, this);//100ms执行一次

    //构造器
    public GamePanel() {
        init();
        //获得焦点和键盘事件
        this.setFocusable(true);//获取焦点事件
        this.addKeyListener(this);//获得键盘监听事件

        timer.start();//游戏一开始定时器就启动
    }

    //初始化方法
    public void init() {
        length = 3;
        snakeX[0] = 100;//脑袋的坐标
        snakeY[0] = 100;
        snakeX[1] = 75;//第一个身体的坐标
        snakeY[1] = 100;
        snakeX[2] = 50;//第二个身体的坐标
        snakeY[2] = 100;
        initDir = "R";//初始化方向向右

        //食物坐标，这样食物就能随机分布在地图上
        foodX = 25 + 25 * random.nextInt(850 / 25);
        foodY = 75 + 25 * random.nextInt(600 / 25);

        score = 0;
    }

    //绘制面板，我们游戏中的所有东西，都使用这个画笔来画
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        //绘制静态的面板
        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this, g, 25, 11);//头部广告/标题栏
        g.fillRect(25, 75, 850, 600);//默认游戏界面

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("楷体",Font.BOLD,18));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数:"+score,750,50);

        //把贪吃蛇画上去
        //head，需要判断方向
        if (initDir.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (initDir.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (initDir.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (initDir.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //body
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        //游戏状态
        if (isStart == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 20));//设置字体
            g.drawString("press the space to start the game", 280, 300);
        }

        //是否失败状态
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("宋体", Font.BOLD, 20));//设置字体
            g.drawString("you Failed, press the space to try again", 260, 300);
        }
    }

    /**
     * 键盘监听事件
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//获取键盘按下了什么
        if (keyCode == KeyEvent.VK_SPACE) {//如何按下的是空格键
            if (isFail) {
                //重新开始
                isFail = false;
                init();
            } else {
                isStart = !isStart;//取反
            }
            repaint();//重新绘制
        }
        //贪吃蛇移动
        if (keyCode == KeyEvent.VK_UP) {
            initDir = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            initDir = "D";
        } else if (keyCode == KeyEvent.VK_LEFT) {
            initDir = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            initDir = "R";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * 事件监听器
     * 事件的监听---需要通过固定事件来判断，1s=10次
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail == false) {//如果游戏是开始状态并且不是失败状态，就让贪吃蛇动起来
            //吃食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {//当贪吃蛇的头部与食物重合
                length++;//长度+1
                score += 10;//分数+10
                //然后再次随机产生食物
                foodX = 25 + 25 * random.nextInt(850 / 25);
                foodY = 75 + 25 * random.nextInt(600 / 25);
            }

            //移动
            for (int i = length - 1; i > 0; i--) {//后一节移到前一节的位置 snakeX[1] = snakeX[0]
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //走向
            if (initDir.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                //边界判断,如果触及边界，就重新从左边出来
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (initDir.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                //边界判断,如果触及边界，就重新从右边出来
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if (initDir.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                //边界判断,如果触及边界，就重新从下边出来
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (initDir.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                //边界判断,如果触及边界，就重新从上边出来
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            //失败的判断，撞到自己就算失败
            //遍历身体所有的坐标。如果头的坐标与身体坐标重合就是撞到了
            for (int i = 1; i < length; i++) {
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    isFail = true;
                }
            }

            repaint();//重画页面
        }
        timer.start();//定时器开启
    }
}