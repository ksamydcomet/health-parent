var dcomethealth = dcomethealth || {};
var searchPO = {};
dcomethealth.GoodsReceipt = (function () {
    var id = "GoodsReceipt";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#grhDate').text(moment().format('DD-MM-YYYY'))
            var start = moment();
            var end = moment();
            $('#GrnDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $('#GrnDateRange').daterangepicker({
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
                $('#GrnDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            });
            autocomplete();
            viewGrnAll();
            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    grhSupplierName: "required",
                    grhDcNo: "required",
                    grhDcDate: "required",
                    grhInvoiceNo: "required",
                    grhInvoiceDate: "required"
                },
                messages: {
                    grhSupplierName: "Enter Supplier name",
                    grhDcNo: "Enter Dc no",
                    grhDcDate: "Enter Dc date",
                    grhInvoiceNo: "Enter Invoice No",
                    grhInvoiceDate: "Enter Invoice Date"
                },
                submitHandler: function (form) {
                    var ac_qty = document.getElementById('grdAcceptedQty').value;
                    var ord_qty = document.getElementById('grdOrderQty').value;
                    var rc_qty = document.getElementById('grdReceiptQty').value;
                    var mrp = document.getElementById('grdMrp').value;
                    var rate = document.getElementById('grdRate').value;
                    if (ord_qty < rc_qty) {
                        alert("Order Qty Is Less");
                        return  false;
                    }
                    if (mrp < rate) {
                        alert("Mrp Is less");
                        return false;
                    }
                    if (ac_qty <= 0) {
                        alert("Enter Accepted Quantity");
                        return false;
                    } else {
                        submit();
                    }
                }
            });
        });
    }
    function addFromPO() {
        if ($('#grhSupplierRid').val() != "") {
            $('#addDiv').removeClass('hidden');
            $("#po_tbody").empty();
            var searchObj = {"pohSupplierRID": $('#grhSupplierRid').val(), "pohGrnReceived": 0, "pohStatus": 4};
            dcomethealth.PurchaseOrderResource.searchPurchaseOrders(searchObj, function (data) {
                if (data !== undefined) {
                    searchPO = data;
                    $.each(data, function (pIdx, data) {
                        $("#po_tbody").append('<tr><td><input type="checkbox" id="checkedPO" name="checkedPO" onclick="dcomethealth.GoodsReceipt.calculation(this)"/>\n\
                        <input type="hidden" id="poRID" name="poRID" value="' + data.id + '"/><input type="hidden" id="checkValue" name="checkValue" value="0"/>\n\
                        <input type="hidden" id="POStatus" name="POStatus" value="' + data.pohStatus + '"/>\n\
                        <input type="hidden" id="pohGrossAmount" name="pohGrossAmount" value="' + data.pohGrossAmount + '"/>\n\
                        <input type="hidden" id="pohTaxAmount" name="pohTaxAmount" value="' + data.pohTaxAmount + '"/>\n\
                        <input type="hidden" id="pohDiscountAmount" name="pohDiscountAmount" value="' + data.pohDiscountAmount + '"/>\n\
                        <input type="hidden" id="pohNetAmount" name="pohNetAmount" value="' + data.pohNetAmount + '"/>\n\
                        <input type="hidden" id="pohDeliveryDate" name="pohDeliveryDate" value="' + data.pohDeliveryDate + '"/>\n\
                        <input type="hidden" id="pohTermsOfDelivery" name="pohTermsOfDelivery" value="' + data.pohTermsOfDelivery + '"/>\n\
                        <input type="hidden" id="createdDateTime" name="createdDateTime" value="' + data.createdDateTime + '"/></td><td>' + data.pohNumber + '</td></tr>');
                    });
                } else {
                    alert("Supplier does not have any PO");
                    dcomethealth.util.loadpage('GoodsReceipt');
                    dcomethealth.util.base_init();
                }
            });
        } else {
            alert("Enter Supplier");
            return false;
        }
    }
    function calculation(row) {
        var zero = dynTableGetNodeInRow(row, 'checkedPO');
        if (zero.checked) {
            dynTableGetNodeInRow(row, 'checkValue').value = 1;
        } else {
            dynTableGetNodeInRow(row, 'checkValue').value = 0;
        }
    }
    function submitPO() {
        $('#addDiv').addClass('hidden');
//        $('#poH_tbody').empty();
        $('#grn_tbody').empty();
        var table = document.getElementById('dyn_po_table');
        var table_length = table.rows.length;
        for (var Z = 0; Z <= table_length - 1; Z++) {
            if (parseInt(dynTableGetNodeInRow(table.rows[Z + 1], 'checkValue').value) === 1) {
                $.each(searchPO, function (pIdx, poH) {
                    if (parseInt(poH.id) === parseInt(dynTableGetNodeInRow(table.rows[Z + 1], 'poRID').value)) {
                        $.each(poH.purchaseOrderD, function (pIdx, poD) {
                            $('#grn_tbody').append('<tr><td width="1%"><i id="del" class="ace-icon fa fa-minus hidden" onclick="delete_row(this)"></i><input type="hidden" id="grdSkuRID" name="grdSkuRID" value="' + poD.podSkuRID + '"/><input type="hidden" id="grdRID" name="grdRID" value=""/><input type="hidden" id="poDRID" name="poDRID" value="' + poD.id + '"/><input type="hidden" id="podpohRID" name="podpohRID" value="' + poD.podPohRID + '"/><input type="hidden" id="pohRID" name="pohRID" value="' + poH.id + '"/></td>\n\
<td width="20%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdItemName" name="grdItemName" value="' + poD.podSkuName + '" readonly/></td>\n\
<td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdBatchNo" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" name="grdBatchNo" value="' + poD.podBatchNo + '"/></td>\n\
<td width="10%"><input type="text" id="grdExpiryDate" name="grdExpiryDate" class="col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip" title="MM / YYYY format" maxlenth="7" onkeypress="return dcomethealth.validation.ValidateDateEntry(event)" onkeyup="return dcomethealth.validation.ValidateDate(this, event.keyCode)" value="' + poD.podExpiryDate + '" readonly/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdOrderQty" name="grdOrderQty" maxlength="10" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + poD.podQty + '" readonly/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdReceiptQty" name="grdReceiptQty" maxlength="10" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value=""/><input type="hidden" id="podGRNReceivedQty" name="podGRNReceivedQty" value="' + poD.podGrnReceivedQty + '"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdAcceptedQty" name="grdAcceptedQty" maxlength="10" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" onchange="calculation(this)" value=""/></td>\n\
<td width="9%"><select class="col-md-10 col-sm-10 col-xs-10" id="grdUOM" name="grdUOM"><option value="226">Nos</option></select></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdRate" name="grdRate" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" value="' + poD.podRate + '" onchange="calculation(this)" readonly/></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdMrp" name="grdMrp" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" onchange="mrpValidation(\'dyn_table\', this)" value="' + poD.podRate + '"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdDiscount" name="grdDiscount" maxlength="3" onchange="dcomethealth.validation.isPercentaged(this)" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" value="' + poD.podDiscountPercentage + '" onchange="calculation(this)"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdTax" name="grdTax" value="' + poD.podTaxPercentage + '" maxlength="3" onchange="dcomethealth.validation.isPercentaged(this)" onkeypress="return dcomethealth.validationisDecimalKey(event, this)" onchange="calculation(this)" /></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdNet" name="grdNet" value="' + poD.podNetAmount + '" readonly="readonly" onBlur=""/></td><td width="1%"><i class="ace-icon fa fa-plus red" onclick="insert_row(\'dyn_table\', this)"></i></td></tr>');
                        });
                    }
                });
            }
        }
    }
    function getGrn(id) {
        var searchObj = {"id": id};
        dcomethealth.GoodsReceiptResource.searchGRN(searchObj, function (data) {
            $.each(data, function (pIdx, grh) {
                $("#grhSupplierName").val(grh.grhSupplierName);
                $("#grhSupplierRid").val(grh.grhSupplierRID);
                $("#grhDcNo").val(grh.grhDcNo);
                $("#grhNo").val(grh.grhNo);
                $("#grhSequenceNo").val(grh.grhSequenceNo);
                $("#grhVersion").val(grh.grhVersion);
                $("#grhDcDate").val(grh.grhDcDate);
                $("#grhInvoiceNo").val(grh.grhInvoiceNo);
                $("#grhInvoiceDate").val(grh.grhInvoiceDate);
                $("#grhGrossAmount").val(grh.grhGrossAmount);
                $("#grhDiscountAmount").val(grh.grhDiscountAmount);
                $("#grhTaxAmount").val(grh.grhTaxAmount);
//                $("#round_off_amount").val(grh.round_off_amount);
                $("#grhNetAmount").val(grh.grhNetAmount);
                $('#grn_tbody').empty();
                $.each(grh.goodsReceiptD, function (pIdx, grd) {
                    $('#grn_tbody').append('<tr><td width="1%"><i id="del" class="ace-icon fa fa-minus hidden" onclick="delete_row(this)"></i><input type="hidden" id="grdSkuRID" name="grdSkuRID" value="' + grd.grdSkuRID + '"/><input type="hidden" id="grdRID" name="grdRID" value="' + grd.id + '"/></td>\n\
<td width="20%"><input class="col-md-11 col-sm-11 col-xs-11" type="text" id="grdItemName" name="grdItemName" value="' + grd.grdItemName + '"/></td>\n\
<td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdBatchNo" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" name="grdBatchNo" value="' + grd.grdBatchNo + '"/></td>\n\
<td width="10%"><input type="text" id="grdExpiryDate" name="grdExpiryDate" class="col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip" title="MM / YYYY format" maxlenth="7" onkeypress="return dcomethealth.validation.ValidateDateEntry(event)" onkeyup="return dcomethealth.validation.ValidateDate(this, event.keyCode)" value="' + grd.grdExpiryDate + '"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdOrderQty" name="grdOrderQty" maxlength="10" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + grd.grdOrderQty + '" /></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdReceiptQty" name="grdReceiptQty" maxlength="10" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + grd.grdReceiptQty + '"/><input type="hidden" id="podGRNReceivedQty" name="podGRNReceivedQty" value=""/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdAcceptedQty" name="grdAcceptedQty" maxlength="10" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" onchange="calculation(this)" value="' + grd.grdAcceptedQty + '"/></td>\n\
<td width="9%"><select class="col-md-10 col-sm-10 col-xs-10" id="grdUOM" name="grdUOM"><option value="226">Nos</option></select></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdRate" name="grdRate" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" value="' + grd.grdRate + '" onchange="calculation(this)"/></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdMrp" name="grdMrp" maxlength="9" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" onChange="mrpValidation(\'dyn_table\', this)" value="' + grd.grdMrp + '"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdDiscount" name="grdDiscount" maxlength="3" onchange="dcomethealth.validation.isPercentaged(this)" onkeypress="return dcomethealth.validation.isDecimalKey(event, this)" value="' + grd.grdDiscount + '" onchange="calculation(this)"/></td>\n\
<td width="5%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdTax" name="grdTax" value="' + grd.grdTax + '" maxlength="3" onchange="dcomethealth.validation.isPercentaged(this)" onkeypress="return dcomethealth.validationisDecimalKey(event, this)" onchange="calculation(this)" /></td>\n\
<td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="grdNet" name="grdNet" value="' + grd.grdNet + '" readonly="readonly" onBlur=""/></td><td width="1%"><i class="ace-icon fa fa-plus red" onclick="insert_row(\'dyn_table\', this)"></i></td></tr>');
                });
            });
        });
    }
    function viewGrnAll() {
        var date = $('#GrnDateRangeSpan').html().split('-');
        var searchObj = {"grhFromDate": moment(date[0]), "grhToDate": moment(date[1]).add(1, 'days')};
        dcomethealth.GoodsReceiptResource.searchGRN(searchObj, function (data) {
            if (data != undefined) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $('#grn_data').empty();
                $.each(data, function (pIdx, Grns) {
                    var status = "";
                    if (Grns.grhStatus == 1) {
                        status = "GRN Created";
                    } else if (parseInt(Grns.grhStatus) === 2) {
                        status = "GRN Modified";
                    } else if (parseInt(Grns.grhStatus) === -1) {
                        status = "GRN Cancelled";
                    }
                    $('#grn_data').append('<tr onclick="modifyGrn(' + Grns.id + ',' + Grns.grhStatus + ')"><td>' + Grns.grhNo + '<input type="hidden" id="grnRid"/></td><td>' + Grns.grhSupplierName + '</td>\n\
<td>' + moment(Grns.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td><td>' + status + '</td></tr>');
                });
                $('#tab_grn_details').dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $('#tab_grn_details').dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function autocomplete() {
        var grdItemName;
        $("#" + id + " input[name='grdItemName']").autocomplete({
            select: function (event, ui) {
                grdItemName = ui.item.value;
                dynTableGetNodeInRow(this, 'grdSkuRID').value = ui.item.data.id;
//                $("#").val(ui.item.data.id);
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
                        var details = "<table><tr><td style='white-space:nowrap;padding-right:10px;'>CDE #:</td><td>" + item.skuName + "</td></tr>";
                        details += "</table>";
                        return {
                            label: (item.skuName || ""),
                            value: item.skuName || "",
                            name: (item.skuName || "") + (item.skuName && item.id ? " - " : "") + (item.id || ""),
                            details: details,
                            data: item
                        };
                    }), function (item, index) {
                        return index < 75;
                    }));
                });
            },
            minLength: 1
        });
        $("#" + id + " input[name='grhSupplierName']").autocomplete({
            select: function (event, ui) {
                var grhSupplierName = ui.item.value;
                $("#grhSupplierRid").val(ui.item.data.id);
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
                        var details = "<table><tr><td style='white-space:nowrap;padding-right:10px;'>CDE #:</td><td>" + item.supName + "</td></tr>";
                        details += "</table>";
                        return {
                            label: (item.supName || ""),
                            value: item.supName || "",
                            name: (item.supName || "") + (item.supName && item.id ? " - " : "") + (item.id || ""),
                            details: details,
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
    function submit() {
        var form = $("form");
        if (dcomethealth.GoodsReceipt.validateForm(form)) {
            if (parseInt($('#grhStatus').val()) !== 2) {
                var parent = {};
                if ($("#grhRid").val() != "") {
                    parent.id = $("#grhRid").val();
                }
                parent.grhSupplierName = $("#grhSupplierName").val();
                if ($("#grhSupplierRid").val() != "") {
                    parent.grhSupplierRID = $("#grhSupplierRid").val();
                }
                if ($("#grhNo").val() != "") {
                    parent.grhNo = $("#grhNo").val();
                    parent.grhSequenceNo = $("#grhSequenceNo").val();
                    parent.grhVersion = $("#grhVersion").val();
                }
                parent.grhDcNo = $("#grhDcNo").val();
                parent.grhDcDate = $("#grhDcDate").val();
                parent.grhInvoiceNo = $("#grhInvoiceNo").val();
                parent.grhUnitRID = dcomethealth.selectedunit;
                parent.grhStatus = 1;
                parent.grhState = 1;
                parent.grhIsReturned = 0;
                parent.grhInvoiceDate = $("#grhInvoiceDate").val();
                parent.grhGrossAmount = $("#grhGrossAmount").val();
                parent.grhDiscountAmount = $("#grhDiscountAmount").val();
                parent.grhTaxAmount = $("#grhTaxAmount").val();
                parent.grhNetAmount = $("#grhNetAmount").val();
                var childPOList = [];
                var tablePO = document.getElementById('dyn_po_table');
                var table_lengthPO = tablePO.rows.length;
                for (var i = 0; i < table_lengthPO - 1; i++) {
                    if (parseInt(dynTableGetNodeInRow(tablePO.rows[i + 1], 'checkValue').value) === 1) {
                        var parentPO = {};
                        if (dynTableGetNodeInRow(tablePO.rows[i + 1], 'poRID').value != "") {
                            parentPO.id = dynTableGetNodeInRow(tablePO.rows[i + 1], 'poRID').value;
                        }
                        parentPO.pohSupplierRID = $("#grhSupplierRid").val();
                        parentPO.pohFinanceYear = moment().format('YYYY');
                        parentPO.pohQuoteRID = '1';
                        parentPO.pohStatus = dynTableGetNodeInRow(tablePO.rows[i + 1], 'POStatus').value;
                        parentPO.pohGrossAmount = dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohGrossAmount').value;
                        parentPO.pohTaxAmount = dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohTaxAmount').value;
                        parentPO.pohDiscountAmount = dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohDiscountAmount').value;
//        parent.pohFreightAmount = '12.25';
                        parentPO.pohNetAmount = dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohNetAmount').value;
                        parentPO.pohRemarks = 'User Needed';
                        parentPO.pohType = 0;
                        parentPO.pohGrnReceivableUnitRID = dcomethealth.selectedunit;
                        var podList = [];
                        var tableGRN = document.getElementById('dyn_table');
                        var table_lengthGRN = tableGRN.rows.length;
                        for (var k = 0; k < table_lengthGRN - 1; k++) {
                            var purchaseorderD = {}
                            if (parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'pohRID').value) === parseInt(dynTableGetNodeInRow(tablePO.rows[i + 1], 'poRID').value)) {
                                if ((parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'podGRNReceivedQty').value) + parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdAcceptedQty').value))
                                        === parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdOrderQty').value)) {
                                    parentPO.pohGrnReceived = '1';
                                } else if ((parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'podGRNReceivedQty').value) + parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdAcceptedQty').value))
                                        > parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdOrderQty').value)) {
                                    alert("Accepted Qty is Greater than Previous GRN Qty");
                                    return false;
                                } else {
                                    parentPO.pohGrnReceived = '0';
                                }
                            }
                            if (parseInt(dynTableGetNodeInRow(tablePO.rows[i + 1], 'poRID').value) === parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'podpohRID').value)) {
                                if (dynTableGetNodeInRow(tableGRN.rows[k + 1], 'poDRID').value != "") {
                                    purchaseorderD.id = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'poDRID').value;
                                }
                                purchaseorderD.podQty = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdOrderQty').value;
                                purchaseorderD.podReceiptQty = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdOrderQty').value;
                                purchaseorderD.podGrnReceivedQty = parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'podGRNReceivedQty').value) + parseInt(dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdAcceptedQty').value);
                                purchaseorderD.podRate = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdRate').value;
                                purchaseorderD.podMrp = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdRate').value;
                                if (dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdTax').value == "") {
                                    purchaseorderD.podTaxPercentage = 0;
                                } else {
                                    purchaseorderD.podTaxPercentage = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdTax').value;
                                }
                                if (dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdDiscount').value == "") {
                                    purchaseorderD.podDiscountPercentage = 0;
                                } else {
                                    purchaseorderD.podDiscountPercentage = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdDiscount').value;
                                }
                                purchaseorderD.podNetAmount = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdNet').value;
                                purchaseorderD.podStatus = '1';
                                purchaseorderD.podRowInvalidated = 0;
                                purchaseorderD.podBatchNo = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdBatchNo').value;
                                purchaseorderD.podExpiryDate = dynTableGetNodeInRow(tableGRN.rows[k + 1], 'grdExpiryDate').value;
                                podList.push(purchaseorderD)
                            }
                        }
                        parentPO.pohStoreRID = '1';
//            parentPO.pohRoundOffAmount = '324';
                        if (dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohDeliveryDate').value != "" && dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohDeliveryDate').value != undefined) {
                            parentPO.pohDeliveryDate = moment(dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohDeliveryDate').value, 'DD-MM-YYYY').format('DD-MM-YYYY');
                        }
                        parentPO.pohTermsOfDelivery = dynTableGetNodeInRow(tablePO.rows[i + 1], 'pohTermsOfDelivery').value;
                        if (dynTableGetNodeInRow(tablePO.rows[i + 1], 'createdDateTime').value != "" && dynTableGetNodeInRow(tablePO.rows[i + 1], 'createdDateTime').value != undefined) {
                            parentPO.createdDateTime = dynTableGetNodeInRow(tablePO.rows[i + 1], 'createdDateTime').value;
                        }
                        parentPO.purchaseOrderD = podList;
                        childPOList.push(parentPO);
                    }
                }
                var child1List = [];
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var j = 0; j < table_length - 1; j++) {
                    var goodsReceiptD = {};
                    if (dynTableGetNodeInRow(table.rows[j + 1], 'grdRID').value != "" && parseInt(dynTableGetNodeInRow(table.rows[j + 1], 'grdRID').value) != 0) {
                        goodsReceiptD.id = dynTableGetNodeInRow(table.rows[j + 1], 'grdRID').value;
                    }
                    goodsReceiptD.grdSkuRID = dynTableGetNodeInRow(table.rows[j + 1], 'grdSkuRID').value;
                    goodsReceiptD.grdItemName = dynTableGetNodeInRow(table.rows[j + 1], 'grdItemName').value;
                    goodsReceiptD.grdBatchNo = dynTableGetNodeInRow(table.rows[j + 1], 'grdBatchNo').value;
                    goodsReceiptD.grdExpiryDate = dynTableGetNodeInRow(table.rows[j + 1], 'grdExpiryDate').value;
                    goodsReceiptD.grdOrderQty = dynTableGetNodeInRow(table.rows[j + 1], 'grdOrderQty').value;
                    goodsReceiptD.grdReceiptQty = dynTableGetNodeInRow(table.rows[j + 1], 'grdReceiptQty').value;
                    goodsReceiptD.grdAcceptedQty = dynTableGetNodeInRow(table.rows[j + 1], 'grdAcceptedQty').value;
                    goodsReceiptD.grdRate = dynTableGetNodeInRow(table.rows[j + 1], 'grdRate').value;
                    goodsReceiptD.grdMrp = dynTableGetNodeInRow(table.rows[j + 1], 'grdMrp').value;
                    goodsReceiptD.grdDiscount = dynTableGetNodeInRow(table.rows[j + 1], 'grdDiscount').value;
                    goodsReceiptD.grdTax = dynTableGetNodeInRow(table.rows[j + 1], 'grdTax').value;
                    goodsReceiptD.grdNet = dynTableGetNodeInRow(table.rows[j + 1], 'grdNet').value;
                    child1List.push(goodsReceiptD);
                }
                parent.purchaseOrderH = childPOList;
                parent.goodsReceiptD = child1List;
                dcomethealth.GoodsReceiptResource.saveGRN(parent)
                        .done(function (data, textStatus, jqXHR) {
                            alert("Saved");
                            dcomethealth.util.loadpage('GoodsReceipt');
                            dcomethealth.util.base_init();
                            loadNotification();
                            //requestObj.field1 = data.primaryKey
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("failed");
                    // TO DO
                });
            } else {
                alert("Modified GRN Cannot be saved");
                return false;
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
        addFromPO: addFromPO,
        calculation: calculation,
        submitPO: submitPO,
        getGrn: getGrn,
        viewGrnAll: viewGrnAll,
        validateForm: validateForm,
        autocomplete: autocomplete,
        refreshData: refreshData

    };
}());
dcomethealth.GoodsReceipt.init();