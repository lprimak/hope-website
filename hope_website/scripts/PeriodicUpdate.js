/**
 * Periodically Update a Zone
 */

Tapestry.PeriodicUpdater = Class.create({
    initialize: function(element, url, period) {      
        this.period = period; 
        this.element = element;
        this.url = url;
 
        this.start();
    },
 
    start: function() {
        this.onUpdate = this.updateComplete.bind(this);
        this.timer = this.onTimerEvent.bind(this).delay(this.period);
    },
 
    stop: function() {
        this.onUpdate = undefined;
        clearTimeout(this.timer);
    },
 
    updateComplete: function() {
        this.timer = this.onTimerEvent.bind(this).delay(this.period);
    },
 
    onTimerEvent: function() {
        var zoneObject = Tapestry.findZoneManagerForZone(this.element);
 
        if (!zoneObject) return;
 
        zoneObject.updateFromURL(this.url);
 
        (this.onUpdate || Prototype.emptyFunction).apply(this, arguments);
    }
});
 
Tapestry.Initializer.periodicupdater = function(spec)
{
    var elementId = spec.elementId;
    var uri = spec.uri;
    var period = spec.period;
    
    $T(elementId).periodicupdater = new Tapestry.PeriodicUpdater(elementId, uri, period);
};

