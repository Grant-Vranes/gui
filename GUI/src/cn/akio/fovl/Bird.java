package cn.akio.fovl;

import java.awt.image.BufferedImage;

/**
 * @author Akio
 * @ClassName bird
 * @Description 翼龙障碍物
 * @Date 2021/7/19 11:18
 */
public class Bird extends SuperObject{
    private int speed;//移动速度
    public Bird(){
        super(38,23);
        this.speed = 1;
    }


    @Override
    public void step() {

    }

    private int index = 0;
    @Override
    public BufferedImage getImage() {
        return Images.birds[index++%Images.birds.length];
    }
}
