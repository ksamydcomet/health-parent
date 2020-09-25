var dcomethealth = dcomethealth || {}, currencyExchange = {};
dcomethealth.CreateAdvance = (function () {
    var id = "CreateAdvance";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#curDate').text(moment().format('DD-MM-YYYY'));
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
            $("#" + id + " input[name='mrnSearch']").autocomplete({
                select: function (event, ui) {
                    var patMrnNo = ui.item.value;
                    var c = (ui.item.data.patDob).split('-');
                    var year = c[2];
                    var cyear = new Date().getFullYear();
                    $('#PatientAge').val(cyear - year);
                    $("#patientRID").val(ui.item.data.id);
                    $("#PatientName").val(ui.item.data.patFullName);

                    if (parseInt((ui.item.data.patGenderIndex)) === 1) {
                        $("#PatientGender").val("Male");
                    } else if (parseInt((ui.item.data.patGenderIndex)) === 2) {
                        $("#PatientGender").val("Female");
                    } else if (parseInt((ui.item.data.patGenderIndex)) === 3) {
                        $("#PatientGender").val("TransGendar");
                    }
                    $("#soPatientMobile").val(ui.item.data.patPhoneNo);
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
                    var searchParams = {"q": queryString};
                    dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
                        if (!!data && data.length > 0) {
                            response($.grep($.map(data, function (item) {
                                return {
                                    label: (item.patMrnNo || "") + (item.patMrnNo && item.patPhoneNo ? " - " : "") + (item.patPhoneNo || ""),
                                    value: (item.patMrnNo || ""),
                                    name: (item.patTitle || "") + (item.patTitle && item.patFullName ? " - " : ""),
                                    data: item
                                };
                            }), function (item, index) {
                                return index < 10;

                            }));
                        } else {
                            alert("No data available");
                            $("#mrnSearch").val("");
                            $("#patientRID").val("");
                            return false;
                        }
                    });
                },
                minLength: 1
            });
            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    mrnSearch: "required"
                },
                messages: {
                    mrnSearch: "Enter UHID"
                },
                submitHandler: function (form) {
                    var tab_id = document.getElementById('pmd_table');
                    var tab_length = tab_id.rows.length;
                    var check = false;
                    for (var i = 0; i < tab_length; i++) {
                        var total = dynTableGetNodeInRow(tab_id.rows[i], 'pmd_amount').value;
                        var payMode = dynTableGetNodeInRow(tab_id.rows[i], 'pay_mode_ddict');
                        if (payMode.options[payMode.selectedIndex].innerHTML == "Card") {
                            var bankName = dynTableGetNodeInRow(tab_id.rows[i], 'bankname').value;
                            var cardNo = dynTableGetNodeInRow(tab_id.rows[i], 'cardno').value;
                            var expDate = dynTableGetNodeInRow(tab_id.rows[i], 'expdate').value;
                            var approvalNo = dynTableGetNodeInRow(tab_id.rows[i], 'approvalno').value;
                            if (bankName == "") {
                                alert("Enter bank name");
                                return false;
                            }
                            if (approvalNo == "" || approvalNo == 0) {
                                alert("Enter approval no");
                                return false;
                            }
                            if (cardNo == "" || cardNo == 0) {
                                alert("Enter card no");
                                return false;
                            }
                            if (expDate == "") {
                                alert("Enter expiry date");
                                return false;
                            }
                        } else if (payMode.options[payMode.selectedIndex].innerHTML == "Cheque") {
                            var checkNo = dynTableGetNodeInRow(tab_id.rows[i], 'cheqno').value;
                            var bName = dynTableGetNodeInRow(tab_id.rows[i], 'bankname1').value;
                            var chequeDate = dynTableGetNodeInRow(tab_id.rows[i], 'cheqdate').value;
                            var branchName = dynTableGetNodeInRow(tab_id.rows[i], 'branchname').value;
                            if (bName == "") {
                                alert("Enter bank name");
                                return false;
                            }
                            if (checkNo == "" || checkNo == 0) {
                                alert("Enter cheque no");
                                return false;
                            }
                            if (chequeDate == "") {
                                alert("Enter cheque date");
                                return false;
                            }
                            if (branchName == "") {
                                alert("Enter branch name");
                                return false;
                            }
                        } else if (payMode.options[payMode.selectedIndex].innerHTML == "M-pesa") {
                            var mobileNo = dynTableGetNodeInRow(tab_id.rows[i], 'mobileno').value;
                            if (mobileNo == "" || mobileNo == 0) {
                                alert("Enter Mobile No");
                                return false;
                            }
                        }
                        if (total == "" || total == 0) {
                            check = true;
                        }
                    }
                    if (check) {
                        alert("Enter Amount");
                        return false;
                    } else {
                        submit(0, "ADVANCE", "CREATE_ADVANCE");
                    }
                }
            });
        });
    }
    function submit(boRID, boCode, actionCode) {
        var form = $("form");
        if (dcomethealth.CreateAdvance.validateForm(form)) {
            var advance = {};
            if ($("#advanceRID").val() !== "") {
                advance.id = $("#advanceRID").val();
            }
            advance.adType = 0;
//        advance.adRefRID = $("#visitReason").val();
            advance.adPayerRID = $("#patientRID").val();
//        advance.adPayerType = $("#visitReason").val();
            advance.adPayerNo = $("#soPatientMobile").val(); // need to verify mrn or patient no
            advance.adPayerName = $("#PatientName").val();
            advance.adAmount = $("#total_pmd").val();
            advance.adRecEntityRID = dcomethealth.loginuser.entityRid;
            advance.adAdjustedAmount = 0.00;
            advance.adRefundedAmount = 0.00;
            advance.adBalanceAmount = $("#total_pmd").val();
            advance.adPaidAmount = 0.00;
            var advanceList = [];
            var table2 = document.getElementById('pmd_table');
            var table_length2 = table2.rows.length;
            for (var j = 0; j < table_length2; j++) {
                var pmd = {};
                pmd.pmdTransType = 1;
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
                advanceList.push(pmd);
            }
            advance.paymentModeDetails = advanceList;
            var arg = boRID + "/" + boCode + "/" + actionCode;
            dcomethealth.AdvanceResource.saveAdvance(advance, arg)
                    .done(function (data, textStatus, jqXHR) {
                        alert("Saved");
                        dcomethealth.util.loadpage('CreateAdvance');
                        dcomethealth.util.base_init();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function  validateForm(form) {
        return  form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        submit: submit,
        validateForm: validateForm,
        refreshData: refreshData
    };
}());
dcomethealth.CreateAdvance.init();