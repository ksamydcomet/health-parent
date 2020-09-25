var dcomethealth = dcomethealth || {};
dcomethealth.Reports = (function () {
    var id = "Reports";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.datatypes.init($("#" + id));
            featureAppend();
        });
    }
    function featureAppend() {
        $("#frontOfficeTbl").empty();
        $("#billingTbl").empty();
        $("#laboratoryTbl").empty();

        if (dcomethealth.security.isPrivillage('NewVisitsReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'NewVisits\');\"><td>New Visits</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('RepeatVisitsReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'RepeatVisits\');\"><td>Repeat Visits</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('TotalVisitsReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'TotalVisits\');\"><td>Total Visits</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('NewRegistrationReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'New Registration\');\"><td>New Registration Report</td></tr>');
        }
//        if (dcomethealth.security.isPrivillage('ReviewVisitReport') == true) {
//            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Review Visit\');\"><td>Review Visit Report</td></tr>');
//        }
        if (dcomethealth.security.isPrivillage('AppointmentReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Appointment\');\"><td>Appointment Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('DateWiseReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Date Wise Visit\');\"><td>Date Wise Visit Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('SpecialityWiseReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Speciality Wise\');\"><td>Speciality Wise Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('CancelledAppointmentReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Cancelled Appointment\');\"><td>Cancelled Appointment Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('ReferralTypeReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Referral Type\');\"><td>Referral Type Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('DoctorWiseReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Doctor Wise\');\"><td>Doctor Wise Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('AppointmentWiseReport') == true) {
            $("#frontOfficeTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Appointment Wise\');\"><td>Appointment Wise Report</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('SalesReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Billing Sales\');\"><td>Sales</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('CollectionsReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Collections\');\"><td>Collections</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('UnitSalesReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Unit Sales\');\"><td>Unit Sales</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('PendingSalesReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Pending Sales\');\"><td>Pending Sales</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('ServiceSalesReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Service Sales\');\"><td>Service Sales</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('PayerSalesReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Payer Sales\');\"><td>Payer Sales</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('PharmacySalesReport') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Pharmacy Sales\');\"><td>Pharmacy Sales</td></tr>');
        }
//        if (dcomethealth.security.isPrivillage('WiseCollectionReport') == true) {
//            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Wise Collection Report\');\"><td>Wise Collection Report</td></tr>');
//        }
        if (dcomethealth.security.isPrivillage('CancelledBills') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Cancelled Bills\');\"><td>Cancelled Bills</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('DiscountBills') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Discount Bills\');\"><td>Discount Bills</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('Advance') == true) {
            $("#billingTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Advance\');\"><td>Advance</td></tr>');
        }
        if (dcomethealth.security.isPrivillage('LaboratorySalesReport') == true) {
            $("#laboratoryTbl").append('<tr onclick=\"dcomethealth.Reports.callReport(\'Laboratory Report\');\"><td>Laboratory Report</td></tr>');
        }
    }
    function callReport(val) {
        dcomethealth.util.loadpage('ReportView');
//        dcomethealth.util.loadpage(val);        
        dcomethealth.opdVal = val;
    }
    function refreshData() {
    }
    return {
        init: init,
        callReport: callReport,
        refreshData: refreshData,
        featureAppend: featureAppend
    };
}());
dcomethealth.Reports.init();
