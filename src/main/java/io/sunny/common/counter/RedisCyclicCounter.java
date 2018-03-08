package io.sunny.common.counter;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class RedisCyclicCounter implements CyclicCounter {
    private String name;
    private long maxVal;
    private RedisAtomicLong al;

    public RedisCyclicCounter(String name, long maxVal, RedisConnectionFactory connectionFactory) {
        this.name = name;
        this.maxVal = maxVal;
        this.al = new RedisAtomicLong(name + ":" + maxVal, connectionFactory);
    }

    @Override
    public long increment() {
        long curVal, newVal;
        do {
            curVal = al.get();
            newVal = (curVal + 1) % this.maxVal;
        } while (!al.compareAndSet(curVal, newVal));
        return newVal;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getMaxVal() {
        return maxVal;
    }

    @Override
    public String toString() {
        return al.toString();
    }
}
