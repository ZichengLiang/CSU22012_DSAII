package Graph;

import java.util.List;

public class Vertex {
    boolean visited;
    String name;
    List<Vertex> adjs;

    Vertex(String name, List<Vertex> adjs) {
        this.name = name;
        this.adjs = adjs;
        this.visited = false;
    }
}
