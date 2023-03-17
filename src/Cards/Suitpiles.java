package Cards;

/*最后用来放的四个堆*/
public class Suitpiles extends CardStack{
    //实现父类的构造方法
    public Suitpiles(int x, int y) {
        super(x, y);
    }

    /*能否放入 第一种情况和前面同花色且大小比它大一时可以放入
    * 第二种情况，空的时候只能放A*/
    /*子类特有方法*/
    public boolean canAdd(Card card){
        if(!isEmpty()){
            Card previousCard = top();//前一张牌
            if(card.getValue() == previousCard.getValue() + 1 && card.getType() == previousCard.getType()){
                //和前面同花色且大小比它大一时可以放入
                return true;
            }else{
                return false;
            }
        }else{
            if(card.getValue() == 0){//的时候只能放A
                return true;
            }else{
                return false;
            }
        }
    }
}
