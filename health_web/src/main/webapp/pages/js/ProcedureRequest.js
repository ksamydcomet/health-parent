var dcomethealth = dcomethealth || {}, currencyExchange = {};
var procedureData = {};
dcomethealth.ProcedureRequest = (function () {
    var id = "ProcedureRequest", serviceClearVar = 0, staffList = [], preDrugPendingVar = 0, postDrugPendingVar = 0, preServicePendingVar = 0;
    var postServicePendingVar = 0, idArrayDoctor = [], idArrayNurse = [], idArrayTech = [], idArrayAnesthesist = [], check = false, prDraftBillState = 0;
    var isValid = false, intraPostOpEnable = 0, intraPostEdit = 0, isMajor = false, isPreDrug = 0, isIntraService = 0, isIntraDrug = 0, isPostService = 0, isPostDrug = 0, prCatgryIsMinor = false;
    var bedCharge = {}, bedObj = {}, isBedchargeAdd = 0, rescheduleShow = 0, otRoomRid = 0, prOtRoomName = "", wholeEditDisable = 0, intraService = false, intraMaterial = false, postMaterial = false, postService = false;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            fetchProcedureList();
            autocomplete();
            autocompleteProcedure();
            autocompletePreService();
            autocompleteIntraService();
            autocompletePostService();
            autocompletePreDrug();
            autocompleteIntraDrug();
            autocompletePostDrug();
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
            $('#scheduleDatetime').datetimepicker({
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
            });
            $('#scheduleDatetime').datetimepicker({format: 'd/m/Y H:i', step: 5});
            $("#lastFluidTime").datetimepicker({
                pickDate: false,
                minuteStep: 5,
                pickerPosition: 'bottom-right',
                format: 'hh:ii', autoclose: true,
                showMeridian: true,
                startView: 1,
                maxView: 1,
            });
            $("#lastFoodTime").datetimepicker({
                pickDate: false,
                minuteStep: 5,
                pickerPosition: 'bottom-right',
                format: 'hh:ii',
                autoclose: true,
                showMeridian: true,
                startView: 1,
                maxView: 1,
            });
            var today = new Date();
            var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
            var dayName = days[today.getDay()];
            var DAY;
            $.each(dcomethealth.sDdict, function (pIdx, dDictType) {
                if (dDictType.dditCode === "DAYS") {
                    $.each(dDictType.ddict, function (cIdx, ddict) {
                        if (ddict.ddictValue === dayName) {
                            DAY = ddict.id;
                        }
                    });
                }
            });
//            dcomethealth.MasterResource.searchResource({"resCategory": 433}, function (data) {
//                if (!!data) {
//                    $.each(data, function (pIdx, val) {
//                        if (!!val.resourceWorkingHours) {
//                            $.each(val.resourceWorkingHours, function (pIdx, unit) {
//                                $.each(unit, function (pIdx, day) {
//                                    if (DAY == day) {
//                                        $("#anesthes").append("<option value=" + unit.whResourceRID + ">" + val.resName + "</option>")
//                                    }
//                                });
//                            });
//                        }
//                    });
//                }
//            });
            var searchParams = {"staffCategory": 237};
            var options = '';
            dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
                if (!!data) {
                    $.each(data, function (idx, Val) {
                        options += '<option value="' + Val.id + '">' + Val.staffName + '</option>';
                    });
                    $("#primaryDoctor").html(options);
                } else {
                    $("#primaryDoctor").empty();
                }
            });
            $("#prAnesthesistRequired").change(function () {
                if ($("#prAnesthesistRequired").is(":checked")) {
                    $("#anesthesisDiv").removeClass("hidden");
                } else {
                    $("#anesthesisDiv").addClass("hidden");
                }
            });
            $("#scheduleDatetime").on("focus", function () {
                if (intraPostOpEnable == 1) {
                    $(".datetimepicker").each(function () {
                        $(this).attr("style", "display:none;");
                    });
                }
            });
            $("#admissionRequired").change(function () {
                if ($("#admissionRequired").is(":checked")) {
                    $("#admissionDiv").removeClass("hidden");
                } else {
                    $("#admissionDiv").addClass("hidden");
                }
            });
            $("#transplantRequested").change(function () {
                if ($("#transplantRequested").is(":checked")) {
                    $("#tranNotesDiv").removeClass("hidden");
                } else {
                    $("#tranNotesDiv").addClass("hidden");
                    $("#tranNotes").val("");
                }
            });
            $("#transReqSpan").click(function () {
                if ($("#transplantRequested").is(":checked")) {
                    $("#transplantRequested").prop("checked", false);
                    $("#tranNotesDiv").addClass("hidden");
                    $("#tranNotes").val("");
                } else {
                    $("#transplantRequested").prop("checked", true);
                    $("#tranNotesDiv").removeClass("hidden");
                }
            });
            $("#bloodRequested").change(function () {
                if ($("#bloodRequested").is(":checked")) {
                    $("#bloodNotesDiv").removeClass("hidden");
                } else {
                    $("#bloodNotesDiv").addClass("hidden");
                    $("#bloodNotes").val("");
                }
            });
            $("#bloodReqSpan").click(function () {
                if ($("#bloodRequested").is(":checked")) {
                    $("#bloodRequested").prop("checked", false);
                    $("#bloodNotesDiv").addClass("hidden");
                    $("#bloodNotes").val("");
                } else {
                    $("#bloodRequested").prop("checked", true);
                    $("#bloodNotesDiv").removeClass("hidden");
                }
            });
            $("#deNormally").change(function () {
                if ($("#deNormally").is(":checked")) {
                    $("#deNormalNotesDiv").addClass("hidden");
                    $("#lastffDiv").addClass("hidden");
                    $("#lastFluidTime").val("");
                    $("#lastFoodTime").val("");
                    $("#deNormalNotes").val("");
                } else {
                    $("#deNormalNotesDiv").removeClass("hidden");
                    $("#lastffDiv").removeClass("hidden");
                }
            });
            $("#deNormallySpan").click(function () {
                if ($("#deNormally").is(":checked")) {
                    $("#deNormally").prop("checked", false);
                    $("#deNormalNotesDiv,#lastffDiv").removeClass("hidden");
                } else {
                    $("#deNormally").prop("checked", true);
                    $("#deNormalNotesDiv,#lastffDiv").addClass("hidden");
                    $("#lastFluidTime").val("");
                    $("#lastFoodTime").val("");
                    $("#deNormalNotes").val("");
                }
            });
            $("#attDoctor").select2({width: 'resolve'});
            $("#nurses").select2({width: 'resolve'});
            $("#technician").select2({width: 'resolve'});
            $("#anesthes").select2({width: 'resolve'});
            dcomethealth.DataDictionaryResource.searchTemplate({"tempType": "SUMMARY_PRINT"}, function (data) {
                if (!!data) {
                    $.each(data, function (inx, template) {
                        $("#printDIV").html(template.tempNodes);
                    });
                }
            });
            dcomethealth.MasterResource.searchStaffNc({}, function (data) {
                if (!!data) {
                    staffList = data;
                }
            });
            if (parseInt(dcomethealth.commonInit) !== 0) {
                dcomethealth.MasterResource.searchStaffNc({}, function (data) {
                    if (!!data) {
                        staffList = data;
                    }
                });
                $("#ProcedureDetails").removeClass("panel panel-primary").addClass("hidden");
                $("#PatientInfo").removeClass("hidden").addClass("panel panel-primary");
                $('#head_OP').text('Procedure Request Info');
                $("#btns_state").empty();
                var categories = [];
                var btnNumber = 0;
                $("#btns_state").append('<button type="button" class="btn-primary btn hidden" id="COMPLETE1" style="margin-top: 3px; margin-bottom: 3px;">Complete</button>');
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    pIdx++;
                    var div = $("#btns_state");
                    if ($.inArray(datalist.boaName, categories) === -1) {
                        categories.push(datalist.boaName);
                        div.append('<button type="button" class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>');
                        btnName = datalist.boaName;
                        btnNumber++;
                        var boCode = "";
                        if ((btnName == 'Admit' && btnNumber == 1) || (btnNumber == 2 && btnName == 'Schedule')) {//Need close button function admit page arguments
                            boCode = "ADMIT";
                        } else {
                            if ((btnName == 'Complete Procedure' && btnNumber == 2)) {
                                boCode = "SCHEDULE";
                                isMajor = true;
                            } else {
                                boCode = datalist.boaCode;
                            }
                        }
                        if (dcomethealth.actionDatalist.length == pIdx) {
                            div.append('<button type="button" id="closeBtn" class="btn btn-primary  jqueryUIToolTip" title="Close" onclick="dcomethealth.ProcedureRequest.loadWorkList(\'' + boCode + '\')"><i class="fa fa-times" aria-hidden="true"></i></button>');
                        }
                        if (btnName == 'Schedule' || btnName == "Complete Procedure") {
                            check = true;
                        }
                        if (btnName == "Complete Procedure" || btnName == "Discharge Patient") {
                            intraPostEdit = 1;
                        }
                        if (btnName == "Discharge Patient" || btnName == "Release Patient") {
                            wholeEditDisable = 1;
                        }
                        if (btnName == 'Admit' || btnName == "Complete Procedure" || btnName == "Discharge Patient" || btnName == "Release Patient") {
                            isValid = true;
                        }
                        if (btnName == "Discharge Patient") {
                            $("#MARK_AS_DISCHARGE").html("Mark As Discharge");
                            isBedchargeAdd = 1;
                            dcomethealth.DataDictionaryResource.getSysParam({"paramCode": "BED_CHARGE", "paramEntityRID": dcomethealth.loginuser.entityRid}, function (data) {
                                $.each(data, function (Idx, val) {
                                    $.each(dcomethealth.serviceMaster, function (sdx, serv) {
                                        if (serv.bsCode == val.paramValue) {
                                            bedCharge.bsName = serv.bsName;
                                            bedCharge.bPrice = serv.bPrice;
                                            bedCharge.id = serv.id;
                                        }
                                    });
                                });
                            });
                        }
                    }
                });
                if (btnNumber == 2 && btnName == 'Schedule') {
                    rescheduleShow = 1;
                    $("#SCHEDULE").hide();
                }
                if (btnName == "Complete Procedure" || btnName == "Discharge Patient" || btnName == "Release Patient") {
                    if (isMajor) {
                        intraPostOpEnable = 0;
                    } else {
                        intraPostOpEnable = 1; //used for enable intra and post op and readonly the schedule datetime and some fields on the screen
                        $("#intraOpDiv,#postOpDiv,#otComplicationDiv,#postOPInstructionsDiv").removeClass("hidden");
                    }
                    if (btnName == "Release Patient") {
                        $("#summaryDivBtn").removeClass('hidden');
                    } else {
                        $("#summaryDivBtn").addClass('hidden');
                    }
                }
                getProcedureRequest();
                dcomethealth.datatypes.init($("#" + id));
                if (!!$("#prId").val()) {
                    $("#paymentDiv").addClass("hidden");
                }
            }
            if (!!$("#prId").val()) {
                $("#paymentDiv").addClass("hidden");
            }
            $("#btnSummary").on("click", function () {
                $("#summaryDiv").removeClass('hidden');
                $("#summaryModal").modal('show');
            });
            $("#saveSubmit").on("click", function () {
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
                submit(0, "PROCEDURE_REQUEST", "REQUEST");
            });
            $("#SCHEDULE").on("click", function () {
                beforeActionValidation("SCHEDULE");
            });
            $("#ADMIT").on("click", function () {
                beforeActionValidation("ADMIT");
            });
            $("#COMPLETE1").on("click", function () {
                beforeActionValidation("COMPLETE1");
            });
            $("#COMPLETE").on("click", function () {
                beforeActionValidation("COMPLETE");
            });
            $("#MARK_AS_DISCHARGE").on("click", function () {
                beforeActionValidation("MARK_AS_DISCHARGE");
            });
            $("#RELEASE").on("click", function () {
                beforeActionValidation("RELEASE");
            });
        });
    }
    function beforeActionValidation(action) {
        if (action == "complete") {
            alert("Need to create bill");
        } else {
            if ($("#mrnSearch").val() == "") {
                alert("Enter patient name");
                setTimeout(function () {
                    $("#mrnSearch").focus();
                }, 1);
                return false;
            }
            if ($("#procedureSearch").val() == "") {
                alert("Enter procedure name");
                setTimeout(function () {
                    $("#procedureSearch").focus();
                }, 1);
                return false;
            }
            if (!$("#prType").val()) {
                alert("Enter status");
                setTimeout(function () {
                    $("#prType").focus();
                }, 1);
                return false;
            }
            if (!$("#prCategory").val()) {
                alert("Select category");
                setTimeout(function () {
                    $("#procedureSearch").focus();
                }, 1);
                return false;
            }
            if (!$("#primaryDoctor").val()) {
                alert("Enter primary doctor");
                setTimeout(function () {
                    $("#primaryDoctor").focus();
                }, 1);
                return false;
            }
            if ($("#prCategorySpan").text() == 'Minor Surgery') {
                prCatgryIsMinor = true;
            }
            if (!$("#estimetdDuration").val() && !prCatgryIsMinor) {
                alert("Enter estimated duration");
                setTimeout(function () {
                    $("#estimetdDuration").focus();
                }, 1);
                return false;
            }
            if ($("#scheduleDatetime").val() == "" && !prCatgryIsMinor) {
                alert("Select scheduled datetime");
                setTimeout(function () {
                    $("#scheduleDatetime").focus();
                }, 1);
                return false;
            }
            if ((!$("#otRoom").val() || $("#otRoom").val() == 0) && !prCatgryIsMinor) {
                if (action != 'RELEASE') {
                    alert("Select OT room");
                    setTimeout(function () {
                        $("#otRoom").focus();
                    }, 1);
                    return false;
                }
            }

            var validAttnDoc = true;
            $("#s2id_attDoctor").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                validAttnDoc = false;
            });
            if (validAttnDoc && !prCatgryIsMinor) {
                alert("Select attending doctor");
                setTimeout(function () {
                    $("#attDoctor").focus();
                }, 1);
                return false;
            }
            var validNurse = true;
            $("#s2id_nurses").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                validNurse = false;
            });
            if (validNurse && !prCatgryIsMinor) {
                alert("Select nurse");
                return false;
            }
            var validTech = true;
            $("#s2id_technician").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                validTech = false;
            });
            if (validTech && !prCatgryIsMinor) {
                alert("Select technician");
                return false;
            }
            var validAnes = true;
            $("#s2id_anesthes").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                validAnes = false;
            });
            if (validAnes == false && !prCatgryIsMinor) {
                if (!$("#prAnesthesistType").val() || $("#prAnesthesistType").val() == 0 || $("#prAnesthesistType").val() == null) {
                    alert("Select anesthetist type");
                    return false;
                }
            }
            if ((!!$("#prAnesthesistType").val() || $("#prAnesthesistType").val() != 0 || $("#prAnesthesistType").val() != null) && !prCatgryIsMinor) {
                var validAnes = true;
                $("#s2id_anesthes").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                    validAnes = false;
                });
                if (validAnes) {
                    alert("Select anesthetist");
                    return false;
                }
            }
            if (action == "ADMIT") {
//                    var today = new Date();
//                    var tDate = today.getDate() + "-" + (today.getMonth() + 1) + "-" + today.getFullYear();
//                    var tdy = moment(tDate, 'DD-MM-YYYY').format('DD-MM-YYYY');
//                    var tTime = today.getHours() + ":" + today.getMinutes();
//                    var tdyTime = moment(tTime, 'HH:mm').format('HH:mm');
//                    var schDate = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY');
//                    var schTime = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('HH:mm');
//                    if (!(moment(schDate).isSame(moment(tdy))) && !(moment(tdyTime, 'HH:mm').isSame(moment(schTime, 'HH:mm')))) {//else if ((moment(schDate).isSame(moment(tdy))) && (moment(schTime, 'HH:mm').isAfter(moment(tdyTime, 'HH:mm')))) {
//                        alert("Scheduled Date Is" + schDate);
//                        return false;
//                    }
                if (preServicePendingVar == 1) {
                    alert("Pre op service is pending");
                    return false;
                }
                if (preDrugPendingVar == 1) {
                    alert("Pre op material/drug request is pending");
                    return false;
                }
            }
            if (action == "Complete Procedure") {
//                    if (postServicePendingVar == 1) {
//                        alert("Post Op Service is pending");
//                        return false;
//                    }
            }
            if (action == "MARK_AS_DISCHARGE") {

            }
            if (action == "RELEASE") {
                if (prDraftBillState != 3 && prDraftBillState < 3) {
                    alert("Payment is pending");
                    return false;
                }
                if (postServicePendingVar == 1) {
//                        alert("Post Op Service is pending");
//                        return false;
                }
                if (postDrugPendingVar == 1) {
//                        alert("Post Op material Request is pending");
//                        return false;
                }
            }
