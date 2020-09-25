var dcomethealth = dcomethealth || {};
dcomethealth.PurchaseReturn = (function () {

    var id = "PurchaseReturn";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#curDate').text(moment().format('DD-MM-YYYY'));
            GrnAutocomplete();
            $("#" + id + " input[name='prdSkuName']").autocomplete({
                select: function (event, ui) {
                    var grdItemName = ui.item.value;
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
                            return index < 75;

                        }));
                    });
                },
                minLength: 1
            });

            $("#" + id + " input[name='prhSupplierName']").autocomplete({
                select: function (event, ui) {
//                    grhSupplierRid = ui.item.data.id;
                    $('#suppRid').val(ui.item.data.id);
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

            $(".btn-primary").off("click").on("click", function () {
                $(".btn-primary").attr("disabled", "disabled");
                var supplierName = document.getElementById("prhSupplierName").value;
                if (supplierName == "") {
                    alert("Enter Supplier Name");
                    return false;
                }
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var ret_qty = dynTableGetNodeInRow(table.rows[i + 1], 'ret_qty').value;
                    var stk_qty = dynTableGetNodeInRow(table.rows[i + 1], 'stk_qty').value;
                    if (stk_qty <= 0) {
                        alert("Out Of Stock");
                        return false;
                    }
                    if (ret_qty == "") {
                        alert("Enter Return Quantity ");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[i + 1], 'ret_qty').focus();
                        }, 1);
                        return false;
                    }
                }
                var net_tot_amount = document.getElementById("net_tot_amount").value;
                if (net_tot_amount == 0) {
                    alert("Enter Correct Details");
                } else {
                    editAllButtonOnClick();
                }
            }
            );
        });
    }
    function GrnAutocomplete() {
        $("#" + id + " input[name='grnNo']").autocomplete({
            select: function (event, ui) {
                $('#grnRid').val(ui.item.data.id)
                $('#grhdcdate').val(ui.item.data.grhDcDate);
                $("#grhDcNo").val(ui.item.data.grhDcNo);
                $("#grhSequenceNo").val(ui.item.data.grhSequenceNo);
                $("#grhVersion").val(ui.item.data.grhVersion);
                $("#grhInvoiceNo").val(ui.item.data.grhInvoiceNo);
                $("#grhInvoiceDate").val(ui.item.data.grhInvoiceDate);
                $("#grhGrossAmount").val(ui.item.data.grhGrossAmount);
                $("#grhDiscountAmount").val(ui.item.data.grhDiscountAmount);
                $("#grhTaxAmount").val(ui.item.data.grhTaxAmount);
                $("#grhNetAmount").val(ui.item.data.grhNetAmount);
                $("#grn_tbody").empty();
                $.each(ui.item.data.goodsReceiptD, function (pIdx, goodsReceiptD) {
                    var searchParams = {"stkSkuName": goodsReceiptD.grdItemName, "stkBatchNo": goodsReceiptD.grdBatchNo, "stkExpiryDate": goodsReceiptD.grdExpiryDate, "stkUnitRID": dcomethealth.selectedunit};
                    dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
                        $.each(data, function (pIdx, stkH) {
                            $.each(stkH.stockDChildList, function (pIdx, stockD) {
                                $('#grn_tbody').append('<tr><td width="27%"><input type="hidden" id="skuRid" name="skuRid" value="' + goodsReceiptD.grdSkuRID + '"/><input type="hidden" id="grdRid" name="grdRid" value="' + goodsReceiptD.id + '"/>\n\
<input class="col-md-12 col-sm-12 col-xs-12" type="text" id="prdSkuName" name="prdSkuName" readonly="readonly"   value="' + goodsReceiptD.grdItemName + '"/></td>\n\
<td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="prdBatchNo" name="prdBatchNo" readonly="readonly" value="' + goodsReceiptD.grdBatchNo + '"/></td>\n\
<td width="10%"><input class="col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip"  type="text" id="prdExpiryDate" readonly="readonly" name="prdExpiryDate" title="MM / YYYY format" value="' + goodsReceiptD.grdExpiryDate + '"/></td>\n\
<td width="9%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="purQty" name="purQty" readonly="readonly" value="' + goodsReceiptD.grdReceiptQty + '" /><input type="hidden" id="grdOrderQty" name="grdOrderQty" value="' + goodsReceiptD.grdOrderQty + '" /></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="stk_qty" name="stk_qty" readonly="readonly" value="' + stockD.stkdQty + '" onBlur="receiptQtyValidation(\'dyn_table\', this)"/></td>\n\
<td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="retable_qty" name="retable_qty" readonly="readonly" value="' + goodsReceiptD.grdAcceptedQty + '"/><input type="hidden" id="grdAcceptedQty" name="grdAcceptedQty" value="' + goodsReceiptD.grdAcceptedQty + '" /></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="ret_qty" onkeypress="return dcomethealth.validation.isNumberKey(event)" name="ret_qty" onblur="calculation(this)" value=""/><input type="hidden" id="grdDiscount" name="grdDiscount" value="' + goodsReceiptD.grdDiscount + '" /><input type="hidden" id="grdTax" name="grdTax" value="' + goodsReceiptD.grdTax + '" /></td>\n\
<td width="1%" id="UOM"><select type="text" id="UOM" name="UOM"><option value="226">Nos</option></select></td><td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rate" name="rate" value="' + goodsReceiptD.grdRate + '" readonly/><input type="hidden" id="grdRateOld" name="grdRateOld" value="' + goodsReceiptD.grdRate + '" /><input type="hidden" id="grdMrpOld" name="grdMrpOld" value="' + goodsReceiptD.grdMrp + '" /><input type="hidden" id="prTax" name="prTax" value="0"/></td>\n\
<td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="prdNetAmount" name="prdNetAmount" value="" readonly="readonly"/></td></tr>');
                            });
                        });
                    });
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
                var searchParams = {"grhNo": queryString, "grhSupplierRID": $('#suppRid').val()};
                dcomethealth.GoodsReceiptResource.searchGRN(searchParams, function (data) {

                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.grhNo || ""),
                            value: item.grhNo || "",
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
    function editAllButtonOnClick() {
        var parentList = [];
        var parent = {};
        parent.prhSupplierRID = $('#suppRid').val();
        parent.prhSupplierName = $("#prhSupplierName").val();
        parent.prhReturnAmount = $("#net_tot_amount").val();
        parent.prhGatePassNo = $("#prhGatePassNo").val();
        parent.prhGrossAmount = $("#gross_amount").val();
        parent.prhTaxAmount = $("#net_tax").val();
        parent.prhRoundOffAmount = $("#round_off_amount").val();
//        parent.prhTypeIndex = $("#prhSupplierName").val();
//        parent.prhStateTypeIndex = $("#prhSupplierName").val();
        parent.prhGrhRID = $("#grnRid").val();
//        parent.prhMatirxType = $("#prhSupplierName").val();
        var parent2 = {};
        if ($("#grnRid").val() != "") {
            parent2.id = $("#grnRid").val();
        }
        parent2.grhSupplierName = $("#prhSupplierName").val();
        if ($("#suppRid").val() != "") {
            parent2.grhSupplierRID = $("#suppRid").val();
        }
        if ($("#grnNo").val() != "") {
            parent2.grhNo = $("#grnNo").val();
            parent2.grhSequenceNo = $("#grhSequenceNo").val();
            parent2.grhVersion = $("#grhVersion").val();
        }
        parent2.grhDcNo = $("#grhDcNo").val();
        parent2.grhDcDate = $("#grhdcdate").val();
        parent2.grhInvoiceNo = $("#grhInvoiceNo").val();
        parent2.grhUnitRID = dcomethealth.selectedunit;
        parent2.grhStatus = 1;
        parent2.grhState = 1;
        parent2.grhIsReturned = 1;
        parent2.grhInvoiceDate = $("#grhInvoiceDate").val();

        var child1List = [];
        var child2List = [];
        var gross = 0, net = 0, disc_total = 0, tax_total = 0;
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var child = {};
            child.prdSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRid').value;
            child.prdSkuName = dynTableGetNodeInRow(table.rows[i + 1], 'prdSkuName').value;
            child.prdBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'prdBatchNo').value;
//            child.prdBaseUomIndex = '2';
//            child.prdBaseUomDesc = 'PR';
            child.prdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'rate').value;
            child.prdPodQty = dynTableGetNodeInRow(table.rows[i + 1], 'retable_qty').value;
            child.prdQty = dynTableGetNodeInRow(table.rows[i + 1], 'ret_qty').value;
//            child.prdStatus = '1';
            child.prdExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'prdExpiryDate').value;
            child.prdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'prdNetAmount').value;
            child.prdMrp = dynTableGetNodeInRow(table.rows[i + 1], 'rate').value;
//                        child.prdAbatedMrp='12.46';
//                        child.prdTaxApplicabilityOn='1';
//                        child.prdTaxPerc='12.46';                        
            child.prdTaxAmount = 0;
            child.prdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'prdNetAmount').value;
            child.prdGrdRID = dynTableGetNodeInRow(table.rows[i + 1], 'grdRid').value;
            child1List.push(child);


            var goodsReceiptD = {};
            if (dynTableGetNodeInRow(table.rows[i + 1], 'grdRid').value != "" && parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'grdRid').value) != 0) {
                goodsReceiptD.id = dynTableGetNodeInRow(table.rows[i + 1], 'grdRid').value;
            }

            goodsReceiptD.grdSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'skuRid').value;
            goodsReceiptD.grdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'prdSkuName').value;
            goodsReceiptD.grdBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'prdBatchNo').value;
            goodsReceiptD.grdExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'prdExpiryDate').value;
            goodsReceiptD.grdOrderQty = dynTableGetNodeInRow(table.rows[i + 1], 'grdOrderQty').value;
            goodsReceiptD.grdReceiptQty = dynTableGetNodeInRow(table.rows[i + 1], 'purQty').value;

            goodsReceiptD.grdAcceptedQty = parseFloat(parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'grdAcceptedQty').value) - parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'ret_qty').value));
            goodsReceiptD.grdRate = dynTableGetNodeInRow(table.rows[i + 1], 'grdRateOld').value;
            goodsReceiptD.grdMrp = dynTableGetNodeInRow(table.rows[i + 1], 'grdMrpOld').value;
            goodsReceiptD.grdDiscount = dynTableGetNodeInRow(table.rows[i + 1], 'grdDiscount').value;
            goodsReceiptD.grdTax = dynTableGetNodeInRow(table.rows[i + 1], 'grdTax').value;

            var grdgross = ((parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'grdAcceptedQty').value) - parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'ret_qty').value)) * dynTableGetNodeInRow(table.rows[i + 1], 'grdRateOld').value);
            var disc_calc = parseFloat(grdgross) * (parseFloat(dynTableGetNodeInRow(table.rows[i + 1], 'grdDiscount').value) / 100);
            disc_total = parseFloat(disc_total) + parseFloat(disc_calc);
            var tax_calc = parseFloat(grdgross) * (parseFloat(dynTableGetNodeInRow(table.rows[i + 1], 'grdTax').value) / 100);
            tax_total = parseFloat(tax_total) + parseFloat(tax_calc);
            gross = parseFloat(gross) + parseFloat(grdgross);

            var net_row = parseFloat(grdgross) - parseFloat(disc_calc) + parseFloat(tax_calc);
            goodsReceiptD.grdNet = net_row.toFixed(2);
            net = parseFloat(net) + parseFloat(net_row);
            child2List.push(goodsReceiptD);
        }

        parent2.grhGrossAmount = gross.toFixed(2);
        parent2.grhDiscountAmount = disc_total.toFixed(2);
        parent2.grhTaxAmount = tax_total.toFixed(2);
        parent2.grhNetAmount = net.toFixed(0);
        parent2.goodsReceiptD = child2List;
        parentList.push(parent2)
        parent.purchaseReturnD = child1List;
        parent.goodsReceiptH = parentList;
        dcomethealth.InventoryResource.savepurchasereturn(parent)
                .done(function (data, textStatus, jqXHR) {
                    alert("Data Saved")
                    dcomethealth.util.loadpage('PurchaseReturn');
                    dcomethealth.util.base_init();
                    loadNotification();
                    //requestObj.field1 = data.primaryKey
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("failed")
        });
        //}
    }
    function refreshData() {
    }
    return {
        init: init,
        GrnAutocomplete: GrnAutocomplete,
        editAllButtonOnClick: editAllButtonOnClick,
        refreshData: refreshData
    }
}());

dcomethealth.PurchaseReturn.init();