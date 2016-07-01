/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *
 * @author Wal
 * @param <T>
 */
public abstract class AbstractBlockValidateReusablePool<T> extends AbstractReusablePool<T> {

    protected ScheduledExecutorService scheduledThreadPool;

    protected AbstractBlockValidateReusablePool() {
        super();
        scheduledThreadPool = Executors.newScheduledThreadPool(2, Executors.defaultThreadFactory());
    }

    @Override
    public void validatePool() {
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long timeNow = System.currentTimeMillis();
                Collection<Map.Entry<IReusable<T>, Long>> collection = Collections.synchronizedCollection(unlocked.entrySet());
                synchronized (collection) {
                    if (!collection.isEmpty()) {
                        Iterator<Map.Entry<IReusable<T>, Long>> iterator = collection.iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<IReusable<T>, Long> next = iterator.next();
                            if ((timeNow - next.getValue()) >= expirationTime) {
                                if (validate(next.getKey())) {
                                    next.getKey().expire();
                                    unlocked.remove(next.getKey());
                                } else {
                                    unlocked.remove(next.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }, 300, 2700, TimeUnit.SECONDS);
    }

    @Override
    public void expirePool() {
        scheduledThreadPool.shutdown();
        try {
            if (!scheduledThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduledThreadPool.shutdownNow();
                if (!scheduledThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    Logger.getLogger(AbstractBlockValidateReusablePool.class.getName()).info("Executor Service did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            scheduledThreadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        super.expirePool();
    }
}
