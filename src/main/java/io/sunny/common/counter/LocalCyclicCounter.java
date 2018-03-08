package io.sunny.common.counter;

import java.util.concurrent.atomic.AtomicLong;

public class LocalCyclicCounter implements CyclicCounter {
    private final long maxVal;
    private final AtomicLong al = new AtomicLong(0);

    public LocalCyclicCounter(long maxVal) {
        this.maxVal = maxVal;
    }

    @Override
    public long increment() {
        long curVal, newVal;
        do {
            curVal = this.al.get();
            newVal = (curVal + 1) % this.maxVal;
        } while (!this.al.compareAndSet(curVal, newVal));
        return newVal;
    }

    @Override
    public long getMaxVal() {
        return this.maxVal;
    }

    @Override
    public String toString() {
        return al.toString();
    }
}
