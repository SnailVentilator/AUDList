/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.Objects;

/**
 * @author fabian
 */
@SuppressWarnings("unchecked")
public class MyList<T> {

	private static final int INITIAL_SIZE = 10;
	private Object[] array;
	private int size;

	public MyList() {
		this(INITIAL_SIZE);
	}

	public MyList(int initialSize) {
		array = new Object[initialSize];
	}

	private void ensureCapacity(int capacity) {
		if(array.length >= capacity) {
			return;
		}
		Object[] newArray = new Object[array.length * 2];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	private void checkBounds(int index) {
		if(index >= 0 && index < size) {
			return;
		}
		throw new IndexOutOfBoundsException();
	}

	public boolean add(T element) {
		ensureCapacity(size + 1);
		array[size] = element;
		size++;
		return true;
	}

	public void add(int index, T element) {
		ensureCapacity(size + 1);
		System.arraycopy(array, index, array, index + 1, size - index);
		size++;
		array[index] = element;
	}

	public T get(int index) {
		checkBounds(index);
		return (T) array[index];
	}

	public boolean remove(T element) {
		int index = indexOf(element);
		if(index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	public T remove(int index) {
		System.arraycopy(array, index + 1, array, index, size - index);
		size--;
		return null;
	}

	public T set(int index, T element) {
		ensureCapacity(index + 1);
		T old = (T) array[index];
		array[index] = element;
		return old;
	}

	public boolean contains(T element) {
		for(Object object : array) {
			if(Objects.equals(object, element)) {
				return true;
			}
		}
		return false;
	}

	public int indexOf(T element) {
		for(int i = 0; i < array.length; i++) {
			T currentElement = (T) array[i];
			if(currentElement == element) {
				return i;
			}
		}
		return -1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
