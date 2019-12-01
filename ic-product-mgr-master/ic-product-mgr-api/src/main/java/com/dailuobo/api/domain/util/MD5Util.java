package com.dailuobo.api.domain.util;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.util.encoders.Hex;

public class MD5Util {
    public static String digest(String username, String password) {
        Digest md5 = new MD5Digest();
        byte[] challenge = username.getBytes();
        byte[] salt = password.getBytes();
        md5.update(challenge, 0, challenge.length);
        md5.update(salt, 0, salt.length);
        byte result[] = new byte[16];
        md5.doFinal(result, 0);
        return new String(Hex.encode(result));
    }
}
