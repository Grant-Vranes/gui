package cn.akio.fovl;

import java.awt.image.BufferedImage;

/**
 * @author Akio
 * @ClassName bird
 * @Description 翼龙障碍物
 * @Date 2021/7/19 11:18
 */
public class Bird extends SuperObject implements GetScore{
    private int speed;//移动速度
    public Bird(){
        super(38,23);
        this.speed = 3;
    }


    @Override
    public void step() {
        x -= speed;
    }

    private int index = 0;
    @Override
    public BufferedImage getImage() {
        return Images.birds[index++%Images.birds.length];
    }

    /**
     * 得分方法
     * 成功躲避鸟，得10分
     * @return
     */
    @Override
    public int getScore() {
        return 10;
    }
}
