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
    public void testGet() {
        assertEquals(jl.get(0), ml.get(0));
    }


    @Test
    public void testRemove() {
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
    public void testIndexOf() {
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);

        assertEquals(jl.indexOf(listItem2), ml.indexOf(listItem2));
    }

    @Test
    public void testIsEmpty() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);

        assertEquals(jl.isEmpty(), ml.isEmpty());
    }

    //Borderline Cases!
    @Test
    public void testContainEmpty() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem1 = "test1";

        assertEquals(jl.contains(listItem1), ml.contains(listItem1));

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
