package tree;

import algorithm.Algorithm;
import algorithm.Item;

import java.util.ArrayList;
import java.util.List;

public class Parcours {
    public List<Boolean> MaxState= new ArrayList<>();
    public int MaxValue=0;

    public void parcoursWithBound(List<Item> items,double weightRemaining,int height){
        if(items.size()-1>=height){ //node
            if(weightRemaining-items.get(height).weight>0){
                items.get(height).setTaken(1);
                if(Algorithm.OptimumWithHeight(items,weightRemaining,height)>MaxValue){
                    parcoursWithBound(items,(weightRemaining-items.get(height).weight),height+1); //left
                }

            }
            items.get(height).setTaken(2);
            if(Algorithm.OptimumWithHeight(items,weightRemaining,height)>MaxValue){
                parcoursWithBound(items,weightRemaining,height+1); //right
            }

        }else{ //feuille
            int value=0;
            for(Item i: items){
                if(i.getTaken()==1){
                    value+=i.value;
                }
            }
            if(MaxValue<value){
                if(MaxState.size()!=items.size()){
                    for(int i =0;i<items.size();i++){
                        MaxState.add(false);
                    }
                }
                for(int i =0;i<items.size();i++){
                    if(items.get(i).getTaken()==1){
                        MaxState.set(i,true);
                    }else {
                        MaxState.set(i, false);
                    }
                }
                MaxValue=value;
            }
        }
    }

}
