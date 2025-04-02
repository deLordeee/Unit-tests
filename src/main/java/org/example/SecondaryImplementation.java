package org.example;



public class SecondaryImplementation {
    private Node head;
    private int size;


    public static class Node {
        private final char data;
        private Node next;

        Node(char data) {
            this.data = data;
        }


        char getData() {
            return data;
        }

        Node getNext() {
            return next;
        }
    }
    public Node getHeadNodeForTesting() {
        return head;
    }

    public SecondaryImplementation() {
        this.head = null;
        this.size = 0;
    }


    public int length() {
        return size;
    }


    public void append(char element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        size++;
    }


    public char get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }



}
