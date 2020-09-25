var dcomethealth = dcomethealth || {}, currencyExchange = {};
dcomethealth.IPAdmission = (function () {
    var id = "IPAdmission", serviceClearVar = 0, staffData = {}, unitObj = {}, servicePoint = {}, delKinList = [], procedureReq = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().startOf('days'), end = moment().endOf('days');
            $('#patientRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            var ddictValue = 0;
            $.each(dcomethealth.sDdict, function (pIdx, s_ddict) {
                if (s_ddict.dditCode === "UNIT_TYPE") {
                    $.each(s_ddict.ddict, function (i) {
                        if (s_ddict.ddict[i].ddictValue === "Operation Theatre") {
                            ddictValue = s_ddict.ddict[i].id;
                        }
                    });
                }
                if (s_ddict.dditCode === "VISIT_TYPE") {
                    $.each(s_ddict.ddict, function (i) {
                        if (s_ddict.ddict[i].ddictValue === "IP") {
                            $("#visitType").val(s_ddict.ddict[i].id);
                        }
                    });
                }
            });
            viewPatient();
            dcomethealth.MasterResource.searchUnit({"unitHasBeds": 1}, function (data) {
                $.each(unitObj = data, function (pIdx, unit) {
                    if (unit.unitTypeIndex != ddictValue) {
                        $('#unitName').append('<option value="' + unit.id + '">' + unit.unitName + '</option>');
                    }
                });
            });
            $('#patientRange').daterangepicker({
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    'Last 7 Days': [moment().subtract('days', 6), moment()],
                    'Last 30 Days': [moment().subtract('days', 29), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]},
                opens: 'left',
                startDate: moment().subtract('days', 29),
                endDate: moment()
            },
            function (start, end) {
                $('#patientRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                viewPatient();
            });
            doctorSearch();
            autocomplete();
            autocompleteService();
            autocompleteProcedure();
            if (dcomethealth.clearPage == 1) {
                $("#IPAdmissionHeader").addClass("hidden");
                $("#addNew").removeClass("hidden");
            }
            $.each(dcomethealth.s_countries, function (pIdx, s_countries) {
                $(s_countries).each(function () {
                    if (s_countries.couCode === dcomethealth.userEntityCountryCode) {
                        $('#prefixMobile').val(s_countries.couPhoneCode);
                    }
                });
            });
            if (dcomethealth.security.isPrivillage(13) === true) {
                document.getElementById('collectPayment').value = 1;
                $("#paymentMode").removeClass("hidden").addClass("panel panel-primary");
            } else {
                document.getElementById('collectPayment').value = 0;
                $("#paymentMode").removeClass("panel panel-primary").addClass("hidden");
            }
            dcomethealth.DataDictionaryResource.getCurrencyExchange({}, function (data) {
                if (!!data) {
                    currencyExchange = data;
                }
            });
            $.each(dcomethealth.s_countries, function (pIdx, s_countries) {
                $(s_countries).each(function () {
                    if (s_countries.couCode === dcomethealth.userEntityCountryCode) {
                        $('#prefixMobile').val(s_countries.couPhoneCode);
                    }
                });
            });
            var searchobjs = {"currMapEntRID": dcomethealth.loginuser.entityRid};
            dcomethealth.DataDictionaryResource.getCurrencyMap(searchobjs, function (data) {
                var options = "";
                if (!!data) {
                    var count = 0;
                    $.each(data, function (idx, cur) {
                        count++;
                        options += '<option value="' + cur.currencyM.id + '">' + cur.currencyM.curShortName + '</option>';
                    });
                    if (count > 1) {
                        $("#pay_mode_currency").html(options);
                    } else {
                        $("#pay_mode_currency").html(options);
                        $("#pay_mode_currency").addClass("hidden");
                    }
                }
            });
            $("#edit_user_form").validate({
                rules: {
                    patName: "required",
                    patMobile: "required",
                    patDOB: "required",
                    patMaritalStatus: {
                        selectMarStatus: true
                    },
                    unitName: {
                        selectUnit: true
                    },
                    servicePoint: {
                        selectServiceUnit: true
                    },
                    groupName: {
                        selectGroup: true
                    }
                },
                messages: {
                    patName: "Enter Patient Name",
                    patMobile: "Enter Mobile Number",
                    patDOB: "Enter Date of Birth",
                },
                submitHandler: function (form) {
                    if (!$("#groupName").val()) {
                        alert("Select bed group");
                        $("#groupName").focus();
                        return false;
                    } else {
                        if (parseInt($("#isExistGroup").val()) == 1 && (!$("#sBedName").text() || $("#sBedName").text() == "undefined")) {
                            alert("Bed name is empty");
                            return false;
                        }
                    }
                    if ($("#procedureCheck").is(':checked')) {
                        if (!$("#procedureRid").val()) {
                            alert("Enter procedure name");
                            $("#procedureName").focus();
                            return false;
                        } else if (parseInt($("#primaryDoctorsList option:selected").val()) == 0) {
                            alert("Select primary surgeon");
                            return false;
                        }
                    }
                    var kinCheck = false;
                    var kinTable = document.getElementById('kinTable');
                    var j = 1;
                    if (parseInt(kinTable.rows.length) == 2) {
                        j = 0;
                    }
                    for (var k = j; k < kinTable.rows.length - 1; k++) {
                        var kinName = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').value;
                        var kinMobileNo = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinMobileNo').value;
                        var kinOccupation = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinOccupation').value;
                        if (kinName == '') {
                            alert("Need kin name");
                            dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').focus();
                            return false;
                        }
                        if (kinMobileNo == "") {
                            alert("Need kin mobile number");
                            dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinMobileNo').focus();
                            return false;
                        }
                        if (kinOccupation == "") {
                            alert("Need kin occupation");
                            dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinOccupation').focus();
                            return false;
                        }
                    }
                    var totAmount = parseFloat($("#total_pmd").val());
                    if (!$("#total_pmd").val() || $("#total_pmd").val() == 0) {
                        alert("Enter admitting advance");
                        $("#total_pmd").focus();
                        return false;
                    } else {
                        var table = document.getElementById('pmd_table');
                        var table_length = table.rows.length;
                        var pmdAmount = 0;
                        for (var i = 0; i < table_length; i++) {
                            pmdAmount = dynTableGetNodeInRow(table.rows[i], 'pmd_amount').value;
                            if (pmdAmount == '') {
                                alert("Enter payment");
                                dynTableGetNodeInRow(table.rows[i], 'pmd_amount').focus();
                                return false;
                            }
                        }
                        if (table_length == 1) {
                            $("#advTextAmnt").text(pmdAmount);
                        }
                        if (parseFloat($("#advTextAmnt").text()) != parseFloat(totAmount)) {
                            alert("Total advance amount should be equal to admitting advance");
                            return false;
                        }
                    }
                    submit(0, "VISIT", "BUILTIN_ACTION", $("#collectPayment").val());
                }
            });
            jQuery.validator.addMethod('selectMarStatus', function (value) {
                return (value != '0');
            }, "Select Marital Status");
            jQuery.validator.addMethod('selectUnit', function (value) {
                return (value != '0');
            }, "Select Admitting Unit");
            jQuery.validator.addMethod('selectServiceUnit', function (value) {
                return (value != '0');
            }, "Select Service Unit");
            jQuery.validator.addMethod('selectGroup', function (value) {
                return (value != '0');
            }, "Select Bed Group");
        });
    }
    function checkDate(elem) {
        var curMonth = moment().format("MM");
        var curYear = moment().format("YYYY");
        var valueDate = (elem.value).split("/");
        if (valueDate[0] >= curMonth && valueDate[1] >= curYear) {
        } else {
            alert("Enter valid month and year");
            elem.value = "";
            return false;
        }
        return false;
    }
    function deleteRow(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('kinTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (table_length > 2) {
                if (!!dynTableGetNodeInRow(elem, 'kinRid').value && dynTableGetNodeInRow(elem, 'kinRid').value != "" && dynTableGetNodeInRow(elem, 'kinRid').value != "undefined") {
                    var kinObj = {};
                    kinObj.id = dynTableGetNodeInRow(elem, 'kinRid').value;
                    kinObj.kinName = dynTableGetNodeInRow(elem, 'kinName').value;
                    kinObj.kinMobileNo = dynTableGetNodeInRow(elem, 'kinMobileNo').value;
                    kinObj.kinRelationship = dynTableGetNodeInRow(elem, 'kinRelationship').value;
                    kinObj.kinOccupation = dynTableGetNodeInRow(elem, 'kinOccupation').value;
                    kinObj.kinIsActive = 0;
                    delKinList.push(kinObj);
                }
                dynTableDeleteRow(x);
                dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus";
                return false;
            }
        }
    }
    function getStates() {
        var isExist = true;
        $.each(dcomethealth.s_countries, function (pIdx, s_countries) {
            $(s_countries).each(function () {
                var cntryCode = $('#patCountry option[value="' + $('#cntryName').val() + '"]').text();
                if (s_countries.couCode == cntryCode.substring(0, 2)) {
                    $('#prefixMobile').val(s_countries.couPhoneCode);
                }
                if (cntryCode == "") {
                    isExist = false;
                }
            });
        });
        dcomethealth.DataDictionaryResource.searchStates({}, function (data) {
            dcomethealth.s_states = data;
            var targetContainer = targetContainer || document;
            $("datalist.dcomet-c-s_states-list", targetContainer).each(function (idx, elem) {
                $.each(dcomethealth.s_states, function (pIdx, s_states) {
                    $(s_states).each(function () {
                        var cntryName = $('#patCountry option[value="' + $('#cntryName').val() + '"]').text();
                        if (cntryName === s_states.countryCode) {
                            $(elem).append('<option value="' + s_states.states + '">' + s_states.stateRid + '</option>');
                        }
                    });
                });
            });
        });
    }
    function viewGroupWiseBed(elem) {
        $("#bedDetails").empty();
        if (!!$(elem).val()) {
            $.each(bedGroupObj, function (id, bedGroupM) {
                if (parseInt($(elem).val()) == bedGroupM.bgmRid) {
                    if (bedGroupM.bedMasterList.length > 1) {
                        $.each(bedGroupM.bedMasterList, function (idx, bedMaster) {
                            if (bedMaster.bedIsActive == 1 && bedMaster.bedStatus == 0) {
                                $("#bedDetails").append('<a href="#grprooms" class="aTag" onclick="dcomethealth.IPAdmission.bedSelection(' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedMaster.bedBgmRid + ')"><div class= "col-md-4 col-sm-4 col-xs-4"  style="cursor:pointer"><img src="/health_web/css/images/bedImages/bedOccupy2.png"   width="65" height="55" alt="bed"></img><span id="bedName" class="row col-md-12" >' + bedMaster.bedName + '</span><input value="' + bedMaster.bedRid + '" id="btmRid" type="hidden" /><input value="' + bedMaster.bedBgmRid + '" id="btmPrice" type="hidden" /></div></a>');
                            } else {
                                $("#bedDetails").append('<a href="#grprooms" class="aTag"><div class= "col-md-4 col-sm-4 col-xs-4" style="cursor:not-allowed;"><img src="/health_web/css/images/bedImages/bedOccupy.svg"   width="65" height="55" alt="bed"></img><span id="bedName" class="row col-md-12" >' + bedMaster.bedName + '</span><input value="' + bedMaster.bedRid + '" id="bedRid" type="hidden" /><input value="' + bedMaster.bedBgmRid + '" id="bedBgmRid" type="hidden" /></div></a>');
                            }
                        });
                        $("#helpModel").modal('show');
                    } else {
                        $.each(bedGroupM.bedMasterList, function (idx, bedMaster) {
                            if (bedMaster.bedIsActive == 1 && bedMaster.bedStatus == 0) {
                                bedSelection(bedMaster.bedRid, bedMaster.bedName, bedMaster.bedBgmRid);
                            } else {
                                $("#viewBedDiv").addClass('hidden');
                                alert("Room not available");
                            }
                        });
                    }
                }
            });
        } else {
            $("#viewBedDiv").addClass("hidden");
        }
    }
    function unitWiseSerPoint(elem) {
        dcomethealth.MasterResource.getServicePoints({"wrdUnitRid": $(elem).val(), "wrdEntityRid": dcomethealth.loginuser.entityRid}, function (data) {
            if (!!data && data.length > 0) {
                $("#spDiv,#groupName,#subGroup").removeClass('hidden');
                $("#servicePoint").empty();
                $("#servicePoint").append('<option value="0">--Choose--</option>');
                $.each(servicePoint = data, function (index, sPoint) {
                    $("#servicePoint").append('<option value="' + sPoint.wrdRid + '">' + sPoint.wrdName + '</option>');
                });
            } else {
                $('#sBedName').text('');
                $('#sBedCharge').text('');
                $('#sBedRid').text('');
                $("#spDiv").addClass('hidden');
                $("#bedDetails").empty();
                var searchParams = {"bgmBedUnitRid": $(elem).val()};
                dcomethealth.BedManagementResource.searchBedGroupMaster(searchParams, function (data) {
                    if (!!data && data.length > 0) {
                        $.each(data, function (id, bedGroupM) {
                            $.each(bedGroupM.bedMasterList, function (id, bedMaster) {
                                if (bedMaster.bedIsActive == 1 && bedMaster.bedStatus == 0) {
                                    $("#bedDetails").append('<a href="#grprooms" class="aTag" onclick="dcomethealth.IPAdmission.bedSelection(' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedMaster.bedBgmRid + ')"><div class= "col-md-4 col-sm-4 col-xs-4"  style="cursor:pointer"><img src="/health_web/css/images/bedImages/bedOccupy2.png"   width="65" height="55" alt="bed"></img><span id="bedName" class="row col-md-12" >' + bedMaster.bedName + '</span><input value="' + bedMaster.bedRid + '" id="bedRid" type="hidden" /><input value="' + bedMaster.bedBgmRid + '" id="bedBgmRid" type="hidden" /></div></a>');
                                } else {
                                    $("#bedDetails").append('<a href="#grprooms" class="aTag"><div class= "col-md-4 col-sm-4 col-xs-4" style="cursor:not-allowed;"><img src="/health_web/css/images/bedImages/bedOccupy.svg"   width="65" height="55" alt="bed"></img><span id="bedName" class="row col-md-12" >' + bedMaster.bedName + '</span><input value="' + bedMaster.bedRid + '" id="bedRid" type="hidden" /><input value="' + bedMaster.bedBgmRid + '" id="bedBgmRid" type="hidden" /></div></a>');
                                }
                            });
                        });
                        $("#helpModel").modal('show');
                    } else {
                        $('#sBedName').text('');
                        $('#sBedCharge').text('');
                        $('#sBedRid').text('');
                        $('#spGroupDiv,#bedNameLabel').addClass("hidden");
                        $("#groupName").empty();
                        $('#groupName,#subGroup').addClass("hidden");
                        alert("Bed does not exist");
                        return false;
                    }
                });
            }
        });
        var options = "", options1 = "";
        $.each(staffData, function (idx, Val) {
            if (parseInt(Val.staffCategory) == 237) {
                if (!!Val.userUnitMaps) {
                    $.each(Val.userUnitMaps, function (sdx, stfMap) {
                        if ($("#unitName option:selected").val() == stfMap.suUnitRID) {
                            options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                        }
                    });
                }
            }
        });
        if (options != "") {
            $("#attendingDoctorsList").empty();
            $("#attendingDoctorsList").html(options);
        } else {
            $("#attendingDoctorsList").empty();
            alert("No Doctor incharge available");
        }
    }
    function autoIdSet(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'serviceRID').value = "";
            dynTableGetNodeInRow(elem, 'serviceName').value = "";
            dynTableGetNodeInRow(elem, 'MRP').value = "";
            dynTableGetNodeInRow(elem, 'netAmount').value = "";
            dynTableGetNodeInRow(elem, 'serviceDisc').value = "";
            dynTableGetNodeInRow(elem, 'qtyService').value = "";
        }
    }
    function getPatient(patRid) {
        $("#IPAdmissionHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#addNew").removeClass("hidden").addClass("panel panel-primary");
        $("#visitReasonDiv").addClass("hidden");
        $("#payerDiv").addClass("hidden");
        $("#serviceOrder").addClass("hidden");
        $("#reset").addClass("hidden");
        $("#paymentDiv").addClass("hidden");
        $('#patImage').attr('src', '');
        dcomethealth.PatientResource.searchPatient({"id": patRid}, function (data) {
            $.each(data, function (Index, data) {
                $("#update").val(1);
                $('#patTitle option[value="' + data.patTitle + '"]').prop('selected', true);
                $("#mrnSearch").val(data.patMrnNo);
                $('#patName').val(data.patName);
                $('#fstName').val(data.patName);
                $('#patRid').val(data.id);
                $('#LName').val(data.patFamilyName);
                $('#patDOB').val(data.patDob);
                var c = $('#patDOB').val().split('-');
                $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                $('#patGender option[value="' + data.patGenderIndex + '"]').prop('selected', true);
                $('#patMaritalStatus option[value="' + data.patMaritalStatus + '"]').prop('selected', true);
                $('#patMobile').val(data.patPhoneNo);
                $('#Mobile').val(data.patPhoneNo);
                $('#patEmail').val(data.patEmailId);
                $('#pinCode').val(data.patPincode);
                $('#stateGeo').val(data.patStateIndex);
                $("#patCity").val(data.patCityIndex);
                $('#cntryName').val(data.patCountryIndex);
                $('#patAddress').val(data.patAddress);
                $('#patBldGrp').val(data.patBloodGroupIndex);
                $('#isVIP').val(data.patVipStatus);
                if (parseInt($('#isVIP').val()) === 1) {
                    document.getElementById("openVip").checked = true;
                }
                dcomethealth.PatientResource.searchPatientImage(data.id, function (data) {
                    if (!!data) {
                        $("#resourceId").val(data);
                        $('#patImage').attr('src', '/health_web/rest/patient/v1/photo?imgId=' + data);
                    } else {
                        $('#patImage').attr('src', '');
                    }
                });
            });
        });
    }
    function addAdvance(elem) {
        var table = document.getElementById('pmd_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length; i++) {
            if (table_length == 1) {
                dynTableGetNodeInRow(table.rows[i], 'pmd_amount').value = elem.value;
            }
        }
    }
    function viewPatient() {
        var date = $('#patientRangeSpan').html().split('-');
        var searchObj = {"visFromDate": moment(date[0]), "visToDate": moment(date[1]).add(1, 'days')};
        dcomethealth.PatientResource.searchIpVisit(searchObj, function (data) {
            $('#patient_Table').empty();
            if (data != undefined) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $.each(data, function (pIdx, vis) { //onclick="dcomethealth.Patient.getPatient(' + Pat.id + ')"
                    $.each(dcomethealth.patientData = vis.patient, function (pIdx, Pat) { //onclick="dcomethealth.Patient.getPatient(' + Pat.id + ')"
                        var Print = '<a class="btn btn-primary hidden-xs" onclick="dcomethealth.IPAdmission.patientIdCard(' + Pat.id + ')"><i class="fa fa-id-card-o"></i></a>';
                        var Edit = '<a class="btn btn-primary hidden-xs" onclick="dcomethealth.IPAdmission.getPatient(' + Pat.id + ')"><i class="fa fa-edit"></i></a>';
                        $('#patient_Table').append('<tr><td>' + Pat.patMrnNo + '</td><td>' + Pat.patFullName + '</td>\n\
                    <td>' + Pat.patPhoneNo + '</td><td align="right" width="20%">' + Print + ' ' + Edit + '</td></tr>');
                    });
                });
                $('#tab_Admissoin_details').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $('#tab_Admissoin_details').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function autocomplete() {
        $("#" + id + " input[name='mrnSearch']").autocomplete({
            select: function (event, ui) {
                $('#lvDateLi').removeClass('hidden');
                var RegDate = "New Entry";
                dcomethealth.PatientResource.searchVisit({"visPatRid": ui.item.data.id}, function (data2) {
                    $("#patientId").removeClass("hidden");
                    if (data2 != undefined) {
                        RegDate = data2[0].createdDateTime;
                        $.each(data2, function (i, visit) {
                            if (moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') === moment().format('DD-MM-YYYY')) {
                                if (visit.visTypeIndex == $("#visitType").val()) {
                                    alert("Patient is active today");
                                    dcomethealth.util.base_init();
                                    dcomethealth.util.loadpage('IPAdmission');
                                }
                            }
                        });
                        $('#lastVDate').text(moment(RegDate, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY'));
                    } else {
                        $('#lastVDate').text(RegDate);
                    }
                    dcomethealth.PatientResource.searchPatientImage(ui.item.data.id, function (data) {
                        if (!!data) {
                            $("#resourceId").val(data);
                            $('#patImage').attr('src', '/health_web/rest/patient/v1/photo?imgId=' + data);
                        } else {
                            $('#patImage').attr('src', '');
                        }
                    });
                    var validator = $("#edit_user_form").validate();
                    validator.resetForm(); // Need to clear validation error messages
                    $('#patTitle option[value="' + ui.item.data.patTitle + '"]').prop('selected', true);
                    $('#patName').val(ui.item.data.patFullName);
                    $('#patRid').val(ui.item.data.id);
                    $('#LName').val(ui.item.data.patFamilyName);
                    $('#patDOB').val(ui.item.data.patDob);
                    var c = $('#patDOB').val().split('-');
                    $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                    $('#patGender option[value="' + ui.item.data.patGenderIndex + '"]').prop('selected', true);
                    $('#patMaritalStatus option[value="' + ui.item.data.patMaritalStatus + '"]').prop('selected', true);
                    $('#patMobile').val(ui.item.data.patPhoneNo);
                    $('#Mobile').val(ui.item.data.patPhoneNo);
                    $('#patEmail').val(ui.item.data.patEmailId);
                    $('#pinCode').val(ui.item.data.patPincode);
                    $('#stateGeo').val(ui.item.data.patStateIndex);
                    $("#patCity").val(ui.item.data.patCityIndex);
                    $('#cntryName').val(ui.item.data.patCountryIndex);
                    $('#patAddress').val(ui.item.data.patAddress);
                    $('#patBldGrp').val(ui.item.data.patBloodGroupIndex);
                    $('#isVIP').val(ui.item.data.patVipStatus);
                    if (parseInt($('#isVIP').val()) === 1) {
                        document.getElementById("openVip").checked = true;
                    }
                });
                document.getElementById("patRID").value = parseInt(ui.item.data.id);
                if (!!ui.item.data.kinDetails) {
                    $("#kinTbody").empty();
                    $("#kinTbody").append('<tr><td><i id="kinDetailsDel" class="ace-icon fa fa-minus" onclick="dcomethealth.IPAdmission.deleteRow(this)"></i><input type="hidden" id="kinRid" name="kinRid" value=""/></td>\n\
                    <td><input type="text" id="kinName" name="kinName" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                    <td><input type="text" id="kinMobileNo" name="kinMobileNo" onkeypress="return dcomethealth.validation.isNumberKey(event)" maxlength="15" class="form-control"/></td>\n\
                    <td><select id="kinRelationship" class="form-control dcomet-c-s_ddict_relationship_type-list"><option value=0>--Select--</option></select></td>\n\
                    <td><input type="text" id="kinOccupation" name="kinOccupation" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                    <td><i id="plus" class="ace-icon fa fa-plus blue" onclick="insertRow(\'kinTable\', this,1)"></i></td></tr>');
                    dcomethealth.datatypes.init($("#" + id));
                    var relationship = '';
                    $.each(ui.item.data.kinDetails, function (ifx, kin) {
                        if (parseInt(ui.item.data.id) == kin.kinPatRid) {
                            $.each(dcomethealth.dDictVal, function (i, val) {
                                if (parseInt(kin.kinRelationship) == val.id) {
                                    relationship = val.ddictValue;
                                }
                            });
                            $("#kinTbody").append('<tr><td><i id="kinDetailsDel" class="ace-icon fa fa-minus" onclick="dcomethealth.IPAdmission.deleteRow(this)"></i><input type="hidden" id="kinRid" name="kinRid" value="' + kin.id + '"/></td>\n\                         <td><input type="text" id="kinName" name="kinName" value="' + kin.kinName + '" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                        <td><input type="text" id="kinMobileNo" name="kinMobileNo" value="' + kin.kinMobileNo + '" onkeypress="return dcomethealth.validation.isNumberKey(event)" maxlength="10" class="form-control"/></td>\n\
                        <td><select id="kinRelationship" class="form-control"><option value="' + kin.kinRelationship + '">' + relationship + '</option></select></td>\n\
                        <td><input type="text" id="kinOccupation" name="kinOccupation" value="' + kin.kinOccupation + '" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                        <td><i id="plus" class="" onclick="insertRow(\'kinTable\', this,1)"></i></td></tr>');
                        }
                    });
                } else {
                    $("#kinTbody").empty();
                    $("#kinTbody").append('<tr><td><i id="kinDetailsDel" class="ace-icon fa fa-minus hidden" onclick="dcomethealth.IPAdmission.deleteRow(this)"></i><input type="hidden" id="kinRid" name="kinRid" value=""/></td>\n\
                    <td><input type="text" id="kinName" name="kinName" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                    <td><input type="text" id="kinMobileNo" name="kinMobileNo" onkeypress="return dcomethealth.validation.isNumberKey(event)" maxlength="10" class="form-control"/></td>\n\
                    <td><select id="kinRelationship" class="form-control dcomet-c-s_ddict_relationship_type-list"><option value=0>--Select--</option></select></td>\n\
                    <td><input type="text" id="kinOccupation" name="kinOccupation" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                    <td><i id="plus" class="ace-icon fa fa-plus blue" onclick="insertRow(\'kinTable\', this,1)"></i></td></tr>');
                    dcomethealth.datatypes.init($("#" + id));
                    $("select option").each(function () { //For remove duplicates
                        $(this).siblings("[value='" + this.value + "']").remove();
                    });
                }
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
                var searchParams = {"q": queryString};
                dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.patMrnNo || "") + (item.patMrnNo && item.patPhoneNo ? " - " : "") + (item.patPhoneNo || ""),
                            value: (item.patMrnNo || ""),
                            name: (item.patTitle || "") + (item.patTitle && item.patFullName ? " - " : ""), data: item
                        };
                    }), function (item, index) {
                        return index < 10;
                    }));
                });
            },
            minLength: 1
        });
    }
