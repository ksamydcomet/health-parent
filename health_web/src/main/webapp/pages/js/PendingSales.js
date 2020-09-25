var dcomethealth = dcomethealth || {};
dcomethealth.PendingSales = (function () {
    var id = "PendingSales";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {

        });
    }
    function refreshData() {
    }
    return {
        init: init,
        refreshData: refreshData
    };
}());
dcomethealth.PendingSales.init();