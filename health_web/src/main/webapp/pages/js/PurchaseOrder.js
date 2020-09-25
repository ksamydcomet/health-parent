var dcomethealth = dcomethealth || {};
var boStatus = [];
dcomethealth.PurchaseOrder = {
    id: "PurchaseOrder",
    init: function () {
        var _this = this;
        $("#main_navigation_bar").append('<div id="' + this.id + '" class="main_container">');
        $("#" + this.id).load("pages/html/" + this.id + ".html", function () {
            dcomethealth.datatypes.init($("#" + this.id));
            var start = moment();
            var end = moment();
            $('#PoDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $('#PoDateRange').daterangepicker({
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
                $('#PoDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            });
            $('#curDate').text(moment().format('DD-MM-YYYY'));
            _this.autoCompleteItemName();
            _this.autoCompleteSupplier();
            _this.getBoStatus('PURCHASE_ORDER');
            _this.viewPO();
            if (parseInt(dcomethealth.commonInit) != 0) {
                $("#PurchaseOrderHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#AddNew").removeClass("hidden").addClass("panel panel-primary");
                $("#btns_state").empty();
                $("#")//remarks
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    var div = $("#btns_state");
                    div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>');
                });
                $('#pod_tbody').empty()
                var boRID = dcomethealth.boRID;
                _this.getPO(boRID, 1);

            }

            $(".btn-primary").off("click").on("click", function () {
                $(".btn-primary").attr("disabled", "disabled");
                var supplierName = document.getElementById("supplierName").value;
                if (supplierName == "") {
                    alert("Enter Supplier Name");
                    return false;
                }
                if ($("#pohDeliveryDate").val() == "" || $("#pohDeliveryDate").val() == undefined) {
                    alert("Enter Delivery Date");
                    return false;
                }
                var table = document.getElementById('dyn_table_PO');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var item_name = dynTableGetNodeInRow(table.rows[i + 1], 'item_name').value;
                    if (item_name == "") {
                        alert("Enter Item Name");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[i + 1], 'item_name').focus();
                        }, 1);
                        return false;

                        var order_qty = dynTableGetNodeInRow(table.rows[i + 1], 'order_qty').value;
                    } else if (order_qty == "") {
                        alert("Enter Ordered Quantity");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[i + 1], 'order_qty').focus();
                        }, 1);
                        return false;

                        var rate = dynTableGetNodeInRow(table.rows[i + 1], 'rate').value;
                    } else if (rate == "") {
                        alert("Enter Rate");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[i + 1], 'rate').focus();
                        }, 1);
                        return false;

                        var dis = dynTableGetNodeInRow(table.rows[i + 1], 'dis').value;
                    } else if (dis == "") {
                        alert("Enter Discount %");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[i + 1], 'dis').focus();
                        }, 1);
                        return false;

                    }
                }
                var netAmt = document.getElementById("pohNetAmount").value;
                if (netAmt == "") {
                    alert("Net Amount Should Not Be Empty");
                    return false;
                }
                var actionBtn = this.id;
                var i = 1;
                if (dcomethealth.actionDatalist == 0 && actionBtn == "Save") {
                    _this.editAllButtonOnClick(dcomethealth.boRID, "PURCHASE_ORDER", "SUBMIT", 1);
                } else if (dcomethealth.actionDatalist == 0 && actionBtn == "Modify") {
                    _this.editAllButtonOnClick(dcomethealth.boRID, "PURCHASE_ORDER", "SUBMIT", 0);
                }
                else {
                    $.each(dcomethealth.actionDatalist, function (pIdx, action) {
                        if (action.boaCode == actionBtn) {
                            if (i == 1) {
                                dcomethealth.NotificationResource.searchMasterCode(action.boaBomTypeIndex, function (data) {
                                    _this.editAllButtonOnClick(dcomethealth.boRID, data, action.boaCode, 1);
                                });
                                i++;
                            }
                        }
                    });
                }
            });
        });
    },
    getPO: function (boRIDPO, val) {
        var searchObj = {"id": boRIDPO};
        dcomethealth.PurchaseOrderResource.searchPurchaseOrders(searchObj, function (data) {
            $.each(data, function (pIdx, data) {
//                if (data.id == boRIDPO) {
                $("#PORid").val(data.id);
                $("#supplierId").val(data.pohSupplierRID);
                $("#pohNumber").val(data.pohNumber);
                var searchParams = {"id": data.pohSupplierRID};
                dcomethealth.MasterResource.searchSuppliers(searchParams, function (data) {
                    $.each(data, function (pIdx, data) {
                        $("#supplierName").val(data.supName);
                        $("#supplierAdd").val(data.supAdd);
                    })
                });
                $("#createdDateTime").val(data.createdDateTime);
                $("#pohDeliveryDate").val(data.pohDeliveryDate);
                $("#pohTermsOfDelivery").val(data.pohTermsOfDelivery);
                $("#pohGrossAmount").val(data.pohGrossAmount);
                $("#pohTaxAmount").val(data.pohTaxAmount);
                $("#pohDiscountAmount").val(data.pohDiscountAmount);
                $("#pohNetAmount").val(data.pohNetAmount);
                $("#pod_tbody").empty();
                if (val == 1) {
                    $.each(data.purchaseOrderD, function (pIdx, poD) {
                        $("#pod_tbody").append('<tr><td width="1%"><i id="del_PO" class="ace-icon fa fa-minus red hidden" onclick="delete_row(this)"></i></td><td class="hidden"><input type="hidden" id="podRID" name="podRID" value="' + poD.id + '"/>\n\
<input type="hidden" id="itemRID" name="itemRID" value="' + poD.podSkuRID + '"/></td>\n\
<td width="20%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="item_name" name="item_name" value="' + poD.podSkuName + '" readonly="true"/></td>\n\
<td width="7%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="podBatchNo" name="item_BatchNo" value="' + poD.podBatchNo + '"/></td>\n\
<td width="7%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="podExpiryDate" maxlength="10" name="item_ExpDate" value="' + poD.podExpiryDate + '"/></td>\n\
<td width="7%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="order_qty" name="order_qty" onkeypress="return dcomethealth.validation.isNumberKey(event)" value="' + poD.podQty + '" readonly="true"/></td>\n\
<td width="7%"><select class="dcomet-c-s_ddict_uom-list col-md-12 col-sm-12 col-xs-12" id="UOM_PO" name="UOM_PO" ><option value="226">Nos</option></select></td>\n\
<td width="7%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rate" name="rate" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + poD.podRate + '" onBlur="calculation(this)" readonly="true"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="dis" name="dis" readonly="true" onchange="dcomethealth.validation.isPercentaged(this)" value="' + poD.podDiscountPercentage + '" onBlur="calculation(this)"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="tax" name="tax" value="' + poD.podTaxPercentage + '" readonly="true" onchange="dcomethealth.validation.isPercentaged(this)" onBlur="calculation(this)" /></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="net_amount" name="net_amount" value="' + poD.podNetAmount + '" readonly="true" onBlur=""/></td><td width="1%"><i class="ace-icon fa fa-plus red" onclick="insert_row(\'dyn_table_PO\', this)"></i></td></tr>');
                    });
                } else {
                    $.each(data.purchaseOrderD, function (pIdx, poD) {
                        $("#pod_tbody").append('<tr><td width="1%"><i id="del_PO" class="ace-icon fa fa-minus red hidden" onclick="delete_row(this)"></i></td><td class="hidden"><input type="hidden" id="podRID" name="podRID" value="' + poD.id + '"/>\n\
<input type="hidden" id="itemRID" name="itemRID" value="' + poD.podSkuRID + '"/></td>\n\
<td width="20%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="item_name" name="item_name" value="' + poD.podSkuName + '" /></td>\n\
<td width="7%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="podBatchNo" name="item_BatchNo" value="' + poD.podBatchNo + '"/></td>\n\
<td width="7%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="podExpiryDate" maxlength="10" name="item_ExpDate" value="' + poD.podExpiryDate + '"/></td>\n\
<td width="7%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="order_qty" name="order_qty" onkeypress="return dcomethealth.validation.isNumberKey(event)" value="' + poD.podQty + '" /></td>\n\
<td width="7%"><select class="dcomet-c-s_ddict_uom-list col-md-12 col-sm-12 col-xs-12" id="UOM_PO" name="UOM_PO" ><option value="226">Nos</option></select></td>\n\
<td width="7%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rate" name="rate" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + poD.podRate + '" onBlur="calculation(this)" /></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="dis" name="dis"  onchange="dcomethealth.validation.isPercentaged(this)" value="' + poD.podDiscountPercentage + '" onBlur="calculation(this)"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="tax" name="tax" value="' + poD.podTaxPercentage + '"  onchange="dcomethealth.validation.isPercentaged(this)" onBlur="calculation(this)" /></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="net_amount" name="net_amount" value="' + poD.podNetAmount + '" readonly="true" onBlur=""/></td><td width="1%"><i class="ace-icon fa fa-plus red" onclick="insert_row(\'dyn_table_PO\', this)"></i></td></tr>');
                    });
                }
            });
        });
    },
    getBoStatus: function (args) {
        boStatus = [];
        var array = [];
        dcomethealth.DataDictionaryResource.getBoMasterByName(args, function (masters) {
            if (!!masters) {
            $.each(masters, function (idx, bomStatus) {
                $.each(bomStatus.boStatus, function (cdx, bomboStatus) {
                    if ($.inArray(bomboStatus.bosuIndex, array) === -1) {
                        array.push(bomboStatus.bosuIndex);
                        boStatus.push(bomboStatus);
                    }
                });
            });
            }
        });
    },
    viewPO: function () {
        var array = [];
        var date = $('#PoDateRangeSpan').html().split('-');
        var searchObj = {"pohFromDate": moment(date[0]), "pohToDate": moment(date[1]).add(1, 'days')};
        var tables = $.fn.dataTable.fnTables(true);
        $(tables).each(function () {
            $(this).dataTable().fnClearTable();
            $(this).dataTable().fnDestroy();
        });
        dcomethealth.PurchaseOrderResource.searchPurchaseOrders(searchObj, function (data) {
            if (data !== undefined) {
                $('#po_data').empty();
                $.each(data, function (pIdx, pos) {                    
                    $.each(boStatus, function (idx, bomboStatus) {
                        if (bomboStatus.bosuIndex == pos.pohStatus) {
                            $('#po_data').append('<tr onclick="modifyPO(' + pos.id + ',\'' + bomboStatus.bosuName + '\')"><td>' + pos.pohNumber + '<input type="hidden" id="poRid" value="' + pos.id + '"/></td>\n\
<td>' + moment(pos.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td><td>' + bomboStatus.bosuName + '</td></tr>');
                        }

                    });
                });
                $('#tab_po_details').dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $('#tab_po_details').dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    },
    autoCompleteSupplier: function () {
        $("#" + this.id + " input[name='supplierName']").autocomplete({
            select: function (event, ui) {
                $("#supplierId").val(ui.item.data.id);
                $("#supplierAdd").val(ui.item.data.supAdd);
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
                var searchParams = {"supName": queryString};
                dcomethealth.MasterResource.searchSuppliers(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.supName || ""),
                            value: item.supName || "",
                            data: item
                        };
                    }), function (item, index) {
                        return index < 75;

                    }));
                });
            },
            minLength: 1
        });
    },
    autoCompleteItemName: function () {
        $("#" + this.id + " input[name='item_name']").autocomplete({
            select: function (event, ui) {
                var grdItemName = ui.item.value;
                dynTableGetNodeInRow(this, 'itemRID').value = ui.item.data.id;
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
                var searchParams = {"skuName": queryString};
                dcomethealth.MasterResource.searchSkus(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.skuName || ""),
                            value: item.skuName || "",
                            data: item
                        };
                    }), function (item, index) {
                        return index < 15;

                    }));
                });
            },
            minLength: 1
        });
    },
    editAllButtonOnClick: function (boRID, boCode, actionCode, type) {
        var parent = {};
        if ($('#PORid').val() != "") {
            parent.id = $('#PORid').val();
            boRID = $('#PORid').val();
        }
        parent.pohNumber = $('#pohNumber').val();
        parent.pohSupplierRID = $('#supplierId').val();
        parent.pohFinanceYear = moment().format('YYYY');
        parent.pohQuoteRID = '1';
        if ($('#POStatus').val() != "" && $('#POStatus').val() == "Submitted") {
            parent.pohStatus = '1';
        } else if ($('#POStatus').val() != "" && $('#POStatus').val() == "PO Approved") {
            parent.pohStatus = '2';
        } else if ($('#POStatus').val() != "" && $('#POStatus').val() == "PO rejected") {
            parent.pohStatus = '3';
        }
        parent.pohGrossAmount = $("#pohGrossAmount").val();
        parent.pohTaxAmount = $("#pohTaxAmount").val();
        parent.pohDiscountAmount = $("#pohDiscountAmount").val();
//        parent.pohFreightAmount = '12.25';
        parent.pohNetAmount = $("#pohNetAmount").val();
        parent.pohRemarks = 'User Needed';
        parent.pohType = 0;
        parent.pohGrnReceived = 0;
        parent.pohGrnReceivableUnitRID = '1';
        parent.pohStoreRID = '1';
        if (actionCode == 'APPROVE') {
            parent.pohApprovedUserRID = dcomethealth.loginuser.id;
//            parent.pohApprovedDateTime = '2014-10-15 13:07:29';
        }
        parent.pohRoundOffAmount = '324';
        if ($("#pohDeliveryDate").val() != "") {
            parent.pohDeliveryDate = moment($("#pohDeliveryDate").val(), 'DD-MM-YYYY').format('DD-MM-YYYY');
        }
        parent.pohTermsOfDelivery = $("#pohTermsOfDelivery").val();
        if ($("#createdDateTime").val() != "") {
            parent.createdDateTime = $("#createdDateTime").val();
        }
        var child1List = [];

        // LOOP Start
        var table = document.getElementById('dyn_table_PO');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var child = {};
            if (dynTableGetNodeInRow(table.rows[i + 1], 'podRID').value != "") {
                child.id = dynTableGetNodeInRow(table.rows[i + 1], 'podRID').value;
            }
            child.podSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value;
            child.podSkuName = dynTableGetNodeInRow(table.rows[i + 1], 'item_name').value;
            child.podQty = dynTableGetNodeInRow(table.rows[i + 1], 'order_qty').value;
            child.podReceiptQty = dynTableGetNodeInRow(table.rows[i + 1], 'order_qty').value;
            child.podGrnReceivedQty = 0;
            child.podRate = dynTableGetNodeInRow(table.rows[i + 1], 'rate').value;
            child.podMrp = dynTableGetNodeInRow(table.rows[i + 1], 'rate').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'tax').value == "") {
                child.podTaxPercentage = 0;
            } else {
                child.podTaxPercentage = dynTableGetNodeInRow(table.rows[i + 1], 'tax').value;
            }
            if (dynTableGetNodeInRow(table.rows[i + 1], 'dis').value == "") {
                child.podDiscountPercentage = 0;
            } else {
                child.podDiscountPercentage = dynTableGetNodeInRow(table.rows[i + 1], 'dis').value;
            }
            child.podNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'net_amount').value;
            child.podStatus = '1';
            child.podRowInvalidated = 0;
            child.podBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'podBatchNo').value;
            child.podExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'podExpiryDate').value;
