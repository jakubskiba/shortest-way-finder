package com.jskiba;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    @Test
    void testStringRepresentation() {
        Vertex vertex = new Vertex(new Node("Warsaw"), new Node("Amsterdam"), 200);

        assertEquals("Warsaw - Amsterdam : 200", vertex.toString());
    }

    @Test
    void testGetOppositeNode1() {
        Node node1 = new Node("Warsaw");
        Node node2 = new Node("Amsterdam");

        Vertex vertex = new Vertex(node1, node2, 200);

        assertEquals(node2, vertex.getOppositeNode(node1));
    }

    @Test
    void testGetOppositeNode2() {
        Node node1 = new Node("Warsaw");
        Node node2 = new Node("Amsterdam");

        Vertex vertex = new Vertex(node1, node2, 200);

        assertEquals(node1, vertex.getOppositeNode(node2));
    }

    @Test
    void testGetOppositeNodeWhenNonPresent() {
        Vertex vertex = new Vertex(new Node("Warsaw"), new Node("Amsterdam"), 200);

        assertNull(vertex.getOppositeNode(new Node("Paris")));
    }

    @Test
    void testHasNode1True() {
        Node node1 = new Node("Warsaw");
        Node node2 = new Node("Amsterdam");

        Vertex vertex = new Vertex(node1, node2, 200);

        assertTrue(vertex.hasNode(node1));
    }

    @Test
    void testHasNode2True() {
        Node node1 = new Node("Warsaw");
        Node node2 = new Node("Amsterdam");

        Vertex vertex = new Vertex(node1, node2, 200);

        assertTrue(vertex.hasNode(node2));
    }

    @Test
    void testHasNodeFalse() {
        Node node1 = new Node("Warsaw");
        Node node2 = new Node("Amsterdam");

        Vertex vertex = new Vertex(node1, node2, 200);

        assertFalse(vertex.hasNode(new Node("Paris")));
    }

}