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
    Node getHeadForTesting() {
        return head;
    }
    public void insert(char element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node newNode = new Node(element);
        if (index == 0) {
            if (head == null) {
                head = newNode;
                head.next = head;
            } else {
                Node temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                newNode.next = head;
                head = newNode;
                temp.next = head;
            }
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
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

    public char delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node temp = head;
        char deletedData;

        if (index == 0) {
            deletedData = head.data;
            if (head.next == head) {
                head = null;
            } else {
                Node last = head;
                while (last.next != head) {
                    last = last.next;
                }
                head = head.next;
                last.next = head;
            }
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            deletedData = temp.next.data;
            temp.next = temp.next.next;
        }
        size--;
        return deletedData;
    }


    public void deleteAll(char element) {
        if (head == null) return;

        Node current = head;
        Node previous = null;
        boolean found = false;


        while (head != null && head.data == element) {
            if (head.next == head) {
                head = null;
                size = 0;
                return;
            } else {
                Node last = head;
                while (last.next != head) {
                    last = last.next;
                }
                head = head.next;
                last.next = head;
                current = head;
                found = true;
                size--;
            }
        }


        do {
            if (current.data == element) {
                previous.next = current.next;
                size--;
                found = true;
            } else {
                previous = current;
            }
            current = current.next;
        } while (current != head);


        if (found && previous != null && previous.next.data == element) {
            previous.next = head;
            size--;
        }
    }

    public SecondaryImplementation clone() {
        SecondaryImplementation newList = new SecondaryImplementation();
        Node temp = head;
        if (temp != null) {
            do {
                newList.append(temp.data);
                temp = temp.next;
            } while (temp != head);
        }
        return newList;
    }


    public void reverse() {
        if (head == null || head.next == head) {
            return;
        }

        Node prev = null;
        Node current = head;
        Node next;
        do {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } while (current != head);

        head.next = prev;
        head = prev;
    }
    public int findFirst(char element) {
        if (head == null) {
            return -1;
        }

        int index = 0;
        Node current = head;

        do {
            if (current.data == element) {
                return index;
            }
            current = current.next;
            index++;
        } while (current != head);

        return -1;
    }


    public int findLast(char element) {
        Node temp = head;
        int index = -1;
        int i = 0;
        if (temp != null) {
            do {
                if (temp.data == element) {
                    index = i;
                }
                temp = temp.next;
                i++;
            } while (temp != head);
        }
        return index;
    }
    public void clear() {
        head = null;
        size = 0;
    }


    public void extend(SecondaryImplementation otherList) {
        if (otherList.head == null) return;

        Node temp = otherList.head;
        do {
            append(temp.data);
            temp = temp.next;
        } while (temp == otherList.head);
    }
}
