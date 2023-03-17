package GUI;

import InitGame.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*panel类继承JPanel类和MouseListener, ActionListener, MouseMotionListener接口，实现相关方法*/
public class SolitairePanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener {
    //鼠标位置
    private int x;
    private int y;

    private boolean isDragged = false;//是否被拖拽

    /*构造方法
    * */
    public SolitairePanel(){
        setSize(1334, 750);//设置大小
        setLayout(null);//清空布局管理器，便于用x,y值来确定组件的位置
        addMouseListener(this);//设置鼠标事件监听
        addMouseMotionListener(this);
    }

    /*界面绘制*/
    public void paint(Graphics graphics) {

        graphics.clearRect(0, 0, 1366, 768);

        graphics.setColor(new Color(0x4E7CA1));

        graphics.fillRect(0, 0, 1366, 768);

        for (int i = 0; i < 13; i++){
            InitGame.cardStacks[i].show(graphics);

        }


        InitGame.cardMoving.show(graphics, x, y);
    }


    /*接口实现*/
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /*鼠标按下事件
    * 获取坐标*/
    /*鼠标按下时，如果有纸牌，自动居中显示拖动的纸牌*/
    @Override
    public void mousePressed(MouseEvent e) {
        // 获取坐标
        x = e.getX();
        y = e.getY();
        isDragged = false;

        boolean isSelect = false;

        //点击牌堆的情况
        isSelect = InitGame.showOneCard(x, y);
        if (!isSelect) {

            //移动的纸牌堆里有没有纸牌
            isSelect = InitGame.toCardMoving(x, y);
            if (isSelect){
                isDragged = true;
            }else{
                isSelect = InitGame.testSevenStack(x, y);
                if (isSelect){
                    isDragged = true;
                }
            }
        }
        isDragged = false;
        repaint();
    }

    /*鼠标松开事件
    * */
    @Override
    public void mouseReleased(MouseEvent e) {

        if (isDragged && InitGame.cardMoving.size() > 0) {
            //鼠标松开时，有在移动牌
            //牌中心在牌堆上才可以放
            boolean canAdd = false;
            canAdd = InitGame.canAddToSuitPile(x, y);
            if (!canAdd){
                canAdd = InitGame.canAddToSevenPiles(x,y);
            }
            if (!canAdd){
                //都不能放就放回去
                InitGame.returnToFromPile();
            }else{
                InitGame.turnSevenPiles();
            }
            isDragged = false;

            repaint();
        } else {
            if (InitGame.cardMoving.size() > 0){
                //没有放下，则移动回去
                InitGame.returnToFromPile();
            }
            repaint();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /*鼠标拖拽事件
    * */
    @Override
    public void mouseDragged(MouseEvent e) {
        isDragged = true;
        x = e.getX();
        y = e.getY();
        repaint();//重新绘制组件
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
