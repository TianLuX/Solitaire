package Cards;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Card {

    /*
    * 卡片属性
    * */
    final public static int width = 96;// 纸牌宽度
    final public static int height = 130;// 纸牌高度

    final public static int Diamond = 0;;// 方块
    final public static int Spade = 1;// 黑桃
    final public static int Hearts = 2;// 红桃
    final public static int Club = 3;// 梅花

    private boolean isFront;//卡片是否正面朝上
    private int value;// 牌的数值
    private int type;// 牌的花色
    private int x;// 位置坐标x
    private int y;// 位置坐标y

    /*
    * 构造函数
    * */
    public Card(int type,int value) {

        this.value = value;// 牌的数值
        this.type = type;// 牌的花色

        isFront = false;//默认正面朝下
    }

    /*
    * 获取和设置值纸牌是否正面
    * */
    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    /*
    * 获取和设置纸牌类型
    * */
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    /*纸牌面值的设置和获得
    * */

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /*
    * 纸牌位置的设置和获得
    * */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*
    * 通过纸牌花色获取纸牌颜色
    * */
    public Color getColor() {
        if (isFront()) {
            if (getType() == Diamond || getType() == Hearts){//0是方块，2是红桃
                return Color.red;
            }else{
                return Color.black;
            }
        }
        return Color.white;//背面颜色
    }

    /*
    *在相应地方绘制纸牌
    * 需要确定绘制正面还是背面
    * 正面需要花色和大小
    * */
    public void draw(Graphics graphics) {

        Image image = null;

        // 正面朝上的获取相应卡牌图片
        if (isFront()) {
            try {
                String picture = "C:\\Users\\Huawei\\IdeaProjects\\Solitaire\\src\\images\\"+this.type+"-"+this.value+".gif";
                image = ImageIO.read(new File(picture));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("读取"+"C:\\Users\\Huawei\\IdeaProjects\\Solitaire\\src\\images\\"+this.type+"-"+this.value+".gif"+"失败");
            }
        } else {
            // 背面朝上的获取背面图片
            try {
                String picture = "C:\\Users\\Huawei\\IdeaProjects\\Solitaire\\src\\images\\back.gif";
                image = ImageIO.read(new File(picture));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // 放置图片
        graphics.drawImage(image, getX(), getY(), Card.width, Card.height, null);

    }



}
