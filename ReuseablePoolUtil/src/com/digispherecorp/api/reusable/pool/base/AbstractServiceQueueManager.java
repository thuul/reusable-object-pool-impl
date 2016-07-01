/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Wal
 *
 * The Constructor for this class is protected. Implementing classes needs to
 * call its super and implement as a singleton for integrity purpose.
 * @param <T>
 */
public abstract class AbstractServiceQueueManager<T> implements IServiceQueueManager<T> {

    protected volatile static AbstractServiceQueueManager instance;
    protected BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    protected boolean keepAlive = true;
    protected IReusablePool poolInstance;
    protected T t;

    protected AbstractServiceQueueManager() {
    }

    protected AbstractServiceQueueManager(IReusablePool poolInstance) {
        this.poolInstance = poolInstance;
    }

    @Override
    public BlockingQueue<T> getQueue() {
        return queue;
    }

    @Override
    public void setQueue(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public IReusablePool getPoolInstance() {
        return poolInstance;
    }

    @Override
    public void setPoolInstance(IReusablePool poolInstance) {
        this.poolInstance = poolInstance;
    }

    @Override
    public T getObject() {
        return t;
    }

    @Override
    public void setObject(T t) {
        this.t = t;
    }

    @Override
    public void shutDown() {
        keepAlive = false;
        poolInstance.expirePool();
        poolInstance = null;
        if (!queue.isEmpty()) {
            queue.clear();
        }
        queue = null;
        t = null;
    }
}
