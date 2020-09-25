var dcomethealth = dcomethealth || {};
dcomethealth.ReportView = (function () {
    var id = "ReportView", payerMaster = {}, payerName;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().startOf('days');
            var end = moment().endOf('days');
            $('#ReportDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $('#ReportDateRange').daterangepicker({
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
                endDate: moment(),
                showDropdowns: true
            },
            function (start, end) {
                $('#ReportDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                View($('#report_head').text());
            });
            dcomethealth.ReportView.View(dcomethealth.opdVal);
            dcomethealth.MasterResource.searchUnit({}, function (data) {
                $.each(data, function (pIdx, unit) {
                    $('#unitWiseSelect').append('<option value="' + unit.id + '">' + unit.unitName + '</option>');
                });
            });
            dcomethealth.MasterResource.searchPayerMaster({}, function (data) {
                $.each(payerMaster = data, function (pSx, payer) {
                    $('#payerWiseSelect').append('<option value="' + payer.pdId + '">' + payer.pdPayerName + '</option>');
                });
            });
//            $("#unitWiseSelect").on("change", function () {
//                viewDetails();
//            });
            $("#typeWiseSelect").on("change", function () {
                viewCollectionsDetails();
            });
            $("#payerWiseSelect").on("change", function () {
                View("Payer Sales");
            });
        });
    }
    function View(val) {
        $('#report_head').text(val);
        var date = $('#ReportDateRangeSpan').html().split('-');
        $("#report_thead").empty();
        $("#report_thead_excel").empty();
        $("#report_tbody").empty();
        $("#report_tbody_excel").empty();
        switch (val) {
            case "NewVisits" :
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + val;
                dcomethealth.PatientResource.searchVisitReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>User Name</th><th>Patient Name</th><th>Visit Reason</th><th>Speciality</th><th>Res. Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Contact No</th><th>Address</th><th>Country</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>User Name</th><th>Patient Name</th><th>Visit Reason</th><th>Speciality</th><th>Res. Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Contact No</th><th>Address</th><th>Country</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, visitData) {
                            var refType = visitData[6] != null ? visitData[6] : "--"
                            var refname = visitData[7] != null ? visitData[7] : "--"
                            var patAddress = visitData[9] != null ? visitData[9] : "--"
                            var patCountry = visitData[10] != null ? visitData[10] : "--"
                            $("#report_tbody").append('<tr><td>' + visitData[0] + '</td><td>' + visitData[1] + '</td><td>' + visitData[2] + '</td><td>' + visitData[3] + '</td><td>' + visitData[4] + '</td><td>' + visitData[5] + '</td><td>' + refType + '</td><td>' + refname + '</td><td>' + visitData[8] + '</td><td>' + patAddress + '</td><td>' + patCountry + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + visitData[0] + '</td><td>' + visitData[1] + '</td><td>' + visitData[2] + '</td><td>' + visitData[3] + '</td><td>' + visitData[4] + '</td><td>' + visitData[5] + '</td><td>' + refType + '</td><td>' + refname + '</td><td>' + visitData[8] + '</td><td>' + patAddress + '</td><td>' + patCountry + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "RepeatVisits" :
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + val;
                dcomethealth.PatientResource.searchVisitReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>User Name</th><th>Patient Name</th><th>Visit Reason</th><th>Speciality</th><th>Res. Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Contact No</th><th>Address</th><th>Country</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>User Name</th><th>Patient Name</th><th>Visit Reason</th><th>Speciality</th><th>Res. Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Contact No</th><th>Address</th><th>Country</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, visitData) {
                            var refType = visitData[6] != null ? visitData[6] : "--"
                            var refname = visitData[7] != null ? visitData[7] : "--"
                            var patAddress = visitData[9] != null ? visitData[9] : "--"
                            var patCountry = visitData[10] != null ? visitData[10] : "--"
                            $("#report_tbody").append('<tr><td>' + visitData[0] + '</td><td>' + visitData[1] + '</td><td>' + visitData[2] + '</td><td>' + visitData[3] + '</td><td>' + visitData[4] + '</td><td>' + visitData[5] + '</td><td>' + refType + '</td><td>' + refname + '</td><td>' + visitData[8] + '</td><td>' + patAddress + '</td><td>' + patCountry + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + visitData[0] + '</td><td>' + visitData[1] + '</td><td>' + visitData[2] + '</td><td>' + visitData[3] + '</td><td>' + visitData[4] + '</td><td>' + visitData[5] + '</td><td>' + refType + '</td><td>' + refname + '</td><td>' + visitData[8] + '</td><td>' + patAddress + '</td><td>' + patCountry + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "Male Visits" :
                dcomethealth.PatientResource.searchVisit({"visFromDate": moment(date[0]), "visToDate": moment(date[1]).add(1, 'days')}, function (visitData) {
                    if (!!visitData && visitData !== "") {
                        $("#report_thead").empty();
                        $("#report_thead_excel").empty();
                        $("#report_tbody").empty();
                        $("#report_tbody_excel").empty();
                        $("#report_thead").append('<tr><th>Visit Reason</th><th>MRN</th><th>Patient Name</th><th>Contact No</th><th>Speciality</th></tr>');
                        $("#report_thead_excel").append('<tr><th>Visit Reason</th><th>MRN</th><th>Patient Name</th><th>Contact No</th><th>Speciality</th></tr>');
                        var tables = $.fn.dataTable.fnTables(true);
                        $(tables).each(function () {
                            $(this).dataTable().fnClearTable();
                            $(this).dataTable().fnDestroy();
                        });
                        $.each(visitData, function (i1, visit) {
                            $.each(visit.patient, function (pIdx, patient) {
                                if (patient.patGenderIndex == 1) {
                                    $("#report_tbody").append('<tr><td>' + visit.visReasonName + '</td><td>' + patient.patMrnNo + '</td><td>' + patient.patFullName + '</td><td>' + patient.patPhoneNo + '</td><td>' + visit.visSpecialityName + '</td></tr>');
                                    $("#report_tbody_excel").append('<tr><td>' + visit.visReasonName + '</td><td>' + patient.patMrnNo + '</td><td>' + patient.patFullName + '</td><td>' + patient.patPhoneNo + '</td><td>' + visit.visSpecialityName + '</td></tr>');
                                }
                            });
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "Female Visits" :
                dcomethealth.PatientResource.searchVisit({"visFromDate": moment(date[0]), "visToDate": moment(date[1]).add(1, 'days')}, function (visitData) {
                    if (!!visitData && visitData !== "") {
                        $("#report_thead").empty();
                        $("#report_thead_excel").empty();
                        $("#report_tbody").empty();
                        $("#report_tbody_excel").empty();
                        $("#report_thead").append('<tr><th>Visit Reason</th><th>MRN</th><th>Patient Name</th><th>Contact No</th><th>Speciality</th></tr>');
                        $("#report_thead_excel").append('<tr><th>Visit Reason</th><th>MRN</th><th>Patient Name</th><th>Contact No</th><th>Speciality</th></tr>');
                        var tables = $.fn.dataTable.fnTables(true);
                        $(tables).each(function () {
                            $(this).dataTable().fnClearTable();
                            $(this).dataTable().fnDestroy();
                        });
                        $.each(visitData, function (i1, visit) {
                            $.each(visit.patient, function (pIdx, patient) {
                                if (patient.patGenderIndex == 2) {
                                    $("#report_tbody").append('<tr><td>' + visit.visReasonName + '</td><td>' + patient.patMrnNo + '</td><td>' + patient.patFullName + '</td><td>' + patient.patPhoneNo + '</td><td>' + visit.visSpecialityName + '</td></tr>');
                                    $("#report_tbody_excel").append('<tr><td>' + visit.visReasonName + '</td><td>' + patient.patMrnNo + '</td><td>' + patient.patFullName + '</td><td>' + patient.patPhoneNo + '</td><td>' + visit.visSpecialityName + '</td></tr>');
                                }
                            });
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "TotalVisits" :
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + val;
                var newPatName, revPatName;
                dcomethealth.PatientResource.searchVisitReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>New Patient Name</th><th>Review Patient Name</th><th>Visit Reason</th><th>Speciality</th><th>Res. Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Contact No</th><th>Address</th><th>Country</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>New Patient Name</th><th>Review Patient Name</th><th>Visit Reason</th><th>Speciality</th><th>Res. Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Contact No</th><th>Address</th><th>Country</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, visitData) {
                            if (visitData[1] == 26) {
                                newPatName = visitData[2], revPatName = "--";
                            } else {
                                revPatName = visitData[2], newPatName = "--";
                            }
                            var refType = visitData[6] != null ? visitData[6] : "--"
                            var refname = visitData[7] != null ? visitData[7] : "--"
                            var patAddress = visitData[9] != null ? visitData[9] : "--"
                            var patCountry = visitData[10] != null ? visitData[10] : "--"
                            var visitReason = visitData[3] != null ? visitData[3] : "--"
                            var speaciality = visitData[4] != null ? visitData[4] : "--";
                            $("#report_tbody").append('<tr><td>' + visitData[0] + '</td><td>' + newPatName + '</td><td>' + revPatName + '</td><td>' + visitReason + '</td><td>' + speaciality + '</td><td>' + visitData[5] + '</td><td>' + refType + '</td><td>' + refname + '</td><td>' + visitData[8] + '</td><td>' + patAddress + '</td><td>' + patCountry + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + visitData[0] + '</td><td>' + newPatName + '</td><td>' + revPatName + '</td><td>' + visitReason + '</td><td>' + speaciality + '</td><td>' + visitData[5] + '</td><td>' + refType + '</td><td>' + refname + '</td><td>' + visitData[8] + '</td><td>' + patAddress + '</td><td>' + patCountry + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No Data Found");
                    }
                });
                break;
            case "Billing Sales" :
                $("#paidTotalDiv,#discTotalDiv,#dueTotalDiv").addClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.BillingResource.getBillingSalesReport(searchObj, function (data) {
                    var netTotal = 0;
                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>Bill No</th><th>User Name</th><th>Patient Name</th><th>Resident Doctor</th><th>Speciality</th><th>Gross Amount</th><th>Net Amount</th><th>Discount Amt</th><th>Paid Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>Bill No</th><th>User Name</th><th>Patient Name</th><th>Resident Doctor</th><th>Speciality</th><th>Gross Amount</th><th>Net Amount</th><th>Discount Amt</th><th>Paid Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, billData) {
                        var speciality = billData[9] != null ? billData[9] : "--"
                        var grossAmt = billData[5];
                        var netAmt = billData[6];
                        var paidAmt = billData[7]
                        var discAmt = billData[8];
                        netTotal += netAmt;
                        $("#report_tbody").append('<tr><td>' + billData[0] + '</td><td>' + billData[1] + '</td><td>' + billData[2] + '</td><td>' + billData[3] + '</td><td>' + billData[4] + '</td><td>' + speciality + '</td><td align="right">' + grossAmt.toFixed(2) + '</td><td align="right">' + netAmt.toFixed(2) + '</td><td align="right">' + discAmt.toFixed(2) + '</td><td align="right">' + paidAmt.toFixed(2) + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + billData[0] + '</td><td>' + billData[1] + '</td><td>' + billData[2] + '</td><td>' + billData[3] + '</td><td>' + billData[4] + '</td><td>' + speciality + '</td><td align="right">' + grossAmt.toFixed(2) + '</td><td align="right">' + netAmt.toFixed(2) + '</td><td align="right">' + discAmt.toFixed(2) + '</td><td align="right">' + paidAmt.toFixed(2) + '</td></tr>');
                        $("#net_count").text(netTotal.toFixed(2));
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Collections":
                $("#viewRowQty").addClass("hidden");
                $("#typeSpan,#typeWiseSelect,#colSummaryDetails").addClass("hidden");
                $("#collectionDetails").removeClass("hidden");
                $("#typeWiseSelect").val(0);
//                $("#salesDetails").removeClass("hidden");
                var totalCollection = 0;
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.BillingResource.getCollectionSummaryReport(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Type</th><th>Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Type</th><th>Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, collectionSummary) {
                        totalCollection += collectionSummary[1];
                        $("#report_tbody").append('<tr><td>' + collectionSummary[0] + '</td><td>' + collectionSummary[1] + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + collectionSummary[0] + '</td><td>' + collectionSummary[1] + '</td></tr>');
                        if (inx == (data.length - 1)) {
                            $("#report_tbody").append('<tr class="hidden"><td>Total Collection</td><td>' + totalCollection + '</td></tr>');
                            $("#report_tbody_excel").append('<tr class="hidden"><td>Total Collection</td><td>' + totalCollection + '</td></tr>');
                        }
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Unit Sales":
//                $("#unitSpan,#unitWiseSelect,#summaryDetails").addClass("hidden");
//                $("#unitWiseSelect").val(0);
//                $("#salesDetails").removeClass("hidden");
                $("#discTotalDiv, #dueTotalDiv, #paidTotalDiv").addClass("hidden");
                $("#grossTotalDiv").removeClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                var netTotal = 0, grossTotal = 0;
                dcomethealth.BillingResource.getUnitSalesSummaryReport(searchObj, function (data) {
                    $("#net_count").text(0), $("#gross_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th style="width: 50%">Unit Name</th><th style="width: 8%">Gross Amount</th><th style="width: 8%">Net Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Unit Name</th><th>Gross Amount</th><th>Net Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, unitSummary) {

                        grossTotal += unitSummary[1], netTotal += unitSummary[2];
                        $("#report_tbody").append('<tr><td>' + unitSummary[0] + '</td><td align="right">' + (unitSummary[1]).toFixed(2) + '</td><td align="right">' + (unitSummary[2]).toFixed(2) + '</td></tr>');
                        $("#net_count").text(netTotal.toFixed(2)), $("#gross_count").text(grossTotal.toFixed(2));
                        $("#report_tbody_excel").append('<tr><td>' + unitSummary[0] + '</td><td align="right">' + (unitSummary[1]).toFixed(2) + '</td><td align="right">' + (unitSummary[2]).toFixed(2) + '</td></tr>');
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Pending Sales":
                $("#discTotalDiv").addClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                var netTotal = 0, paidTotal = 0, dueTotal = 0, discTotal = 0;
                dcomethealth.BillingResource.getPendingSalesReport(searchObj, function (data) {
                    $("#net_count").text(0), $("#paid_count").text(0), $("#due_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>Bill No.</th><th>User Name</th><th>Patient Name</th><th>Speciality</th><th>Payer Type</th><th>Net Amount</th><th>Discount</th><th>Paid Amount</th><th>Due Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>Bill No.</th><th>User Name</th><th>Patient Name</th><th>Speciality</th><th>Payer Type</th><th>Net Amount</th><th>Discount</th><th>Paid Amount</th><th>Due Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, reportVal) {
                        var netAmt = reportVal[7] != null ? reportVal[7] : "--"
//                        var refType = labReport[6] != null ? labReport[6] : "--"                        
                        $("#report_tbody").append('<tr><td>' + reportVal[0] + '</td><td>' + reportVal[1] + '</td><td>' + reportVal[2] + '</td><td>' + reportVal[3] + '</td><td>' + reportVal[4] + '</td><td>' + reportVal[5] + '</td><td align="right">' + reportVal[6] + '</td><td align="right">' + netAmt + '</td><td align="right">' + reportVal[8] + '</td><td align="right">' + reportVal[9] + '</td></tr>');
                        netTotal += reportVal[6];
                        paidTotal += reportVal[8];
                        dueTotal += reportVal[9];
//                        discTotal += reportVal[11];
                        $("#net_count").text(netTotal.toFixed(2)), $("#paid_count").text(paidTotal.toFixed(2)), $("#due_count").text(dueTotal.toFixed(2));
                        $("#report_tbody_excel").append('<tr><td>' + reportVal[0] + '</td><td>' + reportVal[1] + '</td><td>' + reportVal[2] + '</td><td>' + reportVal[3] + '</td><td>' + reportVal[4] + '</td><td>' + reportVal[5] + '</td><td align="right">' + reportVal[6] + '</td><td align="right">' + reportVal[7] + '</td><td align="right">' + reportVal[8] + '</td><td align="right">' + reportVal[9] + '</td></tr>');
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Service Sales":
//                $("#serviceSpan,#serviceWiseSelect,#serSummaryDetails").addClass("hidden");
//                $("#serSalesDetails").removeClass("hidden");
                $("#paidTotalDiv,#discTotalDiv,#dueTotalDiv").addClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                var netTotal = 0, paidTotal = 0, dueTotal = 0, discTotal = 0;
                dcomethealth.BillingResource.getServiceSalesSummaryReport(searchObj, function (data) {
                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>UHID</th><th>User Name</th><th>Patient Name</th><th>Resident Doctor</th><th>Ref. Name</th><th>Service Type</th><th>Service Name</th><th>Net Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>UHID</th><th>User Name</th><th>Patient Name</th><th>Resident Doctor</th><th>Ref. Name</th><th>Service Type</th><th>Service Name</th><th>Net Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, serviceSummary) {
                        var refName = serviceSummary[5] != null ? serviceSummary[5] : "--"
                        var netAmount = serviceSummary[8];
                        netTotal += netAmount;
                        $("#report_tbody").append('<tr><td>' + serviceSummary[1] + '</td><td>' + serviceSummary[0] + '</td><td>' + serviceSummary[2] + '</td><td>' + serviceSummary[3] + '</td><td>' + serviceSummary[4] + '</td><td>' + refName + '</td><td>' + serviceSummary[6] + '</td><td>' + serviceSummary[7] + '</td><td align="right">' + netAmount.toFixed(2) + '</td></tr>');
                        $("#net_count").text(netTotal.toFixed(2));
                        $("#report_tbody_excel").append('<tr><td>' + serviceSummary[1] + '</td><td>' + serviceSummary[0] + '</td><td>' + serviceSummary[2] + '</td><td>' + serviceSummary[3] + '</td><td>' + serviceSummary[4] + '</td><td>' + refName + '</td><td>' + serviceSummary[6] + '</td><td>' + serviceSummary[7] + '</td><td align="right">' + netAmount.toFixed(2) + '</td></tr>');
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Payer Sales":
                $("#viewRowQty").addClass("hidden");
                $("#payerSpan,#payerWiseSelect").removeClass("hidden");
                if ($('#report_head').text() == "Payer Sales" && ($('#payerWiseSelect').val() != 0)) {
                    $(".overAllExcel").removeClass('hidden');
                } else {
                    $(".overAllExcel").addClass('hidden');
                }
                var payerRid = $('#payerWiseSelect option:selected').val();
//                var searchObj = "";               
//                if (payerRid != 0) {
//                    searchObj = {"bhFromDate": moment(date[0]), "bhToDate": moment(date[1]).add(1, 'days'), "bhPayerRID": payerRid};
//                    alert("")
//                } else {
//                    searchObj = {"bhFromDate": moment(date[0]), "bhToDate": moment(date[1]).add(1, 'days'), "bhPayerType": 31};
//                }
                searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + payerRid;
//                searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');                
                dcomethealth.BillingResource.getPayerSummary(searchObj, function (data) {
                    $("#net_count").text(0), $("#paid_count").text(0), $("#due_count").text(0), $("#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();

                    $("#report_thead").append('<tr><th>Date</th><th>UHID</th><th>Invoice No</th><th>Beneficiary Name</th><th>Beneficiary Contact</th><th>Beneficiary Address</th><th>Ref number</th><th>Payer Name</th><th>Claim Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>UHID</th><th>Invoice No</th><th>Beneficiary Name</th><th>Beneficiary Contact</th><th>Beneficiary Address</th><th>Ref number</th><th>Payer Name</th><th>Claim Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, payerSummary) {
//                            $.each(payerMaster, function (i, pm) {
//                                if (bill.billH.bhPayerRID == pm.pdId) {
//                                    payerName = pm.pdPayerName;
//                                }
//                            });
//                            a.bhBillDate = bill.billH.bhBillDate, a.bhBillNo = bill.billH.bhBillNo, a.bhBillNo = bill.billH.bhBillNo, a.bhPatientName = bill.billH.bhPatientName, a.bhApprovalNumber = bill.billH.bhApprovalNumber, a.bhEligibleAmount = bill.billH.bhEligibleAmount, a.bhBillNo = bill.billH.bhBillNo;
//                            $.each(bill.patient, function (inf38x, patient) {
//                                a.patFullName = patient.patFullName, a.patPhoneNo = patient.patPhoneNo, a.patAddress = !!patient.patAddress ? patient.patAddress : "--", a.patMrnNo = patient.patMrnNo;
//                            });
                        $("#report_tbody").append('<tr><td>' + payerSummary[0] + '</td><td>' + payerSummary[1] + '</td><td>' + payerSummary[2] + '</td><td>' + payerSummary[3] + '</td><td>' + payerSummary[4] + '</td><td>' + payerSummary[5] + '</td><td>' + payerSummary[6] + '</td><td>' + payerSummary[7] + '</td><td align="right">' + payerSummary[8] + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + payerSummary[0] + '</td><td>' + payerSummary[1] + '</td><td>' + payerSummary[2] + '</td><td>' + payerSummary[3] + '</td><td>' + payerSummary[4] + '</td><td>' + payerSummary[5] + '</td><td>' + payerSummary[6] + '</td><td>' + payerSummary[7] + '</td><td align="right">' + payerSummary[8] + '</td></tr>');
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Pharmacy Sales":
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                var netTotal = 0, paidTotal = 0, dueTotal = 0, discTotal = 0;
                dcomethealth.BillingResource.getPharmacySalesReport(searchObj, function (data) {
                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>Date</th><th>User Name</th><th>Patient Name</th><th>Res. Doctor</th><th>Net Amount</th><th>Discount</th><th>Paid Amount</th><th>Due Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>Date</th><th>User Name</th><th>Patient Name</th><th>Res. Doctor</th><th>Net Amount</th><th>Discount</th><th>Paid Amount</th><th>Due Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, pharmSales) {
                        var netAmt = pharmSales[5], paidAmt = pharmSales[7], discAmt = pharmSales[6], dueAmt = pharmSales[8];
                        netTotal += netAmt, discTotal += discAmt, paidTotal += paidAmt, dueTotal += dueAmt;
                        $("#report_tbody").append('<tr><td>' + pharmSales[0] + '</td><td>' + pharmSales[1] + '</td><td>' + pharmSales[2] + '</td><td>' + pharmSales[3] + '</td><td>' + pharmSales[4] + '</td><td align="right">' + netAmt.toFixed(2) + '</td><td align="right">' + discAmt.toFixed(2) + '</td><td align="right">' + paidAmt.toFixed(2) + '</td><td align="right">' + dueAmt.toFixed(2) + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + pharmSales[0] + '</td><td>' + pharmSales[1] + '</td><td>' + pharmSales[2] + '</td><td>' + pharmSales[3] + '</td><td>' + pharmSales[4] + '</td><td align="right">' + netAmt.toFixed(2) + '</td><td align="right">' + discAmt.toFixed(2) + '</td><td align="right">' + paidAmt.toFixed(2) + '</td><td align="right">' + dueAmt.toFixed(2) + '</td></tr>');
                        $("#net_count").text(netTotal.toFixed(2)), $("#paid_count").text(paidTotal.toFixed(2)), $("#due_count").text(dueTotal.toFixed(2)), $("#disc_count").text(discTotal.toFixed(2));
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
//            case "Wise Collection Report":
//                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
//                var netTotal = 0, paidTotal = 0, dueTotal = 0, discTotal = 0;
//                dcomethealth.BillingResource.getWiseCollectionReport(searchObj, function (data) {
//                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
//                    $("#report_thead").empty();
//                    $("#report_thead_excel").empty();
//                    $("#report_tbody").empty();
//                    $("#report_tbody_excel").empty();
//                    $("#report_thead").append('<tr><th>S.No</th><th>Name</th><th>Mode of payment</th><th>WireTransfer</th><th>Total</th></tr>');
//                    $("#report_thead_excel").append('<tr><th>S.No</th><th>Name</th><th>Mode of payment</th><th>WireTransfer</th><th>Total</th></tr>');
//                    var tables = $.fn.dataTable.fnTables(true);
//                    $(tables).each(function () {
//                        $(this).dataTable().fnClearTable();
//                        $(this).dataTable().fnDestroy();
//                    });
//                    $.each(data, function (inx, pharmSales) {
////                        var netAmt = pharmSales[5], paidAmt = pharmSales[7], discAmt = pharmSales[6], dueAmt = pharmSales[8];
////                        netTotal += netAmt, discTotal += discAmt, paidTotal += paidAmt, dueTotal += dueAmt;
//                        $("#report_tbody").append('<tr><td>' + pharmSales[0] + '</td><td>' + pharmSales[1] + '</td><td>' + pharmSales[2] + '</td><td>' + pharmSales[3] + '</td><td>' + pharmSales[4] + '</td></tr>');
//                        $("#report_tbody_excel").append('<tr><td>' + pharmSales[0] + '</td><td>' + pharmSales[1] + '</td><td>' + pharmSales[2] + '</td><td>' + pharmSales[3] + '</td><td>' + pharmSales[4] + '</td></tr>');
////                        $("#net_count").text(netTotal.toFixed(2)), $("#paid_count").text(paidTotal.toFixed(2)), $("#due_count").text(dueTotal.toFixed(2)), $("#disc_count").text(discTotal.toFixed(2));
//                    });
//                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                    $('.dataTables_length select').addClass('form-control');
//                });
//                break;
            case "Cancelled Bills":
                $("#viewRowQty").addClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.BillingResource.getCancelledReport(searchObj, function (data) {
                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Bill. No</th><th>Bill Date</th><th>UHID</th><th>Patient Name</th><th>Cancelled Date&Time</th><th>Reason</th><th>Amount</th><th>Cancelled By</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Bill. No</th><th>Bill Date</th><th>UHID</th><th>Patient Name</th><th>Cancelled Date&Time</th><th>Reason</th><th>Amount</th><th>Cancelled By</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, cancelBill) {
//                        var netAmt = pharmSales[5], paidAmt = pharmSales[7], discAmt = pharmSales[6], dueAmt = pharmSales[8];
//                        netTotal += netAmt, discTotal += discAmt, paidTotal += paidAmt, dueTotal += dueAmt;
                        $("#report_tbody").append('<tr><td>' + cancelBill[0] + '</td><td>' + cancelBill[1] + '</td><td>' + cancelBill[2] + '</td><td>' + cancelBill[3] + '</td><td>' + cancelBill[4] + '</td><td>' + "--" + '</td><td>' + cancelBill[5] + '</td><td>' + cancelBill[6] + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + cancelBill[0] + '</td><td>' + cancelBill[1] + '</td><td>' + cancelBill[2] + '</td><td>' + cancelBill[3] + '</td><td>' + cancelBill[4] + '</td><td>' + "--" + '</td><td>' + cancelBill[5] + '</td><td>' + cancelBill[6] + '</td></tr>');
//                        $("#net_count").text(netTotal.toFixed(2)), $("#paid_count").text(paidTotal.toFixed(2)), $("#due_count").text(dueTotal.toFixed(2)), $("#disc_count").text(discTotal.toFixed(2));
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Discount Bills":
                $("#viewRowQty").addClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.BillingResource.getDiscountBill(searchObj, function (data) {
                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Bill. No</th><th>Bill Date</th><th>UHID</th><th>Patient Name</th><th>Doctor/Consultant</th><th>Bill Amount</th><th>Discount Amount</th><th>Net Amount</th><th>Athorised</th><th>Created By</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Bill. No</th><th>Bill Date</th><th>UHID</th><th>Patient Name</th><th>Doctor/Consultant</th><th>Bill Amount</th><th>Discount Amount</th><th>Net Amount</th><th>Athorised</th><th>Created By</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, cancelBill) {
//                        var netAmt = pharmSales[5], paidAmt = pharmSales[7], discAmt = pharmSales[6], dueAmt = pharmSales[8];
//                        netTotal += netAmt, discTotal += discAmt, paidTotal += paidAmt, dueTotal += dueAmt;
                        $("#report_tbody").append('<tr><td>' + cancelBill[0] + '</td><td>' + cancelBill[1] + '</td><td>' + cancelBill[2] + '</td><td>' + cancelBill[3] + '</td><td>' + "--" + '</td><td>' + cancelBill[4] + '</td><td>' + cancelBill[5] + '</td><td>' + cancelBill[6] + '</td><td>' + cancelBill[7] + '</td><td>' + "--" + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + cancelBill[0] + '</td><td>' + cancelBill[1] + '</td><td>' + cancelBill[2] + '</td><td>' + cancelBill[3] + '</td><td>' + "--" + '</td><td>' + cancelBill[4] + '</td><td>' + cancelBill[5] + '</td><td>' + cancelBill[6] + '</td><td>' + cancelBill[7] + '</td><td>' + "--" + '</td></tr>');
//                        $("#net_count").text(netTotal.toFixed(2)), $("#paid_count").text(paidTotal.toFixed(2)), $("#due_count").text(dueTotal.toFixed(2)), $("#disc_count").text(discTotal.toFixed(2));
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "Laboratory Report":
                $("#dueTotalDiv,#discTotalDiv,#paidTotalDiv").addClass("hidden");
                var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                var netTotal = 0;
                dcomethealth.BillingResource.getLaboratoryReport(searchObj, function (data) {
                    $("#net_count,#paid_count,#due_count,#disc_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>Date</th><th>User Name</th><th>Patient Name</th><th>Patient Contact</th><th>Service Name</th><th>Resident Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Net Amount</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>Date</th><th>User Name</th><th>Patient Name</th><th>Patient Contact</th><th>Service Name</th><th>Resident Doctor</th><th>Ref. Type</th><th>Ref. Name</th><th>Net Amount</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    $.each(data, function (inx, labReport) {
                        var refType = labReport[6] != null ? labReport[6] : "--"
                        var refName = labReport[7] != null ? labReport[7] : "--"
                        var netAmt = labReport[8];
                        netTotal += labReport[8];
                        $("#report_tbody").append('<tr><td>' + labReport[0] + '</td><td>' + labReport[1] + '</td><td>' + labReport[2] + '</td><td>' + labReport[3] + '</td><td>' + labReport[4] + '</td><td>' + labReport[9] + '</td><td>' + labReport[5] + '</td><td>' + refType + '</td><td>' + refName + '</td><td align="right">' + netAmt.toFixed(2) + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + labReport[0] + '</td><td>' + labReport[1] + '</td><td>' + labReport[2] + '</td><td>' + labReport[3] + '</td><td>' + labReport[4] + '</td><td>' + labReport[9] + '</td><td>' + labReport[5] + '</td><td>' + refType + '</td><td>' + refName + '</td><td align="right">' + netAmt.toFixed(2) + '</td></tr>');
                        $("#net_count").text(netTotal.toFixed(2));
                    });
                    $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
                break;
            case "New Registration":
                $("#viewRowQty").addClass("hidden");
                $("#ReportDateRange").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment().format("YYYY-MM-DD");
                dcomethealth.PatientResource.searchNewRegistrationReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>Patient Name</th><th>Ref. Name</th><th>DOB</th><th>Age</th><th>Gender</th><th>Reg. Date</th><th>Contact No</th><th>E-mail</th><th>Address</th><th>Created User</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>Patient Name</th><th>Ref. Name</th><th>DOB</th><th>Age</th><th>Gender</th><th>Reg. Date</th><th>Contact No</th><th>E-mail</th><th>Address</th><th>Created User</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, newRegData) {
                            var age = '';
                            var refname = newRegData[2] != null ? newRegData[2] : "--"
                            var patAddress = newRegData[8] != null ? newRegData[8] : "--"
                            var emailID = newRegData[7] != "" ? newRegData[7] : "--"
                            var gender = '';
                            if (parseInt(newRegData[4]) === 1) {
                                gender = 'Male';
                            } else if (parseInt(newRegData[4]) === 2) {
                                gender = 'Female';
                            } else {
                                gender = 'Transgender';
                            }
                            var dob = newRegData[3]
                            var c = dob.split('-');
                            age = Math.floor((new Date() - new Date(new Date(c[0], c[1] - 1, c[2]))) / (365.25 * 24 * 60 * 60 * 1000));
                            $("#report_tbody").append('<tr><td>' + newRegData[0] + '</td><td>' + newRegData[1] + '</td><td>' + refname + '</td><td>' + newRegData[3] + '</td><td>' + age + '</td><td>' + gender + '</td><td>' + newRegData[5] + '</td><td>' + newRegData[6] + '</td><td>' + emailID + '</td><td>' + patAddress + '</td><td>' + newRegData[9] + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + newRegData[0] + '</td><td>' + newRegData[1] + '</td><td>' + refname + '</td><td>' + newRegData[3] + '</td><td>' + age + '</td><td>' + gender + '</td><td>' + newRegData[5] + '</td><td>' + newRegData[6] + '</td><td>' + emailID + '</td><td>' + patAddress + '</td><td>' + newRegData[9] + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;

//            case "Review Visit":
//                $("#viewRowQty").addClass("hidden");
//                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
//                dcomethealth.PatientResource.searchReviewVisitReports(searchObj, function (data) {
//                    $("#report_thead").empty();
//                    $("#report_thead_excel").empty();
//                    $("#report_tbody").empty();
//                    $("#report_tbody_excel").empty();
//                    $("#report_thead").append('<tr><th>UHID</th><th>Patient Name</th><th>Reg. Date</th><th>DOB</th><th>Age</th><th>Gender</th><th>Contact No</th><th>E-mail</th><th>Doctor/Consultant</th></tr>');
//                    $("#report_thead_excel").append('<tr><th>UHID</th><th>Patient Name</th><th>Reg. Date</th><th>DOB</th><th>Age</th><th>Gender</th><th>Contact No</th><th>E-mail</th><th>Doctor/Consultant</th></tr>');
//                    var tables = $.fn.dataTable.fnTables(true);
//                    $(tables).each(function () {
//                        $(this).dataTable().fnClearTable();
//                        $(this).dataTable().fnDestroy();
//                    });
//                    if (!!data && data !== "") {
//                        $.each(data, function (inx, reviewVisitData) {
//                            var age = '';
//                            var gender = '';
//                            var emailID = reviewVisitData[6] != "" ? reviewVisitData[6] : "--"
//                            if (parseInt(reviewVisitData[4]) === 1) {
//                                gender = 'Male';
//                            } else if (parseInt(reviewVisitData[4]) === 2) {
//                                gender = 'Female';
//                            } else {
//                                gender = 'Transgender';
//                            }
//                            var c = reviewVisitData[3].split('-');
//                            age = Math.floor((new Date() - new Date(new Date(c[0], c[1] - 1, c[2]))) / (365.25 * 24 * 60 * 60 * 1000));
//                            $("#report_tbody").append('<tr><td>' + reviewVisitData[0] + '</td><td>' + reviewVisitData[1] + '</td><td>' + reviewVisitData[2] + '</td><td>' + reviewVisitData[3] + '</td><td>' + age + '</td><td>' + gender + '</td><td>' + reviewVisitData[5] + '</td><td>' + emailID + '</td><td>' + reviewVisitData[7] + '</td></tr>');
//                            $("#report_tbody_excel").append('<tr><td>' + reviewVisitData[0] + '</td><td>' + reviewVisitData[1] + '</td><td>' + reviewVisitData[2] + '</td><td>' + reviewVisitData[3] + '</td><td>' + age + '</td><td>' + gender + '</td><td>' + reviewVisitData[5] + '</td><td>' + emailID + '</td><td>' + reviewVisitData[7] + '</td></tr>');
//                        });
//                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                        $('.dataTables_length select').addClass('form-control');
//                    } else {
//                        alert("No data Found");
//                    }
//                });
//                break;

            case "Appointment":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchAppointmentReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>UHID</th><th>Appointment Date</th><th>Time</th><th>Patient Name</th><th>Doctor/Consultant</th><th>Created User</th></tr>');
                    $("#report_thead_excel").append('<tr><th>UHID</th><th>Appointment Date</th><th>Time</th><th>Patient Name</th><th>Doctor/Consultant</th><th>Created User</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, appData) {
                            var mrnNo = appData[0] != null ? appData[0] : "--";
                            var doctorName = appData[4] != null ? appData[4] : "--";
                            $("#report_tbody").append('<tr><td>' + mrnNo + '</td><td>' + appData[1] + '</td><td>' + appData[2] + '</td><td>' + appData[3] + '</td><td>' + doctorName + '</td><td>' + appData[5] + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + mrnNo + '</td><td>' + appData[1] + '</td><td>' + appData[2] + '</td><td>' + appData[3] + '</td><td>' + doctorName + '</td><td>' + appData[5] + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "Date Wise Visit":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchDateWiseReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, dateWiseVisitData) {
                            var total = dateWiseVisitData[1] + dateWiseVisitData[2] + dateWiseVisitData[3] + dateWiseVisitData[4];
                            $("#report_tbody").append('<tr><td>' + dateWiseVisitData[0] + '</td><td>' + dateWiseVisitData[1] + '</td><td>' + dateWiseVisitData[2] + '</td><td>' + dateWiseVisitData[3] + '</td><td>' + dateWiseVisitData[4] + '</td><td>' + total + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + dateWiseVisitData[0] + '</td><td>' + dateWiseVisitData[1] + '</td><td>' + dateWiseVisitData[2] + '</td><td>' + dateWiseVisitData[3] + '</td><td>' + dateWiseVisitData[4] + '</td><td>' + total + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "Speciality Wise":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchSpecialityWiseReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, specialityWiseData) {
                            var total = specialityWiseData[1] + specialityWiseData[2] + specialityWiseData[3] + specialityWiseData[4];
                            $("#report_tbody").append('<tr><td>' + specialityWiseData[0] + '</td><td>' + specialityWiseData[1] + '</td><td>' + specialityWiseData[2] + '</td><td>' + specialityWiseData[3] + '</td><td>' + specialityWiseData[4] + '</td><td>' + total + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + specialityWiseData[0] + '</td><td>' + specialityWiseData[1] + '</td><td>' + specialityWiseData[2] + '</td><td>' + specialityWiseData[3] + '</td><td>' + specialityWiseData[4] + '</td><td>' + total + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;

            case "Cancelled Appointment":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchCancelledAppointmentReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Patient Name</th><th>Mobile</th><th>Appointment Date</th><th>Appointment Time</th><th>Booking Type</th><th>Cancelled By</th><th>Cancelled Date&Time</th><th>Reason</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Patient Name</th><th>Mobile</th><th>Appointment Date</th><th>Appointment Time</th><th>Booking Type</th><th>Cancelled By</th><th>Cancelled Date&Time</th><th>Reason</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, cancelledApptData) {
                            $("#report_tbody").append('<tr><td>' + cancelledApptData[0] + '</td><td>' + cancelledApptData[1] + '</td><td>' + cancelledApptData[2] + '</td><td>' + cancelledApptData[3] + '</td><td>--</td><td>' + cancelledApptData[4] + '</td><td>' + cancelledApptData[5] + ' ' + cancelledApptData[6] + '</td><td>--</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + cancelledApptData[0] + '</td><td>' + cancelledApptData[1] + '</td><td>' + cancelledApptData[2] + '</td><td>' + cancelledApptData[3] + '</td><td>--</td><td>' + cancelledApptData[4] + '</td><td>' + cancelledApptData[5] + ' ' + cancelledApptData[6] + '</td><td>--</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;

            case "Referral Type":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchReferralTypeReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Referral Type</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Referral Type</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, refTypeData) {
                            var total = refTypeData[1] + refTypeData[2] + refTypeData[3] + refTypeData[4];
                            $("#report_tbody").append('<tr><td>' + refTypeData[0] + '</td><td>' + refTypeData[1] + '</td><td>' + refTypeData[2] + '</td><td>' + refTypeData[3] + '</td><td>' + refTypeData[4] + '</td><td>' + total + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + refTypeData[0] + '</td><td>' + refTypeData[1] + '</td><td>' + refTypeData[2] + '</td><td>' + refTypeData[3] + '</td><td>' + refTypeData[4] + '</td><td>' + total + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;

            case "Doctor Wise":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchDoctorWiseReports(searchObj, function (doctorWiseReportData) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Doctor/Consultant</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Doctor/Consultant</th><th>Less than 1 Year</th><th>1 to 4 Years</th><th>5 to 19 Years</th><th>20 Years and above</th><th>Total</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!doctorWiseReportData && doctorWiseReportData !== "") {
                        $.each(doctorWiseReportData, function (inx, doctorwiseReport) {
                            $("#report_tbody").append('<tr><td>' + doctorwiseReport.doctorName + '</td><td>' + doctorwiseReport.ageLessthan1 + '</td><td>' + doctorwiseReport.ageUpto4 + '</td><td>' + doctorwiseReport.ageUpto19 + '</td><td>' + doctorwiseReport.ageGreaterThan20 + '</td><td>' + doctorwiseReport.ageTotalCount + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + doctorwiseReport.doctorName + '</td><td>' + doctorwiseReport.ageLessthan1 + '</td><td>' + doctorwiseReport.ageUpto4 + '</td><td>' + doctorwiseReport.ageUpto19 + '</td><td>' + doctorwiseReport.ageGreaterThan20 + '</td><td>' + doctorwiseReport.ageTotalCount + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;

            case "Appointment Wise":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchAppointmentWiseReports(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>Doctor/Consultant</th><th>Speciality</th><th>No. of Appointments</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>Doctor/Consultant</th><th>Speciality</th><th>No. of Appointments</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, apptWiseData) {
                            $("#report_tbody").append('<tr><td>' + apptWiseData[0] + '</td><td>' + apptWiseData[1] + '</td><td>' + apptWiseData[2] + '</td><td>' + apptWiseData[3] + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + apptWiseData[0] + '</td><td>' + apptWiseData[1] + '</td><td>' + apptWiseData[2] + '</td><td>' + apptWiseData[3] + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;
            case "Advance":
                $("#viewRowQty").addClass("hidden");
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.BillingResource.getAdvanceReport(searchObj, function (data) {
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Date</th><th>Doctor/Consultant</th><th>Speciality</th><th>No. of Appointments</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Date</th><th>Doctor/Consultant</th><th>Speciality</th><th>No. of Appointments</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, apptWiseData) {
                            $("#report_tbody").append('<tr><td>' + apptWiseData[0] + '</td><td>' + apptWiseData[1] + '</td><td>' + apptWiseData[2] + '</td><td>' + apptWiseData[3] + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + apptWiseData[0] + '</td><td>' + apptWiseData[1] + '</td><td>' + apptWiseData[2] + '</td><td>' + apptWiseData[3] + '</td></tr>');
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
                break;

            case "Total Income":
                $("#dueTotalDiv,#discTotalDiv").addClass("hidden");
                var netTotal = 0, paidTotal = 0
                var searchObj = dcomethealth.loginuser.entityRid + "/" + moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
                dcomethealth.PatientResource.searchTotalIncomeWiseReports(searchObj, function (data) {
                    $("#net_count,#paid_count").text(0);
                    $("#report_thead").empty();
                    $("#report_thead_excel").empty();
                    $("#report_tbody").empty();
                    $("#report_tbody_excel").empty();
                    $("#report_thead").append('<tr><th>Bill No</th><th>Bill Date</th><th>UHID</th><th>Patient Name</th><th>Doctor/Consultant</th><th>Referred By</th><th>Net Amount</th><th>Paid Amount</th><th>Bill Type</th><th>Payment Type</th><th>Created User</th></tr>');
                    $("#report_thead_excel").append('<tr><th>Bill No</th><th>Bill Date</th><th>UHID</th><th>Patient Name</th><th>Doctor/Consultant</th><th>Referred By</th><th>Net Amount</th><th>Paid Amount</th><th>Bill Type</th><th>Payment Type</th><th>Created User</th></tr>');
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    if (!!data && data !== "") {
                        $.each(data, function (inx, totalIncomedata) {
                            var refName = totalIncomedata[5] != null ? totalIncomedata[5] : "--";
                            var paymentType = totalIncomedata[9] != null ? totalIncomedata[9] : "--";
                            var billType = "";
                            if (totalIncomedata[8] == 1) {
                                billType = "OP Bill";
                            } else if (totalIncomedata[8] == 2) {
                                billType = "IP Bill";
                            } else {
                                billType = "Pharmacy Bill";
                            }
                            netTotal += totalIncomedata[6], paidTotal += totalIncomedata[7];
                            $("#report_tbody").append('<tr><td>' + totalIncomedata[0] + '</td><td>' + totalIncomedata[1] + '</td><td>' + totalIncomedata[2] + '</td><td>' + totalIncomedata[3] + '</td><td>' + totalIncomedata[4] + '</td><td>' + refName + '</td><td align="right">' + totalIncomedata[6] + '</td><td align="right">' + totalIncomedata[7] + '</td><td>' + billType + '</td><td>' + paymentType + '</td><td>' + totalIncomedata[10] + '</td></tr>');
                            $("#report_tbody_excel").append('<tr><td>' + totalIncomedata[0] + '</td><td>' + totalIncomedata[1] + '</td><td>' + totalIncomedata[2] + '</td><td>' + totalIncomedata[3] + '</td><td>' + totalIncomedata[4] + '</td><td>' + refName + '</td><td align="right">' + totalIncomedata[6] + '</td><td align="right">' + totalIncomedata[7] + '</td><td>' + billType + '</td><td>' + paymentType + '</td><td>' + totalIncomedata[10] + '</td></tr>');
                            $("#net_count").text(netTotal.toFixed(2)), $("#paid_count").text(paidTotal.toFixed(2));
                        });
                        $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    } else {
                        alert("No data Found");
                    }
                });
        }
    }
//    function viewDetails() {
//        var date = $('#ReportDateRangeSpan').html().split('-');
//        $("#salesDetails").addClass("hidden");
//        $("#unitSpan,#unitWiseSelect,#summaryDetails").removeClass("hidden");
//        $("#report_thead").empty();
//        var unitRid = 0;
//        if ($("#unitWiseSelect option:selected").val() != 0 && $("#unitWiseSelect option:selected").val() != undefined) {
//            unitRid = parseInt($("#unitWiseSelect option:selected").val());
//        }
//        var searchQuery = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + unitRid;
//        dcomethealth.BillingResource.getUnitSalesReport(searchQuery, function (data) {
//            if (!!data && data.length > 0) {
//                $("#report_thead").append('<tr><th>Unit Name</th><th>Bill No</th><th>Patient Name</th><th>Paid Amount</th><th>Due Amount</th></tr>');
//                var tables = $.fn.dataTable.fnTables(true);
//                $(tables).each(function () {
//                    $(this).dataTable().fnClearTable();
//                    $(this).dataTable().fnDestroy();
//                });
//                $.each(data, function (i1, bill) {
//                    $.each(bill.patient, function (i2, patient) {
//                        $("#report_tbody").append('<tr><td>' + bill.billH.bhUnitName + '</td><td>' + bill.billH.bhBillNo + '</td><td>' + patient.patFullName + '</td><td>' + bill.billH.bhPaidAmount + '</td><td>' + bill.billH.bhDueAmount + '</td></tr>');
//                    });
//                });
//                $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                $('.dataTables_length select').addClass('form-control');
//            } else {
//                var tables = $.fn.dataTable.fnTables(true);
//                $(tables).each(function () {
//                    $(this).dataTable().fnClearTable();
//                    $(this).dataTable().fnDestroy();
//                });
//                alert("No Data Found");
//            }
//        });
//    }
    function viewCollectionsDetails() {
        var date = $('#ReportDateRangeSpan').html().split('-');
        $("#collectionDetails").addClass("hidden");
        $("#typeSpan,#typeWiseSelect,#colSummaryDetails").removeClass("hidden");
        $("#report_thead").empty();
        var searchObj = moment(date[0], 'MMMM D, YYYY').format('YYYY-MM-DD') + "/" + moment(date[1], 'MMMM D, YYYY').format('YYYY-MM-DD');
        dcomethealth.BillingResource.getCollectionReport(searchObj, function (data) {
            if (!!data && data.length > 0) {
                $("#report_thead").empty();
                $("#report_thead_excel").empty();
                $("#report_tbody").empty();
                $("#report_tbody_excel").empty();
                $("#report_thead").append('<tr><th>Type</th><th>Amount</th><th>Collected By</th></tr>');
                $("#report_thead_excel").append('<tr><th>Type</th><th>Amount</th><th>Collected By</th></tr>');
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $.each(data, function (ix, collectionDetails) {
                    if (parseInt($("#typeWiseSelect option:selected").val()) == collectionDetails[1]) {
                        $("#report_tbody").append('<tr><td>' + collectionDetails[0] + '</td><td>' + collectionDetails[2] + '</td><td>' + collectionDetails[3] + '</td></tr>');
                        $("#report_tbody_excel").append('<tr><td>' + collectionDetails[0] + '</td><td>' + collectionDetails[2] + '</td><td>' + collectionDetails[3] + '</td></tr>');
                    }
                });
                $("#ReportTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                alert("No Data Found");
            }
        });
    }
    function  getExcel() {
        if ($('#report_head').text() == "Payer Sales") {
            var date = $('#ReportDateRangeSpan').html().split('-');
            var payerRid = $('#payerWiseSelect option:selected').val();
            dcomethealth.BillingResource.getPayerReport({"bhFromDate": moment(date[0]), "bhToDate": moment(date[1]).add(1, 'days'), "bhPayerRID": payerRid}, function (data) {
                if (!!data) {
                    $("#printDiv").html(data);
                    $("#fromDate").text(moment(date[0]).format("DD-MM-YYYY"));
                    $("#toDate").text(moment(date[1]).format("DD-MM-YYYY"));
                    var a = document.createElement('a');
                    var data_type = 'data:application/vnd.ms-excel';
                    a.href = data_type + ', ' + escape($("#printDiv").html()); // for correct symbol from portugese
//                    a.href = data_type + ', ' + encodeURIComponent($("#printDiv").html());
                    a.download = "reports" + '.xls';
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                    return false;
                }
            });
        } else {
            exportExcel();
            return false;
        }
    }
    function  exportExcel() {
        var id = "ReportTableExcel";
        var date = $('#ReportDateRangeSpan').html().split('-');
        var fromToDate = "(" + moment(date[0]).format("DD/MM/YYYY") + "-" + moment(date[1]).format("DD/MM/YYYY") + ")";
        $('#' + id).attr('border', 1);
        $('#reportCaptionExcel').text($('#report_head').text() + " Report - " + fromToDate);
        var htmltable = document.getElementById(id);
        var html = htmltable.outerHTML;
        var a = document.createElement('a');
        var data_type = 'data:application/vnd.ms-excel';
        a.href = data_type + ', ' + encodeURIComponent(html);
        a.download = "reports" + '.xls';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        $('#' + id).attr('border', 0);
    }
    function refreshData() {
    }
    return {
        init: init,
        getExcel: getExcel,
        exportExcel: exportExcel,
        View: View,
//        viewDetails: viewDetails,
        viewCollectionsDetails: viewCollectionsDetails,
        refreshData: refreshData
    };
}());
dcomethealth.ReportView.init();
