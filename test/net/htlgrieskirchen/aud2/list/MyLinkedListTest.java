/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aaigner18
 */
public class MyLinkedListTest {

    List<String> jll;
    MyLinkedList<String> mll;

    @Before
    public void before() {
        jll = new LinkedList<>();
        mll = new MyLinkedList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jll.add(listItem1);
        mll.add(listItem1);
        jll.add(listItem2);
        mll.add(listItem2);
    }

    @Test
    public void testGet() {
        assertEquals(jll.get(0), mll.get(0));
    }

    @Test
    public void testAddAtIndex() {
        jll.add(1, "temp");
        mll.add(1, "temp");
        assertEquals(jll.get(1), mll.get(1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(jll.isEmpty(), mll.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(jll.size(), mll.size());
    }

    @Test
    public void testSizeOfEmpty() {
        jll.remove(0);
        jll.remove(0);
        mll.remove(0);
        mll.remove(0);
        assertEquals(jll.size(), mll.size());
    }

    @Test
    public void testRemoveAtIndex() {
        jll.remove(0);
        mll.remove(0);
        assertEquals(jll.get(0), mll.get(0));
    }

    public void testRemove() {
        String listItem1 = "test1";
        jll.remove(listItem1);
        mll.remove(listItem1);
        assertEquals(jll.get(0), mll.get(0));
    }

    @Test
    public void testSet() {
        String temp = "replaced";
        jll.set(0, temp);
        mll.set(0, temp);
        assertEquals(jll.get(0), mll.get(0));
    }

    @Test
    public void testSetBefore() {
        String temp = "replaced";
        assertEquals(jll.set(1, temp), mll.set(1, temp));
    }

    @Test
    public void testContains() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        assertEquals(jll.contains(listItem2), mll.contains(listItem2));
    }

    @Test
    public void testIndexOf() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        assertEquals(jll.indexOf(listItem2), mll.indexOf(listItem2));
    }

    @Test
    public void testIsEmptyOnEmpty() {
        jll.remove(0);
        jll.remove(0);
        mll.remove(0);
        mll.remove(0);
        assertEquals(jll.isEmpty(), mll.isEmpty());
    }

    //Borderline Cases!
    @Test
    public void testContainEmpty() {
        List<String> j1 = new LinkedList<>();
        MyLinkedList<String> m1 = new MyLinkedList<>();
        String listItem1 = "test1";

        assertEquals(j1.contains(listItem1), m1.contains(listItem1));

    }

    @Test
    public void testAddNull() {
        List<String> j1 = new LinkedList<>();
        MyLinkedList<String> m1 = new MyLinkedList<>();
        j1.add(null);
        m1.add(null);

        assertEquals(j1.get(0), m1.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListGetMyLinkedList() {
        MyLinkedList<String> m1 = new MyLinkedList<>();
        m1.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListGetJava() {
        List<String> j1 = new LinkedList<>();
        j1.get(0);
    }

    @Test
    public void testIndexOfEmptyMyLinkedList() {
        MyLinkedList<String> m1 = new MyLinkedList<>();
        assertEquals(-1, m1.indexOf("test"));
    }

    @Test
    public void testIndexOfEmptyJava() {
        List<String> j1 = new LinkedList<>();
        assertEquals(-1, j1.indexOf("test"));
    }

    //Borderline cases!
    @Test
    public void testSizeWithDoubleElement() {
        jll.add("test1");
        mll.add("test1");
        assertEquals(jll.size(), mll.size());
    }
}
