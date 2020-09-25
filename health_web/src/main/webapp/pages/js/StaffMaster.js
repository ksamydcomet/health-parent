var dcomethealth = dcomethealth || {};
var dcometRoles = {};
dcomethealth.StaffMaster = (function () {
    var id = "StaffMaster";
    var serviceClearVar = 0;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#page-header').innerHTML = 'Staff Master';
            $('.activeStatus').toggles({on: true}, "staffIsConsultant");
            $('.surgeonStatus').toggles({on: false}, "staffIsSurgeon", 0);
            $('.doctorStatus').toggles({on: false}, "staffIsAdmittingDoctor", 0);
            $('.staffStatus').toggles({on: true}, "staffValid");
            fetchStaffList();
            getStates();
            service_AutoComplete();
            getFcCondition();
            dcomethealth.MasterResource.searchUnit({}, function (data) {
                $('#unit_tbody').empty();
                $.each(data, function (pIdx, item) {
                    var opt1 = document.createElement("option");
                    opt1.value = item.id;
                    opt1.text = item.unitName;
                    document.getElementById('primaryUnit').options.add(opt1);
                    $('#unit_tbody').append('<tr><td><input type="checkbox" id="unitCheck" name="unitCheck"></td>\n\
                                <td><input type="text" id="unitName" name="unitName" value="' + item.unitName + '" style="border:none"/>\n\
                                    <input type="hidden" id="unitRid" name="unitRid" value="' + item.id + '"></td></tr>');
                });
            });
            $('#countryCode').val(dcomethealth.userEntityCountryCode);
            var obj = $('#patCountry').find('option[id="' + dcomethealth.userEntityCountryCode + '"]');
            $('#staffCountry').val(obj.val());
            dcomethealth.MasterResource.getRoles(function (data) {
                dcometRoles = data;
            });
            $("#roles").select2({width: 'resolve'});
            $("#units").select2({width: 'resolve'});
            $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
                if (parseInt(dcomethealth.loginuser.entityRid) == entities.id) {
                    $.each(entities.units, function (pIdx, units) {
                        $("select#units").append('<option value="' + units.id + '">' + units.unitName + '</option>');
                    });
                }
            });
            $("#staffAbbrv").change(function () {
                var searchParams = {"staffSpecialityIndex": $('#staffAbbrv option:selected').val()};
                var options = '';
                dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
                    $.each(data, function (idx, Val) {
                        options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                    });
                    $("#VDName").html(options);
                });
            });
            $("#edit_user_form").validate({
                // Specify the validation rules
                rules: {
                    staffName: "required",
                    staffContactNumber: "required",
                    staffUname: "required",
//                    staffCompensation: "required",
//                    FreshConsultation: "required",
//                    ReviewConsultation: "required",
                    MaximumConsultation: "required",
                    staffEmail: {
                        required: true,
                        email: true
                    },
                    staffAbbrv: {
                        selectcheck: true

                    },
                    staffCategory: {
                        selectchec: true

                    }
                },
                // Specify the validation error messages
                messages: {
                    staffName: "Enter Staff Name",
                    staffContactNumber: "Enter Contact No",
                    staffUname: "Enter User Name",
                    staffEmail: "Enter Valid Email",
//                    staffCompensation: "Enter Compensation",
//                    FreshConsultation: "Enter Fee",
//                    ReviewConsultation: "Enter Fee",
                    MaximumConsultation: "Enter Max Consultation"
                },
                submitHandler: function (form) {
                    submit();
                }
            });
            jQuery.validator.addMethod('selectcheck', function (value) {
                return (value != '0');
            }, "Select Speciality");
            jQuery.validator.addMethod('selectchec', function (value) {
                return (value != '0');
            }, "Select Category");
        });
    }
    function searchPostalCode() {
        var pincode = $('#staffPcode').val()
        var searchObj = {"zipCode": pincode, "couCode": dcomethealth.userEntityCountryCode};
        dcomethealth.DataDictionaryResource.searchByZipcode(searchObj, function (data) {
            $("#staffCity").empty();
            if (!!data) {
                $.each(data, function (pIdx, data) {
                    $("#staffCity").append('<option value="' + data.place + '">' + data.place + '</option>');
                    $("#staffState").val(data.division1);
                });
            }
        });
    }
    function fetchStaffList() {
        var Title = null;
        var search = {"staffValid": 1};
        dcomethealth.MasterResource.searchStaff(search, function (data) {
            var tables = $.fn.dataTable.fnTables(true);
            $(tables).each(function () {
                $(this).dataTable().fnClearTable();
                $(this).dataTable().fnDestroy();
            });
            if (!!data) {
                $("#tbody_head").empty();
                $.each(dcomethealth.s_staff = data, function (index, s_staff) {
                    $.each(dcomethealth.dDictVal, function (i, val) {
                        if (parseInt(s_staff.staffTitle) == val.id) {
                            Title = val.ddictValue;
                        }
                    });
                    $("#tbody_head").append('<tr onclick="dcomethealth.StaffMaster.showStaffDetail(' + s_staff.id + ')" id="tr_head">\n\
                    <td>' + s_staff.staffCode + '</td><td>' + s_staff.staffName + '</td><td>' + s_staff.staffDesignation + '</td><td>' + s_staff.staffContactNumber + '</td></tr>');
                });
                $("#staffTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $("#tbody_head").empty();
                $("#staffTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function showStaffDetail(val) {
        $.each(dcomethealth.s_staff, function (index, s_staff) {
            if (parseInt(s_staff.id) === parseInt(val)) {
                $("#StaffMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                $("select.dcomet-c-s_roles-list").empty();
                $("select.dcomet-c-s_roles-list", $("#StaffMaster")).each(function (idx, elem) {
                    $.each(dcometRoles, function (index, roles) {
                        if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID) && !!roles.roleValid) {
                            $(elem).append('<option value="' + roles.id + '">' + roles.roleName + '</option>');
                        }
                    });
                    var opts = $("#source").html(), opts2 = "<option></option>" + opts;
                    $("select.populate").each(function () {
                        var e = $(this);
                        e.html(e.hasClass("placeholder") ? opts2 : opts);
                    });
                });
                populateFields(s_staff);
            }
        });
    }
    function getCity() {
        var couCode = $('#patCountry option[value="' + $('#cntryName').val() + '"]').text();
        var searchObj = {"couCode": couCode.substring(0, 2)};
        dcomethealth.DataDictionaryResource.searchByZipcode(searchObj, function (data) {
            $.each(data, function (pIdx, s_cou_zipcode) {
                $(s_cou_zipcode).each(function () {
                    if (s_cou_zipcode.division1 == $("#staffState").val()) {
                        $("#Cities").append('<option value="' + s_cou_zipcode.place + '">' + s_cou_zipcode.place + '</option>');
                    }
                });
            });
        });
    }
    function getStates() {
        dcomethealth.DataDictionaryResource.searchStates({}, function (data) {
            dcomethealth.s_states = data;
            var targetContainer = targetContainer || document;
            $("datalist.dcomet-c-s_ddict_state-list", targetContainer).each(function (idx, elem) {
                $.each(dcomethealth.s_states, function (pIdx, s_states) {
                    $(s_states).each(function () {
                        var cntryName = $('#patCountry option[value="' + $('#staffCountry').val() + '"]').text();
                        if (cntryName === s_states.countryCode) {
                            $(elem).append('<option value="' + s_states.states + '">' + s_states.stateRid + '</option>');
                        }
                    });
                });
            });
        });
    }
    function checkServiceValid(elem) {
        if (serviceClearVar == 0) {
            dynTableGetNodeInRow(elem, 'fcServiceName').value = "";
            dynTableGetNodeInRow(elem, 'fcServiceRID').value = "";
        }
        var table = document.getElementById('dyn_table_fc');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 2; i++) {
            if (dynTableGetNodeInRow(table.rows[i + 1], 'fcServiceRID').value == dynTableGetNodeInRow(elem, 'fcServiceRID').value) {
                alert("Already Existing Service");
                dynTableGetNodeInRow(elem, 'fcServiceName').value = "";
                dynTableGetNodeInRow(elem, 'fcServiceRID').value = "";
                return false;
            }
        }
    }
    function getFcCondition() {
        var searchObj = {};
        dcomethealth.MasterResource.getFcCondition(searchObj, function (data) {
            $.each(data, function (pIdx, fcCondition) {
                var condition = fcCondition.fcConditionMinimum + " - " + fcCondition.fcConditionMaximum + " days";
                $("#fcCondition").append('<option value="' + fcCondition.id + '">' + condition + '</option>');
            });
        });
    }
    function service_AutoComplete() {
        $("#" + id + " input[name='fcServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
//                autoIdSet(dynTableGetNodeInRow(this, 'serviceRID'));
                dynTableGetNodeInRow(this, 'fcServiceRID').value = ui.item.data.id;
//                dynTableGetNodeInRow(this, 'bdGroupRID').value = ui.item.data.bsServiceType;
//                if ($('#visitReason option:selected').text() == 'Emergency') {
//                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bEprice;
//                } else {
//                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bPrice;
//                }
//                $('#total_pmd').val('');
//                payerNameChange(0);
//                checkExistService(dynTableGetNodeInRow(this, 'serviceRID').value);
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
//                var check = true;
                var queryString = request.term;
                queryString = queryString.trim();
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    serviceClearVar = 0;
//                    if (!!data && data.length > 0) {
//                    check = false;
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
//                    } else {
//                        if (check == true) {
//                            $('#total_pmd').val('');
//                            checkValidService()
//                            return false;
//                        }
//                    }
                });
            },
            minLength: 1
        });
    }
    function populateFields(data) {
        $("#staffName").val(data.staffName);
        $('select[name="staffTitle"]').val(data.staffTitle);
        $("#staffRID").val(data.id);
        $("#staffContactNumber").val(data.staffContactNumber);
        $("#staffDesignation").val(data.staffDesignation);
        $("#staffDOJ").val(data.staffDOJ);
        $("#staffDOL").val(data.staffDOL);
        $("#staffAccountNumber").val(data.staffAccountNumber);
        $("#staffBankName").val(data.staffBankName);
        $("#staffBankBranch").val(data.staffBankBranch);
        $("#staffIfscCode").val(data.staffIfscCode);
        $("#staffPanCardNo").val(data.staffPanCardNo);
        $("#MaximumConsultation").val(data.staffMaxConsultationNo);
        $("#staffCompensation").val(data.staffCompensation);
        $("#staffIsConsultant").val(data.staffIsConsultant);
        $('#staffAbbrv option[value="' + data.staffSpecialityIndex + '"]').prop('selected', true);
        $("#staffState").val(data.staffState);
        $("#staffCity").val(data.staffCity);
        $("#staffPcode").val(data.staffZipCode);
        $('#staffTitle option[value="' + data.staffTitle + '"]').prop('selected', true);
        $('#staffCategory option[value="' + data.staffCategory + '"]').prop('selected', true);
        $('#primaryUnit option[value="' + data.staffUnitRID + '"]').prop('selected', true);
        if (!!data.userUnitMaps) {
            $.each(data.userUnitMaps, function (i, stfUnitMap) {
                var table = document.getElementById('unit_dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i <= table_length - 1; i++) {
                    if (parseInt(dynTableGetNodeInRow(table.rows[i], 'unitRid').value) === parseInt(stfUnitMap.suUnitRID)) {
                        dynTableGetNodeInRow(table.rows[i], 'unitCheck').checked = true;
                    }
                }
                if (parseInt(stfUnitMap.suIsPrimaryUnit) === 1) {
                    $('#primaryUnit option[value="' + stfUnitMap.suUnitRID + '"]').prop('selected', true);
                }
            });
        }
        dcomethealth.StaffMaster.searchUser(data.staffUserRID);
        dcomethealth.StaffMaster.searchResource(data.staffResourceRID);
        var staffIsConsultant = parseInt(data.staffIsConsultant);
        if (staffIsConsultant === 1) {
            $('.activeStatus').toggles({on: true}, "staffIsConsultant");
        } else {
            $('.activeStatus').toggles({on: false}, "staffIsConsultant");
        }
        var staffIsSurgeon = parseInt(data.staffIsSurgeon);
        if (staffIsSurgeon === 1) {
            $('.surgeonStatus').toggles({on: true}, "staffIsSurgeon");
            $("#doctorDiv").removeClass("hidden");
        } else {
            $('.surgeonStatus').toggles({on: false}, "staffIsSurgeon");
            $("#doctorDiv").addClass("hidden");
        }
        var staffIsAdmittingDoctor = parseInt(data.staffIsAdmittingDoctor);
        if (staffIsAdmittingDoctor === 1) {
            $('.doctorStatus').toggles({on: true}, "staffIsAdmittingDoctor");
        } else {
            $('.doctorStatus').toggles({on: false}, "staffIsAdmittingDoctor");
        }
        if (!!data.freeConsultationCategory) {
            if (!!data.freeConsultationCategory.fccConditionRid && data.freeConsultationCategory.fccConditionRid != 0) {
                $("#fcCondition").val(data.freeConsultationCategory.fccConditionRid);
            }
            if (!!data.freeConsultationCategory.fccRid) {
                $("#fcCategory").val(data.freeConsultationCategory.fccRid);
            }
        }
        if (!!data.freeConsultationTransitions) {
            $("#fcTbody").empty();
            $.each(data.freeConsultationTransitions, function (fcidx, fcTransitions) {
                $("#fcTbody").append('<tr><td><i id="fcDel" class="ace-icon fa fa-minus" onclick="delete_row_fc(this)"></i><input type="hidden" id="fcCategoryRid" name="fcCategoryRid" value="' + fcTransitions.fctCategoryRid + '"/></td>\n\
            <td width="30%"><input class="col-md-11 col-sm-10 col-xs-12" type="text" onkeypress="return dcomethealth.validation.isNumberKey(this)" readonly id="fcVisitCount" name="fcVisitCount" value="' + fcTransitions.fctToState + '"/></td>\n\
            <td width="60%"><input class="col-md-11 col-sm-11 col-xs-12" type="text" id="fcServiceName" name="fcServiceName" value="' + fcTransitions.fctServiceName + '" onchange="dcomethealth.StaffMaster.checkServiceValid(this)"/><input type="hidden" id="fcServiceRID" name="fcServiceRID" value="' + fcTransitions.fctServiceRid + '"/></td>\n\
            <td><i class="ace-icon fa fa-plus blue" onclick="insert_row_fc(\'dyn_table_fc\', this)"></i></td></tr>');
                service_AutoComplete();
            });
        }
    }
    function searchResource(val) {
        $('#tbody_days').empty();
        $('#tbody_date').empty();
        dcomethealth.MasterResource.searchResource({id: val}, function (data) {
            console.log(data);
            if (!!data) {
                $.each(data, function (index, s_Res) {
                    var days = ""
                    $("#resRid").val(s_Res.id);
//                $.each(s_Res.resourceAvailability, function (i, resAvail) {
//
//                });
//                $.each(s_Res.resourceUnitMap, function (i, stfUnitMap) {
//                    var table = document.getElementById('unit_dyn_table');
//                    var table_length = table.rows.length;
//                    for (var i = 0; i <= table_length - 1; i++) {
//                        if (parseInt(dynTableGetNodeInRow(table.rows[i], 'unitRid').value) === parseInt(stfUnitMap.ruUnitRID)) {
//                            dynTableGetNodeInRow(table.rows[i], 'unitCheck').checked = true;
//                        }
//                    }
//                    if (parseInt(stfUnitMap.suIsPrimaryUnit) === 1) {
//                        $('#primaryUnit option[value="' + stfUnitMap.suUnitRID + '"]').prop('selected', true);
//                    }
//                });

                    var flag = 0;
                    console.log(s_Res.resourceWorkingHours);
                    $.each(s_Res.resourceWorkingHours, function (j, resWhAvail) {
                        flag = resWhAvail.whFlag;
                        if (parseInt(resWhAvail.whFlag) === 1) {
                            //                        resWh.whDayOfWeek = dynTableGetNodeInRow(table2.rows[i], 'resAvilDays').value;
                            $('#collect').val(1);
                            $('#openPmd').prop('checked', true);
                            $('#datew').prop('disabled', true);
                            $("#daywise").removeClass("hidden").addClass("panel panel-primary");
                            document.getElementById('availdate').value = 0;
                            $("#datewise").removeClass("panel panel-primary").addClass("hidden");
//                        var table4 = document.getElementById('dyn_table_day');
//                        var x = dynTableRow(table4.rows[j]);
//                        dynTableCloneRow('dyn_table_day', x);
//                        var newRow = dynTableAppendRow('dyn_table_day');
//                        dynTableGetNodeInRow(newRow, 'fromTime').value = resWhAvail.whFromTime;
//                        dynTableGetNodeInRow(newRow, 'ToTime').value = resWhAvail.whToTime;
//                        dynTableGetNodeInRow(newRow, 'resAvilWhRid').value = resWhAvail.id;
                            //                        dynTableGetNodeInRow(newRow, 'resAvilDays').value = resWhAvail.whDayOfWeek;
                            $.each(dcomethealth.dDictVal, function (ifx, val) {
                                if (resWhAvail.whDayOfWeek == val.id) {
                                    days = val.ddictValue
                                }
                            });
                            $('#tbody_days').append('<tr><td width="1%"><i id="del_saleR" class="dct-icon fa fa-minus-circle hidden" onclick="delete_row(this, \'dyn_table_day\')"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value="' + resWhAvail.id + '"/></td>\n\
<td width="15%"><Select class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDays" name="resAvilDays"><option value="' + resWhAvail.whDayOfWeek + '">' + days + '</option></select></td>\n\
<td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + resWhAvail.whFromTime + '" name="fromTime"/></td>\n\
<td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + resWhAvail.whToTime + '" name="ToTime"/></td>\n\
                    <td width="1%"><i class="dct-icon fa fa-plus-square red" onclick="insert_row(\'dyn_table_day\', this)"></i></td></tr>');
                        } else if (parseInt(resWhAvail.whFlag) === 2) {
                            $('#availdate').val(1);
                            $('#datew').prop('checked', true);
                            $('#openPmd').prop('disabled', true);
                            $("#datewise").removeClass("hidden").addClass("panel panel-primary");
                            document.getElementById('collect').value = 0;
                            $("#daywise").removeClass("panel panel-primary").addClass("hidden");
//                        var table3 = document.getElementById('dyn_table_date');
//                        var x = dynTableRow(table3.rows[j]);
//                        dynTableCloneRow('dyn_table_date', x);
//                        var newRow = dynTableAppendRow('dyn_table_date');
////                                dynTableGetNodeInRow(table3.rows[i], 'resAvilRid').value = resAvail.id;
//                        dynTableGetNodeInRow(newRow, 'fromTime').value = resWhAvail.whFromTime;
//                        dynTableGetNodeInRow(newRow, 'ToTime').value = resWhAvail.whToTime;
//                        dynTableGetNodeInRow(newRow, 'resAvilWhRid').value = resWhAvail.id;
//                        dynTableGetNodeInRow(newRow, 'resAvilDateFrom').value = resWhAvail.whFromDate;
                            //                        dynTableGetNodeInRow(newRow, 'resAvilDateTo').value = resWhAvail.whToDate;
                            $('#tbody_date').append('<tr><td width="1%"><i id="del_saleR" class="dct-icon fa fa-minus-circle hidden" onclick="delete_row(this, \'dyn_table_date\')"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value="' + resWhAvail.id + '"/></td>\n\
<td width="15%"><input class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDateFrom" name="resAvilDateFrom" value="' + resWhAvail.whFromDate + '" placeholder="From Date"/></td>\n\
<td width="15%"><input class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDateTo" value="' + resWhAvail.whToDate + '" name="resAvilDateTo" placeholder="To Date"/></td>\n\
<td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" value="' + resWhAvail.whFromTime + '" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" name="fromTime"/></td>\n\
<td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" value="' + resWhAvail.whToTime + '" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" name="ToTime"/></td>\n\
                    <td width="1%"><i class="dct-icon fa fa-plus-square red" onclick="insert_row(\'dyn_table_date\', this)"></i></td></tr>');
                        }
                    });
//                if (parseInt(flag) === 1) {
//                    var tab = document.getElementById('dyn_table_day');
//                    dynTableDeleteRow(tab.rows[0]);
//                } else if (parseInt(flag) === 2) {
//                    var tab1 = document.getElementById('dyn_table_date');
//                    dynTableDeleteRow(tab1.rows[0]);
                    //                }
                });
            }
        });
    }
    function searchUser(val) {
        dcomethealth.MasterResource.searchUsers({"id": val}, function (data) {
            $.each(dcomethealth.s_user = data, function (index, s_user) {
                if (val == s_user.id) {
                    $("#userRid").val(s_user.id);
                    $("#staffUname").val(s_user.userID);
                    $("#staffEmail").val(s_user.userEmail);
                    var userRolesList = [];
                    if (!!s_user.userRoleMap) {
                        $.each(s_user.userRoleMap, function (idx, urRoleMap) {
                            $.each(dcometRoles, function (index, roles) {
                                if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID)) {
                                    if (parseInt(urRoleMap.urRoleRID) === parseInt(roles.id)) {
                                        var item = {};
                                        item ["id"] = roles.id;
                                        item ["text"] = roles.roleName;
                                        userRolesList.push(item);
                                        $('#tbody_hidden').append('<tr><td><input type="hidden" id="urRoleMapRid" name="urRoleMapRid" value="' + urRoleMap.id + '"/><input type="hidden" id="urRoleRid" name="urRoleRid" value="' + roles.id + '"/></td></tr>');
                                    }
                                }
                            });
                        });
                        $("#s2id_roles").select2('data', userRolesList);
                    } else {
                        $("select.dcomet-c-s_roles-list").empty();
                        $("select.dcomet-c-s_roles-list", $("#StaffMaster")).each(function (idx, elem) {
                            $.each(dcometRoles, function (index, roles) {
                                if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID) && !!roles.roleValid) {
                                    $(elem).append('<option value="' + roles.id + '">' + roles.roleName + '</option>');
                                }
                            });
                            var opts = $("#source").html(), opts2 = "<option></option>" + opts;
                            $("select.populate").each(function () {
                                var e = $(this);
                                e.html(e.hasClass("placeholder") ? opts2 : opts);
                            });
                        });
                        $('#tbody_hidden').empty();
                        $('.select2-choices').find('.select2-search-choice').each(function () {
                            $(this).remove();
                        });
                        $('#tbody_hidden').append('<tr><td><input type="hidden" id="urRoleMapRid" name="urRoleMapRid" value=""/></td></tr>');
                    }
//                    var uUnitList = [];
//                    $.each(s_user.userUnitMapList, function (idx, uUnitM) {
//                        $.each(uUnitM.unit, function (uIdx, units) {
//                            if (uUnitM.suUnitRID == units.id) {
//                                var item = {};
//                                item ["id"] = units.id;
//                                item ["text"] = units.unitName;
//                                uUnitList.push(item);
//                            }
//                        });
//                    });
                    //                    $("#s2id_units").select2('data', uUnitList);
                }
            });
        });
    }
    function submit() {
        var form = $("form");
        if (dcomethealth.StaffMaster.validateForm(form)) {
            var check = document.getElementById('openPmd');
            var check1 = document.getElementById('datew');
            var isFccondition = 0;
            if (check.checked) {
                var table = document.getElementById('dyn_table_day');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value != "" && dynTableGetNodeInRow(table.rows[i + 1], 'fromTime').value != "" && dynTableGetNodeInRow(table.rows[i + 1], 'ToTime').value != "") {

                    } else {
                        alert("Enter All The Fields");
                        return false;
                    }
                }
            } else if (check1.checked) {
                var table1 = document.getElementById('dyn_table_date');
                var table_length1 = table1.rows.length;
                for (var i = 0; i < table_length1 - 1; i++) {
                    if (dynTableGetNodeInRow(table1.rows[i + 1], 'resAvilDateFrom').value != "" && dynTableGetNodeInRow(table1.rows[i + 1], 'resAvilDateTo').value != "" && dynTableGetNodeInRow(table1.rows[i + 1], 'fromTime').value != "" && dynTableGetNodeInRow(table1.rows[i + 1], 'ToTime').value != "") {

                    } else {
                        alert("Enter All The Fields");
                        return false;
                    }
                }
            } else {
                alert("Select Atleast One Availability Timing Box");
                return  false;
            }
            var staff = {}, user = {}, resource = {};
            var staffList = [], resourceList = [];
