package org.ngk.graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnDirectedAdjListGraphTest {


    // Test cases for Graph<T> methods
    @Test
    public void givenEmptyGraph_whenAddVertex_thenVertexIsAdded() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();

        // When
        boolean result = graph.addVertex(1);

        // Then
        assertTrue(result);
        assertTrue(graph.containVertex(1));
        assertEquals(1, graph.size());
    }

    @Test
    public void givenGraphWithTwoVertices_whenAddEdge_thenEdgeIsAdded() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);

        // When
        boolean result = graph.addEdge(1, 2);

        // Then
        assertTrue(result);
        assertTrue(graph.containEdge(1, 2));
        assertEquals(1, graph.numberOfEdges());
    }

    @Test
    public void givenGraphWithVertexAndEdges_whenRemoveVertex_thenVertexAndEdgesAreRemoved() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        // When
        Integer removedVertex = graph.removeVertex(1);

        // Then
        assertEquals(Integer.valueOf(1), removedVertex);
        assertFalse(graph.containVertex(1));
        assertFalse(graph.containEdge(1, 2));
        assertFalse(graph.containEdge(1, 3));
        assertEquals(2, graph.size());
        assertEquals(0, graph.numberOfEdges());
    }

    @Test
    public void givenGraphWithoutLoopsOrMultipleEdges_whenIsSimpleGraph_thenReturnTrue() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        // When
        boolean isSimple = graph.isSimpleGraph();

        // Then
        assertTrue(isSimple);
    }

    @Test
    public void givenGraphWithVerticesAndEdges_whenBFS_thenVerticesAreVisitedInOrder() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        List<Integer> visitedOrder = new ArrayList<>();

        // When
        graph.bfs(1,visitedOrder::add);
        graph.bfs(3,System.out::println);

        // Then
        assertEquals(List.of(1, 2, 3), visitedOrder);
    }

    @Test
    public void givenGraphWithVerticesAndEdges_whenDFS_thenVerticesAreVisitedInOrder() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        List<Integer> visitedOrder = new ArrayList<>();

        // When
        graph.dfs(1,visitedOrder::add);

        // Then
        assertEquals(List.of(1, 2, 3), visitedOrder);
    }

    @Test
    public void givenBipartiteGraph_whenIsBipartiteGraph_thenReturnTrue() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);


        // When
        boolean isBipartite = graph.isBipartiteGraph();

        // Then
        assertTrue(isBipartite);
    }

    @Test
    public void givenCompleteGraph_whenIsCompletedGraph_thenReturnTrue() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // When
        boolean isComplete = graph.isCompletedGraph();

        // Then
        assertTrue(isComplete);
    }

    @Test
    public void givenGraphWithEdge_whenRemoveEdge_thenEdgeIsRemoved() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        // When
        boolean result = graph.removeEdge(1, 2);

        // Then
        assertTrue(result);
        assertFalse(graph.containEdge(1, 2));
        assertEquals(0, graph.numberOfEdges());
    }

    // Test cases for UnDirectedGraph<T> methods
    @Test
    public void givenGraphWithVertexAndEdges_whenDegree_thenReturnCorrectDegree() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        // When
        int degreeOfVertex1 = graph.degree(1);

        // Then
        assertEquals(2, degreeOfVertex1);
    }

    @Test
    public void givenGraphWithVerticesAndEdges_whenTotalDegree_thenReturnCorrectTotalDegree() {
        // Given
        UnDirectedGraph<Integer> graph = new UnDirectedAdjListGraph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // When
        int totalDegree = graph.totalDegree();

        // Then
        assertEquals(6, totalDegree); // Each edge contributes 2 to the total degree
    }
}