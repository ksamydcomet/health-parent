var dcomethealth = dcomethealth || {};
var payerData = {};
var payerRid = null, btnName = "", check = false, currencyExchange = {}, visitReason = 0;
dcomethealth.CreateOPBill = (function () {
    var id = "CreateOPBill", serviceClearVar = 0, serviceNameVar = "", isNotExistVisit = false, billHRidVar = false;
    var parameters = {userId: null};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            if (dcomethealth.loginuser.entityRid == 4) {
                $("select#patRefType option").each(function (ix, selectOption) {
                    if (($(selectOption).text()).includes("Corporate")) {
                        $(selectOption).remove();
                    }
                });
            }
            mrn_autocomplete();
            autocomplete();
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
            if (parseInt(dcomethealth.commonInit) == 0) {
                var searchParams = {"staffCategory": 237};
                var options = '';
                dcomethealth.MasterResource.searchStaffNc(searchParams, function (data) {
                    $.each(data, function (idx, Val) {
                        options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                    });
                    $("#VDName").html(options);
                });
            }
            if (parseInt(dcomethealth.commonInit) !== 0) {
                $('#head_OP').text('Billing Info');
                if (parseInt(dcomethealth.bomtypeIndex) == 40) {
                    $('#head_OP').text('Process Services');
                }
                $("#btns_state").empty();
                $('#paidDiv').removeClass('hidden');
                $('#dueDiv').removeClass('hidden');
                $('#remainAmountDiv').removeClass('hidden');
                var categories = [];
                var btnNumber = 0;
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    pIdx++;
                    var div = $("#btns_state");
                    if ($.inArray(datalist.boaName, categories) === -1) {
                        categories.push(datalist.boaName);
                        if (datalist.boaName === "BUILT IN ACTION") {
                            div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">Submit</button>');
                        } else if (datalist.boaName === "Cancel Bill") {

                        } else {
                            if (datalist.boaCode == 'CREATE') {
                                datalist.boaName = 'Process';
                            }
                            div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>');
                        }
                        btnName = datalist.boaName;
                        btnNumber++;
                        var boCode = "";
                        if ((btnName == 'Process' && btnNumber == 1) || (btnNumber == 2 && btnName == 'Cancel')) {//Need close button function admit page arguments
                            boCode = "CREATE";
                        } else if ((btnName == 'Collect Payment' && btnNumber == 1) || (btnNumber == 2 && btnName == 'Cancel Bill')) {
                            boCode = "COLLECT_PAYMENT";
                        }
                        if (dcomethealth.actionDatalist.length == pIdx) {
                            div.append('<button type="button" id="closeBtn" class="btn btn-primary  jqueryUIToolTip" title="Close" onclick="dcomethealth.CreateOPBill.loadWorkList(\'' + boCode + '\')"><i class="fa fa-times" aria-hidden="true"></i></button>');
                        }
                    }
                });
                dcomethealth.datatypes.init($("#" + id));
                fetchDetails();
            }
            $(".btn-primary").off("click").on("click", function () {
                $(".btn-primary").attr("disabled", "disabled");
                if ($("#patRefType").val() != 31) {
                    if (parseInt($("#payerName").val()) == 0) {
                        alert("Select payer");
                        $(".btn-primary").attr("disabled", false);
                        return false;
                    }
                    if (parseInt($("#payerName option:selected").val()) > 0) {
                        if (!$("#bhEligibleAmount").val()) {
                            alert("Enter eligible amount");
                            $(".btn-primary").attr("disabled", false);
                            return  false;
                        }
                        if (!$("#bhApprovalNo").val()) {
                            alert("Enter approval number");
                            $(".btn-primary").attr("disabled", false);
                            return  false;
                        }
                    }
                }
                var actionBtn = this.id;
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var itemname = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                }
                if (parseInt(dcomethealth.commonInit) == 0) {
                    if (check) {
                        if (dcomethealth.actionDatalist === 0) {
                            saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                        } else if (dcomethealth.actionDatalist !== 0 && $('#total_pmd').val() === "" && actionBtn !== "CANCEL_BILL") {
                            alert("Payment is empty");
                            setTimeout(function () {
                                $('#total_pmd').focus();
                            }, 1);
                        } else if (parseInt(dcomethealth.bomtypeIndex) === 35) {
                            saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                        } else {
                            var i = 1;
                            $.each(dcomethealth.actionDatalist, function (pIdx, action) {
                                if (action.boaCode === actionBtn) {
                                    if (i === 1) {

                                        var billHDueAmount = document.getElementById('billHDueAmount').value;
                                        var total_pmd = document.getElementById('total_pmd').value;
                                        var payerAmount = !!$('#serviceEligibleAmount').val() ? document.getElementById('serviceEligibleAmount').value : 0;
                                        var payerPaidAmount = !!$('#payerPaidAmount').val() ? $('#payerPaidAmount').val() : 0;
                                        var payeramountcheckwithPaidAmount = payerAmount - payerPaidAmount;
                                        var dueAmount = billHDueAmount - payeramountcheckwithPaidAmount;
                                        if (parseFloat(total_pmd) > parseFloat(dueAmount)) {
                                            setTimeout('$(".btn-primary").removeAttr("disabled")', 1500);
                                            alert("Due amount is less or Payer Amount is pending");
                                            return false;
                                        } else {
                                            dcomethealth.NotificationResource.searchMasterCode(action.boaBomTypeIndex, function (data) {
                                                saveBill(dcomethealth.boRID, data, action.boaCode);
                                            });
                                            i++;
                                        }
                                    }
                                }
                            });
                        }
                    } else {
                        alert("Patient not registered yet");
                    }
                } else {
                    if (dcomethealth.actionDatalist === 0) {
                        saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                    } else if (dcomethealth.actionDatalist !== 0 && $('#total_pmd').val() === "" && actionBtn !== "CANCEL_BILL" && dcomethealth.bomtypeIndex == 6) {
                        var table = document.getElementById('pmd_table');
                        var table_length = table.rows.length;
                        alert("Payment is empty");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[table_length - 1], 'pmd_amount').focus();
                        }, 1);
                    } else if (parseInt(dcomethealth.bomtypeIndex) === 35) {
                        saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                    } else {
                        if (isNotExistVisit) {
                            alert("Patient not registered yet");
                            return false;
                        }
                        var i = 1;
                        $.each(dcomethealth.actionDatalist, function (pIdx, action) {
                            if (action.boaCode === actionBtn) {
                                if (i === 1) {
                                    var billHDueAmount = document.getElementById('billHDueAmount').value;
                                    var total_pmd = document.getElementById('total_pmd').value;
                                    var payerAmount = !!$('#serviceEligibleAmount').val() ? document.getElementById('serviceEligibleAmount').value : 0;
                                    var payerPaidAmount = !!$('#payerPaidAmount').val() ? $('#payerPaidAmount').val() : 0;
                                    var payeramountcheckwithPaidAmount = payerAmount - payerPaidAmount;
                                    var dueAmount = billHDueAmount - payeramountcheckwithPaidAmount;
                                    if (parseFloat(total_pmd) > parseFloat(dueAmount)) {
                                        setTimeout('$(".btn-primary").removeAttr("disabled")', 1500);
                                        alert("Due amount is less or Payer Amount is pending");
                                        return false;
                                    } else {
                                        dcomethealth.NotificationResource.searchMasterCode(action.boaBomTypeIndex, function (data) {
                                            saveBill(dcomethealth.boRID, data, action.boaCode);
                                        });
                                        i++;
                                    }
                                }
                            }
                        });
                    }
                }
                setTimeout('$(".btn-primary").removeAttr("disabled")', 1500);
            });
        });
    }
    function loadWorkList(actionCode) {
        if (actionCode == "CREATE") {
            dcomethealth.util.loadWorklistByCode('PENDING_SERVICE_REQUESTS');
        } else if (actionCode == "COLLECT_PAYMENT" && $("#bhIsDraft").val() == 1) {
            dcomethealth.util.loadWorklistByCode('PENDING_DRAFT_BILLS');
        } else if (actionCode == "COLLECT_PAYMENT" && (!$("#bhIsDraft").val() || $("#bhIsDraft").val() == 0)) {
            dcomethealth.util.loadWorklistByCode('COLLECT_PAYMENT');
        }
    }
    function fetchDetails() {
        if (parseInt(dcomethealth.bomtypeIndex) !== 40) {
            var searchObj = {"id": dcomethealth.boRID};
            dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
                $.each(data, function (pIdx, data) {
                    $('#billHRID').val(data.billH.id);
                    $('#patientRID').val(data.billH.bhPatientRID);
                    $('#visitRID').val(data.billH.bhPatientVisitRID);
                    $('#billNo').val(data.billH.bhBillNo);
                    $('#bhPrintableBillNo').val(data.billH.bhPrintableBillNo);
                    $('#bhPrefix').val(data.billH.bhPrefix);
                    $('#bhType').val(data.billH.bhType);
                    $('#bhIsDraft').val(data.billH.bhIsDraft);
                    $('#serviceGrossAmount').val(data.billH.bhGrossAmount);
                    $('#round_off_amount').val(data.billH.bh);
                    $('#serviceDiscountAmount').val(data.billH.bhTotalDiscountAmount);
                    $('#serviceNetAmount').val(data.billH.bhNetAmount);
                    $('#billHPaidAmount').val(data.billH.bhPaidAmount);
                    $('#billHPaidAmt').val(data.billH.bhPaidAmount);
                    $('#billHDueAmount').val(data.billH.bhDueAmount);
                    $('#remainAmount').val(data.billH.bhDueAmount);
                    $('#payerPaidAmount').val(data.billH.bhPayerAmount);
                    $('#patRefType').attr('disabled', true);
                    $('#patRefType').val(data.billH.bhPayerType);
                    if (parseFloat(data.billH.bhTotalDiscountAmount) != 0) {
                        $('#viewDiscount').removeClass('hidden');
                        $('#DiscName').attr('disabled', true);
                        $('#DiscValue').attr('disabled', true);
                        $('#DiscPercent').attr('disabled', true);
                    } else {
                        $('#DiscName').attr('disabled', true);
                        $('#DiscValue').attr('disabled', true);
                        $('#DiscPercent').attr('disabled', true);
                    }
                    $('#DiscValue').val(data.billH.bhTotalDiscountAmount);
//                    if (data.billH.bhEligibleAmount != 0) {
//                        $("#eAmountDiv").removeClass("hidden");
//                        $("#approvalnoDiv").removeClass("hidden");
//                        $('#bhEligibleAmount').val(data.billH.bhEligibleAmount);
//                        $('#bhApprovalNo').val(data.billH.bhApprovalNumber);
//                        $('#bhEligibleAmount').attr('disabled', true);
//                        $('#bhApprovalNo').attr('disabled', true);
//                    }
                    if (parseInt(data.billH.bhPayerType) != 31) {
                        $("#payerInfo").removeClass("hidden");
                        $("#eAmountDiv").removeClass("hidden");
                        $("#approvalnoDiv").removeClass("hidden");
                        $('#bhEligibleAmount').val(data.billH.bhEligibleAmount);
                        $('#bhApprovalNo').val(data.billH.bhApprovalNumber);
                        $('#bhEligibleAmount').attr('disabled', true);
                        $('#bhApprovalNo').attr('disabled', true);
                        payerChange();
                    }

                    var options = "";
                    options = '<option value="' + data.billH.bhDocRefRID + '">' + data.billH.bhDoctorName + '</option>';
                    $('#VDName').html(options);
                    $('#VDName').attr('disabled', true);
                    $.each(data.patient, function (pIdx, patient) {
                        $('#mrnSearch').val(patient.patMrnNo);
                        $('#mrnSearch').attr("readonly", true);
                        $('#doctorName').addClass("hidden");
                        $('#PatientName').val(patient.patFullName);
                        if (patient.patDob !== undefined) {
                            var c = patient.patDob.split('-');
                            $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                        }
                        var gen = '';
                        if (parseInt(patient.patGenderIndex) === 1) {
                            gen = 'Male';
                        } else if (parseInt(patient.patGenderIndex) === 2) {
                            gen = 'Female';
                        } else {
                            gen = 'Transgender';
                        }
                        $('#PatientGender').val(gen);
//                            $('#PatientGender option[value="' + patient.patGenderIndex + '"]').prop('selected', true);
                        $('#patientMobile').val(patient.patPhoneNo);
                    });
                    $("#so_tbody").empty();
                    $.each(data.billH.billD, function (pIdx, billD) {
                        serviceRequestDraft = false;
                        $("#so_tbody").append('<tr><td width="5%"><i class="dct-icon fa fa-minus-circle hidden " onclick="delete_row(this)"></i></td>\n\
                        <td width="40%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" id="serviceName" name="serviceName" readonly="readonly" value = "' + billD.bdItemName + '" onchange="ClearName(this)" class="col-md-11 col-sm-11 col-xs-11"><input type="hidden" id="serviceRID" name="serviceRID" value="' + billD.bdItemRID + '"><input type="hidden" id="bdGroupRID" name="bdGroupRID" value="' + billD.bdGroupRID + '">\n\
                        <input id="serviceReqDRid" type="hidden"/><input type="hidden" id="serviceOrderRID" name="serviceOrderRID" value="' + billD.bdItemOrderRID + '"><input id="billDRID" type="hidden" value="' + billD.id + '"></td><td width="10%"><input id = "qtyService" maxlength = "3" type = "text" readonly="readonly" value = "' + billD.bdQty + '" name = "qtyService" onBlur="calculation(this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="10%"><input type="text" id="serviceDisc" style="text-align:right;" readonly="readonly" name = "serviceDisc" value = "' + billD.bdDiscountAmount + '" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="10%"><input type="text" id="MRP" name = "MRP" style="text-align:right;" readonly="readonly" value = "' + billD.bdPrice + '" onBlur = "calculation(this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="20%"><input type = "text" id = "netAmount" style="text-align:right;" readonly="readonly" name = "netAmount" value = "' + billD.bdNetAmount + '" onBlur = "calculation(this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="5%"><i id="plus" class = "dct-icon fa fa-plus-square red hidden" onclick = "insert_row(\'dyn_table\',this,1)" ></i></td></tr>');
                    });
                    searchAdvance(data.billH.bhPatientRID);
                    payerRid = data.billH.bhPayerRID;
                });
                calculation();
            });
        } else {
            $("#paymentHeaderDiv,#discountHeader,#viewDiscount,#billInformation,#paymentDetailsDiv,#doctorName").addClass("hidden");
            $("#so_tbody").empty();
            var searchObj = {"serReqhId": dcomethealth.boRID};
            dcomethealth.ServiceRequestResource.searchServiceRequest(searchObj, function (datas) {
                $.each(datas, function (pIdx, data) {
                    $('#patientRID').val(data.serReqhPatRid);
                    $('#billHRID').val(data.serReqhBillHRid);
                    $('#visitRID').val(data.serReqOpVisitRid);
                    $('#serReqhProcedureRid').val(data.serReqhProcedureRid);
                    $('#serReqhOpCheck').val(data.serReqhOpCheck);
                    $('#billHPaidAmount').val(0);
                    if (data.serReqOpVisitRid == 0 || !data.serReqOpVisitRid) {
                        dcomethealth.PatientResource.searchVisit({"visPatRid": data.serReqhPatRid}, function (data) {
                            if (!!data) {
                                if (data[0].visTypeIndex == 19) {//IP
                                    $('#visitRID').val(data[0].id);
                                    $('#visitType').val("IP");
                                }
                                if (data[0].visTypeIndex == 20) {//OP
                                    $('#visitRID').val(data[0].id);
                                    $('#visitType').val("OP");
                                }
                            } else {
                                isNotExistVisit = true;
                            }
                        });
                    }
                    setTimeout(function () {
                        if (!data.serReqhBillHRid || data.serReqhBillHRid == 0) {
                            if (data.serReqhOpCheck == 1) {
                                var searchObj = {"drugReqHProcedureRid": data.serReqhProcedureRid, "drugReqHPatRid": data.serReqhPatRid};
                                dcomethealth.ItemOrderResource.getDrugs(searchObj, function (data) {
                                    $.each(data, function (pIdx, drugReq) {
                                        if (drugReq.drugReqHBillHRID != 0 && !!drugReq.drugReqHBillHRID) {
                                            billHRidVar = true;
                                            $("#billHRID").val(drugReq.drugReqHBillHRID);
                                        }
                                    });
                                    if (!billHRidVar) {
                                        if (!!$('#visitRID').val() && $('#visitRID').val() != 0) {
                                            var searchObj = {"bhPatientVisitRID": $('#visitRID').val()};
                                            dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
                                                if (data.length > 0) {
                                                    billHRidVar = true;
                                                    $("#billHRID").val(data[0].billH.id);
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                            if (data.serReqhOpCheck == 3) {
                                var searchObj = {"bhPatientVisitRID": data.serReqOpVisitRid};
                                dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
                                    if (data.length > 0) {
                                        billHRidVar = true;
                                        $("#billHRID").val(data[0].billH.id);
                                    }
                                });
                            }
                        }
                    }, 500);
                    dcomethealth.PatientResource.searchPatient({"id": data.serReqhPatRid}, function (dataPat) {
                        $.each(dataPat, function (pIdx, patient) {
                            $('#mrnSearch').val(patient.patMrnNo);
                            $('#mrnSearch').attr("readonly", true);
                            $('#PatientName').val(patient.patFullName);
                            if (patient.patDob !== undefined) {
                                var c = patient.patDob.split('-');
                                $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                            }
                            var gen = '';
                            if (parseInt(patient.patGenderIndex) === 1) {
                                gen = 'Male';
                            } else if (parseInt(patient.patGenderIndex) === 2) {
                                gen = 'Female';
                            } else {
                                gen = 'Transgender';
                            }
                            $('#PatientGender').val(gen);
                            $('#patientMobile').val(patient.patPhoneNo);
                        });
                    });
                    $("#discHead").addClass("hidden");
                    $("#so_tbody").empty();
                    $("#so_tbody").append('<tr><td width="5%"><i id="minus" class="dct-icon fa fa-minus-circle " onclick="delete_row(this)"></i></td>\n\
                                    <td width="40%"><input id="serviceReqDRid" type="hidden" ><input type="text" id="serviceName" name="serviceName" value = ""  onkeypress="return dcomethealth.validation.ValidateAlpha(event)" onchange="dcomethealth.CreateOPBill.autoIdSet(this)" class="col-md-11 col-sm-11 col-xs-11"><input type="hidden" id="serviceRID" name="serviceRID" value=""><input type="hidden" id="bdGroupRID" name="bdGroupRID" value="">\n\
                                    <input type="hidden" id="serviceOrderRID" name="serviceOrderRID" value=""><input type="hidden" id="serReqItemPrice" name="serReqItemPrice" value=""><input id="billDRID" type="hidden" value=""></td><td width="10%"><input id = "qtyService" value="1" onkeypress="return dcomethealth.validation.isNumberKey(event)" onblur="calculation()" type = "text" class="col-md-12 col-sm-12 col-xs-12" value = "" name ="qtyService" maxlength="5" ></td>\n\
                                    <td class="hidden" width="0%"><input type = "hidden" onblur="calculation()" id = "serviceDisc" name = "serviceDisc" value = "" class="hidden col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="10%"><input type = "text" id = "MRP" name = "MRP" maxlength="10" value = "" onBlur = "calculation()" class="col-md-12 col-sm-12 col-xs-12" style="text-align:right;" onkeypress="return dcomethealth.validation.isDecimalKey(event)" /></td>\n\
                                    <td width="20%"><input type = "text" id ="netAmount" style="text-align:right;" name="netAmount" value="" onkeypress="return dcomethealth.validation.isDecimalKey(event)" onkeydown="tabKeyPress(this, event)" onBlur="calculation()" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="5%"><i id="plus" class = "dct-icon fa fa-plus-square red" onclick = "insert_row(\'dyn_table\', this,1)" ></i></td></tr>');
                    autocomplete();
                    $.each(data.serviceRequest, function (pIdx, serviceReq) {
                        $("#so_tbody").append('<tr><td width="5%"><i id="minus" class="dct-icon fa fa-minus-circle hidden" onclick="delete_row(this)"></i></td>\n\
                                    <td width="40%"><input id="serviceReqDRid" type="hidden" value="' + serviceReq.serReqRid + '"><input type="text"  onkeypress="return dcomethealth.validation.ValidateAlpha(event)" readonly id="serviceName" name="serviceName" value = "' + serviceReq.serReqItemName + '"  onchange="dcomethealth.CreateOPBill.autoIdSet(this)" class="col-md-11 col-sm-11 col-xs-11"><input type="hidden" id="serviceRID" name="serviceRID" value="' + serviceReq.serReqItemRID + '"><input type="hidden" id="bdGroupRID" name="bdGroupRID" value="' + serviceReq.serReqItemGroupRid + '">\n\
                                    <input type="hidden" id="serviceOrderRID" name="serviceOrderRID" value=""><input type="hidden" id="serReqItemPrice" name="serReqItemPrice" value="' + serviceReq.serReqItemPrice + '"><input id="billDRID" type="hidden" value=""></td><td width="10%"><input id="qtyService" onblur="calculation()" onkeypress="return dcomethealth.validation.isNumberKey(event)" type="text" class="col-md-12 col-sm-12 col-xs-12" value = "' + serviceReq.serReqItemQty + '" name="qtyService" maxlength="5" disabled></td>\n\
                                    <td class="hidden" width="0%"><input type = "hidden" onblur="calculation()" readonly id = "serviceDisc" name = "serviceDisc" value = "" class="hidden col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="10%"><input type = "text" id = "MRP" name = "MRP"  maxlength="10" value = "' + serviceReq.serReqServiceCost + '" onBlur = "calculation()" class="col-md-12 col-sm-12 col-xs-12" style="text-align:right;" onkeypress="return dcomethealth.validation.isDecimalKey(event)" disabled/></td>\n\
                                    <td width="20%"><input type = "text" id = "netAmount" readonly style="text-align:right;" name = "netAmount" value = "" onBlur = "calculation()" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                                    <td width="5%"><i id="plus" class = "" onclick = "insert_row(\'dyn_table\', this,0)" ></i></td></tr>');
                        var gross = 0, net = 0, disc_total = 0;
                        calculation();
                    });
                });
                var searchObj = {"id": $('#billHRID').val()};
                dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
                    $.each(data, function (pIdx, data) {
                        $('#billNo').val(data.billH.bhBillNo);
                        $('#bhPrintableBillNo').val(data.billH.bhPrintableBillNo);
                        $('#bhPrefix').val(data.billH.bhPrefix);
                        $('#bhType').val(data.billH.bhType);
                        $('#bhIsDraft').val(data.billH.bhIsDraft);
                    });
                });
            });
        }
    }
    function  searchAdvance(payerRID) {
        var searchObj = {"adPayerRID": payerRID};
        dcomethealth.AdvanceResource.searchAdvances(searchObj, function (data) {
            if (data == null) {
                $("#openAdvance").addClass("hidden");
                $("#closeAdvance").addClass("hidden");
            }
            $("#adjustAmount").addClass("hidden");
            $("#advance_tbody").empty();
            if (!!data) {
                $.each(data, function (pIdx, advance) {
                    if (parseInt(advance.adStatus) == 1 || parseInt(advance.adStatus) == 3) {
                        $("#openAdvance").removeClass("hidden");
                        var zeroCheck = "";
                        if (advance.adBalanceAmount == 0 || advance.adBalanceAmount == "") {
                            zeroCheck = "readonly";
                        } else {
                            zeroCheck = "";
                        }
                        $("#advance_tbody").append('<tr><td>' + advance.adNo + '<input type="hidden" id="advanceNo" name="advanceNo" value="' + advance.adNo + '" readonly/><input type="hidden" id="advanceRID" value="' + advance.id + '"/></td>\n\
                <td><input class="text-right" type="text" id="adjustedAmt" name="adjustedAmt" value="" ' + zeroCheck + ' placeholder="Adjust amount.." maxlength=10 onchange="advPaid(this)" onkeypress="return dcomethealth.validation.isDecimalKey(event)"/></td>\n\
                <td>' + advance.adAmount + '<input type="hidden" id="advanceAmt" name="advanceAmt" value="' + advance.adAmount + '" readonly/></td>\n\
                <td>' + advance.adBalanceAmount + '<input type="hidden" id="balanceAmt" name="balanceAmt" value="' + advance.adBalanceAmount + '" readonly/></td><td class="hidden">' + advance.adPaidAmount + ' <input type="hidden" id="paidAmt" name="paidAmt" value="' + advance.adPaidAmount + '" readonly/></td></tr>');//
                    }
                });
            }
        });
    }
    function payerChange() {
        var payerType = $("#patRefType option:selected").val();
        $("#eligibleAmtDiv").addClass("hidden");
        $("#payerInfo").addClass("hidden");
        payerData = {};
        if (parseInt(payerType) != 31) {//payerType 31 is self
            $("#dueAmtDiv").removeClass("hidden");
            $("#eligibleAmtDiv").removeClass("hidden");
            $("#payerInfo").removeClass("hidden").addClass("");
            $("#payerName").empty();
            var searchObj = {"pdPayerType": payerType, "pdIsActive": 1};
            dcomethealth.MasterResource.searchPayerMaster(searchObj, function (data) {
                $('#payerName').append($("<option></option>").attr("value", 0).text("Select Payer"));
                if (!!data && data.length > 0) {
                    $.each(payerData = data, function (pSx, payer) {
                        $('#payerName').append($("<option></option>").attr("value", payer.pdId).text(payer.pdPayerName));
                    });
                    if (parseInt(dcomethealth.bomtypeIndex) !== 40 && parseInt(dcomethealth.bomtypeIndex) !== 0) {
                        $('#payerName').attr('disabled', true);
                        $('#payerName').val(payerRid);
                    }
                }
            });
            if (dcomethealth.bomtypeIndex != 6) {
//                $("#paymentDiv").addClass("hidden");
//                $("#paymentDivWithPayer").removeClass("hidden");
            } else {
//                $("#paymentDiv").removeClass("hidden");
//                $("#paymentDivWithPayer").addClass("hidden");
            }
        } else {
            $("#dueAmtDiv").addClass("hidden");
            $("#eligibleAmtDiv").addClass("hidden");
            $("#payerInfo").addClass("hidden");
            clearPayerValues();
            doctorChange();
        }
        if (parseInt(dcomethealth.bomtypeIndex) !== 40) {
            return false;
        } else {
            payerNameChange(1);
        }
    }
    function clearPayerValues() {
        $("#bhEligibleAmount").val("");
        $("#bhApprovalNo").val("");
    }
    function doctorChange() {
        var check = false;
        if ($("#patRefType option:selected").val() == 31) {
            var searchObj = {"rsmDocRid": $("#VDName option:selected").val()};
            dcomethealth.MasterResource.searchResourceServiceMap(searchObj, function (data) {
                if (!!data && data.length > 0) {
                    $.each(data, function (ifx, servicemap) {
                        var table = document.getElementById('dyn_table');
                        for (var i = 0; i < table.rows.length - 1; i++) {
                            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
                            if (parseInt(serviceRid) == parseInt(servicemap.rsmServiceRid) && servicemap.rsmDocRid == parseInt($("#VDName option:selected").val())) {
                                if (visitReason == 'Emergency') {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = servicemap.rsmEmergencyPrice;
                                    check = true;
                                    return false;
                                } else {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = servicemap.rsmNormalPrice;
                                    check = true;
                                    return false;
                                }
                            } else {
                                $.each(dcomethealth.serviceMaster, function (pfx, ServicePr) {
                                    if (parseInt(serviceRid) == parseInt(ServicePr.id)) {
                                        if (visitReason == 'Emergency') {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bEprice;
                                            check = true;
                                        } else {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bPrice;
                                            check = true;
                                        }
                                    }
                                });
                            }
                        }
                    });
                    if (check == true) {
                        calculation();
                    }
                } else {
                    payerNameChange(0);
                }
            });
        } else {
            payerNameChange(0);
        }
    }
    function payerNameChange(value) {
        if ($("#payerName option:selected").val() != 0) {
            if (value != 0) {
                $("#bhEligibleAmount").val("");
                $("#bhApprovalNo").val("");
            }
            $("#eAmountDiv").removeClass("hidden");
            $("#approvalnoDiv").removeClass("hidden");
        } else if ($("#payerName option:selected").val() == "undefined") {
            $("#bhEligibleAmount").val("");
            $("#bhApprovalNo").val("");
            $("#eAmountDiv").addClass("hidden");
            $("#approvalnoDiv").addClass("hidden");
        } else {
            $("#bhEligibleAmount").val("");
            $("#bhApprovalNo").val("");
            $("#eAmountDiv").addClass("hidden");
            $("#approvalnoDiv").addClass("hidden");
        }
        var payerType = $("#patRefType option:selected").val();
        var table = document.getElementById('dyn_table');
        for (var i = 0; i < table.rows.length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            if (parseInt(payerType) != 31 && !!payerData) {//payerType 31 is self
                var payerId = $("#payerName option:selected").val();
                $.each(payerData, function (pdx, payer) {
                    if (payerId == payer.pdId) {
                        $.each(payer.payerServiceMap, function (pEx, payerSerCost) {
                            if (parseInt(serviceRid) == parseInt(payerSerCost.psmServiceRid)) {
                                dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = payerSerCost.psmDiscountPrice;
                                return false;
                            } else {
                                $.each(dcomethealth.serviceMaster, function (pfx, ServicePr) {
                                    if (parseInt(serviceRid) == parseInt(ServicePr.id)) {
                                        if (visitReason == 'Emergency') {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bEprice;
                                        } else {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bPrice;
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            } else {
                $.each(dcomethealth.serviceMaster, function (pfx, ServicePr) {
                    if (parseInt(serviceRid) == parseInt(ServicePr.id)) {
                        if (visitReason == 'Emergency') {
                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bEprice;
                        } else {
                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bPrice;
                        }
                    }
                });
            }
            if (!!serviceRid) {
                calculation();
            }
        }
    }
    function autoIdSet(elem) {
        calculation();
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'serviceRID').value = "";
            dynTableGetNodeInRow(elem, 'serviceName').value = "";
            dynTableGetNodeInRow(elem, 'MRP').value = "";
            dynTableGetNodeInRow(elem, 'qtyService').value = "1";
            dynTableGetNodeInRow(elem, 'netAmount').value = "";
        }
        if (dynTableGetNodeInRow(elem, 'serviceName').value != serviceNameVar) {
            dynTableGetNodeInRow(elem, 'serviceName').value = "";
            dynTableGetNodeInRow(elem, 'serviceRID').value = "";
            dynTableGetNodeInRow(elem, 'qtyService').value = "1";
            dynTableGetNodeInRow(elem, 'MRP').value = "";
            dynTableGetNodeInRow(elem, 'netAmount').value = "";
        }
    }
    function autocomplete() {
        $("#" + id + " input[name='serviceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                serviceNameVar = ui.item.data.bsName;
//                autoIdSet(dynTableGetNodeInRow(this, 'serviceRID').value);
//                dynTableGetNodeInRow(this, 'serviceRID').value = ui.item.data.id;
//                dynTableGetNodeInRow(this, 'bdGroupRID').value = ui.item.data.bsServiceType;
//                dynTableGetNodeInRow(this, 'qtyService').value = 1;
//                var table = document.getElementById('dyn_table');
//                var table_length = table.rows.length;/
//                var y = table_length - 2;
//                for (var i = 0; i < table_length - 1; i++) {
                dynTableGetNodeInRow(this, 'serviceRID').value = ui.item.data.id;
                var sid = dynTableGetNodeInRow(this, 'serviceRID').value;
                if (parseInt(dcomethealth.commonInit) !== 0) {
                    dynTableGetNodeInRow(this, 'serReqItemPrice').value = ui.item.data.bPrice;
                }
                dynTableGetNodeInRow(this, 'bdGroupRID').value = ui.item.data.bsServiceType;
                dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bPrice;
                doctorChange();
//                    payerNameChange(0);
//                    $.each(dcomethealth.dDictVal, function (index, val) {
//                        if (parseInt(ui.item.data.bsServiceType) == parseInt(val.id)) {
//                            if (val.ddictValue == 'Radiology.') {
//                                dynTableGetNodeInRow(table.rows[y + 1], 'plus').className = 'hidden';
//                            }
//                        }
//                    });
//                }
                validateExistService(sid, dynTableGetNodeInRow(this));
                document.getElementById('DiscPercent').value = "";
                document.getElementById('DiscValue').value = "";
                document.getElementById('DiscName').value = "";
                document.getElementById('DiscType').value = "";
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1}; //"bsName": queryString, "bsServiceActive": 1 "q": queryString (item.bsCode || "") + (item.bsCode && item.bsName ? " - " : "") + (item.bsName || "")
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data) {
                        check = false;
                        response($.grep($.map(data, function (item) {
                            return {
                                label: item.bsName || "",
                                value: item.bsName || "",
                                name: (item.bsName || "") + (item.bsName && item.id ? " - " : "") + (item.id || ""),
                                data: item
                            };
                        }), function (item, index) {
                            return index < 10;
                        }));
                    } else {
                        if (check == true) {
                            $('#total_pmd').val('');
                            checkValidService();
                            return false;
                        }
                    }

                });
            },
            minLength: 1
        });
    }
    function checkValidService() {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == 0) {
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = "";
                dynTableGetNodeInRow(elem, 'qtyService').value = "1";
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = "";
            }
        }
    }
    function validateExistService(servRid, row) {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            if (parseInt(serviceRid) == parseInt(servRid)) {
                count++;
                if (count == 2) {
                    alert("Already existing sevice");
                    servRid = "";
                    delete_row(row);
                    dynTableGetNodeInRow(table.rows[1], 'plus').className = "ace-icon fa fa-plus";
                    calculation();
                    return false;
                }
            }
        }
    }
    function mrn_autocomplete() {
        $("#" + id + " input[name='mrnSearch']").autocomplete({
            select: function (event, ui) {
                var patMrnNo = ui.item.value;
                $('#patientRID').val(parseInt(ui.item.data.id));
                $('#PatientName').val(ui.item.data.patFullName);
                var c = ui.item.data.patDob.split('-');
                $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                var gen = '';
                if (parseInt(ui.item.data.patGenderIndex) === 1) {
                    gen = 'Male';
                } else if (parseInt(ui.item.data.patGenderIndex) === 2) {
                    gen = 'Female';
                } else {
                    gen = 'Transgender';
                }
                $('#PatientGender').val(gen);
                $('#patientMobile').val(ui.item.data.patPhoneNo);
                check = false;
                var search = {"visPatRid": $('#patientRID').val(), "visDate": moment().format("DD-MM-YYYY")};
                dcomethealth.PatientResource.searchVisit(search, function (data) {
                    if (!!data) {
                        check = true;
                        $.each(data, function (pfx, visits) {
                            $('#visitRID').val(visits.id);
                            $.each(dcomethealth.dDictVal, function (ddx, ddictValue) {
                                if (parseInt(ddictValue.id) == parseInt(visits.visReasonIndex)) {
                                    if (ddictValue.ddictValue = 'Emergency') {
                                        visitReason = 0;
                                    } else {
                                        visitReason = "Emergency";
                                    }
                                }
                            });
                        });
                    }
                });
                searchAdvance(ui.item.data.id);
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
                });
            },
            minLength: 1
        });
    }
    function saveBill(boRID, boCode, actionCode) {

        var serBoRid = 0, actionCheck = false, billNormalSave = 0;
        var totalPaid = 0.00, draftBillSave = false;
        var billDList = [], pmdList = [], receiptDList = [], serviceRequestsList = [], visitSerBillDetails = [];
        var billH = {};
        var receiptHList = [];
        var receiptH = {};
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        var serviceRequestH = {};
        var visit = {};
        //       for create visit     
        if (!!$('#visitRID').val()) {
            visit.id = $('#visitRID').val();
        }
        visit.visPatRid = $('#patientRID').val();
        if (!!$("#VDName option:selected").val()) {
            visit.visConsDocRid = $("#VDName option:selected").val();
        }
        if (parseInt(dcomethealth.bomtypeIndex) === 40) {
            if (!!boRID) {
                serviceRequestH.serReqhId = boRID;
            }
        }
        serviceRequestH.serReqhBillHRid = $("#billHRID").val();
        serviceRequestH.serReqhPatRid = $('#patientRID').val();
        serviceRequestH.serReqhPatName = $('#PatientName').val();
        serviceRequestH.serReqhPatMrn = $('#mrnSearch').val();
        if (!!$('#serReqhProcedureRid').val()) {
            serviceRequestH.serReqhProcedureRid = $('#serReqhProcedureRid').val();
        }
        if (!!$('#serReqhOpCheck').val()) {
            serviceRequestH.serReqhOpCheck = $('#serReqhOpCheck').val();
        }
        if (!!$('#visitRID').val()) {
            serviceRequestH.serReqOpVisitRid = $('#visitRID').val();
        }
        for (var i = 0; i < table_length - 1; i++) {
            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value != 0) {
                var billD = {};
                var serviceRequest = {};
                if ($("#billDRID").val() !== "") {
                    billD.id = dynTableGetNodeInRow(table.rows[i + 1], 'billDRID').value;
                    serviceRequest.serReqBillDRID = dynTableGetNodeInRow(table.rows[i + 1], 'billDRID').value;
                }
                billD.bdItemType = "Service";
                billD.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value !== "" && dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value != "undefined") {
                    billD.bdItemOrderRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value;
                }
                billD.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value != "undefined") {
                    billD.bdGroupRID = dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value;
                }
                billD.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
                billD.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                billD.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value * dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value !== "") {
                    billD.bdDiscountAmount = dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value;
                } else {
                    billD.bdDiscountAmount = 0;
                }
                billD.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'netAmount').value;
                billDList.push(billD);
                if (parseInt(dcomethealth.bomtypeIndex) == 40) {
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDRid').value != "undefined" && !!dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDRid').value) {
                        serviceRequest.serReqRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDRid').value;
                    }
                    serviceRequest.serReqItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
                    serviceRequest.serReqItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                    serviceRequest.serReqItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'serReqItemPrice').value;
                    serviceRequest.serReqItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
                }
                if ($("#billHRID").val() !== "") {
                    serviceRequest.serReqBillHRID = $("#billHRID").val();
                }
                serviceRequestsList.push(serviceRequest);
            }
        }
        serviceRequestH.serviceRequest = serviceRequestsList;
        var serviceNetAmount = document.getElementById('serviceNetAmount').value;
        var serviceDueAmount = document.getElementById('serviceDueAmount').value;
        var total_pmd = document.getElementById('total_pmd').value;
        if (total_pmd === "") {
            total_pmd = 0;
        }
        if (serviceNetAmount === "") {
            alert("Bill amount is empty");
            return false;
        } else if (parseFloat(serviceDueAmount) < parseFloat(total_pmd)) {
            alert("Due amount is lesser than total amount paid");
            return false;
        }
        if ($("#billHRID").val() !== "" && $("#billHRID").val() != 0) {
            billH.id = $("#billHRID").val();
        }
        if ($("#visitRID").val() !== "") {
            billH.bhPatientVisitRID = $("#visitRID").val();
        }
        if ($("#patientRID").val() !== "") {
            billH.bhPatientRID = $("#patientRID").val();
            receiptH.rhPayerRID = $("#patientRID").val();
        } else {
            alert("Patient not registered yet");
            return false;
        }
        billH.bhBillNo = $('#billNo').val();
        billH.bhPrefix = $('#bhPrefix').val();
        billH.bhPrintableBillNo = $('#bhPrintableBillNo').val();
        billH.bhPatientName = $("#PatientName").val();
        billH.bhPatientNo = $("#patientMobile").val();
        billH.bhDocRefRID = !!$("#VDName").val() ? $("#VDName").val() : 0;
        billH.bhType = $('#bhType').val() == "" ? 1 : $('#bhType').val(); // OP bill type : 1;
        if (!!$('#bhIsDraft').val()) {
            billH.bhIsDraft = $('#bhIsDraft').val();
        }
        billH.bhGrossAmount = $("#serviceGrossAmount").val();
        billH.bhTotalDiscountAmount = $("#serviceDiscountAmount").val();
        billH.bhNetAmount = $("#serviceNetAmount").val();
        billH.bhEligibleAmount = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
        billH.bhPayerAmount = !!$('#payerPaidAmount').val() ? $('#payerPaidAmount').val() : 0;

        billH.bhApprovalNumber = !!$("#bhApprovalNo").val() ? $("#bhApprovalNo").val() : 0;
