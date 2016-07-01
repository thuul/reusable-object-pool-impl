/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

import java.io.Serializable;

/**
 *
 * @author Wal
 * @param <T>
 */
public interface IReusable<T> extends Serializable{

    /**
     *
     * @param t
     */
    public void setReusable(T t);

    /**
     *
     * @return T
     */
    public T getReusable();

    /**
     *
     */
    public void expire();
}
