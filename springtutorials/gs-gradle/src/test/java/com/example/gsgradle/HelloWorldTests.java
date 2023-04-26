package com.example.gsgradle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloWorldTests {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(byteArrayOutputStream);

    @BeforeEach
    public void setup() {
        System.setOut(printStream);
    }

    @Test
    public void shouldPrintTimeToConsole() {
         HelloWorld.main(new String[] { });
         assertThat(output()).contains("The current local time is");
    }

    @Test
    public void shouldPrintHelloWorldToConsole() {
        HelloWorld.main(new String[] { });
        assertThat(output()).contains("Hello, World!");
    }

    private String output() {
        return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    }

}
