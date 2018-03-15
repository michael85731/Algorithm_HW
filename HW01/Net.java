import java.util.*;
import java.io.*;

class Net{
    private int[] content       = null;
    private int[] weight        = null;
    private Algorithm algorithm = null;
    private boolean printWeight = false;

    public Net(int size, String algorithmName){
        // Create nodes and initialize weight
        this.content = new int[size];
        this.weight = new int[size];
        for(int i = 0 ; i < size; i++){
            this.content[i] = i;
        }

        // Set algorithm
        try{
            Object object = Class.forName(algorithmName).getConstructor(int[].class, int[].class).newInstance(this.content, this.weight);
            this.algorithm = (Algorithm)object;  //Downcasting variable object into Algorithm class
        }catch(Exception e){
            e.printStackTrace();
        }

        // Set print weight property(true if using WeightQuickUnion)
        this.printWeight = algorithmName.equals("WeightedQuickUnion") ? true : false;
    }

    // Will return is execute the union action, if already connected then it will return false(not execute union)
    public boolean union(int firstNode, int secondNode){
        if(this.algorithm.connected(firstNode, secondNode)){
            return false;
        }else{
            this.content = this.algorithm.union(firstNode, secondNode);
            return true;
        }
    }

    public void print(){
        // Print value
        System.out.print("value" + "\t");
        for(int i = 0 ; i < this.content.length ; i++){
            System.out.print(i + "\t");
        }
        System.out.println("");

        // Print father
        System.out.print("father" + "\t");
        for(int i = 0 ; i < this.content.length ; i++){
            System.out.print(this.content[i] + "\t");
        }
        System.out.println("");

        // Print weihgt(optional)
        if(this.printWeight){
            System.out.print("weight" + "\t");
            for(int i = 0 ; i < this.content.length ; i++){
                System.out.print(this.algorithm.weight[i] + "\t");
            }
            System.out.println("");
        }

        // Separate each union result
        System.out.println("");
    }

    public void writeToFile(PrintWriter pw){
        // Print value
        pw.print("value" + "  ");
        for(int i = 0 ; i < this.content.length ; i++){
            pw.print(i + " ");
        }
        pw.println("");

        // Print father
        pw.print("father" + " ");
        for(int i = 0 ; i < this.content.length ; i++){
            pw.print(this.content[i] + " ");
        }
        pw.println("");

        // Print wiehgt(optional)
        if(this.printWeight){
            pw.print("weight" + " ");
            for(int i = 0 ; i < this.content.length ; i++){
                pw.print(this.algorithm.weight[i] + " ");
            }
            pw.println("");
        }

        // Separate each union result
        pw.println("");
    }
}
