package algorithm;

import ReadInput.ReadFile;
import tree.Parcours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
    public static int backpackweight;
    private static final String FILENAME="fileInput/Sac1";
    public static  void main(String args[]){


        List<Item> remainingItems= ReadFile.read(FILENAME);
        System.out.println(remainingItems);
        Collections.sort(remainingItems, Collections.reverseOrder());
        double res=OptimumWithTaken(remainingItems, backpackweight);
        System.out.println(res);
        Parcours parcours= new Parcours();
        parcours.parcoursWithBound(remainingItems,backpackweight,0);
        System.out.println("parcours : Value = "+parcours.MaxValue+" items= " +parcours.MaxState);
    }

    public static double OptimumWithTaken(List<Item> items,int backpackWeight){
        List<Item> onlyNotTaken = new ArrayList<>();
        int remainingWeight=backpackWeight;
        int valueFromAlreadyTakenItem=0;
        for(Item i: items){
            if(i.getTaken()==0){
                onlyNotTaken.add(i);
            }else if(i.getTaken()==1){
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
