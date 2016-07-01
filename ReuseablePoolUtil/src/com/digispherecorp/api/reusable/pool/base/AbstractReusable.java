/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base;

/**
 *
 * @author Wal
 * @param <T>
 */
public abstract class AbstractReusable<T> implements IReusable<T> {

    protected T reusableObj;

    @Override
    public void setReusable(T t) {
        this.reusableObj = t;
    }

    @Override
    public T getReusable() {
        return reusableObj;
    }

    @Override
    public void expire() {
        this.reusableObj = null;
    }
    
}
