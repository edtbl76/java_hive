package structures.graphs;

import utils.Generated;

import java.util.LinkedList;

// DFS Technique for undirected graph
@Generated
@SuppressWarnings("java:S106")
public class DFS {

    @Generated
    static class Graph {
        int vertices;
        LinkedList<Integer>[] adjacency;
        @SuppressWarnings("unchecked")
        Graph(int vertices) {
            this.vertices = vertices;
            adjacency = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacency[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {
            adjacency[source].addFirst(destination);
        }

        // Wrappa
        public void recurseDFS(int start) {
            boolean[] visited = new boolean[this.vertices];
            dfs(start, visited);
        }

        public void dfs(int start, boolean[] visited) {

            visited[start] = true;

            System.out.print(start + " ");

            for(int i = 0; i < adjacency[start].size(); i++) {
                int destination = adjacency[start].get(i);
                if (!visited[destination]) {
                    dfs(destination, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        graph.recurseDFS(0);
    }
}

/*

        0
       / |
      1  2
     / | |
    3  4 5

    0 -> 2 -> 5 -> 1 -> 4 -> 3

 */