//        billH.bhDiscount = $("#serviceDiscountAmount").val();
        billH.bhPayerType = $("#patRefType").val();
        billH.bhBaseCurRid = dcomethealth.sCurrencyRid;
        if (parseInt($("#patRefType").val()) != 31) {
            billH.bhPayerRID = $("#payerName").val();
        } else {
            billH.bhPayerRID = $("#patientRID").val();
        }
        billH.bhUnitRID = dcomethealth.selectedunit; // user selected unit;
        receiptH.rhPaidAmount = parseFloat(total_pmd);
        receiptH.rhTotalAmount = $("#serviceNetAmount").val();
        receiptH.rhEligibleAmt = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
        receiptH.rhPayerType = $("#patRefType").val();
        receiptH.rhBaseCurRID = dcomethealth.sCurrencyRid;
        if ($("#patRefType").val() != 31) {
            receiptH.rhPayerName = $("#payerName option:selected").text();
        } else {
            receiptH.rhPayerName = $("#PatientName").val();
            receiptH.rhPayerNo = $("#patientMobile").val();
        }
        var advanceList = [], receiptDList = [];
        if (!$("#viewAdvance").hasClass('hidden')) {
            var table = document.getElementById('adv_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var advance = {};
                var receiptDAdv = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value !== "") {
                    advance.id = dynTableGetNodeInRow(table.rows[i + 1], 'advanceRID').value;
                    advance.adType = 0;
                    advance.adRefRID = !!$("#billHRID").val() ? $("#billHRID").val() : null;
                    advance.adPayerRID = $("#patientRID").val();
                    advance.adPayerNo = $("#PatientMobile").val(); // Need to verify Mrn no or Patient No
                    advance.adPayerName = $("#PatientName").val();
                    advance.adAmount = dynTableGetNodeInRow(table.rows[i + 1], 'advanceAmt').value;
                    advance.adAdjustedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value;
                    advance.adBalanceAmount = dynTableGetNodeInRow(table.rows[i + 1], 'balanceAmt').value;
                    advance.adPaidAmount = dynTableGetNodeInRow(table.rows[i + 1], 'paidAmt').value;
                    advance.adRefundedAmount = 0.00;
                    receiptDAdv.rdAdvAdjustedAmount = dynTableGetNodeInRow(table.rows[i + 1], 'adjustedAmt').value;
                    receiptDAdv.rdBillAmount = $("#serviceNetAmount").val();
                    if ($("#total_pmd").val() !== "") {
                        receiptDAdv.rdPaidAmount = parseFloat($("#total_pmd").val());
                    } else {
                        receiptDAdv.rdPaidAmount = 0.00;
                    }
                    advanceList.push(advance);
                    receiptDList.push(receiptDAdv);
                }
            }
        }
        if (parseFloat(total_pmd) !== 0) {
            var table2 = document.getElementById('pmd_table');
            var table_length2 = table2.rows.length;
            for (var j = 0; j < table_length2; j++) {
                var pmd = {};
                var receiptDPmd = {};
                if (!!dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value && dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value != "undefined") {
                    pmd.pmdTransType = 3;
                    pmd.pmdPaymentMode = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_ddict').value;
                    pmd.pmdCardType = dynTableGetNodeInRow(table2.rows[j], 'cardtype').value;
                    pmd.pmdDocNo = dynTableGetNodeInRow(table2.rows[j], 'cheqno').value;
                    pmd.pmdDocDate = dynTableGetNodeInRow(table2.rows[j], 'cheqdate').value;
                    pmd.pmdDocExpDate = dynTableGetNodeInRow(table2.rows[j], 'expdate').value;
                    pmd.pmdDocApprovalNo = dynTableGetNodeInRow(table2.rows[j], 'approvalno').value;
                    pmd.pmdBankName = dynTableGetNodeInRow(table2.rows[j], 'bankname').value;
                    pmd.pmdBankDetails = dynTableGetNodeInRow(table2.rows[j], 'branchname').value;
                    pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                    pmd.pmdCurrencyRID = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_currency').value;
                    pmdList.push(pmd);
                    receiptDPmd.rdBillAmount = $("#serviceNetAmount").val();
                    receiptDPmd.rdBillRID = !!$("#billHRID").val() ? $("#billHRID").val() : null;
                    if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value !== "") {
                        pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                        receiptDPmd.rdPaidAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                    } else {
                        pmd.pmdAmount = 0.00;
                        receiptDPmd.rdPaidAmount = 0.00;
                    }
                    receiptDList.push(receiptDPmd);
                }
            }
            receiptH.receiptD = receiptDList;
            receiptH.paymentModeDetails = pmdList;
            receiptHList.push(receiptH);
        }
        if ($("#billHPaidAmt").val() !== "" && !!$("#billHPaidAmt").val() && $("#billHPaidAmt").val() != 'undefined') {
            billH.bhPaidAmount = parseFloat(totalPaid) + parseFloat(total_pmd) + parseFloat($("#billHPaidAmt").val());
        } else {
            billH.bhPaidAmount = parseFloat(totalPaid) + parseFloat(total_pmd);
        }
        billH.billD = billDList;
        receiptH.receiptD = receiptDList;
        receiptH.advanceDetails = advanceList;
        if (parseInt(dcomethealth.bomtypeIndex) === 40 && actionCode != 'CANCEL') {
            if (!!$("#billHRID").val() && $("#billHRID").val() != 0) {
                billNormalSave = 1;
                serBoRid = boRID;
                boRID = $("#billHRID").val();
                boCode = "BILL";
                actionCode = "REQUEST";
                actionCheck = false;
            } else {
                serBoRid = boRID;
                boRID = 0;
                boCode = "BILL";
                actionCheck = true;
                if ($('#serReqhOpCheck').val() > 0) {
                    actionCode = "REQUEST";
                } else {
                    actionCode = "BUILTIN_ACTION";
                }
            }
        }
        if (actionCode !== "CANCEL_BILL" && actionCode != 'CANCEL') {
            billH.receiptH = receiptHList;
        }
        billH.billD = billDList;
        if (actionCode == "COLLECT_PAYMENT" || actionCode == "BUILTIN_ACTION") {
            actionCheck = true;
        }
        if (!billHRidVar && $("#visitType").val() == "IP") {
            draftBillSave = true;
            actionCheck = false;
            var draftBillDList = [];
            var draftBillHReq = {};
            if ($("#patientRID").val() != "") {
                draftBillHReq.bhPatientRID = $("#patientRID").val();
            }
////             else {
////                alert("Patient Not Registered Yet");
////                return false;
////            }
            draftBillHReq.bhIsDraft = 1;
            draftBillHReq.bhPatientName = $("#PatientName").val();
            draftBillHReq.bhDocRefRID = $("#VDName").val();
            draftBillHReq.bhPatientNo = $("#patientMobile").val();
            draftBillHReq.bhType = 2;
            draftBillHReq.bhEligibleAmount = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
            draftBillHReq.bhApprovalNumber = !!$("#bhApprovalNo").val() ? $("#bhApprovalNo").val() : 0;
            draftBillHReq.bhGrossAmount = $("#serviceGrossAmount").val();
            draftBillHReq.bhTotalDiscountAmount = parseFloat($("#serviceDiscountAmount").val());
//            draftBillHReq.bhTotalTaxAmount = $("#retailTaxAmount").val();
            draftBillHReq.bhNetAmount = $("#serviceNetAmount").val();
            draftBillHReq.bhUnitRID = dcomethealth.selectedunit;
//            draftBillHReq.bhDiscount = $("#serviceDiscountAmount").val();
            draftBillHReq.bhPayerType = $("#patRefType").val();
            if (parseInt($("#patRefType").val()) != 31) {
                draftBillHReq.bhPayerRID = $("#payerName").val();
            } else {
                draftBillHReq.bhPayerRID = $("#patientRID").val();
            }
            if ($("#visitRID").val() !== "" && !!$("#visitRID").val()) {
                draftBillHReq.bhPatientVisitRID = $("#visitRID").val();
            }
            draftBillHReq.bhPaidAmount = 0;
            var table = document.getElementById('dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var billDReqObj = {};
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value != 0) {
                    if ($("#billDRID").val() !== "") {
                        billDReqObj.id = dynTableGetNodeInRow(table.rows[i + 1], 'billDRID').value;
                    }
                    billDReqObj.bdItemType = "Service";
                    billDReqObj.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value !== "" && dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value != "undefined") {
                        billDReqObj.bdItemOrderRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceOrderRID').value;
                    }
                    billDReqObj.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value != "undefined") {
                        billDReqObj.bdGroupRID = dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value;
                    }
                    billDReqObj.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
                    billDReqObj.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                    billDReqObj.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value * dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value !== "") {
                        billDReqObj.bdDiscountAmount = dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value;
                    } else {
                        billDReqObj.bdDiscountAmount = 0;
                    }
                    billDReqObj.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'netAmount').value;
                    draftBillDList.push(billDReqObj);
                }
            }
            draftBillHReq.billD = draftBillDList;
            var args = 0 + "/" + "BILL" + "/" + "REQUEST";

            dcomethealth.BillingResource.saveBill(draftBillHReq, args)
                    .done(function (data, textStatus, jqXHR) {
                        serviceRequestH.serReqhBillHRid = data.id;
                        var args = dcomethealth.boRID + "/" + "SERVICE_ORDER_REQ" + "/" + actionCode;
                        dcomethealth.ServiceRequestResource.saveServiceRequest(serviceRequestH, args).done(function (data, textStatus, jqXHR) {
                            alert("Saved");
                            dcomethealth.util.loadpage('CreateOPBill');
                            dcomethealth.util.base_init();
                            dcomethealth.util.loadNotification();
//                            dcomethealth.util.loadpage('ProcedureRequest');
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                        });
                    }).fail(function (jqXHR, textStatus, errorThrown) {
//                alert("Failed");
            });
        }


        if (actionCheck && !draftBillSave) {
            var args = boRID + "/" + boCode + "/" + actionCode;
            dcomethealth.BillingResource.saveBill(billH, args)
                    .done(function (data, textStatus, jqXHR) {
                        alert("Saved");
                        dcomethealth.util.loadpage('CreateOPBill');
                        dcomethealth.util.base_init();
                        dcomethealth.util.loadNotification();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        } else if (billNormalSave == 1 && !draftBillSave) {
            if (actionCode != 'CANCEL') {
                dcomethealth.BillingResource.saveDraftBill(billH)
                        .done(function (data, textStatus, jqXHR) {
                            if (!!data) {
                                $.each(data.billD, function (inx, billD) {
                                    $.each(serviceRequestH.serviceRequest, function (indx, serReqD) {
                                        if (billD.bdItemRID == serReqD.serReqItemRID) {
                                            serReqD.serReqBillDRID = billD.id;
                                        }
                                    });
                                });
                                actionCode = (actionCode == "CANCEL") ? actionCode : "CREATE";
                                serBoRid = (actionCode == "CANCEL") ? boRID : serBoRid;
                                var args = serBoRid + "/" + "SERVICE_ORDER_REQ" + "/" + actionCode;
                                dcomethealth.ServiceRequestResource.saveServiceRequest(serviceRequestH, args).done(function (data, textStatus, jqXHR) {
                                    if (actionCode == "CANCEL") {
                                        alert("Cancelled");
                                    }
                                    dcomethealth.util.loadpage('CreateOPBill');
                                    dcomethealth.util.base_init();
                                    dcomethealth.util.loadNotification();
//                            dcomethealth.util.loadpage('ProcedureRequest');

                                }).fail(function (jqXHR, textStatus, Patients) {
                                });
                            }
                            alert("Saved");
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
            } else {
                if (!draftBillSave) {
                    actionCode = (actionCode == "CANCEL") ? actionCode : "CREATE";
                    serBoRid = (actionCode == "CANCEL") ? boRID : serBoRid;
                    var args = serBoRid + "/" + "SERVICE_ORDER_REQ" + "/" + actionCode;
                    dcomethealth.ServiceRequestResource.saveServiceRequest(serviceRequestH, args).done(function (data, textStatus, jqXHR) {
                        if (actionCode == "CANCEL") {
                            alert("Cancelled");
                        }
                        dcomethealth.util.base_init();
                        dcomethealth.util.loadNotification();
                        dcomethealth.util.loadpage('CreateOpBill');
//                        dcomethealth.util.loadpage('ProcedureRequest');
                    }).fail(function (jqXHR, textStatus, Patients) {
                    });
                }
            }
        }
        if (parseInt(dcomethealth.bomtypeIndex) == 40 && billNormalSave != 1 && !draftBillSave) {
            actionCode = (actionCode == "CANCEL") ? actionCode : "CREATE";
            serBoRid = (actionCode == "CANCEL") ? boRID : serBoRid;
            var args = serBoRid + "/" + "SERVICE_ORDER_REQ" + "/" + actionCode;
            dcomethealth.ServiceRequestResource.saveServiceRequest(serviceRequestH, args).done(function (data, textStatus, jqXHR) {
                if (actionCode == "CANCEL") {
                    alert("Cancelled");
                }
                dcomethealth.util.base_init();
                dcomethealth.util.loadNotification();
                dcomethealth.util.loadpage('CreateOPBill');
            }).fail(function (jqXHR, textStatus, Patients) {
            });
        }
    }
    function refreshData() {
    }
    return {
        parameters: parameters,
        autoIdSet: autoIdSet,
        doctorChange: doctorChange,
        init: init,
        fetchDetails: fetchDetails,
        autocomplete: autocomplete,
        payerChange: payerChange,
        loadWorkList: loadWorkList,
        clearPayerValues: clearPayerValues,
        payerNameChange: payerNameChange,
        mrn_autocomplete: mrn_autocomplete,
        validateExistService: validateExistService,
        saveBill: saveBill,
        refreshData: refreshData
    };
}
());
dcomethealth.CreateOPBill.init();