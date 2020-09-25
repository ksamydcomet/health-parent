var dcomethealth = dcomethealth || {};
dcomethealth.PayerMaster = (function () {
    var id = "PayerMaster", serviceClearVar = 1, DeletedSlist = [];
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $("#payerType option").each(function (pIdx, Object) {
                if (($(Object).text()).includes("Self")) {
                    $(Object).remove();
                }
            });
            if (dcomethealth.loginuser.entityRid == 4) {
                $("select#payerType option").each(function (ix, selectOption) {
                    if (($(selectOption).text()).includes("Corporate")) {
                        $(selectOption).remove();
                    }
                });
            }
            fetchPayer();
            serviceAutoComplete();
            $('.activeStatus').toggles({on: true}, "pdIsActive", 1);

            $('.activeStatus').toggles({on: true}, "pdIsActive", 1);
            $('#curDate').text(moment().format('DD-MM-YYYY'));
            $("#edit_user_form").validate({
                rules: {
                    category: "required",
                    pdPayerNo: "required",
                    pdPayerName: "required",
                    pdPayerAddress: "required",
                    pdCity: "required",
                },
                messages: {
                    category: "Enter Valid Category",
                    pdPayerName: "Enter Payer Name",
                    pdPayerAddress: "Enter Payer Address",
                    pdCity: "Enter Payer City",
                    pdPayerNo: "Enter Number",
                },
                submitHandler: function (form) {
                    var table = document.getElementById('dyn_table');
                    var table_length = table.rows.length;
                    for (var i = 0; i < table_length - 1; i++) {
                        var pservicename = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceName').value;
                        var pserviceRID = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceRid').value;
                        var pdis = dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPercent').value;
                        var payerPrice = dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPrice').value;
                        if (pservicename == "") {
                            alert("Enter Service Name");
                            dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceName').focus();
                            return false;
                        }
                        if (payerPrice == 0 || payerPrice == "") {
                            alert("Enter the valid payer cost");
                            dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPrice').focus();
                            return false;
                        }
                        if (pdis >= 100) {
                            alert("Enter The Valid Discount Percent")
                            dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPercent').value = "";
                            dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPercent').focus();
                            return false;
                        }
                        if (pserviceRID == "" || pserviceRID == 0 || pserviceRID == undefined) {
                            alert("Enter Service Name Correctly");
                            dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceName').focus();
                            return false;
                        }
                    }
                    submit();
                }
            });
        });
    }
    function deletedService(elem) {
        var x = dynTableRow(elem);
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (table_length > 2) {
                if (!!dynTableGetNodeInRow(elem, 'psmServiceRid').value && dynTableGetNodeInRow(elem, 'psmServiceRid').value != "" && dynTableGetNodeInRow(elem, 'psmServiceRid').value != "undefined") {
                    var delService = {};
                    delService.psmServiceName = dynTableGetNodeInRow(elem, 'psmServiceName').value;
                    delService.psmServiceRid = dynTableGetNodeInRow(elem, 'psmServiceRid').value;
                    delService.psmId = dynTableGetNodeInRow(elem, 'psmId').value;
                    delService.psmPdRid = dynTableGetNodeInRow(elem, 'psmPdRid').value;
                    delService.psmServiceGroup = dynTableGetNodeInRow(elem, 'psmServiceGroup').value;
                    delService.psmServiceCost = dynTableGetNodeInRow(elem, 'psmServiceCost').value;
                    delService.psmDiscountPercent = dynTableGetNodeInRow(elem, 'psmDiscountPercent').value;
                    delService.psmDiscountVal = dynTableGetNodeInRow(elem, 'psmDiscountVal').value;
                    delService.psmDiscountPrice = dynTableGetNodeInRow(elem, 'psmDiscountPrice').value;
                    DeletedSlist.push(delService);
                }
                dynTableDeleteRow(x);
                return false;
            }
        }
    }
    function serviceAutoComplete() {
        var ServiceName;
        $("#" + id + " input[name='psmServiceName']").autocomplete({
            select: function (event, ui) {
                serviceClearVar = 1;
                ServiceName = ui.item.value;
                autoIdSet(dynTableGetNodeInRow(this, 'psmServiceRid'));
                dynTableGetNodeInRow(this, 'psmServiceRid').value = ui.item.data.id;
                checkService(dynTableGetNodeInRow(this, 'psmServiceRid').value, dynTableGetNodeInRow(this));
                var table = document.getElementById('dyn_table');
                var table_length = table.rows.length;
                var y = table_length - 2;
                for (var i = 0; i < table_length - 1; i++) {
                    dynTableGetNodeInRow(table.rows[y + 1], 'psmServiceRid').value = ui.item.data.id;
                    dynTableGetNodeInRow(table.rows[y + 1], 'psmServiceCost').value = ui.item.data.bPrice;
                    dynTableGetNodeInRow(table.rows[y + 1], 'psmServiceGroup').value = ui.item.data.bsGroup;
                }
                calculation(this);
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
                    serviceClearVar = 0;
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
    function fetchPayer() {
        var search = {"pdIsActive": 1};       
        dcomethealth.MasterResource.searchPayerMaster(search, function (data) {          
            $("#tbody_head").empty();
            if (!!data) {
                $.each(dcomethealth.p_pay = data, function (index, p_pay) {
                    $("#tbody_head").append('<tr onclick="dcomethealth.PayerMaster.showPayerDetail(' + p_pay.pdId + ')" id="tr_head">\n\
                    <td>' + p_pay.pdPayerNo + '<input type="hidden" value="' + p_pay.pdId + '"/></td>\n\
                        <td>' + p_pay.pdPayerName + '</td><td>' + p_pay.pdCity + '</td></tr>');
                });
                $("#PayerTable").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
            else {
                $("#PayerTable").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function showPayerDetail(val) {
        $.each(dcomethealth.p_pay, function (index, p_pay) {
            if (p_pay.pdId === parseInt(val)) {
                $("#PayerMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(p_pay);
            }
        });
    }
    function populateFields(p_pay) {
        $("#pdPayerNo").val(p_pay.pdPayerNo);
        $("#pdId").val(p_pay.pdId);
        $("#pdPayerName").val(p_pay.pdPayerName);
        $("#pdPayerAddress").val(p_pay.pdPayerAddress);
        $("#pdCity").val(p_pay.pdCity);
        $("#pdContact").val(p_pay.pdContact);
        $("#pdNuitNum").val(p_pay.pdNuitId);
        $("#activeStatus").val(p_pay.pdIsActive);
        if (p_pay.pdIsActive == 1) {
            $('.activeStatus').toggles({on: true}, "pdIsActive");
        } else {
            $('.activeStatus').toggles({on: false}, "pdIsActive");
        }
        $("#payerType").val(p_pay.pdPayerType);
        $("#payerMaster").empty();
        $.each(p_pay.payerServiceMap, function (Idx, map) {
            $("#payerMaster").append('<tr><td width="2%"><i id="minus" class="ace-icon fa fa-minus " onclick="dcomethealth.PayerMaster.deletedService(this)"></i></td>\n\
                    <td width="16%"><input type="text" class="col-md-11 col-sm-11 col-xs-11" id="psmServiceName"  readonly value="' + map.psmServiceName + '"  name="psmServiceName" onchange="dcomethealth.PayerMaster.autoIdSet(this); calculation(this)" maxlength="90" onkeypress="return dcomethealth.validation.ValidateAlpha(event)"/><input type="hidden" id="psmServiceRid" value="' + map.psmServiceRid + '" name="psmServiceRid"/> <input type="hidden" id="psmPdRid" value="' + map.psmPdRid + '" name="psmPdRid"/><input type="hidden" id="psmId" value="' + map.psmId + '" name="psmId"/><input type="hidden" id="psmServiceGroup" value="' + map.psmServiceGroup + '" name="psmServiceGroup"/></td>\n\                   <td width="6%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" id="psmServiceCost" style=" text-align: right;" onchange="calculation(this)" readonly   maxlength="12" value="' + (map.psmServiceCost).toFixed(2) + '" name="psmServiceCost"/></td>\n\
                    <td width="4%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" id="psmDiscountPercent" style=" text-align: right;" maxlength="5" onkeypress="return dcomethealth.validation.isDecimalKey(event)" onchange="discountval(this,0);calculation(this)" value="' + (map.psmDiscountPercent).toFixed(2) + '" name="psmDiscountPercent"/></td>\n\
                    <td width="6%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" id="psmDiscountVal" maxlength="12" style=" text-align: right;" onchange="discountval(this,1);calculation(this)" onkeypress="return dcomethealth.validation.isDecimalKey(event)" value="' + (map.psmDiscountVal).toFixed(2) + '" name="psmDiscountVal"/></td>\n\
                    <td width="6%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" id="psmDiscountPrice" onchange="discountval(this,2);calculation(this)" maxlength="14" style=" text-align: right;" value="' + (map.psmDiscountPrice).toFixed(2) + '" name="psmDiscountPrice"/></td>\n\
                    <td width="2%"><i class="ace-icon fa fa-plus red" onclick="insert_row(\'dyn_table\', this)"></i></td></tr>');
        });
    }
    function checkService(pserviceRID, row) {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        var count = 0;
        for (var i = 0; i < table_length - 1; i++) {
            var pserviceRid = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceRid').value;
            if (parseInt(pserviceRid) == parseInt(pserviceRID)) {
                if (pserviceRID == "") {
                    alert("Enter the Service Name");
                    dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceName').focus();
                    return false;
                }
                count++;
                if (count == 2) {
                    alert("Already Existing Service");
                    pserviceRID = "";
                    dynTableDeleteRow(row);
                    return false;
                }
            }
        }
    }
    function checkValidService() {
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            if (dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceRid').value == "" || dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceRid').value == 0) {
                dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceRid').value = "";
                dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceName').value = "";
            }
        }
    }
    function autoIdSet(elem) {
        if (parseInt(serviceClearVar) != 1) {
            dynTableGetNodeInRow(elem, 'psmServiceRid').value = "";
            dynTableGetNodeInRow(elem, 'psmServiceName').value = "";
        }
    }
    function submit() {
        var payer = {};
        var serviceList = [];
        if ($('#pdId').val() != "") {
            payer.pdId = $('#pdId').val();
        }
        payer.pdPayerNo = $("#pdPayerNo").val();
        payer.pdPayerType = $("#payerType").val();
        payer.pdPayerName = $("#pdPayerName").val();
        payer.pdPayerAddress = $("#pdPayerAddress").val();
        payer.pdCity = $("#pdCity").val();
        payer.pdContact = $("#pdContact").val();        
        payer.pdNuitId = $("#pdNuitNum").val();
        payer.pdIsActive = $("#pdIsActive").val();
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var servicedetails = {};
            if (dynTableGetNodeInRow(table.rows[i + 1], 'psmId').value != "") {
                servicedetails.psmId = dynTableGetNodeInRow(table.rows[i + 1], 'psmId').value;
            }
            servicedetails.psmServiceName = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceName').value;
            servicedetails.psmServiceRid = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceRid').value;
            servicedetails.psmServiceGroup = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceGroup').value;
            servicedetails.psmServiceCost = dynTableGetNodeInRow(table.rows[i + 1], 'psmServiceCost').value;
            if (!!dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPercent').value) {
                servicedetails.psmDiscountPercent = dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPercent').value;
            } else {
                servicedetails.psmDiscountPercent = 0;
            }
            servicedetails.psmDiscountVal = !!dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountVal').value ? dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountVal').value : 0;
            servicedetails.psmDiscountPrice = dynTableGetNodeInRow(table.rows[i + 1], 'psmDiscountPrice').value;
            servicedetails.psmIsActive = 1;
            serviceList.push(servicedetails);
        }
        if (DeletedSlist.length > 0) {
            $.each(DeletedSlist, function (indx, List) {
                var servicedetails = {};
                servicedetails.psmServiceName = List.psmServiceName;
                servicedetails.psmId = List.psmId;
                servicedetails.psmServiceRid = List.psmServiceRid;
                servicedetails.psmServiceGroup = List.psmServiceGroup;
                servicedetails.psmServiceCost = List.psmServiceCost;
                servicedetails.psmDiscountPercent = List.psmDiscountPercent;
                servicedetails.psmDiscountVal = List.psmDiscountVal;
                servicedetails.psmDiscountPrice = List.psmDiscountPrice;
                servicedetails.psmPdRid = List.psmPdRid;
                servicedetails.psmIsActive = 0;

                serviceList.push(servicedetails);
            });
        }
        payer.payerServiceMap = serviceList;       
        dcomethealth.MasterResource.savePayerMaster(payer).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('PayerMaster');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function refreshData() {
    }
    return {
        init: init,
        serviceAutoComplete: serviceAutoComplete,
        submit: submit,
        fetchPayer: fetchPayer,
        showPayerDetail: showPayerDetail,
        autoIdSet: autoIdSet,
        checkValidService: checkValidService,
        deletedService: deletedService,
        refreshData: refreshData
    };
}());
dcomethealth.PayerMaster.init();