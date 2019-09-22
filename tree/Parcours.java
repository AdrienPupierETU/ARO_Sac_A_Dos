package tree;

import algorithm.Item;

import java.util.List;

public class Parcours {


    public void parcoursWithBound(List<Item> items,int height){
        if(items.size()-1<height){
            items.get(height).taken=1;
            parcoursWithBound(items,height++); //left
            items.get(height).taken=2;
            parcoursWithBound(items,height++); //right
        }
    }

}
