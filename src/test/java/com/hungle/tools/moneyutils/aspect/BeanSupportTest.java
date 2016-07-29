package com.hungle.tools.moneyutils.aspect;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import com.hungle.tools.moneyutils.fi.model.bean.FiInfoBean;

public class BeanSupportTest {
    @Test
    public void addressChangeNotifications() {
        FiInfoBean testCustomer = new FiInfoBean();
        testCustomer.setName("oldName");

        final AtomicInteger counter = new AtomicInteger();

        testCustomer.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                Assert.assertEquals("name", evt.getPropertyName());
                Assert.assertEquals("oldName", evt.getOldValue());
                Assert.assertEquals("newName", evt.getNewValue());
                counter.incrementAndGet();
            }
        });

        testCustomer.setName("newName");
        Assert.assertEquals(1, counter.get());
    }

}
