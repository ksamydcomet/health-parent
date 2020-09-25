var dcomethealth = dcomethealth || {};
var visitData = {};
dcomethealth.OPDDashboard = (function () {
    var id = "OPDDashboard";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            dcomethealth.DashBoardResource.getTodayVisit(function (data) {
                $.each(data, function (pIdx, dOpdPatient) {
                    $("#totalNoOfRegistration").text(!!dOpdPatient.totalNoOfRegistration ? dOpdPatient.totalNoOfRegistration : 0);
                    $("#totalNoOfVisited").text(!!dOpdPatient.totalNoOfVisited ? dOpdPatient.totalNoOfVisited : 0);
                    $("#totalNoOfMale").text(!!dOpdPatient.totalNoOfMale ? dOpdPatient.totalNoOfMale : 0);
                    $("#totalNoOfFemale").text(!!dOpdPatient.totalNoOfFemale ? dOpdPatient.totalNoOfFemale : 0);
                });
            });
//            dcomethealth.PatientResource.searchVisit({"createdDateTime": moment().format('DD-MM-YYYY 00:00:00')}, function (data2) {
            dcomethealth.PatientResource.searchVisit({"visDate": moment().format('DD-MM-YYYY')}, function (data2) {
                visitData = data2;
            });

            thisWeekVisit();
            todayReferral();
        });
    }
    function openPatView(val) {
        $("#pat_details").empty();
        if (parseInt(val) === 1) {
            dcomethealth.PatientResource.searchPatient({"patRegDate": moment().format('DD-MM-YYYY')}, function (data) {
                if (!!data && data !== "" && parseInt($('#totalNoOfRegistration').text()) !== 0) {
                    $('#opdHeadView').removeClass('hidden');
                    $('#opdDashboard').addClass('hidden');
                    $.each(data, function (i, pat) {
                        $("#pat_details").append('<tr><td>' + pat.patMrnNo + '</td><td>' + pat.patFullName + '</td><td>' + pat.patPhoneNo + '</td></tr>');
                    });
                } else {
                    alert("No data Found");
                }
            });
        }
        if (parseInt(val) === 2) {
            dcomethealth.PatientResource.searchPatient({"patRegDate": moment().format('DD-MM-YYYY')}, function (data) {
                if (!!data && data !== "" && parseInt($('#totalNoOfVisited').text()) !== 0) {
                    $('#opdHeadView').removeClass('hidden');
                    $('#opdDashboard').addClass('hidden');
                    $.each(data, function (i, pat) {
                        $.each(visitData, function (i2, visit) {
                            if (parseInt(pat.id) === parseInt(visit.visPatRid)) {
                                $("#pat_details").append('<tr><td>' + pat.patMrnNo + '</td><td>' + pat.patFullName + '</td><td>' + pat.patPhoneNo + '</td></tr>');
                            }
                        });
                    });
                } else {
                    alert("No data Found");
                }
            });
        }
        if (parseInt(val) === 3) {
            dcomethealth.PatientResource.searchPatient({"patRegDate": moment().format('DD-MM-YYYY'), "patGenderIndex": 1}, function (data) {
                if (!!data && data !== "" && parseInt($('#totalNoOfMale').text()) !== 0) {
                    $('#opdHeadView').removeClass('hidden');
                    $('#opdDashboard').addClass('hidden');
                    $.each(data, function (i, pat) {
                        $("#pat_details").append('<tr><td>' + pat.patMrnNo + '</td><td>' + pat.patFullName + '</td><td>' + pat.patPhoneNo + '</td></tr>');
                    });
                } else {
                    alert("No data Found");
                }
            });
        }
        if (parseInt(val) === 4) {
            dcomethealth.PatientResource.searchPatient({"patRegDate": moment().format('DD-MM-YYYY'), "patGenderIndex": 2}, function (data) {
                if (!!data && data !== "" && parseInt($('#totalNoOfFemale').text()) !== 0) {
                    $('#opdHeadView').removeClass('hidden');
                    $('#opdDashboard').addClass('hidden');
                    $.each(data, function (i, pat) {
                        $("#pat_details").append('<tr><td>' + pat.patMrnNo + '</td><td>' + pat.patFullName + '</td><td>' + pat.patPhoneNo + '</td></tr>');
                    });
                } else {
                    alert("No data Found");
                }
            });
        }
    }
    function exportExcel(val) {
        var htmltable = document.getElementById('opdTable');
        var html = htmltable.outerHTML;
        if (parseInt(val) === 1) {
            var a = document.createElement('a');
            var data_type = 'data:application/vnd.ms-excel';
            a.href = data_type + ', ' + encodeURIComponent(html);
            a.download = 'OPDDetails.xls';
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
    function weekVisitRedraw(totalNoOfRegistration, totalNoOfVisited) {
        var plot_statistics = $.plot($("#site-statistics"), [{
                data: totalNoOfRegistration,
                label: "Registration Count"
            }, {
                data: totalNoOfVisited,
                label: "Visitor Count"
            }], {
            series: {
                lines: {
                    show: true,
                    lineWidth: 1.5,
                    fill: 0.05
                },
                points: {
                    show: true
                },
                shadowSize: 0
            },
            grid: {
                labelMargin: 10,
                hoverable: true,
                clickable: true,
                borderWidth: 0
            },
            colors: ["#a6b0c2", "#71a5e7"],
            xaxis: {
                tickColor: "transparent",
                ticks: [[7, "S"], [1, "M"], [2, "T"], [3, "W"], [4, "T"], [5, "F"], [6, "S"]],
                tickDecimals: 0,
                autoscaleMargin: 0,
                font: {
                    color: '#8c8c8c',
                    size: 12
                }
            },
            yaxis: {
                ticks: 4,
                tickDecimals: 0,
                tickColor: "#e3e4e6",
                font: {
                    color: '#8c8c8c',
                    size: 12
                },
                tickFormatter: function (val, axis) {
                    if (val > 999) {
                        return (val / 1000) + "K";
                    } else {
                        return val;
                    }
                }
            },
            legend: {
                labelBoxBorderColor: 'transparent'
            }
        });
    }
    function thisWeekVisit() {
        dcomethealth.DashBoardResource.getWeekVisit(function (data) {
            var totalNoOfRegistration = [];
            var totalNoOfVisited = [];
            $.each(data, function (pIdx, dOpdPatient) {
                var noOfRegistration = [];
                noOfRegistration.push(dOpdPatient.id);
                noOfRegistration.push(dOpdPatient.totalNoOfRegistration);
                totalNoOfRegistration.push(noOfRegistration);
                var noOfVisited = [];
                noOfVisited.push(dOpdPatient.id);
                noOfVisited.push(dOpdPatient.totalNoOfVisited);
                totalNoOfVisited.push(noOfVisited);
            });
            weekVisitRedraw(totalNoOfRegistration, totalNoOfVisited);
        });
    }
    function previousWeekVisit() {
        dcomethealth.DashBoardResource.getPreviousWeekVisit(function (data) {
            var totalNoOfRegistration = [];
            var totalNoOfVisited = [];
            $.each(data, function (pIdx, dOpdPatient) {
                var noOfRegistration = [];
                noOfRegistration.push(dOpdPatient.id);
                noOfRegistration.push(dOpdPatient.totalNoOfRegistration);
                totalNoOfRegistration.push(noOfRegistration);
                var noOfVisited = [];
                noOfVisited.push(dOpdPatient.id);
                noOfVisited.push(dOpdPatient.totalNoOfVisited);
                totalNoOfVisited.push(noOfVisited);
            });
            weekVisitRedraw(totalNoOfRegistration, totalNoOfVisited);
        });
    }
    function todayReferral() {
        dcomethealth.DashBoardResource.getTodayReferral(function (data) {
            var referrals = [];
            $.each(data, function (pIdx, referral) {
                if (!!referral) {
                    referrals.push({label: referral.referralValue, data: referral.totalNoOfCount});
                }
            });
            $.plot($("#interactive"), referrals,
                    {
                        series: {
                            pie: {
                                show: true
                            }
                        },
                        grid: {
                            hoverable: true,
                            clickable: true
                        },
                        legend: {
                            show: false
                        }
                    });
            $("#interactive").bind("plothover", function pieHover(event, pos, obj) {
                if (!obj)
                    return;
                percent = parseFloat(obj.series.percent).toFixed(2);
                $("#interactivehoverdata").html('<span style="font-weight: bold; color: ' + obj.series.color + '">' + obj.series.label + ' (' + percent + '%)</span>');
            });

        });
    }
    function refreshData() {
    }
    return {
        init: init,
        openPatView: openPatView,
        weekVisitRedraw: weekVisitRedraw,
        thisWeekVisit: thisWeekVisit,
        previousWeekVisit: previousWeekVisit,
        todayReferral: todayReferral,
        refreshData: refreshData

    };
}());
dcomethealth.OPDDashboard.init();