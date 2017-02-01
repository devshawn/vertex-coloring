package com.devshawn.coloring;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    static PrintWriter output;
    static StringWriter outputString;

    // For testing without writing JUnit tests

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        output = new PrintWriter(System.out);

        while(true) {
            printOptions();
            pickOption();
        }

    }

    static void main(Scanner scanner, StringWriter myOutput) {
        sc = scanner;
        outputString = myOutput;
        output = new PrintWriter(outputString);

        while(true) {
            printOptions();
            pickOption();
        }
    }

    private static void printOptions() {
        output.println("Welcome to the vertex coloring library! Please pick an option below by typing its number:");
        output.println("\r\n\t1. Enter your own graph");
        output.println("\r\n\t2. Generate a graph with a specified number of vertices and percentage of edges");
        output.println("\r\n\t3. Exit program\r\n");
        output.flush();

    }

    private static void pickOption() {
        int option = sc.nextInt();
        sc.nextLine();


        switch(option) {
            case 1:
                enterGraph();
                break;
            case 2:
                generatePercentageGraph();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                output.println("Please choose an option.");
                output.flush();
                break;
        }
    }

    private static void generatePercentageGraph() {
        output.println("Please enter the number of vertices you want:");
        output.flush();

        int vertices = sc.nextInt();
        sc.nextLine();

        output.println("Please enter the percentage at which an edge should be placed (i.e. 30):");
        output.flush();

        double percentage = sc.nextDouble();
        percentage = percentage / (double) 100;
        sc.nextLine();

        int[][] graph = GraphGenerator.simple(vertices, percentage);

        output.println(GraphGenerator.isSymmetric(graph));
        output.flush();

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.GREEDY);
        result.printSummary(output);
        sc.nextLine();
    }

    private static void enterGraph() {
        output.println("Please enter the number of vertices your graph has: ");
        output.flush();

        int vertices = sc.nextInt();
        sc.nextLine();

        output.println("Please enter the adjacency matrix of your graph. Example for |V| = 3: ");
        output.println("011\n101\n110\n");
        output.flush();

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
        result.printSummary(output);
    }
}
