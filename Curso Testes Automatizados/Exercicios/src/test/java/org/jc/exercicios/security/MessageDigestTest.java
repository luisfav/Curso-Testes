/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.security;

import java.security.MessageDigest;
import java.util.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean
 */
public class MessageDigestTest {
    
    private static final String IPSUM_LOREM = ""
        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean "
        + "ultrices pretium tempus. Proin euismod justo et ligula cursus "
        + "vehicula. Aliquam erat volutpat. Suspendisse potenti. "
        + "Cras tincidunt lorem nulla, eu imperdiet nunc bibendum id. Praesent "
        + "fermentum fermentum augue ac commodo. Sed viverra turpis ac cursus "
        + "tempus. Duis id nulla non magna posuere maximus. Proin congue odio "
        + "risus, ut ultricies eros egestas vitae.";
    
    public MessageDigestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void geraHashes() throws Exception {
        System.out.println(java.time.OffsetDateTime.now());
        final Base64.Encoder base64 = Base64.getMimeEncoder();
        byte[] data = IPSUM_LOREM.getBytes();
        System.out.println("Tamanho dos dados: " + data.length);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        final byte[] md5Digest = md5.digest();
        print(base64.encodeToString(md5Digest), md5Digest);
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        sha1.update(data);
        final byte[] sha1Digest = sha1.digest();
        print(base64.encodeToString(sha1Digest), sha1Digest);
    }

    private void print(String base64, byte[] digest) {
        System.out.printf(
            "Tamanho: %d. Dados: %s%n",
            digest.length,
            base64
        );
    }
}
