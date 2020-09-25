var dcomethealth = dcomethealth || {};
dcomethealth.EmailResource = {
    saveContactDetails: function (data, callback, errorCallback) {        
//        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: "rest/email/v1/contact/save",
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: "json"
                }).done(function (data, textStatus, jqXHR) {                    
//            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {            
//            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    }
};
