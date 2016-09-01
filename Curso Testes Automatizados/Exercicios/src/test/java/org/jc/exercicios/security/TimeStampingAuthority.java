/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.security;

import java.time.OffsetDateTime;

/**
 *
 * @author jean
 */
public class TimeStampingAuthority {

    private DataSignature ds;

    public TimeStampingAuthority(DataSignature ds) {
        this.ds = ds;
    }

    public TimeStampingAuthority() {
        this(new DataSignature());
    }

    public void stamp(byte[] data) {
        byte[] local = data.clone();
        final OffsetDateTime now = OffsetDateTime.now();
        final String time = now.toString();
        byte[] ts = time.getBytes();
        byte[] stamped = new byte[local.length + ts.length];
        System.arraycopy(local, 0, stamped, 0, local.length);
        System.arraycopy(ts, 0, stamped, local.length, ts.length);
        DataSignature.Envelope env = ds.sign(stamped);

    }

    public static class TimeStamp {
        private final byte[] digest;
        private final byte[] pub;
        private final String pkAlg;
        private final String sgnAlg;
        private final OffsetDateTime stmp;

        TimeStamp(byte[] hash, byte[] key, String keyAlg,
            String signAlg, OffsetDateTime stamp) {
            this.digest = hash.clone();
            this.pub = key.clone();
            this.pkAlg = keyAlg;
            this.sgnAlg = signAlg;
            this.stmp = stamp;
        }

        public byte[] hash() {
            return digest;
        }

        public byte[] publicKey() {
            return pub;
        }

        public String publicKeyAlgorithm() {
            return pkAlg;
        }

        public String signatureAlgorithm() {
            return sgnAlg;
        }

        public OffsetDateTime stamp() {
            return stmp;
        }
    }

}
