package com.flowlogix.website.pages;

import com.flowlogix.web.mixins.CalendarPopupPatch;
import com.flowlogix.web.mixins.DisableAfterSubmit;
import com.flowlogix.web.mixins.ZoneLoadingSpinner;
import com.flowlogix.web.services.annotations.AJAX;
import com.flowlogix.website.dao.SampleDAOLocal;
import com.flowlogix.website.entities.Sample;
import com.flowlogix.website.mixins.ColorHighlightOverride;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Zone;

/**
 *
 * @author lprimak
 */
@Import(stack = "core")
public class TestDateField
{
    @SetupRender
    private void populate()
    {
        birthdays = sampleDAO.query("Sample.findAll");
    }
    
    
    public @AJAX Block onSubmit()
    {
        return birthdayZone.getBody();
    }
    
    
    private @Persist @Getter List<Sample> birthdays;
    private @InjectComponent Zone birthdayZone;
    private @EJB SampleDAOLocal sampleDAO;

    private @Parameter(defaultPrefix = BindingConstants.LITERAL, value = "#5AACFD") @NotNull String highlightColor;
    private @Mixin ColorHighlightOverride highlightOverride;
    private @Mixin DisableAfterSubmit das;
    private @Mixin CalendarPopupPatch calendarPopupPatch;
    private @Mixin ZoneLoadingSpinner spi;
}
