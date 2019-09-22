package tree;

import algorithm.Item;

import java.util.List;

public class Parcours {
    public List<Item> MaxState;
    public int MaxValue=0;

    public void parcoursWithBound(List<Item> items,double weightRemaining,int height){
        if(items.size()-1>=height){ //node
            if(weightRemaining-items.get(height).weight>0){
                items.get(height).setTaken(1);
                parcoursWithBound(items,(weightRemaining-items.get(height).weight),height+1); //left
            }
            items.get(height).setTaken(2);
            parcoursWithBound(items,weightRemaining,height+1); //right
        }else{ //feuille
            int value=0;
            for(Item i: items){
                if(i.getTaken()==1){
                    value+=i.value;
                }
            }
            if(MaxValue<value){
                MaxState=items;
                MaxValue=value;
            }
        }
    }

}
