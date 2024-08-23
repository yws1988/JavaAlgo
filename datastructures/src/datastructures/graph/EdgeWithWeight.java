package datastructures.graph;

public class EdgeWithWeight implements Comparable<EdgeWithWeight>{
    public int src;
    public int des;
    public int weight;

    public EdgeWithWeight(int src, int des, int weight) {
        this.src = src;
        this.des = des;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeWithWeight o) {
        return this.weight-o.weight;
    }
}
