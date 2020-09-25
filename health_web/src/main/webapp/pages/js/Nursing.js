var dcomethealth = dcomethealth || {};
dcomethealth.Nursing = (function () {
    var id = "Nursing", secArray = [], doctor = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $("#doctorName").select2({width: 'resolve'});
            $('#div2,#div3,#div4,#div5,#div6,#soCompleteDiv').hide();
            $('#div1,#bedDiv').show();
//            $('#patientDiv').removeClass('hidden');
            dcomethealth.MasterResource.searchStaff({}, function (data) {
                doctor = data;
            });
            dcomethealth.DataDictionaryResource.searchTemplate({"tempType": "DISCHARGE_SUMMARY"}, function (data) {
                if (!!data) {
                    $.each(dcomethealth.template = data, function (indx, template) {
                        $("#disSummaryDiv").html(template.tempNodes);
                    });
                }
            });
            dcomethealth.MasterResource.getServicePoints({"wrdEntityRid": dcomethealth.loginuser.entityRid}, function (data) {
                if (!!data) {
                    servicePoint = data;
                }
            });
            $("#processServiceOn").datepicker({format: 'dd-mm-yyyy'});
            $('#processServiceOn').val(moment().format('DD-MM-YYYY'));
//            patientSearch();
            autocompleteService();
            autoCompleteDrug();
//            nursingSearch(0);
            houseKeeping();
            maintainenceSearch();
            $("#bedSubGroupIndex").on("change", function () {
                $("#bedSubGroupMRid").val($(this).val());
            });
            $('input.timepicker').timepicker({timeFormat: 'h:mm:ss p'});
            $("#btmGroupRid").change(function () {
                var roomTypeArray = [], countRoom = 0
                $("#bgmBedPriceTypeIndex").empty();
                $("#bgmBedPriceTypeIndex").append('<option value="0">--Select--</option>');
                var searchParams = {"bedIsActive": 1, "bedBgmRid": $('#btmGroupRid option:selected').val()};
                dcomethealth.BedManagementResource.searchBedmaster(searchParams, function (data) {
                    if (!!data) {
//                        $.each(data, function (idx, bedType) {
//                            var optRoomType = null;
//                            optRoomType = bedType.bgmBedPriceTypeIndex;
//                            if ($.inArray(optRoomType, roomTypeArray) === -1) {
//                                roomTypeArray.push(optRoomType);
//                            }
//                        });
                        for (var i = 0; i < roomTypeArray.length; i++) {
                            var rmTypeValue = roomTypeArray[i];
                            $.each(dcomethealth.dDictVal, function (i, val) {
                                if (parseInt(rmTypeValue) === val.id) {
                                    $("#bgmBedPriceTypeIndex").append('<option value="' + rmTypeValue + '">' + val.ddictValue + '</option>');
                                }
                            });
                        }
                    }
                });
            });
        });
    }
