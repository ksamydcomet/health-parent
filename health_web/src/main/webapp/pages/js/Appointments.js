var dcomethealth = dcomethealth || {};
var selectedDate = new Date();
var visitToday = 0;
dcomethealth.Appointments = (function () {
    var id = "Appointments", resourceObj = {}, doctorRID = null;
    function init() {
        var tab = "";
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#appointmentsDate').datepaginator({
                onSelectedDateChanged: function (event, date) {
                    if (dcomethealth.loginuser.entityRid == 4) {
                        doctorRID = $('#docRid').val();
                    } else {
                        doctorRID = $('#doc_Rid').val();
                    }
                    if (doctorRID == "") {
                        alert("Select Doctor Name");
                        return false;
                    } else {
                        selectedDate = date;
                        var isValid = false;
                        if ((moment().format("DD-MM-YYYY") != moment(selectedDate).format("DD-MM-YYYY") && (moment(selectedDate).isBefore(moment())))) {
                            alert("Select Valid Date");
                            $("#time_table").empty();
                            return false;
                        } else {
                            if (!!resourceObj) {
                                $.each(resourceObj, function (ix, resource) {
                                    if (doctorRID == resource.id) {
                                        $.each(resource.resourceWorkingHours, function (ix, resourceWH) {
                                            $.each(dcomethealth.dDictVal, function (index, val) {
                                                if (parseInt(resourceWH.whDayOfWeek) == val.id) {
                                                    if (val.ddictValue == moment(selectedDate).format("dddd")) {
                                                        isValid = true;
                                                        setScheduleTimeTable(resourceWH.whFromTime, resourceWH.whToTime);
                                                        return false;
                                                    } else {
                                                        if (moment(selectedDate).isAfter(moment()) && !isValid) {
                                                            $("#time_table").empty();
//                                                            alert("Doctor not available this date");
                                                            return false;
                                                        }
                                                    }
                                                }
                                            });
                                        });
                                    }
                                });
                            } else {
                                $("#time_table").empty();
                            }
                        }
                        var table = document.getElementById('dyn_table');
                        var table_length = table.rows.length;
                        for (var i = 0; i < table_length - 1; i++) {
                            dynTableGetNodeInRow(table.rows[i], 'cal_val').innerHTML = "";
                        }
                        viewAppointments();
                    }
                }
            });

//            dcomethealth.DataDictionaryResource.getSysParam({"paramCode": "LOAD_DOCTOR_NAME"}, function (data) {
//                if (!!data) {
            var check = false;
            if (dcomethealth.loginuser.entityRid == 4) {
                $('#doctorname').show();
                $('#docname').hide();
                dcomethealth.MasterResource.searchResource({}, function (data) {
                    resourceObj = data;
                    $.each(data, function (idx, Val) {
                        $("#doct_nme").append('<option value="' + Val.id + '">' + Val.resName + '</option>');
                        $("#doct_nme").on('change', function (e) {
                            var valueSelected = this.value;
                            $('#docRid').val(Val.id);
                            $.each(Val.resourceWorkingHours, function (idx, resourceWH) {
                                if (valueSelected == resourceWH.whResourceRID) {
                                    $.each(dcomethealth.dDictVal, function (index, val) {
                                        if (parseInt(resourceWH.whDayOfWeek) == val.id) {
                                            if (val.ddictValue == moment(selectedDate).format("dddd")) {
                                                setScheduleTimeTable(resourceWH.whFromTime, resourceWH.whToTime);
                                                check = true;
                                            }
                                        }
                                    });
                                }
                            });
                            $('#timing').attr('step', (Val.resSchedInterval) * 60);
                            if (check) {
                                viewAppointments();
                            }
                        });
                    });
                });
            } else {
                $('#doctorname').hide();
                $('#docname').show();
                var rid = "";
                $("#" + id + " input[name='doc_name']").autocomplete({
                    select: function (event, ui) {
                        $('#doc_Rid').val(ui.item.data.id);
                        rid = ui.item.data.id;
                        $.each(ui.item.data.resourceWorkingHours, function (idx, resourceWH) {
                            $.each(dcomethealth.dDictVal, function (index, val) {
                                if (parseInt(resourceWH.whDayOfWeek) == val.id) {
                                    if (val.ddictValue == moment(selectedDate).format("dddd")) {
                                        setScheduleTimeTable(resourceWH.whFromTime, resourceWH.whToTime);
                                    }
                                }
                            });
                        });
                        $('#timing').attr('step', (ui.item.data.resSchedInterval) * 60);
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
                        viewAppointments();
                    },
                    source: function (request, response) {
                        var queryString = request.term;
                        queryString = queryString.trim();
                        var searchParams = {"resName": queryString};
                        dcomethealth.MasterResource.searchResource(searchParams, function (data) {
                            resourceObj = data;
                            response($.grep($.map(data, function (item) {
                                return {
                                    label: (item.resName || ""),
                                    value: (item.resName || ""),
                                    name: (item.resName || ""),
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
//            });
            $("#apptSave").off("click").on("click", function () {
                if ($("#doc_Rid").val() != "" || $("#docRid").val() != "") {
                    saveAppointments();
                } else {
                    alert("Enter Doctor Name");
                    return false;
                }
            });
            $("#pat_save").click(function () {
                $('#patDiv').addClass('hidden');
                if (parseInt(visitToday) === 0) {
                    if ($('#pat_name').val() !== "") {
                        var PatientName = $('#pat_name').val();
                        var patientRID = $('#patientRID').val();
                        var PatientMobile = $('#pat_mobile').val();
                        var PatientTime = $('#timing').val();
                        var currentRow = $('#currentRow').val();
                        var currentRowValue = $('#currentRowValue').val();
                        var table = document.getElementById('patientsTable');
                        var table_length = table.rows.length;
                        for (var i = 0; i < table_length; i++) {
                            var patRid = dynTableGetNodeInRow(table.rows[i], 'patRID').value;
                            if (parseInt(patientRID) == parseInt(patRid)) {
                                alert("Already Booked Patient");
                                return false;
                            }
                        }
                        if (currentRowValue != "") {
                            var checkTime = currentRowValue.split('[');
                            var i = 1;
                            for (var j = 0; j < checkTime.length - 1; j++) {
                                var time = checkTime[j + 1].split('-');
                                if (PatientTime != time[0]) {
                                    i++;
                                }
                            }
                            if (parseInt(i) !== parseInt(checkTime.length - 1)) {
                                tab = PatientTime + '-' + PatientName + '-' + PatientMobile;
                                var table = document.getElementById('dyn_table');
                                var table_length = table.rows.length;
                                for (var i = 0; i < table_length - 1; i++) {
                                    dynTableGetNodeInRow(table.rows[currentRow - 1], 'cal_val').innerHTML = currentRowValue + '<a onclick="openPatient(' + patientRID + ',' + "" + ')">[' + tab + ']</a><input type="hidden" id="patRID" value="' + patientRID + '"/>';
                                }
                            } else {
                                alert("Slot is already Taken");
                                return false;
                            }
                        } else {
                            tab = PatientTime + '-' + PatientName + '-' + PatientMobile;
                            var table = document.getElementById('dyn_table');
                            var table_length = table.rows.length;
                            var valid = false;
                            for (var i = 0; i < table_length - 1; i++) {
                                valid = true;
                                dynTableGetNodeInRow(table.rows[currentRow - 1], 'cal_val').innerHTML = currentRowValue + '<a onclick="openPatient(' + patientRID + ',' + "" + ')">[' + tab + ']</a>';
                            }
                            if (valid) {
                                $("#patientsTbody").append('<tr><td><input type="hidden" id="patRID" value="' + patientRID + '"/></td></tr>');
                            }
                        }
                    } else {
                        alert("Enter Patient Name");
                        return false;
                    }
                } else {
                    alert("Patient is Active Today");
                    $('#mrn_no').val();
                    $('#patientRID').val();
                    $('#pat_name').val();
                    $('#pat_mobile').val();
                }
            });
        });
    }
//    function selectResource() {
//        $.each(resourceData, function (idx, val) {
//            if (parseInt($("#doct_nme").val()) == parseInt(val.id)) {
//                $('#doc_Rid').val(val.id);
//                viewAppointments();
//                $.each(val.resourceWorkingHours, function (idx, resourceWH) {
//                    setScheduleTimeTable(resourceWH.whFromTime, resourceWH.whToTime);
//                });
//            }
//            $('#timing').attr('step', (val.resSchedInterval) * 60);
//        });
//    }
    function viewAppointments() {
        dcomethealth.AppointmentsResource.searchAppointments({"apptFromDate": moment(selectedDate)}, function (data) {
            if (data != undefined) {
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length; i++) {
                    var arr = (dynTableGetNodeInRow(table.rows[i], 'cal_time').innerHTML).split('<input');
                    $.each(data, function (idx, appt) {
                        $.each(appt.appointmentResourceMap, function (id, arm) {
                            var docRID = (dcomethealth.loginuser.entityRid == 4) ? $("#docRid").val() : $("#doc_Rid").val();
                            if (parseInt(arm.armResourceRid) === parseInt(docRID)) {
                                var a = moment(arr[0], 'hh:mm A').format('HH:mm:ss');
                                var c = moment(arr[0], 'hh:mm A').add('hours', 1).format('HH:mm:ss');
                                var b = moment(appt.apptFromTime, 'HH:mm:ss').format('HH:mm:ss');
                                if ((a <= b) && (b < c)) {
                                    dynTableGetNodeInRow(table.rows[i], 'cal_val').innerHTML = dynTableGetNodeInRow(table.rows[i], 'cal_val').innerHTML + '<a onclick="openPatient(' + appt.apptPatientRID + ',' + appt.id + ')">[' + appt.apptFromTime + '-' + appt.apptPatientName + '-' + appt.apptPatientPhone + ']</a>';
                                }
                            }
                        });
                    });
                }
            } else {
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    dynTableGetNodeInRow(table.rows[i], 'cal_val').innerHTML = "";
                }
            }
        });

    }
    function autocompletePat() {
        $("#" + id + " input[name='mrn_no']").autocomplete({
            select: function (event, ui) {
                var patMrnNo = ui.item.value;
                visitToday = 0;
                $('#patientRID').val(parseInt(ui.item.data.id));
                $('#pat_name').val(ui.item.data.patFullName);
                $('#pat_mobile').val(ui.item.data.patPhoneNo);
                dcomethealth.PatientResource.searchVisit({"visPatRid": parseInt(ui.item.data.id)}, function (data2) {
                    $.each(data2, function (i, visit) {
                        if (moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') === moment().format('DD-MM-YYYY')) {
                            visitToday++;
                        }
                    });
                });
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
            }, close: function () {
                $(document).tooltip("destroy");
            },
            source: function (request, response) {
                var queryString = request.term;
                queryString = queryString.trim();
                var searchParams = {"patMrnNo": queryString};
                dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
                    response($.grep($.map(data, function (item) {
                        return {
                            label: (item.patMrnNo || "") + (item.patMrnNo && item.patPhoneNo ? " - " : "") + (item.patPhoneNo || ""),
                            value: (item.patMrnNo || ""),
                            name: (item.patTitle || "") + (item.patTitle && item.patName ? " - " : ""),
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
    function setScheduleTimeTable(StartTime, EndTime) {
        StartTime = moment(StartTime, 'HH:mm:ss');
        EndTime = moment(EndTime, 'HH:mm:ss');
        $("#time_table").empty();
        var i = 1;
        for (var m = StartTime; m.isBefore(EndTime); m.add('hours', 1)) {
            $("#time_table").append('<tr data-toggle="modal" href="#myModal" onclick="VisitTime(this)"><td id="cal_time">' + m.format('hh:mm A') + '<input type="hidden" id="dynRow" value="' + i + '"/></td><td id="cal_val"></td></tr>');
            i++;
        }
    }
    function saveAppointments() {
        var appointmentsList = [];
        var date = moment(selectedDate).format("DD-MM-YYYY");
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length; i++) {
            var str2 = dynTableGetNodeInRow(table.rows[i], 'cal_val').innerHTML;
            if (str2 != "") {
                var String = str2.split(']');
                for (var j = 0; j < String.length - 1; j++) {
                    var appointmentResourceMapList = [];
                    var str3 = String[j].split('[');
                    var pos = String[j].split('(');
                    var pos2 = pos[1].split(')');
                    var pos3 = pos2[0].split(',');
                    var appointments = {};
                    var array = str3[1].split('-');
                    if (pos3[1] != "") {
                        appointments.id = pos3[1];
                    }
                    if (pos3[1] === "") {
                        var arm = {};
                        arm.armResourceRid = (dcomethealth.loginuser.entityRid == 4) ? $("#docRid").val() : $("#doc_Rid").val();
                        appointmentResourceMapList.push(arm)
                    }
                    appointments.apptUnitRID = dcomethealth.selectedunit;
                    appointments.apptFromDate = date;
                    appointments.apptFromTime = moment(array[0], 'HH:mm').format('HH:mm:ss');
                    if (!!pos3[0]) {
                        appointments.apptPatientRID = pos3[0];
                    }
                    appointments.apptPatientName = array[1];
                    appointments.apptPatientPhone = array[2];
//                    appointments.apptPatientMrn = $("#mrn_no").val();
                    appointments.apptFromDateTime = moment(date + " " + array[0], 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY HH:mm:ss');
                    appointments.apptDocRID = (dcomethealth.loginuser.entityRid == 4) ? $("#docRid").val() : $("#doc_Rid").val();
                    appointments.appointmentResourceMap = appointmentResourceMapList;
                    //                appointments.apptToDateTime = $("#patientRID").val();
                    appointmentsList.push(appointments);
                }
            }
        }
        dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                .done(function (data, textStatus, jqXHR) {
                    alert("Saved");
                    dcomethealth.util.loadpage('Appointments');
                    dcomethealth.util.base_init();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function refreshData() {
    }
    return {
        init: init,
        viewAppointments: viewAppointments,
        autocompletePat: autocompletePat,
        setScheduleTimeTable: setScheduleTimeTable,
        saveAppointments: saveAppointments,
//        selectResource: selectResource,
        refreshData: refreshData
    };
}());
dcomethealth.Appointments.init();
