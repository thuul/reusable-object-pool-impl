/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.base.schedules;

import java.util.List;

/**
 *
 * @author Ikram
 * 
 * A custom composite interface and manager for @IRunnableTaskSchedulerService.
 * @param <T>
 * 
 */
public interface IRunnableTaskSchedulerServiceCompositeManager<T> extends IRunnableTaskSchedulerService<T>
{

    /**
     *
     * @param component
     * @return boolean
     */
    boolean addComponent(IRunnableTaskSchedulerService component);

    /**
     *
     * @param component
     * @return boolean
     */
    boolean removeComponent(IRunnableTaskSchedulerService component);

    /**
     *
     * @return List
     */
    List<IRunnableTaskSchedulerService> getComponents();

    /**
     *
     */
    void loadAllRunnableTaskScheduler();

    /**
     *
     */
    void dropAllRunnableTaskScheduler();
    
    /**
     *
     */
    void dropAllRunnableTaskSchedulerNow();
    
    /**
     *
     * @param schedulable
     * @return IRunnableTaskSchedulerService
     */
    IRunnableTaskSchedulerService newRunnableTaskScheduler(T schedulable);
}
