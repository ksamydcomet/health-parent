var dcomethealth = dcomethealth || {};
dcomethealth.AppointmentsResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/appointment/v1",
    saveAppointments: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/save",
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
    searchAppointments: function (data, callback, errorCallback) {
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
    searchAppointmentsCount: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/search/count",
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
    searchAppointmentsByMobileNo: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/search/appointment",
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
    searchAppointmentByResource: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/arm/search",
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
    searchAppointmentSlots: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/appointmentslots/search",
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
    }
};
