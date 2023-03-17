package Cards;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/*桌面上七个还没放的堆*/
public class SevenPiles extends CardStack {
    private int unTurnCardSum;//没有翻的纸牌数量
    private int cardSum;//每堆的数量

    final public static int separation = 30;// 正面纸牌间隔
    final public static int unTurnSeparation = 10;// 背面纸牌间隔


    /*子类自己的构造方法*/
    public SevenPiles(int x, int y, int unTurnCardSum) {
        super(x, y);
        this.unTurnCardSum = unTurnCardSum;

        cardSum = unTurnCardSum + 1;//初始时所有牌只有第一张翻开了
    }

    /*改写父类的isCardStack方法，因为有牌的间隔*/
    public boolean isCardStack(int pointX,int pointY){
        int beginX, beginY, endX, endY;
        beginX = x;
        beginY = y;
        endX = x + Card.width;//横向的宽度为卡牌的宽度

        //根据牌的数量设置纸牌尾部的y
        if (cardStack.size() > 0){
            //不止一张牌的情况
            endY = beginY + unTurnSeparation*unTurnCardSum
                    + separation * (cardStack.size() - 1 - unTurnCardSum) + Card.height;
        }else{
            //只有一张牌的情况
            endY = beginY + Card.height;
        }

        boolean flag = (beginX <= pointX && pointX <= endX && beginY <= pointY && pointY <= endY);
        return flag;
    }

    /*能否添加纸牌*/
    public boolean canAdd(Card card){
        if (isEmpty()){
            return card.getValue() == 12;//空的只能放K
        }
        Card previousCard = top();//前一张牌
        //必须颜色不同，且大小正好小一的情况才能放
        return (card.getColor() != previousCard.getColor())
                && (card.getValue() == previousCard.getValue() - 1);
    }

    /*往牌堆添加新的牌,继承后改写*/
    public void addCard(Object card) {
        ArrayList<Card> cardList = (ArrayList<Card>)card;
        cardSum += cardList.size();
        for (int i = 0; i < cardList.size(); i++) {
            cardStack.push(cardList.get(i));
        }
    }

    /*牌堆的绘制*/
    public void show(Graphics graphics){
        if (isEmpty()) {
            //没有牌时
            Image image = null;
            try {
                String picture = "C:\\Users\\Huawei\\IdeaProjects\\Solitaire\\src\\images\\blank.bmp";
                image = ImageIO.read(new File(picture));
            } catch (IOException e) {
                e.printStackTrace();
            }
            graphics.drawImage(image, this.x, this.y, Card.width, Card.height, null);
        } else {
            //有牌时，通过枚举绘制牌
            int pointY = y;
            for (Enumeration enumeration = cardStack.elements(); enumeration.hasMoreElements();) {
                //进行枚举
                Card aCard = (Card) enumeration.nextElement();
                aCard.setX(x);
                aCard.setY(pointY);
                aCard.draw(graphics);
                if (aCard.isFront()){
                    //是正面的情况
                    pointY += separation;
                }else{
                    //是背面的情况
                    pointY += unTurnSeparation;
                }

            }
        }
    }

    /**
     * 弹出栈顶的纸牌,改写父类的方法，因为子类需要减去cardSum，移走牌时
     */
    public Card pop() {
        cardSum--;
        return super.pop();

    }

    /*获取牌堆的数量，改写父类的方法，因为牌堆有间隔*/
    public int countCard(int pointX,int pointY){
        if (!(isEmpty())) {
            int beginX, beginY, endX, endY;
            beginX = x;
            beginY = y + unTurnSeparation * unTurnCardSum;//没翻的牌

            endX = x + Card.width;
            endY = beginY + unTurnSeparation * unTurnCardSum
                    + separation * (cardStack.size() - 1 - unTurnCardSum) + Card.height;

            boolean flag = (beginX <= pointX && pointX <= endX && beginY <= pointY
                    && pointY <= endY);

            if (flag) {
                int t = (pointY - beginY) / separation + unTurnCardSum;
                if (t >= cardStack.size()) {
                    t = cardStack.size() - 1;
                }
                return t;// 从零开始
            } else
                return -1;
        } else{
            //牌堆为空
            return -1;
        }

    }

    /*获取纸牌翻开间隔*/
    public static int getSeparation() {
        return separation;
    }
    /*获取纸牌未翻开间隔*/
    public static int getUnTurnSeparation() {
        return unTurnSeparation;
    }

    /*设置和获取值纸牌总数*/
    public int getCardSum() {
        return cardSum;
    }
    public void setCardSum(int cardSum) {
        this.cardSum = cardSum;
    }

    /*获取和设置未翻开的纸牌总数*/
    public int getUnTurnCardSum() {
        return unTurnCardSum;
    }
    public void setUnTurnCardSum(int unTurnCardSum) {
        this.unTurnCardSum = unTurnCardSum;
    }

}
