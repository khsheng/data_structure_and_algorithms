package ADT;

import java.util.Comparator;
import java.util.function.Predicate;

public class ListADT<T> implements InterfaceADT<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size = 0;

    // ================= ADD =================
    @Override
    public void add(T element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
    }

    // ================= ADD AT INDEX =================
    @Override
    public void add(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node newNode = new Node(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1 && current != null; i++) {
                current = current.next;
            }

            if (current == null) {
                add(element);
                return;
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    // ================= GET =================
    @Override
    public T get(int index) {
        inBoundsValidation(index, "get");

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    // ================= REMOVE =================
    @Override
    public void remove(int index) {
        inBoundsValidation(index, "remove");

        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;
        }

        size--;
    }

    // ================= REPLACE =================
    @Override
    public void replace(int index, T element) {
        inBoundsValidation(index, "replace");

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.data = element;
    }

    // ================= CLEAR =================
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    // ================= LEN =================
    @Override
    public int len() {
        return size;
    }

    // ================= FIND =================
    @Override
    public int find(T element) {
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

    // ================= FIND ALL =================
    @Override
    public ListADT<Integer> findAll(T element) {
        ListADT<Integer> result = new ListADT<>();

        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(element)) {
                result.add(index);
            }
            current = current.next;
            index++;
        }

        if (result.len() == 0) {
            result.add(-1);
        }

        return result;
    }

    @Override
    public ListADT<Integer> findAll(Predicate<T> condition) {
        ListADT<Integer> result = new ListADT<>();

        Node current = head;
        int index = 0;

        while (current != null) {
            if (condition.test(current.data)) {
                result.add(index);
            }
            current = current.next;
            index++;
        }

        if (result.len() == 0) {
            result.add(-1);
        }

        return result;
    }

    // ================= SORT =================
    @Override
    public void sort(Comparator<T> comparator) {
        head = mergeSort(head, comparator);
    }

    private Node mergeSort(Node head, Comparator<T> comparator) {
        if (head == null || head.next == null) {
            return head;
        }

        // split list into two halves
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;

        middle.next = null;

        Node left = mergeSort(head, comparator);
        Node right = mergeSort(nextOfMiddle, comparator);

        return merge(left, right, comparator);
    }

    private Node getMiddle(Node head) {
        if (head == null) return head;

        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private Node merge(Node left, Node right, Comparator<T> comparator) {
        Node dummy = new Node(null);
        Node tail = dummy;

        while (left != null && right != null) {
            if (comparator.compare(left.data, right.data) <= 0) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        if (left != null) tail.next = left;
        if (right != null) tail.next = right;

        return dummy.next;
    }


    // ================= TO ARRAY =================
    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];

        Node current = head;
        int i = 0;

        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }

        return arr;
    }

    // ================= COPY =================
    @Override
    public ListADT<T> copy() {
        ListADT<T> newList = new ListADT<>();
        Node current = head;

        while (current != null) {
            newList.add(current.data);
            current = current.next;
        }

        return newList;
    }

    // ================= UTIL =================
    private void inBoundsValidation(int index, String process) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index '" + index + "' not exist and invalid for " + process
            );
        }
    }

    // ================= TO STRING =================
    @Override
    public String toString() {
        if (head == null) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }
}