//    function checkExistService(serviceRID) {
//        var table = document.getElementById('dyn_table');
//        var table_length = table.rows.length;
//        var y = table_length - 2;
//        var check = false;
//        for (var i = 0; i < table_length - 2; i++) {
//            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
//            if (parseInt(serviceRid) == parseInt(serviceRID)) {
//                serviceRID = "";
//                dynTableGetNodeInRow(table.rows[y + 1], 'serviceRID').value = 0;
//                dynTableGetNodeInRow(table.rows[y + 1], 'MRP').value = "";
//                dynTableGetNodeInRow(table.rows[y + 1], 'netAmount').value = "";
//                setTimeout(function () {
//                    dynTableGetNodeInRow(table.rows[y + 1], 'serviceName').value = "";
//                }, 20);
//                check = true;
//            }
//        }
//        if (check) {
//            alert("Already existing service");
//            return false;
//        }
//    }
    function checkValidService() {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == 0) {
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = "";
            }
        }
    }
    function autocompleteProcedure() {
        $("#" + id + " input[name='procedureName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                $("#procedureRid").val(ui.item.data.id);
                $("#procedureCategory").val(ui.item.data.bsServiceProcedureCategory);
                $("#procedureServiceCategory").val(ui.item.data.bsCategory);
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1, "bsServiceSchedule": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data && data.length > 0) {
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
                    }
                });
            },
            minLength: 1
        });
    }
    function autocompleteService() {
        $("#" + id + " input[name='serviceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                autoIdSet(dynTableGetNodeInRow(this, 'serviceRID'));
                dynTableGetNodeInRow(this, 'serviceRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'bdGroupRID').value = ui.item.data.bsServiceType;
                dynTableGetNodeInRow(this, 'qtyService').value = 1;
                if ($('#visitReason option:selected').text() == 'Emergency') {
                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bEprice;
                } else {
                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bPrice;
                }

                calculation();
                payerNameChange(0);
                var sId = dynTableGetNodeInRow(this, 'serviceRID').value;
                checkExistService(sId, dynTableGetNodeInRow(this));
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
//                        if (check == true) {
//                            $('#total_pmd').val('');
//                            checkValidService()
//                            return false;
//                        }
                    }
                });
            },
            minLength: 1
        });
    }
    function doctorSearch() {
        var searchParams = {"staffSpecialityIndex": $('#VDept option:selected').val()};
        var options = '', options1 = '';
        dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
            if (!!data) {
                $.each(staffData = data, function (idx, Val) {
                    if (parseInt(Val.staffCategory) == 237) {
                        options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                    }
                    else if (parseInt(Val.staffCategory) == 431) {
                        options1 += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                    }
                });
                $("#admittedDoctorsList").html(options);
                $("#attendingDoctorsList").html(options);
                $("#primaryDoctorsList").html(options);
                $("#staffInchargeList").html(options1);
            } else {
                $("#admittedDoctorsList").empty();
                $("#attendingDoctorsList").empty();
                $("#primaryDoctorsList").empty();
                $("#primaryDoctorsList").empty();
            }
        });
    }
    function changeSpeciality() {
        $("#primaryDoctorsList").empty();
        var options = "";
        $.each(staffData, function (idx, Val) {
            if (parseInt(Val.staffCategory) == 237 && parseInt(Val.staffSpecialityIndex) == $("#speciality option:selected").val()) {
                options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
            }
        });
        $("#primaryDoctorsList").append(options);
    }
    function payerChange() {
        var payerType = $("#patRefType option:selected").val();
        $("#eligibleAmtDiv").addClass("hidden");
        payerData = {};
        if (parseInt(payerType) != 31) {
            $("#payerInfo").removeClass("hidden").addClass("");
//            $("#paymentDiv").addClass("hidden");
            $("#dueAmtDiv").removeClass("hidden");
            $("#eligibleAmtDiv").removeClass("hidden");
            $("#payerName").empty();
            $('#payerName').append("<option value='0'>- Select Payer -</option>");
            var searchObj = {"pdPayerType": payerType};
            dcomethealth.MasterResource.searchPayerMaster(searchObj, function (data) {
                $.each(payerData = data, function (pSx, payer) {
                    $('#payerName').append('<option value="' + payer.pdId + '">' + payer.pdPayerName + '</option>');
                });
            });
        } else {
            $("#dueAmtDiv").addClass("hidden");
            $("#eligibleAmtDiv").addClass("hidden");
            $("#payerInfo").removeClass("").addClass("hidden");
            $("#paymentDiv").removeClass("hidden");
        }
        payerNameChange(1);
    }

    function payerNameChange(value) {
        if ($("#payerName option:selected").val() != 0) {
            $("#eAmountDiv").removeClass("hidden");
            $("#approvalnoDiv").removeClass("hidden");
            if (value != 0) {
                $("#bhEligibleAmount").val("");
                $("#bhApprovalNo").val("");
            }
        } else {
            if (value == 0) {
                calculation();
            }
            $("#bhEligibleAmount").val("");
            $("#bhApprovalNo").val("");
            $("#eAmountDiv").addClass("hidden");
            $("#approvalnoDiv").addClass("hidden");
        }
        var payerType = $("#patRefType option:selected").val();
        var table = document.getElementById('dyn_table');
        for (var i = 0; i < table.rows.length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            if (parseInt(payerType) != 31 && !!payerData) {
                var payerId = $("#payerName option:selected").val();
                $('#total_pmd').val('');
                $.each(payerData, function (pdx, payer) {
                    if (payerId == payer.pdId) {
                        $.each(payer.payerServiceMap, function (pEx, payerSerCost) {
                            if (parseInt(serviceRid) == parseInt(payerSerCost.psmServiceRid)) {
                                dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = payerSerCost.psmDiscountPrice;
                                return false;
                            } else {
                                $.each(dcomethealth.serviceMaster, function (pfx, ServicePr) {
                                    if (parseInt(serviceRid) == parseInt(ServicePr.id)) {
                                        if ($('#visitReason option:selected').text() == 'Emergency') {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bEprice;
                                        } else {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bPrice;
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            } else {
                $.each(dcomethealth.serviceMaster, function (pfx, ServicePr) {
                    if (parseInt(serviceRid) == parseInt(ServicePr.id)) {
                        if ($('#visitReason option:selected').text() == 'Emergency') {
                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bEprice;
                        } else {
                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bPrice;
                        }
                    }
                });
            }
            if (!!serviceRid) {
                calculation();
            }
        }
    }
    function bedSelection(bedRid, bedName, bedBgmRid) {
        $("#viewBedDiv").removeClass('hidden');
        $("#helpModel").modal('hide');
        $('#sBedName').text(bedName);
        $('#sBedRid').text(bedRid);
        $('#bedBgmRid').text(bedBgmRid);
        $("#bedNameLabel").removeClass("hidden");
    }
    function genderChange() {
        if (parseInt($('#patTitle option:selected').val()) === 5 || parseInt($('#patTitle option:selected').val()) === 6) {
            $('#patGender option[value="2"]').prop('selected', true);
        } else if (parseInt($('#patTitle option:selected').val()) === 4) {
            $('#patGender option[value="1"]').prop('selected', true);
        }
    }
    function setProcedureReqObj() {
        procedureReq = {};
        var serviceRequestHList = [], drugRequestHList = [];
        procedureReq.prPrimaryProcedure = $("#procedureName").val();
        procedureReq.prProcedureRid = $("#procedureRid").val();
        procedureReq.prCategory = $("#procedureCategory").val();
        if (!!$("#patRid").val() && $("#patRid").val() != 'undefined') {
            procedureReq.prPatientRid = $("#patRid").val();
        }
        procedureReq.prPrimaryDoctorRid = $("#primaryDoctorsList").val();
        $.each(dcomethealth.serviceMaster, function (index, serviceM) {
            if (parseInt(serviceM.id) == $("#procedureRid").val()) {
                var serviceRequestH = {}, matRequestHPreObj = {}, serviceList = [], preMaterialList = [];

                $.each(serviceM.packageServiceMap, function (pSdx, packageSerM) {
                    if (parseInt(packageSerM.psServiceIsSchedulable) != 1) {
                        serviceRequestH.serReqOpVisitRid = 0;
                        if ($("#patRID").val() !== "") {
                            serviceRequestH.serReqhPatRid = $("#patRID").val();
                        }
                        serviceRequestH.serReqhPatMrn = $("#mrnSearch").val();
                        serviceRequestH.serReqhPatName = $("#patName").val();
                        serviceRequestH.serReqhOpCheck = 1.00;
                        serviceRequestH.serReqhBillHRid = 0;
                        var serviceD = {};
                        serviceD.serReqItemRID = packageSerM.psServiceRid;
                        serviceD.serReqItemName = packageSerM.psServiceName;
                        serviceD.serReqItemQty = packageSerM.psServiceQty;
                        serviceD.serReqServicePackageRid = serviceM.id;
                        serviceD.serReqItemPrice = 1;
                        serviceList.push(serviceD);
                    }
                });
                serviceRequestH.serviceRequest = serviceList;
                serviceRequestHList.push(serviceRequestH);
                $.each(serviceM.packageitemMap, function (pIdx, packageItemM) {
                    matRequestHPreObj.drugReqHPatMrn = $("#mrnSearch").val();
                    matRequestHPreObj.drugReqHOpVBisitRID = 0;
                    matRequestHPreObj.drugReqHPatName = $("#patName").val();
                    if ($("#patRID").val() !== "") {
                        matRequestHPreObj.drugReqHPatRid = $("#patRID").val();
                    }
                    matRequestHPreObj.drugReqHOpCheck = 1;
                    matRequestHPreObj.drugReqHBillHRID = 0;
                    var drugD = {};
                    drugD.drugReqDItemRID = packageItemM.piItemRid;
                    drugD.drugReqDItemName = packageItemM.piItemName;
                    drugD.drugReqDItemQty = packageItemM.piItemQty;
                    drugD.drugReqDItemBalanceQty = packageItemM.piItemQty;
                    drugD.drugReqDItemPackageRid = serviceM.id;
                    drugD.drugReqDItemPrice = 1.00;
                    drugD.drugReqDItemIssuedQty = 0;
                    preMaterialList.push(drugD);
                });
                matRequestHPreObj.drugRequestDList = preMaterialList;
                drugRequestHList.push(matRequestHPreObj);
            }
            var serMasLngth = dcomethealth.serviceMaster.length;
            var countIdx = index + 1;
            if (serMasLngth == countIdx) {
                procedureReq.serviceRequestH = serviceRequestHList;
                procedureReq.drugRequestHs = drugRequestHList;
                return procedureReq;
            }
        });
    }

    function submit(boRID, boCode, actionCode, iscollectPayment) {
        var form = $("form"), check = true;
        if (dcomethealth.IPAdmission.validateForm(form)) {
            var visit = {};
            visit.visTypeIndex = $("#visitType").val();
            visit.visSubTypeIndex = 0; // Need to change the value;
            visit.visPatType = 0; // Need to check and change it.
            visit.visApptRid = 0; // passing 0 because its a direct OP registration
            visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
            visit.visConsDocRid = $("#admittedDoctorsList option:selected").val();
            visit.visAttnDocRid = $("#admittedDoctorsList option:selected").val();
            var visitPatDetails = [], patKinDetails = [];
            var patient = {};
            if ($("#patRID").val() !== "") {
                patient.id = $("#patRID").val();
            }
            patient.patTitle = $("#patTitle").val();
            patient.patName = $("#patName").val();
            patient.patFullName = $("#patName").val() + " " + $("#LName").val();
            patient.patGenderIndex = $("#patGender").val();
            patient.patBloodGroupIndex = $("#patBloodgrp").val();
            patient.patDob = $("#patDOB").val();
            patient.patAddress = $("#patAddress").val();
            patient.patPhoneNo = $("#patMobile").val();
            patient.patVipStatus = $("#isVIP").val();
            patient.patGeneratedDob = $("#patDOB").val();
            var kinTable = document.getElementById('kinTable');
            var table_length = kinTable.rows.length;
            for (var k = 0; k < table_length - 1; k++) {
                if (!!dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRid').value && dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRid').value != "" && dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').value == "") {
                    alert("Enter Kin Name");
                    return false;
                }
                if (dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').value != "") {
                    var kin = {};
                    kin.id = !!dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRid').value ? dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRid').value : null;
                    kin.kinPatRid = parseInt($("#patRID").val());
                    kin.kinName = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').value;
                    kin.kinMobileNo = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinMobileNo').value;
                    kin.kinOccupation = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinOccupation').value;
                    kin.kinRelationship = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRelationship').value;
                    kin.kinIsActive = 1;
                    patKinDetails.push(kin);
                }
            }
            if (delKinList.length > 0) {
                $.each(delKinList, function (indx, kin) {
                    var kinD = {};
                    kinD.id = kin.id;
                    kinD.kinPatRid = parseInt($("#patRID").val());
                    kinD.kinName = kin.kinName;
                    kinD.kinMobileNo = kin.kinMobileNo;
                    kinD.kinOccupation = kin.kinOccupation;
                    kinD.kinRelationship = kin.kinRelationship;
                    kinD.kinIsActive = kin.kinIsActive;
                    patKinDetails.push(kinD);
                });
            }

            patient.kinDetails = patKinDetails;
            visitPatDetails.push(patient);
            visit.patient = visitPatDetails;
            var procedureReqList = [], catergoryText = "";
            if ($("#procedureCheck").is(':checked')) {
                catergoryText = "";
                $.each(dcomethealth.dDictVal, function (i, val) {
                    if (parseInt($("#procedureServiceCategory").val()) == val.id) {
                        catergoryText = val.ddictValue;
                    }
                });
                if (catergoryText == "Package") {
                    setProcedureReqObj();
                } else {
                    procedureReq = {}
                    procedureReq.prPrimaryProcedure = $("#procedureName").val();
                    procedureReq.prProcedureRid = $("#procedureRid").val();
                    procedureReq.prCategory = $("#procedureCategory").val();
                    if (!!$("#patRid").val() && $("#patRid").val() != 'undefined') {
                        procedureReq.prPatientRid = $("#patRid").val();
                    }
                    procedureReq.prPrimaryDoctorRid = $("#primaryDoctorsList").val();
                }
            }
            if (!!$("#serviceNetAmount").val()) {
                var visitSerBillDetails = [];
                var billDList = [];
                var billH = {};
                var serviceNetAmount = document.getElementById('serviceNetAmount').value;
                var total_pmd = document.getElementById('total_pmd').value;
                if (total_pmd == "" || isNaN(total_pmd)) {
                    total_pmd = 0;
                }
                if (serviceNetAmount == "") {
                    alert("Bill amount is empty");
                    return false;
                }
                billH.bhType = 2; // IP bill type : 1;
                billH.bhIsDraft = 1;
                billH.bhGrossAmount = $("#serviceGrossAmount").val();
                billH.bhTotalDiscountAmount = $("#serviceDiscountAmount").val();
                billH.bhPatientName = $("#patName").val() + " " + $("#LName").val();
                billH.bhPatientNo = $("#patMobile").val(); // Please change the name
                billH.bhNetAmount = $("#serviceNetAmount").val();
//                billH.bhDiscount = $("#serviceDiscountAmount").val(); // Its already taken as total discount amount
                billH.bhPaidAmount = parseFloat(total_pmd);
                billH.bhDocRefRID = $("#admittedDoctorsList option:selected").val();
                billH.bhUnitRID = dcomethealth.selectedunit; // user selected unit;
                billH.bhPayerType = $("#patRefType").val();
                billH.bhEligibleAmount = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
                billH.bhApprovalNumber = !!$("#bhApprovalNo").val() ? $("#bhApprovalNo").val() : 0;
                if ($("#patRefType").val() != 31) {
                    billH.bhPayerRID = $("#payerName").val();
                }
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var billD = {};
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == 0) {
                        alert('Enter correct service name');
                        dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = "";
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').focus();
                        }, 1);
                        return false;
                    }
                    billD.bdItemType = "Service"; //Change to serv related
                    billD.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
                    billD.bdGroupRID = dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value;
                    billD.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                    billD.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
                    billD.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                    billD.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value * dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value != "") {
                        billD.bdDiscountAmount = dynTableGetNodeInRow(table.rows[i + 1], 'serviceDisc').value;
                    } else {
                        billD.bdDiscountAmount = 0;
                    }
                    billD.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'netAmount').value;
                    billDList.push(billD);
                }
                billH.billD = billDList;
                visitSerBillDetails.push(billH);
                visit.billH = visitSerBillDetails;
            }
            //-----Advance Save----
            if (!$("#total_pmd").val() && $("#total_pmd").val() == 0) {
                return false;
            }
            var advance = {};
            if ($("#advanceRID").val() !== "") {
                advance.id = $("#advanceRID").val();
            }
            advance.adType = 0;
            advance.adPayerRID = $("#patRID").val();
            advance.adPayerNo = $("#patMobile").val(); // need to verify mrn or patient no
            advance.adPayerName = $("#patName").val() + " " + $("#LName").val();
            advance.adAmount = parseFloat($("#total_pmd").val());
            advance.adRecEntityRID = dcomethealth.loginuser.entityRid;
            advance.adAdjustedAmount = 0.00;
            advance.adRefundedAmount = 0.00;
            advance.adBalanceAmount = $("#total_pmd").val();
            advance.adPaidAmount = 0.00;
            var advanceList = [];
            var table2 = document.getElementById('pmd_table');
            var table_length2 = table2.rows.length;
            for (var j = 0; j < table_length2; j++) {
                var pmd = {};
                pmd.pmdTransType = 1;
                pmd.pmdPaymentMode = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_ddict').value;
                pmd.pmdCardType = dynTableGetNodeInRow(table2.rows[j], 'cardtype').value;
                pmd.pmdDocNo = dynTableGetNodeInRow(table2.rows[j], 'cheqno').value;
                pmd.pmdDocDate = dynTableGetNodeInRow(table2.rows[j], 'cheqdate').value;
                pmd.pmdDocExpDate = dynTableGetNodeInRow(table2.rows[j], 'expdate').value;
                pmd.pmdDocApprovalNo = dynTableGetNodeInRow(table2.rows[j], 'approvalno').value;
                pmd.pmdBankName = dynTableGetNodeInRow(table2.rows[j], 'bankname').value;
                pmd.pmdBankDetails = dynTableGetNodeInRow(table2.rows[j], 'branchname').value;
                if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value !== "") {
                    pmd.pmdAmount = parseFloat(dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value);
                } else {
                    pmd.pmdAmount = 0.00;
                }
                pmd.pmdCurrencyRID = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_currency').value;
                advanceList.push(pmd);
            }
            if ($("#procedureCheck").is(':checked')) {
                procedureReq.prActionCode = "REQUEST";
                procedureReqList.push(procedureReq);
                visit.procedureRequestHs = procedureReqList;
            }
            var patRID = 0;
            var search = boRID + "/" + boCode + "/" + actionCode;
            dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                patRID = data;
                dcomethealth.PatientResource.searchPatient({"id": data}, function (data) {
                    dcomethealth.PatientResource.searchVisit({"visPatRid": data[0].id, "visDate": moment().format("DD-MM-YYYY")}, function (visit) {
                        if (!!visit) {
                            saveAdmission(visit[0].id, patRID);
                            var imageContext = {};
                            $("#resourceId").val() !== "" ? imageContext.id = $("#resourceId").val() : null;
                            imageContext.patImgPatRid = data[0].id;
                            dcomethealth.PatientResource.saveImageInfo(imageContext).done(function (data, textStatus, jqXHR) {
                            });
                            if (!!$("#patRID").val()) {
                                advance.adPayerRID = $("#patRID").val();
                            } else {
                                advance.adPayerRID = patRID;
                            }
                            advance.paymentModeDetails = advanceList;
                            var arg = 0 + "/" + "ADVANCE" + "/" + "CREATE_ADVANCE";
                            dcomethealth.AdvanceResource.saveAdvance(advance, arg).done(function (data, textStatus, jqXHR) {
                                $("#advanceRID").val(data);
                                alert("Saved");
                            }).fail(function (jqXHR, textStatus, errorThrown) {
//                                alert("Failed");
                            });
                        }
                    });
                });
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function saveAdmission(visitRid, patRID) {
        var occupy = {};
        if ($("#patRID").val() == "") {
            occupy.bocPatRid = patRID;
        } else {
            occupy.bocPatRid = $("#patRID").val();
        }
        occupy.bocVisitRid = visitRid;
        occupy.bocBedRid = $("#sBedRid").text();
        if (!!$("#admittedDoctorsList").val()) {
            occupy.bocPrimaryDoctor = parseInt($("#admittedDoctorsList").val());
        }
        dcomethealth.BedManagementResource.saveBedOccupancy(occupy).done(function (data, textStatus, jqXHR) {
            occpancyDetails();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function occpancyDetails() {
        var details = {};
        details.bodBedRid = $("#sBedRid").text();
        details.bodBedTypeRid = "2";
        details.bodWardUnitRid = dcomethealth.selectedunit;
        details.bodUserRid = dcomethealth.loginuser.id;
        dcomethealth.BedManagementResource.saveBedOccupancyDetails(details).done(function (data, textStatus, jqXHR) {
            if (!!details.bodBedRid) {
                saveBedMaster();
            }
            //            alert("Saved");
            dcomethealth.util.loadpage('IPAdmission');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveBedMaster() {
        var bed = {};
        bed.bedRid = $("#sBedRid").text();
        bed.bedBgmRid = $("#bedBgmRid").text();
        bed.bedUnitRid = $('#unitName').val();
        bed.bedName = $("#sBedName").text();
        bed.bedIsActive = 1;
        var search = parseInt($("#sBedRid").text()) + "/" + "BED_DETAILS" + "/" + "OCCUPY";
        dcomethealth.BedManagementResource.saveBedMas(bed, search).done(function (data, textStatus, jqXHR) {
            dcomethealth.util.base_init();
            dcomethealth.util.loadNotification();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        doctorSearch: doctorSearch,
        bedSelection: bedSelection,
        autocomplete: autocomplete,
        autoIdSet: autoIdSet,
        deleteRow: deleteRow,
        unitWiseSerPoint: unitWiseSerPoint,
        autocompleteService: autocompleteService,
        autocompleteProcedure: autocompleteProcedure,
        viewGroupWiseBed: viewGroupWiseBed,
        changeSpeciality: changeSpeciality,
        addAdvance: addAdvance,
        genderChange: genderChange,
        payerChange: payerChange,
        viewPatient: viewPatient,
        getPatient: getPatient,
        getStates: getStates,
        checkDate: checkDate,
        payerNameChange: payerNameChange,
        saveAdmission: saveAdmission,
        saveBedMaster: saveBedMaster,
        occpancyDetails: occpancyDetails,
        checkValidService: checkValidService,
        setProcedureReqObj: setProcedureReqObj,
        validateForm: validateForm,
        refreshData: refreshData
    };
}());
dcomethealth.IPAdmission.init();