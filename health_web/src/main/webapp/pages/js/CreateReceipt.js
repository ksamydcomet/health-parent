var dcomethealth = dcomethealth || {}, currencyExchange = {};
dcomethealth.CreateReceipt = (function () {
    var id = "CreateReceipt";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autocomplete();
            $('.reorderStatus').toggles({on: false}, "isReorder", 0);
            dcomethealth.DataDictionaryResource.getCurrencyExchange({}, function (data) {
                if (!!data) {
                    currencyExchange = data;
                }
            });
            var searchobjs = {"currMapEntRID": dcomethealth.loginuser.entityRid};
            dcomethealth.DataDictionaryResource.getCurrencyMap(searchobjs, function (data) {
                var options = "";
                if (!!data) {
                    var count = 0;
                    $.each(data, function (idx, cur) {
                        count++;
                        options += '<option value="' + cur.currencyM.id + '">' + cur.currencyM.curShortName + '</option>';
                    });
                    if (count > 1) {
                        $("#pay_mode_currency").html(options);
                    } else {
                        $("#pay_mode_currency").html(options);
                        $("#pay_mode_currency").addClass("hidden");
                    }
                }
            });
            $(".btnPrimary").off("click").on("click", function () {
                $(".btnPrimary").attr("disabled", "disabled");
                var tot = document.getElementById("total_pmd").value;
                var due = document.getElementById("dueAmtBill").value;
                var table = document.getElementById('adv_table');
                var payerAmount = !!$('#serviceEligibleAmount').val() ? document.getElementById('serviceEligibleAmount').value : 0;
                var payerPaidAmount = !!$('#payerPaidAmount').val() ? $('#payerPaidAmount').val() : 0;
                var payeramountcheckwithPaidAmount = payerAmount - payerPaidAmount;
                var dueAmount = due - payeramountcheckwithPaidAmount;
                var amount = 0, total = 0;
                if ($("#openAdv").is(":checked")) {
                    if (!!table) {
                        for (var i = 0; i < table.rows.length - 1; i++) {
                            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value) {
                                amount = dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value
                            } else {
                                amount = 0;
                            }
                            total = parseFloat(total) + parseFloat(amount);
                        }
                        if (!!total && total != 0) {
                            if (parseFloat(tot) > due) {
                                $(".btnPrimary").attr("disabled", false);
                                alert("Enter Correct Amount");
                                return false;
                            } else {
                                if ($("#isReorder").val() == 1) {
                                    if (parseFloat(tot) == parseFloat(due)) {
                                        saveReceipt1(0, "RECEIPT", "CREATE_RECEIPT")
                                    } else {
                                        alert("Enter Amount Fully");
                                        return false;
                                    }

                                } else {
                                    if (parseFloat(tot) > parseFloat(dueAmount)) {
                                        alert("Due amount is less or Payer Amount is pending");
                                        return false;
                                    } else {
                                        saveReceipt(0, "BILL", "COLLECT_PAYMENT");
                                    }

                                }
                            }
                        } else {
                            $(".btnPrimary").attr("disabled", false);
                            alert("Enter Amount");
                            return false;
                        }
                    } else {
                        if (parseFloat(tot) > due) {
                            $(".btnPrimary").attr("disabled", false);
                            alert("Enter Correct Amount");
                            return false;
                        } else {
                            if ($("#isReorder").val() == 1) {
                                if (parseFloat(tot) == parseFloat(due)) {
                                    saveReceipt1(0, "RECEIPT", "CREATE_RECEIPT")
                                } else {
                                    alert("Enter Amount Fully");
                                    return false;
                                }

                            } else {
                                if (parseFloat(tot) > parseFloat(dueAmount)) {
                                    alert("Due amount is less or Payer Amount is pending");
                                    return false;
                                } else {
                                    saveReceipt(0, "BILL", "COLLECT_PAYMENT");
                                }
                            }
                        }
                    }
                } else {
                    if (parseFloat(tot) > due) {
                        $(".btnPrimary").attr("disabled", false);
                        alert("Enter Correct Amount");
                        return false;
                    } else {
                        if ($("#isReorder").val() == 1) {
                            if (parseFloat(tot) == parseFloat(due)) {
                                saveReceipt1(0, "RECEIPT", "CREATE_RECEIPT")
                            } else {
                                alert("Enter Amount Fully");
                                return false;
                            }

                        } else {
                            if (parseFloat(tot) > parseFloat(dueAmount)) {
                                alert("Due amount is less or Payer Amount is pending");
                                return false;
                            } else {
                                saveReceipt(0, "BILL", "COLLECT_PAYMENT");
                            }
                        }
                    }
                }
            });
        });
    }
    function  searchReceipt(billRID) {
//        var searchObj = {"rhBhRID": billRID};
//        dcomethealth.ReceiptResource.searchReceipts(searchObj, function (data) {
//            if (data !== null) {
////                $("#advDetailsCheck").removeClass("col-md-12 hidden").addClass("col-md-12");
//                $("#recDetailsCheck").removeClass("col-md-3 hidden").addClass("col-md-3");
//
//            }
//            $("#accordion").empty();
//            $.each(data, function (pIdx, receipts) {
//                $("#accordion").append('<div class="panel panel-default">\n\
//                            <div class="panel-heading col-md-12"><input type="hidden" id="advanceRID" value="' + receipts.id + '"/><span >Receipt No : ' + receipts.rhNo + ' </span>&nbsp;&nbsp;&nbsp;&nbsp;\n\
//                            <span >Receipt Date : ' + moment(receipts.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + ' </span>&nbsp;&nbsp;\n\
//                            <span >Net Amount : ' + receipts.rhTotalAmount + ' </span>&nbsp;&nbsp;\n\
//                            <span >Paid Amount : ' + receipts.rhPaidAmount + ' </span>&nbsp;&nbsp;\n\
//                            <span >Status : ' + receipts.rhStatus + ' </span></div>');
//            });
//        });

        var searchObj = {"rdBillRID": billRID};
        dcomethealth.ReceiptResource.searchReceiptD(searchObj, function (data) {
            if (data !== null) {
//                $("#advDetailsCheck").removeClass("col-md-12 hidden").addClass("col-md-12");
                $("#recDetailsCheck").removeClass("col-md-3 hidden").addClass("col-md-3");

            }
            $("#accordion").empty();
            $.each(data, function (pIdx, receipts) {
                var status = receipts.rdRhStatus == 1 ? "Created" : receipts.rdRhStatus == -2 ? "Cancelled" : "Created";
                $("#accordion").append('<div class="panel panel-default">\n\
                            <div class="panel-heading col-md-12"><input type="hidden" id="advanceRID" value="' + receipts.rdRhRID + '"/><span >Receipt No : ' + receipts.rdRhNumber + ' </span>&nbsp;&nbsp;&nbsp;&nbsp;\n\
                            <span >Receipt Date : ' + moment(receipts.rdCreatedDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + ' </span>&nbsp;&nbsp;\n\
                            <span >Net Amount : ' + receipts.rdBillAmount + ' </span>&nbsp;&nbsp;\n\
                            <span >Paid Amount : ' + receipts.rdPaidAmount + ' </span>&nbsp;&nbsp;\n\
                            <span >Status : ' + status + ' </span></div>');
            });
        });
    }
    function  searchAdvance(payerRID) {
        var searchObj = {"adPayerRID": payerRID};
        dcomethealth.AdvanceResource.searchAdvances(searchObj, function (data) {
            if (data !== null) {
//                $("#checkboxDetails").removeClass("col-md-12 hidden").addClass("col-md-12");
                $("#advDetailsCheck").removeClass("col-md-3 hidden").addClass("col-md-3");
            }
            $("#accordioninpanel").empty();
            var i = 0;
            var row = '<div class="accordion-item"><a class="accordion-title collapsed" data-toggle="collapse" data-parent="#accordioninpanel" href="#collapseinOne"><h4>Advance List #</h4></a><div style="height: 0px;" id="collapseinOne" class="collapse"><div class="accordion-body">\n\
                       <table id="adv_table" class="table table-responsive table-striped col-md-12 col-sm-12 col-xs-12" width="100%" border="0" cellspacing="0" cellpadding="0"><thead><tr><th>Advance No.</th><th>Adjust Amount</th><th>Advance Amount</th><th>Balance Amount</th><th>Adjusted Amount</th></tr></thead>';
            $.each(data, function (pIdx, advance) {
                row += '<tr><td><input type="text" id="advanceNo" class="col-md-10 col-sm-10 col-xs-12" name="advanceNo" value="' + advance.adNo + '" readonly/><input type="hidden" id="advanceRID" value="' + advance.id + '"/></td>\n\
                                <td><input type="text" id="adjustedAmt" class="col-md-10 col-sm-10 col-xs-12" name="adjustedAmt" value="" placeholder="Adjust amount.." onchange="advPaid(this)"/></td>\n\
                                <td><input type="text" id="advanceAmt" class="col-md-10 col-sm-10 col-xs-12" name="advanceAmt" value="' + advance.adAmount + '" readonly/></td>\n\
                                <td><input type="text" id="balanceAmt" class="col-md-10 col-sm-10 col-xs-12" name="balanceAmt" value="' + advance.adBalanceAmount + '" readonly/></td>\n\
                                <td><input type="text" id="paidAmt" class="col-md-10 col-sm-10 col-xs-12" name="paidAmt" value="' + advance.adPaidAmount + '" readonly/></td></tr>';
                i++;
            });
            row += '</table></div></div></div>';
            $("#accordioninpanel").append(row);
        });

    }
    function autocomplete() {
        $("#" + id + " input[name='billSearch']").autocomplete({
            select: function (event, ui) {
                if ((parseInt($("#isReorder").val())) != 1) {
                    $('#billHRID').val(ui.item.data.billH.id);
                    $('#visitRID').val(ui.item.data.billH.bhPatientVisitRID);
                    $("#patientRID").val(ui.item.data.billH.bhPatientRID);
                    $('#billSearch_hid').val(ui.item.data.billH.bhBillNo);
                    $('#payerRid').val(ui.item.data.billH.bhPayerRID);
                    $('#payerType').val(ui.item.data.billH.bhPayerType);
                    if (parseInt(ui.item.data.billH.bhPayerType) != 31) {
                        $('#payerAmountDue').removeClass('hidden');
                    }
                    $('#DocRefRid').val(ui.item.data.billH.bhDocRefRID);
                    $("#serviceEligibleAmount").val(ui.item.data.billH.bhEligibleAmount);
                    $('#payerPaidAmount').val(ui.item.data.billH.bhPayerAmount);
                    $('#bhApprovalNumber').val(ui.item.data.billH.bhApprovalNumber);
                    $.each(ui.item.data.patient, function (pIdx, patient) {
                        $('#PatientName').val(patient.patFullName);
                        var c = patient.patDob.split('-');
                        $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                        $('#PatientMobile').val(patient.patPhoneNo);
                    });
                    $("#grossAmtBill").val(ui.item.data.billH.bhGrossAmount);
                    $("#discountAmountBill").val(ui.item.data.billH.bhTotalDiscountAmount);
                    $("#dueAmtBill").val(ui.item.data.billH.bhDueAmount);
                    $("#paidAmtBill").val(ui.item.data.billH.bhPaidAmount);
                    $("#netAmtBill").val(ui.item.data.billH.bhNetAmount);
                    searchReceipt(ui.item.data.billH.id);
                    searchAdvance(ui.item.data.billH.bhPatientRID);
                } else {
                    searchBilling(ui.item.data.pdId);
                }
            },
            open: function (event, ui) {
                $(document).tooltip({
                    position: {
                        my: "center bottom-20",
                        at: "right center",
                        using: function (position, feedback) {
                            $(this).css(position);
                        }
                    }
                });
            },
            close: function () {
                $(document).tooltip("destroy");
            },
            source: function (request, response) {
                var queryString = request.term;
                queryString = queryString.trim();
                if ((parseInt(($("#isReorder").val()))) == 1) {
                    var searchParams = {"pdPayerName": queryString, "pdIsActive": 1};
                    dcomethealth.MasterResource.searchPayerMaster(searchParams, function (data) {
                        response($.grep($.map(data, function (item) {
                            return {
                                label: item.pdPayerName || "",
                                value: item.pdPayerName || "",
                                name: (item.pdPayerName || "") + (item.pdPayerName ? " - " : "") + (item.pdId || ""),
                                data: item
                            };
                        }), function (item, index) {
                            return index < 10;
                        }));
                    });
                } else {
                    var searchParams = {"bhBillNo": queryString};
                    dcomethealth.BillingResource.searchBilling(searchParams, function (data) {
                        response($.grep($.map(data, function (item) {
                            return {
                                label: (item.billH.bhBillNo || ""),
                                value: (item.billH.bhBillNo || ""),
                                name: (item.billH.bhBillNo || ""),
                                data: item
                            };
                        }), function (item, index) {
                            return index < 10;
                        }));
                    });
                }
            },
            minLength: 1
        });
    }
    function  searchBilling(payerRid) {
        var searchObj = {"bhPayerRID": payerRid};
        dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
            if (data !== null) {
                $("#patientInfo").addClass("hidden");
                $("#patientInfo2").addClass("hidden");
                $("#billingInfoTable").removeClass("hidden");
            }
            $("#dynTbody").empty();
            $.each(data, function (pIdx, bill) {
                $('#billHRID').val(bill.billH.id);
                $('#visitRID').val(bill.billH.bhPatientVisitRID);
                $("#patientRID").val(bill.billH.bhPatientRID);
                $('#billSearch_hid').val(bill.billH.bhBillNo);
                $('#payerRid').val(bill.billH.bhPayerRID);
                $('#payerType').val(bill.billH.bhPayerType);
                $('#DocRefRid').val(bill.billH.bhDocRefRID);
                $("#serviceEligibleAmount").val(bill.billH.bhEligibleAmount);
                $('#payerPaidAmount').val(bill.billH.bhPayerAmount);
                $('#bhApprovalNumber').val(bill.billH.bhApprovalNumber);
                $.each(bill.patient, function (pIdx, patient) {
                    $('#PatientName').val(patient.patFullName);
                    var c = patient.patDob.split('-');
                    $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                    $('#PatientMobile').val(patient.patPhoneNo);
                });
                if (bill.billH.bhStatus == 1 || bill.billH.bhStatus == 2) {
                    if (bill.billH.bhEligibleAmount !== bill.billH.bhPayerAmount) {
                        $("#dynTbody").append('<tr><td width="1%"><input class="" id="selectRow" value="' + bill.billH.id + '" type="checkbox" onclick="dcomethealth.CreateReceipt.onSelect(this);"></td><td><input type="hidden" value="' + bill.billH.id + '" id="rhBillRid"><span id="billHNumber">' + bill.billH.bhBillNo + '</span></td><td><span id="billGrossAmount">' + bill.billH.bhGrossAmount + '</span></td>\n\
                <td><span id="billHPaidAmount">' + bill.billH.bhPaidAmount + '</span></td><td><span id="billHDueAmount">' + bill.billH.bhEligibleAmount + '</span></td><td><span id="billHNetAmount">' + bill.billH.bhNetAmount + '</span></td></tr>');
                    }

                }
            });
            calculation();
        });
    }
    function onSelect(elem) {
        var table = document.getElementById('dyn_table');
        for (var i = 0; i < table.rows.length - 1; i++) {
            if (elem.checked) {
                if ((elem.value) == dynTableGetNodeInRow(table.rows[i + 1], 'selectRow').value) {
                    dynTableGetNodeInRow(table.rows[i + 1], 'selectRow').checked = true;
                }
            } else {
                if ((elem.value) == dynTableGetNodeInRow(table.rows[i + 1], 'selectRow').value) {
                    dynTableGetNodeInRow(table.rows[i + 1], 'selectRow').checked = false;
                }
            }
        }
        calculation();
    }
    function SelectAll(val) {
        var table = document.getElementById('dyn_table');

        if (val.checked) {
            for (var i = 0; i < table.rows.length - 1; i++) {
                dynTableGetNodeInRow(table.rows[i + 1], 'selectRow').checked = true;
            }
        } else {
            for (var i = 0; i < table.rows.length - 1; i++) {
                dynTableGetNodeInRow(table.rows[i + 1], 'selectRow').checked = false;
            }
        }
        calculation();
    }
    function  saveReceipt(boRID, boCode, actionCode) {
        var billH = {};
        var receiptHList = [];
        var receiptH = {};
        billH.id = $("#billHRID").val();
        billH.bhPatientRID = $("#patientRID").val();
        if ($("#visitRID").val() !== "") {
            billH.bhPatientVisitRID = $("#visitRID").val();
        }
        billH.bhType = 1; // OP bill type : 1;
        billH.bhPatientName = $("#PatientName").val();
        billH.bhPatientNo = $("#PatientMobile").val(); // Please change the name
        billH.bhNetAmount = $("#netAmtBill").val();
        billH.bhGrossAmount = $("#grossAmtBill").val();
        billH.bhIsDiscountPercentage = 1;
        billH.bhBillNo = $('#billSearch').val();
        billH.bhIsDraft = 0;
        billH.bhPayerRID = $('#payerRid').val();
        billH.bhPayerType = $('#payerType').val();
        billH.bhDocRefRID = $('#DocRefRid').val();
        billH.bhUnitRID = dcomethealth.selectedunit; // user selected unit;
        billH.bhApprovalNumber = !!$('#bhApprovalNumber').val() ? $('#bhApprovalNumber').val() : 0;
        billH.bhEligibleAmount = !!$("#serviceEligibleAmount").val() ? $("#serviceEligibleAmount").val() : 0;
        billH.bhPayerAmount = !!$('#payerPaidAmount').val() ? $('#payerPaidAmount').val() : 0;
        billH.bhTotalDiscountAmount = !!$("#discountAmountBill").val() ? $("#discountAmountBill").val() : 0;
        receiptH.rhEligibleAmt = 0;
        receiptH.rhTotalAmount = $("#netAmtBill").val();
        receiptH.rhPayerRID = $("#payerRid").val();
        receiptH.rhPayerName = $("#PatientName").val();
        receiptH.rhPayerNo = $("#PatientMobile").val();
        receiptH.rhPayerType = $('#payerType').val();
        receiptH.rhBaseCurRID = dcomethealth.sCurrencyRid;
        var advanceList = [], receiptDList = [];
        if (parseInt($("#advancePayment").val()) === 1) {
            var table = document.getElementById('adv_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var advance = {};
                var receiptDAdv = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value !== "") {
                    advance.id = dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value;
                    advance.adType = 0;
                    advance.adRefRID = $("#billHRID").val();
                    advance.adPayerRID = $("#patientRID").val();
//        advance.adPayerType = $("#visitReason").val();
                    advance.adPayerNo = $("#PatientMobile").val(); // Need to verify Mrn no or Patient No
                    advance.adPayerName = $("#PatientName").val();
                    advance.adAmount = dynTableGetNodeInRow(table.rows[i + 1], 'advanceAmt').value;
                    advance.adAdjustedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value;
                    advance.adBalanceAmount = dynTableGetNodeInRow(table.rows[i + 1], 'balanceAmt').value;
                    advance.adPaidAmount = dynTableGetNodeInRow(table.rows[i + 1], 'paidAmt').value;
                    advance.adRefundedAmount = 0.00;
//        advance.adRemarks = $("#visitReason").val();
//                    advance.adUnitRID = dcomethealth.selectedunit;
//        advance.adRowInvalidated = $("#visitReason").val();
//        advance.adRowHistoryID = $("#visitReason").val();
//        advance.adCancelledDate = $("#visitReason").val();
//        advance.adContextType = $("#visitReason").val();
//        advance.adContextRID = $("#visitReason").val();
//        advance.adIstransferred = $("#visitReason").val();
//        advance.adCreationMode = $("#visitReason").val();
//        advance.adCancellationMode = $("#visitReason").val();
                    receiptDAdv.rdAdvAdjustedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value;
                    receiptDAdv.rdBillAmount = $("#netAmtBill").val();
                    receiptDAdv.rdPaidAmount = parseFloat($("#total_pmd").val());
                    advanceList.push(advance);
                    receiptDList.push(receiptDAdv);
                }
            }
        }
        var pmdList = [];
        if ($("#total_pmd").val() !== "") {
            var table2 = document.getElementById('pmd_table');
            var table_length2 = table2.rows.length;
            for (var j = 0; j < table_length2; j++) {
                var pmd = {};
                var receiptDPmd = {};
                pmd.pmdTransType = 3;
                pmd.pmdPaymentMode = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_ddict').value;
                pmd.pmdCardType = dynTableGetNodeInRow(table2.rows[j], 'cardtype').value;
                pmd.pmdDocNo = dynTableGetNodeInRow(table2.rows[j], 'cheqno').value;
                pmd.pmdDocDate = dynTableGetNodeInRow(table2.rows[j], 'cheqdate').value;
                pmd.pmdDocExpDate = dynTableGetNodeInRow(table2.rows[j], 'expdate').value;
                pmd.pmdDocApprovalNo = dynTableGetNodeInRow(table2.rows[j], 'approvalno').value;
                pmd.pmdBankName = dynTableGetNodeInRow(table2.rows[j], 'bankname').value;
                pmd.pmdBankDetails = dynTableGetNodeInRow(table2.rows[j], 'branchname').value;
                if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value !== "") {
                    pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                } else {
                    pmd.pmdAmount = 0.00;
                }
                pmd.pmdCurrencyRID = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_currency').value;
//                        child2.pmdCurrencyRID = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
//                        child2.pmdCurrencyValue = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
//                        child2.pmdCurrencyExchangeRate = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
//                        child2.pmdDisplaySymbol = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
//                        child2.pmdRemarks = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
//                        child2.pmdCardHolderName = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
                receiptDPmd.rdBillAmount = $("#netAmtBill").val();
                receiptDPmd.rdBillRID = $("#billHRID").val();
                if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value !== "") {
                    receiptDPmd.rdPaidAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                } else {
                    receiptDPmd.rdPaidAmount = 0.00;
                }
                pmdList.push(pmd);
                receiptDList.push(receiptDPmd);
            }
            receiptH.rhPaidAmount = parseFloat($("#total_pmd").val());
            receiptH.paymentModeDetails = pmdList;
        }
        billH.bhPaidAmount = parseFloat($("#total_pmd").val()) + parseFloat($("#paidAmtBill").val());
        receiptH.rhBhRID = $("#billHRID").val();
        receiptH.receiptD = receiptDList;
        receiptH.advanceDetails = advanceList;
