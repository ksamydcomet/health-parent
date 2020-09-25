var dcomethealth = dcomethealth || {};
dcomethealth.PharmacyDashboard = ( function (){
    title: "Dash Board"    
    var id = "PharmacyDashboard";
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
    }
}());

dcomethealth.PharmacyDashboard.init();