var dcomethealth = dcomethealth || {};
var dcometRoles = {};
var dcometBomBoc = {};
var dcometBomBows = {};
var dcometBoMaster = {};
dcomethealth.UserMaster = (function () {
    var id = "UserMaster", userRoleMapObj = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#page-header').innerHTML = 'User Master';
            dcomethealth.NotificationResource.getBOWorklistConfig(function (dataC) {
                dcometBomBoc = dataC;
            });
            dcomethealth.NotificationResource.getBOWorklistSettings(function (dataS) {
                dcometBomBows = dataS;
            });
            dcomethealth.NotificationResource.getBOMaster(function (data) {
                dcometBoMaster = data;
            });
            fetchUserList();
            $('.activeStatus').toggles({on: true}, "userValid", 1); //---toggles function contains (boolean,valueID)
            $('.staffStatus').toggles({on: true}, "userIsStaff", 1); //---toggles function contains (boolean,valueID)
            dcomethealth.MasterResource.getRoles(function (data) {
                dcometRoles = data;
            });
            $(".dcomet-c-s_feature-list").empty();
            $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                $(this).attr("style", "display: none");
                $(this).removeClass("ms-selected");
            });
            $("select.dcomet-c-s_feature-list", $("#" + this.id)).each(function (idx, elem) {
                dcomethealth.NotificationResource.getBOMaster(function (data) {
                    var row = "";
//                    var parentId = "";
                    $.each(data, function (i, bom) {
                        $.each(dcometBomBoc, function (i, bomBoc) {
                            if (parseInt(bomBoc.bowcBomTypeIndex) === parseInt(bom.bomTypeIndex)) {
                                row += '<optgroup label = "' + bom.bomName + '">';
                                row += '<option>' + bomBoc.bowcName + '</option>';
                            }
                        });
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

            $("#roles").select2({width: 'resolve'});
            $("#units").select2({width: 'resolve'});
            setTargetUnit();
            $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
                if (parseInt(dcomethealth.loginuser.entityRid) == entities.id) {
                    $.each(entities.units, function (pIdx, units) {
                        $("select#units").append('<option value="' + units.id + '">' + units.unitName + '</option>');
                    });
                }
            });
            $("#edit_user_form").validate({
                // Specify the validation rules
                rules: {
                    userID: "required",
                    userFullName: "required",
                    userGender: "required",
                    userMobile: "required",
                    userType: "number",
                    userValid: "number",
                    userIsStaff: "number",
                    roles: "required",
                    userEmail: {
                        required: true,
                        email: true
                    },
//                    lastName: "required",
//                    street: "required",
//                    city: "required",
//                    state: "required",
//                    country: "required",
//                    zipCode: "required",
//                    contactNo: "required",
//                    socialSecurityNo: "required",
//                    
                    userPassword: {
                        required: true,
                        minlength: 4
                    },
                    confirm_userPass: {
                        required: true,
                        minlength: 4,
                        equalTo: "#userPassword"
                    },
//                    agree: "required"
                },
                // Specify the validation error messages
                messages: {
                    userID: "Enter User Name",
                    userFullName: "Enter User Full Name",
                    userGender: "Select Gender",
                    userMobile: "Enter Mobile No",
                    userEmail: "Enter Valid Email",
                    roles: "Enter Roles",
                    userPassword: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 4 characters long"
                    },
                    confirm_userPass: {
                        required: "Please confirm your password",
                        equalTo: "Your password is not match"
                    },
                },
                submitHandler: function (form) {
                    if (parseInt($('#userIsStaff').val()) === 1) {
                        $('#userType').val('Staff');
                    } else {
                        $('#userType').val('User');
                    }
                    if ($('#id').val() === "") {
                        userEmail();
                    } else {
                        submit();
                    }
                }
            });
        });
    }
    function userEmail() {
        var searchObj = {};
        var check = false;
        dcomethealth.MasterResource.searchUsers(searchObj, function (data) {
            $.each(data, function (pIdx, s_user) {
                if ($("#userEmail").val() === s_user.userEmail || $("#userMobile").val() === s_user.userMobile) {
                    check = true;
                }
            });
            if (check === true) {
                alert("User Already Exist");
                return false;
            } else {
                submit();
            }
        });
    }
    function setTargetUnit() {
        $("#primaryUnit").empty();
        $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
            if (parseInt(dcomethealth.loginuser.entityRid) == entities.id) {
                $.each(entities.units, function (pIdx, units) {
                    $("#primaryUnit").append('<option value="' + units.id + '">' + units.unitName + '</option>');
                });
            }
        });
    }

    function fetchUserList() {
        var search = {"userValid": 1};
        dcomethealth.MasterResource.searchUsers(search, function (data) {
            $("#tbody_head").empty();
            $.each(dcomethealth.s_user = data, function (index, s_user) {
                $("#tbody_head").append('<tr onclick="dcomethealth.UserMaster.showUserDetail(' + s_user.id + ')" id="tr_head">\n\
                    <td>' + s_user.userID + '</td><td>' + s_user.userFullName + '</td><td>' + s_user.userType + '</td>\n\
                    <td>' + s_user.userMobile + '</td></tr>');
            });
            $("#usersTable").dataTable();
            $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
            $('.dataTables_length select').addClass('form-control');
        });
    }
    function showUserDetail(val) {
        $.each(dcomethealth.s_user, function (index, s_user) {
            if (parseInt(s_user.id) === parseInt(val)) {
                $("#UserMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                $("select.dcomet-c-s_roles-list").empty();
                $(".dcomet-c-s_feature-list").empty();
                $("select.dcomet-c-s_roles-list", $("#" + id)).each(function (idx, elem) {
                    $.each(dcometRoles, function (index, roles) {
                        if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID) && !!roles.roleValid) {
                            $(elem).append('<option value="' + roles.id + '">' + roles.roleName + '</option>');
                        }
                    });
                    var opts = $("#source").html(), opts2 = "<option></option>" + opts;
                    $("select.populate").each(function () {
                        var e = $(this);
                        e.html(e.hasClass("placeholder") ? opts2 : opts);
                    });
                });
                $("#ms-multi-select").addClass("row");
                $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                    $(this).attr("style", "display: none");
                    $(this).removeClass("ms-selected");
                });
                $("select.dcomet-c-s_feature-list", $("#" + this.id)).each(function (idx, elem) {
                    dcomethealth.NotificationResource.getBOMaster(function (data) {
                        var row = "";
//                    var parentId = "";
                        $.each(data, function (i, bom) {
                            $.each(dcometBomBoc, function (i, bomBoc) {
                                if (parseInt(bomBoc.bowcBomTypeIndex) === parseInt(bom.bomTypeIndex)) {
                                    row += '<optgroup label = "' + bom.bomName + '">';
                                    row += '<option>' + bomBoc.bowcName + '</option>';
                                }
                            });
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
                populateFields(s_user)
            }
        });
    }
    function populateFields(data) {
        document.getElementById("userID").value = data.userID;
        document.getElementById("uId").value = parseInt(data.id);
        document.getElementById("userFullName").value = data.userFullName;
        document.getElementById("userGender").value = data.userGender;
        document.getElementById("userMobile").value = data.userMobile;
        document.getElementById("userEmail").value = data.userEmail;
        document.getElementById("userDOB").value = data.userDOB;
        document.getElementById("userValid").value = data.userValid;
        document.getElementById("userIsStaff").value = data.userIsStaff;
        if (!!data.id && data.id != null) {
            $("#passWordDiv").addClass('hidden');
        }
        var userValid = parseInt(data.userValid);
        if (userValid == 1) {
            $('.activeStatus').toggles({on: true}, "userValid");
        } else {
            $('.activeStatus').toggles({on: false}, "userValid");
        }
        var userIsStaff = parseInt(data.userIsStaff);
        if (userIsStaff == 1) {
            $('.staffStatus').toggles({on: true}, "userIsStaff");
        } else {
            $('.staffStatus').toggles({on: false}, "userIsStaff");
        }
        $('#tbody_hidden').empty();
        $('.select2-choices').find('.select2-search-choice').each(function () {
            $(this).remove();
        });
        $.each(dcometBomBows, function (i, bomBows) {
            if (parseInt(bomBows.bowsUserRid) === parseInt(data.id)) {
                $.each(dcometBomBoc, function (i, bomBoc) {
                    if (parseInt(bomBoc.bowcRid) === parseInt(bomBows.bowsBowcRid)) {
                        $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                            if ($(this).find("span").text() === bomBoc.bowcName) {
                                $(this).addClass("ms-selected");
                                $(this).removeAttr("style");
                            }
                        });
                        $.each(dcometBoMaster, function (i, bom) {
                            if (parseInt(bomBoc.bowcBomTypeIndex) === parseInt(bom.bomTypeIndex)) {
                                $("#ms-multi-select").find(".ms-selection").find(".ms-optgroup-container").find("li").each(function () {
                                    if ($(this).find("span").text() === bom.bomName) {
                                        $(this).removeAttr("style");
                                    }
                                });
                            }
                        });
                    }

                });
            }
        });
        var userRolesList = [];
        $.each(userRoleMapObj = data.userRoleMap, function (idx, urRoleMap) {
            $.each(dcometRoles, function (index, roles) {
                if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID)) {
                    if (parseInt(urRoleMap.urRoleRID) === parseInt(roles.id)) {
                        var item = {};
                        item ["id"] = roles.id;
                        item ["text"] = roles.roleName;
                        userRolesList.push(item);
                        $('#tbody_hidden').append('<tr><td><input type="hidden" id="urRoleMapRid" name="urRoleMapRid" value="' + urRoleMap.id + '"/><input type="hidden" id="urRoleRid" name="urRoleRid" value="' + roles.id + '"/></td></tr>');
//                        $('.select2-search-choice-close').off("click").on("click", function () {
//                            $('.select2-choices').find('.select2-search-choice').each(function () {
////                                $(this).remove();
//                                $('#tbody_hidden').empty();
//                            });
//                        });
                    }
                }
            });
        });
        $("#s2id_roles").select2('data', userRolesList);
        var uUnitList = [];
        $.each(data.userUnitMapList, function (idx, uUnitM) {
            $.each(uUnitM.unit, function (uIdx, units) {
                if (uUnitM.suUnitRID == units.id) {
                    var item = {};
                    item ["id"] = units.id;
                    item ["text"] = units.unitName;
                    uUnitList.push(item);
                }
            });
        });
        $("#s2id_units").select2('data', uUnitList);
    }
    function submit() {
        var form = $("form");
        if (validateForm(form)) {
            var user = serializeFormData(form);

            if (!!$("#uId").val()) {
                user.id = $("#uId").val();
            }

            var child1List = [];
            var child2List = [], child3List = [];
            $("#ms-multi-select").find(".ms-selection").find("li.ms-selected").find("span").each(function () {
                var current = $(this);
                $.each(dcometBomBoc, function (index, wrkConfig) {
                    if (wrkConfig.bowcName === current.text()) {
                        var boWorkSet = {};
                        boWorkSet.bowsBowcRid = wrkConfig.bowcRid;
                        boWorkSet.bowsBowcCode = wrkConfig.bowcCode;
                        boWorkSet.bowsTimer = 0;
                        boWorkSet.bowsIsDateSpec = 0;
                        boWorkSet.bowsEntRID = dcomethealth.loginuser.entityRid;
                        boWorkSet.bowsUnitRID = dcomethealth.selectedunit;
                        boWorkSet.bowsValid = 1;
//                        boWorkSet.bowsModifyTime;
                        child2List.push(boWorkSet);
                    }
                });
            });
            $("#s2id_roles").find(".select2-choices").find(".select2-search-choice").find("div").each(function () {
                var current = $(this);
                $.each(dcometRoles, function (index, roles) {
                    if (parseInt(dcomethealth.loginuser.entityRid) == parseInt(roles.roleEntityRID) && !!roles.roleValid) {
                        if (roles.roleName === current.text()) {
                            var selectedId = $(current).find("input[type='hidden']").val();
                            var table = document.getElementById('dyn_table');
                            var table_length = table.rows.length;
                            var child = {};
                            for (var i = 0; i < table_length; i++) {
                                if (dynTableGetNodeInRow(table.rows[i], 'urRoleMapRid').value != "") {
                                    if (dynTableGetNodeInRow(table.rows[i], 'urRoleRid').value == selectedId) {
                                        child.id = dynTableGetNodeInRow(table.rows[i], 'urRoleMapRid').value;
                                    }
                                }
                            }
                            child.urRoleRID = roles.id;
                            child1List.push(child);
                        }
                    }
                });
            });
            $("#s2id_units").find(".select2-choices").find(".select2-search-choice").find("div").each(function (indx, unit) {
                var sUnit = $(unit).text();
                if (!!sUnit) {
                    var unitMap = {}, check = true;
                    var selectedId = $(unit).find("input[type='hidden']").val();
                    $.each(dcomethealth.s_user, function (idx, sUser) {
                        if (!!sUser.userUnitMapList) {
                            $.each(sUser.userUnitMapList, function (ifx, uUnitMap) {
                                if ($("#uId").val() == uUnitMap.suUserRID && uUnitMap.suUnitRID == selectedId) {
                                    var unitMap = {};
                                    unitMap.id = uUnitMap.id;
                                    unitMap.suUnitRID = selectedId;
                                    check = false;
                                }
                                if (check == true) {
                                    var unitMap = {};
                                    unitMap.suUnitRID = selectedId;
                                }
                            });
                        }
                    });
                    unitMap.suUnitRID = selectedId;
                    unitMap.suIsPrimaryUnit = parseInt(selectedId) == $("#primaryUnit").val() ? 1 : 0;
                    child3List.push(unitMap);
                }
            });
            if (!!child3List) {
                user.userUnitMapList = child3List;
            }
            user.userRoleMap = child1List;
            user.boWorkListSettings = child2List;
//            console.log(user);
//            return false;
            dcomethealth.MasterResource.saveUser(user).done(function (data, textStatus, jqXHR) {
                alert("Data Saved");
                dcomethealth.util.loadpage('UserMaster');
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("failed to save data");
            });
        }
    }

    function serializeFormData(form) {
        var o = {};
        var a = form.serializeArray();
        a = a.filter(function (select) { // For exclude select box named as roles
            return select.name != "roles";
        });
        $.each(a, function () {
            if (this.name !== "confirm_userPass") {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || null);
                } else {
                    o[this.name] = this.value || null;
                }
            }
        });
        return o;
    }
    function validateForm(form) {
        return  form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        fetchUserList: fetchUserList,
        showUserDetail: showUserDetail,
        populateFields: populateFields,
        submit: submit,
        refreshData: refreshData

    };
}());
dcomethealth.UserMaster.init();