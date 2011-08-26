/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.services.ejb;

/**
 * This is a copy of @EJB annotation, to be used in Tapestry sources
 * Can't use the real @EJB annotation in Tapestry because it results
 * in a duplicate access EJB exception
 * 
 * @author lprimak
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EJB 
{
    public String name() default "";
    public String description() default "";
    public String beanName() default "";
    public Class<?> beanInterface() default Object.class;
    public String mappedName() default "";
    public String lookup() default "";
}
