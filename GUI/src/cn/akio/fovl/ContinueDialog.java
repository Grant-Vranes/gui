package cn.akio.fovl;

import com.sun.org.apache.xerces.internal.util.XMLInputSourceAdaptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 死亡后弹窗显示可以复活
 *
 * @author Akio
 * @Create 2021/8/1 14:21
 */
public class ContinueDialog extends JDialog {
    public ContinueDialog() {

        Container container = this.getContentPane();
//         container.setLayout(null);

        //来个标签
        JLabel label = new JLabel("天赋：霸王龙之体，您有"+Dinosaur.life+"次复活机会，当前可复活，您的选择是？");
//         label.setBounds(10, 100);

        //来两个按钮
        JButton yes = new JButton("复活");
//         yes.setSize(60,20);
        JButton no = new JButton("开启下辈子 ");
//         no.setSize(60,20);

        //给两个按钮绑定一下触发事件
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DinoWorld.setState();
                setVisible(false);
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DinoWorld.setNextLife();
                setVisible(false);
            }
        });

        container.add(label, BorderLayout.NORTH);
        container.add(yes, BorderLayout.WEST);
        container.add(no, BorderLayout.EAST);

        this.setVisible(true);
        this.setSize(420, 70);
        this.setLocationRelativeTo(null);
    }

}
