/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.pages;

import com.flowlogix.web.mixins.CalendarPopupPatch;
import com.flowlogix.web.mixins.ColorHighlightOverride;
import com.flowlogix.web.services.annotations.AJAX;
import com.flowlogix.website.entities.Sample;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;

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
    }
    
    
    public @AJAX Block onSubmit()
    {
        return birthdayZone.getBody();
    }
    
    
    private @Persist @Getter List<Sample> birthdays;
    private @InjectComponent Zone birthdayZone;
    private @PersistenceContext(unitName = "Hope") EntityManager em;

    private @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "#5AACFD") @NotNull String highlightColor;
    private @Mixin ColorHighlightOverride highlightOverride;
    private @Mixin CalendarPopupPatch calendarPopupPatch;
}
