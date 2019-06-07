/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Services;

import Decoder.BASE64Encoder;
import java.security.MessageDigest;

/**
 *
 * @author messi
 */
public class encryptedHash {

    private final MessageDigest md;

    public encryptedHash() throws SecurityException {
        try {
            md = MessageDigest.getInstance("MD5", "SUN");
        } catch (Exception se) {
            throw new SecurityException("Dans MD5 constructeur " + se);
        }
    }

    public String encode(String in) throws Exception {
        if (in == null) {
            return null;
        }
        try {
            byte[] raw = null;
            byte[] stringBytes = null;
            stringBytes = in.getBytes("UTF8");
            synchronized (md) {
                raw = md.digest(stringBytes);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(raw);
        } catch (Exception se) {
            throw new Exception("Exception lors de l'encodage " + se);
        }

    }

}
