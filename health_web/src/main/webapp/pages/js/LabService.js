var dcomethealth = dcomethealth || {};
dcomethealth.LabService = (function () {
    var id = "LabService", labResult = {}, labHRid, profileResultCheck = false;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().startOf('days'), end = moment().endOf('days');
            $('#labDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            searchSOByDate();

            if (parseInt(dcomethealth.commonInit) !== 0) {
                var row = "";
                $("#btns_state").empty();
                var categories = [];
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    if ($.inArray(datalist.boaName, categories) === -1) {
                        categories.push(datalist.boaName);
                        row += '<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>';
                    }
                });
                $("#btns_state").append('<a href="#" class="btn btn-inverse" onclick="dcomethealth.util.loadWorklist(dcomethealth.bowBowcRid);" style="margin-top: 3px; margin-bottom: 3px;"><span>Back</span></a><span></span>' + row);
                var boRID = dcomethealth.boRID;
                approvalState(boRID);
            }
            //            dcomethealth.DataDictionaryResource.getSysParam({"paramCode": "LAB_BO"}, function (data) {
//                console.log(data);
//                if (!!data) {

            $("#APPROVE").off("click").on("click", function () {
                submit(boRID, "LAB_APPROVAL", "Approve");
            });
            $("#REJECT").on("click", function () {
                submit(boRID, "LAB_APPROVAL", "Reject");
            });
            $("#labSave").text("Save");
            $("#cancelBtn").removeClass("hidden");
            if (dcomethealth.loginuser.entityRid == 5) {
            } else {
                $("#mailTh").remove();
            }
            $('#labDateRange').daterangepicker({
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
                $('#labDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                searchSOByDate();
            });
            $('#labComDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $('#labComDateRange').daterangepicker({
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
                $('#labComDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                searchCompleted();
//                $("#completedBtn").on("click", function () {
//                    searchCompleted();
//                });
//                $("#cancelBtn").on("click", function () {
//                    searchCancelledOrder();
                //                });
            });
            searchDoctor();
            searchLabserviceExtn();
            $("#labSave").off("click").on("click", function () {
                saveLabServices();
            });
        });
    }
    function searchLabserviceExtn() {
        dcomethealth.LaboratoryResource.searchLabServiceExtn({}, function (data) {
            dcomethealth.LabServiceExtnData = data;
        });
    }
    function searchCancelledOrder() {
        $("#labDateRange").hide();
        $("#labComDateRange").show();
        $("#labDateRangeSpan").addClass("hidden");
        $("#labComDateRangeSpan").removeClass("hidden");
        $("#statusTH").text("Status");
        $("#apTbody").empty();
        var date = $('#labComDateRangeSpan').html().split('-');
        var searchParam = {"lrhFromDate": moment(date[0]), "lrhToDate": moment(date[1]).add(1, 'days'), "lrhState": 3};
        dcomethealth.LaboratoryResource.searchLabResults(searchParam, function (data) {
            if (!!data) {
//                var tables = $.fn.dataTable.fnTables(true);
//                $(tables).each(function () {
//                    $(this).dataTable().fnClearTable();
//                    $(this).dataTable().fnDestroy();
                //                });
                $("#apTbody").empty();
                $.each(labResult = data, function (Idx, lab) {
                    $("#PatientVisitRid").val(lab.lrhPatientVisitID);
                    var stateVal = "Canceled";
//                var Print = '<a class="btn btn-success hidden-xs jqueryUIToolTip" title="Print" onclick="dcomethealth.LabService.check(' + lab.id + ')"><i class="fa fa-print"></i></a>';
                    //                var eMail = '<a class="btn btn-success hidden-xs jqueryUIToolTip" title="Send Email" onclick="dcomethealth.LabService.sendEmail(' + lab.id + ',' + lab.lrhPatientVisitID + ')"><i class="fa fa-envelope"></i></a>';
                    var mrn = '<a href="#" onclick="dcomethealth.LabService.showCompltedResult( ' + lab.lrhPatientID + ',' + lab.id + ')">' + lab.lrhPatientMrn + '</a>';
                    $("#apTbody").append('<tr><td>' + mrn + '<input type="hidden" id="apPatRid" value="' + lab.lrhPatientMrn + '"/></td>\n\
                                         <td><output id="service">' + lab.lrhPatientName + '</output></td>\n\
                                        <td><output id="serDate">' + moment(lab.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY') + '</output></td>\n\
                    <td><output id="serviceStatus">' + stateVal + '</output><td></td><td></td></td></tr>');//
                    $("#dyn_table_pat").dataTable();
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
            } else {
//                var tables = $.fn.dataTable.fnTables(true);
//                $(tables).each(function () {
//                    $(this).dataTable().fnClearTable();
//                    $(this).dataTable().fnDestroy();
                //                });
                $("#apTbody").empty();
                $("#dyn_table_pat").dataTable();
                //                $("#dyn_table_pat").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function searchSOByDate() {
        $("#completedBtn").attr("disabled", true);
        $("#cancelBtn").attr("disabled", true);
        $("#labDateRange").show();
        $("#labComDateRange").hide();
        $("#labDateRangeSpan").removeClass("hidden");
        $("#labComDateRangeSpan").addClass("hidden");
        $("#statusTH").text("Status");
        $("#apTbody").empty();
        var date = $('#labDateRangeSpan').html().split('-');
        var soUnitRID = null;
        $.each(dcomethealth.dDictVal, function (ix, dDict) {
            if (dDict.ddictValue == "Laboratory") {
                soUnitRID = dDict.id;
            }
        });
        var searchParamsSO = {"soFromDate": moment(date[0]), "soToDate": moment(date[1]).add(1, 'days'), "soState": 2, "soSerMode": soUnitRID, "sortOrder": ["soState"], "sortDesc": "desc"};//,"soAgainstUnitRID": soUnitRID,
        dcomethealth.CreateOPBilltResource.searchPendingServiceOrders(searchParamsSO, function (data) {
            if (!!data && data.length > 0) {
                $("#apTbody").empty();
                $.each(dcomethealth.soData = data, function (idx, value) {
                    var mrn = '<a href="#" onclick="dcomethealth.LabService.showResult(' + value.id + ',this)">' + value.patMrnNo + '</a>';
                    var Print = "";
                    var stateVal = "Pending";
                    $("#apTbody").append('<tr><td>' + mrn + '<input type="hidden" id="apPatRid" value="' + value.id + '"/></td>\n\
                    <td><output id="service">' + value.patFullName + '</output></td>\n\
                    <td><output id="serDate">' + moment(value.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY') + '</output></td>\n\
                    <td><output id="serviceStatus">' + stateVal + '</output><td>' + Print + '</td><td></td></td></tr>');
                    $("#completedBtn").attr("disabled", false);
                    $("#cancelBtn").attr("disabled", false);
                });
                $("#dyn_table_pat").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $("#apTbody").empty();
                $("#dyn_table_pat").dataTable();//{"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true}
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
                $("#completedBtn").attr("disabled", false);
                $("#cancelBtn").attr("disabled", false);
            }
        });
    }
    function showResult(val, row) {
        $('#btns_state').addClass('hidden');
        $("#LabServiceHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#add_new").removeClass("hidden").addClass("panel panel-primary");
        var i = 0, check = false;
        $.each(dcomethealth.soData, function (pIjx, pat) {
            if (parseInt(pat.id) == parseInt(val)) {
                $("#patMRN").text(pat.patMrnNo);
                $("#patName").text(pat.patFullName);
                var c = pat.patDob.split('-');
                $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                $('#PatGender').val(pat.patGenderIndex);
                if (parseInt(pat.patGenderIndex) === 1) {
                    $('#PatientGend').val('Male');
                } else if (parseInt(pat.patGenderIndex) === 2) {
                    $('#PatientGend').val('Female');
                } else {
                    $('#PatientGend').val('Transgender');
                }
                $("#soTbody").empty();
                $.each(pat.serviceOrders, function (pIdx, soFiltered) {
                    if (soFiltered.soPatientRID == val) {
                        var lsDescription = "", lsGroupName = "", lsUOM = "", labSerTempRid = 0, tempNodes = {}, lsDesc = "", lsGroup = "", lserUom = "";
                        if (!!soFiltered.serviceMaster.labServiceExtnList) {
                            $.each(soFiltered.serviceMaster.labServiceExtnList, function (cIdx, lService) {
                                if (parseInt(lService.lsServiceRID) === parseInt(soFiltered.soItemID)) {
                                    if (parseInt(lService.lsAgeMinValue) <= parseInt($('#patAge').val()) && parseInt($('#patAge').val()) <= parseInt(lService.lsAgeMaxValue)) {
                                        if (parseInt((lService.lsGenderType)) === (parseInt($('#PatGender').val()))) {
                                            lsDescription = lService.lsDescription;
                                            lsGroupName = lService.lsGroupName;
                                            lsUOM = lService.lsUom;
                                            labSerTempRid = lService.lsTemplateRID;
                                            tempNodes = lService.lsTemplateNode;
                                            $("#setDiv").html(tempNodes);
                                            var findTd = $("#setDiv").find("table#printTable1").find("tr");
                                            $("#viewDiv").html(findTd);
                                            tempNodes = $("#viewDiv").html();
                                            return false;
                                        } else if (parseInt((lService.lsGenderType)) !== 1 && parseInt((lService.lsGenderType)) !== 2) {
                                            lsDescription = lService.lsDescription;
                                            lsGroupName = lService.lsGroupName;
                                            lsUOM = lService.lsUom;
                                            labSerTempRid = lService.lsTemplateRID;
                                            tempNodes = lService.lsTemplateNode;
                                            $("#setDiv").html(tempNodes);
                                            var findTd = $("#setDiv").find("table#printTable1").find("tr");
                                            $("#viewDiv").html(findTd);
                                            tempNodes = $("#viewDiv").html();
                                        }
                                    }
                                }
                            });
                        } else {
                            $.each(dcomethealth.serviceMaster, function (cIdx, serviceM) {
                                if (parseInt(serviceM.id) == parseInt(soFiltered.soItemID)) {
                                    if (!!serviceM.profileServiceList) {
//                                        $.each(serviceM.profileServiceList, function (dIdx, serviceMap) {
//                                            $.each(dcomethealth.LabServiceExtnData, function (pIdx, lService) {
                                        //                                                if (parseInt(serviceMap.prServiceRid) == parseInt(lService.lsServiceRID)) {
                                        check = true;
                                    }
//                                            });
//                                        });
                                    //                                    }
                                }
                            });
                        }
                        $("#visitRidIn").val(soFiltered.soVisitRID);
                        $("#patRidIn").val(soFiltered.soPatientRID);
                        if (parseInt(labSerTempRid) != 0 && !!parseInt(labSerTempRid)) {
                            $("#soTbody").append('<tr id="model' + pIdx + '"><td width="30%"><b>\n\
                            <input type="hidden" id="lsResultValue" style="width: 88%; name="lsResultValue" value="nil" class="col-md-11"/>\n\
                            <input type="hidden" id="tempRid" value="' + 1 + '"/>\n\
                            <input type="text" id="patService1" value= "' + soFiltered.soItemName + '" class="col-md-11" readonly style="border: none"/></b><input type="hidden" id="patService" value="' + soFiltered.soItemName + '"/></td>\n\
                            <input type="hidden" id="soRidIn" value="' + soFiltered.id + '"/>\n\
                            <input type="hidden" id="itemRidIn" value="' + soFiltered.soItemID + '"/></td></tr>' + tempNodes);
                            $('#itemIdIn').eq(i).attr('id', soFiltered.soItemID);
                            setLabValue(soFiltered.id, soFiltered.soItemID);
                            return;
                        } else {
                            if (!!lsDescription) {
                                lsUOM = (lsUOM != "" || !!lsUOM) ? lsUOM : "--";
                                lsDescription = (lsDescription == undefined) ? "--" : lsDescription;
                                var onBlurCall = "dcomethealth.LabService.calResult(this)";
                                if (lsDescription == "Negative" || lsDescription == "Negativo" || lsDescription == undefined || lsDescription == "Negativo/Positivo") {
                                    onBlurCall = ""
                                }
                                $("#soTbody").append('<tr id="model2"><td width="30%"><b><input type="text" id="patService" value= "' + soFiltered.soItemName + '" class="col-md-11" readonly style="border: none"/></b>\n\
                               <input type="hidden" id="tempRid" value="' + 2 + '"/><input type="hidden" id="soRidIn" value="' + soFiltered.id + '"/><input type="hidden" id="itemRidIn" value="' + soFiltered.soItemID + '"/></td><td width="15%"><input type="text" id="lsResultValue" name="lsResultValue" style="width: 88%;"  onblur="' + onBlurCall + '" class="col-md-11" placeholder="Enter result"/></td>\n\
                                <td width="15%"><input type="text" id="lsUom" name="lsUom" readonly="true" class="col-md-11" style="border: none" value="' + lsUOM + '"/></td>\n\
                               <td width="20%"><input type="text" id="lsDescription" readonly="true" name="lsDescription" class="col-md-11" style="border: none" value="' + lsDescription + '"/></td>\n\
                            <td width="20%"><input type="text" id="lsState" name="lsState" readonly="true" class="col-md-11" style="border: none"/><input type="hidden" id="lsGroupName" name="lsGroupName" value="' + lsGroupName + '"/><input type="hidden" id="soDate" name="soDate" value="' + dynTableGetNodeInRow(row, 'serDate').value + '"/></td></tr>');
                            } else if (check) {
                                $.each(dcomethealth.serviceMaster, function (index, serviceM) {
                                    if (parseInt(serviceM.id) == parseInt(soFiltered.soItemID)) {
                                        $.each(dcomethealth.dDictVal, function (ifx, val) {
                                            if (serviceM.bsCategory == val.id) {
                                                if (val.ddictValue === "Profile") {
                                                    $("#soTbody").append('<tr id="model"><td width="30%"><b>\n\
                                                    <input type="hidden" id="lsResultValue" style="width: 88%; name="lsResultValue" value="nil" class="col-md-11"/>\n\
                                                    <input type="hidden" id="profile" value=""/><input type="hidden" id="tempRid" value="' + 0 + '"/>\n\
                                                    <input type="text" id="patService1" value= "' + soFiltered.soItemName + '" class="col-md-11" readonly style="border: none"/></b><input type="hidden" id="patService" value="' + soFiltered.soItemName + '"/></td>\n\
                                                    <input type="hidden" id="soRidIn" value="' + soFiltered.id + '"/>\n\
                                                <input type="hidden" id="itemRidIn" value="' + soFiltered.soItemID + '"/></td></tr>');
                                                }
                                            }
                                        });
                                    }
                                });
                                var prArray = [];
                                $.each(dcomethealth.serviceMaster, function (index, serviceM) {
                                    if (parseInt(serviceM.id) == parseInt(soFiltered.soItemID)) {
                                        if (!!serviceM.profileServiceList) {
                                            $.each(serviceM.profileServiceList, function (dIdx, serviceMap) {
                                                var profileObj = {};
                                                if (serviceMap.prIsActive == 1 && serviceM.id == serviceMap.prProfileRid) {
                                                    profileObj["prProfileRid"] = serviceMap.prProfileRid;
                                                    profileObj["prServiceRid"] = serviceMap.prServiceRid;
                                                    profileObj["prServiceName"] = serviceMap.prServiceName;
                                                    profileObj["prIsActive"] = serviceMap.prServiceName;
                                                    prArray.push(profileObj);
                                                }
                                            });
                                        }
                                        $.each(prArray, function (index, serviceMap) {
                                            $.each(dcomethealth.LabServiceExtnData, function (pIdx, lService) {
                                                if (parseInt(lService.lsServiceRID) == parseInt(serviceMap.prServiceRid)) {
                                                    if (parseInt(lService.lsAgeMinValue) <= parseInt($('#patAge').val()) && parseInt($('#patAge').val()) <= parseInt(lService.lsAgeMaxValue)) {
                                                        if (parseInt((lService.lsGenderType)) === (parseInt($('#PatGender').val()))) {
                                                            var onBlurCall = "dcomethealth.LabService.calResult(this)", inputHidden = '';
                                                            if (lService.lsDescription == "Negative" || lService.lsDescription == "Negativo" || lService.lsDescription == undefined || lService.lsDescription == "Negativo/Positivo") {
                                                                onBlurCall = ""
                                                            }
                                                            lService.lsDescription = (lService.lsDescription == undefined) ? "--" : lService.lsDescription;
                                                            if (index == prArray.length - 1) {
                                                                inputHidden = '<input type="hidden" id="prLastRow" value="lastRowOfProfile"/>';
                                                            }
                                                            $("#soTbody").append('<tr id="model"><td width="30%"><input type="text" id="patService" value= "' + serviceMap.prServiceName + '" class="col-md-11" readonly style="border: none"/>\n\
                                                                    <input type="hidden" id="tempRid" value="' + 0 + '"/><input type="hidden" id="profile" value="profileService"/><input type="hidden" id="soRidIn" value="' + soFiltered.id + '"/><input type="hidden" id="itemRidIn" value="' + serviceMap.prServiceRid + '"/>' + inputHidden + '</td><td width="15%"><input type="text" id="lsResultValue" name="lsResultValue" style="width: 88%;" onblur="' + onBlurCall + '" class="col-md-11" placeholder="Enter result"/></td>\n\
                                                                     <td width="15%"><input type="text" id="lsUom" name="lsUom"  class="col-md-11" style="border: none" value="' + lService.lsUom + '"/></td>\n\
                                                                    <td width="20%"><input type="text" id="lsDescription" readonly="true" name="lsDescription" class="col-md-11" style="border: none" value="' + lService.lsDescription + '"/></td>\n\
                                                        <td width="20%"><input type="text" id="lsState" name="lsState" readonly="true" class="col-md-11" style="border: none"/><input type="hidden" id="lsGroupName" name="lsGroupName" value="' + lService.lsGroupName + '"/><input type="hidden" id="soDate" name="soDate" value="' + dynTableGetNodeInRow(row, 'serDate').value + '"/></td></tr>');
                                                        }
                                                    }
                                                }
                                            });
                                        });
                                    }
                                });
                            }
                            else {
                                $("#soTbody").append('<tr id="model2"><td width="30%"><b><input type="text" id="patService" value= "' + soFiltered.soItemName + '" class="col-md-11" readonly style="border: none"/></b>\n\
                            <input type="hidden" id="tempRid" value="' + 0 + '"/><input type="hidden" id="soRidIn" value="' + soFiltered.id + '"/><input type="hidden" id="itemRidIn" value="' + soFiltered.soItemID + '"/></td><td width="15%"><span class="text-warning">Normal Ranges not Available</span><input type="hidden" id="lsResultValue" name="lsResultValue" style="width: 88%;" onblur="dcomethealth.LabService.calResult(this)" class="col-md-11" placeholder="Enter result"/></td></tr>');
                            }
                        }
                        $("#visitRidIn").val(soFiltered.soVisitRID);
                        $("#patRidIn").val(soFiltered.soPatientRID);
                    }
                });
            }
        });
    }
    function searchCompleted() {
        $("#labDateRange").hide();
        $("#labComDateRange").show();
        $("#labDateRangeSpan").addClass("hidden");
        $("#labComDateRangeSpan").removeClass("hidden");
        $("#apTbody").empty();
        var date = $('#labComDateRangeSpan').html().split('-');
        var searchParam = {"lrhFromDate": moment(date[0]), "lrhToDate": moment(date[1]).add(1, 'days'), "lrhState": 2};
        dcomethealth.LaboratoryResource.searchLabResults(searchParam, function (data) {
            if (!!data) {
                $("#apTbody").empty();
                if (dcomethealth.loginuser.entityRid != 5) {
                    $("#statusTH").text("Status");
                } else {
                    $("#statusTH").text("Group");
                }
                var Format = "";
                $.each(labResult = data, function (Indx, lab) {
                    Format = '<select name="printFormat" id="printFormat' + Indx + '" class="row col-md-8"></select>';
                    $("#PatientVisitRid").val(lab.lrhPatientVisitID);
                    var stateVal = "Completed";
                    var Print = '<a class="btn btn-success hidden-xs jqueryUIToolTip" title="Print" onclick="dcomethealth.LabService.check(' + lab.id + ',' + lab.lrhDoctorRid + ',' + Indx + ')"><i class="fa fa-print"></i></a>';
                    if (dcomethealth.loginuser.entityRid == 5) {
                        var eMail = '<a class="btn btn-success hidden-xs jqueryUIToolTip" title="Send Email" onclick="dcomethealth.LabService.sendEmail(' + lab.id + ',' + lab.lrhPatientVisitID + ',' + Indx + ')"><i class="fa fa-envelope"></i></a>';
                    }
                    var mrn = '<a href="#" onclick="dcomethealth.LabService.showCompltedResult( ' + lab.lrhPatientID + ',' + lab.id + ')">' + lab.lrhPatientMrn + '</a>';
                    if (dcomethealth.loginuser.entityRid == 5) {
                        $("#apTbody").append('<tr><td>' + mrn + '<input type="hidden" id="apPatRid" value="' + lab.lrhPatientMrn + '"/></td>\n\
                                         <td><output id="service">' + lab.lrhPatientName + '</output></td>\n\
                                        <td><output id="serDate">' + moment(lab.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY') + '</output></td>\n\
                    <td><output id="serviceStatus">' + Format + '</output><td>' + Print + '</td><td>' + eMail + '</td></td></tr>');//
                    } else {
                        $("#apTbody").append('<tr><td>' + mrn + '<input type="hidden" id="apPatRid" value="' + lab.lrhPatientMrn + '"/></td>\n\
                                         <td><output id="service">' + lab.lrhPatientName + '</output></td>\n\
                                        <td><output id="serDate">' + moment(lab.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY') + '</output></td>\n\
                    <td><output id="serviceStatus">' + stateVal + '</output><td>' + Print + '</td></td></tr>');
                    }
                    $.each(lab.labResultDs, function (Idx, labResultD) {
                        $.each(dcomethealth.dDictVal, function (ifx, val) {
                            if (labResultD.lrdServiceGroupRid == val.id) {
                                $("#printFormat" + Indx).append('<option value="' + val.id + '">' + val.ddictValue + '</option>');
                            }
                        });
                    });
                    $("select option").each(function () { //For remove duplicates
                        $(this).siblings("[value='" + this.value + "']").remove();
                    });

                    $("#dyn_table_pat").dataTable();
                    //                    $("#dyn_table_pat").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
            } else {
                $("#dyn_table_pat").dataTable();//{"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true}
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function approvalState(boRid) {
        $("#labResultId").val(boRid);
        var i = 0, templateNode = {};
        $("#LabServiceHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#add_new").removeClass("hidden").addClass("panel panel-primary");
        $("#buttons").addClass("hidden");
        var searchParam = {"id": boRid};
        dcomethealth.LaboratoryResource.searchLabResults(searchParam, function (data) {
            $.each(data, function (Idx, lab) {
                $('#dateRange').removeClass('hidden');
                $('#date').text(moment(lab.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY'));
                if (parseInt(boRid) == parseInt(lab.id)) {
                    $("#PatientRid").val(lab.lrhPatientID);
                    var searchParams = {"id": parseInt(lab.lrhPatientID)};
                    dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
                        $.each(data, function (pIdx, pat) {
                            $("#patMRN").text(pat.patMrnNo);
                            $("#patName").text(pat.patFullName);
                            var c = pat.patDob.split('-');
                            $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                            $('#PatGender').val(pat.patGenderIndex);
                            if (parseInt(pat.patGenderIndex) === 1) {
                                $('#PatientGend').val('Male');
                            } else if (parseInt(pat.patGenderIndex) === 2) {
                                $('#PatientGend').val('Female');
                            } else {
                                $('#PatientGend').val('Transgender');
                            }
                        });
                    });
                    $("#visitRidIn").val(lab.lrhPatientVisitID);
                    $("#patRidIn").val(lab.lrhPatientID);
                    $("#patRemarks").val(lab.lrhRemarks);
                    $("#patDoc").val(lab.lrhSignBy);
                    $.each(lab.labResultDs, function (Idx, labD) {
                        if (labD.lrdNodes != 0 && labD.lrdServiceType != 2) {
                            $.each(labD.serviceMaster.labServiceExtnList, function (ldx, template) {
                                templateNode = template.lsTemplateNode;
                                $("#setDiv").html(templateNode);
                                var findTd = $("#setDiv").find("table#printTable1").find("tr");
                                $("#viewDiv").html(findTd);
                                templateNode = $("#viewDiv").html();
                            });
                            $("#soTbody").append('<tr id="model' + Idx + '"><td width="30%"><b>\n\
                                                <input type="hidden" id="lsResultValue" style="width: 88%; name="lsResultValue" value="nil" class="col-md-11"/>\n\
                                                <input type="hidden" id="tempRid" value="' + 1 + '"/>\n\
                                                <input type="text" id="patService1" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/></b><input type="hidden" id="patService" value="' + labD.lrdNServName + '"/></td>\n\
                                                <input type="hidden" id="soRidIn" value="' + labD.lrdSoRid + '"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/>\n\
                                                <input type="hidden" id="lsDescription" value=""/><input type="hidden" id="lsState" value="' + labD.lrdNServRangeState + '"/><input type="hidden" id="lsUom" value=""/>\n\
                            <input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td></tr>' + templateNode);
                            $('#itemIdIn').eq(i).attr('id', labD.lrdServiceRid);
                            $('#form-main').eq(i).attr('id', labD.lrdServiceRid);
                            setLabValue(labD.lrdSORID, labD.lrdServiceRid);
                            return;
                            $('#itemIdIn').eq(i).attr('id', labD.lrdServiceRid);
                            $('#form-main').eq(i).attr('id', labD.lrdServiceRid);
                            setLabValue(labD.lrdSORID, labD.lrdServiceRid);
                            return;
                        } else if (labD.lrdNodes == 0 && labD.lrdServiceType == 2) {
                            $.each(dcomethealth.serviceMaster, function (index, serviceM) {
                                if (parseInt(serviceM.id) == parseInt(labD.lrdServiceRid)) {
                                    $.each(dcomethealth.dDictVal, function (ifx, val) {
                                        if (serviceM.bsCategory == val.id) {
                                            if (val.ddictValue == "Profile") {
                                                $("#soTbody").append('<tr id="model' + Idx + '"><td width="30%"><b>\n\
                                                <input type="hidden" id="lsResultValue" style="width: 88%; name="lsResultValue" value="nil" class="col-md-11"/>\n\
                                                <input type="hidden" id="tempRid" value="' + 0 + '"/>\n\
                                                <input type="text" id="patService1" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/></b><input type="hidden" id="patService" value="' + labD.lrdNServName + '"/></td>\n\
                                                <input type="hidden" id="soRidIn" value="' + labD.lrdSoRid + '"/>\n\
                                                <input type="hidden" id="labResultDId" value="' + labD.id + '"/>\n\
                                                <input type="hidden" id="lsDescription" value=""/><input type="hidden" id="lsState" value=""/><input type="hidden" id="lsUom" value=""/>\n\
                                            <input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td></tr>');
                                            } else {
                                                var onBlurCall = "dcomethealth.LabService.calResult(this)";
                                                if (labD.lrdNServRange == "Negative" || labD.lrdNServRange == "Negativo" || labD.lrdNServRange == undefined || labD.lrdNServRange == "Negativo/Positivo") {
                                                    onBlurCall = ""
                                                }
                                                labD.lrdNR = (labD.lrdNR == undefined) ? "--" : labD.lrdNR;
                                                labD.lrdNServRange = (labD.lrdNServRange == undefined) ? "--" : labD.lrdNServRange;
                                                labD.lrdNServRangeState = (labD.lrdNServRangeState == undefined) ? "--" : labD.lrdNServRangeState;
                                                labD.lrdNServUnit = !!labD.lrdNServUnit ? labD.lrdNServUnit : "--";
                                                $("#soTbody").append('<tr id="model2"><td width="30%"><input type="text" id="patService" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/><input type="hidden" id="tempRid" value="' + 0 + '"/>\n\
                                                </td><td width="15%"><input type="text" id="lsResultValue" name="lsResultValue" style="width: 88%;" onblur="' + onBlurCall + '" class="col-md-11" placeholder="Enter result" value="' + labD.lrdNR + '"/><input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td>\n\
                                                <td width="15%"><input type="text" id="lsUom" name="lsUom" class="col-md-11" style="border: none" value="' + labD.lrdNServUnit + '"/></td>\n\
                                                <td width="20%"><input type="text" id="lsDescription" name="lsDescription" class="col-md-11" style="border: none" value="' + labD.lrdNServRange + '"/></td>\n\
                                            <td width="20%"><input type="text" id="lsState" name="lsState" class="col-md-11" style="border: none" value="' + labD.lrdNServRangeState + '"/></td></tr>');
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            labD.lrdNR = (labD.lrdNR == undefined) ? "--" : labD.lrdNR;
                            labD.lrdNServRange = (labD.lrdNServRange == undefined) ? "--" : labD.lrdNServRange;
                            labD.lrdNServRangeState = (labD.lrdNServRangeState == undefined) ? "--" : labD.lrdNServRangeState;
                            labD.lrdNServUnit = !!labD.lrdNServUnit ? labD.lrdNServUnit : "--";
                            var onBlurCall = "dcomethealth.LabService.calResult(this)";
                            if (labD.lrdNServRange == "Negative" || labD.lrdNServRange == "Negativo" || labD.lrdNServRange == undefined || labD.lrdNServRange == "Negativo/Positivo") {
                                onBlurCall = ""
                            }
                            $("#soTbody").append('<tr id="model2"><td width="30%"><b><input type="text" id="patService" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/><input type="hidden" id="tempRid" value="' + 2 + '"/></b>\n\
                                    </td><td width="15%"><input type="text" id="lsResultValue" name="lsResultValue" style="width: 88%;" onblur="' + onBlurCall + '" class="col-md-11" placeholder="Enter result" value="' + labD.lrdNR + '"/><input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td>\n\
                                    <td width="15%"><input type="text" id="lsUom" name="lsUom" class="col-md-11" style="border: none" value="' + labD.lrdNServUnit + '"/></td>\n\
                                    <td width="20%"><input type="text" id="lsDescription" name="lsDescription" class="col-md-11" style="border: none" value="' + labD.lrdNServRange + '"/></td>\n\
                        <td width="20%"><input type="text" id="lsState" name="lsState" class="col-md-11" style="border: none" value="' + labD.lrdNServRangeState + '"/></td></tr>');
                        }
                        $("#visitRidIn").val(lab.lrhPatientVisitID);
                        $("#patRidIn").val(lab.lrhPatientID);
                    });
                }
            });
        });
    }
    function showCompltedResult(val, id) {
        $('#btns_state').addClass('hidden');
        $("#labResultId").val(id);
        $("#PatientRid").val(val);
        var i = 0, templateNode = {};
        $("#LabServiceHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#add_new").removeClass("hidden").addClass("panel panel-primary");
        var searchParams = {"id": val};
        dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
            $.each(data, function (pIdx, pat) {
                $("#patMRN").text(pat.patMrnNo);
                $("#patName").text(pat.patFullName);
                var c = pat.patDob.split('-');
                $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                $('#PatGender').val(pat.patGenderIndex);
                if (parseInt(pat.patGenderIndex) === 1) {
                    $('#PatientGend').val('Male');
                } else if (parseInt(pat.patGenderIndex) === 2) {
                    $('#PatientGend').val('Female');
                } else {
                    $('#PatientGend').val('Transgender');
                }
            });
        });
        $.each(labResult, function (Idx, lab) {
            if (parseInt(id) == parseInt(lab.id)) {
                if (parseInt(lab.lrhState) === 3) {
                    $("#labSave").hide();
                }
                $("#visitRidIn").val(lab.lrhPatientVisitID);
                $("#patRidIn").val(lab.lrhPatientID);
                $("#patRemarks").val(lab.lrhRemarks);
                $("#patDoc").val(lab.lrhSignBy);
                $.each(lab.labResultDs, function (Idx, labD) {
                    if (labD.lrdNodes != 0 && labD.lrdServiceType != 2) {
                        $.each(labD.serviceMaster.labServiceExtnList, function (ldx, template) {
                            templateNode = template.lsTemplateNode;
                            $("#setDiv").html(templateNode);
                            var findTd = $("#setDiv").find("table#printTable1").find("tr");
                            $("#viewDiv").html(findTd);
                            templateNode = $("#viewDiv").html();
                        });
                        $("#soTbody").append('<tr id="model' + Idx + '"><td width="30%"><b>\n\
                                                <input type="hidden" id="lsResultValue" style="width: 88%; name="lsResultValue" value="nil" class="col-md-11"/>\n\
                                                <input type="hidden" id="tempRid" value="' + 1 + '"/>\n\
                                                <input type="text" id="patService1" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/></b><input type="hidden" id="patService" value="' + labD.lrdNServName + '"/></td>\n\
                                                <input type="hidden" id="soRidIn" value="' + labD.lrdSORID + '"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/>\n\
                                                <input type="hidden" id="lsDescription" value=""/><input type="hidden" id="lsState" value="' + labD.lrdNServRangeState + '"/><input type="hidden" id="lsUom" value=""/>\n\
                        <input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td></tr>' + templateNode);
                        $('#itemIdIn').eq(i).attr('id', labD.lrdServiceRid);
                        $('#form-main').eq(i).attr('id', labD.lrdServiceRid);
                        setLabValue(labD.lrdSORID, labD.lrdServiceRid);
                        return;
                    }
                    else if (labD.lrdNodes == 0 && labD.lrdServiceType == 2) {
                        $.each(dcomethealth.serviceMaster, function (index, serviceM) {
                            if (parseInt(serviceM.id) == parseInt(labD.lrdServiceRid)) {
                                $.each(dcomethealth.dDictVal, function (ifx, val) {
                                    if (serviceM.bsCategory == val.id) {
                                        if (val.ddictValue === "Profile") {
                                            $("#soTbody").append('<tr id="model' + Idx + '"><td width="30%"><b>\n\
                                            <input type="hidden" id="lsResultValue" style="width: 88%; name="lsResultValue" value="nil" class="col-md-11"/>\n\
                                            <input type="hidden" id="tempRid" value="' + 0 + '"/>\n\
                                            <input type="text" id="patService1" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/></b><input type="hidden" id="patService" value="' + labD.lrdNServName + '"/></td>\n\
                                            <input type="hidden" id="soRidIn" value="' + labD.lrdSORID + '"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/>\n\
                                            <input type="hidden" id="lsDescription" value=""/><input type="hidden" id="lsState" value=""/><input type="hidden" id="lsUom" value=""/>\n\
                                        <input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td></tr>');
                                        } else {
                                            labD.lrdNR = (labD.lrdNR == undefined) ? "--" : labD.lrdNR;
                                            labD.lrdNServRange = (labD.lrdNServRange == undefined) ? "--" : labD.lrdNServRange;
                                            labD.lrdNServUnit = !!labD.lrdNServUnit ? labD.lrdNServUnit : " ";
                                            var onBlurCall = "dcomethealth.LabService.calResult(this)";
                                            if (labD.lrdNServRange == "Negative" || labD.lrdNServRange == "Negativo" || labD.lrdNServRange == undefined || labD.lrdNServRange == "Negativo/Positivo") {
                                                onBlurCall = ""
                                            }
                                            labD.lrdNServRangeState = (labD.lrdNServRangeState == undefined) ? "--" : labD.lrdNServRangeState;
                                            $("#soTbody").append('<tr id="model2"><td width="30%"><input type="text" id="patService" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/><input type="hidden" id="tempRid" value="' + 0 + '"/>\n\
                                            </td><td width="15%"><input type="text" id="lsResultValue" name="lsResultValue" style="width: 88%;" onblur="' + onBlurCall + '" class="col-md-11" placeholder="Enter result" value="' + labD.lrdNR + '"/><input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td>\n\
                                            <td width="15%"><input type="text" id="lsUom" name="lsUom" class="col-md-11" style="border: none" value="' + labD.lrdNServUnit + '"/></td>\n\
                                            <td width="20%"><input type="text" id="lsDescription" name="lsDescription" class="col-md-11" style="border: none" value="' + labD.lrdNServRange + '"/></td>\n\
                                        <td width="20%"><input type="text" id="lsState" name="lsState" class="col-md-11" style="border: none" value="' + labD.lrdNServRangeState + '"/></td></tr>');
                                        }
                                    }
                                });

                            }
                        });
                    } else {
                        labD.lrdNR = (labD.lrdNR == undefined) ? "--" : labD.lrdNR;
                        labD.lrdNServUnit = !!labD.lrdNServUnit ? labD.lrdNServUnit : "--";
                        var onBlurCall = "dcomethealth.LabService.calResult(this)";
                        if (labD.lrdNServRange == "Negative" || labD.lrdNServRange == "Negativo" || labD.lrdNServRange == undefined || labD.lrdNServRange == "Negativo/Positivo") {
                            onBlurCall = ""
                        }
                        $("#soTbody").append('<tr id="model2"><td width="30%"><b><input type="text" id="patService" value= "' + labD.lrdNServName + '" class="col-md-11" readonly style="border: none"/><input type="hidden" id="labResultDId" value="' + labD.id + '"/><input type="hidden" id="tempRid" value="' + 2 + '"/></b>\n\
                        </td><td width="15%"><input type="text" id="lsResultValue" name="lsResultValue" style="width: 88%;" onblur="' + onBlurCall + '" class="col-md-11" placeholder="Enter result" value="' + labD.lrdNR + '"/><input type="hidden" id="itemRidIn" value="' + labD.lrdServiceRid + '"/></td>\n\
                        <td width="15%"><input type="text" id="lsUom" name="lsUom" class="col-md-11" style="border: none" value="' + labD.lrdNServUnit + '"/></td>\n\
                        <td width="20%"><input type="text" id="lsDescription" name="lsDescription" class="col-md-11" style="border: none" value="' + labD.lrdNServRange + '"/></td>\n\
                    <td width="20%"><input type="text" id="lsState" name="lsState" class="col-md-11" style="border: none" value="' + labD.lrdNServRangeState + '"/></td></tr>');
                    }
                    $("#visitRidIn").val(lab.lrhPatientVisitID);
                    $("#patRidIn").val(lab.lrhPatientID);
                });
            }
        });
    }
    function sendEmail(labHid, visitRid, idIndex) {
        var groupId = 0;
        if (idIndex != undefined) {
            groupId = $("#printFormat" + idIndex).val();
        }
        if (groupId == undefined) {
            groupId = 0;
        }
        dcomethealth.LaboratoryResource.getLabPrint({"id": labHid}, groupId, function (data) {
            if (!!data) {
                $("#emailDiv").html(data);
                var searchObj = {"id": visitRid};
                dcomethealth.PatientResource.searchVisit(searchObj, function (visitData) {
                    if (!!visitData) {
                        var visitList = {};
                        $.each(visitData, function (ix, data) {
                            var visit = {};
                            visit.id = data.id;
                            visit.visPatRid = data.visPatRid;
                            visit.visDate = data.visDate;
                            visit.visSpecialityIndex = data.visSpecialityIndex;
                            visit.visSubTypeIndex = 0;
                            visit.visPatType = 0;
                            visit.visApptRid = 0;
                            visit.visEpisodeRid = 0;
                            visit.visAttnDocRid = 0;
                            visit.visCsNodes = $("#emailDiv").html();
                            visitList = visit;
                        });
                        dcomethealth.PatientResource.sendEmail(visitList).done(function (data, textStatus, jqXHR) {
                            alert("Sent Mail Successfully");
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                            alert("Failed");
                        });
                    }
                });
            }
        });
    }
    function searchDoctor() {
        dcomethealth.MasterResource.searchStaff({}, function (data) {
            var targetContainer = targetContainer || document;
            $("datalist.dcomet-c-s_doctors-list", targetContainer).each(function (idx, elem) {
                $.each(data, function (pIdx, s_staff) {
                    $(s_staff).each(function () {
                        if (s_staff.staffCategory === 237) {
                            $(elem).append('<option id="' + s_staff.id + '" value="' + s_staff.staffName + '">' + s_staff.staffCode + '</option>');
                        }
                    });
                });
            });
        });
        $("select option").each(function () { //For remove duplicates
            $(this).siblings("[value='" + this.value + "']").remove();
        });
    }
    function check(val, doctorRid, idIndex) {
        var groupId = 0;
        if (idIndex != undefined) {
            groupId = $("#printFormat" + idIndex).val();
        }
        var serviceGroupName = null;
        $.each(dcomethealth.dDictVal, function (ix, dDict) {
            if (dDict.id == groupId) {
                serviceGroupName = dDict.ddictValue;
            }
        });
        if (groupId == undefined) {
            groupId = 0;
        }
        dcomethealth.LaboratoryResource.getLabPrint({"id": val}, groupId, function (data) {
            if (!!data) {
                $("#printDiv").html(data);
                $('#doctorSign').attr('src', '/health_web/rest/master/v1/dcoctorSign/img?id=' + doctorRid);
                $("#emailDiv").html(data);
                $("#entLogo1").attr('src', dcomethealth.image);
                $("#groupName").text("Department of " + serviceGroupName);
                setTimeout(function () {
                    exportExcel($("#printDiv").html());
                }, 500);
            }
        });
    }
    function calResult(row) {
        var searchParams = {"lsServiceRID": parseInt(dynTableGetNodeInRow(row, 'itemRidIn').value)};
        dcomethealth.LaboratoryResource.searchLabServiceExtn(searchParams, function (data) {
            $.each(data, function (pIdx, lService) {
                if (parseInt(lService.lsAgeMinValue) <= parseInt($('#patAge').val()) && parseInt($('#patAge').val()) <= parseInt(lService.lsAgeMaxValue)) {
                    if (parseInt((lService.lsGenderType)) === (parseInt($('#PatGender').val()))) {
                        if (parseFloat(lService.lsRangeMinValue) <= parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) && parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) <= parseFloat(lService.lsRangeMaxValue)) {
                            dynTableGetNodeInRow(row, 'lsState').value = "Normal Range";
                        } else if (parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) < parseFloat(lService.lsRangeMinValue)) {
                            dynTableGetNodeInRow(row, 'lsState').value = "Low Range";
                        } else if (parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) > parseFloat(lService.lsRangeMaxValue)) {
                            dynTableGetNodeInRow(row, 'lsState').value = "High Range";
                        }
                    } else if (parseInt((lService.lsGenderType)) !== 1 && parseInt((lService.lsGenderType)) !== 2) {
                        if (parseFloat(lService.lsRangeMinValue) <= parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) && parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) <= parseFloat(lService.lsRangeMaxValue)) {
                            dynTableGetNodeInRow(row, 'lsState').value = "Normal Range";
                        } else if (parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) < parseFloat(lService.lsRangeMinValue)) {
                            dynTableGetNodeInRow(row, 'lsState').value = "Low Range";
                        } else if (parseFloat(dynTableGetNodeInRow(row, 'lsResultValue').value) > parseFloat(lService.lsRangeMaxValue)) {
                            dynTableGetNodeInRow(row, 'lsState').value = "High Range";
                        }
                    }
                }
            });
        });
    }
    function setLabValue(soRid, itemRID) {
        var form = $('form');
        var f = form.serializeArray();
        dcomethealth.LaboratoryResource.searchLabResults({}, function (data) {
            if (!!data) {
                $.each(labResult = data, function (aIdx, data) {
                    $.each(data.labResultDs, function (pIdx, labResultd) {
                        if (parseInt(soRid) == parseInt(labResultd.lrdSORID)) {
                            var obj = jQuery.parseJSON(labResultd.lrdNodes);
                            $.each(obj, function (idx, val) {
                                $.each(f, function () {
                                    if (this.name == idx) {
                                        $('#' + this.name).val(val);
                                    }
                                });
                            });
                        }
                    });
                });
            }
        });
    }
    function getLabValue(rid) {
        var o = {}, checked = false;
        var tablels = document.getElementById('ls_table');
        for (var i = 0; i < tablels.rows.length - 1; i++) {
            if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 1 && dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value != "nil") {
                if (parseInt(rid) == parseInt(dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value)) {
                    var tr = dynTableGetNodeInRow(tablels.rows[i + 1]);
                    $(tr).find("td :input").not('input[type="hidden"]').each(function (idx, tdInput) {
                        var id = $(tdInput).attr("id");
                        var value = $("#" + id).val();
                        if (id.includes("Result")) {
                            if (value == "" || value == undefined) {
                                checked = true;
                            } else {
                                o[id] = value;
                            }
                        } else {
                            if (!checked) {
                                if (!!value) {
                                    o[id] = value;
                                }
                            }
                        }
                    });
                }
            }
        }
        return o;
    }
    function getProfileValue(rid) {
        var tablels = document.getElementById('ls_table');
        for (var i = 0; i < tablels.rows.length - 1; i++) {
            if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 0 && dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value != "nil") {
                if (parseInt(rid) == parseInt(dynTableGetNodeInRow(tablels.rows[i + 1], 'soRidIn').value)) {
                    if (!!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value) {
                        profileResultCheck = true;
                    }
                }
            }
        }
    }
    function saveLabServices() {
        var tablels = document.getElementById('ls_table');
        if ($('#patDoc').val() === "") {
            alert("Select Doctor Name");
            return false;
        }
        if ($("#labResultId").val() == "") {
            var serviceOrderList = [], labResultH = {};
            var labResultDServiceList = [];
            labResultH.lrhRemarks = $("#patRemarks").val();
            labResultH.lrhSignBy = $("#patDoc").val();
            labResultH.lrhState = 2;
            labResultH.lrhStatus = 2;
            var tablels = document.getElementById('ls_table');
            for (var i = 0; i < tablels.rows.length - 1; i++) {
                if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 2) {
                    var serviceOrder = {};
                    serviceOrder.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'soRidIn').value;
                    serviceOrder.soPatientRID = $("#patRidIn").val();
                    var doctorRid = $('#Doctors option[value="' + $('#patDoc').val() + '"]').attr('id');
                    serviceOrder.soAttndDocRID = doctorRid; //For LabService Doctor Rid
                    !!$("#visitRidIn").val() ? serviceOrder.soVisitRID = $("#visitRidIn").val() : null;
                    serviceOrder.soOrderingUnitRID = dcomethealth.selectedunit;
                    serviceOrder.soOrderType = 1;
                    serviceOrder.soItemID = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    serviceOrder.soItemName = dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value;
                    serviceOrder.soQty = 1;
                    serviceOrder.soProcessingUnitRID = dcomethealth.selectedunit;
                    serviceOrder.soProcessedBY = $("#patDoc").val();
                    serviceOrder.soRemarks = $("#patRemarks").val();
                    var labService = {};
                    var labResultDList = [];
                    if (!!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value) {
                        labService.lrdServiceRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                        labService.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value : null;
                        labService.lrdNR = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value : null;
                        labService.lrdNServUnit = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value : null;
                        labService.lrdNServRange = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value : null;
                        labService.lrdNServRangeState = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value : null;
                        labService.lrdNodes = 0;
                        labService.lrdServiceType = 1
                        labResultDList.push(labService);
                        serviceOrder.labResultDList = labResultDList;
                        serviceOrderList.push(serviceOrder);
                    }
                } else if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 1 && dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value == "nil") {
                    var serviceOrder = {};
                    serviceOrder.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'soRidIn').value;
                    serviceOrder.soPatientRID = $("#patRidIn").val();
                    var doctorRid = $('#Doctors option[value="' + $('#patDoc').val() + '"]').attr('id');
                    serviceOrder.soAttndDocRID = doctorRid; //For LabService Doctor Rid
                    !!$("#visitRidIn").val() ? serviceOrder.soVisitRID = $("#visitRidIn").val() : null;
                    serviceOrder.soOrderingUnitRID = dcomethealth.selectedunit;
                    serviceOrder.soOrderType = 1;
                    serviceOrder.soItemID = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    serviceOrder.soItemName = dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value;
                    serviceOrder.soQty = 1;
                    serviceOrder.soProcessingUnitRID = dcomethealth.selectedunit;
                    serviceOrder.soProcessedBY = $("#patDoc").val();
                    serviceOrder.soRemarks = $("#patRemarks").val();
                    var labService = {};
                    var labResultDList = [], obj = {};
                    var srchItemRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    obj = getLabValue(srchItemRid);
                    labService.lrdNodes = JSON.stringify(getLabValue(srchItemRid));
                    labService.lrdNServName = dynTableGetNodeInRow(tablels.rows[i + 1], 'patService1').value;
                    labService.lrdNR = 0
                    labService.lrdNServUnit = 0;
                    labService.lrdNServRange = 0
                    labService.lrdNServRangeState = 0;
                    labService.lrdServiceType = 1
                    if (!jQuery.isEmptyObject(obj)) {
                        obj = {};
                        labResultDList.push(labService);
                        serviceOrder.labResultDList = labResultDList;
                        serviceOrderList.push(serviceOrder);
                    }
                } else if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 0 && dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value == "nil") {
                    profileResultCheck = false;
                    var serviceOrderService = {};
                    serviceOrderService.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'soRidIn').value;
                    serviceOrderService.soPatientRID = $("#patRidIn").val();
                    var doctorRid = $('#Doctors option[value="' + $('#patDoc').val() + '"]').attr('id');
                    serviceOrderService.soAttndDocRID = doctorRid; //For LabService Doctor Rid
                    !!$("#visitRidIn").val() ? serviceOrderService.soVisitRID = $("#visitRidIn").val() : null;
                    serviceOrderService.soOrderingUnitRID = dcomethealth.selectedunit;
                    serviceOrderService.soOrderType = 1;
                    serviceOrderService.soItemID = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    serviceOrderService.soItemName = dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value;
                    serviceOrderService.soQty = 1;
                    serviceOrderService.soProcessingUnitRID = dcomethealth.selectedunit;
                    serviceOrderService.soProcessedBY = $("#patDoc").val();
                    serviceOrderService.soRemarks = $("#patRemarks").val();
                    var labService = {};
                    var labResultDList = [];
                    var srchItemRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'soRidIn').value;
                    getProfileValue(srchItemRid);
                    labService.lrdNServName = dynTableGetNodeInRow(tablels.rows[i + 1], 'patService1').value;
                    labService.lrdServiceRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    //                    labService.lrdNR = 0
                    labService.lrdNodes = 0;
//                    labService.lrdNServUnit = 0;
//                    labService.lrdNServRange = 0
                    //                    labService.lrdNServRangeState = 0;
                    labService.lrdServiceType = 2
                    if (profileResultCheck) {
                        profileResultCheck = false;
                        labResultDList.push(labService);
                        var table = document.getElementById('ls_table');
                        for (var j = 0; j < table.rows.length - 1; j++) {
                            var labService = {};
                            if (dynTableGetNodeInRow(table.rows[j + 1], 'tempRid').value == 0 && dynTableGetNodeInRow(table.rows[j + 1], 'lsResultValue').value != "nil") {
                                if (parseInt(srchItemRid) == parseInt(dynTableGetNodeInRow(table.rows[j + 1], 'soRidIn').value)) {
                                    labService.lrdServiceRid = dynTableGetNodeInRow(table.rows[j + 1], 'itemRidIn').value;
                                    labService.lrdNServName = !!dynTableGetNodeInRow(table.rows[j + 1], 'patService').value ? dynTableGetNodeInRow(table.rows[j + 1], 'patService').value : null;
                                    labService.lrdNR = !!dynTableGetNodeInRow(table.rows[j + 1], 'lsResultValue').value ? dynTableGetNodeInRow(table.rows[j + 1], 'lsResultValue').value : null;
                                    labService.lrdNServUnit = !!dynTableGetNodeInRow(table.rows[j + 1], 'lsUom').value ? dynTableGetNodeInRow(table.rows[j + 1], 'lsUom').value : null;
                                    labService.lrdNServRange = !!dynTableGetNodeInRow(table.rows[j + 1], 'lsDescription').value ? dynTableGetNodeInRow(table.rows[j + 1], 'lsDescription').value : null;
                                    labService.lrdNServRangeState = !!dynTableGetNodeInRow(table.rows[j + 1], 'lsState').value ? dynTableGetNodeInRow(table.rows[j + 1], 'lsState').value : null;
                                    labService.lrdNodes = 0;
                                    labService.lrdServiceType = 2;
                                    labResultDList.push(labService);
                                }
                            }
                        }
                        serviceOrderService.labResultDList = labResultDList;
                        serviceOrderList.push(serviceOrderService);
                    }
                }
            }
            if (labResultDServiceList.length > 0) {
                serviceOrderService.labResultDList = serviceOrderService;
                serviceOrderList.push(serviceOrderService);
            }
            dcomethealth.ServiceOrderResource.saveServiceOrderResult(serviceOrderList).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                if (dcomethealth.loginuser.entityRid == 5) {
                    dcomethealth.util.loadpage('LabService');
                    dcomethealth.util.base_init();
                    dcomethealth.util.loadNotification();
                } else {
                    if (confirm("Data Saved Proced to Print")) {
                        $.each(data, function (idx, ser) {
                            $.each(ser.labResultDList, function (idx, lab) {
                                labHRid = lab.lrdHRID;
                            });
                        });
                        dcomethealth.LabService.check(labHRid, doctorRid, 0);
                        dcomethealth.util.loadpage('LabService');
                        dcomethealth.util.base_init();
                        dcomethealth.util.loadNotification();
                    } else {
                        dcomethealth.util.loadpage('LabService');
                        dcomethealth.util.base_init();
                    }
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed to Save");
            });
        } else {
            var labResultH = {}, labresultDList = [];
            labResultH.id = $("#labResultId").val();
            labResultH.lrhPatientID = $("#PatientRid").val();
            labResultH.lrhState = 2;//need to change
            labResultH.lrhStatus = 2;
            var doctorRid = $('#Doctors option[value="' + $('#patDoc').val() + '"]').attr('id');
            labResultH.lrhDoctorRid = doctorRid;
            labResultH.lrhSignBy = $("#patDoc").val();
            !!$("#visitRidIn").val() ? labResultH.lrhPatientVisitID = $("#visitRidIn").val() : null;
            labResultH.lrhRemarks = $("#patRemarks").val();
            var tablels = document.getElementById('ls_table');
            for (var i = 0; i < tablels.rows.length - 1; i++) {
                if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 2) {
                    var labresultD = {};
                    var itemRID = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    labresultD.lrdNodes = 0;
                    labresultD.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'labResultDId').value;
                    labresultD.lrdNR = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value;
                    labresultD.lrdNServRange = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value : null;
                    labresultD.lrdNServRangeState = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value : null;
                    labresultD.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value : null;
                    labresultD.lrdServiceRid = itemRID;
                    labresultD.lrdServiceType = 1;
                    labresultD.lrdNServUnit = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value : null;
                    labresultDList.push(labresultD);
                }
                else if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 0) {
                    var labresultD = {};
                    labresultD.lrdNodes = 0;
                    labresultD.lrdServiceType = 2;
                    labresultD.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'labResultDId').value;
                    if (dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value != "nil") {
                        labresultD.lrdNServUnit = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value : null;
                        labresultD.lrdNR = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value;
                        labresultD.lrdNServRange = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value : null;
                        labresultD.lrdNServRangeState = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value : null;
                    }
                    labresultD.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value : null;
                    labresultD.lrdServiceRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;

                    labresultDList.push(labresultD);
                } else if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 1 && dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value == "nil") {
                    var labresultD = {};
                    labresultD.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'labResultDId').value;
                    labresultD.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService1').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService1').value : null;
                    var srchItemRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                    labresultD.lrdNR = 0
                    labresultD.lrdNServUnit = 0;
                    labresultD.lrdNServRange = 0
                    labresultD.lrdNServRangeState = 0;
                    labresultD.lrdServiceType = 1;
                    labresultD.lrdNodes = JSON.stringify(getLabValue(srchItemRid));
                    labresultDList.push(labresultD);
                }
            }
            labResultH.labResultDs = labresultDList;
            dcomethealth.LaboratoryResource.modifyLabResults(labResultH).done(function (data, textStatus, jqXHR) {
                if (confirm("Data Saved Proceed to Print")) {
                    dcomethealth.LabService.check($("#labResultId").val(), doctorRid, 0);
                    dcomethealth.util.loadpage('LabService');
                    dcomethealth.util.base_init();
                } else {
                    dcomethealth.util.loadpage('LabService');
                    dcomethealth.util.base_init();
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed to Save");
            });
        }
    }
    function submit(boRID, boCode, actionCode) {
        var labResultH = {}, labresultDList = [];
        labResultH.id = $("#labResultId").val();
        labResultH.lrhPatientID = $("#PatientRid").val();
        var doctorRid = $('#Doctors option[value="' + $('#patDoc').val() + '"]').attr('id');
        labResultH.lrhDoctorRid = doctorRid;
        labResultH.lrhSignBy = $("#patDoc").val();
        !!$("#visitRidIn").val() ? labResultH.lrhPatientVisitID = $("#visitRidIn").val() : null;
        labResultH.lrhRemarks = $("#patRemarks").val();
        var tablels = document.getElementById('ls_table');
        for (var i = 0; i < tablels.rows.length - 1; i++) {
            if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 2) {
                var labresultD = {};
                var itemRID = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                labresultD.lrdNodes = 0;
                labresultD.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'labResultDId').value;
                labresultD.lrdNR = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value;
                labresultD.lrdNServRange = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value : null;
                labresultD.lrdNServRangeState = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value : null;
                labresultD.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value : null;
                labresultD.lrdServiceRid = itemRID;
                labresultD.lrdServiceType = 1;
                labresultD.lrdNServUnit = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value : null;
                labresultDList.push(labresultD);
            } else if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 1 && dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value == "nil") {
                var labresultD = {};
                labresultD.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'labResultDId').value;
                labresultD.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService1').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService1').value : null;
                var srchItemRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
                labresultD.lrdNR = 0
                labresultD.lrdNServUnit = 0;
                labresultD.lrdNServRange = 0
                labresultD.lrdNServRangeState = 0;
                labresultD.lrdServiceType = 1;
                labresultD.lrdServiceRid = itemRID;
                labresultD.lrdNodes = JSON.stringify(getLabValue(srchItemRid));
                labresultDList.push(labresultD);
            } else if (dynTableGetNodeInRow(tablels.rows[i + 1], 'tempRid').value == 0) {
                var labresultD = {};
                labresultD.lrdNodes = 0;
                labresultD.lrdServiceType = 2;
                labresultD.id = dynTableGetNodeInRow(tablels.rows[i + 1], 'labResultDId').value;
                if (dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value != "nil") {
                    labresultD.lrdNServUnit = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value : null;
                    labresultD.lrdNR = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value;
                    labresultD.lrdNServRange = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value : null;
                    labresultD.lrdNServRangeState = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'lsState').value : null;
                }
                labresultD.lrdNServName = !!dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value ? dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value : null;
                labresultD.lrdServiceRid = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;

                labresultDList.push(labresultD);
            }
        }
        labResultH.labResultDs = labresultDList;
        var search = boRID + "/" + boCode + "/" + actionCode;
        dcomethealth.LaboratoryResource.saveLabResults(labResultH, search).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('LabService');
            dcomethealth.util.base_init();
            dcomethealth.util.loadNotification();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
