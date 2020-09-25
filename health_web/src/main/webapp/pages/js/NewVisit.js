var dcomethealth = dcomethealth || {};
var delivery = {};
dcomethealth.NewVisit = (function () {
    var id = "NewVisit";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().startOf('days'), end = moment().endOf('days');
            $('#reportsRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
//            $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
//                if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(entities.id)) {
//                    $('#entity').val(entities.entityShortName);
//                }
//            });
            $('#reportsRange').daterangepicker({
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Last 7 Days': [moment().subtract('days', 6), moment()],
                    'Last 30 Days': [moment().subtract('days', 29), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                opens: 'left',
                startDate: moment().subtract('days', 29),
                endDate: moment()
            },
            function (start, end) {
                $('#reportsRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                newVisitsReport();
            });
        });
    }



    function newVisitsReport() {
//        $('#dcRange').attr("disabled", true);
//        var classCheck = $("#entityDiv").hasClass('hidden');
//        var date = $('#dcRangeSpan').html().split('-');
//        var searchObj;
//        if (!classCheck) {
//            $('#entityTh1,#entityTh2').removeClass('hidden');
//            if ($("#entityValues option:selected").val() == 0) {
//                searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
//            } else {
//                searchObj = $("#entityValues option:selected").val() + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
//            }
//        } else {
//            searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
//        }

        var date = $('#reportsRangeSpan').html().split('-');
        $("#report_thead").empty();
        dcomethealth.PatientResource.searchVisit({"visReasonIndex": 26, "visFromDate": moment(date[0]), "visToDate": moment(date[1]).add(1, 'days')}, function (data) {
            console.log(data)
//            $("#report_thead").append('<tr><th>Visit Reason</th><th>MRN</th><th>Patient Name</th><th>Contact No</th><th>Speciality</th></tr>');
            var tables = $.fn.dataTable.fnTables(true);
            $(tables).each(function () {
                $(this).dataTable().fnClearTable();
                $(this).dataTable().fnDestroy();
            });
            if (!!data && data !== "") {
                $.each(data, function (i1, visit) {
                    $.each(visit.patient, function (pIdx, patient) {
                        $("#report_tbody").append('<tr><td>' + visit.visReasonName + '</td><td>' + patient.patMrnNo + '</td><td>' + patient.patFullName + '</td><td>' + patient.patPhoneNo + '</td><td>' + visit.visSpecialityName + '</td></tr>');
                    });
                });
                $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                alert("No data Found");
            }
        });
//            $("#dc_rptbdy").empty();
//            $("#dc_rptbdy_Export").empty();
//            if (data.length > 0) {
//                $("#date").text(moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + " " + "TO" + " " + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD'));
//                $.each(data, function (pIdx, rpt) {
//                    if (!classCheck) {
//                        $("#dc_rptbdy").append('<tr><td>' + rpt[0] + '</td><td>' + rpt[2] + '</td><td>' + rpt[3] + '</td><td>' + rpt[4] + '</td><td>' + rpt[5] + '</td><td>' + rpt[6] + '</td><td>' + rpt[7] + '</td></tr>');
//                        $("#dc_rptbdy_Export").append('<tr><td>' + rpt[0] + '</td><td>' + rpt[2] + '</td><td>' + rpt[3] + '</td><td>' + rpt[4] + '</td><td>' + rpt[5] + '</td><td>' + rpt[6] + '</td><td>' + rpt[7] + '</td></tr>');
//                    } else {
//                        $("#dc_rptbdy").append('<tr><td class="hidden">--</td><td>' + rpt[2] + '</td><td>' + rpt[3] + '</td><td>' + rpt[4] + '</td><td>' + rpt[5] + '</td><td>' + rpt[6] + '</td><td>' + rpt[7] + '</td></tr>');
//                        $("#dc_rptbdy_Export").append('<tr><td class="hidden">--</td><td>' + rpt[2] + '</td><td>' + rpt[3] + '</td><td>' + rpt[4] + '</td><td>' + rpt[5] + '</td><td>' + rpt[6] + '</td><td>' + rpt[7] + '</td></tr>');
//                    }
//                });
//                $('#dcRange').attr("disabled", false);
//                $('#dcReportTable').dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                $('.dataTables_filter input').attr('placeholder', 'Search...');
////            $('.dataTables_length select').addClass('form-control');
//            } else {
//                $('#dcRange').attr("disabled", false);
//                $('#dcReportTable').dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                $('.dataTables_filter input').attr('placeholder', 'Search...');
//            }
//
//        });
    }

    function  exportExcel(val) {
        var blob = null;

        $('#dcReportTableExport').attr('border', 1);
        var htmltable = document.getElementById('dcReportTableExport').outerHTML;
        blob = new Blob([htmltable], {
            type: "data:application/vnd.ms-excel"
        });
        saveAs(blob, "DCReport.xls");

    }
    function refreshData() {
    }
    return {
        init: init,
        newVisitsReport: newVisitsReport,
        exportExcel: exportExcel,
        refreshData: refreshData

    };
}());
dcomethealth.NewVisit.init();