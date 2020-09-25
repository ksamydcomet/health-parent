var dcomethealth = dcomethealth || {};
dcomethealth.UnitMaster = (function () {
    var id = "UnitMaster", newSpList = [], idex = -1, delWorkHrsList = [];
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#page-header').innerHTML = 'Unit Master';
            fetchUnitList();
            $('.activeStatus').toggles({on: true}, "isActive", 1);
            $('.hasStatus').toggles({on: false}, "isHas", 0); //---toggles function contains (boolean,valueID)
            $('.clinicalStatus').toggles({on: false}, "isClinical", 0); //---toggles function contains (boolean,valueID)
            $('.externalStatus').toggles({on: false}, "isExternal", 0); //---toggles function contains (boolean,valueID)
            $('.serviceStatus').toggles({on: false}, "isService", 0); //---toggles function contains (boolean,valueID)
            $('.departmentStatus').toggles({on: false}, "isDepartment", 0); //---toggles function contains (boolean,valueID)
            $('.storeStatus').toggles({on: false}, "isStore", 0); //---toggles function contains (boolean,valueID)
            $('.purchaseStatus').toggles({on: false}, "isPurchase", 0); //---toggles function contains (boolean,valueID)
            $("#servicePointDiv").show('slow');
            $("#selectSP").on('change', function () {
                var spText = $(this).find("option:selected").text();
                getValues(spText);
            });
            //---toggles function contains (boolean,valueID)           
            $("#unitTypeIndex").change(function () {
                var searchParams = {"unitTypeIndex": $('#unitTypeIndex option:selected').val()};
                var options = '';
            }),
                    $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    unitName: "required",
                    unitTypeIndex: "required",
                },
                unitTypeIndex: {
                    selectcheck: true

                },
                messages: {
                    unitName: "Enter Unit Name",
                    unitTypeIndex: "Select Unit Type",
                },
                submitHandler: function (form) {
                    if (!!dcomethealth.s_unit && $("#unitRid").val() == "") {
                        var check = false;
                        $.each(dcomethealth.s_unit, function (inx, unit) {
                            if (unit.unitName == $("#unitName").val()) {
                                alert("Unit Name Already Exist");
                                check = true;
                                return false;
                            }
                        });
                        if (!check) {
                            submit();
                        }
                    } else {
                        submit();
                    }
                }
            });
            jQuery.validator.addMethod('selectcheck', function (value) {
                return (value != '0');
            }, "Select Unit Type");
        });
    }
    function fetchUnitList() {
        var search = {"unitValid": 1};
        dcomethealth.MasterResource.searchUnit(search, function (data) {
            $("#tbody_head").empty();
            $.each(dcomethealth.s_unit = data, function (index, s_unit) {
                $("#tbody_head").append('<tr onclick="dcomethealth.UnitMaster.showUnitDetail(' + s_unit.id + ')" id="tr_head">\n\
                                        <td>' + s_unit.unitName + '</td>\n\
                                        <td>' + s_unit.unitCode + '</td></tr>');
            });
            $("#unitsTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
            $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
            $('.dataTables_length select').addClass('form-control');
        });
    }
    function showUnitDetail(val) {
        $.each(dcomethealth.s_unit, function (index, s_unit) {
            if (s_unit.id == parseInt(val)) {
                $("#UnitMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(s_unit)
            }
        });
    }
    function deleteRowWorkingHrs(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('dynTableSchedule');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (table_length > 2) {
                if (!!dynTableGetNodeInRow(elem, 'resAvilWhRid').value && dynTableGetNodeInRow(elem, 'resAvilWhRid').value != "" && dynTableGetNodeInRow(elem, 'resAvilWhRid').value != "undefined") {
                    var resWh = {};
                    resWh.id = dynTableGetNodeInRow(elem, 'resAvilWhRid').value;
                    resWh.whEntityRID = dcomethealth.loginuser.entityRid;
                    resWh.whDayOfWeek = dynTableGetNodeInRow(elem, 'resAvilDays').value;
                    resWh.whFlag = 1;
                    resWh.whFromTime = moment(dynTableGetNodeInRow(elem, 'fromTime').value, 'HH:mm').format('HH:mm:ss');
                    resWh.whToTime = moment(dynTableGetNodeInRow(elem, 'ToTime').value, 'HH:mm').format('HH:mm:ss');
                    if (!!$("#unitRid").val()) {
                        resWh.whUnitRID = $("#unitRid").val();
                    }
                    delWorkHrsList.push(resWh);
                }
                dynTableDeleteRow(x);
                dynTableGetNodeInRow(table.rows[1], 'plus').className = "ace-icon fa fa-plus";
                return false;
            }
        }
    }
    function getValues(spText) {
        $('#tbody_days').empty();
        if (parseInt($("#selectSP").val()) == 0) {
            $("#btnAdd").text("Add");
            $("#wrdName").val("");
            $("#wrdType").val("");
            $("#datewise").addClass("hidden");
            $("#openPmd").attr('checked', false);
            idex = -1;
        } else {
            $("#btnAdd").text("Update");
            $.each(newSpList, function (ix, sPoint) {
                if (spText == sPoint.wrdName) {
                    idex = ix;
                    $("#wrdName").val(sPoint.wrdName);
                    $("#wrdType").val(sPoint.wrdType);
                    $("#resRid").val(sPoint.resource.id);
                    $("#spRid").val(sPoint.wrdRid);
                    (sPoint.wrdIsScheduleRequired == 1) ? $('#openPmd').prop("checked", true) : $("#openPmd").prop('checked', false);
                    if (sPoint.wrdIsScheduleRequired == 0) {
                        clearValues();
                        $("#datewise").addClass("hidden");
                        $('#tbody_days').append('<tr><td width="1%"><i id="del" class="dct-icon fa fa-minus-circle" onclick="dcomethealth.UnitMaster.deleteRowWorkingHrs(this)"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value=""/></td>\n\
                        <td width="15%"><Select class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDays" name="resAvilDays"><option value="0"></option></select></td>\n\
                        <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="" name="fromTime"/></td>\n\
                        <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="" name="ToTime"/></td>\n\
                        <td width="1%"><i id="plus" class="dct-icon fa fa-plus-square red" onclick="insert_row(\'dynTableSchedule\', this,1)"></i></td></tr>');
                        dcomethealth.datatypes.init($("#tbody_days"));
                    }
                    if (!!sPoint.resource.resourceWorkingHours) {
                        $('#tbody_days').empty();
                        $('#tbody_days').append('<tr><td width="1%"><i id="del" class="dct-icon fa fa-minus-circle" onclick="dcomethealth.UnitMaster.deleteRowWorkingHrs(this)"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value=""/></td>\n\
                    <td width="15%"><Select class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDays" name="resAvilDays"><option value="0"></option></select></td>\n\
                    <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="" name="fromTime"/></td>\n\
                    <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="" name="ToTime"/></td>\n\
                        <td width="1%"><i id="plus" class="dct-icon fa fa-plus-square red" onclick="insert_row(\'dynTableSchedule\', this,1)"></i></td></tr>');
                        dcomethealth.datatypes.init($("#tbody_days"));
                        $.each(sPoint.resource.resourceWorkingHours, function (ix, resWH) {
                            var days = "";
                            if (parseInt(resWH.whFlag) === 1 && parseInt(resWH.whIsActive) === 1) {
                                $('#collect').val(1);
                                $('#openPmd').prop('checked', true);
                                $("#datewise").removeClass("hidden");
                                $.each(dcomethealth.dDictVal, function (ifx, val) {
                                    if (resWH.whDayOfWeek == val.id) {
                                        days = val.ddictValue;
                                    }
                                });
                                $('#tbody_days').append('<tr><td width="1%"><i id="del" class="dct-icon fa fa-minus-circle " onclick="dcomethealth.UnitMaster.deleteRowWorkingHrs(this)"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value="' + resWH.id + '"/></td>\n\
                    <td width="15%"><Select class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDays" name="resAvilDays"><option value="' + resWH.whDayOfWeek + '">' + days + '</option></select></td>\n\
                    <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + resWH.whFromTime + '" name="fromTime"/></td>\n\
                    <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + resWH.whToTime + '" name="ToTime"/></td>\n\
                        <td width="1%"><i id="plus" class="" onclick="insert_row(\'dynTableSchedule\', this,1)"></i></td></tr>');
                            }
                        });
                    }
                }
            });
        }
    }
    function clearValues() {
        $("#tbody_days").empty();
        $("#collect").val(0);
        $('#tbody_days').append('<tr><td width="1%"><i id="del" class="dct-icon fa fa-minus-circle " onclick="dcomethealth.UnitMaster.deleteRowWorkingHrs(this)"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value=""/></td>\n\
                        <td width="15%"><Select class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDays" name="resAvilDays"><option value=""></option></select></td>\n\
                        <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="" name="fromTime"/></td>\n\
                        <td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="" name="ToTime"/></td>\n\
                        <td width="1%"><i id="plus" class="dct-icon fa fa-plus-square red" onclick="insert_row(\'dynTableSchedule\', this,1)"></i></td></tr>');

        dcomethealth.datatypes.init($("#tbody_days"));
    }
    function setValues() {
        $("#datewise").addClass("hidden");
        $("#wrdName").val('');
        $("#wrdType").val('');
        $("#resRid").val('');
        $("#spRid").val('');
        $("#openPmd").is(':checked') ? $("#openPmd").attr('checked', false) : $("#openPmd").attr('checked', false);
        $("#selectSP").empty();
        $("#selectSP").append("<option value=" + 0 + ">" + "--Choose--" + "</option>");
        $.each(newSpList, function (ix, sPoint) {
            $("#selectSP").append("<option value=" + sPoint.wrdName + ">" + sPoint.wrdName + "</option>");
        });
    }
    function prepareObject() {
        if (!$("#wrdName").val()) {
            alert("Enter ServicePoint Name");
            return false;
        }
        if ($("#wrdType").val() == 0) {
            alert("Select Service Type");
            return false;
        }

        var check = false, resource = {};
        if (($("#openPmd").is(':checked'))) {
            var table = document.getElementById('dynTableSchedule');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                if (i != 0) {
                    var fromTime = dynTableGetNodeInRow(table.rows[i + 1], 'fromTime').value;
                    var ToTime = dynTableGetNodeInRow(table.rows[i + 1], 'ToTime').value;
                    var daysVal = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value;
                    if (daysVal == "") {
                        alert("Select Day");
                        return false;
                    }
                    if (fromTime == "") {
                        alert("Enter From Time");
                        return false;
                    } else if (ToTime == "") {
                        alert("Enter To Time");
                        return false;
                    }
                }
                if (table_length == 2 && i == 0) {
                    var fromTime = dynTableGetNodeInRow(table.rows[i + 1], 'fromTime').value;
                    var ToTime = dynTableGetNodeInRow(table.rows[i + 1], 'ToTime').value;
                    var daysVal = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value;
                    if (daysVal == "") {
                        alert("Select Day");
                        return false;
                    }
                    if (fromTime == "") {
                        alert("Enter From Time");
                        return false;
                    } else if (ToTime == "") {
                        alert("Enter To Time");
                        return false;
                    }
                }
            }
        }
        var servicePoint = {};
        var resWhList = [], resource = {};
        if (!!$("#resRid").val() && $("#resRid").val() != "") {
            resource.id = $("#resRid").val();
        }
        resource.resName = $("#wrdName").val();
        resource.resType = 2; //lovcation(ex:servicePoint)           
        resource.resTypeRid = 2;
        resource.resEntRID = dcomethealth.loginuser.entityRid;
        resource.resSchedInterval = 0;
        resource.resUnitRID = dcomethealth.selectedunit;
        resource.resValid = 1;
        resource.portResID = 0;
        resource.resSequenceNumber = 0;
        resource.resSubcategory = 0;
        resource.resSchedDuration = 20;
        if (!!$("#spRid").val()) {
            servicePoint.wrdRid = $("#spRid").val();
        }
        servicePoint.wrdType = $("#wrdType").val();
        servicePoint.wrdName = $("#wrdName").val();
        servicePoint.wrdEntityRid = dcomethealth.loginuser.entityRid;
        if ($('#openPmd').is(':checked')) {
            servicePoint.wrdIsScheduleRequired = 1;
        } else {
            $('#collect').val(0);
            servicePoint.wrdIsScheduleRequired = 0;
        }
        servicePoint.wrdNumber = $('#collect').val();
        if (parseInt($('#collect').val()) === 1) {
            var table = document.getElementById('dynTableSchedule');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                if (dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value != 0) {
                    var resWh = {};
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value != "" && dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value != "undefined") {
                        resWh.id = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value;
                    }
                    //                        resWh.servicePointRid = $("#wrdName").val();
                    resWh.whEntityRID = dcomethealth.loginuser.entityRid;
                    if (parseInt($('#collect').val()) === 1) {
                        resWh.whDayOfWeek = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value;
                        resWh.whFlag = 1;
                    }
                    resWh.whFromTime = moment(dynTableGetNodeInRow(table.rows[i + 1], 'fromTime').value, 'HH:mm').format('HH:mm:ss');
                    resWh.whToTime = moment(dynTableGetNodeInRow(table.rows[i + 1], 'ToTime').value, 'HH:mm').format('HH:mm:ss');
                    if (!!$("#unitRid").val()) {
                        resWh.whUnitRID = $("#unitRid").val();
                    }
                    resWh.whIsActive = 1;
                    resWhList.push(resWh);
                }
            }
        }
        if (delWorkHrsList.length > 0) {
            $.each(delWorkHrsList, function (indx, resWhours) {
                var resWh = {};
                resWh.id = resWhours.id;
                resWh.whEntityRID = resWhours.whEntityRID;
                resWh.whDayOfWeek = resWhours.whDayOfWeek;
                resWh.whFlag = resWhours.whFlag;
                resWh.whFromTime = resWhours.whFromTime;
                resWh.whToTime = resWhours.whToTime;
                resWh.whUnitRID = resWhours.whUnitRID;
                resWh.whIsActive = 0;
                resWhList.push(resWh);
            });
        }
        resource.resourceWorkingHours = resWhList;
        servicePoint.resource = resource;
        if (parseInt(idex) == -1) {
            newSpList.push(servicePoint);
        } else {
            newSpList[idex] = servicePoint;
            idex = -1;
        }
        $("#datewise").addClass("hidden");
        $("#btnAdd").text("Add");
        setValues();
    }
    function populateFields(data) {
        if (!!data.wardMasters) {
            if (data.wardMasters.length > 0) {
                newSpList = data.wardMasters;
                $("#servicePointDiv").removeClass('hidden');
                $.each(newSpList, function (ix, sPoint) {
                    $("#selectSP").append("<option value=" + sPoint.wrdName + ">" + sPoint.wrdName + "</option>");
                });
            }
        }
        document.getElementById("unitName").value = data.unitName;
        document.getElementById("unitRid").value = parseInt(data.id);
        document.getElementById("unitCode").value = data.unitCode;
        document.getElementById("unitParentRID").value = parseInt(data.unitParentRID);
        document.getElementById("unitHead").value = parseInt(data.unitHead);
        var hasBed = parseInt(data.unitHasBeds);
        if (hasBed == 1) {
            $('.hasStatus').toggles({on: true}, "isHas");
        } else {
            $('.hasStatus').toggles({on: false}, "isHas");
        }
        var isStore = parseInt(data.unitIsMAINSTORE);
        if (isStore == 1) {
            $('.storeStatus').toggles({on: true}, "isStore");
        } else {
            $('.storeStatus').toggles({on: false}, "isStore");
        }
        var isDepartment = parseInt(data.unitIsDepartment);
        if (isDepartment == 1) {
            $('.departmentStatus').toggles({on: true}, "isDepartment");
        } else {
            $('.departmentStatus').toggles({on: false}, "isDepartment");
        }
        if (data.unitDlNo != undefined) {
            document.getElementById("unitDlNo").value = data.unitDlNo;
        }
        var isExternal = parseInt(data.unitIsExternal);
        if (isExternal == 1) {
            $('.externalStatus').toggles({on: true}, "isExternal");
        } else {
            $('.externalStatus').toggles({on: false}, "isExternal");
        }
        var unitIsServiceProvider = parseInt(data.unitIsServiceProvider);
        if (unitIsServiceProvider == 1) {
            $('.serviceStatus').toggles({on: true}, "isService");
        } else {
            $('.serviceStatus').toggles({on: false}, "isService");
        }

        var unitIsPurchasing = parseInt(data.unitIsPurchasing);
        if (unitIsPurchasing == 1) {
            $('.purchaseStatus').toggles({on: true}, "isPurchase");
        } else {
            $('.purchaseStatus').toggles({on: false}, "isPurchase");
        }

//        document.getElementById("unitCSTNo").value = data.unitCSTNo;
        document.getElementById("unitTypeIndex").value = data.unitTypeIndex;
        document.getElementById("unitCostCenterRID").value = data.unitCostCenterRID;
        document.getElementById("unitLocalSTNo").value = data.unitLocalSTNo;
//        document.getElementById("unitTinNo").value = data.unitTinNo;
        var isValid = parseInt(data.unitValid);
        if (isValid == 1) {
            $('.activeStatus').toggles({on: true}, "isActive");
        } else {
            $('.activeStatus').toggles({on: false}, "isActive");
        }
        var isClinical = parseInt(data.unitIsClinical);
        if (isClinical == 1) {
            $('.clinicalStatus').toggles({on: true}, "isClinical");
        } else {
            $('.clinicalStatus').toggles({on: false}, "isClinical");
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
    function submit() {
        var form = $("form");
        if (validateForm(form)) {
            var parent = {};
            if ($("#unitRid").val() != "") {
                parent.id = $("#unitRid").val();
            }
            parent.unitCode = $("#unitCode").val();
            parent.unitName = $("#unitName").val();
            //            parent.unitEntityRID = dcomethealth.loginuser.entityRid;
            parent.unitParentRID = $("#unitParentRID").val();
            parent.unitHead = $("#unitHead").val();
            //            parent.unitWorkHrsFrom = "From";
            //            parent.unitWorkHasTo = "To";

            if (!$("#isHas").val() || $("#isHas").val() == "undefined") {
                parent.unitHasBeds = 0;
            } else {
                parent.unitHasBeds = $("#isHas").val();
            }
            if ($("#isStore").val() === "undefined") {
                parent.unitIsMAINSTORE = 0;
            } else {
                parent.unitIsMAINSTORE = $("#isStore").val();
            }
            if ($("#isDepartment").val() === "undefined") {
                parent.unitIsDepartment = 0;
            } else {
                parent.unitIsDepartment = $("#isDepartment").val();
            }
            if ($("#isClinical").val() === "undefined") {
                parent.unitIsClinical = 0;
            } else {
                parent.unitIsClinical = $("#isClinical").val();
            }
            if ($("#isExternal").val() === "undefined") {
                parent.unitIsExternal = 0;
            } else {
                parent.unitIsExternal = $("#isExternal").val();
            }
            if ($("#isService").val() === "undefined") {
                parent.unitIsServiceProvider = 0;
            } else {
                parent.unitIsServiceProvider = $("#isService").val();
            }
            if ($("#isPurchase").val() === "undefined") {
                parent.unitIsPurchasing = 0;
            } else {
                parent.unitIsPurchasing = $("#isPurchase").val();
            }
            parent.unitValid = $("#isActive").val();
            parent.unitDLValidUpto = "2015-12-12";
//            parent.unitCSTNo = $("#unitCSTNo").val();
            parent.unitDlNo = $("#unitDlNo").val();
            parent.unitTypeIndex = $("#unitTypeIndex").val();
            parent.unitCostCenterRID = $("#unitCostCenterRID").val();
            parent.unitLocalSTNo = $("#unitLocalSTNo").val();
//            parent.unitTinNo = $("#unitTinNo").val();
            if (newSpList.length > 0) {
                parent.wardMasters = newSpList;
            }
            dcomethealth.MasterResource.saveUnit(parent)
                    .done(function (data, textStatus, jqXHR) {
                        alert("Data Saved");
                        dcomethealth.util.loadpage('UnitMaster');
                        //requestObj.field1 = data.primaryKey
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed to save data");
            });
        }
    }

    function validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }

    return{
        init: init,
        fetchUnitList: fetchUnitList,
        showUnitDetail: showUnitDetail,
        populateFields: populateFields,
        prepareObject: prepareObject,
        deleteRowWorkingHrs: deleteRowWorkingHrs,
        setValues: setValues,
        getValues: getValues,
        clearValues: clearValues,
        submit: submit,
        refreshData: refreshData
    };
}());
dcomethealth.UnitMaster.init();
