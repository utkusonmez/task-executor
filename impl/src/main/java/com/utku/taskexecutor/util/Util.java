package com.utku.taskexecutor.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Util
 */
public enum Util {

    INSTANCE;

    public void sleepRandomBetween(int min, int max) {
        sleep(random(min, max));
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
