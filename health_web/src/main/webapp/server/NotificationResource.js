var dcomethealth = dcomethealth || {};
dcomethealth.NotificationResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/notification",
    searchNotification: function (searchQuery, callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/wl/" + searchQuery,
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    },
    searchWorklistTask: function (searchQuery, callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/worklisttask/" + searchQuery,
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    },
    searchPermittedActions: function (searchQuery, callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/worklistaction/" + searchQuery,
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    },
    searchMasterCode: function (searchQuery, callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/boCode/" + searchQuery,
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    },
    getBOWorklistConfig: function (callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/worklist/config",
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    },
    getBOMaster: function (callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/worklist/master",
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    },
    getBOWorklistSettings: function (callback, errorCallback) {
        var xhr = $.ajax({
            type: "GET",
            url: this.baseURL + "/worklist/settings",
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
        });
        return xhr;
    }
};