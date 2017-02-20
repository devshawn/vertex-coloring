package com.devshawn.coloring.library;

import java.util.Comparator;

public class Vertex implements Comparable<Vertex> {
    private int id;
    private int degrees;

    public Vertex(int id, int degrees) {
        this.id = id;
        this.degrees = degrees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public int compareTo(Vertex other) {
        return other.degrees - degrees;
    }
}
