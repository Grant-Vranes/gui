package cn.akio.fovl;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Akio
 * @ClassName Map
 * @Description TODO
 * @Date 2021/7/19 11:18
 */
public class Map extends SuperObject implements Accelerate {
    private int speed;//移动速度
    private int x1;//代表第二张map背景图的x坐标
    Random random = new Random();

    public int getX1() {
        return x1;
    }

    public Map() {
        super(DinoWorld.WIDTH, DinoWorld.HEIGHT, 0, 0);
        this.speed = SPEED;
        this.x1 = DinoWorld.WIDTH;
    }

//    private int index = 0;//决定地图图片的轮次
    @Override
    public void step() {
        x -= speed;
        x1 -= speed;
        if (x <= -DinoWorld.WIDTH){
            x = DinoWorld.WIDTH;
//            index = 1;
        }
        if (x1 <= -DinoWorld.WIDTH){
            x1 = DinoWorld.WIDTH;
//            index = 0;
        }
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    /**
     * 方法不够优秀，对齐进行重构
     * @param index
     * @return
     */
    public BufferedImage getImage(int index) {
//        int index = random.nextInt(2);
        return Images.maps[index];//图片注意
    }
}
