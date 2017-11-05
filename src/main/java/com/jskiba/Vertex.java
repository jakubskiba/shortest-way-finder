package com.jskiba;

public class Vertex {
    private Node node1;
    private Node node2;
    private Integer value;

    public Vertex(Node node1, Node node2, Integer value) {
        this.node1 = node1;
        this.node2 = node2;
        this.value = value;
    }

    public Node getOppositeNode(Node node) {
        if(this.node1 == node) {
            return this.node2;
        } else if (this.node2 == node) {
            return this.node1;
        } else {
            return null;
        }
    }

    public Integer getValue() {
        return value;
    }

    public boolean hasNode(Node node) {
        return this.node1 == node || this.node2 == node;
    }

    @Override
    public String toString() {
        return String.format("%s - %s : %d", this.node1, this.node2, this.value);
    }
}
