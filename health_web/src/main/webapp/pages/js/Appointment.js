var dcomethealth = dcomethealth || {};
//holdDate = "";
dcomethealth.Appointment = (function () {
    var id = "Appointment", visitToday = 0, calendar = null, parts = [], holdDate = "", staffObj = {}, bookedPatientsList = [], cancelVar = false, engagedVar = false, completedVar = false, reachedVar = false, patAuto = 0;
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            autoCompletePatient();
            holdDate = moment().format("DD-MM-YYYY");
            holdDate = moment().format("DD-MM-YYYY");
            countCalculation();
            $('.jquery-calendar').datetimepicker({
                date: new Date(),
                viewMode: 'YMD',
                onDateChange: function () {
                    holdDate = this.getText('dd-MM-yyyy');
//                    holdDate = this.getText('dd-MM-yyyy');
                    viewAppointments();
                    $("#patDiv").addClass("hidden");
                }
            });
//            calendar = $('#calendar').fullCalendar({
//                header: {}, selectable: true, selectHelper: true,
////                defaultView: 'agendaWeek',
//                editable: true,
//                eventLimit: true,
//                height: 150,
//                width: 150,
////})
//                views: {
//                    agenda: {
//                        eventLimit: 1 // adjust to 6 only for agendaWeek/agendaDay
//                    }
//                },
////                selectable: true,
//                //header and other values
//                select: function (start, end, allDay) {
//                    endtime = $.fullCalendar.formatDate(end, 'h:mm tt');
//                    starttime = $.fullCalendar.formatDate(start, 'ddd, MMM d, h:mm tt');
//                    var sDate = $.fullCalendar.formatDate(start, 'ddd, MMM d yyyy, h:mm');
//                    var appDate = $.fullCalendar.formatDate(start, 'dd-MM-yyyy');
//                    selectedDate = sDate;
//                    var appFromTime = $.fullCalendar.formatDate(start, 'HH:mm');
//                    var appToTime = $.fullCalendar.formatDate(end, 'HH:mm');
//                    holdDate = appDate;
//                    viewAppointments();
//                    $("#patDiv").addClass("hidden");
//                },
//                eventClick: function (event) {
//                },
//                buttonText: {prev: '<i class="fa fa-angle-left"></i>', next: '<i class="fa fa-angle-right"></i>',
//                    prevYear: '<i class="fa fa-angle-double-left"></i>', nextYear: '<i class="fa fa-angle-double-right"></i>',
//                    today: 'Today', month: 'Month', week: 'Week', day: 'Day'}
//            });
            $("#patApptTime").change(function () {
                var patTime = $(this).val();
                $(".spanDiv").each(function (ix, tDiv) {
                    if (moment($("#patSdate").val(), "DD/MM/YYYY").format("DD-MM-YYYY") == $(tDiv).attr("id")) {
                        $(tDiv).find("span").each(function (inx, span) {
                            if ($(span).hasClass("colorSlot")) {
                                $(span).removeClass("colorSlot");
                                $(span).addClass("freeSlot");
                            }
                            if (patTime == $(span).text()) {
                                if ($(span).hasClass("freeSlot")) {
                                    $(span).removeClass("freeSlot")
                                    $(span).addClass("colorSlot");
                                }
                            }
                        });
                    }
                });
            });
            $("#speciality").change(function () {
                var searchParams = {"staffSpecialityIndex": $('#speciality option:selected').val()};
                var options = '';
                $("#patDiv").addClass("hidden");
                dcomethealth.MasterResource.searchStaff(searchParams, function (data) {
                    if (!!data) {
                        $.each(staffObj = data, function (idx, staffData) {
                            options += '<option value="' + staffData.staffResourceRID + '">' + staffData.staffName + '</option>';
//                            $("#docMobileNo").text(staffData.staffContactNumber);
                        });
                        $("#docName").html(options);
                        $("#docMobileNo").text('');
                        $("#docMobileNo1").removeClass("hidden");
                        $.each(staffObj, function (idx, staff) {
                            if ($("#docName").val() == staff.staffResourceRID) {
                                $("#docMobileNo").text(staff.staffContactNumber);
                            }
                        });
                        viewAppointments();
                    } else {
                        $("#docName").empty();
                    }
                });
            });
            $("#docName").on("change", function () {
                $("#patDiv").addClass("hidden");
                $("#docMobileNo").text('');
                $.each(staffObj, function (idx, staff) {
                    if ($("#docName").val() == staff.staffResourceRID) {
                        $("#docMobileNo").text(staff.staffContactNumber);
                    }
                });
            });

        });
    }
    function countCalculation() {
        var booked = 0, waiting = 0, engaged = 0, completed = 0, mDate = holdDate;
        dcomethealth.AppointmentsResource.searchAppointmentsCount({"apptFromDate": holdDate}, function (data) {
            if (!!data) {
                $.each(data, function (idx, appt) {
                    if (appt.apptStatus == 1) {
                        booked++;
                    } else if (appt.apptStatus == 2) {
                        waiting++;
                    } else if (appt.apptStatus == 3) {
                        engaged++;
                    } else if (appt.apptStatus == 4) {
                        completed++;
                    }
                });
                $("#bookedId").text(booked);
                $("#waitingId").text(waiting);
                $("#engagedId").text(engaged);
                $("#completedId").text(completed);
            }
        });
    }
    function clearAutoComplete(e, evnt) {
        if (evnt.keyCode == 8 || evnt.keyCode == 46) {
            $.each(bookedPatientsList, function (index, bookedPatList) {
                var inputBoxId = bookedPatList.dateS + "-PatName";
                if ($(e).attr("id") == inputBoxId) {
                    $("#" + bookedPatList.dateS + "-Menu").empty();
                    var pList = $.grep(bookedPatientsList, function (p) {//for autocomplete
                        var regP = new RegExp($(e).val(), 'gi');
                        if (bookedPatList.dateS == p.dateS) {
                            return (p.patientName).match(regP);
                        }
                    });
                    $.each(pList, function (index, slot) {
                        var li = "", btnclr = "";
                        var editBtnClass = "hidden", cancelBtnClass = "", prevStyle = "", styleAttr = "";
                        prevStyle = "style = 'cursor: pointer'";
                        if (slot.dFlag == 1 || slot.dFlag == 3) {
                            cancelBtnClass = "hidden";
                            prevStyle = "style = 'cursor:not-allowed'";
                        }
                        if (slot.dFlag == 2) {
                            cancelBtnClass = "hidden";
                            editBtnClass = '';
                            if (slot.apptStatus == 1) {
                                styleAttr = "border-bottom: 1px solid #4f8edc;"
                                btnclr = 'btn btn-xs btn-primary';
                                li = '<li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li><li><a id="cancelPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Cancel</a></li>';
                            } else if (slot.apptStatus == 2) {
                                styleAttr = "border-bottom: 1px solid #e73c3c;"
                                btnclr = 'btn btn-xs btn-danger';
                                li = '<li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li>';
                            } else if (slot.apptStatus == 3) {
                                styleAttr = "border-bottom: 1px solid #2bbce0;"
                                btnclr = 'btn btn-xs btn-info';
                                li = '<li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li>';
                            } else if (slot.apptStatus == 4) {
                                styleAttr = "border-bottom: 1px solid #85c744;"
                                editBtnClass = 'hidden';
                            }
                            $("#" + bookedPatList.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div style="cursor: pointer;" onclick="dcomethealth.Appointment.quickRegistration(this,\'' + bookedPatList.dateS + '\',' + slot.apptStatus + ',' + slot.appointmentRid + ', ' + slot.patientRid + ', \'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientAge + ',' + slot.patientVisitRid + ',' + slot.patientGender + ',\'' + slot.patientMobileNo + '\')"><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="' + btnclr + ' dropdown-toggle pull-right" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + bookedPatList.dateS + '">' + li + '</ul></div><a id="cancelBn" class="btn btn-xs btn-inverse pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>');
                        } else {
                            if (slot.dFlag == 3) {
                                cancelBtnClass = "";
                            }
                            $("#" + bookedPatList.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div ><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="btn btn-xs btn-info dropdown-toggle pull-right" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + bookedPatList.dateS + '"><li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li><li><a id="cancelPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Cancel</a></li></ul></div><a id="cancelBn" class="btn btn-xs btn-inverse pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>');
                        }
                    });
                    return false;
                }
            });
        }
    }
    function bookedPatientsAutoComplete(e, evnt) {
        $.each(bookedPatientsList, function (index, bookedPatList) {
            var inputBoxId = bookedPatList.dateS + "-PatName";
            if ($(e).attr("id") == inputBoxId) {
                $("#" + bookedPatList.dateS + "-Menu").empty();
                var pList = $.grep(bookedPatientsList, function (p) {//for autocomplete
                    var regP = new RegExp($(e).val(), 'gi');
                    if (bookedPatList.dateS == p.dateS) {
                        return (p.patientName).match(regP);
                    }
                });
                $.each(pList, function (index, slot) {
                    var li = "", btnclr = "";
                    var editBtnClass = "hidden", cancelBtnClass = "", prevStyle = "", styleAttr = "";
                    prevStyle = "style = 'cursor: pointer'";
                    if (slot.dFlag == 1 || slot.dFlag == 3) {
                        cancelBtnClass = "hidden";
                        prevStyle = "style = 'cursor:not-allowed'";
                    }
                    if (slot.dFlag == 2) {
                        cancelBtnClass = "hidden";
                        editBtnClass = '';
                        if (slot.apptStatus == 1) {
                            styleAttr = "border-bottom: 1px solid #4f8edc;"
                            btnclr = 'btn btn-xs btn-primary';
                            li = '<li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li><li><a id="cancelPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Cancel</a></li>';
                        } else if (slot.apptStatus == 2) {
                            styleAttr = "border-bottom: 1px solid #e73c3c;"
                            btnclr = 'btn btn-xs btn-danger';
                            li = '<li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li>';
                        } else if (slot.apptStatus == 3) {
                            styleAttr = "border-bottom: 1px solid #2bbce0;"
                            btnclr = 'btn btn-xs btn-info';
                            li = '<li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li>';
                        } else if (slot.apptStatus == 4) {
                            styleAttr = "border-bottom: 1px solid #85c744;"
                            editBtnClass = 'hidden';
                        }
                        $("#" + bookedPatList.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div style="cursor: pointer;" onclick="dcomethealth.Appointment.quickRegistration(this,\'' + bookedPatList.dateS + '\',' + slot.apptStatus + ',' + slot.appointmentRid + ', ' + slot.patientRid + ', \'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientAge + ',' + slot.patientVisitRid + ',' + slot.patientGender + ',\'' + slot.patientMobileNo + '\')"><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="' + btnclr + ' dropdown-toggle pull-right" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + bookedPatList.dateS + '">' + li + '</ul></div><a id="cancelBn" class="btn btn-xs btn-inverse pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>');
//                        $("#" + bookedPatList.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div style="cursor: pointer;" onclick="dcomethealth.Appointment.quickRegistration(this,\'' + bookedPatList.dateS + '\',' + slot.apptStatus + ',' + slot.appointmentRid + ', ' + slot.patientRid + ', \'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientAge + ',' + slot.patientVisitRid + ',' + slot.patientGender + ',\'' + slot.patientMobileNo + '\')"><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="' + btnclr + ' dropdown-toggle pull-right ' + cancelBtnClass + '" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + bookedPatList.dateS + '">' + li + '</ul></div><a id="cancelBn" class="btn btn-xs btn-info pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>');
                    } else {
                        if (slot.dFlag == 3) {
                            cancelBtnClass = "";
                        }
                        $("#" + bookedPatList.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="' + btnclr + ' dropdown-toggle pull-right" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + bookedPatList.dateS + '">' + li + '</ul></div><a id="cancelBn" class="btn btn-xs btn-inverse pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>');
                    }
                });
                return false;
            }
        });
    }
    function slideList(id) {
        var iClass = $('#' + id + '-PatName').closest('div').next().find('i').attr('class'); // For finding inputbox nearest 'i' element class
        if (iClass == 'fa fa-chevron-down') {
            $('#' + id + '-PatName').closest('div').next().find('i').attr('class', 'fa fa-chevron-up');
        } else {
            $('#' + id + '-PatName').closest('div').next().find('i').attr('class', 'fa fa-chevron-down');
        }
        if ($('#' + id + '-Menu').is(":visible")) {
            $('#' + id + '-Menu').hide(1000);
            $('#' + id + '-PatName').hide(1000);
//            $('#' + id + '-PatName').removeClass("hidden");
//            $('#' + id + '-Menu').attr("style", "");
//            $('#' + id + '-Menu').slideDown("slow");

        } else {
            $('#' + id + '-Menu').show(1000);
            $('#' + id + '-PatName').show(1000);
//            $('#' + id + '-PatName').addClass("hidden");
            $('#' + id + '-Menu').attr("style", "overflow-y: scroll;height: 100px;");
//            $('#' + id + '-Menu').slideUp("slow");
        }
    }
    function setPatientStatus(elem, apptRid, patRid, patMrn, patTime, patName, patVisitRid, patApptStatus) {
        if (elem.id == "engagePat") {
            if (patApptStatus == 2) {
                $("#apptRid").val(apptRid);
                $("#patRid").val(patRid);
                $("#patientVisitRid").val(patVisitRid);
                engagedVar = true;
                reachedVar = false;
                completedVar = false;
                cancelVar = false;
                saveAppointments();
            } else {
                alert("Patient Not Registered Yet");
            }
        } else if (elem.id == "completPat") {
            if (patApptStatus == 3) {
                $("#apptRid").val(apptRid);
                $("#patRid").val(patRid);
                $("#patientVisitRid").val(patVisitRid);
                completedVar = true;
                engagedVar = false;
                reachedVar = false;
                cancelVar = false;
                saveAppointments();
            } else {
                alert("Patient Not In Consultation");
            }
        } else {
            $("#apptRid").val(apptRid);
            $("#patRid").val(patRid);
            $("#patientVisitRid").val(patVisitRid);
            cancelVar = true;
            completedVar = false;
            engagedVar = false;
            reachedVar = false;
            saveAppointments(); //For cancel Appointment
        }
    }
    function autoIdSet() {
        if (parseInt(patAuto) != 1) {
            $("#patientRID").val("");
            $("#patMrnNo").val("");
        }
    }
    function autoCompletePatient() {
        $("#" + id + " input[name='patMrnNo']").autocomplete({
            select: function (event, ui) {
                patAuto = 1;
                autoIdSet();
                $('#patMrnNo').val(ui.item.data.patMrnNo);
                $('#patientRID').val(ui.item.data.id);
                if (!!$('#patientRID').val() && $('#patientRID').val() != "undefined") {
                    $("#spanDiv").removeClass("hidden");
                    $("#inputDiv,#oldPatBtn").addClass("hidden");
//                    $("#patMrnNo").attr("disabled", true);
//                    $('#docMobileNo').text(ui.item.data.patPhoneNo);
//                    $('#patPhoneNoSpan').text(ui.item.data.patPhoneNo);
//                    $("#patGenderSpan").text(ui.item.data.patGender);
//                    $('#patAgeSpan').text(ui.item.data.patAge);
                    $('#patFullName').val(ui.item.data.patFullName).attr("disabled", true);
                    $('#patPhoneNo').val(ui.item.data.patPhoneNo).attr("disabled", true);
//                    $('#patientMobileNo').text(ui.item.data.patPhoneNo);                    
//                    $('#patientName').val(ui.item.data.patFullName);
//                    $('#patRid').val(ui.item.data.id);
                    $("#patGender").find("option").each(function (indx, option) {
                        if (ui.item.data.patGender == $(option).text()) {
                            $("#patGender").val($(option).val());
                            return false;
                        }
                    });
                    $("#patGender").attr("disabled", true);
                    $('#patAge').val(ui.item.data.patAge).attr("disabled", true);
                    $('#patDOB').val(ui.item.data.patDob);
                }
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
                    position: {my: "center bottom-20", at: "right center",
                        using: function (position, feedback) {
                            $(this).css(position);
                        }
                    }
                });
            }, close: function () {
                $(document).tooltip("destroy");
            },
            source: function (request, response) {
                patAuto = 0;
                var queryString = request.term;
                queryString = queryString.trim();
                var searchParams = {"q": queryString};
                dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
                    if (!!data && data.length > 0) {
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
                    } else {
                        $('#patientRID').val('');
                    }
                });
            },
            minLength: 1
        });
    }
    function viewAppointments() {
        if (!!$("#docName").val() && $("#docName").val() != 0) {
            $("#timeSlots").removeClass("hidden");
            $("#doctorRid").val($("#docName").val());
            $("#slotDiv").empty();
            var searchObj = {"doctorRid": parseInt($("#doctorRid").val()), "selectedDay": moment(holdDate, "DD-MM-YYYY").format('dddd'), "selectedDate": holdDate};//holdDate , "dateInterval": $("#dateInterval option:selected").val()
            dcomethealth.AppointmentsResource.searchAppointmentSlots(searchObj, function (data) {
                if (!!data) {
                    bookedPatientsList = [];
                    $.each(data.appointmentSlotsBydates, function (inx, timeSlot) {
                        var editBtnClass = "hidden", cancelBtnClass = "", prevStyle = "", styleAttr = "";
                        var isCurrentDate = 0;
                        if (timeSlot.dayFlag == 2) {
                            isCurrentDate = 1;
                            editBtnClass = '';
                            cancelBtnClass = "hidden";
                        }
                        if (timeSlot.dayFlag == 1) {
                            cancelBtnClass = "hidden";
                        }
                        prevStyle = "style = 'cursor:pointer'";
                        if (timeSlot.dayFlag == 1 || timeSlot.dayFlag == 3) {
                            prevStyle = "style = 'cursor:not-allowed'";
                        }
                        if (!jQuery.isEmptyObject(timeSlot)) {
                            $("#" + timeSlot.dateS).empty();
                            $("#" + timeSlot.dateS + "-Menu").empty();
                            $("#slotDiv").append('<div style="text-align: center" class="panel col-md-4 col-sm-4"><div id="' + timeSlot.dateS + '-DivText" class="col-md-12 divCaption"  style="margin:0 auto;"><label id="' + timeSlot.dateS + '-Day" style="margin:0 auto;"><b>' + moment(timeSlot.dateS, "DD-MM-YYYY").format("DD/MM/YYYY") + ' (' + timeSlot.dayString + ')' + '</b></label><input type="hidden" id="apptRid" class="form-control"/><input type="hidden" id="dateFlagP" class="form-control"/><input type="hidden" id="patRid" class="form-control"/><input type="hidden" id="previousDayS" class="form-control"/><input type="hidden" id="patientVisitRid" class="form-control"/><p></p></div><div class="panel-body" style="background-color: #fff;color: #000"><div class="spanDiv col-md-12 col-sm-12 col-xs-12" id="' + timeSlot.dateS + '" style=""></div><br/><hr><div class="col-md-12 col-sm-12 col-xs-12"><p></p></div><div class="col-md-11"><input id="' + timeSlot.dateS + '-PatName" type="text" class="form-control  col-md-12 col-sm-12 col-xs-12" onkeyup="dcomethealth.Appointment.clearAutoComplete(this, event)" onkeypress="dcomethealth.Appointment.bookedPatientsAutoComplete(this, event)" placeholder="Patient Name.."/></div><div class="row col-md-1"><a onclick="dcomethealth.Appointment.slideList(\'' + timeSlot.dateS + '\')" style="" id="' + timeSlot.dateS + '-viewBtn" class="btn btn-default btn-xs"><i style="color:#000;" class="fa fa-chevron-down"></i></a></div><ul id="' + timeSlot.dateS + '-Menu" class="menu" style=""></ul></div></div>');//height: 160px;overflow-y: scroll;
                            if (!!timeSlot.appointmentSlotsWithPatients) {
                                $.each(timeSlot.appointmentSlotsWithPatients, function (indx, slot) {
                                    if (!!slot.patientRid || slot.patientRid == 0) {
                                        slot.dFlag = timeSlot.dayFlag;
                                        bookedPatientsList.push(slot); // For autocomplete ul list
                                        var li = "", btnclr = "";
                                        if (timeSlot.dayFlag == 2) {//only for current day
                                            editBtnClass = '';
                                            if (slot.apptStatus == 1) {
                                                styleAttr = "border-bottom: 1px solid #4f8edc;"
                                                btnclr = 'btn btn-xs btn-primary';
                                                li = '<li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li><li><a id="cancelPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Cancel</a></li>';
                                            } else if (slot.apptStatus == 2) {
                                                styleAttr = "border-bottom: 1px solid #e73c3c;"
                                                btnclr = 'btn btn-xs btn-danger';
                                                li = '<li><a id="engagePat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Engaged</a></li><li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li>';
                                            } else if (slot.apptStatus == 3) {
                                                styleAttr = "border-bottom: 1px solid #2bbce0;"
                                                btnclr = 'btn btn-xs btn-info';
                                                li = '<li><a id="completPat" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')">Completed</a></li>';
                                            } else if (slot.apptStatus == 4) {
                                                styleAttr = "border-bottom: 1px solid #85c744;"
                                                editBtnClass = 'hidden';
                                            }
                                        }
                                        var patDetails = '<span class="pull-left" style="color: #fff;">UHID: &nbsp;' + slot.patientMrn + '</span><br/><span class="pull-left" style="color: #fff;"> Name:&nbsp;' + slot.patientName + '</span>';
                                        $("#" + timeSlot.dateS).append("<span id=" + slot.slotTime + " class='bookedSlot col-md-3 col-sm-6 col-xs-3 popovers text-center' style='width: 20%;' Name data-trigger='hover' data-toggle='popover' data-content='" + patDetails + "'  onmouseover='dcomethealth.Appointment.getPatientDetails()'>" + slot.slotTime + "</span><input type='hidden' id='appointmentRid' value='" + slot.appointmentRid + "'/><input type='hidden' id='patRid' value='" + slot.patientRid + "'/>");//for dynamic time slot   data-original-title='<b>Patient Details</b>'
                                        if (timeSlot.dayFlag == 2) {
                                            $("#" + timeSlot.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div style="cursor: pointer;" onclick="dcomethealth.Appointment.quickRegistration(this,\'' + timeSlot.dateS + '\',' + slot.apptStatus + ',' + slot.appointmentRid + ', ' + slot.patientRid + ', \'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientAge + ',' + slot.patientVisitRid + ',' + slot.patientGender + ',\'' + slot.patientMobileNo + '\')"><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="' + btnclr + ' dropdown-toggle pull-right" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + timeSlot.dateS + '">' + li + '</ul></div><a id="cancelBn" class="btn btn-xs btn-inverse pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>'); //for display dynamic ul booked patients list ---by M I Faris
                                        } else {
                                            $("#" + timeSlot.dateS + "-Menu").append('<li class="serviceList" style="' + styleAttr + '"><div><span class="pull-left">UHID:&nbsp;' + slot.patientMrn + '</span><span class="pull-right">TIME:&nbsp;' + slot.slotTime + '</span><br/><span class="pull-left" ' + prevStyle + '">' + slot.patientName + '</span></div>&nbsp;<div class="btn-group pull-right ' + editBtnClass + '"><a id="editBtn" class="' + btnclr + ' dropdown-toggle pull-right" role="button" data-toggle="dropdown" data-trigger="focus" ><i class="fa fa-edit"></i></a><ul class="dropdown-menu" name="' + timeSlot.dateS + '">' + li + '</ul></div><a id="cancelBn" class="btn btn-xs btn-inverse pull-right ' + cancelBtnClass + '" onclick="dcomethealth.Appointment.setPatientStatus(this,' + slot.appointmentRid + ',' + slot.patientRid + ',\'' + slot.patientMrn + '\',\'' + slot.slotTime + '\',\'' + slot.patientName + '\',' + slot.patientVisitRid + ',' + slot.apptStatus + ')" ><i class="fa fa-times"></i></a></li>');
                                        }
                                    } else if (slot.isTimeFreeze == 1) {
                                        $("#" + timeSlot.dateS).append("<span class='freezedSlot  col-md-3 col-sm-6  col-xs-3 text-center' style='width: 20%;'>" + slot.slotTime + "</span><input type='hidden' id='appointmentRid' value='0'/>");
                                    } else {
                                        $("#" + timeSlot.dateS).append("<span id=" + slot.slotTime + " class=' freeSlot col-md-3 col-sm-6 col-xs-3 text-center' style='width: 20%;' onclick='dcomethealth.Appointment.slotBooking(this,\"" + timeSlot.dateS + "\"," + timeSlot.dayFlag + "," + isCurrentDate + ")'>" + slot.slotTime + "</span><input type='hidden' id='appointmentRid' value='0'/>");
                                    }
                                    $("#" + timeSlot.dateS + "-Menu").hide();
                                    $("#" + timeSlot.dateS + "-PatName").hide();
//                                    $("#" + timeSlot.dateS + "-Menu").addClass("hidden");
//                                    $("#" + timeSlot.dateS + "-PatName").addClass("hidden");
                                });
                            } else {
                                if (timeSlot.dayIsHoliday == 1) {
                                    $("#" + timeSlot.dateS).attr("style", ""); //For display no slot available ,if it is empty time slot  
                                    $("#" + timeSlot.dateS).append("<span id='noSlotSpan' class=''><b>" + timeSlot.holidayName + "</b></span>");
                                } else {
                                    $("#" + timeSlot.dateS).attr("style", ""); //For display no slot available ,if it is empty time slot  
                                    $("#" + timeSlot.dateS).append("<span id='noSlotSpan' class=''><b>No Slots Available</b></span>");
                                }
                            }
                            if ($("#" + timeSlot.dateS + "-Menu li").length == 0) { //For hide autoComplete input box & ul list,if it is empty
                                $("#" + timeSlot.dateS + "-Menu").hide();
                                $("#" + timeSlot.dateS + "-PatName").hide();
                                $("#" + timeSlot.dateS + "-viewBtn").hide();
//                                $("#" + timeSlot.dateS + "-PatName").addClass("hidden");
//                                $("#" + timeSlot.dateS + "-Menu").addClass("hidden");
                            }
//                            if (holdDate == timeSlot.dateS) { //For control go to selected date
//                                $('html, body').animate({
//                                    scrollTop: $("#" + timeSlot.dateS).offset().top
//                                });
//                            }
                        }
                    });
                    $(".divCaption").each(function (ix, captionDiv) {
                        var divDate = $(captionDiv).attr("id").toString().substring(0, 10);
                        if (holdDate == divDate) {//holdDate == divDate
                            $("#" + holdDate + "-DivText").attr("style", "border-top: 2px solid #3f444c;font-weight:bold;color: #000"); // $("#" + holdDate + "-DivText")
                        } else {
                            $("#" + divDate + "-DivText").attr("style", "font-weight:bold;color: #000");
                        }
                    });
                } else {
                    alert("No Slots Available");
                    return false;
                }
            });
        } else {
            if ($("#speciality").val() == 0) {
                alert("Select Speciality");
            } else {
                alert("Doctor not Available for this Speciality");
            }
            $("#timeSlots").addClass("hidden");
        }
    }
    function resetVariables() {
        $("#apptRid").val("");
        $("#patientVisitRid").val("");
        $("#patDOB").val("");
        reachedVar = false;
        completedVar = false;
        engagedVar = false;
        cancelVar = false;
        $('#dateCheckFlag').val("");
    }
    function resetSlotColor() {
        $(".spanDiv").each(function (ix, tDiv) {
            $(tDiv).find("span").each(function (inx, span) {
                if ($(span).hasClass("colorSlot")) {
                    $(span).removeClass("colorSlot")
                    $(span).addClass("freeSlot");
                }
            });
        });
    }
    function setSlotsReset() {
        $("#patApptTime").empty();
        $("#patApptTime").append("<option></option>");
        $(".spanDiv").each(function (ix, tDiv) {
            if (moment($("#patSdate").val(), "DD/MM/YYYY").format("DD-MM-YYYY") == $(tDiv).attr("id")) {
                $(tDiv).find("span").each(function (inx, span) {
                    if ($(span).hasClass("freeSlot") || $(span).hasClass("colorSlot")) {
                        $("#patApptTime").append('<option value="' + $(span).text() + '">' + $(span).text() + '</option>');
                    }
                });
            }
        });
    }
    function getPatientDetails() {
        $('.bookedSlot').popover({
            html: true,
            trigger: 'click hover',
            placement: 'auto left',
        });
    }
    function resetValues() {
        var dateSS = $("#patSdate").val();
        $("#patForm").trigger("reset");
        $("#patientRID").val(""); //reset form with id ;
        $("#patSdate").val(dateSS);
        $('#patFullName').attr("disabled", false);
        $('#patPhoneNo').attr("disabled", false);
        $('#patMrnNo').attr("disabled", false);
        $('#patAge').attr("disabled", false);
        $("#patGender").attr("disabled", false);
        $('#dateCheckFlag').val("");
    }
    function slotBooking(elem, day, dateFlag, isCurrentDate) {
        resetValues(elem);
        var slotParts = [];
        var slotTime = null;
        slotParts = ($(elem).text().split(":"));
        slotTime = slotParts[0] + "." + slotParts[1];
        var today = moment().format("HH:mm");
        var timeParts = [];
        timeParts = today.split(":");
        var time = timeParts[0] + "." + timeParts[1];
        resetVariables();
        if (isCurrentDate == 1 && parseFloat(slotTime) < parseFloat(time)) {
            alert("previousTime");
            $("#patDiv").addClass("hidden");
            resetValues();
            resetVariables();
            viewAppointments();
            return false;
        }
        $("#registerPatient").addClass("hidden");
        $("#confirmPatient").removeClass("hidden");
        $("#patApptTime").empty();
        $("#patApptTime").append('<option></option>');
        $(".divCaption").each(function (ix, captionDiv) {
            var divDate = $(captionDiv).attr("id").toString().substring(0, 10);
            if (day == divDate) {
                $("#" + day + "-DivText").attr("style", "border-top: 2px solid #3f444c;font-weight:bold;color: #000");
            } else {
                $("#" + divDate + "-DivText").attr("style", "font-weight:bold;color: #000");
            }
        });
        $(".spanDiv").each(function (ix, tDiv) {
            if (day == $(tDiv).attr("id")) {
                $(tDiv).find("span").each(function (inx, span) {
                    if ($(span).hasClass("freeSlot")) {
                        $("#patAppDate").val(day);
                        $("#patApptTime").append('<option value="' + $(span).text() + '">' + $(span).text() + '</option>');
                    } else {
                        if ($(span).hasClass("colorSlot")) {
                            $(span).removeClass("colorSlot")
                            $(span).addClass("freeSlot");
                            $("#patApptTime").append('<option value="' + $(span).text() + '">' + $(span).text() + '</option>');
                        }
                    }
                });
            } else {
                $(tDiv).find("span").each(function (inx, span) {
                    if ($(span).hasClass("colorSlot")) {
                        $(span).removeClass("colorSlot")
                        $(span).addClass("freeSlot");
                    }
                });
            }
        });
        if ($(elem).hasClass("freeSlot")) {
            $(elem).removeClass("freeSlot")
            $(elem).addClass("colorSlot");
            $("#patDiv").removeClass("hidden");
            $("#patMrnNo").focus();
            $("#patApptTime").val($(elem).text());
            $("#patAppDate").val(day);
            $("#patSdate").val(moment(day, "DD-MM-YYYY").format("DD/MM/YYYY"));
            $("#dateCheckFlag").val(dateFlag);
        } else {
            $("#patDiv").addClass("hidden");
        }
    }
    function calculateDOB() {
        var year = new Date().getFullYear();
        var month = new Date().getMonth();
        var day = new Date().getDate();
        if ($('#patDOB').val() == "") {
            if (!isNaN($('#patAge').val())) {
                year = year - $('#patAge').val();
                $('#patDOB').val(day + "-" + (month + 1) + "-" + year);
            }
        }
    }
    function quickRegistration(elem, dateStatus, apptStatus, apptRid, patRid, patMrn, patTime, patName, patAge, patVisitRid, patGender, patMobileNo) {
        $("#patApptTime").empty();
        if (apptStatus < 2) {
            $("#patDiv").removeClass("hidden");
            $("#patMrnNo").val(patMrn).attr("disabled", true);
            $("#patFullName").val(patName).attr("disabled", true);
            $("#apptRid").val(apptRid);
            $("#patRid").val(patRid);
            $("#patAge").val(patAge).attr("disabled", true);
            $("#patPhoneNo").val(patMobileNo).attr("disabled", true);
            $("#patGender").val(patGender).attr("disabled", true);
            $("#patSdate").val(moment(dateStatus, "DD-MM-YYYY").format("DD/MM/YYYY"));
            $("#patApptTime").append('<option value="' + patTime + '">' + patTime + '</option>');
            $("#confirmPatient").addClass("hidden");
            $("#registerPatient").removeClass("hidden");
            calculateDOB();
//                $("#dateCheckFlag").val(dateFlag);
        } else if (apptStatus == 4) {
            alert("Consultation Completed");
        } else {
            alert("Already Registered");
            $("#patDiv").addClass("hidden");
            resetAppointments();
            resetVariables();
        }
    }
    function saveRegistration() {
        var visit = {}, check = false, activePatient = false;
        //        visit.visTypeIndex = $("#visitType").val();
        visit.visPatRid = $("#patRid").val();
        visit.visSubTypeIndex = 0; // Need to change the value;
        visit.visPatType = 0; // Need to check and change it.
        visit.visApptRid = $("#apptRid").val(); // passing 0 because its a direct OP registration
        visit.visEpisodeRid = 0; // Passing 0 as of now, need to enable visit episode
        //        visit.visReasonIndex = $("#visitReason").val();
        visit.visSpecialityIndex = $("#speciality option:selected").val();
        visit.visConsDocRid = $("#docName option:selected").val();
        visit.visAttnDocRid = 0; // We have only consulting doc RID now, need to update it later if the attending doctor is different.
        var visitPatDetails = [];
        var patient = {};
        if ($("#patRid").val() !== "" && $("#patRid").val() != 0) {
            patient.id = $("#patRid").val();
            dcomethealth.PatientResource.searchVisit({"visPatRid": $("#patRid").val()}, function (data) {
                if (!!data && data.length > 0) {
                    $.each(data, function (i, visit) {
                        if (moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') === moment().format('DD-MM-YYYY')) {
                            alert("Patient is Active Today");
                            activePatient = true;
                            return false;
                        }
                    });
                }
                patient.patFullName = $("#patFullName").val();
                patient.patName = $("#patFullName").val();
                patient.patDob = $('#patDOB').val();
                patient.patGenderIndex = $("#patGender").val();
                patient.patPhoneNo = $("#patPhoneNo").val();
                visitPatDetails.push(patient);
                visit.patient = visitPatDetails;
                if (activePatient == false) {
                    var boRID = 0, boCode = "VISIT", actionCode = "SUBMIT";
                    var search = boRID + "/" + boCode + "/" + actionCode;
                    dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                        alert("Patient Registered");
                        $("#patDiv").addClass("hidden");
                        resetAppointments();
                        resetVariables();
                        countCalculation();
                        viewAppointments();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                        alert("Failed");
                    });
                }
            });
        } else {
            patient.patFullName = $("#patFullName").val();
            patient.patName = $("#patFullName").val();
            patient.patDob = $('#patDOB').val();
            patient.patGenderIndex = $("#patGender").val();
            patient.patPhoneNo = $("#patPhoneNo").val();
            visitPatDetails.push(patient);
            visit.patient = visitPatDetails;
            var boRID = 0, boCode = "VISIT", actionCode = "SUBMIT";
            var search = boRID + "/" + boCode + "/" + actionCode;
            dcomethealth.PatientResource.save(visit, search).done(function (data, textStatus, jqXHR) {
                alert("Patient Registered");
                $("#patDiv").addClass("hidden");
                resetAppointments();
                resetVariables();
                countCalculation();
                viewAppointments();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function saveAppointments() {
        var appointmentsList = [];
        var appointmentResourceMapList = [];
        var appointments = {};
        if ($("#apptRid").val() == "" || $("#apptRid").val() == 'undefined') {
            if ($('#patFullName').prop('disabled')) {
                if (!$("#patientRID").val()) {
                    alert("Select UHID");
                    return false;
                }
            }
//            if (!!$("#patMrnNo").val()) {
//                if (!$("#patientRID").val()) {
//                    alert("Select UHID");
//                    return false;
//                }
//            }
            if (!!$("#patientRID").val()) {
                if (!$("#patMrnNo").val()) {
                    alert("Select UHID");
                    return false;
                }
            }
            if ($("#patFullName").val() == "") {
                alert("Enter Patient Name");
                return false;
            }
            if (!$("#patGender").val()) {
                alert("Select Gender");
                return false;
            }
            if (!$("#patAge").val()) {
                alert("Enter Age");
                return false;
            }
            if (!$("#patPhoneNo").val()) {
                alert("Enter Mobile number");
                return false;
            }
            var fromDate = $("#patAppDate").val();
            var fromTime = $("#patApptTime").val();
            if (!!fromTime) {
                appointments.apptFromDate = $("#patAppDate").val();
                appointments.apptFromTime = moment(fromTime, 'HH:mm').format('HH:mm:ss');
            } else {
                alert("Select Slot");
                return false;
            }
            var arm = {};
            arm.armResourceRid = $("#doctorRid").val();
            appointmentResourceMapList.push(arm);
            appointments.apptUnitRID = dcomethealth.selectedunit;
            appointments.apptPatientRID = !!$("#patientRID").val() ? $("#patientRID").val() : 0;
            appointments.apptPatientName = !!$("#patFullName").val() ? $("#patFullName").val() : "";
            appointments.apptPatientPhone = !!$("#patPhoneNo").val() ? $("#patPhoneNo").val() : "";
            appointments.apptPatientMrn = !!$("#patMrnNo").val() ? $("#patMrnNo").val() : "";
            appointments.apptPatientAge = $("#patAge").val();
            appointments.apptPatientGenderIndex = $("#patGender").val();
            appointments.apptFromDateTime = moment(fromDate + " " + fromTime, 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY HH:mm:ss');
            appointments.apptDocRID = $("#doctorRid").val();
            appointments.apptAttendingUserRID = $("#doctorRid").val();
            if (parseInt($("#patientRID").val()) != 0) {
                appointments.apptStatus = 1; //For New Appointments                
            } else {
                appointments.apptStatus = 0;
            }
            appointments.appointmentResourceMap = appointmentResourceMapList;
            appointmentsList.push(appointments);
            dcomethealth.AppointmentsResource.searchAppointmentsByMobileNo({"apptFromDate": $("#patAppDate").val(), "apptPatientRID": appointments.apptPatientRID, "apptPatientPhone": $("#patPhoneNo").val()}, function (data) {
                var check = 0;
                if (!!data) {
                    $.each(data, function (inx, appt) {
                        if (appt.apptFromTime == appointments.apptFromTime && appt.apptStatus != -1) {
                            alert("Already This Time Is Booked By This Patient");
                            check = 1;
                        }
                    });
                    if (check == 0) {
                            dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                                    .done(function (data, textStatus, jqXHR) {
                                        $("#patDiv").addClass("hidden");
                                        if (reachedVar == false) {
                                            alert("Appointment Booked");
                                            countCalculation();
                                            viewAppointments();
                                        }
                                    }).fail(function (jqXHR, textStatus, errorThrown) {
                                alert("Failed");
                            });
                        }
                } else {
                    dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                            .done(function (data, textStatus, jqXHR) {
                                $("#patDiv").addClass("hidden");
                                if (reachedVar == false) {
                                    alert("Appointment Booked");
                                    countCalculation();
                                    viewAppointments();
                                }
                            }).fail(function (jqXHR, textStatus, errorThrown) {
                        alert("Failed");
                    });
                }
            });
        } else {
            appointments.id = $("#apptRid").val();
            appointments.apptPatientRID = $("#patRid").val();
            if (cancelVar) {
                if (!$("#patientVisitRid").val()) {
                    appointments.apptStatus = -1;
                    if (confirm("Do you want to cancel?")) {
                        appointmentsList.push(appointments);
                        dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                                .done(function (data, textStatus, jqXHR) {
                                    $("#patDiv").addClass("hidden");
                                    if (reachedVar == false) {
                                        alert("Appointment Cancelled");
                                        countCalculation();
                                        viewAppointments();
                                    }
                                }).fail(function (jqXHR, textStatus, errorThrown) {
                            alert("Failed");
                        });
                    } else {
                        return false;
                    }
                } else {
                    alert("Already Registered");
                }
            } else {
                if (reachedVar) {
                    appointments.apptStatus = 2;
                } else if (engagedVar) {
                    appointments.apptStatus = 3;
                } else if (completedVar) {
                    appointments.apptStatus = 4;
                }
                appointmentsList.push(appointments);
                dcomethealth.AppointmentsResource.saveAppointments(appointmentsList)
                        .done(function (data, textStatus, jqXHR) {
                            $("#patDiv").addClass("hidden");
                            if (reachedVar == false) {
                                if (engagedVar == true) {
                                    alert("Patient Consultation Started");
                                }
                                if (completedVar == true) {
                                    alert("Consultation Completed");
                                }
                                countCalculation();
                                viewAppointments();
                            }
                        }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
            }
        }
    }
    function refreshData() {
    }
    return {
        init: init,
        autoCompletePatient: autoCompletePatient,
        setPatientStatus: setPatientStatus,
        setSlotsReset: setSlotsReset,
        slideList: slideList,
        calculateDOB: calculateDOB,
        resetSlotColor: resetSlotColor,
        getPatientDetails: getPatientDetails,
        quickRegistration: quickRegistration,
        clearAutoComplete: clearAutoComplete,
        countCalculation: countCalculation,
        bookedPatientsAutoComplete: bookedPatientsAutoComplete,
        viewAppointments: viewAppointments,
        slotBooking: slotBooking,
        autoIdSet: autoIdSet,
        saveAppointments: saveAppointments,
        saveRegistration: saveRegistration,
        refreshData: refreshData
    };
}());
dcomethealth.Appointment.init();
