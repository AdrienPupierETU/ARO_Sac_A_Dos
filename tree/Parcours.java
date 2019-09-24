package tree;

import algorithm.Algorithm;
import algorithm.Item;

import java.util.ArrayList;
import java.util.List;

public class Parcours {
    public static List<Boolean> MaxState= new ArrayList<>();
    public double MaxValue=0;

    public void BranchAndBound(List<Item> items, int weightRemaining, int height,double value){
        if(items.size()>height &&Algorithm.getOptimumFractionaire(items,weightRemaining,height,value)>MaxValue){ //node
            if(weightRemaining-items.get(height).weight>=0){
                items.get(height).setTaken(1);
                BranchAndBound(items,(weightRemaining-items.get(height).weight),height+1,value+items.get(height).value); //left (taken)
            }
            items.get(height).setTaken(2);
            BranchAndBound(items,weightRemaining,height+1,value); //right (not taken)
        }else{ //feuille
            setValueAndState(items,value);
        }
    }


    private void setValueAndState(List<Item> items,double value){
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
