import java.util.*;

abstract class Algorithm{
    protected int[] net    = null;
    protected int[] weight = null;

    public Algorithm(){
        // Do nothing
    }

    public Algorithm(int[] content, int[] weight){
        this.net = content;
        this.weight = weight;
    }

    public boolean connected(int firstNode, int secondNode){
        return root(net[firstNode]) == root(net[secondNode]) ? true : false;
    }

    public int root(int node){
        while(node != this.net[node]){
            node = this.net[node];
        }
        return node;
    }

    abstract public int[] union(int firstNode, int secondNode);
}
