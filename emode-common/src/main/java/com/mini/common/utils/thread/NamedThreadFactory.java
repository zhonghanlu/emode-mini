package com.mini.common.utils.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger count = new AtomicInteger(1);
    private final String namePrefix;

    public NamedThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, namePrefix + count.getAndIncrement());
    }
}