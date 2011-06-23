package com.flowlogix.website.services;

import com.flowlogix.website.JunkMailEraser;
import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import lombok.SneakyThrows;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.tynamo.jpa.JPASymbols;

/**
 * Integrate EJB3 Beans into the Web site
 * 
 * @author lprimak
 */
public class EjbModule
{
    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration)
    {
        configuration.add(JPASymbols.PERSISTENCE_UNIT, hopePU);
    }

    
    @SneakyThrows(NamingException.class)
    public static Context buildEjbContext()
    {
        return new InitialContext();
    }
    

    @SneakyThrows(NamingException.class)    
    public static JunkMailEraser buildJunkMailEraser(@InjectService("EjbContext") Context ic)
    {
        final String eraserName = "java:module/" + JunkMailEraser.NAME;
        JunkMailEraser eraser = (JunkMailEraser) ic.lookup(eraserName);
        try
        {
            // call any method to actually test the bean
            eraser.isMock();
        }
        catch (EJBException e)
        {
            eraser = (JunkMailEraser) ic.lookup(eraserName + "Mock");
        }
        return eraser;
    }
    
    
    private static final String dbProdSuffix =
            System.getProperty("com.baw.website.dbProdSuffix", "Test");
    
    private static final String hopePU = "HopePU" + dbProdSuffix;
}
