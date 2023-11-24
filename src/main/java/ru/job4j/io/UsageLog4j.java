package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 127;
        short b = 32767;
        int c = 2147483647;
        long d = 9223372036854775807L;
        float e = 3.4e+38F;
        double f = 1.7e+308;
        boolean g = true;
        char h = 'A';
        LOG.debug("Java Primitives: byte {}, short {}, int {}, long {}, float {}, double {}, boolean {}, char {}",
                a, b, c, d, e, f, g, h);
    }
}