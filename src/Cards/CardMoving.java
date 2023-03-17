package Cards;

import java.awt.*;
import java.util.ArrayList;

/*鼠标拖动的牌*/
public class CardMoving {
    /*用堆栈是因为可能一次移动好几张牌*/
    //坐标位置
    private int x;
    private int y;

    final public static int separation = 30;// 纸牌打开后纵向的偏移量

    //存放移动中的纸牌
    private CardStack cardMovingStack;
    private ArrayList<Card> cardArrayList;

    /*构造方法*/
    public CardMoving() {
        cardArrayList = new ArrayList<Card>();
    }

    /*返回动态数组是否为空*/
    public boolean isEmpty(){
        return cardArrayList.isEmpty();
    }

    /*返回动态数组大小*/
    public int size(){
        return cardArrayList.size();
    }

    /*相关的get和set方法*/

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

    public static int getSeparation() {
        return separation;
    }

    public CardStack getCardMovingStack() {
        return cardMovingStack;
    }

    public void setCardMovingStack(CardStack cardMovingStack) {
        this.cardMovingStack = cardMovingStack;
    }

    public ArrayList<Card> getCardArrayList() {
        return cardArrayList;
    }

    public void setCardArrayList(ArrayList<Card> cardArrayList) {
        this.cardArrayList = cardArrayList;
    }

    /*添加纸牌对象*/
    public void addCard(Card card) {
        cardArrayList.add(0, card);
    }

    /*返回第一个纸牌对象,类似于CardStack中的top*/
    public Card top(){
        if (!cardArrayList.isEmpty()){
            return cardArrayList.get(0);
        }else{
            return null;
        }
    }

    /*取走第一个纸牌，并且放到Card中*/
    public Card pop(){
        if (!cardArrayList.isEmpty()){
            return cardArrayList.remove(0);
        }else{
            return null;
        }
    }

    /*设置list里的card的图片*/
    public void show(Graphics graphics, int pointX, int pointY){
        //point卡牌中心点，xy卡牌左上角
        x = pointX - Card.width / 2;
        y = pointY - Card.height / 2;//便于后来加separation

        int localy = y;

        int size = cardArrayList.size();
        for (int i = 0; i <size; i++) {
            Card Card = cardArrayList.get(i);
            Card.setX(x);
            Card.setY(localy);

            if (!(Card.isFront())) {
                Card.setFront(true);

            }
            Card.draw(graphics);
            localy += separation;
        }
    }

    /*保存当前cardArrayList，然后清空当前的cardArrayList*/
    public ArrayList<Card> clear() {
        ArrayList<Card> newList = cardArrayList;
        cardArrayList = new ArrayList<Card>();
        return newList;
    }

}
