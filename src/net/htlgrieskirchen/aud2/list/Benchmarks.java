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
public class Benchmarks {

    private static String[] randomValues;

    public static void initialize() {
        randomValues = generateRandomStrings(10000000);
    }

    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static class InsertionBenchmark implements BenchmarkFlexer.Benchmarkable {

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            switch (type) {
                case JavaArrayList:
                    ArrayList<String> javaArrayList = new ArrayList<>();
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.add(randomValues[i]);
                    }
                    return t1.getTime();
                case MyArrayList:
                    MyList<String> myArrayList = new MyList<>();
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.add(randomValues[i]);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    LinkedList<String> javaLinkedList = new LinkedList<>();
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.add(randomValues[i]);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    MyLinkedList<String> myLinkedList = new MyLinkedList<>();
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
                if (BenchmarkFlexer.timeWaster) {
                    myLinkedList.add(randomValues[i]);
                }
            }
        }

        private final ArrayList<String> javaArrayList = new ArrayList<>();
        private final MyList<String> myArrayList = new MyList<>();
        private final LinkedList<String> javaLinkedList = new LinkedList<>();
        private final MyLinkedList<String> myLinkedList = new MyLinkedList<>();

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

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static class RemoveByIndexBenchmark implements BenchmarkFlexer.Benchmarkable {

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            switch (type) {
                case JavaArrayList:
                    ArrayList<String> javaArrayList = new ArrayList<>();
                    for (int i = 0; i < size + 1; i++) {
                        javaArrayList.add(randomValues[i]);

                    }
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.remove(0);
                    }
                    return t1.getTime();
                case MyArrayList:
                    MyList<String> myArrayList = new MyList<>();
                    for (int i = 0; i < size + 1; i++) {
                        myArrayList.add(randomValues[i]);
                    }
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.remove(0);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    LinkedList<String> javaLinkedList = new LinkedList<>();
                    for (int i = 0; i < size + 1; i++) {
                        javaLinkedList.add(randomValues[i]);
                    }
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.remove(0);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    MyLinkedList<String> myLinkedList = new MyLinkedList<>();
                    for (int i = 0; i < size + 1; i++) {
                        myLinkedList.add(randomValues[i]);
                    }
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

    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static class RemoveByValueBenchmark implements BenchmarkFlexer.Benchmarkable {

        @Override
        public long execute(BenchmarkFlexer.ListType type, long size) {
            switch (type) {
                case JavaArrayList:
                    ArrayList<String> javaArrayList = new ArrayList<>();
                    for (int i = 0; i < size + 1; i++) {
                        javaArrayList.add(randomValues[i]);
                    }
                    Timer t1 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaArrayList.remove(randomValues[i]);
                    }
                    return t1.getTime();
                case MyArrayList:
                    MyList<String> myArrayList = new MyList<>();
                    for (int i = 0; i < size + 1; i++) {
                        myArrayList.add(randomValues[i]);
                    }
                    Timer t2 = new Timer();
                    for (int i = 0; i < size; i++) {
                        myArrayList.remove(randomValues[i]);
                    }
                    return t2.getTime();
                case JavaLinkedList:
                    LinkedList<String> javaLinkedList = new LinkedList<>();
                    for (int i = 0; i < size + 1; i++) {
                        javaLinkedList.add(randomValues[i]);
                    }
                    Timer t3 = new Timer();
                    for (int i = 0; i < size; i++) {
                        javaLinkedList.remove(randomValues[i]);
                    }
                    return t3.getTime();
                case MyLinkedList:
                    MyLinkedList<String> myLinkedList = new MyLinkedList<>();
                    for (int i = 0; i < size + 1; i++) {
                        myLinkedList.add(randomValues[i]);
                    }
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
