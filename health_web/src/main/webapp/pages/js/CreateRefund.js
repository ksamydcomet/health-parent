var dcomethealth = dcomethealth || {};
dcomethealth.CreateRefund = (function () {
    var id = "CreateRefund", approvalRefund = 0;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autocomplete();
            $('#divSave').removeClass('hidden');
            $('#divSelectPat').removeClass('hidden');
            var searchQuery1 = {"paramCode": "REFUND_APPROVAL"};
            dcomethealth.DataDictionaryResource.getSysParam(searchQuery1, function (data) {
                if (parseInt(data.length) > 0) {
                    approvalRefund = 1;
                }
            });
            if (parseInt(dcomethealth.commonInit) !== 0) {
                $('#head_OP').text('Refund Info');
                $("#btns_state").empty();
                $('#divSave').addClass('hidden');
                $('#divSelectPat').addClass('hidden');
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    var div = $("#btns_state");
                    if (datalist.boaCode != "CANCEL_REFUND") {
                        div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '">' + datalist.boaName + '</button>');
                    }
                });
                var boRID = dcomethealth.boRID;
                dcomethealth.RefundResource.searchRefunds({"id": boRID}, function (data) {
                    $.each(data, function (pIdx, refund) {
                        $("#refhRid").val(refund.id);
                        $("#patientID").val(refund.refhPayerRID);
                        $("#patientName").val(refund.refhPayerName);
                        var searchObj = {"id": refund.refhPayerRID}
                        dcomethealth.PatientResource.searchPatient(searchObj, function (data) {
                            $.each(data, function (i, patData) {
                                if (patData.id === refund.refhPayerRID) {
                                    if (patData.patDob !== undefined) {
                                        var c = patData.patDob.split('-');
                                        $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                                    }
                                    var gen = '';
                                    if (patData.patGenderIndex == 1) {
                                        gen = 'Male';
                                    } else if (patData.patGenderIndex == 2) {
                                        gen = 'Female';
                                    } else {
                                        gen = 'Transgender';
                                    }
                                    $('#PatientGender').val(gen);
                                    $('#PatientMobile').val(patData.patPhoneNo);
                                }
                            });
                        });
                        $("#patientSearch").val(refund.refhPayerNo);
                        $("#totalAdv").val(refund.refhAmount);
                        $("#totalAdv").attr('readonly', true);
                        dcomethealth.CreateRefund.searchVisit(refund.refhPayerRID);
                        $.each(refund.refundD, function (idx, refD) {
                            dcomethealth.AdvanceResource.searchAdvances({"id": refD.refdAdvanceRID}, function (data) {
                                $.each(data, function (pIdx, advance) {
//                                    if (advance.adStatus !== 4 && parseInt(advance.adBalanceAmount) > 0) {
                                    $('#advDetails').append('<tr><td><input type="hidden" id="advanceRid" name="advanceRid" value="' + advance.id + '"/></td><td>' + advance.adNo + '"<input type="hidden" id="advanceRID" value="' + advance.id + '"/>\n\
                                <input type="hidden" id="adAdjustedAmount" name="adAdjustedAmount" value="' + advance.adAdjustedAmount + '"/>\n\
                                <input type="hidden" id="adRefundedAmount" name="adRefundedAmount" value="' + advance.adRefundedAmount + '"/></td>\n\
                                <td><input type="text" id="advanceAmount" value="' + (advance.adAmount).toFixed(2) + '" style="border: none"/></td>\n\
                                <td><input type="text" id="adBalanceAmount" value="' + (advance.adBalanceAmount).toFixed(2) + '" style="border: none"/></td>\n\
                                <td><input type="text" id="adPaidAmount" value="' + (advance.adPaidAmount).toFixed(2) + '" style="border: none"/></td></tr>');
//                                    }
                                });
                            });
                        });
                    });
                });
            }
            $(".btn-primary").off("click").on("click", function () {
                $(".btn-primary").attr("disabled", "disabled");
                var actionBtn = this.id;
                $.each(dcomethealth.actionDatalist, function (pIdx, action) {
                    if (action.boaCode === actionBtn) {
                        dcomethealth.NotificationResource.searchMasterCode(action.boaBomTypeIndex, function (data) {
                            if (actionBtn === "CANCEL_REFUND") {
                                saveRefundBOCancel("ADVANCE", "BUILTIN_ACTION");
                            } else {
                                saveRefundBO(dcomethealth.boRID, data, action.boaCode);
                            }
                        });
                    }
                });
            });
            $("#refhSave").off("click").on("click", function () {
                if ($("#patientID").val() != "") {
                    if (parseInt(approvalRefund) == 0) {
                        saveRefund(0, "ADVANCE", "CANCEL_ADVANCE");
                    } else if (parseInt(approvalRefund) == 1) {
                        saveRefund(0, "ADVANCE", "REFUND_REQUEST");
                    }
                } else {
                    alert("Enter valid UHID");
                    return false;
                }
            });
        });
    }
    function autocomplete() {
        var patientSearch;
        $("#" + id + " input[name='patientSearch']").autocomplete({
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
                $("#PatientMobile").val(ui.item.data.patPhoneNo);
                patientSearch = ui.item.value;
                var ID = parseInt(ui.item.data.id);
                $("#patientID").val(ID);
                $("#patientName").val(ui.item.data.patFullName);
                $("#patientNo").val(ui.item.data.patPhoneNo);
                $("#patMRN").val(ui.item.data.patMrnNo);
                dcomethealth.CreateRefund.searchVisit(ID);
                dcomethealth.CreateRefund.searchAdvance(ID);
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
                            return index < 75;
                        }));
                    } else {
                        alert("No data available");
                        $("#patientSearch").val("");
                        $("#patientID").val("");
                        return false;
                    }
                });
            },
            minLength: 1
        });
    }
    function  searchVisit(payerRID) {
        $("#visitDetails").empty();
        var searchParams = {"visPatRid": payerRID, "sortOrder": ["createdDateTime"], "sortDesc": "desc"};
        dcomethealth.PatientResource.searchVisit(searchParams, function (data) {
            if (!!data) {
                $.grep($.map(data, function (item) {
                    var reason = "";
                    $.each(dcomethealth.sDdict, function (pIdx, s_ddict) {
                        $(s_ddict).each(function () {
                            if (this.dditCode === "VISIT_REASON") {
                                $(this.ddict).each(function (cIdx, ddict) {
                                    if (ddict.id === item.visReasonIndex) {
                                        reason = ddict.ddictValue;
                                    }
                                });
                            }
                        });
                    });
                    $("#visitDetails").append("<tr><td>" + moment(item.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + "</td><td>" + reason + "</td></tr>")
                }), function (item, index) {
                    return index < 10;
                });
            }
        });
    }
    function searchAdvance(payerRID) {
        var searchObj = {"adPayerRID": payerRID};
        dcomethealth.AdvanceResource.searchAdvances(searchObj, function (data) {
            if (data !== undefined) {
                $.each(data, function (pIdx, advance) {
                    if (advance.adStatus !== 4 && parseInt(advance.adBalanceAmount) > 0) {
                        $('#advDetails').append('<tr><td><input type="checkbox" id="advanceCheck" value="' + advance.id + '" onclick="calculation(this)"/><input type="hidden" id="checkValue" value="0"/></td><td>' + advance.adNo + '"<input type="hidden" id="advanceRID" value="' + advance.id + '"/><input type="hidden" id="adAdjustedAmount" name="adAdjustedAmount" value="' + advance.adAdjustedAmount + '"/></td>\n\
                                <td><input type="text" id="advanceAmount" value="' + (advance.adAmount).toFixed(2) + '" style="border: none"/></td>\n\
                                <td><input type="text" id="adBalanceAmount" value="' + (advance.adBalanceAmount).toFixed(2) + '" style="border: none"/></td>\n\
                                <td><input type="text" id="adPaidAmount" value="' + (advance.adPaidAmount).toFixed(2) + '" style="border: none"/></td></tr>');
                    }
                });
            } else {
                alert("Payer Doesnt Have Advance");
                return false;
            }
        });
    }
    function saveRefund(boRID, boCode, actionCode) {
        var advanceList = [];
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var advance = {};
            if (parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'checkValue').value) === 1) {
                if (dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value !== "") {
                    advance.id = dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value;
                } else {
                    alert("Search Error");
                    return false;
                }
                advance.adType = 0;
//        advance.adRefRID = $("#visitReason").val();
                advance.adPayerRID = $("#patientID").val();
//        advance.adPayerType = $("#visitReason").val();
                advance.adPayerNo = $("#patMRN").val(); // need to verify mrn or patient no
                advance.adPayerName = $("#patientName").val();
                advance.adAmount = dynTableGetNodeInRow(table.rows[i + 1], 'advanceAmount').value;
                advance.adAdjustedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adAdjustedAmount').value;
//                advance.adRefundedAmount = 0.00;
                advance.adRefundedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adBalanceAmount').value;
                advance.adBalanceAmount = 0.00;
                advance.adPaidAmount = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'adPaidAmount').value);
