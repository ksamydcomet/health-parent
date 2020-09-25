var dcomethealth = dcomethealth || {};
dcomethealth.PackageMaster = (function () {
    var id = "PackageMaster", PackageMaster = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            //            fetchServicePoints();
            dcomethealth.MasterResource.searchUnit({"unitIsServiceProvider": 1}, function (data) {
                $.each(data, function (pIdx, unit) {
                    $('#packageUnit').append('<option value="' + unit.id + '">' + unit.unitName + '</option>');
                });
            });

            $("#submitData").on("click", function () {
                $("#edit_user_form").validate({
                    rules: {
//                        wrdType: "required",
//                        wrdUnitRid: "required",
//                        wrdName: "required"
                    },
                    messages: {
//                        wrdType: "Select Service Point Group",
//                        wrdUnitRid: "Select Unit",
//                        wrdName: "Enter Service Point Name"
                    },
                    submitHandler: function (form) {
                        submit();
                    }
                });
            });
            autocompleteService();
        });
    }

//    function fetchServicePoints() {
//        dcomethealth.MasterResource.getServicePoints({"wrdEntityRid": dcomethealth.loginuser.entityRid}, function (data) {
//            if (!!data) {
//                $("#tbodyHead").empty();
//                var Group = null, SubGroup = null;
//                $.each(servicePoint = data, function (index, sPoint) {
//                    $.each(dcomethealth.dDictVal, function (index, val) {
//                        if (parseInt(sPoint.wrdType) == val.id) {
//                            Group = val.ddictValue;
//                        }
//                        if (parseInt(sPoint.wrdCategory) == val.id) {
//                            SubGroup = val.ddictValue;
//                        }
//                    });
//                    var act = "No";
//                    if (sPoint.wrdNumber === 1) {
//                        act = "Yes";
//                    }
//                    $("#tbodyHead").append('<tr onclick="dcomethealth.ServicePoints.showServicePoint(' + sPoint.wrdRid + ')" id="tr_head">\n\
//                        <td>' + sPoint.wrdName + '<input type="hidden" value="' + sPoint.wrdRid + '"/></td><td>' + Group + '</td><td>' + SubGroup + '</td><td>' + act + '</td></tr>');
//                });
//                $("#sPointTable").dataTable();
//                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
//                $('.dataTables_length select').addClass('form-control');
//            }
//        });
//    }
//    function showServicePoint(val) {
//        $.each(servicePoint, function (index, sPoint) {
//            if (sPoint.wrdRid == parseInt(val)) {
//                $("#ServicePointsHeader").removeClass("panel panel-primary").addClass("hidden");
//                $("#addNew").removeClass("hidden").addClass("panel panel-primary");
//                populateFields(sPoint);
//            }
//        });
//    }
//    function populateFields(data) {
//        $("#wrdRid").val(data.wrdRid);
//        $("#wrdType").val(data.wrdType);
//        $.each(dcomethealth.dDictVal, function (index, val) {
//            if (parseInt($("#wrdType").val()) == val.ddictParentIndex) {
//                $("#wrdCategory").append("<option value=" + val.id + ">" + val.ddictValue + "</option>");
//            }
//        });
//        $("#wrdCategory").val(data.wrdCategory);
//        $("#wrdName").val(data.wrdName);
//        $("#wrdUnitRid").val(data.wrdUnitRid);
//        var isSchedule = parseInt(data.wrdNumber);
//        if (isSchedule == 1) {
//            $('#collect').val(data.wrdNumber);
//            $('#openPmd').prop("checked", true);
//            daywiseStatus();
//        } else {
//            $('#collect').val(data.wrdNumber);
//            $('#openPmd').prop("checked", false);
//            daywiseStatus();
//        }
//        dcomethealth.ServicePoints.searchResource(data.wrdResourceRid);
//    }

    function checkExistService(serviceRID) {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        var y = table_length - 2;
        var check = false;
        for (var i = 0; i < table_length - 2; i++) {
            var serviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceRID').value;
            if (parseInt(serviceRid) == parseInt(serviceRID)) {
                serviceRID = "";
                dynTableGetNodeInRow(table.rows[y + 1], 'serviceRID').value = 0;
//                dynTableGetNodeInRow(table.rows[y + 1], 'MRP').value = "";
//                dynTableGetNodeInRow(table.rows[y + 1], 'netAmount').value = "";
                setTimeout(function () {
                    dynTableGetNodeInRow(table.rows[y + 1], 'serviceName').value = "";
                }, 20);
                check = true;
            }
        }
        if (check) {
            alert("Already Existing Service");
            $('#total_pmd').val('');
            return false;
        }
    }
    function autoIdSet(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'serviceRID').value = "";
            dynTableGetNodeInRow(elem, 'packageServiceName').value = "";
