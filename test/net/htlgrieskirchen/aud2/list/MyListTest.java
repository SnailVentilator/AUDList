/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author fabian
 */
public class MyListTest {
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

    @Test
    public void testEmptyListGet() {
        List<String> jl = new ArrayList<>();
        MyList<String> ml = new MyList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            jl.get(0);
            ml.get(0);
        });
    }
}
