(function (document, $) {
    "use strict";
    // when dialog gets injected
    $(document).on("foundation-contentloaded", function (e) {
        // if there is already an inital value make sure the according target element becomes visible
        checkboxShowHideHandler($(".cq-dialog-checkbox-showhide", e.target));
    });

    $(document).on("change", ".cq-dialog-checkbox-showhide", function (e) {
        checkboxShowHideHandler($(this));
    });

    function checkboxShowHideHandler(el) {
        el.each(function (i, element) {
            if($(element).is("coral-checkbox")) {
                // handle Coral3 base drop-down
                Coral.commons.ready(element, function (component) {
                    showHide(component, element);
                    component.on("change", function () {
                        showHide(component, element);
                    });
                });
            } else {
                // handle Coral2 based drop-down
                var component = $(element).data("checkbox");
                if (component) {
                    showHide(component, element);
                }
            }
        })
    }
    function showHide(component, element) {
        // get the selector to find the target elements. its stored as data-.. attribute
        var target = $(element).data("cqDialogCheckboxShowhideTarget");
        var $target = $(target);
        if (target) {
            $target.hide();
            
            if($target.selector == ".toggleBannerOneCTA"){
                $('input[name="./callToActionText"]').prop('required',false);
                $('input[name="./callToActionLink"]').prop('required',false);
            }
            if (component.checked) {
                if($target.selector == ".toggleBannerOneCTA"){
                    $('input[name="./callToActionText"]').prop('required',true);
                    $('input[name="./callToActionLink"]').prop('required',true);
                }
                $target.show();
            }
        }
    }
})(document, Granite.$);
