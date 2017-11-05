package com.jskiba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    Node node;

    @BeforeEach
    void initNode() {
        this.node = new Node("Warsaw");
    }

    @Test
    void testGetName() {
        assertEquals("Warsaw", this.node.getName());
    }

    @Test
    void testStringRepresentation() {
        assertEquals("Warsaw", this.node.toString());
    }

    @Test
    void testDefaultDistance() {
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), this.node.getDistance());
    }

    @Test
    void testDefaultWasLooked() {
        assertFalse(this.node.getWasLooked());
    }

    @Test
    void testDefaultPreviousNode() {
        assertFalse(this.node.getPreviousNode().isPresent());
    }

    @Test
    void testDefaultHasPreviousNode() {
        assertFalse(this.node.hasPreviousNode());
    }

    @Test
    void testStartDistance() {
        this.node.setAsStart();
        assertEquals(Integer.valueOf(0), this.node.getDistance());
    }

    @Test
    void testSetDistance() {
        this.node.setDistance(10);
        assertEquals(Integer.valueOf(10), this.node.getDistance());
    }

    @Test
    void testSetPreviousNode() {
        Node someNode = new Node("Paris");
        this.node.setPreviousNode(someNode);
        assertEquals(someNode, this.node.getPreviousNode().get());
    }

    @Test
    void testHasPreviousNodeTrue() {
        Node someNode = new Node("Paris");
        this.node.setPreviousNode(someNode);
        assertTrue(this.node.hasPreviousNode());
    }

    @Test
    void testSetWasLocked() {
        this.node.setWasLooked(true);
        assertTrue(this.node.getWasLooked());
    }

}