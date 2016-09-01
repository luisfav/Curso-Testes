/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.security;

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
public class DigitalSignatureTest {
    
    public DigitalSignatureTest() {
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
    public void hello() {
        DataSignature ds = new DataSignature();
        final byte[] data = "Test data".getBytes();
        DataSignature.Envelope envelope = ds.sign(data);
        final Base64.Encoder base64 = Base64.getMimeEncoder();
        System.out.println("dados: " + base64.encodeToString(envelope.signedData()));
        System.out.println("chave: " + base64.encodeToString(envelope.pubKey()));
        System.out.println("keyAlg: " + envelope.keyAlg());
        System.out.println("signatureAlg: " + envelope.signatureAlg());
        SignatureVerification sv = new SignatureVerification(
            envelope.pubKey(),
            envelope.keyAlg(),
            envelope.signatureAlg()
        );
        assertTrue(sv.verify(envelope.signedData(), data));
    }
}
