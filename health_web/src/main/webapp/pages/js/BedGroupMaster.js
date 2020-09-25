var dcomethealth = dcomethealth || {};
dcomethealth.BedGroupMaster = (function () {
    var id = "BedGroupMaster", bedGroupData = {}, servicePoint = {}, unitObj = {}, delBedsList = [];
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            dcomethealth.MasterResource.getServicePoints({"wrdEntityRid": dcomethealth.loginuser.entityRid}, function (data) {
                if (!!data) {
                    servicePoint = data;
                }
            });
            dcomethealth.MasterResource.searchUnit({"unitHasBeds": 1}, function (data) {
                $.each(unitObj = data, function (pIdx, unit) {
                    $('#bgmBedUnitRid').append('<option value="' + unit.id + '">' + unit.unitName + '</option>');
                });
                fetchBedMaster();
            });
            $("#bgmBedGroupIndex").on("change", function () {
                var groupVal = $(this).val();
                $('#bgmBedSubGroupIndex').empty();
                $('#bgmBedSubGroupIndex').append("<option value=''></option>");
                if (!!groupVal && groupVal != 0) {
                    $.each(dcomethealth.dDictVal, function (index, val) {
                        if (val.ddictParentIndex == parseInt(groupVal)) {
                            $('#bgmBedSubGroupIndex').append("<option value='" + val.id + "'>" + val.ddictValue + "</option>");
                        }
                    });
                }
            });
            $("#submitData").on("click", function () {
                $("#edit_user_form").validate({
                    rules: {
                        bgmBedGroupName: "required",
                        bgmBedUnitRid: {
                            selectcheck: true
                        },
                        bgmBedPriceTypeIndex: {
                            selectchek: true
                        },
                    },
                    messages: {
                        bgmBedGroupName: "Enter Bed Group Name",
                    },
                    submitHandler: function (form) {
                        var bedCheck = false;
                        var table = document.getElementById('dynTableBed');
                        var table_length = table.rows.length;
                        for (var i = 1; i < table_length - 1; i++) {
                            if (dynTableGetNodeInRow(table.rows[i + 1], 'bedName').value == "") {
                                alert("Enter Bed Name");
                                bedCheck = true;
                            }
                        }
                        if (!bedCheck) {
                            submit();
                        }
                    }
                });
            });
            jQuery.validator.addMethod('selectcheck', function (value) {
                return (value != '0');
            }, "Select Unit");
            jQuery.validator.addMethod('selectchek', function (value) {
                return (value != '0');
            }, "Select Price Type");
        });
    }
    function deleteRowBed(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('dynTableBed');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (table_length > 2) {
                if (dynTableGetNodeInRow(elem, 'bedState').value == 0) {
                    if (!!dynTableGetNodeInRow(elem, 'bedRid').value && dynTableGetNodeInRow(elem, 'bedRid').value != "" && dynTableGetNodeInRow(elem, 'bedRid').value != "undefined") {
                        var delBed = {};
                        delBed.bedName = dynTableGetNodeInRow(elem, 'bedName').value;
                        delBed.bedRid = dynTableGetNodeInRow(elem, 'bedRid').value;
                        delBedsList.push(delBed);
                    }
                    dynTableDeleteRow(x);
                    dynTableGetNodeInRow(table.rows[1], 'plus').className = "ace-icon fa fa-plus";
                    return false;
                } else {
                    alert("This Bed Not In Ready to occupy Status");
                }
            }
        }
    }
    function fetchBedMaster() {
        dcomethealth.BedManagementResource.searchBedGroupMaster({}, function (data) {
            if (!!data) {
                $("#tbody_head").empty();
                $.each(bedGroupData = data, function (index, bedGroupM) {
                    $.each(unitObj, function (pIdx, unit) {
                        if (unit.id == bedGroupM.bgmBedUnitRid) {
                            $("#tbody_head").append('<tr onclick="dcomethealth.BedGroupMaster.showBedDetail(' + bedGroupM.bgmRid + ')" id="tr_head">\n\
                             <td>' + bedGroupM.bgmBedGroupName + '<input type="hidden" value="' + bedGroupM.bgmRid + '"/></td><td>' + unit.unitName + '</td></tr>');
                        }
                    });
                });
                $('#bedTable').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $('#bedTable').dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function unitWiseServicePoint(elem) {
        $("#bgmBedServicePointRid").empty();
        $("#bgmBedServicePointRid").append('<option value="0">--Choose--</option>');
        $.each(servicePoint, function (index, sPoint) {
            if ($(elem).val() == sPoint.wrdUnitRid) {
                $("#bgmBedServicePointRid").append('<option value="' + sPoint.wrdRid + '">' + sPoint.wrdName + '</option>');
            }
        });
    }
    function showBedDetail(val) {
        $.each(bedGroupData, function (index, bedGroupM) {
            if (bedGroupM.bgmRid == parseInt(val)) {
                $("#BedGroupMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(bedGroupM);
            }
        });
    }
    function populateFields(data) {
        var unitRid = "", priceType = "";
        $('#bgmBedGroupIndex').empty();
        $('#bgmBedSubGroupIndex').empty();
        $('#bgmBedPriceTypeIndex').empty();
        $("#bgmBedGroupName").val(data.bgmBedGroupName);
        $("#bgmRid").val(data.bgmRid);
        $.each(unitObj, function (pIdx, unit) {
            if (unit.id == data.bgmBedUnitRid) {
                unitRid = data.bgmBedUnitRid;
            }
        });
        $('#bgmBedUnitRid').val(unitRid);
        $.each(servicePoint, function (index, sPoint) {
            if ($('#bgmBedUnitRid').val() == sPoint.wrdUnitRid) {
                $("#bgmBedServicePointRid").append('<option value="' + sPoint.wrdRid + '">' + sPoint.wrdName + '</option>');
            }
        });
        $("#bgmBedServicePointRid").val(data.bgmBedServicePointRid);
        $('#bgmBedGroupIndex').append("<option value=''></option>");
        $('#bgmBedSubGroupIndex').append("<option value=''></option>");
        $.each(dcomethealth.dDictVal, function (index, val) {
            if (data.bgmBedGroupIndex == val.id) {
                $('#bgmBedGroupIndex').append("<option value='" + val.id + "'>" + val.ddictValue + "</option>");
            }
            if (data.bgmBedPriceTypeIndex == val.id) {
                $('#bgmBedPriceTypeIndex').append("<option value='" + val.id + "'>" + val.ddictValue + "</option>");
            }
        });
        $('#bgmBedGroupIndex').val(data.bgmBedGroupIndex);
        $("#dynTableTbody").empty();
        $("#dynTableTbody").append('<tr><td width="2%"><i id="del" class="ace-icon fa fa-minus" onclick="dcomethealth.BedGroupMaster.deleteRowBed(this)"></i></td><td width="24%"><input type="text" id="bedName" name="bedName" class="col-md-11 col-sm-11 col-xs-11 " value="" onchange="checkExistBed(this)"  onkeypress="return dcomethealth.validation.ValidateAlphaNumeric(event)"/><input type="hidden" id="bedState" name="bedState" value=""/><input type="hidden" id="bedStatus" name="bedStatus" value=""/><input type="hidden" id="bedRid" name="bedRid" value=""/><input type="hidden" id="bedBgmRid" name="bedBgmRid" value=""/></td><td width="8%"></td><td width="1%"><i id="plus" class="ace-icon fa fa-plus red" onclick="insertRowBed(\'dynTableBed\', this , 1)"></i></td></tr>');
        $.each(data.bedMasterList, function (indx, bedMaster) {
            if (bedMaster.bedIsActive == 1) {
                $("#dynTableTbody").append('<tr><td width="2%"><i id="del" class="ace-icon fa fa-minus" onclick="dcomethealth.BedGroupMaster.deleteRowBed(this)"></i></td><td width="24%"><input type="text" id="bedName" name="bedName" class="col-md-11 col-sm-11 col-xs-11 " value="' + bedMaster.bedName + '" onchange="checkExistBed(this)"  onkeypress="return dcomethealth.validation.ValidateAlphaNumeric(event)"/>  <input type="hidden" id="bedStatus" name="bedStatus" value="' + bedMaster.bedStatus + '"/><input type="hidden" id="bedState" name="bedState" value="' + bedMaster.bedState + '"/><input type="hidden" id="bedRid" name="bedRid" value="' + bedMaster.bedRid + '"/><input type="hidden" id="bedBgmRid" name="bedBgmRid" value="' + bedMaster.bedBgmRid + '"/></td><td width="8%"></td><td width="1%"><i id="plus" class="" onclick="insertRowBed(\'dynTableBed\', this , 1)"></i></td></tr>');
            }
        });
        dcomethealth.datatypes.init($("#" + id));
        $("#bgmBedSubGroupIndex > option").each(function () {
            if ($(this).val() == data.bgmBedSubGroupIndex) {
                $("#bgmBedSubGroupIndex").val(data.bgmBedSubGroupIndex);
            }
        });
    }
    function submit() {
        var form = $("form");
        if (validateForm(form)) {
            var bedGroupM = {};
            bedGroupM.bgmRid = !!$("#bgmRid").val() ? $("#bgmRid").val() : null;
            bedGroupM.bgmBedServicePointRid = $("#bgmBedServicePointRid option:selected").val()
            bedGroupM.bgmBedGroupName = $("#bgmBedGroupName").val();
            if (!!$("#bgmBedGroupIndex").val()) {
                bedGroupM.bgmBedGroupIndex = $("#bgmBedGroupIndex").val();
            }
            bedGroupM.bgmBedUnitRid = $("#bgmBedUnitRid").val();
            if (!!$("#bgmBedSubGroupIndex").val()) {
                bedGroupM.bgmBedSubGroupIndex = $("#bgmBedSubGroupIndex").val();
            }
            bedGroupM.bgmBedPriceTypeIndex = $("#bgmBedPriceTypeIndex").val();
            var childList = [];
            var table = document.getElementById('dynTableBed');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                if (!!dynTableGetNodeInRow(table.rows[i + 1], 'bedRid').value && dynTableGetNodeInRow(table.rows[i + 1], 'bedRid').value != "" && dynTableGetNodeInRow(table.rows[i + 1], 'bedName').value == "") {
                    alert("Enter Bed Name");
                    return false;
                }
                if (dynTableGetNodeInRow(table.rows[i + 1], 'bedName').value != "") {
                    var bedMaster = {};
                    if (dynTableGetNodeInRow(table.rows[i + 1], 'bedRid').value != "") {
                        bedMaster.bedRid = dynTableGetNodeInRow(table.rows[i + 1], 'bedRid').value;
                    }
                    bedMaster.bedState = 0;
                    bedMaster.bedStatus = 0;
                    bedMaster.bedName = dynTableGetNodeInRow(table.rows[i + 1], 'bedName').value;
                    bedMaster.bedIsActive = 1;
                    bedMaster.bedUnitRid = $("#bgmBedUnitRid").val();
                    childList.push(bedMaster);
                }
            }
            if (delBedsList.length > 0) {
                $.each(delBedsList, function (indx, bedM) {
                    var bedMaster = {};
                    bedMaster.bedRid = bedM.bedRid;
                    bedMaster.bedName = bedM.bedName;
                    bedMaster.bedState = 0;
                    bedMaster.bedStatus = 0;
                    bedMaster.bedIsActive = 0;
                    bedMaster.bedUnitRid = !!$("#bgmBedUnitRid").val() ? $("#bgmBedUnitRid").val() : null;
                    childList.push(bedMaster);
                });
            }
            bedGroupM.bedMasterList = childList;
            dcomethealth.BedManagementResource.saveBedGroupMaster(bedGroupM).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('BedGroupMaster');
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
        showBedDetail: showBedDetail,
        unitWiseServicePoint: unitWiseServicePoint,
        deleteRowBed: deleteRowBed,
        refreshData: refreshData
    };
}());
dcomethealth.BedGroupMaster.init();