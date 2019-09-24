package algorithm;

import ReadInput.ReadFile;
import tree.Parcours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
    public static int backpackweight;
    private static final String FILENAME="src/fileInput/Sac4";
    public static  void main(String args[]){


        List<Item> items= ReadFile.read(FILENAME);
        System.out.println(items);
        Collections.sort(items, Collections.reverseOrder());
        System.out.println(items);
        double res=getOptimumFractionaire(items, backpackweight,0,0);
        System.out.println(res);
        Parcours parcours= new Parcours();
        parcours.BranchAndBound(items,backpackweight,0,0);
        System.out.println("parcours : Value = "+parcours.MaxValue+" items= " +parcours.MaxState);
        int value=0;
        for(int i =0;i<items.size();i++){
            if(Parcours.MaxState.get(i)){
                value+=items.get(i).value;
            }
        }
        System.out.println(value);
    }


    /* @Param : list in decreasing order and max Weight in backpack
       @Return: High boundary limit of the backpack problems
     */
    public static double  getOptimumFractionaire(List<Item> items,int backpackWeight,int height,double value){
        double result=0;
        int actualWeightUse=0;
        for(int i =height;i<items.size();i++){
            Item courant=items.get(i);
            if(actualWeightUse+courant.weight<=backpackWeight){
                actualWeightUse+=courant.weight;
                result+=courant.value;
            }else{
                double remainingSpace=backpackWeight-actualWeightUse;
                double ratio=remainingSpace/courant.weight;
                result+=ratio*courant.value;
                break;
            }
        }
        return value+result;
    }
}
