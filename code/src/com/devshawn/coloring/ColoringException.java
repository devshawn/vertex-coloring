package com.devshawn.coloring;

public class ColoringException extends RuntimeException {

    public static String INVALID_MATRIX = "Your adjacency matrix is invalid.";
    public static String SELF_LOOPS = "Your graph is not a simple graph. It contains self-loops.";

    public ColoringException (String message) {
        super(message);
    }

}
