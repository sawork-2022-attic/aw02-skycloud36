package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    //add the item to cart by productID
    public boolean addItem(Item item) {
        Item temp = this.findItem(item);
        if(temp != null){
            temp.setAmount(temp.getAmount() + item.getAmount());
            return true;
        }
        else{
            return items.add(item);
        }
    }

    //modify the amount of the cart by productID
    public boolean modifyItem(Item item) {
        Item temp = this.findItem(item);
        if(temp != null){
            temp.setAmount(item.getAmount());
            if (temp.getAmount() <= 0){
                items.remove(item);
            }
            return true;
        }
        else{
            if(item.getAmount() > 0)
                return items.add(item);
            else
                return false;
        }
    }

    //delete the item by productID
    public boolean deleteItem(Item item){
        Item temp = this.findItem(item);
        if(temp != null){
            items.remove(temp);
            return true;
        }
        else{
            return false;
        }
    }

    //find the item by productID
    public Item findItem(Item item){
        for(Item it: items){
            if(it.getProduct().getId().equals(item.getProduct().getId())){
                return it;
            }
        }
        return null;
    }

    //empty the cart
    public boolean emptyCart(){
        if(items.size() > 0){
            items.clear();
            return true;
        }
        else
            return false;
    }



    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
