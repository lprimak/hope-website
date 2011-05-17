/**
 * Enables a zone to be periodically refreshed with the response from the given event.
 */
package com.flowlogix.website.mixins;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 *
 * @author lprimak
 */
@Import(library="context:scripts/PeriodicUpdate.js")
public class PeriodicUpdate
{
    /**
     * The name of the event to call to update the zone.
     */
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String event;
 
    /**
     * The context for the triggered event. The clientId of the containing zone is always 
     * added as the final context item.
     */
    @Parameter("defaultContext")
    private Object[] context;
 
    /**
     * How long, in seconds, to wait between the end of one request and the beginning of the next.
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private int period;
 
    @InjectContainer
    private Zone zone;
 
    @Inject
    private ComponentResources resources;
 
    @Environmental
    private JavaScriptSupport jsSupport;
 
    public Object[] getDefaultContext() {
        return new Object[0];
    }
 
    void afterRender() {
 
        final String id = zone.getClientId();
 
        final List<Object> context = Lists.newArrayList(Arrays.asList(this.context));
        context.add(zone.getClientId());
 
        final Link link = resources.createEventLink(event, context.toArray(new Object[context.size()]));
 
        final JSONObject config = new JSONObject();
 
        if (resources.isBound("period"))
        {
            config.put("period", period);
        }
        else
        {
            config.put("period", 2);
        }
        config.put("elementId", id);
        config.put("uri", link.toAbsoluteURI());
        
        jsSupport.addInitializerCall("periodicupdater", config);
    }
}

