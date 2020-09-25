var dcomethealth = dcomethealth || {};
dcomethealth.ServerRequestResource = {
    baseURL: "rest",
    useCrossDomain: false,
    ajaxLoadingStart: function () {
        $(document).ready(function () {
            $.blockUI({
                message: '<h3><img src="css/images/wait.gif" width="30px"/> Loading ..</h3>',
                css: {
                    border: 'none',
                    padding: '15px',
                    color: '#000',
                    backgroundColor: '#fff',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px'
                }});
            setTimeout($.unblockUI, 1000);
        });
    },
    ajaxLoadingStop: function () {
        $(document).ready(function () {
            $.unblockUI();
        });
    },
    ajaxImageLoadingStart: function () {
        $.blockUI({
            message: "<h3>Loading...</h3>",
            css: {
                padding: 30,
                margin: 0,
                width: '20%',
                top: '40%',
                left: '35%',
                textAlign: 'center',
                color: '#000',
                border: '1px solid #aaa',
                backgroundColor: '#e6e6e6',
                cursor: 'wait',
                '-webkit-border-radius': '5px',
                '-moz-border-radius': '5px',
                'border-radius': '5px'
            },
            // minimal style set used when themes are used
            themedCSS: {
                width: '20%',
                top: '40%',
                left: '35%'
            },
            // styles for the overlay
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.0,
                cursor: 'wait'
            }
        });
    },
//    ajaxImageLoadingStop: $.unblockUI,
    ajaxRequest: function (ajaxOptions, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        var deferred = $.Deferred();
        $.ajax($.extend({
            success: function (data, textStatus, jqXHR) {
                dcomethealth.ServerRequestResource.ajaxLoadingStop();
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
                deferred.resolve(data, textStatus, jqXHR);
            },
            error: function () {
                dcomethealth.ServerRequestResource.ajaxLoadingStop();
                $.isFunction(errorCallback) && errorCallback.apply(this, arguments);
                deferred.reject();
            }
        }, ajaxOptions));
        return deferred;
    },
    globelMessage: function (status, message) {
        var globelmessage = $("#globel-message-label");
        if (globelmessage) {
            globelmessage.show();
            if (0 === status) {
                globelmessage.removeClass('globel-message-fail').addClass("globel-message-success");
            }
            else {
                globelmessage.removeClass('globel-message-success').addClass("globel-message-fail");
            }
            globelmessage.text(message);
            globelmessage.delay(4000).fadeOut('slow');
        }
    }
};