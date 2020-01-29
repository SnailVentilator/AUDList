/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

/**
 * @author fabian
 */
class Node<T> {

	private Node<T> next;
	private T value;

	public Node(T value) {
		this.value = value;
	}

	public Node(Node<T> next, T value) {
		this.next = next;
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}