//            dynTableGetNodeInRow(elem, 'MRP').value = "";
//            dynTableGetNodeInRow(elem, 'netAmount').value = "";
        }
    }
    function autocompleteService() {
        $("#" + id + " input[name='packageServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                autoIdSet(dynTableGetNodeInRow(this, 'serviceRID'));
                dynTableGetNodeInRow(this, 'serviceRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'bdGroupRID').value = ui.item.data.bsServiceType;
//                if ($('#visitReason option:selected').text() == 'Emergency') {
//                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bEprice;
//                } else {
//                    dynTableGetNodeInRow(this, 'MRP').value = ui.item.data.bPrice;
//                }
                $('#total_pmd').val('');
//                payerNameChange(0);               
                checkExistService(dynTableGetNodeInRow(this, 'serviceRID').value);
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
                var check = true;
                var queryString = request.term;
                queryString = queryString.trim();
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
                    serviceClearVar = 0;
                    if (!!data && data.length > 0) {
                        check = false;
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
                    } else {
                        if (check == true) {
                            $('#total_pmd').val('');
                            checkValidService()
                            return false;
                        }
                    }
                });
            },
            minLength: 1
        });
    }
    function submit() {        
        var form = $("form");
        if (validateForm(form)) {
            var package = {};
            if (!!$("#pmRid").val()) {
                package.pmRid = $("#pmRid").val();
            }
            package.pmName = $("#packageName").val();
            package.pmPrice = $("#packagePrice").val();
            package.pmType = $("#packageUnit").val();
//            servicePoint.wrdUnitRid = $("#wrdUnitRid").val();
//            servicePoint.wrdEntityRid = dcomethealth.loginuser.entityRid;
////            servicePoint.wrdResourceRid = $("#wrdResourceRid").val();
//            servicePoint.wrdNumber = $('#collect').val();
//            if (parseInt($('#collect').val()) === 1) {
//
//                if ($("#resRid").val() != "") {
//                    resource.id = $("#resRid").val();
//                }
//                resource.resName = $("#wrdName").val();
//                resource.resType = 1;
//                resource.resEntRID = dcomethealth.loginuser.entityRid;
//                resource.resSchedInterval = 0;
//                resource.resUnitRID = dcomethealth.selectedunit;
//                resource.resValid = 1;
//                resource.portResID = 0;
//                resource.resSequenceNumber = 0;
//                resource.resCategory = $("#wrdType").val();
//                resource.resSubcategory = 0;
//                resource.resSchedDuration = 20;
//
//                var table = document.getElementById('dynTableSchedule');
//                var table_length = table.rows.length;
//                for (var i = 0; i < table_length - 1; i++) {
//                    var resWh = {};
//                    if (dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value != "") {
//                        resWh.id = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilWhRid').value;
//                    }
//                    resWh.whEntityRID = dcomethealth.loginuser.entityRid;
//                    if (parseInt($('#collect').val()) === 1) {
//                        resWh.whDayOfWeek = dynTableGetNodeInRow(table.rows[i + 1], 'resAvilDays').value;
//                        resWh.whFlag = 1;
//                    }
//                    resWh.whFromTime = moment(dynTableGetNodeInRow(table.rows[i + 1], 'fromTime').value, 'HH:mm').format('HH:mm:ss');
//                    resWh.whToTime = moment(dynTableGetNodeInRow(table.rows[i + 1], 'ToTime').value, 'HH:mm').format('HH:mm:ss');
//                    resWh.whUnitRID = $("#wrdUnitRid").val();
//                    resWhList.push(resWh);
//                }
//            }
//            resource.resourceWorkingHours = resWhList;
//            servicePointList.push(servicePoint);
//            resource.wardMasters = servicePointList;

            dcomethealth.MasterResource.savePackage(package).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('PackageMaster');
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
        autocompleteService: autocompleteService,
        autoIdSet: autoIdSet,
        checkExistService:checkExistService,
//        showServicePoint: showServicePoint,
//        searchResource: searchResource,
        refreshData: refreshData
    };
}());
dcomethealth.PackageMaster.init();