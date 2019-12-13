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
public class MyList<T> {
    public boolean add(T element) {return false;}
    public void add(int index, T element) {}
    public String get(int index) {return null;}
    public boolean remove(T element) {return false;}
    public String remove(int index) {return null;}
    public String set(int index, T element) {return null;}
    public boolean contains(T element) {return false;}
    public int indexOf(T element) {return 0;}
    public int size() {return 0;}
    public boolean isEmpty() {return false;}
}
