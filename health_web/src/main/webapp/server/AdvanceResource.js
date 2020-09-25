var dcomethealth = dcomethealth || {};
dcomethealth.AdvanceResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/advance/v1",
    saveAdvance: function (data, arg, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/save/" + arg,
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp"
                            : "json"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    saveRefundByAdvance: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/saverefundwithadvance",
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp"
                            : "json"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    saveAdvanceList: function (data, arg, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/saveList/" + arg,
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp"
                            : "json"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    searchAdvances: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/search",
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp"
                            : "json"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    searchAdvancesReport: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/advance",
                    data: JSON.stringify(data),
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: "html"
                }).done(function (data, textStatus, jqXHR) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    }

};

