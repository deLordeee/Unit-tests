package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;

public class SecondaryImplementationTest {
    private SecondaryImplementation list;

    @BeforeEach
    void setUp() {
        list = new SecondaryImplementation();
    }


    @Test
    void testEmptyList() {
        assertEquals(0, list.length());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testAppend() {
        list.append('a');
        assertEquals(1, list.length());
        assertEquals('a', list.get(0));
    }

    @Test
    void testMultipleAppends() {
        list.append('a');
        list.append('b');
        list.append('c');
        assertEquals(3, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('c', list.get(2));
    }


    @Test
    void testCircularLinks() {
        list.append('a');
        list.append('b');

        SecondaryImplementation.Node first = list.getHeadNodeForTesting();
        SecondaryImplementation.Node second = first.getNext();

        assertEquals('a', first.getData());
        assertEquals('b', second.getData());
        assertSame(first, second.getNext()); // Verify circularity
    }


    @Test
    void testSingleNodeCircularity() {
        list.append('x');
        SecondaryImplementation.Node node = list.getHeadNodeForTesting();
        assertSame(node, node.getNext());
    }


}