package com.devshawn.coloring.library;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class GraphGeneratorSpec {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void simple() {
        int[][] graph = GraphGenerator.simple(1000, 0.3);
        assertEquals(true, GraphGenerator.isSymmetric(graph));
    }

    @Test
    public void badProbability() throws ColoringException {
        thrown.expect(ColoringException.class);
        thrown.expectMessage(ColoringException.INVALID_PROBABILITY);

        int[][] graph = GraphGenerator.simple(4, 2);
    }

    @Test
    public void isNotSymmetric() {
        int[][] graph = {
                {1, 1},
                {0, 1}
        };

        assertEquals(false, GraphGenerator.isSymmetric(graph));
    }

    @Test
    public void isSymmetric() {
        int[][] graph = {
                {0, 1},
                {1, 0}
        };

        assertEquals(true, GraphGenerator.isSymmetric(graph));
    }

    @Test
    public void generateAdjacencyMatrix() {
        List<Tuple> edges = new ArrayList<>();
        edges.add(new Tuple(0, 1));
        edges.add(new Tuple(1, 2));
        edges.add(new Tuple(0, 2));
        int[][] graph = GraphGenerator.generateAdjacencyMatrix(3, edges);
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph.length; j++) {
                assertEquals((i == j) ? 0 : 1, graph[i][j]);
            }
        }

        String str = GraphGenerator.adjacencyMatrixToString(graph);
        assertTrue(str.equals("011\n101\n110\n"));
    }

    @Test
    public void generateCrownGraph() {
        List<Tuple> edges = new ArrayList<>();
        edges.add(new Tuple(0, 1));
        edges.add(new Tuple(0, 2));
        edges.add(new Tuple(0, 5));
        edges.add(new Tuple(0, 6));
        edges.add(new Tuple(1, 3));
        edges.add(new Tuple(1, 4));
        edges.add(new Tuple(1, 7));
        edges.add(new Tuple(2, 3));
        edges.add(new Tuple(2, 4));
        edges.add(new Tuple(2, 8));
        edges.add(new Tuple(3, 5));
        edges.add(new Tuple(3, 9));
        edges.add(new Tuple(4, 6));
        edges.add(new Tuple(4, 9));
        edges.add(new Tuple(5, 7));
        edges.add(new Tuple(5, 8));
        edges.add(new Tuple(6, 7));
        edges.add(new Tuple(6, 8));
        edges.add(new Tuple(7, 9));
        edges.add(new Tuple(8, 9));
        int[][] graph = GraphGenerator.generateAdjacencyMatrix(10, edges);

        String str = GraphGenerator.adjacencyMatrixToString(graph);
        System.out.println(str);
    }
}
