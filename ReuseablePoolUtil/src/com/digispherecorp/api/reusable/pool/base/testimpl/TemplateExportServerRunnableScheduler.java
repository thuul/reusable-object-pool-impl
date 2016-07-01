/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base.testimpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.digispherecorp.api.base.schedules.IRunnableTaskSchedulerService;

/**
 *
 * @author Ikram
 */
public class TemplateExportServerRunnableScheduler implements IRunnableTaskSchedulerService {

    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> runnableScheduledFuture;
    private ScheduledFuture<?> monitorRunnableScheduledFuture;
    private Runnable runnableProcess;
    private long delay;

    public TemplateExportServerRunnableScheduler(Runnable runnableProcess) {
        this.runnableProcess = runnableProcess;
        this.scheduler = Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
    }

    @Override
    public void scheduleRunnableTask() {
        this.runnableScheduledFuture = this.scheduler.schedule(this.runnableProcess, 1, TimeUnit.SECONDS);
    }

    @Override
    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public ScheduledFuture<?> getRunnableScheduledFuture() {
        return runnableScheduledFuture;
    }

    @Override
    public ScheduledFuture<?> getMonitorRunnableScheduledFuture() {
        return monitorRunnableScheduledFuture;
    }

    @Override
    public Runnable getRunnableProcess() {
        return runnableProcess;
    }

    @Override
    public void setRunnableProcess(Runnable runnableProcess) {
        this.runnableProcess = runnableProcess;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public boolean checkIfTaskRunning() {
        return !runnableScheduledFuture.isDone() || !runnableScheduledFuture.isCancelled();
    }

    @Override
    public boolean cancelRunningTask() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
