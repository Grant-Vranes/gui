package cn.akio.fovl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @author Akio
 * @ClassName Images
 * @Description 图片加载类
 * @Date 2021/7/19 8:53
 */
public class Images {
    public static BufferedImage[] maps;
    public static BufferedImage[] dinosaurs;
    public static BufferedImage[] cactus;//有三种类型的仙人掌障碍物
    public static BufferedImage cloud;
    public static BufferedImage[] birds;

    public static BufferedImage init;//游戏首次开始图片
    public static BufferedImage start;//游戏开始
    public static BufferedImage game_over;//结束状态
    public static BufferedImage pause;//暂停按钮

    static {//初始化图片
        //加载背景图片组
        maps = new BufferedImage[2];
        maps[0] = readImage("statics/map.png");
        maps[1] = readImage("statics/map1.png");

        //加载小恐龙图片组
        dinosaurs = new BufferedImage[4];
        for (int i = 1; i <= dinosaurs.length; i++) {
            dinosaurs[i-1] = readImage("statics/dino" + i + ".png");
        }

        //加载翼龙障碍物图片组
        birds = new BufferedImage[2];
        birds[0] = readImage("statics/bird1.png");
        birds[1] = readImage("statics/bird2.png");

        //加载仙人掌障碍物图片组
        cactus = new BufferedImage[3];
        for (int i = 1; i <= cactus.length; i++) {
            cactus[i-1] = readImage("statics/cactus0" + i + ".png");
        }

        //加载云图片
        cloud = readImage("statics/cloud.png");

        //加载幕布首次开始图片
        init = readImage("statics/init.png");

        //加载开始图片
        start = readImage("statics/start.png");

        //加载结束图片
        game_over = readImage("statics/game_over.png");

        //加载暂停图标
        pause = readImage("statics/pause.png");

    }

    /**
     * 读取图片
     */
    public static BufferedImage readImage(String fileName) {
        try {
            //读取与Images类在同包中的图片素材
            BufferedImage img = ImageIO.read(Images.class.getResource(fileName));
            return img;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

//    public static void main(String[] args) {
//        System.out.println(Images.cactus.length);
//    }
}