//    function generatePageByGroupName(groupName) {
//        var tablels = document.getElementById('ls_table');
//        var table_length = tablels.rows.length;
//        var a = 0, itemRID = null, soRID = null;
//        $("#ExportHeamatologyTable").append('<tr id="trTable"><td><table id="' + groupName + '" width="100%"><tr> <td> <table width="100%"> <tr> <td width="18%"><img src="#" alt="Logo" style="max-height: 180px; max-width: 100px; visibility:hidden;" id="entityImagePath' + groupName + '"/></td><td width="69%"> <p><strong><span style="visibility:hidden;" id="entityName' + groupName + '"> </span></strong><strong><br/></strong><small> <span style="visibility:hidden;" id="entityDisplayName' + groupName + '"></span><br/><span style="visibility:hidden;">Telefone: </span><span  style="visibility:hidden;" id="entityPhone' + groupName + '"></span><br/><span  style="visibility:hidden;">Email:</span> <span id="entityEmail' + groupName + '" style="visibility:hidden;"></span><span style="visibility:hidden;">Website:</span> <span style="visibility:hidden;" id="entityWebSite' + groupName + '"></span></small> </p></td><td width="13%" style="visibility:hidden;"><small>Original</small></td></tr></table> </td></tr><tr> <td></td></tr><tr > <td> <p align="center" style="background-color: #FF9900 !important;-webkit-print-color-adjust: exact; border: 1px solid #FF9900; padding:5px; visibility:hidden;"><small>REPORT</small></p></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr> <td> <tr> <td> <table style="float: left;" width="60%"><tr><td width="20%"><span style="visibility:hidden;"><small>Name</small></span></td><td style="visibility:hidden;"></td><td width="78%"><small><span style="margin-left:70px;" id="PatientName' + groupName + '" ></span></small></td></tr><tr><td></td></tr><tr> <td width="20%" style="visibility:hidden;"><small>Age</small></td><td style="visibility:hidden;">:</td><td width="78%"><small> <span style="margin-left:70px;" id="PatientAge' + groupName + '"></span><span> / </span><span id="PatientGender' + groupName + '"></span></small></td></tr><tr> <td width="20%" style="visibility:hidden;"><small>Gender</small> </td><td style="visibility:hidden;">:</td><td width="78%"></td></tr><tr> <td width="20%" ><span style="visibility:hidden;"><small>Corporate</small></span></td><td style="visibility:hidden;">:</td><td width="78%"><small><span id="corporate"></span></small></td></tr></table> <table style="float: right;" width="40%"> <tr> <td width="25%" style="visibility:hidden;"><small>Uhid</small></td><td style="visibility:hidden;">:</td><td width="78%"><small><span id="uhid' + groupName + '"></span></small></td></tr><tr> <td width="25%" style="visibility:hidden;"><small>Reg No</small></td><td style="visibility:hidden;">:</td><td width="78%"><small> <span id="regNo"></span></small></td></tr><tr> <td width="25%" style="visibility:hidden;"><small>Reg Date</small></td><td style="visibility:hidden;">:</td><td width="78%"><small><span id="RegOn' + groupName + '"></span></small></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr> <td width="30%" style="visibility:hidden;"><small>Collected On</small></td><td style="visibility:hidden;">:</td><td style="text-align: left;" width="68%"><small><span id="ColOn">' + moment().format('DD-MM-YYYY') + '</span></small></td></tr><tr> <td width="30%" style="visibility:hidden;"><small>Reported On</small></td><td style="visibility:hidden;">:</td><td style="text-align: left;" width="68%"><small><span id="RepOn">' + moment().format('DD-MM-YYYY') + '</span></small></td></tr></table> </td></tr><table><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr></table><tr><td><table width="100%" style="margin-top: -50px;"> <tr> <td></td></tr><tr> <td> <table width="100%"> <tr> <td> <table style="border-style: none;" border="0" width="100%" cellspacing="0" cellpadding="0"> <thead> <caption id="groupName' + groupName + '" style="background-color: #FF9900 !important;-webkit-print-color-adjust: exact;font-size: 12px;border: 1px solid #FF9900;padding: 4px;border-radius:15px;margin-bottom: 10px;visibility:hidden;"><small></small></caption> <tr> <th style="font-size: 12px;background-color: #FF9900 !important; -webkit-print-color-adjust: exact; text-align: center; border: 1px solid #FF9900;padding: 2px;border-radius:15px;visibility:hidden;" width="37%">Test Name</th> <th style="font-size: 12px;background-color: #FF9900 !important; -webkit-print-color-adjust: exact;text-align: center; border: 1px solid #FF9900;padding: 2px; border-radius:15px;visibility:hidden;" width="15%">Result</th> <th style="font-size: 12px;background-color: #FF9900 !important; -webkit-print-color-adjust: exact; text-align: center; border: 1px solid #FF9900;padding: 2px;border-radius:15px;visibility:hidden;" width="14%">Units</th> <th style="font-size: 12px;background-color: #FF9900 !important; -webkit-print-color-adjust: exact;text-align: center; border: 1px solid #FF9900;padding: 2px;border-radius:15px;visibility:hidden;" width="22%">Biological Reference Intervals</th </tr></thead> <tbody id="exp_H_tbody' + groupName + '" ></tbody> </table> </td></tr><br><tr> <td> <p style="padding: 5px;visibility:hidden;" align="left"> <small>Impression :</small> <br/> </td></tr><tr> <td width="75%"> <p id="descriptionReport"></p></td></tr><br/> <tr> <td> <p align="center" style="visibility:hidden;"><small>------End Of Report------</small></p></td></tr></table></td></tr></table></td></tr></td></tr></table></td></tr>');
//        $('#entityName' + groupName + '').text(dcomethealth.userEntityName);
//        $('#entityDisplayName' + groupName + '').text(dcomethealth.userEntityDisplayAddress);
//        $('#entityEmail' + groupName + '').text(dcomethealth.userEntityMail);
//        $('#entityPhone' + groupName + '').text(dcomethealth.userEntityPhone);
//        $('#entityImagePath' + groupName + '').prop('src', dcomethealth.userEntityPath);
//        $('#entityWebSite' + groupName + '').text(dcomethealth.userEntityWebSite);
//        $('#PatientName' + groupName + '').text($('#patName').val());
//        $('#PatientGender' + groupName + '').text($('#PatientGend').val());
//        $('#PatientAge' + groupName + '').text($('#patAge').val());
//        $('#uhid' + groupName + '').text($('#patMRN').val());
//        $('#groupName' + groupName).text(groupName);
//        for (var i = 0; i < table_length - 1; i++) {
//            itemRID = dynTableGetNodeInRow(tablels.rows[i + 1], 'itemRidIn').value;
//            soRID = dynTableGetNodeInRow(tablels.rows[i + 1], 'soRidIn').value;
//            $.each(dcomethealth.sodata, function (aIdx, data) {
//                if (parseInt(data.id) == parseInt(soRID)) {
//                    $.each(data.labResultDList, function (pIdx, labResultD) {
//                        if (!!labResultD.lrdNodes) {
//                            $.each(dcomethealth.template, function (pIdx, template) {
//                                if (parseInt(itemRID) == parseInt(template.tempRID)) {
//                                    $("#exp_H_tbody" + groupName).append('<tr style="margin-top: 200px;"><td style="border-style: none !important; padding: 5px; font-size: 12px; white-space: nowrap;" >' + dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value + '</td></small></tr>');
//                                    $("#exp_H_tbody" + groupName).append(template.tempNodes);
//                                    $('#form-main').eq(a).attr('id', itemRID);
//                                    $("#exp_H_tbody :input").css({"border": "0", "text-align": "right"});
////                                    $("#exp_H_tbody :input").css({"border": "0","text-align": "right"});
//                                    var obj = jQuery.parseJSON(labResultD.lrdNodes);
//                                    $.each(obj, function (idx, val) {
//                                        $('table#itemIdIn tr td').each(function (inx, td) {
//                                            var inputId = $(td).find("input[type='text']").attr("id");
//                                            if (inputId == idx) {
////                                                var sliceID = inputId.slice(-6);
////                                                if (inputId.slice(-5) == "State") {
////                                                    $('#' + inputId).remove();
////                                                } else {
//                                                $('#' + inputId).attr("value", val);
////                                                }
////                                                if (sliceID == "Result") {
////                                                    $('#' + inputId).css({"margin-left": "25px","text-align": "right"});
////                                                }
////                                                $("#exp_H_tbody input#cabcResult").css({"text-align": "right", "margin-left": "31px"});
//
//                                            }
//                                        });
//                                    });
//                                }
//                            });
//                        } else {
//                            $("#exp_H_tbody" + groupName).append('<tr style="margin-top: 200px;"><td style="border-style: none !important; padding: 5px; font-size: 12px;" width="40%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value + '</td></small><small><td style="border-style: none !important;font-size: 12px;text-align: center;" width="15%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value + '</td></small><small><td style="border-style: none !important;text-align: center;font-size: 12px;" width="15%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value + '</td></small><small><td style="border-style: none !important;text-align: center;font-size: 12px;"width="30%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value + '</td></small></tr>');
//                        }
//                    });
//                }
//            });
//
////            if ((dynTableGetNodeInRow(tablels.rows[i + 1], 'lsGroupName').value).toUpperCase() === groupName) {
////            $('#RegOn' + groupName + i).text((dynTableGetNodeInRow(tablels.rows[i + 1], 'soDate').value).toUpperCase());
////            $("#exp_H_tbody" + groupName).append('<tr style="margin-top: 200px;"><td style="border-style: none !important; padding: 5px; font-size: 12px;" width="40%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'patService').value + '</td></small><small><td style="border-style: none !important;font-size: 12px;text-align: center;" width="15%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'lsResultValue').value + '</td></small><small><td style="border-style: none !important;text-align: center;font-size: 12px;" width="15%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'lsUom').value + '</td></small><small><td style="border-style: none !important;text-align: center;font-size: 12px;"width="30%">' + dynTableGetNodeInRow(tablels.rows[i + 1], 'lsDescription').value + '</td></small></tr>');
////                if (parseInt(i + 1) < parseInt(table_length - 1)) {
////                    document.getElementById(i + 1).style.pageBreakAfter = "always";
////                }
////                grpNameOld = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsGroupName').value;
////            }
////            if (dynTableGetNodeInRow(tablels.rows[i + 1], 'lsGroupName').value !== groupName) {
////                document.getElementById(i + 1).style.pageBreakAfter = "always";
    ////            }
    //        }
    //    }
    function exportExcel(html) {
//        var groupNames = [];
//        $("#exp_H_tbody").empty();
//        var tablels = document.getElementById('ls_table');
//        var table_length = tablels.rows.length;
//        for (var i = 0; i < table_length - 1; i++) {
//            var lsGroupName = dynTableGetNodeInRow(tablels.rows[i + 1], 'lsGroupName').value;
//            if ($.inArray(lsGroupName, groupNames) <= -1) {
//                groupNames.push(lsGroupName);
//        var name = "";
//        generatePageByGroupName(name);
//        document.getElementById(totalTable).style.pageBreakAfter = "avoid";
        //        var htmltable = document.getElementById('ExportHeamatologyTable');
        //        var html = htmltable.outerHTML;

        var printWindow = window.open();
        printWindow.document.write(html);
        //                printWindow.onload = function () { // necessary if the div contain images//           
        setTimeout(function () {
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }, 500);
        var printWindow = window.open('', '_blank');
        printWindow.document.write(html);
        printWindow.document.close();
        printWindow.focus();
                printWindow.print();
                printWindow.close();
        dcomethealth.util.loadpage('LabService');
        dcomethealth.util.base_init();
    }
    function refreshData() {
    }
    return {
        init: init,
        searchSOByDate: searchSOByDate,
        searchDoctor: searchDoctor,
        searchCompleted: searchCompleted,
        showCompltedResult: showCompltedResult,
        showResult: showResult,
        check: check,
        calResult: calResult,
        getLabValue: getLabValue,
        setLabValue: setLabValue,
        getProfileValue: getProfileValue,
        saveLabServices: saveLabServices,
        sendEmail: sendEmail,
        searchCancelledOrder: searchCancelledOrder,
        //        generatePageByGroupName: generatePageByGroupName,
        exportExcel: exportExcel,
        refreshData: refreshData
    };
}());
dcomethealth.LabService.init();