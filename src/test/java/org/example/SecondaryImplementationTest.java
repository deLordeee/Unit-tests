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
        assertSame(first, second.getNext());
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

        list.append('a');
        list.append('b');
        list.append('a');
        list.append('c');
        list.append('a');


        list.deleteAll('a');


        assertEquals(2, list.length(), "Should remove all 'a' characters");


        if (list.length() >= 1) {
            assertEquals('b', list.get(0), "First remaining element should be 'b'");
        }
        if (list.length() >= 2) {
            assertEquals('c', list.get(1), "Second remaining element should be 'c'");
        }


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

        SecondaryImplementation.Node head = list.getHeadForTesting();
        SecondaryImplementation.Node last = head.getNext().getNext();
        assertSame(head, last.getNext());
    }
    @Test
    void testMultipleNodesCircularity() {
        list.append('a');
        list.append('b');
        list.append('c');
        SecondaryImplementation.Node head = list.getHeadForTesting();
        SecondaryImplementation.Node last = head.getNext().getNext();
        assertSame(head, last.getNext());
    }


    @Test
    void testInsertInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert('a', 1));
    }

    @Test
    void testDeleteInvalidIndex() {
        list.append('a');
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1));
    }
    @Test
    void testFindFirstInEmptyList() {
        assertEquals(-1, list.findFirst('a'),
                "Should return -1 for empty list");
    }

    @Test
    void testFindFirstSingleNode() {
        list.append('a');
        assertEquals(0, list.findFirst('a'),
                "Should find character in single-node list");
        assertEquals(-1, list.findFirst('b'),
                "Should return -1 for non-existent character");
    }

    @Test
    void testFindFirstMultipleNodes() {
        list.append('a');
        list.append('b');
        list.append('c');
        list.append('a');
        list.append('d');


        assertEquals(0, list.findFirst('a'),
                "Should find first occurrence of 'a'");

        assertEquals(1, list.findFirst('b'),
                "Should find first occurrence of 'b'");


        assertEquals(2, list.findFirst('c'),
                "Should find first occurrence of 'c'");


        assertEquals(4, list.findFirst('d'),
                "Should find first occurrence of 'd'");

        assertEquals(-1, list.findFirst('z'),
                "Should return -1 for non-existent character");
    }

    @Test
    void testFindFirstCircularVerification() {
        list.append('a');
        list.append('b');
        list.append('a');

        int firstIndex = list.findFirst('a');
        assertEquals(0, firstIndex);


        SecondaryImplementation.Node node = list.getHeadForTesting();
        for (int i = 0; i < firstIndex; i++) {
            node = node.getNext();
        }
        assertEquals('a', node.getData());
    }

    @Test
    void testFindLastInEmptyList() {
        assertEquals(-1, list.findLast('a'),
                "Should return -1 for empty list");
    }

    @Test
    void testFindLastSingleNode() {
        list.append('a');
        assertEquals(0, list.findLast('a'),
                "Should find character in single-node list");
        assertEquals(-1, list.findLast('b'),
                "Should return -1 for non-existent character");
    }

    @Test
    void testFindLastMultipleNodes() {
        list.append('a');
        list.append('b');
        list.append('c');
        list.append('a');
        list.append('d');

        assertEquals(3, list.findLast('a'),
                "Should find last occurrence of 'a'");
        assertEquals(1, list.findLast('b'),
                "Should find only occurrence of 'b'");
        assertEquals(4, list.findLast('d'),
                "Should find last character");
        assertEquals(-1, list.findLast('z'),
                "Should return -1 for non-existent character");
    }

    @Test
    void testFindLastCircularVerification() {
        list.append('a');
        list.append('b');
        list.append('a');
        list.append('c');

        int lastIndex = list.findLast('a');
        assertEquals(2, lastIndex);


        SecondaryImplementation.Node node = list.getHeadForTesting();
        for (int i = 0; i < lastIndex; i++) {
            node = node.getNext();
        }
        assertEquals('a', node.getData());


        assertEquals('c', node.getNext().getData());
    }

    @Test
    void testFindFirstLastWithFullCircle() {

        list.append('a');
        list.append('b');
        list.append('a');
        list.append('b');
        list.append('a');

        assertEquals(0, list.findFirst('a'));
        assertEquals(4, list.findLast('a'));


    }

    @Test
    void testClearEmptyList() {
        list.clear();
        assertEquals(0, list.length());
        assertNull(list.getHeadForTesting());
    }

    @Test
    void testClearSingleElementList() {
        list.append('a');
        list.clear();
        assertEquals(0, list.length());
        assertNull(list.getHeadForTesting());
    }

    @Test
    void testClearMultipleElements() {
        list.append('a');
        list.append('b');
        list.append('c');
        list.clear();
        assertEquals(0, list.length());
        assertNull(list.getHeadForTesting());


        list.append('x');
        assertEquals(1, list.length());
        assertEquals('x', list.get(0));
    }


    @Test
    void testExtendWithEmptyList() {
        SecondaryImplementation other = new SecondaryImplementation();
        list.append('a');
        list.extend(other);
        assertEquals(1, list.length());
        assertEquals('a', list.get(0));
    }

    @Test
    void testExtendWithSingleElementList() {
        SecondaryImplementation other = new SecondaryImplementation();
        other.append('b');

        list.append('a');
        list.extend(other);

        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));


        SecondaryImplementation.Node head = list.getHeadForTesting();
        SecondaryImplementation.Node last = head.getNext();
        assertSame(head, last.getNext());
    }

    @Test
    void testExtendWithMultipleElements() {
        SecondaryImplementation other = new SecondaryImplementation();
        other.append('b');
        other.append('c');
        other.append('d');

        list.append('a');
        list.extend(other);

        assertEquals(4, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('c', list.get(2));
        assertEquals('d', list.get(3));


        SecondaryImplementation.Node current = list.getHeadForTesting();
        for (int i = 0; i < 4; i++) {
            current = current.getNext();
        }
        assertSame(list.getHeadForTesting(), current);
    }

    @Test
    void testExtendEmptyListWithNonEmpty() {
        SecondaryImplementation other = new SecondaryImplementation();
        other.append('a');
        other.append('b');

        list.extend(other);

        assertEquals(2, list.length());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
    }



    @Test
    void testExtendMaintainsSeparateLists() {
        SecondaryImplementation other = new SecondaryImplementation();
        other.append('x');
        other.append('y');

        list.append('a');
        list.extend(other);
        other.append('z');

        assertEquals(3, list.length());
        assertEquals('a', list.get(0));
        assertEquals('x', list.get(1));
        assertEquals('y', list.get(2));


        assertEquals(3, other.length());
    }
}