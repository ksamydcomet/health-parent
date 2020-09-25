var dcomethealth = dcomethealth || {};
dcomethealth.SecurityResource = {
    baseURL: dcomethealth.ServerRequestResource.baseURL + "/security",
    logout: function () {
        var _this = this;
        dcomethealth.ServerRequestResource.ajaxLoadingStart();
        return $
                .ajax({
                    type: "GET",
                    url: "/health_web/logout",
                    headers: {"Accept": "application/json", "Content-Type": "application/json; charset=utf-8"},
                    dataType: dcomethealth.ServerRequestResource.useCrossDomain ? "jsonp"
                            : "json"
                }).done(function (data, textStatus, jqXHR) {
            _this.login();
            dcomethealth.ServerRequestResource.ajaxLoadingStop();

        }).fail(function (jqXHR, textStatus, errorThrown) {
            _this.login();
            dcomethealth.ServerRequestResource.ajaxLoadingStop();
        });
    },
    login: function () {
        var _this = this;
        var data = $.ajax({
            password: "xxxxxxxx",
            username: "invalid",
            url: "/health_web/j_security_check",
            type: 'GET',
            async: false
        });
        _this.clearAuthentication();
    },
    clearAuthentication: function () {
        document.execCommand("ClearAuthenticationCache");
        window.location = '/health_web/';
    }
};

// @ sourceURL = server/SecurityResource.js
