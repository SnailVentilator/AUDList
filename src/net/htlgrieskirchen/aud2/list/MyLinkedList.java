/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

/**
 *
 * @author fabian
 * @param <T>
 */
public class MyLinkedList<T> {

    private Node<T> firstNode;

    public <T> MyLinkedList() {
        firstNode = null;
    }

    private Node<T> getLastNode() {
        Node<T> lastLastFound = null;
        Node<T> lastFound = firstNode;
        while (lastFound != null) {
            lastLastFound = lastFound;
            lastFound = lastFound.getNext();
        }
        return lastLastFound;
    }

    private Node<T> getNthNode(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        Node<T> lastFound = firstNode;
        for (int i = 0; i < n; i++) {
            lastFound = lastFound.getNext();
            if (lastFound == null) {
                throw new IndexOutOfBoundsException();
            }
        }

        return lastFound;
    }

    //--------------------------------------------------------------------------
    public boolean add(T s) {
        if (firstNode == null) {
            firstNode = new Node(s);
        } else {
            getLastNode().setNext(new Node(s));
        }
        return true;
    }

    public T get(int index) {
        try {
            return getNthNode(index).getValue();
        } catch (NullPointerException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int indexOf(T s) {
        if (firstNode == null) {
            return -1; //Test case
        }
        Node<T> lastNode = firstNode;
        int index = 0;
        while (lastNode.getNext() != null && !lastNode.getValue().equals(s)) {
            lastNode = lastNode.getNext();
            index++;
        }
        assert lastNode != null; //Shut up, Netbeans
        return lastNode.getValue().equals(s) ? index : -1;
    }

    public T remove(int index) {
        if (index == 0) { //TODO: Test case with 0 and non 0
            T oldValue = firstNode.getValue();
            firstNode = firstNode.getNext();
            return oldValue;
        } else {
            Node<T> nthNode = getNthNode(index - 1);
            T oldValue = nthNode.getNext().getValue();
            nthNode.setNext(nthNode.getNext().getNext());
            return oldValue;
        }
    }

    public boolean contains(T s) {
        return indexOf(s) != -1;
    }

    public T set(int index, T s) {
        Node<T> nthNode = getNthNode(index);
        T oldValue = nthNode.getValue();
        nthNode.setValue(s);
        return oldValue;
    }

    public void add(int index, T s) {
        Node<T> nthNode = getNthNode(index - 1);
        nthNode.setNext(new Node(nthNode.getNext(), s));
    }

    public boolean remove(T s) {
        int indexOf = indexOf(s);
        if (indexOf == -1) {
            return false;
        }

        remove(indexOf);

        //Node<T> nthNode = getNthNode(indexOf - 1);
        //nthNode.setNext(nthNode.getNext().getNext());
        return true;
    }

    public int size() {
        if (firstNode == null) {
            return 0;
        }
        Node<T> lastFound = firstNode;
        int size = 0;
        while (lastFound != null) {
            size++;
            lastFound = lastFound.getNext();
        }
        return size;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }
}
