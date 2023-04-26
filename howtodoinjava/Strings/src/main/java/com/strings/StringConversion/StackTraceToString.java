package com.strings.StringConversion;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceToString {

    public static void main(String[] args) {

        // METHOD 1: Apache Commons "ExceptionUtils"
        String errorStr = ExceptionUtils.getStackTrace(new NullPointerException("Custom Error"));
        System.out.println(errorStr);

        // METHOD 2: StringWriter
        /*
            This takes more effort, but you can play around w/ the exception internally if you have to.
         */
        String errorStr2 = convertStackStraceToString(new NullPointerException("Custom Error"));
        System.out.println(errorStr2);

    }

    private static String convertStackStraceToString(Throwable throwable) {

        try (StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw)) {

            throwable.printStackTrace(pw);
            return sw.toString();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
