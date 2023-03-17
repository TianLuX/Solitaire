package Cards;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

/*
* 扑克堆，使用类似堆栈数据结构
* */
public class CardStack {
    // 位置坐标x,y
    protected int x;
    protected int y;

    public Stack<Card> cardStack = new Stack<Card>();//使用类似堆栈数据结构

    /*构造方法*/
    public CardStack(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*判断牌堆是否为空*/
    public boolean isEmpty(){
        return cardStack.empty();
    }

    /*返回牌堆第一张*/
    public Card top(){
        if(!cardStack.isEmpty()){
            return (Card)cardStack.peek();//返回栈顶的元素但不移除它
        }
        return null;
    }

    /*取走牌堆第一张*/
    public Card pop(){
        if(!cardStack.isEmpty()){
            return (Card)cardStack.pop();//返回栈顶的元素但不移除它
        }
        return null;
    }

    /*判断鼠标在x,y处是否点在牌堆上*/
    public boolean isCardStack(int pointX,int pointY){
        return this.x <= pointX && pointX <= this.x + Card.width
                && this.y <= pointY && pointY <= this.y + Card.height;
    }

    /*判断牌堆含有的牌数*/
    public int countCard(int pointX,int pointY){
        if(isCardStack(pointX,pointY)){//是牌堆
            if(isEmpty()){
                return -1;//牌堆没有牌
            }else{
                return cardStack.size()-1;
            }
        }else{
            return -2;//不是牌堆
        }
    }

    /*往牌堆添加新的牌,DiscardCard和sevenPiles将它改写*/
    public void addCard(Object card) {
        cardStack.push((Card)card);
    }

    /*牌堆第一张牌的绘制*/
    public void show(Graphics graphics) {

        // 栈空时，放置背景框图片
        if (isEmpty()) {
            Image image = null;
            try {
                String picture = "C:\\Users\\Huawei\\IdeaProjects\\Solitaire\\src\\images\\blank.bmp";
                image = ImageIO.read(new File(picture));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 放置图片
            graphics.drawImage(image, this.x, this.y, Card.width, Card.height, null);
        } else {
            // 放置栈顶纸牌图片
            top().setX(x);
            top().setY(y);
            top().draw(graphics);//绘制图片
        }
    }



}
