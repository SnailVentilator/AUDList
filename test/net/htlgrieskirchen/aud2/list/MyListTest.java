/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Birgit.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Andy
 */
public class MyListTest {
    //TODO: Implement before and after and fix the last two tests(with -1)
    @Before
    public void before() {

    }
    @After
    public void after() {

    }
    @Test
    public void testAddAndGet() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem = "test";
        jl.add(listItem);
        ml.add(listItem);
        assertEquals(jl.get(0), ml.get(0));
    }

    @Test
    public void testAddAtIndex() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);
    }

    @Test
    public void testAddAndRemove() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);
        jl.remove(0);
        ml.remove(0);

        assertEquals(jl.get(0), ml.get(0));
    }

    @Test
    public void testAddAndReplace() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        String replacement = "replaced";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);

        jl.set(0, replacement);
        ml.set(0, replacement);

        assertEquals(jl.get(0), ml.get(0));
    }
    @Test
    public void testAddAndContains() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        String listItem1 = "test1";
        String listItem2 = "test2";
        jl.add(listItem1);
        ml.add(listItem1);
        jl.add(listItem2);
        ml.add(listItem2);

        assertEquals(jl.contains(listItem2), ml.contains(listItem2));
    }
    @Test
    public void testAddAndIndexOf() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
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
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();
        jl.add(null);
        ml.add(null);

        assertEquals(jl.get(0), ml.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListGetMyList() {
        MyList<String> ml = new MyList<>();
        ml.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmptyListGetJava() {
         List<String> jl = new ArrayList<>();
         jl.get(0);
    }

    @Test
    public void testIndexOfEmptyMyList() {
        MyList<String> ml = new MyList<>();

        ml.indexOf("test");
    }

    @Test
    public void testIndexOfEmptyJava() {
        List<String> jl = new ArrayList<>();
        jl.indexOf("test");
    }
}
