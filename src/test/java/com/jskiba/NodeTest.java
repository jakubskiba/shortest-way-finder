package com.jskiba;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testStringRepresentation() {
        Node node = new Node("Warsaw");

        assertEquals("Warsaw", node.toString());
    }

}