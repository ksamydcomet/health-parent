var dcomethealth = dcomethealth || {};
dcomethealth.ModifyTransaction = (function () {
    var id = "ModifyTransaction", serviceClearVar = 0, receiptDList = [];
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            viewDetails(dcomethealth.id, dcomethealth.transType, dcomethealth.transStatus);
            AutoCompleteTran();
            $('#modify').off("click").on("click", function () {
                var table = document.getElementById('dyn_table');
                var count = 0;
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value == 0 || dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value == null || dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value == "undefined") {
                        count++;
                        alert("Enter service name");
                        dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').focus();
                        return false;
                    }
                }
                if (parseInt(count) == 0) {
                    saveBill(0, "BILL", "BUILTIN_ACTION");
                } else {
                    return false;
                }
            });
            $('#cancel').off("click").on("click", function () {
                if (dcomethealth.transType === 'advance') {
                    cancelAdvance();
                } else if (dcomethealth.transType === 'receipt') {
                    cancelReceiptModBill();
                } else if (dcomethealth.transType === 'bill') {
                    saveBill(dcomethealth.id, "BILL", "CANCEL_BILL");
                } else if (dcomethealth.transType === 'refund') {
                    cancelRefund();
                }

            });
        });
    }
    function viewDetails(id, tranType, status) {
        if (tranType == 'bill') {
            $('#itemDetails').removeClass('hidden');
            $('#advanceDetails').addClass('hidden');
            $('#refundDetails').addClass('hidden');
            $('#receiptDetails').addClass('hidden');
            $('#receiptDetails').addClass('hidden');
            $('#viewDiscountDetails').removeClass('hidden');

            if (status == 'Submitted') {
                $('#modify').removeClass('hidden');
                $('#cancel').removeClass('hidden');
            } else if (status == 'Partially paid') {
                $('#cancel').removeClass('hidden');
            }
            var searchObj = {"id": id};
            dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
                $.each(data, function (pIdx, bill) {
                    $('#billHRID').val(bill.billH.id);
                    $('#payerRid').val(bill.billH.bhPayerRID);
                    $('#payerType').val(bill.billH.bhPayerType);
                    $('#patientRID').val(bill.billH.bhPatientRID);
                    $('#visitRID').val(bill.billH.bhPatientVisitRID);
                    $('#billNo').val(bill.billH.bhBillNo);
                    $('#bhPrefix').val(bill.billH.bhPrefix);
                    $('#bhPrintableBillNo').val(bill.billH.bhPrintableBillNo);
                    $('#bhIsDraft').val(bill.billH.bhIsDraft);
                    $('#serviceGrossAmount').val(bill.billH.bhGrossAmount);
                    $('#serviceDiscountAmount').val(bill.billH.bhTotalDiscountAmount);
                    $('#netAmtBill').val(bill.billH.bhNetAmount);
                    $('#payerAmtBill').val(bill.billH.bhEligibleAmount);
                    $('#paidAmtBill').val(bill.billH.bhPaidAmount);
                    $('#dueAmtBill').val(bill.billH.bhDueAmount);
                    $('#serviceNetAmount').val(bill.billH.bhNetAmount);
                    $('#billHPaidAmount').val(bill.billH.bhPaidAmount);
                    $('#payer_amount').val(bill.billH.bhEligibleAmount);
                    $('#payer_paid_amount').val(bill.billH.bhPayerAmount);
                    $('#billHDueAmount').val(bill.billH.bhDueAmount - bill.billH.bhEligibleAmount);
                    $('#DiscValue').val(bill.billH.bhTotalDiscountAmount);
                    $('#bhDocRefRID').val(bill.billH.bhDocRefRID);
                    $('#bhApprovalNo').val(bill.billH.bhApprovalNumber);
                    $.each(bill.patient, function (pIdx, patient) {
                        $('#mrnSearch').val(patient.patMrnNo);
                        $('#PatientName').val(patient.patFullName);
                        var c = patient.patDob.split('-');
                        $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
//                        $('#PatientGender option[value="' + patient.patGenderIndex + '"]').prop('selected', true);
                        $('#patientMobile').val(patient.patPhoneNo);
                    });
                    $("#modifyBill_tbody").empty();
                    $.each(bill.billH.billD, function (pIdx, billD) {
                        $("#modifyBill_tbody").append('<tr><td width="5%"><i id="minus" class=" hidden dct-icon fa fa-minus-circle" onclick="delete_row(this) "></i></td>\n\
                                    <td width="42%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" readonly id="serviceName" name="serviceName" onBlur = "calculation(this)" value = "' + billD.bdItemName + '" onkeypress="return dcomethealth.validation.ValidateAlpha()"  onchange="ClearName(this)" ><input type="hidden"  id="serviceRID" name="serviceRID" value="' + billD.bdItemRID + '">\n\
                                    <input type="hidden" id="serviceOrderRID" name="serviceOrderRID" value="' + billD.bdItemOrderRID + '"><input id="billDRID" type="hidden" readonly value="' + billD.id + '"></td><td width="6%"><input id = "qtyService" onkeypress="return dcomethealth.validation.isNumberKey(event)" type = "text" value = "' + billD.bdQty + '" onBlur = "calculation(this)" maxlength="3" name = "qtyService" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="10%"><input type = "text" id = "serviceDisc" maxlength="3" onchange="calculation(this)" onkeypress="return dcomethealth.validation.isNumberKey(event)" name = "serviceDisc"  value = "' + billD.bdDiscountAmount + '" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="10%"><input type = "text" id = "MRP" readonly name = "MRP" value = "' + billD.bdPrice + '" onBlur = "calculation(this)" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event)"  class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="20%"><input type = "text" id = "netAmount" maxlength="11" name = "netAmount" value = "' + billD.bdNetAmount + '" onBlur = "calculation(this)" readonly class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="5%"><i class = "hidden dct-icon fa fa-plus-square red" onclick = "insert_row(\'dyn_table\', this)" ></i></td></tr>');
                    });
                    dcomethealth.ModifyTransaction.AutoCompleteTran();
                });
            });
        } else if (tranType === 'advance') {
            $("#viewDiscountDetails1").addClass("hidden");
            $('#advanceDetails').removeClass('hidden');
            $('#itemDetails').addClass('hidden');
            $('#refundDetails').addClass('hidden');
            $('#receiptDetails').addClass('hidden');
            if (status === 'Cancelled') {
                $('#modify').addClass('hidden');
                $('#cancel').addClass('hidden');
            } else {
                $('#modify').addClass('hidden');
                $('#cancel').removeClass('hidden');
            }
            $('#billDetailsH').addClass('hidden');
            dcomethealth.ModifyTransaction.searchAdvance(id);
        } else if (tranType == 'receipt') {
            $('#receiptDetails').removeClass('hidden');
            $('#itemDetails').addClass('hidden');
            $('#advanceDetails').addClass('hidden');
            $('#refundDetails').addClass('hidden');
            $('#billDetailsH').addClass('hidden');
            $('#modify').addClass('hidden');
            $('#viewDiscountDetails1').addClass('hidden');
            if (status === 'Cancelled') {
                $('#cancel').addClass('hidden');
            } else {
                $('#cancel').removeClass('hidden');
            }
            dcomethealth.ModifyTransaction.searchReceipt(id);
        } else {
            $("#viewDiscountDetails1").addClass("hidden");
            $('#refundDetails').removeClass('hidden');
            $('#itemDetails').addClass('hidden');
            $('#advanceDetails').addClass('hidden');
            $('#receiptDetails').addClass('hidden');
            $('#modify').addClass('hidden');
            $('#cancel').addClass('hidden');
            dcomethealth.ModifyTransaction.searchRefund(id);
        }
    }
    function checkValid() {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == 0) {
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = "";
            }
        }
    }
    function checkservice(pitemRID, row) {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var pitemRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            if (parseInt(pitemRid) == parseInt(pitemRID)) {
                count++;
                if (count == 2) {
                    alert("Already Existing Service");
                    pitemRID = "";
                    dynTableDeleteRow(row);
                    return false;
                }
            }
        }
    }
    function autoIdSetService(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'serviceRID').value = "";
            dynTableGetNodeInRow(elem, 'serviceName').value = "";
        }
    }
    function AutoCompleteTran() {
        $("#" + id + " input[name='serviceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                var y = table_length - 2;
                for (var i = 0; i < table_length - 1; i++) {
                    dynTableGetNodeInRow(table.rows[y + 1], 'serviceRID').value = ui.item.data.id;
                    dynTableGetNodeInRow(table.rows[y + 1], 'MRP').value = ui.item.data.bPrice;
                    var serRid = dynTableGetNodeInRow(table.rows[y + 1], 'serviceRID').value;
                    autoIdSetService(dynTableGetNodeInRow(this, 'serviceRID'));
                    checkservice(serRid, dynTableGetNodeInRow(this));
                    calculation();
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
                var check = true;
                var queryString = request.term;
                queryString = queryString.trim();
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data && data.length > 0) {
                        check = false;
                        response($.grep($.map(data, function (item) {
                            return {
                                label: item.bsName || "",
                                value: item.bsName || "",
                                name: (item.bsName || "") + (item.bsName && item.id ? " - " : "") + (item.id || ""),
                                data: item
                            };
                        }), function (item, index) {
                            return index < 75;

                        }));
                    } else {
                        if (check == true) {
                            checkValid()
                            return false;
                        }
                    }
                });
            },
            minLength: 1
        });
    }
    function searchAdvance(advRID) {
        var searchObj = {"id": advRID};
        dcomethealth.AdvanceResource.searchAdvances(searchObj, function (data) {
            $('#modifyAdv_tbody').empty();
            $.each(data, function (pIdx, advance) {
                $('#PatientNameAdv').val(advance.adPayerName);
                $('#patientMobileAdv').val(advance.adPayerNo);
                $("#patientRIDAdv").val(advance.adPayerRID);
                $('#modifyAdv_tbody').append('<tr><td><input type="text" id="advanceNo" name="advanceNo" value="' + advance.adNo + '" readonly style="border: none"/><input type="hidden" id="advanceRID" value="' + advance.id + '"/></td>\n\
                                <td><input class="advanceAmt" type="text"  id="advanceAmt" name="advanceAmt" value="' + advance.adAmount + '" readonly style="border: none"/><input type="hidden" id="adjustAmt" name="adjustAmt" value="' + advance.adAdjustedAmount + '"/></td>\n\
                                <td><input class="adjustedAmt" type="text"  id="adjustedAmt" name="adjustedAmt" value="' + advance.adPaidAmount + '" readonly style="border: none"/></td>\n\
                                <td><input type="text" id="refundedAmt" name="refundedAmt" value="' + advance.adRefundedAmount + '" readonly style="border: none"/><input type="hidden" id="balanceAmt" name="balanceAmt" value="' + advance.adBalanceAmount + '"/></td>\n\
                                    </tr>');
            });
        });
    }
    function searchReceipt(receiptRID) {
        var searchObj = {"id": receiptRID};
        dcomethealth.ReceiptResource.searchReceipts(searchObj, function (data) {
            $.each(data, function (pIdx, receipts) {
                $('#PatientNameRec').val(receipts.rhPayerName);
                $('#patientMobileRec').val(receipts.rhPayerNo);
                $("#patientRIDRec").val(receipts.rhPayerRID);
                $("#billHRidRec").val(receipts.rhBhRID);
                $("#RecRid").val(receipts.id);
                $("#RecNo").val(receipts.rhNo);
                $("#RecDate").val(moment(receipts.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY'));
                $("#RecNet").val(receipts.rhTotalAmount);
//                $("#RecPaid").val(receipts.rhPaidAmount);
                $('#recptPaidAmount').val(receipts.rhPaidAmount);
                $('#payerAmount').val(receipts.rhEligibleAmt);
                $('#rhPayerType').val(receipts.rhPayerType);
                receiptDList = receipts.receiptD;
            });
        });
    }
    function searchRefund(refundRID) {
        var searchObj = {"id": refundRID};
        dcomethealth.RefundResource.searchRefunds(searchObj, function (data) {
            $.each(data, function (pIdx, refunds) {
                $('#PatientNameRef').val(refunds.refhPayerName);
                $('#patientMobileRef').val(refunds.refhPayerNo);
                $("#patientRIDRef").val(refunds.refhPayerRID);
                $('#ref_tbody').append('<tr><td>' + refunds.refhNo + '</td>\n\
                <td>' + moment(refunds.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td><td>' + refunds.refhAmount + '</td><td>' + dcomethealth.loginuser.userID + '</td></tr>');
            });
        });
    }
    function cancelAdvance() {
        var advance = {};
        if ($("#advanceRID").val() != "") {
            advance.id = $("#advanceRID").val();
        }
        advance.adType = 0;
        advance.adPayerRID = $("#patientRIDAdv").val();
        advance.adPayerNo = $("#patientMobileAdv").val(); // need to verify mrn or patient no
        advance.adPayerName = $("#PatientNameAdv").val();
        advance.adAmount = $("#advanceAmt").val();
        advance.adBalanceAmount = 0.00;

        if ($("#adjustedAmt").val() === "undefined" || $("#adjustedAmt").val() === "") {
            advance.adPaidAmount = 0;
        } else {
            advance.adPaidAmount = $("#adjustedAmt").val();
        }
        if ($("#refundedAmt").val() == "undefined" || $("#refundedAmt").val() == "") {
            advance.adRefundedAmount = 0;
        } else {
            advance.adRefundedAmount = $("#refundedAmt").val();
        }
        if ($("#adjustAmt").val() == "undefined" || $("#adjustAmt").val() == "") {
            advance.adAdjustedAmount = 0;
        } else {
            advance.adAdjustedAmount = $("#adjustAmt").val();
        }
        advance.adCancellationMode = 0;
        var arg = $("#advanceRID").val() + "/ADVANCE/CANCEL_ADVANCE";
        dcomethealth.AdvanceResource.saveAdvance(advance, arg)
                .done(function (data, textStatus, jqXHR) {
                    alert("Advance Cancelled");
                    dcomethealth.util.loadpage('CreateAdvance');
                    dcomethealth.util.base_init();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function cancelRefund() {
        var refundH = {}, advanceDetails = {};
        var advanceDetailsList = [];
        refundH.id = $('#RecRid').val();


        advanceDetails.adPayerRID = $("#patientRIDRec").val();
        advanceDetails.adRefRID = $("#billHRidRec").val();
        advanceDetailsList.push(advanceDetails);
        refundH.advanceDetails = advanceDetailsList;

        var args = $('#RefRid').val() + "/REFUND/CANCEL_REFUND";
        dcomethealth.RefundResource.saveRefund(refundH, args)
                .done(function (data, textStatus, jqXHR) {
                    dcomethealth.ModifyTransaction.cancelReceiptModBill();
//                    alert("Saved");
//                    dcomethealth.util.loadpage('ViewTransaction');
//                    dcomethealth.util.base_init();
//                    loadNotification();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Receipt Failed");
        });
    }
    function cancelReceiptModBill() {
//        var billH = {};
//        var receiptHList = [];
//        var receiptH = {};
//        var advanceDetails = {};
//        var advanceDetailsList = [];
//        receiptH.id = $('#RecRid').val();
//        receiptH.rhTotalAmount = $("#RecNet").val();
//        receiptH.rhPayerName = $("#PatientNameRec").val();
//        receiptH.rhPayerNo = $("#patientMobileRec").val();
//        receiptH.rhPaidAmount = $('#recptPaidAmount').val();
//        receiptH.rhEligibleAmt = $('#payerAmount').val();
//        if (!!$('#rhPayerType').val()) {
//            receiptH.rhPayerType = $('#rhPayerType').val();
//        }
//        receiptH.rhBhRID = $("#billHRidRec").val();
//        if ($("#patientRIDRec").val() !== "") {
//            receiptH.rhPayerRID = $("#patientRIDRec").val();
//            advanceDetails.adPayerRID = $("#patientRIDRec").val();
//        }
//        advanceDetails.adRefRID = $("#billHRidRec").val();
//        advanceDetailsList.push(advanceDetails);
//        receiptH.advanceDetails = advanceDetailsList;
//        receiptHList.push(receiptH);
//        if ($("#billHRidRec").val() !== "") {
//            billH.id = $("#billHRidRec").val();
//        }
//        if ($("#patientRIDRec").val() !== "") {
//            billH.bhPatientRID = $("#patientRIDRec").val();
//        } else {
//            alert("Patient Not Registered Yet");
//            return false;
//        }
//        var searchObj = {"id": $("#billHRidRec").val()};
//        dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
//            $.each(data, function (pIdx, bill) {
//                billH.bhBillNo = bill.billH.bhBillNo;
//                billH.bhPrefix = bill.bhPrefix;
//                billH.bhPrintableBillNo = bill.billH.bhPrintableBillNo;
//                billH.bhIsDraft = bill.billH.bhIsDraft;
//                billH.bhPatientName = $("#PatientNameRec").val();
//                billH.bhPatientNo = $("#patientMobileRec").val();
//                billH.bhType = 1; // OP bill type : 1;
//                billH.bhPayerRID = bill.billH.bhPayerRID;
//                billH.bhPayerType = bill.billH.bhPayerType;
//                billH.bhGrossAmount = bill.billH.bhGrossAmount;
//                billH.bhTotalDiscountAmount = bill.billH.bhTotalDiscountAmount;
//                billH.bhTotalTaxAmount = bill.billH.bhTotalTaxAmount;
//                billH.bhNetAmount = bill.billH.bhNetAmount;
////                billH.bhDiscount = bill.billH.bhDiscount;
//                billH.bhDueAmount = parseFloat(bill.billH.bhDueAmount) + parseFloat($('#recptPaidAmount').val());
//                billH.bhEligibleAmount = parseFloat($('#payerAmount').val());
//                billH.bhDocRefRID = bill.billH.bhDocRefRID;
//                if (parseFloat(bill.billH.bhPaidAmount) >= parseFloat($('#recptPaidAmount').val())) {
//                    billH.bhPaidAmount = parseFloat(bill.billH.bhPaidAmount) - parseFloat($('#recptPaidAmount').val());
//                } else {
//                    alert("Bill Paid is lesser than Receipt Paid");
//                    return false;
//                }
//                billH.bhUnitRID = dcomethealth.selectedunit;
//                billH.receiptH = receiptHList;
//                var args = 0 + "/BILL/BUILTIN_ACTION";
//                dcomethealth.BillingResource.saveBill(billH, args)
//                        .done(function (data, textStatus, jqXHR) {
//                            alert("Receipt Cancelled");
//                            dcomethealth.util.loadpage('ViewTransaction');
//                            dcomethealth.util.base_init();
//                            loadNotification();
//                        }).fail(function (jqXHR, textStatus, errorThrown) {
//                    alert("Failed");
//                });
//            });
//        });

        var count = 0;
        var billH = {};
        var receiptHList = [];
        var receiptH = {};
        var advanceDetails = {};
        var advanceDetailsList = [];
        receiptH.id = $('#RecRid').val();
        receiptH.rhTotalAmount = $("#RecNet").val();
        receiptH.rhPayerName = $("#PatientNameRec").val();
        receiptH.rhPayerNo = $("#patientMobileRec").val();
        receiptH.rhPaidAmount = $('#recptPaidAmount').val();
        receiptH.rhEligibleAmt = $('#payerAmount').val();
        if (!!$('#rhPayerType').val()) {
            receiptH.rhPayerType = $('#rhPayerType').val();
        }
        receiptH.rhBhRID = 0;
        if ($("#patientRIDRec").val() !== "") {
            receiptH.rhPayerRID = $("#patientRIDRec").val();
            advanceDetails.adPayerRID = $("#patientRIDRec").val();
        }
        advanceDetails.adRefRID = 0;
        advanceDetailsList.push(advanceDetails);
        receiptH.advanceDetails = advanceDetailsList;
        receiptH.receiptD = receiptDList;
        dcomethealth.BillingResource.saveReceipt(receiptH, 0).done(function (data, textStatus, jqXHR) {
            $("#cancel").attr("disabled", true);
            alert("Receipt Cancelled");
            if (dcomethealth.AccountsPage == 1) {
                dcomethealth.util.loadpage('Accounts');
            } else {
                dcomethealth.util.loadpage('ViewTransaction');
            }

            dcomethealth.util.base_init();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            $("#cancel").attr("disabled", false);
            alert("Failed");
        });
    }
    function saveBill(boRID, boCode, actionCode) {
        var totalPaid = 0.00;
        var billDList = [];
        var billH = {};
        var receiptH = {};
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var billD = {};
            //BillD
//            alert($("#billDRID").val() != "");
//            alert($("#billDRID").val() != 0);
//            alert($("#billDRID").val() != 'undefined');
//            alert($("#billDRID").val() != null);

            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'billDRID').value) {
                billD.id = dynTableGetNodeInRow(table.rows[i + 1], 'billDRID').value;
            }
            billD.bdItemType = "Service";

            billD.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value != "" && dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value != "undefined") {
                billD.bdItemOrderRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value;
            } else {
                billD.bdItemOrderRID = 0;
            }
            billD.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            billD.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
            billD.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
            billD.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value * dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value != "") {
                billD.bdDiscountAmount = dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value;
            } else {
                billD.bdDiscountAmount = 0;
            }
//            child.bdTotalTaxAmount = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdTaxPercent = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
            billD.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'netAmount').value;
//            child.bdPatientAmount = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdIsDiscountPercentage = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdDiscountValue = dynTableGetNodeInRow(table.rows[i + 1], 'discVal').value;
//            child.bdIsItemLevelDiscountGiven = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdDiscountReasonRID = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdDiscountVoucherNo = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdDiscountApprovalUserRID = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdIsItemOrderedFromBIll = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdIsManualInclusion = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
//            child.bdIsDsicountApproved = dynTableGetNodeInRow(table.rows[i + 1], 'grdSkuRID').value;
            billDList.push(billD);
        }
        var serviceNetAmount = document.getElementById('serviceNetAmount').value;
//        var total_pmd = document.getElementById('total_pmd').value;
//        if (total_pmd == "") {
//            total_pmd = 0;
//        }
        if (serviceNetAmount === "") {
            alert("Bill Amount is empty");
            return false;
        }
//        else if (parseFloat(serviceNetAmount) < parseFloat(total_pmd)) {l
//            alert("Net amount lesser than Total amount paid not equal");
//            return false;
//        }
        if ($("#billHRID").val() !== "") {
            billH.id = $("#billHRID").val();
            if ($("#visitRID").val() !== "") {
                billH.bhPatientVisitRID = $("#visitRID").val();
            }
        }
        if ($("#patientRID").val() !== "") {
            billH.bhPatientRID = $("#patientRID").val();
//            receiptH.rhPayerRID = $("#patientRID").val();
        } else {
            alert("Patient Not Registered Yet");
            return false;
        }
        billH.bhBillNo = $('#billNo').val();
        billH.bhPrefix = $('#bhPrefix').val();
        billH.bhPrintableBillNo = $('#bhPrintableBillNo').val();
        billH.bhDocRefRID = $('#bhDocRefRID').val();
        billH.bhPayerType = $('#payerType').val();
        billH.bhPayerRID = $('#payerRid').val();
        billH.bhPatientName = $("#PatientName").val();
        billH.bhPatientNo = $("#patientMobile").val();
        billH.bhType = 1; // OP bill type : 1;
        billH.bhGrossAmount = $("#serviceGrossAmount").val();
        billH.bhEligibleAmount = $('#payer_amount').val();
        billH.bhPayerAmount = !!$('#payer_paid_amount').val() ? $('#payer_paid_amount').val() : 0;
        billH.bhApprovalNumber = !!$("#bhApprovalNo").val() ? $("#bhApprovalNo").val() : 0;
        if (!!$("#serviceDiscountAmount").val()) {
            billH.bhTotalDiscountAmount = $("#serviceDiscountAmount").val();
//            billH.bhDiscount = $("#serviceDiscountAmount").val();
        }
        billH.bhNetAmount = $("#serviceNetAmount").val();
        billH.bhUnitRID = dcomethealth.selectedunit; // user selected unit;

//        receiptH.rhPaidAmount = parseFloat(total_pmd);
//        receiptH.rhTotalAmount = $("#serviceNetAmount").val();
//        receiptH.rhPayerName = $("#PatientName").val();
//        receiptH.rhPayerNo = $("#PatientMobile").val();
//        receiptH.rhBhRID = $("#billHRID").val();

//        if (parseFloat(total_pmd) != 0) {
//            var table2 = document.getElementById('pmd_table');
//            var table_length2 = table2.rows.length;
//            for (var j = 0; j < table_length2; j++) {
//                var pmd = {};
//                var receiptDPmd = {};
//                pmd.pmdTransType = 3;
//                pmd.pmdPaymentMode = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_ddict').value;
//                pmd.pmdCardType = dynTableGetNodeInRow(table2.rows[j], 'cardtype').value;
//                pmd.pmdDocNo = dynTableGetNodeInRow(table2.rows[j], 'cheqno').value;
//                pmd.pmdDocDate = dynTableGetNodeInRow(table2.rows[j], 'cheqdate').value;
//                pmd.pmdDocExpDate = dynTableGetNodeInRow(table2.rows[j], 'expdate').value;
//                pmd.pmdDocApprovalNo = dynTableGetNodeInRow(table2.rows[j], 'approvalno').value;
//                pmd.pmdBankName = dynTableGetNodeInRow(table2.rows[j], 'bankname').value;
//                pmd.pmdBankDetails = dynTableGetNodeInRow(table2.rows[j], 'branchname').value;
//                pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
////                        child2.pmdCurrencyRID = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
////                        child2.pmdCurrencyValue = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
////                        child2.pmdCurrencyExchangeRate = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
////                        child2.pmdDisplaySymbol = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
////                        child2.pmdRemarks = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
////                        child2.pmdCardHolderName = dynTableGetNodeInRow(table.rows[i + 1], 'retailNetAmount').value;
//                pmdList.push(pmd);
//                receiptDPmd.rdBillAmount = $("#serviceNetAmount").val();
//                receiptDPmd.rdPaidAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
//                receiptDList.push(receiptDPmd);
//            }
//            receiptH.receiptD = receiptDList;
//            receiptH.paymentModeDetails = pmdList;
//            receiptHList.push(receiptH);
//        }
        billH.bhPaidAmount = parseFloat(totalPaid) + parseFloat($("#billHPaidAmount").val());
        billH.billD = billDList;
        //        if (actionCode !== "CANCEL_BILL") {
//            billH.receiptH = receiptHList;
//        }
//        dcomethealth.boRID, , ""
        var args = boRID + "/" + boCode + "/" + actionCode;
        dcomethealth.BillingResource.saveBill(billH, args)
                .done(function (data, textStatus, jqXHR) {
                    if (actionCode == "BUILTIN_ACTION") {
                        alert("Bill Modified");
                    } else {
                        alert("Bill Cancelled");
                    }
                    dcomethealth.util.loadpage('ViewTransaction');
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function refreshData() {
    }
    return {
        init: init,
        viewDetails: viewDetails,
        AutoCompleteTran: AutoCompleteTran,
        searchAdvance: searchAdvance,
        searchReceipt: searchReceipt,
        searchRefund: searchRefund,
        cancelAdvance: cancelAdvance,
        cancelRefund: cancelRefund,
        cancelReceiptModBill: cancelReceiptModBill,
        saveBill: saveBill,
        refreshData: refreshData
    };
}());
dcomethealth.ModifyTransaction.init();