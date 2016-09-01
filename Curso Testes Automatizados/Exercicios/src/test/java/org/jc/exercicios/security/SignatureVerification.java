/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author jean
 */
public class SignatureVerification {
    private final byte[] key;
    private final String keyAlg;
    private final String sigAlg;

    public SignatureVerification(byte[] key, String keyAlg, String sigAlg) {
        this.key = key.clone();
        this.keyAlg = keyAlg;
        this.sigAlg = sigAlg;
    }

    public boolean verify(byte[] sinedData, byte[] data) {
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlg, "SUN");
            PublicKey pubKey
                = keyFactory.generatePublic(pubKeySpec);
            Signature sig = Signature.getInstance(sigAlg, "SUN");
            sig.initVerify(pubKey);
            sig.update(data);
            return sig.verify(sinedData);
        } catch (NoSuchAlgorithmException | NoSuchProviderException
            | InvalidKeySpecException | InvalidKeyException
            | SignatureException ex) {
            throw new RuntimeException("Falha ao verificar assinatura", ex);
        }
    }
}
