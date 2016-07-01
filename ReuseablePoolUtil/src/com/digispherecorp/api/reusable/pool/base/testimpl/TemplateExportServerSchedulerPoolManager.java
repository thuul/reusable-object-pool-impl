/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base.testimpl;

import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.digispherecorp.api.reusable.pool.base.AbstractBlockValidateReusablePool;
import com.digispherecorp.api.reusable.pool.base.IReusable;
import com.digispherecorp.api.base.schedules.IRunnableTaskSchedulerService;

/**
 *
 * @author Wal
 */
public class TemplateExportServerSchedulerPoolManager extends AbstractBlockValidateReusablePool<IRunnableTaskSchedulerService> {

    private static volatile TemplateExportServerSchedulerPoolManager instance;

    private TemplateExportServerSchedulerPoolManager() {
        super();
        this.setMaximumPoolSize(5).setExpirationTime(3600000);
    }

    public static TemplateExportServerSchedulerPoolManager newInstance() {
        synchronized (TemplateExportServerSchedulerPoolManager.class) {
            if (instance == null) {
                instance = new TemplateExportServerSchedulerPoolManager();
            }
        }
        return instance;
    }

    public static TemplateExportServerSchedulerPoolManager getInstance() {
        return instance;
    }

    @Override
    public IReusable acquireReusable(Class... cls) {
        return null;
    }

    @Override
    public IReusable<IRunnableTaskSchedulerService> acquireReusable(Object... obj) {
        IReusable<IRunnableTaskSchedulerService> acquireReusable = super.acquireReusable();
        if (acquireReusable != null) {
            ((TemplateExportServerRunnable) acquireReusable.getReusable().getRunnableProcess()).setSocket((Socket) obj[0]);
            return acquireReusable;
        } else {
            if ((unlocked.size() + locked.size()) < maximumPoolSize) {
                long timeNow = System.currentTimeMillis();
                IReusable<IRunnableTaskSchedulerService> createReusable = createReusable(obj);
                locked.put(createReusable, timeNow);
                return createReusable;
            } else {
                final CountDownLatch aLatch = new CountDownLatch(1);
                this.scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        if (!unlocked.isEmpty()) {
                            aLatch.countDown();
                        }
                    }
                }, 0, 2, TimeUnit.SECONDS);
                try {
                    aLatch.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(TemplateExportServerSchedulerPoolManager.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                }
            }
            acquireReusable = super.acquireReusable();
            ((TemplateExportServerRunnable) acquireReusable.getReusable().getRunnableProcess()).setSocket((Socket) obj[0]);
            return acquireReusable;
        }
    }

    @Override
    public IReusable createReusable() {
        return null;
    }

    @Override
    public IReusable createReusable(Object... obj) {
        return new TemplateExportServerReusable(new TemplateExportServerRunnable((Socket) obj[0]));
    }

    @Override
    public IReusable createReusable(Class... cls) {
        return null;
    }
}
