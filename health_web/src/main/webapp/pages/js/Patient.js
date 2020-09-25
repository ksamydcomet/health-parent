var dcomethealth = dcomethealth || {}, currencyExchange = {};
var payerData = {};
dcomethealth.Patient = (function () {
    var id = "Patient", docstaff = {}, IdCard = {}, serviceClearVar = 0;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().startOf('days'), end = moment().endOf('days');
            $('#patientRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            viewPatient();
            $('#patientRange').daterangepicker({
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
                $('#patientRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                viewPatient();
            });
            dcomethealth.Pictures.setDragNDropListeners(0);
            if (dcomethealth.loginuser.entityRid == 4) {
                $("select#patRefType option").each(function (ix, selectOption) {
                    if (($(selectOption).text()).includes("Corporate")) {
                        $(selectOption).remove();
                    }
                });
            }
//            if (dcomethealth.loginuser.entityRid == 5 
//            
//            ) {
//                $("#VDept").find("option").each(function (inx, option) {
//                    if ($(this).text() == 'Ophthalmology') {
//                        $(this).remove();
//                    }
//                });
//            }
            if (dcomethealth.loginuser.entityRid == 2) {
                $("#VDept").find("option").each(function (inx, option) {
                    if ($(this).text() == 'Radiology' || $(this).text() == 'Ophthalmology') {
                        $(this).remove();
                    }
                });
            }
//            dcomethealth.DataDictionaryResource.getSysParam({"paramCode": "POSTALCODE_CHANGE"}, function (data) {
//                if (!!data) {
//            if (dcomethealth.loginuser.entityRid == 4) {
//                $("#countryDiv,#bgDiv2,#postal,#county,#town").removeClass("hidden");
//                $("#countryDiv2,#bgDiv1").addClass("hidden");
//            } else {
//                $("#zipcode").removeClass("hidden");
//                $("#state").removeClass("hidden");
//                $("#cty").removeClass("hidden");
//            }
//                } else {
//                }
//            });
            if (dcomethealth.searchPatByAppointments !== 0 && dcomethealth.searchPatByAppointments != -1) {
                $("#RegistrationHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#addNew").removeClass("hidden").addClass("panel panel-primary");
                searchPatByAppointment(dcomethealth.searchPatByAppointments);
            } else if (dcomethealth.searchPatByAppointments == 0 && dcomethealth.searchPatByAppointments != -1) {
            } else if (dcomethealth.searchPatByAppointments == -1 && dcomethealth.searchPatByAppointments !== 0) {
                $("#RegistrationHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#addNew").removeClass("hidden").addClass("panel panel-primary");
            }
            if (dcomethealth.searchApptByAppointments !== 0 && dcomethealth.searchPatByAppointments !== -1) {
                $("#RegistrationHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#addNew").removeClass("hidden").addClass("panel panel-primary");
                searchApptByAppointment(dcomethealth.searchApptByAppointments);
            }
            if (dcomethealth.security.isPrivillage(13) === true) {
                document.getElementById('collectPayment').value = 1;
                $("#paymentMode").removeClass("hidden").addClass("panel panel-primary");
            } else {
                document.getElementById('collectPayment').value = 0;
                $("#paymentMode").removeClass("panel panel-primary").addClass("hidden");
            }
            autocomplete();
            autocompleteService();
            dcomethealth.DataDictionaryResource.getCurrencyExchange({}, function (data) {
                if (!!data) {
                    currencyExchange = data;
                }
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
            $.each(dcomethealth.s_countries, function (pIdx, s_countries) {
                $(s_countries).each(function () {
                    if (s_countries.couCode === dcomethealth.userEntityCountryCode) {
                        $('#prefixMobile').val(s_countries.couPhoneCode);
                    }
                });
            });
            $('#countryCode').val(dcomethealth.userEntityCountryCode);
            var obj = $('#patCountry').find('option[id="' + dcomethealth.userEntityCountryCode + '"]');
            $('#cntryName').val(obj.val());
            $("#visitReason").change(function () {
                $.each(dcomethealth.sDdict, function (pIdx, s_ddict) {
                    if (s_ddict.dditCode === "VISIT_TYPE") {
                        $.each(s_ddict.ddict, function (i) {
                            if ($("#visitReason option:selected").text() == "Admission") {
                                if (s_ddict.ddict[i].ddictValue === "IP") {
                                    $("#visitType").val(s_ddict.ddict[i].id);
                                }
                            } else {
                                if (s_ddict.ddict[i].ddictValue === "OP") {
                                    $("#visitType").val(s_ddict.ddict[i].id);
                                }
                            }
                        });
                    }
                });
            });
            $("#VDept").change(function () {
                var searchParams = {"staffSpecialityIndex": $('#VDept option:selected').val()};
                var options = '';
                dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
                    if (!!data) {
                        $.each(data, function (idx, Val) {
                            options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                        });
                        $("#VDName").html(options);
                        freeConsultationCheck();
                    } else {
                        $("#VDName").empty();
                    }
                });
            });
            $("#grprooms").change(function () {
                var roomTypeArray = [], countRoom = 0
                $("#roomType").empty();
                $("#roomType").append('<option value="0">--Select--</option>');
                var searchParams = {"btmActiveYesno": 1, "btmGroupRid": $('#grprooms option:selected').val()};
                dcomethealth.BedManagementResource.searchBedmaster(searchParams, function (data) {
                    var count = 1;
                    if (!!data) {
                        $.each(data, function (idx, bedType) {
                            var optRoomType = null;
                            optRoomType = bedType.btmRoomType;
                            if ($.inArray(optRoomType, roomTypeArray) === -1) {
                                roomTypeArray.push(optRoomType);
                                countRoom += count;
                            }
                        });
                        for (var i = 0; i < roomTypeArray.length; i++) {
                            var rmTypeValue = roomTypeArray[i];
                            $.each(dcomethealth.dDictVal, function (i, val) {
                                if (parseInt(rmTypeValue) === val.id) {
                                    $("#roomType").append('<option value="' + rmTypeValue + '">' + val.ddictValue + '</option>');
                                }
                            });
                        }
                        $('#totalRoom').text(countRoom);
                    } else {
                        $('#totalRoom').text(0);
                    }

                });
            });
            $("#roomType").on('change', function () {
                $("#bedDetails").empty();
                var searchParams = {"btmActiveYesno": 1, "btmGroupRid": $('#grprooms option:selected').val(), "btmRoomType": $('#roomType option:selected').val()};
                dcomethealth.BedManagementResource.searchBedmaster(searchParams, function (data) {
                    if (!!data) {
                        $.each(data, function (id, bedType) {
                            if (bedType.btmStatus != 0) {
                                $("#bedDetails").append('<a href="#grprooms" class="aTag" onclick=""><div class= "col-md-3 col-sm-3 col-xs-3 " style="cursor:pointer"><img src="/health_web/css/images/bedImages/bedOccupy.svg"   width="60" height="55" alt="bed"></img><span class="" >' + bedType.btmName + '</span><input value="' + bedType.btmRid + '" id="btmRid" type="hidden" /><input value="' + bedType.btmPrice + '" id="btmPrice" type="hidden" /></div></a>');
                            } else {
                                $("#bedDetails").append('<a href="#grprooms" class="aTag" onclick="dcomethealth.Patient.bedSelection(' + bedType.btmRid + ',\'' + bedType.btmName + '\',' + bedType.btmPrice + ')"><div class= "col-md-3 col-sm-3 col-xs-3"  style="cursor:pointer"><img src="/health_web/css/images/bedImages/bedOccupy2.png"   width="65" height="55" alt="bed"></img><span id="bedName" class="" >' + bedType.btmName + '</span><input value="' + bedType.btmRid + '" id="btmRid" type="hidden" /><input value="' + bedType.btmPrice + '" id="btmPrice" type="hidden" /></div></a>');
                            }
                        });
                    }
                });
            });
            $("#grprooms").change(function () {
                var searchCategory = {"btmActiveYesno": 1, "btmGroupRid": $('#grprooms option:selected').val()}, countval = 0;
                $("#totalBed").empty();
                dcomethealth.BedManagementResource.searchBedmaster(searchCategory, function (data) {
                    if (!!data) {
                        var count = 1;
                        $.each(data, function (idx, Val) {
                            if (!!Val.btmRid) {
                                countval = count++;
                            }
                        });
                        $("#totalBed").text(countval);
                    } else {
                        $("#totalBed").text(0);
                    }
                });
            });
            var searchWard = {};
            dcomethealth.MasterResource.searchStaff(searchWard, function (data) {
                $("#bocPrimaryDoctor").empty();
                if (!!data) {
                    docstaff = data;
                }
                $.each(data, function (idx, staff) {
                    $("#dName").val(data.staffName);
                    if (staff.id != 1) {
                        $("#bocPrimaryDoctor").append('<option value="' + staff.id + '">' + staff.staffName + '</option>');
                    }
                });
            });
            $("#edit_user_form").validate({
                // Specify the validation rules                
                rules: {
                    patName: "required",
                    patMobile: "required",
                    patDOB: "required",
                    VDept: {
                        selectcheck: true
                    },
                    visitReason: {
                        selectchk: true
                    },
                    payerName: {
                        selectchk1: true
                    }
                },
                messages: {
                    patName: "Enter Patient Name",
                    patMobile: "Enter Mobile Number",
                    patDOB: "Enter Date of Birth",
                },
                submitHandler: function (form) {
                    if ($('#visitReason option:selected').text() == 'Admission') {
                        var kinTable = document.getElementById('kinTable');
                        for (var k = 0; k < kinTable.rows.length - 1; k++) {
                            var kinName = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').value;
                            if (kinName == '') {
                                alert("Need Kin Details");
                                return false;
                            } else {
                                submit(0, "VISIT", "BUILTIN_ACTION", $("#collectPayment").val());
                            }
                        }
                    } else {
                        if (parseInt($("#update").val()) == 1) {
                            if ($("#patDOB").val() == "" || $("#patAge").val() == "") {
//                                alert("Enter Date of Birth");
                                return false;
                            } else {
                                patientSave();
                            }
                        } else {
                            if ($("#patRefType").val() != 31) {
                                if (parseInt($("#payerName").val()) == 0) {
                                    alert("Select Payer");
                                    $(".btn-primary").attr("disabled", false);
                                    return false;
                                }
                                if (parseInt($("#payerName option:selected").val()) > 0) {
                                    if (!$("#bhEligibleAmount").val()) {
                                        alert("Enter Eligible Amount");
                                        $(".btn-primary").attr("disabled", false);
                                        return  false;
                                    }
                                    if (!$("#bhApprovalNo").val()) {
                                        alert("Enter Approval Number");
                                        $(".btn-primary").attr("disabled", false);
                                        return  false;
                                    }
                                }
                            }
                            submit(0, "VISIT", "BUILTIN_ACTION", $("#collectPayment").val());
                        }
                    }
                }
            });
            jQuery.validator.addMethod('selectcheck', function (value) {
                return (value != '0');
            }, "Select Speciality");
            jQuery.validator.addMethod('selectchk1', function (value) {
                return (value != '0');
            }, "Select Payer");
            jQuery.validator.addMethod('selectchk', function (value) {
                return (value != '0');
            }, "Select Reason");
        });
    }
    function bedSelection(btmRid, btmName, btmCharge) {
        $("#helpModel").modal('hide');
        $('#viewBedDiv').show();
        $('#sBedName').text(btmName);
        $('#sBedCharge').text(btmCharge);
        $('#sBedRid').text(btmRid);
    }
    function getCity() {
        $("#Cities").empty();
        var couCode = $('#patCountry option[value="' + $('#cntryName').val() + '"]').text();
        var searchObj = {"couCode": couCode.substring(0, 2)};
        dcomethealth.DataDictionaryResource.searchByZipcode(searchObj, function (data) {
            if (!!data && data.length > 0) {
                $.each(data, function (pIdx, s_cou_zipcode) {
                    $(s_cou_zipcode).each(function () {
//                    if (dcomethealth.userEntityCountryCode == s_cou_zipcode.couCode) {
                        if (s_cou_zipcode.division1 == $("#stateGeo").val()) {
                            $("#Cities").append('<option value="' + s_cou_zipcode.place + '">' + s_cou_zipcode.place + '</option>');
                        }
//                    }
                    });
                });
            }
        });
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
        if (!isExist) {
            alert("Country is not available");
            $('#cntryName').val("");
            $('#prefixMobile').val("");
            $('#cntryName').focus();
            return false;
        }
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
            var searchObj = {"pdPayerType": payerType, "pdIsActive": 1};
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
    function checkExistService(serviceRID) {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        var y = table_length - 2;
        var check = false;
        for (var i = 0; i < table_length - 2; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            if (parseInt(serviceRid) == parseInt(serviceRID)) {
                serviceRID = "";
                dynTableGetNodeInRow(table.rows[y + 1], 'serviceRID').value = 0;
                dynTableGetNodeInRow(table.rows[y + 1], 'MRP').value = "";
                dynTableGetNodeInRow(table.rows[y + 1], 'netAmount').value = "";
                setTimeout(function () {
                    dynTableGetNodeInRow(table.rows[y + 1], 'serviceName').value = "";
                }, 20);
                check = true;
            }
        }
        if (check) {
            alert("Already Existing Service");
            $('#total_pmd').val('');
            return false;
        }
    }
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
    function autoIdSet(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'serviceRID').value = "";
            dynTableGetNodeInRow(elem, 'serviceName').value = "";
            dynTableGetNodeInRow(elem, 'MRP').value = "";
            dynTableGetNodeInRow(elem, 'netAmount').value = "";
        }
    }
    function autocompleteService() {
        $("#" + id + " input[name='serviceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                autoIdSet(dynTableGetNodeInRow(this, 'serviceRID'));
                dynTableGetNodeInRow(this, 'serviceRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'bdGroupRID').value = ui.item.data.bsServiceType;
                if ($('#visitReason option:selected').text() == 'Emergency') {
                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bEprice;
                } else {
                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bPrice;
                }
                $('#total_pmd').val('');
                payerNameChange(0);
                doctorChange();
                checkExistService(dynTableGetNodeInRow(this, 'serviceRID').value);
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
                            checkValidService()
                            return false;
                        }
                    }
                });
            },
            minLength: 1
        });
    }
    function searchPatByAppointment(patRid) {
        if (patRid != 0) {
            dcomethealth.PatientResource.searchPatient({"id": patRid}, function (data) {
                $.each(data, function (i, pat) {
                    $('#lvDateLi').removeClass('hidden');
                    var lastVisitDate = "New Entry";
                    dcomethealth.PatientResource.searchVisit({"visPatRid": patRid}, function (data2) {
                        if (data2 != undefined) {
                            $.each(data2, function (i, visit) {
                                lastVisitDate = visit.createdDateTime;
                                if (moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') === moment().format('DD-MM-YYYY')) {
                                    dcomethealth.util.loadpage('Patient');
                                    dcomethealth.util.base_init();
                                    dcomethealth.searchPatByAppointments = -1;
                                    dcomethealth.Patient.getStates();
                                    $("#RegistrationHeader").removeClass("panel panel-primary").addClass("hidden");
                                    $("#addNew").removeClass("hidden").addClass("panel panel-primary");
                                    alert("Patient is Active Today");
                                }
                            });
                            $('#lastVDate').text(moment(lastVisitDate, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY'));
                        } else {
                            $('#lastVDate').text(lastVisitDate);
                        }
                        $('#patTitle option[value="' + pat.patTitle + '"]').prop('selected', true);
                        $('#patName').val(pat.patFullName);
                        $('#mrnSearch').val(pat.patMrnNo);
                        $('#patDOB').val(pat.patDob);
                        var c = $('#patDOB').val().split('-');
                        $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                        $('#patGender option[value="' + pat.patGenderIndex + '"]').prop('selected', true);
                        $('#patMaritalStatus option[value="' + pat.patMaritalStatus + '"]').prop('selected', true);
                        $('#patMobile').val(pat.patPhoneNo);
                        $('#patEmail').val(pat.patEmailId);
                        $('#pinCode').val(pat.patPincode);
                        $('#stateGeo').val(pat.patStateIndex);
                        $('#patCity').val(pat.patCityIndex);
                        $('#cntryName').val(pat.patCountryIndex);
                        $('#patAddress').val(pat.patAddress);
                        $('#patBldGrp').val(pat.patBloodGroupIndex);
                        $('#isVIP').val(pat.patVipStatus);
                        if (parseInt($('#isVIP').val()) === 1) {
                            document.getElementById("openVip").checked = true;
                        }
                    });
                });
                document.getElementById("patRID").value = parseInt(patRid);
            });
        } else {
            alert("New Patient");
        }
    }
    function searchApptByAppointment(apptRid) {
        var options = "";
        dcomethealth.AppointmentsResource.searchAppointmentByResource({"armApptRid": apptRid}, function (data) {
            $.each(data, function (id, arm) {
                dcomethealth.MasterResource.searchStaff({"staffResourceRID": arm.armResourceRid}, function (data) {
                    $.each(data, function (idx, Val) {
                        $('#VDept option[value="' + Val.staffSpecialityIndex + '"]').prop('selected', true);
                        options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                    });
                    $("#VDName").html(options);
                });
            });
        });
        $('#VDept').prop('disabled', true);
        $('#VDName').prop('disabled', true);
    }
    function genderChange() {
        if (parseInt($('#patTitle option:selected').val()) === 5 || parseInt($('#patTitle option:selected').val()) === 6) {
            $('#patGender option[value="2"]').prop('selected', true);
        } else if (parseInt($('#patTitle option:selected').val()) === 4) {
            $('#patGender option[value="1"]').prop('selected', true);
        }
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
                                alert("Patient is Active Today");
                                dcomethealth.util.base_init();
                                dcomethealth.util.loadpage('Patient');
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
                    $('#patTitle option[value="' + ui.item.data.patTitle + '"]').prop('selected', true);
                    $('#patName').val(ui.item.data.patName);
                    $('#fstName').val(ui.item.data.patName);
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
                    var relationship = '';
                    $.each(ui.item.data.kinDetails, function (ifx, kin) {
                        if (parseInt(ui.item.data.id) == kin.kinPatRid) {
                            $.each(dcomethealth.dDictVal, function (i, val) {
                                if (parseInt(kin.kinRelationship) == val.id) {
                                    relationship = val.ddictValue;
                                }
                            });
                            $("#kinTbody").append('<tr><td><i id="kinDetailsDel" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this)"></i><input type="hidden" id="kinRid" name="kinRid" value="' + kin.id + '"/></td>\n\
                        <td><input type="text" id="kinName" name="kinName" value="' + kin.kinName + '" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                        <td><input type="text" id="kinMobileNo" name="kinMobileNo" value="' + kin.kinMobileNo + '" onkeypress="return dcomethealth.validation.isNumberKey(event)" maxlength="10" class="form-control"/></td>\n\
                        <td><select id="kinRelationship" class="form-control"><option value="' + kin.kinRelationship + '">' + relationship + '</option></select></td>\n\
                        <td><input type="text" id="kinOccupation" name="kinOccupation" value="' + kin.kinOccupation + '" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                        <td><i class="ace-icon fa fa-plus blue" onclick="insertRow(\'kinTable\', this)"></i></td></tr>');
                        }
                    });
                } else {
                    $("#kinTbody").empty();
                    $("#kinTbody").append('<tr><td><i id="kinDetailsDel" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this)"></i><input type="hidden" id="kinRid" name="kinRid" value=""/></td>\n\
                    <td><input type="text" id="kinName" name="kinName" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                    <td><input type="text" id="kinMobileNo" name="kinMobileNo" onkeypress="return dcomethealth.validation.isNumberKey(event)" maxlength="10" class="form-control"/></td>\n\
                    <td><select id="kinRelationship" class="form-control dcomet-c-s_ddict_relationship_type-list"></select></td>\n\
                    <td><input type="text" id="kinOccupation" name="kinOccupation" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" class="form-control"/></td>\n\
                    <td><i class="ace-icon fa fa-plus blue" onclick="insertRow(\'kinTable\', this)"></i></td></tr>');
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
                            name: (item.patTitle || "") + (item.patTitle && item.patFullName ? " - " : ""),
                            data: item
                        };
                    }), function (item, index) {
                        return index < 10;
                    }));
                });
            },
            minLength: 1
        });
    }

    function freeConsultationCheck() {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        if (!!$("#VDName").val() && $("#VDName").val() != 0 && !!$("#patRid").val() && $("#patRid").val() != 0) {
            var searchObj = {"fcDoctorRid": $("#VDName").val(), "fcPatientRid": $("#patRid").val()};
            dcomethealth.ClinicalResource.getFreeConsultationVo(searchObj, function (data) {
                if (!!data && Object.keys(data).length > 0) {
                    $("#freeConPatDocMappingRid").val(data.fcPatDocMapRid);
                    $("#fcCurrentState").val(data.fcCurrentState);
                    for (var i = 0; i < table_length - 1; i++) {
                        dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = data.fcServiceName;
                        dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value = data.fcServiceRid;
                        dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value = data.fcServiceType;
                        if ($('#visitReason option:selected').text() == 'Emergency') {
                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = data.fcServiceEPrice;
                        } else {
                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = data.fcServicePrice;
                        }
                    }
                    calculation();
                } else {
                    var searchObj = {"fcPatientRid": $("#patRid").val(), "fcDoctorRid": $("#VDName").val()};
                    dcomethealth.ClinicalResource.searchFcPatDocMap(searchObj, function (data) {
                        if (!!data) {
                            $.each(data, function (pdx, pdmap) {
                                $("#freeConPatDocMappingRid").val(pdmap.id);
                            });
                        }
                        for (var i = 0; i < table_length - 1; i++) {
                            dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = "";
                            dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value = "";
                            dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value = "";
                            if ($('#visitReason option:selected').text() == 'Emergency') {
                                dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = 0;
                            } else {
                                dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = 0;
                            }
                        }
                        calculation();
                    });
                }
            });
        } else {
            for (var i = 0; i < table_length - 1; i++) {
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'bdGroupRID').value = "";
                if ($('#visitReason option:selected').text() == 'Emergency') {
                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = 0;
                } else {
                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = 0;
                }
            }
            calculation();
        }
    }
    function searchPostalCode() {
        var pincode = $('#pinCode').val();
        var searchObj = {"zipCode": pincode, "couCode": dcomethealth.userEntityCountryCode};
        dcomethealth.DataDictionaryResource.searchByZipcode(searchObj, function (data) {
            $("#Cities").empty();
            $.each(data, function (pIdx, data) {
                $("#Cities").append('<option value="' + data.place + '">' + data.place + '</option>');
                $("#stateGeo").val(data.division1);
            });
        });
    }
    function getPatient(patRid) {
        $("#RegistrationHeader").removeClass("panel panel-primary").addClass("hidden");
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
    function doctorChange() {
        var check = false;
        if ($("#patRefType option:selected").val() == 31) {
            var searchObj = {"rsmDocRid": $("#VDName option:selected").val()};
            dcomethealth.MasterResource.searchResourceServiceMap(searchObj, function (data) {
                if (!!data) {
                    $.each(data, function (ifx, servicemap) {
                        var table = document.getElementById('dyn_table');
                        for (var i = 0; i < table.rows.length - 1; i++) {
                            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
                            if (parseInt(serviceRid) == parseInt(servicemap.rsmServiceRid) && servicemap.rsmDocRid == parseInt($("#VDName option:selected").val())) {
                                if ($("#visitReason option:selected").text() == 'Emergency') {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = servicemap.rsmEmergencyPrice;
                                    check = true;
                                } else {
                                    dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = servicemap.rsmNormalPrice;
                                    check = true;
                                }
                            } else {
                                $.each(dcomethealth.serviceMaster, function (pfx, ServicePr) {
                                    if (parseInt(serviceRid) == parseInt(ServicePr.id)) {
                                        if ($("#visitReason option:selected").text() == 'Emergency') {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bEprice;
                                            check = true;
                                        } else {
                                            dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value = ServicePr.bPrice;
                                            check = true;
                                        }

                                    }
                                });
                            }

                        }
                    });
                    if (check == true) {
                        calculation();
                    }
                    calculation();
                } else {
//                    payerChange();
                    payerNameChange(0);
                }
            });
        } else {
            calculation();
            payerNameChange(0);
        }
    }
    function viewPatient() {
        var date = $('#patientRangeSpan').html().split('-');
        var searchObj = {"patFromDate": moment(date[0]), "patToDate": moment(date[1]).add(1, 'days')};
        dcomethealth.PatientResource.searchPatient(searchObj, function (data) {
            $('#patient_Table').empty();
            if (data != undefined) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $.each(dcomethealth.patientData = data, function (pIdx, Pat) { //onclick="dcomethealth.Patient.getPatient(' + Pat.id + ')"
                    var Print = '<a class="btn btn-primary hidden-xs" onclick="dcomethealth.Patient.patientIdCard(' + Pat.id + ')"><i class="fa fa-id-card-o"></i></a>';
                    var Edit = '<a class="btn btn-primary hidden-xs" onclick="dcomethealth.Patient.getPatient(' + Pat.id + ')"><i class="fa fa-edit"></i></a>';
                    $('#patient_Table').append('<tr><td>' + Pat.patMrnNo + '</td><td>' + Pat.patFullName + '</td>\n\
                    <td>' + Pat.patPhoneNo + '</td><td align="right" width="20%">' + Print + ' ' + Edit + '</td></tr>');
                });
                $('#tab_patient_details').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $('#tab_patient_details').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function submit(boRID, boCode, actionCode, iscollectPayment) {
        var form = $("form"), check = true;
        if (dcomethealth.Patient.validateForm(form)) {
            var visit = {};
            visit.visTypeIndex = $("#visitType").val();
            visit.visSubTypeIndex = 0; // Need to change the value;
            visit.visPatType = 0; // Need to check and change it.
            visit.visApptRid = 0; // passing 0 because its a direct OP registration
            visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
            visit.visReasonIndex = $("#visitReason").val();
            visit.visSpecialityIndex = $("#VDept option:selected").val();
//            visit.visRemarks = !!$("#visitRemarks").val() ? $("#visitRemarks").val() : "";
            visit.visConsDocRid = !!$("#VDName").val() ? $("#VDName option:selected").val() : 0;
            visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.
            visit.visRefTypeIndex = $("#refType").val();
            if ($("#RName").val() == "") {
                visit.visRefName = null;
            } else {
            visit.visRefName = $("#RName").val();
            }
            var fcPatDocMap = {};
            if (!!$("#freeConPatDocMappingRid").val()) {
                fcPatDocMap.id = $("#freeConPatDocMappingRid").val();
            }
            if (!!$("#patRid").val()) {
                fcPatDocMap.fcPatientRid = $("#patRid").val();
            }
            fcPatDocMap.fcDoctorRid = !!$("#VDName").val() ? $("#VDName option:selected").val() : 0;
            if (!!$("#fcCurrentState").val()) {
                fcPatDocMap.fcCurrentState = $("#fcCurrentState").val();
            } else {
                fcPatDocMap.fcCurrentState = 0;
            }
            var visitPatDetails = [], patKinDetails = [];
            var patient = {};
            if ($("#patRID").val() !== "") {
                patient.id = $("#patRID").val();
            }
            patient.patTitle = $("#patTitle").val();
            patient.patName = $("#patName").val();
            patient.patFullName = $("#patName").val() + " " + $("#LName").val();
            patient.patFamilyName = $("#LName").val();
            patient.patGenderIndex = $("#patGender").val();
            patient.patBloodGroupIndex = $("#patBldGrp").val();
            patient.patDob = $("#patDOB").val();
            patient.patMaritalStatus = $("#patMaritalStatus").val();
            if ($("#patAddress").val() == "") {
                patient.patAddress = null;
            } else {
            patient.patAddress = $("#patAddress").val();
            }
            patient.patCityIndex = $("#patCity").val();
            if ($("#pinCode").val() !== "") {
                patient.patPincode = $("#pinCode").val();
            }
            patient.patStateIndex = $("#stateGeo").val();
            patient.patCountryIndex = $('#cntryName').val();
            patient.patPhoneNo = $("#patMobile").val();
            patient.patEmailId = $("#patEmail").val();
            patient.patVipStatus = $("#isVIP").val();
//        patient.patParentRid = 0; Need to enable later;
            patient.patGeneratedDob = $("#patDOB").val();
            if ($('#visitReason option:selected').text() == 'Admission') {
                var kinTable = document.getElementById('kinTable');
                for (var k = 0; k < kinTable.rows.length - 1; k++) {
                    var kin = {};
                    kin.id = !!dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRid').value ? dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRid').value : null;
                    kin.kinPatRid = parseInt($("#patRID").val());
                    kin.kinName = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinName').value;
                    kin.kinMobileNo = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinMobileNo').value;
                    kin.kinOccupation = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinOccupation').value;
                    kin.kinRelationship = dynTableGetNodeInRow(kinTable.rows[k + 1], 'kinRelationship').value;
                    patKinDetails.push(kin);
                }
                patient.kinDetails = patKinDetails;
            } else if ($('#visitReason option:selected').text() == 'Emergency') {
                var kinTable = document.getElementById('kinTable');
                for (var k = 0; k < kinTable.rows.length - 1; k++) {
                    var kin = {};
                    kin.id = !!$("#kinId").val() ? parseInt($("#kinId").val()) : null;
                    kin.kinPatRid = parseInt($("#patRID").val());
                    kin.kinName = $("#EName").val();
                    kin.kinMobileNo = parseInt($("#EContact").val());
                    kin.kinOccupation = parseInt($("#isMLC").val());
                    kin.kinRelationship = parseInt($("#ERelation").val());
                    kin.kinIsActive = 1;
                    patKinDetails.push(kin);
                }
                patient.kinDetails = patKinDetails;
            }

            visitPatDetails.push(patient);
            visit.patient = visitPatDetails;
            visit.consultationPatientDoctorMap = fcPatDocMap;
            if (!!$("#serviceNetAmount").val() && $("#serviceNetAmount").val() != 0) {
                var visitSerBillDetails = [];
                var billDList = [], pmdList = [], receiptDList = [];
                var billH = {};
                var receiptHList = [];
                var receiptH = {};
                var serviceNetAmount = document.getElementById('serviceNetAmount').value;
                var serviceDueAmount = document.getElementById('serviceDueAmount').value;
                var total_pmd = document.getElementById('total_pmd').value;
                if (total_pmd == "" || isNaN(total_pmd)) {
                    total_pmd = 0;
                }
                if (serviceNetAmount == "") {
                    alert("Bill Amount is empty");
                    return false;
                } else if (parseFloat(serviceDueAmount) < parseFloat(total_pmd)) {
                    alert("Due amount is lesser than Total amount paid");
                    return false;
                }
                billH.bhType = 1; // OP bill type : 1;
                billH.bhGrossAmount = $("#serviceGrossAmount").val();
                billH.bhTotalDiscountAmount = $("#serviceDiscountAmount").val();
                billH.bhPatientName = $("#patName").val() + " " + $("#LName").val();
                billH.bhPatientNo = $("#patMobile").val(); // Please change the name
                billH.bhNetAmount = $("#serviceNetAmount").val();
//                billH.bhDiscount = $("#serviceDiscountAmount").val(); // Its already taken as total discount amount  see this command this
                billH.bhPaidAmount = parseFloat(total_pmd);
                billH.bhDocRefRID = !!$("#VDName").val() ? $("#VDName").val() : 0;
                billH.bhUnitRID = dcomethealth.selectedunit; // user selected unit;
                billH.bhPayerType = $("#patRefType").val();
                billH.bhEligibleAmount = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
                billH.bhPayerAmount = 0;
                billH.bhApprovalNumber = !!$("#bhApprovalNo").val() ? $("#bhApprovalNo").val() : 0;
                if ($("#patRefType").val() != 31) {
                    billH.bhPayerRID = $("#payerName").val();
                }
                receiptH.rhTotalAmount = $("#serviceNetAmount").val();
                receiptH.rhEligibleAmt = !!$("#bhEligibleAmount").val() ? $("#bhEligibleAmount").val() : 0;
                receiptH.rhPaidAmount = parseFloat(total_pmd);
                receiptH.rhPayerType = $("#patRefType").val();
                receiptH.rhBaseCurRID = dcomethealth.sCurrencyRid;
                if ($("#patRefType").val() != 31) {
                    receiptH.rhPayerName = $("#payerName option:selected").text();
                } else {
                    receiptH.rhPayerName = $("#patName").val() + " " + $("#LName").val();
                    receiptH.rhPayerNo = $("#patMobile").val();
                }
                if (parseFloat(total_pmd) !== 0) {
                    var table2 = document.getElementById('pmd_table');
                    var table_length2 = table2.rows.length;
                    for (var j = 0; j < table_length2; j++) {
                        if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value != "") {
                            if (parseFloat(serviceNetAmount) < parseFloat(total_pmd)) {
                                alert("Net amount and Total amount paid not equal");
                                return false;
                            }
                            var pmd = {};
                            var receiptDPmd = {};
                            pmd.pmdTransType = 3;
                            pmd.pmdPaymentMode = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_ddict').value;
                            pmd.pmdCardType = dynTableGetNodeInRow(table2.rows[j], 'cardtype').value;
                            pmd.pmdDocNo = dynTableGetNodeInRow(table2.rows[j], 'cheqno').value;
                            pmd.pmdDocDate = dynTableGetNodeInRow(table2.rows[j], 'cheqdate').value;
                            pmd.pmdDocExpDate = dynTableGetNodeInRow(table2.rows[j], 'expdate').value;
                            pmd.pmdDocApprovalNo = dynTableGetNodeInRow(table2.rows[j], 'approvalno').value;
                            pmd.pmdBankName = dynTableGetNodeInRow(table2.rows[j], 'bankname').value;
                            pmd.pmdBankDetails = dynTableGetNodeInRow(table2.rows[j], 'branchname').value;
                            pmd.pmdCurrencyRID = dynTableGetNodeInRow(table2.rows[j], 'pay_mode_currency').value;
                            if (dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value != "") {
                                pmd.pmdAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                            } else {
                                pmd.pmdAmount = 0;
                            }
                            receiptDPmd.rdBillAmount = $("#serviceNetAmount").val();
                            receiptDPmd.rdPaidAmount = dynTableGetNodeInRow(table2.rows[j], 'pmd_amount').value;
                            pmdList.push(pmd);
                            receiptDList.push(receiptDPmd);
                        }
                        receiptH.receiptD = receiptDList;
                        receiptH.paymentModeDetails = pmdList;
                        receiptHList.push(receiptH);
                        billH.receiptH = receiptHList;
                    }
                }
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
//            var serviceOrder = {};
                    var billD = {};
                    //BillD
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value == 0) {
//                         check = false;
                        alert('Entered service name is incorrect');
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
            var search = boRID + "/" + boCode + "/" + actionCode;
            dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                dcomethealth.PatientResource.searchPatient({"id": data}, function (data) {
                    var imageContext = {};
                    $("#resourceId").val() !== "" ? imageContext.id = $("#resourceId").val() : null;
                    imageContext.patImgPatRid = data[0].id;
                    dcomethealth.PatientResource.saveImageInfo(imageContext).done(function (data, textStatus, jqXHR) {
                    });
                    alert(data[0].patMrnNo + " is Saved");
                    $('#patImage').attr('src', '');
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadpage('Patient');
                    dcomethealth.util.loadNotification();
                });
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function saveAdmission(visitRid) {
        var occupy = {};
        occupy.bocPatRid = !!$("#patRID").val() ? $("#patRID").val() : null;
        occupy.bocVisitRid = visitRid;
        occupy.bocBedRid = $("#sBedRid").text();
        occupy.bocPrimaryDoctor = parseInt($("#bocPrimaryDoctor option:selected").val());
        dcomethealth.BedManagementResource.saveBedOccupancy(occupy).done(function (data, textStatus, jqXHR) {
            occpancyDetails();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function occpancyDetails() {
        var details = {};
//        details.bodSessionId = $("#payBed").val();
        details.bodBedRid = $("#sBedRid").text();
        details.bodWardRid = "3";
        details.bodGroupRid = $("#grprooms").val();
//        details.bodFromDatetime = fromDate;
//        details.bodToDatetime = toDate;
        details.bodBedTypeRid = "2";
        details.bodWardUnitRid = dcomethealth.selectedunit;
        details.bodUserRid = dcomethealth.loginuser.id;
        dcomethealth.BedManagementResource.saveBedOccupancyDetails(details).done(function (data, textStatus, jqXHR) {
            if (!!details.bodBedRid) {
                saveBedMaster();
            }
            alert("Saved");
            dcomethealth.util.loadpage('Patient');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveBedMaster() {
        var bed = {};
        bed.btmRid = $("#sBedRid").text();
        bed.btmGroupRid = $('#grprooms option:selected').val();
        bed.btmRoomType = $('#roomType option:selected').val();
        bed.btmPrice = $("#btmPrice").val();
        bed.btmName = $("#sBedName").text();
        bed.btmActiveYesno = 1;
        var search = parseInt($("#sBedRid").text()) + "/" + "BED_DETAILS" + "/" + "OCCUPY";
        dcomethealth.BedManagementResource.saveBedMas(bed, search).done(function (data, textStatus, jqXHR) {
//            alert("Saved");
//            dcomethealth.util.loadpage('BedMaster');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function patientSave() {
        var Patientgrid = {};
        Patientgrid.id = $('#patRid').val();
        Patientgrid.patTitle = $("#patTitle").val();
        Patientgrid.patName = $("#patName").val();
        Patientgrid.patFullName = $("#patName").val() + " " + $("#LName").val();
        Patientgrid.patFamilyName = $("#LName").val();
        Patientgrid.patGenderIndex = $("#patGender").val();
        Patientgrid.patBloodGroupIndex = $("#patBldGrp").val();
        Patientgrid.patDob = $("#patDOB").val();
        Patientgrid.patMaritalStatus = $("#patMaritalStatus").val();
        Patientgrid.patAddress = $("#patAddress").val();
        Patientgrid.patCityIndex = $("#patCity").val();
        if ($("#pinCode").val() !== "") {
            Patientgrid.patPincode = $("#pinCode").val();
        }
        Patientgrid.patStateIndex = $("#stateGeo").val();
        Patientgrid.patCountryIndex = $('#cntryName').val();
        Patientgrid.patPhoneNo = $("#patMobile").val();
        Patientgrid.patEmailId = $("#patEmail").val();
        Patientgrid.patVipStatus = $("#isVIP").val();
        Patientgrid.patGeneratedDob = $("#patDOB").val();
        dcomethealth.PatientResource.savePatgrid(Patientgrid).done(function (data, textStatus, jqXHR) {
            var imageContext = {};
            $("#resourceId").val() !== "" ? imageContext.id = $("#resourceId").val() : null;
            imageContext.patImgPatRid = $('#patRid').val();
            dcomethealth.PatientResource.saveImageInfo(imageContext).done(function (data, textStatus, jqXHR) {
            });
            alert("Saved");
            dcomethealth.util.base_init();
            dcomethealth.util.loadpage('Patient');
            dcomethealth.util.loadNotification();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }

    function patientIdCard(val) {
        $("#patRid").val(val);
        dcomethealth.DataDictionaryResource.searchTemplate({"tempType": "IdCard"}, function (data) { //"tempType": IdCard
            if (!!data) {
                $.each(data, function (index, template) {
                    $.each(dcomethealth.patientData, function (i, patient) {
                        if (val == patient.id) {
                            $("#printIdCard").html(template.tempNodes);
                            var svgId = "#" + $("#printIdCard").find("svg").attr("id");
                            JsBarcode(svgId, patient.patMrnNo, {
                                format: "code39", lineColor: "#00000", width: 1,
                                height: 20,
                                posX: 0,
                                posY: 0,
                                displayValue: false
                            });
                            $("#printIdCard").find("span#patientName").text(patient.patFullName);
                            $("#printIdCard").find("span#mobileNo").text(patient.patPhoneNo);
                            $("#printIdCard").find("span#ptgender").text(patient.patGender);
                            $("#printIdCard").find("span#ptage").text(patient.patAge);
                            $("#printIdCard").find("span#ptmrnNo").text(patient.patMrnNo);
                            $("#printIdCard").find("#patientPhoto").attr('src', '/health_web/rest/patient/v1/pat/image?imgPatRid=' + parseInt($('#patRid').val()));
                        }
                    });
                });
                exportExcel($("#printIdCard").html());
            }
        });
    }
    function exportExcel(data) {
        var printWindow = window.open();
        printWindow.document.write(data);
        setTimeout(function () {
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }, 800);
        dcomethealth.util.loadpage('Patient');
        dcomethealth.util.base_init();
    }

    function validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        getCity: getCity,
        getStates: getStates,
        payerChange: payerChange,
        payerNameChange: payerNameChange,
        doctorChange: doctorChange,
        autocompleteService: autocompleteService,
        searchPatByAppointment: searchPatByAppointment,
        searchApptByAppointment: searchApptByAppointment,
        genderChange: genderChange,
        autocomplete: autocomplete,
        freeConsultationCheck: freeConsultationCheck,
        viewPatient: viewPatient,
        getPatient: getPatient,
        checkExistService: checkExistService,
        searchPostalCode: searchPostalCode,
        submit: submit,
        autoIdSet: autoIdSet,
        patientSave: patientSave,
        exportExcel: exportExcel,
        patientIdCard: patientIdCard,
        saveAdmission: saveAdmission,
        occpancyDetails: occpancyDetails,
        bedSelection: bedSelection,
        validateForm: validateForm,
        refreshData: refreshData
    };
}());
dcomethealth.Patient.init();