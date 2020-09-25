var dcomethealth = dcomethealth || {};
var attRefType = 0;
dcomethealth.UserProfile = (function () {
    var id = "UserProfile";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            $("#up-header-name").html('<h3><strong>' + dcomethealth.util.ucword(dcomethealth.loginuser.userFullName) + '</strong></h3>');
            $("#up-user-email").html('<a href="mailto:' + dcomethealth.loginuser.userEmail + '">' + dcomethealth.loginuser.userEmail + '</a>');
            $.each(dcomethealth.dDictVal, function (i, val) {
                if (parseInt(dcomethealth.loginuser.userGender) == val.id) {
                    $("#up-user-gender").text(val.ddictValue);
                }
            });
            $("#up-user-dob").text(dcomethealth.loginuser.userDOB);
            $("#up-user-address").text(dcomethealth.util.ucword(dcomethealth.loginuser.userStreetAddress));
            $("#up-user-city").text(dcomethealth.util.ucword(dcomethealth.loginuser.userCity));
            $("#up-user-country").text(dcomethealth.util.ucword(dcomethealth.loginuser.userCountry));
            $("#up-user-pincode").text(dcomethealth.loginuser.userPincode);
            $("#up-user-phone").text(dcomethealth.loginuser.userPhone);
            $("#up-user-mobile").text(dcomethealth.loginuser.userMobile);
//            $('#userImg').attr('src', '/apptics_web/rest/file/user/photo?id=' + dcomethealth.loginuser.id);
            $('#userImg').attr('src', dcomethealth.userImage);
            $('#user_photo').attr('src', dcomethealth.userImage);
            $('#user_photo_dropdown').attr('src', dcomethealth.userImage);
            dcomethealth.pictures.setDragNDropListeners();
        });
    }
    function div_Back() {
        $("#oldpwdDiv").removeClass("hidden").removeClass('has-error');
        $("#newPwdDiv").addClass("hidden");
        $("#back").addClass("hidden");
        $("#oldPassword").val("");
        $("#enterDiv").removeClass("hidden");
        $("#confirmDiv").addClass("hidden");
        $("#oldPwd").css({"margin-top": "0px"});
    }
    function showResetScreen() {
        $("#resetPassword").addClass("hidden");
        $("#reset").addClass("hidden");
        $("#resetPwd").removeClass("hidden");
    }
    function isExistPassword() {
        var oldPwd = $('#oldPassword').val();
        var user = {};
        user.userID = dcomethealth.loginuser.userID;
        user.userPassword = oldPwd;
        dcomethealth.MasterResource.loginCheck(user).done(function (data, textStatus, jqXHR) {
            if (data != 0) {
                $('#oldpwdDiv').removeClass('has-error').addClass("hidden");
                $("#newPwdDiv").removeClass("hidden");
                $("#back").removeClass("hidden");
                $("#enterDiv").addClass("hidden");
                $("#confirmDiv").removeClass("hidden");
//                $("#confirmBtn").css({"margin-top": "-58px"});
            } else {
                $('#oldpwdDiv').addClass('has-error');
            }
        })

//        if (oldEncriptPwd == dcomethealth.loginuser.userPassword) {
//            $('#oldpwdDiv').removeClass('has-error').addClass("hidden");
//            $("#newPwdDiv").removeClass("hidden");
//            $("#back").removeClass("hidden");
//            $("#enterDiv").addClass("hidden");
//            $("#confirmDiv").removeClass("hidden");
//            $("#confirmBtn").css({"margin-top": "-58px"});
//        } else {
//            $('#oldpwdDiv').addClass('has-error');
//        }
    }
    function saveUser() {
        var newPwd = $("#newPwd").val();
        var confirmPwd = $("#confirmPwd").val();
        if (newPwd == confirmPwd && !!confirmPwd) {
            $('#newPwdDiv').removeClass('has-error');
            $('#confirmPwdDiv').removeClass('has-error');
            var user = {};
            user.id = dcomethealth.loginuser.id;
            user.userID = dcomethealth.loginuser.userID;
            user.userType = dcomethealth.loginuser.userType;
            user.userPassword = confirmPwd;
            user.userFullName = dcomethealth.loginuser.userFullName;
            user.userGender = dcomethealth.loginuser.userGender;
            user.userEmail = dcomethealth.loginuser.userEmail;
            user.userDOB = dcomethealth.loginuser.userDOB;
            user.userStreetAddress = dcomethealth.loginuser.userStreetAddress;
            user.userCity = dcomethealth.loginuser.userCity;
            user.userCountry = dcomethealth.loginuser.userCountry;
            user.userPincode = dcomethealth.loginuser.userPincode;
            user.userMobile = dcomethealth.loginuser.userMobile;
            user.userPhone = dcomethealth.loginuser.userPhone;
            user.userValid = dcomethealth.loginuser.userValid;
            dcomethealth.MasterResource.saveUser(user).done(function (data, textStatus, jqXHR) {
                alert("Data Saved");
                dcomethealth.util.loadpage('UserProfile');
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("failed to save data");
            });
        } else {
            $('#newPwdDiv').addClass('has-error');
            $('#confirmPwdDiv').addClass('has-error');
            alert("Your Password Doesn't Match");
        }
    }
    function refreshData() {
    }
    return {
        init: init,
        refreshData: refreshData,
        div_Back: div_Back,
        showResetScreen: showResetScreen,
        isExistPassword: isExistPassword,
        saveUser: saveUser
    };
}());
dcomethealth.UserProfile.init();