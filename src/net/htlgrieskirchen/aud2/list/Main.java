/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.Random;

/**
 *
 * @author fabian
 */
public class Main {

    private static Random random = new Random();
    private static final char[] CHARACTERS = {};

    private static String generateRandomString(int length) {
        return random.ints(length, 0, CHARACTERS.length).map(i -> CHARACTERS[i])
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

}
