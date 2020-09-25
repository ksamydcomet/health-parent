var dcomethealth = dcomethealth || {};
dcomethealth.ServiceMaster = (function () {
    var id = "ServiceMaster", idArray = [], serviceClearVar = 0, serviceDelList = [], itemDelList = [];
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#page-header').innerHTML = 'Service Master';
            dcomethealth.MasterResource.searchUnit({"unitIsServiceProvider": 1, "unitValid": 1}, function (data) {
                $.each(data, function (pIdx, unit) {
                    $('#bsUnit').append('<option value="' + unit.id + '">' + unit.unitName + '</option>');
                });
            });
            resource_autocomplete();
            autocompleteService();
            autocompleteItem();
            $("select option").each(function () { //For remove duplicates
                $(this).siblings("[value='" + this.value + "']").remove();
            });
            $('.schedulableStatus').toggles({on: false}, "isShedulable", 0);
            $('.manualTestStatus').toggles({on: false}, "isManual", 0);
            $('.resultStatus').toggles({on: false}, "isResultExpected", 0);
            $('.conversionStatus').toggles({on: false}, "isConversion", 0);
            $('.priceBillingStatus').toggles({on: false}, "BillPriceEditable", 0);
            $('.priceOrderingStatus').toggles({on: false}, "OrderPriceEditable", 0);
            $('.qtyBillingStatus').toggles({on: false}, "BillQtyEditable", 0);
            $('.qtyOrderingStatus').toggles({on: false}, "OrderQtyEditable", 0);
            $('.activeStatus').toggles({on: true}, "isActive");
            $('.sampleStatus').toggles({on: false}, "isSampleRequired", 0);
            $("#serviceTest").select2({width: 'resolve'});
            fetchServiceList();
            $("#edit_user_form").validate({
                rules: {
                    bsCode: "required",
                    bsName: "required",
                    bsServiceType: "required",
                    bsCategory: {
                        selectcheck: true
                    }
                },
                messages: {
                    bsCode: "Enter Service Code",
                    bsName: "Enter Service Name",
                    bsServiceType: "Enter Service Type"
                },
                submitHandler: function (form) {
                    if ($("#price").val() == "") {
                        alert("Enter Service Price");
                        return false;
                    } else {
                        if ($("#emergencyPrice").val() == "" || $("#emergencyPrice").val() == 0) {
                            alert("Enter Emergency Price");
                            return false;
                        } else {
                            if ($("#serviceRid").val() === "") {
                                var searchObj = {};
                                var check = false;
                                dcomethealth.MasterResource.searchServices(searchObj, function (data) {
                                    $.each(data, function (pIdx, t_service_master) {
                                        if ($("#bsCode").val() === t_service_master.bsCode || $("#bsName").val() == t_service_master.bsName) {
                                            check = true;
                                        }
                                    });
                                    if (check === true) {
                                        alert("Enter New Service");
                                        return false;
                                    } else {
                                        $("#submit").attr("disabled", true);
                                        submit();
                                    }
                                });
                            } else {
                                submit();
                            }
                        }
                    }
                }
            });
            jQuery.validator.addMethod('selectcheck', function (value) {
                return (value != '0');
            }, "Select Category");
        });
    }
    function serviceWiseGroup() {
        $("#bsGroup").empty();
        $("#bsGroup").append('<option value=""></option>');
        $.each(dcomethealth.dDictVal, function (i, val) {
            if (parseInt($("#bsServiceType").val()) === val.ddictParentIndex && parseInt($("#bsServiceType").val()) != 0) {
                $("#bsGroup").append('<option value="' + val.id + '">' + val.ddictValue + '</option>');
            }
        });
    }
    function fetchServiceList() {
        $("#tbody_head").empty();
        $.each(dcomethealth.serviceMaster, function (index, serviceM) {
            if (serviceM.bsServiceActive == 1) {
                $("#tbody_head").append('<tr onclick="dcomethealth.ServiceMaster.showServiceDetail(' + serviceM.id + ')" id="tr_head">\n\
                <td>' + serviceM.bsCode + '<input type="hidden" value="' + serviceM.id + '"/></td><td>' + serviceM.bsName + '</td></tr>');
            }
        });
        $("#servicesTable").dataTable();
        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
        $('.dataTables_length select').addClass('form-control');
    }
    function showServiceDetail(val) {
        $.each(dcomethealth.serviceMaster, function (index, serviceM) {
            if (serviceM.id == parseInt(val)) {
                $("#ServiceHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(serviceM);
            }
        });
    }
    function addTest() {
        $('#testDiv').addClass('hidden');
        $("#packageDetailsDiv").addClass("hidden");
        if ($("#bsCategory option:selected").text() === "Profile") {
            $('#testDiv').removeClass('hidden');
            $("#packageDetailsDiv").addClass("hidden");
            var options = "";
            $.each(dcomethealth.serviceMaster, function (index, serviceM) {
                if (serviceM.bsCategory != "undefined") {
                    $.each(dcomethealth.dDictVal, function (i, val) {
                        if (serviceM.bsServiceType == val.id && parseInt($("#bsUnit").val()) == serviceM.bsUnit) { //For loading unit wise service 
                            if (val.ddictValue == "Laboratory") {
                                $.each(dcomethealth.dDictVal, function (i, val) {
                                    if (parseInt(serviceM.bsCategory) == parseInt(val.id)) {
                                        if (val.ddictValue == "Service" || val.ddictValue == "Test") {
                                            if (val.ddictValue != "Profile") {
                                                options += '<option value="' + serviceM.id + '">' + serviceM.bsName + '</option>';
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            });
            $("#serviceTest").html(options);
        } else if ($("#bsCategory option:selected").text() == "Package") {
            $('#testDiv').addClass('hidden');
            $("#packageDetailsDiv").removeClass("hidden");
        }
    }
    function autocompleteItem() {
        $("#" + id + " input[name='packageItemName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                dynTableGetNodeInRow(this, 'packageItemRID').value = ui.item.data.id;
                var sid = dynTableGetNodeInRow(this, 'packageItemRID').value;
                check(sid, dynTableGetNodeInRow(this), 2);
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
                var searchParams = {"skuName": queryString, "skuIsConsumable": 1};
                dcomethealth.MasterResource.searchSkus(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data && data.length > 0) {
                        check = false;
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
                    } else {
                        if (check == true) {
                            $('#total_pmd').val('');
                            checkValid(2)
                            return false;
                        }
                    }
                });
            },
            minLength: 2
        });
    }
    function check(pserviceRID, row, value) {
        var pre, tableid, count = 0, postfix;
        if (value == 1) {
            pre = "packageService";
            tableid = "package_service_dyn_table";
            postfix = " service"
        }
        if (value == 2) {
            pre = "packageItem";
            tableid = "package_item_dyn_table";
            postfix = " item"
        }
        var table = document.getElementById(tableid);
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var pserviceRid = dynTableGetNodeInRow(table.rows[i + 1], pre + 'RID').value;
            if (parseInt(pserviceRid) == parseInt(pserviceRID)) {
                count++;
                if (count == 2) {
                    alert("Already existing" + postfix);
                    setTimeout(function () {
                        dynTableGetNodeInRow(table.rows[1], pre + 'RID').value = "";
                        dynTableGetNodeInRow(table.rows[1], pre + 'Name').value = "";
                        return false;
                    }, 20)
                }
            }
        }
    }
    function checkValid(value) {
        var pre, tableid;
        if (value == 1) {
            pre = "packageService";
            tableid = "package_service_dyn_table";
        }
        if (value == 2) {
            pre = "packageItem";
            tableid = "package_item_dyn_table";
        }
        var table = document.getElementById(tableid);
        if (dynTableGetNodeInRow(table.rows[1], pre + 'RID').value == "" || dynTableGetNodeInRow(table.rows[1], pre + 'RID').value == 0 || serviceClearVar == 0) {
            dynTableGetNodeInRow(table.rows[1], pre + 'RID').value = "";
            dynTableGetNodeInRow(table.rows[1], pre + 'Name').value = "";
        }
    }
    function autocompleteService() {
        $("#" + id + " input[name='packageServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                dynTableGetNodeInRow(this, 'packageServiceRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'packageServiceIsSchedulable').value = ui.item.data.bsServiceSchedule;
                check(dynTableGetNodeInRow(this, 'packageServiceRID').value, dynTableGetNodeInRow(this), 1);
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data && data.length > 0) {
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
                            checkValid(1)
                            return false;
                        }
                    }
                });
            },
            minLength: 2
        });
    }
    function deleteRowService(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('package_service_dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var pServiceDelMap = {};
            if (table_length > 2) {
                if (dynTableGetNodeInRow(elem, 'packageServiceRID').value != "" || dynTableGetNodeInRow(elem, 'packageServiceRID').value != 0) {
                    if (dynTableGetNodeInRow(elem, 'psRid').value != "") {
                        pServiceDelMap.psRid = dynTableGetNodeInRow(elem, 'psRid').value;
                    }
                    pServiceDelMap.psServiceRid = dynTableGetNodeInRow(elem, 'packageServiceRID').value;
                    pServiceDelMap.psServiceName = dynTableGetNodeInRow(elem, 'packageServiceName').value;
                    pServiceDelMap.psServiceQty = dynTableGetNodeInRow(elem, 'packageServiceQty').value;
                    pServiceDelMap.psServiceIsSchedulable = dynTableGetNodeInRow(elem, 'packageServiceIsSchedulable').value;
                    pServiceDelMap.psServiceIsActive = 0;
                    serviceDelList.push(pServiceDelMap);
                    dynTableDeleteRow(x);
                    return false;
                }
            }
        }
    }
    function deleteRowItem(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('package_item_dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var pItemDelMap = {};
            if (table_length > 2) {
                if (dynTableGetNodeInRow(elem, 'packageItemRID').value != "" || dynTableGetNodeInRow(elem, 'packageItemRID').value != 0) {
                    if (dynTableGetNodeInRow(elem, 'piRid').value != "") {
                        pItemDelMap.piRid = dynTableGetNodeInRow(elem, 'piRid').value;
                    }
                    pItemDelMap.piItemRid = dynTableGetNodeInRow(elem, 'packageItemRID').value;
                    pItemDelMap.piItemName = dynTableGetNodeInRow(elem, 'packageItemName').value;
                    pItemDelMap.piItemQty = dynTableGetNodeInRow(elem, 'packageItemQty').value;
                    pItemDelMap.piItemIsActive = 0;
                    itemDelList.push(pItemDelMap);
                    dynTableDeleteRow(x);
                    return false;
                }
            }
        }
    }
    function resource_autocomplete() {
        $("#" + id + " input[name='rsmDoctorName']").autocomplete({
            select: function (event, ui) {
                dynTableGetNodeInRow(this, 'rsmDoctorName').value = ui.item.value;
                dynTableGetNodeInRow(this, 'rsmDocRid').value = ui.item.data.id;
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
                var searchParams = {"staffName": queryString, "staffCategory": 237};
                dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.staffName || ""),
                            value: (item.staffName || ""),
                            name: (item.staffName || ""),
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
    function populateFields(data) {
        console.log(data);
        idArray = [];
        document.getElementById("bsName").value = data.bsName;
        document.getElementById("serviceRid").value = parseInt(data.id);
        document.getElementById("bsCode").value = data.bsCode;
        $.each(dcomethealth.dDictVal, function (i, val) {
            if (parseInt(data.bsGroup) === val.id) {
                $("#bsGroup").append('<option value="' + data.bsGroup + '">' + val.ddictValue + '</option>');
            }
        });
        document.getElementById("bsGroup").value = data.bsGroup;
        document.getElementById("bsCategory").value = data.bsCategory;
        document.getElementById("bsUnit").value = parseInt(data.bsUnit);
        document.getElementById("bsServiceType").value = data.bsServiceType;
        var isSchedulable = parseInt(data.bsServiceSchedule);
        if (isSchedulable === 1) {
            $('.schedulableStatus').toggles({on: true}, "isShedulable");
        } else {
            $('.schedulableStatus').toggles({on: false}, "isShedulable");
        }
        var isManual = parseInt(data.bsServiceManual);
        if (isManual === 1) {
            $('.manualTestStatus').toggles({on: true}, "isManual");
        } else {
            $('.manualTestStatus').toggles({on: false}, "isManual");
        }
        var isResultExpected = parseInt(data.bsIsresultExpected);
        if (isResultExpected === 1) {
            $("#labDiv").removeClass("hidden");
            $('.resultStatus').toggles({on: true}, "isResultExpected");
        } else {
            $('.resultStatus').toggles({on: false}, "isResultExpected");
        }

        var isConversion = parseInt(data.bsServiceConversion);
        if (isConversion === 1) {
            $('.conversionStatus').toggles({on: true}, "isConversion");
        } else {
            $('.conversionStatus').toggles({on: false}, "isConversion");
        }

        var priceatbill = parseInt(data.bsPriceatbill);
        if (priceatbill === 1) {
            $('.priceBillingStatus').toggles({on: true}, "BillPriceEditable");
        } else {
            $('.priceBillingStatus').toggles({on: false}, "BillPriceEditable");
        }

        var priceatorder = parseInt(data.bsPriceatorder);
        if (priceatorder === 1) {
            $('.priceOrderingStatus').toggles({on: true}, "OrderPriceEditable");
        } else {
            $('.priceOrderingStatus').toggles({on: false}, "OrderPriceEditable");
        }

        var qtyatbill = parseInt(data.bsQtyatbilling);
        if (qtyatbill === 1) {
            $('.qtyBillingStatus').toggles({on: true}, "BillQtyEditable");
        } else {
            $('.qtyBillingStatus').toggles({on: false}, "BillQtyEditable");
        }
        var qtyatorder = parseInt(data.bsQtyatOrdering);
        if (qtyatorder === 1) {
            $('.qtyOrderingStatus').toggles({on: true}, "OrderQtyEditable");
        } else {
            $('.qtyOrderingStatus').toggles({on: false}, "OrderQtyEditable");
        }
        var isActive = parseInt(data.bsServiceActive);
        if (isActive === 1) {
            $('.activeStatus').toggles({on: true}, "isActive");
        } else {
            $('.activeStatus').toggles({on: false}, "isActive");
        }
        if (parseFloat(data.bPrice) !== "undefined") {
            $("#price").val(data.bPrice);
        }
        if (parseFloat(data.bEprice) !== "undefined") {
            document.getElementById("emergencyPrice").value = parseFloat(data.bEprice);
        }
        if (!!data.labServiceExtnList) {
            $("#serviceMasterTbody").empty();
        }
        if (!!data.labServiceExtnList) {
            $.each(data.labServiceExtnList, function (idx, labServEx) {
                var lsGender;
                if (parseInt(labServEx.lsGenderType) === 1) {
                    lsGender = "Male";
                }
                if (parseInt(labServEx.lsGenderType) === 2) {
                    lsGender = "Female";
                }
                if (parseInt(labServEx.lsGenderType) === 3) {
                    lsGender = "Transgender";
                }
                $("#serviceMasterTbody").append('<tr><td><i class="ace-icon fa fa-minus" class="hidden" onclick="delete_row(this)"></i></td>\n\
                <td width="25%"><input class="col-md-12 col-sm-10 col-xs-12" type="text" id="MethodName" name="MethodName" value="' + labServEx.lsProcessTechType + '"/><input type="hidden" id="id" name="id" value="' + labServEx.id + '"/></td>\n\
                <td width="24%"><input class="col-md-12 col-sm-10 col-xs-12" type="text" id="GroupName" name="GroupName" value="' + labServEx.lsGroupName + '"/></td>\n\
                <td width="6%"><input class="col-md-12 col-sm-11 col-xs-12" type="text" id="fromAge" maxlength="9" name="fromAge" onkeypress="return dcomethealth.validation.isNumberKey(event)" value="' + labServEx.lsAgeMinValue + '" /></td>\n\
                <td width="6%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="toAge" maxlength="9" name="toAge" onkeypress="return dcomethealth.validation.isNumberKey(event)" value="' + labServEx.lsAgeMaxValue + '"/></td>\n\
                <td width="10%"><select class="col-md-12 col-sm-12 col-xs-12" id="Gender" maxlength="9" name="Gender"><option value="' + parseInt(labServEx.lsGenderType) + '">' + lsGender + '</option></select></td>\n\
                <td width="6%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="fromrange" maxlength="9" name="fromrange" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + labServEx.lsRangeMinValue + '"/></td>\n\
                <td width="6%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="toRange" maxlength="9" name="toRange" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + labServEx.lsRangeMaxValue + '"/></td>\n\
                <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="Description" maxlength="100" name="Description" onkeypress="return dcomethealth.validation.validateDateEntry(event)" value="' + labServEx.lsDescription + '"/></td>\n\
                <td width="8%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="Uom" maxlength="9" name="Uom" value="' + labServEx.lsUom + '"/></td>\n\
                <td><i class="ace-icon fa fa-plus blue" onclick="insert_row(\'dyn_table\', this)"></i></td></tr>');
            });
        }
        if (!!data.resourceServiceMaps) {
            $("#rsmTbody").empty();
            $.each(data.resourceServiceMaps, function (rdx, rsmList) {
                $("#rsmTbody").append('<tr><td><i id="serviceRMapDel" class="ace-icon fa fa-minus hidden" onclick="delete_row_resource_map(this)"></i><input type="hidden" id="rsmRid" name="rsmRid" value="' + rsmList.rsmRid + '"/></td>\n\
<td width="40%"><input class="col-md-11 col-sm-10 col-xs-12" type="text" id="rsmDoctorName" name="rsmDoctorName" value="' + rsmList.rsmDoctorName + '" onkeypress="return dcomethealth.validation.ValidateAlpha(event);" onchange="isExistResourceCheck(this)"/><input type="hidden" id="rsmDocRid" name="rsmDocRid" value="' + rsmList.rsmDocRid + '"/></td>\n\
<td width="20%"><input class="col-md-11 col-sm-11 col-xs-12" type="text" id="rsmNormalPrice" maxlength="9" name="rsmNormalPrice" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + rsmList.rsmNormalPrice + '"/></td>\n\
<td width="20%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rsmEmergencyPrice" maxlength="9" name="rsmEmergencyPrice" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + rsmList.rsmEmergencyPrice + '"/></td>\n\
<td width="20%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rsmDoctorShare" maxlength="2" name="rsmDoctorShare" onkeypress="return dcomethealth.validation.isNumberKey(event)" value="' + rsmList.rsmDoctorShare + '"/></td>\n\
<td><i class="ace-icon fa fa-plus blue" onclick="insert_row_resourceMap(\'dyn_table_Resource_map\', this)"></i></td></tr>');
            });
        } else {
            $("#rsmTbody").empty();
            $("#rsmTbody").append('<tr><td><i id="serviceRMapDel" class="ace-icon fa fa-minus hidden" onclick="delete_row_resource_map(this)"></i><input type="hidden" id="rsmRid" name="rsmRid" value=""/></td>\n\
<td width="40%"><input class="col-md-11 col-sm-10 col-xs-12" type="text" id="rsmDoctorName" name="rsmDoctorName" value="" onkeypress="return dcomethealth.validation.ValidateAlpha(event);" onchange="isExistResourceCheck(this)"/><input type="hidden" id="rsmDocRid" name="rsmDocRid" value=""/></td>\n\
<td width="20%"><input class="col-md-11 col-sm-11 col-xs-12" type="text" id="rsmNormalPrice" maxlength="9" name="rsmNormalPrice" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value=""/></td>\n\
<td width="20%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rsmEmergencyPrice" maxlength="9" name="rsmEmergencyPrice" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value=""/></td>\n\
<td width="20%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="rsmDoctorShare" maxlength="2" name="rsmDoctorShare" onkeypress="return dcomethealth.validation.isNumberKey(event)" value=""/></td>\n\
<td><i class="ace-icon fa fa-plus blue" onclick="insert_row_resourceMap(\'dyn_table_Resource_map\', this)"></i></td></tr>');
            dcomethealth.ServiceMaster.resource_autocomplete();
        }
        if (!!data.servicePriceTypeList) {
            $("#srPriceTbody").empty();
            var srPriceTypeName = '';
            $.each(data.servicePriceTypeList, function (idx, sevicePriceType) {
                $.each(dcomethealth.dDictVal, function (ifx, val) {
                    if (parseInt(sevicePriceType.srPriceType) == val.id) {
                        srPriceTypeName = val.ddictValue;
                    }
                });
                $("#srPriceTbody").append('<tr><td><i class="ace-icon fa fa-minus hidden" onclick="deleteRowSrPrice(this)" id="serviceRMapDel"></i> <input id="srPriceRid" name="srPriceRid" type="hidden" value="' + sevicePriceType.id + '" /></td><td width="50%"><select class = "col-md-11 col-sm-11 col-xs-11 dcomet-c-s_ddict_SERVICE_POINT_TYPE-list" id="srPriceType" name="srPriceType" onchange="checkExistType(this)"> <option value="' + sevicePriceType.srPriceType + '" >' + srPriceTypeName + ' </option></select></td>="return dcomethealth.validation.isDecimalKey(event)" maxlength = "9" > </td><td width="50%">      <input id="srPriceServiceRid" value="' + sevicePriceType.srPriceServiceRid + '" name = "srPriceServiceRid" type="hidden"><input id="srPriceAmount" name="srPriceAmount" value="' + sevicePriceType.srPriceAmount + '" class="col-md-12 col-sm-12 col-xs-12" onkeypress="return dcomethealth.validation.isDecimalKey(event)" ></td>\n\
                <td><i class="ace-icon fa blue fa-plus" onclick="insertRowServicePriceType(\'dynTableServicePriceType\',this)"></i></td></tr>');
            });
        }
        if (!!data.packageServiceMap) {
            $("#package_service_tbody").empty();
            $("#package_service_tbody").append('<tr style="background: "><td width="3%"><i id="packageSerDel" style="color: royalblue" class="fa fa-minus-circle hidden"onclick="dcomethealth.ServiceMaster.deleteRowService(this)"></i></td>\n\
                    <td width="35%"><input type="text" id="packageServiceName" name="packageServiceName" value=""  placeholder="Enter Service Name" class="col-md-11 col-sm-11 col-xs-11"/><input type="hidden" id="packageServiceRID" name="packageServiceRID" value=""/>\n\
                    <input type="hidden" id="psRid" name="psRid" value=""/><input type="hidden" id="packageBdGroupRID" name="packageBdGroupRID"/><input type="hidden" id="packageServicePrice" name="packagesServicePrice"/></td>\n\
                    <td width="5%"><input type="hidden" id="packageServiceIsSchedulable" value="0"/><input id="packageServiceQty" type="text" value = "1"value="" name="packageServiceQty" style="text-align:right" onchange="validQty(this,2)" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                    <td width="15%"><i style="color: royalblue" class="fa fa-plus-square"   id="packageServicePlus"  onclick="insert_row_Package(\'package_service_dyn_table\', this,1)"></i></td> <td width="10%" style="visibility: hidden"></td></tr>')

            $.each(data.packageServiceMap, function (ifx, serviceList) {
                $("#package_service_tbody").append('<tr style="background: "><td width="3%"><i id="packageServiceDel"  style="color: royalblue" class="fa fa-minus-circle "  onclick="dcomethealth.ServiceMaster.deleteRowService(this,\'package_service_dyn_table\')"></i></td>\n\
                       <td width="35%"><input type="text" id="packageServiceName" readOnly  name="packageServiceName" value="' + serviceList.psServiceName + '" placeholder="Enter ServiceName" class="col-md-11 col-sm-11 col-xs-11"/><input type="hidden" id="packageServiceRID" name="packageServiceRID" value="' + serviceList.psServiceRid + '"/>\n\
                        <input type="hidden" id="psRid" name="psRid" value="' + serviceList.psRid + '"/><input type="hidden" id="packageBdGroupRID" name="packageBdGroupRID"/><input type="hidden" id="packageServicePrice" name="packagesServicePrice"/></td>\n\
                  <td width="5%"><input type="hidden" id="packageServiceIsSchedulable" value="' + serviceList.psServiceIsSchedulable + '"/> <input id="packageServiceQty" type="text" value="' + serviceList.psServiceQty + '" name="packageServiceQty" style="text-align:right" onchange="validQty(this,2)" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\ \n\\n\
                  <td width="15%"><i style="color: royalblue"   id="serPlus"  class="fa fa-plus-square hidden"  onclick="insert_row_Package(\'package_service_dyn_table\', this)"></i></td><td width="10%" style="visibility: hidden"></td></tr>')
                autocompleteService();
            });
        }
        if (!!data.packageitemMap) {
            $("#package_item_tbody").empty();
            $("#package_item_tbody").append('<tr style="background: "><td width="3%"><i id="packageItemDel" style="color: royalblue" class="fa fa-minus-circle hidden" onclick="dcomethealth.ServiceMaster.deleteRowItem(this)"></i></td>\n\
                <td width="35%"><input type="text" id="packageItemName" name="packageItemName"value="" placeholder="Enter Drug Name" class="col-md-11 col-sm-11 col-xs-11"/><input type="hidden" id="packageItemRID" name="packageItemRID" value=""/><input type="hidden" id="piRid" name="piRid"value=""/><input type="hidden" id="packageItemPrice" name="packageItemPrice"/></td>\n\
                <td width="5%"><input id="packageItemQty" type="text" value = "1"value="" name="packageItemQty"  onchange="validQty(this,1)" style="text-align:right" maxlength="3"onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="col-md-12 col-sm-12col-xs-12"></td>\n\
                <td width="15%"><i style="color: royalblue" class="fa fa-plus-square"  id="packageItemPlus"  onclick="insert_row_Package(\'package_item_dyn_table\',this,2)"></i></td></tr>')
            $.each(data.packageitemMap, function (ifx, itemList) {
                $("#package_item_tbody").append('<tr style="background: "><td width="3%"><i id="packageItemDel" style="color: royalblue" class="fa fa-minus-circle "  onclick="dcomethealth.ServiceMaster.deleteRowItem(this)"></i></td>\n\
                 <td width="35%"><input type="text" id="packageItemName" readOnly  name="packageItemName"value="' + itemList.piItemName + '" placeholder="Enter Drug Name"class="col-md-11 col-sm-11 col-xs-11"/><input type="hidden" id="packageItemRID" name="packageItemRID" value="' + itemList.piItemRid + '"/><input type="hidden" id="piRid" name="piRid"value="' + itemList.piRid + '"/><input type="hidden" id="packageItemPrice" name="packageItemPrice"/></td>\n\
                 <td width="5%"><input id="packageItemQty" type="text" value="' + itemList.piItemQty + '" name="packageItemQty"  onchange="validQty(this,1)" style="text-align:right" maxlength="3"onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="col-md-12 col-sm-12col-xs-12"></td>\n\\n\\n\
                 <td width="15%"><i style="color: royalblue"  id="itemplus" class="fa fa-plus-square hidden"  onclick="insert_row_Package(\'package_item_dyn_table\',this)"></i></td><td width="10%" style="visibility: hidden"></td></tr>')
                autocompleteItem();
            });
        }

        var isSampleRequired = parseInt(data.bSIsSampleRequired);
        if (isSampleRequired === 1) {
            $('.sampleStatus').toggles({on: true}, "isSampleRequired");
        } else {
            $('.sampleStatus').toggles({on: false}, "isSampleRequired");
        }
        addTest();
        var serviceTestList = [];
        $.each(dcomethealth.dDictVal, function (ifx, val) {
            if (parseInt(data.bsCategory) == val.id) {
                if (val.ddictValue === "Profile") {
                    if (!!data.profileServiceList) {
                        $.each(dcomethealth.serviceMaster, function (index, serMaster) {
                            $.each(data.profileServiceList, function (idx, profileMap) {
                                if (parseInt(profileMap.prServiceRid) == parseInt(serMaster.id)) {
                                    if (profileMap.prIsActive == 1 && dcomethealth.loginuser.entityRid == profileMap.prEntityRid) {
                                        var serviceMasterObj = {}, existServiceObj = {};
                                        existServiceObj["prMapId"] = profileMap.id;
                                        existServiceObj["serviceRid"] = profileMap.prServiceRid;
                                        existServiceObj["serviceName"] = profileMap.prServiceName;
                                        idArray.push(existServiceObj);
                                        serviceMasterObj["id"] = profileMap.prServiceRid;
                                        serviceMasterObj["text"] = serMaster.bsName;
                                        serviceTestList.push(serviceMasterObj);
                                    }
                                }
                            });
                        });
                    }
                }
            }
        });
        $("#s2id_serviceTest").select2('data', serviceTestList);
    }
    function submit() {
        var form = $("form");
        if (validateForm(form)) {
            var parent = {};
            var RID = $("#serviceRid").val();
            if (RID === "") {
                RID = null;
            }
            parent.id = RID;
            parent.bsCode = $("#bsCode").val();
            parent.bsName = $("#bsName").val();
            parent.bsUnit = $("#bsUnit").val();
            parent.bsServiceType = $("#bsServiceType").val();
            parent.bsCategory = $("#bsCategory").val();
            parent.bsGroup = $("#bsGroup").val();
            parent.bsServiceActive = $("#isActive").val();
            if ($("#isShedulable").val() == "undefined") {
                parent.bsServiceSchedule = 0;
            } else {
                parent.bsServiceSchedule = $("#isShedulable").val();
            }
            if ($("#isManual").val() == "undefined") {
                parent.bsServiceManual = 0;
            } else {
                parent.bsServiceManual = $("#isManual").val();
            }
            if ($("#isConversion").val() == "undefined") {
                parent.bsServiceConversion = 0;
            } else {
                parent.bsServiceConversion = $("#isConversion").val();
            }
            if ($("#OrderPriceEditable").val() == "undefined") {
                parent.bsPriceatorder = 0;
            } else {
                parent.bsPriceatorder = $("#OrderPriceEditable").val();
            }
            if ($("#BillPriceEditable").val() == "undefined") {
                parent.bsPriceatbill = 0;
            } else {
                parent.bsPriceatbill = $("#BillPriceEditable").val();
            }
            if ($("#BillQtyEditable").val() === "undefined") {
                parent.bsQtyatbilling = 0;
            } else {
                parent.bsQtyatbilling = $("#BillQtyEditable").val();
            }
            if ($("#OrderQtyEditable").val() == "undefined") {
                parent.bsQtyatOrdering = 0;
            } else {
                parent.bsQtyatOrdering = $("#OrderQtyEditable").val();
            }
            if ($("#isResultExpected").val() == "undefined") {
                parent.bsIsresultExpected = 0;
            } else {
                parent.bsIsresultExpected = $("#isResultExpected").val();
            }
            if ($("#isSampleRequired").val() == "undefined") {
                parent.bSIsSampleRequired = 0;
            } else {
                parent.bSIsSampleRequired = $("#isSampleRequired").val();
            }
            if ($("#price").val() !== "") {
                parent.bPrice = $("#price").val();
            } else {
                parent.bPrice = 0;
            }
            if ($("#emergencyPrice").val() !== "") {
                parent.bEprice = $("#emergencyPrice").val();
            } else {
                parent.bEprice = 0;
            }
            var child = [];
            if (parseInt($("#isResultExpected").val()) === 1) {
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var LabSerExtn = {};
                    LabSerExtn.lsServiceName = $("#bsName").val();
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'id').value !== "") {
                        LabSerExtn.id = dynTableGetNodeInRow(table.rows[i + 1], 'id').value;
                    }
                    LabSerExtn.lsProcessTechType = dynTableGetNodeInRow(table.rows[i + 1], 'MethodName').value;
                    LabSerExtn.lsGroupName = dynTableGetNodeInRow(table.rows[i + 1], 'GroupName').value;
                    LabSerExtn.lsAgeMinValue = dynTableGetNodeInRow(table.rows[i + 1], 'fromAge').value;
                    LabSerExtn.lsAgeMaxValue = dynTableGetNodeInRow(table.rows[i + 1], 'toAge').value;
                    LabSerExtn.lsGenderType = dynTableGetNodeInRow(table.rows[i + 1], 'Gender').value;
                    LabSerExtn.lsRangeMinValue = dynTableGetNodeInRow(table.rows[i + 1], 'fromrange').value;
                    LabSerExtn.lsRangeMaxValue = dynTableGetNodeInRow(table.rows[i + 1], 'toRange').value;
                    LabSerExtn.lsDescription = dynTableGetNodeInRow(table.rows[i + 1], 'Description').value;
                    LabSerExtn.lsUom = dynTableGetNodeInRow(table.rows[i + 1], 'Uom').value;
                    child.push(LabSerExtn);
                }
            }
            var rsm = [], check = 0;
            var table = document.getElementById('dyn_table_Resource_map');
            var table_length_rm = table.rows.length;
            if (table_length_rm > 0) {
                for (var i = 0; i < table_length_rm - 1; i++) {
                    if (i == 0) {
                        var dName = dynTableGetNodeInRow(table.rows[i + 1], 'rsmDoctorName').value;
                        if (dName != "") {
                            check = 1;
                        } else {
                            check = 0;
                        }
                    }
                    if (parseInt(check) == 1) {
                        var ResourceServiceMap = {};
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'rsmRid').value != "") {
                            ResourceServiceMap.rsmRid = dynTableGetNodeInRow(table.rows[i + 1], 'rsmRid').value;
                        }
                        ResourceServiceMap.rsmDocRid = dynTableGetNodeInRow(table.rows[i + 1], 'rsmDocRid').value;
                        var RID = $("#serviceRid").val();
                        if (RID === "") {
                            RID = null;
                        }
                        ResourceServiceMap.rsmServiceRid = RID;
                        ResourceServiceMap.rsmDoctorName = dynTableGetNodeInRow(table.rows[i + 1], 'rsmDoctorName').value;
                        ResourceServiceMap.rsmServiceName = $('#bsName').val();
                        ResourceServiceMap.rsmNormalPrice = dynTableGetNodeInRow(table.rows[i + 1], 'rsmNormalPrice').value;
                        ResourceServiceMap.rsmEmergencyPrice = dynTableGetNodeInRow(table.rows[i + 1], 'rsmEmergencyPrice').value;
                        ResourceServiceMap.rsmDoctorShare = dynTableGetNodeInRow(table.rows[i + 1], 'rsmDoctorShare').value;
                        rsm.push(ResourceServiceMap);
                    }
                }
            }
            var srPriceTypeList = [];
            var table = document.getElementById('dynTableServicePriceType');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var servicePriceTypeObj = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'srPriceType').value != 0) {
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'srPriceRid').value !== "") {
                        servicePriceTypeObj.id = dynTableGetNodeInRow(table.rows[i + 1], 'srPriceRid').value;
                    }
                    servicePriceTypeObj.srPriceType = dynTableGetNodeInRow(table.rows[i + 1], 'srPriceType').value;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'srPriceAmount').value != 0 && !!dynTableGetNodeInRow(table.rows[i + 1], 'srPriceAmount').value) {
                        servicePriceTypeObj.srPriceAmount = dynTableGetNodeInRow(table.rows[i + 1], 'srPriceAmount').value;
                    } else {
                        alert("Enter Price Amount");
                        return false;
                    }
                    srPriceTypeList.push(servicePriceTypeObj);
                } else {
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'srPriceType').value == 0) {
                        alert("Enter Price Type");
                        return false;
                    }
                }
            }
            if ($("#bsCategory option:selected").text() == "Package") {
                var itemList = [];
                var table = document.getElementById('package_item_dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var pItemMap = {};
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRID').value != "") {
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'piRid').value != "") {
                            pItemMap.piRid = dynTableGetNodeInRow(table.rows[i + 1], 'piRid').value;
                        }
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRID').value != "") {
                            pItemMap.piItemRid = dynTableGetNodeInRow(table.rows[i + 1], 'packageItemRID').value;
                        }
                        pItemMap.piItemName = dynTableGetNodeInRow(table.rows[i + 1], 'packageItemName').value;
                        pItemMap.piItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'packageItemQty').value;
                        pItemMap.piItemIsActive = 1;
                        itemList.push(pItemMap);
                    }
                }
                var serviceList = [];
                var table = document.getElementById('package_service_dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var pServiceMap = {};
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceRID').value != "") {
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'psRid').value != "") {
                            pServiceMap.psRid = dynTableGetNodeInRow(table.rows[i + 1], 'psRid').value;
                        }
                        if (dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceRID').value != "") {
                            pServiceMap.psServiceRid = dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceRID').value;
                        }
                        pServiceMap.psServiceName = dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceName').value;
                        pServiceMap.psServiceQty = dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceQty').value;
                        alert(dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceIsSchedulable').value);
                        pServiceMap.psServiceIsSchedulable = dynTableGetNodeInRow(table.rows[i + 1], 'packageServiceIsSchedulable').value;
                        pServiceMap.psServiceIsActive = 1;
                        serviceList.push(pServiceMap);
                    }
                }
                if (serviceDelList.length > 0) {
                    $.each(serviceDelList, function (indx, delList) {
                        var delservicelist = {};
                        delservicelist.psRid = delList.psRid;
                        delservicelist.psServiceRid = delList.psServiceRid;
                        delservicelist.psServiceQty = delList.psServiceQty;
                        delservicelist.psServiceName = delList.psServiceName;
                        delservicelist.psServiceIsActive = delList.psServiceIsActive;
                        delservicelist.psServiceIsSchedulable = delList.psServiceIsSchedulable;
                        serviceList.push(delservicelist);
                    });
                }
                if (itemDelList.length > 0) {
                    $.each(itemDelList, function (indx, delList) {
                        var itemDellisit = {};
                        itemDellisit.piRid = delList.piRid;
                        itemDellisit.piItemRid = delList.piItemRid;
                        itemDellisit.piItemQty = delList.piItemQty;
                        itemDellisit.piItemName = delList.piItemName;
                        itemDellisit.piItemIsActive = delList.piItemIsActive;
                        itemList.push(itemDellisit);
                    });
                }
                parent.packageServiceMap = serviceList;
                parent.packageitemMap = itemList;
            }

            if (child.length > 0) {
                parent.labServiceExtnList = child;
            }
            if (srPriceTypeList.length > 0) {
                parent.servicePriceTypeList = srPriceTypeList;
            }
            if (parseInt(check) == 1) {
                parent.resourceServiceMaps = rsm;
            }
            dcomethealth.MasterResource.saveService(parent).done(function (data, textStatus, jqXHR) {
                var classCheck = $("#testDiv").hasClass('hidden');
                if (!classCheck) {
                    if (!!parseInt($("#serviceRid").val())) {
                        submitServiceTest($("#serviceRid").val());
                    } else {
                        submitServiceTest(data);
                    }
                } else {
                    alert("Data Saved");
                    dcomethealth.util.loadpage('ServiceMaster');
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed to save data");
            });
        }
    }

    function submitServiceTest(profileRid) {
        var profileServiceMapList = [], serviceMaster = {};
        var check;
        if ($("#bsCategory option:selected").text() == "Profile") {
            if ($("#serviceTest option:selected").text() == "") {
                alert("Select any one profile service");
                return false;
            } else {
                serviceMaster.id = profileRid;
                serviceMaster.bsCode = $("#bsCode").val();
                serviceMaster.bsName = $("#bsName").val();
                serviceMaster.bsUnit = $("#bsUnit").val();
                serviceMaster.bsServiceType = $("#bsServiceType").val();
                serviceMaster.bsCategory = $("#bsCategory").val();
                serviceMaster.bsGroup = $("#bsGroup").val();
                serviceMaster.bsServiceActive = $("#isActive").val();
                if ($("#isShedulable").val() == "undefined") {
                    serviceMaster.bsServiceSchedule = 0;
                } else {
                    serviceMaster.bsServiceSchedule = $("#isShedulable").val();
                }
                if ($("#isManual").val() == "undefined") {
                    serviceMaster.bsServiceManual = 0;
                } else {
                    serviceMaster.bsServiceManual = $("#isManual").val();
                }
                if ($("#isConversion").val() == "undefined") {
                    serviceMaster.bsServiceConversion = 0;
                } else {
                    serviceMaster.bsServiceConversion = $("#isConversion").val();
                }
                if ($("#OrderPriceEditable").val() == "undefined") {
                    serviceMaster.bsPriceatorder = 0;
                } else {
                    serviceMaster.bsPriceatorder = $("#OrderPriceEditable").val();
                }

                if ($("#BillPriceEditable").val() == "undefined") {
                    serviceMaster.bsPriceatbill = 0;
                } else {
                    serviceMaster.bsPriceatbill = $("#BillPriceEditable").val();
                }
                if ($("#BillQtyEditable").val() === "undefined") {
                    serviceMaster.bsQtyatbilling = 0;
                } else {
                    serviceMaster.bsQtyatbilling = $("#BillQtyEditable").val();
                }
                if ($("#OrderQtyEditable").val() == "undefined") {
                    serviceMaster.bsQtyatOrdering = 0;
                } else {
                    serviceMaster.bsQtyatOrdering = $("#OrderQtyEditable").val();
                }
                if ($("#isResultExpected").val() == "undefined") {
                    serviceMaster.bsIsresultExpected = 0;
                } else {
                    serviceMaster.bsIsresultExpected = $("#isResultExpected").val();
                }
                if ($("#isSampleRequired").val() == "undefined") {
                    serviceMaster.bSIsSampleRequired = 0;
                } else {
                    serviceMaster.bSIsSampleRequired = $("#isSampleRequired").val();
                }

                if ($("#price").val() !== "") {
                    serviceMaster.bPrice = $("#price").val();
                } else {
                    serviceMaster.bPrice = 0;
                }
                if ($("#emergencyPrice").val() !== "") {
                    serviceMaster.bEprice = $("#emergencyPrice").val();
                } else {
                    serviceMaster.bEprice = 0;
                }
                $("#s2id_serviceTest").find(".select2-choices").find(".select2-search-choice").find("div").each(function (indx, service) {
                    var valid = true, serviceRid = null, prServiceMap = {};
                    var serviceVal = $(service).text();
                    serviceRid = $(service).find("input[type='hidden']").val();
                    $.each(idArray, function (index, mapRid) {
                        var profileServiceMap = {};
                        if (!!serviceVal) {
//                            serviceRid = $(service).find("input[type='hidden']").val();
                            if (serviceRid == mapRid.serviceRid) {
                                profileServiceMap.id = mapRid.prMapId;
                                profileServiceMap.prProfileRid = profileRid;
                                profileServiceMap.prServiceRid = serviceRid;
                                profileServiceMap.prServiceName = mapRid.serviceName;
                                profileServiceMap.prIsActive = 1;
                                profileServiceMap.prEntityRid = dcomethealth.loginuser.entityRid;
                                profileServiceMapList.push(profileServiceMap);
                                valid = false;
                            }
                        }
                    });
                    if (valid) {
                        prServiceMap.prProfileRid = profileRid;
                        prServiceMap.prServiceRid = serviceRid;
                        prServiceMap.prServiceName = serviceVal;
                        prServiceMap.prIsActive = 1;
                        prServiceMap.prEntityRid = dcomethealth.loginuser.entityRid;
                        profileServiceMapList.push(prServiceMap);
                    }
                });
                if (idArray.length > 0) {
                    $.each(idArray, function (index, mapRid) {
                        check = true;
                        var serviceMap = {};
                        $.each(profileServiceMapList, function (index, selectedId) {
                            if (parseInt(mapRid.serviceRid) == parseInt(selectedId.prServiceRid)) {
                                check = false;
                            }
                        });
                        if (check) {
                            serviceMap.id = mapRid.prMapId;
                            serviceMap.prProfileRid = profileRid;
                            serviceMap.prServiceRid = mapRid.serviceRid;
                            serviceMap.prServiceName = mapRid.serviceName;
                            serviceMap.prIsActive = 0;
                            serviceMap.prEntityRid = dcomethealth.loginuser.entityRid;
                            profileServiceMapList.push(serviceMap);
                        }
                    });
                }
                serviceMaster.profileServiceList = profileServiceMapList;
                dcomethealth.MasterResource.saveService(serviceMaster).done(function (data, textStatus, jqXHR) {
                    alert("Data Saved");
                    dcomethealth.util.loadpage('ServiceMaster');
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed to save data");
                    $("#submit").attr("disabled", false);
                });
            }
        } else {
            return false;
        }
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
    function validateForm(form) {
        return  form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        addTest: addTest,
        serviceWiseGroup: serviceWiseGroup,
        fetchServiceList: fetchServiceList,
        showServiceDetail: showServiceDetail,
        populateFields: populateFields,
        resource_autocomplete: resource_autocomplete,
        submit: submit,
        autocompleteService: autocompleteService,
        autocompleteItem: autocompleteItem,
        deleteRowService: deleteRowService,
        deleteRowItem: deleteRowItem,
        refreshData: refreshData

    };
}());
dcomethealth.ServiceMaster.init();