//     function patientSearch() {
//        $("#" + id + " input[id='patSearch']").autocomplete({
//            select: function (event, ui) {
//                var uiData = ui.item.data; 
//                $("#patRidSearch").val(uiData.id);
//                $("#patName").text(uiData.patName);
//                $("#patDemoInfo").text("(Gender: " + uiData.patGender + ", Age: " + uiData.patAge + ")");
//                loadPatientVisitTab(uiData.id);
////                getFiles(uiData.id);
//                getLedgerDetails(uiData.id);
//            },
//            source: function (request, response) {
//                var queryString = request.term;
//                queryString = queryString.trim();
//                var searchParams = {"q": queryString};
//                dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
//                    response($.grep($.map(data, function (data) {
//                        return {label: (data.patMrnNo || "") + (data.patMrnNo && data.patPhoneNo ? " - " : "") + (data.patPhoneNo || ""),
//                            value: (data.patMrnNo || ""), name: (data.patTitle || "") + (data.patTitle && data.patFullName ? " - " : ""), data: data
//                        };
//                    }), function (data, index) {
//                        return index < 10;
//                    }));
//                });
//            },
//            minLength: 1
//        });
//    }
    function isChecked(e) {
        if ($("#" + e.id).is(':checked')) {
            $("#disSummaryDiv").find("div." + (e.id).slice(0, -5) + "Div").removeClass('hidden');
        }
    }
    function openTab(e, visitRid, PatRid, btmRid, bedName, bgmBedPriceTypeIndex, btmPrice, patPrimaryDoctor) {
        if (e.id == "drugTab") {
            getVisitPatient(PatRid, btmRid, bedName, bgmBedPriceTypeIndex, btmPrice, patPrimaryDoctor);
            $('#bedDetails,#dischargeDiv,#maintenanceDiv,#bedTransferDiv,#houseKeepingDiv,#bedDiv,#div1,#div3,#div4,#div5').hide();
            $('#div2').show();
        } else {
            getVisitPatient(PatRid, btmRid, bedName, bgmBedPriceTypeIndex, btmPrice, patPrimaryDoctor);
            $('#bedDetails,#dischargeDiv,#maintenanceDiv,#bedTransferDiv,#houseKeepingDiv,#bedDiv,#div1,#div2,#div4,#div5').hide();
            $('#div3').show();
        }
    }
    function openModal(patRid, visitRid, primaryDoctor, bedRid, bocRid) {
        var secDoctorList = [], secDoctorArray = [];
        $('#patientRid').val(patRid);
        $('#patientVisitRid').val(visitRid);
        $('#patPrimaryDoctor').val(primaryDoctor);
        $('#patBedRid').val(bedRid);
        $('#bocRid').val(bocRid);
        var searchDoctor = {"bocBedRid": bedRid, "bocPrimaryDoctor": primaryDoctor};
        dcomethealth.BedManagementResource.searchBedOccupancy(searchDoctor, function (data) {
            if (!!data) {
                $.each(data, function (idx, bedOccupancy) {
                    secArray = [];
                    if (!!(bedOccupancy.bocSecondaryDoctor)) {
                        var secData = bedOccupancy.bocSecondaryDoctor;
                        secDoctorArray = JSON.parse("[" + secData + "]"); //For String to Array Conversion 
                        if (!!secDoctorArray.length) {
                            $.each(secDoctorArray, function (i) {
                                $("#doctorName").empty();
                                $.each(doctor, function (idx, staff) {
                                    var staffAdmin = staff.staffName;
                                    var secDocObj = {};
                                    if (parseInt(staff.id) != parseInt(bedOccupancy.bocPrimaryDoctor) && !(staffAdmin).includes('Admin')) {
                                        if (staff.id == parseInt(secDoctorArray[i])) {
                                            secDocObj ["id"] = staff.id;
                                            secDocObj ["text"] = staff.staffName;
                                            secDoctorList.push(secDocObj);
                                            secArray.push(secDoctorArray[i])
                                        }
                                        $("#doctorName").append('<option value="' + staff.id + '">' + staff.staffName + '</option>');
                                    }
                                });
                            });
                            $("#s2id_doctorName").select2('data', secDoctorList);
                            secDoctorList = [];
                        }
                    } else {
                        $("#s2id_doctorName").select2('data', '');
                        $("#doctorName").empty();
                        $.each(doctor, function (idx, staff) {
                            if (staff.id != bedOccupancy.bocPrimaryDoctor && !(staff.staffName).includes('Admin')) {
                                $("#doctorName").append('<option value="' + staff.id + '">' + staff.staffName + '</option>');
                            }
                        });
                    }
                });
            }
        });
        $('#doctorModal').modal('show');
    }
    function trigger(val) {
        $('.popover-markup>.trigger').popover({
            html: true,
            placement: 'top',
            content: function () {
                return $(this).parent().find('.content').html();
            }
        });
        $(document).on("click", "#addNotes", function () {
            var inputval = $(".popover #bedNotes").val();
            $('#notesValues').val(inputval);
            $('.popover-markup>.trigger').popover('hide');
            return false;
        });
        $(document).on("click", "#cancelNotes", function () {
            $('.popover-markup>.trigger').popover('hide');
            return false;
        });
    }
    function autocompleteService() {
        $("#" + id + " input[name='serviceName']").autocomplete({
            select: function (event, ui) {
                dynTableGetNodeInRow(this, 'serviceName').value = ui.item.value;
                dynTableGetNodeInRow(this, 'itemRID').value = ui.item.data.id;
                dynTableGetNodeInRow(this, 'servicePrice').value = ui.item.data.bPrice;
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
                var searchParams = {"bsName": queryString, "bsServiceActive": 1};
                dcomethealth.MasterResource.searchServices(searchParams, function (data) {
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
                });
            },
            minLength: 1
        });
    }
    function autoCompleteDrug() {
        $("#" + id + " input[name='matItemName']").autocomplete({
            select: function (event, ui) {
                dynTableGetNodeInRow(this, 'matItemName').value = ui.item.value;
                dynTableGetNodeInRow(this, 'matSkuRid').value = ui.item.data.id;
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
    function houseKeeping() {
        dcomethealth.BedManagementResource.searchBedGroupMaster({}, function (data) {
            $('#hkTbody').empty();
            $.each(data, function (igx, bedGroupM) {
                $.each(bedGroupM.bedMasterList, function (igx, bedMaster) {
                    var priceType = null, spVal = null, groupVal = null, subGroupVal = null, bedNo = "--";
                    if (parseInt(bedMaster.bedStatus) == 3) {
                        $.each(dcomethealth.dDictVal, function (i, val) {
                            if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                priceType = val.ddictValue;
                            }
                            if (parseInt(bedGroupM.bgmBedGroupIndex) == val.id) {
                                groupVal = val.ddictValue;
                            }
                            if (parseInt(bedGroupM.bgmBedSubGroupIndex) == val.id) {
                                subGroupVal = val.ddictValue;
                            }
                        });    //<td><span id="spType">' + spVal + '</span><input type="hidden" id="bedSpType" value="' + bedGroupM.bgmBedServicePointRid + '"/></td>\n\                    
                        bedNo = (!!bedMaster.bedNo ? bedMaster.bedNo : "--");
                        groupVal = (!!groupVal ? groupVal : "--");
                        subGroupVal = (!!subGroupVal ? subGroupVal : "--");
                        $('#hkTbody').append('<tr><td><input type="hidden" value="' + bedMaster.bedRid + '" id="bedid"/><input type="hidden" value="' + bedMaster.bedBgmRid + '" id="bedGrMrid"/><span class="col-md-12" id="bedNo">' + bedNo + '</span></td>\n\
                        <td><span class="col-md-12" id="bedName">' + bedMaster.bedName + '</span></td>\n\
                        <td><span id="priceType">' + priceType + '</span><input type="hidden" id="bedPriceType" value="' + bedGroupM.bgmBedPriceTypeIndex + '"/></td>\n\
                        <td><span id="bedGroupType">' + groupVal + '</span><input type="hidden" id="bedGpType" value="' + bedGroupM.bgmBedGroupIndex + '"/></td>\n\
                        <td><span id="bedSubGroupType">' + subGroupVal + '</span><input type="hidden" id="bedSubGpType" value="' + bedGroupM.bgmBedSubGroupIndex + '"/></td>\n\
                        <td><select id="hkStatus" class="col-md-8"><option value=""></option><option value="2">Maintenance</option><option value="3">Ready to Occupy</option>\n\
                        </select><input type="hidden" id="notesValues"/>\n\
                        <div class="popover-markup" style=""><a href="#" onclick="dcomethealth.Nursing.trigger(' + bedMaster.bedRid + ')" class="trigger" >Add Notes</a><div class="content hide"><div class="form-group"><input type="text" id="bedNotes" class="form-control" placeholder="Add Notes"/></div><button id="addNotes" type="button" class="btn btn-aris"><i class="fa fa-check"></i></button>&nbsp;<button id="cancelNotes" type="button" class="btn btn-aris"><i class="fa fa-times"></i></button></div></td>\n\
                        <td><a class="btn-aris btn-aris-sm" onclick="dcomethealth.Nursing.submitBedMaster(' + bedMaster.bedRid + ',\'hkTable\')"><i class="fa fa-floppy-o"></i></a></td></tr>');
                    }
                });
            });
        });
    }
    function maintainenceSearch() {
        dcomethealth.BedManagementResource.searchBedGroupMaster({}, function (data) {
            $('#mTbody').empty();
            $.each(data, function (igx, bedGroupM) {
                $.each(bedGroupM.bedMasterList, function (igx, bedMaster) {
                    var priceType = null, groupVal = null, subGroupVal = null, bedNo = "--";
                    if (parseInt(bedMaster.bedStatus) == 4) {
                        $.each(dcomethealth.dDictVal, function (i, val) {
                            if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                priceType = val.ddictValue;
                            }
                            if (parseInt(bedGroupM.bgmBedGroupIndex) == val.id) {
                                groupVal = val.ddictValue;
                            }
                            if (parseInt(bedGroupM.bgmBedSubGroupIndex) == val.id) {
                                subGroupVal = val.ddictValue;
                            }
                        });
                        bedNo = (!!bedMaster.bedNo ? bedMaster.bedNo : "--");
                        groupVal = (!!groupVal ? groupVal : "--");
                        subGroupVal = (!!subGroupVal ? subGroupVal : "--");
                        $('#mTbody').append('<tr><td><input type="hidden" value="' + bedMaster.bedRid + '" id="bedid"/><input type="hidden" value="' + bedMaster.bedBgmRid + '" id="bedGrMrid"/><span class="col-md-12" id="bedNo">' + bedNo + '</span></td>\n\
                        <td><span class="col-md-12" id="bedName">' + bedMaster.bedName + '</span></td>\n\
                        <td><span id="priceType">' + priceType + '</span><input type="hidden" id="bedPriceType" value="' + bedGroupM.bgmBedPriceTypeIndex + '"/></td>\n\
                        <td><span id="bedGroupType">' + groupVal + '</span><input type="hidden" id="bedGpType" value="' + bedGroupM.bgmBedGroupIndex + '"/></td>\n\
                        <td><span id="bedSubGroupType">' + subGroupVal + '</span><input type="hidden" id="bedSubGpType" value="' + bedGroupM.bgmBedSubGroupIndex + '"/></td>\n\
                        <td><select id="hkStatus" class="col-md-8"><option value=""></option><option value="1">HouseKeeping</option><option value="3">Ready to Occupy</option>\n\
                        </select><input type="hidden" id="notesValues"/>\n\
                        <div class="popover-markup" style=""><a href="#" onclick="dcomethealth.Nursing.trigger(' + bedMaster.bedRid + ')" class="trigger" >Add Notes</a><div class="content hide"><div class="form-group"><input type="text" id="bedNotes" class="form-control" placeholder="Add Notes"/></div><button id="addNotes" type="button" class="btn btn-aris"><i class="fa fa-check"></i></button>&nbsp;<button id="cancelNotes" type="button" class="btn btn-aris"><i class="fa fa-times"></i></button></div></td>\n\
                        <td><a class="btn-aris btn-aris-sm" onclick="dcomethealth.Nursing.submitBedMaster(' + bedMaster.bedRid + ',\'mTable\')"><i class="fa fa-floppy-o"></i></a></td></tr>');
                    }
                });
            });
        });
    }
    function fetchBedDetails(eId) {
        $.each(bedGroupData, function (index, bedGroupM) {

        });
    }
    function nursingSearch(val) {
        $('#nTbody').empty();
        var vacantCount = 0, occupiedCount = 0, maleCount = 0, femaleCount = 0, maintainanceCount = 0, hkCount = 0, array = [], groupKey = "", groupId = "";
        if (val == 0) {
            $('#vacantCount').text(0);
            $('#occupiedCount').text(0);
            $('#MaintainanceCount').text(0);
            $('#maleCount').text(0);
            $('#femaleCount').text(0);
            $('#hkCount').text(0);
        }
        if (!!bedGroupData && bedGroupData.length > 0) {
            $.each(bedGroupData, function (index, bedGroupM) {
                if (!("bgmBedGroupIndex" in bedGroupM) && !("bgmBedSubGroupIndex" in bedGroupM)) {
                    groupKey = bedGroupM.bgmBedServicePointRid;
                    groupId = "btmServicePointIndex";
                }
                if (bedGroupM.bgmBedGroupIndex == 0 && bedGroupM.bgmBedSubGroupIndex == 0) {
                    groupKey = bedGroupM.bgmBedServicePointRid;
                    groupId = "btmServicePointIndex";
                }
                if (("bgmBedGroupIndex" in bedGroupM) && bedGroupM.bgmBedGroupIndex != 0) {
                    groupKey = bedGroupM.bgmBedGroupIndex;
                    groupId = "bgmBedGroupIndex";
                }
                if (("bgmBedSubGroupIndex" in bedGroupM) && bedGroupM.bgmBedSubGroupIndex != 0) {
                    groupKey = bedGroupM.bgmBedSubGroupIndex;
                    groupId = "bgmBedSubGroupIndex";
                }
                if (groupKey == parseInt($("#" + groupId).val())) {
                    $.each(bedGroupM.bedMasterList, function (igx, bedMaster) {
                        if (parseInt(bedMaster.bedStatus) == 1) {
                            var vacant = 1;
                            vacantCount += vacant;
                        }
                        else if (parseInt(bedMaster.bedStatus) == 2) {
                            var occupied = 1;
                            occupiedCount += occupied;
                        }
                        else if (parseInt(bedMaster.bedStatus) == 3) {
                            var houseKeeping = 1;
                            hkCount += houseKeeping;
                        }
                        else if (parseInt(bedMaster.bedStatus) == 4) {
                            var maintainance = 1;
                            maintainanceCount += maintainance;
                        }

                        $('#vacantCount').text(vacantCount);
                        $('#occupiedCount').text(!!occupiedCount ? occupiedCount : 0);
                        $('#MaintainanceCount').text(!!maintainanceCount ? maintainanceCount : 0);
                        $('#hkCount').text(!!hkCount ? hkCount : 0);
                        var bedType = null, bedRoomType = null, visitType = null;
                        $.each(dcomethealth.sDdict, function (pIdx, s_ddict) {
                            if (s_ddict.dditCode === "VISIT_TYPE") {
                                $.each(s_ddict.ddict, function (i) {
                                    if (s_ddict.ddict[i].ddictValue === "IP") {
                                        visitType = s_ddict.ddict[i].id;
                                    }
                                });
                            }
                        });
                        if (parseInt(val) == 0) {
                            if (parseInt(bedMaster.bedStatus) != 2) {
                                $.each(dcomethealth.dDictVal, function (i, val) {
                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                        bedType = val.ddictValue;
                                        bedRoomType = val.id;
                                    }
                                });
                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>' + bedType + '</td><td>--</td></tr>');
                            } else {
                                var primaryDoctor = '--';
                                if (parseInt(bedMaster.bedStatus) == 2) {
                                    dcomethealth.PatientResource.searchVisit({"visTypeIndex": visitType}, function (data) {
                                        if (!!data) {
                                            $.each(data, function (ihx, visitData) {
                                                if (!!visitData.bedOccupancy) {
                                                    $.each(visitData.bedOccupancy, function (Idx, occupancy) {
                                                        if (parseInt(occupancy.bocPatRid) == parseInt(visitData.visPatRid) && parseInt(bedMaster.bedRid) == parseInt(occupancy.bocBedRid)) {
                                                            if ($.inArray(occupancy.bocPatRid, array) == -1) {
                                                                array.push(occupancy.bocPatRid);
                                                                $.each(visitData.patient, function (igjx, patients) {
                                                                    if (parseInt(occupancy.bocPatRid) == parseInt(patients.id)) {
                                                                        if (parseInt(patients.patGenderIndex) == 1) {
                                                                            var male = 1;
                                                                            maleCount += male;
                                                                            $('#maleCount').text(!!maleCount ? maleCount : 0);
                                                                        } else if (parseInt(patients.patGenderIndex) == 2) {
                                                                            var female = 1;
                                                                            femaleCount += female;
                                                                            $('#femaleCount').text(!!femaleCount ? femaleCount : 0);
                                                                        }
                                                                    }
                                                                    $.each(doctor, function (idx, staff) {
                                                                        if (staff.id == occupancy.bocPrimaryDoctor) {
                                                                            primaryDoctor = staff.staffName;
                                                                        }
                                                                    });
//                                                    if (parseInt(bedMaster.bedRid) == parseInt(occupancy.bocBedRid)) {
                                                                    $.each(dcomethealth.dDictVal, function (i, val) {
                                                                        if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                                                            bedType = val.ddictValue;
                                                                            bedRoomType = val.id;
                                                                        }
                                                                    });
                                                                    $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>' + patients.patMrnNo + '</td><td><a href="#" onclick="dcomethealth.Nursing.getVisitPatient(' + occupancy.bocPatRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')" class="text-primary">' + patients.patName + '</a></td><td>' + primaryDoctor + '</td><td>' + visitData.id + '</td><td>--</td><td>' + bedType + '</td><td><a href="#" id="drugTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Drug Order" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')"><i class="fa fa-medkit"></i></a>&nbsp;<a href="#" id="serviceTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Service Request" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')"><i class="fa  fa-pencil-square-o "></i></a>&nbsp;<a href="#" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Add Secondary Doctor" onclick="dcomethealth.Nursing.openModal(' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + occupancy.bocPrimaryDoctor + ',' + occupancy.bocBedRid + ',' + occupancy.bocRid + ')"><i class="fa  fa-user-md "></i></a>&nbsp;<a href="#" id="bedTransferTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Bed Transfer" onclick="dcomethealth.Nursing.viewBedTransfer(' + occupancy.bocRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',' + occupancy.bocPrimaryDoctor + ',\'' + occupancy.bocSecondaryDoctor + '\')"><i class="fa fa-exchange"></i></a></td></tr>');
                                                                });
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        }
                        else if (parseInt(val) == 1) {
                            $('#nTbody').empty();
                            var primaryDoctor1 = '--';
                            if (parseInt(bedMaster.bedStatus) == 2) {
                                dcomethealth.PatientResource.searchVisit({"visTypeIndex": visitType}, function (data) {
                                    if (!!data) {
                                        $.each(data, function (ihx, visitData) {
                                            if (visitData.bedOccupancy != null) {
                                                $.each(visitData.bedOccupancy, function (Idx, occupancy) {
                                                    if (parseInt(occupancy.bocPatRid) == parseInt(visitData.visPatRid)) {
                                                        $.each(visitData.patient, function (igjx, patients) {
                                                            if (parseInt(bedMaster.bedRid) == parseInt(occupancy.bocBedRid) && parseInt(patients.patGenderIndex) == 1) {
                                                                if (parseInt(patients.patGenderIndex) == 1) {
                                                                    var male = 1;
                                                                    maleCount += male;
                                                                    $('#maleCount').text(!!maleCount ? maleCount : 0);
                                                                }
                                                                else if (parseInt(patients.patGenderIndex) == 2) {
                                                                    var female = 1;
                                                                    femaleCount += female;
                                                                    $('#femaleCount').text(!!femaleCount ? femaleCount : 0);
                                                                }
                                                                $.each(dcomethealth.dDictVal, function (i, val) {
                                                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                                                        bedType = val.ddictValue;
                                                                        bedRoomType = val.id;
                                                                    }
                                                                });
                                                                $.each(doctor, function (idx, staff) {
                                                                    if (staff.id == occupancy.bocPrimaryDoctor) {
                                                                        primaryDoctor1 = staff.staffName;
                                                                    }
                                                                });
//                                                if (parseInt($("#btmServicePointIndex").val()) == parseInt(bedMaster.btmGroupRid)) {
                                                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>' + patients.patMrnNo + '</td><td><a href="#" onclick="dcomethealth.Nursing.getVisitPatient(' + occupancy.bocPatRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedRid + ',\'' + primaryDoctor + '\')" class="text-primary">' + patients.patName + '</a></td><td>' + primaryDoctor1 + '</td><td>' + visitData.id + '</td><td>--</td><td>' + bedType + '</td><td><a href="#" id="drugTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Drug Order" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedRid + ',\'' + primaryDoctor + '\')"><i class="fa fa-medkit"></i></a>&nbsp;<a href="#" id="serviceTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Service Request" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedRid + ',\'' + primaryDoctor + '\')"><i class="fa  fa-pencil-square-o "></i></a>&nbsp;<a href="#" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Add Secondary Doctor" onclick="dcomethealth.Nursing.openModal(' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + occupancy.bocPrimaryDoctor + ',' + occupancy.bocBedRid + ',' + occupancy.bocRid + ')"><i class="fa  fa-user-md "></i></a>&nbsp;<a href="#" id="bedTransferTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Bed Transfer" onclick="dcomethealth.Nursing.viewBedTransfer(' + occupancy.bocRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + bedRoomType + ',' + bedMaster.bedRid + ',' + occupancy.bocPrimaryDoctor + ',\'' + occupancy.bocSecondaryDoctor + '\')"><i class="fa fa-exchange"></i></a></td></tr>');
//                                                }
                                                            }
//                                                        }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        } else if (parseInt(val) == 2) {
                            $('#nTbody').empty();
                            var primaryDoctor2 = '--';
                            if (parseInt(bedMaster.bedStatus) == 2) {
                                dcomethealth.PatientResource.searchVisit({"visTypeIndex": visitType}, function (data) {
                                    if (!!data) {
                                        $.each(data, function (ihx, visitData) {
                                            if (visitData.bedOccupancy != null) {
                                                $.each(visitData.bedOccupancy, function (Idx, occupancy) {
                                                    if (parseInt(occupancy.bocPatRid) == parseInt(visitData.visPatRid)) {
                                                        $.each(visitData.patient, function (igjx, patients) {
                                                            if (parseInt(bedMaster.bedRid) == parseInt(occupancy.bocBedRid) && parseInt(patients.patGenderIndex) == 2) {
                                                                if (parseInt(patients.patGenderIndex) == 1) {
                                                                    var male = 1;
                                                                    maleCount += male;
                                                                    $('#maleCount').text(!!maleCount ? maleCount : 0);
                                                                }
                                                                else if (parseInt(patients.patGenderIndex) == 2) {
                                                                    var female = 1;
                                                                    femaleCount += female;
                                                                    $('#femaleCount').text(!!femaleCount ? femaleCount : 0);
                                                                }
                                                                $.each(dcomethealth.dDictVal, function (i, val) {
                                                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                                                        bedType = val.ddictValue;
                                                                        bedRoomType = val.id;
                                                                    }
                                                                });
                                                                $.each(doctor, function (idx, staff) {
                                                                    if (staff.id == occupancy.bocPrimaryDoctor) {
                                                                        primaryDoctor2 = staff.staffName;
                                                                    }
                                                                });
//                                                if (parseInt($("#btmServicePointIndex").val()) == parseInt(bedMaster.btmGroupRid)) {
                                                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>' + patients.patMrnNo + '</td><td><a href="#" onclick="dcomethealth.Nursing.getVisitPatient(' + occupancy.bocPatRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')" class="text-primary">' + patients.patName + '</a></td><td>' + primaryDoctor2 + '</td><td>' + visitData.id + '</td><td>--</td><td>' + bedType + '</td><td><a href="#" id="drugTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Drug Order" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')"><i class="fa fa-medkit"></i></a>&nbsp;<a href="#" id="serviceTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Service Request" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')"><i class="fa  fa-pencil-square-o "></i></a>&nbsp;<a href="#" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Add Secondary Doctor" onclick="dcomethealth.Nursing.openModal(' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + occupancy.bocPrimaryDoctor + ',' + occupancy.bocBedRid + ',' + occupancy.bocRid + ')"><i class="fa  fa-user-md "></i></a>&nbsp;<a href="#" id="bedTransferTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Bed Transfer" onclick="dcomethealth.Nursing.viewBedTransfer(' + occupancy.bocRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',' + occupancy.bocPrimaryDoctor + ',\'' + occupancy.bocSecondaryDoctor + '\')"><i class="fa fa-exchange"></i></a></td></tr>');
//                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        } else if (parseInt(val) == 3) {
                            $('#nTbody').empty();
                            var primaryDoctor3 = '--';
                            if (parseInt(bedMaster.bedStatus) == 2) {
                                dcomethealth.PatientResource.searchVisit({"visTypeIndex": visitType}, function (data) {
                                    if (!!data) {
                                        $.each(data, function (ihx, visitData) {
                                            if (visitData.bedOccupancy != null) {
                                                $.each(visitData.bedOccupancy, function (Idx, occupancy) {
                                                    if (parseInt(occupancy.bocPatRid) == parseInt(visitData.visPatRid)) {
                                                        $.each(visitData.patient, function (igjx, patients) {
                                                            if (parseInt(bedMaster.bedRid) == parseInt(occupancy.bocBedRid)) {
                                                                $.each(dcomethealth.dDictVal, function (i, val) {
                                                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                                                        bedType = val.ddictValue;
                                                                        bedRoomType = val.id;
                                                                    }
                                                                });
                                                                $.each(doctor, function (idx, staff) {
                                                                    if (staff.id == occupancy.bocPrimaryDoctor) {
                                                                        primaryDoctor3 = staff.staffName;
                                                                    }
                                                                });

                                                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>' + patients.patMrnNo + '</td><td><a href="#" onclick="dcomethealth.Nursing.getVisitPatient(' + occupancy.bocPatRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')" class="text-primary">' + patients.patName + '</a></td><td>' + primaryDoctor3 + '</td><td>' + visitData.id + '</td><td>--</td><td>' + bedType + '</td><td><a href="#" id="drugTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Drug Order" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')"><i class="fa fa-medkit"></i></a>&nbsp;<a href="#" id="serviceTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Service Request" onclick="dcomethealth.Nursing.openTab(this,' + visitData.id + ',' + patients.id + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',\'' + primaryDoctor + '\')"><i class="fa  fa-pencil-square-o "></i></a>&nbsp;<a href="#" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Add Secondary Doctor" onclick="dcomethealth.Nursing.openModal(' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + occupancy.bocPrimaryDoctor + ',' + occupancy.bocBedRid + ',' + occupancy.bocRid + ')"><i class="fa  fa-user-md "></i></a>&nbsp;<a href="#" id="bedTransferTab" class="btn btn-aris btn-aris-sm jqueryUIToolTip" title="Bed Transfer" onclick="dcomethealth.Nursing.viewBedTransfer(' + occupancy.bocRid + ',' + bedMaster.bedRid + ',\'' + bedMaster.bedName + '\',' + occupancy.bocPatRid + ',' + occupancy.bocVisitRid + ',' + bedRoomType + ',' + bedMaster.bedBgmRid + ',' + occupancy.bocPrimaryDoctor + ',\'' + occupancy.bocSecondaryDoctor + '\')"><i class="fa fa-exchange"></i></a></td></tr>');
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        } else if (parseInt(val) == 4) {
                            if (parseInt(bedMaster.bedStatus) == 1) {
                                $.each(dcomethealth.dDictVal, function (i, val) {
                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                        bedType = val.ddictValue;
                                    }
                                });
//                        if (parseInt($("#btmServicePointIndex").val()) == parseInt(bedMaster.btmGroupRid)) {
                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>' + bedType + '</td><td>--</td></tr>');
//                        }
                            }
                        } else if (parseInt(val) == 5) {
                            if (parseInt(bedMaster.bedStatus) == 4) {
                                $.each(dcomethealth.dDictVal, function (i, val) {
                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                        bedType = val.ddictValue;
                                    }
                                });
//                        if (parseInt($("#btmServicePointIndex").val()) == parseInt(bedMaster.btmGroupRid)) {
                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>' + bedType + '</td><td>--</td></tr>');
//                        }
                            }
                        } else if (parseInt(val) == 6) {
                            if (parseInt(bedMaster.bedStatus) == 3) {
                                $.each(dcomethealth.dDictVal, function (i, val) {
                                    if (parseInt(bedGroupM.bgmBedPriceTypeIndex) == val.id) {
                                        bedType = val.ddictValue;
                                    }
                                });
//                        if (parseInt($("#btmServicePointIndex").val()) == parseInt(bedMaster.btmGroupRid)) {
                                $('#nTbody').append('<tr><td>' + bedMaster.bedName + '</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>' + bedType + '</td><td>--</td></tr>');
//                        }
                            }
                        }
                    });
                }
            });
        }
    }
    function searchCompleted(visRid, visPatRid) {
        $('#div3').hide();
        $('#soCompleteDiv').show();
        var visitDate = $("#activeVisitDate").val();
        if (!visRid && !visPatRid) {
            visRid = $('#activeVisitRid').val();
            visPatRid = $("#patRid").val();
            visitDate = $("#visDate").val();
        }
        var count = 1; //        var date = $('#tcDateRangeSpan').html().split('-'), count = 1, cnt = 1;
//        var searchParam = {"lrhFromDate": moment(date[0]), "lrhToDate": moment(date[1]).add(1, 'days'), "lrhPatientID": visPatRid};
        var tables = $.fn.dataTable.fnTables(true);
        $(tables).each(function () {
            $(this).dataTable().fnClearTable();
            $(this).dataTable().fnDestroy();
        });
        $("#treatTbody").empty();
//         var searchObj = {"soFromDate": moment(date[0]), "soToDate": moment(date[1]).add(1, 'days'), "soPatientRID": visPatRid};
        var searchParam = {"lrhPatientID": visPatRid}; //"lrhPatientVisitID": visRid,
        dcomethealth.LaboratoryResource.searchLabResults(searchParam, function (data) {
            if (!!data) {
                $.each(data, function (Idx, lab) {
                    $.each(lab.labResultDs, function (indx, labResultD) {
                        var labDate = moment(lab.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY');
//                        if (labDate == visitDate) {
                        count = count + indx;
                        var Print = '<a class="btn btn-success btn-sm hidden-xs" onclick="dcomethealth.Nursing.labPrint(' + lab.id + ')"><i class="fa fa-print"></i></a>';
                        $("#treatTbody").append('<tr><input type="hidden" id="labPatRid" value="' + lab.lrhPatientMrn + '"/>\n\
                            <td><output id="labServiceName">' + labResultD.lrdNServName + '</output></td>\n\
                            <td><output id="labServiceDate">' + labDate + '</output></td><td>' + Print + '</td></tr>');
                        $("#treatTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                        $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                        $('.dataTables_length select').addClass('form-control');
//                        }
                    });
                });
            }
        });
        var searchObj = {"soVisitRID": visRid, "soPatientRID": visPatRid};
        dcomethealth.ServiceOrderResource.searchServiceOrder(searchObj, function (data) {
            if (!!data) {
                $.each(data, function (Idx, sO) {
                    var soDate = moment(sO.createdDateTime, "DD-MM-YYYY HH:mm:ss").format('DD-MM-YYYY');
//                    if (soDate == visitDate) {
                    count = count + Idx;
                    $("#treatTbody").append('<tr><input type="hidden" id="soPatRid" value="' + sO.soPatientRID + '"/>\n\
                        <td><output id="serviceName">' + sO.soItemName + '</output></td>\n\
                        <td><output id="serDate">' + soDate + '</output></td><td>---</td></tr>');
                    $("#treatTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                    $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                    $('.dataTables_length select').addClass('form-control');
//                    }
                });
            }
            $("#tCom").text((count == 1 ? 1 : (count + 1)));
            count = 0;
        });
    }
    function getVisitPatient(visPatRid, bedRid, bedName, bgmBedPriceTypeIndex, bedBgmRid, patPrimaryDoctor) {
        $('#disBedRid').val(bedRid);
        $('#bedBgmRid').val(bedBgmRid);
//        $('#disBtmPrice').val(btmPrice);
        $('#disBtmRoomType').val(bgmBedPriceTypeIndex);
        $('#bedNumber').text(bedName);
        $('#docName').text(patPrimaryDoctor);
        $('#dischargeDateTime').text(moment().format('DD-MM-YYYY hh:mm'));
        var disDT = moment($('#dischargeDateTime').text(), 'DD-MM-YYYY hh:mm').format("DD-MM-YYYY HH:mm:ss");
        $('#disDateTime').val(disDT);
        $('#dischargedBy').text(dcomethealth.loginuser.userFullName);
        $('#disUserRid').val(dcomethealth.loginuser.id);
        var searchParams = {"id": visPatRid};
        dcomethealth.PatientResource.searchPatient(searchParams, function (data) {
            $.each(data, function (idx, patient) {
                $("#patRid").val(patient.id);
                $("#patName").text(patient.patFullName);
                $("#patMRN").val(patient.patMrnNo);
                $("#patAge").val(patient.patAge);
                $("#patDemoInfo").text("(Gender: " + patient.patGender + ", Age: " + patient.patAge + ")");
            });
            loadPatientVisitTab(visPatRid);
        });
    }
    function loadPatientVisitTab(visPatRid) {
        $("#backPanel").removeClass('hidden');
        $("#frontPanel").addClass('hidden');
//        if (!!$("#patRidSearch").val()) {
//            $('#saveComplaints ,#saveHistory ,#saveVitals ,#saveDiagnosis ,#saveClinical ,#saveService ,#saveRxOrder ,#saveNursing ,#saveAdmtPatient ,#saveFiles').hide();
//        } else {
//            $('#saveComplaints ,#saveHistory ,#saveVitals ,#saveDiagnosis ,#saveClinical ,#saveService ,#saveRxOrder ,#saveNursing ,#saveAdmtPatient ,#saveFiles').show();
//        }
//        var totalLen = 0;
        var search = {"visPatRid": visPatRid, "sortOrder": ["createdDateTime"], "sortDesc": "desc"};
        dcomethealth.PatientResource.searchVisit(search, function (visits) {
            if (!!visits) {
                $("#patientVisitTab").empty();
//                totalLen = visits.length;
                $.each(visits, function (pIdx, visit) {
                    $('#activeVisitRid').val(visit.id);
                    $('#activeSpecialityIndex').val(visit.visSpecialityIndex);
                    $('#patientVisitTab').append('<li onclick="dcomethealth.Nursing.loadPatientVisit(this,' + visit.id + ',' + visit.visPatRid + ',' + visit.visSpecialityIndex + ')"><a href="#visit1" data-toggle="tab">' + moment(visit.createdDateTime, 'DD-MM-YYYY HH:mm:ss').format('DD-MM-YYYY') + '</a><input id="visDate" name="visDate" class="" type="hidden" value="' + visit.visDate + '"></li>'); //<input id="visSpecialityIndex" name="visSpecialityIndex" class="" type="hidden" value="' + (!!visit.visSpecialityIndex ? visit.visSpecialityIndex : 0) + '">
                });
//                if (totalLen >= 10) { // For go to next,previous visit 
//                    $('#patientVisitTab li:nth-child(1)').append('<li style="margin-top:-7px;"><a href="#" id="prev" onclick="movePage()"><i class="fa fa-arrow-left"></i></a></li>');
//                    $('#patientVisitTab').last().append('<li><a href="#" id="next" onclick="movePage()"><i class="fa fa-arrow-right"></i></a></li>');
//                }
            } else {
                $('#activeVisitRid').val(''); //New Appointments visitRid is Empty
            }
        });
    }
    function loadPatientVisit(e, visitRid, visPatRid, visitSpecialityIndx) {
        $("#activeVisitDate").val($(e).find("input[name='visDate']").val());
        $('#activeVisitRid').val(visitRid);
        $('#activeSpecialityIndex').val(visitSpecialityIndx);
        getVisitVitals(visitRid);
        getService(visitRid);
        getrxOrder(visitRid);
        getDisSummary(visitRid, visPatRid);
//        searchCompleted(visitRid, visPatRid);
    }
    function viewEditor() {
        if ($('#consultantCheck,#investCheck,#diagnosisCheck,#opCheck,#peCheck,#icuCheck,#tgCheck,#stayCheck,#disTypeCheck,#chCheck,#adviceCheck,#folowUpCheck,#medCheck,#surgeonCheck,#opPerformCheck,#cnCheck,#opNotesCheck,#genExamCheck').is(':checked')) {
            $("#checkBoxDiv").addClass("hidden");
            $("#disBack,#saveDisTemp,#viewPrint,#saveFinalize").removeClass("hidden");
            $("#disSummaryDiv").find("div[class$='Div'],div[class*='Div ']").each(function (ix, div) { // get className ends with Div 
                if ($(div).hasClass("hidden")) { //check className is hidden
                    $(div).empty();
                }
            });
            $("#patntName").text($("#patName").text());
//            $("#patntName").attr('readOnly',true);
//            $("#patntName").contentEditable = false;
            $("#patntName").attr('contentEditable', false);
//            CKEDITOR.inline($("#patntName").attr('contenteditable', false));
//            $("#patntName").css("pointer-events", "none");
            $("#patntAge").text($("#patAge").val());
            $("#dctrName").text($('#docName').text());
            CKEDITOR.instances.disEditor.setData($("#disSummaryDiv").html());
            $("#dischargeEditor").removeClass("hidden");
        } else {
            alert("Need Any One Details");
            return false;
        }
    }
    function viewPrint() {
        var data = CKEDITOR.instances.disEditor.getData();
        if (!!data) {
            var printWindow = window.open();
            printWindow.document.write(data);
//                printWindow.onload = function () { // necessary if the div contain images//           
            setTimeout(function () {
                printWindow.document.close();
                printWindow.focus();
                printWindow.print();
                printWindow.close();
            }, 300);
        }
    }
    function viewBedTransfer(bocRid, btmRid, bedName, bedPatRid, bedVisitRid, bedType, bedBgmRid, primaryDoctor, secdryDoctor) {
        $("#existBocRid").val(bocRid);
        $("#existBtmRid").val(btmRid);
        $("#existBtmName").val(bedName);
        $("#btmName").text(bedName);
        $("#existBtmPatRid").val(bedPatRid);
        $("#existBtmVisitRid").val(bedVisitRid);
        $("#existBedType").val(bedType);
        $("#existBedBgmRID").val(bedBgmRid);
        $("#bocPrimaryDoctor").val(primaryDoctor);
        if (!!secdryDoctor && secdryDoctor != 'undefined') {
            $("#bocSecondaryDoctor").val(secdryDoctor);
        }
        $("#bedTransferDiv").removeClass('hidden');
        $('#bedDiv,#houseKeepingDiv,#maintenanceDiv,#viewDischarge,#dischargeDiv').addClass('hidden');
        $(".servicePointDiv,.servicePointGroupDiv,.servicePointSubGroupDiv").addClass("hidden");
    }
    function viewBedDetails(btmRid, bedName, bedBgmRID) {
        $("#bedModal").modal('hide');
        $('#selectedBedDiv').removeClass("hidden");
        $('#bedName').text(bedName);
        $("#bedBgmRID").val(bedBgmRID);
        $('#newBedName').text(bedName);
        $('#btmCharge').text(bedBgmRID);
        $('#newBedRid').val(btmRid);
    }
    function labPrint(val) {
        dcomethealth.LaboratoryResource.getLabPrint({"id": val}, function (data) {
            if (!!data) {
                exportExcel(data);
            }
        });
    }
    function getVisitVitals(val) {
        var search = {"vitVisRID": val};
        dcomethealth.ClinicalResource.searchVisitVitals(search, function (data) {
            if (!!data) {
                $("#height,#weight,#blood,#heart,#respiratory,#bodyTemp").removeClass("hidden");
                $.each(data, function (index, visitVital) {
                    var obj = jQuery.parseJSON(visitVital.vitValue);
                    $("#visitVitalId").val(visitVital.id);
                    $("#od_vital_height").val(!!obj.od_vital_height ? obj.od_vital_height : '');
                    $("#od_vital_height_unit").val(!!obj.od_vital_height_unit ? obj.od_vital_height_unit : '');
                    $("#od_vital_weight").val(!!obj.od_vital_weight ? obj.od_vital_weight : '');
                    $("#od_vital_weight_unit").val(!!obj.od_vital_weight_unit ? obj.od_vital_weight_unit : '');
                    $("#od_vital_blood_pressure").val(!!obj.od_vital_blood_pressure ? obj.od_vital_blood_pressure : '');
                    $("#od_vital_blood_pressure_unit").val(!!obj.od_vital_blood_pressure_unit ? obj.od_vital_blood_pressure_unit : '');
                    $("#od_vital_heart_rate").val(!!obj.od_vital_heart_rate ? obj.od_vital_heart_rate : '');
                    $("#od_vital_heart_rate_unit").val(!!obj.od_vital_heart_rate_unit ? obj.od_vital_heart_rate_unit : '');
                    $("#od_vital_resporatory_rate").val(!!obj.od_vital_resporatory_rate ? obj.od_vital_resporatory_rate : '');
                    $("#od_vital_body_temp").val(!!obj.od_vital_body_temp ? obj.od_vital_body_temp : '');
                    $("#od_vital_body_temp_unit").val(!!obj.od_vital_body_temp_unit ? obj.od_vital_body_temp_unit : '');
                    $("#od_vital_bmi").val(!!obj.od_vital_bmi ? obj.od_vital_bmi : '');
                    $("#od_vital_bmi_unit").val(!!obj.od_vital_bmi_unit ? obj.od_vital_bmi_unit : '');
                });
            } else {
                $("#height,#weight,#blood,#heart,#respiratory,#bodyTemp").removeClass("hidden");
                $("#visitVitalId,#od_vital_height,#od_vital_weight,#od_vital_blood_pressure,#od_vital_heart_rate,#od_vital_resporatory_rate,#od_vital_body_temp,#od_vital_bmi").val('');
            }
        });
    }
    function  getrxOrder(visitId) {
        var searchobj = {"matVisitRid": visitId};
        dcomethealth.ClinicalResource.getMaterialIssue(searchobj, function (data) {
            $("#rxOrderTbody").empty();
            if (!!data) {
                $.each(data, function (igx, matIssueH) {
                    $("#matIssueRid").val(matIssueH.id);
                    $.each(matIssueH.materialIssueD, function (igs, matIssueD) {//<span><small><a href="#">add Notes</a></small></span>
                        $("#rxOrderTbody").append('<tr id="rxOrders' + igs + '"><td width="5%"><i id="minus" class="ace-icon fa fa-minus hidden" onclick="delete_rxrow(this)"></i></td>\n\
                        <td><input type="text" id="matItemName" name="matItemName" value="' + matIssueD.matItemName + '" class="col-md-12"/><input type="hidden" id="matSkuRid" value="' + matIssueD.matSkuRid + '" name="matSkuRid"/><input type="hidden" id="matIssueHRid" value="' + matIssueD.matIssueHRid + '" name="matIssueHRid"/><input type="hidden" id="matIssueDRid" value="' + matIssueD.id + '" name="matIssueDRid"/><br></td>\n\
                        <td><input type="text" id="strength" class="col-md-12"/></td><td><select id="strengthSelect" class="col-md-12"><option>mg</option></select></td>\n\
                        <td><input type="text" id="matOrderQty" value="' + matIssueD.matOrderQty + '" class="col-md-9"/></td>\n\
                        <td><i class="row pull-left ace-icon fa fa-plus red" onclick="insert_rxrow(\'rxOrderTable\', this)"></i></td></tr>');
                        dcomethealth.datatypes.init($("#rxOrders" + igs));
                    });
                });
            }
            else {//<span><small><a href="#">add Notes</a></small></span>
                $("#matIssueRid").val('');
                $("#rxOrderTbody").append('<tr id="rxOrders"><td width="5%"><i id="minus" class="ace-icon fa fa-minus hidden" onclick="delete_rxrow(this)"></i></td>\n\
                <td><input type="text" id="matItemName" name="matItemName" value="" class="col-md-12"/><input type="hidden" id="matSkuRid" value="" name="matSkuRid"/><input type="hidden" id="matIssueHRid" value="" name="matIssueHRid"/><input type="hidden" id="matIssueDRid" value="" name="matIssueDRid"/><br></td>\n\
                <td><input type="text" id="strength" class="col-md-12"/></td><td><select id="strengthSelect" class="col-md-12"><option>mg</option></select></td>\n\
                <td><input type="text" id="matOrderQty" value="" class="col-md-6"/></td>\n\
                <td><i class="row pull-left ace-icon fa fa-plus red" onclick="insert_rxrow(\'rxOrderTable\', this)"></i></td></tr>');
                dcomethealth.datatypes.init($("#rxOrderTbody"));
                autoCompleteDrug();
            }
        });
    }
    function getService(id) {
        var searchobj = {"serReqOpVisitRid": id};
        dcomethealth.ServiceRequestResource.searchServiceRequest(searchobj, function (data) {
            $("#serviceOrderTbody").empty();
            if (!!data) {
                $("#serviceSave").hide();
                $.each(data, function (idx, serviceH) {
                    $.each(serviceH.serviceRequest, function (ifdx, serviceval) {//<span><small><a href="#">show Teeth</a></small></span>    <td><span id="addInstructions"><small><a href="#">add Instructions</a></small></span></td>                        
                        $("#serviceOrderTbody").append('<tr><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="delete_servicerow(this)"></i></td>\n\
                                    <td><input type="text" id="serviceName" name="serviceName" value="' + serviceval.serReqItemName + '" class="col-md-12"/><input type="hidden" id="itemRID" value="' + serviceval.serReqItemRID + '"/><input type="hidden" id="serviceReqDId" value="' + serviceval.serReqRid + '"/><br></td>\n\
                                    <td><input type="text" id="qtyService" value="' + serviceval.serReqItemQty + '" class="col-md-12"/></td>\n\
                                    <td><input type="text" id="processServiceOn" value="' + moment(serviceval.createdDateTime).format('DD-MM-YYYY') + '" class="col-md-12"/></td>\n\
                                    \n\
                                    <td><i class="ace-icon fa fa-plus red" onclick="insert_servicerow(\'serviceOrderTable\', this)"></i></td></tr>');
                    });
                });
            }
            else {//<span><small><a href="#">show Teeth</a></small></span>  <td><span id="addInstructions"><small><a href="#">add Instructions</a></small></span></td>                
                $("#serviceOrderTbody").append('<tr><td><i id="minus" class="ace-icon fa fa-minus hidden" onclick="delete_servicerow(this)"></i></td>\n\
                                    <td><input type="text" id="serviceName" name="serviceName" value="" class="col-md-12"/><input type="hidden" id="itemRID" value=""/><input type="hidden" id="serviceReqDId" /><br></td>\n\
                                    <td><input type="text" id="qtyService" value="" class="col-md-12"/></td>\n\
                                    <td><input type="text" id="processServiceOn" value="" class="col-md-12"/></td>\n\
//                                  \n\
                                    <td><i class="ace-icon fa fa-plus red" onclick="insert_servicerow(\'serviceOrderTable\', this)"></i></td></tr>');
                autocompleteService();
            }
        });
    }
    function getDisSummary(visitRid, visitPatRid) {
        $("#checkBoxDiv").addClass("hidden");//,#patientVisitTab
        $("#disBack,#saveDisTemp,#viewPrint,#saveFinalize").removeClass("hidden");
        $('#dischargeEditor').removeClass('hidden');
        $('#disPatId').val(visitPatRid);
        CKEDITOR.instances.disEditor.setData('');
        var searchObj = {"disVisitRid": visitRid};
        dcomethealth.BedManagementResource.searchDischargeDetails(searchObj, function (data) {
            if (!!data) {
                $.each(data, function (ix, discharge) {
                    $('#disId').val(discharge.id);
                    CKEDITOR.instances.disEditor.setData(discharge.disNodes); ////set HTML Data to CKEditor
                });
            }
        });
    }
    function addSecondaryDoctor() {
        $('#doctorModal').modal('hide');
        var bedOccupancy = {}, staffRid = [], addQuotes = "", secDoctrArray = [], doc = [], secDoctrArray2 = [], arrayString = [];
        if (!!parseInt($('#bocRid').val())) {
            bedOccupancy.bocRid = parseInt($('#bocRid').val());
        }
        bedOccupancy.bocPatRid = !!parseInt($("#patientRid").val()) ? parseInt($("#patientRid").val()) : null;
        bedOccupancy.bocVisitRid = parseInt($('#patientVisitRid').val());
        bedOccupancy.bocBedRid = parseInt($('#patBedRid').val());
        bedOccupancy.bocPrimaryDoctor = parseInt($('#patPrimaryDoctor').val());
        $("#s2id_doctorName").find(".select2-choices").find(".select2-search-choice").find("div").each(function (indx, s2Div) {
            var s2Text = $(s2Div).text();
            if (!!s2Text) {
                $.each(doctor, function (idx, staff) {
                    if (staff.staffName == s2Text) {
                        var optionValue = $(s2Div).find("input[type='hidden']").val();
                        staffRid.push(optionValue);
                    }
                });
            }
        });
        $.each(staffRid, function (didx, docRid) {
            addQuotes = (didx !== 0) ? addQuotes + "," + docRid : docRid;
        });
        secDoctrArray = JSON.parse("[" + secArray + "]");
        secDoctrArray2 = JSON.parse("[" + addQuotes + "]");
        var arrayStr = secDoctrArray2 + "," + secDoctrArray;
        var arrayString = arrayStr.split(',');
        $.each(arrayString, function (i) {
            if (!!arrayString[i]) {
                if ($.inArray(arrayString[i], doc) == -1) {
                    doc.push(arrayString[i]);
                }
            }
        });
        if (!!doc) {
            $.each(doc, function (didx, docRid) {
                addQuotes = (didx !== 0) ? addQuotes + "," + docRid : docRid;
            });
        }
        if (addQuotes !== "") {
            bedOccupancy.bocSecondaryDoctor = addQuotes;
        }
        secArray = [];
        dcomethealth.BedManagementResource.saveBedOccupancy(bedOccupancy).done(function (data, textStatus, jqXHR) {
            alert("Saved");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function submit() {
        var visitVitals = {}, vitalsInfo = {};
        visitVitals.id = !!$("#visitVitalId").val() ? $("#visitVitalId").val() : null;
        visitVitals.vitPatRID = parseInt($("#patRid").val());
        visitVitals.vitName = $("#patName").text();
        visitVitals.vitVisRID = $("#activeVisitRid").val();
        vitalsInfo.od_vital_height = $("#od_vital_height").val();
        vitalsInfo.od_vital_height_unit = $("#od_vital_height_unit").val();
        vitalsInfo.od_vital_weight = $("#od_vital_weight").val();
        vitalsInfo.od_vital_weight_unit = $("#od_vital_weight_unit").val();
        vitalsInfo.od_vital_blood_pressure = $("#od_vital_blood_pressure").val();
        vitalsInfo.od_vital_blood_pressure_unit = $("#od_vital_blood_pressure_unit").val();
        vitalsInfo.od_vital_heart_rate = $("#od_vital_heart_rate").val();
        vitalsInfo.od_vital_heart_rate_unit = $("#od_vital_heart_rate_unit").val();
        vitalsInfo.od_vital_resporatory_rate = $("#od_vital_resporatory_rate").val();
        vitalsInfo.od_vital_resporatory_rate_unit = $("#od_vital_resporatory_rate_unit").val();
        vitalsInfo.od_vital_body_temp = $("#od_vital_body_temp").val();
        vitalsInfo.od_vital_body_temp_unit = $("#od_vital_body_temp_unit").val();
        visitVitals.vitValue = JSON.stringify(vitalsInfo);
        dcomethealth.ClinicalResource.saveVisitVitals(visitVitals).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('Nursing');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function submitRxOrder() {
        var matIssueH = {};
        var matIssueDList = [];
        matIssueH.id = !!$("#matIssueRid").val() ? $("#matIssueRid").val() : null;
        matIssueH.matVisitRid = parseInt($("#activeVisitRid").val());
        matIssueH.matPatRid = parseInt($("#patRid").val());
        matIssueH.matPatName = $("#patName").text();
        var table = document.getElementById('rxOrderTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var matIssueD = {};
            matIssueD.id = !!dynTableGetNodeInRow(table.rows[i + 1], 'matIssueDRid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'matIssueDRid').value : null;
            matIssueD.matIssueHRid = !!dynTableGetNodeInRow(table.rows[i + 1], 'matIssueHRid').value ? dynTableGetNodeInRow(table.rows[i + 1], 'matIssueHRid').value : null;
            matIssueD.matSkuRid = dynTableGetNodeInRow(table.rows[i + 1], 'matSkuRid').value;
            matIssueD.matItemName = dynTableGetNodeInRow(table.rows[i + 1], 'matItemName').value;
            matIssueD.matOrderQty = dynTableGetNodeInRow(table.rows[i + 1], 'matOrderQty').value;
            matIssueDList.push(matIssueD);
        }
        matIssueH.materialIssueD = matIssueDList;
        var args = 0 + "/" + "DRUG_ORDER_REQUEST" + "/" + "REQUEST";
        dcomethealth.ClinicalResource.saveMaterialIssue(matIssueH, args).done(function (data, textStatus, jqXHR) {
            alert("Data Saved");
            dcomethealth.util.loadpage('Nursing');
        }).fail(function (jqXHR, textStatus, Patients) {
            alert("Failed to save data");
        });
    }
    function submitService() {
        var serviceH = {};
        serviceH.serReqhId = !!$("#serviceId").val() ? $("#serviceId").val() : null;
        serviceH.serReqOpVisitRid = $("#activeVisitRid").val();
        serviceH.serReqhPatMrn = $("#patMRN").val();
        serviceH.serReqhPatName = $("#patName").text();
        serviceH.serReqhPatRid = parseInt($("#patRid").val());
        var serviceList = [];
        var table = document.getElementById('serviceOrderTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var serviceD = {};
            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value && dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value != "undefined") {
                serviceD.serReqRid = dynTableGetNodeInRow(table.rows[i + 1], 'serviceReqDId').value;
            }
            serviceD.serReqItemRID = dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value;
            serviceD.serReqItemName = dynTableGetNodeInRow(table.rows[i + 1], 'serviceName').value;
            serviceD.serReqItemQty = dynTableGetNodeInRow(table.rows[i + 1], 'qtyService').value;
//            serviceD.serReqProcessDate = dynTableGetNodeInRow(table.rows[i + 1], 'processServiceOn').value;
            serviceList.push(serviceD);
        }
        serviceH.serviceRequest = serviceList;
        var args = 0 + "/" + "SERVICE_ORDER_REQ" + "/" + "REQUEST";
        dcomethealth.ServiceRequestResource.saveService(serviceH, args).done(function (data, textStatus, jqXHR) {
            alert("Data Saved");
            dcomethealth.util.loadpage('Nursing');
        }).fail(function (jqXHR, textStatus, Patients) {
            alert("failed to save data");
        });
    }
    function saveDischarge() {
        if (parseInt($('#disType').val()) != 0) {
            if ($('#disAccept').is(':checked')) {
                var discharge = {};
                if ($('#disRid').val() != "") {
                    discharge.id = $('#disRid').val();
                }
                discharge.disVisitRid = parseInt($('#activeVisitRid').val());
                discharge.disPatRid = parseInt($("#patRid").val());
                discharge.disPatName = $("#patName").text();
                discharge.disNotes = $('#disNotes').val();
                discharge.disDateTime = $('#disDateTime').val();
                discharge.disFollowUpDate = $('#followUpDate').val();
                discharge.disType = parseInt($('#disType').val());
                discharge.dischargeBy = parseInt($('#disUserRid').val());
                discharge.disDeclaration = ($('#disAccept').is(':checked')) ? 1 : 0;
                discharge.disSms = ($('#disAcceptSms').is(':checked')) ? 1 : 0;
//            discharge.disNodes = CKEDITOR.instances.disEditor.getData(); //get HTML Data from CKEditor
                dcomethealth.BedManagementResource.saveDischarge(discharge).done(function (data, textStatus, jqXHR) {
                    submitBedMaster(parseInt($('#disBedRid').val()), 'Discharge');
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
                var visit = {};
                if ($('#activeVisitRid').val() !== "") {
                    visit.id = parseInt($('#activeVisitRid').val());
                }
                visit.visTypeIndex = 21;//For Discharge 
                visit.visSubTypeIndex = 0; // Need to change the value;
                visit.visPatType = 0; // Need to check and change it.
                visit.visPatRid = parseInt($("#patRid").val()); // Need to check and change it.
                dcomethealth.PatientResource.saveVisit(visit).done(function (data, textStatus, jqXHR) {
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("Failed");
                });
            } else {
                alert("Accept Declaration");
                return false;
            }
        } else {
            alert("Select Anyone Discharge Type");
            return false;
        }
    }
    function saveDischargeSummary() {
        var discharge = {};
        if ($('#disId').val() != "") {
            discharge.id = $('#disId').val();
        }
        discharge.disVisitRid = parseInt($("#activeVisitRid").val());
        discharge.disPatRid = parseInt($('#disPatId').val());
        discharge.disPatName = $('#patName').text();
        discharge.disNodes = CKEDITOR.instances.disEditor.getData(); //get HTML Data from CKEditor
//        discharge.disNotes = $('#disNotes').val();
//        discharge.disDateTime = $('#disDateTime').val();
//        discharge.disFollowUpDate = $('#followUpDate').val();
//        discharge.disType = parseInt($('#disType').val());
//        discharge.dischargeBy = parseInt($('#disUserRid').val());
//        discharge.disDeclaration = ($('#disAccept').is(':checked')) ? 1 : 0;
//        discharge.disSms = ($('#disAcceptSms').is(':checked')) ? 1 : 0;
        dcomethealth.BedManagementResource.saveDischarge(discharge).done(function (data, textStatus, jqXHR) {
            alert("Saved");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function submitBedMaster(value, tableName) {
        var bedMaster = {}, search = null;
        if (tableName == 'Discharge') {
            bedMaster.bedRid = parseInt(value);
            bedMaster.bedBgmRid = $('#bedBgmRid').val();
//        bed.bedName = $("#bgmBedGroupName").text();
//        bed.bedState = 1;
//        bed.bedStatus = 1;
            bedMaster.bedUnitRid = $('#unitName').val();
//        bed.btmPrice = parseFloat($("#sBedCharge").text())

            bedMaster.bedName = $('#bedNumber').text();
            bedMaster.bedIsActive = 1;
//        bed.btmActiveYesno = 1;
            if (parseInt($('#disBedStatus option:selected').val()) == 2) {
                search = value + "/" + "BED_DETAILS" + "/" + "MAINTENANCE";
            } else {
                search = value + "/" + "BED_DETAILS" + "/" + "HK";
            }
//            var search = parseInt(value) + "/" + "BED_DETAILS" + "/" + "OCCUPY";
//            dcomethealth.BedManagementResource.saveBedMas(bed, search).done(function (data, textStatus, jqXHR) {
////            alert("Saved");
////            dcomethealth.util.loadpage('BedMaster');
//                dcomethealth.util.base_init();
//                dcomethealth.util.loadNotification();
//            }).fail(function (jqXHR, textStatus, errorThrown) {
//                alert("Failed");
//            });
        } else if (tableName == 'BedTransfer') {

            //            bedMaster.bedRid = parseInt(value);
//              var bed = {};
            bedMaster.bedRid = parseInt(value);
            bedMaster.bedBgmRid = $("#existBedBgmRID").val();
//        bed.bedName = $("#bgmBedGroupName").text();
//        bed.bedState = 1;
//        bed.bedStatus = 1;
//            bedMaster.bedUnitRid = ;
            bedMaster.bedIsActive = 1;
            bedMaster.bedName = $('#btmName').text();
            bedMaster.bedNotes = $('#notesValues').val();
            search = parseInt(value) + "/" + "BED_DETAILS" + "/" + "HK";
        } else {
            var table = document.getElementById(tableName);
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                if (dynTableGetNodeInRow(table.rows[i + 1], 'bedid').value == parseInt(value)) {
                    bedMaster.bedRid = dynTableGetNodeInRow(table.rows[i + 1], 'bedid').value;
                    bedMaster.bedBgmRid = dynTableGetNodeInRow(table.rows[i + 1], 'bedGrMrid').value;
                    bedMaster.bedName = dynTableGetNodeInRow(table.rows[i + 1], 'bedName').innerHTML;
//                    bedMaster.bgmBedPriceTypeIndex = dynTableGetNodeInRow(table.rows[i + 1], 'bedRoomType').value;
//                    bedMaster.btmGroupRid = dynTableGetNodeInRow(table.rows[i + 1], 'bedPriceType').value;
                    bedMaster.bedIsActive = 1;
                    bedMaster.bedNotes = dynTableGetNodeInRow(table.rows[i + 1], 'notesValues').value;
                }
                if (dynTableGetNodeInRow(table.rows[i + 1], 'hkStatus').value == 2) {
                    search = value + "/" + "BED_DETAILS" + "/" + "MAINTENANCE";
                } else if (dynTableGetNodeInRow(table.rows[i + 1], 'hkStatus').value == 3) {
                    search = value + "/" + "BED_DETAILS" + "/" + "READY";
                } else if (dynTableGetNodeInRow(table.rows[i + 1], 'hkStatus').value == 1) {
                    search = value + "/" + "BED_DETAILS" + "/" + "HK";
                }
            }
        }
        dcomethealth.BedManagementResource.saveBedMas(bedMaster, search).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('Nursing');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveBedOccupancy(bedRid) {
        var bedOccupy = {}
        bedOccupy.bocRid = parseInt($("#existBocRid").val());
        bedOccupy.bocPatRid = parseInt($("#existBtmPatRid").val());
        bedOccupy.bocVisitRid = parseInt($("#existBtmVisitRid").val());
        bedOccupy.bocBedRid = bedRid;
        bedOccupy.bocPrimaryDoctor = parseInt($("#bocPrimaryDoctor").val());
        bedOccupy.bocSecondaryDoctor = $("#bocSecondaryDoctor").val();
        dcomethealth.BedManagementResource.saveBedOccupancy(bedOccupy).done(function (data, textStatus, jqXHR) {
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveBedTransfer() {
        var bedMaster = {};
        bedMaster.bedRid = parseInt($("#newBedRid").val());
        bedMaster.bedBgmRid = parseInt($("#bedBgmRID").val());
        bedMaster.bedName = $("#newBedName").text();
        bedMaster.bedNotes = $('#notesValues').val();
        bedMaster.bedIsActive = 1;
        var search = parseInt($("#newBedRid").val()) + "/" + "BED_DETAILS" + "/" + "OCCUPY";
        dcomethealth.BedManagementResource.saveBedMas(bedMaster, search).done(function (data, textStatus, jqXHR) {
            submitBedMaster(parseInt($("#existBtmRid").val()), 'BedTransfer');
            saveBedOccupancy(data.bedRid);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function exportExcel(html) {
        var printWindow = window.open('', '_blank');
        printWindow.document.write(html);
        printWindow.document.close();
        printWindow.focus();
        printWindow.print();
        printWindow.close();
        dcomethealth.util.loadpage('Nursing');
        dcomethealth.util.base_init();
    }
    function refreshData() {
    }
    return {
        init: init,
        isChecked: isChecked,
        openTab: openTab,
        openModal: openModal,
        trigger: trigger,
        autocompleteService: autocompleteService,
        autoCompleteDrug: autoCompleteDrug,
        houseKeeping: houseKeeping,
        maintainenceSearch: maintainenceSearch,
        nursingSearch: nursingSearch,
        searchCompleted: searchCompleted,
        getVisitPatient: getVisitPatient,
        loadPatientVisitTab: loadPatientVisitTab,
        loadPatientVisit: loadPatientVisit,
        fetchBedDetails: fetchBedDetails,
        viewEditor: viewEditor,
        viewPrint: viewPrint,
        viewBedTransfer: viewBedTransfer,
        viewBedDetails: viewBedDetails,
        labPrint: labPrint,
        getVisitVitals: getVisitVitals,
        getrxOrder: getrxOrder,
        getService: getService,
        getDisSummary: getDisSummary,
        addSecondaryDoctor: addSecondaryDoctor,
        submit: submit,
        submitRxOrder: submitRxOrder,
        submitService: submitService,
        saveDischarge: saveDischarge,
        saveDischargeSummary: saveDischargeSummary,
        submitBedMaster: submitBedMaster,
        saveBedOccupancy: saveBedOccupancy,
        saveBedTransfer: saveBedTransfer,
        exportExcel: exportExcel,
        refreshData: refreshData
    };
}());
dcomethealth.Nursing.init();