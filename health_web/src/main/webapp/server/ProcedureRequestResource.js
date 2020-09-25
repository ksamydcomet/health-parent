var dcomethealth = dcomethealth || {};
dcomethealth.ProcedureRequestResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/procedurerequest/v1",
    saveProcedureRequest: function (data, args, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/procedurerequest/save/" + args,
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    getProcedureRequest: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/procedureRequest/search",
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
};