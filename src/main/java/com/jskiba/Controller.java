package com.jskiba;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class Controller
{

    public void start(PrintStream out, InputStream in) throws InvalidInputException {
        UserInterface UI = new UserInterface(out, in);

        List<String> lines = UI.getData();

        Graph graph = parseGraphData(lines);

        String[] splitLine = lines.get(lines.size() - 1).split(" ");
        Node from = findNode(splitLine[0].trim(), graph);
        Node to = findNode(splitLine[1].trim(), graph);

        graph.computeRoads(from);
        UI.presentData(to);
    }


    private Graph parseGraphData(List<String> lines) throws InvalidInputException {
        Graph graph = new Graph();

        for(int i = 0; i < lines.size() - 1; i++) {
            parseVertex(graph, lines.get(i));
        }

        return graph;
    }

    private void parseVertex(Graph graph, String line) throws InvalidInputException {
        String[] words = line.split(" ");
        checkVertexData(words);
        String node1Name = words[0];
        String node2Name = words[1];
        Integer distance = Integer.valueOf(words[2]);

        graph.createVertice(node1Name, node2Name, distance);

    }

    private void checkVertexData(String[] words) throws InvalidInputException {
        checkWordAmount(words);
        checkVertexDistance(words[2]);
    }

    private void checkVertexDistance(String value) throws InvalidInputException {
        if(!value.matches("[0-9]+") || value.matches("0")) {
            throw new InvalidInputException("Distance should be positive integer! Given value " + value);
        }
    }

    private void checkWordAmount(String[] words) throws InvalidInputException {
        if(words.length != 3) {
            throw new InvalidInputException("Graph data should be in format: Node Node Distance");
        }
    }

    public Node findNode(String name, Graph graph) throws InvalidInputException {
        Optional<Node> nodeBuffer = graph.getNode(name);
        if(nodeBuffer.isPresent()) {
            return nodeBuffer.get();
        } else {
            throw new InvalidInputException("No such node!");
        }
    }


}
