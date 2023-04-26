package com.java7.changes;

import java.rmi.AccessException;

public class ExceptionSuppression_WithJava7 {

    /*
        Exceptions thrown in the code, but are ignored.

        EX: Try-Catch-Finally block.
            - exceptions thrown in the finally block are suppressed
     */

    public static void memberFunction() throws Exception {
        Throwable th = null;
        DirtyResource resource = new DirtyResource();

        try {
            resource.accessResource();
        } catch (Exception e) {
            th = e;
            throw e;
        } finally {
            try {
                resource.close();
            } catch (Exception e) {
                if (th != null) {
                    e.addSuppressed(th); // Add to primary exception
                    throw e;
                }
            }
        }
    }

    /**
     * Exceutable function demonstrating suppressed exceptions
     */
    public static void main(String[] args) throws Exception {

        try {
            memberFunction();
        } catch (Exception ex) {
            System.err.println("Exception encountered: " + ex.toString());
            final Throwable[] suppressedExceptions = ex.getSuppressed();
            final int numSuppressed = suppressedExceptions.length;
            if (numSuppressed > 0) {
                System.err.println("There are " + numSuppressed + " suppressed Exceptions.");
                for (final Throwable exception :suppressedExceptions) {
                    System.err.println(exception.toString());
                }
            }
        }
    }
}

