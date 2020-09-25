var dcomethealth = dcomethealth || {};
dcomethealth.loginuser = {};
dcomethealth.privilages = [];
dcomethealth.selectedunit = {};
dcomethealth.workListConfig;
dcomethealth.util = (function () {
    function init() {
        $("body").removeClass("hide");
        $('#breadCrumb').addClass('hidden');
        $("#sidebar").append(build_menu(dcomethealth.loginuser.pageList));
        if (!!dcomethealth.loginuser.userUnitMapList) {
            loadUserUnit();
        }
        loadUserUtil();
        dcomethealth.sDdict.init();
    }
    function ucword(str) {
        if (!!str) {
            str = str.toLowerCase().replace(/(^([a-zA-Z\p{M}]))|([ -][a-zA-Z\p{M}])/g, function (replace_latter) {
                return replace_latter.toUpperCase();
            });  //Can use also /\b[a-z]/g
            return str;  //First letter capital in each word
        } else {
            return "";
        }
    }
    function build_menu(data, li) {
        return data.map(function (el) {
            if (el.id != "NonMenuGroup") {
                var li = !!el.menuGroup ? $('<li class="hasChild">') : null;
                if (el.id || el.link) {
                    if (el.sub && el.menuGroup) {
                        li.append($('<a>', {
                            html: ' <i class="' + el.icon + '" style="color:#16a085"></i><span>' + (el.id || el.link),
                            href: 'javascript:;',
                            onclick: !!el.link ? "dcomethealth.util.loadpage('" + el.link + "');" : "javascript:void(0);",
                            name: el.id,
                            class: 'dropdown-toggle'
                        }));
                    }
                    else {
                        if (el.menuGroup) {
                            li.append($('<a>', {
                                text: el.id || el.link,
                                href: 'javascript:;',
                                onclick: !!el.link ? "dcomethealth.util.loadpage('" + el.link + "');dcomethealth.util.base_init();" : "javascript:void(0);", // baseInit('" + el.link + "');
                                name: el.id
                            }));
                        }
                        dcomethealth.privilages.push(el);
                    }
                }
            }
            if (el.sub)
                $('<ul class="acc-menu">').appendTo(li).append(build_menu(el.sub, li));
            return li;
        });
    }
    function loadpage(id) {
        if (dcomethealth.security.isPrivillage(id) !== true) {
            alert("Privilege not available to load a Page");
            return false;
        } else {
            $("#main_navigation_bar div").remove();
            $("#main_navigation_bar").append("<div id='" + id + "'></div>");
            $('#page-header').text(id);
            $.getScript("pages/js/" + id + ".js", function (obj) {
            });
        }
    }
    function base_init() {
        dcomethealth.commonInit = 0;
        dcomethealth.boRID = 0;
        dcomethealth.opdVal = 0;
        dcomethealth.actionDatalist = 0;
        dcomethealth.bomtypeIndex = 0;
        dcomethealth.searchPatByAppointments = 0;
        dcomethealth.searchApptByAppointments = 0;
    }
    function loadUserUtil() {
        $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
            if (entities.id == dcomethealth.loginuser.userEntityRID) {
                dcomethealth.userEntityCountryCode = entities.entityCountryCode;
                dcomethealth.userEntityName = entities.entityName;
                dcomethealth.userEntityMail = entities.entityMail;
                dcomethealth.userEntityDisplayAddress = entities.entityDisplayAddress;
                if (!!entities.currencyMapList) {
                    $.each(dcomethealth.currencyMapList = entities.currencyMapList, function (pIdx, cml) {
                        if (parseInt(cml.currIsPrimary) === 1) {
                            dcomethealth.sCurrencyRid = cml.currencyM.id;
                            dcomethealth.sCurrencyCode = cml.currencyM.curShortName;
                        }
                    });
                }
            }
        });
        adminPages();
    }
    function adminPages() {
        $('#adminPages').append('<ul class="dropdown-menu">');
        $.each(dcomethealth.privilages, function (pIdx, asset) {
            if (asset.link == "Settings") {
                $('#adminPages ul').append('<li><a href="#" onclick="dcomethealth.util.loadpage(' + "'" + asset.link + "'" + ');">' + asset.id + '\n\
                    <i class="pull-right fa fa-cogs"></i></a></li>');
            } else if (asset.link == "Master") {
                $('#adminPages ul').append('<li><a href="#" onclick="dcomethealth.util.loadpage(' + "'" + asset.link + "'" + ');">' + asset.id + '\n\
                    <i class="pull-right fa fa-book"></i></a></li>');
            }
        });
//        $('#adminPages ul').append('<li class="divider"></li>\n\
//                <li><a href="#" onclick="dcomethealth.SecurityResource.logout();">Sign Out <i class="pull-right fa fa-sign-out"></i></a></li>');
    }
    function loadUserUnit() {
        var unitDiv = '<div class="container"><div class="row">';
        $.each(dcomethealth.loginuser.userUnitMapList, function (pIdx, userUnitMapList) {
            $.each(userUnitMapList.unit, function (pIdx, unit) {
                if (parseInt(unit.unitValid) === 1) {
                    if (userUnitMapList.suIsPrimaryUnit) {
                        $("#headerbardropdown").append('<span id="selectedunit">' + unit.unitName + ' <i class="fa fa-level-down"></i></span>');
                        dcomethealth.selectedunit = unit.id;
                        dcomethealth.selectedunitname = unit.unitName;
                        dcomethealth.selectedUnitCode = unit.unitCode;
                        dcomethealth.util.loadpage(unit.unitCode);
                        loadNotification();
                    }
                    unitDiv += '<div class="col-xs-6 col-sm-2"><a href="#" onclick=dcomethealth.util.selectUnit("' + unit.unitName + '","' + unit.id + '","' + unit.unitCode + '") class="shortcut-tiles ' + unit.unitDisplayColor + '"><div class="tiles-body"><div class="pull-left"><i class="' + unit.unitDisplayIcon + '"></i></div></div><div class="tiles-footer">' + unit.unitName + '</div></a></div>';
                }
            });
        });
        unitDiv += '</div></div>';
        $("#headerbar").append(unitDiv);
    }
    function selectUnit(unitName, unitId, unitCode) {
        dcomethealth.selectedunit = unitId;
        dcomethealth.selectedunitname = unitName;
        dcomethealth.selectedUnitCode = unitCode;
        dcomethealth.util.loadpage(unitCode);
        dcomethealth.ServerRequestResource.globelMessage(0, unitName);
        $("#headerbardropdown").empty();
        $("#headerbardropdown").append('<span class="selectedunit">' + unitName + '<i class="fa fa-level-down"></i></span>');
    }
    function loadNotification() {
        $("#worklistHead").empty();
        dcomethealth.NotificationResource.getBOWorklistConfig(function (data) {
            dcomethealth.workListConfig = data;
        });
        var searchParams = dcomethealth.selectedunit + '/' + new Date();
        dcomethealth.NotificationResource.searchNotification(searchParams, function (data) {
            var li;
            var notification_count = !!data ? data.length : 0;
            var ali = $('<a href="#" class="hasnotifications dropdown-toggle" data-toggle=\'dropdown\'>\n\
                       <i class="fa fa-tasks"></i><span class="badge">' + notification_count + '</span></a><ul class="dropdown-menu notifications arrow">\n\
                       <li class="dd-header">' + notification_count + ' Tasks (' + $.datepicker.formatDate('dd M yy', new Date()) + ')<span class=\"fa fa-refresh cursorHand pull-right\" title=\"Refresh\" onclick=\"dcomethealth.util.base_init()\">\n\
                       </span></li><div class="scrollthis" id="worklistDiv"></div><li class="dd-footer"><a href="#">View All Notifications</a></li></ul>');
            $("#worklistHead").append(ali);
            $.each(data, function (pIdx, workList) {

                li = $('<li >');
                li.append($('<a>', {
                    html: "<span class=\"pull-right \">" + workList.workListcount + "</span><div><span class=\"name\"> " + workList.workListName + "</span>\n\
                                       <input type=\"hidden\" id=\"worklistConfigRID\" value=" + workList.bowBowcRid + "></div>",
                    class: "active",
                    onclick: "dcomethealth.util.loadWorklist(" + workList.bowBowcRid + ");"
                }));
                $("#worklistDiv").append(li);
            });
        });
    }
    function loadWorklist(bowBowcRid) {
        dcomethealth.bowBowcRid = bowBowcRid;
        dcomethealth.util.loadpage('Notification');
    }
    function loadWorklistByCode(bowcCode) {
        $.each(dcomethealth.workListConfig, function (pIdx, wc) {
            if (wc.bowcCode == bowcCode) {
                dcomethealth.bowBowcRid = wc.bowcRid;
                dcomethealth.util.loadpage('Notification');
            }
        });
    }
    return {
        init: init,
        ucword: ucword,
        build_menu: build_menu,
        base_init: base_init,
        loadpage: loadpage,
        loadUserUtil: loadUserUtil,
        adminPages: adminPages,
        loadUserUnit: loadUserUnit,
        selectUnit: selectUnit,
        loadNotification: loadNotification,
        loadWorklist: loadWorklist,
        loadWorklistByCode: loadWorklistByCode
    };
}());
dcomethealth.sDdict = (function () {
    function init() {
        dDictValues();
        sCountryDetails();
        getServiceMaster();
    }
    function dDictValues() {
        dcomethealth.DataDictionaryResource.getDdicts(function (data) {
            dcomethealth.sDdict = data;
        });
        dcomethealth.DataDictionaryResource.searchDdictValue({}, function (data) {
            dcomethealth.dDictVal = data;
        });
//        dcomethealth.DataDictionaryResource.searchDdictByEntityMap({}, function (data) {
//            dcomethealth.dDictByEntitymap = data;
//        });
    }
    function sCountryDetails() {
        dcomethealth.DataDictionaryResource.searchCountries({}, function (data) {
            dcomethealth.s_countries = data;
        });
    }
    function getServiceMaster() {
        dcomethealth.MasterResource.searchServices({}, function (data) {
            dcomethealth.serviceMaster = data;
        });
    }
    return {
        init: init,
        dDictValues: dDictValues,
        sCountryDetails: sCountryDetails,
        getServiceMaster: getServiceMaster
    };
}());
dcomethealth.security = (function () {
    function isPrivillage(rId) {
        return !!getFeatureName(rId) ? true : false;
    }
    function getFeatureName(rId) {
        var ufCode = '';
        $.each(dcomethealth.privilages, function (pIdx, asset) {
            if (!!rId && asset.ufRid == rId || asset.link === rId) {
                ufCode = asset.link;
            }
        });
        return ufCode;
    }
    return {
        isPrivillage: isPrivillage,
        getFeatureName: getFeatureName
    };
}());
