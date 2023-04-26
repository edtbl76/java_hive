package com.basics.UUIDEx;

import java.util.UUID;

public class UUID4Example {

    public static void main(String[] args) {

        UUID uuid = UUID.randomUUID();

        System.out.println(uuid);
        System.out.println(uuid.variant());
        System.out.println(uuid.version());
    }
}
