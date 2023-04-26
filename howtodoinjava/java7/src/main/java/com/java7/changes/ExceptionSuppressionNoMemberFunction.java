package com.java7.changes;

public class ExceptionSuppressionNoMemberFunction {

    public static void main(String[] args) throws Exception {

        // This is going to show the suppressed
        try (DirtyResource dirtyResource =  new DirtyResource()) {
            dirtyResource.accessResource();
        }

    }
}
