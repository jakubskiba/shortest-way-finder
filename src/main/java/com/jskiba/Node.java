package com.jskiba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Node {
    private String name;
    private Boolean wasLooked;
    private Integer distance;
    private Optional<Node> previousNode;

    public Node(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
        this.wasLooked = false;
        this.previousNode = Optional.ofNullable(null);
    }

    public void setAsStart() {
        this.distance = 0;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = Optional.ofNullable(previousNode);
    }

    public String getName() {
        return name;
    }

    public Boolean getWasLooked() {
        return wasLooked;
    }

    public void setWasLooked(Boolean wasLooked) {
        this.wasLooked = wasLooked;
    }

    public boolean hasPreviousNode() {
        return this.previousNode.isPresent();
    }

    public Optional<Node> getPreviousNode() {
        return previousNode;
    }

    public List<String> getRoute() {
        List<String> route = new ArrayList<>();
        Node backTrack = this;
        while(backTrack.hasPreviousNode()) {
            route.add(backTrack.getName());
            backTrack = backTrack.getPreviousNode().get();
        }
        route.add(backTrack.getName());

        Collections.reverse(route);

        return route;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
