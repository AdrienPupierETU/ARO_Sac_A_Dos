package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

    public static  void main(String args[]){
        final int  BACKPACKWEIGHT=18;

        List<Item> RemainingItems= new ArrayList<Item>();
        List<Item> itemsTaken= new ArrayList<Item>();

        RemainingItems.add(new Item(4,48));
        RemainingItems.add(new Item(10,200));
        RemainingItems.add(new Item(2,30));
        RemainingItems.add(new Item(3,30));
        RemainingItems.add(new Item(5,80));

        RemainingItems.get(0).taken=1;
        Collections.sort(RemainingItems, Collections.reverseOrder());
        double res=OptimumWithTaken(RemainingItems,BACKPACKWEIGHT);
        System.out.println(res);
    }

    public static double OptimumWithTaken(List<Item> items,int backpackWeight){
        List<Item> onlyNotTaken = new ArrayList<>();
        int remainingWeight=backpackWeight;
        int valueFromAlreadyTakenItem=0;
        for(Item i: items){
            if(i.taken==0){
                onlyNotTaken.add(i);
            }else if(i.taken==1){
                remainingWeight-=i.weight;
                valueFromAlreadyTakenItem+=i.value;
            }
        }
        return valueFromAlreadyTakenItem+getOptimumFractionaire(onlyNotTaken,remainingWeight);
    }

    /* @Param : list in decreasing order and max Weight in backpack
       @Return: High boundary limit of the backpack problems
     */
    public static double  getOptimumFractionaire(List<Item> items,int backpackWeight){
        double result=0;
        int actualWeightUse=0;
        for(Item i : items){
            if(actualWeightUse==backpackWeight){
                break;
            }

            if(actualWeightUse+i.weight<=backpackWeight){
                actualWeightUse+=i.weight;
                result+=i.value;
            }else{
                double remainingSpace=backpackWeight-actualWeightUse;
                actualWeightUse=backpackWeight;
                double ratio=remainingSpace/i.weight;
                result+=ratio*i.value;
            }
        }
        return result;
    }
}
