package com.java7.changes;

public class ExceptionSuppression_Before {

    /**
     * Executable method demonstrating suppressed exceptions
     */
    public static void memberFunction() throws Exception {
        DirtyResource resource = new DirtyResource();
        try {
            resource.accessResource();
        } finally {
            resource.close();
        }
    }

    public static void main(String[] args) {
        try {
            memberFunction();
        } catch (Exception ex) {
            System.err.println("Exception encountered: " + ex.toString());
            final Throwable[] suppressedExceptions  = ex.getSuppressed();
            final int numSuppressed = suppressedExceptions.length;
            if (numSuppressed > 0) {
                System.err.println("There are " + numSuppressed + " suppressed exceptions: ");
                for (final Throwable exception : suppressedExceptions)
                    System.err.println(exception.toString());
            }
        }
    }
}
