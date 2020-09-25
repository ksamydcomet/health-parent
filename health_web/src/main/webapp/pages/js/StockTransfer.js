var dcomethealth = dcomethealth || {};
dcomethealth.StockTransfer = (function () {
    title: "Stock Transfer"

    var id = "StockTransfer";
    function init() {

        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autocomplete();
            getEntity();
            getUnit();

            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    item_name: "required",
                    smdQty: "required",
                    toUnitSelect: {
                        select: true
                    }
                },
                messages: {
                    item_name: "Enter Item Name",
                    smdQty: "Enter Quantity"

                },
                submitHandler: function (form) {
                    if (parseInt($("#availableQty").val()) < parseInt($("#smdQty").val())) {
                        alert("Availabl Qty is Less");
                        return  false;
                    }
                    submit();
                }
            });
            jQuery.validator.addMethod('select', function (value) {
                return (value != '0');
            }, "Select To Unit");
        });
    }
//            $(".edit-all-button").off("click").on("click", function () {
//                var table = document.getElementById('dyn_table');
//                var table_length = table.rows.length;
//                for (var i = 0; i < table_length - 1; i++) {
//                    var itemname = dynTableGetNodeInRow(table.rows[i + 1], 'item_name').value;
//                    var mrp_row = dynTableGetNodeInRow(table.rows[i + 1], 'smdMrp').value;
//                    var smd_qty_row = dynTableGetNodeInRow(table.rows[i + 1], 'smdQty').value;
//                    if (itemname == "") {
//                        alert("Enter ItemName");
//                        setTimeout(function () {
//                            dynTableGetNodeInRow(table.rows[i + 1], 'item_name').focus();
//                        }, 1);
//                        return false;
//                    } else if (mrp_row == "" || parseInt(mrp_row) == 0) {
//                        alert("Enter Mrp");
//                        setTimeout(function () {
//                            dynTableGetNodeInRow(table.rows[i + 1], 'smdMrp').focus();
//                        }, 1);
//                        return false;
//                    } else if (smd_qty_row == "" || parseInt(smd_qty_row) == 0) {
//                        alert("Enter Transfer Qty");
//                        setTimeout(function () {
//                            dynTableGetNodeInRow(table.rows[i + 1], 'smdQty').focus();
//                        }, 1);
//                        return false;
//                    }
//                }
//                editAllButtonOnClick();
//            });

    function autocomplete() {
        var skuName;
        $("#" + id + " input[name='item_name']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                var Auto = this;
                dynTableGetNodeInRow(this, 'smdSkuRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'smdBatchNo').innerHTML = "--BatchNo--";
                dynTableGetNodeInRow(this, 'smdExpiryDate').innerHTML = "--ExpDate--";
                dynTableGetNodeInRow(this, 'smdMrp').innerHTML = "--MRP--";
                dynTableGetNodeInRow(this, 'availableQty').innerHTML = "--Stock--";
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
                            var opt3 = document.createElement("option");
                            opt3.value = item.stockDChildList[0].stkdQty;
                            opt3.text = item.stockDChildList[0].stkdQty;
                            dynTableGetNodeInRow(Auto, 'smdSkuRID').value = item.stkSkuRID;
                            dynTableGetNodeInRow(Auto, 'smdBatchNo').options.add(opt);
                            dynTableGetNodeInRow(Auto, 'smdExpiryDate').options.add(opt1);
                            dynTableGetNodeInRow(Auto, 'smdMrp').options.add(opt2);
                            dynTableGetNodeInRow(Auto, 'availableQty').options.add(opt3);
                        }), function (item, index) {
                            return index < 75;
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
    function  onBatchChange(elem) {
        var itemname = dynTableGetNodeInRow(elem, 'item_name').value;
        var batchNo = dynTableGetNodeInRow(elem, 'smdBatchNo').value;
        dynTableGetNodeInRow(elem, 'smdExpiryDate').innerHTML = "";
        dynTableGetNodeInRow(elem, 'smdMrp').innerHTML = "";
        dynTableGetNodeInRow(elem, 'availableQty').innerHTML = "";
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
                dynTableGetNodeInRow(elem, 'smdExpiryDate').options.add(opt1);
                dynTableGetNodeInRow(elem, 'smdMrp').options.add(opt2);
                dynTableGetNodeInRow(elem, 'availableQty').options.add(opt3);
                ;
            }), function (item, index) {
                return index < 75;
            });
        });
    }
    function onExpiryChange(elem) {
        var itemname = dynTableGetNodeInRow(elem, 'item_name').value;
        var batchNo = dynTableGetNodeInRow(elem, 'smdBatchNo').value;
        var expDate = dynTableGetNodeInRow(elem, 'smdExpiryDate').value;
        dynTableGetNodeInRow(elem, 'smdMrp').innerHTML = "";
        dynTableGetNodeInRow(elem, 'availableQty').innerHTML = "";
        var searchParams = {"stkSkuName": itemname, "stkBatchNo": batchNo, "stkExpiryDate": expDate};
        dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
            $.grep($.map(data, function (item) {
                var opt2 = document.createElement("option");
                opt2.value = item.stkMrp;
                opt2.text = item.stkMrp;
                var opt3 = document.createElement("option");
                opt3.value = item.stockDChildList[0].stkdQty;
                opt3.text = item.stockDChildList[0].stkdQty;
                dynTableGetNodeInRow(elem, 'smdMrp').options.add(opt2);
                dynTableGetNodeInRow(elem, 'availableQty').options.add(opt2);
            }), function (item, index) {
                return index < 75;
            });
        });
    }
    function getEntity() {
        dcomethealth.DataDictionaryResource.getEntities(function (data) {
            $.each(data, function (pIdx, item) {
                if (dcomethealth.loginuser.entityRid === item.id) {
                    var opt1 = document.createElement("option");
                    opt1.value = item.id;
                    opt1.text = item.entityName;
                    document.getElementById('toEntitySelect').options.add(opt1);
                }
            })
        });
    }
    function getUnit() {
        var searchParams = {};
        dcomethealth.MasterResource.searchUnit(searchParams, function (data) {
            $.each(data, function (pIdx, item) {
                if (dcomethealth.selectedunit != item.id) {
                    var opt1 = document.createElement("option");
                    opt1.value = item.id;
                    opt1.text = item.unitName;
                    document.getElementById('toUnitSelect').options.add(opt1);
                }
            })
        });
    }
    function serializeFormData(form) {
        var o = {};
        var a = form.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            }
        });
        return o;
    }
    function submit() {
        var form = $("form");
        if (dcomethealth.StockTransfer.validateForm(form)) {
            var parent = {};
            parent.smhSourceEntityRID = dcomethealth.loginuser.entityRid;
            parent.smhDestinationEntityRID = $("#toEntitySelect").val();
            parent.smhSourceUnitRID = dcomethealth.selectedunit;
            parent.smhDestinationUnitRID = $("#toUnitSelect").val();
            parent.smhRemarks = $("#remarks").val();
            parent.smhTransportModeIndex = $("#transMode").val();
            parent.smhTransportModeDesc = $("#transMode").text();
            parent.smhTransportDetails = $("#transport_details").val();
            parent.smhPackingDetails = $("#packing_details").val();
            parent.smhTotalQty = $("#availableQty").val();
            parent.smhTransType = 0;
//        parent.smhMatrixType = ;
            var child1List = [];
            var table = document.getElementById('dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var child = {};
                child.smdSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'smdSkuRID').value;
                child.smdBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'smdBatchNo').value;
                child.smdExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'smdExpiryDate').value;
                child.smdMrp = dynTableGetNodeInRow(table.rows[i + 1], 'smdMrp').value;
                child.smdRate = dynTableGetNodeInRow(table.rows[i + 1], 'smdMrp').value;
                if (dynTableGetNodeInRow(table.rows[i + 1], 'uom_name').value != "") {
                    child.smdUomID = dynTableGetNodeInRow(table.rows[i + 1], 'uom_name').value;
                } else {
                    alert("Enter UOM");
                    return false;
                }
                child.smdTransferQty = dynTableGetNodeInRow(table.rows[i + 1], 'smdQty').value;
//      child.smdAcceptQty = $("#packing_details").val();
//      child.smdRejectQty = $("#packing_details").val();
                child.smdTransferStatus = 1;
                child1List.push(child);
            }
            parent.stockMovementD = child1List;
            dcomethealth.InventoryResource.savestockmovement(parent)
                    .done(function (data, textStatus, jqXHR) {
                        alert("Saved");
                        dcomethealth.util.loadpage('StockTransfer');
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
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
        getEntity: getEntity,
        getUnit: getUnit,
        serializeFormData: serializeFormData,
        submit: submit,
        validateForm: validateForm,
        refreshData: refreshData
    }
}());
dcomethealth.StockTransfer.init();