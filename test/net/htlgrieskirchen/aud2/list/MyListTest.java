/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Birgit.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Andy
 */
public class MyListTest {
    List<String> jl;
    MyList<String> ml;
    @Before
    public void before() {
        jl = new ArrayList<>();
        ml = new MyList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);
    }

    @After
    public void after() {
        
    }

    @Test
    public void testAddAtIndex() {
        jl.add(1, "temp");
        ml.add(1, "temp");
        assertEquals(jl.get(1), ml.get(1));
    }

    @Test
    public void testGet() {
        assertEquals(jl.get(0), ml.get(0));
        assertEquals(jl.get(1), ml.get(1));
    }


    @Test
    public void testRemoveIndex() {
        jl.remove(0);
        ml.remove(0);
        assertEquals(jl.get(0), ml.get(0));
    }

    @Test
    public void testReplace() {
        String replacement = "replaced";
        jl.set(0, replacement);
        ml.set(0, replacement);
        assertEquals(jl.get(0), ml.get(0));
    }

    @Test
    public void testContains() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        assertEquals(jl.contains(listItem2), ml.contains(listItem2));
    }

    @Test
    public void testContainsImpelemtEquals() {
        List<Custom> l1 = new ArrayList<>();
        MyList<Custom> l2 = new MyList<>();
        Custom listItem1 = new Custom(187, "Brigitte");
        Custom listItem2 = new Custom(187, "Brigitte");
        l1.add(listItem1);
        l2.add(listItem1);
        assertEquals(l1.contains(listItem2), l2.contains(listItem2));
    }

    @Test
    public void testIndexOf() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        assertEquals(jl.indexOf(listItem2), ml.indexOf(listItem2));
    }

    @Test
    public void testIsEmptyAndSize() {
        assertEquals(jl.size(), ml.size());
        assertEquals(jl.isEmpty(), ml.isEmpty());
    }

    //Borderline Cases!
    @Test
    public void testContainEmpty() {
        List<String> j1 = new ArrayList<>();
        MyList<String> m1 = new MyList<>();
        String listItem1 = "test1";

        assertEquals(j1.contains(listItem1), m1.contains(listItem1));

    }

    @Test
    public void testRemoveRandomElement() {
        assertEquals(jl.remove("random"), ml.remove("random"));
    }

    @Test
    public void testAddNull() {
        List<String> j1 = new ArrayList<>();
        MyList<String> m1 = new MyList<>();
        j1.add(null);
        m1.add(null);

        assertEquals(j1.get(0), m1.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListGetMyList() {
        MyList<String> m1 = new MyList<>();
        m1.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListGetJava() {
        List<String> j1 = new ArrayList<>();
        j1.get(0);
    }

    @Test
    public void testIndexOfEmptyMyList() {
        MyList<String> m1 = new MyList<>();
        assertEquals(-1, m1.indexOf("test"));
    }

    @Test
    public void testIndexOfEmptyJava() {
        List<String> j1 = new ArrayList<>();
        assertEquals(-1,j1.indexOf("test"));
    }   
}
