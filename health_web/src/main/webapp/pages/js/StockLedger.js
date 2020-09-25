var dcomethealth = dcomethealth || {};
dcomethealth.StockLedger = (function () {
    title: "Stock Ledger"
    var id = "StockLedger";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));

            $("#" + id + " input[name='itemname']").autocomplete({
                select: function (event, ui) {
                    var itemName = ui.item.value;
                    searchByName(ui.item.data.id)
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
                            var details = "<table><tr><td style='white-space:nowrap;padding-right:10px;'>CDE #:</td><td>" + item.skuName + "</td></tr>";
                            details += "</table>";
                            return {
                                label: (item.skuName || ""),
                                value: item.skuName || "",
                                name: (item.skuName || "") + (item.skuName && item.id ? " - " : ""),
                                details: details,
                                data: item
                            };
                        }), function (item, index) {
                            return index < 75;

                        }));
                    });
                },
                minLength: 1
            });

            var searchObj = {};
            dcomethealth.InventoryResource.searchStockLedgerReports(searchObj, function (ledgerReports) {
                dcomethealth.s_StkLedgerReports = ledgerReports;
                $.each(ledgerReports, function (i) {
                    $("#data_sl").append('<tr id="tr_head"><td>' + ledgerReports[i].tranRefNo + '<input id="tranRID" type="hidden" value="' + ledgerReports[i].tranID + '"/></td>\n\
<td>' + ledgerReports[i].tranDate + '</td><td>-</td><td>-</td><td>-</td><td>' + ledgerReports[i].tranIssueQty + '</td><td>' + ledgerReports[i].tranReceiptQty + '</td><td>-</td><td>-</td></tr>');
                });
                $("#dyn_table_sl").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            });
            $(".edit-all-button").off("click").on("click", function () {
                editAllButtonOnClick();
            });

        });
    }
    function exportExcel(val) {
        var htmltable = document.getElementById('dyn_table_sl');
        var html = htmltable.outerHTML;
        if (parseInt(val) == 1) {
            var a = document.createElement('a');
            var data_type = 'data:application/vnd.ms-excel';
            a.href = data_type + ', ' + encodeURIComponent(html);
            a.download = 'StockLedger.xls';
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
//            window.open('data:application/vnd.ms-excel,' + encodeURIComponent(html));
        }
        if (parseInt(val) == 2) {
            var printWindow = window.open();
            printWindow.document.write(html);

            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }
    }
    function refreshData() {
    }
    return {
        init: init,
        exportExcel: exportExcel,
        refreshData: refreshData
    }
}());

dcomethealth.StockLedger.init();