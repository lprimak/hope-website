package com.flowlogix.website.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.services.FormSupport;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library="context:scripts/DisableAfterSubmit.js")
public class DisableAfterSubmit
{
    @AfterRender
    void addDisabler()
    {
        js.addScript("new DisableAfterSubmit('%s', '%s', '%s');",
                submitButton.getClientId(), fs.getClientId(), zone);
    }
    
    
    @Environmental private JavaScriptSupport js;
    @InjectContainer private ClientElement submitButton;
    @Environmental private FormSupport fs;
    @Parameter(required=true, defaultPrefix=BindingConstants.LITERAL) private String zone;
}
