/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud2.list;

import java.util.Objects;

/**
 *
 * @author Andreas
 */
public class Custom {
    private int random;
    private String name;

    public Custom(int random, String name) {
        this.random = random;
        this.name = name;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.random;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Custom other = (Custom) obj;
        if (this.random != other.random) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    
    
    
    
}
