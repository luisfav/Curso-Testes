/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author jean
 */
public class DataSignature {
    
    private KeyPair pair;
    private Signature signature;

    public DataSignature(KeyPair pair, Signature sig) {
        this.pair = pair;
        this.signature = sig;
    }

    public DataSignature() {
        this(generateKeyPair(), sigInstance());
    }

    public Envelope sign(byte[] data) {
        try {
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            signature.initSign(priv);
            signature.update(data);
            
            byte[] signed = signature.sign();
            byte[] key = pub.getEncoded();
            return new Envelope(
                signed,
                key,
                signature.getAlgorithm(),
                pub.getAlgorithm()
            );
        } catch (InvalidKeyException ex) {
            throw new RuntimeException("Chave inválida", ex);
        } catch (SignatureException ex) {
            throw new RuntimeException("Falha ao assinar", ex);
        }
    }
    
    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Algoritmo não encontrado", ex);
        } catch (NoSuchProviderException ex) {
            throw new RuntimeException("Provider não encontrado", ex);
        }
    }
    
    private static Signature sigInstance() {
        try {
            return Signature.getInstance("SHA1withDSA", "SUN");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Algoritmo não encontrado", ex);
        } catch (NoSuchProviderException ex) {
            throw new RuntimeException("Provider não encontrado", ex);
        }
    }
    
    public static class Envelope {
        private final byte[] signed;
        private final byte[] key;
        private final String sigAlg;
        private final String keyAlg;

        Envelope(byte[] signed, byte[] key, String sigAlg,
            String keyAlg) {
            this.signed = signed.clone();
            this.key = key.clone();
            this.sigAlg = sigAlg;
            this.keyAlg = keyAlg;
        }

        public byte[] signedData() {
            return signed.clone();
        }

        public byte[] pubKey() {
            return key.clone();
        }

        public String signatureAlg() {
            return sigAlg;
        }

        public String keyAlg() {
            return keyAlg;
        }
        
        
    }
}
