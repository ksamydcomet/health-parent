var dcomethealth = dcomethealth || {};
dcomethealth.Notification = (function () {
    title: "Notification"
    var id = "Notification";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
//            dcomethealth.datatypes.init($("#" + id));            
            $.each(dcomethealth.workListConfig, function (pIdx, workListConfig) {
                if (workListConfig.bowcRid === dcomethealth.bowBowcRid) {
                    var headerArray = workListConfig.bowcHeaderSpec.split("&");
                    var row = '<tr>';
                    $.each(headerArray, function (i) {
                        row += '<th width="30%">' + headerArray[i] + '</th>';
                    });
                    row += '</tr>';
                    $("#tr_head").append(row);
                }
            });
            dcomethealth.NotificationResource.searchWorklistTask(dcomethealth.bowBowcRid + "/" + dcomethealth.selectedunit + "/" + new Date(), function (data) {
                var categories = [];
                $.each(data, function (pIdx, workList) {                       
                    if ($.inArray(workList.bowBoRid, categories) === -1) {
                        categories.push(workList.bowBoRid);
                        var array = workList.bowDesc.split("&");
                        var row = '<tr onclick="dcomethealth.Notification.worklistaction(' + workList.bowBomTypeIndex + ',' + workList.bowBoRid + ', \'' + workList.bowBoaCode + '\');">';
                        $.each(array, function (i) {
                            row += '<td>' + array[i] + '<input type="hidden" name="bowRid" value="' + workList.bowRid + '">\n\
                                                    <input type="hidden" name="boCode" value="' + workList.bowBoaCode + '">\n\
                                                    <input type="hidden" name="worklistType" value="' + workList.bowBomTypeIndex + '">\n\
                                                    <input type="hidden" name="worklistDate" value="' + workList.bowDate + '"></td>';
                        });                       
                        row += '</tr>';
                        $("#tr_details").append(row);
//                    $("#taksDynTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                    $('.dataTables_length select').addClass('form-control');
                    }
                });
            });
        });
    }
    function worklistaction(typeIndex, boRID, bowBoaCode) {
        dcomethealth.boRID = boRID;
        dcomethealth.bomtypeIndex = typeIndex;
        dcomethealth.NotificationResource.searchPermittedActions(typeIndex + "/" + boRID, function (data) {            
            var categories = [];
            dcomethealth.actionDatalist = data;
            dcomethealth.commonInit = 1;
            var i = 1;
            $.each(data, function (pIdx, data) {
                if ($.inArray(bowBoaCode, categories) === -1) {
                    categories.push(bowBoaCode);
                    if (data.boaCode === bowBoaCode) {
                        dcomethealth.util.loadpage(dcomethealth.security.getFeatureName(data.boaFeatureRid));
                    } else if (bowBoaCode === 'BUILTIN_ACTION') {
                        if (parseInt(i) === 1) {
                            dcomethealth.util.loadpage(dcomethealth.security.getFeatureName(data.boaFeatureRid));
                        }
                        i++;
                    }
                }
            });
        });
    }
    function exportExcel() {
        var id = "taksDynTable";
        var htmltable = document.getElementById(id);
        var html = htmltable.outerHTML;
        var a = document.createElement('a');
        var data_type = 'data:application/vnd.ms-excel';
        a.href = data_type + ', ' + encodeURIComponent(html);
        a.download = "task" + '.xls';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    }
    function refreshData() {
    }
    return {
        init: init,
        worklistaction: worklistaction,
        refreshData: refreshData,
        exportExcel: exportExcel
    };
}());
dcomethealth.Notification.init();