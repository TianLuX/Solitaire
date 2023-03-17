package GUI;

import InitGame.InitGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/*纸牌游戏菜单栏
* */
public class SolitaireMenu extends JMenuBar{

    //生成Solitaire框架对象
    SolitaireFrame main = null;

    //生成菜单组
    JMenu jCardGame = new JMenu("游戏");
    JMenu jElse = new JMenu("其他");

    //生成顶端菜单项
    JMenuItem jMenuItemAbout = new JMenuItem("关于游戏制作");
    JMenuItem jMenuItemPlayAgain = new JMenuItem("重新开始");
    JMenuItem jMenuItemExit = new JMenuItem("退出游戏");

    /**
     **构造函数，生成JMenuBar的图形界面
     */
    public SolitaireMenu(SolitaireFrame solitaireFrame){

        this.main = solitaireFrame;

        /**
         **初始化游戏菜单栏
         */
        jCardGame.add(jMenuItemPlayAgain);//重新开始
        jCardGame.add(jMenuItemExit);//退出游戏

        /**
         **初始化其他菜单栏
         */
        jElse.add(jMenuItemAbout);//关于游戏制作

        //添加到solitaire JFrame
        this.add(jCardGame);
        this.add(jElse);

        /*组件添加监听事件
        * */

        /*重新开始*/
        jMenuItemPlayAgain.addActionListener(
                new java.awt.event.ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        InitGame.reGame();
                        repaint();
                    }
                }
        );

        /*退出游戏*/
        jMenuItemExit.addActionListener(
                new java.awt.event.ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //退出游戏；
                        main.dispose();//关闭窗体
                        System.exit(0);//系统退出
                    }
                }
        );




        /*其他*/
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {

                new AboutSolitaire();
            }
        });





    }




}
