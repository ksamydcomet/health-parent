var dcomethealth = dcomethealth || {};
dcomethealth.DiscountApproval = (function () {
    var id = "DiscountApproval", approvalDetail = {};
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            if (parseInt(dcomethealth.commonInit) !== 0) {
                $("#btns_state").empty();
                var categories = [];
                $.each(dcomethealth.actionDatalist, function (pIdx, datalist) {
                    var div = $("#btns_state");
                    if ($.inArray(datalist.boaName, categories) === -1) {
                        categories.push(datalist.boaName);
                        div.append('<button class="btn-primary btn" id="' + datalist.boaCode + '" style="margin-top: 3px; margin-bottom: 3px;">' + datalist.boaName + '</button>');

                    }
                });
                dcomethealth.datatypes.init($("#" + id));
                fetchDiscDetails();
            }
            $("#APPROVE").off("click").on("click", function () {
                $("#APPROVE").attr("disabled", "disabled");
                submit("Approve");
            });
            $("#REJECT").off("click").on("click", function () {
                $("#APPROVE").attr("disabled", "disabled");
                submit("Reject");
            });
        });
    }
    function fetchDiscDetails() {
        var searchParams = {"appId": dcomethealth.boRID};
        dcomethealth.BillingResource.searchApproval(searchParams, function (data) {
            $.each(data, function (idx, discApp) {
                approvalDetail = discApp;
                $("#billCode").text(discApp.appBillCode);
                $("#netAmount").text(discApp.billNetAmount);
                $("#discName").text(discApp.discName);
                $("#discPercentage").text(discApp.discPercentage);

                var searchParams = {"id": discApp.appBillRid};
                dcomethealth.BillingResource.searchBilling(searchParams, function (data) {
                    $.each(data, function (pIdx, data) {
                        $.each(data.billH.billD, function (pIdx, billD) {
                            $("#so_tbody").append('<tr><td width="5%"><i class="dct-icon fa fa-minus-circle hidden " onclick="delete_row(this)"></i></td>\n\
                        <td width="40%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" id="serviceName" name="serviceName" readonly="readonly" value = "' + billD.bdItemName + '" onchange="ClearName(this)" class="col-md-11 col-sm-11 col-xs-11"><input type="hidden" id="serviceRID" name="serviceRID" value="' + billD.bdItemRID + '"><input type="hidden" id="bdGroupRID" name="bdGroupRID" value="' + billD.bdGroupRID + '">\n\
                        <input id="serviceReqDRid" type="hidden"/><input type="hidden" id="serviceOrderRID" name="serviceOrderRID" value="' + billD.bdItemOrderRID + '"><input id="billDRID" type="hidden" value="' + billD.id + '"><input id="isPackageService" type="hidden" value="' + billD.bdItemPackageRID + '"></td><td width="10%"><input id = "qtyService" maxlength = "3" type = "text" readonly="readonly" value = "' + billD.bdQty + '" name = "qtyService" onBlur="calculation(this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="10%"><input type="text" id="serviceDisc" style="text-align:right;" onBlur = "calculation(this)" name = "serviceDisc" value = "' + billD.bdDiscountAmount + '" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="10%"><input type="text" id="MRP" name = "MRP" style="text-align:right;" readonly="readonly" value = "' + billD.bdPrice + '" onBlur = "calculation(this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="20%"><input type = "text" id = "netAmount" style="text-align:right;" readonly="readonly" name = "netAmount" value = "' + billD.bdNetAmount + '" onBlur = "calculation(this)" class="col-md-12 col-sm-12 col-xs-12"></td>\n\
                        <td width="5%"><i id="plus" class = "dct-icon fa fa-plus-square red hidden" onclick = "insert_row(\'dyn_table\',this,1)" ></i></td></tr>');
                        });
                    });
                });
            });

        });

    }
    function submit(actionCode) {
        var args = approvalDetail.appId + "/" + "DISCOUNT_APPROVAL" + "/" + actionCode;
        dcomethealth.BillingResource.saveApproval(approvalDetail, args).done(function (data, textStatus, jqXHR) {
            if (actionCode == "Approve") {
                alert("Approved");
            } else if (actionCode == "Reject") {
                alert("Rejected");
            }
            dcomethealth.util.loadpage('CreateOPBill');
            dcomethealth.util.base_init();
            dcomethealth.util.loadNotification();
        }).fail(function (jqXHR, textStatus, errorThrown) {
        });
    }
    function refreshData() {
    }
    return {
        init: init,
        fetchDiscDetails: fetchDiscDetails,
        refreshData: refreshData
    };
}
());
dcomethealth.DiscountApproval.init();