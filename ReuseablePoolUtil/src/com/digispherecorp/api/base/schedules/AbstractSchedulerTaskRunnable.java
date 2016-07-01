/*
 * Copyright 2013 VirtualPostman CC.
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
 * An abstract implementation interface for @ISchedulerTaskRunnable.
 * @param <K>
 * 
 */
public abstract class AbstractSchedulerTaskRunnable<K> implements ISchedulerTaskRunnable<K>{
    
    protected K genericRunnable;

    @Override
    public K getGenericRunnable() {
        return genericRunnable;
    }

    @Override
    public void setGenericRunnable(K genericRunnable) {
        this.genericRunnable = genericRunnable;
    }
    
}
