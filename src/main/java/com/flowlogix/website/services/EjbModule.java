package com.flowlogix.website.services;

import com.flowlogix.website.services.ejb.internal.EJBAnnotationWorker;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

/**
 * Integrate EJB3 Beans into the Web site
 * 
 * @author lprimak
 */
public class EjbModule
{
    @Contribute(ComponentClassTransformWorker2.class)
    public static void provideClassTransformWorkers(OrderedConfiguration<ComponentClassTransformWorker2> configuration)
    {
        configuration.addInstance("EJB", EJBAnnotationWorker.class, "before:Property");
    }
}
