var dcomethealth = dcomethealth || {};
var boStatus = [];
var PayerData = {};
dcomethealth.ViewTransaction = (function () {
    var id = "ViewTransaction", printData = {}, printReceiptData = {}, printAdvanceData = {}, printRefundData = {}, src = "";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().endOf('days');
            var end = moment().endOf('days');
            $('#billDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $('#billDateRange').daterangepicker({
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
                $('#billDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                changeStatus();
            });
            getBoStatus('Bill');
            billView('--Status--');
        });
    }

    function changeStatus() {
        var val = document.getElementById('transactionSel').value;
        if (parseInt(val) === 1) {
            dcomethealth.ViewTransaction.getBoStatus('Bill');
            dcomethealth.ViewTransaction.billView($("#transtatus option:selected").html());
        } else if (parseInt(val) === 2) {
            dcomethealth.ViewTransaction.getBoStatus('Advance');
            dcomethealth.ViewTransaction.advanceView($("#transtatus option:selected").html());
        } else if (parseInt(val) === 3) {
            dcomethealth.ViewTransaction.getBoStatus('Receipt');
            dcomethealth.ViewTransaction.receiptView($("#transtatus option:selected").html());
        } else if (parseInt(val) === 4) {
            dcomethealth.ViewTransaction.getBoStatus('Refund');
            dcomethealth.ViewTransaction.refundView($("#transtatus option:selected").html());
        }
    }
    function  changeTransaction() {
        var val = document.getElementById('transactionSel').value;
        if (parseInt(val) === 1) {
            dcomethealth.ViewTransaction.getBoStatus('Bill');
            dcomethealth.ViewTransaction.billView('--Status--');
            document.getElementById("Billing").className = 'visible';
            document.getElementById("Advance").className = 'hidden';
            document.getElementById("Receipt").className = 'hidden';
            document.getElementById("Refund").className = 'hidden';
            document.getElementById("billno").placeholder = 'Bill No';
            document.getElementById("transtatus").length = 0;
            var batch = ",--Status--~0,Submitted~2,Partially paid~3,Fully Paid~3,Collected~4,Cancelled~5".split('~');
//            var batch = ",--Status--~0,Draft~1,Submitted~2,Partially paid~3,Fully Paid~3,Collected~4,Cancelled~5,Dont Care~6".split('~');
            for (var i = 0; i < batch.length - 1; i++)
            {
                var batch1 = batch[i].split(',');
                var opt = document.createElement("option");
                opt.value = batch1[0];
                opt.text = batch1[1];
                document.getElementById("transtatus").options.add(opt);
            }

        } else if (parseInt(val) === 2) {
            dcomethealth.ViewTransaction.getBoStatus('Advance');
            dcomethealth.ViewTransaction.advanceView('--Status--');
            document.getElementById("Billing").className = 'hidden';
            document.getElementById("Advance").className = 'visible';
            document.getElementById("Receipt").className = 'hidden';
            document.getElementById("Refund").className = 'hidden';
            document.getElementById("billno").placeholder = 'Advance No';
            document.getElementById("transtatus").length = 0;
            var batch = ',--Status--~0,Advance created~2,Advance adjusted~4,Advance partially adjusted~8,Cancelled~16,Partially cancelled~'.split('~');
//            var batch = ',--Status--~0,Draft~1,Advance created~2,Advance adjusted~4,Advance partially adjusted~8,Cancelled~16,Partially cancelled~,Dont Care~'.split('~');
            for (var i = 0; i < batch.length - 1; i++)
            {
                var batch1 = batch[i].split(',');
                var opt = document.createElement("option");
                opt.value = batch1[0];
                opt.text = batch1[1];
                document.getElementById("transtatus").options.add(opt);
            }
        } else if (parseInt(val) === 3) {
            dcomethealth.ViewTransaction.getBoStatus('Receipt');
            dcomethealth.ViewTransaction.receiptView('--Status--');
            document.getElementById("Billing").className = 'hidden';
            document.getElementById("Advance").className = 'hidden';
            document.getElementById("Receipt").className = 'visible';
            document.getElementById("Refund").className = 'hidden';
            document.getElementById("billno").placeholder = 'Receipt No';
            document.getElementById("transtatus").length = 0;
            var batch = ",--Status--~0,Receipt created~2,Cancelled~3".split('~');
//            var batch = ",--Status--~0,Draft~1,Receipt created~2,Cancelled~3,Dont Care~4".split('~');
            for (var i = 0; i < batch.length - 1; i++)
            {
                var batch1 = batch[i].split(',');
                var opt = document.createElement("option");
                opt.value = batch1[0];
                opt.text = batch1[1];
                document.getElementById("transtatus").options.add(opt);
            }

        } else if (parseInt(val) === 4) {
            dcomethealth.ViewTransaction.getBoStatus('Refund');
            dcomethealth.ViewTransaction.refundView('--Status--');
            document.getElementById("Billing").className = 'hidden';
            document.getElementById("Advance").className = 'hidden';
            document.getElementById("Receipt").className = 'hidden';
            document.getElementById("Refund").className = 'visible';
            document.getElementById("billno").placeholder = 'Refund No';
            document.getElementById("transtatus").length = 0;
            var batch = ",--Status--~0,Waiting for approval~2,Refund approved~3,Refund rejected~4,Cancelled~5".split('~');
//            var batch = ",--Status--~0,Draft~1,Waiting for approval~2,Refund approved~3,Refund rejected~4,Cancelled~5,Dont Care~6".split('~');
            for (var i = 0; i < batch.length - 1; i++)
            {
                var batch1 = batch[i].split(',');
                var opt = document.createElement("option");
                opt.value = batch1[0];
                opt.text = batch1[1];
                document.getElementById("transtatus").options.add(opt);
            }
        }
    }
    function getBoStatus(args) {
        dcomethealth.DataDictionaryResource.getBoMasterByName(args, function (masters) {
            $.each(masters, function (idx, bomStatus) {
                boStatus = bomStatus.boStatus;
            });
        });
    }
