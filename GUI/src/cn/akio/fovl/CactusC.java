package cn.akio.fovl;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Akio
 * @ClassName Cactus
 * @Description 仙人掌
 * @Date 2021/7/19 11:18
 */
public class CactusC extends SuperObject implements Accelerate{
    private int speed;//移动速度

    public CactusC() {
        super(26,48);
        this.speed = SPEED;
    }

    @Override
    public void step() {
        x -= speed;
    }

    @Override
    public BufferedImage getImage() {
        return Images.cactus[2];
    }
}
