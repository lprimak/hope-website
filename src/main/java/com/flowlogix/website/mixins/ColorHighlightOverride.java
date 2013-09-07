package com.flowlogix.website.mixins;

import org.apache.tapestry5.annotations.BindParameter;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 *
 * @author lprimak
 */
public class ColorHighlightOverride
{
    void setupRender()
    {
        js.require("js/HighlightEffect.js").with(highlightColor);
    }
    
    
    private @BindParameter String highlightColor;    
    private @Environmental JavaScriptSupport js;
}
