/**
 * Disable Submit button after AJAX Form Submission
 */

var DisableAfterSubmit = Class.create();
DisableAfterSubmit.prototype = {
		initialize: function(elementId, formId, zoneId) {
			this.formId = formId;
			this.elementId = elementId;
			this.zoneId = zoneId;

			Event.observe($(elementId), 'click',
					this.doDisable.bindAsEventListener(this));
			
			Event.observe($(zoneId), Tapestry.ZONE_UPDATED_EVENT,
					this.doEnable.bindAsEventListener(this));
		},

		doDisable: function(e) {
			$(this.elementId).disable();
			$(this.formId).onsubmit();
		},
		
		doEnable: function(e) {
			$(this.elementId).enable();
		}
};
