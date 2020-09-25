var dcomethealth = dcomethealth || {};
//var templt = '';
var docPatients = {};
dcomethealth.MyPatients = (function () {
    var id = "MyPatients";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            setDateSearch();
            specialityChange();
        });
    }
    function setDateSearch() {
        var start = moment().endOf('days');
        var end = moment().endOf('days');
        $('#visitDateRangeSpan').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        $('#visitDateRange').daterangepicker({
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
            $('#visitDateRange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        });
    }
    function specialityChange() {
        $("#specialist").change(function () {
            var searchParams = {"staffSpecialityIndex": $('#specialist option:selected').val()};
            clearDoctorVisits();
            dcomethealth.MasterResource.searchStaff(searchParams, function (staffs) {
                if (!!staffs) {
                    var options = '';
                    var select_staff_id = 0;
                    if (staffs.length > 1) {
                        options += '<option value="0">--Select--</option>';
                    }
                    $.each(staffs, function (idx, staff) {
                        options += '<option value="' + staff.id + '">' + staff.staffName + '</option>';
                        select_staff_id = staff.id;
                    });
                    $("#doc_name").html(options);
                    if (!!staffs && staffs.length === 1) {
                        $('#doc_name').val(select_staff_id);
                        loadDoctorVisits();
                    }
                } else {
                    $("#doc_name").html('<option value="0">--Name--</option>');
                }
            });
        });
        $("#doc_name").change(function () {
            loadDoctorVisits();
        });
    }
    function clearDoctorVisits() {
// Clear the table data
        var tables = $.fn.dataTable.fnTables(true);
        $(tables).each(function () {
            $(this).dataTable().fnClearTable();
            $(this).dataTable().fnDestroy();
        });
        $('#tbody_pat').empty();
    }
    function loadDoctorVisits() {
        clearDoctorVisits();
        if ($('#doc_name option:selected').val() === "0") {
            return;
        }
        // load doctor's patient visit list
        var date = $('#visitDateRangeSpan').html().split('-');
        var searchParams = {"visFromDate": moment(date[0]), "visToDate": moment(date[1]).add(1, 'days'), "visConsDocRid": $('#doc_name option:selected').val(), "sortOrder": ["createdDateTime"], "sortDesc": "desc"};
        dcomethealth.PatientResource.searchVisit(searchParams, function (visit4Doctor) {
            var categories = [];
            if (!!visit4Doctor) {
                $.each(docPatients = visit4Doctor, function (idx, visit) {
                    if ($.inArray(visit.visPatRid, categories) === -1) {
                        categories.push(visit.visPatRid);
                        $.each(visit.patient, function (idx, patient) {
                            $('#tbody_pat').append('<tr><td><a href="#" onclick="dcomethealth.MyPatients.loadPatientVisits(' + patient.id + ',' + visit.id + ')">' + patient.patMrnNo + '</a></td>\n\
                                <td>' + patient.patFullName + '</td><td>' + patient.patPhoneNo + '</td><td>' + moment(patient.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td>\n\
                                <td>' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</td></tr>');
                        });
                    }
                    $("#mPatTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
                });
            }
        });
    }
    function loadPatientVisits(patRID, visitRid) {
        $('#myPatDiv').removeClass("hidden");
        $('#mPatHeader').addClass("hidden");
        loadPatient(patRID);
        loadPatientVisitTab(patRID);
        loadPatientVisit(visitRid, patRID);
    }
    function loadPatient(patRID) {
        $.each(docPatients, function (idx, visit) {
            $.each(visit.patient, function (pIdx, patient) {
                if (patient.id == patRID) {
                    $('#mrnSearch').val(patient.patMrnNo);
                    $('#patientRID').val(patient.id);
                    $('#PatientName').val(patient.patFullName);
                    if (parseInt(patient.patGenderIndex) === 1) {
                        $('#patGender').val('Male');
                    } else if (parseInt(patient.patGenderIndex) === 2) {
                        $('#patGender').val('Female');
                    } else if (parseInt(patient.patGenderIndex) === 3) {
                        $('#patGender').val('Transgender');
                    }
                    var c = patient.patDob.split('-');
                    $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                    $('#patientMobile').val(patient.patPhoneNo);
                }
            });
        });
    }
    function loadPatientVisitTab(visPatRid) {
        var search = {"visPatRid": visPatRid, "sortOrder": ["createdDateTime"], "sortDesc": "desc"};
        dcomethealth.PatientResource.searchVisit(search, function (visits) {
            if (!!visits) {
                $("#patient_visit_tab").empty();
                $.each(visits, function (pIdx, visit) {
                    if (pIdx === 0) {
                        $('#patient_visit_tab').append('<li class="active" onclick="dcomethealth.MyPatients.loadPatientVisit(' + visit.id + ', ' + visPatRid + ')"><a href="#visit1" data-toggle="tab">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</a><input id="visitRid" name="visitRid" class="activeVisitRid" type="hidden" value="' + visit.id + '"></li>');
                    } else {
                        $('#patient_visit_tab').append('<li class="" onclick="dcomethealth.MyPatients.loadPatientVisit(' + visit.id + ', ' + visPatRid + ')"><a href="#visit1" data-toggle="tab">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</a><input id="visitRid" name="visitRid" class="hiddenVisitRid" type="hidden" value="' + visit.id + '"></li>');
                    }
                });
            }
        });
    }
    function loadPatientVisit(visitRid, visPatRid) {
        $("#template").empty();
        $('.activeVisitRid').val(visitRid);
        dcomethealth.DataDictionaryResource.searchTemplate({"tempRID": $('#specialist option:selected').val()}, function (templates) {
            $.each(templates, function (index, template) {
                $("#template").append(template.tempNodes);
                dcomethealth.DataDictionaryResource.searchTemplate({"id": 1}, function (complaint_templates) {
                    $.each(complaint_templates, function (index, complaint_template) {
                        $("#complaints_block").append(complaint_template.tempNodes);
                        dcomethealth.complainttemplate.setValue(visitRid);
                    });
                });
                dcomethealth.DataDictionaryResource.searchTemplate({"id": 2}, function (vital_templates) {
                    $.each(vital_templates, function (index, vital_template) {
                        $("#vital_block").append(vital_template.tempNodes);
                        dcomethealth.vitaltemplate.setValue(visitRid);
                    });
                });
                dcomethealth.DataDictionaryResource.searchTemplate({"id": 3}, function (so_templates) {
                    $.each(so_templates, function (index, so_template) {
                        $("#service_order_block").append(so_template.tempNodes);
                        dcomethealth.soordertemplate.setValue(visitRid);
                        dcomethealth.soordertemplate.autocompleteFavServiceOrder();
                    });
                });
                dcomethealth.DataDictionaryResource.searchTemplate({"id": 4}, function (rxo_templates) {
                    $.each(rxo_templates, function (index, rxo_template) {
                        $("#diabetic_rx_block").append(rxo_template.tempNodes);
                        dcomethealth.rxordertemplate.setValue(visitRid);
                        dcomethealth.rxordertemplate.autocompleteFavItemOrder();
                    });
                });
                var searchObj = {"vistVisitRID": visitRid, "vistPatRID": visPatRid};
                dcomethealth.ClinicalResource.searchVisitTemplate(searchObj, function (visitTemplate) {
                    if (!!visitTemplate) {
                        $.each(visitTemplate, function (index, t_visit_template) {
                            $('#visitTempRID').val(t_visit_template.id);
                            var visit_template_data = jQuery.parseJSON(t_visit_template.vistNodes);
                            dcomethealth.template.setValue(visit_template_data);
                        });
                    } else {
                        $('#savebtn').removeClass("hidden");
                        if (null != template.tempDefaultData) {
                            dcomethealth.template.setValue(jQuery.parseJSON(template.tempDefaultData));
                        }
                    }
                    dcomethealth.datatypes.init($("#" + id));
                });
            });
        });
    }
    function autocompleteSku() {
        var skuName = "";
        $("#" + id + " input[name='drugOrderReq']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                var Auto = this;
                dynTableGetNodeInRow(this, 'drugOrderReqSkuRid').value = ui.item.data.id;
//                var searchParams = {"stkSkuName": skuName};
//                dcomethealth.InventoryResource.searchStockH(searchParams, function (data) {
//                    if (data != undefined) {
//                        $.grep($.map(data, function (item) {
//                            item.id = item.stkBatchNo;
//                            var opt = document.createElement("option");
//                            opt.value = item.stkBatchNo;
//                            opt.text = item.stkBatchNo;
//                            var opt1 = document.createElement("option");
//                            opt1.value = item.stkExpiryDate;
//                            opt1.text = item.stkExpiryDate;
//                            var opt2 = document.createElement("option");
//                            opt2.value = item.stkMrp;
//                            opt2.text = item.stkMrp;
//                            dynTableGetNodeInRow(Auto, 'skuRID').value = item.stkSkuRID;
//                            dynTableGetNodeInRow(Auto, 'batchNo').options.add(opt);
//                            dynTableGetNodeInRow(Auto, 'expDate').options.add(opt1);
//                            dynTableGetNodeInRow(Auto, 'MRP').options.add(opt2);
//                            $.each(item.stockDChildList, function (pIdx, stockD) {
//                                var opt3 = document.createElement("option");
//                                opt3.value = stockD.stkdQty;
//                                opt3.text = stockD.stkdQty;
//                                dynTableGetNodeInRow(Auto, 'Stkqty').options.add(opt3);
//                            });
//                        }), function (item, index) {
//                            return index < 75;
//
//                        };
//                    } else {
//                        alert("Out of stock");
//                        return false;
//                    }
//                });

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
    function save() {

        var visit = {};
        var visitTemplateList = [];
        var visitServiceReqList = [];
        var visitTemplate = {};
        if (!!$('#visitTempRID').val()) {
            visitTemplate.id = $('#visitTempRID').val();
        }
        visitTemplate.vistVisitRID = $('.activeVisitRid').val();
        visitTemplate.vistPatRID = $('#patientRID').val();
        visitTemplate.vistNodes = JSON.stringify(dcomethealth.template.getValue());
        visitTemplate.vistSeqNum = 1;
        visitTemplateList.push(visitTemplate);
        visit.visitTemplate = visitTemplateList;
        visit.complaints = dcomethealth.complainttemplate.getValue();
        visit.visitVitals = dcomethealth.vitaltemplate.getValue();
//        visit.serviceRequest = dcomethealth.soordertemplate.getValue();
//        visit.serviceRequestDrug = dcomethealth.rxordertemplate.getValue();
//        visit.visitPlan = dcomethealth.rxordertemplate.getValue();        

        dcomethealth.ClinicalResource.saveMyPatient(visit).done(function (data, textStatus, jqXHR) {
            dcomethealth.util.loadpage('MyPatients');
            dcomethealth.util.base_init();
            loadNotification();
            alert("Saved");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    return {
        init: init,
        setDateSearch: setDateSearch,
        clearDoctorVisits: clearDoctorVisits,
        loadDoctorVisits: loadDoctorVisits,
        loadPatientVisits: loadPatientVisits,
        loadPatientVisit: loadPatientVisit,
        autocompleteSku: autocompleteSku, // ?     
        save: save
    };
}());
dcomethealth.MyPatients.init();
