/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list.benchmarks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import net.htlgrieskirchen.aud2.list.BenchmarkFlexer;
import net.htlgrieskirchen.aud2.list.MyLinkedList;
import net.htlgrieskirchen.aud2.list.MyList;

/**
 *
 * @author fabian
 */
public class Benchmarks {

    private static String[] randomValues;

    public static void initialize() {
        randomValues = generateRandomStrings(1000000);
    }

    public static class InsertionBenchmark implements BenchmarkFlexer.Benchmarkable {

        private ArrayList<String> javaArrayList;
        private MyList<String> myArrayList;
        private LinkedList<String> javaLinkedList;
        private MyLinkedList<String> myLinkedList;

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            switch (type) {
                case JavaArrayList:
                    javaArrayList = new ArrayList<>();
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.add(randomValues[i]);
                    }
                    return t1.getTime();
                case MyArrayList:
                    myArrayList = new MyList<>();
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.add(randomValues[i]);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    javaLinkedList = new LinkedList<>();
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.add(randomValues[i]);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    myLinkedList = new MyLinkedList<>();
                    Timer t4 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myLinkedList.add(randomValues[i]);
                    }
                    return t4.getTime();
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public String getTitle() {
            return "Insertion into List";
        }

    }

    public static class ContainsBenchmark implements BenchmarkFlexer.Benchmarkable {

        public ContainsBenchmark(int size) {
            for (int i = 0; i < size; i++) {
                javaArrayList.add(randomValues[i]);
                myArrayList.add(randomValues[i]);
                javaLinkedList.add(randomValues[i]);
                //TODO: myLinkedList.add(randomValues[i]);
            }
        }

        private ArrayList<String> javaArrayList = new ArrayList<>();
        private MyList<String> myArrayList = new MyList<>();
        private LinkedList<String> javaLinkedList = new LinkedList<>();
        private MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            switch (type) {
                case JavaArrayList:
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.contains(randomValues[i]);
                    }
                    return t1.getTime();
                case MyArrayList:
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.contains(randomValues[i]);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.contains(randomValues[i]);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    Timer t4 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myLinkedList.contains(randomValues[i]);
                    }
                    return t4.getTime();
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public String getTitle() {
            return "Contains in List";
        }

    }

    public static class RemoveByIndexBenchmark implements BenchmarkFlexer.Benchmarkable {

        private ArrayList<String> javaArrayList = new ArrayList<>();
        private MyList<String> myArrayList = new MyList<>();
        private LinkedList<String> javaLinkedList = new LinkedList<>();
        private MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            javaArrayList = new ArrayList<>();
            myArrayList = new MyList<>();
            javaLinkedList = new LinkedList<>();
            myLinkedList = new MyLinkedList<>();

            for (int i = 0; i < size + 1; i++) {
                javaArrayList.add(randomValues[i]);
                myArrayList.add(randomValues[i]);
                javaLinkedList.add(randomValues[i]);
                //TODO: myLinkedList.add(randomValues[i]);
            }

            switch (type) {
                case JavaArrayList:
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.remove(0);
                    }
                    return t1.getTime();
                case MyArrayList:
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.remove(0);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.remove(0);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    Timer t4 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myLinkedList.remove(0);
                    }
                    return t4.getTime();
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public String getTitle() {
            return "Remove from List by Index";
        }

    }

    public static class RemoveByValueBenchmark implements BenchmarkFlexer.Benchmarkable {

        private ArrayList<String> javaArrayList = new ArrayList<>();
        private MyList<String> myArrayList = new MyList<>();
        private LinkedList<String> javaLinkedList = new LinkedList<>();
        private MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            javaArrayList = new ArrayList<>();
            myArrayList = new MyList<>();
            javaLinkedList = new LinkedList<>();
            myLinkedList = new MyLinkedList<>();

            for (int i = 0; i < size + 1; i++) {
                javaArrayList.add(randomValues[i]);
                myArrayList.add(randomValues[i]);
                javaLinkedList.add(randomValues[i]);
                //TODO: myLinkedList.add(randomValues[i]);
            }

            switch (type) {
                case JavaArrayList:
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.remove(randomValues[i]);
                    }
                    return t1.getTime();
                case MyArrayList:
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.remove(randomValues[i]);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.remove(randomValues[i]);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    Timer t4 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myLinkedList.remove(randomValues[i]);
                    }
                    return t4.getTime();
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public String getTitle() {
            return "Remove from List by Value";
        }

    }

    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private static String[] generateRandomStrings(int size) {
        Random random = new Random();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.ints(32, 0, CHARACTERS.length).map(j -> CHARACTERS[j])
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        }
        return values;
    }

    private static class Timer {

        private final long startingTime;

        public Timer() {
            this.startingTime = System.currentTimeMillis();
        }

        public long getTime() {
            return System.currentTimeMillis() - startingTime;
        }
    }
}
