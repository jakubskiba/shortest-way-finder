package com.jskiba;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private List<Node> nodes = new ArrayList<>();
    private List<Vertex> vertices = new ArrayList<>();

    public Node createNode(String name) {
        Optional<Node> node = nodes.stream()
                .filter(n -> n.getName().equals(name))
                .findAny();

        if (!node.isPresent()) {
            node = Optional.of(new Node(name));
            nodes.add(node.get());
        }

        return node.get();
    }

    public Vertex createVertice(String node1Name, String node2Name, Integer value) {

        Node node1 = this.createNode(node1Name);
        Node node2 = this.createNode(node2Name);

        Vertex vertex = new Vertex(node1, node2, value);

        this.vertices.add(vertex);

        return vertex;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * This method implements Dijkstra Algorithm
     * than computes shortest roads to all nodes in graph
     *
     * Computational complexity : O (n2)
     *
     * @param from - start node
     */
    public void computeRoads(Node from) {
        List<Node> searchedNodes = new ArrayList<>(this.nodes);
        searchedNodes.stream().forEach(node -> node.setWasLooked(false));
        from.setAsStart();

        while(!searchedNodes.isEmpty()) {
            Node nearestNode = findNearestNode(searchedNodes);

            searchedNodes.remove(nearestNode);
            nearestNode.setWasLooked(true);

            this.computeDistances(nearestNode);
        }
    }

    private Node findNearestNode(List<Node> nodeList) {
        return nodeList.stream()
                       .filter(node -> !node.getWasLooked())
                       .min(Comparator.comparingInt(Node::getDistance))
                       .get();
    }

    private void computeDistances(Node nearestNode) {
        for(Vertex vertex : getAllNeightbors(nearestNode)) {

            Node oppositeNode = vertex.getOppositeNode(nearestNode);
            Integer newDistance = nearestNode.getDistance() + vertex.getValue();

            if(oppositeNode.getDistance() > newDistance) {
                oppositeNode.setDistance(newDistance);
                oppositeNode.setPreviousNode(nearestNode);
            }
        }
    }



    public List<Vertex> getAllNeightbors(Node node) {
        return this.vertices.stream()
                            .filter(vertex -> vertex.hasNode(node))
                            .collect(Collectors.toList());


    }

    public Optional<Node> getNode(String name) {
        return this.nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findAny();
    }

}
