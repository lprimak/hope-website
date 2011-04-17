package com.flowlogix.website.mixins;

import javax.validation.constraints.NotNull;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class ColorHighlight
{
    @SetupRender
    void init()
    {
        String script = "Tapestry.ElementEffect.colorhighlight = function(element) {" +
        "return new Effect.Highlight(element, { startcolor : '%s' });" +
        "}";
        js.addScript(script, color);
    }
    
    @Environmental private JavaScriptSupport js;
    @Parameter(required=true, defaultPrefix=BindingConstants.LITERAL) @NotNull 
    private String color;
}
