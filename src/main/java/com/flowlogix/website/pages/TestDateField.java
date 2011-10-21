/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flowlogix.website.pages;

import com.flowlogix.web.mixins.ColorHighlightOverride;
import com.flowlogix.web.services.annotations.AJAX;
import com.flowlogix.website.entities.Sample;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.Parameter;
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
        birthday = birthdays.get(0).getDoB();
    }
    
    
    @AJAX(requireSession = false)
    Block onSubmitFromForm()
    {
        return birthdayZone.getBody();
    }
    
    
    private @Getter List<Sample> birthdays;
    private @Getter @Setter Date birthday;
    private @InjectComponent Zone birthdayZone;
    private @PersistenceContext(unitName = "Hope") EntityManager em;

    @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "#5AACFD") @NotNull private String highlightColor;
    @Mixin private ColorHighlightOverride highlightOverride;
}
