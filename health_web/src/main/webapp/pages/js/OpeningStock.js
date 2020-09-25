var dcomethealth = dcomethealth || {};
dcomethealth.OpeningStock = (function () {
    var id = "OpeningStock";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#page-header').innerHTML = 'Opening Stock';
            autoComplete();
            $("#edit_user_form").validate({
                rules: {
                    item_name: "required",
                    osdRate: "required",
                    osdMrp: "required",
                    osdQty: "required"
                },
                messages: {
                    item_name: "Enter Item Name",
                    osdRate: "Enter rate",
                    osdMrp: "Enter Mrp",
                    osdQty: "Enter Quantity"
                },
                submitHandler: function (form) {
                    submit();
                }
            });
        });
    }
    function autoComplete() {
        var skuName;
        $("#" + id + " input[name='item_name']").autocomplete({
            select: function (event, ui) {
                skuName = ui.item.value;
                dynTableGetNodeInRow(this, 'SkuRID').value = ui.item.data.id;
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
                        return {
                            label: item.skuName || "",
                            value: item.skuName || "",
                            name: (item.skuName || "") + (item.skuName && item.id ? " - " : "") + (item.id || ""),
                            data: item
                        };
                    }), function (item, index) {
                        return index < 75;
                    }));
                });
            },
            minLength: 1
        });
    }
    function submit() {
        var form = $("form");
        if (validateForm(form)) {

            var parent = {};
            parent.osUnitRID = dcomethealth.selectedunit;
            var child1List = [];
            var table = document.getElementById('dyn_table');
            var table_length = table.rows.length;
            for (var i = 0; i < table_length - 1; i++) {
                var osD = {};
                if (dynTableGetNodeInRow(table.rows[i + 1], 'osdRID').value != "") {
                    osD.id = dynTableGetNodeInRow(table.rows[i + 1], 'osdRID').value;
                }
                osD.osdSkuRID = dynTableGetNodeInRow(table.rows[i + 1], 'SkuRID').value;
                osD.osdBatchNo = dynTableGetNodeInRow(table.rows[i + 1], 'osdBatchNo').value;
                osD.osdExpiryDate = dynTableGetNodeInRow(table.rows[i + 1], 'osdExpiryDate').value;
                osD.osdRate = dynTableGetNodeInRow(table.rows[i + 1], 'osdRate').value;
                osD.osdMrp = dynTableGetNodeInRow(table.rows[i + 1], 'osdMrp').value;
                osD.osdQty = dynTableGetNodeInRow(table.rows[i + 1], 'osdQty').value;
                child1List.push(osD);
            }
            parent.openingStockD = child1List;
            dcomethealth.InventoryResource.saveopeningstock(parent).done(function (data, textStatus, jqXHR) {
                alert("Saved");
                dcomethealth.util.loadpage('OpeningStock')
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("failed");
            });
        }
    }
    function validateForm(form) {
        return form.validate();
    }
    function refreshData() {
    }
    return{
        init: init,
        autoComplete: autoComplete,
        submit: submit,
        refreshData: refreshData
    };
}());
dcomethealth.OpeningStock.init();