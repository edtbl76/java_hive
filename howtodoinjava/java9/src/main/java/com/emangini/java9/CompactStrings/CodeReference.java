package com.emangini.java9.CompactStrings;

import jdk.internal.vm.annotation.Stable;

import java.io.Serializable;

public class CodeReference {

}

// PRIOR TO JAVA 9

final class StringPre9 implements java.io.Serializable, Comparable<String>, CharSequence {

    private final char value[];

    StringPre9(char[] value) {
        this.value = value;
    }

    public int length() {
        return 0;
    }

    public char charAt(int index) {
        return 0;
    }

    public CharSequence subSequence(int start, int end) {
        return null;
    }

    public int compareTo(String o) {
        return 0;
    }
}


// Java9
final class StringJava9 implements Serializable, Comparable<String>, CharSequence {

    /** The value is used for character storage */
    @Stable
    private final byte[] value;

    /**
     * The identifier of the encoding used to encode the bytes in
     * {@code value}. The supported values in this implementation are
     *
     * LATIN1
     * UTF16
     *
     * @implNote This field is trusted by the VM, and is a subject  to
     * constant folding if String instance is constant. Overwriting this
     * field after construction will cause problems.
     */
    private final byte coder;

    StringJava9(byte[] value, byte coder) {
        this.value = value;
        this.coder = coder;
    }


    public int length() {
        return 0;
    }

    public char charAt(int index) {
        return 0;
    }

    public CharSequence subSequence(int start, int end) {
        return null;
    }

    public int compareTo(String o) {
        return 0;
    }
}