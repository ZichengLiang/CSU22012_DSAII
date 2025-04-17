package Graph;

import java.util.ArrayList;
import java.util.List;

import Graph.Patent;

/**
 * A flow network for Patents, storing capacities for each directed edge.
 */
public class PatentFlowNetwork {

    private final PatentIndex patentIndex; // maps Patent -> integer ID
    private final int V;                   // number of vertices
    private int E;                         // number of edges
    private List<FlowEdge>[] adj;          // adjacency list: for each vertex, list of FlowEdges out of it

    // A small class to hold the capacity/flow data for an edge
    public static class FlowEdge {
        private final int from;
        private final int to;
        private final double capacity;
        private double flow; // actually flow, this will change with the augmenting path

        public FlowEdge(int from, int to, double capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0.0;
        }

        public int from()          { return from; }
        public int to()            { return to; }
        public double capacity()   { return capacity; }
        public double flow()       { return flow; }

        /**
         * The residual capacity toward 'vertex' — how much more flow we can send in that direction.
         * If 'vertex' == to, residual capacity is capacity - flow (forward edge).
         * If 'vertex' == from, residual capacity is flow (back edge).
         */
        public double residualCapacityTo(int vertex) {
            if (vertex == to) {
                return capacity - flow; // forward edge -- not full (if full -> 0)
            } else if (vertex == from) {
                return flow;            // backward edge -- not empty (if empty -> 0)
            } else {
                throw new IllegalArgumentException("Invalid endpoint");
            }
        }

        /**
         * Add delta flow toward 'vertex'.
         * If vertex == to, we are pushing more flow forward.
         * If vertex == from, we are reducing flow (pushing it back).
         */
        public void addResidualFlowTo(int vertex, double delta) {
            if (vertex == to) {
                flow += delta;
            } else if (vertex == from) {
                flow -= delta;
            } else {
                throw new IllegalArgumentException("Invalid endpoint");
            }
        }

        public String toString() {
            return String.format("(%d->%d) flow=%.1f/%.1f", from, to, flow, capacity);
        }
    }

    public PatentFlowNetwork(List<Patent> patents) { // Constructor
        // Build the PatentIndex
        this.patentIndex = new PatentIndex();
        for (Patent p : patents) {
            patentIndex.getOrCreateId(p);
        }
        this.V = patentIndex.size();
        this.E = 0;

        // adjacency list
        adj = (List<FlowEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    /**
     * Add a directed edge from 'fromP' to 'toP' with capacity 'cap'.
     * We'll also add a parallel "back" edge for the residual graph with capacity 0 (initially).
     */
    public void addCitation(Patent fromP, Patent toP, double cap) {
        int from = patentIndex.getOrCreateId(fromP);
        int to   = patentIndex.getOrCreateId(toP);

        // forward edge
        FlowEdge fwd = new FlowEdge(from, to, cap);
        // backward edge (capacity 0 by default)
        FlowEdge rev = new FlowEdge(to, from, 0.0);

        // Add them to adjacency
        adj[from].add(fwd);
        adj[to].add(rev);

        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public PatentIndex getPatentIndex() {
        return patentIndex;
    }

    /**
     * Adjacency list for vertex v: all FlowEdges emanating from v.
     */
    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }
}
