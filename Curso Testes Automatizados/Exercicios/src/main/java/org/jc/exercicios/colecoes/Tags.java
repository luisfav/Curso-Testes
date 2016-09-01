/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.colecoes;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jean
 */
public class Tags {
    private final String list;
    private final String delimiter;

    public Tags(String tgs, String dlmtr) {
        this.list = tgs;
        this.delimiter = dlmtr;
    }

    public Tags(String tgs) {
        this(tgs, ":");
    }

    public List<String> toList() {
        return Arrays.asList(list.split(delimiter));
    }
    
    public Tags inclui(String tag) {
        final StringBuilder novas = new StringBuilder(this.list);
        novas.append(this.delimiter).append(tag);
        return new Tags(novas.toString(), this.delimiter);
    }
}
