package structures.graphs;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings("unused")
public class Graph {

    int vertices;
    List<List<GNode>> adjacencyList = new ArrayList<>();

    public Graph(List<Edge> edges) {
        // allocate mem for adjacency
        for (int i = 0; i < edges.size(); i++) {
            adjacencyList.add(i, new ArrayList<>());
        }

        // add edges
        edges.forEach(edge -> adjacencyList.get(edge.source)
                .add(new GNode(edge.destination, edge.weight)));
    }


    @SuppressWarnings({"java:S106", "unused"})
    public void printMatrix() {
        int sourceVertex = 0;
        int listSize = this.adjacencyList.size();

        while (sourceVertex < listSize) {
            // traverse adj matrix and print edges
            int finalSourceVertex = sourceVertex;
            this.adjacencyList.get(sourceVertex)
                    .forEach(edge -> System.out.print("Vertex: "
                            + finalSourceVertex
                            + " ==> " + edge.value
                            + " (" + edge.weight + ")\t"));

            System.out.println();
            sourceVertex++;
        }
    }
}
