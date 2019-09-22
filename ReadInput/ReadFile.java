package ReadInput;


import algorithm.Algorithm;
import algorithm.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    /*
    @Param filename : @format, 1st word is "backpack Maxweight" then each line with : "weight value"
    @Return : Array with list of item from file
     */
    public static List<Item> read(String fileName){
        File file = new File(fileName);
        Scanner input= null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Item> items= new ArrayList<>();
        Algorithm.backpackweight=Integer.parseInt(input.nextLine()); // first line = backpack weight
        while (input.hasNextLine()){
            String line = input.nextLine();
            String[] words= line.split(" "); // 2 word expected, weight value
            items.add(new Item(Integer.parseInt(words[0]),Integer.parseInt(words[1])));
        }

        return items;
    }
}
