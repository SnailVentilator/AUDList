/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. Brigitte.
 */
package net.htlgrieskirchen.aud2.list;

/**
 *
 * @author fabian
 */
public class Timer {

    private final long startTime;
    private final String title;

    public Timer(String title) {
        this.title = title;
        this.startTime = System.currentTimeMillis();
    }

    public void end() {
        System.out.format("%s: %dms\n", title, System.currentTimeMillis() - startTime);
    }
}
