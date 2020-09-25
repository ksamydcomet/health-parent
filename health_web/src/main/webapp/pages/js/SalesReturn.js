var dcomethealth = dcomethealth || {};
dcomethealth.SalesReturn = (function () {
    var id = "SalesReturn";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $("#" + id + " input[name='billSearch']").autocomplete({
                select: function (event, ui) {
                    $('#billHRID').val(ui.item.data.billH.id);
                    $('#visitRID').val(ui.item.data.billH.bhPatientVisitRID);
                    $("#patientRID").val(ui.item.data.billH.bhPatientRID);
                    $('#sReturn_tbody').empty();
                    $.each(ui.item.data.patient, function (pIdx, patient) {
                        $('#mrnSearch').val(patient.patMrnNo);
                        $('#PatientName').val(patient.patFullName);
                        var c = patient.patDob.split('-');
                        $('#PatientAge').val(Math.floor((new Date() - new Date(new Date(c[2], c[1] - 1, c[0]))) / (365.25 * 24 * 60 * 60 * 1000)));
                        $('#PatientMobile').val(patient.patPhoneNo);
                    });
                    $("#grossAmtBill").val(ui.item.data.billH.bhGrossAmount);
                    $("#dueAmtBill").val(ui.item.data.billH.bhDueAmount);
                    $("#paidAmtBill").val(ui.item.data.billH.bhPaidAmount);
                    $("#netAmtBill").val(ui.item.data.billH.bhNetAmount);

                    $.each(ui.item.data.salesH, function (pIdx, salesH) {
                        $.each(salesH.salesD, function (pIdx, salesD) {
                            $('#sReturn_tbody').append('<tr><td width="30%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="skuName" name="skuName" value="' + salesD.salItemName + '" readonly/>\n\
                        <input type="hidden" id="itemRID" name="itemRID" value="' + salesD.salSkuRID + '"/><input type="hidden" id="retailTax" name="retailTax" value="0"/><input type="hidden" id="srdBillRid" name="srdBillRid" value="' + salesD.id + '"/></td>\n\
                        <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" id="batchNo" name="batchNo" value="' + salesD.salBatchNo + '" readonly/></td><td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" id="expDate" name="expDate" value="' + salesD.salExpDate + '" readonly/></td>\n\
                        <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" id="MRP" name="MRP" value="' + salesD.salRate + '" readonly/></td><td width="10%"><input type="text" class="col-md-12 col-sm-12 col-xs-12" name="qty" id="qty" value="' + salesD.salQty + '" readonly/></td>\n\
                        <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="returnable_qty" name="returnable_qty" value="' + salesD.salReturnQty + '" readonly/></td>\n\
                        <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="return_qty" onblur="calculation(this)" name="return_qty" value="" /></td>\n\
                        <td width="10%"><input class="col-md-12 col-sm-12 col-xs-12" type="text" id="return_amount" name="return_amount" readonly="readonly" value="" /></td></tr>');
                        });
                    });
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
                    var searchParams = {"bhBillNo": queryString};
                    dcomethealth.BillingResource.searchBilling(searchParams, function (data) {
                        response($.grep($.map(data, function (item) {
                            return {
                                label: (item.billH.bhBillNo || ""),
                                value: (item.billH.bhBillNo || ""),
                                name: (item.billH.bhBillNo || ""),
                                data: item
                            };
                        }), function (item, index) {
                            return index < 10;
                        }));
                    });
                },
                minLength: 1
            });
            $("#edit_user_form").validate({
                rules: {
                    billSearch: "required"
                },
                messages: {
                    billSearch: "Enter BillNo"
                },
                submitHandler: function (form) {
                    submit();
                }
            });
        });
    }
    function submit() {
        var form = $("form");
        if (dcomethealth.SalesReturn.validateForm(form)) {
            var salesReturnH = {};
            var salesReturnDList = [];
            salesReturnH.srhPatientRID = $("#patientRID").val();
            salesReturnH.srhPatientMrn = $("#mrnSearch").val();
            salesReturnH.srhPatientName = $("#PatientName").val();
            if ($("#visitRID").val() !== "") {
                salesReturnH.srhVisitRID = $("#visitRID").val();
            }
            salesReturnH.srhRoundoffAmount = $("#round_off_amount").val();
            salesReturnH.srhPaidAmount = $("#paidAmtBill").val();
            salesReturnH.srhNetAmount = $("#salesNetAmount").val();
            salesReturnH.srhUnitRID = dcomethealth.selectedunit;
            salesReturnH.srhBIllRID = $("#billHRID").val();
            salesReturnH.srhBillNo = $("#billSearch").val();
            var table = document.getElementById('dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var salesReturnD = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'return_qty').value != "" && dynTableGetNodeInRow(table.rows[i + 1], 'return_qty').value != 0) {
                    salesReturnD.srdSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'itemRID').value;
                    salesReturnD.srdSkuName = dynTableGetNodeInRow(table.rows[i + 1], 'skuName').value;
                    salesReturnD.srdItemBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'batchNo').value;
                    salesReturnD.srdItemExpDate = dynTableGetNodeInRow(table.rows[i + 1], 'expDate').value;
                    salesReturnD.srdBIllRID = dynTableGetNodeInRow(table.rows[i + 1], 'srdBillRid').value;
                    salesReturnD.srdReturnQty = dynTableGetNodeInRow(table.rows[i + 1], 'return_qty').value;
                    salesReturnD.srdReturnRate = dynTableGetNodeInRow(table.rows[i + 1], 'MRP').value;
                    salesReturnD.srdTaxAmount = 0;
                    salesReturnD.srdReturnNetAmount = dynTableGetNodeInRow(table.rows[i + 1], 'return_amount').value;

                    salesReturnDList.push(salesReturnD);
                }
            }
            salesReturnH.salesReturnD = salesReturnDList;
            dcomethealth.SalesResource.saveSalesReturn(salesReturnH).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('SalesReturn');
                dcomethealth.util.base_init();
                loadNotification();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Failed");
            });
        }
    }
    function  validateForm(form) {
        return  form.validate();
    }
    function refreshData() {
    }
    return {
        init: init,
        submit: submit,
        validateForm: validateForm,
        refreshData: refreshData

    };
}());
dcomethealth.SalesReturn.init();