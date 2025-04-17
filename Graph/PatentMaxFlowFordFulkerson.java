package Graph;

import java.util.*;

import Graph.Patent;
import Graph.PatentFlowNetwork;

/**
 * Ford–Fulkerson (BFS) to compute max flow in a PatentFlowNetwork.
 */
public class PatentMaxFlowFordFulkerson {

    private double value;  // total max flow
    private boolean[] marked;
    private PatentFlowNetwork.FlowEdge[] edgeTo;

    /**
     * Run Ford–Fulkerson from a given source Patent to a sink Patent.
     */
    public PatentMaxFlowFordFulkerson(PatentFlowNetwork G, Patent source, Patent sink) {
        int s = G.getPatentIndex().getId(source);
        int t = G.getPatentIndex().getId(sink);

        value = 0.0;
        // Repeatedly find augmenting paths via BFS
        while (true) {
            double bottleneck = bfs(G, s, t);
            // It executes a bfs everytime
            // What will this return in the first run?
            if (bottleneck == 0) {
                break;  // no augmenting path -- The ONLY EXIT condition
            }

            /* --- The following codes will be executed when an augmenting path still exists --- */
            value += bottleneck;

            // Walk back from target to source, adding flow along that path
            int v = t;
            while (v != s) {
                // walk from target to source, following the path in the last bfs run
                PatentFlowNetwork.FlowEdge e = edgeTo[v];
                // If e.from() == prev vertex, we push flow forward
                // If e.to() == v, we do e.addResidualFlowTo(v, bottleneck)
                e.addResidualFlowTo(v, bottleneck);
                // v iteration
                v = e.from();
            }
            /* --- In each bfs iteration, we "disable" one edge --- */
        }
    }

    /**
     *  It returns the value of bottleneck along the path (which path?) from s to t.
     *  If the return value is 0, then there is NO augmenting path (?)
     */
    private double bfs(PatentFlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new PatentFlowNetwork.FlowEdge[G.V()];
        // An array of FlowEdge: FlowEdge keep record of from, to, currentFlow, capacity
        Arrays.fill(edgeTo, null);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (PatentFlowNetwork.FlowEdge e : G.adj(v)) { // each time, it runs through the current vertex v's adj list (Incidence list actually?? Because adjacency is used for vertices
                int w = e.to(); // w is the forward vertex on edge e
                // If there's residual capacity and w not visited in this round of bfs, we can go there
                // In the first run, every edge has full residual capacity
                if (!marked[w] && e.residualCapacityTo(w) > 0) {
                    // edgeTo is intialised in bfs method
                    edgeTo[w] = e;    // remember how we got to w in the class level in this bfs run
                    // bfs basic..
                    marked[w] = true;
                    queue.offer(w);

                    // If we reached target, compute bottleneck by walking edgeTo[] back up
                    if (w == t) { // t is the index of target vertex
                        double bottleneck = Double.POSITIVE_INFINITY; // it will definitely get updated
                        int x = t;  // current vertex
                        while (x != s) { // return the bottleneck along the path, it will indicate a full fwd / empty bwd if they exist
                            PatentFlowNetwork.FlowEdge edge = edgeTo[x]; // 1st: edge = edge to t (isn't it e?)
                            bottleneck = Math.min(bottleneck, edge.residualCapacityTo(x)); // Then it must be edge.residualCapacityTo(x) no??
                            x = edge.from(); // sourcing...
                        }
                        // it will return the edge with least capacity in the first run
                        return bottleneck;
                    }
                }
            }
        }

        // no path found
        return 0.0;
    }

    /**
     * Returns the max flow value found
     */
    public double value() {
        return value;
    }
}
