var dcomethealth = dcomethealth || {};
dcomethealth.DataDictionaryMaster = (function () {
    var id = "DataDictionaryMaster", dDictTypeObj = {}, entityDdictObj = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            loadDdictTypeGrid();
            $('.dDictStatus').toggles({on: true}, "isValidDdict", 1);
            $("#dDictCode").select2({placeholder: 'Select Ddict Code', width: 'resolve'});
            $("#dDictCode").empty();
            if (!!dcomethealth.sDdict) {
                $("#dDictCode").append("<option value=''></option>");
                $.each(dcomethealth.sDdict, function (pIdx, dDictType) {
                    $("#dDictCode").append("<option value='" + dDictType.dditTypeIndex + "'>" + dDictType.dditCode + "</option>");
                });
            }

            dcomethealth.DataDictionaryResource.getEntities(function (data) {
                $.each(data, function (pIdx, ent) {
                    if (ent.id != 1) {
                        $("#entityName").append("<option value='" + ent.id + "'>" + ent.entityName + "</option>");
                    }
                });
            });
            $("#dDictCode").change(function () {
                assignDdictValues($(this).val(), $("option:selected", this).text());
            });
            $("#entityName").on("change", function () {
                if ($(this).val() != 0 && !!$(this).val()) {
//                    dcomethealth.DataDictionaryResource.searchDdictValueByEntityMap({"ddictMapEntityRid": $(this).val()}, function (data) {
//                        entityDdictObj = data;
//                        getEntityWiseDdict($(this).val());
//                    });
                }
            });
            $("#dDictCode").on("change", function () {
//                assignDdictValues($(this).val(), $(this).find('option:selected').text());
            });
            $("#saveDdictType").off("click").on("click", function () {
                if ($("#dditCode").val() == "") {
                    alert("Ddict Code Name is empty");
                    return false;
                } else {
                    saveDdictType();
                }
            });
            $("#saveDdict").off("click").on("click", function () {
                if ($("#dDictCode").val() === "") {
                    alert("Code Name is empty");
                    return false;
                }
                if ($("#entityName").val() === "" || $("#entityName").val() == 0) {
                    alert("Select Entity Name");
                    return false;
                }
                if (parseInt($("#ms-multi-select").find(".ms-selection").find("li.ms-selected").find("span").length) === 0) {
                    alert("Option is empty");
                    return false;
                }
                if ($('#dditTypeIndex').val() === "") {
                    dcomethealth.Roles.getRoles();
                } else {
                    saveDdict();
                }
            });
        });
    }
    function getDdictValues(dDictTypeIndex) {
        $("#DataDictionaryMasterHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#addDdictDiv").removeClass("hidden").addClass("panel panel-primary");
        if (!!dDictTypeObj) {
            $("#dynTbody").empty();
            $.each(dDictTypeObj, function (inx, dDictTypeValue) {
                if (dDictTypeValue.dditTypeIndex == dDictTypeIndex) {
                    $("#dDictTypeRid").val(dDictTypeValue.dditTypeIndex);
                    $("#dditCode").val(dDictTypeValue.dditCode);
                    $.each(dDictTypeValue.ddict, function (inx, dDict) {
                        $("#ddictParentIndex").val(dDict.ddictParentIndex);
                        (dDict.ddictValid == 1) ? $('.dDictStatus').toggles({on: true}, "isValidDdict", 1) : $('.dDictStatus').toggles({on: false}, "isValidDdict", 0);
                        $("#dynTbody").append('<tr><td width="2%"><i id="del" class="ace-icon fa fa-minus hidden" onclick="dcomethealth.DataDictionaryMaster.deleteRow(this)"></i></td><td width="97%"><input id="ddictValue" type="text" name="ddictValue" onChange="" value="' + dDict.ddictValue + '" class="col-md-12 col-sm-12 col-xs-12 form-control" maxlength="30"/><input type="hidden" id="dDictRid" name="dDictRid" value="' + dDict.id + '"/></td><td width="1%"><i class="ace-icon fa fa-plus red" onclick="dcomethealth.DataDictionaryMaster.insertRow(this)"></i></td></tr>');
                    });
                }
            });
        }
    }
    function sortAscending(data_A, data_B) //For Sorting in Ascending Order Edited by Faris
    {
        return (data_A - data_B);
    }
    function loadDdictTypeGrid() {
        dcomethealth.DataDictionaryResource.getDdicts(function (data) {
            $("#dDictTbody").empty();
            var tables = $.fn.dataTable.fnTables(true);
            $(tables).each(function () {
                $(this).dataTable().fnClearTable();
                $(this).dataTable().fnDestroy();
            });
            if (!!data) {
                data.sort(sortAscending);   //for sort values             
                $.each(dDictTypeObj = data, function (index, dDictType) {
//                    $.each(dDictType.ddict, function (index, dDictValue) {
//                        var dDictValid = (dDictValue.ddictValid == 1) ? "Yes" : "No";
                    $("#dDictTbody").append('<tr onclick="dcomethealth.DataDictionaryMaster.getDdictValues(' + dDictType.dditTypeIndex + ')" id="tr_head"><td>' + dDictType.dditCode + '</td><td>' + dDictType.dditTypeIndex + '</td></tr>');
//                    });
                });
                $("#dDictTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $("#dDictTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });

    }
    function assignDdictValues(dDictTypeIndex, dDictCode) {
        if (!!dDictTypeObj) {
            $("#dDictValueDiv").removeClass("hidden");
            $.each(dDictTypeObj, function (index, dDictType) {
                if (dDictType.dditTypeIndex == dDictTypeIndex) {
                    $("#ms-multi-select").find(".ms-selectable").find(".ms-optgroup-container").find("li").each(function () { //For Selectable Multi-Box
                        $(this).removeClass("ms-elem-selectable");
                        $(this).attr("style", "display: none");
                    });
                    $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () { //For Selection Multi-Box
                        $(this).removeClass("ms-elem-selection");
                        $(this).attr("style", "display: none");

                    });
                    $("select.dDictClass", $("#" + id)).each(function (idx, elem) {
                        var row = "";
                        $("#multi-select").empty();
                        if (!!dDictType.ddict) {
                            $.each(dDictType.ddict, function (index, dDict) {
                                if (dDict.ddictDditTypeIndex == dDictTypeIndex) {
                                    row += '<optgroup label = "' + dDictCode + '">';
                                    row += '<option value="' + dDict.id + '">' + dDict.ddictValue + '</option>';
                                }
                            });
                            $(elem).append(row);
                            $(elem).multiSelect({
                                selectableHeader: "<input type='text' class='form-control' style='margin-bottom: 10px;'  autocomplete='off' placeholder='Filter entries...'>",
                                selectionHeader: "<input type='text' class='form-control' style='margin-bottom: 10px;' autocomplete='off' placeholder='Filter entries...'>",
                                afterInit: function (ms) {
                                    var that = this,
                                            $selectableSearch = that.$selectableUl.prev(),
                                            $selectionSearch = that.$selectionUl.prev(),
                                            selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                                            selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

                                    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                                            .on('keydown', function (e) {
                                                if (e.which === 40) {
                                                    that.$selectableUl.focus();
                                                    return false;
                                                }
                                            });
                                    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                                            .on('keydown', function (e) {
                                                if (e.which === 40) {
                                                    that.$selectionUl.focus();
                                                    return false;
                                                }
                                            });
                                },
                                afterSelect: function () {
                                    this.qs1.cache();
                                    this.qs2.cache();
                                },
                                afterDeselect: function () {
                                    this.qs1.cache();
                                    this.qs2.cache();
                                }
                            });
                            $(elem).multiSelect('refresh');
                        } else {
                            alert("Selected Ddict Code Values Not Available");
                            $("#dDictValueDiv").addClass("hidden");
                            return false;
                        }
                    });
                    setDdictDetails(dDictType);
                }
            });
        }
    }
    function setDdictDetails(dDictType) {
        $("#dditTypeIndex").val(dDictType.dditTypeIndex);
        alert($("#entityName").val());
        dcomethealth.DataDictionaryResource.searchDdictValueByEntityMap({"ddictMapEntityRid": $("#entityName").val()}, function (data) {
            if (!!data && data.length > 0) {
                console.log(data);
                $.each(data, function (inx, entDdict) {
                    if (entDdict.ddictDditTypeIndex == dDictType.dditTypeIndex) {
                        $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                            if ($(this).find("span").text() == entDdict.ddictValue || $(this).find("span").text() == dDictType.dditCode) {
                                $(this).addClass("ms-selected");
                                $(this).removeAttr("style");
                            }
                        });
                    }
                });
            } else {
                alert("else");
            }
        });
    }
    function addNew() {
        $("#DataDictionaryMasterHeader").removeClass("panel panel-primary").addClass("hidden");
        $("#addDdictDiv").removeClass("hidden").addClass("panel panel-primary");
    }
    function insertRow(elem) {
        var x = dynTableRow(elem);
        dynTableCloneRow('dynTable', x);
        var table = document.getElementById('dynTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var optionName = dynTableGetNodeInRow(table.rows[i + 1], 'ddictValue').value;
            if (optionName === "") {
                alert("Enter Option Name");
                return false;
            }
        }
        var newRow = dynTableAppendRow('dynTable');
        dynTableGetNodeInRow(newRow, 'del').className = "ace-icon fa fa-minus";
        dynTableGetNodeInRow(newRow, 'ddictValue').value = "";
    }
    function deleteRow(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('dynTable');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (table_length > 2) {
                dynTableDeleteRow(x);
            }
        }
    }
    function saveDdict() {
        var dDict = {};
//        if ($("#ddictId").val() != "") {
//            dDict.id = $("#ddictId").val();
//        }
        var dDictList = [];
        $("#ms-multi-select").find(".ms-selection").find("li.ms-selected").each(function () { //.find("span")
//           
//            console.log($(this));
//            var currentID = $(this).attr('id');
//            alert(currentID);
//            alert(currentID.toString());
//            alert(typeof currentID);
            var spanText = $(this).find('span').text();

            if (!!$(this).attr('id')) {
                var currentID = $(this).attr('id').toString();
                alert(currentID);
                $.each(dcomethealth.dDictVal, function (index, dDictVal) {
                    if (parseInt(dDictVal.id) == parseInt(currentID.slice(0, -10)) && spanText == dDictVal.ddictValue) {
                        var dDictMap = {}, dDictObj = {}, dDictMapList = [];
                        if ($("#dDictEntityMapRid").val() != "" && !!$("#dDictEntityMapRid").val()) {
                            dDictMap.id = $("#dDictEntityMapRid").val();
                        }
                        dDictObj['id'] = dDictVal.id;
                        dDictObj['ddictDditTypeIndex'] = dDictVal.ddictDditTypeIndex;
                        dDictObj['ddictAbbrv'] = dDictVal.ddictAbbrv;
                        dDictObj['ddictValue'] = dDictVal.ddictValue;
                        dDictObj['ddictParentIndex'] = dDictVal.ddictParentIndex;
                        dDictObj['ddictValid'] = dDictVal.ddictValid;

                        dDictMap.id = dDictVal.id;
                        dDictMap.ddictMapDdictRid = dDictVal.id;
                        dDictMap.ddictMapEntityRid = $("#entityName").val();
                        dDictMapList.push(dDictMap);
                        dDictObj.ddictEntityMapList = dDictMapList;
                        dDictList.push(dDictObj);
                    }
                });
            }
        });
        console.log(dDictList);
//        return false;
//        $.each(dDictArray,function(inx,))
//        dDict.Ddict = dDictArray
//        dDictList.ddictEntityMapList = dDictMapList;
//        console.log(dDictList);
        dcomethealth.DataDictionaryResource.saveDdict(dDictList).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('DataDictionaryMaster');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function saveDdictType() {
        var dDictType = {};
        if ($("#dDictTypeRid").val() != "") {
            dDictType.dditTypeIndex = parseInt($("#dDictTypeRid").val());
        }
        dDictType.dditCode = $("#dditCode").val().toUpperCase();
        alert($("#dditCode").val().toUpperCase());

        var dynTable = document.getElementById('dynTable');
        var dynTableLength = dynTable.rows.length;
        var dDictList = [];
        for (var i = 0; i < dynTableLength - 1; i++) {
            var dDict = {};
            if (dynTableGetNodeInRow(dynTable.rows[i + 1], 'dDictRid').value != "") {
                dDict.id = dynTableGetNodeInRow(dynTable.rows[i + 1], 'dDictRid').value;
            }
            dDict.ddictValid = !!$("#isValidDdict").val() ? parseInt($("#isValidDdict").val()) : 0;
            dDict.ddictValue = dynTableGetNodeInRow(dynTable.rows[i + 1], 'ddictValue').value;
            dDict.ddictAbbrv = dynTableGetNodeInRow(dynTable.rows[i + 1], 'ddictValue').value;
            dDict.ddictParentIndex = !!$("#ddictParentIndex").val() ? parseInt($("#ddictParentIndex").val()) : 0;
            dDictList.push(dDict);
        }
        dDictType.ddict = dDictList;
        dcomethealth.DataDictionaryResource.saveDdictType(dDictType).done(function (data, textStatus, jqXHR) {
            alert("Data Saved");
            dcomethealth.util.loadpage('DataDictionaryMaster');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed to save data");
        });

    }
    function refreshData() {
    }
    return {
        init: init,
        addNew: addNew,
        insertRow: insertRow,
        deleteRow: deleteRow,
        getDdictValues: getDdictValues,
        loadDdictTypeGrid: loadDdictTypeGrid,
        assignDdictValues: assignDdictValues,
        setDdictDetails: setDdictDetails,
        saveDdictType: saveDdictType,
        saveDdict: saveDdict,
        refreshData: refreshData
    };
}());
dcomethealth.DataDictionaryMaster.init();
