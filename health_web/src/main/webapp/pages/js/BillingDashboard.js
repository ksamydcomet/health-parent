var dcomethealth = dcomethealth || {};
dcomethealth.BillingDashboard = (function () {
    var id = "BillingDashboard";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.DashBoardResource.getTodayBillCollection(function (data) {
                $.each(data, function (pIdx, billCollection) {
                    $("#totalAmount").text(!!billCollection.totalAmount ? billCollection.totalAmount : 0);
                    $("#totalCash").text(!!billCollection.totalCash ? billCollection.totalCash : 0);
                    $("#totalCard").text(!!billCollection.totalCard ? billCollection.totalCard : 0);
                    $("#totalCheque").text(!!billCollection.totalCheque ? billCollection.totalCheque : 0);
                });
            });
        });
    }
    function refreshData() {
    }
    return {
        init: init,
        refreshData: refreshData
    };
}());
dcomethealth.BillingDashboard.init();