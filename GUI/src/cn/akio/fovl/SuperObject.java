package cn.akio.fovl;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Akio
 * @ClassName SuperObject
 * @Description 超类3
 * @Date 2021/7/19 11:15
 */
public abstract class SuperObject {
    protected int width;
    protected int height;
    protected int x;//x坐标
    protected int y;//y坐标

    /**
     *  给Cactus、Cloud、Bird提供的构造方法
     * @param width
     * @param height
     */
    public SuperObject(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = DinoWorld.WIDTH;
        this.y = DinoWorld.HEIGHT-this.height-32;
    }

    /**
     * 给Map、Dinosaur提供的构造方法
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
//        this.x = DinoWorld.WIDTH + this.width;//界面之外
//        this.y = DinoWorld.HEIGHT - this.height;//猜想位置
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
}
