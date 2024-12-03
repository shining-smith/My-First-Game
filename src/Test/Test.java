package Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(603, 680);
        jf.setTitle("事件演示");
        jf.setLocationRelativeTo(null);
        jf.setAlwaysOnTop(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);

        Random rd = new Random();

        JButton jb = new JButton("点我逝世");
        jb.setBounds(0,0,100,50);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf2 =  new JFrame();
                
                jf2.setVisible(true);
            }
        });



        jf.getContentPane().add(jb);


        jf.setVisible(true);
    }
}
