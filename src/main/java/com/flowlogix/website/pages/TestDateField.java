/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.pages;

import com.flowlogix.web.mixins.ColorHighlightOverride;
import com.flowlogix.website.entities.Sample;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author lprimak
 */
public class TestDateField
{
    @SetupRender
    private void populate()
    {
        birthdays = em.createNamedQuery("Sample.findAll", Sample.class).getResultList();
        birthday = birthdays.get(0).getDoB();
    }
    
    
    private @Getter List<Sample> birthdays;
    private @Getter Date birthday;
    private @PersistenceContext(unitName = "Hope") EntityManager em;

    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "#5AACFD") @NotNull private String highlightColor;
    @Mixin private ColorHighlightOverride highlightOverride;
}