//            var resAvailList = [], resWhList = [];
            var resWhList = [];
            var resUnitMapList = [], staffUnitMapList = [], child1List = [], child3List = [];
            if ($("#userRid").val() != "") {
                user.id = $("#userRid").val();
            }
            user.userID = $("#staffUname").val();
            user.userType = "Staff";
            user.userFullName = $("#staffName").val();
//            user.userGender = $("#lensValid").val();
//            user.userDOB = $("#lensValid").val();
            //            user.userStreetAddress = $("#lensValid").val();
            user.userCity = $("#staffCity").val();
            user.userCountry = $("#staffCountry").val();
            user.userPincode = $("#staffPcode").val();
            user.userPhone = $("#staffContactNumber").val();
            user.userMobile = $("#staffContactNumber").val();
            user.userEmail = $("#staffEmail").val();
            user.userValid = 1;
            user.userIsStaff = 1;
            if ($("#resRid").val() != "") {
                resource.id = $("#resRid").val();
            }
            resource.resName = $("#staffName").val();
            resource.resType = 1;
            resource.resEntRID = dcomethealth.loginuser.entityRid;
            resource.resSchedInterval = $("#resSchedInterval").val() ? $("#resSchedInterval").val() : 10;
            //            resource.resSchedCapacity = $("#lensValid").val();
            resource.resUnitRID = $("#primaryUnit").val();
            resource.resValid = 1;
