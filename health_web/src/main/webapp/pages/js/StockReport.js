var dcomethealth = dcomethealth || {};
dcomethealth.StockReport = (function () {
    var id = "StockReport";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            var searchObj = {"stkDate": moment(), "stkUnitRID": dcomethealth.selectedunit};
            getReports(searchObj, 0);
            getUnit();
        });
    }
    function getUnit() {
        var searchParams = {};
        dcomethealth.MasterResource.searchUnit(searchParams, function (data) {
            $.each(data, function (pIdx, item) {
                var opt1 = document.createElement("option");
                opt1.value = item.id;
                opt1.text = item.unitName;
                if (dcomethealth.selectedunit == item.id) {
                    opt1.selected = true;
                }
                document.getElementById('UnitSelect').options.add(opt1);
            })
        });
    }
    function exportExcel(val) {
        var htmltable = document.getElementById('reports_full_table');
        var html = htmltable.outerHTML;
        if (parseInt(val) === 1) {
            var a = document.createElement('a');
            var data_type = 'data:application/vnd.ms-excel';
            a.href = data_type + ', ' + encodeURIComponent(html);
            a.download = 'StockReport.xls';
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        }
        if (parseInt(val) === 2) {
            var printWindow = window.open();
            printWindow.document.write(html);

            printWindow.document.close();
            printWindow.focus();
            printWindow.print();
            printWindow.close();
        }
    }
    function onChange() {
        var date = moment($("#packDate").val());
        if ($('#UnitSelect').val() !== 0) {
            var unit = $('#UnitSelect').val();
        }
        var searchObj = {"stkDate": date, "stkUnitRID": unit};
        $('#withBatchNo').attr('checked', false);
        dcomethealth.StockReport.getReports(searchObj, 0);
    }
    function getReports(searchObj, val) {
        dcomethealth.InventoryResource.searchStockReports(searchObj, function (reports) {
            $("#tbody_reports").empty();
            $("#tbody_reports1").empty();
            $("#thead_reports").empty();
            $("#thead_reports1").empty();
            if (parseInt(val) === 1) {
                $('#thead_reports').append('<tr><th>Description</th><th>Stock</th><th>Batch No</th><th>UOM</th><th>Rate(<span class="dcomet-c-s_currCode"></span>)</th><th>Stock Value</th></tr>');
                $('#thead_reports1').append('<tr><th>Description</th><th>Stock</th><th>Batch No</th><th>UOM</th><th>Rate(<span class="dcomet-c-s_currCode"></span>)</th><th>Stock Value</th></tr>');
            } else if (parseInt(val) === 0) {
                $('#thead_reports').append('<tr><th>Description</th><th>Stock</th><th>UOM</th><th>Rate(<span class="dcomet-c-s_currCode"></span>)</th><th>Stock Value</th></tr>');
                $('#thead_reports1').append('<tr><th>Description</th><th>Stock</th><th>UOM</th><th>Rate(<span class="dcomet-c-s_currCode"></span>)</th><th>Stock Value</th></tr>');
            }
            var tables = $.fn.dataTable.fnTables(true);
            $(tables).each(function () {
                $(this).dataTable().fnClearTable();
                $(this).dataTable().fnDestroy();
            });
            if (reports != undefined) {
                $.each(reports, function (idx, report) {
                    $.each(report.stockDChildList, function (pIdx, stkD) {
                        if (parseInt(val) == 0) {
                            $("#tbody_reports").append('<tr id="tr_head"><td>' + report.stkSkuName + '<input id="stkRID" type="hidden" value="' + report.stkRID + '"/></td>\n\
                                         <td>' + stkD.stkdQty + '</td><td>Nos</td><td>' + (report.stkMrp).toFixed(2) + '</td>\n\
                                         <td>' + (stkD.stkdQty * report.stkMrp).toFixed(2) + '</td></tr>');
                            $("#tbody_reports1").append('<tr id="tr_head"><td>' + report.stkSkuName + '<input id="stkRID" type="hidden" value="' + report.stkRID + '"/></td>\n\
                                         <td>' + stkD.stkdQty + '</td><td>Nos</td><td>' + (report.stkMrp).toFixed(2) + '</td>\n\
                                         <td>' + (stkD.stkdQty * report.stkMrp).toFixed(2) + '</td></tr>');
                        } else if (parseInt(val) === 1) {
                            $("#tbody_reports").append('<tr id="tr_head"><td>' + report.stkSkuName + '<input id="stkRID" type="hidden" value="' + report.stkRID + '"/></td>\n\
                                         <td>' + stkD.stkdQty + '</td><td>' + report.stkBatchNo + '</td><td>Nos</td><td>' + (report.stkMrp).toFixed(2) + '</td>\n\
                                         <td>' + (stkD.stkdQty * report.stkMrp).toFixed(2) + '</td></tr>');
                            $("#tbody_reports1").append('<tr id="tr_head"><td>' + report.stkSkuName + '<input id="stkRID" type="hidden" value="' + report.stkRID + '"/></td>\n\
                                         <td>' + stkD.stkdQty + '</td><td>' + report.stkBatchNo + '</td><td>Nos</td><td>' + (report.stkMrp).toFixed(2) + '</td>\n\
                                         <td>' + (stkD.stkdQty * report.stkMrp).toFixed(2) + '</td></tr>');
                        }
                    });
                });
                $("#reports_table").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $("#reports_table").dataTable({"bJQueryUI": false, "bAutoWidth": false, "bDestroy": true, });
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function refreshData() {
    }
    return {
        init: init,
        getUnit: getUnit,
        exportExcel: exportExcel,
        onChange: onChange,
        getReports: getReports,
        refreshData: refreshData
    }
}());
dcomethealth.StockReport.init();
