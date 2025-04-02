package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.SecondaryImplementation.Node;
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
    @Test
    void testInsertAtBeginning() {
        list.append('b');
        list.insert('a', 0);
        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
    }

    @Test
    void testInsertAtEnd() {
        list.append('a');
        list.insert('b', 1);
        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
    }

    @Test
    void testInsertInMiddle() {
        list.append('a');
        list.append('c');
        list.insert('b', 1);
        assertEquals(3, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('c', list.get(2));
    }
    @Test
    void testDeleteOnlyElement() {
        list.append('a');
        char removed = list.delete(0);
        assertEquals('a', removed);
        assertEquals(0, list.length());
    }

    @Test
    void testDeleteFirstElement() {
        list.append('a');
        list.append('b');
        list.append('c');
        char removed = list.delete(0);
        assertEquals('a', removed);
        assertEquals(2, list.length());
        assertEquals('b', list.get(0));
        assertEquals('c', list.get(1));
    }

    @Test
    void testDeleteLastElement() {
        list.append('a');
        list.append('b');
        list.append('c');
        char removed = list.delete(2);
        assertEquals('c', removed);
        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
    }
    @Test
    void testDeleteAll() {
        // Setup
        list.append('a');
        list.append('b');
        list.append('a');
        list.append('c');
        list.append('a');

        // Action
        list.deleteAll('a');

        // Verification
        assertEquals(2, list.length(), "Should remove all 'a' characters");

        // Check remaining elements
        if (list.length() >= 1) {
            assertEquals('b', list.get(0), "First remaining element should be 'b'");
        }
        if (list.length() >= 2) {
            assertEquals('c', list.get(1), "Second remaining element should be 'c'");
        }

        // Verify circularity is maintained
        if (list.length() > 0) {
            Node head = list.getHeadForTesting();
            Node current = head;
            int count = 0;
            do {
                count++;
                current = current.getNext();
            } while (current != head && count <= list.length());

            assertEquals(count, list.length(), "Circular structure broken");
        }
    }

    @Test
    void testReverse() {
        list.append('a');
        list.append('b');
        list.append('c');
        list.reverse();
        assertEquals(3, list.length());
        assertEquals('c', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('a', list.get(2));
        // Verify circularity is maintained
        SecondaryImplementation.Node head = list.getHeadForTesting();
        SecondaryImplementation.Node last = head.getNext().getNext();
        assertSame(head, last.getNext());
    }
}