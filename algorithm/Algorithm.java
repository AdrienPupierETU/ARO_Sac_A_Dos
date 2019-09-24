package algorithm;

import ReadInput.ReadFile;
import tree.Parcours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
    public static int backpackweight;
    private static final String FILENAME="src/fileInput/Sac2";
    public static  void main(String args[]){


        List<Item> items= ReadFile.read(FILENAME);
        System.out.println(items);
        Collections.sort(items, Collections.reverseOrder());
        System.out.println(items);
        double res=getOptimumFractionaire(items, backpackweight);
        System.out.println(res);
        Parcours parcours= new Parcours();
        parcours.parcoursWithBound(items,backpackweight,0);
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

    public static double OptimumWithHeight(List<Item> items,double RemainingBackpackWeight,int height){
        List<Item> toDoOptimum = new ArrayList<>();
        for(int i=height+1;i<items.size();i++){
            toDoOptimum.add(items.get(i));
        }
        int value=0;
        for(int j=0;j<=height;j++){
            Item courant = items.get(j);
            if(courant.getTaken()==1){
                value+=courant.value;
            }
        }
        return value+getOptimumFractionaire(toDoOptimum,RemainingBackpackWeight);
    }


    /* @Param : list in decreasing order and max Weight in backpack
       @Return: High boundary limit of the backpack problems
     */
    public static double  getOptimumFractionaire(List<Item> items,double backpackWeight){
        double result=0;
        double actualWeightUse=0;
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
