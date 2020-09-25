var dcomethealth = dcomethealth || {};
dcomethealth.ItemMaster = (function () {
    var id = "ItemMaster", itemObj = {};         
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('.consumableStatus').toggles({on: false}, "isConsumable", 0);
            $('.billableStatus').toggles({on: false}, "isBillable", 0);
            $('.consignmentStatus').toggles({on: false}, "isConsignment", 0);
            $('.activeStatus').toggles({on: true}, "isActive", 1);
            dcomethealth.MasterResource.searchSkus({}, function (data) {
                if (!!data) {
                    itemObj = data;
                }
            });
            searchDetails();
            supplier_autocomplete();
            generic_autocomplete();
            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    skuName: "required",
                    mrp: "required",
                    skuCode: "required"
                },
                messages: {
                    skuName: "Enter Item Name",
                    mrp: "Enter Mrp",
                    skuCode: "Enter Item Code"
                },
                submitHandler: function (form) {
                    if ($("#skuRID").val() === "") {
                        var check = false;
                        if (!!itemObj) {
                            $.each(itemObj, function (pIdx, t_sku_m) {
                                if ($("#skuCode").val() == t_sku_m.skuCode || $("#skuName").val() == t_sku_m.skuName) {
                                    check = true;
                                }
                            });
                        }
                        if (check) {
                            alert("Enter New Item");
                            return false;
                        } else {
                            submit();
                        }
                    } else {
                        if (!!itemObj) {
                            $.each(itemObj, function (pIdx, t_sku_m) {
                                if ($("#skuRID").val() != t_sku_m.id) {
                                    if ($("#skuCode").val() == t_sku_m.skuCode || $("#skuName").val() == t_sku_m.skuName) {
                                        check = true;
                                    }
                                }
                            });
                        }
                        if (check) {
                            alert("Enter New Item");
                            return false;
                        } else {
                            submit();
                        }
                }
                }
            });
        });
    }
    function searchdetails(val) {
        $.each(dcomethealth.s_sku, function (index, s_sku) {
            if (s_sku.id == parseInt(val)) {
                $("#ItemMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                dcomethealth.ItemMaster.setDetails(s_sku)
            }
        });
    }
    function setDetails(data) {
        $("#itemMasterTbody").empty();
        document.getElementById("skuName").value = data.skuName;
        document.getElementById("skuRID").value = parseInt(data.id);
        document.getElementById("skuGenericName").value = data.skuGenericName;
        document.getElementById("skuGroupRID").value = data.skuGroupRID;
        document.getElementById("skuDdPurUomIndex").value = data.skuDdPurUomIndex;
        document.getElementById("skuDdBaseUomIndex").value = data.skuDdBaseUomIndex;
        document.getElementById("skuDdSaleUomIndex").value = data.skuDdSaleUomIndex;        
        document.getElementById("sku_sub_group").value = data.skuGroupParentRID;
        document.getElementById("skuCode").value = data.skuCode;
        document.getElementById("mrp").value = parseFloat(data.skuLastMrp);
        if (parseFloat(data.skuReorderLevel) === "" || isNaN(parseFloat(data.skuReorderLevel))) {
        } else {
            document.getElementById("rol").value = parseFloat(data.skuReorderLevel);
        }
        if (parseFloat(data.skuMinOrderLevel) === "" || isNaN(parseFloat(data.skuMinOrderLevel))) {
        } else {
            document.getElementById("minOL").value = parseFloat(data.skuMinOrderLevel);
        }
//        document.getElementById("supplierName").value = data.skuName;
        if (!!data.skuSupplierMapList) {
            $.each(data.skuSupplierMapList, function (idx, skuSupp) {
                $("#itemMasterTbody").append('<tr><td><i class="ace-icon fa fa-minus" onclick="delete_row(this)"></i><input type="hidden" id="suppRid" name="suppRid" value="' + skuSupp.ssmSupRID + '"/><input type="hidden" id="ssmRid" name="ssmRid" value="' + skuSupp.id + '"/></td>\n\
<td><input class="col-md-11 col-sm-11 col-xs-12" type="text" id="supplierName" name="supplierName" value="' + skuSupp.ssmSupName + '"/></td>\n\
<td><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="costPrice" maxlength="9" name="costPrice" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + skuSupp.ssmPurchaseRate + '" /></td>\n\
<td><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="skuMinOrderQty" maxlength="9" name="skuMinOrderQty" onkeypress="return dcomethealth.validation.isNumberKey(event)" value="' + skuSupp.ssmSupMinOrderQty + '"/></td>\n\
<td><i class="ace-icon fa fa-plus blue" onclick="insert_row(\'dyn_table\', this)"></i></td></tr>');
            });
        }
        this.supplier_autocomplete();
        var active = parseInt(data.skuIsActive);
        if (active === 1) {
            $('.activeStatus').toggles({on: true}, "isActive");
        } else {
            $('.activeStatus').toggles({on: false}, "isActive");
        }
        var isConsignment = parseInt(data.skuConsignmentItem);
        if (isConsignment === 1) {
            $('.consignmentStatus').toggles({on: true}, "isConsignment");
        } else {
            $('.consignmentStatus').toggles({on: false}, "isConsignment");
        }
        var isConsumable = parseInt(data.skuIsConsumable);
        if (isConsumable === 1) {
            $('.consumableStatus').toggles({on: true}, "isConsumable");
        } else {
            $('.consumableStatus').toggles({on: false}, "isConsumable");
        }
        var isBillable = parseInt(data.skuIsBillable);
        if (isBillable === 1) {
            $('.billableStatus').toggles({on: true}, "isBillable");
        } else {
            $('.billableStatus').toggles({on: false}, "isBillable");
        }
    }
    function searchDetails() {
        var search = {"skuIsActive": 1};
        dcomethealth.MasterResource.searchSkus(search, function (data) {
            $("#tbody_head").empty();
            $.each(dcomethealth.s_sku = data, function (index, s_sku) {
                $("#tbody_head").append('<tr onclick="dcomethealth.ItemMaster.searchdetails(' + s_sku.id + ')" id="tr_head"><td>' + s_sku.skuName + '<input type="hidden" value="' + s_sku.id + '"/></td><td>' + s_sku.skuCode + '</td></tr>');
            });
            $("#itemsTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
            $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
            $('.dataTables_length select').addClass('form-control');
        });
    }
    function generic_autocomplete() {
        $("#" + this.id + " input[name='skuGenericName']").autocomplete({
            select: function (event, ui) {
                var gmName = ui.item.value;
                $('#gmRid').val(parseInt(ui.item.data.gmRID));
//                $('#PatientName').val(ui.item.data.patFullName);
//                var c = ui.item.data.patDob.split('-');
//                $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
//                $('#PatientGender option[value="' + ui.item.data.patGenderIndex + '"]').prop('selected', true);
//                $('#patientMobile').val(ui.item.data.patPhoneNo);
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
                var searchParams = {"gmName": queryString};
                dcomethealth.MasterResource.searchGeneric(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.gmName || ""),
                            value: (item.gmName || ""),
                            name: (item.gmName || ""),
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
    function supplier_autocomplete() {
        $("#" + id + " input[name='supplierName']").autocomplete({
            select: function (event, ui) {
                var supName = ui.item.value;
                $('#suppRid').val(parseInt(ui.item.data.id));
//                $('#PatientName').val(ui.item.data.patFullName);
//                var c = ui.item.data.patDob.split('-');
//                $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
//                $('#PatientGender option[value="' + ui.item.data.patGenderIndex + '"]').prop('selected', true);
//                $('#patientMobile').val(ui.item.data.patPhoneNo);
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
                            value: (item.supName || ""),
                            name: (item.supName || ""),
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
        if (dcomethealth.ItemMaster.validateForm(form)) {
            var parent = {};
            var RID = $("#skuRID").val();
            if (RID == "") {
                RID = null;
            }
            parent.id = RID;
            parent.skuCode = $("#skuCode").val();
            parent.skuName = $("#skuName").val();
            parent.skuGenericName = $("#skuGenericName").val();
            parent.skuGroupRID = $("#skuGroupRID").val();
            parent.skuGroupParentRID = $("#sku_sub_group").val();
            parent.skuDdPurUomIndex = $("#skuDdPurUomIndex").val();
            parent.skuDdBaseUomIndex = $("#skuDdBaseUomIndex").val();
            parent.skuDdSaleUomIndex = $("#skuDdSaleUomIndex").val();
            
            parent.skuIsActive = $("#isActive").val();
            if (($("#minOL").val() !== "")) {
                parent.skuMinOrderLevel = $("#minOL").val();
            }
            if ($("#s_conv_factor").val() != "") {
                parent.skuDdSaleUomDesc = $("#s_conv_factor").val();
            }
            if ($("#p_conv_factor").val() != "") {
                parent.skuDdPurUomConv = $("#p_conv_factor").val();
            }
            if ($("#rol").val() != "") {
                parent.skuReorderLevel = $("#rol").val();
            }
            if ($("#mrp").val() != "") {
                parent.skuLastMrp = $("#mrp").val();
            }
            if ($("#isConsignment").val() === "undefined") {
                parent.skuConsignmentItem = 0;
            } else {
                parent.skuConsignmentItem = $("#isConsignment").val();
            }
            if ($("#isConsumable").val() === "undefined") {
                parent.skuIsConsumable = 0;
            } else {
                parent.skuIsConsumable = $("#isConsumable").val();
            }
            if ($("#isBillable").val() === "undefined") {
                parent.skuIsBillable = 0;
            } else {
                parent.skuIsBillable = $("#isBillable").val();
            }
            var child = [];
            var table = document.getElementById('dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var skuSupMap = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'supplierName').value != "") {
                    skuSupMap.ssmSupName = dynTableGetNodeInRow(table.rows[i + 1], 'supplierName').value;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'ssmRid').value != "") {
                        skuSupMap.id = dynTableGetNodeInRow(table.rows[i + 1], 'ssmRid').value;
                    }
                    skuSupMap.ssmSupRID = dynTableGetNodeInRow(table.rows[i + 1], 'suppRid').value;
                    skuSupMap.ssmIsPreffered = 1;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'skuMinOrderQty').value != "") {
                        skuSupMap.ssmSupMinOrderQty = dynTableGetNodeInRow(table.rows[i + 1], 'skuMinOrderQty').value;
                    }
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'costPrice').value != "") {
                        skuSupMap.ssmPurchaseRate = dynTableGetNodeInRow(table.rows[i + 1], 'costPrice').value;
                    }
                    skuSupMap.ssmRowInvalidated = 2;
                    child.push(skuSupMap);
                }
            }
            parent.skuSupplierMapList = child;
            dcomethealth.MasterResource.saveItem(parent)
                    .done(function (data, textStatus, jqXHR) {
                        dcomethealth.util.loadpage('ItemMaster');
                        alert("Data saved")
                        //requestObj.field1 = data.primaryKey
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed")
                // TO DO
            });
        }
    }
    function  validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        searchdetails: searchdetails,
        setDetails: setDetails,
        searchDetails: searchDetails,
        generic_autocomplete: generic_autocomplete,
        supplier_autocomplete: supplier_autocomplete,
        validateForm: validateForm,
        submit: submit,
        refreshData: refreshData
    };
}());
dcomethealth.ItemMaster.init();
