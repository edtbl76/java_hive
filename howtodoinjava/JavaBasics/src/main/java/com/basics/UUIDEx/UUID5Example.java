package com.basics.UUIDEx;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUID5Example {

    public static UUID fromUTF8(String name) {
        return fromBytes(name.getBytes(Charset.forName("UTF-8")));
    }

    private static UUID fromBytes(byte[] name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return makeUUID(md.digest(name), 5);
        } catch (NoSuchAlgorithmException nsae) {
            throw new AssertionError(nsae);
        }
    }

    private static UUID makeUUID(byte[] hash, int version) {
        long msb = peekLong(hash, 0, ByteOrder.BIG_ENDIAN);
        long lsb = peekLong(hash, 8, ByteOrder.BIG_ENDIAN);

        // set version field
        msb &= ~(0xfL << 12);
        msb |= ((long) version) << 12;

        // set variant to 2
        lsb &= ~(0x3L << 62);
        lsb |= 2L << 62;
        return new UUID(msb, lsb);
    }

    private static long peekLong(final byte[] src, final int offset, final ByteOrder order) {
        long ans = 0;
        if (order == ByteOrder.BIG_ENDIAN) {
            for (int i = offset; i < offset + 8; i += 1) {
                ans <<= 8;
                ans |= src[i] & 0xffL;
            }
        } else {
            for (int i = offset + 7; i >=offset; i -= 1) {
                ans <<= 8;
                ans |= src[i] & 0xffL;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        // test uuid
        //UUID uuid = UUID5Example.fromUTF8("954aac7d-47b2-5975-9a80-37eeed186527");
        /*
            d1d16b54-9757-5743-86fa-9ffe3b937d78
            2
            5
         */
        UUID uuid = UUID5Example.fromUTF8(UUID.randomUUID().toString());
        System.out.println(uuid);
        System.out.println(uuid.variant());
        System.out.println(uuid.version());
    }
}
