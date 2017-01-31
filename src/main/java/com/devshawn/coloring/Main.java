package com.devshawn.coloring;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    // For testing without writing JUnit tests

    public static void main(String[] args) {

        while(true) {
            printOptions();
            pickOption();
        }

    }

    private static void printOptions() {
        System.out.println("Welcome to the vertex coloring library! Please pick an option below by typing its number:");
        System.out.println("\r\n\t1. Enter your own graph");
        System.out.println("\r\n\t2. Generate a graph with a specified number of vertices and number of edges");
        System.out.println("\r\n\t3. Generate a graph with a specified number of vertices and percentage of edges\r\n");
    }

    private static void pickOption() {
        int option = sc.nextInt();
        sc.nextLine();


        switch(option) {
            case 1:
                enterGraph();
                break;
            case 2:
                System.out.println("Oops! Not implemented yet!");
                break;
            case 3:
                generatePercentageGraph();
                break;
            default:
                System.out.println("Please choose an option.");
                break;
        }
    }

    private static void generatePercentageGraph() {
        System.out.println("Please enter the number of vertices you want:");

        int vertices = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter the percentage at which an edge should be placed (i.e. 30):");
        double percentage = sc.nextDouble();
        percentage = percentage / (double) 100;
        sc.nextLine();

        int[][] graph = GraphGenerator.simple(vertices, percentage);

        System.out.println(GraphGenerator.isSymmetric(graph));

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.GREEDY);
        result.printSummary();
    }

    private static void enterGraph() {
        System.out.println("Please enter the number of vertices your graph has: ");

        int vertices = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter the adjacency matrix of your graph. Example for |V| = 3: ");
        System.out.println("011\n101\n110\n");


        int[][] graph = new int[vertices][];

        for(int i = 0; i < vertices; i++) {
            String[] line = sc.nextLine().split("");
            int[] numbers = new int[vertices];
            for(int j = 0; j < line.length; j++) {
                numbers[j] = Integer.parseInt(line[j]);
            }
            graph[i] = numbers;
        }


        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.GREEDY);
        result.printSummary();
    }
}
