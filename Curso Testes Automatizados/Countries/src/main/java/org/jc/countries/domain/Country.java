/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.countries.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jean
 */
public class Country {
    private final Name name;
    private final Geography geography;
    private final List<Currency> currencies;

    public Country(Name name, Geography geography, List<Currency> currencies) {
        this.name = name;
        this.geography = geography;
        this.currencies = new ArrayList<>(currencies);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.geography);
        hash = 29 * hash + Objects.hashCode(this.currencies);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.geography, other.geography)) {
            return false;
        }
//        if (!Objects.equals(this.currencies, other.currencies)) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "name=" + name + ", geography=" + geography + ", currencies=" + currencies + '}';
    }
    
    
}
