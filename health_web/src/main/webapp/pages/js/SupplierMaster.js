var dcomethealth = dcomethealth || {};
dcomethealth.SupplierMaster = (function () {
    var id = "SupplierMaster";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            $('#page-header').innerHTML = 'Supplier Master';
            fetchSupplierList();
            $('#countryCode').val(dcomethealth.userEntityCountryCode);
            var obj = $('#patCountry').find('option[id="' + dcomethealth.userEntityCountryCode + '"]');
            $('#supCountry').val(obj.val());

            $('.activeStatus').toggles({on: true}, "isActive"); //---toggles function contains (boolean,valueID)
            var opts = $("#source").html(), opts2 = "<option></option>" + opts;
            $("select.populate").each(function () {
                var e = $(this);
                e.html(e.hasClass("placeholder") ? opts2 : opts);
            });
            $("#roles").select2({width: 'resolve'});
            $("#edit_user_form").validate({
                // Specify the validation rules

                rules: {
                    supName: "required",
                    supCode: "required",
                    supMobile: "required",
                    supPin: "required",
                    supCreditPeriod: "required"
                },
                messages: {
                    supCode: "Enter Supplier Code",
                    supName: "Enter Supplier Name",
                    supMobile: "Enter Mobile Number",
                    supPin: "Enter PinCode",
                    supCreditPeriod: "Enter Credit Period"
                },
                submitHandler: function (form) {
                    if ($("#supRid").val() === "") {
                        var searchObj = {};
                        var check = false;
                        dcomethealth.MasterResource.searchSuppliers(searchObj, function (data) {
                            if (!!data) {
                                $.each(data, function (pIdx, t_supplier_m) {
                                    if ($("#supCode").val() === t_supplier_m.supCode) {
                                        check = true;
                                    }
                                });
                            }
                            if (check) {
                                alert("Enter New Supplier");
                                return false;
                            } else {
                                submit();
                            }
                        });
                    } else {
                        submit();
                    }
                }
            });
        });
    }
    function fetchSupplierList() {
        var search = {"supStatus": 1};
        dcomethealth.MasterResource.searchSuppliers(search, function (data) {
            $("#tbody_head").empty();
            if (!!data) {
                $.each(dcomethealth.s_supplier = data, function (index, s_supplier) {

                    $("#tbody_head").append('<tr onclick="dcomethealth.SupplierMaster.showSupplierDetail(' + s_supplier.id + ')" id="tr_head">\n\
                        <td>' + s_supplier.supName + '<input type="hidden" value="' + s_supplier.id + '"/></td>\n\
                        <td>' + s_supplier.supCode + '</td><td>' + s_supplier.supContactNumber + '</td><td>' + s_supplier.supContactPerson + '</td></tr>');
                });
                $("#suppliersTable").dataTable();
                $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
                $('.dataTables_length select').addClass('form-control');
            }
        });
    }
    function showSupplierDetail(val) {
        $.each(dcomethealth.s_supplier, function (index, s_supplier) {
            if (s_supplier.id == parseInt(val)) {
                $("#SupplierHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(s_supplier)

            }

        });
    }
    function getCity() {
        var searchObj = {"couCode": dcomethealth.userEntityCountryCode};
        dcomethealth.DataDictionaryResource.searchByZipcode(searchObj, function (data) {
            $.each(data, function (pIdx, s_cou_zipcode) {
                $(s_cou_zipcode).each(function () {
                    if (dcomethealth.userEntityCountryCode == s_cou_zipcode.couCode) {
                        if (s_cou_zipcode.division1 == $("#stateGeo").val()) {
                            $("#Cities").append('<option value="' + s_cou_zipcode.place + '">' + s_cou_zipcode.place + '</option>');
                        }
                    }
                });
            });

        });
    }
    function searchPostalCodes() {
        var pincode = $('#supPin').val();
        var searchObj = {"zipCode": pincode, "couCode": dcomethealth.userEntityCountryCode};
        dcomethealth.DataDictionaryResource.searchByZipcode(searchObj, function (data) {
            $("#supCity").empty();
            $.each(data, function (pIdx, data) {
                $("#supCity").append('<option value="' + data.place + '">' + data.place + '</option>');
                $("#supState").val(data.division1);
            });
        });
    }
    function populateFields(data) {
        document.getElementById("supName").value = data.supName;
        document.getElementById("supRid").value = parseInt(data.id);
        document.getElementById("supCode").value = data.supCode;
        document.getElementById("supCity").value = data.supCity;
        document.getElementById("supMobile").value = data.supMobile;
        document.getElementById("supPin").value = data.supPin;
        document.getElementById("supAdd").value = data.supAdd;
        document.getElementById("supEmailID").value = data.supEmailID;
        document.getElementById("supContactPerson").value = data.supContactPerson;
        document.getElementById("supContactNumber").value = data.supContactNumber;
        document.getElementById("supTinNumber").value = data.supTinNumber;
        document.getElementById("supCstNumber").value = data.supCstNumber;
        document.getElementById("supCreditPeriod").value = data.supCreditPeriod;
        var isActive = parseInt(data.supStatus);
        if (isActive == 1) {
            $('.activeStatus').toggles({on: true}, "isActive");
        } else {
            $('.activeStatus').toggles({on: false}, "isActive");
        }
    }
    function submit() {
        var form = $("form");
        if (validateForm(form)) {
            var parent = {};
            if ($("#supRid").val() != "") {
                parent.id = $("#supRid").val();
            }
            parent.supCode = $("#supCode").val();
            parent.supName = $("#supName").val();
            parent.supCountry = $("#supCountry").val();
            parent.supState = $("#supState").val();
            parent.supCity = $("#supCity").val();
            parent.supMobile = $("#supMobile").val();
            parent.supPin = $("#supPin").val();
            parent.supAdd = $("#supAdd").val();
            parent.supEmailID = $("#supEmailID").val();
            parent.supContactPerson = $("#supContactPerson").val();
            parent.supContactNumber = $("#supContactNumber").val();
            parent.supCreditPeriod = $("#supCreditPeriod").val();
            parent.supCstNumber = $("#supCstNumber").val();
            parent.supTinNumber = $("#supTinNumber").val();
            parent.supStatus = ($("#isActive").val());
            parent.supEntityRID = dcomethealth.loginuser.entityRid;
            dcomethealth.MasterResource.saveSupplier(parent).done(function (data, textStatus, jqXHR) {
                if (!!data) {
                    alert("Data Saved");
                    dcomethealth.util.loadpage('SupplierMaster');
                } else {
                    alert("Supplier Name Already Exists");
                }
                //requestObj.field1 = data.primaryKey
            }).fail(function (jqXHR, textStatus, errorThrown) {

                alert("Failed to Save Data");
            });
        }
    }
//            var supplier = serializeFormData(form);
//            dcomethealth.MasterResource.saveSupplier(supplier).done(function (data, textStatus, jqXHR) {
//                alert("Data Saved");
//                dcomethealth.util.loadpage('SupplierMaster');
//            }).fail(function (jqXHR, textStatus, errorThrown) {
//                alert("failed to save data");
//            });
//        }

    function serializeFormData(form) {
        var o = {};
        var a = form.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
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
        fetchSupplierList: fetchSupplierList,
        searchPostalCodes: searchPostalCodes,
        getCity: getCity,
        showSupplierDetail: showSupplierDetail,
        populateFields: populateFields,
        submit: submit,
        refreshData: refreshData

    };
}());
dcomethealth.SupplierMaster.init();