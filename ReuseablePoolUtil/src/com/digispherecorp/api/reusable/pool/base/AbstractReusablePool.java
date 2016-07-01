/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Wal
 * @param <T>
 */
public abstract class AbstractReusablePool<T> implements IReusablePool<IReusable<T>> {

    protected HashMap<IReusable<T>, Long> locked, unlocked;
    protected IReusable<T> aReusable;
    protected long expirationTime = 60000;
    protected int initialPoolSize = 0;
    protected int maximumPoolSize = 5;

    /**
     *
     */
    protected AbstractReusablePool() {
        locked = new HashMap<>();
        unlocked = new HashMap<>();
    }

    @Override
    public final void initialize(Object... obj) {
        long timeNow = System.currentTimeMillis();
        while (initialPoolSize != 0) {
            unlocked.put(createReusable(obj), timeNow);
            --initialPoolSize;
        }
    }

    @Override
    public final void initialize(Class... cls) {
        long timeNow = System.currentTimeMillis();
        while (initialPoolSize != 0) {
            unlocked.put(createReusable(cls), timeNow);
            --initialPoolSize;
        }
    }

    @Override
    public final void initialize() {
        long timeNow = System.currentTimeMillis();
        while (initialPoolSize != 0) {
            unlocked.put(createReusable(), timeNow);
            --initialPoolSize;
        }
    }

    @Override
    public void validatePool() {
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

    @Override
    public boolean validate(IReusable<T> o) {
        return (o != null && o.getReusable() != null);
    }

    @Override
    public void expirePool() {
        this.removeAllReusable();
    }

    @Override
    public void expire(IReusable<T> o) {
        o.expire();
    }

    private void removeAllReusable() {
        Collection<Map.Entry<IReusable<T>, Long>> collection = Collections.synchronizedCollection(unlocked.entrySet());
        synchronized (collection) {
            if (!collection.isEmpty()) {
                Iterator<Map.Entry<IReusable<T>, Long>> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<IReusable<T>, Long> next = iterator.next();
                    if (validate(next.getKey())) {
                        next.getKey().expire();
                        unlocked.remove(next.getKey());
                    } else {
                        unlocked.remove(next.getKey());
                    }
                }
            }
        }
        collection = Collections.synchronizedCollection(locked.entrySet());
        synchronized (collection) {
            if (!collection.isEmpty()) {
                Iterator<Map.Entry<IReusable<T>, Long>> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<IReusable<T>, Long> next = iterator.next();
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

    @Override
    public synchronized IReusable<T> acquireReusable() {
        long timeNow = System.currentTimeMillis();
        Collection<Map.Entry<IReusable<T>, Long>> collection = Collections.synchronizedCollection(unlocked.entrySet());
        if (!collection.isEmpty()) {
            Iterator<Map.Entry<IReusable<T>, Long>> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Map.Entry<IReusable<T>, Long> next = iterator.next();
                if ((timeNow - next.getValue()) < expirationTime) {
                    if (validate(next.getKey())) {
                        unlocked.remove(next.getKey());
                        locked.put(next.getKey(), timeNow);
                        return next.getKey();
                    } else {
                        unlocked.remove(next.getKey());
                    }
                } else if (validate(next.getKey())) {
                    next.getKey().expire();
                    unlocked.remove(next.getKey());
                } else {
                    unlocked.remove(next.getKey());
                }
            }
        }
        return null;
    }

    @Override
    public synchronized void releaseReusable(IReusable<T> reusable) {
        locked.remove(reusable);
        unlocked.put(reusable, System.currentTimeMillis());
    }

    @Override
    public IReusablePool setInitialPoolSize(int i) {
        this.initialPoolSize = i;
        return this;
    }

    @Override
    public IReusablePool setMaximumPoolSize(int i) {
        this.maximumPoolSize = i;
        return this;
    }

    @Override
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    @Override
    public IReusablePool setExpirationTime(long expTime) {
        this.expirationTime = expTime;
        return this;
    }

    @Override
    public HashMap<IReusable<T>, Long> getLocked() {
        return locked;
    }

    @Override
    public HashMap<IReusable<T>, Long> getUnlocked() {
        return unlocked;
    }
}
