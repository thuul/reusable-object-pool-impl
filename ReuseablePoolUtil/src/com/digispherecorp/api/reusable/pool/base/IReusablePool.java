/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Walle
 * @param <T>
 *
 * <p>
 *
 * Concrete implementation of the reusable pool interface should be as a
 * singleton.
 *
 *
 * @see AbstractReusable
 * @see AbstractReusablePool
 * @see AbstractBlockValidateReusablePool
 *
 *
 */
public interface IReusablePool<T> extends Serializable {

    /**
     *
     */
    public void initialize();

    /**
     *
     * @param cls
     */
    public void initialize(Class... cls);

    /**
     *
     * @param obj
     */
    public void initialize(Object... obj);

    /**
     *
     * @return
     */
    public T acquireReusable();

    /**
     *
     * @param cls
     * @return
     */
    public T acquireReusable(Class... cls);

    /**
     *
     * @param obj
     * @return
     */
    public T acquireReusable(Object... obj);

    /**
     *
     * @param reusable
     */
    public void releaseReusable(T reusable);

    /**
     *
     * @param i
     * @return
     */
    public IReusablePool setInitialPoolSize(int i);

    /**
     *
     * @param i
     * @return
     */
    public IReusablePool setMaximumPoolSize(int i);

    /**
     *
     * @return
     */
    public int getMaximumPoolSize();

    /**
     *
     * @param expTime
     * @return
     */
    public IReusablePool setExpirationTime(long expTime);

    /**
     *
     * @return
     */
    public T createReusable();

    /**
     *
     * @param cls
     * @return
     */
    public T createReusable(Class... cls);

    /**
     *
     * @param obj
     * @return
     */
    public T createReusable(Object... obj);

    /**
     *
     */
    public void validatePool();

    /**
     *
     * @param o
     * @return
     */
    public boolean validate(T o);

    /**
     *
     */
    public void expirePool();

    /**
     *
     * @param o
     */
    public void expire(T o);

    /**
     *
     * @return
     */
    public HashMap<T, Long> getLocked();

    /**
     *
     * @return
     */
    public HashMap<T, Long> getUnlocked();
}
