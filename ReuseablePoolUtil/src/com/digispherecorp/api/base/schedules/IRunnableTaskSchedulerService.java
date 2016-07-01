/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.base.schedules;

import java.io.Serializable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 *
 * @author Ikram

 A base custom interface facade for
 * @param <T>
 * @java.util.concurrent ScheduledExecutorService object.
 *
 */
public interface IRunnableTaskSchedulerService<T> extends Serializable {

    /**
     *
     */
    void scheduleRunnableTask();

    /**
     *
     * @return
     */
    ScheduledExecutorService getScheduler();

    /**
     *
     * @param scheduler
     */
    void setScheduler(ScheduledExecutorService scheduler);

    /**
     *
     * @return
     */
    public Runnable getRunnableProcess();

    /**
     *
     * @param runnableProcess
     */
    public void setRunnableProcess(Runnable runnableProcess);

    /**
     *
     * @return
     */
    public ScheduledFuture<?> getRunnableScheduledFuture();

    /**
     *
     * @return
     */
    public ScheduledFuture<?> getMonitorRunnableScheduledFuture();

    /**
     *
     * @return boolean
     */
    public boolean checkIfTaskRunning();

    /**
     *
     * @return boolean
     */
    public boolean cancelRunningTask();

    /**
     *
     * @return
     */
    public long getDelay();

    /**
     *
     * @param delay
     */
    public void setDelay(long delay);
}
