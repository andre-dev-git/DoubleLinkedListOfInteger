public class DoubledLinkedListOfInteger {
    Node head;
    Node tail;
    int size;

    public DoubledLinkedListOfInteger() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private class Node {
        Integer data;
        Node prev;
        Node next;

        public Node(Integer data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    } 

    public void add(int index, Integer data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node newNode = new Node(data);

        if (index == 0) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(Integer element) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int indexOf(Integer element) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; 
    }

}
