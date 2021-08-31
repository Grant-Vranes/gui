package cn.akio.fovl;

import java.awt.image.BufferedImage;

/**
 * @author Akio
 * @ClassName Dinosaur
 * @Description 恐龙，只有弹跳动作，空格控制
 * @Date 2021/7/19 8:41
 */
public class Dinosaur extends SuperObject {
    public static final int dinoWidth = 40;//恐龙的宽
    public static final int dinoHeight = 43;//恐龙的高
    public static final int DINOX = 30;//恐龙静态位置x坐标
    public static final int DINOY = 208;//恐龙静态位置y坐标
    public static final int RUN = 0;//奔跑状态
    public static final int JUMP = 1;//跳跃状态
    public static final int DEAD = 2;//死亡状态
    private int state = RUN;//当前为默认状态
    private int dinoJumpHigh = 170;//跳跃高度
    public static int life = 3;//霸王龙之体，可以复活三次

    public Dinosaur() {
        super(dinoWidth, dinoHeight, DINOX, DINOY);
    }

    //跳跃增量
    private int dinoJumpValue = 5;
    /**
     * 重写移动方法
     */
    @Override
    public void step() {
        if (isJump()){
           this.y -= dinoJumpValue;
           if (this.y <= DINOY+dinoHeight-dinoJumpHigh || this.y >= DINOY){//如果触及边界
               dinoJumpValue *= -1;//则切换方向（正负相转换）
               if (this.y >= DINOY){
                   jump();//调整会RUN状态
               }
           }
        }
    }

    /**
     * 判断是否是奔跑状态，即游戏运行状态
     * @return
     */
    public boolean isRun() {
        return state == RUN;
    }

    /**
     * 判断是否跳跃状态
     * @return
     */
    public boolean isJump(){
        return state == JUMP;
    }

    /**
     * 判断是否失败状态
     */
    public boolean isDead(){
        return state == DEAD;
    }

    /**
     * 切换跳跃和奔跑状态状态
     */
    public void jump(){
        if (isRun()){
            state = JUMP;
        }else if(isJump()){
            state = RUN;
        }
    }

    /**
     * 小恐龙失败
     */
    public void goDead(){
        state = DEAD;
    }

    private int index = 0;
    /**
     * 重写获取图片的方法
     *
     * @return
     */
    @Override
    public BufferedImage getImage() {
        if (isRun()) {
            BufferedImage img = Images.dinosaurs[index++ % (Images.dinosaurs.length-1)];
            return img;
        }else if (isJump()){
            return Images.dinosaurs[2];
        }else {
            return Images.dinosaurs[3];
        }
    }

    /**
     * 获取复活次数
     * @return
     */
    public int getLife() {
        return life;
    }

    /**
     * 修改复活次数
     * @param life
     */
    public void setLife(int life) {
        this.life += life;
    }
}
