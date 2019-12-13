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
public class Node<T> {
    private Node<T> next;
    private T element;
    
    public Node(T element) {
        this.element = element;
    }

    public Node(Node<T> next, T element) {
        this.next = next;
        this.element = element;
    }
    
    public T getElement() {
        return element;
    }
    
    public T setElement(T element) {
        T old = element;
        this.element = element;
        return old;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
