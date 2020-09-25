var dcomethealth = dcomethealth || {};
var patData = {};
var recData = {};
var advData = {};
dcomethealth.BillView = {
    id: "BillView",
    init: function () {
        var _this = this;
        $("#main_navigation_bar").append('<div id="' + this.id + '" class="main_container">');
        $("#" + this.id).load("pages/html/" + this.id + ".html", function () {
            dcomethealth.datatypes.init($("#" + this.id));

            dcomethealth.BillView.openPatBillView(dcomethealth.opdVal);
            dcomethealth.ReceiptResource.searchReceipts({"rhDate": moment().format('DD-MM-YYYY')}, function (data3) {
                recData = data3;
            });
            dcomethealth.AdvanceResource.searchAdvances({"adRegDate": moment().format('DD-MM-YYYY')}, function (data4) {
                advData = data4;
            });
        });
    },
    openPatBillView: function (val) {
        $("#patBill_details").empty();
        if (parseInt(val) === 1) {
            dcomethealth.BillingResource.searchPmd({"pmdRegDate": moment().format('DD-MM-YYYY')}, function (data) {
                if (!!data && data != "") {
                    $.each(data, function (i1, pmd) {
                        if (parseInt(pmd.pmdTransType) === 3) {
                            dcomethealth.ReceiptResource.searchReceipts({"rhDate": moment().format('DD-MM-YYYY')}, function (recData) {
                                $.each(recData, function (i2, rec) {
                                    if (parseInt(rec.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + rec.rhPayerName + '</td><td>' + rec.rhPayerNo + '</td><td>' + rec.rhDate + '</td><td>Receipt</td><td>' + rec.rhPaidAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        } else if (parseInt(pmd.pmdTransType) === 1) {
                            dcomethealth.AdvanceResource.searchAdvances({"adRegDate": moment().format('DD-MM-YYYY')}, function (advData) {
                                $.each(advData, function (i2, adv) {
                                    if (parseInt(adv.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + adv.adPayerName + '</td><td>' + adv.adPayerNo + '</td><td>' + adv.adRegDate + '</td><td>Advance</td><td>' + adv.adAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        }
                    });
                } else {
                    dcomethealth.util.loadpage(dcomethealth.selectedunitname + 'Dashboard')
                    alert("No data Found");
                }
            });
        }
        if (parseInt(val) === 2) {
            dcomethealth.BillingResource.searchPmd({"pmdRegDate": moment().format('DD-MM-YYYY'), "pmdPaymentMode": 12}, function (data) {
                if (!!data && data != "") {
                    $.each(data, function (i1, pmd) {
                        if (parseInt(pmd.pmdTransType) === 3) {
                            dcomethealth.ReceiptResource.searchReceipts({"rhDate": moment().format('DD-MM-YYYY')}, function (recData) {
                                $.each(recData, function (i2, rec) {
                                    if (parseInt(rec.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + rec.rhPayerName + '</td><td>' + rec.rhPayerNo + '</td><td>' + rec.rhDate + '</td><td>Receipt</td><td>' + rec.rhPaidAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        } else if (parseInt(pmd.pmdTransType) === 1) {
                            dcomethealth.AdvanceResource.searchAdvances({"adRegDate": moment().format('DD-MM-YYYY')}, function (advData) {
                                $.each(advData, function (i2, adv) {
                                    if (parseInt(adv.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + adv.adPayerName + '</td><td>' + adv.adPayerNo + '</td><td>' + adv.adRegDate + '</td><td>Advance</td><td>' + adv.adAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        }
                    });

                } else {
                    dcomethealth.util.loadpage(dcomethealth.selectedunitname + 'Dashboard')
                    alert("No data Found");
                }

            });
        }
        if (parseInt(val) === 3) {
            dcomethealth.BillingResource.searchPmd({"pmdRegDate": moment().format('DD-MM-YYYY'), "pmdPaymentMode": 13}, function (data) {
                if (!!data && data != "") {
                    $.each(data, function (i1, pmd) {
                        if (parseInt(pmd.pmdTransType) === 3) {
                            dcomethealth.ReceiptResource.searchReceipts({"rhDate": moment().format('DD-MM-YYYY')}, function (recData) {
                                $.each(recData, function (i2, rec) {
                                    if (parseInt(rec.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + rec.rhPayerName + '</td><td>' + rec.rhPayerNo + '</td><td>' + rec.rhDate + '</td><td>Receipt</td><td>' + rec.rhPaidAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        } else if (parseInt(pmd.pmdTransType) === 2) {
                            dcomethealth.AdvanceResource.searchAdvances({"adRegDate": moment().format('DD-MM-YYYY')}, function (advData) {
                                $.each(advData, function (i2, adv) {
                                    if (parseInt(adv.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + adv.adPayerName + '</td><td>' + adv.adPayerNo + '</td><td>' + adv.adRegDate + '</td><td>Advance</td><td>' + adv.adAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        }
                    });

                } else {
                    dcomethealth.util.loadpage(dcomethealth.selectedunitname + 'Dashboard')
                    alert("No data Found");
                }
            });
        }
        if (parseInt(val) === 4) {
            dcomethealth.BillingResource.searchPmd({"pmdRegDate": moment().format('DD-MM-YYYY'), "pmdPaymentMode": 14}, function (data) {
                if (!!data && data != "") {
                    $.each(data, function (i1, pmd) {
                        if (parseInt(pmd.pmdTransType) === 3) {
                            dcomethealth.ReceiptResource.searchReceipts({"rhDate": moment().format('DD-MM-YYYY')}, function (recData) {
                                $.each(recData, function (i2, rec) {
                                    if (parseInt(rec.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + rec.rhPayerName + '</td><td>' + rec.rhPayerNo + '</td><td>' + rec.rhDate + '</td><td>Receipt</td><td>' + rec.rhPaidAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        } else if (parseInt(pmd.pmdTransType) === 2) {
                            dcomethealth.AdvanceResource.searchAdvances({"adRegDate": moment().format('DD-MM-YYYY')}, function (advData) {
                                $.each(advData, function (i2, adv) {
                                    if (parseInt(adv.id) === parseInt(pmd.pmdTransRID)) {
                                        $("#patBill_details").append('<tr><td>' + adv.adPayerName + '</td><td>' + adv.adPayerNo + '</td><td>' + adv.adRegDate + '</td><td>Advance</td><td>' + adv.adAmount + '</td></tr>');
                                    }
                                });
                                $("#billTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                $('.dataTables_length select').addClass('form-control');
                            });
                        }
                    });

                } else {
                    dcomethealth.util.loadpage(dcomethealth.selectedunitname + 'Dashboard')
                    alert("No data Found");
                }

            });
        }
    },
    exportExcel: function (val) {
        var htmltable = document.getElementById('billTable');
        var html = htmltable.outerHTML;
        if (parseInt(val) === 1) {
            var a = document.createElement('a');
            var data_type = 'data:application/vnd.ms-excel';
            a.href = data_type + ', ' + encodeURIComponent(html);
            a.download = 'OPDDetails.xls';
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        }
        if (parseInt(val) === 2) {
            var printWindow = window.open();
            printWindow.document.write(html);
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }
    },
    refreshData: function () {
    }
};
dcomethealth.BillView.init();
