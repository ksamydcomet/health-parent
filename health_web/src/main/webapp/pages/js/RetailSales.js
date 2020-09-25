var dcomethealth = dcomethealth || {}, currencyExchange = {};
var payerData = {};
dcomethealth.RetailSales = (function () {
    var id = "RetailSales", act = 0, isNotExistVisit = false, visitCheck = false, checkQtyList = [], serviceClearVar = 0, skuNameVar = "", billHRidVar = false;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autocomplete();
            mrn_autocomplete();
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
            $("select.dcomet-c-s_ddict-list", $("#" + id)).each(function (idx, elem) {
                $.each(dcomethealth.sDdict, function (index, s_ddict) {
                    for (var i = 0; i < s_ddict.length; i++) {
                        if (s_ddict[i].dditCode == "GENDER") {
                            for (var j = 0; j < s_ddict[i].ddict.length; j++) {
                                $(elem).append('<option value="' + s_ddict[i].ddict[j].id + '">' + s_ddict[i].ddict[j].ddictValue + '</option>');
                            }
                        }
                    }
                });
            });
            var searchParams = {"staffCategory": 237};
            var options = '';
            dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
                $.each(data, function (idx, Val) {
                    options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                });
                $("#VDName").html(options);
            });
            if (parseInt(dcomethealth.commonInit) != 0) {
                $("#discountHeader,#viewDiscount,#billInformation,#doctorName,#paymentDiv").addClass("hidden");
                $("#h4Header").text("Material Issue");
                $("#btns_state").empty();
                var categories = [];
                var btnNumber = 0;
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    pIdx++;
                    var div = $("#btns_state");
                    if ($.inArray(datalist.boaName, categories) === -1) {
                        categories.push(datalist.boaName);
                        if (datalist.boaName != "PARTIALLY_ISSUE") {
                            div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>');
                        }
                        var boCode = "";
                        btnName = datalist.boaName;
                        btnNumber++;
                        if ((btnName == 'PARTIALLY_ISSUE' && btnNumber == 1) || (btnName == 'ISSUE' && btnNumber == 2) || (btnNumber == 3 && btnName == 'CANCEL')) {//Need close button function ISSUE page arguments
                            boCode = "ISSUE";
                        }
                        if (dcomethealth.actionDatalist.length == pIdx) {
                            div.append('<button type="button" id="closeBtn" class="btn btn-primary jqueryUIToolTip" title="Close" onclick="dcomethealth.RetailSales.loadWorkList(\'' + boCode + '\')"><i class="fa fa-times" aria-hidden="true"></i></button>');
                        }
                    }
                });
                var boRID = dcomethealth.boRID;
                $("#retailTody").empty();
                $("#issuedDynTody").empty();
                $("#iQty").removeClass("hidden");
                var searchObj = {"id": boRID};
                dcomethealth.BillingResource.searchMaterialRequestByBill(searchObj, function (data) {
                    $.each(data, function (pIdx, data) {
                        $("#visitRID").val(data.drugReqHOpVBisitRID);
                        $('#billHRID').val(data.drugReqHBillHRID);
                        if (!!data.drugReqHBillHRID && data.drugReqHBillHRID != 0) {
                            $('#billHRID').val(data.drugReqHBillHRID);
                            var searchObj = {"bhPatientVisitRID": data.drugReqHOpVBisitRID};
                            dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
                                if (!!data && data.length > 0) {
                                    billHRidVar = true;
                                    $("#billHRID").val(data[0].billH.id);
                                    $("#billNo").val(data[0].billH.bhBillNo);
                                    $("#bhPrefix").val(data[0].billH.bhPrefix);
                                    $("#bhPrintableBillNo").val(data[0].billH.bhPrintableBillNo);
                                    $("#bhDraftBillNo").val(data[0].billH.bhDraftBillNo);
                                    $("#bhIsDraft").val(data[0].billH.bhIsDraft);
                                }
                            });
                        }
                        if (data.drugReqHOpVBisitRID != 0 && !!data.drugReqHOpVBisitRID) {
                            $('#visitRID').val(data.drugReqHOpVBisitRID);
                            if (data.drugReqHVisitType == 19) {
                                $('#visitType').val("IP");
                            } else if (data.drugReqHVisitType == 20) {
                                $('#visitType').val("OP");
                            }
                        } else {
                            isNotExistVisit = true;
                        }
                        $("#patientRID").val(data.drugReqHPatRid);
                        $("#drugReqHId").val(data.id);
                        $("#drugReqHOpCheck").val(data.drugReqHOpCheck);
                        $("#drugReqHProcedureRid").val(data.drugReqHProcedureRid);
                        dcomethealth.PatientResource.searchPatient({"id": data.drugReqHPatRid}, function (dataPat) {
                            $.each(dataPat, function (Idx, patient) {
                                $('#PatientGender option[value="' + patient.patGenderIndex + '"]').prop('selected', true);
                                $('#mrnSearch').val(patient.patMrnNo);
                                $('#mrnSearch').attr("readonly", true);
                                $('#PatientName').val(patient.patFullName);
                                if (patient.patDob != undefined) {
                                    var c = patient.patDob.split('-');
                                    $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                                }
                                $('#PatientMobile').val(patient.patPhoneNo);
                            });
                        });
                        $("#reqQty").text("Requested Qty");
                        $("#disc_head").addClass("hidden");
                        $("#tax_head").addClass("hidden");
                        $("#issuedDiv").removeClass("hidden");
                        var disabled = "", opBatch = "", hidden = "", styleAttr = "", countNo = 0;
                        $("#retailTody").append('<tr style=""><td width="1%"><i id="del_saleR" class="dct-icon fa fa-minus-circle" onclick="delete_row(this)"></i></td>\n\
                        <td width="25%"><input class="col-md-11 col-sm-12 col-xs-12" type="text" id="skuName"  name="skuName" value="" placeholder="Item Name" onchange="dcomethealth.RetailSales.autoIdSet(this)"/>\n\
                        <input type="hidden" id="drugDId" name="drugDId" value=""/><input type="hidden" id="packageItemRid" name="PackageItemRid" value="0"><input type="hidden" id="skuRID" name="skuRID" value=""/><input type="hidden" id="drugReqDRid" name="drugReqDRid" value=""/>\n\
                        <input type="hidden" id="drugReqDItemRID" name="drugReqDItemRID" value=""/><input type="hidden" id="drugReqDbillDRid" name="drugReqDbillDRid" value=""/></td>\n\
                        <td width="8%"><select class="col-md-12 col-sm-12 col-xs-12 " id="batchNo" name="batchNo" onchange="dcomethealth.RetailSales.onBatchChange(this)"  selected></select></td>\n\
                        <td width="10%"><select class="col-md-12 col-sm-12 col-xs-12 " id="expDate" name="expDate" onkeypress="return dcomethealth.validation.validateDob(event)" onchange="dcomethealth.RetailSales.onExpiryChange(this)" ></select></td>\n\
                        <td  width="9%"><select class="col-md-12 col-sm-12 col-xs-12 " id="MRP" name="MRP" onchange="dcomethealth.RetailSales.onMrpChange(this)" ></select></td>\n\
                        <td width="8%"><input class="col-md-12 col-sm-12 col-xs-12 " id="Stkqty" readonly name="Stkqty" onchange="calculation(this)"  style="text-align:right"/></td>\n\
                        <td width="7%"><input class="col-md-12 col-sm-12 col-xs-12 " id="qty"  type="text" value="" name="qty" maxlength="9" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" autocomplete="off" onBlur="calculation(this)"  style="text-align:right"></td>\n\
                        <td width="8%"><input class="jqueryUIToolTip col-md-12 col-sm-12 col-xs-12" title="Already Issued 0" id="issueQty" name="issueQty" onchange="calculation(this)" onkeydown="tabKeyPress(this, \'dyn_table\', event)" value="0" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" maxlength="6"  style="text-align:right"/><input type="hidden" id="issuedQty" value=""></td>\n\
                        <td class="hidden" width="7%"><input class="col-md-12 col-sm-12 col-xs-12" id="salDiscount"  type="text" name="salDiscount" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" autocomplete="off" onBlur="calculation(this)"></td>\n\
                        <td class="hidden" width="7%"><input class="col-md-12 col-sm-12 col-xs-12" id="retailTax"  type="text" name="retailTax" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" autocomplete="off" onBlur="calculation(this)"></td>\n\
                        <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12 " type="text" id="net_amount" name="net_amount" readonly  style="text-align:right"/></td><td width="1%"><i id="plus" class="dct-icon fa fa-plus-square" onclick="insert_row(\'dyn_table\', this,0)" ></i></td></tr>');
                        $.each(data.drugRequestDList, function (cIdx, child) {
                            var optBatch = '<option value="0">-Select-</option>';
                            var optExpiry = '<option value="0">-Select-</option>';
                            var optMrp = '<option value="0">-Select-</option>';
                            var searchParams = {"stkSkuRID": child.drugReqDItemRID};
                            dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {                               
                                if (!!data) {
                                    $.each(data, function (ifx, item) {
                                        if (!!item.stkBatchNo) {
                                            optBatch += '<option value="' + item.stkBatchNo + '">' + item.stkBatchNo + '</option>';
                                        }
                                    });
                                }
                            });
                            setTimeout(function () {
                                if (child.drugReqDItemQty == child.drugReqDItemIssuedQty) {
                                    countNo++;
                                    $("#issuedDynTody").append('<tr><td>' + countNo + '</td><td><span id="itemName" class="col-md-11 col-sm-12 col-xs-12">' + child.drugReqDItemName + '</span></td><td><span id="itemIssuedQty" class="col-md-11 col-sm-12 col-xs-12">' + child.drugReqDItemIssuedQty + '</span></td></tr>');
                                } else {
                                    $("#retailTody").append('<tr style=""><td width="1%"><i id="del_saleR" class="dct-icon fa fa-minus-circle hidden" onclick="delete_row(this)"></i></td>\n\
                                <td width="25%"><input class="col-md-11 col-sm-12 col-xs-12" type="text" id="skuName" readonly name="skuName" value="' + child.drugReqDItemName + '" placeholder="Item Name" disabled onchange="dcomethealth.RetailSales.autoIdSet(this)"/>\n\
                                <input type="hidden" id="drugDId" name="drugDId" value="' + child.id + '"/><input type="hidden" id="packageItemRid" name="packageItemRid" value="' + child.drugReqDItemPackageRid + '"><input type="hidden" id="skuRID" name="skuRID" value="' + child.drugReqDItemRID + '"/><input type="hidden" id="drugReqDRid" name="drugReqDRid" value="' + child.druReqDSrhRID + '"/>\n\
                                <input type="hidden" id="drugReqDItemRID" name="drugReqDItemRID" value="' + child.drugReqDItemRID + '"/><input type="hidden" id="drugReqDbillDRid" name="drugReqDbillDRid" value="' + child.drugReqDBillDRID + '"/></td>\n\
                                <td width="8%"><select class="col-md-12 col-sm-12 col-xs-12 " id="batchNo" name="batchNo" onchange="dcomethealth.RetailSales.onBatchChange(this)" ' + disabled + ' selected>' + optBatch + '</select></td>\n\
                                <td width="10%"><select class="col-md-12 col-sm-12 col-xs-12 " id="expDate" name="expDate" onkeypress="return dcomethealth.validation.validateDob(event)" onchange="dcomethealth.RetailSales.onExpiryChange(this)" ' + disabled + '>' + optExpiry + '</select></td>\n\
                                <td  width="9%"><select class="col-md-12 col-sm-12 col-xs-12 " id="MRP" name="MRP" onchange="dcomethealth.RetailSales.onMrpChange(this)" >' + optMrp + '</select></td>\n\
                                <td width="8%"><input class="col-md-12 col-sm-12 col-xs-12 " id="Stkqty" readonly name="Stkqty" onchange="calculation(this)"  style="text-align:right"/></td>\n\
                                <td width="7%"><input class="col-md-12 col-sm-12 col-xs-12 " id="qty" readonly type="text" value="' + child.drugReqDItemQty + '" name="qty" maxlength="9" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" autocomplete="off" onBlur="calculation(this)"  style="text-align:right"></td>\n\
                                <td width="8%"><input class="jqueryUIToolTip col-md-12 col-sm-12 col-xs-12" title="Already Issued ' + child.drugReqDItemIssuedQty + '" id="issueQty" name="issueQty" onchange="calculation(this)" onkeydown="tabKeyPress(this, \'dyn_table\', event)" value="0" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" maxlength="6"  style="text-align:right"/><input type="hidden" id="issuedQty" value="' + child.drugReqDItemIssuedQty + '"></td>\n\
                                <td class="hidden" width="7%"><input class="col-md-12 col-sm-12 col-xs-12" id="salDiscount" readonly type="text" name="salDiscount" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" autocomplete="off" onBlur="calculation(this)"></td>\n\
                                <td class="hidden" width="7%"><input class="col-md-12 col-sm-12 col-xs-12" id="retailTax" readonly type="text" name="retailTax" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" autocomplete="off" onBlur="calculation(this)"></td>\n\
                                <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12 " type="text" id="net_amount" name="net_amount" readonly="readonly"  style="text-align:right"/></td><td width="1%"><i id="plus" class="" onclick="insert_row(\'dyn_table\', this,0)" ></i></td></tr>');
                                }
                                dcomethealth.datatypes.init($("#" + id));
                            }, 500);
                            autocomplete();
                        });
                    });
                });
            }
            $("#viewIssue").on("click", function () {
                var table = document.getElementById('issuedDynTable');
                var table_length = table.rows.length;
                if (table_length > 1) {
                } else {
                    alert("No item is available");
                    return false;
                }
                if ($("#issuedDynTable").hasClass('hidden')) {
                    $("#issuedDynTable").removeClass("hidden");
                    $("#viewIssue").text("Hide issued item");
                } else {
                    $("#issuedDynTable").addClass("hidden");
                    $("#viewIssue").text("Click issued item");
                }
            });
            $(".btn-primary").off("click").on("click", function () {
                if ($("#patRefType").val() != 31) {
                    if (parseInt($("#payerName").val()) == 0) {
                        alert("Select Payer");
                        $(".btn-primary").attr("disabled", false);
                        return false;
                    }
                    if (parseInt($("#payerName option:selected").val()) > 0) {
                        if (!$("#bhEligibleAmount").val()) {
                            alert("Enter Eligible Amount");
                            $(".btn-primary").attr("disabled", false);
                            return  false;
                        }
                        if (!$("#bhApprovalNo").val()) {
                            alert("Enter Approval Number");
                            $(".btn-primary").attr("disabled", false);
                            return  false;
                        }
                    }
                }
                var actionBtn = this.id;
                var checkIssueQty = false, checkStockQty = false;
                if ($('#mrnSearch').val() != "") {
                    var table = document.getElementById('dyn_table');
                    var table_length = table.rows.length;
                    var lngthTble = 0;
                    for (var i = 0; i < table_length - 1; i++) {
                        var itemname = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                        var acc_qty_row = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                        var stk_qty = dynTableGetNodeInRow(table.rows[i + 1], 'Stkqty').value;
                        var issueQty = dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value;
                        var batchNo = dynTableGetNodeInRow(table.rows[i + 1], 'batchNo').value;
                        if (parseInt(dcomethealth.commonInit) == 0) {
                            var MRP = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                            if (itemname == "") {
                                alert("Enter item name");
                                setTimeout(function () {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'skuName').focus();
                                }, 1);
                                return false;
                            } else if (acc_qty_row == "" || parseInt(acc_qty_row) == 0) {
                                alert("Enter qty");
                                setTimeout(function () {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'qty').focus();
                                }, 1);
                                return false;
                            } else if (MRP == "" || parseInt(MRP) == 0) {
                                alert("Enter mrp");
                                setTimeout(function () {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').focus();
                                }, 1);
                                return false;
                            } else if (parseInt(acc_qty_row) > parseInt(stk_qty)) {
                                alert("Stock qty is less");
                                setTimeout(function () {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'qty').focus();
                                }, 1);
                                return false;
                            }
                        } else {
                            if (!stk_qty) {
                                stk_qty = 0;
                            }
                            if ((!!issueQty && issueQty != 0) && parseInt(stk_qty) == 0) {
                                alert("Stock qty is less");
                                dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value = "";
                                setTimeout(function () {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').focus();
                                }, 1);
                                return false;
                            }
                            if ((!!issueQty && issueQty != 0)) {
                                checkIssueQty = true;
                            }
                            if ((!!stk_qty && stk_qty != 0)) {
                                checkStockQty = true;
                            }
                            if (parseInt(dcomethealth.bomtypeIndex) == 47) {
                                var issueQty = dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value;
                                var issuedQty = dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value;
                                if (!issueQty) {
                                    issueQty = 0;
                                }
                                if (!issuedQty || isNaN(issuedQty)) {
                                    issuedQty = 0;
                                }
                                var totQty = parseInt(issuedQty) + parseInt(issueQty);
                                if (parseInt(acc_qty_row) > totQty) {
                                    act = 1;
                                } else if (parseInt(acc_qty_row) < totQty) {
                                    alert("Requested qty is less");
                                    dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value = "";
                                    setTimeout(function () {
                                        dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').focus();
                                    }, 1);
                                    return false;
                                }
                            }
                            if ((!!dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value != 0) && (!!dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value && dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value != 0) && (!!dynTableGetNodeInRow(table.rows[i + 1], 'Stkqty').value && dynTableGetNodeInRow(table.rows[i + 1], 'Stkqty').value != 0)) {
                                lngthTble++;
                                var checkQtyObj = {};
                                var MRP = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                                if (batchNo == "" || parseInt(batchNo) == 0) {
                                    alert("Select batch no.");
                                    setTimeout(function () {
                                        dynTableGetNodeInRow(table.rows[i + 1], 'batchNo').focus();
                                    }, 1);
                                    return false;
                                }
                                if (acc_qty_row == "" || parseInt(acc_qty_row) == 0) {
                                    alert("Enter qty");
                                    setTimeout(function () {
                                        dynTableGetNodeInRow(table.rows[i + 1], 'qty').focus();
                                    }, 1);
                                    return false;
                                }
                                if (MRP == "" || parseInt(MRP) == 0) {
                                    alert("Enter mrp");
                                    setTimeout(function () {
                                        dynTableGetNodeInRow(table.rows[i + 1], 'MRP').focus();
                                    }, 1);
                                    return false;
                                }
                                if (issueQty == "" || parseInt(issueQty) == 0) {
                                    alert("Enter issue qty");
                                    setTimeout(function () {
                                        dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').focus();
                                    }, 1);
                                    return false;
                                }
                                checkQtyObj["ItemRid"] = dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value;
                                checkQtyObj["ItemName"] = itemname;
                                checkQtyList.push(checkQtyObj);
                            }
                        }
                    }
                    if (parseInt(act) == 1) {
                        act = 'PARTIALLY_ISSUE'
                    } else {
                        act = 'ISSUE'
                    }
                    if (this.id == "CANCEL") {
                        act = "CANCEL";
                    }
                    if ($("#h4Header").text() != "Retail Sales") {
                        if (!checkIssueQty || !checkStockQty) {
                            alert("Enter issue / stock Qty");
                            return false;
                        }
                    }
                } else {
                    alert("Enter uhid no.");
                    return false;
                }
                if (dcomethealth.actionDatalist == 0) {
                    if (visitCheck == false && dcomethealth.commonInit == 0 || isNotExistVisit) {
                        saveVisit(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                    } else {
                        saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                    }
                } else if (dcomethealth.actionDatalist != 0 && $('#total_pmd').val() == "" && actionBtn != "CANCEL_BILL" && parseInt(dcomethealth.bomtypeIndex) != 47) {
                    alert("Payment is empty");
                    return false;
                } else if (parseInt(dcomethealth.bomtypeIndex) === 47) {
                    if (visitCheck == false && dcomethealth.commonInit == 0 || isNotExistVisit) {
                        saveVisit(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                    } else {
                        saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                    }
                } else {
                    var i = 1;
                    $.each(dcomethealth.actionDatalist, function (pIdx, action) {
                        if (action.boaCode == actionBtn) {
                            if (i == 1) {
                                dcomethealth.NotificationResource.searchMasterCode(action.boaBomTypeIndex, function (data) {
                                    if (visitCheck == false && dcomethealth.commonInit == 0 || isNotExistVisit) {
                                        saveVisit(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                                    } else {
                                        saveBill(dcomethealth.boRID, "BILL", "BUILTIN_ACTION");
                                    }
                                });
                                i++;
                            }
                        }
                    });
                }
            });
        });
    }
    function loadWorkList(actionCode) {
        if (actionCode == "ISSUE") {
            dcomethealth.util.loadWorklistByCode('PENDING_MATERIAL_REQUESTS_TO_ISSUE');
        }
    }
    function doctorChange() {
        var check = false;
        if ($("#patRefType option:selected").val() == 31) {
            var searchObj = {"rsmDocRid": $("#VDName option:selected").val()};
            dcomethealth.MasterResource.searchResourceServiceMap(searchObj, function (data) {
                if (!!data) {
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
//                    payerChange();
                }
            });
        } else {
            payerNameChange(0);
//            payerChange();
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
        }
        else {
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
    function mrn_autocomplete() {
        $("#" + id + " input[name='mrnSearch']").autocomplete({
            select: function (event, ui) {
                var patMrnNo = ui.item.value;
                $('#patientRID').val(parseInt(ui.item.data.id));
                $('#PatientName').val(ui.item.data.patFullName);
                var c = ui.item.data.patDob.split('-');
                $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                $('#PatientGender option[value="' + ui.item.data.patGenderIndex + '"]').prop('selected', true);
                $('#PatientMobile').val(ui.item.data.patPhoneNo);
                visitCheck = false;
                var search = {"visPatRid": $('#patientRID').val(), "visDate": moment().format("DD-MM-YYYY")};
                dcomethealth.PatientResource.searchActiveVisit(search, function (data) {
                    if (!!data) {
                        visitCheck = true;
                        $('#visitRID').val(data.id);
                        $.each(dcomethealth.dDictVal, function (ddx, ddictValue) {
                            if (parseInt(ddictValue.id) == parseInt(data.visReasonIndex)) {
                                if (ddictValue.ddictValue == 'Emergency') {
                                    visitReason = "Emergency";
                                } else {
                                    visitReason = 0;
                                }
                            }
                        });
                    }
                });
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
    function setFocusToTextBox(row) {
        dynTableGetNodeInRow(row, 'skuName').focus();
    }
    function autocomplete() {
        var skuName;
        $("#" + id + " input[name='skuName']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                skuNameVar = ui.item.value;
                serviceClearVar = 1;
                var Auto = this;
                dynTableGetNodeInRow(this, 'skuRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'batchNo').innerHTML = "";
                dynTableGetNodeInRow(this, 'expDate').innerHTML = "";
                dynTableGetNodeInRow(this, 'MRP').innerHTML = "";
//                dynTableGetNodeInRow(this, 'batchNo').innerHTML = "--Batch No--";
                var option1 = document.createElement("option");
                option1.value = "";
                option1.text = "--Select--";
//                dynTableGetNodeInRow(this, 'batchNo').options.remove();
                dynTableGetNodeInRow(this, 'batchNo').options.add(option1);
                dynTableGetNodeInRow(this, 'expDate').innerHTML = "--Exp Date--";
                dynTableGetNodeInRow(this, 'MRP').innerHTML = "--MRP--";
                dynTableGetNodeInRow(this, 'Stkqty').innerHTML = "";
                var searchParams = {"stkSkuName": skuName};
                dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
                    if (data != undefined) {
                        $.grep($.map(data, function (item) {
                            item.id = item.stkBatchNo;
                            var opt = document.createElement("option");
                            opt.value = item.stkBatchNo;
                            opt.text = item.stkBatchNo;
                            var opt1 = document.createElement("option");
                            opt1.value = item.stkExpiryDate;
                            opt1.text = item.stkExpiryDate;
                            var opt2 = document.createElement("option");
                            opt2.value = item.stkMrp;
                            opt2.text = item.stkMrp;
                            dynTableGetNodeInRow(Auto, 'skuRID').value = item.stkSkuRID;
                            dynTableGetNodeInRow(Auto, 'batchNo').options.add(opt);
                        }), function (item, index) {
                            return index < 75;
                        });
                    } else {
                        alert("Out of stock");
                        return false;
                    }
                    dynTableGetNodeInRow(Auto, 'salDiscount').value = "";
                    dynTableGetNodeInRow(Auto, 'retailTax').value = "";
                    dynTableGetNodeInRow(Auto, 'net_amount').value = "";
                });
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
                var searchParams = {"skuName": queryString, "skuIsConsumable": 1};
                dcomethealth.MasterResource.searchSkus(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: item.skuName || "",
                            value: item.skuName || "",
                            name: (item.skuName || "") + (item.skuName && item.id ? " - " : "") + (item.id || ""),
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
    function onBatchChange(elem) {
        var skuRID = dynTableGetNodeInRow(elem, 'skuRID').value;
        var batchNo = dynTableGetNodeInRow(elem, 'batchNo').value;
        dynTableGetNodeInRow(elem, 'expDate').innerHTML = "";
        dynTableGetNodeInRow(elem, 'MRP').innerHTML = "";
        dynTableGetNodeInRow(elem, 'Stkqty').value = "";
        if (!!batchNo) {
            var searchParams = {"stkSkuRID": skuRID, "stkBatchNo": batchNo};
            dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
                if (!!data) {
                    dynTableGetNodeInRow(elem, 'issueQty').value = "";
                    dynTableGetNodeInRow(elem, 'net_amount').value = "";
                    $.grep($.map(data, function (item) {
                        item.id = item.stkBatchNo;
                        var opt1 = document.createElement("option");
                        opt1.value = item.stkExpiryDate;
                        opt1.text = item.stkExpiryDate;
                        var opt2 = document.createElement("option");
                        opt2.value = item.stkMrp;
                        opt2.text = item.stkMrp;
                        dynTableGetNodeInRow(elem, 'expDate').options.add(opt1);
                        dynTableGetNodeInRow(elem, 'MRP').options.add(opt2);
                        $.each(item.stockDChildList, function (pIdx, stockD) {
                            dynTableGetNodeInRow(elem, 'Stkqty').value = stockD.stkdQty;
                            var qty1 = dynTableGetNodeInRow(elem, 'qty').value - dynTableGetNodeInRow(elem, 'issuedQty').value;
                            if (dynTableGetNodeInRow(elem, 'Stkqty').value > qty1 || dynTableGetNodeInRow(elem, 'Stkqty').value == qty1) {
                                dynTableGetNodeInRow(elem, 'issueQty').value = qty1;
                            } else if (dynTableGetNodeInRow(elem, 'Stkqty').value < qty1) {
                                dynTableGetNodeInRow(elem, 'issueQty').value = dynTableGetNodeInRow(elem, 'Stkqty').value;
                            }
                            calculation(elem);
                        });
                    }), function (item, index) {
                        return index < 75;
                    });
                } else {
                    dynTableGetNodeInRow(elem, 'issueQty').value = "";
                    dynTableGetNodeInRow(elem, 'net_amount').value = "";
                }
            });
        } else {
            dynTableGetNodeInRow(elem, 'expDate').innerHTML = "";
            dynTableGetNodeInRow(elem, 'MRP').innerHTML = "";
            dynTableGetNodeInRow(elem, 'Stkqty').value = "";
            dynTableGetNodeInRow(elem, 'qty').value = "";
            dynTableGetNodeInRow(elem, 'issueQty').value = "";
            dynTableGetNodeInRow(elem, 'net_amount').value = "";
        }
    }
    function autoIdSet(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'skuRID').value = "";
            dynTableGetNodeInRow(elem, 'skuName').value = "";
            dynTableGetNodeInRow(elem, 'MRP').value = "";
            dynTableGetNodeInRow(elem, 'qty').value = "";
            dynTableGetNodeInRow(elem, 'net_amount').value = "";
        }
        if (dynTableGetNodeInRow(elem, 'skuName').value != skuNameVar) {
            dynTableGetNodeInRow(elem, 'skuName').value = "";
            dynTableGetNodeInRow(elem, 'skuRID').value = "";
            dynTableGetNodeInRow(elem, 'batchNo').innerHTML = "";
            dynTableGetNodeInRow(elem, 'expDate').innerHTML = "";
            dynTableGetNodeInRow(elem, 'Stkqty').value = "";
            if (parseInt(dcomethealth.commonInit) == 1) {
                dynTableGetNodeInRow(elem, 'issueQty').value = "";
            }
            dynTableGetNodeInRow(elem, 'salDiscount').value = "";
            dynTableGetNodeInRow(elem, 'retailTax').value = "";
            dynTableGetNodeInRow(elem, 'MRP').value = "";
            dynTableGetNodeInRow(elem, 'qty').value = "";
            dynTableGetNodeInRow(elem, 'net_amount').value = "";
        }
    }
    function onExpiryChange(elem) {
        var itemname = dynTableGetNodeInRow(elem, 'skuName').value;
        var batchNo = dynTableGetNodeInRow(elem, 'batchNo').value;
        var expDate = dynTableGetNodeInRow(elem, 'expDate').value;
        dynTableGetNodeInRow(elem, 'MRP').innerHTML = "";
        dynTableGetNodeInRow(elem, 'Stkqty').innerHTML = "";
        var searchParams = {"stkSkuName": itemname, "stkBatchNo": batchNo, "stkExpiryDate": expDate};
        dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
            $.grep($.map(data, function (item) {
                var opt2 = document.createElement("option");
                opt2.value = item.stkMrp;
                opt2.text = item.stkMrp;
                dynTableGetNodeInRow(elem, 'MRP').options.add(opt2);
                $.each(item.stockDChildList, function (pIdx, stockD) {
                    dynTableGetNodeInRow(elem, 'Stkqty').value = stockD.stkdQty;
                    var qty1 = dynTableGetNodeInRow(elem, 'qty').value - dynTableGetNodeInRow(elem, 'issuedQty').value;
                    if (dynTableGetNodeInRow(elem, 'Stkqty').value > qty1 || dynTableGetNodeInRow(elem, 'Stkqty').value == qty1) {
                        dynTableGetNodeInRow(elem, 'issueQty').value = qty1;
                    } else if (dynTableGetNodeInRow(elem, 'Stkqty').value < qty1) {
                        dynTableGetNodeInRow(elem, 'issueQty').value = dynTableGetNodeInRow(elem, 'Stkqty').value;
                    }
                    calculation(elem);
                });
            }));
        });
    }
    function onMrpChange(elem) {
        var itemname = dynTableGetNodeInRow(elem, 'skuName').value;
        var batchNo = dynTableGetNodeInRow(elem, 'batchNo').value;
        var expDate = dynTableGetNodeInRow(elem, 'expDate').value;
        var mrp = dynTableGetNodeInRow(elem, 'MRP').value;
        dynTableGetNodeInRow(elem, 'Stkqty').innerHTML = "";
        var searchParams = {"stkSkuName": itemname, "stkBatchNo": batchNo, "stkExpiryDate": expDate, "stkMrp": mrp};
        dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
            if (data != undefined) {
                $.grep($.map(data, function (item) {
                    $.each(item.stockDChildList, function (pIdx, stockD) {
                        dynTableGetNodeInRow(elem, 'Stkqty').value = stockD.stkdQty;
                        var qty1 = dynTableGetNodeInRow(elem, 'qty').value - dynTableGetNodeInRow(elem, 'issuedQty').value;
                        if (dynTableGetNodeInRow(elem, 'Stkqty').value > qty1 || dynTableGetNodeInRow(elem, 'Stkqty').value == qty1) {
                            dynTableGetNodeInRow(elem, 'issueQty').value = qty1;
                        } else if (dynTableGetNodeInRow(elem, 'Stkqty').value < qty1) {
                            dynTableGetNodeInRow(elem, 'issueQty').value = dynTableGetNodeInRow(elem, 'Stkqty').value;
                        }
                        calculation(elem);
                    });
                }), function (item, index) {
                    return index < 75;
                });
            } else {
                alert("Batch No and Exp date is wrong");
                dynTableGetNodeInRow(elem, 'Stkqty').value = "";
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
    function saveVisit(boRID, boCode, actionCode) {
        var visit = {};
        if (visitCheck == false && dcomethealth.commonInit == 0 || isNotExistVisit) {
            if (!!$('#visitRID').val() && $('#visitRID').val() != 0) {
                visit.id = $('#visitRID').val();
            }
            visit.visTypeIndex = 20;
            visit.visSubTypeIndex = 0; // Need to change the value;
            visit.visPatType = 0; // Need to check and change it.
            visit.visPatRid = $("#patientRID").val();
            visit.visApptRid = 0; // passing 0 because its a direct OP registration
            visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
            if (!!$("#VDName option:selected").val()) {
                visit.visConsDocRid = $("#VDName option:selected").val();
            }
            visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.   
            var search = 0 + "/" + "VISIT" + "/" + "BUILTIN_ACTION";
            dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                dcomethealth.PatientResource.searchActiveVisit({"visPatRid": data, "visDate": moment().format("DD-MM-YYYY")}, function (visit) {                    //                    
                    if ($("#visitRID").val() == "" || $("#visitRID").val() == 0) {
                        $("#visitRID").val(visit.id);
                        if (visit.visTypeIndex == 19) {
                            $('#visitType').val("IP");
                            saveBill(boRID, boCode, actionCode)
                        } else if (visit.visTypeIndex == 20) {
                            $('#visitType').val("OP");
                            saveBill(boRID, boCode, actionCode)
                        }
                    }
                });
            });
        }
    }
    function saveBill(boRID, boCode, actionCode) {
        var totalPaid = 0.00, serBoRid = 0, billSaveWithDrugReqSave = 0;
        var billDList = [], salesDList = [], salesHList = [], pmdList = [], receiptDList = [], drugDList = [];
        var billH = {}, salesH = {}, drugH = {};
        var receiptHList = [];
        var receiptH = {};
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var billD = {}, salesD = {};
            var drugD = {};
            //BillD             
            if (parseInt(dcomethealth.commonInit) != 0) {
                $.each(checkQtyList, function (inx, item) {
                    if (item.ItemRid == dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value) {
                        if (!!dynTableGetNodeInRow(table.rows[i + 1], 'drugReqDbillDRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'drugReqDbillDRid').value != 'undefined') {
                            billDCheck = 1;
                            billD.id = dynTableGetNodeInRow(table.rows[i + 1], 'drugReqDbillDRid').value;
                        }
                        if (!!dynTableGetNodeInRow(table.rows[i + 1], 'drugDId').value && dynTableGetNodeInRow(table.rows[i + 1], 'drugDId').value != 'undefined') {
                            drugD.id = dynTableGetNodeInRow(table.rows[i + 1], 'drugDId').value;
                        }
                        drugD.drugReqDItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value;
                        drugD.drugReqDItemName = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                        drugD.drugReqDItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                        drugD.drugReqDItemBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'batchNo').value;
                        drugD.drugReqDItemExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'expDate').value;
                        if (!dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value || isNaN(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value)) {
                            dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value = 0;
                        }
                        if (!dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value || isNaN(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value)) {
                            dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value = 0;
                        }
                        drugD.drugReqDItemIssuedQty = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value) + parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value);
                        drugD.drugReqDItemBalanceQty = drugD.drugReqDItemQty - drugD.drugReqDItemIssuedQty;
                        drugD.drugReqDItemPrice = parseFloat(dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value);
                        drugDList.push(drugD);
                        salesD.salSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value;
                        salesD.salItemName = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                        salesD.salBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'batchNo').value;
                        salesD.salExpDate = dynTableGetNodeInRow(table.rows[i + 1], 'expDate').value;
                        salesD.salStockQty = dynTableGetNodeInRow(table.rows[i + 1], 'Stkqty').value;
                        if (!dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value || isNaN(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value)) {
                            dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value = 0;
                        }
                        if (!dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value || isNaN(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value)) {
                            dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value = 0;
                        }
                        if (parseInt(dcomethealth.commonInit) != 0) {
                            salesD.salQty = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value) + parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value);
                        } else {
                            salesD.salQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                        }
                        salesD.salReturnQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                        salesD.salRate = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                        salesD.salGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'salDiscount').value != "") {
                            salesD.salDiscount = dynTableGetNodeInRow(table.rows[i + 1], 'salDiscount').value;
                            billD.bdDiscountAmount = dynTableGetNodeInRow(table.rows[i + 1], 'salDiscount').value;
                        } else {
                            salesD.salDiscount = 0;
                            billD.bdDiscountAmount = 0;
                        }
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'retailTax').value != "") {
                            salesD.salTax = dynTableGetNodeInRow(table.rows[i + 1], 'retailTax').value;
                            billD.bdTaxPercent = dynTableGetNodeInRow(table.rows[i + 1], 'retailTax').value;
                        } else {
                            salesD.salTax = 0;
                            billD.bdTaxPercent = 0;
                        }
                        salesD.salNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                        billD.bdItemType = "Drugs";
                        billD.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                        if (!!dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value) {
                            billD.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value;
                        } else {
                            billD.bdItemRID = 0;
                        }

                        billD.bdGroupRID = 0; // For Service Sales  
                        if (parseInt(dcomethealth.commonInit) != 0) {
                            billD.bdQty = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value) + parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value);
                        } else {
                            billD.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                        }
                        if (!!dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRid').value != "undefined") {
                            billD.bdItemPackageRID = dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRid').value;
                        }
                        billD.bdIsItemPackage = 0;
                        if (!!dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value) {
                            billD.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                        } else {
                            billD.bdPrice = 0;
                        }
                        if (!!dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value) {
                            billD.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                            billD.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                        } else {
                            billD.bdGrossAmount = 0;
                            billD.bdNetAmount = 0;
                        }
                        billD.bdIsDiscountPercentage = 1;
                        billDList.push(billD);
                        salesDList.push(salesD);
                    }
                });
            } else {
                salesD.salSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value;
                salesD.salItemName = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                salesD.salBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'batchNo').value;
                salesD.salExpDate = dynTableGetNodeInRow(table.rows[i + 1], 'expDate').value;
                salesD.salStockQty = dynTableGetNodeInRow(table.rows[i + 1], 'Stkqty').value;
                if (!dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value || isNaN(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value)) {
                    dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value = 0;
                }
                if (!dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value || isNaN(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value)) {
                    dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value = 0;
                }
                if (parseInt(dcomethealth.commonInit) != 0) {
                    salesD.salQty = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value) + parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value);
                } else {
                    salesD.salQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                }
                salesD.salReturnQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                salesD.salRate = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                salesD.salGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                if (dynTableGetNodeInRow(table.rows[i + 1], 'salDiscount').value != "") {
                    salesD.salDiscount = dynTableGetNodeInRow(table.rows[i + 1], 'salDiscount').value;
                    billD.bdDiscountAmount = dynTableGetNodeInRow(table.rows[i + 1], 'salDiscount').value;
                } else {
                    salesD.salDiscount = 0;
                    billD.bdDiscountAmount = 0;
                }
                if (dynTableGetNodeInRow(table.rows[i + 1], 'retailTax').value != "") {
                    salesD.salTax = dynTableGetNodeInRow(table.rows[i + 1], 'retailTax').value;
                    billD.bdTaxPercent = dynTableGetNodeInRow(table.rows[i + 1], 'retailTax').value;
                } else {
                    salesD.salTax = 0;
                    billD.bdTaxPercent = 0;
                }
                salesD.salNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                billD.bdItemType = "Drugs";
                billD.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value) {
                    billD.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRID').value;
                } else {
                    billD.bdItemRID = 0;
                }

                billD.bdGroupRID = 0; // For Service Sales  
                if (parseInt(dcomethealth.commonInit) != 0) {
                    billD.bdQty = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issueQty').value) + parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'issuedQty').value);
                } else {
                    billD.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'qty').value;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRid').value != "undefined") {
                    billD.bdItemPackageRID = dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRid').value;
                }
                billD.bdIsItemPackage = 0;
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value) {
                    billD.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                } else {
                    billD.bdPrice = 0;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value) {
                    billD.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                    billD.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
                } else {
                    billD.bdGrossAmount = 0;
                    billD.bdNetAmount = 0;
                }
                billD.bdIsDiscountPercentage = 1;
                billDList.push(billD);
                salesDList.push(salesD);
            }
        }
        if (!!$("#drugReqHId").val()) {
            drugH.id = $("#drugReqHId").val();
        }
        drugH.drugReqHPatMrn = $("#mrnSearch").val();
        drugH.drugReqHPatName = $("#PatientName").val();
        drugH.drugReqHPatRid = $("#patientRID").val();
        drugH.drugReqHProcedureRid = $("#drugReqHProcedureRid").val();
        drugH.drugReqHOpCheck = $("#drugReqHOpCheck").val();
        if (!!$('#visitRID').val()) {
            drugH.drugReqHOpVBisitRID = $('#visitRID').val();
        }
        var serviceNetAmount = $("#retailNetAmount").val();
        var total_pmd = document.getElementById('total_pmd').value;
        if (total_pmd == "") {
            total_pmd = 0;
        }
        if (serviceNetAmount == "") {
            alert("Bill Amount is empty");
            return false;
        } else if (parseFloat(serviceNetAmount) < parseFloat(total_pmd)) {
            alert("Net amount lesser than total amount paid not equal");
            return false;
        }
        if ($("#billHRID").val() != "" && $("#billHRID").val() != 0) {
            billH.id = $("#billHRID").val();
            salesH.salBhRID = $("#billHRID").val();
            drugH.drugReqHBillHRID = $("#billHRID").val();
        }
        if ($("#patientRID").val() != "") {
            billH.bhPatientRID = $("#patientRID").val();
            receiptH.rhPayerRID = $("#patientRID").val();
        } else {
            alert("Patient Not Registered Yet");
            return false;
        }

        salesH.salBillNo = $('#billNo').val();
        salesH.salCustomerId = $("#patientRID").val();
        salesH.salCustomerName = $("#PatientName").val();
        salesH.salCntPerson = $("#PatientName").val();
        salesH.salPhoneNo = $("#PatientMobile").val();
        salesH.salGrossAmount = $("#retailGrossAmount").val();
        salesH.salTotalDiscount = $("#retailDiscountAmount").val();
        salesH.salTotalTax = $("#retailTaxAmount").val();
        salesH.salRoundOffAmount = $("#round_off_amount").val();
        salesH.salNetAmount = $("#retailNetAmount").val();
        salesH.salCustomerRID = $("#patientRID").val();
        salesH.salType = 0;
        billH.bhBillNo = $('#billNo').val();
        billH.bhPrefix = $('#bhPrefix').val();
        billH.bhPrintableBillNo = $('#bhPrintableBillNo').val();
        billH.bhIsDraft = !!$('#bhIsDraft').val() ? $('#bhIsDraft').val() : 0;
        billH.bhDraftBillNo = $('#bhDraftBillNo').val();
        billH.bhPatientName = $("#PatientName").val();
        billH.bhDocRefRID = $("#VDName").val();
        billH.bhPatientNo = $("#PatientMobile").val();
        billH.bhType = 3;
        billH.bhEligibleAmount = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
        billH.bhApprovalNumber = !!$("#bhApprovalNo").val() ? $("#bhApprovalNo").val() : 0;
        billH.bhGrossAmount = $("#retailGrossAmount").val();
        billH.bhTotalDiscountAmount = $("#retailDiscountAmount").val();
        billH.bhTotalTaxAmount = $("#retailTaxAmount").val();
        billH.bhNetAmount = $("#retailNetAmount").val();
        billH.bhUnitRID = dcomethealth.selectedunit;
        billH.bhPayerType = $("#patRefType").val();
        if (parseInt($("#patRefType").val()) != 31) {
            billH.bhPayerRID = $("#payerName").val();
        } else {
            billH.bhPayerRID = $("#patientRID").val();
        }

        receiptH.rhPaidAmount = parseFloat(total_pmd);
        receiptH.rhTotalAmount = $("#retailNetAmount").val();
        receiptH.rhEligibleAmt = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
        receiptH.rhPayerType = $("#patRefType").val();
        receiptH.rhBaseCurRID = dcomethealth.sCurrencyRid;
        if ($("#patRefType").val() != 31) {
            receiptH.rhPayerName = $("#payerName option:selected").text();
        } else {
            receiptH.rhPayerName = $("#PatientName").val();
            receiptH.rhPayerNo = $("#patientMobile").val();
        }

        if (parseFloat(total_pmd) != 0) {
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
                if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value != "") {
                    pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                } else {
                    pmd.pmdAmount = 0.00;
                }
                pmd.pmdCurrencyRID = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_currency').value;
                pmdList.push(pmd);
                receiptDPmd.rdBillAmount = $("#retailNetAmount").val();
                receiptDPmd.rdPaidAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                receiptDList.push(receiptDPmd);
            }
            receiptH.receiptD = receiptDList;
            receiptH.paymentModeDetails = pmdList;
            receiptHList.push(receiptH);
        }
        salesH.salesD = salesDList;
        salesHList.push(salesH);
        salesH.salPaidAmount = parseFloat(totalPaid) + parseFloat(total_pmd) + parseFloat($("#billHPaidAmount").val());
        billH.bhPaidAmount = parseFloat(totalPaid) + parseFloat(total_pmd) + parseFloat($("#billHPaidAmount").val());
        drugH.drugRequestDList = drugDList;
        billH.billD = billDList;
        checkQtyList = [];
        if (parseInt(dcomethealth.bomtypeIndex) == 6 || parseInt(dcomethealth.bomtypeIndex) == 0) {
            billH.bhActionCode = actionCode;
        } else if ($("#visitType").val() == "OP") {//pre op with op visit creatting op bill
            billH.bhActionCode = "BUILTIN_ACTION";
            billSaveWithDrugReqSave = 1;
        } else if (!billHRidVar && $("#visitType").val() == "IP") {
            // Ip visit and without billRid-->we need to create draft bill and save service request.So neglet direct serReq save using(draftBillCreationWithSerReqSave=1)
            billSaveWithDrugReqSave = 1;
            billH.bhIsDraft = 1;
            billH.bhType = 2;
            billH.bhActionCode = "REQUEST";
        } else if (!billHRidVar && $("#visitType").val() == "OP") {
            billH.bhActionCode = actionCode;
        } else {
            billH.bhActionCode = "DRAFT_BILL_ADD";
            billSaveWithDrugReqSave = 1;
        }
