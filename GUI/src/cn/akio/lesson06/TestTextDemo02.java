package cn.akio.lesson06;

import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;

/**
 * @author Akio
 * @ClassName TestTextDemo01
 * @Description TODO
 * @Date 2021/7/15 19:20
 */
public class TestTextDemo02 extends JFrame {
    public TestTextDemo02(){
        Container container = this.getContentPane();

        JPasswordField passwordField = new JPasswordField();//****
        passwordField.setEchoChar('*');

        container.add(passwordField);

        this.setVisible(true);
        this.setSize(500,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TestTextDemo02();
    }
}
