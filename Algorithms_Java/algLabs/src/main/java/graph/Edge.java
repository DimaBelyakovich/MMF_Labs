package graph;

public class Edge{
    private Vertex from;
    private Vertex to;
    private int cost;

    public Edge() {
        this.from = null;
        this.to = null;
        this.cost = 0;
    }

    public Edge(int cost, Vertex from, Vertex to){
        if (from == null || to == null)
            throw (new NullPointerException("Both 'to' and 'from' vertices need to be non-NULL."));

        this.cost = cost;
        this.from = from;
        this.to = to;
    }

    public Edge(Edge e){
        this(e.cost, e.from, e.to);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Vertex getFromVertex(){
        return this.from;
    }

    public Vertex getToVertex(){
        return this.to;
    }

    @Override
    public boolean equals(Object e1) {
        if (!(e1 instanceof Edge))
            return false;

        final Edge e = (Edge) e1;

        final boolean costs = this.cost == e.cost;
        if (!costs)
            return false;

        final boolean from = this.from.equals(e.from);
        if (!from)
            return false;

        final boolean to = this.to.equals(e.to);
        if (!to)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int cost = (this.cost * (this.getFromVertex().hashCode() * this.getToVertex().hashCode()));
        return 31 * cost;
    }

    public int compareTo(Edge e) {
        if (this.cost < e.cost)
            return -1;
        if (this.cost > e.cost)
            return 1;

        final int from = this.from.compareTo(e.from);
        if (from != 0)
            return from;

        final int to = this.to.compareTo(e.to);
        if (to != 0)
            return to;

        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ").append(from.getValue()).append("]").append(" -> ")
                .append("[ ").append(to.getValue()).append("]").append(" = ").append(cost).append("\n");
        return builder.toString();
    }
}
