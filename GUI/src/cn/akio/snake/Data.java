package cn.akio.snake;

import javax.swing.*;
import java.net.URL;

/**
 * @author Akio
 * @ClassName Data
 * @Description 数据中心
 * @Date 2021/7/15 19:47
 */
public class Data {
    //相对路径 123.jpg
    //绝对路径  /相当于当前项目
    public static URL headerURL = Data.class.getResource("statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    public static URL upURL = Data.class.getResource("statics/up.png");
    public static URL downURL = Data.class.getResource("statics/down.png");
    public static URL leftURL = Data.class.getResource("statics/left.png");
    public static URL rightURL = Data.class.getResource("statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);

    public static URL bodyURL = Data.class.getResource("statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    public static URL foodURL = Data.class.getResource("statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);


}