//                        child.podProcessedDate='2014-10-15';
//                        child.podTaxApplicabilityOn='34';
//                        child.podCessPercentage='45';
//                        child.podCessAmount='554';
//                        child.podClosingStock='22';
//                        child.podSkuAvgLeadTime='12.45';
//                        child.podSafetyStock='16';                        
//                        child.podReOrderLevel='1';
//                        child.podEoqf='24.35';
//                        child.podParentRID='1'; 
            child1List.push(child);
        }
        parent.purchaseOrderD = child1List;
        if (parseInt(type) === 1) {
            var search = boRID + "/" + boCode + "/" + actionCode;
            dcomethealth.PurchaseOrderResource.savepurchaseorder(parent, search)
                    .done(function (data, textStatus, jqXHR) {
                        alert("saved");
                        dcomethealth.util.loadpage('PurchaseOrder');
                        dcomethealth.util.base_init();
                        dcomethealth.util.loadNotification();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("failed");
            });
        } else {
            dcomethealth.PurchaseOrderResource.updatepurchaseorder(parent)
                    .done(function (data, textStatus, jqXHR) {
                        alert("saved");
                        dcomethealth.util.loadpage('PurchaseOrder');
                        dcomethealth.util.base_init();
                        dcomethealth.util.loadNotification();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("failed");
            });
        }
    },
    refreshData: function () {
    }
};
dcomethealth.PurchaseOrder.init();