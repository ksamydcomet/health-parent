var dcomethealth = dcomethealth || {};
dcomethealth.StockConsumption = (function () {
    title: "Stock Consumption"
    var id = "StockConsumption", actionCode = "";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autocomplete();
//            
            if (parseInt(dcomethealth.commonInit) !== 0) {
                $('#head_sc').text('Material Issue');
                $("#btns_state").empty();
                var categories = [];
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    var div = $("#btns_state");
                    if ($.inArray(datalist.boaName, categories) === -1) {
                        categories.push(datalist.boaName);
                        if (datalist.boaName != "PARTIALLY_ISSUE") {
                            div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>');
                            btnName = datalist.boaName;
                        }

                    }
                });
                itemIssueDetails();
            }


            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    ConsSkuName: "required",
                    ConsQty: "required"
                },
                messages: {
                    ConsSkuName: "Enter Item Name",
                    ConsQty: "Enter Quantity"

                },
                submitHandler: function (form) {
                    if (parseInt(dcomethealth.bomtypeIndex) == 47) {
                        var table = document.getElementById('dyn_table');
                        var table_length = table.rows.length;
                        var check = false;
                        for (var i = 0; i < table_length - 1; i++) {
                            var reqQty = dynTableGetNodeInRow(table.rows[i + 1], 'reqQty').value;
                            var ConsQty = dynTableGetNodeInRow(table.rows[i + 1], 'ConsQty').value;
                            if (reqQty > ConsQty) {
                                check = true;
                            }
                        }
                        if (check) {
                            actionCode = "PARTIALLY_ISSUE";
                            submit();
                        } else {
                            actionCode = "ISSUE";
                            submit();
                        }
                    } else {
                        submit();
                    }
                }
            });
        });
    }

    function  itemIssueDetails() {
        alert(dcomethealth.bomtypeIndex);
        var searchObj = {"id": dcomethealth.boRID};
        dcomethealth.ItemOrderResource.getDrugs(searchObj, function (data) {
            console.log(data);
            $("#patientDiv").removeClass("hidden");
            $.each(data, function (Idx, data) {
                $("#drugReqHId").val(data.id);
                $("#patMrn").text(data.drugReqHPatMrn);
                $("#patName").text(data.drugReqHPatName);
                $("#drugReqHPatRid").val(data.drugReqHPatRid);
                $("#drugReqHOpCheck").val(data.drugReqHOpCheck);
                $("#drugReqHProcedureRid").val(data.drugReqHProcedureRid);

                $("#itemTbody").empty();
                $.each(data.drugRequestDList, function (cIdx, child) {
                    alert(child.id);
                    $("#itemTbody").append('<tr><td width="2%"><i id="del" class="ace-icon fa fa-minus hidden" onclick="delete_row(this)"></i><input type="hidden" id="ConsSkuRID" name="ConsSkuRID" value="' + child.drugReqDItemRID + '"></td>\n\
                     <td id="itemaNameTd" width="28%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="ConsSkuName" name="ConsSkuName" value="' + child.drugReqDItemName + '"/><input type="hidden" id="drugDId" value="' + child.id + '"/></td>\n\
                    <td width="11%" class="align-middle"><select class="col-md-12 col-sm-12 col-xs-12" id="ConsBatchNo" name="ConsBatchNo" onchange="dcomethealth.StockConsumption.onBatchChange(this)"> value=""</select></td>\n\
                    <td id="batch_td" width="11%"><select class="col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip" title="MM/YYYY format" id="ConsExpiryDate" name="ConsExpiryDate" onchange="dcomethealth.StockConsumption.onExpiryChange(this)"></select></td>\n\
                    <td id="expDate_td" width="10%"><select class="col-md-12 col-sm-12 col-xs-12" id="ConsMrp" name="ConsMrp" ></select></td>\n\
                    <td width="10%"><select class="col-md-12 col-sm-12 col-xs-12" id="stkQty" name="stkQty"><option>100</option></select></td> \n\
                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" id="reqQty" name="reqQty" value="' + child.drugReqDItemQty + '"/></td> \n\
                    <td width="8%"><select id="UOMcons" name="UOMcons" class="dcomet-c-s_ddict_uom-list col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip" title="Select UOM"></select></td>\n\
                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="ConsQty" name="ConsQty" maxlength="9" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" onBlur="calculate_rows_qty(this)"/></td>\n\
                    <td width="2%"><i class="ace-icon fa fa-plus red" onclick="insert_row("/dyn_table"/, this)"></i></td></tr>');
                });
//                var table = document.getElementById('dyn_table');
//                var table_length = table.rows.length;
//                var y = table_length - 1;
//                alert(y);
//                for (var i = 0; i < y; i++) {
//                    var opt = document.createElement("option");
//                    var opt1 = document.createElement("option");
//                    var opt2 = document.createElement("option");
//                    var opt3 = document.createElement("option");
//                    var searchParams = {"stkSkuRID": dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuRID').value};
//                    dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
////                        console.log(data);
//                        $.each(data, function (sIdx, item) {
//                            opt.value = item.stkBatchNo;
//                            opt.text = item.stkBatchNo;
//
//                            opt1.value = item.stkExpiryDate;
//                            opt1.text = item.stkExpiryDate;
//
//                            opt2.value = item.stkMrp;
//                            opt2.text = item.stkMrp;
//
//                            opt3.value = item.stockDChildList[0].stkdQty;
//                            opt3.text = item.stockDChildList[0].stkdQty;
//                            console.log(opt);
////                            console.log(opt1);
////                            console.log(opt2);
////                            console.log(opt3);
//                        });
////                        dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuRID').value = item.stkSkuRID;
//                        var o = ["1", "2"];
////                        console.log(opt);
//                        setTimeout(function () {
//                            alert("11");
//                            console.log(opt);
//                            dynTableGetNodeInRow(table.rows[i + 1], 'ConsBatchNo').options.add(opt);
//                            dynTableGetNodeInRow(table.rows[i + 1], 'ConsExpiryDate').options.add(opt1);
//                            dynTableGetNodeInRow(table.rows[i + 1], 'ConsMrp').options.add(opt2);
//                            dynTableGetNodeInRow(table.rows[i + 1], 'stkQty').options.add(opt3);
//                        }, 3000);
//
//                    });
//            }
            });
        });
    }
    function autocomplete() {
        var skuName;
        $("#" + id + " input[name='ConsSkuName']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                dynTableGetNodeInRow(this, 'ConsSkuRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'ConsBatchNo').innerHTML = "--BatchNo--";
                dynTableGetNodeInRow(this, 'ConsExpiryDate').innerHTML = "--ExpDate--";
                dynTableGetNodeInRow(this, 'ConsMrp').innerHTML = "--MRP--";
                dynTableGetNodeInRow(this, 'stkQty').innerHTML = "--Stock--";
                var searchParams = {"stkSkuName": skuName};
                dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
                    if (data != undefined) {
                        $.grep($.map(data, function (item) {
                            item.id = item.stkBatchNo;
                            var table = document.getElementById('dyn_table');
                            var table_length = table.rows.length;
                            var y = table_length - 2;
                            var opt = document.createElement("option");
                            opt.value = item.stkBatchNo;
                            opt.text = item.stkBatchNo;
                            var opt1 = document.createElement("option");
                            opt1.value = item.stkExpiryDate;
                            opt1.text = item.stkExpiryDate;
                            var opt2 = document.createElement("option");
                            opt2.value = item.stkMrp;
                            opt2.text = item.stkMrp;
                            var opt3 = document.createElement("option");
                            opt3.value = item.stockDChildList[0].stkdQty;
                            opt3.text = item.stockDChildList[0].stkdQty;
                            for (var i = 0; i < table_length - 1; i++) {
                                dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuRID').value = item.stkSkuRID;
                                dynTableGetNodeInRow(table.rows[i + 1], 'ConsBatchNo').options.add(opt);
                                dynTableGetNodeInRow(table.rows[i + 1], 'ConsExpiryDate').options.add(opt1);
                                dynTableGetNodeInRow(table.rows[i + 1], 'ConsMrp').options.add(opt2);
                                dynTableGetNodeInRow(table.rows[i + 1], 'stkQty').options.add(opt3);
                            }
//
                        }), function (item, index) {
                            return index < 10;
//
                        });
                    } else {
                        alert("Out of stock");
                        return false;
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
                        return index < 75;
                    }));
                });
            },
            minLength: 1
        });
    }
    function onBatchChange(elem) {
        var itemname = dynTableGetNodeInRow(elem, 'ConsSkuName').value;
        var batchNo = dynTableGetNodeInRow(elem, 'ConsBatchNo').value;
        dynTableGetNodeInRow(elem, 'ConsExpiryDate').innerHTML = "";
        dynTableGetNodeInRow(elem, 'ConsMrp').innerHTML = "";
        dynTableGetNodeInRow(elem, 'stkQty').innerHTML = "";
        var searchParams = {"stkSkuName": itemname, "stkBatchNo": batchNo};
        dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
            $.grep($.map(data, function (item) {
                item.id = item.stkBatchNo;
                var opt1 = document.createElement("option");
                opt1.value = item.stkExpiryDate;
                opt1.text = item.stkExpiryDate;
                var opt2 = document.createElement("option");
                opt2.value = item.stkMrp;
                opt2.text = item.stkMrp;
                var opt3 = document.createElement("option");
                opt3.value = item.stockDChildList[0].stkdQty;
                opt3.text = item.stockDChildList[0].stkdQty;
                dynTableGetNodeInRow(elem, 'ConsSkuRID').value = item.stkSkuRID;
                dynTableGetNodeInRow(elem, 'ConsExpiryDate').options.add(opt1);
                dynTableGetNodeInRow(elem, 'ConsMrp').options.add(opt2);
                dynTableGetNodeInRow(elem, 'stkQty').options.add(opt3);
                ;
            }), function (item, index) {
                return index < 75;
            });
        });
    }
    function onExpiryChange(elem) {
        var itemname = dynTableGetNodeInRow(elem, 'ConsSkuName').value;
        var batchNo = dynTableGetNodeInRow(elem, 'ConsBatchNo').value;
        var expDate = dynTableGetNodeInRow(elem, 'ConsExpiryDate').value;
        dynTableGetNodeInRow(elem, 'ConsMrp').innerHTML = "";
        dynTableGetNodeInRow(elem, 'stkQty').innerHTML = "";
        var searchParams = {"stkSkuName": itemname, "stkBatchNo": batchNo, "stkExpiryDate": expDate};
        dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
            $.grep($.map(data, function (item) {
                var opt2 = document.createElement("option");
                opt2.value = item.stkMrp;
                opt2.text = item.stkMrp;
                var opt3 = document.createElement("option");
                opt3.value = item.stockDChildList[0].stkdQty;
                opt3.text = item.stockDChildList[0].stkdQty;
                dynTableGetNodeInRow(elem, 'ConsSkuRID').value = item.stkSkuRID;
                dynTableGetNodeInRow(elem, 'ConsMrp').options.add(opt2);
                dynTableGetNodeInRow(elem, 'stkQty').options.add(opt2);
            }), function (item, index) {
                return index < 75;
            });
        });
    }
    function submit() {
        var form = $("form");
        if (dcomethealth.StockConsumption.validateForm(form)) {
            if (dcomethealth.bomtypeIndex == 47) {
                var drugH = {}, drugDlist = [];
                if (!!$("#drugReqHId").val()) {
                    drugH.id = $("#drugReqHId").val();
                }
                drugH.drugReqHPatMrn = $("#patMrn").text();
                drugH.drugReqHPatName = $("#patName").text();
                drugH.drugReqHPatRid = $("#drugReqHPatRid").val();
                drugH.drugReqHProcedureRid = $("#drugReqHProcedureRid").val();
                drugH.drugReqHOpCheck = $("#drugReqHOpCheck").val();
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var drugD = {};
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'drugDId').value && dynTableGetNodeInRow(table.rows[i + 1], 'drugDId').value != "undefined") {
                        drugD.id = dynTableGetNodeInRow(table.rows[i + 1], 'drugDId').value;
                    }
                    drugD.drugReqDItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuRID').value;
                    drugD.drugReqDItemName = dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuName').value;
                    drugD.drugReqDItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'reqQty').value;
                    drugD.drugReqDItemBatchNo = 111;
                    drugD.drugReqDItemExpiryDate = 111;
                    drugD.drugReqDItemIssuedQty = 111;
                    drugD.drugReqDItemBalanceQty = drugD.drugReqDItemQty - drugD.drugReqDItemIssuedQty;
                    drugDlist.push(drugD);
                }
                drugH.drugRequestDList = drugDlist;
