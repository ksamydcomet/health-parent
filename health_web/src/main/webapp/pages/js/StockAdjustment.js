var dcomethealth = dcomethealth || {};
dcomethealth.StockAdjustment = (function () {
    title: "Stock Adjustment"
    var id = "StockAdjustment", unitObj = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autoComplete();
            dcomethealth.MasterResource.searchUnit({}, function (data) {
                if (!!data) {
                    unitObj = data;
                }
            });
            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    itemname: "required"
                },
                messages: {
                    itemname: "Enter ItemName"
                },
                submitHandler: function (form) {
                    submit();

                }
            });
        });
    }
    function autoComplete() {
        var skuName, unitName = '';
        $("#" + id + " input[name='itemname']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                $('#skuRid').val(ui.item.data.id);
                $('#stkAdj_tbody').empty();
                var searchParams = {"stkSkuName": skuName};
                dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
                    if (data != undefined) {
                        var uom = "";
                        $.grep($.map(data, function (item) {
                            if (parseInt(item.stkUomIndex) == parseInt(28)) {
                                uom = 'Nos';
                            }
                            $.each(unitObj, function (ix, unit) {
                                if (item.stkUnitRID == unit.id) {
                                    unitName = unit.unitName;
                                }
                            });
                            $('#stkAdj_tbody').append('<tr><td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="sadUnit" name="sadUnit" value="' + unitName + '" readonly/></td>\n\
                                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="sadBatchNo" name="sadBatchNo" value="' + item.stkBatchNo + '" readonly/></td>\n\
                                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip" type="text" id="sadExpiryDate" name="sadExpiryDate" title="MM/YYYY format" value="' + item.stkExpiryDate + '" readonly/></td>\n\
                                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="sadStkQty" name="sadStkQty" value="' + item.stockDChildList[0].stkdQty + '" readonly/></td>\n\
                                    <td width="10%"><input id="uom" name="uom" class="col-md-12 col-sm-12 col-xs-12" value="' + uom + '" readonly style="border: none;"/></td>\n\
                                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="sadRate" name="sadRate" value="' + item.stkRate + '" readonly/></td>\n\
                                    <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="sadMrp" name="sadMrp" value="' + item.stkMrp + '" readonly/></td>\n\
                                    <td width="10%"><input type="hidden" id="sadQtyHidden" value="' + item.stockDChildList[0].stkdQty + '"/><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="sadQty" name="sadQty" onkeypress ="return dcomethealth.validation.isNumberKey(event)" value="' + item.stockDChildList[0].stkdQty + '"/></td></tr>');
                            dcomethealth.datatypes.init($("#" + id));
                        }), function (item, index) {
                            return index < 75;
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
                var searchParams = {"skuName": queryString};
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
    function submit() {
        var form = $("form");
        if (dcomethealth.StockAdjustment.validateForm(form)) {
            var parent = {};
            parent.sahUnitRID = dcomethealth.selectedunit;
            parent.sahRemarksDesc = $('#reason').val();
            var child1List = [];
            var table = document.getElementById('dyn_table');
            var table_length = table.rows.length;
            var changes = 0;
            for (var i = 0; i < table_length - 1; i++) {

                if (parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'sadQtyHidden').value) !== parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'sadQty').value)) {
                    changes++;
                    var child = {};
                    child.sadSkuRID = $('#skuRid').val();
                    child.sadSkuName = $('#itemname').val();
                    child.sadBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'sadBatchNo').value;
                    child.sadExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'sadExpiryDate').value;
                    child.sadRate = dynTableGetNodeInRow(table.rows[i + 1], 'sadRate').value;
                    child.sadMrp = dynTableGetNodeInRow(table.rows[i + 1], 'sadMrp').value;
                    child.sadQty = dynTableGetNodeInRow(table.rows[i + 1], 'sadQty').value;
                    child1List.push(child);
                }

            }
            if (parseInt(changes) === 0) {
                alert("There is no changes Made");
                return false;
            }
            // LOOP End
            parent.stockAdjustmentD = child1List;

            dcomethealth.InventoryResource.savestockadjustment(parent)
                    .done(function (data, textStatus, jqXHR) {
                        alert(parseInt(changes) + " Item successfully Adjusted");
                        dcomethealth.util.loadpage('StockAdjustment');
                        dcomethealth.util.base_init();
                        loadNotification();
                        //requestObj.field1 = data.primaryKey
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
                // TO DO
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
        autoComplete: autoComplete,
        validateForm: validateForm,
        submit: submit,
        refreshData: refreshData

    }
}());
dcomethealth.StockAdjustment.init();