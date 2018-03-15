class QuickUnion extends Algorithm{
    public QuickUnion(){
        super();
    }

    public QuickUnion(int[] content, int[] weight){
        super(content, weight);
    }

    @Override
    public int[] union(int firstNode, int secondNode){
        this.net[root(firstNode)] = root(secondNode);
        return this.net;
    }
}
