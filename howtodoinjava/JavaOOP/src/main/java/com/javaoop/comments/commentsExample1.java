package com.javaoop.comments;

/**
 * Contains method to greet users by their name and location
 *
 * @author Ed Mangini
 */
public class commentsExample1 {

    /**
     * Launches application
     *
     * @param args - Application startup arguments
     */
    public static void main(String[] args) {
        getMessage("Mangini", "Wilmington");
    }

    /**
     * Returns welcome message for a person by name and location
     *
     * @param name - Name of the visitor
     * @param region - Location
     * @return Welcome message
     */
    public static String getMessage(String name, String region) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello ");
        builder.append(name);
        builder.append(", Welcome to ");
        builder.append(region);
        builder.append(" !!");
        return builder.toString();
    }
}