//                advance.adPaidAmount = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'adPaidAmount').value) + parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'adBalanceAmount').value);
//                refundD.refdAdvanceRID = dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value;
//                refundD.refdType = 1;
//                refundD.refdAmount = dynTableGetNodeInRow(table.rows[i + 1], 'advanceAmount').value;
//
                advanceList.push(advance);
            }
        }
        if (parseInt(advanceList.length) < 1) {
            alert("Select Atleast One Advance");
            return false;
        }
        var arg = $("#advanceRID").val() + "/" + boCode + "/" + actionCode;
        dcomethealth.AdvanceResource.saveAdvanceList(advanceList, arg)
                .done(function (data, textStatus, jqXHR) {
                    alert("Refund Raised for " + $("#patMRN").val());
                    dcomethealth.util.loadpage('CreateRefund');
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function  saveRefundBO(boRID, boCode, actionCode) {
        var refund = {};
        if ($("#refhRid").val() !== "") {
            refund.id = $("#refhRid").val();
        } else {
            alert("Error Creating Refund");
            return false;
        }
        refund.refhRemarks = '';
        refund.refhUnitRID = dcomethealth.selectedunit;
//        refund.refhCancelledDate = $("#lensValid").val();
        if (actionCode === 'APPROVE_REFUND') {
            refund.refhApproverUserRID = dcomethealth.loginuser.id;
        }
        refund.refhActionCode = actionCode;
        refund.refhBeniciaryName = $("#patientName").val();
//        alert(actionCode);
//        console.log(refund);
//        return false;
        var arg = boRID + "/" + boCode + "/" + actionCode;
        dcomethealth.AdvanceResource.saveRefundByAdvance(refund)
                .done(function (data, textStatus, jqXHR) {
                    alert("Saved");
                    dcomethealth.util.loadpage('CreateRefund');
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveRefundBOCancel(boCode, actionCode) {
        var advance = {};
        var refh = {};
        var refList = [];
        if ($("#advanceRID").val() !== "") {
            advance.id = $("#advanceRID").val();
        } else {
            alert("Error Creating Advance");
            return false;
        }
        advance.adType = 0;
        advance.adRemarks = "Cancelled Refund";
//        advance.adRefRID = $("#visitReason").val();
        advance.adPayerRID = $("#patientID").val();
//        advance.adPayerType = $("#visitReason").val();
        advance.adPayerNo = $("#patMRN").val(); // need to verify mrn or patient no
        advance.adPayerName = $("#patientName").val();
        advance.adAmount = $("#advanceAmount").val();
        advance.adAdjustedAmount = $("#adAdjustedAmount").val();
        if ($("#adAdjustedAmount").val() != 0) {
            advance.adBalanceAmount = $("#advanceAmount").val() - $("#adAdjustedAmount").val();
        }
        advance.adBalanceAmount = $("#advanceAmount").val();
        advance.adRefundedAmount = $("#adRefundedAmount").val();
        advance.adPaidAmount = 0.00;
        if ($("#refhRid").val() !== "") {
            refh.id = $("#refhRid").val();
        } else {
            alert("Error Creating Refund");
            return false;
        }
        refh.refhRemarks = '';
        refh.refhUnitRID = dcomethealth.selectedunit;
//        refund.refhCancelledDate = $("#lensValid").val();
        refh.refhBeniciaryName = $("#patientName").val();
//                refundD.refdAdvanceRID = dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value;
//                refundD.refdType = 1;
//                refundD.refdAmount = dynTableGetNodeInRow(table.rows[i + 1], 'advanceAmount').value;
        refList.push(refh);
        advance.refundH = refList;
        var arg = $("#advanceRID").val() + "/" + boCode + "/" + actionCode;
        dcomethealth.AdvanceResource.saveAdvance(advance, arg)
                .done(function (data, textStatus, jqXHR) {
                    alert("Saved");
                    dcomethealth.util.loadpage('CreateAdvance');
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
        autocomplete: autocomplete,
        searchVisit: searchVisit,
        searchAdvance: searchAdvance,
        saveRefund: saveRefund,
        saveRefundBO: saveRefundBO,
        saveRefundBOCancel: saveRefundBOCancel,
        refreshData: refreshData
    };
}());
dcomethealth.CreateRefund.init();