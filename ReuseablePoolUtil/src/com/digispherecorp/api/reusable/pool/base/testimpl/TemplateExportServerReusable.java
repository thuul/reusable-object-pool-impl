/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base.testimpl;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.digispherecorp.api.reusable.pool.base.AbstractReusable;
import com.digispherecorp.api.base.schedules.IRunnableTaskSchedulerService;

/**
 *
 * @author Wal
 */
public class TemplateExportServerReusable extends AbstractReusable<IRunnableTaskSchedulerService> {

    public TemplateExportServerReusable(Runnable runnable) {
        this.reusableObj = new TemplateExportServerRunnableScheduler(runnable);
    }

    public TemplateExportServerReusable() {
    }

    @Override
    public void expire() {
        ScheduledExecutorService scheduler = this.reusableObj.getScheduler();
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    Logger.getLogger(TemplateExportServerReusable.class.getName()).info("Executor Service did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        super.expire();
    }
}
