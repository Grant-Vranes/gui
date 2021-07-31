package cn.akio.fovl;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Akio
 * @ClassName Cactus
 * @Description 仙人掌
 * @Date 2021/7/19 11:18
 */
public class CactusB extends SuperObject implements Accelerate,GetScore{
    private int speed;//移动速度

    public CactusB() {
        super(21,39);
        this.speed = SPEED;
    }

    @Override
    public void step() {
        x -= speed;
    }

    @Override
    public BufferedImage getImage() {
        return Images.cactus[1];
    }

    /**
     * 成功躲过仙人掌B得8分
     * @return
     */
    @Override
    public int getScore() {
        return 8;
    }
}
