package cn.akio.fovl;

import java.awt.image.BufferedImage;

/**
 * @author Akio
 * @ClassName Dinosaur
 * @Description 恐龙，只有弹跳动作，空格控制
 * @Date 2021/7/19 8:41
 */
public class Dinosaur extends SuperObject {
    public static final int LIVE = 0;//活着的
    private int state = LIVE;//当前为默认状态

    public Dinosaur() {
        super(40, 43, 10, 200);
    }

    /**
     * 重写移动方法
     */
    @Override
    public void step() {

    }

    /**
     * 判断是否是活着的
     *
     * @return
     */
    public boolean isLive() {
        return state == LIVE;
    }

    private int index = 0;
    /**
     * 重写获取图片的方法
     *
     * @return
     */
    @Override
    public BufferedImage getImage() {
        if (isLive()) {
            BufferedImage img = Images.dinosaurs[index++ % (Images.dinosaurs.length-1)];
            return img;
        }else {
            return Images.dinosaurs[3];
        }
    }

}
