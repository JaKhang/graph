package org.ngk.graph;


public class Main {
    public static void main(String[] args) {
        UnDirectedGraph<String> graph = new UndirectedIncMatrixGraph<>(6);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B");
        graph.addEdge("A", "A");
        graph.addEdge("A", "C");
        graph.addEdge("A", "F");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");


        System.out.println(graph.degree("C"));
        System.out.println(graph);


    }
}