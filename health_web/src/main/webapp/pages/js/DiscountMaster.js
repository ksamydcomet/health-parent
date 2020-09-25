var dcomethealth = dcomethealth || {};
dcomethealth.DiscountMaster = (function () {
    var id = "DiscountMaster";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            fetchDiscount();
            $('.approvedStatus').toggles({on: true}, "disIsApproved", 1);
            $('.discountStatus').toggles({on: true}, "disIsActive", 1);
            $("#edit_user_form").validate({
                rules: {
                    disCode: "required",
                    disName: "required",
                    disPercentage: "required",
                },
                messages: {
                    disCode: "Enter discount code",
                    disName: "Enter discount name",
                    disPercentage: "Enter discount percentage",
                },
                submitHandler: function (form) {
                    submit();
                }
            });
        });
    }
    function fetchDiscount() {
        var search = {disIsActive: 1};
        dcomethealth.MasterResource.searchDiscount(search, function (data) {
            $("#tbody_head").empty();
            var disCategrory = "";
            if (!!data) {
                $.each(sDiscontList = data, function (idx, s_discount) {
                    $.each(dcomethealth.dDictVal, function (i, val) {
                        if (parseInt(s_discount.disCategory) == val.id) {
                            disCategrory = val.ddictValue;
                        }
                    });
                    $("#tbody_head").append('<tr onclick="dcomethealth.DiscountMaster.showDiscountDetail(' + s_discount.id + ')" id="tr_head">\n\
            <td>' + s_discount.disName + '</td><td>' + disCategrory + '</td><td>' + s_discount.disPercentage + '</td></tr>');
                });
                $("#discountMasterTable").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            } else {
                $("#discountMasterTable").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function showDiscountDetail(id) {
        $.each(sDiscontList, function (idx, s_discount) {
            if (s_discount.id == parseInt(id)) {
                $("#DiscountMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(s_discount)
            }

        });
    }
    function populateFields(data) {
        $("#disCode").val(data.disCode);
        $("#id").val(data.id);
        $("#disName").val(data.disName);
        $("#disPercentage").val(data.disPercentage);
        $("#ApprovedStatus").val(data.disIsApproved);
        $("#category").val(data.disCategory);
        if (data.disIsApproved == 1) {
            $('.approvedStatus').toggles({on: true}, "disIsApproved");
        } else {
            $('.approvedStatus').toggles({on: false}, "disIsApproved");
        }
        $("#discountStatus").val(data.disIsActive);
        if (data.disIsActive == 1) {
            $('.discountStatus').toggles({on: true}, "disIsActive");
        } else {
            $('.discountStatus').toggles({on: false}, "disIsActive");
        }
    }


    function submit() {
        var discount = {};
        if (!!$("#id").val()) {
            discount.id = $("#id").val();
        }
        discount.disCode = $("#disCode").val();
        discount.disName = $("#disName").val();
        discount.disCategory = $("#category").val();
        discount.disPercentage = $("#disPercentage").val();
        discount.disIsActive = $("#disIsActive").val();
        discount.disIsApproved = $("#disIsApproved").val();
        console.log(discount);
        dcomethealth.MasterResource.saveDiscount(discount).done(function (data, textStatus, jqXHR) {
            alert("Saved");
            dcomethealth.util.loadpage('DiscountMaster');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    }
    function refreshData() {

    }

    return{
        init: init,
        refreshData: refreshData,
        showDiscountDetail: showDiscountDetail,
        fetchDiscount: fetchDiscount,
    };
}());
dcomethealth.DiscountMaster.init();