//            resource.resContWorkingSlotCount = $("#lensValid").val();
            //            resource.reseservedSlotCount = $("#lensValid").val();
            resource.portResID = 0;
            resource.resSequenceNumber = 0;
            resource.resCategory = $("#staffCategory").val();
            resource.resSubcategory = 0;
            resource.resSchedDuration = 20;
            var table = document.getElementById('unit_dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i <= table_length - 1; i++) {
                var staffUnitMap = {};
                var resourceUnitMap = {};
                var unitCheck = dynTableGetNodeInRow(table.rows[i], 'unitCheck');
                if (unitCheck.checked) {
                    if (parseInt(dynTableGetNodeInRow(table.rows[i], 'unitRid').value) === parseInt($('#primaryUnit option:selected').val())) {
                        staffUnitMap.suIsPrimaryUnit = 1;
                    } else {
                        staffUnitMap.suIsPrimaryUnit = 0;
                    }
                    staffUnitMap.suUnitRID = dynTableGetNodeInRow(table.rows[i], 'unitRid').value;
                    resourceUnitMap.ruUnitRID = dynTableGetNodeInRow(table.rows[i], 'unitRid').value;
                    resUnitMapList.push(resourceUnitMap);
                    staffUnitMapList.push(staffUnitMap);
                }
            }
            if (parseInt($('#collect').val()) === 1) {
                var table2 = document.getElementById('dyn_table_day');
            } else if (parseInt($('#availdate').val()) === 1) {
                var table2 = document.getElementById('dyn_table_date');
            }
            var table_length2 = table2.rows.length;
            for (var i = 0; i < table_length2 - 1; i++) {
//                var resAvail = {};
                var resWh = {};
//                if (dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilRid').value != "") {
//                    resAvail.id = dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilRid').value;
//                }
//                resAvail.availUnitRID = $("#primaryUnit").val();
//                resAvail.availFromDateTime = dynTableGetNodeInRow(table.rows[i], 'skuRid').value;
                //                resAvail.availToDateTime = dynTableGetNodeInRow(table.rows[i], 'skuRid').value;
//                resAvail.availIsUserSpecified = 1;
//                resAvail.availFromDateFromTime = moment(dynTableGetNodeInRow(table2.rows[i + 1], 'fromTime').value, 'HH:mm').format('HH:mm:ss');
//                resAvail.availToDateToTime = moment(dynTableGetNodeInRow(table2.rows[i + 1], 'ToTime').value, 'HH:mm').format('HH:mm:ss');
//                resAvail.availStyle = dynTableGetNodeInRow(table.rows[i], 'skuRid').value;
//                resAvail.availReason = dynTableGetNodeInRow(table.rows[i], 'skuRid').value;

                if (dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilWhRid').value != "") {
                    resWh.id = dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilWhRid').value;
                }
                resWh.whEntityRID = dcomethealth.loginuser.entityRid;
                //                resWh.whToDate = dynTableGetNodeInRow(table.rows[i], 'skuRid').value;
                if (parseInt($('#collect').val()) === 1) {
                    resWh.whDayOfWeek = dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilDays').value;
//                    resAvail.availFlag = 1;
                    resWh.whFlag = 1;
                }
                if (parseInt($('#availdate').val()) === 1) {
                    resWh.whFromDate = dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilDateFrom').value;
                    resWh.whToDate = dynTableGetNodeInRow(table2.rows[i + 1], 'resAvilDateTo').value;
//                    resAvail.availFlag = 2;
                    resWh.whFlag = 2;
                }
                resWh.whFromTime = moment(dynTableGetNodeInRow(table2.rows[i + 1], 'fromTime').value, 'HH:mm').format('HH:mm:ss');
                resWh.whToTime = moment(dynTableGetNodeInRow(table2.rows[i + 1], 'ToTime').value, 'HH:mm').format('HH:mm:ss');
                //                resWh.whType;
                resWh.whUnitRID = $("#primaryUnit").val();
                resWhList.push(resWh);
//                resAvailList.push(resAvail);
            }
            var RID = $("#staffRID").val();
            if (RID === "") {
                RID = null;
            }
            staff.id = RID;
            staff.staffUnitRID = $("#primaryUnit").val();
            staff.staffEntityRID = dcomethealth.loginuser.entityRid;
            staff.staffName = $("#staffName").val();
            staff.staffCategory = $("#staffCategory").val();
            staff.staffContactNumber = $("#staffContactNumber").val();
            staff.staffDesignation = $("#staffDesignation").val();
            staff.staffDOJ = $("#staffDOJ").val();
            staff.staffDOL = $("#staffDOL").val();
            staff.staffValid = 1;
            staff.staffIsConsultant = $("#staffIsConsultant").val();
            if (!!$("#staffIsSurgeon").val() && $("#staffIsSurgeon").val() != "undefined") {
                staff.staffIsSurgeon = $("#staffIsSurgeon").val();
            } else {
                staff.staffIsSurgeon = 0;
            }
            if (!!$("#staffIsAdmittingDoctor").val() && $("#staffIsAdmittingDoctor").val() != "undefined") {
                staff.staffIsAdmittingDoctor = $("#staffIsAdmittingDoctor").val();
            } else {
                staff.staffIsAdmittingDoctor = 0;
            }
            if ($("#staffAccountNumber").val() !== "") {
                staff.staffAccountNumber = $("#staffAccountNumber").val();
            }
            if ($("#staffBankName").val() !== "") {
                staff.staffBankName = $("#staffBankName").val();
            }
            if ($("#staffBankBranch").val() !== "") {
                staff.staffBankBranch = $("#staffBankBranch").val();
            }
            if ($("#staffIfscCode").val() !== "") {
                staff.staffIfscCode = $("#staffIfscCode").val();
            }
            if ($("#staffPanCardNo").val() !== "") {
                staff.staffPanCardNo = $("#staffPanCardNo").val();
            }
