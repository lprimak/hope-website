(function() {
    define(["t5/core/dom", "t5/core/events", "js/jquery-ui-1.10.3.custom.js"], function(dom, events) {
        return function(highlightColor) {
            dom.onDocument(events.zone.didUpdate, function() {
                this.$.effect("highlight", { color: highlightColor }, 800);
            });
        };
    });
}).call(this);
