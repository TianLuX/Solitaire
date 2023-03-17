package GUI;

import javax.swing.*;
import java.awt.*;

/*窗口*/

public class SolitaireFrame extends JFrame {
    private SolitairePanel solitairePanel;

    /*
    * 构造方法
    * */
    public SolitaireFrame(){
        Font font = new Font("Dialog", Font.PLAIN, 13);// 改变系统默认字体

        // 窗体配置
        setSize(1334, 750);
        setTitle("Solitaire Game");
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        // 生成MenuBar对象，并放置在框架之上
        setJMenuBar(new SolitaireMenu(this));

        // 生成myMenuBar对象，并放置在框架之上
        solitairePanel = new SolitairePanel();
        add(solitairePanel);

        setVisible(true);


    }
}
