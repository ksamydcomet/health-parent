var dcomethealth = dcomethealth || {};
var attRefType = 2;
var poType = 0;
dcomethealth.Patients = (function () {
    var id = "Patients", billList = {}, staffList = {}, fileList = [], serviceList = [], staffSpeciality = {}, fileCheck = false, patSearch = false;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            dcomethealth.Pictures.setDragNDropListeners(id);
//            $("#processServiceOn").datepicker({format: 'dd-mm-yyyy'});
            $('#processServiceOn').val(moment().format('DD-MM-YYYY'));
            dcomethealth.MasterResource.searchStaff({}, function (data) {
                if (!!data) {
                    staffList = data;
                    $.each(data, function (indx, staff) {
                        if (dcomethealth.loginuser.id == staff.staffUserRID) {
                            staffSpeciality = staff.staffSpecialityIndex;
                            getPatientDetails(staffSpeciality);
                        }
                    });
                }
            });
            patientSearch();
            autocompleteService();
            autoCompleteDrug();
//            getTemplatesForService();
//            fetchServicesFromRadiology();
            $("ul#serviceMenu").hide();
//            $('#div2,#div3,#div4,#div5,#div6,#div7,#div8,#div9,#div10,#div11,#div12').hide();
            var searchObj = {"unitIsServiceProvider": 1};
            dcomethealth.MasterResource.searchUnit(searchObj, function (data) {
                if (!!data) {
                    $.each(data, function (ix, unit) {
                        $("#patSpeciality").append("<option value='" + unit.id + "'>" + unit.unitName + "</option>");
                    });
                }
            });

        });
    }
    function patientSearch() {
        $("#" + id + " input[id='patSearch']").autocomplete({
            select: function (event, ui) {
                patSearch = true;
                var uiData = ui.item.data;
                $("#patRidSearch").val(uiData.id);
                $("#patRid").val(uiData.id);
                $("#patName").text(uiData.patFullName);
                $("#patDemoInfo").text("(Gender: " + uiData.patGender + ", Age: " + uiData.patAge + ")");
                if (dcomethealth.security.isPrivillage('PatientModify')) {
                    $("#completeBtn").removeClass("hidden");
                } else {
                    $("#completeBtn").addClass("hidden"); //for hide complete button
                    $("#backBtn").attr("style", "float:right;margin-top: 3px;");
                }
                loadPatientVisitTab(uiData.id, 1);
                getLedgerDetails(uiData.id);
            },
            source: function (request, response) {
                var queryString = request.term;
                queryString = queryString.trim();
                var searchParams = {"q": queryString};
                dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
                    response($.grep($.map(data, function (data) {
                        return {label: (data.patMrnNo || "") + (data.patMrnNo && data.patPhoneNo ? " - " : "") + (data.patPhoneNo || ""),
                            value: (data.patMrnNo || ""), name: (data.patTitle || "") + (data.patTitle && data.patFullName ? " - " : ""), data: data
                        };
                    }), function (data, index) {
                        return index < 10;
                    }));
                });
            },
            minLength: 1
        });
    }
    function getPatientDetails(staffSpeciality) {
        var searchObj = {"visDate": moment().format('DD-MM-YYYY'), "visSpecialityIndex": staffSpeciality};
        dcomethealth.PatientResource.searchVisit(searchObj, function (data) {
            if (!!data) {
                $.each(data, function (pIdx, visits) {
                    if (!!visits.patient && (visits.patient).length > 0) {
                        $.each(visits.patient, function (pdx, patients) {
                            if (parseInt(visits.visIsCompleted) == 1) {
                                $("#printFop").removeClass('hidden');
                                $("#patientDiv").append('<div style="" id="visDiv" class="col-md-11 patientBox effect8 patientBox-success"><a href="#" ><span class="pull-right"><i class="fa fa-caret"></i></span><span onclick="dcomethealth.Patients.getVisitPatient(' + visits.visPatRid + ',' + visits.visIsCompleted + ')" id="patientsName"><b>' + patients.patFullName + '</b></span><br><span id="patientsMrnNo"><b><small>' + patients.patMrnNo + '</small></b></span><br><span id="patientsAge"><small>' + patients.patAge + ' / ' + patients.patGender + '</small></span><br><input id="patRid" value="' + visits.visPatRid + '" type="hidden"></a></div>');
                            } else {
                                if ((visits.visIsCompleted == undefined || visits.visIsCompleted == "")) {
                                    visits.visIsCompleted = 0;
                                }
                                $("#printFop").addClass('hidden');
                                $("#patientDiv").append('<div style="" id="visDiv" class="col-md-2 patientBox effect8 patientBox-danger"><a href="#" style=""><span class="pull-right"><i class="fa fa-caret"></i></span><span onclick="dcomethealth.Patients.getVisitPatient(' + visits.visPatRid + ',' + visits.visIsCompleted + ')" id="patientsName"><b>' + patients.patFullName + '</b></span><br><span id="patientsMrnNo"><b><small>' + patients.patMrnNo + '</small></b></span><br><span id="patientsAge"><small>' + patients.patAge + ' / ' + patients.patGender + '</small></span><br><input id="patRid" value="' + visits.visPatRid + '" type="hidden"></a></div>');
                            }
                        });
                    }
                });
            }
        });
    }
    function getDiagnosis(visRid) {
        var searchObj = {"id": visRid};
        dcomethealth.PatientResource.searchVisit(searchObj, function (data) {
            if (!!data) {
                $.each(data, function (pIdx, visits) {
                    $('#visDiagnosis').val(visits.visDiagnosis);
                });
            }
        });
    }
    function getTreatmentData(visRid) {
        var searchObj = {"id": visRid};
        dcomethealth.PatientResource.searchVisit(searchObj, function (data) {
            if (!!data) {
                $.each(data, function (pIdx, visits) {
                    $('#treatText').val(visits.visTreatment);
                });
            }
        });
    }
    function autocompleteService() {
        $("#" + id + " input[name='serviceName']").autocomplete({
            select: function (event, ui) {
                dynTableGetNodeInRow(this, 'serviceName').value = ui.item.value;
                dynTableGetNodeInRow(this, 'itemRID').value = ui.item.data.id;
                if (!!ui.item.data.bsGroup && ui.item.data.bsGroup != 'undefined') {
                    dynTableGetNodeInRow(this, 'serReqItemGroupRid').value = ui.item.data.bsGroup;
                }
                if (!!ui.item.data.bPrice && ui.item.data.bPrice != 'undefined') {
                    dynTableGetNodeInRow(this, 'servicePrice').value = ui.item.data.bPrice;
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
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
                });
            },
            minLength: 1
        });
    }
    function autoCompleteDrug() {
        $("#" + id + " input[name='drugName']").autocomplete({
            select: function (event, ui) {
                dynTableGetNodeInRow(this, 'drugName').value = ui.item.value;
                dynTableGetNodeInRow(this, 'SkuRID').value = ui.item.data.id;
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
    function searchCompleted(visRid, visPatRid) {
        var count = 1;
        var tables = $.fn.dataTable.fnTables(true);
        $(tables).each(function () {
            $(this).dataTable().fnClearTable();
            $(this).dataTable().fnDestroy();
        });
        $("#treatTbody").empty();
//         var searchObj = {"soFromDate": moment(date[0]), "soToDate": moment(date[1]).add(1, 'days'), "soPatientRID": visPatRid};
        var searchObj = {"soVisitRID": visRid, "soPatientRID": visPatRid};
        dcomethealth.ServiceOrderResource.searchServiceOrder(searchObj, function (data) {
            if (!!data) {
                $.each(data, function (Idx, sO) {
                    $.each(dcomethealth.dDictVal, function (index, val) {
                        if (sO.soSerMode == val.id) {
                            if (val.ddictValue != 'Laboratory') {
                                var soDate = moment(sO.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY');
                                if (soDate == $("#activeVisitDate").val()) {
                                    count = count + Idx;
                                    $("#treatTbody").append('<tr><input type="hidden" id="soPatRid" value="' + sO.soPatientRID + '"/>\n\
                                    <td><output id="serviceName">' + sO.soItemName + '</output></td>\n\
                                    <td><output id="serDate">' + soDate + '</output></td><td>---</td></tr>');
                                    $("#treatTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                                    $('.dataTables_length select').addClass('form-control');
                                }
                            }
                        }
                    });
                });
            }
            $("#tCom").text((count == 1 ? 1 : (count + 1)));
            count = 0;
        });
        var searchParam = {"lrhPatientID": visPatRid}; //"lrhPatientVisitID": visRid,
        dcomethealth.LaboratoryResource.searchLabResults(searchParam, function (data) {
            if (!!data) {
//                $("#treatTbody").empty();
                $.each(data, function (Idx, lab) {
                    $.each(lab.labResultDs, function (indx, labResultD) {
                        var labDate = moment(lab.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY');
                        if (labDate == $("#activeVisitDate").val()) {
                            count = count + indx;
                            var Print = '<a class="btn btn-success btn-sm hidden-xs" onclick="dcomethealth.Patients.labPrint(' + lab.id + ',' + lab.lrhDoctorRid + ',' + labResultD.lrdServiceGroupRid + ')"><i class="fa fa-print"></i></a>';
                            $("#treatTbody").append('<tr><input type="hidden" id="labPatRid" value="' + lab.lrhPatientMrn + '"/>\n\
                            <td><output id="labServiceName">' + labResultD.lrdNServName + '</output></td>\n\
                            <td><output id="labServiceDate">' + labDate + '</output></td><td>' + Print + '</td></tr>');
                            $("#treatTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                            $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                            $('.dataTables_length select').addClass('form-control');
                        }
                    });
                });
            }
        });
    }
    function setServiceValue(servId, servName, servPrice, servGroup) {
        var table = document.getElementById('serviceOrderTable');
        var isExist = false;
        servGroup = (servGroup == "undefined" || servGroup == "") ? 0 : servGroup;
        for (var i = 0; i < table.rows.length - 1; i++) {
            var serviceName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
            var x = dynTableRow(dynTableGetNodeInRow(table.rows[i + 1]));
            dynTableCloneRow('serviceOrderTable', x);
            if (serviceName == '') {
                dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value = servName;
                dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value = servId;
                dynTableGetNodeInRow(table.rows[i + 1], 'serReqItemGroupRid').value = servGroup;
                dynTableGetNodeInRow(table.rows[i + 1], "qtyService").value = "1";
                dynTableGetNodeInRow(table.rows[i + 1], "processServiceOn").value = moment().format("DD-MM-YYYY");
                if (!!servPrice && servPrice != 'undefined') {
                    dynTableGetNodeInRow(table.rows[i + 1], 'servicePrice').value = servPrice;
                }
            }
            if (!!serviceName && dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value == servId) {
                isExist = true;
                alert("Service Already Added");
                return false;
            }
        }
        if (serviceName != '' && !isExist) {
            var newRow = dynTableAppendRow('serviceOrderTable');
            dynTableGetNodeInRow(newRow, "serviceName").value = servName;
            dynTableGetNodeInRow(newRow, "itemRID").value = servId;
            dynTableGetNodeInRow(newRow, "serReqItemGroupRid").value = servGroup;
            dynTableGetNodeInRow(newRow, "serviceReqDId").value = "";
            if (!!servPrice && servPrice != 'undefined') {
                dynTableGetNodeInRow(newRow, 'servicePrice').value = servPrice;
            }
            dynTableGetNodeInRow(newRow, "servicePrice").value = servPrice;
            dynTableGetNodeInRow(newRow, "qtyService").value = "1";
            dynTableGetNodeInRow(newRow, "qtyService").style.cursor = "not-allowed";
            dynTableGetNodeInRow(newRow, "qtyService").readOnly = "true";
            dynTableGetNodeInRow(newRow, "processServiceOn").value = moment().format("DD-MM-YYYY");
            dynTableGetNodeInRow(newRow, "processServiceOn").style.cursor = "not-allowed";
            dynTableGetNodeInRow(newRow, "processServiceOn").readOnly = "true";
            dynTableGetNodeInRow(newRow, "minus").className = 'ace-icon fa fa-minus';
            autocompleteService();
        }
    }
    function getServiceList() {
        $("ul#serviceMenu").empty();
        var serviceNameList = $.grep(serviceList, function (serviceMaster) {//for autocomplete
            var regService = new RegExp($("#patService").val(), 'gi');
            return (serviceMaster.bsName).match(regService);
//                return servName.includes($("#patService").val());
        });
        $.each(serviceNameList, function (index, servMaster) {
            $("ul#serviceMenu").append('<li class="serviceList"><a href="#div6" style="cursor: pointer;" onclick="dcomethealth.Patients.setServiceValue(' + servMaster.id + ',\'' + servMaster.bsName + '\',' + servMaster.bPrice + ',' + servMaster.bsGroup + ')" >' + servMaster.bsName + '</a></li>');
        });
    }
    function getCategoryWiseService() {
        var bsUnitType = null;
        if (parseInt($("#patSpeciality").val()) == 10 || parseInt($("#patSpeciality").val()) == 4) {
            bsUnitType = 36;
        } else if (parseInt($("#patSpeciality").val()) == 20) {
            bsUnitType = 340;
        } else if (parseInt($("#patSpeciality").val()) == 8) {
            bsUnitType = 347;
        }
        if (!!bsUnitType && bsUnitType != "undefined") {
            var searchParams = {"bsServiceType": bsUnitType, "bsServiceActive": 1};
            dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                serviceList = [];
                if (!!data && data.length > 0) {
                    $("ul#serviceMenu").show();
                    $("#patService").removeClass("hidden");
                    $("ul#serviceMenu").empty();
                    $.each(serviceList = data, function (index, servMaster) {
                        $("ul#serviceMenu").append('<li class="serviceList"><a href="#div6" style="cursor: pointer;" onclick="dcomethealth.Patients.setServiceValue(' + servMaster.id + ',\'' + servMaster.bsName + '\',' + servMaster.bPrice + ',' + servMaster.bsGroup + ')" >' + servMaster.bsName + '</a></li>');
                    });
                } else {
                    $("ul#serviceMenu").hide();
                    $("#patService").addClass("hidden");
                }
            });
        } else {
            $("ul#serviceMenu").hide();
            $("#patService").addClass("hidden");
        }
    }
    function finalOpticalPrint() {
        var pAgeandGender = [];
        dcomethealth.DataDictionaryResource.searchTemplate({"tempType": "FoPrescription"}, function (data) {
            if (!!data) {
                $.each(data, function (index, template) {
                    $("#fopDiv").html(template.tempNodes);
                    $("#fopDiv").find("span#pName").text($("#patName").text());
                    $("#fopDiv").find("span#pMrn").text($("#patMRN").val());
                    pAgeandGender = ($("#patDemoInfo").text()).split(',');
                    $("#fopDiv").find("span#pGender").text(pAgeandGender[0].substring(8).trim());
                    $("#fopDiv").find("span#pAge").text(pAgeandGender[1].substring(5, 8).trim());
                    $.each(staffList, function (idx, staff) {
                        if (parseInt($('#activeDoctorId').val()) == parseInt(staff.id)) {
                            $("#fopDiv").find("span#doctName").text(staff.staffName);
                        }
                    });
                    $("#fopDiv").find("span#datTime").text(moment().format('DD-MM-YYYY hh:mm'));
                    var patRID = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
                    var searchObj = {"vistVisitRID": $('#activeVisitRid').val(), "vistPatRID": patRID}, isCheck = false;
                    dcomethealth.ClinicalResource.searchVisitTemplate(searchObj, function (data) {
                        if (!!data) {
                            $("#printFop").removeClass('hidden');
                            $.each(data, function (index, visitTemplate) {
                                var templateData = jQuery.parseJSON(visitTemplate.vistNodes);
                                dcomethealth.template.setValue(templateData);
                                isCheck = true;
                            });
                            if (isCheck) {
                                exportExcel($("#fopDiv").html(), 'Fop');
                            }
                        } else {
                            $("#printFop").addClass('hidden');
                        }
                    });
                });
            }
        });
    }
    function labPrint(val, doctorRid, serviceGroupRid) {
        dcomethealth.LaboratoryResource.getLabPrint({"id": val}, serviceGroupRid, function (data) {
            if (!!data) {
                $("#printDiv").html(data);
                $('#doctorSign').attr('src', '/health_web/rest/master/v1/dcoctorSign/img?id=' + doctorRid);
                $("#entLogo1").attr('src', dcomethealth.image);
                exportExcel(data, 'Lab');
            }
        });
    }
    function csPrint() {
        var visitRid = !!$('#activeVisitRid').val() ? $('#activeVisitRid').val() : 0;
        var doctorRid = !!$('#activeDoctorId').val() ? $('#activeDoctorId').val() : 0;
        var check = false;
        dcomethealth.PatientResource.getCsPrint({"id": visitRid}, function (data) {
            if (!!data) {
                $("#csPrintDiv").html(data);
                $('#doctorSign').attr('src', '/health_web/rest/master/v1/dcoctorSign/img?id=' + doctorRid);
                $("#entLogo").attr('src', dcomethealth.image);
                $.each(staffList, function (idx, staff) {
                    if (parseInt($('#activeDoctorId').val()) == parseInt(staff.id)) {
                        $("span#doctorNamePrint").text(staff.staffName);
                        check = true;
                    }
                });
                if (check) {
                    exportExcel($("#csPrintDiv").html(), '');
                }
            }
        });
    }
    function exportExcel(html, printFrom) {
        var printWindow = window.open();
        printWindow.document.write(html);
        //                printWindow.onload = function () { // necessary if the div contain images//           
        setTimeout(function () {
            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }, 800);
        if (printFrom == 'Lab') {
            dcomethealth.util.loadpage('Patients');
            dcomethealth.util.base_init();
        }
    }
    function getClinicalSummary(visRid) {
        if ($('#dyn_table_download tbody#fileDownload_tbody').children().length == 0) { // For Table Head view if it's length != 0
            $("#fileDownload_Header").addClass('hidden');
        } else {
            $("#fileDownload_Header").removeClass('hidden');
        }
        var searchQuery = {"id": visRid};
        dcomethealth.PatientResource.searchVisit(searchQuery, function (data) {
            if (!!data) {
                CKEDITOR.instances.visCsNodes.setData('');
                $.each(data, function (pIdx, visit) {
                    $("#sCameDate").text(visit.visDate);
                    CKEDITOR.instances.visCsNodes.setData(visit.visCsNodes);
                });
            } else {
                CKEDITOR.instances.visCsNodes.setData('');
            }
            $("#fileDownload_tbody").empty();
            var searchParams = {"taRefRid": visRid, "taType": 0};
            dcomethealth.FileResource.searchFile(searchParams, function (data) {
                if (!!data) {
                    $("#fileDownload_Header").removeClass('hidden');
                    $.each(data, function (pIdx, file) {
                        $("#fileDownload_tbody").append('<tr><td class="row"><td><input id=taFileName name=taFileName style="cursor: not-allowed;" value="' + file.taFileName + '" class="" disabled>\n\
                    <input id=fileUploadId name=fileUploadId type="hidden" value="' + file.id + '"><td><a class="btn btn-dulab1 btn-dulab1-sm jqueryUIToolTip col-md-12" title="download File" id=download  href="/health_web/rest/file/download/file?id=' + file.id + "&type=" + 0 + '"><i class="fa fa-download"></i></a></tr>');
                        $("#tafileName").val(file.taFileName);
                    });
                } else {
                    $("#fileDownload_Header").addClass('hidden');
                }
            });
        });
    }
    function getVisitPatient(visPatRid, visitCheck) {
        var searchParams = {"id": visPatRid};
        dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
            $.each(data, function (idx, patient) {
                $("#patRid").val(patient.id);
                $("#patName").text(patient.patFullName);
                $("#patMRN").val(patient.patMrnNo);
                $("#patDemoInfo").text("(Gender: " + patient.patGender + ", Age: " + patient.patAge + ")");
            });
            $("#completeBtn").removeClass("hidden");
            loadPatientVisitTab(visPatRid, visitCheck);
            getLedgerDetails(visPatRid); //To Load Ledger while click Ledger Menu
//            callNavigation('nextBtn');
        });
    }
    function loadPatientVisitTab(visPatRid, visitCheck) {
        $("#backPanel").removeClass('hidden');
        $("#frontPanel").addClass('hidden');
//        if (!!$("#patRidSearch").val()) {
//            $('#saveComplaints ,#saveHistory ,#saveVitals ,#saveDiagnosis ,#saveClinical ,#saveService ,#saveRxOrder ,#saveNursing ,#saveAdmtPatient ,#saveFiles').hide();
//        } else {
//            $('#saveComplaints ,#saveHistory ,#saveVitals ,#saveDiagnosis ,#saveClinical ,#saveService ,#saveRxOrder ,#saveNursing ,#saveAdmtPatient ,#saveFiles').show();
//        }
//        var totalLen = 0;
        var search = {"visPatRid": visPatRid, "sortOrder": ["createdDateTime"], "sortDesc": "desc"};
        dcomethealth.PatientResource.searchVisit(search, function (visits) {
            if (!!visits) {
                $("#patient_visit_tab").empty();
//                totalLen = visits.length;
                $.each(visits, function (pIdx, visit) {
                    $('#activeVisitRid').val(visit.id);
                    if (pIdx == visits.length - 1) {
                        $('#activeSpecialityIndex').val(visit.visSpecialityIndex);
                    }
                    $("#sCameDate").text(visit.visDate);
                    $.each(dcomethealth.dDictVal, function (index, val) {
                        if (parseInt($('#activeSpecialityIndex').val()) == val.id) {
                            if (val.ddictValue == 'Dentistry') {
                                $("#pastDental").removeClass("hidden");
                                $("#pastOpthalmic").addClass('hidden');

                                $("#glaucomaDivFH").hide();
                                $("#glaucomaDivMH").hide();
                            }
                            if (dcomethealth.loginuser.entityRid != 5) {
                                if (val.ddictValue == 'Ophthalmology') {
                                    $("#pastOpthalmic").removeClass("hidden");
                                    $("#pastDental").addClass("hidden");

                                    $("#glaucomaDivFH").show();
                                    $("#glaucomaDivMH").show();
                                    //glaucomaDivFH glaucomaDivMH
                                }
                            }
                        }
                    });
//                    if (pIdx == (visits.length - 1)) {
//                        $("#nextBtn").css({'cursor': 'not-allowed'}).attr('readOnly', true);
//                        $('#visitDateBtn').append('<a href="#visit1" onclick="dcomethealth.Patients.loadPatientVisit(this,' + visit.id + ',' + visit.visPatRid + ',' + visit.visSpecialityIndex + ',' + visit.visConsDocRid + ')">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '<input id="visDate" name="visDate" class="" type="hidden" value="' + visit.visDate + '"></a>'); //<input id="visSpecialityIndex" name="visSpecialityIndex" class="" type="hidden" value="' + (!!visit.visSpecialityIndex ? visit.visSpecialityIndex : 0) + '">
//                    }
                    $('#patient_visit_tab').append('<li onclick="dcomethealth.Patients.loadPatientVisit(this,' + visit.id + ',' + visit.visPatRid + ',' + visit.visSpecialityIndex + ',' + visit.visConsDocRid + ',' + visitCheck + ')" name="visitedLink"><a href="#visit1" data-toggle="tab">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</a><input id="visDate" name="visDate" class="" type="hidden" value="' + visit.visDate + '"/><input id="checkValue" name="checkValue" class="" type="hidden" value="1"/></li>'); //<input id="visSpecialityIndex" name="visSpecialityIndex" class="" type="hidden" value="' + (!!visit.visSpecialityIndex ? visit.visSpecialityIndex : 0) + '">
                    if (pIdx == (visits.length - 1)) {
                        loadPatientVisit(this, visit.id, visit.visPatRid, visit.visSpecialityIndex, visit.visConsDocRid, visitCheck);
                    }
                });
//                if (totalLen >= 10) { // For go to next,previous visit 
//                    $('#patient_visit_tab li:nth-child(1)').append('<li style="margin-top:-7px;"><a href="#" id="prev" onclick="movePage()"><i class="fa fa-arrow-left"></i></a></li>');
//                    $('#patient_visit_tab').last().append('<li><a href="#" id="next" onclick="movePage()"><i class="fa fa-arrow-right"></i></a></li>');
//                }
            } else {
                $('#activeVisitRid').val(''); //New Appointments visitRid is Empty
            }
        });
    }
//    function callNavigation(btnId) {
//        var search = {"visPatRid": $("#patRid").val(), "sortOrder": ["createdDateTime"], "sortDesc": "desc"};
//        dcomethealth.PatientResource.searchVisit(search, function (data) {
//            if (!!data) {
//                $.each(data, function (pIdx, visit) {
//                    $('#activeVisitRid').val(visit.id);
//                    $('#activeSpecialityIndex').val(visit.visSpecialityIndex);
//                    $("#sCameDate").text(visit.visDate);
//                    $.each(dcomethealth.dDictVal, function (index, val) {
//                        if (parseInt(visit.visSpecialityIndex) == val.id) {
//                            if (val.ddictValue == 'Dentistry') {
//                                $("#pastDental").removeClass("hidden");
//                                $("#pastOpthalmic").addClass('hidden');
//                                $("#glaucomaDivFH").hide();
//                                $("#glaucomaDivMH").hide();
//                            }
//                            if (val.ddictValue == 'Ophthalmology') {
//                                $("#pastOpthalmic").removeClass("hidden");
//                                $("#pastDental").addClass("hidden");
//                                $("#glaucomaDivFH").show();
//                                $("#glaucomaDivMH").show();
//                            }
//                        }
//                    });
//                    if (btnId == 'nextBtn') {
//                        $('#visitDateBtn').empty();
//                        if (pIdx == (data.length - 1)) {
//                            $("#nextBtn").css({'cursor': 'not-allowed'}).attr('readOnly', true);
//                            $('#visitDateBtn').append('<a href="#visit1" id="nextDate" onclick="dcomethealth.Patients.loadPatientVisit(this,' + visit.id + ',' + visit.visPatRid + ',' + visit.visSpecialityIndex + ',' + visit.visConsDocRid + ')">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '<input id="visDate" name="visDate" class="" type="hidden" value="' + visit.visDate + '"></a>'); //<input id="visSpecialityIndex" name="visSpecialityIndex" class="" type="hidden" value="' + (!!visit.visSpecialityIndex ? visit.visSpecialityIndex : 0) + '">
//                        }
//                    }
//                    if (btnId == 'prevBtn') {
//                        alert($("#nextDate").text());
//                        $('#visitDateBtn').empty();
//                        var nextDate = $("#nextDate").text();
//                        var prevDate = moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY');
////                        alert(prevDate + "||" + nextDate);
//                        if (nextDate < prevDate) {
//                            console.log("prevoius");
//                            $("#nextBtn").css({'cursor': 'pointer'}).attr('readOnly', false);
//                            $('#visitDateBtn').append('<a href="#visit1" id="prevDate" onclick="dcomethealth.Patients.loadPatientVisit(this,' + visit.id + ',' + visit.visPatRid + ',' + visit.visSpecialityIndex + ',' + visit.visConsDocRid + ')">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '<input id="visDate" name="visDate" class="" type="hidden" value="' + visit.visDate + '"></a>');
//                        }
//                    }
//                });
//            }
//        });
//
//    }
//    
    function loadPatientVisit(e, visitRid, visPatRid, visitSpecialityIndx, visDocRid, visitCheck) {
        if ($(e).find("input[name='checkValue']").val() == undefined && $(e).find("input[name='checkValue']").val() != 1) {
            $("#activeVisitDate").val(e.visDate);
            $("#sCameDate").text(e.visDate);
        } else {
            if (e != 0) {
                $("#activeVisitDate").val($(e).find("input[name='visDate']").val());
                $("#sCameDate").text($(e).find("input[name='visDate']").val());
            }
        }
        $('#activeVisitRid').val(visitRid);
        $('#activeSpecialityIndex').val(visitSpecialityIndx);
        if (!!visDocRid && visDocRid != 'undefined') {
            $('#activeDoctorId').val(visDocRid);
        } else {
            $('#activeDoctorId').val('');
        }
        $("#totalDiv").find("div.select2-container").each(function (ix, div) {
            $(div).find("option").remove();//remove option overflow after div 
        });
//        if (visitCheck == 1) {
        $("#template").empty();
//        }
        if (!!visitSpecialityIndx) {
            dcomethealth.DataDictionaryResource.searchTemplate({"tempRID": visitSpecialityIndx}, function (templates) {
                if (!!templates) {
                    $("#template").removeClass("hidden");
                    $("#noTemplate").addClass("hidden");
                    $.each(templates, function (index, template) {
                        $("#template").append(template.tempNodes);
                        $("#tempType").val(template.tempType);
                        if (template.tempType == "Dental") {
                            $("#tempType").val(template.tempType);
                            dcomethealth.DataDictionaryResource.searchTemplate({"id": 11}, function (templates) {
                                $.each(templates, function (index, template) {
                                    $("#totalDiv").find("form").each(function (idx, inputId) {
                                        var id = $(inputId).attr("id");
                                        $("#" + id + "div").append(template.tempNodes);
                                    });
                                });
                            });
                        }
                        var searchObj = {"vistVisitRID": visitRid, "vistPatRID": visPatRid};
                        dcomethealth.ClinicalResource.searchVisitTemplate(searchObj, function (visitTemplate) {
                            if (!!visitTemplate) {
                                $.each(visitTemplate, function (index, t_visit_template) {
                                    var visitedDate = moment(t_visit_template.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY');
                                    if (e == 0) {
                                        $("#activeVisitDate").val(visitedDate);
                                    }
                                    $('#visitTempRID').val(t_visit_template.id);
                                    if (!!t_visit_template.vistNodes) {
                                        var visit_template_data = jQuery.parseJSON(t_visit_template.vistNodes);
                                        if (!!visit_template_data) {//&& visit_template_data.length > 0 for removed endrocronology 
                                            $.each(visit_template_data, function (idx, o) {
                                                if (template.tempType == "Dental") {
                                                    $("#tempType").val(template.tempType);
                                                    dcomethealth.DataDictionaryResource.searchTemplate({"tempRID": 229}, function (templates) {
                                                        $.each(templates, function (index, template) {
                                                            $("#" + o.id + "div").empty();
                                                            $("#" + o.id + "div").removeClass("hidden");
                                                            $("#" + o.id + "Sdiv").addClass("hidden");
                                                            $("#" + o.id + "div").append(template.tempNodes);
                                                            setValue(o.id, visit_template_data);
                                                        });
                                                    });
                                                }
                                            });
                                            if (template.tempType == "Diabetic") {
                                                dcomethealth.template.setValue(visit_template_data);
                                            }
                                            if (dcomethealth.loginuser.entityRid == 4) {
                                                if (template.tempType == "Ophthalmology") {
                                                    $("#opthalmicDiv").removeClass('hidden');
                                                    dcomethealth.template.setValue(visit_template_data);
                                                }
                                            }
                                        } else {
                                            $("#totalDiv").find("div.select2-container").each(function (ix, div) {
                                                $(div).find("option").remove();//remove option overflow after div 
                                            });
                                        }
                                    }
                                });
                            } else {
                                $('#visitTempRID').val('');
                            }
                        });
                    });
                } else {
                    $("#template").addClass("hidden");
                    $("#noTemplate").removeClass("hidden");
                    var searchObj = {"vistVisitRID": visitRid, "vistPatRID": visPatRid};
                    dcomethealth.ClinicalResource.searchVisitTemplate(searchObj, function (visitTemplate) {
                        if (!!visitTemplate) {
                            $.each(visitTemplate, function (index, t_visit_template) {
                                var visitedDate = moment(t_visit_template.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY');
                                if (e == 0) {
                                    $("#activeVisitDate").val(visitedDate);
                                }
                                $('#visitTempRID').val(t_visit_template.id);
                                if (!!t_visit_template.vistNodes) {
                                    CKEDITOR.instances.noTempNodes.setData(t_visit_template.vistNodes);
                                }
                            });
                        }
                    });
                }
            });
        }
//        if ($("#activeVisitDate").val() != moment().format('DD-MM-YYYY')) { // For current visit save 
//            $("#completeBtn").addClass('form-control');
//            $("#completeBtn").attr('disabled',true);
//        } else {
//            $("#completeBtn").removeClass('form-control');
//            $("#completeBtn").attr('disabled',false);
//        }
        if (visitCheck == 1) {
            getHistory(visitRid);
            getClinicalSummary(visitRid);
            getVisitVitals(visitRid);
            getSymptoms(visitRid);
            getService(visitRid);
            getrxOrder(visitRid);
            getFiles(visitRid);
            searchCompleted(visitRid, visPatRid);
            getDiagnosis(visitRid);
            getTreatmentData(visitRid);
        }
    }
    function getFiles(id) {
        dcomethealth.FileResource.getFileInfo(id, attRefType, function (data) {
            if (!!data) {
                dcomethealth.Pictures.loadImage(data, "image_containerImg", data.id);
            } else {
                $("#image_containerImg").empty();
                $("#resourceId").val("");
                return false;
            }
        });
    }
    function getService(id) {
        var searchobj = {"serReqOpVisitRid": id};
        dcomethealth.ServiceRequestResource.searchServiceRequest(searchobj, function (data) {
            $("#serviceOrderTbody").empty();
            var visDate = $("#activeVisitDate").val();
            if (!!data) {
                $.each(data, function (idx, serviceH) {
                    $("#serviceId").val(serviceH.serReqhId);
                    if (!!serviceH.serviceRequest) {
                        $.each(serviceH.serviceRequest, function (ifdx, serviceval) {//<span><small><a href="#">show Teeth</a></small></span>    <td><span id="addInstructions"><small><a href="#">add Instructions</a></small></span></td>                        
                            var itemPrice = !!serviceval.serReqItemPrice && (serviceval.serReqItemPrice != 'undefined') ? serviceval.serReqItemPrice : 0;
                            $("#serviceOrderTbody").append('<tr><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this,\'serviceOrderTable\')"></i></td>\n\
                                    <td><input type="text" id="serviceName" name="serviceName" value="' + serviceval.serReqItemName + '" class="col-md-12"/><input type="hidden" id="itemRID" value="' + serviceval.serReqItemRID + '"/><input type="hidden" id="serReqItemGroupRid" value="' + serviceval.serReqItemGroupRid + '"/><input type="hidden" id="serviceReqDId" value="' + serviceval.serReqRid + '"/><input type="hidden" id="servicePrice" value="' + itemPrice + '"/><br></td>\n\
                                    <td><input type="text" id="qtyService" style="cursor: not-allowed;"  value="' + serviceval.serReqItemQty + '" class="col-md-12" readOnly/></td>\n\
                                    <td><input type="text" id="processServiceOn" style="cursor: not-allowed;" value="' + moment(serviceval.createdDateTime).format('DD-MM-YYYY') + '" class="col-md-12" readOnly/></td>\n\\n\
                                    <td><i class="ace-icon fa fa-plus red" onclick="insert_servicerow(\'serviceOrderTable\', this)"></i></td></tr>');
                        });
                    }
                });
            } else {//<span><small><a href="#">show Teeth</a></small></span>  <td><span id="addInstructions"><small><a href="#">add Instructions</a></small></span></td>                
                $("#serviceOrderTbody").append('<tr><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this,\'serviceOrderTable\')"></i></td>\n\
                                    <td><input type="text" id="serviceName" name="serviceName" value="" class="col-md-12"/><input type="hidden" id="itemRID" value=""/><input type="hidden" id="serReqItemGroupRid" value=""/><input type="hidden" id="serviceReqDId" value="0"/><input type="hidden" id="servicePrice" value="0"/><br></td>\n\
                                    <td><input type="text" id="qtyService" value="1" style="cursor: not-allowed;" class="col-md-12" readOnly/></td>\n\
                                    <td><input type="text" id="processServiceOn" style="cursor: not-allowed;" value="' + visDate + '" class="col-md-12" readOnly/></td>\n\
                                    <td><i class="ace-icon fa fa-plus red" onclick="insert_servicerow(\'serviceOrderTable\', this)"></i></td></tr>');
                autocompleteService();
            }
        });
    }
    function setCompleted() {
        var visit = {};
        if (fileList.length > 0) {
            if (!fileCheck) {
                alert("Upload Attached File");
                return false;
            }
        }
        visit.id = !!$('#activeVisitRid').val() ? $('#activeVisitRid').val() : null;
        visit.visIsCompleted = 1;
        var patRID = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
        visit.visPatRid = patRID;
        visit.visDate = !!$('#activeVisitDate').val() ? $('#activeVisitDate').val() : moment().format('DD-MM-YYYY');
//        visit.visTypeIndex = $("#visitType").val();
        visit.visSubTypeIndex = 0; // Need to change the value;
        visit.visPatType = 0; // Need to check and change it.
        visit.visApptRid = 0; // passing 0 because its a direct OP registration
        visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
//        visit.visReasonIndex = $("#visitReason").val();
        visit.visSpecialityIndex = !!$('#activeSpecialityIndex').val() ? $('#activeSpecialityIndex').val() : 0;
//        visit.visRemarks = $("#visitRemarks").val();
//        visit.visConsDocRid = $("#VDName option:selected").val();
        visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.
//        visit.visRefTypeIndex = $("#refType").val();
//        visit.visState = $('#activeVisitState').val();
        visit.visCsNodes = !!CKEDITOR.instances.visCsNodes.getData() ? CKEDITOR.instances.visCsNodes.getData() : null;
        dcomethealth.PatientResource.saveVisit(visit).done(function (data, textStatus, jqXHR) {
            submitSymptoms();
            saveHistory();
            submit();
            save();
            saveDiagnosis();
            submitService();
            submitRxOrder();
            saveTreatment();
            saveFiles();
            attachFiles();
            sendEmail(visit);
            if (textStatus == 'success') {
                showAlert(textStatus);
            }
            dcomethealth.util.loadpage('Patients');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Patient Completed Failed");
        });
    }
    function sendEmail(visit) {
        if ($('#sendEmailCheck').is(':checked')) {
            dcomethealth.PatientResource.sendEmail(visit).done(function (data, textStatus, jqXHR) {
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Sent Email Failed");
            });
        } else {
            return false;
        }
    }
    function showAlert(status) {
        if (status == 'success') {
            alert("Data Saved");
            return false;
        }
    }
    function getStatus(e) {
        $('div').find(".patientBox").each(function (idx, div) {
            if ($(div).hasClass("patientBox-warning")) {
                $(div).removeClass("hidden");
            }
            if (e.id == "today") {
                if ($(div).hasClass("patientBox-danger") || $(div).hasClass("patientBox-success")) {
                    $("#patientDiv").append(div);
                    $(div).show();
                } else {
                    if ($(div).hasClass("patientBox-warning")) {
                        $(div).hide();
                    }
                }
            } else if (e.id == "waiting") {
                if ($(div).hasClass("patientBox-danger")) {
                    $("#patientDiv").append(div);
                    $(div).show();
                }
                if ($(div).hasClass("patientBox-success")) {
                    $(div).hide();
                }
                if ($(div).hasClass("patientBox-warning")) {
                    $(div).hide();
                }
            } else if (e.id == "appointments") {
                if ($(div).hasClass("patientBox-warning")) {
                    $("#patientDiv").append(div);
                    $(div).show();
                }
                if ($(div).hasClass("patientBox-success")) {
                    $(div).hide();
                }
                if ($(div).hasClass("patientBox-danger")) {
                    $(div).hide();
                }
            } else {
                if ($(div).hasClass("patientBox-success")) {
                    $("#patientDiv").append(div);
                    $(div).show();
                }
                if ($(div).hasClass("patientBox-danger")) {
                    $(div).hide();
                }
                if ($(div).hasClass("patientBox-warning")) {
                    $(div).hide();
                }
            }
        });
    }
    function  getLedgerDetails(patRid) {
        patRid = (patSearch == true) ? $("#patRidSearch").val() : patRid;
        var searchObj = {"bhPatientRID": patRid};
        dcomethealth.BillingResource.searchBilling(searchObj, function (data) {
            if (!!data) {
                $("#ledgersTbody").empty();
                $.each(billList = data, function (pIdx, billData) {
                    $("#ledgersTbody").append('<tr><td><small>' + (billData.billH.bhBillDate == undefined ? "--" : billData.billH.bhBillDate) + '</small></td><td><small>Invoice</small></td><td><small>' + billData.billH.bhBillNo + '</small></td><td><small>' + billData.billH.bhGrossAmount + '</small></td><td><small>0.00</small></td><td><small>' + billData.billH.bhGrossAmount + '</small></td></tr>');
                    if (!!billData.billH.receiptH) {
                        $.each(billData.billH.receiptH, function (pIdx, receiptH) {
                            var balAmount = receiptH.rhTotalAmount - receiptH.rhPaidAmount;
                            $("#ledgersTbody").append('<tr><td><small>' + (receiptH.rhDate == undefined ? "--" : receiptH.rhDate) + '</small></td><td><small>Receipt</small></td><td><small>' + receiptH.rhNo + '</a>/<small></td><td><small>0.00</small></td><td><small>' + receiptH.rhPaidAmount + '</small></td><td><small>' + (parseInt(balAmount) == 0 ? "0.00" : balAmount) + '</small></td></tr>');
                        });
                    }
                });
            }
        });
    }
    function  getrxOrder(visitId) {
        var searchobj = {"drugReqHOpVBisitRID": visitId};
        dcomethealth.ItemOrderResource.getDrugs(searchobj, function (data) {
            $("#rxOrderTbody").empty();
            if (!!data) {
//                $("#drugprint").removeClass("hidden");
                var durationSelect = "", whenSelect = "";
                $.each(data, function (igx, drugH) {
                    $("#drugId").val(drugH.id);
                    $("#drugReqHfollowupDate").val(drugH.drugReqHfollowupDate);
                    $("#drugReqHComments").val(drugH.drugReqHComments);
                    $.each(drugH.drugRequestDList, function (igs, drugD) {//<span><small><a href="#">add Notes</a></small></span>
                        $.each(dcomethealth.dDictVal, function (index, val) {
                            if (parseInt(drugD.drugReqDDurationTime) == val.id) {
                                durationSelect = val.ddictValue;
                            }
                            if (parseInt(drugD.drugReqDDosageTime) == val.id) {
                                whenSelect = val.ddictValue;
                            }
                        });
                        $("#rxOrderTbody").append('<tr id="rxOrders' + igs + '"><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this,\'rxOrderTable\')"></i></td>\n\
                        <td><input type="text" id="drugName" name="drugName" value="' + drugD.drugReqDItemName + '" class="col-md-12"/><input type="hidden" id="SkuRID" value="' + drugD.drugReqDItemRID + '" name="SkuRID"/><input type="hidden" id="drugHRid" value="' + drugD.druReqDSrhRID + '" name="drugHRid"/><input type="hidden" id="drugDid" value="' + drugD.id + '" name="drugDid"/><br></td>\n\
                        <td><input type="text" id="strength" class="col-md-12"/></td>\n\
                        <td><select id="strengthSelect" class="col-md-12"><option>mg</option></select></td>\n\
                        <td><input type="text" id="duration" class="col-md-12"/></td>\n\
                        <td><select id="durataionSelect" class="col-md-12 dcomet-c-s_ddict_duration-list"><option value="' + drugD.drugReqDDurationTime + '">' + durationSelect + '</option></select></td>\n\
                        <td><select id="whenSelect" class="col-md-12 dcomet-c-s_ddict_dosage_time-list"><option value="' + drugD.drugReqDDosageTime + '">' + whenSelect + '</option></select></td>\n\
                        <td><input type="text" id="morning" value="' + (!!parseInt(drugD.drugReqDMrng) == 0 ? '' : drugD.drugReqDMrng) + '" class="col-md-12"/></td>\n\
                        <td><input type="text" id="afternoon" value="' + (!!parseInt(drugD.drugReqDAfternoon) == 0 ? '' : drugD.drugReqDAfternoon) + '" class="col-md-12"/></td>\n\
                        <td><input type="text" id="evening" value="' + (!!parseInt(drugD.drugReqDEve) == 0 ? '' : drugD.drugReqDEve) + '" class="col-md-12"/></td>\n\
                        <td><input type="text" id="night" value="' + (!!parseInt(drugD.drugReqDNight) == 0 ? '' : drugD.drugReqDNight) + '" class="col-md-12"/></td>\n\
                        <td><input type="text" id="drugQty" value="' + drugD.drugReqDItemQty + '" class="col-md-12"/></td>\n\
                        <td><i class="ace-icon fa fa-plus red" onclick="insert_rxrow(\'rxOrderTable\', this)"></i></td></tr>');
                        dcomethealth.datatypes.init($("#rxOrderTbody"));
                    });
                });
            } else {
                $("#drugprint").addClass("hidden");
                $("#drugId").val('');
                $("#rxOrderTbody").append('<tr id="rxOrders"><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this, \'rxOrderTable\')"></i></td>\n\
                <td><input type="text" id="drugName" name="drugName" value="" class="col-md-12"/><input type="hidden" id="SkuRID" value="" name="SkuRID"/><input type="hidden" id="drugHRid" value="" name="drugHRid"/><input type="hidden" id="drugDid" value="" name="drugDid"/><br></td>\n\
                <td><input type="text" id="strength" class="col-md-12"/></td>\n\
                <td><select id="strengthSelect" class="col-md-12"><option>mg</option></select></td>\n\
                <td><input type="text" id="duration" class="col-md-12"/></td>\n\
                <td><select id="durataionSelect" name="duration" class="col-md-12 dcomet-c-s_ddict_duration-list"></select></td>\n\
                <td><select id="whenSelect" name="when" class="col-md-12 dcomet-c-s_ddict_dosage_time-list"></select></td>\n\
                <td><input type="text" id="morning" value="" class="col-md-12"/></td>\n\
                <td><input type="text" id="afternoon" value="" class="col-md-12"/></td>\n\
                <td><input type="text" id="evening" value="" class="col-md-12"/></td>\n\
                <td><input type="text" id="night" value="" class="col-md-12"/></td>\n\
                <td><input type="text" id="drugQty" value="" class="col-md-12"/></td>\n\
                <td><i class="ace-icon fa fa-plus red" onclick="insert_rxrow(\'rxOrderTable\', this)"></i></td></tr>');
                autoCompleteDrug();
                dcomethealth.datatypes.init($("#rxOrderTbody"));
            }
        });
    }
    function getVisitVitals(val) {
        var search = {"vitVisRID": val};
        dcomethealth.ClinicalResource.searchVisitVitals(search, function (data) {
            if (!!data) {
                $("#height,#weight,#blood,#heart,#respiratory,#bodyTemp").removeClass("hidden");
                $.each(data, function (index, visitVital) {
                    var obj = jQuery.parseJSON(visitVital.vitValue);
                    $("#visitVitalId").val(visitVital.id);
                    $("#od_vital_height").val(!!obj.od_vital_height ? obj.od_vital_height : '');
                    $("#od_vital_height_unit").val(!!obj.od_vital_height_unit ? obj.od_vital_height_unit : '');
                    $("#od_vital_weight").val(!!obj.od_vital_weight ? obj.od_vital_weight : '');
                    $("#od_vital_weight_unit").val(!!obj.od_vital_weight_unit ? obj.od_vital_weight_unit : '');
                    $("#od_vital_blood_pressure").val(!!obj.od_vital_blood_pressure ? obj.od_vital_blood_pressure : '');
                    $("#od_vital_blood_pressure_unit").val(!!obj.od_vital_blood_pressure_unit ? obj.od_vital_blood_pressure_unit : '');
                    $("#od_vital_heart_rate").val(!!obj.od_vital_heart_rate ? obj.od_vital_heart_rate : '');
                    $("#od_vital_heart_rate_unit").val(!!obj.od_vital_heart_rate_unit ? obj.od_vital_heart_rate_unit : '');
                    $("#od_vital_resporatory_rate").val(!!obj.od_vital_resporatory_rate ? obj.od_vital_resporatory_rate : '');
                    $("#od_vital_body_temp").val(!!obj.od_vital_body_temp ? obj.od_vital_body_temp : '');
                    $("#od_vital_body_temp_unit").val(!!obj.od_vital_body_temp_unit ? obj.od_vital_body_temp_unit : '');
                    $("#od_vital_bmi").val(!!obj.od_vital_bmi ? obj.od_vital_bmi : '');
                    $("#od_vital_bmi_unit").val(!!obj.od_vital_bmi_unit ? obj.od_vital_bmi_unit : '');
                    computeBMI();
                });
            } else {
                $("#height,#weight,#blood,#heart,#respiratory,#bodyTemp").removeClass("hidden");
                $("#visitVitalId,#od_vital_height,#od_vital_weight,#od_vital_blood_pressure,#od_vital_heart_rate,#od_vital_resporatory_rate,#od_vital_body_temp,#od_vital_bmi").val('');
            }
        });
    }
    function getSymptoms(visitRid) {
        var searchObj = {"cplVisitRID": visitRid};
        dcomethealth.ClinicalResource.getComplaints(searchObj, function (data) {
            $("#complaintsTbody").empty();
            if (!!data) {
                var durationSelect = "", currentStatus = "";
                $.each(data, function (pIdx, comp) {//<span><small><a href="#">show Teeth</a></small></span>  <td><span id="addnotes"><small><a href="#">add Notes</a></small></span></td>
                    $.each(dcomethealth.dDictVal, function (index, val) {
                        if (parseInt(comp.cplDescRangeIndex) == val.id) {
                            durationSelect = val.ddictValue;
                        }
                        if (parseInt(comp.cplCurrentStatusIndex) == val.id) {
                            currentStatus = val.ddictValue;
                        }
                    });
                    $("#complaintsTbody").append('<tr id="pd_complaints_tbody' + pIdx + '"><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this, \'complaintsTable\')"></i></td>\n\
                    <td><input type="text" id="complaintsValue" value="' + comp.cplName + '" class="col-md-12" /><br><input type="hidden" id="compId" value="' + comp.id + '"/></td>\n\
                    <td><input type="text"  id="complaintsDurations" value="' + comp.cplNameIndex + '" class="col-md-12" onkeypress="return dcomethealth.validation.isNumberKey(event, this)"/></td>\n\
                    <td><select id="compDurataionSelect" name="compDurataionSelect" class="col-md-12"><option value="' + comp.cplDescRangeIndex + '">' + durationSelect + '</option></select></td>\n\
                    <td><select id="compCurrentStatus" name="compCurrentStatus" class="col-md-12"><option value="' + comp.cplCurrentStatusIndex + '">' + currentStatus + '</option></select></td>\n\
                    <td><i class="ace-icon fa fa-plus red" onclick="insert_row(\'complaintsTable\', this)"></i></td></tr>');
//                    dcomethealth.datatypes.init($("#pd_complaints_tbody" + pIdx));
//                                 onclick="dcomethealth.Patients.getComplaints();"
//                    $('select[name="compDurataionSelect"]:eq(' + pIdx + ')').val(comp.cplDescRangeIndex);
//                    $('select[name="compCurrentStatus"]:eq(' + pIdx + ')').val(comp.cplCurrentStatusIndex); //                    onclick="dcomethealth.Patients.getComplaints();"

                });
            } else {//<span><small><a href="#">show Teeth</a></small></span>   <td><span id="addnotes"><small><a href="#">add Notes</a></small></span></td>
                $("#complaintsTbody").append('<tr id="pd_complaints_tbody"><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this, \'complaintsTable\')"></i></td>\n\
                 <td><input type="text" id="complaintsValue" value="" class="col-md-12" /><br><input type="hidden" id="compId" value=""/></td>\n\
                 <td><input type="text"  id="complaintsDurations" value="" class="col-md-12" onkeypress="return dcomethealth.validation.isNumberKey(event, this)"/></td>\n\
                 <td><select id="compDurataionSelect" name="compDurataionSelect" class="col-md-12 dcomet-c-s_ddict_duration-list"><option value="0"></option></select></td>\n\
                 <td><select id="compCurrentStatus" name="compCurrentStatus" class="col-md-12 dcomet-c-s_ddict_complaint_status-list"><option value="0"></option></select></td>\n\
                 <td><i class="ace-icon fa fa-plus red" onclick="insert_row(\'complaintsTable\', this)"></i></td></tr>');
                dcomethealth.datatypes.init($("#complaintsTbody"));
            }
        });
    }
    function viewLedger(val) {
//        $("#patient_visit_tab").empty();
        if (val == 1) {
            $("#ledgersTbody").empty();
            $.each(billList, function (pIdx, billData) {
                $("#ledgersTbody").append('<tr><td><small>' + (billData.billH.bhBillDate == undefined ? "--" : billData.billH.bhBillDate) + '</small></td><td><small>Invoice</small></td><td><small>' + billData.billH.bhBillNo + '</small></td><td><small>' + billData.billH.bhGrossAmount + '</small></td><td><small>0.00</small></td><td><small>' + billData.billH.bhGrossAmount + '</small></td></tr>'); //<td><small>Print</small></td>
            });
        } else if (val == 2) {
            $("#ledgersTbody").empty();
            $.each(billList, function (pIdx, bill) {
                $.each(bill.billH.receiptH, function (pIdx, receiptH) {
                    var balAmount = receiptH.rhTotalAmount - receiptH.rhPaidAmount;
                    $("#ledgersTbody").append('<tr><td><small>' + (receiptH.rhDate == undefined ? "--" : receiptH.rhDate) + '</small></td><td><small>Receipt</small></td><td><small>' + receiptH.rhNo + '</small></td><td><small>0.00</small></td><td><small>' + receiptH.rhPaidAmount + '</small></td><td><small>' + (parseInt(balAmount) == 0 ? "0.00" : balAmount) + '</small></td></tr>'); //<td><small>Print</small></td><a href=""></a>
                });
            });
        } else if (val == 3) {
            var patRid = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
            var searchObj = {"adPayerRID": patRid};
            dcomethealth.AdvanceResource.searchAdvances(searchObj, function (advances) {
                $("#ledgersTbody").empty();
                if (!!advances) {
                    $.each(advances, function (pIdx, advance) {
                        var advanceDate = advance.createdDateTime.toString();
                        $("#ledgersTbody").append('<tr><td><small>' + (advanceDate.substring(0, 10)) + '</small></td><td><small>Advance</small></td><td><small>' + advance.adNo + '</small></td><td><small>' + advance.adAmount + '</small></td><td><small>0.00</small></td><td><small>0.00</small></td></tr>');
                    });
                }
            });
        } else {
            var patRid = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
            var searchObj = {"refhPayerRID": patRid};
            dcomethealth.RefundResource.searchRefunds(searchObj, function (refunds) {
                $("#ledgersTbody").empty();
                if (!!refunds) {
                    $.each(refunds, function (pIdx, refundH) {
                        var refundDate = refundH.createdDateTime.toString();
                        $("#ledgersTbody").append('<tr><td><small>' + (refundDate.substring(0, 10)) + '</small></td><td><small>Refund</small></td><td><small>' + refundH.refhNo + '</small></td><td><small>0.00</small></td><td><small>' + refundH.refhAmount + '</small></td><td><small>0.00</small></td></tr>');
                    });
                }
            });
        }
    }
    function attachFiles() {
        $("#fileDownload_Header").addClass('hidden');
//        $("#uploadButton").removeClass('hidden');
        var table = document.getElementById('dyn_table_upload');
        var table_length = table.rows.length;
        var file = null, fileSizeCheck = false, fileCheck = false;
        fileList = [];
        for (var i = 0; i < table_length - 1; i++) {
            file = dynTableGetNodeInRow(table.rows[i + 1], 'file').files;
            $.each(file, function (index, files) {
                var fileSize = dcomethealth.Pictures.isAllowedFileSize(files);
                if (!fileSize) {
                    dynTableGetNodeInRow(table.rows[i + 1], 'file').value = "";
                    fileSizeCheck = true;
                }
                var fileName = files.name;
                var fileExtension = dcomethealth.Pictures.isAllowedFileExtenstion(fileName);
                if (!fileExtension) {
                    dynTableGetNodeInRow(table.rows[i + 1], 'file').value = "";
                    fileCheck = true;
                }
                if (!fileSizeCheck && !fileCheck) {
                    if ($.inArray(file, fileList) == -1) {
                        fileList.push(file);
                    }
                } else {
                    alert("Attach Valid File");
                    return false;
                }
            });
        }
    }
    function uploadAttachFiles() {
        if (!!fileList) {
            $.each(fileList, function (idx, files) {
                dcomethealth.FileResource.uploadAttachments(files, $("#activeVisitRid").val(), poType, "", function (data) {
                    fileCheck = true;
                });
            });
            alert("Uploaded Successfully");
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
                o[this.name].push(this.value || null);
            } else {
                if (this.value != null && this.value != "") {
                    o[this.name] = this.value;
                }
            }
        });
        return o;
    }
    function saveHistory() {
        var form = $("div.History").find("form");
        var hisObj = serializeFormData(form);
        var objectCheck = false;
        if (!jQuery.isEmptyObject(hisObj)) { //check if any 'textarea' have values 
            objectCheck = true;
        }
        var history = {}, historyDtls = [];
        if (!!$("#history_rid").val()) {
            history.id = $("#history_rid").val();
        }
        history.hisVisitRid = $("#activeVisitRid").val();
        history.hisPatRid = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();

        history.hisPoREye = $("#opthalmicRight").val();
        history.hisPoLEye = $("#opthalmicLeft").val();

        history.hisMedPastMedicalHistory = $("#pastMedical").val();
        history.hisMedSurgicalHistory = $("#surgicalHistory").val();
        history.hisFsFamilyHistory = $("#familyHistory").val();

        history.hisMedPersonalHistory = $("#personalHistory").val();
        history.hisMedDrugHistory = $("#drugHistory").val();
        history.hisMedPastDentalHistory = $("#pastDentalHistory").val();


        if ($("#allergiesYes").is(":checked")) {
            history.hisMedAllergiesStatus = 1;
        }
        if ($("#allergiesNo").is(":checked")) {
            history.hisMedAllergiesStatus = 0;
        }
        if ($("#dmYes").is(":checked")) {
            history.hisMedDmStatus = 1;
        }
        if ($("#dmNo").is(":checked")) {
            history.hisMedDmStatus = 0;
        }
        if ($("#htYes").is(":checked")) {
            history.hisMedHtStatus = 1;
        }
        if ($("#htNo").is(":checked")) {
            history.hisMedHtStatus = 0;
        }
        if ($("#asthmaYes").is(":checked")) {
            history.hisMedAsthmaStatus = 1;
        }
        if ($("#asthmaNo").is(":checked")) {
            history.hisMedAsthmaStatus = 0;
        }
        if ($("#eczemaYes").is(":checked")) {
            history.hisMedEczemaStatus = 1;
        }
        if ($("#eczemaNo").is(":checked")) {
            history.hisMedEczemaStatus = 0;
        }
        if ($("#glaucomaYes").is(":checked")) {
            history.hisMedGlaucomaStatus = 1;
        }
        if ($("#glaucomaNo").is(":checked")) {
            history.hisMedGlaucomaStatus = 0;
        }
        if ($("#arthritisYes").is(":checked")) {
            history.hisMedArthritisStatus = 1;
        }
        if ($("#arthritisNo").is(":checked")) {
            history.hisMedArthritisStatus = 0;
        }
        if ($("#othrNo").is(":checked")) {
            history.hisMedOthersStatus = 0;
        }
        if ($("#fmdmYes").is(":checked")) {
            history.hisFsDmStatus = 1;
        }
        if ($("#fmdmNo").is(":checked")) {
            history.hisFsDmStatus = 0;
        }
        if ($("#fmhtYes").is(":checked")) {
            history.hisFsHtStatus = 1;
        }
        if ($("#fmhtNo").is(":checked")) {
            history.hisFsHtStatus = 0;
        }
        if ($("#fmasthmaYes").is(":checked")) {
            history.hisFsAsthmaStatus = 1;
        }
        if ($("#fmasthmaNo").is(":checked")) {
            history.hisFsAsthmaStatus = 0;
        }
        if ($("#fmglaucomaYes").is(":checked")) {
            history.hisFsGlaucomaStatus = 1;
        }
        if ($("#fmglaucomaNo").is(":checked")) {
            history.hisFsGlaucomaStatus = 0;
        }
        if ($("#fmarthritisYes").is(":checked")) {
            history.hisFsArthritisStatus = 1;
        }
        if ($("#fmarthritisNo").is(":checked")) {
            history.hisFsArthritisStatus = 0;
        }
        if ($("#fmsmokingYes").is(":checked")) {
            history.hisFsSmokingStatus = 1;
        }
        if ($("#fmsmokingNo").is(":checked")) {
            history.hisFsSmokingStatus = 0;
        }
        if ($("#fmalcoholYes").is(":checked")) {
            history.hisFsAlcoholStatus = 1;
        }
        if ($("#fmalcoholNo").is(":checked")) {
            history.hisFsAlcoholStatus = 0;
        }
        if ($("#fmothrNo").is(":checked")) {
            history.hisFsOthersStatus = 0;
        }
        var checked = false;
        $(form).find("input[type='radio']").each(function (ix, radio) {//check if any 'Yes' radio button is checked  
            var radioYesId = $(radio).attr("id").toString();
            if (radioYesId.includes("Yes")) {
                if ($("#" + radioYesId).is(":checked")) {
                    checked = true;
                }
            }
        });
        if (objectCheck == true || checked == true) {
            historyDtls.push(history);
            dcomethealth.ClinicalResource.saveHistory(historyDtls).done(function (data, textStatus, jqXHR) {
//            alert("Saved");
//            getHistory(data);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("History Failed");
            });
        }
    }
    function getHistory(visRid) {
        var searchObj = {"hisVisitRid": visRid};
        dcomethealth.ClinicalResource.searchHistory(searchObj, function (data) {
            if (!!data) {
                $('#history_rid').val("");
                $("#personalHistory,#drugHistory,#pastMedical,#familyHistory,#surgicalHistory,#opthalmicRight,#opthalmicLeft,#pastDentalHistory").val("");
                $("#allergiesNo,#dmNo,#htNo,#asthmaNo,#eczemaNo,#glaucomaNo,#arthritisNo,#fmdmNo,#fmhtNo,#fmasthmaNo,#fmglaucomaNo,#fmarthritisNo,#fmsmokingNo,#fmalcoholNo").prop('checked', true);
                $.each(data, function (pIdx, historys) {
//                    if ($('#activeVisitDate').val() == moment(historys.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY')) {                        
                    $('#history_rid').val(historys.id);

                    $('#opthalmicRight').val(historys.hisPoREye);
                    $('#opthalmicLeft').val(historys.hisPoLEye);

                    $('#pastMedical').val(historys.hisMedPastMedicalHistory);
                    $('#surgicalHistory').val(historys.hisMedSurgicalHistory);
                    $('#familyHistory').val(historys.hisFsFamilyHistory);

                    $('#personalHistory').val(historys.hisMedPersonalHistory);
                    $('#drugHistory').val(historys.hisMedDrugHistory);
                    $('#pastDentalHistory').val(historys.hisMedPastDentalHistory);

                    var medAllergiesStatus = parseInt(historys.hisMedAllergiesStatus);
                    if (medAllergiesStatus == 1) {
                        document.getElementById("allergiesYes").checked = true;
                    } else {
                        document.getElementById("allergiesNo").checked = true;
                    }
                    var medDmStatus = parseInt(historys.hisMedDmStatus);
                    if (medDmStatus === 1) {
                        document.getElementById("dmYes").checked = true;
                    } else {
                        document.getElementById("dmNo").checked = true;
                    }

                    var medHtStatus = parseInt(historys.hisMedHtStatus);
                    if (medHtStatus === 1) {
                        document.getElementById("htYes").checked = true;
                    } else {
                        document.getElementById("htNo").checked = true;
                    }

                    var medAsthmaStatus = parseInt(historys.hisMedAsthmaStatus);
                    if (medAsthmaStatus === 1) {
                        document.getElementById("asthmaYes").checked = true;
                    } else {
                        document.getElementById("asthmaNo").checked = true;
                    }

                    var medEczemaStatus = parseInt(historys.hisMedEczemaStatus);
                    if (medEczemaStatus === 1) {
                        document.getElementById("eczemaYes").checked = true;
                    } else {
                        document.getElementById("eczemaNo").checked = true;
                    }

                    var medGlaucomaStatus = parseInt(historys.hisMedGlaucomaStatus);
                    if (medGlaucomaStatus === 1) {
                        document.getElementById("glaucomaYes").checked = true;
                    } else {
                        document.getElementById("glaucomaNo").checked = true;
                    }

                    var medArthritisStatus = parseInt(historys.hisMedArthritisStatus);
                    if (medArthritisStatus === 1) {
                        document.getElementById("arthritisYes").checked = true;
                    } else {
                        document.getElementById("arthritisNo").checked = true;
                    }

                    var fsDmStatus = parseInt(historys.hisFsDmStatus);
                    if (fsDmStatus === 1) {
                        document.getElementById("fmdmYes").checked = true;
                    } else {
                        document.getElementById("fmdmNo").checked = true;
                    }

                    var fsHtStatus = parseInt(historys.hisFsHtStatus);
                    if (fsHtStatus === 1) {
                        document.getElementById("fmhtYes").checked = true;
                    } else {
                        document.getElementById("fmhtNo").checked = true;
                    }

                    var fsAsthmaStatus = parseInt(historys.hisFsAsthmaStatus);
                    if (fsAsthmaStatus === 1) {
                        document.getElementById("fmasthmaYes").checked = true;
                    } else {
                        document.getElementById("fmasthmaNo").checked = true;
                    }

                    var fsGlaucomaStatus = parseInt(historys.hisFsGlaucomaStatus);
                    if (fsGlaucomaStatus === 1) {
                        document.getElementById("fmglaucomaYes").checked = true;
                    } else {
                        document.getElementById("fmglaucomaNo").checked = true;
                    }

                    var fsArthritisStatus = parseInt(historys.hisFsArthritisStatus);
                    if (fsArthritisStatus === 1) {
                        document.getElementById("fmarthritisYes").checked = true;
                    } else {
                        document.getElementById("fmarthritisNo").checked = true;
                    }

                    var fsSmokingStatus = parseInt(historys.hisFsSmokingStatus);
                    if (fsSmokingStatus === 1) {
                        document.getElementById("fmsmokingYes").checked = true;
                    } else {
                        document.getElementById("fmsmokingNo").checked = true;
                    }

                    $('#fmalcoholYes').val(historys.hisFsAlcoholStatus);
                    var fsAlcoholStatus = parseInt(historys.hisFsAlcoholStatus);
                    if (fsAlcoholStatus === 1) {
                        document.getElementById("fmalcoholYes").checked = true;
                    } else {
                        document.getElementById("fmalcoholNo").checked = true;
                    }
//                    }
                });
            } else {
                $('#history_rid').val("");
                $("#personalHistory,#drugHistory,#pastMedical,#familyHistory,#surgicalHistory,#opthalmicRight,#opthalmicLeft,#pastDentalHistory").val("");
                $("#allergiesNo,#dmNo,#htNo,#asthmaNo,#eczemaNo,#glaucomaNo,#arthritisNo,#fmdmNo,#fmhtNo,#fmasthmaNo,#fmglaucomaNo,#fmarthritisNo,#fmsmokingNo,#fmalcoholNo").prop('checked', true);
            }
        });
    }
    function submit() {
        if (!!$("#od_vital_height").val() || !!$("#od_vital_weight").val() || !!$("#od_vital_blood_pressure").val() || !!$("#od_vital_heart_rate").val() || !!$("#od_vital_resporatory_rate").val() || !!$("#od_vital_body_temp").val()) {
            var visitVitals = {}, vitalsInfo = {};
            visitVitals.id = !!$("#visitVitalId").val() ? $("#visitVitalId").val() : null;
            visitVitals.vitPatRID = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
            visitVitals.vitName = $("#patName").text();
            visitVitals.vitVisRID = $("#activeVisitRid").val();
            vitalsInfo.od_vital_height = $("#od_vital_height").val();
            vitalsInfo.od_vital_height_unit = $("#od_vital_height_unit").val();
            vitalsInfo.od_vital_weight = $("#od_vital_weight").val();
            vitalsInfo.od_vital_weight_unit = $("#od_vital_weight_unit").val();
            vitalsInfo.od_vital_blood_pressure = $("#od_vital_blood_pressure").val();
            vitalsInfo.od_vital_blood_pressure_unit = $("#od_vital_blood_pressure_unit").val();
            vitalsInfo.od_vital_heart_rate = $("#od_vital_heart_rate").val();
            vitalsInfo.od_vital_heart_rate_unit = $("#od_vital_heart_rate_unit").val();
            vitalsInfo.od_vital_resporatory_rate = $("#od_vital_resporatory_rate").val();
            vitalsInfo.od_vital_resporatory_rate_unit = $("#od_vital_resporatory_rate_unit").val();
            vitalsInfo.od_vital_body_temp = $("#od_vital_body_temp").val();
            vitalsInfo.od_vital_body_temp_unit = $("#od_vital_body_temp_unit").val();
            visitVitals.vitValue = JSON.stringify(vitalsInfo);
            dcomethealth.ClinicalResource.saveVisitVitals(visitVitals).done(function (data, textStatus, jqXHR) {
//            alert("Saved");
                //            getVisitVitals(data);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Vitals Failed");
            });
        }
    }
    function submitSymptoms() {
        var complaintsList = [], checkIsNull = true;
        var table = document.getElementById('complaintsTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var complaints = {};
            if (dynTableGetNodeInRow(table.rows[i + 1], 'complaintsValue').value != 'undefined' && !!dynTableGetNodeInRow(table.rows[i + 1], 'complaintsValue').value) {
                complaints.id = !!dynTableGetNodeInRow(table.rows[i + 1], 'compId').value ? dynTableGetNodeInRow(table.rows[i + 1], 'compId').value : null;
                complaints.cplPatRID = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
                complaints.cplVisitRID = $("#activeVisitRid").val();
                complaints.cplNameIndex = parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'complaintsDurations').value);
                complaints.cplCurrentStatusIndex = dynTableGetNodeInRow(table.rows[i + 1], 'compCurrentStatus').value;
                complaints.cplDescRangeIndex = dynTableGetNodeInRow(table.rows[i + 1], 'compDurataionSelect').value;
                complaints.cplName = dynTableGetNodeInRow(table.rows[i + 1], 'complaintsValue').value;
                complaintsList.push(complaints);
                checkIsNull = false;
            }
        }
        if (!checkIsNull) {
            dcomethealth.ClinicalResource.saveComplaints(complaintsList).done(function (data, textStatus, jqXHR) {
                //                getSymptoms(data);
            }).fail(function (jqXHR, textStatus, Patients) {
                alert("Symptoms Failed");
            });
        }
    }
    function saveDiagnosis() {
        if (!!$('#visDiagnosis').val() && $('#visDiagnosis').val() != "") {
            var visit = {};
            visit.id = !!$('#activeVisitRid').val() ? $('#activeVisitRid').val() : null;
            visit.visPatRid = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
            visit.visDate = !!$('#activeVisitDate').val() ? $('#activeVisitDate').val() : moment().format('DD-MM-YYYY');
            //        visit.visTypeIndex = $("#visitType").val();         visit.visSubTypeIndex = 0; // Need to change the value;         visit.visPatType = 0; // Need to check and change it.
            visit.visApptRid = 0; // passing 0 because its a direct OP registration
            visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
//        visit.visReasonIndex = $("#visitReason").val();
            visit.visSpecialityIndex = !!$('#activeSpecialityIndex').val() ? $('#activeSpecialityIndex').val() : 0;
            //        visit.visRemarks = $("#visitRemarks").val();
//        visit.visConsDocRid = $("#VDName option:selected").val();
            visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.
            //        visit.visRefTypeIndex = $("#refType").val();
            //        visit.visState = $('#activeVisitState').val();
            visit.visDiagnosis = $('#visDiagnosis').val();
            dcomethealth.PatientResource.saveVisit(visit).done(function (data, textStatus, jqXHR) {
                //            getDiagnosis(data);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Diagnosis Failed");
            });
        }
    }
    function submitService() {
        var serviceH = {}, checkIsNull = true;
        if ($("#serviceId").val() == "") {
//            serviceH.serReqhId = !!$("#serviceId").val() ? $("#serviceId").val() : null;
            serviceH.serReqOpVisitRid = $("#activeVisitRid").val();
            serviceH.serReqhPatMrn = $("#patMRN").val();
            serviceH.serReqhPatName = $("#patName").text();
            serviceH.serReqhPatRid = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
            var serviceList = [];
            var table = document.getElementById('serviceOrderTable');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var serviceD = {};
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value && dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value != 0) {
                    serviceD.serReqRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value != "undefined" && !!dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value) {
                    serviceD.serReqItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value;
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'serReqItemGroupRid').value != undefined && !!dynTableGetNodeInRow(table.rows[i + 1], 'serReqItemGroupRid').value) {
                        serviceD.serReqItemGroupRid = dynTableGetNodeInRow(table.rows[i + 1], 'serReqItemGroupRid').value;
                    }
                    serviceD.serReqItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                    serviceD.serReqItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
                    serviceD.serReqItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'servicePrice').value;
                    //            serviceD.serReqProcessDate = dynTableGetNodeInRow(table.rows[i + 1], 'processServiceOn').value;
                    serviceList.push(serviceD);
                    checkIsNull = false;
                }
            }
            if (!checkIsNull) {
                serviceH.serviceRequest = serviceList;
                if (dcomethealth.loginuser.entityRid != 5) {
                    dcomethealth.ServiceRequestResource.saveService(serviceH).done(function (data, textStatus, jqXHR) {
//                getService(data);
                        //                    loadNotification();
                    }).fail(function (jqXHR, textStatus, Patients) {
                        alert("Service Failed");
                    });
                } else {
                    var args = 0 + "/" + "SERVICE_ORDER_REQ" + "/" + "REQUEST";
                    dcomethealth.ServiceRequestResource.saveServiceRequest(serviceH, args).done(function (data, textStatus, jqXHR) {
                        //                getService(data);
                        dcomethealth.util.loadNotification();
                    }).fail(function (jqXHR, textStatus, Patients) {
                        alert("Service Failed");
                    });
                }
            }
        } else {
            serviceH.serReqhId = $("#serviceId").val();
            serviceH.serReqOpVisitRid = $("#activeVisitRid").val();
            serviceH.serReqhPatMrn = $("#patMRN").val();
            serviceH.serReqhPatName = $("#patName").text();
            serviceH.serReqhPatRid = $("#patRid").val();
            var serviceList = [];
            var table = document.getElementById('serviceOrderTable');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var serviceD = {};
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value && dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value != "undefined" && dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value != 0) {
                    serviceD.serReqRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value;
                }
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value != "undefined" && !!dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value) {
                    serviceD.serReqItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value;
                    serviceD.serReqItemGroupRid = dynTableGetNodeInRow(table.rows[i + 1], 'serReqItemGroupRid').value;
                    serviceD.serReqItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
                    serviceD.serReqItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
                    serviceD.serReqItemPrice = dynTableGetNodeInRow(table.rows[i + 1], 'servicePrice').value;
                    //            serviceD.serReqProcessDate = dynTableGetNodeInRow(table.rows[i + 1], 'processServiceOn').value;
                    serviceList.push(serviceD);
                    checkIsNull = false;
                }
            }

            if (!checkIsNull) {
                serviceH.serviceRequest = serviceList;
                dcomethealth.ServiceRequestResource.modifyService(serviceH).done(function (data, textStatus, jqXHR) {
                }).fail(function (jqXHR, textStatus, Patients) {
                    alert("Service Failed");
                });
            }
        }
    }

    function drugPrint() {
        filename = "billsTable";
        var searchObj = {"drugReqHOpVBisitRID": parseInt($("#activeVisitRid").val())}, check = false, payerName = null;
        dcomethealth.ItemOrderResource.getDrugPrint(searchObj, function (data) { //                $("#entLogo").attr('src', dcomethealth.image);
            if (!!data) {
                $('#printHtml').html(data);
                var searchObject = {"bhPatientVisitRID": parseInt($("#activeVisitRid").val())};
                dcomethealth.BillingResource.searchBilling(searchObject, function (data) {
                    if (!!data && data.length > 0) {
                        $.each(data, function (pIdx, billData) {
                            $.each(dcomethealth.dDictVal, function (index, val) {
                                if (parseInt(billData.billH.bhPayerType) == val.id) {
                                    payerName = val.ddictValue;
                                }
                            });
                            $('#printHtml').find('span#corporate').text(payerName);
                            check = true;
                        });
                        if (check) {
                            exportExcel($('#printHtml').html(), 'Drug');
                        }
                    } else {
                        exportExcel($('#printHtml').html(), 'Drug');
                    }
                });
            }
        });
    }
    function submitRxOrder() {
        var drugH = {}, checkIsNull = true;
        var drugDList = [];
        drugH.id = !!$("#drugId").val() ? $("#drugId").val() : null;
        drugH.drugReqHOpVBisitRID = $("#activeVisitRid").val();
        drugH.drugReqHPatMrn = $("#patMRN").val();
        drugH.drugReqHPatName = $("#patName").text();
        drugH.drugReqHfollowupDate = $("#drugReqHfollowupDate").val();
        drugH.drugReqHComments = $("#drugReqHComments").val();
        var table = document.getElementById('rxOrderTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var drugD = {};
            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'SkuRID').value && dynTableGetNodeInRow(table.rows[i + 1], 'SkuRID').value) {
                drugD.id = !!dynTableGetNodeInRow(table.rows[i + 1], 'drugDid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'drugDid').value : null;
                drugD.druReqDSrhRID = !!dynTableGetNodeInRow(table.rows[i + 1], 'drugHRid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'drugHRid').value : null;
                drugD.drugReqDItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'SkuRID').value;
                drugD.drugReqDItemName = dynTableGetNodeInRow(table.rows[i + 1], 'drugName').value;
                drugD.drugReqDItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'drugQty').value;
                //            drugD.drugReqDDosageTime = dynTableGetNodeInRow(table.rows[i + 1], 'whenSelect').value;
                drugD.drugReqDMrng = !!dynTableGetNodeInRow(table.rows[i + 1], 'morning').value ? dynTableGetNodeInRow(table.rows[i + 1], 'morning').value : 0;
                drugD.drugReqDAfternoon = !!dynTableGetNodeInRow(table.rows[i + 1], 'afternoon').value ? dynTableGetNodeInRow(table.rows[i + 1], 'afternoon').value : 0;
                //            drugD.drugReqDDuration = dynTableGetNodeInRow(table.rows[i + 1], 'duration').value;
                //            drugD.drugReqDDurationTime = dynTableGetNodeInRow(table.rows[i + 1], 'durataionSelect').value;
                drugD.drugReqDEve = !!dynTableGetNodeInRow(table.rows[i + 1], 'evening').value ? dynTableGetNodeInRow(table.rows[i + 1], 'evening').value : 0;
                drugD.drugReqDNight = !!dynTableGetNodeInRow(table.rows[i + 1], 'night').value ? dynTableGetNodeInRow(table.rows[i + 1], 'night').value : 0;
                drugDList.push(drugD);
                checkIsNull = false;
            }
        }
        if (!checkIsNull) {
            drugH.drugRequestDList = drugDList;
            dcomethealth.ItemOrderResource.saveDrugs(drugH).done(function (data, textStatus, jqXHR) {
                //                getrxOrder(data);
            }).fail(function (jqXHR, textStatus, Patients) {
                alert("Drug Failed");
            });
        }
    }
    function setValue(id, obj) {
        var a = $("#" + id).serializeArray();
        $.each(obj, function (idx, obj) {
            if (obj.id == id) {
                $.each(obj.teeth, function (cdx, o) {
                    $.each(o, function (k, v) { // get key and value of object
                        $("#" + id).find("input[type=radio]").each(function (indx, radio) { //set value for radio button 
                            if ($(radio).val() == v) {
                                $(radio).attr("checked", true);
                            }
                        });
                    });
                    $.each(a, function () {
                        if (!!o[this.name]) {
                            $("#" + id).find("#" + this.name).val(o[this.name]);
                            $("#" + id).find("#s" + this.name).css("background-color", "#1bc4a3");
                        }
                    });
                });
            }
        });
    }
    function getValue() {
        $("#patient_visit_tab").removeClass("hidden");
        var htm = $("#totalDiv").html();
        var dataList = [];
        $(htm).find("form").each(function (indx, frm) {
            var id = $(frm).attr("id");
            var teethList = [];
            var o = {}, formSet = {};
            var a = $("#" + id).not("input[type=radio]").serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || null);
                } else {
                    if (!!this.value) {
                        o[this.name] = this.value || null;
                    }
                }
            });
            $("#" + id).find("select").each(function (indx, select2) {
                var s2Id = $(select2).attr("id");
                $("#s2id_" + s2Id).find(".select2-choices").find(".select2-search-choice").find("div").each(function (indx, s2) {
                    var s2Text = $(s2).text();
                    if (!!s2Text) {
                        var tStr = "t" + s2Text;
                        o[tStr] = "1";
                    }
                });
            });
            if (Object.keys(o).length > 0) {
                teethList.push(o);
                formSet.id = id;
                formSet.teeth = teethList;
                dataList.push(formSet);
            }
        });
        return dataList;
    }
    function saveFiles() {
        var imageContext = {};
        $("#resourceId").val() !== "" ? imageContext.id = $("#resourceId").val() : null;
        imageContext.refType = attRefType;
        $('.ImgPlaceHolder').each(function () {
            var containerId = "image_containerImg";
            if ($(this).length) {
                var imgandAndCaption = dcomethealth.Pictures.getImgandCaptionfromContainer(containerId);
                var context = {};
                context["image"] = imgandAndCaption["image"];
                context["caption"] = imgandAndCaption["caption"];
                imageContext.context = context;
            }
        });
        imageContext.refRid = $('#activeVisitRid').val();
        //        imageContext.refRid = $("#patRid").val();            
        dcomethealth.FileResource.saveFileInfo(imageContext, function (data) {
            //            alert("Data Saved");
        });
    }
    function save() {
        var visitTemplate = {};
        if (!!$('#visitTempRID').val()) {
            visitTemplate.id = $('#visitTempRID').val();
        }
        visitTemplate.vistVisitRID = $('#activeVisitRid').val();
        visitTemplate.vistPatRID = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
        if ($("#tempType").val() == "Dental") {
            if (!!getValue()) {
                visitTemplate.vistNodes = JSON.stringify(getValue());
            } else {
                visitTemplate.vistNodes = "";
            }
        } else if ($("#tempType").val() == "Diabetic" || $("#tempType").val() == "Ophthalmology") {
            if (!!dcomethealth.template.getValue()) {
                visitTemplate.vistNodes = JSON.stringify(dcomethealth.template.getValue());
            } else {
                visitTemplate.vistNodes = "";
            }
        } else {
            visitTemplate.vistNodes = !!CKEDITOR.instances.noTempNodes.getData() ? CKEDITOR.instances.noTempNodes.getData() : "";
        }
        //        visitTemplate.vistNodes = $("#tempType").val() == "Dental" ? JSON.stringify(getValue()) : JSON.stringify(dcomethealth.template.getValue());
        visitTemplate.vistSeqNum = 1;
        visitTemplate.vistSpeciality = !!$('#activeSpecialityIndex').val() ? $('#activeSpecialityIndex').val() : 0;
        dcomethealth.ClinicalResource.saveVisitTemplate(visitTemplate).done(function (data, textStatus, jqXHR) {
            dcomethealth.ClinicalResource.searchVisitTemplate({"vistVisitRID": data}, function (visitTemplate) {
                if (!!visitTemplate) {
                    loadPatientVisit(0, visitTemplate[0].vistVisitRID, visitTemplate[0].vistPatRID, visitTemplate[0].vistSpeciality, "", 1);
                }
            }); //            alert("Saved");
            //            loadNotification();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Clinical Notes Failed");
        });
    }
    function saveTreatment() {
        if (!!$('#treatText').val() && $('#treatText').val() != "") {
            var visit = {};
            visit.id = !!$('#activeVisitRid').val() ? $('#activeVisitRid').val() : null;
            visit.visPatRid = (patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
            visit.visDate = !!$('#activeVisitDate').val() ? $('#activeVisitDate').val() : moment().format('DD-MM-YYYY');
            //        visit.visTypeIndex = $("#visitType").val();         visit.visSubTypeIndex = 0; // Need to change the value;         visit.visPatType = 0; // Need to check and change it.
            visit.visApptRid = 0; // passing 0 because its a direct OP registration
            visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
//        visit.visReasonIndex = $("#visitReason").val();
            visit.visSpecialityIndex = !!$('#activeSpecialityIndex').val() ? $('#activeSpecialityIndex').val() : 0;
            //        visit.visRemarks = $("#visitRemarks").val();
//        visit.visConsDocRid = $("#VDName option:selected").val();
            visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.
            //        visit.visRefTypeIndex = $("#refType").val();
            //        visit.visState = $('#activeVisitState').val();
            visit.visTreatment = $('#treatText').val();
            dcomethealth.PatientResource.saveVisit(visit).done(function (data, textStatus, jqXHR) {
                //            getTreatmentData(data);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Treatment Failed");
            });
        }
    }

//    function addFavourite() {
//        var favourite = {};
//        var favouriteDList = [];
//        favourite.id = !!$("#favouriteId").val() ? $("#favouriteId").val() : null;
//        favourite.fsohUserRID =(patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
////        favourite.fsohName = $("#").val();
//        var table = document.getElementById('serviceOrderTable');
//        var table_length = table.rows.length;
//        for (var i = 0; i < table_length - 1; i++) {
//            var favouriteD = {};
//
////            favouriteD.id = !!dynTableGetNodeInRow(table.rows[i + 1], 'favouriteDId').value ? dynTableGetNodeInRow(table.rows[i + 1], 'favouriteDId').value : null;
//            favouriteD.id = !!dynTableGetNodeInRow(table.rows[i + 1], 'favDid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'favDid').value : null;
//            favouriteD.fsodFsohRID = !!dynTableGetNodeInRow(table.rows[i + 1], 'favHRid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'favHRid').value : null;
////            favouriteD.fsodFsohRID = !!dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value ? dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value : null;
//            favouriteD.fsodServiceRID = dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value;
//            favouriteDList.push(favouriteD);
//        }
//        favourite.favouriteServiceOrderD = favouriteDList;
//        dcomethealth.FavouriteResource.saveFavouriteServiceOrder(favourite).done(function (data, textStatus, jqXHR) {
//        }).fail(function (jqXHR, textStatus, Patients) {
//            alert("Data Saved");
//        });
//            }


//    function  getFavourite() {
//        var searchObj = {"fsohUserRID":(patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val()};
//        dcomethealth.FavouriteResource.searchFavouriteSearviceOrder(searchObj, function (data) {
//            if (!!data) {
//                $("#serviceOrderTbody").empty();
//                $.each(data, function (igx, favH) {
//                    $("#favouriteId").val(favH.id);
//                    $.each(favH.favouriteServiceOrderD, function (ind, favD) {
////                        dcomethealth.MasterResource.searchSkus({"id": favD.fsodServiceRID}, function (data) {
////                            if (!!data) {
////                                $.each(data, function (igx, serviceName) {
//                                    $("#serviceOrderTbody").append('<tr><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this,\'serviceOrderTable\')"></i></td>\n\
//                                    <td><input type="text" id="serviceName" name="serviceName" value="' + favD.fsodServiceRID + '" class="col-md-12"/><input type="hidden" id="itemRID" value=""/><input type="hidden" id="SkuRID" value="' + favH.drugReqDItemRID + '" name="SkuRID"/><input type="hidden" id="favHRid" value="' + favH.druReqDSrhRID + '" name="favHRid"/><input type="hidden" id="favDid" value="' + favH.id + '" name="favDid"/></td>\n\
//                                    <td><input type="text" id="qtyService" value="1" style="cursor: not-allowed;" class="col-md-12" readonly/></td>\n\
//                                    <td><input type="text" id="processServiceOn" value="" style="cursor: not-allowed;" class="col-md-12" readonly/></td>\n\
//                                    \n\
//                                    <td><i class="ace-icon fa fa-plus red" onclick="insert_servicerow(\'serviceOrderTable\', this)"></i></td></tr>');
////                                });
////                            }
////                        });
//                    });
//                });
//            }
//        });
//        }
//    function addDrugFavourite() {
//        var drugfavourite = {};
//        var drugfavouriteDList = []; 
//        drugfavourite.id = !!$("#drugfavouriteId").val() ? $("#drugfavouriteId").val() : null;
//        drugfavourite.fiohUserRID =(patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val();
//        var table = document.getElementById('rxOrderTable');
//        var table_length = table.rows.length;
//        for (var i = 0; i < table_length - 1; i++) {
//            var drugfavouriteD = {};
//            drugfavouriteD.id = !!dynTableGetNodeInRow(table.rows[i + 1], 'drugDid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'drugDid').value : null;
//            drugfavouriteD.fiodFiohRID = !!dynTableGetNodeInRow(table.rows[i + 1], 'drugHRid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'drugHRid').value : null;
//            drugfavouriteD.fiodItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'SkuRID').value;
//            drugfavouriteDList.push(drugfavouriteD);
//                }
//        drugfavourite.favouriteItemOrderD = drugfavouriteDList;
//        dcomethealth.FavouriteResource.saveFavouriteItemOrder(drugfavourite).done(function (data, textStatus, jqXHR) {
//        }).fail(function (jqXHR, textStatus, Patients) {
//            alert("Data Saved");
//            });
//    }


//    function  getDrugFavourite() {
//        var searchObj = {"fiohUserRID":(patSearch == true) ? $("#patRidSearch").val() : $("#patRid").val()};
//        dcomethealth.FavouriteResource.searchFavouriteItemOrder(searchObj, function (data) {
////            $("#rxOrderTbody").empty();
//            if (!!data) {
//                $.each(data, function (igx, drugH) {
//                    $.each(drugH.favouriteItemOrderD, function (igs, drugD) {
//                        dcomethealth.MasterResource.searchSkus({"id": drugD.fiodItemRID}, function (data) {
////                            $("#rxOrderTbody").empty();
//                            if (!!data) {
////                                $("#rxOrderTbody").empty();
//                                $.each(data, function (igx, drugName) {
//                                    $("#rxOrderTbody").append('<tr id="rxOrders' + igs + '"><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this,\'rxOrderTable\')"></i></td>\n\
//                        <td><input type="text" id="drugName" name="drugName" value="' + drugName.skuName + '" class="col-md-12"/><input type="hidden" id="SkuRID" value="' + drugD.drugReqDItemRID + '" name="SkuRID"/><input type="hidden" id="drugHRid" value="' + drugD.druReqDSrhRID + '" name="drugHRid"/><input type="hidden" id="drugDid" value="' + drugD.id + '" name="drugDid"/><br></td>\n\
//                        <td><input type="text" id="strength" class="col-md-12"/></td>\n\
//                        <td><select id="strengthSelect" class="col-md-12"><option>mg</option></select></td>\n\
//                        <td><input type="text" id="duration" class="col-md-12"/></td>\n\
//                        <td><select id="durataionSelect" class="col-md-12 dcomet-c-s_ddict_duration-list"></select></td>\n\
//                        <td><select id="whenSelect" class="col-md-12 dcomet-c-s_ddict_dosage_time-list"></select></td>\n\
//                        <td><input type="text" id="morning" value="' + " " + '" class="col-md-12"/></td>\n\
//                        <td><input type="text" id="afternoon" value="' + " " + '" class="col-md-12"/></td>\n\
//                        <td><input type="text" id="evening" value="' + " " + '" class="col-md-12"/></td>\n\
//                        <td><input type="text" id="night" value="' + " " + '" class="col-md-12"/></td>\n\
//                        <td><input type="text" id="drugQty" value="' + " " + '" class="col-md-12"/></td>\n\
//                        <td><i class="ace-icon fa fa-plus red" onclick="insert_rxrow(\'rxOrderTable\', this)"></i></td></tr>');
//                                    dcomethealth.datatypes.init($("#rxOrders" + igs));
//        });
//    }
    //                        });
//                    });
    //                });
//            }
    //        });
//    }
    function refreshData() {
    }
    return {
        init: init,
        submit: submit,
        submitSymptoms: submitSymptoms,
        submitService: submitService,
        submitRxOrder: submitRxOrder,
        drugPrint: drugPrint,
        saveDiagnosis: saveDiagnosis,
        uploadAttachFiles: uploadAttachFiles,
        autocompleteService: autocompleteService,
        autoCompleteDrug: autoCompleteDrug,
        getCategoryWiseService: getCategoryWiseService,
        getServiceList: getServiceList,
//        addFavourite: addFavourite,
//        getFavourite: getFavourite,
        //        addDrugFavourite: addDrugFavourite,
        //        getDrugFavourite: getDrugFavourite,
        setServiceValue: setServiceValue,
        attachFiles: attachFiles,
        searchCompleted: searchCompleted,
        //        getTemplatesForService: getTemplatesForService,
        getDiagnosis: getDiagnosis,
        getTreatmentData: getTreatmentData,
        getPatientDetails: getPatientDetails,
        getVisitPatient: getVisitPatient,
        getLedgerDetails: getLedgerDetails,
        getClinicalSummary: getClinicalSummary,
        setCompleted: setCompleted,
        getStatus: getStatus,
        loadPatientVisit: loadPatientVisit,
        loadPatientVisitTab: loadPatientVisitTab,
        finalOpticalPrint: finalOpticalPrint,
//        callNavigation: callNavigation,
        save: save,
        labPrint: labPrint,
        csPrint: csPrint,
        exportExcel: exportExcel,
        saveFiles: saveFiles,
        sendEmail: sendEmail,
        getValue: getValue,
        getHistory: getHistory,
        saveHistory: saveHistory,
        saveTreatment: saveTreatment,
        getFiles: getFiles,
        setValue: setValue,
        viewLedger: viewLedger,
        refreshData: refreshData
    };
}());
dcomethealth.Patients.init();