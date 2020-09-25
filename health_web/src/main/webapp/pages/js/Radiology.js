var dcomethealth = dcomethealth || {};
dcomethealth.Radiology = (function () {
    var id = "Radiology", tempData = {}, soPendingObj = {}, soCompleted = {}, fileList = [], fileCheck = false;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var start = moment().startOf('days'), end = moment().endOf('days');
            $('#radioDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            searchSOByDate();
            $('#radioDateRange').daterangepicker({
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
                $('#radioDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                searchSOByDate();
            });
            $('#radioComDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $('#radioComDateRange').daterangepicker({
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
                $('#radioComDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                searchCompleted();
            });
//            searchCompleted();
        });
    }
    function searchSOByDate() {
        $("#radioDateRange").show();
        $("#radioComDateRange").hide();
        $("#radioDateRangeSpan").removeClass("hidden");
        $("#radioComDateRangeSpan").addClass("hidden");
        var date = $('#radioDateRangeSpan').html().split('-');
        var soUnitRID = null;
        $.each(dcomethealth.dDictVal, function (ix, dDict) {
            if (dDict.ddictValue == "Radiology.") {
                soUnitRID = dDict.id;
            }
        });
        var searchParamsSO = {"soFromDate": moment(date[0]), "soToDate": moment(date[1]).add(1, 'days'), "soState": 2, "soSerMode": soUnitRID, "soEntityRID": dcomethealth.loginuser.entityRid, "sortOrder": ["soState"], "sortDesc": "desc"};
        dcomethealth.CreateOPBilltResource.searchServiceOrders(searchParamsSO, function (data) {
            if (!!data) {
                $("#viewTbody").empty();
                $.each(soPendingObj = data, function (idx, serviceOrder) {
                    var tables = $.fn.dataTable.fnTables(true);
                    $(tables).each(function () {
                        $(this).dataTable().fnClearTable();
                        $(this).dataTable().fnDestroy();
                    });
                    dcomethealth.PatientResource.searchPatient({"id": serviceOrder.soPatientRID}, function (data) {
                        $.each(data, function (ix, patient) {
                            if (serviceOrder.soPatientRID == patient.id) {
                                var mrn = '<a href="#" onclick="dcomethealth.Radiology.viewPendingService(' + serviceOrder.id + ',\'' + patient.patMrnNo + '\',\'' + patient.patFullName + '\',\'' + patient.patDob + '\',' + patient.patGenderIndex + ',this)">' + patient.patMrnNo + '</a>';
//                    var Print = '<a href="#" class="btn btn-aris" onclick="dcomethealth.Radiology.addTemplates(' + serviceOrder.id + ',this)"><i class="fa fa-plus"></i></a>';
                                var stateVal = "Pending";
                                $("#viewTbody").append('<tr><td>' + mrn + '<input type="hidden" id="apPatRid" value="' + serviceOrder.soPatientRID + '"/></td>\n\
                            <td><output id="service">' + patient.patFullName + '</output></td>\n\
                            <td><output id="serDate">' + moment(serviceOrder.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY') + '</output></td>\n\
                            <td><output id="serviceStatus">' + stateVal + '</output></td></tr>');
                            }
                        });
                    });
                });
//                $("#dynTable").dataTable();
//                $("#dynTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                $('.dataTables_length select').addClass('form-control');
            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#viewTbody").empty();
                $("#dynTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function viewPendingService(serviceRid, patMrn, patName, patDob, patGender, row) {
        $('#btns_state').addClass('hidden');
        $("#RadioServiceHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#add_new").removeClass("hidden").addClass("panel panel-primary");
        $("#patMRN").text(patMrn);
        $("#patName").text(patName);
        $('#patDOB').val(patDob);
        var c = $('#patDOB').val().split('-');
        $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
        $('#patGender').val(patGender);
        $.each(soPendingObj, function (pIjx, serviceOrder) {
            if (serviceRid == serviceOrder.id) {
                if (serviceOrder.serviceMaster.id == serviceOrder.soItemID) {
//                    $("#sodVisRid").val(serviceRid);
                    $("#sodPatRid").val(serviceOrder.soPatientRID);
                    $("#sodSoRid").val(serviceOrder.id);
                    $("#soItemID").val(serviceOrder.soItemID);
                    $("#soItemName").val(serviceOrder.soItemName);
                    $("#soAgainstUnitRID").val(serviceOrder.soAgainstUnitRID);
                    $("#soSerMode").val(serviceOrder.soSerMode);
                    $("#soStatus").val(serviceOrder.soStatus);
                    $("#serName").text(serviceOrder.soItemName);
                    $("#soRidIn").val(serviceOrder.id);
                    $("#itemRidIn").val(serviceOrder.soItemID);
//                    $("#serviceViewDiv").append('<div class="col-md-4">' + serviceOrder.soItemName + '<input type="hidden" id="soRidIn" value="' + serviceOrder.id + '"/><input type="hidden" id="itemRidIn" value="' + serviceOrder.soItemID + '"/></td></div><div id="tempBtn" class="col-md-8"><div class="row col-md-4"><ul id="tempUL"></ul></div></div>');
                    $("#tempListDiv").append('<ul id="tempUL" class="col-md-12"></ul>');
                    var searchObj = {"tempRID": serviceOrder.serviceMaster.id};//"tempType": "Radiology"
                    dcomethealth.DataDictionaryResource.searchTemplate(searchObj, function (data) {
                        if (!!data) {
                            $("#tempNameSpan").text(serviceOrder.soItemName);
                            $.each(tempData = data, function (idex, template) {
                                $("ul#tempUL").append('<li class="serviceList"><a id="T' + (idex + 1) + '" style="cursor: pointer;" onclick="dcomethealth.Radiology.addTemplates(this)" >T' + (idex + 1) + '</a></li>');
//                                $("#tempBtn").append('<div class="row col-md-1"><button type="button" id="T' + (idex + 1) + '" onClick="dcomethealth.Radiology.addTemplates(this)" class="btn btn-xs btn-info ">T' + (idex + 1) + '</button></div>');//' + serviceOrder.serviceMaster.id + ',\'' + serviceOrder.soItemName + '\',
                            });
                        }
                    });
                }
            }
        });
    }
    function addTempNodes() {
        var templateNodes = "";
        $("#radioModal").modal("hide");
        $("#tempDynTable").removeClass("hidden");
        var serviceName = $("#soItemName").val();
        var templateName = $("#tempName").val();
        templateNodes = CKEDITOR.instances.radioNodes.getData();
        $("#tempTbody").append("<tr><td width='2%'></td><td width='20%'><span id='soServiceName'>" + serviceName + "</span></td><td width='10%'><span id='soTempName'>" + templateName + "</span><div id=" + templateName + " class='" + templateName + " hidden'>" + templateNodes + "</div></td><td width='1%'><button id='temp-" + templateName + "' class='btn btn-sm btn-info' type='button' onclick='dcomethealth.Radiology.viewModal(this)'><i class='fa fa-eye'></i></button> </td></tr>");
    }
    function viewModal(e) {
        var templateNodes = $("div#" + (e.id).substring(5)).html();
        CKEDITOR.instances.radioNodes.setData(templateNodes);
        $("#addTempService").addClass("hidden");
        $("#radioModal").modal("show");
        CKEDITOR.instances.radioNodes.setReadOnly(true);
        return false;
    }
    function addTemplates(elem) {
        $("#tempName").val(elem.id);
        $("#addTempService").removeClass("hidden");
        CKEDITOR.instances.radioNodes.setReadOnly(false);
        var check = false, templateNodes = "", gender = "";
        var table = document.getElementById('tempDynTable');
        for (var i = 0; i < table.rows.length - 1; i++) {
            var tempName = dynTableGetNodeInRow(table.rows[i + 1], 'soTempName').innerHTML;
            if (elem.id == tempName) {
                templateNodes = $("div#" + elem.id).html();
                dynTableDeleteRow(dynTableGetNodeInRow(table.rows[i + 1]));
                check = true;
            }
        }
        $.each(dcomethealth.dDictVal, function (ix, dDict) {
            if (dDict.id == parseInt($('#patGender').val())) {
                gender = dDict.ddictValue;
            }
        });
        $.each(tempData, function (idex, template) {
            if (elem.id == template.tempType) {
                if (check) {
                    $("#viewDiv2").html(templateNodes);
                    $("#patientName").text($("#patName").text());
                    $("#patientMrn").text($("#patMRN").text());
                    $("#ageGender").text($("#patAge").val() + " Years , " + gender);
                    $("#dateOfScan").text(moment().format("DD-MM-YYYY"));
                    CKEDITOR.instances.radioNodes.setData($("#viewDiv2").html());
                } else {
                    $("#viewDivs").html(template.tempNodes);
                    $("#patientName").text($("#patName").text());
                    $("#patientMrn").text($("#patMRN").text());
                    $("#ageGender").text($("#patAge").val() + " Years , " + gender);
                    $("#dateOfScan").text(moment().format("DD-MM-YYYY"));
                    CKEDITOR.instances.radioNodes.setData($("#viewDivs").html());
                }
            }
        });
        $("#radioModal").modal("show");
    }
    function viewCompletedService(soRid, sodPatRid, soItemName, patMrn, patName, patDob, patGender) {
        $("#RadioServiceHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#add_new").removeClass("hidden").addClass("panel panel-primary");
        $("#serviceCompletedDiv").removeClass("hidden");
        $("#serviceDiv,#btns_state,#tempDynTable,#tempDynDiv,#radioSave,#addTempService,#tempHeader").addClass("hidden");
        $("#patMRN").text(patMrn);
        $("#patName").text(patName);
        $('#patDOB').val(patDob);
        var c = $('#patDOB').val().split('-');
        $('#patAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
        $('#patGender').val(patGender);
        $("#tempNameSpan").text(soItemName);
        $("#tempViewTbody").empty();
        var checkRid = true;
        $.each(soCompleted, function (ix, sOrder) {
            $.each(sOrder.serviceOrderDList, function (ix, sOrderD) {
                if (soRid == sOrderD.sodSoRid && sodPatRid == sOrderD.sodPatRid) {
                    $("#tempViewTbody").append("<tr><td width='20%'>" + sOrder.soItemName + "<input id='soItemID' type='hidden' value=" + sOrder.soItemID + "/><input id='soSodRid' type='hidden' value=" + sOrderD.id + "/></td><td width='20%'>T-" + (ix + 1) + "<div id=temDiv-" + sOrderD.id + " class='hidden'>" + sOrderD.sodNodes + "</div></td><td width='20%'><button type='button' id='temp-" + sOrderD.id + "' class='btn btn-info btn-sm' onclick='dcomethealth.Radiology.viewTemplates(" + sOrderD.id + ")' ><i class='fa fa-eye'></i></button>&nbsp;<button type='button' id='tempPrint' class='btn btn-info btn-sm' onclick='dcomethealth.Radiology.getTemplates(" + sOrderD.id + "," + sOrder.id + ")' ><i class='fa fa-print'></i></td></tr>");
                    checkRid = false;
                }
            });

        });
        if (!checkRid) {
            $("#attachFile").hide();
            $("#fileDownload_tbody").empty();
            var searchParams = {"taRefRid": soRid, "taType": 1};
            dcomethealth.FileResource.searchFile(searchParams, function (data) {
                if (!!data) {
                    $("#fileDownload_Header").removeClass('hidden');
                    $.each(data, function (pIdx, file) {
                        $("#fileDownload_tbody").append('<tr><td width="10%" class="row"><td><input id=taFileName name=taFileName style="cursor: not-allowed;" value="' + file.taFileName + '" class="" disabled>\n\
                        <input id=fileUploadId name=fileUploadId type="hidden" value="' + file.id + '"><td width="10%"><a class="btn btn-sm btn-info jqueryUIToolTip" title="download File" id=download  href="/health_web/rest/file/download/file?id=' + file.id + "&type=" + 1 + '"><i class="fa fa-download"></i></a></td></tr>');
                        $("#tafileName").val(file.taFileName);
                    });
                } else {
                    $("#fileDownload_Header").addClass('hidden');
    }
            });
        }
    }
    function searchCompleted() {
        var soUnitRID = null;
        var date = $('#radioDateRangeSpan').html().split('-');
        $.each(dcomethealth.dDictVal, function (ix, dDict) {
            if (dDict.ddictValue == "Radiology.") {
                soUnitRID = dDict.id;
            }
        });
        var searchObj = {"soFromDate": moment(date[0]), "soToDate": moment(date[1]).add(1, 'days'), "soState": 3, "soSerMode": soUnitRID, "soEntityRID": dcomethealth.loginuser.entityRid, "sortOrder": ["soState"], "sortDesc": "desc"};//
        dcomethealth.ServiceOrderResource.searchServiceOrderD(searchObj, function (data) {
            if (!!data) {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#viewTbody").empty();
                $.each(soCompleted = data, function (ix, sO) {
                    dcomethealth.PatientResource.searchPatient({"id": sO.soPatientRID}, function (data) {
                        $.each(data, function (ix, patient) {

                            var mrn = '<a href="#" onclick="dcomethealth.Radiology.viewCompletedService(' + sO.id + ',' + sO.soPatientRID + ',\'' + sO.soItemName + '\',\'' + patient.patMrnNo + '\',\'' + patient.patFullName + '\',\'' + patient.patDob + '\',' + patient.patGenderIndex + ')">' + patient.patMrnNo + '</a>';
//                    var Print = '<a href="#" class="btn btn-aris" onclick="dcomethealth.Radiology.addTemplates(' + sO.id + ',this)"><i class="fa fa-plus"></i></a>';
                            var stateVal = "Completed";
                            $("#viewTbody").append('<tr><td>' + mrn + '<input type="hidden" id="apPatRid" value="' + sO.soPatientRID + '"/></td><td><output id="service">' + patient.patFullName + '</output></td>\n\
                            <td><output id="serDate">' + moment(sO.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY') + '</output></td><td><output id="serviceStatus">' + stateVal + '</output></td></tr>');
                        });
                    });
                });
//                $("#dynTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
//                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                $('.dataTables_length select').addClass('form-control');
            } else {
                var tables = $.fn.dataTable.fnTables(true);
                $(tables).each(function () {
                    $(this).dataTable().fnClearTable();
                    $(this).dataTable().fnDestroy();
                });
                $("#viewTbody").empty();
                $("#patMRN").text('');
                $("#patName").text('');
                $("#dynTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function viewTemplates(sodRid) {
        CKEDITOR.instances.radioNodes.setData('');
        var tempNodes = $("div#temDiv-" + sodRid).html();
        CKEDITOR.instances.radioNodes.setData(tempNodes);
        $("#radioModal").modal("show");
    }
    function getTemplates(sodRid, serviceOrderRid) {
        dcomethealth.ServiceOrderResource.getRadioServicePrint({"id": sodRid}, function (data) {
            if (!!data) {
                $("#reportDiv").html(data);
                $("#entLogo").attr('src', dcomethealth.image);
                getServiceOrderD(sodRid, serviceOrderRid);
            }
        });
    }
    function getServiceOrderD(sodRid, serviceOrderRid) {
        dcomethealth.ServiceOrderResource.searchServiceOrderD({"id": serviceOrderRid}, function (data) {
            if (!!data) {
                $.each(data, function (ix, sOrder) {
                    $.each(sOrder.serviceOrderDList, function (ix, sOrderD) {
                        $("#temporaryDiv").html(sOrderD.sodNodes);
                        $("#temporaryDiv").find("table#patDetailsTable").remove();
                        $("#temporaryDiv").find("p#serviceNameP").remove();
                        $("#reportDiv").find("caption#groupName").text(sOrder.soItemName);
                        $("#reportDiv").find("#tempTbody").append($("#temporaryDiv").html());
                        exportPrint($("#reportDiv").html());
                    });
                });
            }
        });
    }
    function exportPrint(data) {
        if (!!data) {
            var printWindow = window.open();
            printWindow.document.write(data);
//                printWindow.onload = function () { // necessary if the div contain images//           
            setTimeout(function () {
                printWindow.document.close();
                printWindow.focus();
                printWindow.print();
                printWindow.close();
            }, 800);
            dcomethealth.util.loadpage('Radiology');
            dcomethealth.util.base_init();
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
            if (file.length > 0) {
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
            } else {
                alert("Attach AnyOne File");
                return false;
            }
        }
    }
    function uploadAttachFiles() {
        var valid = attachFiles();
        if (valid != false) {
            if (!!fileList && fileList.length > 0) {
                $.each(fileList, function (idx, files) {
                    dcomethealth.FileResource.uploadAttachments(files, $("#sodSoRid").val(), 1, "", function (data) {
                        fileCheck = true;
                    });
                });
                alert("Uploaded Successfully");
            }
        }
    }
    function saveTemplates() {
        var serviceOrderList = [], serviceOrderDList = [];
        var table = document.getElementById('tempDynTable');
        var table_length = table.rows.length;
        if (table_length > 1) {
            if (fileList.length > 0) {
                if (!fileCheck) {
                    alert("Upload Attached File");
                    return false;
                }
            } 
            var serviceOrder = {};
            serviceOrder.id = $("#sodSoRid").val();
            serviceOrder.soPatientRID = $("#sodPatRid").val();
//        serviceOrder.soVisitRID = $("#sodVisRid").val();
            serviceOrder.soAgainstUnitRID = $("#soAgainstUnitRID").val();
            serviceOrder.soOrderingUnitRID = dcomethealth.selectedunit;
            serviceOrder.soOrderType = 1;
            serviceOrder.soItemID = $("#soItemID").val();
            serviceOrder.soItemName = $("#soItemName").val();
            serviceOrder.soQty = 1;
            serviceOrder.soProcessingUnitRID = dcomethealth.selectedunit;
            serviceOrder.soSerMode = $("#soSerMode").val();
            serviceOrder.soStatus = (parseInt($("#soStatus").val()) == 2 ? 3 : 2);
            serviceOrder.soState = (parseInt($("#soStatus").val()) == 2 ? 3 : 2);
//        serviceOrder.soProcessedBY = $("#patDoc").val();
//        serviceOrder.soRemarks = $("#patRemarks").val();
            for (var i = 0; i < table_length - 1; i++) {
                var serviceOrderD = {};
                serviceOrderD.id = !!$("#sodRid").val() ? $("#sodRid").val() : null;
                serviceOrderD.sodSoRid = $("#sodSoRid").val();
//            serviceOrderD.sodVisRid = $("#sodVisRid").val();
                serviceOrderD.sodPatRid = $("#sodPatRid").val();
                $("div#T" + (i + 1)).find('table').attr('id', 'patDetailsTable');
//                $("div#T" + (i + 1)).find('p').attr('id', 'serviceNameP');
                serviceOrderD.sodNodes = $("div#T" + (i + 1)).html();
                serviceOrderDList.push(serviceOrderD);
            }
            serviceOrder.serviceOrderDList = serviceOrderDList;
            serviceOrderList.push(serviceOrder);
            dcomethealth.ServiceOrderResource.saveServiceOrderWithD(serviceOrderList).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('Radiology');
            }).fail(function (jqXHR, textStatus, Patients) {
                alert("Service Failed");
            });
        } else {
            alert("Add anyone template");
            return false;
        }
    }
    function refreshData() {
    }
    return {
        init: init,
        addTemplates: addTemplates,
        addTempNodes: addTempNodes,
        viewModal: viewModal,
        saveTemplates: saveTemplates,
        searchSOByDate: searchSOByDate,
        searchCompleted: searchCompleted,
        viewTemplates: viewTemplates,
        getServiceOrderD: getServiceOrderD,
        viewPendingService: viewPendingService,
        viewCompletedService: viewCompletedService,
        uploadAttachFiles: uploadAttachFiles,
        attachFiles: attachFiles,
        getTemplates: getTemplates,
        exportPrint: exportPrint,
        refreshData: refreshData
    }
    ;
}());
dcomethealth.Radiology.init();
