package com.java7.changes;

public class DirtyResource implements AutoCloseable {

    /**
     *  Need to call this method if you want to access this resource
     * @throws RuntimeException
     */
    public void accessResource() {
        throw new RuntimeException("Sorry Charlie!");
    }

    /**
     * The overriden closure method from AutoCloseable interface
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        throw new NullPointerException("NPE!");
    }
}
