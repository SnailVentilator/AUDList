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
class Node<T> {

    private Node<T> next;
    private T value;

    public Node(T value) {
        this.value = value;
    }

    public Node(Node<T> next, T element) {
        this.next = next;
        this.value = element;
    }

    public T getValue() {
        return value;
    }

    public T setValue(T element) {
        T old = element;
        this.value = element;
        return old;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
