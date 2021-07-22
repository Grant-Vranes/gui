package cn.akio.fovl;

import java.awt.image.BufferedImage;

/**
 * @author Akio
 * @ClassName Cloud
 * @Description TODO
 * @Date 2021/7/19 11:18
 */
public class Cloud extends SuperObject {
    private int speed;//移动速度

    public Cloud() {
        super(41, 11);
        this.speed = 1;
    }

    @Override
    public void step() {

    }

    @Override
    public BufferedImage getImage() {
        return Images.cloud;
    }
}
