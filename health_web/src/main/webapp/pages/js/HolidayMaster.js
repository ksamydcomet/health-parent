var dcomethealth = dcomethealth || {};
dcomethealth.HolidayMaster = (function () {
    var id = "HolidayMaster";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('body').on('focus', "#hmWorkingFromTime", function () {
                $(this).datetimepicker({
                    datepicker: false,
                    format: 'H:i',
                    step: 5,
                });
            });
            $('body').on('focus', "#hmWorkingToTime", function () {
                $(this).datetimepicker({
                    datepicker: false,
                    format: 'H:i',
                    step: 5
                });
            });
            fetchHolidayMaster();
            $("#submitData").on("click", function () {
                submit();
            });
        });
    }

    function fetchHolidayMaster() {
        dcomethealth.MasterResource.searchHolidayMaster({}, function (data) {
            if (!!data && data.length > 0) {
                $("#holidayTbody").empty();
                var dayType = "", isActive = "";
                $.each(data, function (index, holiday) {
                    $.each(dcomethealth.dDictVal, function (index, val) {
                        if (parseInt(holiday.hmValue) == val.id) {
                            dayType = val.ddictValue;
                        }
                    });
                    if (holiday.hmIsActive == 1) {
                        isActive = "checked";
                    } else {
                        isActive = "";
                    }
                    $("#holidayTbody").append('<tr>\n\
                    <td width="10%"><input id="hmHolidayName" name="hmHolidayName" class="form-control col-md-11 col-sm-11 col-xs-11" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" value="' + holiday.hmHolidayName + '"/> <input id="hmRid" name="hmRid" type="hidden">\n\
                    <td width="4%"><input id="hmHolidayDate" name="hmHolidayDate" value="' + holiday.hmHolidayDate + '" maxlength="10" class="form-control col-md-12 col-sm-12 col-xs-12 jqueryUIToolTip" onkeypress="return dcomethealth.validation.ValidateDateEntry(event)" onblur="dateValidation(this)" title="DD-MM-YYYY">\n\
                    <td width="6%"><select class="form-control dcomet-c-s_ddict_day_type-list" id="hmValue" onchange="dcomethealth.HolidayMaster.checkDayType(this)"><option value="' + holiday.hmValue + '>' + dayType + '</option></select>\n\
                    <td width="3%"><input id="hmWorkingFromTime" name="hmWorkingFromTime" maxlength="5" class="form-control col-md-12 col-sm-12 col-xs-12" onkeypress="return dcomethealth.validation.isNumberKey(event,this)" onchange="timeValidation(this)"/>\n\
                    <td width="3%"><input id="hmWorkingToTime" name="hmWorkingToTime" maxlength="5" class="form-control col-md-12 col-sm-12 col-xs-12" onkeypress="return dcomethealth.validation.isNumberKey(event,this)" onchange="timeValidation(this)">\n\
                    <td width="2%"><input id="hmIsActive" name="hmIsActive" class="col-md-12" type="checkbox" ' + isActive + '/><td width="2%"><i class="ace-icon fa fa-plus red" onclick="insertRow(\'dynTableHoliday\',this)"></i></td></tr>');
                });
            } else {
                alert("else");
                $("#holidayTbody").append('<tr><td width="1%"><i id="minus" class="ace-icon fa fa-minus hidden" onclick="deleteRow(this)"></i></td>\n\
                <td width="10%"><input type="text" class="col-md-11 col-sm-11 col-xs-11 form-control" id="hmHolidayName"  name="hmHolidayName" onkeypress="return dcomethealth.validation.ValidateAlpha(event)" /><input type="hidden" id="hmRid" name="hmRid"/></td>\n\
                <td width="4%"><input type="text" id="hmHolidayDate"  name="hmHolidayDate" maxlength="10" title="DD-MM-YYYY Format" onkeypress="return dcomethealth.validation.ValidateDateEntry(event)" onblur="dateValidation(this)" class="col-md-12 col-sm-12 col-xs-12 form-control jqueryUIToolTip"></td>\n\
                <td width="6%"><select id="hmValue" class="form-control dcomet-c-s_ddict_day_type-list" onchange="dcomethealth.HolidayMaster.checkDayType(this)"><option value="">--Select--</option></select></td>\n\
                <td width="3%"><input type="text" class="col-md-12 col-sm-12 col-xs-12 form-control" maxlength="5" id="hmWorkingFromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" name="hmWorkingFromTime" onchange="" disabled/></td>\n\
                <td width="3%"><input type="text" class="col-md-12 col-sm-12 col-xs-12 form-control" maxlength="5" id="hmWorkingToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" name="hmWorkingToTime" onchange="timeValidation(this)" disabled/></td> \n\
                <td width="2%"><input type="checkbox" id="hmIsActive" name="hmIsActive" class="col-md-12" checked/></td>\n\
                <td width="2%"><i class="ace-icon fa fa-plus red" onclick="insertRow(\'dynTableHoliday\', this)"></i></td></tr>');
            }
        });
    }
    function checkDayType(elem) {
        if ($(elem).find(":selected").text() == "Half Day") {
            dynTableGetNodeInRow(elem, 'hmWorkingFromTime').className = "form-control col-md-12 col-sm-12 col-xs-12";
            dynTableGetNodeInRow(elem, 'hmWorkingFromTime').disabled = false;
            dynTableGetNodeInRow(elem, 'hmWorkingToTime').className = "form-control col-md-12 col-sm-12 col-xs-12";
            dynTableGetNodeInRow(elem, 'hmWorkingToTime').disabled = false;
        } else {
            dynTableGetNodeInRow(elem, 'hmWorkingFromTime').className = "form-control col-md-12 col-sm-12 col-xs-12";
            dynTableGetNodeInRow(elem, 'hmWorkingFromTime').disabled = true;
            dynTableGetNodeInRow(elem, 'hmWorkingFromTime').value = "";
            dynTableGetNodeInRow(elem, 'hmWorkingToTime').className = "form-control col-md-12 col-sm-12 col-xs-12";
            dynTableGetNodeInRow(elem, 'hmWorkingToTime').disabled = true;
            dynTableGetNodeInRow(elem, 'hmWorkingToTime').value = "";
        }
    }
    function submit() {
        alert("1");
        var holidayMasterList = [];
        var table = document.getElementById('dynTableHoliday');
        for (var i = 0; i < table.rows.length - 1; i++) {
            alert("2");
            var holidayMaster = {};
            if (!!dynTableGetNodeInRow(table.rows[i + 1], "hmRid").value || dynTableGetNodeInRow(table.rows[i + 1], "hmRid").value != "") {
                holidayMaster.hmRid = dynTableGetNodeInRow(table.rows[i + 1], "hmRid").value;
            }
            holidayMaster.hmHolidayName = dynTableGetNodeInRow(table.rows[i + 1], "hmHolidayName").value;
            holidayMaster.hmHolidayDate = dynTableGetNodeInRow(table.rows[i + 1], "hmHolidayDate").value;
            holidayMaster.hmValue = dynTableGetNodeInRow(table.rows[i + 1], "hmValue").value;
            if (dynTableGetNodeInRow(table.rows[i + 1], "hmWorkingFromTime").value != "" && dynTableGetNodeInRow(table.rows[i + 1], "hmWorkingToTime").value != "") {
                holidayMaster.hmWorkingFromTime = dynTableGetNodeInRow(table.rows[i + 1], "hmWorkingFromTime").value;
                holidayMaster.hmWorkingToTime = dynTableGetNodeInRow(table.rows[i + 1], "hmWorkingToTime").value;
            }
            if (dynTableGetNodeInRow(table.rows[i + 1], "hmIsActive").checked) {
                holidayMaster.hmIsActive = 1;
            } else {
                holidayMaster.hmIsActive = 0;
            }
            holidayMasterList.push(holidayMaster);
        }
        alert("3");
        console.log(holidayMasterList);
        dcomethealth.MasterResource.saveHolidayMaster(holidayMasterList).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('HolidayMaster');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function refreshData() {
    }
    return {init: init,
        submit: submit,
        checkDayType: checkDayType,
        refreshData: refreshData
    };
}());
dcomethealth.HolidayMaster.init();