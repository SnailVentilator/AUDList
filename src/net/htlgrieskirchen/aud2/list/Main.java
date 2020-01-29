/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author fabian
 */
public class Main {

    private static Random random = new Random();
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private static String generateRandomString(int length) {
        return random.ints(length, 0, CHARACTERS.length).map(i -> CHARACTERS[i])
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static void main(String[] args) {
        BenchmarkFlexer.flex();
        System.exit(0);

        Timer timer;
        ArrayList<String> arrayList = new ArrayList<>();
        MyList<String> myList = new MyList();
        LinkedList<String> linkedList = new LinkedList<>();
        MyLinkedList<String> myLinkedList = new MyLinkedList();

        String[] testStrings = new String[100000];
        for (int i = 0; i < testStrings.length; i++) {
            testStrings[i] = generateRandomString(128);
        }

        timer = new Timer("[ArrayList] Insert 100.000 random strings");
        for (String testString : testStrings) {
            arrayList.add(testString);
        }
        timer.end();

        timer = new Timer("[MyList] Insert 100.000 random strings");
        for (String testString : testStrings) {
            myList.add(testString);
        }
        timer.end();

        timer = new Timer("[LinkedList] Insert 100.000 random strings");
        for (String testString : testStrings) {
            linkedList.add(testString);
        }
        timer.end();

        timer = new Timer("[MyLinkedList] Insert 100.000 random strings");
        for (String testString : testStrings) {
            myLinkedList.add(testString);
        }
        timer.end();

        timer = new Timer("[ArrayList] Search 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            arrayList.contains(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[MyList] Search 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            myList.contains(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[LinkedList] Search 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            linkedList.contains(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[MyLinkedList] Search 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            myLinkedList.contains(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[ArrayList] Remove 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            arrayList.remove(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[MyList] Remove 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            myList.remove(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[LinkedList] Remove 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            linkedList.remove(testStrings[i]);
        }
        timer.end();

        timer = new Timer("[MyLinkedList] Remove 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            myLinkedList.remove(testStrings[i]);
        }
        timer.end();
    }
}