//            }
            if (action == "COMPLETE1") {
                action = "COMPLETE";
            }
            if (!$("#prId").val()) {
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
            }
            submit(dcomethealth.boRID, "PROCEDURE_REQUEST", action);
        }
    }
    function loadWorkList(actionCode) {
        if (actionCode == "SCHEDULE") {
            dcomethealth.util.loadWorklistByCode('PENDING_PROCEDURES');
        } else if (actionCode == "ADMIT") {//SCHEDULE_PROCEDURES
            dcomethealth.util.loadWorklistByCode('ADMIT_PROCEDURES');
        } else if (actionCode == "COMPLETE") {
            dcomethealth.util.loadWorklistByCode('PROCEDURE_TO_COMPLETE');
        } else if (actionCode == "MARK_AS_DISCHARGE") {
            dcomethealth.util.loadWorklistByCode('DISCHARGE_TO_PROCESS');
        } else {
            dcomethealth.util.loadWorklistByCode('DISCHARGE_TO_CLEAR');
        }
    }
    function getProcedureRequest() {
        idArrayDoctor = [];
        idArrayNurse = [];
        idArrayTech = [];
        idArrayAnesthesist = [];
        $("#prId").val(dcomethealth.boRID);
        if ($("#saveSubmit").length == 0) {    //hide if request flow is not available.
            $("#paymentDiv").addClass("hidden");
        }
        var attndDoctorList = [], nurseList = [], techList = [], anesList = [], disabled = "";
        var searchObj = {"id": dcomethealth.boRID};
        dcomethealth.ProcedureRequestResource.getProcedureRequest(searchObj, function (data) {
            $.each(data, function (pIdx, procReq) {
                if (btnName == "Discharge Patient") {
                    searchObj = {"bcVisitRid": procReq.prVisitRid, "bcBedEntityRid": dcomethealth.loginuser.entityRid};
                    dcomethealth.ClinicalResource.getBedChargeVo(searchObj, function (data) {
                        if (!!data) {
                            bedObj = data;
                            isBedchargeAdd = 1;
                        } else {
                            isBedchargeAdd = 0;
                        }
                    });
                }
                dcomethealth.PatientResource.searchPatient({"id": procReq.prPatientRid}, function (data) {
                    $.each(data, function (inx, pat) {
                        $("#patDobText").text(pat.patDob);
                        $("#patGenderTd").text(pat.patGender);
                        $("#patAddressTd").text(pat.patAddress);
                    });
                    $("#patName,#patMobile").removeClass("hidden");
                    $("#customerSearch").addClass("hidden");
                    $("#mrnSearch1").removeClass("hidden");
                    $("#mrnSearch").val(procReq.prPatientUhid);
                    $("#mrnSearch").attr("disabled", true);
                    $("#mrnSearch,#uhidLabel").addClass("hidden");
                    $("#patName").text(procReq.prPatientName);
                    $("#patUhid").text(procReq.prPatientUhid);
                    $("#patUhid").removeClass("hidden");
                    $("#patDetails").removeClass("hidden");
                    $("#patMobile").text(procReq.prPatientMobile);
                    $("#procedureRid").val(procReq.id);
                    $("#prType").val(procReq.prType);
                    if (!!procReq.prCategory) {
                        $("#prCategory").val(procReq.prCategory);
                        categorySelect();
                    }
                    prDraftBillState = procReq.prDraftBillState;
                    $("#prPatientRid").val(procReq.prPatientRid);
                    $("#primaryDoctor").val(procReq.prPrimaryDoctorRid);
                    $("#prAnesthesistType").val(procReq.prAnesthesistType);
                    $("#patUhidLabel").text(procReq.prPatientUhid);
                    $("#patUHIDTd").text(procReq.prPatientUhid);
                    $("#patNameLabel").text(procReq.prPatientName);
                    $("#patNameTd").text(procReq.prPatientName);
                    $("#patMobileNoLabel").text(procReq.prPatientMobile);
                    $("#patMobileTd").text(procReq.prPatientMobile);
                    $("#procNameLabel").text(procReq.prPrimaryProcedure);
                    $("#procedureTd").text(procReq.prPrimaryProcedure);
                    $("#prPrimaryTd").text(procReq.prCategory);
                    $("#anesTypeLabel").text($("#prAnesthesistType option:selected").text());
                    $("#prAnesTypeTd").text($("#prAnesthesistType option:selected").text());
                    $("#primaryDoctorLabel").text($("#primaryDoctor option:selected").text());
                    $("#prPrimDocTd").text($("#primaryDoctor option:selected").text());
                    CKEDITOR.instances.safeSurCheckList.setData(procReq.prSafeSurgeryChecklist);
                    if (!!procReq.prSurgeryDateTime) {
                        $("#scheduleDatetime").val(moment(procReq.prSurgeryDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY  HH:mm'));
                    }
                    if (intraPostOpEnable == 1) {
                        $("#scheduleDatetime").attr("readonly", true);
                    }
                    $("#estimetdDuration").val(procReq.prEstimatedDuration);
                    $("#attnDocID").val(procReq.prType);
                    $("#prInstructions").val(procReq.prInstructions);
                    $("#prRemarks").val(procReq.prRemarks);
                    $("#postOtComplications").val(procReq.postOtComplications);
                    $("#prOtComplications").val(procReq.prOtComplications);
                    $("#prPostInstructions").val(procReq.prPostInstructions);
                    $("#prFollowUp").val(procReq.prFollowUp);
                    $("#followUpTd").text(procReq.prFollowUp);
                    $("#primaryDoctor").val(procReq.prPrimaryDoctorRid);
                    !!(procReq.prEstimatedDurationMinIndex) ? $("#prEstimatedDurationMinIndex").val(procReq.prEstimatedDurationMinIndex) : "";
                    $("#procedureRid").val(procReq.prProcedureRid);
                    $("#procedureSearch").val(procReq.prPrimaryProcedure);
                    $("#prAnesthesistType").val(procReq.prAnesthesistType);
                    $("#prVisitRid").val(procReq.prVisitRid);
                    $("#prDraftBillId").val(procReq.prDraftBillId);
                    if (procReq.prAnesthesistRequired == 1) {
                        $("#prAnesthesistRequired").attr("checked", true);
                        $("#anesthesisDiv").removeClass("hidden");
                    } else {
                        $("#prAnesthesistRequired").attr("checked", false);
                        $("#anesthesisDiv").addClass("hidden");
                    }
                    if (procReq.prIsTransplantRequested == 1) {
                        $("#transplantRequested").attr("checked", true);
                        $("#tranNotesDiv").removeClass("hidden");
                        $("#tranNotes").text(procReq.prTransplantNotes);
                    } else {
                        $("#transplantRequested").attr("checked", false);
                        $("#tranNotesDiv").addClass("hidden");
                    }
                    if (procReq.prIsBloodRequested == 1) {
                        $("#bloodRequested").attr("checked", true);
                        $("#bloodNotesDiv").removeClass("hidden");
                        $("#bloodNotes").text(procReq.prBloodNotes);
                    } else {
                        $("#bloodRequested").attr("checked", false);
                        $("#tranNotesDiv").addClass("hidden");
                    }
                    otRoomRid = procReq.prOtRoomRid;
                    prOtRoomName = procReq.prOtRoomName;
                    var doctStr = "", nurseStr = "", techStr = "", anesStr = "";
                    if (check) {
                        checkAvailability(0);
                    }
                    if (!!procReq.procedureAttendDoctorList) {
                        $.each(procReq.procedureAttendDoctorList, function (inx, attndDoctor) {
                            $.each(staffList, function (indx, staff) {
                                if (staff.staffResourceRID == attndDoctor.procedureAttDoctorRid) {
                                    var existDoctorObj = {};
                                    existDoctorObj["id"] = attndDoctor.id;
                                    existDoctorObj["procedureRid"] = procReq.id;
                                    existDoctorObj["procedureAttDoctorRid"] = attndDoctor.procedureAttDoctorRid;
                                    idArrayDoctor.push(existDoctorObj);
                                    doctStr += " " + staff.staffName;
                                    var doctorObj = {};
                                    doctorObj ["id"] = attndDoctor.procedureAttDoctorRid;
                                    doctorObj ["text"] = staff.staffName;
                                    attndDoctorList.push(doctorObj);
                                }
                            });
                            $("#s2id_attDoctor").select2('data', attndDoctorList);
                        });
                    }
                    doctStr = !!doctStr ? doctStr : "--";
                    $("#attndDocLabel").text(doctStr);
                    $("#prAttDocTd").text(doctStr);
                    if (!!procReq.procedureNurseList) {
                        $.each(procReq.procedureNurseList, function (inx, procNurse) {
                            $.each(staffList, function (indx, staff) {
                                if (staff.staffResourceRID == procNurse.procNurseRid) {
                                    var existNurseObj = {};
                                    existNurseObj ["id"] = procNurse.id;
                                    existNurseObj ["procRid"] = procReq.id;
                                    existNurseObj ["procNurseRid"] = procNurse.procNurseRid;
                                    idArrayNurse.push(existNurseObj);
                                    nurseStr += " " + staff.staffName;
                                    var nurseObj = {};
                                    nurseObj ["id"] = procNurse.procNurseRid;
                                    nurseObj ["text"] = staff.staffName;
                                    nurseList.push(nurseObj);
                                }
                            });
                            $("#s2id_nurses").select2('data', nurseList);
                        });
                    }
                    nurseStr = !!nurseStr ? nurseStr : "--";
                    $("#nurseNameLabel").text(nurseStr);
                    $("#prNurseTd").text(nurseStr);
                    if (!!procReq.procedureTechnicianList) {
                        $.each(procReq.procedureTechnicianList, function (inx, procTech) {
                            $.each(staffList, function (indx, staff) {
                                if (staff.staffResourceRID == procTech.procedureTechRid) {
                                    var existTechObj = {};
                                    existTechObj ["id"] = procTech.id;
                                    existTechObj ["procedureRid"] = procReq.id;
                                    existTechObj ["procedureTechRid"] = procTech.procedureTechRid;
                                    idArrayTech.push(existTechObj);
                                    techStr += " " + staff.staffName;
                                    var techObj = {};
                                    techObj ["id"] = procTech.procedureTechRid;
                                    techObj ["text"] = staff.staffName;
                                    techList.push(techObj);
                                }
                            });
                            $("#s2id_technician").select2('data', techList);
                        });
                    }
                    techStr = !!techStr ? techStr : "--";
                    $("#techNameLabel").text(techStr);
                    $("#prTechTd").text(techStr);
                    if (!!procReq.procedureAnesthesistList) {
                        $.each(procReq.procedureAnesthesistList, function (inx, procAnesthes) {
                            $.each(staffList, function (indx, staff) {
                                if (staff.staffResourceRID == procAnesthes.procedureAnesthesRid) {
                                    var existAnesObj = {};
                                    existAnesObj ["id"] = procAnesthes.id;
                                    existAnesObj ["procedureRid"] = procReq.id;
                                    existAnesObj ["procedureAnesthesRid"] = procAnesthes.procedureAnesthesRid;
                                    idArrayAnesthesist.push(existAnesObj);
                                    anesStr += " " + staff.staffName;
                                    var anesObj = {};
                                    anesObj ["id"] = procAnesthes.procedureAnesthesRid;
                                    anesObj ["text"] = staff.staffName;
                                    anesList.push(anesObj);
                                }
                            });
                            $("#s2id_anesthes").select2('data', anesList);
                        });
                    }
                    if (isValid) {
                        disabled = "disabled";
                    }
                    anesStr = !!anesStr ? anesStr : "--";
                    var checkVisit = false;
                    $("#anesthesistNameLabel").text(anesStr);
                    $("#prAnesNameTd").text(anesStr);
                    if (!!procReq.serviceRequestH) {
                        $.each(procReq.serviceRequestH, function (inx, serReqH) {
                            if (serReqH.serReqhOpCheck == 1) {
                                if (!!serReqH.serReqOpVisitRid && serReqH.serReqOpVisitRid != 0) {
                                    if (!$("#prVisitRid").val() || $("#prVisitRid").val() == 0) {
                                        dcomethealth.PatientResource.searchVisit({"id": serReqH.serReqOpVisitRid}, function (data) {
                                            if (!!data) {
                                                if (data[0].visTypeIndex == 19) {//IP
                                                    $("#prVisitRid").val(serReqH.serReqOpVisitRid);
                                                    checkVisit = true;
                                                }
                                            }
                                        });
                                        if (!checkVisit) {
                                            dcomethealth.PatientResource.searchVisit({"visPatRid": serReqH.serReqhPatRid}, function (data) {
                                                if (!!data) {
                                                    $.each(data, function (inx, visit) {
                                                        if (visit.visTypeIndex == 19) {//IP
                                                            $("#prVisitRid").val(visit.id);
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                }
                                if (!!serReqH.serReqhBillHRid && serReqH.serReqhBillHRid != 0) {
                                    if (!$("#prDraftBillId").val() || $("#prDraftBillId").val() == 0) {
                                        $("#prDraftBillId").val(serReqH.serReqhBillHRid);
                                    }
                                }
                                isPreService = 1;
                                if (serReqH.serReqhState == 1) {
                                    preServicePendingVar = 1;
                                }
                                $("#serReqhPreOpRid").val(serReqH.serReqhId);
                                $("#pre_op_so_tbody,#preOpTbody,#preOpPrint").empty();
                                $("#pre_op_so_tbody").append('<tr id="pre_ser_first_row" class="hidden"><td width="3%"><i id="preOpSerDel" class="fa fa-minus " onclick="deleteRow(this, \'pre_op_service_dyn_table\')"></i></td><td width="40%"><input type="text" id="preOpServiceName" name="preOpServiceName" placeholder="Enter Service Name"    onchange="" class="col-md-11 col-sm-11 col-xs-11" value="" /><input type="hidden" id="preOpServiceDRid" name="preOpServiceDRid"  value="0"/><input type="hidden" id="preOpServiceRID" name="preOpServiceRID"  value=""/><input type="hidden" id="preOpBdGroupRID" name="preOpBdGroupRID" value=""/><input type="hidden" id="preOpServicePrice" name="preOpServicePrice" value=""/></td><td width="10%"><input id="preOpServiceQty" type="text" value = "1" name="preOpServiceQty"  maxlength="3" onkeydown="tabKeyPress(this, \'pre_op_service_dyn_table\', event)" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12"></td><td width="5%"><i id="plus"   class="fa fa-plus faPlus" onclick="insert_row_pre_service(\'pre_op_service_dyn_table\',this,1)" ></i></td></tr>');
                                $.each(serReqH.serviceRequest, function (index, serRequest) {
                                    index++;
                                    $("#pre_op_so_tbody").append('<tr><td width="3%"><i id="preOpSerDel" class="fa fa-minus hidden" onclick="deleteRow(this, \'pre_op_service_dyn_table\')"></i></td><td width="40%"><input type="text" id="preOpServiceName" name="preOpServiceName" placeholder="Enter Service Name"  onchange="" class="col-md-11 col-sm-11 col-xs-11" value="' + serRequest.serReqItemName + '" ' + disabled + ' /><input type="hidden" id="preOpServiceDRid" name="preOpServiceDRid"  value="' + serRequest.serReqRid + '"/><input type="hidden" id="preOpServiceRID" name="preOpServiceRID"  value="' + serRequest.serReqItemRID + '"/><input type="hidden" id="preOpBdGroupRID" name="preOpBdGroupRID" value="' + serRequest.serReqItemGroupRid + '"/><input type="hidden" id="preOpServicePrice" name="preOpServicePrice" value="' + serRequest.serReqItemPrice + '"/></td><td width="10%"><input id="preOpServiceQty" type="text" value = "' + serRequest.serReqItemQty + '" name="preOpServiceQty" onkeydown="tabKeyPress(this, \'pre_op_service_dyn_table\', event)" maxlength="3"  onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ' + disabled + '></td><td width="5%"><i id="plus" class=""  onclick="insert_row_pre_service(\'pre_op_service_dyn_table\',this,1)" ></i></td></tr>');
                                    $("#preOpTbody").append('<tr><td width="30%">' + index + '</td><td width="50%" align="left">' + serRequest.serReqItemName + '</td><td width="20%">' + serRequest.serReqItemQty + '</td></tr>');
                                    $("#preOpPrint").append('<tr><td width="20%" align="left">' + index + '</td><td width="60%" align="left">' + serRequest.serReqItemName + '</td><td width="20%" align="left">' + serRequest.serReqItemQty + '</td></tr>');
                                });
                                setMaxLength("pre_op_service_dyn_table", "preOpServiceName", "preOpServiceQty");
                                autocompletePreService();
                            }
                            if (serReqH.serReqhOpCheck == 3) {
                                if (!!serReqH.serReqOpVisitRid && serReqH.serReqOpVisitRid != 0) {
                                    if (!$("#prVisitRid").val() || $("#prVisitRid").val() == 0) {
                                        dcomethealth.PatientResource.searchVisit({"id": serReqH.serReqOpVisitRid}, function (data) {
                                            if (!!data) {
                                                if (data[0].visTypeIndex == 19) {//IP
                                                    $("#prVisitRid").val(serReqH.serReqOpVisitRid);
                                                }
                                            }
                                        });
                                    }
                                }
                                if (!!serReqH.serReqhBillHRid && serReqH.serReqhBillHRid != 0) {
                                    if (!$("#prDraftBillId").val() || $("#prDraftBillId").val() == 0) {
                                        $("#prDraftBillId").val(serReqH.serReqhBillHRid);
                                    }
                                }
                                if (serReqH.serReqhState == 1) {
                                    postServicePendingVar = 1;
                                }
                                $("#serReqhPostOpRid").val(serReqH.serReqhId);
                                $("#post_op_service_tbody,#postOpTbody,#postOpPrint").empty();
                                var hiddenvar = 'hidden';
                                if (intraPostEdit == 1) {
                                    hiddenvar = '';
                                }
                                $("#post_op_service_tbody").append('<tr id="post_ser_first_row" class="' + hiddenvar + '"><td width="3%"><i id="postOpSerDel" class="fa fa-minus " onclick="deleteRow(this, \'post_op_service_dyn_table\')"></i></td><td width="40%"><input type="text" id="postOpServiceName" name="postOpServiceName"  placeholder="Enter Service Name"  onchange="" class="col-md-11 col-sm-11 col-xs-11" value=""/><input type="hidden" id="postOpServiceDRid" name="postOpServiceDRid"  value="0"/><input type="hidden" id="postOpServiceRID" name="postOpServiceRID"  value=""/><input type="hidden" id="postOpBdGroupRID" name="postOpBdGroupRID" value=""/><input type="hidden" id="postOpServicePrice" name="postOpServicePrice" value=""/></td><td width="10%"><input id="postOpServiceQty" type="text" value = "1" name="postOpServiceQty"  onkeydown="tabKeyPress(this, \'post_op_service_dyn_table\', event)" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12"></td><td width="5%"><i id="plus" class="fa fa-plus faPlus"  onclick="insert_row_post_service(\'post_op_service_dyn_table\',this,1)"></i></td></tr>');
                                $.each(serReqH.serviceRequest, function (indx, serRequest) {
                                    indx++;
                                    $("#post_op_service_tbody").append('<tr><td width="3%"><i id="postOpSerDel" class="fa fa-minus hidden" onclick="deleteRow(this, \'post_op_service_dyn_table\')"></i></td><td width="40%"><input type="text" id="postOpServiceName" name="postOpServiceName" placeholder="Enter Service Name"   onchange="" class="col-md-11 col-sm-11 col-xs-11" value="' + serRequest.serReqItemName + '" ' + disabled + '/><input type="hidden" id="postOpServiceDRid" name="postOpServiceDRid"  value="' + serRequest.serReqRid + '"/><input type="hidden" id="postOpServiceRID" name="postOpServiceRID"  value="' + serRequest.serReqItemRID + '"/><input type="hidden" id="postOpBdGroupRID" name="postOpBdGroupRID" value="' + serRequest.serReqItemGroupRid + '"/><input type="hidden" id="postOpServicePrice" name="postOpServicePrice" value="' + serRequest.serReqItemPrice + '"/></td><td width="10%"><input id="postOpServiceQty"  type="text" value = "' + serRequest.serReqItemQty + '" name="postOpServiceQty" onkeydown="tabKeyPress(this, \'post_op_service_dyn_table\', event)" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ' + disabled + '></td><td width="5%"><i id="plus" class=""  onclick="insert_row_post_service(\'post_op_service_dyn_table\',this,1)"></i></td></tr>');
                                    $("#postOpTbody").append('<tr><td width="30%">' + indx + '</td><td width="50%" align="left">' + serRequest.serReqItemName + '</td><td width="20%">' + serRequest.serReqItemQty + '</td></tr>');
                                    $("#postOpPrint").append('<tr><td width="20%" align="left">' + indx + '</td><td width="60%" align="left">' + serRequest.serReqItemName + '</td><td width="20%" align="left">' + serRequest.serReqItemQty + '</td></tr>');
                                });
                                setMaxLength("post_op_service_dyn_table", "postOpServiceName", "postOpServiceQty");
                                autocompletePostService();
                            }
                        });
                    }

                    if (!!procReq.drugRequestHs) {
                        $.each(procReq.drugRequestHs, function (dnx, drugReqH) {
                            if (drugReqH.drugReqHOpCheck == 1) {
                                if (drugReqH.drugReqHState != 3 && drugReqH.drugReqHState != -1) {
                                    preDrugPendingVar = 1;
                                }
                                $("#drugReqhPreOpRid").val(drugReqH.id);
                                $("#preOpDrugTbody,#preOpDrugPrint").empty();
                                $("#pre_op_drug_tbody").empty();
                                $("#pre_op_drug_tbody").append('<tr id="pre_drug_first_row" class="hidden"><td width="3%"><i id="preOpDrugDel" class="fa fa-minus " onclick="deleteRow(this, \'pre_op_drug_dyn_table\')"></i></td><td width="40%"><input type="text" id="preOpDrugName"  name="preOpDrugName" placeholder="Enter Material / Drug Name" class="col-md-11 col-sm-11 col-xs-11" value="" /><input type="hidden" id="preOpDrugDRid" name="preOpDrugDRid"  value="0"/><input type="hidden" id="preOpDrugRID" name="preOpDrugRID"  value=""/><input type="hidden" id="preOpDrugPrice" name="preOpDrugPrice" value=""/></td><td width="10%"><input id="preOpQtyDrug" type="text" value = "1" name="preOpQtyDrug" maxlength="3" onkeydown="tabKeyPress(this, \'pre_op_drug_dyn_table\', event)" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12"></td><td width="5%"><i id="plus" class="fa fa-plus faPlus"  onclick="insert_row_pre_drug(\'pre_op_drug_dyn_table\',this,1)"></i></td></tr>');
                                $.each(drugReqH.drugRequestDList, function (index, drugRequestD) {
                                    index++;
                                    var drugReqDItemPrice = !!(drugRequestD.drugReqDItemPrice) ? drugRequestD.drugReqDItemPrice : 0;
                                    $("#pre_op_drug_tbody").append('<tr><td width="3%"><i id="preOpDrugDel" class="fa fa-minus hidden" onclick="deleteRow(this, \'pre_op_drug_dyn_table\')"></i></td><td width="40%"><input type="text" id="preOpDrugName" name="preOpDrugName"  placeholder="Enter Material / Drug Name" class="col-md-11 col-sm-11 col-xs-11" value="' + drugRequestD.drugReqDItemName + '" ' + disabled + '/><input type="hidden" id="preOpDrugDRid" name="preOpDrugDRid"  value="' + drugRequestD.id + '"/><input type="hidden" id="preOpDrugRID" name="preOpDrugRID"  value="' + drugRequestD.drugReqDItemRID + '"/><input type="hidden" id="preOpDrugPrice" name="preOpDrugPrice" value="' + drugReqDItemPrice + '"/></td><td width="10%"><input id="preOpQtyDrug" type="text" onkeydown="tabKeyPress(this, \'pre_op_drug_dyn_table\', event)"  value = "' + drugRequestD.drugReqDItemQty + '" name="preOpQtyDrug" maxlength="3"   onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ' + disabled + '></td><td width="5%"><i id="plus" class=""   onclick="insert_row_pre_drug(\'pre_op_drug_dyn_table\',this,1)"></i></td></tr>');
                                    $("#preOpDrugTbody").append('<tr><td width="30%">' + index + '</td><td width="50%" align="left">' + drugRequestD.drugReqDItemName + '</td><td width="20%" class="text-right">' + drugRequestD.drugReqDItemQty + '</td></tr>');
                                    $("#preOpDrugPrint").append('<tr><td width="20%" align="left">' + index + '</td><td width="60%" align="left">' + drugRequestD.drugReqDItemName + '</td><td width="20%" class="text-right">' + drugRequestD.drugReqDItemQty + '</td></tr>');
                                });
                                setMaxLength("pre_op_drug_dyn_table", "preOpDrugName", "preOpQtyDrug");
                                autocompletePreDrug();
                            }
                            if (drugReqH.drugReqHOpCheck == 3) {
                                if (drugReqH.drugReqHState == 1) {
                                    postDrugPendingVar = 1;
                                }
                                var hiddenvar = 'hidden';
                                if (intraPostEdit == 1) {
                                    hiddenvar = '';
                                }
                                $("#drugReqhPostOpRid").val(drugReqH.id);
                                $("#post_op_drug_tbody").empty();
                                $("#post_op_drug_tbody").append('<tr id="post_drug_first_row" class="' + hiddenvar + '"><td width="3%"><i id="postOpDrugDel" class="fa fa-minus " onclick="deleteRow(this, \'post_op_drug_dyn_table\')"></i></td><td width="40%"><input type="text" id="postOpDrugName" name="postOpDrugName" placeholder="Enter Material / Drug Name"  class="col-md-11 col-sm-11 col-xs-11" value="" /><input type="hidden" id="postOpDrugDRid" name="postOpDrugDRid" value="0"/><input type="hidden" id="postOpDrugRID" name="postOpDrugRID"  value=""/><input type="hidden" id="postOpDrugPrice" name="postOpDrugPrice" value=""/></td><td width="10%"><input id="postOpDrugQty"  type="text" value = "1" name="postOpDrugQty" onkeydown="tabKeyPress(this, \'post_op_drug_dyn_table\', event)"  maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ></td><td width="5%"><i id="plus" class="fa fa-plus faPlus"  onclick="insert_row_post_drug(\'post_op_drug_dyn_table\',this,1)"></i></td></tr>');
                                $("#postOpDrugTbody,#postOpDrugPrint").empty();
                                $.each(drugReqH.drugRequestDList, function (indx, drugRequestD) {
                                    indx++;
                                    var drugReqDItemPrice = !!(drugRequestD.drugReqDItemPrice) ? drugRequestD.drugReqDItemPrice : 0;
                                    $("#post_op_drug_tbody").append('<tr><td width="3%"><i id="postOpDrugDel" class="fa fa-minus hidden" onclick="deleteRow(this, \'post_op_drug_dyn_table\')"></i></td><td width="40%"><input type="text" id="postOpDrugName" name="postOpDrugName" placeholder="Enter Material / Drug Name" class="col-md-11 col-sm-11 col-xs-11"  value="' + drugRequestD.drugReqDItemName + '" ' + disabled + '/><input type="hidden" id="postOpDrugDRid" name="postOpDrugDRid"  value="' + drugRequestD.id + '"/><input type="hidden" id="postOpDrugRID" name="postOpDrugRID"  value="' + drugRequestD.drugReqDItemRID + '"/><input type="hidden" id="postOpDrugPrice" name="postOpDrugPrice" value="' + drugReqDItemPrice + '"/></td><td width="10%"><input id="postOpDrugQty"  onkeydown="tabKeyPress(this, \'post_op_drug_dyn_table\', event)" type="text" value = "' + drugRequestD.drugReqDItemQty + '" name="postOpDrugQty" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ' + disabled + '></td><td width="5%"><i id="plus" class=""  onclick="insert_row_post_drug(\'post_op_drug_dyn_table\',this,1)"></i></td></tr>');
                                    $("#postOpDrugTbody").append('<tr><td width="30%">' + indx + '</td><td width="50%" align="left">' + drugRequestD.drugReqDItemName + '</td><td width="20%">' + drugRequestD.drugReqDItemQty + '</td></tr>');
                                    $("#postOpDrugPrint").append('<tr><td width="20%" align="left">' + indx + '</td><td width="60%" align="left">' + drugRequestD.drugReqDItemName + '</td><td width="20%" align="left">' + drugRequestD.drugReqDItemQty + '</td></tr>');
                                });
                                setMaxLength("post_op_drug_dyn_table", "postOpDrugName", "postOpDrugQty");
                                autocompletePostDrug();
                            }
                        });
                    }
                    if (!!procReq.prVisitRid && procReq.prVisitRid != 0) {
                        var searchObj = {"soVisitRID": procReq.prVisitRid, "soMajorProcedureStatus": 0};
                        dcomethealth.ServiceOrderResource.searchServiceOrder(searchObj, function (data) {
                            if (parseInt(data.length) > 0) {
                                var hiddenvar = 'hidden';
                                if (intraPostEdit == 1) {
                                    hiddenvar = '';
                                }
                                $("#intra_op_so_tbody,#intraOpTbody,#intraOpPrint").empty();
                                $("#intra_op_so_tbody").append('<tr id="intra_ser_first_row" class="' + hiddenvar + '"><td width="3%"><i id="intraOpSerDel" class="fa fa-minus " onclick="deleteRow(this, \'intra_op_service_dyn_table\')"></i></td><td width="40%"><input type="text" id="intraOpServiceName" name="intraOpServiceName" placeholder="Enter Service Name"    onchange="" class="col-md-11 col-sm-11 col-xs-11" value="" /><input type="hidden" id="intraOpSerReqRid" name="intraOpSerReqRid"  value="0"/><input type="hidden" id="intraOpServiceRID" name="intraOpServiceRID"  value=""/><input type="hidden" id="intraOpBdGroupRID" name="intraOpBdGroupRID" value="0"/><input type="hidden" id="intraOpServicePrice" name="intraOpServicePrice" value="0"/></td><td width="10%"><input id="intraOpServiceQty" type="text" value = "1" name="intraOpServiceQty" maxlength="3" onkeydown="tabKeyPress(this, \'intra_op_service_dyn_table\', event)" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ></td><td width="5%"><i id="plus" class="fa fa-plus faPlus"    onclick="insert_row_intra_service(\'intra_op_service_dyn_table\',this,1)"></i></td></tr>');
                                $.each(data, function (indx, sOrder) {
                                    indx++;
                                    $("#intra_op_so_tbody").append('<tr><td width="3%"><i id="intraOpSerDel" class="fa fa-minus hidden" onclick="deleteRow(this, \'intra_op_service_dyn_table\')"></i></td><td width="40%"><input type="text" id="intraOpServiceName" name="intraOpServiceName" placeholder="Enter Service Name"    onchange="" class="col-md-11 col-sm-11 col-xs-11" value="' + sOrder.soItemName + '" ' + disabled + '/><input type="hidden" id="intraOpSerReqRid" name="intraOpSerReqRid"  value="' + sOrder.id + '"/><input type="hidden" id="intraOpServiceRID" name="intraOpServiceRID"  value="' + sOrder.soItemID + '"/><input type="hidden" id="intraOpBdGroupRID" name="intraOpBdGroupRID" value="0"/><input type="hidden" id="intraOpServicePrice" name="intraOpServicePrice" value="0"/></td><td width="10%"><input id="intraOpServiceQty" type="text" value = "' + sOrder.soQty + '" name="intraOpServiceQty" maxlength="3" onkeydown="tabKeyPress(this, \'intra_op_service_dyn_table\', event)" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ' + disabled + '></td><td width="5%"><i id="plus" class=""    onclick="insert_row_intra_service(\'intra_op_service_dyn_table\',this,1)"></i></td></tr>');
                                    $("#intraOpTbody").append('<tr><td width="30%">' + indx + '</td><td width="50%" align="left">' + sOrder.soItemName + '</td><td width="20%">' + sOrder.soQty + '</td></tr>');
                                    $("#intraOpPrint").append('<tr><td width="20%" align="left">' + indx + '</td><td width="60%" align="left">' + sOrder.soItemName + '</td><td width="20%" align="left">' + sOrder.soQty + '</td></tr>');
                                });
                                setMaxLength("intra_op_service_dyn_table", "intraOpServiceName", "intraOpServiceQty");
                                autocompleteIntraService();
                            }
                        });
                        var searchObj = {"ioVisitRid": procReq.prVisitRid, "ioMajorProcedureStatus": 0};
                        dcomethealth.ItemOrderResource.searchItemOrder(searchObj, function (data) {
                            if (parseInt(data.length) > 0) {
                                var hiddenvar = 'hidden';
                                if (intraPostEdit == 1) {
                                    hiddenvar = '';
                                }
                                $("#intra_op_drug_tbody,#intraOpTbody,#intraOpPrint").empty();
                                $("#intra_op_drug_tbody").append('<tr id="intra_post_first_row" class="' + hiddenvar + '"><td width="3%"><i id="intraOpDrugDel" class="fa fa-minus " onclick="deleteRow(this, \'intra_op_drug_dyn_table\')"></i></td><td width="40%"><input type="text" id="intraOpDrugName"  name="intraOpDrugName" placeholder="Enter Service Name"  onchange="" class="col-md-11 col-sm-11 col-xs-11" value="" /><input type="hidden" id="intraOpDrugRID" name="intraOpDrugRID"  value=""/><input type="hidden" id="intraOpDrugDRID" name="intraOpDrugDRID"  value="0"/><input type="hidden" id="intraOpBdGroup1RID" name="intraOpBdGroup1RID" value="0"/><input type="hidden" id="intraOpDrugPrice" name="intraOpDrugPrice" value="0"/></td><td width="10%"><input id="intraOpDrugQty" type="text" value = "1" name="intraOpDrugQty"  maxlength="3" onkeydown="tabKeyPress(this, \'intra_op_drug_dyn_table\', event)" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ></td><td width="5%"><i id="plus" class="fa fa-plus faPlus"  onclick="insert_row_intra_drug(\'intra_op_drug_dyn_table\',this,1)"></i></td></tr>');
                                $.each(data, function (indx, itemOrder) {
                                    indx++;
                                    $("#intra_op_drug_tbody").append('<tr><td width="3%"><i id="intraOpDrugDel" class="fa fa-minus hidden" onclick="deleteRow(this, \'intra_op_drug_dyn_table\')"></i></td><td width="40%"><input type="text" id="intraOpDrugName" name="intraOpDrugName"    placeholder="Enter Material / Drug  Name"  onchange="" class="col-md-11 col-sm-11 col-xs-11" value="' + itemOrder.ioItemName + '" ' + disabled + '/><input type="hidden" id="intraOpDrugDRID" name="intraOpDrugDRID"  value="' + itemOrder.id + '"/><input type="hidden" id="intraOpDrugRID" name="intraOpDrugRID"  value="' + itemOrder.ioItemId + '"/><input type="hidden" id="intraOpBdGroup1RID" name="intraOpBdGroup1RID" value="0"/><input type="hidden" id="intraOpDrugPrice" name="intraOpDrugPrice" value="0"/></td><td width="10%"><input id="intraOpDrugQty" type="text"  value = "' + itemOrder.ioItemQty + '" name="intraOpDrugQty" onkeydown="tabKeyPress(this, \'intra_op_drug_dyn_table\', event)" maxlength="3" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" class="text-right col-md-12 col-sm-12 col-xs-12" ' + disabled + '></td><td width="5%"><i id="plus" class=""  onclick="insert_row_intra_drug(\'intra_op_drug_dyn_table\',this,1)"></i></td></tr>');
                                    $("#intraOpTbody").append('<tr><td width="30%">' + indx + '</td><td width="50%" align="left">' + itemOrder.ioItemName + '</td><td width="20%">' + itemOrder.ioItemQty + '</td></tr>');
                                    $("#intraOpPrint").append('<tr><td width="20%" align="left">' + indx + '</td><td width="60%" align="left">' + itemOrder.ioItemName + '</td><td width="20%" align="left">' + itemOrder.ioItemQty + '</td></tr>');
                                });
                                setMaxLength("intra_op_drug_dyn_table", "intraOpDrugName", "intraOpDrugQty");
                                autocompleteIntraDrug();
                            }
                        });
                    }
                });
                if (parseInt(wholeEditDisable) == 1) {
                    $("#procedureSearch,#estimetdDuration,#prInstructions,#prRemarks,#tranNotes,#tranNotes,#deNormalNotes,#lastFluidTime,#lastFoodTime").attr("disabled", true);
                    $("#primaryDoctor,#prType,#otRoom,#prEstimatedDurationMinIndex,#transplantRequested,#bloodRequested,#bloodNotes,#deNormally,#prAnesthesistType").attr('disabled', true);
                    $("#otRoom").html('<option value="' + procReq.prOtRoomRid + '" >' + procReq.prOtRoomName + '</option>');
                    $('#anesthes,#nurses,#attDoctor,#technician').attr('disabled', 'disabled');
                    $("#transReqSpan,#bloodReqSpan,#deNormallySpan").unbind("click");
                    $("#transReqSpan,#bloodReqSpan,#deNormallySpan").attr("style", "cursor:not-allowed");
                    CKEDITOR.instances.safeSurCheckList.setReadOnly(true);
                }
                var searchParams = {"id": procReq.prProcedureRid, "bsName": procReq.prPrimaryProcedure, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    if (!!data) {
                        if (data[0].id == procReq.prProcedureRid) {
                            $("#prPrice").val(data[0].bPrice);
                        }
                    }
                });
            });
        });
    }
    function categorySelect() {
        if (!$("#prCategory").val() || $("#prCategory").val() == "undefined" || $("#prCategory").val() == 0) {
            $("#categoryDiv").addClass("hidden");
            $("#prCategorySpan").text("");
            $("#prCategory").val("");
        }
        if ($("#prCategory").val() == 2 || $("#prCategory").val() == 3) {
            $("#deNormalDiv,#schedulingDiv,#preOpDiv,#safeSurCheckListdiv,#saveSubmit,#SCHEDULE").removeClass("hidden");
            if (intraPostOpEnable == 0) {
                $("#postOpDiv,#intraOpDiv,#COMPLETE").addClass("hidden");
            } else {
                $("#postOpDiv,#intraOpDiv").removeClass("hidden");
            }
        } else {
            $("#deNormalDiv,#schedulingDiv,#preOpDiv,#safeSurCheckListdiv,#saveSubmit,#SCHEDULE").addClass("hidden");
            $("#intraOpDiv,#postOpDiv,#COMPLETE").removeClass("hidden");
        }
        if (parseInt($("#prCategory").val()) == 1) {
            $("#categoryDiv").removeClass("hidden");
            $("#prCategorySpan").text("Minor Surgery");
        }
        if (parseInt($("#prCategory").val()) == 2) {
            $("#categoryDiv").removeClass("hidden");
            $("#prCategorySpan").text("Major Surgery");
        }
    }

    function printSummary() {
        exportPrint($("#printDIV").html());
    }
    function exportPrint(data) {
        if (!!data) {
            var printWindow = window.open();
            printWindow.document.write(data);
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
            dcomethealth.util.loadpage('ProcedureRequest');
            dcomethealth.util.base_init();
        }
    }
    function checkAvailability(val) {
        if (rescheduleShow == 1 && val == 1) {
            $("#ADMIT").hide();
            $("#SCHEDULE").show();
            $("#SCHEDULE").html('Reschedule');
            $("#pre_ser_first_row,#pre_drug_first_row").removeClass("hidden");
        }
        var today = new Date();
        var tDate = today.getDate();
        var tMonth = today.getMonth() + 1;
        var tYear = today.getFullYear();
        var tHrs = today.getHours();
        var tMins = today.getMinutes();
        if (!!$("#scheduleDatetime").val()) {
            var schDate = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('DD');
            var schMonth = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('MM');
            var schYear = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('YYYY');
            var schHour = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('HH');
            var schMins = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('mm');
            if (schYear < tYear) {
                alert("Schedule cannot be done for previous date and time");
                $("#scheduleDatetime").val("");
                return false;
            }
            if (schYear == tYear) {
                if ((schMonth < tMonth)) {
                    alert("Schedulecannot be done for previous date and time");
                    $("#scheduleDatetime").val("");
                    return false;
                } else if (schMonth == tMonth) {
                    if (schDate < tDate) {
                        alert("Schedulecannot be done for previous date and time");
                        $("#scheduleDatetime").val("");
                        return false;
                    } else if (schDate == tDate) {
                        if ((schHour < tHrs)) {
                            alert("Schedulecannot be done for previous date and time");
                            $("#scheduleDatetime").val("");
                            return false;
                        } else if (schMins < tMins) {
                            alert("Schedulecannot be done for previous date and time");
                            $("#scheduleDatetime").val("");
                            return false;
                        }
                    }
                }
            }
            $("#saveSubmit,#COMPLETE1").addClass("hidden");
            if (!!$("#prId").val()) {
                $("#paymentDiv").addClass("hidden");
            }
        }
        if (!$("#scheduleDatetime").val() && !$("#estimetdDuration").val()) {
            if (val != 0) {
                $("#saveSubmit,#COMPLETE1").removeClass("hidden");
            }
        }

        if (!!$("#scheduleDatetime").val() && !!$("#estimetdDuration").val()) {
            var searchObj = {"scheduledDateAndTime": $("#scheduleDatetime").val(), "estimatedDuration": $("#estimetdDuration").val(), "estimatedIndex": 1, "resEntityRid": dcomethealth.loginuser.entityRid}
            dcomethealth.MasterResource.searchResourceAvailability(searchObj, function (data) {
                console.log(data);
                var optionsD = "", optionsN = "", optionsT = "", optionsA = "", optionsOt = "";
                $.each(data.resourceAvailabilityWithCategorys, function (idx, rawCategories) {
                    if (rawCategories.categoryName == "Doctor") {
                        $.each(rawCategories.availableResources, function (gdx, availRes) {
                            optionsD += '<option value="' + availRes.resourceRid + '">' + availRes.resourceName + '</option>';
                        });
                    } else if (rawCategories.categoryName == "Nurse") {
                        $.each(rawCategories.availableResources, function (gdx, availRes) {
                            optionsN += '<option value="' + availRes.resourceRid + '">' + availRes.resourceName + '</option>';
                        });
                    } else if (rawCategories.categoryName == "Technician") {
                        $.each(rawCategories.availableResources, function (gdx, availRes) {
                            optionsT += '<option value="' + availRes.resourceRid + '">' + availRes.resourceName + '</option>';
                        });
                    } else if (rawCategories.categoryName == "Anesthetist") {
                        $.each(rawCategories.availableResources, function (gdx, availRes) {
                            optionsA += '<option value="' + availRes.resourceRid + '">' + availRes.resourceName + '</option>';
                        });
                    } else if (rawCategories.categoryName == "Operation Theatre") {
                        if (otRoomRid > 0) {
                            optionsOt += '<option value="' + otRoomRid + '">' + prOtRoomName + '</option>';
                        } else {
                            optionsOt += '<option value="' + 0 + '"></option>';
                        }


                        $.each(rawCategories.availableResources, function (gdx, availRes) {
                            optionsOt += '<option value="' + availRes.resourceRid + '">' + availRes.resourceName + '</option>';
                        });
                    }
                });
                if (val == 1) {
                    $("#attDoctor").select2("val", "");
                    $("#nurses").select2("val", "");
                    $("#technician").select2("val", "");
                    $("#anesthes").select2("val", "");
                    $("#prAnesthesistType").val(0);
                    $("#otRoom").empty();
                }
                $("#attDoctor").html(optionsD);
                $("#nurses").html(optionsN);
                $("#technician").html(optionsT);
                $("#anesthes").html(optionsA);
                $("#otRoom").html(optionsOt);
//                alert(otRoomRid);
//                $("#otRoom").val(otRoomRid);
            });
        } else {
            if (val == 1) {
                $("#attDoctor").select2("val", "");
                $("#nurses").select2("val", "");
                $("#technician").select2("val", "");
                $("#anesthes").select2("val", "");
                $("#prAnesthesistType").val(0);
                $("#otRoom").empty();
            }
            return false;
        }
    }

    function checkExistPreService(serviceRID, row) {
        var table = document.getElementById('pre_op_service_dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceRID').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceDRid').value == 0) {
                if (parseInt(serviceRid) == parseInt(serviceRID)) {
                    count++;
                    if (count == 2) {
                        alert("Alredy exsiting service");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[1], 'preOpServiceName').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpServiceRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpBdGroupRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpServicePrice').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus faPlus";
                            dynTableGetNodeInRow(table.rows[1], 'preOpServiceName').focus();
                            return false;
                        }, 20)
                    }
                }
            }
        }
    }
    function setMaxLength(tableId, columnId1, columnId2) {
        var table = document.getElementById(tableId);
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var columnName1 = dynTableGetNodeInRow(table.rows[i + 1], columnId1).value;
            var columnName2 = dynTableGetNodeInRow(table.rows[i + 1], columnId2).value;
            if (!!columnName1 && !!columnName2) {
                dynTableGetNodeInRow(table.rows[i + 1], columnId1).maxLength = columnName1.length;
                dynTableGetNodeInRow(table.rows[i + 1], columnId2).maxLength = columnName2.length;
            }
        }
    }
    function checkExistintraService(serviceRID, row) {
        var table = document.getElementById('intra_op_service_dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceRID').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'intraOpSerReqRid').value == 0) {
                if (parseInt(serviceRid) == parseInt(serviceRID)) {
                    count++;
                    if (count == 2) {
                        alert("Already exsiting service");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[1], 'intraOpServiceName').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpServicePrice').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpServiceRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus faPlus";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpServiceName').focus();
                            return false;
                        }, 20)
                    }
                }
            }
        }
    }
    function checkExistpostService(serviceRID, row) {
        var table = document.getElementById('post_op_service_dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceRID').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceDRid').value == 0) {
                if (parseInt(serviceRid) == parseInt(serviceRID)) {
                    count++;
                    if (count == 2) {
                        alert("Already exsiting service");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[1], 'postOpServiceName').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpServiceRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpBdGroupRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpServicePrice').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpServiceDRid').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus faPlus";
                            dynTableGetNodeInRow(table.rows[1], 'postOpServiceName').focus();
                            return false;
                        }, 20)
                    }
                }
            }
        }
    }
    function autoClearPR() {
        if (parseInt(serviceClearVar) != 1) {
            $("#procedureRid").val("");
            $("#procedureSearch").val("");
            $("#categoryDiv").addClass("hidden");
        }
    }
    function autoIdSetpre(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'preOpServiceRID').value = "";
            dynTableGetNodeInRow(elem, 'preOpServiceName').value = "";
            dynTableGetNodeInRow(elem, 'preOpBdGroupRID').value = "";
            dynTableGetNodeInRow(elem, 'preOpServicePrice').value = "";
        }
    }
    function autoIdSetintra(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'intraOpServiceRID').value = "";
            dynTableGetNodeInRow(elem, 'intraOpServiceName').value = "";
            dynTableGetNodeInRow(elem, 'intraOpBdGroupRID').value = "";
            dynTableGetNodeInRow(elem, 'intraOpServicePrice').value = "";
        }
    }
    function autoIdSetpost(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'postOpServiceRID').value = "";
            dynTableGetNodeInRow(elem, 'postOpServiceName').value = "";
            dynTableGetNodeInRow(elem, 'postOpBdGroupRID').value = "";
            dynTableGetNodeInRow(elem, 'postOpServicePrice').value = "";
        }
    }
    function autoIdSetpreDrug(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'preOpDrugRID').value = "";
            dynTableGetNodeInRow(elem, 'preOpDrugName').value = "";
            dynTableGetNodeInRow(elem, 'preOpDrugPrice').value = "";
        }
    }
    function autoIdSetintraDrug(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'intraOpDrugRID').value = "";
            dynTableGetNodeInRow(elem, 'intraOpDrugName').value = "";
            dynTableGetNodeInRow(elem, 'intraOpDrugPrice').value = "";
        }
    }
    function autoIdSetpostDrug(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'postOpDrugRID').value = "";
            dynTableGetNodeInRow(elem, 'postOpDrugName').value = "";
            dynTableGetNodeInRow(elem, 'postOpDrugPrice').value = "";
        }
    }
    function autocompletePreService() {
        $("#" + id + " input[name='preOpServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                $("#serReqhPreOpCheck").val(1);
                autoIdSetpre(dynTableGetNodeInRow(this, 'preOpServiceRID'));
                dynTableGetNodeInRow(this, 'preOpServiceRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'preOpBdGroupRID').value = ui.item.data.bsGroup;
                dynTableGetNodeInRow(this, 'preOpServicePrice').value = ui.item.data.bPrice;
                checkExistPreService(dynTableGetNodeInRow(this, 'preOpServiceRID').value, dynTableGetNodeInRow(this));
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
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
            minLength: 1});
    }
    function autocompleteIntraService() {
        $("#" + id + " input[name='intraOpServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                $("#serReqhIntraOpCheck").val(2);
                autoIdSetintra(dynTableGetNodeInRow(this, 'intraOpServiceRID'));
                dynTableGetNodeInRow(this, 'intraOpServiceRID').value = ui.item.data.id;
                var y = dynTableGetNodeInRow(this, 'intraOpServiceRID').value;
                dynTableGetNodeInRow(this, 'intraOpBdGroupRID').value = ui.item.data.bsGroup;
                dynTableGetNodeInRow(this, 'intraOpServicePrice').value = ui.item.data.bPrice;
                var row = dynTableGetNodeInRow(this);
                checkExistintraService(y, row);
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
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
            minLength: 1});
    }
    function autocompletePostService() {
        $("#" + id + " input[name='postOpServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                $("#serReqhPostOpCheck").val(3);
                autoIdSetpost(dynTableGetNodeInRow(this, 'postOpServiceRID'));
                dynTableGetNodeInRow(this, 'postOpServiceRID').value = ui.item.data.id;
                var y = dynTableGetNodeInRow(this, 'postOpServiceRID').value;
                dynTableGetNodeInRow(this, 'postOpBdGroupRID').value = ui.item.data.bsGroup;
                dynTableGetNodeInRow(this, 'postOpServicePrice').value = ui.item.data.bPrice;
                var row = dynTableGetNodeInRow(this);
                checkExistpostService(y, row);
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
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
            minLength: 1});
    }
    function checkExistPreDrug(serviceRID, row) {
        var table = document.getElementById('pre_op_drug_dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugRID').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugDRid').value == 0) {
                if (parseInt(serviceRid) == parseInt(serviceRID)) {
                    count++;
                    if (count == 2) {
                        alert("Alredy exsiting material / drug");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus faPlus";
                            dynTableGetNodeInRow(table.rows[1], 'preOpDrugName').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpDrugRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpDrugPrice').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpDrugDRid').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'preOpDrugName').focus();
                            return false;
                        }, 20)
                    }
                }
            }
        }
    }
    function checkExistintraDrug(serviceRID, row) {
        var table = document.getElementById('intra_op_drug_dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugRID').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugDRID').value == 0) {
                if (parseInt(serviceRid) == parseInt(serviceRID)) {
                    count++;
                    if (count == 2) {
                        alert("Alredy exsiting material / drug");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus faPlus";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpDrugName').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpDrugRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpBdGroup1RID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpDrugPrice').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpDrugDRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'intraOpDrugName').focus();
                            return false;
                        }, 20)
                    }
                }
            }
        }
    }
    function checkExistpostDrug(serviceRID, row) {
        var table = document.getElementById('post_op_drug_dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugRID').value;
            if (dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugDRid').value == 0) {
                if (parseInt(serviceRid) == parseInt(serviceRID)) {
                    count++;
                    if (count == 2) {
                        alert("Already exsiting material / drug");
                        setTimeout(function () {
                            dynTableGetNodeInRow(table.rows[1], 'plus').className = "fa fa-plus faPlus";
                            dynTableGetNodeInRow(table.rows[1], 'postOpDrugName').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpDrugRID').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpDrugPrice').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpDrugDRid').value = "";
                            dynTableGetNodeInRow(table.rows[1], 'postOpDrugName').focus();
                            return false;
                        }, 20)
                    }
                }
            }
        }
    }
    function autocompletePreDrug() {
        var skuName;
        $("#" + id + " input[name='preOpDrugName']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                var Auto = this;
                $("#matReqhPreOpCheck").val(1);
                serviceClearVar = 1;
                autoIdSetpreDrug(dynTableGetNodeInRow(this, 'preOpDrugRID'));
                dynTableGetNodeInRow(this, 'preOpDrugRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'preOpDrugPrice').value = ui.item.data.skuLastMrp;
                var y = dynTableGetNodeInRow(this, 'preOpDrugRID').value;
                var row = dynTableGetNodeInRow(this);
                checkExistPreDrug(y, row);
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
                serviceClearVar = 0;
                var searchParams = {"skuName": queryString, "skuIsConsumable": 1};
                dcomethealth.MasterResource.searchSkus(searchParams, function (data) {
                    if (!!data) {
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
                    }
                });
            },
            minLength: 3
        });
    }
    function autocompleteIntraDrug() {
        var skuName;
        $("#" + id + " input[name='intraOpDrugName']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                var Auto = this;
                $("#matReqhIntraOpCheck").val(2);
                serviceClearVar = 1;
                autoIdSetintraDrug(dynTableGetNodeInRow(this, 'intraOpDrugRID'));
                dynTableGetNodeInRow(this, 'intraOpDrugRID').value = ui.item.data.id;
                var y = dynTableGetNodeInRow(this, 'intraOpDrugRID').value;
                dynTableGetNodeInRow(this, 'intraOpDrugPrice').value = ui.item.data.skuLastMrp;
                var row = dynTableGetNodeInRow(this);
                checkExistintraDrug(y, row);
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
                var searchParams = {"skuName": queryString, "skuIsConsumable": 1};
                dcomethealth.MasterResource.searchSkus(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data) {
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
                    }
                });
            },
            minLength: 3
        });
    }
    function autocompletePostDrug() {
        var skuName;
        $("#" + id + " input[name='postOpDrugName']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                var Auto = this;
                $("#matReqhPostOpCheck").val(3);
                serviceClearVar = 1;
                autoIdSetpostDrug(dynTableGetNodeInRow(this, 'postOpDrugRID'));
                dynTableGetNodeInRow(this, 'postOpDrugRID').value = ui.item.data.id;
                var y = dynTableGetNodeInRow(this, 'postOpDrugRID').value;
                dynTableGetNodeInRow(this, 'postOpDrugPrice').value = ui.item.data.skuLastMrp;
                var row = dynTableGetNodeInRow(this);
                checkExistpostDrug(y, row);
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
                serviceClearVar = 0;
                var searchParams = {"skuName": queryString, "skuIsConsumable": 1};
                dcomethealth.MasterResource.searchSkus(searchParams, function (data) {
                    if (!!data) {
//                        serviceClearVar = 1;
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
                    }
                });
            },
            minLength: 3
        });
    }
    function autocomplete() {
        $("#" + id + " input[name='mrnSearch']").autocomplete({
            select: function (event, ui) {
                $("#patName,#patMobile,#patUhid").removeClass("hidden");
                $('#patName').text(ui.item.data.patFullName);
                $('#prPatientRid').val(ui.item.data.id);
                $('#prPatDob').text(ui.item.data.patDob);
                $('#patAge').text(ui.item.data.patAge);
                $('#patGender').text(ui.item.data.patGender);
                $('#prPatAddress').text(ui.item.data.patAddress);
                $('#patMobile').text(ui.item.data.patPhoneNo);
                $('#patUhid').text(ui.item.data.patMrnNo);
                $("#patDetails").removeClass("hidden");
            }, open: function (event, ui) {
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
    function autocompleteProcedure() {
        $("#" + id + " input[name='procedureSearch']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                $("#procedureRid").val(ui.item.data.id);
                autoClearPR();
                $("#prCategory").val(ui.item.data.bsServiceProcedureCategory);
                $("#prPrice").val(ui.item.data.bPrice);
                categorySelect();
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
    function addAdvance(elem) {
        var table = document.getElementById('pmd_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length; i++) {
            if (table_length == 1) {
                dynTableGetNodeInRow(table.rows[i], 'pmd_amount').value = elem.value;
            }
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
    function submit(boRID, boCode, actionCode) {
        var proRequestObj = {}, procedureReqList = [], procedureAttendDoctorList = [], serviceRequestHList = [], drugRequestHList = [], procedureNurseList = [], procedureTechnicianList = [], procedureAnesthesisList = [];
        var checkServiceIntraOp = false, checkDrugIntraOp = false;
        var billHGross = 0;
        if (!!$("#prId").val()) {
            proRequestObj.id = $("#prId").val();
        }
        proRequestObj.prType = $("#prType").val();
        proRequestObj.prCategory = $("#prCategory").val();
        proRequestObj.prPatientRid = $("#prPatientRid").val();
        proRequestObj.prPatientName = $("#patName").text();
        proRequestObj.prOtRoomRid = !!$("#otRoom").val() && !!$("#otRoom option:selected").text() ? $("#otRoom").val() : null;
        proRequestObj.prOtRoomName = $("#otRoom option:selected").text();
        if (!!$("#procedureRid").val()) {
            proRequestObj.prProcedureRid = $("#procedureRid").val();
        }
        if ($("#prDraftBillId").val() !== "") {
            proRequestObj.prDraftBillId = $("#prDraftBillId").val();
        }
        proRequestObj.prPrimaryProcedure = $("#procedureSearch").val();
        proRequestObj.prPrimaryDoctorRid = $("#primaryDoctor").val();
        if (!prCatgryIsMinor && actionCode != "REQUEST") {
            proRequestObj.prSurgeryDateTime = moment($("#scheduleDatetime").val(), 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY HH:mm:ss');
            proRequestObj.prEstimatedDuration = $("#estimetdDuration").val();
            proRequestObj.prEstimatedDurationMinIndex = $("#prEstimatedDurationMinIndex").val();
        }
        proRequestObj.prInstructions = $("#prInstructions").val();
        proRequestObj.prRemarks = $("#prRemarks").val();
        proRequestObj.prOtComplications = $("#prOtComplications").val();
        proRequestObj.postOtComplications = $("#postOtComplications").val();
        proRequestObj.prPostInstructions = $("#prPostInstructions").val();
        proRequestObj.prSafeSurgeryChecklist = !!CKEDITOR.instances.safeSurCheckList.getData() ? CKEDITOR.instances.safeSurCheckList.getData() : null;
        proRequestObj.prAnesthesistRequired = $("#prAnesthesistRequired").is(":checked") ? 1 : 0;
        if (!!$("#prAnesthesistType").val()) {
            proRequestObj.prAnesthesistType = $("#prAnesthesistType").val();
        }
        if (!!$("#prVisitRid").val()) {
            proRequestObj.prVisitRid = $("#prVisitRid").val();
        }
        proRequestObj.prFollowUp = $("#prFollowUp").val();
        proRequestObj.prEdIndexText = $("#prEstimatedDurationMinIndex option:selected").text();
        if ($("#transplantRequested").is(":checked")) {
            proRequestObj.prIsTransplantRequested = 1;
            proRequestObj.prTransplantNotes = $("#tranNotes").val();
        } else {
            proRequestObj.prIsTransplantRequested = 0;
        }
        if ($("#bloodRequested").is(":checked")) {
            proRequestObj.prIsBloodRequested = 1;
            proRequestObj.prBloodNotes = $("#bloodNotes").val();
        } else {
            proRequestObj.prIsBloodRequested = 0;
        }
        $("#s2id_attDoctor").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
            var valid = true, serviceRid = null, attndocMap1 = {};
            var current = $(this);
            var currentName = $(this).text();
            var selectedId = $(current).find("input[type='hidden']").val();
            console.log(idArrayDoctor);
            $.each(idArrayDoctor, function (indx, docRid) {
                var attnDocMap = {};
                if (!!currentName) {
                    if (selectedId == docRid.procedureAttDoctorRid) {
                        attnDocMap.id = docRid.id;
                        attnDocMap.procedureAttDoctorRid = docRid.procedureAttDoctorRid;
                        attnDocMap.procedureRid = docRid.procedureRid;
                        procedureAttendDoctorList.push(attnDocMap);
                        valid = false;
                    }
                }
            });
            if (valid) {
                attndocMap1.procedureAttDoctorRid = selectedId;
                procedureAttendDoctorList.push(attndocMap1);
            }
        });
        $("#s2id_nurses").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
            var valid = true, serviceRid = null, attnNurseMap1 = {};
            var current = $(this);
            var currentName = $(this).text();
            var selectedId = $(current).find("input[type='hidden']").val();
            $.each(idArrayNurse, function (ndx, nurseRid) {
                var nurseMap = {};
                if (!!currentName) {
                    if (selectedId == nurseRid.procNurseRid) {
                        nurseMap.id = nurseRid.id;
                        nurseMap.procNurseRid = nurseRid.procNurseRid;
                        nurseMap.procRid = nurseRid.procRid;
                        procedureNurseList.push(nurseMap);
                        valid = false;
                    }
                }
            });
            if (valid) {
                attnNurseMap1.procNurseRid = selectedId;
                procedureNurseList.push(attnNurseMap1);
            }
        });
        $("#s2id_technician").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
            var valid = true, serviceRid = null, attnTechMap1 = {};
            var current = $(this);
            var currentName = $(this).text();
            var selectedId = $(current).find("input[type='hidden']").val();
            $.each(idArrayTech, function (tndx, tech) {
                var techMap = {};
                if (!!currentName) {
                    if (selectedId == tech.procedureTechRid) {
                        techMap.id = tech.id;
                        techMap.procedureTechRid = tech.procedureTechRid;
                        techMap.procedureRid = tech.procedureRid;
                        procedureTechnicianList.push(techMap);
                        valid = false;
                    }
                }
            });
            if (valid) {
                attnTechMap1.procedureTechRid = selectedId;
                procedureTechnicianList.push(attnTechMap1);
            }
        });
        $("#s2id_anesthes").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
            var valid = true, serviceRid = null, attnAnesMap1 = {};
            var current = $(this);
            var currentName = $(this).text();
            var selectedId = $(current).find("input[type='hidden']").val();
            $.each(idArrayAnesthesist, function (adx, anes) {
                var anesMap = {};
                if (!!currentName) {
                    if (selectedId == anes.procedureAnesthesRid) {
                        anesMap.id = anes.id;
                        anesMap.procedureAnesthesRid = anes.procedureAnesthesRid;
                        anesMap.procedureRid = anes.procedureRid;
                        procedureAnesthesisList.push(anesMap);
                        valid = false;
                    }
                }
            });
            if (valid) {
                attnAnesMap1.procedureAnesthesRid = selectedId;
                procedureAnesthesisList.push(attnAnesMap1);
            }
        });
        if ($("#serReqhPreOpCheck").val() == 1) { //Pre OP Service Order 
            var serviceRequestH = {}, serviceList = [];
            if (!!$("#serReqhPreOpRid").val()) {
                serviceRequestH.serReqhId = $("#serReqhPreOpRid").val();
            }
            serviceRequestH.serReqOpVisitRid = !!$("#prVisitRid").val() ? $("#prVisitRid").val() : 0;
            serviceRequestH.serReqhPatRid = $("#prPatientRid").val();
            serviceRequestH.serReqhPatMrn = $("#mrnSearch").val();
            serviceRequestH.serReqhPatName = $("#patName").text();
            serviceRequestH.serReqhPatRid = $("#prPatientRid").val();
            serviceRequestH.serReqhOpCheck = 1;
            serviceRequestH.serReqhBillHRid = !!$("#prDraftBillId").val() ? $("#prDraftBillId").val() : 0;
            var table = document.getElementById('pre_op_service_dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var serviceD = {};
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceDRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceDRid').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceDRid').value != 0) {
                    serviceD.serReqRid = dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceDRid').value;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceRID').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceRID').value != 0) {
                    serviceD.serReqItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceRID').value;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceName').value && dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceDRid').value == 0) {
                    serviceD.serReqItemName = dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceName').value;
                    serviceD.serReqItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'preOpServiceQty').value;
                    serviceD.serReqItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'preOpServicePrice').value;
                    serviceList.push(serviceD);
                }
            }
            serviceRequestH.serviceRequest = serviceList;
            serviceRequestHList.push(serviceRequestH);
        }

        if ($("#matReqhPreOpCheck").val() == 1) {//Pre OP Material Req
            var matRequestHPreObj = {}, preMaterialList = [];
            matRequestHPreObj.drugReqHPatMrn = $("#mrnSearch").val();
            matRequestHPreObj.drugReqHOpVBisitRID = !!$("#prVisitRid").val() ? $("#prVisitRid").val() : 0;
            matRequestHPreObj.drugReqHPatName = $("#patName").text();
            matRequestHPreObj.drugReqHPatRid = $("#prPatientRid").val();
            matRequestHPreObj.drugReqHOpCheck = 1;
            matRequestHPreObj.drugReqHBillHRID = !!$("#prDraftBillId").val() ? $("#prDraftBillId").val() : 0;
            var table = document.getElementById('pre_op_drug_dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var drugD = {};
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugRID').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugRID').value != 0) {
                    drugD.drugReqDItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugRID').value;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugName').value && dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugDRid').value == 0) {
                    drugD.drugReqDItemName = dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugName').value;
                    drugD.drugReqDItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'preOpQtyDrug').value;
                    drugD.drugReqDItemBalanceQty = dynTableGetNodeInRow(table.rows[i + 1], 'preOpQtyDrug').value;
                    drugD.drugReqDItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'preOpDrugPrice').value;
                    drugD.drugReqDItemIssuedQty = 0;
                    preMaterialList.push(drugD);
                }
            }
            matRequestHPreObj.drugRequestDList = preMaterialList;
            drugRequestHList.push(matRequestHPreObj);
        }

        if ($("#serReqhIntraOpCheck").val() == 2) { //Intra OP Service Order    
            var billH = {};
            if ($("#prVisitRid").val() !== "") {
                billH.bhPatientVisitRID = $("#prVisitRid").val();
            }
            billH.bhProcedureIntraService = 1;
            if ($("#prDraftBillId").val() !== "") {
                billH.id = $("#prDraftBillId").val();
            }
            if ($("#prPatientRid").val() !== "") {
                billH.bhPatientRID = $("#prPatientRid").val();
            }
            if (!!$('#bhIsDraft').val()) {
                billH.bhIsDraft = $('#bhIsDraft').val();
            }
            if (!!$("#primaryDoctor").val()) {
                billH.bhDocRefRID = $("#primaryDoctor").val();
            }
            billH.bhBaseCurRid = dcomethealth.sCurrencyRid;
            billH.bhUnitRID = dcomethealth.selectedunit; // user selected unit;

            checkServiceIntraOp = true;
            var billDList = [];
            var table = document.getElementById('intra_op_service_dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var billD = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'intraOpSerReqRid').value == 0 && (!!dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceRID').value != 0)) {
                    intraService = true;
                    billD.bdItemType = "Service";
                    billD.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceName').value;
                    billD.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceRID').value;
                    billD.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceQty').value;
                    billD.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServicePrice').value;
                    billD.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceQty').value * dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServicePrice').value;
                    billD.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServiceQty').value * dynTableGetNodeInRow(table.rows[i + 1], 'intraOpServicePrice').value;
                    billD.bdDiscountAmount = 0;
                    billHGross += billD.bdGrossAmount;
                    billDList.push(billD);
                }
            }
            billH.bhGrossAmount = billHGross;
            billH.bhNetAmount = billHGross;
            billH.billD = billDList;
        }
        if (isBedchargeAdd == 1) { //Intra OP Service Order    
            var billHB = {};
            if ($("#prVisitRid").val() !== "") {
                billHB.bhPatientVisitRID = $("#prVisitRid").val();
            }
            if ($("#prDraftBillId").val() !== "") {
                billHB.id = $("#prDraftBillId").val();
            }
            if ($("#patientRID").val() !== "") {
                billHB.bhPatientRID = $("#prPatientRid").val();
            }
            if (!!$('#bhIsDraft').val()) {
                billHB.bhIsDraft = $('#bhIsDraft').val();
            }
            if (!!$("#primaryDoctor").val()) {
                billHB.bhDocRefRID = $("#primaryDoctor").val();
            }
            billHB.bhBaseCurRid = dcomethealth.sCurrencyRid;
            billHB.bhUnitRID = dcomethealth.selectedunit; // user selected unit;


            var billDList = [];
            var billD = {};
            billD.bdItemType = "Service";
            billD.bdItemName = bedObj.bcServiceName;
            billD.bdItemRID = bedObj.bcServiceRid;
            billD.bdQty = bedObj.bcDaysCount;
            billD.bdPrice = bedObj.bcServicePrice;
            billD.bdGrossAmount = bedObj.bcGrossAmount;
            billD.bdNetAmount = bedObj.bcGrossAmount;
            billD.bdDiscountAmount = 0;
            billDList.push(billD);
            billHB.bhGrossAmount = bedObj.bcGrossAmount;
            billHB.bhNetAmount = bedObj.bcGrossAmount;
            billHB.billD = billDList;
        }
        if ($("#matReqhIntraOpCheck").val() == 2) {//Intra OP Material Order
            var billHDrug = {};
            if ($("#prVisitRid").val() !== "") {
                billHDrug.bhPatientVisitRID = $("#prVisitRid").val();
            }
            billHDrug.bhProcedureIntraMaterial = 1;
            if ($("#prDraftBillId").val() !== "") {
                billHDrug.id = $("#prDraftBillId").val();
            }
            if ($("#prPatientRid").val() !== "") {
                billHDrug.bhPatientRID = $("#prPatientRid").val();
            }
            if (!!$('#bhIsDraft').val()) {
                billHDrug.bhIsDraft = $('#bhIsDraft').val();
            }
            billHDrug.bhBaseCurRid = dcomethealth.sCurrencyRid;
            billHDrug.bhUnitRID = dcomethealth.selectedunit; // user selected unit;

            checkDrugIntraOp = true;
            var billDDrugList = [], billHGross1 = 0;
            var table = document.getElementById('intra_op_drug_dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                if (dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugDRID').value == 0 && (!!dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugRID').value != 0)) {
                    intraMaterial = true;
                    var billDDrug = {};
                    billDDrug.bdItemType = "Drug";
                    billDDrug.bdItemName = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugName').value;
                    billDDrug.bdItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugRID').value;
                    billDDrug.bdQty = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugQty').value;
                    billDDrug.bdPrice = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugPrice').value;
                    billDDrug.bdGrossAmount = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugQty').value * dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugPrice').value;
                    billDDrug.bdNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugQty').value * dynTableGetNodeInRow(table.rows[i + 1], 'intraOpDrugPrice').value;
                    billDDrug.bdDiscountAmount = 0;
                    billHGross += billDDrug.bdGrossAmount;
                    billHGross1 += billDDrug.bdGrossAmount;
                    billDDrugList.push(billDDrug);
                    if (actionCode == "COMPLETE" && (!$("#prDraftBillId").val() || $("#prDraftBillId").val() == 0)) {
                        billDList.push(billDDrug);
                    }
                }
            }
            billHDrug.bhGrossAmount = billHGross1;
            billHDrug.bhNetAmount = billHGross1;
            billHDrug.billD = billDDrugList;
            if (actionCode == "COMPLETE" && (!$("#prDraftBillId").val() || $("#prDraftBillId").val() == 0)) {
                billH.billD = billDList;
            }
        }
        //-----Advance Save----
        if (!$("#prId").val()) {
            if (!$("#total_pmd").val() && $("#total_pmd").val() == 0) {
                return false;
            }
            var advance = {};
            if ($("#advanceRID").val() !== "") {
                advance.id = $("#advanceRID").val();
            }
            advance.adType = 0;
            advance.adPayerRID = $("#prPatientRid").val();
            advance.adPayerNo = $("#patMobile").text(); // need to verify mrn or patient no
            advance.adPayerName = $("#patName").text();
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
        }
        if ((!$("#prVisitRid").val() && prCatgryIsMinor)) {
            var visit = {};
            visit.visTypeIndex = 19;
            visit.visSubTypeIndex = 0; // Need to change the value;
            visit.visPatType = 0; // Need to check and change it.
            visit.visPatRid = $("#prPatientRid").val();
            visit.visApptRid = 0; // passing 0 because its a direct OP registration
            visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
            visit.visConsDocRid = $("#primaryDoctor").val();
            visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different. 
            var search = 0 + "/" + "VISIT" + "/" + "BUILTIN_ACTION";
            dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                dcomethealth.PatientResource.searchActiveIpVisit({"visPatRid": data}, function (visit) {
                    if (!!$("#prVisitRid").val()) {
                        proRequestObj.prVisitRid = $("#prVisitRid").val();
                    } else {
                        proRequestObj.prVisitRid = visit.id;
                        $("#prVisitRid").val(visit.id);
                    }
                    if ((((checkServiceIntraOp && intraService) && (checkDrugIntraOp && intraMaterial)) || ((!checkServiceIntraOp && !intraService) && (checkDrugIntraOp && intraMaterial)) || ((checkServiceIntraOp && intraService) && (!checkDrugIntraOp && !intraMaterial))) && (actionCode == "COMPLETE")) {
                        billH.bhPatientVisitRID = visit.id;
                        billH.bhProcedureIntraMaterial = 1;
                        billH.bhIsDraft = 1;
                        billH.bhType = 2; // IP bill type : 1;
                        billH.bhPatientName = $("#patName").text();
                        billH.bhPatientNo = $("#patMobile").text();
                        billH.bhPaidAmount = 0;
                        billH.bhEligibleAmount = 0;
                        billH.bhGrossAmount = billHGross;
                        billH.bhNetAmount = billHGross;
                        var args = 0 + "/" + "BILL" + "/" + "REQUEST";
                        dcomethealth.BillingResource.saveBill(billH, args)
                                .done(function (data, textStatus, jqXHR) {
                                }).fail(function (jqXHR, textStatus, errorThrown) {
                        });
                    }
                });
            }).fail(function (jqXHR, textStatus, errorThrown) {
            });
        }
        setTimeout(function () {
            if (actionCode == "COMPLETE") {
                var billHC = {};
                if ($("#prVisitRid").val() !== "") {
                    billHC.bhPatientVisitRID = $("#prVisitRid").val();
                }
                if ($("#prDraftBillId").val() !== "") {
                    billHC.id = $("#prDraftBillId").val();
                }
                if ($("#patientRID").val() !== "") {
                    billHC.bhPatientRID = $("#prPatientRid").val();
                }
                if (!!$('#bhIsDraft').val()) {
                    billHC.bhIsDraft = $('#bhIsDraft').val();
                }
                if (!!$("#primaryDoctor").val()) {
                    billHC.bhDocRefRID = $("#primaryDoctor").val();
                }
                if ((!checkDrugIntraOp && !intraMaterial) && (!checkServiceIntraOp && !intraService)) {
                    billHC.bhIsDraft = 1;
                    billHC.bhType = 2; // IP bill type : 1;
                    billHC.bhPatientName = $("#patName").text();
                    billHC.bhPatientNo = $("#patMobile").text();
                    billHC.bhPaidAmount = 0;
                    billHC.bhEligibleAmount = 0;
                    billHC.bhGrossAmount = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                    billHC.bhNetAmount = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                } else {
                    billHC.bhGrossAmount = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                    billHC.bhNetAmount = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                }
                billHC.bhBaseCurRid = dcomethealth.sCurrencyRid;
                billHC.bhUnitRID = dcomethealth.selectedunit; // user selected unit;
                var billDList = [];
                var billD = {};
                billD.bdItemType = "Service";
                billD.bdItemName = $("#procedureSearch").val();
                billD.bdItemRID = $("#procedureRid").val();
                billD.bdQty = 1;
                billD.bdPrice = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                billD.bdGrossAmount = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                billD.bdNetAmount = isNaN($("#prPrice").val()) || $("#prPrice").val() == 0 ? 0 : parseFloat($("#prPrice").val());
                billD.bdDiscountAmount = 0;
                billDList.push(billD);
                billHC.billD = billDList;
            }
            if ($("#serReqhPostOpCheck").val() == 3) { //Post OP Service Order
                var serviceRequestHPostObj = {}, serviceListArray = [];
                if (!!$("#serReqhPostOpRid").val()) {
                    serviceRequestHPostObj.serReqhId = $("#serReqhPostOpRid").val();
                }
                postService = true;
                serviceRequestHPostObj.serReqOpVisitRid = !!$("#prVisitRid").val() ? $("#prVisitRid").val() : 0;
                serviceRequestHPostObj.serReqhPatMrn = $("#mrnSearch").val();
                serviceRequestHPostObj.serReqhPatName = $("#patName").text();
                serviceRequestHPostObj.serReqhBillHRid = !!$("#prDraftBillId").val() ? $("#prDraftBillId").val() : 0;
                serviceRequestHPostObj.serReqhPatRid = $("#prPatientRid").val();
                serviceRequestHPostObj.serReqhOpCheck = 3;
                var table = document.getElementById('post_op_service_dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var serviceD = {};
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceDRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceDRid').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceDRid').value != 0) {
                        serviceD.serReqRid = dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceDRid').value;
                    }
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceRID').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceRID').value != 0) {
                        serviceD.serReqItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceRID').value;
                    }
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceName').value && dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceDRid').value == 0) {
                        serviceD.serReqItemName = dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceName').value;
                        serviceD.serReqItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'postOpServiceQty').value;
                        serviceD.serReqItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'postOpServicePrice').value;
                        serviceListArray.push(serviceD);
                    }
                }
                serviceRequestHPostObj.serviceRequest = serviceListArray;
                serviceRequestHList.push(serviceRequestHPostObj);
            }
            if ($("#matReqhPostOpCheck").val() == 3) {//Post OP Material Req
                var matRequestHPostObj = {}, materialListArray = [];
                postMaterial = true;
                matRequestHPostObj.drugReqHPatMrn = $("#mrnSearch").val();
                matRequestHPostObj.drugReqHOpVBisitRID = !!$("#prVisitRid").val() ? $("#prVisitRid").val() : 0;
                matRequestHPostObj.drugReqHPatName = $("#patName").text();
                matRequestHPostObj.drugReqHPatRid = $("#prPatientRid").val();
                matRequestHPostObj.drugReqHOpCheck = 3;
                matRequestHPostObj.drugReqHBillHRID = !!$("#prDraftBillId").val() ? $("#prDraftBillId").val() : 0;
                var table = document.getElementById('post_op_drug_dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var drugD = {};
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugRID').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugRID').value != 0) {
                        drugD.drugReqDItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugRID').value;
                    }
                    if (!!dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugName').value && dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugDRid').value == 0) {//                   
                        drugD.drugReqDItemName = dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugName').value;
                        drugD.drugReqDItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugQty').value;
                        drugD.drugReqDItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugPrice').value;
                        drugD.drugReqDItemBalanceQty = dynTableGetNodeInRow(table.rows[i + 1], 'postOpDrugQty').value;
                        drugD.drugReqDItemIssuedQty = 0;
                        materialListArray.push(drugD);
                    }
                }
                matRequestHPostObj.drugRequestDList = materialListArray;
                drugRequestHList.push(matRequestHPostObj);
            }
            proRequestObj.procedureAttendDoctorList = procedureAttendDoctorList;
            proRequestObj.procedureNurseList = procedureNurseList;
            proRequestObj.procedureTechnicianList = procedureTechnicianList;
            proRequestObj.procedureAnesthesistList = procedureAnesthesisList;
            proRequestObj.serviceRequestH = serviceRequestHList;
            proRequestObj.drugRequestHs = drugRequestHList;
            if (isBedchargeAdd == 1) {
                proRequestObj.bedChargeVo = bedObj;
            }