//            staff.staffFirstConsultationFee = $("#FreshConsultation").val();
            //            staff.staffFollowupConsultationFee = $("#ReviewConsultation").val();
            staff.staffMaxConsultationNo = $("#MaximumConsultation").val();
            if ($("#staffCompensation").val() == "") {
                $("#staffCompensation").val() == null;
            } else {
                staff.staffCompensation = $("#staffCompensation").val();
            }
            staff.staffSpecialityIndex = $("#staffAbbrv option:selected").val();
            staff.staffSpecialityAbbrv = $("#staffAbbrv option:selected").text();
            staff.staffAbbrv = $("#staffAbbrv option:selected").text();
            staff.staffCity = $("#staffCity").val();
            staff.staffState = $("#staffState").val();
            staff.staffCountry = $("#staffCountry").val();
            staff.staffZipCode = $("#staffPcode").val();
            staff.staffTitle = $("#staffTitle option:selected").val();
            var fc_table = document.getElementById('dyn_table_fc');
            var fc_table_length = fc_table.rows.length;
            var fcTransitionList = [];
            if (fc_table_length > 1) {
                if (!!$("#fcCondition").val() && $("#fcCondition").val() != 0) {
                    var fcCategory = {};
                    if (!!$("#fcCategory").val() && $("#fcCategory").val() != 0) {
                        fcCategory.fccRid = $("#fcCategory").val();
                    }
                    if (!!$("#staffRID").val() && $("#staffRID").val() != 0) {
                        fcCategory.fccResourceRid = $("#staffRID").val();
                    }
                    fcCategory.fccConditionRid = $("#fcCondition").val();
                    for (var i = 0; i < fc_table_length - 1; i++) {
                        if (!parseInt(dynTableGetNodeInRow(fc_table.rows[i + 1], 'fcServiceRID').value) || parseInt(dynTableGetNodeInRow(fc_table.rows[i + 1], 'fcServiceRID').value) == 0) {
                            alert("Select Service Name");
                            return false;
                        } else {
                            isFccondition = 1;
                            var fcTransition = {};
                            fcTransition.fctType = 1;
                            fcTransition.fctFromState = parseInt(dynTableGetNodeInRow(fc_table.rows[i + 1], 'fcVisitCount').value) - 1;
                            fcTransition.fctToState = parseInt(dynTableGetNodeInRow(fc_table.rows[i + 1], 'fcVisitCount').value);
                            fcTransition.fctServiceRid = parseInt(dynTableGetNodeInRow(fc_table.rows[i + 1], 'fcServiceRID').value);
                            fcTransitionList.push(fcTransition);
                        }
                    }
                } else {
                    isFccondition = 0;
                }
            }

            $("#s2id_roles").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                var current = $(this);
                $.each(dcometRoles, function (index, roles) {
                    if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID) && !!roles.roleValid) {
                        if (roles.roleName === current.text()) {
                            var selectedId = $(current).find("input[type='hidden']").val();
                            var table = document.getElementById('dyn_table');
                            var table_length = table.rows.length;
                            var child = {};
                            for (var i = 0; i < table_length; i++) {
                                if (dynTableGetNodeInRow(table.rows[i], 'urRoleMapRid').value != "") {
                                    if (dynTableGetNodeInRow(table.rows[i], 'urRoleRid').value == selectedId) {
                                        child.id = dynTableGetNodeInRow(table.rows[i], 'urRoleMapRid').value;
                                    }
                                }
                            }
                            child.urRoleRID = roles.id;
                            child1List.push(child);
                        }
                    }
                });
            });
            resource.resourceUnitMap = resUnitMapList;
            resource.resourceWorkingHours = resWhList;
