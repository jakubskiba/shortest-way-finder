package com.jskiba;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    @Nested
    class GraphData {
        Graph graph;

        @BeforeEach
        void initGraph() {
            this.graph = new Graph();
        }

        @Test
        void testGetNodes() {
            List<Node> expectedNodes = new ArrayList<>();

            expectedNodes.add(this.graph.createNode("Warsaw"));
            expectedNodes.add(this.graph.createNode("Amsterdam"));

            List<Node> actualNodes = this.graph.getNodes();

            assertEquals(expectedNodes, actualNodes);
        }

        @Test
        void testRepetitiveNodes() {
            List<Node> expectedNodes = new ArrayList<>();


            expectedNodes.add(this.graph.createNode("Warsaw"));
            this.graph.createNode("Warsaw");
            expectedNodes.add(this.graph.createNode("Amsterdam"));

            List<Node> actualNodes = this.graph.getNodes();

            assertEquals(expectedNodes, actualNodes);
        }

        @Test
        void testGetVertices() {
            List<Vertex> expectedVertices = new ArrayList<>();

            expectedVertices.add(graph.createVertex("Warsaw", "Amsterdam", 100));
            expectedVertices.add(graph.createVertex("Amsterdam", "London", 300));

            List<Vertex> actualVertices = this.graph.getVertices();

            assertEquals(expectedVertices, actualVertices);
        }

        @Test
        void testGetNodeWhenPresent() {
            Node expectedNode = graph.createNode("Warsaw");
            assertEquals(expectedNode, graph.getNode("Warsaw").get());
        }

        @Test
        void testGetNodeIsPresent() {
            graph.createNode("Warsaw");
            assertTrue(graph.getNode("Warsaw").isPresent());
        }

        @Test
        void testGetNodeNotPresent() {
            graph.createNode("Amsterdam");
            assertFalse(graph.getNode("Warsaw").isPresent());
        }
    }

    @Nested
    class Algorithm {

        Graph graph;

        @BeforeEach
        void initGraph() {
            this.graph = new Graph();
            this.graph.createVertex("Warsaw", "Paris", 100);
            this.graph.createVertex("Paris", "Lisboa", 300);
            this.graph.createVertex("Lisboa", "London", 250);
            this.graph.createVertex("Warsaw", "London", 450);
            this.graph.createVertex("London", "Athens", 500);
            this.graph.createVertex("Lisboa", "Athens", 800);
        }

        @Test
        void testGetDistance() {
            Node from = this.graph.getNode("Warsaw").get();
            Node to = this.graph.getNode("Athens").get();

            this.graph.computeRoads(from);

            assertEquals(Integer.valueOf(950), to.getDistance());
        }

        @Test
        void testGetRoute() {
            Node from = this.graph.getNode("Warsaw").get();
            Node to = this.graph.getNode("Athens").get();

            List<String> expectedRoute = new ArrayList<>();
            expectedRoute.add("Warsaw");
            expectedRoute.add("London");
            expectedRoute.add("Athens");

            this.graph.computeRoads(from);

            assertEquals(expectedRoute, to.getRoute());
        }

    }
}