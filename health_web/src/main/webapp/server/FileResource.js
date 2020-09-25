var dcomethealth = dcomethealth || {};
dcomethealth.FileResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/file",
//    ajaxStart: function () {
//        $.blockUI({
//            message: "<h3>Loading...</h3>",
//            css: {
//                padding: 30,
//                margin: 0,
//                width: '20%',
//                top: '40%',
//                left: '35%',
//                textAlign: 'center',
//                color: '#000',
//                border: '1px solid #aaa',
//                backgroundColor: '#e6e6e6',
//                cursor: 'wait',
//                '-webkit-border-radius': '5px',
//                '-moz-border-radius': '5px',
//                'border-radius': '5px'
//            },
//            // minimal style set used when themes are used
//            themedCSS: {
//                width: '20%',
//                top: '40%',
//                left: '35%'
//            },
//            // styles for the overlay
//            overlayCSS: {
//                backgroundColor: '#fff',
//                opacity: 0.0,
//                cursor: 'wait'
//            }
//        })
//    },
//    ajaxStop: $.unblockUI,
    uploadUserImage: function (file, fkId, callback, errorCallback, progressHandlingFunction) {
        var formData = new FormData();
        formData.append('file', file);
//        dcomethealth.FileResource.ajaxStart();

        $.ajax({
            type: "POST",
            url: this.baseURL + "/user/photo/upload?id=" + fkId,
            xhr: function () {  // custom xhr
                myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) { // check if upload property exists
                    myXhr.upload.addEventListener('progress', progressHandlingFunction, false); // for handling the progress of the upload
                }
                return myXhr;
            },
            success: function () {
//                dcomethealth.FileResource.ajaxStop();
                $.isFunction(callback) && callback.apply(this, arguments);
            },
            error: function () {
//                dcomethealth.FileResource.ajaxStop();
                $.isFunction(errorCallback) && errorCallback.apply(this, arguments);
            },
            data: formData,
            //Options to tell JQuery not to process data or worry about content-type
            cache: false,
            contentType: false,
            processData: false,
            accepts: {
                json: "application/json"
            },
            dataType: "json"
        });
    },
    uploadFile: function (file, callback, errorCallback, progressHandlingFunction) {
        var formData = new FormData();
        formData.append('file', file);
//        dcomethealth.FileResource.ajaxStart();

        $.ajax({
            type: "POST",
            url: this.baseURL + "/user/photo/upload?id=1",
            xhr: function () {  // custom xhr
                myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) { // check if upload property exists
                    myXhr.upload.addEventListener('progress', progressHandlingFunction, false); // for handling the progress of the upload
                }
                return myXhr;
            },
            success: function () {
//                dcomethealth.FileResource.ajaxStop();
                $.isFunction(callback) && callback.apply(this, arguments);
            },
            error: function () {
//                dcomethealth.FileResource.ajaxStop();
                $.isFunction(errorCallback) && errorCallback.apply(this, arguments);
            },
            data: formData,
            //Options to tell JQuery not to process data or worry about content-type
            cache: false,
            contentType: false,
            processData: false,
            accepts: {
                json: "application/json"
            },
            dataType: "json"
        });
    },
    deleteFile: function (id, callback, errorCallback) {
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/info/delete?Id=" + id,
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
                }).done(function (data, textStatus, jqXHR) {
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    uploadFiles: function (file, callback, errorCallback, progressHandlingFunction) {
        var formData = new FormData();
        formData.append('file', file);
//        dcomethealth.FileResource.ajaxStart();
        $.ajax({
            type: "POST",
            url: this.baseURL + "/upload",
            xhr: function () {  // custom xhr
                myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) { // check if upload property exists
                    myXhr.upload.addEventListener('progress', progressHandlingFunction, false); // for handling the progress of the upload
                }
                return myXhr;
            },
            success: function () {
                $.isFunction(callback) && callback.apply(this, arguments);
            },
            error: function () {
                $.isFunction(errorCallback) && errorCallback.apply(this, arguments);
            },
            data: formData,
            //Options to tell JQuery not to process data or worry about content-type
            cache: false,
            contentType: false,
            processData: false,
            accepts: {
                json: "application/json"
            },
            dataType: "json"
        });
    },
    getContentType: function (imageid, callback, errorCallback) {
//        dcomethealth.FileResource.ajaxStart();
        $.ajax({
            type: "GET",
            url: this.baseURL + "/mimetype/" + encodeURI(imageid),
            dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp"
                    : "json",
            success: function () {
//                dcomethealth.FileResource.ajaxStop();
                $.isFunction(callback) && callback.apply(this, arguments);
            },
            error: function () {
//                dcomethealth.FileResource.ajaxStop();
                $.isFunction(errorCallback) && errorCallback.apply(this, arguments);
            }
        });
    },
    saveFileInfo: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxImageLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/info/save",
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
    getFileInfo: function (id, type, callback, errorCallback) {
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/info?Id=" + id + "&Type=" + type,
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp" : "json"
                }).done(function (data, textStatus, jqXHR) {
            $.isFunction(callback) && callback(data, textStatus, jqXHR);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            ($.isFunction(errorCallback)) ? errorCallback(jqXHR, textStatus, errorThrown) : console.log("ERROR " + errorThrown);
        });
    },
    uploadAttachments: function (file, refRid, taType, taFileDesc, callback, errorCallback, progressHandlingFunction) {
        var formData = new FormData();
        for (var i = 0, len = file.length; i < len; i++) {
            formData.append('file', file[i]);
        }
        $.ajax({
            type: "POST",
            url: this.baseURL + "/upload/attachments?refRid=" + refRid + "&taFileDesc=" + taFileDesc + "&taType=" + taType,
            xhr: function () {  // custom xhr
                myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) { // check if upload property exists
                    myXhr.upload.addEventListener('progress', progressHandlingFunction, false); // for handling the progress of the upload
                }
                return myXhr;
            },
            success: function () {
                $.isFunction(callback) && callback.apply(this, arguments);
            },
            error: function () {
                $.isFunction(errorCallback) && errorCallback.apply(this, arguments);
            },
            data: formData, //Options to tell JQuery not to process data or worry about content-type
            cache: false,
            contentType: false,
            processData: false,
            accepts: {
                json: "application/json"
            },
            dataType: "json"
        });
    },
    searchFile: function (data, callback, errorCallback) {
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "POST",
                    url: this.baseURL + "/search",
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
    }
};