//                submit(dcomethealth.boRID, "PROCEDURE_REQUEST", "COMPLETE");
                alert(actionCode);
//                return false;
                var args = dcomethealth.boRID + "/" + "DRUG_REQUEST" + "/" + actionCode;
                dcomethealth.ItemOrderResource.saveDrugReqH(drugH, args)
                        .done(function (data, textStatus, jqXHR) {
                            alert("Saved");
                            dcomethealth.util.loadpage('StockConsumption');
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
            } else {
                var comsumptionList = [];
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var parent = {};
                    parent.consType = 2;
                    parent.consSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuRID').value;
                    parent.consSkuName = dynTableGetNodeInRow(table.rows[i + 1], 'ConsSkuName').value;
                    parent.consBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'ConsBatchNo').value;
                    parent.consExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'ConsExpiryDate').value;
                    parent.consMrp = dynTableGetNodeInRow(table.rows[i + 1], 'ConsMrp').value;
                    parent.consQty = dynTableGetNodeInRow(table.rows[i + 1], 'ConsQty').value;
                    comsumptionList.push(parent);
                }
                dcomethealth.InventoryResource.saveconsumption(comsumptionList)
                        .done(function (data, textStatus, jqXHR) {
                            alert("Saved");
                            dcomethealth.util.loadpage('StockConsumption');
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
            }
        }
    }
    function validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        autocomplete: autocomplete,
        onBatchChange: onBatchChange,
        onExpiryChange: onExpiryChange,
        validateForm: validateForm,
        submit: submit,
        refreshData: refreshData

    }
}());
dcomethealth.StockConsumption.init();