/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.pages;

import com.flowlogix.website.entities.WebStats;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.Getter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author lprimak
 */
public class DbTable
{
    @SuppressWarnings("unused")
    @SetupRender
    private void populate()
    {
        webStats = em.createNamedQuery("WebStats.findAll", WebStats.class).getResultList();
    }
    
    
    @Inject private EntityManager em;
    @Getter @Persist private List<WebStats> webStats;
}
