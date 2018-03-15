import java.util.*;

class WeightedQuickUnion extends Algorithm{
    public WeightedQuickUnion(){
        super();
    }

    public WeightedQuickUnion(int[] content, int[] weight){
        super(content, weight);
    }

    @Override
    public int[] union(int firstNode, int secondNode){
        int firstRoot    = this.root(firstNode);
        int secondRoot   = this.root(secondNode);
        int firstWeight  = this.weight[firstRoot];
        int secondWeight = this.weight[secondRoot];

        if(firstWeight > secondWeight){
            this.net[secondRoot] = this.net[firstRoot];
        }else if(firstWeight == secondWeight){
            this.net[firstRoot] = this.net[secondRoot];
            this.weight[secondRoot] += 1;
        }else{
            this.net[firstRoot] = this.net[secondRoot];
        }

        return this.net;
    }
}
