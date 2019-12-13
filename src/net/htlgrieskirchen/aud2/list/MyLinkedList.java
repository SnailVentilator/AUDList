/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

/**
 *
 * @author fabian
 */
public class MyLinkedList<T> {
    private Node<T> firstNode;

    public <T> MyLinkedList() {
    }
    
    private Node<T> getLastNode() {
        Node<T> lastFound = firstNode;
        while(firstNode.getNext() != null) {
            lastFound = firstNode.getNext();
        }
        return lastFound;
    }
    
    private Node<T> getNthNode(int n) {
        Node<T> lastFound = firstNode;
        for (int i = 0; i < n; i++) {
            if(lastFound == null) throw new IndexOutOfBoundsException();
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
        if(index == -1) return false;
        Node<T> nodeBefore = getNthNode(index-1);
        nodeBefore.setNext(nodeBefore.getNext().getNext());
        return true;
    }

    public T remove(int index) {
        throw new RuntimeException();
    }

    public T set(int index, T element) {
        throw new RuntimeException();
    }

    public boolean contains(T element) {
        throw new RuntimeException();
    }

    public int indexOf(T element) {
        int i = 0;
        Node<T> lastFound = firstNode;
        while(lastFound.getNext() != null) {
            if(lastFound.getElement() == element) return i;
            lastFound = lastFound.getNext();
            i++;
        }
        return -1;
    }

    public int size() {
        throw new RuntimeException();
    }

    public boolean isEmpty() {
        throw new RuntimeException();
    }
}
