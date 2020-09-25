var dcomethealth = dcomethealth || {};
dcomethealth.GoodsReceiptResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/goodsReceipt/v1",
    saveGRN: function (data, callback, errorCallback) {
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
    searchGRN: function (data, callback, errorCallback) {
        var xhr = $.ajax({
            type: "POST",
            url: this.baseURL + "/search",
            data: JSON.stringify(data),
            headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json",
            success: function (data, textStatus, jqXHR) {
                $.isFunction(callback) && callback(data, textStatus, jqXHR);
            },
            error: errorCallback,
        });
        return xhr;
    }

};