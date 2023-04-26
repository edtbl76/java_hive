package com.java7.changes;

public class SuppressedException_J7_WithTryWRsrc {

    public static void memberFunction() throws Exception {
        // This replaces the finally block in the previous two examples.
        try (DirtyResource resource = new DirtyResource()) {
            resource.accessResource();
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            memberFunction();
        } catch (Exception e) {
            System.err.println("Exception encountered: " + e.toString());
            final Throwable[] suppressedExceptions = e.getSuppressed();
            final int numSuppressed = suppressedExceptions.length;
            if (numSuppressed > 0 ) {
                System.err.println("There are " + numSuppressed + " suppressed exceptions.");
                for (final Throwable exception : suppressedExceptions) {
                    System.err.println(exception.toString());
                }
            }
        }
    }
}
