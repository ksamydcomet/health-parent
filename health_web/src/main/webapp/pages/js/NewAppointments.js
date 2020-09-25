var dcomethealth = dcomethealth || {};
dcomethealth.NewAppointments = (function () {
    var id = "NewAppointments", visitToday = 0, selectedDate = new Date(), calendar = null;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
             dcomethealth.datatypes.init($("#" + id));
            $('#patientImg').attr('src', '/health_web/rest/file/user/photo?id=' + dcomethealth.loginuser.id);
            $('#patientImg1').attr('src', '/health_web/rest/file/user/photo?id=' + dcomethealth.loginuser.id);
            searchDoctor();
            autoCompletePatient();
//            var date = new Date();
//            var d = date.getDate();
//            var m = date.getMonth();
//            var y = date.getFullYear();
//            var todaysDate = moment();
            $('#calendar').fullCalendar('getView');
            calendar = $('#calendar').fullCalendar({
                header: {left: 'prev,next today', center: 'title', right: 'month,agendaWeek,agendaDay'}, selectable: true, selectHelper: true,
//                defaultView: 'agendaWeek',
                editable: true,
                disableDragging: true,
                slotMinutes: 10,
                eventLimit: true, // for all non-agenda views
                eventLimitText: "more",
//                views: {
//                    month: {
//                        eventLimit: 3 // adjust to 5 only for agendaWeek/agendaDay
//                    },
//                    agendaWeek: {
//                        eventLimit: 3
//                    },
//                    agendaDay: {
//                        eventLimit: 3
//                    }
//                },
//                slotDuration: '00:10:00',
//                droppable: true,
//                selectable: true,
                //header and other values
                select: function (start, end, allDay) {
                    endtime = $.fullCalendar.formatDate(end, 'h:mm tt');
                    starttime = $.fullCalendar.formatDate(start, 'ddd, MMM d, h:mm tt');
                    var sDate = $.fullCalendar.formatDate(start, 'ddd, MMM d yyyy, h:mm');
                    var appDate = $.fullCalendar.formatDate(start, 'dd-MM-yyyy');
                    selectedDate = sDate;
                    var appFromTime = $.fullCalendar.formatDate(start, 'HH:mm');
                    var appToTime = $.fullCalendar.formatDate(end, 'HH:mm');
                    var mywhen = starttime + ' - ' + endtime;
                    $('#createEventModal #appDate').val(appDate);
                    $('#createEventModal #appFromTime').val(appFromTime);
                    $('#createEventModal #appToTime').val(appToTime);
                    $('#createEventModal #apptStartTime').val(start);
                    $('#createEventModal #apptEndTime').val(end);
                    $('#createEventModal #apptAllDay').val(allDay);
                    $('#createEventModal #when').text(mywhen);
                    if ($("#doctorName").val() != "") {
                        $('#createEventModal').modal('show');
                        viewAppointments();
                    } else {
                        alert("Enter Doctor Name");
                        return false;
                    }
                },
//                eventDrop: function (event, revertFunc) {
////                    console.log(event);
////                    console.log(calendar.fullCalendar('eventDrop'));
//                    if (!confirm("Are you sure about this change?")) {
//                        revertFunc();
//                    }
//                    savePatients();
//                },
                //eventOverlap: false,
//                eventDrop: function (eventData, delta, revertFunc) {
//                    if (moment(eventData._start).format('YYYY-MM-DD') < todaysDate.format('YYYY-MM-DD')) {
////                        alert("True");
//                        //block modification of old dates
////                        revertFunc();
//                    }
//                },

                eventClick: function (event) {
                    var appointmentsList = [];
                    if (confirm("Will Remove this Event, Do you want to Proceed") === false) {
                        return false;
                    } else {
                        var table = document.getElementById('dynTable');
                        for (var i = 0; i <= table.rows.length - 1; i++) {
                            var appointments = {}, appResourceMapList = [];
                            var patName = dynTableGetNodeInRow(table.rows[i + 1], 'patName').value;
                            if (event.title == patName) {//event.className != "highPriority"
                                if (dynTableGetNodeInRow(table.rows[i + 1], 'apptRid').value != "") {
                                    appointments.id = dynTableGetNodeInRow(table.rows[i + 1], 'apptRid').value;
                                }
                                if (dynTableGetNodeInRow(table.rows[i + 1], 'apptRid').value === "") {
                                    var arm = {};
                                    arm.armResourceRid = $("#doctorRid").val();
                                    appResourceMapList.push(arm);
                                }
                                appointments.apptUnitRID = dcomethealth.selectedunit;
                                var fromDate = dynTableGetNodeInRow(table.rows[i + 1], 'patAppDate').value;
                                var fromTime = dynTableGetNodeInRow(table.rows[i + 1], 'patApptTime').value;
                                appointments.apptFromDate = dynTableGetNodeInRow(table.rows[i + 1], 'patAppDate').value;
                                appointments.apptFromTime = moment(fromTime, 'HH:mm').format('HH:mm:ss');
                                appointments.apptPatientRID = dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value;
                                appointments.apptPatientName = dynTableGetNodeInRow(table.rows[i + 1], 'patName').value;
                                appointments.apptPatientPhone = dynTableGetNodeInRow(table.rows[i + 1], 'patMobileNo').value;
                                appointments.apptPatientMrn = dynTableGetNodeInRow(table.rows[i + 1], 'mrnNo').value;
                                appointments.apptFromDateTime = moment(fromDate + " " + fromTime, 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY HH:mm:ss');
                                appointments.apptDocRID = $("#doctorRid").val();
                                appointments.apptAttendingUserRID = $("#doctorRid").val();
                                appointments.apptStatus = 2; //For Cancel Appointments
                                appointments.appointmentResourceMap = appResourceMapList;
                                //                appointments.apptToDateTime = $("#patientRID").val();
                                appointmentsList.push(appointments);
                                dynTableDeleteRow(dynTableGetNodeInRow(table.rows[i + 1]));
                                $('#calendar').fullCalendar('removeEvents', event._id);
                            }
                        }
                        dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                                .done(function (data, textStatus, jqXHR) {
                                    alert("Appointment Cancelled");
//                                    dcomethealth.util.loadpage('Appointment');
//                                    dcomethealth.util.base_init();
                                }).fail(function (jqXHR, textStatus, errorThrown) {
                            alert("Failed");
                        });
                    }
                },
                buttonText: {prev: '<i class="fa fa-angle-left"></i>', next: '<i class="fa fa-angle-right"></i>',
                    prevYear: '<i class="fa fa-angle-double-left"></i>', nextYear: '<i class="fa fa-angle-double-right"></i>',
                    today: 'Today', month: 'Month', week: 'Week', day: 'Day'}
            });
//            $('#calendar').limitEvents(4);
            $('#savePatients').on('click', function (e) {
                e.preventDefault();
                $("#calendar").fullCalendar('event', {title: $('#patientName').val(), start: new Date($('#apptStartTime').val()), end: new Date($('#apptEndTime').val()), allDay: ($('#apptAllDay').val() == "true")}, true);
                if (parseInt(visitToday) === 0) {
                    savePatients();
                } else {
                    alert("Patient is Active Today");
                    $("#patMrnNo,#patientName,#patientMobileNo").val('');
                }
            });
            $('#saveApp').on('click', function () {
                if ($("#doctorRid").val() != "") {
                    saveAppointments(0, 0);
                } else {
                    alert("Enter Doctor Name");
                    return false;
                }
            });
        });
    }
    function autoCompletePatient() {
        $("#" + id + " input[name='patMrnNo']").item_autocomplete({
            select: function (event, ui) {
                $('#patMrnNo').val(ui.item.data.patMrnNo);
                $('#patientName').val(ui.item.data.patFullName);
                $('#patRid').val(ui.item.data.id);
                $('#patientMobileNo').val(ui.item.data.patPhoneNo);
                visitToday = 0;
                dcomethealth.PatientResource.searchVisit({"visPatRid": parseInt(ui.item.data.id)}, function (data) {
                    if (!!data) {
                        $.each(data, function (i, visit) {
                            if (moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') === moment().format('DD-MM-YYYY')) {
                                visitToday++;
                            }
                        });
                    }
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
    function searchDoctor() {
        $("#" + id + " input[name='doctorName']").item_autocomplete({
            select: function (event, ui) {
                $('#doctorRid').val(ui.item.data.id);
                $('#doctorName').val(ui.item.data.value);
                viewAppointments();
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
                var searchParams = {"resName": queryString};
                dcomethealth.MasterResource.searchResource(searchParams, function (data) {
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

    function viewAppointments() {
        var events, checkDocRid = false, arrayList = [], booked = 0, waiting = 0, engaged = 0, completed = 0;
        dcomethealth.AppointmentsResource.searchAppointments({"apptFromDate": moment(selectedDate)}, function (data) {
            if (data != undefined) {
                $('#dynTbody').empty();
                $('#visitTable').empty();
                $("#bookedId").empty();
                $("#waitingId").empty();
                $("#engagedId").empty();
                $("#completedId").empty();
                $.each(data, function (idx, appt) {
                    $.each(appt.appointmentResourceMap, function (id, arm) {
                        if (parseInt(arm.armResourceRid) === parseInt($("#doctorRid").val())) {
                            events = {
                                title: appt.apptPatientName,
                                start: moment(appt.apptFromDateTime, "DD-MM-YYYY hh:mm:ss").format('YYYY-MM-DD hh:mm'),
                                className: "evtBackGround"
//                                backgroundColor: '#85c744'
                            };
                            arrayList.push(events);
                            checkDocRid = true;
                            $('#calendar').fullCalendar('removeEvents'); //remove all events
                            $('#calendar').fullCalendar('renderEvent', arrayList, true);
                            $("#calendar").fullCalendar('addEventSource', arrayList); // add event in calendar                           
                            if (appt.apptStatus == 1) {
                                booked++;
                            } else if (appt.apptStatus == 2) {
                                waiting++;
                            } else if (appt.apptStatus == 3) {
                                engaged++;
                            } else if (appt.apptStatus == 4) {
                                completed++;
                            }
                            $('#dynTbody').append('<tr><td width="25%"><input type="text" id="mrnNo" name="mrnNo" maxlength="100" class="form-control col-md-11 col-sm-11 col-xs-11" value="' + appt.apptPatientMrn + '" /><input type="hidden" id="apptRid" name="apptRid" value="' + appt.id + '"/><input type="hidden" id="patRID" name="patRID" value="' + appt.apptPatientRID + '"/><input type="hidden" id="patAppDate" name="patAppDate" value="' + appt.apptFromDate + '"/></td>\n\
                            <td width="25%"><input type="text" id="patName" name="patName" maxlength="10" value="' + appt.apptPatientName + '" class="form-control col-md-12 col-sm-12 col-xs-12" /></td>\n\
                            <td width="25%"><input type="text" id="patMobileNo" name="patMobileNo" maxlength="10" value="' + appt.apptPatientPhone + '" class="form-control col-md-12 col-sm-12 col-xs-12" /></td>\n\
                            <td width="25%"><input class="form-control col-md-12 col-sm-12 col-xs-12" id="patApptTime" type="text" name="patApptTime" maxlength="10" value="' + appt.apptFromTime + '"/></td><td width="25%"><input class="form-control col-md-12 col-sm-12 col-xs-12" id="patApptState" type="text" name="patApptState" maxlength="10" value="' + appt.apptStatus + '"/></td></tr>');
                            $('#visitTable').append('<div><img src="css/images/avatar.png" height="25" width="25"></img><span id="statusId"></span><span id="mrnNo"><b><small>' + appt.apptPatientMrn + '</small></b></span><span>' + "/" + '</span><input type="hidden" id="apptRid" name="apptRid" value="' + appt.id + '"/><input type="hidden" id="patRID" name="patRID" value="' + appt.apptPatientRID + '"/></td>\n\
                            <span id="patName"><b><small>' + appt.apptPatientName + '</small></b></span><div class="pull-right btn-group"><button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"><i class="fa fa-pencil"></i></button><ul class="dropdown-menu"><li onclick="dcomethealth.Appointment.saveAppointments(' + appt.apptPatientRID + ',this)" value="1"><a href="#"><span id="patName"></span><small>Reached</small></a></li><li onclick="dcomethealth.Appointment.saveAppointments(' + appt.apptPatientRID + ',this)" value="2"><a href="#"><small>Engaged</small></a></li><li onclick="dcomethealth.Appointment.saveAppointments(' + appt.apptPatientRID + ',this)" value="3"><a href="#"><small>Completed</small></a></li><li onclick="dcomethealth.Appointment.saveAppointments(' + appt.apptPatientRID + ',this)" value="4"><a href="#"><small>Cancel</small></a></li></ul></div>');
                            if (!!booked) {
                                $("#bookedId").text(booked);
                            } else {
                                $("#bookedId").text(0);
                            }
                            if (!!waiting) {
                                $("#waitingId").text(waiting);
                            } else {
                                $("#waitingId").text(0);
                            }
                            if (!!engaged) {
                                $("#engagedId").text(engaged);
                            } else {
                                $("#engagedId").text(0);
                            }
                            if (!!completed) {
                                $("#completedId").text(completed);
                            } else {
                                $("#completedId").text(0);
                            }
                        }
                    });
                });
                if (!checkDocRid) {
                    $('#calendar').fullCalendar('removeEvents');
                }
            } else {
                $('#calendar').fullCalendar('removeEvents');
            }
        });
    }
    function savePatients() {
        $("#createEventModal").modal('hide');
        $("#calendar").fullCalendar('renderEvent', {id: $('#patRid').val(), title: $('#patientName').val(), start: new Date($('#apptStartTime').val()), end: new Date($('#apptEndTime').val()), allDay: ($('#apptAllDay').val() == "true")}, true);
        var patientName = $('#patientName').val();
        if (!!$('#patRid').val() === "") {
            var patientRid = 1;
        } else {
            patientRid = $('#patRid').val();
        }
        var patientApptDate = $('#appDate').val();
        var patientApptTime = $('#appFromTime').val();
        var patientMobile = $('#patientMobileNo').val();
//        var patientMrnNo = $('#patMrnNo').val();

        if ($('#patMrnNo').val() === "") {
            savePat();
        } else {
            var patientMrnNo = $("#patMrnNo").val();
        }

//        <td width="25%"><input type="text" id="patMobileNo" name="patMobileNo" maxlength="10" value="' + patientMobile + '" class="form-control col-md-12 col-sm-12 col-xs-12" /></td>\n\
        $('#dynTbody').append('<tr><td width="25%"><input type="text" id="mrnNo" name="mrnNo" maxlength="100" class="form-control col-md-11 col-sm-11 col-xs-11" value="' + patientMrnNo + '" /><input type="hidden" id="apptRid" name="apptRid" value=""/><input type="hidden" id="patRID" name="patRID" value="' + patientRid + '"/><input type="hidden" id="patAppDate" name="patAppDate" value="' + patientApptDate + '"/></td>\n\
        <td width="25%"><input type="text" id="patName" name="patName" maxlength="10" value="' + patientName + '" class="form-control col-md-12 col-sm-12 col-xs-12" /></td>\n\
        <td width="25%"><input type="text" id="patMobileNo" name="patMobileNo" maxlength="10" value="' + patientMobile + '" class="form-control col-md-12 col-sm-12 col-xs-12" /></td>\n\
        <td width="25%"><input class="form-control col-md-12 col-sm-12 col-xs-12" id="patApptTime" type="text" name="patApptTime" maxlength="10" value="' + patientApptTime + '"/></td><td width="25%"><input class="form-control col-md-12 col-sm-12 col-xs-12" id="patApptState" type="text" name="patApptState" maxlength="10" value="' + 1 + '"/></td></tr>');
    }


    function savePat() {
        var patient = {}, visits = {};
        var visitPatDetails = [];
        patient.patName = $("#patientName").val();
        patient.patPhoneNo = $("#patMrnNo").val();
        patient.patFullName = $("#patientName").val();
        visitPatDetails.push(patient);
        visits.patient = visitPatDetails;
        var search = 0 + "/" + "VISIT" + "/" + "SUBMIT";
        dcomethealth.PatientResource.save(visits, search)
                .done(function (data, textStatus, jqXHR) {
//                    alert("Saved");
//                        dcomethealth.util.loadpage('Appointment');
//                        dcomethealth.util.base_init();
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveAppointments(val, elem) {
        var appointmentsList = [];
        var table = document.getElementById('dynTable');
        for (var i = 0; i < table.rows.length - 1; i++) {
            var appointmentResourceMapList = [];
            var appointments = {};
            if (dynTableGetNodeInRow(table.rows[i + 1], 'apptRid').value != "") {
                appointments.id = dynTableGetNodeInRow(table.rows[i + 1], 'apptRid').value;
            }
            if (dynTableGetNodeInRow(table.rows[i + 1], 'apptRid').value === "") {
                var arm = {};
                arm.armResourceRid = $("#doctorRid").val();
                appointmentResourceMapList.push(arm);
            }
            appointments.apptUnitRID = dcomethealth.selectedunit;
            var fromDate = dynTableGetNodeInRow(table.rows[i + 1], 'patAppDate').value;
            var fromTime = dynTableGetNodeInRow(table.rows[i + 1], 'patApptTime').value;
            appointments.apptFromDate = dynTableGetNodeInRow(table.rows[i + 1], 'patAppDate').value;
            appointments.apptFromTime = moment(fromTime, 'HH:mm').format('HH:mm:ss');
            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value) {
                appointments.apptPatientRID = dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value;
            } else {
                savePat();
                appointments.apptPatientRID = dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value = 0;
            }
            appointments.apptPatientName = dynTableGetNodeInRow(table.rows[i + 1], 'patName').value;
//            appointments.apptPatientPhone = dynTableGetNodeInRow(table.rows[i + 1], 'patMobileNo').value;
            appointments.apptPatientMrn = dynTableGetNodeInRow(table.rows[i + 1], 'mrnNo').value;
            appointments.apptFromDateTime = moment(fromDate + " " + fromTime, 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY HH:mm:ss');
            appointments.apptDocRID = $("#doctorRid").val();
            appointments.apptAttendingUserRID = $("#doctorRid").val();
            if (parseInt(val) != 0) {
                if (parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value) == parseInt(val)) {
                    appointments.apptStatus = elem.value == 1 ? 2 : elem.value == 2 ? 3 : elem.value == 3 ? 4 : elem.value == 4 ? 5 : 1; //For New Appointments
                    if (elem.value == 1) {
                        saveVisit(val);
                    }
                } else {
                    appointments.apptStatus = dynTableGetNodeInRow(table.rows[i + 1], 'patApptState').value;
                }
            } else {
                appointments.apptStatus = dynTableGetNodeInRow(table.rows[i + 1], 'patApptState').value;
            }
            appointments.appointmentResourceMap = appointmentResourceMapList;
            appointmentsList.push(appointments);
        }
        dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                .done(function (data, textStatus, jqXHR) {
//                    if (parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value) == parseInt(val)) {
//                        alert(parseInt(dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value) == parseInt(val));
//                        alert("Saved");
//                    } else {
                    alert("Saved");
                    dcomethealth.util.loadpage('NewAppointments');
                    dcomethealth.util.base_init();
//                    }
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveVisit(val) {
        var visit = {};
        var table = document.getElementById('dynTable');
        for (var i = 0; i < table.rows.length - 1; i++) {
//            var visit = {};
            if (dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value == parseInt(val)) {
                visit.visPatRid = dynTableGetNodeInRow(table.rows[i + 1], 'patRID').value;
            }
            visit.visDate = dynTableGetNodeInRow(table.rows[i + 1], 'patAppDate').value;
            visit.visConsDocRid = $("#doctorRid").val();
        }
        var search = 0 + "/" + "VISIT" + "/" + "SUBMIT";
        dcomethealth.PatientResource.saveVisit(visit, search).done(function (data, textStatus, jqXHR) {
//            alert("Saved");
//            dcomethealth.util.base_init();
//            dcomethealth.util.loadpage('Appointment');
//            loadNotification();
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }

    function refreshData() {
    }
    return {
        init: init,
        searchDoctor: searchDoctor,
        autoCompletePatient: autoCompletePatient,
        viewAppointments: viewAppointments,
        savePatients: savePatients,
        saveAppointments: saveAppointments,
        saveVisit: saveVisit,
        savePat: savePat,
        refreshData: refreshData
    };
}());
dcomethealth.NewAppointments.init();