//        receiptH.billH = billHList;
        receiptHList.push(receiptH);
        billH.receiptH = receiptHList;
        var args = $("#billHRID").val() + "/" + boCode + "/" + actionCode;
        if ($('#total_pmd').val() != 0 && !!$('#total_pmd').val()) {
            dcomethealth.BillingResource.saveBill(billH, args).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('CreateReceipt');
                dcomethealth.util.base_init();
                dcomethealth.util.loadNotification();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $(".btnPrimary").attr("disabled", false);
                alert("Failed");
            });
        } else {
            alert('Total amount should not be zero');
        }

    }
    function  saveReceipt1(boRID, boCode, actionCode) {
        var billH = {};
        var receiptHList = [];
        var receiptH = {};
        receiptH.rhEligibleAmt = 0;
        receiptH.rhTotalAmount = $("#netAmtBill").val();
        receiptH.rhPayerRID = $("#payerRid").val();
        receiptH.rhPayerName = $("#billSearch").val();
        receiptH.rhPayerNo = $("#PatientMobile").val();
        receiptH.rhPayerType = $('#payerType').val();
        receiptH.rhBaseCurRID = dcomethealth.sCurrencyRid;

        var advanceList = [], receiptDList = [];
        if (parseInt($("#advancePayment").val()) === 1) {
            var table = document.getElementById('adv_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var advance = {};
                var receiptDAdv = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value !== "") {
                    advance.id = dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value;
                    advance.adType = 0;
                    advance.adRefRID = $("#billHRID").val();
                    advance.adPayerRID = $("#patientRID").val();
//        advance.adPayerType = $("#visitReason").val();
                    advance.adPayerNo = $("#PatientMobile").val(); // Need to verify Mrn no or Patient No
                    advance.adPayerName = $("#PatientName").val();
                    advance.adAmount = dynTableGetNodeInRow(table.rows[i + 1], 'advanceAmt').value;
                    advance.adAdjustedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value;
                    advance.adBalanceAmount = dynTableGetNodeInRow(table.rows[i + 1], 'balanceAmt').value;
                    advance.adPaidAmount = dynTableGetNodeInRow(table.rows[i + 1], 'paidAmt').value;
                    advance.adRefundedAmount = 0.00;
                    advanceList.push(advance);
                }
            }
        }
        var pmdList = [];
        if ($("#total_pmd").val() !== "") {
            var table2 = document.getElementById('pmd_table');
            var table_length2 = table2.rows.length;
            for (var j = 0; j < table_length2; j++) {
                var pmd = {};
                var receiptDPmd = {};
                pmd.pmdTransType = 3;
                pmd.pmdPaymentMode = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_ddict').value;
                pmd.pmdCardType = dynTableGetNodeInRow(table2.rows[j], 'cardtype').value;
                pmd.pmdDocNo = dynTableGetNodeInRow(table2.rows[j], 'cheqno').value;
                pmd.pmdDocDate = dynTableGetNodeInRow(table2.rows[j], 'cheqdate').value;
                pmd.pmdDocExpDate = dynTableGetNodeInRow(table2.rows[j], 'expdate').value;
                pmd.pmdDocApprovalNo = dynTableGetNodeInRow(table2.rows[j], 'approvalno').value;
                pmd.pmdBankName = dynTableGetNodeInRow(table2.rows[j], 'bankname').value;
                pmd.pmdBankDetails = dynTableGetNodeInRow(table2.rows[j], 'branchname').value;
                if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value !== "") {
                    pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                } else {
                    pmd.pmdAmount = 0.00;
                }

                pmdList.push(pmd);
            }

            var table3 = document.getElementById('dyn_table');
            var table_length3 = table3.rows.length;
            for (var j = 0; j < table_length3 - 1; j++) {
                if (dynTableGetNodeInRow(table3.rows[j + 1], 'selectRow').checked) {
                    var receiptDPmd = {};
                    receiptDPmd.rdBillAmount = dynTableGetNodeInRow(table3.rows[j + 1], 'billHNetAmount').innerHTML;
                    receiptDPmd.rdBillRID = dynTableGetNodeInRow(table3.rows[j + 1], 'rhBillRid').value;
//                if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value !== "") {
                    receiptDPmd.rdPaidAmount = dynTableGetNodeInRow(table3.rows[j + 1], 'billHDueAmount').innerHTML;
//                } else {
//                    receiptDPmd.rdPaidAmount = 0.00;
//                }
                    receiptDList.push(receiptDPmd);
                }
            }
            receiptH.rhPaidAmount = parseFloat($("#total_pmd").val());
        }
        receiptH.rhBhRID = 0;
        if (receiptDList.length > 0) {
            receiptH.receiptD = receiptDList;
        }
        if (advanceList.length > 0) {
            receiptH.advanceDetails = advanceList;
        }

        receiptH.paymentModeDetails = pmdList;       
        var args = 1;
        if (receiptDList.length > 0) {
            if ($('#total_pmd').val() != 0 && !!$('#total_pmd').val()) {
                dcomethealth.BillingResource.saveReceipt(receiptH, args).done(function (data, textStatus, jqXHR) {
                    alert("Saved");
                    dcomethealth.util.loadpage('CreateReceipt');
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    $(".btnPrimary").attr("disabled", false);
                    alert("Failed");
                });
            } else {
                alert('Total amount should not be zero');
            }
        } else {
            alert('Select atleast one bill');
            return false;

        }

    }
    function refreshData() {
    }
    return {
        init: init,
        onSelect: onSelect,
        SelectAll: SelectAll,
        searchReceipt: searchReceipt,
        searchAdvance: searchAdvance,
        saveReceipt: saveReceipt,
        refreshData: refreshData
    };
}());
dcomethealth.CreateReceipt.init();