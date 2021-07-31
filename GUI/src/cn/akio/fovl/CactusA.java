package cn.akio.fovl;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Akio
 * @ClassName Cactus
 * @Description 仙人掌
 * @Date 2021/7/19 11:18
 */
public class CactusA extends SuperObject implements Accelerate,GetScore{
    private int speed;//移动速度

    public CactusA() {
        super(48, 39);
        this.speed = SPEED;
    }

    @Override
    public void step() {
        x -= speed;
    }

    @Override
    public BufferedImage getImage() {
        return Images.cactus[0];
    }

    /**
     * 成功躲过仙人掌A，得5分
     * @return
     */
    @Override
    public int getScore() {
        return 5;
    }
}
