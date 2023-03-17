package Cards;


/*用来展示所取的牌，但是还没放的*/
public class DiscardCard extends CardStack {
    //实现父类的构造方法
    public DiscardCard(int x, int y) {
        super(x, y);
    }

    /*添加纸牌到堆，需要改写父类addCard，需要把牌正面朝上放入堆栈*/
    public void addCard(Card card) {
        // 背面朝上设置成正面朝上
        if (!(card.isFront())){
            card.setFront(true);
        }
        cardStack.push(card);// 压栈

    }


}
