/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
 
package org.mybatch.runtime.metric;

import java.util.HashMap;
import java.util.Map;
import javax.batch.runtime.Metric;

/**
 * Maintains execution metrics for a single step.
 */
final public class StepMetrics {

    private final Map<Metric.MetricType, MetricImpl> metricsMapping = new HashMap<Metric.MetricType, MetricImpl>();

    public StepMetrics() {
        for (Metric.MetricType m : Metric.MetricType.values()) {
            metricsMapping.put(m, new MetricImpl(m));
        }
    }

    public Metric[] getMetrics() {
        return metricsMapping.values().toArray(new Metric[metricsMapping.size()]);
    }

    public void set(Metric.MetricType name, long value) {
        MetricImpl targetMetric = metricsMapping.get(name);
        targetMetric.setValue(value);
    }

    public void increment(Metric.MetricType name, long value) {
        MetricImpl targetMetric = metricsMapping.get(name);
        targetMetric.increment(value);
    }

    @Override
    public String toString() {
        return "StepMetrics: " + metricsMapping;
    }
}
