package com.helper.work.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class KeysTest {

    @Test
    void generateWithGoogle_notNull() {
        try {
            final String generated = Keys.generateWithGoogle();
            assertFalse(generated.isEmpty(), "Code generated");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
