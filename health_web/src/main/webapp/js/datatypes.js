var dcomethealth = dcomethealth || {};
dcomethealth.datatypes = (function () {
    function init(targetContainer) {
        var targetContainer = targetContainer || document;
        $($('[class*=dcomet-c-s_ddict_]'), targetContainer).each(function (idx, e) {
            var a = $(e).attr('class').split(' ');
            for (var index = 0; index < a.length; ++index) {
                var value = a[index];
                if (value.search("dcomet-c-s_ddict_") !== -1) {
                    var c = value.slice(17, -5).toUpperCase();
                    applyDataTypes(c, e);
                    break;
                }
            }
        });
        $("span.dcomet-c-s_currCode", targetContainer).each(function (idx, elem) {
            $(elem).text(dcomethealth.sCurrencyCode);
        });
        $("select.dcomet-c-s_currList", targetContainer).each(function (ifx, elem) {
            $.each(dcomethealth.currencyMapList, function (pIdx, cml) {
                $(elem).append('<option value="' + cml.currencyM.id + '">' + cml.currencyM.curShortName + '</option>');
            });
        });
        $("select.dcomet-c-s_entityList", targetContainer).each(function (ifx, elem) {
            $.each(dcomethealth.loginuser.entity, function (pIdx, entities) {
                $(elem).append('<option value="' + entities.id + '">' + entities.entityShortName + '</option>');
            });
        });
        $("datalist.dcomet-c-s_countries-list", targetContainer).each(function (idx, elem) {
            $.each(dcomethealth.s_countries, function (pIdx, s_countries) {
                $(s_countries).each(function () {
                    $(elem).append('<option id="' + s_countries.couCode + '" value="' + s_countries.couName + '">' + s_countries.couCode + '</option>');
                });
            });
        });
        $('.jqueryUIToolTip', targetContainer).tooltip({
            open: function (event, ui) {
            }
        });
    }
    function applyDataTypes(c, e) {
        $.each(dcomethealth.sDdict, function (pIdx, sDdict) {
            $(sDdict).each(function () {
                if (this.dditCode === c) {
                    $(this.ddict).each(function (cIdx, ddict) {
                        $(e).append('<option value="' + ddict.id + '">' + ddict.ddictValue + '</option>');
                    });
                }
            });
        });
        $(e).find("option").each(function () { //For remove duplicates
            $(this).siblings("[value='" + this.value + "']").remove();
        });
    }
    function refreshData() {
    }
    return{
        init: init,
        applyDataTypes: applyDataTypes,
        refreshData: refreshData
    };
}());
dcomethealth.datatypes.init();