//            alert((checkServiceIntraOp && intraService) && (!!$("#prDraftBillId").val() && $("#prDraftBillId").val() != 0));
//            alert((checkDrugIntraOp && intraMaterial) && (!!$("#prDraftBillId").val() && $("#prDraftBillId").val() != 0));
//            console.log(billHDrug);
//            return false;
            if (!$("#prVisitRid").val() && actionCode == "ADMIT") {//for create ip visit backend (proReqobj set inside the visit obj)
                var visit = {};
                visit.visTypeIndex = 19;
                visit.visSubTypeIndex = 0; // Need to change the value;
                visit.visPatType = 0; // Need to check and change it.
                visit.visPatRid = $("#prPatientRid").val();
                visit.visApptRid = 0; // passing 0 because its a direct OP registration
                visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
                visit.visConsDocRid = $("#primaryDoctor").val();
                visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.                
                proRequestObj.prActionCode = "Admit";
                procedureReqList.push(proRequestObj);
                visit.procedureRequestHs = procedureReqList;
                var search = 0 + "/" + "VISIT" + "/" + "BUILTIN_ACTION";
                dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                    dcomethealth.util.loadpage('ProcedureRequest');
                });
            } else {
                var args = boRID + "/" + boCode + "/" + actionCode;
                dcomethealth.ProcedureRequestResource.saveProcedureRequest(proRequestObj, args).done(function (data, textStatus, jqXHR) {
                    alert("Saved");
                    if (!$("#prId").val() && !!$("#prPatientRid").val()) {
                        advance.adPayerRID = $("#prPatientRid").val();
                        advance.paymentModeDetails = advanceList;
                        var arg = 0 + "/" + "ADVANCE" + "/" + "CREATE_ADVANCE";
                        dcomethealth.AdvanceResource.saveAdvance(advance, arg).done(function (data, textStatus, jqXHR) {
                            $("#advanceRID").val(data);
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                        });
                    }
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                    dcomethealth.util.loadpage('ProcedureRequest');
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
            }

            setTimeout(function () {
                if (isBedchargeAdd == 1 && $("#prCategory").val() != 1) {
                    dcomethealth.BillingResource.saveDraftBill(billHB)
                            .done(function (data, textStatus, jqXHR) {
                            }).fail(function (jqXHR, textStatus, errorThrown) {
                    });
                }
            }, 200);
            setTimeout(function () {
                if ((checkServiceIntraOp && intraService) && (!!$("#prDraftBillId").val() && $("#prDraftBillId").val() != 0)) {//&& actionCode != "COMPLETE"
                    dcomethealth.BillingResource.saveDraftBill(billH)
                            .done(function (data, textStatus, jqXHR) {
                            }).fail(function (jqXHR, textStatus, errorThrown) {
                    });
                }
            }, 200);
            setTimeout(function () {
                if ((checkDrugIntraOp && intraMaterial) && (!!$("#prDraftBillId").val() && $("#prDraftBillId").val() != 0)) {//&& actionCode != "COMPLETE"
                    alert("intraMaterial");
                    dcomethealth.BillingResource.saveDraftBill(billHDrug)
                            .done(function (data, textStatus, jqXHR) {
                            }).fail(function (jqXHR, textStatus, errorThrown) {
                    });
                }
            }, 200);
            setTimeout(function () {
                if ((!$("#prDraftBillId").val() || $("#prDraftBillId").val() == 0) && actionCode == "COMPLETE") {
                    var args = 0 + "/" + "BILL" + "/" + "REQUEST";
                    billHC.bhActionCode = "REQUEST";
                    dcomethealth.BillingResource.saveBillWithCondition(billHC).done(function (data, textStatus, jqXHR) {
                        if (checkServiceIntraOp && intraService) {
                            billH.id = data.id;
                            dcomethealth.BillingResource.saveDraftBill(billH)
                                    .done(function (data, textStatus, jqXHR) {
                                    }).fail(function (jqXHR, textStatus, errorThrown) {
                            });
                        }
                        if (checkDrugIntraOp && intraMaterial) {
                            billHDrug.id = data.id;
                            dcomethealth.BillingResource.saveDraftBill(billHDrug)
                                    .done(function (data, textStatus, jqXHR) {
                                    }).fail(function (jqXHR, textStatus, errorThrown) {
                            });
                        }
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                    });
                } else if ((!!$("#prDraftBillId").val() || $("#prDraftBillId").val() != 0) && actionCode == "COMPLETE") {
                    dcomethealth.BillingResource.saveDraftBill(billHC).done(function (data, textStatus, jqXHR) {
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                    });
                }
            }, 200);
            setTimeout(function () {

            }, 200);
        }, 1000);
    }
    function fetchProcedureList() {
        var search = {};
        dcomethealth.ProcedureRequestResource.getProcedureRequest(search, function (data) {
            $("#tbodyHead").empty();
            if (!!data) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $.each(procedureData = data, function (idx, s_procedure) {
                    if (!s_procedure.prPatientName) {
                        s_procedure.prPatientName = "---";
                    }
                    $("#tbodyHead").append('<tr  id="tr_head">\n\
                    <td>' + s_procedure.prPatientName + '</td><td>' + s_procedure.prPrimaryProcedure + '</td></tr>');
                });
                $("#ProcedureDetailsTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $('#ProcedureDetailsTable').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }

    function refreshData() {
    }
    return {
        init: init,
        beforeActionValidation: beforeActionValidation,
        autocompletePreService: autocompletePreService,
        autocompleteIntraService: autocompleteIntraService,
        autocompletePostService: autocompletePostService,
        autocompletePreDrug: autocompletePreDrug,
        autocompleteIntraDrug: autocompleteIntraDrug,
        autocompletePostDrug: autocompletePostDrug,
        autocompleteProcedure: autocompleteProcedure,
        autocomplete: autocomplete,
        categorySelect: categorySelect,
        getProcedureRequest: getProcedureRequest,
        checkExistPreService: checkExistPreService,
        checkAvailability: checkAvailability,
        searchPostalCode: searchPostalCode,
        submit: submit,
        setMaxLength: setMaxLength,
        printSummary: printSummary,
        autoIdSetpre: autoIdSetpre,
        loadWorkList: loadWorkList,
        autoIdSetintra: autoIdSetintra,
        autoIdSetpost: autoIdSetpost,
        autoClearPR: autoClearPR,
        addAdvance: addAdvance,
        autoIdSetpreDrug: autoIdSetpreDrug,
        autoIdSetintraDrug: autoIdSetintraDrug,
        autoIdSetpostDrug: autoIdSetpostDrug,
        fetchProcedureList: fetchProcedureList,
        refreshData: refreshData
    };
}());
dcomethealth.ProcedureRequest.init();