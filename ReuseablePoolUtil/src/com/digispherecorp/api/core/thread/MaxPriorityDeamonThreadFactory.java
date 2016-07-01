/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.core.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Wal
 * 
 * <p>
 *
 * A simple Deamon Thread with maximum execution priority
 * 
 * @see SimpleDeamonThreadFactory
 */
public class MaxPriorityDeamonThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOLNUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    /**
     *
     */
    public MaxPriorityDeamonThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup()
                : Thread.currentThread().getThreadGroup();
        namePrefix = "pool-"
                + POOLNUMBER.getAndIncrement()
                + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        t.setDaemon(true);
        if (t.getPriority() != Thread.MAX_PRIORITY) {
            t.setPriority(Thread.MAX_PRIORITY);
        }
        return t;
    }

}
