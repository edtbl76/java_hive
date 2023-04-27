package oop.polymorphism;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubWidgetTest {
    SubWidget subWidget;

    @Test
    void testGetName() {
        subWidget = new SubWidget("Son of Widget");
        assertEquals("Widget, Son of Widget", subWidget.getName());
    }
}