//            resource.resourceAvailability = resAvailList;
            staff.userUnitMaps = staffUnitMapList;
            if (parseInt(isFccondition) == 1) {
                staff.freeConsultationTransitions = fcTransitionList;
                staff.freeConsultationCategory = fcCategory;
            }

            staffList.push(staff);
            resource.staff = staffList;
            resourceList.push(resource);
            user.resource = resourceList;
            user.userRoleMap = child1List;
            alert(user.userRoleMap);
            console.log(user);
            return false;
            dcomethealth.MasterResource.saveUser(user).done(function (data, textStatus, jqXHR) {
                console.log(data);
                dcomethealth.util.loadpage('StaffMaster');
                alert("Data saved");
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function validateForm(form) {
        return  form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        searchPostalCode: searchPostalCode,
        fetchStaffList: fetchStaffList,
        showStaffDetail: showStaffDetail,
        getCity: getCity,
        getStates: getStates,
        service_AutoComplete: service_AutoComplete,
        checkServiceValid: checkServiceValid,
        getFcCondition: getFcCondition,
        populateFields: populateFields,
        searchResource: searchResource,
        searchUser: searchUser,
        submit: submit,
        validateForm: validateForm,
        refreshData: refreshData
    };
}());
dcomethealth.StaffMaster.init();