//        console.log(billH);
//        return false;
        if (parseInt(billSaveWithDrugReqSave) == 1 || parseInt(dcomethealth.bomtypeIndex) == 6 || parseInt(dcomethealth.bomtypeIndex) == 0) {
            dcomethealth.BillingResource.saveBillWithCondition(billH).done(function (data, textStatus, jqXHR) {
                var saveAlert = 1;
                if (parseInt(dcomethealth.bomtypeIndex) == 47) {
                    if (!!data) {
                        saveAlert = 0;
                        if ($("#visitType").val() == "IP") {//no need to add bill rid for op visit
                            $.each(data.billD, function (inx, billD) {
                                $.each(drugH.drugRequestDList, function (indx, drugD) {
                                    if (billD.bdItemRID == drugD.drugReqDItemRID) {
                                        drugD.drugReqDBillDRID = billD.id;
                                    }
                                });
                            });
                        }
                        drugH.drugReqHActionCode = act;
                        var args = dcomethealth.boRID + "/" + "DRUG_REQUEST" + "/" + act;
                        dcomethealth.ItemOrderResource.saveDrugReqH(drugH, args)
                                .done(function (data, textStatus, jqXHR) {
                                    if (act == "CANCEL") {
                                        alert("Cancelled");
                                    } else {
                                        alert("Saved");
                                    }
                                    dcomethealth.util.base_init();
                                    dcomethealth.util.loadNotification();
                                    dcomethealth.util.loadpage('ProcedureRequest');
                                }).fail(function (jqXHR, textStatus, errorThrown) {
                        });
                    }
                }
                if (parseInt(saveAlert) == 1) {
                    alert("Saved");
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                    dcomethealth.util.loadpage('RetailSales');
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
        if (parseInt(dcomethealth.bomtypeIndex) == 47 && parseInt(billSaveWithDrugReqSave) == 0) {
            drugH.drugReqHActionCode = act;
            var args = dcomethealth.boRID + "/" + "DRUG_REQUEST" + "/" + act;
            dcomethealth.ItemOrderResource.saveDrugReqH(drugH, args).done(function (data, textStatus, jqXHR) {
                if (act == "CANCEL") {
                    alert("Cancelled");
                } else {
                    alert("Saved");
                }
                dcomethealth.util.base_init();
                dcomethealth.util.loadNotification();
                dcomethealth.util.loadpage('RetailSales');
            }).fail(function (jqXHR, textStatus, Patients) {
                alert("Failed");
            });
        }
    }

    function refreshData() {
    }
    return {
        init: init,
        mrn_autocomplete: mrn_autocomplete,
        setFocusToTextBox: setFocusToTextBox,
        payerChange: payerChange,
        payerNameChange: payerNameChange,
        doctorChange: doctorChange,
        autocomplete: autocomplete,
        onBatchChange: onBatchChange,
        onExpiryChange: onExpiryChange,
        autoIdSet: autoIdSet,
        loadWorkList: loadWorkList,
        onMrpChange: onMrpChange,
        saveBill: saveBill,
        refreshData: refreshData

    };
}());
dcomethealth.RetailSales.init();