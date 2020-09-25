var dcomethealth = dcomethealth || {};
dcomethealth.ServicePoints = (function () {
    var id = "ServicePoints", servicePoint = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            fetchServicePoints();

            $("#submitData").on("click", function () {
                $("#edit_user_form").validate({
                    rules: {
                        wrdType: "required",
                        wrdUnitRid: "required",
                        wrdName: "required"
                    },
                    messages: {
                        wrdType: "Select Service Point Group",
                        wrdUnitRid: "Select Unit",
                        wrdName: "Enter Service Point Name"
                    },
                    submitHandler: function (form) {
                        submit();
                    }
                });
            });
        });
    }
    function fetchServicePoints() {
        dcomethealth.MasterResource.getServicePoints({"wrdEntityRid": dcomethealth.loginuser.entityRid}, function (data) {
            if (!!data) {
                $("#tbodyHead").empty();
                var Group = null;
                $.each(servicePoint = data, function (idx, sPoint) {
                    $.each(dcomethealth.dDictVal, function (ddex, val) {
                        if (parseInt(sPoint.wrdGroupIndex) == val.id) {
                            Group = val.ddictValue;
                        }
                    });
                    var act = "No";
                    if (sPoint.wrdIsScheduleRequired === 1) {
                        act = "Yes";
                    }
                    $("#tbodyHead").append('<tr onclick="dcomethealth.ServicePoints.showServicePoint(' + sPoint.wrdRid + ')" id="tr_head">\n\
                        <td>' + sPoint.wrdName + '<input type="hidden" value="' + sPoint.wrdRid + '"/></td><td>' + Group + '</td></tr>');
                });
                $("#sPointTable").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function showServicePoint(val) {
        $.each(servicePoint, function (index, sPoint) {
            if (sPoint.wrdRid == parseInt(val)) {
                $("#ServicePointsHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#addNew").removeClass("hidden").addClass("panel panel-primary");
                populateFields(sPoint);
            }
        });
    }
    function populateFields(data) {
        $("#wrdRid").val(data.wrdRid);
        $("#wrdType").val(data.wrdType);
        $("#wrdGroupIndex").val(data.wrdGroupIndex);
        $("#wrdSubGroupIndex").val(data.wrdSubGroupIndex);
        $("#wrdName").val(data.wrdName);
        $("#wrdUnitRid").val(data.wrdUnitRid);
//        alert(data.wrdIsScheduleRequired);
        var isSchedule = parseInt(data.wrdIsScheduleRequired);
        if (isSchedule == 1) {
            $('#collect').val(data.wrdIsScheduleRequired);
            $('#openPmd').prop("checked", true);
            daywiseStatus();
        } else {
            $('#collect').val(data.wrdIsScheduleRequired);
            $('#openPmd').prop("checked", false);
            daywiseStatus();
        }
        dcomethealth.ServicePoints.searchResource(data.wrdResourceRid);
    }
    function searchResource(val) {
        $('#tbody_days').empty();
        dcomethealth.MasterResource.searchResource({id: val}, function (data) {
            if (!!data) {
                $.each(data, function (index, s_Res) {
                    var days = ""
                    $("#resRid").val(s_Res.id);
                    $.each(s_Res.resourceWorkingHours, function (j, resWhAvail) {
                        flag = resWhAvail.whFlag;
                        if (parseInt(resWhAvail.whFlag) === 1) {
                            $('#collect').val(1);
                            $('#openPmd').prop('checked', true);
                            $('#datew').prop('disabled', true);
                            $("#daywise").removeClass("hidden").addClass("panel panel-primary");
                            $("#datewise").removeClass("panel panel-primary").addClass("hidden");
                            $.each(dcomethealth.dDictVal, function (ifx, val) {
                                if (resWhAvail.whDayOfWeek == val.id) {
                                    days = val.ddictValue
                                }
                            });
                            $('#tbody_days').append('<tr><td width="1%"><i id="del_saleR" class="dct-icon fa fa-minus-circle hidden" onclick="delete_row(this, \'dynTableSchedule\')"></i><input type="hidden" id="resAvilRid" name="resAvilRid" value=""/><input type="hidden" id="resAvilWhRid" name="resAvilWhRid" value="' + resWhAvail.id + '"/></td>\n\
<td width="15%"><Select class="col-md-11 col-sm-12 col-xs-12 dcomet-c-s_ddict_days-list form-control"  id="resAvilDays" name="resAvilDays"><option value="' + resWhAvail.whDayOfWeek + '">' + days + '</option></select></td>\n\
<td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="fromTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + resWhAvail.whFromTime + '" name="fromTime"/></td>\n\
<td width="10%"><input type="time" class="col-md-12 col-sm-12 col-xs-12 form-control" id="ToTime" onkeypress="return dcomethealth.validation.isNumberKey(event, this)" value="' + resWhAvail.whToTime + '" name="ToTime"/></td>\n\
                    <td width="1%"><i class="dct-icon fa fa-plus-square red" onclick="insert_row(\'dynTableSchedule\', this)"></i></td></tr>');
                        }
                    });
                });
            }
        });
    }
    function submit() {
        var form = $("form");
        if (validateForm(form)) {
            var servicePoint = {}, servicePointList = [];
            var resWhList = [], resource = {};
            if (!!$("#wrdRid").val()) {
                servicePoint.wrdRid = $("#wrdRid").val();
            }
            servicePoint.wrdType = $("#wrdType").val();
            servicePoint.wrdGroupIndex = $("#wrdGroupIndex").val();
            servicePoint.wrdSubGroupIndex = $("#wrdSubGroupIndex").val();
            servicePoint.wrdName = $("#wrdName").val();
            servicePoint.wrdUnitRid = $("#wrdUnitRid").val();
            servicePoint.wrdEntityRid = dcomethealth.loginuser.entityRid;
            if ($('#openPmd').is(':checked')) {
                servicePoint.wrdIsScheduleRequired = 1;
            } else {
                servicePoint.wrdIsScheduleRequired = 0;
            }

//            servicePoint.wrdResourceRid = $("#wrdResourceRid").val();
            servicePoint.wrdNumber = $('#collect').val();
            if (parseInt($('#collect').val()) === 1) {

                if ($("#resRid").val() != "") {
                    resource.id = $("#resRid").val();
                }
                resource.resName = $("#wrdName").val();
                resource.resType = 1;
                resource.resEntRID = dcomethealth.loginuser.entityRid;
                resource.resSchedInterval = 0;
                resource.resUnitRID = dcomethealth.selectedunit;
                resource.resValid = 1;
                resource.portResID = 0;
                resource.resSequenceNumber = 0;
                resource.resCategory = $("#wrdGroupIndex").val();
                resource.resSubcategory = 0;
                resource.resSchedDuration = 20;

                var table = document.getElementById('dynTableSchedule');
                var table_length = table.rows.length;
                for (var i = 0; i < table_length - 1; i++) {
                    var resWh = {};
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value != "") {
                        resWh.id = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value;
                    }
                    resWh.whEntityRID = dcomethealth.loginuser.entityRid;
                    if (parseInt($('#collect').val()) === 1) {
                        resWh.whDayOfWeek = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value;
                        resWh.whFlag = 1;
                    }
                    resWh.whFromTime = moment(dynTableGetNodeInRow(table.rows[i + 1], 'fromTime').value, 'HH:mm').format('HH:mm:ss');
                    resWh.whToTime = moment(dynTableGetNodeInRow(table.rows[i + 1], 'ToTime').value, 'HH:mm').format('HH:mm:ss');
                    resWh.whUnitRID = $("#wrdUnitRid").val();
                    resWhList.push(resWh);
                }
            }
            resource.resourceWorkingHours = resWhList;
            servicePointList.push(servicePoint);
            resource.wardMasters = servicePointList;
            dcomethealth.MasterResource.saveResource(resource).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('ServicePoints');
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        submit: submit,
        showServicePoint: showServicePoint,
        searchResource: searchResource,
        refreshData: refreshData
    };
}());
dcomethealth.ServicePoints.init();