var dcomethealth = dcomethealth || {};
dcomethealth.validation = {
    validateEmail: function (email) {
        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        if (!!email.value && reg.test(email.value) === false) {
            alert('Invalid Email Address');
            email.value = "";
            return false;
        }
    },
    isZero: function (zero) {
        if ((zero.value) === 0) {
            alert('Stock Quantity IS Zero');
            return false;
        }
    },
    validateDob: function (testdate) {

        var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
        if ((date_regex.test(testdate)) === false)
        {
            alert("Please Enter A Valid Date");
            return false;
        }
    },
    isPercentaged: function (input)
    {
        if (input.value < 0)
            input.value = 0;
        if (input.value > 100)
            input.value = 100;
    },
    ValidateAlphaNumeric: function (key) {
        var keycode = (key.which) ? key.which : key.keyCode;
        if ((keycode >= 65 && keycode <= 90) || (keycode >= 97 && keycode <= 122) || (keycode >= 46 && keycode <= 57) || (keycode == 9) || (keycode == 8) || (keycode == 46) || (keycode == 36) || (keycode == 35) || (keycode == 32)) {
            return true;
        } else {
            return false;
        }
    },
    ValidateDateEntry: function (evt) {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 57) {
            return false;
        }
        return true;
    },
    ValidateAlpha: function (evt) {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (parseInt(charCode) > 32 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)) {
            if ((charCode != 36) && (charCode != 35) && (charCode != 46)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    },
    isNumberKeyAndPercentage: function (evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && charCode != 37 && (charCode < 48 || charCode > 57))
            return false;
        return true;

    },
    isNumberKey: function (evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
            return true;
        }
    },
    isDecimalKey: function (evt, elem)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if ((charCode != 46 || $(elem).val().indexOf('.') != -1) && charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    },
    mrpValid: function (tableid, elem)
    {
        var x = dynTableRow(elem);
        dynTableCloneRow(tableid, x);
        var table = document.getElementById('dyn_table');
        var table_length = table.rows.length;
        for (var i = 0; i < table_length - 1; i++) {
            var ConsMrp = dynTableGetNodeInRow(table.rows[i + 1], 'ConsMrp').value;
            if (parseFloat(rate) == 0 || rate == '') {
                alert("Enter Consumed Mrp");
                setTimeout(function () {
                    dynTableGetNodeInRow(table.rows[i + 1], 'ConsMrp').focus();
                }, 1);
                return false;
            }
        }
    },
//    receiptQtyValidation: function (tableid, elem) {
//        var x = dynTableRow(elem);
//        dynTableCloneRow(tableid, x); 
//        var table = document.getElementById('dyn_table');
//        var table_length = table.rows.length;
//        for (var i = 0; i < table_length - 1; i++) {
//            var acc_qty1 = dynTableGetNodeInRow(table.rows[i + 1], 'grdAcceptedQty').value;
//            var rec_qty1 = dynTableGetNodeInRow(table.rows[i + 1], 'grdReceiptQty').value;
//            var order_qty = dynTableGetNodeInRow(table.rows[i + 1], 'grdOrderQty').value;
//            if (parseInt(order_qty) == 0 || order_qty == '') {
//                alert("Enter order qty");
////                setTimeout(function () {
//                    dynTableGetNodeInRow(table.rows[i + 1], 'grdOrderQty').focus();
////                }, 1);
////                return false;
//            } else if (parseInt(order_qty) < parseInt(rec_qty1)) {
//                alert("Order qty is less");
////                setTimeout(function () {
//                    dynTableGetNodeInRow(table.rows[i + 1], 'grdOrderQty').focus();
////                }, 1);
////                return false;
//            } else if (parseInt(rec_qty1) == 0 || rec_qty1 == '') {
//                alert("Enter recieved qty");
////                setTimeout(function () {
//                    dynTableGetNodeInRow(table.rows[i + 1], 'grdReceiptQty').focus();
////                }, 1);
//                alert("Recieved qty is less");
////                return false;
//            } else if (parseInt(acc_qty1) == 0 || acc_qty1 == '') {
//                alert("Enter accept qty");
////                setTimeout(function () {
//                    dynTableGetNodeInRow(table.rows[i + 1], 'grdAcceptedQty').focus();
////                }, 1);
////                return false;
//            } else if (parseInt(rec_qty1) < parseInt(acc_qty1)) {
////                setTimeout(function () {
//                    dynTableGetNodeInRow(table.rows[i + 1], 'grdReceiptQty').focus();
////                }, 1);
////                return false;
//            }
//        }
//    },
    DateFormat: function (txt, keyCode)
    {
        if (keyCode == 16)
            isShift = true;
        //Validate that its Numeric
        if (((keyCode >= 48 && keyCode <= 57) || keyCode == 8 ||
                keyCode <= 37 || keyCode <= 39 ||
                (keyCode >= 96 && keyCode <= 105)) && isShift == false)
        {
            if (txt.value.length == 1) {
                if (txt.value > 1) {
                    txt.value = "0" + txt.value;
                }
            }
            if ((txt.value.length == 2) && (txt.value > 12)) {
                alert("Invalid Month.....Try Again");
                return false;
            }
            if ((txt.value.length == 2) && keyCode != 8)
            {
                txt.value += seperator;
            }
            return true;
        }
        else
        {
            return false;
        }
    },
    isPercentaged: function (input) {
        if (input.value < 0)
            input.value = 0;
        if (input.value > 100)
            input.value = 100;
    },
            ValidateDate: function (txt, keyCode) {
                if (keyCode == 16)
                    isShift = false;
                var val = txt.value;
                if (val.length == 7)
                {
                    var splits = val.split("/");
                    var dt = new Date(splits[0] + "/" + splits[2]);
                    //Validation for Dates
                    if (dt.getMonth() + 1 == splits[1] && dt.getFullYear() == splits[2])
                    {
                    }
                }
            },
};