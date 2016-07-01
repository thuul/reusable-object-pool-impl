/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Wal
 * @param <T>
 */
public interface IServiceQueueManager<T> {

    /**
     *
     * @return
     */
    BlockingQueue<T> getQueue();

    /**
     *
     * @param queue
     */
    void setQueue(BlockingQueue<T> queue);

    /**
     *
     * @return
     */
    public IReusablePool getPoolInstance();

    /**
     *
     * @param poolInstance
     */
    public void setPoolInstance(IReusablePool poolInstance);

    /**
     *
     * @return
     */
    public T getObject();

    /**
     *
     * @param t
     */
    public void setObject(T t);

    /**
     *
     */
    void consumeService();

    /**
     *
     */
    void shutDown();
}