//    function getBoStatus(args) {
//        boStatus = [];
//        var array = [];
//        dcomethealth.DataDictionaryResource.getBoMasterByName(args, function (masters) {
//            $.each(masters, function (idx, bomStatus) {
//                $.each(bomStatus.boStatus, function (cdx, bomboStatus) {
//                    if ($.inArray(bomboStatus.bosuIndex, array) === -1) {
//                        array.push(bomboStatus.bosuIndex);
//                        boStatus.push(bomboStatus);
//                    }
//                });
//            });
//        });
//    }

//    function getBoStatus(args) {
//        dcomethealth.DataDictionaryResource.getBoMasterByName(args, function (masters) {
//            $.each(masters, function (idx, bomStatus) {
//                boStatus = bomStatus.boStatus;
//            });
//        });
//    }
    function billView(valStatus) {
        var date = $('#billDateRangeSpan').html().split('-');
        var searchObj = {"bhFromDate": moment(date[0]), "bhToDate": moment(date[1]).add(1, 'days'), "bhBillNo": $('#billno').val(), "bhPatientName": $('#cus_name').val()};
        dcomethealth.BillingResource.searchBilling(searchObj, function (bills) {
            if (!!bills) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#bill_tbody").empty();
                var payerName = null;
                $.each(bills, function (i, bill) {
                    $.each(bill.patient, function (i, patient) {
                        var array = [];
                        $.each(dcomethealth.dDictVal, function (i, dVal) {
                            if (parseInt(bill.billH.bhPayerType) == dVal.id) {
                                payerName = dVal.ddictValue;
                            }
                        });//<input onclick="dcomethealth.ViewTransaction.setData(this)" class="" name="bill" type="checkbox" value="' + bill.billH.id + '">                        
                        $.each(boStatus, function (idx, bomboStatus) {
                            if ($.inArray(bomboStatus.bosuIndex, array) === -1) {
                                array.push(bomboStatus.bosuIndex);
                                if (bomboStatus.bosuIndex === bill.billH.bhStatus) {
                                    var Print = '<a class="btn btn-sm btn-orange " hidden-xs pull-right" onclick="dcomethealth.ViewTransaction.beforeExport(' + bill.billH.id + ',' + 1 + ')"><i class="fa fa-print"></i></a>';
                                    if (valStatus === "--Status--") {
                                        $("#bill_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + bill.billH.id + ',\'bill\',\'' + bomboStatus.bosuName + '\')">' + bill.billH.bhBillNo + '</a></td>\n\
                                    <td>' + payerName + '</td><td>' + patient.patMrnNo + '</td><td>' + bill.billH.bhPatientName + '</td><td>' + moment(bill.billH.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + bill.billH.bhNetAmount + '</td><td align="right">' + bill.billH.bhDueAmount + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                                    } else if (bomboStatus.bosuName === valStatus) {//<input onclick="dcomethealth.ViewTransaction.setData(this)" name="bill" class="" type="checkbox" value="' + bill.billH.id + '">
                                        $("#bill_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + bill.billH.id + ',\'bill\',\'' + bomboStatus.bosuName + '\')">' + bill.billH.bhBillNo + '</a></td>\n\
                                    <td>' + payerName + '</td><td>' + bill.billH.bhPatientNo + '</td><td>' + bill.billH.bhPatientName + '</td><td>' + moment(bill.billH.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td>' + bill.billH.bhNetAmount + '</td><td>' + bill.billH.bhDueAmount + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                                    }
                                }
                            }
                        });
                        $("#billsTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
                    });
                });
            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#bill_tbody").empty();
                $("#billsTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function receiptView(valStatus) {
        var date = $('#billDateRangeSpan').html().split('-');
        var searchObj = {"rhFromDate": moment(date[0]), "rhToDate": moment(date[1]).add(1, 'days'), "rhNo": $('#billno').val(), "rhPayerName": $('#cus_name').val()};
        dcomethealth.ReceiptResource.searchReceipts(searchObj, function (receipts) {
            if (!!receipts) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#receipt_tbody").empty();
                $.each(receipts, function (i) {
                    var array = [];
                    $.each(boStatus, function (idx, bomboStatus) {
                        if ($.inArray(bomboStatus.bosuIndex, array) === -1) {
                            array.push(bomboStatus.bosuIndex);
                            if (bomboStatus.bosuIndex === receipts[i].rhStatus) {
                                var Print = '<a class="btn btn-sm btn-orange" hidden-xs pull-right" onclick="dcomethealth.ViewTransaction.beforeExport(' + receipts[i].id + ',' + 1 + ')"><i class="fa fa-print"></i></a>';
                                var no = receipts[i].rhPayerNo;
                                no = !!no ? no = no : "--";
//                                alert(bomboStatus.bosuName);
//                                alert(bomboStatus.bosuName + "===" + valStatus);
                                if (valStatus === "--Status--") {
                                    $("#receipt_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + receipts[i].id + ',\'receipt\',\'' + bomboStatus.bosuName + '\')">' + receipts[i].rhNo + '</a></td>\n\
                                    <td>' + receipts[i].rhPayerName + '</td><td>' + no + '</td><td>' + moment(receipts[i].createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + receipts[i].rhTotalAmount + '</td><td align="right">' + receipts[i].rhPaidAmount + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                                } else if (bomboStatus.bosuName === valStatus) {
                                    $("#receipt_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + receipts[i].id + ',\'receipt\',\'' + bomboStatus.bosuName + '\')">' + receipts[i].rhNo + '</a></td>\n\
                                    <td>' + receipts[i].rhPayerName + '</td><td>' + no + '</td><td>' + moment(receipts[i].createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + receipts[i].rhTotalAmount + '</td><td align="right">' + receipts[i].rhPaidAmount + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                                }
                            }
                        }
                    });
                    $("#receiptTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('hidden');
                    $('.dataTables_length select').addClass('form-control');
                });
            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#receipt_tbody").empty();
                $("#receiptTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function advanceView(valStatus) {
        var date = $('#billDateRangeSpan').html().split('-');
        var searchObj = {"adFromDate": moment(date[0]), "adToDate": moment(date[1]).add(1, 'days'), "adNo": $('#billno').val(), "adPayerName": $('#cus_name').val()};
        dcomethealth.AdvanceResource.searchAdvances(searchObj, function (advances) {
            if (!!advances) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#advance_tbody").empty();
                $.each(advances, function (i) {
                    var array = [];
                    $.each(boStatus, function (idx, bomboStatus) {
                        if ($.inArray(bomboStatus.bosuIndex, array) === -1) {
                            array.push(bomboStatus.bosuIndex);
                            if (bomboStatus.bosuIndex === advances[i].adStatus) {
                                var no = advances[i].adPayerNo;
                                no = !!no ? no = no : "--";
                                var Print = Print = '<a class="btn  btn-sm  btn-orange" hidden-xs pull-right" onclick="dcomethealth.ViewTransaction.beforeExport(' + advances[i].id + ',' + 1 + ')"><i class="fa fa-print"></i></a>';
                                if (valStatus === "--Status--") {
                                    $("#advance_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + advances[i].id + ',\'advance\',\'' + bomboStatus.bosuName + '\')">' + advances[i].adNo + '</a></td>\n\
                                    <td>' + advances[i].adPayerName + '</td><td>' + no + '</td><td>' + moment(advances[i].createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + advances[i].adAmount + '</td><td align="right">' + advances[i].adPaidAmount + '</td><td align="right">' + advances[i].adRefundedAmount + '</td>\n\
                                    <td>' + bomboStatus.bosuName + '</td></tr>');
                                } else if (bomboStatus.bosuName === valStatus) {
                                    $("#advance_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + advances[i].id + ',\'advance\',\'' + bomboStatus.bosuName + '\')">' + advances[i].adNo + '</a></td>\n\
                                    <td>' + advances[i].adPayerName + '</td><td>' + no + '</td><td>' + moment(advances[i].createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + advances[i].adAmount + '</td><td align="right">' + advances[i].adPaidAmount + '</td><td align="right">' + advances[i].adRefundedAmount + '</td>\n\
                                    <td>' + bomboStatus.bosuName + '</td></tr>');
                                }
                            }
                        }
                    });
                    $("#advanceTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });

            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#advance_tbody").empty();
                $("#advanceTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function refundView(valStatus) {
        var date = $('#billDateRangeSpan').html().split('-');
        var searchObj = {"refhFromDate": moment(date[0]), "refhToDate": moment(date[1]).add(1, 'days'), "refhNo": $('#billno').val(), "refhPayerName": $('#cus_name').val()};
        dcomethealth.RefundResource.searchRefunds(searchObj, function (refunds) {
            if (!!refunds) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#refund_tbody").empty();
                $.each(refunds, function (i, refund) {
                    var array = [];
                    $.each(boStatus, function (idx, bomboStatus) {
                        if ($.inArray(bomboStatus.bosuIndex, array) === -1) {
                            array.push(bomboStatus.bosuIndex);
                            if (bomboStatus.bosuIndex === refund.refhStatus) {
                                var no = refund.refhPayerNo;
//                                no = !!no ? no = no : "--";
                                no = !!no ? no : "--";
                                var Print = '<a class="btn  btn-sm  btn-orange" hidden-xs pull-right" onclick="dcomethealth.ViewTransaction.beforeExport(' + refund.id + ',' + 1 + ')"><i class="fa fa-print"></i></a>';
                                if (valStatus === "--Status--") {
                                    $("#refund_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + refund.id + ',\'refund\',\'' + bomboStatus.bosuName + '\')">' + refund.refhNo + '</a></td>\n\
                                    <td>' + refund.refhPayerName + '</td><td>' + no + '</td><td>' + moment(refund.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + refund.refhAmount + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                                } else if (bomboStatus.bosuName === valStatus) {
                                    $("#refund_tbody").append('<tr><td>' + Print + '</td>\n\
                                    <td><a href="#" onclick="modifyTran(' + refund.id + ',\'refund\',\'' + bomboStatus.bosuName + '\')">' + refund.refhNo + '</a></td>\n\
                                    <td>' + refund.refhPayerName + '</td><td>' + no + '</td><td>' + moment(refund.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                    <td align="right">' + refund.refhAmount + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                                }
                            }
                        }
                    });
                    $("#refundTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('hidden');
                    $('.dataTables_length select').addClass('form-control');
                });
            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#refund_tbody").empty();
                $("#refundTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function beforeExport(elem, val) {
        var filename = "Bill";
        if (parseInt($('#transactionSel').val()) === 2) {           
            filename = "advanceTable";
            var searchObj = {"id": elem};
            dcomethealth.AdvanceResource.searchAdvancesReport(searchObj, function (data) {
                printAdvanceData = data;
                $("#printDiv").empty();
                $("#printDiv").html(printAdvanceData);
                $("#entLogo").attr('src', dcomethealth.image);
                exportExcel(val, filename, $("#printDiv").html());
            });
        } else if (parseInt($('#transactionSel').val()) === 3) {            
            filename = "receiptTable";
            var searchObj = {"id": elem};
            dcomethealth.ReceiptResource.getTransactionReceiptReport(searchObj, function (data) {
                printReceiptData = data;
                $("#printDiv").empty();
                $("#printDiv").html(printReceiptData);
                $("#entLogo").attr('src', dcomethealth.image);
                exportExcel(val, filename, $("#printDiv").html());
            });
        } else if (parseInt($('#transactionSel').val()) === 4) {          
            filename = "refundTable";
            var searchObj = {"id": elem};
            dcomethealth.RefundResource.searchRefundsReport(searchObj, function (data) {
                printRefundData = data;
                $("#printDiv").empty();
                $("#printDiv").html(printRefundData);
                $("#entLogo").attr('src', dcomethealth.image);
                exportExcel(val, filename, $("#printDiv").html());
            });
        }
        else if (parseInt($('#transactionSel').val()) === 1) {            
            filename = "billsTable";
            var searchObj = {"id": elem};
            dcomethealth.BillingResource.getTransactionReport(searchObj, function (data) {
                printData = data;
                $("#printDiv").empty();
                $("#printDiv").html(printData);
                $("#entLogo").attr('src', dcomethealth.image);
                exportExcel(val, filename, $("#printDiv").html());
            });
        }
    }
    function exportExcel(val, filename, data) {
        var htmltable = document.getElementById(filename);
        var html = htmltable.outerHTML;
        if (!!data) {
            if (parseInt(val) === 2) {
                window.open('data:application/vnd.ms-excel,' + encodeURIComponent(html)); //$('div[id$=printDiv]').html()
            }
            if (parseInt(val) === 1) {
                while (data.indexOf('ã') != - 1)
                    data = data.replace('ã', '&atilde;');
                while (data.indexOf('á') != - 1)
                    data = data.replace('á', '&aacute;');
                while (data.indexOf('Á') != - 1)
                    data = data.replace('Á', '&Aacute;');
                while (data.indexOf('ç') != - 1)
                    data = data.replace('ç', '&ccedil');
                while (data.indexOf('é') != - 1)
                    data = data.replace('é', '&eacute;');
                while (data.indexOf('É') != - 1)
                    data = data.replace('É', '&Eacute;');
                while (data.indexOf('í') != - 1)
                    data = data.replace('í', '&iacute;');
                while (data.indexOf('Í') != - 1)
                    data = data.replace('Í', '&Iacute;');
                while (data.indexOf('ó') != - 1)
                    data = data.replace('ó', '&oacute;');
                while (data.indexOf('Ó') != - 1)
                    data = data.replace('Ó', '&Oacute;');
                while (data.indexOf('ú') != - 1)
                    data = data.replace('ú', '&uacute;');
                while (data.indexOf('Ú') != - 1)
                    data = data.replace('Ú', '&Uacute;');
                while (data.indexOf('º') != - 1)
                    data = data.replace('º', '&ordm;');
                while (data.indexOf('ñ') != - 1)
                    data = data.replace('ñ', '&ntilde;');
                while (data.indexOf('Ñ') != - 1)
                    data = data.replace('Ñ', '&Ntilde;');
                var printWindow = window.open();
                printWindow.document.write(data);
                setTimeout(function () {
                    printWindow.document.close();
                    printWindow.focus();
                    printWindow.print();
                    printWindow.close();
                }, 300);
            }
        }
    }
    function refreshData() {
    }
    return {
        init: init,
        changeStatus: changeStatus,
        changeTransaction: changeTransaction,
        getBoStatus: getBoStatus,
        billView: billView,
        receiptView: receiptView,
        advanceView: advanceView,
        refundView: refundView,
        beforeExport: beforeExport,
        exportExcel: exportExcel,
        refreshData: refreshData
    };
}());
dcomethealth.ViewTransaction.init();