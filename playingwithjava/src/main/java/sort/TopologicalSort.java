package sort;

import utils.Generated;

import java.util.LinkedList;
import java.util.Stack;

@Generated
@SuppressWarnings("java:S106")
public class TopologicalSort {


    @Generated
    @SuppressWarnings("java:S1149")
    static class Graph {
        int vertices;
        LinkedList<Integer>[] adjacency;
        @SuppressWarnings("unchecked")
        Graph(int vertices) {
            this.vertices = vertices;
            this.adjacency = new LinkedList[this.vertices];
            for (int i = 0; i < vertices; i++) {
                this.adjacency[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {
            adjacency[source].addFirst(destination);
        }

        // Dr. Dre -> Rapper, get it?
        public void sort() {
            boolean[] visited = new boolean[this.vertices];

            Stack<Integer> integerStack = new Stack<>();

            // visit!
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    sortRecursive(i, visited, integerStack);
                }
            }
            System.out.println("Topological Sort: ");
            int size = integerStack.size();
            for (int i = 0; i < size; i++) {
                System.out.print(integerStack.pop() + " ");
            }
        }

        public void sortRecursive(int start, boolean[] visited, Stack<Integer> stack) {
            // mark first visited
            visited[start] = true;

            for (int i = 0; i < adjacency[start].size(); i++) {
                int vertex = adjacency[start].get(i);
                if(!visited[vertex]) {
                    sortRecursive(vertex, visited, stack);
                }
            }
            stack.push(start);

        }

    }

    public static void main(String[] args) {
        int vertices = 5;

        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        graph.sort();
    }

}
