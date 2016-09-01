/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.countries.domain;

import java.util.Objects;

/**
 *
 * @author jean
 */
public class Geography {
    private final String capital;
    private final Long population;
    private final Location location;

    public Geography(String capital, Long population, Location location) {
        this.capital = capital;
        this.population = population;
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.capital);
        hash = 83 * hash + Objects.hashCode(this.population);
        hash = 83 * hash + Objects.hashCode(this.location);
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
        final Geography other = (Geography) obj;
        if (!Objects.equals(this.capital, other.capital)) {
            return false;
        }
        if (!Objects.equals(this.population, other.population)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Geography{" + "capital=" + capital + ", population=" + population + ", location=" + location + '}';
    }
    
    
}
