package cn.akio.lesson03;

import java.awt.*;

/**
 * @author Akio
 * @ClassName TestPaint
 * @Description TODO
 * @Date 2021/7/12 16:50
 */
public class TestPaint {
    public static void main(String[] args) {
        new MyPaint().loadFrame();
    }
}

class MyPaint extends Frame {
    public void loadFrame(){
        setBounds(200,200,600,500);
        setVisible(true);
    }

    //画笔
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
//        g.drawOval(100,100,100,1000);//画圆
        g.fillOval(100,100,100,100);//画实心圆

        //使用前都要更换画笔颜色
        g.setColor(Color.GREEN);
        g.fillRect(150,200,200,200);

        //要养成习惯，画笔用完，将他还原到最初的颜色
    }
}
