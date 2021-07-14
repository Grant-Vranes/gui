package cn.akio.lesson02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestText01 {
    public static void main(String[] args) {
        new MyFrame();
    }
}

class MyFrame extends Frame{
    public MyFrame(){
        TextField textField = new TextField();
        add(textField);

        //监听这个文本框输入的文字
        MyActionListener2 myActionListener2 = new MyActionListener2();
        //按下enter 就会触发这个输入框的事件
        textField.addActionListener(myActionListener2);

        //设置替换编码，所有的输入都可以转换成*
        textField.setEchoChar('*');

        setVisible(true);
        pack();
    }
}

class MyActionListener2 implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        TextField field = (TextField)e.getSource();//获得一些资源，返回值类型是Object，我们可以对其做类型转换
        System.out.println(field.getText());//获取输入框的文本
        field.setText("");//每次回车后就会设置为""
    }
}
