/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digispherecorp.api.reusable.pool.base.testimpl;

import java.net.Socket;
import com.digispherecorp.api.reusable.pool.base.IReusable;
import com.digispherecorp.api.base.schedules.IRunnableTaskSchedulerService;

/**
 *
 * @author Ikram
 */
public class TemplateExportServerRunnable implements Runnable {

    private Socket socket;
    private IReusable<IRunnableTaskSchedulerService> reusable;

    public TemplateExportServerRunnable() {
    }

    public TemplateExportServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setReusable(IReusable<IRunnableTaskSchedulerService> reusable) {
        this.reusable = reusable;
    }
}
