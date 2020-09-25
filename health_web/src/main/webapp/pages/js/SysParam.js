var dcomethealth = dcomethealth || {};
var sParamList = [], unitMapList = {};
dcomethealth.SysParam = (function () {
    var id = "SysParam";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            fetchParamList();
            $("#assignEntity").select2({width: 'resolve'});
            setTargetEntity();
            $("#edit_user_form").validate({
                rules: {
                    paramCode: "required",
                    paramName: "required",
                    paramDescription: "required",
                    paramValue: "required",
                },
                messages: {
                    paramCode: "Enter param name",
                    paramName: "Enter param code",
                    paramDescription: "Enter payer price",
                    paramValue: "Enter Patient price",
                },
                submitHandler: function (form) {
                    submit();
                }
            });
        });
    }
    function fetchParamList() {
        var search = {};
        dcomethealth.DataDictionaryResource.getSysParam(search, function (data) {
            $("#tbodyHead").empty();
            $.each(sParamList = data, function (idx, s_param) {
                $("#tbodyHead").append('<tr onclick="dcomethealth.SysParam.showParamDetail(' + s_param.id + ')" id="tr_head">\n\
                    <td>' + s_param.paramCode + '</td><td>' + s_param.paramName + '</td><td>' + s_param.paramValue + '</td></tr>');
            });
            $("#paramMasterTable").dataTable();
            $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
            $('.dataTables_length select').addClass('form-control');
        });
    }
    function showParamDetail(val) {
        $.each(sParamList, function (idx, s_param) {
            if (s_param.id == parseInt(val)) {
                $("#activeDiv").removeClass("hidden")
                $("#sysParamHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#addNew").removeClass("hidden").addClass("panel panel-primary");
                populateFields(s_param);
            }
        });
    }
    function populateFields(data) {
        var unitList = [], unitArray = [];
        $("#paramRid").val(data.id);
        $("#paramCode").val(data.paramCode);
        $("#paramName").val(data.paramName);
        $("#paramDescription").val(data.paramDescription);
        $("#paramValue").val(data.paramValue);
        $.each(unitMapList = data, function (cdx, unitMap) {
            if ($.inArray(unitMap.sumUnitRID, unitArray) === -1) {
                unitArray.push(unitMap.sumUnitRID);
            }
        });
        for (var k = 0; k < unitArray.length; k++) {
            var Uid = unitArray[k];
            $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
                $.each(entities.units, function (cIdx, units) {
                    if (parseInt(Uid) == parseInt(units.id)) {
                        var item = {};
                        item ["id"] = Uid;
                        item ["text"] = entities.entityShortName + '-' + units.unitName
                        unitList.push(item);
                    }
                });
            });
        }
        $("#s2id_assignEntity").select2('data', unitList);
        $("#s2id_assignEntity").find(".select2-choices").find(".select2-search-choice-close").remove();
    }
    function setTargetEntity() {
        dcomethealth.DataDictionaryResource.getEntities(function (data) {
            $.each(data, function (pIdx, units) {
                if (units.entityRootParentRID == 1) {
                    $("#assignEntity").append('<option value="' + units.id + '">' + units.entityShortName + '</option>');
                }
            });
        });
    }

    function submit() {
        var form = $("form");
        var param = {};
        if (!!$("#paramRid").val()) {
            param.id = $("#paramRid").val();
        }
        param.paramCode = $("#paramCode").val();
        param.paramName = $("#paramName").val();
        param.paramDescription = $("#paramDescription").val();
        param.paramValue = $("#paramValue").val();
        param.paramUnitRID = 0;
        $("#s2id_assignEntity").find(".select2-choices").find(".select2-search-choice").find("div").each(function (indx, target) {
            var target = $(target).find("input[type='hidden']").val();
            if (!!target) {
                if (!$.isEmptyObject(unitMapList)) {
                    var check = true;
                    $.each(unitMapList, function (cIdx, unit) {
                        if (parseInt(target) == parseInt(unit.sumUnitRID)) {
                            check = false;
                            var skuUnitMap = {};
                            skuUnitMap.id = unit.id;
                            skuUnitMap.sumUnitRID = target;
                            lensUnitMapList.push(skuUnitMap);
                        }
                    });
                    if (check) {
                        var skuUnitMap = {};
                        skuUnitMap.sumUnitRID = target;
                        lensUnitMapList.push(skuUnitMap);
                    }
                } else {
                    var skuUnitMap = {};
                    skuUnitMap.sumUnitRID = target;
                    lensUnitMapList.push(skuUnitMap);
                }
            }
        });
        console.log(param);
        return false;
        dcomethealth.DataDictionaryResource.saveSysParam(param).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('SysParam');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }

    function refreshData() {
    }
    return {
        init: init,
        submit: submit,
        showParamDetail: showParamDetail,
        fetchParamList: fetchParamList,
        populateFields: populateFields,
        refreshData: refreshData

    };
}());
dcomethealth.SysParam.init();