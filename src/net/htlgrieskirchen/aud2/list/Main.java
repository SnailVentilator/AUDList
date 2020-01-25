/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.ArrayList;
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
        MyList<String> myList = new MyList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        
        String[] testStrings = new String[100000];
        for (int i = 0; i < testStrings.length; i++) {
            testStrings[i] = generateRandomString(128);
        }
        
        timer = new Timer("[MyList] Insert 100.000 random strings");
        for (String testString : testStrings) {
            myList.add(testString);
        }
        timer.end();
        
        timer = new Timer("[ArrayList] Insert 100.000 random strings");
        for (String testString : testStrings) {
            arrayList.add(testString);
        }
        timer.end();
        
        //TODO: choose random strings
        
        timer = new Timer("[MyList] Search 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            myList.contains(testStrings[i]);
        }
        timer.end();
        
        timer = new Timer("[ArrayList] Search 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            arrayList.contains(testStrings[i]);
        }
        timer.end();
        
        timer = new Timer("[MyList] Remove 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            myList.remove(testStrings[i]);
        }
        timer.end();
        
        timer = new Timer("[ArrayList] Remove 10.000 random strings");
        for (int i = 0; i < 10000; i++) {
            arrayList.remove(testStrings[i]);
        }
        timer.end();
    }
}
