package cn.akio.fovl;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Akio
 * @ClassName SuperObject
 * @Description 超类，所有元素继承此类
 * @Date 2021/7/19 11:15
 */
public abstract class SuperObject {
    protected int width;
    protected int height;
    protected int x;//x坐标
    protected int y;//y坐标
    Random random = new Random();

    /**
     * 给Cactus、Cloud、Bird逆向来物提供的构造方法
     *
     * @param width
     * @param height
     */
    public SuperObject(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = DinoWorld.WIDTH;
        if (this instanceof Accelerate) {//如果是仙人掌
            this.y = DinoWorld.HEIGHT - this.height - 32;
        }
        if (this instanceof Bird) {//如果是鸟
            this.y = DinoWorld.HEIGHT - 4 * this.height - 32;
        }
        if (this instanceof Cloud) {//如果是云
            this.y = random.nextInt(DinoWorld.HEIGHT / 2);
        }
    }

    /**
     * 给Map、Dinosaur提供的构造方法
     *
     * @param width
     * @param height
     * @param x
     * @param y
     */
    public SuperObject(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    /**
     * 移动
     */
    public abstract void step();

    /**
     * 获取对象的图片
     *
     * @return
     */
    public abstract BufferedImage getImage();

    /**
     * 判断是否越界
     */
    public boolean isOutOfBounds() {
        return x <= -this.width;
    }

    /**
     * 检测碰撞 this:逆向来物 other:恐龙
     */
    public boolean isHit(Dinosaur dinosaur) {
        int x1 = Dinosaur.DINOX - this.width;
        int x2 = Dinosaur.DINOX + dinosaur.width;
        int y1 = Dinosaur.DINOY - this.height;
        int y2 = Dinosaur.DINOY;
        int x = this.x;//逆向来物的x
        int y = dinosaur.y;//小恐龙的y
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }
}
