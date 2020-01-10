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
    }

    private Node<T> getLastNode() {
        if (firstNode == null) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> lastFound = firstNode;
        while (lastFound.getNext() != null) {
            lastFound = lastFound.getNext();
        }
        return lastFound;
    }

    private Node<T> getNthNode(int n) {
        Node<T> lastFound = firstNode;
        for (int i = 0; i < n; i++) {
            if (lastFound == null) {
                throw new IndexOutOfBoundsException();
            }
            lastFound = lastFound.getNext();
        }
        return lastFound;
    }

    public boolean add(T element) {
        getLastNode().setNext(new Node<>(element));
        return true;
    }

    public void add(int index, T element) {
        Node<T> nthNode = getNthNode(index);
        Node<T> oldNode = nthNode.getNext();
        nthNode.setNext(new Node<>(oldNode, element));
    }

    public T get(int index) {
        return getNthNode(index).getElement();
    }

    public boolean remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public T remove(int index) {
        Node<T> nodeBefore = getNthNode(index - 1);
        T element = nodeBefore.getElement();
        nodeBefore.setNext(nodeBefore.getNext().getNext());
        return element;
    }

    public T set(int index, T element) {
        Node<T> node = getNthNode(index);
        T old = node.getElement();
        node.setElement(element);
        return old;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        int i = 0;
        Node<T> lastFound = firstNode;
        while (lastFound.getNext() != null) {
            if (lastFound.getElement() == element) {
                return i;
            }
            lastFound = lastFound.getNext();
            i++;
        }
        return -1;
    }

    public int size() {
        int i = 0;
        Node<T> lastFound = firstNode;
        while (lastFound.getNext() != null) {
            lastFound = lastFound.getNext();
            i++;
        }
        return i;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }
}
