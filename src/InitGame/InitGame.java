package InitGame;

import Cards.*;

import java.util.ArrayList;

/*
* 游戏的初始化
* */
public class InitGame {
    //使用数组存放纸牌
    static public ArrayList<Card> cards;
    //使用堆栈存放牌堆
    static public CardStack cardStacks[];
    //用来取牌的牌堆
    static public DeckCard deckCard;
    //还没有取走的牌堆
    static public DiscardCard discardCard;
    //还没移动的七个牌堆
    static public SevenPiles sevenPiles[];
    //用来放四个花色的牌堆
    static public Suitpiles suitpiles[];
    //移动的纸牌
    static public CardMoving cardMoving;


    /*主要进行洗牌：通过数组随机数交换的方式
    * 初始化sevenPiles牌堆，放入牌并且把第一张反过来
    * 初始化deckCard，把剩下的牌都放到deckCard
    * */
    static {

        cards = new ArrayList<Card>();
        /*把所有的牌添加到数组*/
        for(int i = 0; i < 4; i++){
            for (int j = 0; j <= 12; j++){
                cards.add(new Card(i,j));//调用自定义的构造方法，添加牌的花色和大小
            }
        }

        /*
         * 使用数组交换的方式进行洗牌
         * */
        for (int i = 0; i < 26; i++){
            int a = (int) (Math.random() * 52);
            int b = (int) (Math.random() * 52);
            Card t = cards.get(a);
            cards.set(a, cards.get(b));
            cards.set(b, t);
        }



        //界面上总共有4+7+1+1个牌堆
        cardStacks = new CardStack[13];

        //初始化取牌的牌堆，参数为界面上的xy点
        deckCard = new DeckCard(200, 40);

        //那些还没有移动的牌堆
        discardCard = new DiscardCard(200 + Card.width + 50, 40);

        //界面上的四个牌堆,用来存放四个花色
        suitpiles = new Suitpiles[4];

        //界面上的七个牌堆1234567
        sevenPiles = new SevenPiles[7];

        //还没取到的牌
        cardStacks[0] = deckCard;
        //取过但是还没有放的牌
        cardStacks[1] = discardCard;

        //界面上的四个牌堆
        for (int i = 0; i < 4; i++){
            //调用构造方法
            //200 + Card.width + 50 + Card.width + 150 前面有两个取牌和放牌的堆
            suitpiles[i] = new Suitpiles(400 + Card.width * 2 + (40 + Card.width) * i, 40);
            //在所有牌堆里的编号是2-5
            cardStacks[2 + i] = suitpiles[i];
        }


        //界面上的七个牌堆1234567
        for (int i = 0; i < 7; i++){
            //调用构造方法，前两个为界面位置xy，第三个参数没有为翻开的牌数，第一个全都翻开，每个只翻开第一张
             sevenPiles[i] = new SevenPiles(200 + (50 + Card.width) * i, 40 + Card.height + 40, i);
             //在所有牌堆里的编号是6到12
             cardStacks[6 + i] = sevenPiles[i];
        }

        //将牌放到七个牌堆里
        for (int i = 0; i < 7; i++){

            ArrayList<Card> pile = new ArrayList<Card>();

            //按照每个牌堆的编号，放入牌
            for (int j = 0; j < sevenPiles[i].getCardSum(); j++) {
                //从cards所有牌中取走，添加到临时牌堆pile里
                pile.add(cards.remove(cards.size() - 1));
            }

            //把取来的牌放到pile里
            sevenPiles[i].addCard(pile);
            //记录每堆牌的数量
            sevenPiles[i].setCardSum(sevenPiles[i].getUnTurnCardSum() + 1);
            //把每堆牌第一张翻开
            sevenPiles[i].top().setFront(true);


        }


        //除了放到sevenPiles中的牌，把剩下的牌放到deckCard中
        int rest = cards.size();
        for (int i = 0; i < rest; i++) {
            deckCard.addCard(cards.remove(cards.size() - 1));
        }

        cardMoving = new CardMoving();

    }


    /*重新游戏*/
    public static void reGame(){
        cards = new ArrayList<Card>();
        /*把所有的牌添加到数组*/
        for(int i = 0; i < 4; i++){
            for (int j = 0; j <= 12; j++){
                cards.add(new Card(i,j));//调用自定义的构造方法，添加牌的花色和大小
            }
        }
        /*
         * 使用数组交换的方式进行洗牌
         * */
        for (int i = 0; i < 26; i++){
            int a = (int) (Math.random() * 52);
            int b = (int) (Math.random() * 52);
            Card t = cards.get(a);
            cards.set(a, cards.get(b));
            cards.set(b, t);
        }




        //界面上总共有4+7+1+1个牌堆
        cardStacks = new CardStack[13];

        //初始化取牌的牌堆，参数为界面上的xy点
        deckCard = new DeckCard(200, 40);

        //那些还没有移动的牌堆
        discardCard = new DiscardCard(200 + Card.width + 50, 40);

        //界面上的四个牌堆,用来存放四个花色
        suitpiles = new Suitpiles[4];

        //界面上的七个牌堆1234567
        sevenPiles = new SevenPiles[7];

        //还没取到的牌
        cardStacks[0] = deckCard;
        //取过但是还没有放的牌
        cardStacks[1] = discardCard;

        //界面上的四个牌堆
        for (int i = 0; i < 4; i++){
            //调用构造方法
            //200 + Card.width + 50 + Card.width + 150 前面有两个取牌和放牌的堆
            suitpiles[i] = new Suitpiles(400 + Card.width * 2 + (40 + Card.width) * i, 40);
            //在所有牌堆里的编号是2-5
            cardStacks[2 + i] = suitpiles[i];
        }


        //界面上的七个牌堆1234567
        for (int i = 0; i < 7; i++){
            //调用构造方法，前两个为界面位置xy，第三个参数没有为翻开的牌数，第一个全都翻开，每个只翻开第一张
            sevenPiles[i] = new SevenPiles(200 + (50 + Card.width) * i, 40 + Card.height + 40, i);
            //在所有牌堆里的编号是6到12
            cardStacks[6 + i] = sevenPiles[i];
        }

        //将牌放到七个牌堆里
        for (int i = 0; i < 7; i++){

            ArrayList<Card> pile = new ArrayList<Card>();

            //按照每个牌堆的编号，放入牌
            for (int j = 0; j < sevenPiles[i].getCardSum(); j++) {
                //从cards所有牌中取走，添加到临时牌堆pile里
                pile.add(cards.remove(cards.size() - 1));
            }

            //把取来的牌放到pile里
            sevenPiles[i].addCard(pile);
            //记录每堆牌的数量
            sevenPiles[i].setCardSum(sevenPiles[i].getUnTurnCardSum() + 1);
            //把每堆牌第一张翻开
            sevenPiles[i].top().setFront(true);


        }


        //除了放到sevenPiles中的牌，把剩下的牌放到deckCard中
        int rest = cards.size();
        for (int i = 0; i < rest; i++) {
            deckCard.addCard(cards.remove(cards.size() - 1));
        }

        cardMoving = new CardMoving();


    }

