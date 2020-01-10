/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aaigner18
 */
public class MyLinkedListTest {
    List<String> jl;
    MyLinkedList<String> mll;
    @Before
    public void before() {
        jl = new ArrayList<>();
        mll = new MyLinkedList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        mll.add(listItem1);
        jl.add(listItem2);
        mll.add(listItem2);
    }
    
    @Test
    public void testGet() {
        assertEquals(jl.get(0), mll.get(0));
    }


    @Test
    public void testRemove() {
        jl.remove(0);
        mll.remove(0);
        assertEquals(jl.get(0), mll.get(0));
    }

    @Test
    public void testReplace() {
        String replacement = "replaced";
        jl.set(0, replacement);
        mll.set(0, replacement);
        assertEquals(jl.get(0), mll.get(0));
    }

    @Test
    public void testContains() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        assertEquals(jl.contains(listItem2), mll.contains(listItem2));
    }

    @Test
    public void testIndexOf() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        assertEquals(jl.indexOf(listItem2), mll.indexOf(listItem2));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(jl.isEmpty(), mll.isEmpty());
    }

    //Borderline Cases!
    @Test
    public void testContainEmpty() {
        List<String> j1 = new ArrayList<>();
        MyLinkedList<String> m1 = new MyLinkedList<>();
        String listItem1 = "test1";

        assertEquals(j1.contains(listItem1), m1.contains(listItem1));

    }

    @Test
    public void testAddNull() {
        List<String> j1 = new ArrayList<>();
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
        List<String> j1 = new ArrayList<>();
        j1.get(0);
    }

    @Test
    public void testIndexOfEmptyMyLinkedList() {
        MyLinkedList<String> m1 = new MyLinkedList<>();
        assertEquals(-1, m1.indexOf("test"));
    }

    @Test
    public void testIndexOfEmptyJava() {
        List<String> j1 = new ArrayList<>();
        assertEquals(-1,j1.indexOf("test"));
    }   
}
