<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>HealthPlus</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="HealthPlus">
        <meta name="author" content="dCometTechnologies">

        <link rel="stylesheet" href="css/dcometStyles/styles.css" />
        <link rel="stylesheet" type="text/css" href="js/jquery/jqueryui.css" />
        <link rel="stylesheet" type="text/css" href="js/jquery/jquery.lightbox-0.5.css" />
        <link rel="stylesheet" type="text/css" href="js/plugins/form-daterangepicker/daterangepicker-bs3.css" />

        <script type='text/javascript' src='js/jquery/jquery-1.10.2.min.js'></script>
        <script type="text/javascript" src="js/jquery/jquery.blockUI.js"></script>
        <script type='text/javascript' src='js/jquery/jqueryui-1.10.3.min.js'></script>
        <script type='text/javascript' src='js/jquery/jquery.lightbox-0.5.js'></script>

        <!-- Main java script -->
        <script type="text/javascript" src="js/dcomethealth.js"></script>
        <script type="text/javascript" src="js/datatypes.js"></script>
        <script type="text/javascript" src="js/handlebars.js"></script>
        <script type="text/javascript" src="js/Validation.js"></script>
        <script type="text/javascript" src="js/DynamicTableTemplate.js"></script>

        <!-- Resource-->
        <script type="text/javascript" src="server/ServerRequestResource.js"></script>
        <script type="text/javascript" src="server/AppointmentsResource.js"></script>
        <script type="text/javascript" src="server/DataDictionaryResource.js"></script>
        <script type="text/javascript" src="server/ClinicalResource.js"></script>
        <script type="text/javascript" src="server/ServiceRequestResource.js"></script>        
        <script type="text/javascript" src="server/SalesResource.js"></script>
        <script type="text/javascript" src="server/InventoryResource.js"></script>
        <script type="text/javascript" src="server/BillingResouce.js"></script>
        <script type="text/javascript" src="server/BedManagementResource.js"></script>
        <script type="text/javascript" src="server/MasterResource.js"></script>
        <script type="text/javascript" src="server/FavouriteResource.js"></script>
        <script type="text/javascript" src="server/PatientResource.js"></script>
        <script type="text/javascript" src="server/NotificationResource.js"></script>
        <script type="text/javascript" src="server/GoodsReceiptResource.js"></script>
        <script type="text/javascript" src="server/PurchaseOrderResource.js"></script>
        <script type="text/javascript" src="server/ProcedureRequestResource.js"></script>
        <script type="text/javascript" src="server/ReceiptResource.js"></script>
        <script type="text/javascript" src="server/CreateOPBillResource.js"></script>
        <script type="text/javascript" src="server/ServiceOrderResource.js"></script>
        <script type="text/javascript" src="server/AdvanceResource.js"></script>
        <script type="text/javascript" src="server/LaboratoryResource.js"></script>
        <script type="text/javascript" src="server/ItemOrderResource.js"></script>
        <script type="text/javascript" src="server/DashBoardResource.js"></script>
        <script type="text/javascript" src="server/SecurityResource.js"></script>
        <script type="text/javascript" src="server/RefundResource.js"></script>        
        <script type="text/javascript" src="server/FileResource.js"></script>
        <script type="text/javascript" src="server/pictures.js"></script>
        <script type="text/javascript" src="server/ReportResource.js"></script>

        <script >
            var dcomethealth = dcomethealth || {};
            function init() {
                dcomethealth.MasterResource.loginUser().done(function (user) {
                    dcomethealth.loginuser = user;
                    dcomethealth.util.init();
                    loadUserInfo();
                });
            }
            function loadUserInfo() {
                $("#user-name-in-dropdown").html(dcomethealth.util.ucword(dcomethealth.loginuser.userID) + '<i class="fa fa-caret-down"></i>');
                $("#user-name-in-loggedin").html('<h5>Hi, ' + dcomethealth.util.ucword(dcomethealth.loginuser.userFullName) + '!</h5><small>Logged in as <span>' + dcomethealth.util.ucword(dcomethealth.loginuser.userID) + '</span></small>');
                var userImage = new Image();
                userImage = '/health_web/rest/file/user/photo?id=' + dcomethealth.loginuser.id;
                dcomethealth.image = '/health_web/rest/master/v1/printInfo/logo?id=' + dcomethealth.loginuser.entityRid;
                $('#headerLogo').attr('src', '/health_web/rest/master/v1/printInfo/logo?id=' + dcomethealth.loginuser.entityRid);
                dcomethealth.userImage = userImage;
                if (dcomethealth.userImage == null) {
                    $('#user_photo').attr('src', dcomethealth.userImage);
                    $('#user_photo_dropdown').attr('src', dcomethealth.userImage);
                } else {
                    $('#user_photo').attr('src', '/health_web/css/images/avatars/avatar.png');
                    $('#user_photo_dropdown').attr('src', '/health_web/css/images/avatars/avatar.png');
                }
            }
            function imgError(image) {
                image.onerror = "";
                image.src = "";
                return true;
            }
            init();
        </script>
    </head>
    <body class="collapse-leftbar hide"> 
        <div id="headerbar"></div>
        <header id="banner-header-bar" class="navbar navbar-inverse navbar-fixed-top" role="banner">
            <div class="navbar-header pull-left"><a class="navbar-brand" href="#" onclick="dcomethealth.util.selectUnit(dcomethealth.selectedunitname, dcomethealth.selectedunit, dcomethealth.selectedUnitCode)">healthPlus</a><img id="headerLogo" src="" onerror="imgError(this)" class="row navbar-client-brand" /></div>
            <ul class="nav navbar-nav pull-right toolbar">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle username" data-toggle="dropdown"><span class="hidden-xs" id="user-name-in-dropdown"></span>
                        <img id="user_photo" alt="User"/></a>
                    <ul class="dropdown-menu userinfo arrow">
                        <li class="username" onclick="dcomethealth.util.loadpage('UserProfile');">
                            <a href="#"><div class="pull-left"><img id="user_photo_dropdown" alt="User"/></div>
                                <div class="pull-right" id="user-name-in-loggedin"></div>
                            </a>
                        </li>
                        <li class="userlinks" id="adminPages"></li>
                        <li class="userlinks">
                            <ul class="dropdown-menu">
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath}/logout">Sign Out <i class="pull-right fa fa-sign-out"></i></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="dropdown" id="worklistHead"></li>
                <li id="unitHead"><a href="#" id="headerbardropdown"></a></li>
            </ul>
        </header>
        <div id="page-container">
            <nav id="page-leftbar" role="navigation"><ul class="acc-menu" id="sidebar"></ul></nav>
            <div id="page-content">
                <div id='wrap'>
                    <div id="page-heading">
                        <div id="breadcrumbs" class="breadcrumbs breadcrumbs-fixed">
                            <div class="left" id="breadCrumb"></div>
                        </div>
                    </div>
                    <div class="container" id="main_navigation_bar"></div>
                </div>
            </div>
            <footer role="contentinfo">
                <div class="clearfix">
                    <ul class="list-unstyled list-inline pull-left"><li><a href="http://www.dcomet.com" target="_blank">dComet Technologies</a> &copy; 2016</li></ul>
                    <button class="pull-right btn btn-inverse-alt btn-xs hidden-print" id="back-to-top"><i class="fa fa-arrow-up"></i></button>
                </div>
            </footer>
        </div>
        <script type='text/javascript' src='js/jquery/jquery.validate.min.js'></script> 
        <script type='text/javascript' src='js/jquery/bootstrap.min.js'></script>
        <script type='text/javascript' src='js/jquery/jquery.nicescroll.min.js'></script> 
        <script type='text/javascript' src='js/jquery/enquire.js'></script>  
        <script type='text/javascript' src='js/application.js'></script>
        <script type='text/javascript' src='js/plugins/datatables/jquery.dataTables.min.js'></script> 
        <script type='text/javascript' src='js/plugins/datatables/dataTables.bootstrap.js'></script> 
        <script type='text/javascript' src='js/plugins/form-daterangepicker/moment.min.js'></script> 
        <script type='text/javascript' src="js/plugins/form-datepicker/bootstrap-datepicker.js"></script>
    </body>
</html>