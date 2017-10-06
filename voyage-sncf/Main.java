package com.voyage.sncf;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author minh-
 */
public class Main {
    // TODO: Generalizing for every input object collection
    // TODO: Returning list of possible packs but not only print out
    public static void packaging(Integer[] itemsList){
        int numberOfItems = itemsList.length-1;
        int nextItem = numberOfItems;
        ArrayList<Integer> pack = new ArrayList();
        if (numberOfItems > 1){
            for (int i = 0; i < numberOfItems; i ++){
                if(i > nextItem)
                    break;
                
                int sum = 0;
                pack.add(itemsList[i]);
                for(int j = nextItem; j >= i; j--){
                    sum = pack.stream()
                            .reduce(0,(u, size) -> u += size, 
                                    (sum1, sum2) -> sum1 + sum2);
                    if(sum + itemsList[j] <=10)
                        pack.add(itemsList[j]);
                    else{
                        nextItem = j;
                        break;}
                }
                
                // Print the possible pack
                pack.stream().forEach(s -> {System.out.print(s+";");});
                System.out.print("/");
                pack.clear();
                
            }
        }
        else
            System.out.println("Only one package needed");
    }
    
    public static void main(String[] args){
        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        
        String inputLine = "163841689525773";
        
        Arrays.asList(inputLine.split("")).stream().forEach(s 
                -> arraylist.add(Integer.parseInt(s)));
        
        // Sort Item by size (desc)
        // TODO: Generalize object item by implements Comparable
        Collections.sort(arraylist, Collections.reverseOrder());
        System.out.print("Input items sorted by size: ");
        arraylist.stream().forEach(s -> {System.out.print(s+";");});
        System.out.println();
        
        // 
        System.out.println("Possible packs: ");
        packaging((arraylist.toArray(new Integer[arraylist.size()])));
    }
}

class Item implements Comparable {
    private String itemName;
    private int itemSize;

    public Item(String name, int size) {
         this.itemSize = size;
         this.itemName = name;
    }

    public String getItemName() {
         return itemName;
    }
    public void setItemName(String name) {
	this.itemName = name;
    }
    public int getItemSize() {
	return itemSize;
    }
    public void setItemSize(int size) {
	this.itemSize = size;
    }
    
    @Override
    public int compareTo(Object item) {
        int size=((Item)item).getItemSize();
        return size - this.itemSize;
    }

}
