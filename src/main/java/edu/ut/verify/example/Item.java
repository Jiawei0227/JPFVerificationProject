package edu.ut.verify.example;

/**
 * Created by Jerry Wang on 2018/11/26.
 */
public class Item {

   private static int[] prices = new int[]{ 1,5,10,15 };

   private int price;
   private int number;

   public Item(int number, int index){
       this.number = number;
       this.price = prices[index];
   }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice(){
       return price*number;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