    /*判断是否能够放到最终四个牌堆suitpiles上*/
    public static boolean canAddToSuitPile(int x, int y) {
        if (cardMoving.size() == 1) {//鼠标拖动的牌只有一张时
            //分别判断十个堆可否放
            for (int i = 0; i < 4; i++) {
                if (suitpiles[i].isCardStack(x, y)) {//suitpiles调用父类方法，鼠标按下时判断是否在牌堆上
                    //如果可以放入，则拿出放入
                    if (suitpiles[i].canAdd(cardMoving.top())) {
                        suitpiles[i].addCard(cardMoving.pop());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*从deckCard中翻出一张牌*/
    public static boolean showOneCard(int x, int y){
        //获得牌的数量
        int cardNum = deckCard.countCard(x, y);
        //有牌才能取
        if (cardNum >= 0) {
            //从deckCard牌堆拿出放到discardCard牌堆里
            discardCard.addCard(deckCard.pop());
            return true;
        } else if (cardNum == -1) {
            //没有牌的情况
            InitGame.returnToDiscardToDeck();
            return true;
        } else {
            //返回-2不是牌堆
            return false;
        }

    }
    /*把discardCard的牌放回deckCard*/
    /*在showOneCard方法中调用，如果只有一张牌的情况*/
    public static void returnToDiscardToDeck() {
        while (!(discardCard.isEmpty())) {
            Card card = discardCard.pop();
            card.setFront(false);
            deckCard.addCard(card);
        }
    }

    /*
     * 点击discardCard牌，开始移动
     * */
    public static boolean toCardMoving(int x, int y) {

        int selectNum = discardCard.countCard(x, y);
        if (selectNum >= 0) {
            cardMoving.clear();//这时鼠标只能拖动一张牌
            cardMoving.addCard(discardCard.pop());
            cardMoving.setCardMovingStack(discardCard);
            return true;
        }
        return false;
    }



    /*判断能否放到sevenPiles上*/
    public static boolean canAddToSevenPiles(int x, int y) {
        //判断每个牌堆
        for (int i = 0; i < 7; i++) {
            //这个位置是牌堆
            if (sevenPiles[i].isCardStack(x, y)) {
                if (sevenPiles[i].hashCode() != cardMoving.getCardMovingStack().hashCode()){//hashCode用来识别一个对象
                    if (sevenPiles[i].canAdd(cardMoving.top())) {
                        sevenPiles[i].addCard(cardMoving.clear());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*拿走sevenPiles的第一张后把sevenPiles下一张翻过来*/
    public static void turnSevenPiles() {

        for (int i = 0; i < 7; i++) {
            //如果牌堆不为空
            if (sevenPiles[i].top() != null)
                //而且第一张牌没反过来
                if (!(sevenPiles[i].top().isFront())) {
                    //把第一张牌反过来
                    sevenPiles[i].top().setFront(true);
                    sevenPiles[i].setUnTurnCardSum(sevenPiles[i].getUnTurnCardSum() - 1);
                }

        }
    }
    /*拖拽桌面的牌堆,整堆拖过去的情况
     * */
    public static boolean testSevenStack(int x, int y) {

        boolean isDragged = false;
        for (int i = 0; i < sevenPiles.length; i++) {
            int count = sevenPiles[i].countCard(x, y);
            if (count >= 0) {
                cardMoving.clear();
                int num = sevenPiles[i].getCardSum();
                for (int j = count; j < num; j++) {
                    cardMoving.addCard(sevenPiles[i].pop());
                }
                cardMoving.setCardMovingStack(sevenPiles[i]);
                return true;
            }

        }
        return isDragged;
    }

    /*把cardMoving放回到原来的牌堆，只能从discard取和sevenpiles取，无法移动的情况
     */
    public static void returnToFromPile() {

        if (cardMoving.getCardMovingStack() != null)
            if (cardMoving.getCardMovingStack().hashCode() == discardCard.hashCode()) {

                while (!(cardMoving.isEmpty())) {
                    cardMoving.getCardMovingStack().addCard(cardMoving.pop());
                }

            } else{
                cardMoving.getCardMovingStack().addCard(cardMoving.clear());
            }



    }





}
