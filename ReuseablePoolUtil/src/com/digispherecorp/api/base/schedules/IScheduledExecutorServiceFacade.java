/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.base.schedules;

/**
 *
 * @author Ikram
 * 
 * A self-contained base interface facade for @java.util.concurrent ScheduledExecutorService object.
 * 
 */
public interface IScheduledExecutorServiceFacade {

    /**
     *
     */
    void scheduleRunnableTask();
    
    /**
     *
     */
    void shutdownRunnableTask();
    
}
