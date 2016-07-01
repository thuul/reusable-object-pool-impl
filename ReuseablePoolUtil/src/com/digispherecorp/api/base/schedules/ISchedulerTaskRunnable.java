/*
 * Copyright 2013 Digisphere CC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.digispherecorp.api.base.schedules;

/**
 *
 * @author Ikram
 * 
 * A base custom typed runnable interface for data replication using @java.util.concurrent classes schedulers.
 * @param <K>
 * 
 */
public interface ISchedulerTaskRunnable<K> extends Runnable {

    /**
     *
     * @return
     */
    public K getGenericRunnable();

    /**
     *
     * @param genericRunnable
     */
    public void setGenericRunnable(K genericRunnable);
}
