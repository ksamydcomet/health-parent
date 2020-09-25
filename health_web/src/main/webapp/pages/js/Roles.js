var dcomethealth = dcomethealth || {};
dcomethealth.Roles = (function () {
    var id = "Roles";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            searchDetail();
            $('.roleStatus').toggles({on: true}, "isActiveRole", 1);
            $(".dcomet-c-s_feature-list").empty();
            $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                $(this).attr("style", "display: none");
                $(this).removeClass("ms-selected");

            });
            $("select.dcomet-c-s_feature-list", $("#" + id)).each(function (idx, elem) {
                dcomethealth.DataDictionaryResource.getFeatures(function (data) {
//                dcomethealth.DataDictionaryResource.getFeaturesByEntityMap({}, function (data) {
                    dcomethealth.s_feature = data;
                    var row = "";
                    var parentId = "";
                    $.each(data, function (index, feature) {
                        if (feature.featureName === "") {
                            parentId = feature.id;
                            row += '<optgroup label = "' + feature.featureCode + '">';
                            $.each(data, function (index, featureSub) {
                                if (featureSub.featureName !== "") {
                                    if (featureSub.featureParentGroup === parentId) {
                                        row += '<option>' + featureSub.featureName + '</option>';
                                    }
                                }

                            });
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
                });
            });
            $("#saveRoles").off("click").on("click", function () {
                if ($("#roleName").val() === "") {
                    alert("Role Name is empty");
                    return false;
                }
                if (parseInt($("#ms-multi-select").find(".ms-selection").find("li.ms-selected").find("span").length) === 0) {
                    alert("Feature is empty");
                    return false;
                }
                if ($('#roleRid').val() === "") {
                    dcomethealth.Roles.getRoles();
                } else {
                    editAllButtonOnClick();
                }

            });
        });
    }
    function getRoles() {
        var searchObj = {};
        var check = false;
        dcomethealth.MasterResource.searchRoles(searchObj, function (data) {
            $.each(data, function (pIdx, roles) {
                if ($("#roleName").val() === roles.roleName) {
                    check = true;
                }
            });
            if (check === true) {
                alert("Role Name Already Exist");
                return false;
            } else {
                dcomethealth.Roles.editAllButtonOnClick();
            }
        });
    }
    function searchDetail() {
        var search = {"roleValid": 1};
        dcomethealth.MasterResource.searchRoles(search, function (data) {
            $("#tbody_head").empty();
            var tables = $.fn.dataTable.fnTables(true);
            $(tables).each(function () {
                $(this).dataTable().fnClearTable();
                $(this).dataTable().fnDestroy();
            });
            if (data != undefined) {
                $.each(dcomethealth.sroles = data, function (index, s_roles) {
                    var str = "No";
                    if (s_roles.roleValid === 1) {
                        str = "Yes";
                    }
                    $("#tbody_head").append('<tr onclick="dcomethealth.Roles.searchdetails(' + s_roles.id + ')" id="tr_head"><td>' + s_roles.roleName + '</td><td>' + str + '</td></tr>');
                });
                $("#rolesTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $("#rolesTable").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true});
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function searchdetails(val) {
        $.each(dcomethealth.sroles, function (index, s_roles) {
            if (parseInt(s_roles.id) === parseInt(val)) {
                $("#RoleMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                $(".dcomet-c-s_feature-list").empty();
                $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                    $(this).removeClass("ms-selected");
                    $(this).attr("style", "display: none")
                });
                $("select.dcomet-c-s_feature-list", $("#Roles")).each(function (idx, elem) {
//                    dcomethealth.DataDictionaryResource.getFeatures(function (data) {

//                        dcomethealth.s_feature = data;
                    var row = "";
                    var parentId = "";
                    $.each(dcomethealth.s_feature, function (index, feature) {
                        if (feature.featureName === "") {
                            parentId = feature.id;
                            row += '<optgroup label = "' + feature.featureCode + '">';
                            $.each(dcomethealth.s_feature, function (index, featureSub) {
                                if (featureSub.featureName !== "") {
                                    if (featureSub.featureParentGroup === parentId) {
                                        row += '<option>' + featureSub.featureName + '</option>';
                                    }
                                }

                            });
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
                });
//                });
                dcomethealth.Roles.setDetails(s_roles)
            }
        });
    }
    function setDetails(s_roles) {
        $("#roleRid").val(s_roles.id);
        $("#roleName").val(s_roles.roleName);
        var active = parseInt(s_roles.roleValid);
        if (parseInt(active) === 1) {
            $('.roleStatus').toggles({on: true}, "isActiveRole", 1);
        } else {
            $('.roleStatus').toggles({on: false}, "isActiveRole", 0);
        }
        $.each(s_roles.privilegeList, function (idx, priv) {
            $.each(dcomethealth.s_feature, function (index, feature) {
                if (parseInt(feature.id) === parseInt(priv.privFeatureRID)) {
                    $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                        if ($(this).find("span").text() === feature.featureName) {
                            $(this).addClass("ms-selected");
                            $(this).removeAttr("style");
                        }
                    });
                    $.each(dcomethealth.s_feature, function (index, feature2) {
                        if (parseInt(feature.featureParentGroup) === parseInt(feature2.id)) {
                            $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                                if ($(this).find("span").text() === feature2.featureCode) {
                                    $(this).removeAttr("style");
                                }
                            });
                        }
                    });
                }

            });
        });
    }
    function editAllButtonOnClick() {
        var parent = {};
        if ($("#roleRid").val() != "") {
            parent.id = $("#roleRid").val();
        }
        parent.roleEntityRID = dcomethealth.loginuser.entityRid;
        parent.roleName = $("#roleName").val();
        parent.roleProductRID = 0;
        parent.roleValid = $("#isActiveRole").val();
        parent.roleRecordExist = 0;
//        parent.roleDashboard = 0;
        var child1List = [];
        var parentList = [];
        $("#ms-multi-select").find(".ms-selection").find("li.ms-selected").find("span").each(function () {
            var current = $(this);
            $.each(dcomethealth.s_feature, function (index, s_feature) {
                if (s_feature.featureName === current.text()) {
                    var child = {};
                    child.privProdRID = 0;
                    child.privFeatureRID = s_feature.id;
                    child.privRecordExist = 0;
                    child.tempTrgtPrivRID = 0;
                    child1List.push(child);
                    parentList.push(s_feature.featureParentGroup);
                }
            });
        });
        var categories = [];
        $.each(parentList, function (i, el) {
            if ($.inArray(el, categories) === -1)
                categories.push(el);
        });
        $.each(dcomethealth.s_feature, function (index, s_feature) {
            $.each(categories, function (i, el) {
                if (s_feature.id === el) {
                    var childP = {};
                    childP.privProdRID = 0;
                    childP.privFeatureRID = s_feature.id;
                    childP.privRecordExist = 0;
                    childP.tempTrgtPrivRID = 0;
                    child1List.push(childP);
                }
            });
        });
        parent.privilegeList = child1List;
        dcomethealth.MasterResource.saveRoles(parent)
                .done(function (data, textStatus, jqXHR) {
                    alert("Saved");
                    dcomethealth.util.loadpage('Roles');
                    //requestObj.field1 = data.primaryKey
                }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
            // TO DO
        });
        //}
    }
    function refreshData() {
    }
    return {
        init: init,
        getRoles: getRoles,
        searchDetail: searchDetail,
        searchdetails: searchdetails,
        setDetails: setDetails,
        editAllButtonOnClick: editAllButtonOnClick,
        refreshData: refreshData
    };
}());
dcomethealth.Roles.init();
