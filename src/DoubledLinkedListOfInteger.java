import java.util.Arrays;

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


    //Método que criei para termos a possibilidade de adicionar o
    public void add(Integer data){
        this.add(size, data);
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

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index <= size / 2) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.data;
        } else {
            Node current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current.data;
        }
    }

    public Integer set(int index, Integer e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Integer oldValue = current.data;
        current.data = e;
        return oldValue;
    }

    public int removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            Integer removedValue = head.data;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return removedValue;
        } else if (index == size - 1) {
            Integer removedValue = tail.data;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
            return removedValue;
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Integer removedValue = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
            return removedValue;
        }
    }

    public boolean removeAll(Integer element) {
        boolean removed = false;
        Node current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                removed = true;
            }
            current = current.next;
        }
        return removed;
    }

    public int[] subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Invalid fromIndex or toIndex");
        }

        int[] subArray = new int[toIndex - fromIndex];
        Node current = head;
        for (int i = 0; i < toIndex; i++) {
            if (i >= fromIndex) {
                subArray[i - fromIndex] = current.data;
            }
            current = current.next;
        }
        return subArray;
    }

    public void sort() {
        Integer[] arr = new Integer[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        Arrays.sort(arr, (a, b) -> b - a);

        current = head;
        for (int i = 0; i < size; i++) {
            current.data = arr[i];
            current = current.next;
        }
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        Node current = head;
        Node temp = null;

        Node tempNode = head;
        head = tail;
        tail = tempNode;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
    }

    public int contaOcorrencias(Integer element) {
        int count = 0;
        Node current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

}
