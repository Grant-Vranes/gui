package cn.akio.lesson02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCalc_better {
    public static void main(String[] args) {
        new Calculator1().loadFrame();
    }
}

//计算器类
class Calculator1 extends Frame {
    //属性
    TextField num1,num2,num3;
    //方法
    public void loadFrame(){
        //三个文本框
        num1 = new TextField(10);//字符数
        num2 = new TextField(10);//字符数
        num3 = new TextField(20);//字符数

        //1个按钮
        Button button = new Button("=");
        //给按钮绑定一个事件
        button.addActionListener(new MyCalcListener1(this));

        //一个标签
        Label label = new Label("+");

        //布局：流式布局
        setLayout(new FlowLayout());

        add(num1);
        add(label);
        add(num2);
        add(button);
        add(num3);

        pack();
        setVisible(true);
    }
}

//监听器类
class MyCalcListener1 implements ActionListener {
    //获取计算器这个对象，在一个类中组合另外一个类
    Calculator1 calculator1 = null;

    public MyCalcListener1(Calculator1 calculator1) {
        this.calculator1 = calculator1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //1、获得加数和被加数
        int n1 = Integer.parseInt(calculator1.num1.getText());
        int n2 = Integer.parseInt(calculator1.num2.getText());
        //2、将这个值+法运算后，放到第三个框
        calculator1.num3.setText(""+(n1+n2));
        //3、清除前两个框的内容
        calculator1.num1.setText("");
        calculator1.num2.setText("");
    }
}