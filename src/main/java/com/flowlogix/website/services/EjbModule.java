package com.flowlogix.website.services;

import com.flowlogix.website.JunkMailEraser;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import lombok.SneakyThrows;
import org.apache.tapestry5.ioc.annotations.InjectService;

/**
 * Integrate EJB3 Beans into the Web site
 * 
 * @author lprimak
 */
public class EjbModule
{
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
    
    
    private static final Logger log = Logger.getLogger(EjbModule.class.getName());
}
