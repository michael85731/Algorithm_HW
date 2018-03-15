import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        // Run test, it will need you to place the test_data folder that TA post in current folder
        TestCase tc = new TestCase("WorstCase");      // Accept Sample or WorstCase as parameter, will generate different result
        tc.process();

        // Or run it manually
        //Net net = new Net(10, "QuickUnion");
        //boolean unionResult = false;
        //printResult(net, true);         // Print initial state
        //unionResult = net.union(0, 9);
        //printResult(net, unionResult);
        //unionResult = net.union(0, 7);
        //printResult(net, unionResult);
        //unionResult = net.union(1, 8);
        //printResult(net, unionResult);
        //unionResult = net.union(0, 8);
        //printResult(net, unionResult);
        //unionResult = net.union(1, 9);
        //printResult(net, unionResult);
    }

    private static void printResult(Net net, boolean result){
        if(result){
            net.print();
        }else{
            System.out.println("Connected.\n");
        }
    }
}
