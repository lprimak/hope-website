/** 
 * Triggers a zone update after a timer expires
 */

var DeferUpdate = Class.create();
DeferUpdate.prototype = {
		initialize: function(zoneId) {
                    this.zoneId = zoneId;
                    this.handler = this.doDefer.bindAsEventListener(this)
			Event.observe($(zoneId), Tapestry.ZONE_UPDATED_EVENT,
    					this.handler);
		},
		
		doDefer: function() {
                    if($T(this.zoneId).PeriodicUpdater.updateTriggered == false)
                    {
                        $T(this.zoneId).PeriodicUpdater.stop();
                        $T(this.zoneId).PeriodicUpdater.start(true);                            
                    }
                    else
                    {
                        $T(this.zoneId).PeriodicUpdater.updateTriggered = false;
                    }
		}
}
